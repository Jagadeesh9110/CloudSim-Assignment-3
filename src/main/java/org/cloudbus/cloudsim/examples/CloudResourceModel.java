package org.cloudbus.cloudsim.examples;

import java.text.DecimalFormat;
import java.util.*;
import org.cloudbus.cloudsim.*;

import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.provisioners.*;

public class CloudResourceModel {

    private static List<Vm> vmList;
    private static List<Cloudlet> cloudletList;

    public static void main(String[] args) {

        try {
            /*  Initialize CloudSim  */
            int numUsers = 1;
            Calendar calendar = Calendar.getInstance();
            boolean traceFlag = false;

            CloudSim.init(numUsers, calendar, traceFlag);

            /* Create Datacenter (Host) */
            Datacenter datacenter = createDatacenter("Datacenter_0");

            /*  Create Broker */
            DatacenterBroker broker = new DatacenterBroker("Broker_0");
            int brokerId = broker.getId();

            /*  Create VM Configurations */
            vmList = new ArrayList<>();

            // Small VM
            Vm vmSmall = new Vm(0, brokerId, 500, 1, 512, 1000, 10000,
                    "Xen", new CloudletSchedulerTimeShared());

            // Medium VM
            Vm vmMedium = new Vm(1, brokerId, 1000, 2, 2048, 1000, 10000,
                    "Xen", new CloudletSchedulerTimeShared());

            // Large VM
            Vm vmLarge = new Vm(2, brokerId, 2000, 4, 4096, 1000, 10000,
                    "Xen", new CloudletSchedulerTimeShared());

            vmList.add(vmSmall);
            vmList.add(vmMedium);
            vmList.add(vmLarge);
            broker.submitVmList(vmList);

            // Create Cloudlets
            cloudletList = new ArrayList<>();
            UtilizationModel utilizationModel = new UtilizationModelFull();

            Cloudlet c1 = new Cloudlet(0, 100000, 1, 300, 300,
                    utilizationModel, utilizationModel, utilizationModel);
            c1.setUserId(brokerId);
            c1.setVmId(0);

            Cloudlet c2 = new Cloudlet(1, 100000, 2, 300, 300,
                    utilizationModel, utilizationModel, utilizationModel);
            c2.setUserId(brokerId);
            c2.setVmId(1);

            Cloudlet c3 = new Cloudlet(2, 100000, 4, 300, 300,
                    utilizationModel, utilizationModel, utilizationModel);
            c3.setUserId(brokerId);
            c3.setVmId(2);

            cloudletList.add(c1);
            cloudletList.add(c2);
            cloudletList.add(c3);
            broker.submitCloudletList(cloudletList);

            // Run Simulation
            CloudSim.startSimulation();
            List<Cloudlet> results = broker.getCloudletReceivedList();
            CloudSim.stopSimulation();

            // STEP 7: Metrics
            printVmHostMapping(results);
            analyzeHostUtilization(vmList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* ================= DATACENTER ================= */

    private static Datacenter createDatacenter(String name) {

        List<Host> hostList = new ArrayList<>();

        List<Pe> peList = new ArrayList<>();
        int mips = 10000;

        for (int i = 0; i < 8; i++) {
            peList.add(new Pe(i, new PeProvisionerSimple(mips)));
        }

        Host host = new Host(
                0,
                new RamProvisionerSimple(16384), // 16 GB RAM
                new BwProvisionerSimple(10000),
                1000000,
                peList,
                new VmSchedulerTimeShared(peList)
        );

        hostList.add(host);

        DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
                "x86", "Linux", "Xen",
                hostList, 10.0, 3.0, 0.05, 0.001, 0.0
        );

        try {
            return new Datacenter(name, characteristics,
                    new VmAllocationPolicySimple(hostList),
                    new LinkedList<>(), 0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* ================= METRIC 1 ================= */

    private static void printVmHostMapping(List<Cloudlet> list) {

        System.out.println("\n========== METRIC 1: VM-TO-HOST MAPPING ==========");
        System.out.println("VM ID | VM Type  | Host ID | Status");
        System.out.println("---------------------------------------------");

        for (Cloudlet c : list) {
            String type = (c.getVmId() == 0) ? "Small"
                    : (c.getVmId() == 1) ? "Medium" : "Large";

            System.out.println(
                    c.getVmId() + "     | " + type +
                            "    | Host 0  | SUCCESS"
            );
        }
    }

    /* ================= METRIC 2 & 3 ================= */

    private static void analyzeHostUtilization(List<Vm> vms) {

        System.out.println("\n========== METRIC 2 & 3: HOST UTILIZATION ANALYSIS ==========");

        double hostRamCapacity = 16384;   // MB
        double hostCoreCapacity = 8;      // Cores
        double hostStorageCapacity = 1000000; // MB

        double usedRam = 0;
        double usedCores = 0;
        double usedStorage = 0;

        for (Vm vm : vms) {
            usedRam += vm.getRam();
            usedCores += vm.getNumberOfPes();
            usedStorage += vm.getSize();
        }

        double ramUtil = (usedRam / hostRamCapacity) * 100;
        double cpuUtil = (usedCores / hostCoreCapacity) * 100;
        double storageUtil = (usedStorage / hostStorageCapacity) * 100;

        DecimalFormat df = new DecimalFormat("###.##");

        System.out.println("Host Capacity  : 16384 MB RAM | 8 CPU Cores | 1000000 MB Storage");
        System.out.println("VM Allocation  : " + (int) usedRam + " MB RAM | "
                + (int) usedCores + " CPU Cores | "
                + (int) usedStorage + " MB Storage");

        System.out.println("------------------------------------------------------------");
        System.out.println(">> CPU Utilization     : " + df.format(cpuUtil) + "%");
        System.out.println(">> RAM Utilization     : " + df.format(ramUtil) + "%");
        System.out.println(">> Storage Utilization : " + df.format(storageUtil) + "%");
        System.out.println("============================================================");
    }

}

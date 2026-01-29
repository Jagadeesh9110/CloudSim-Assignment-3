# CloudSim Assignment 3 – Model Cloud Data Center Resources

## Course
Cloud Computing & Visualization

## Student Details
- **Name:** M. Jagadeeswar Reddy  
- **Roll No:** 23BDS033  

---

## Assignment Objective
This assignment models cloud data center resources using the **CloudSim 5.0 (Classic Toolkit)**.  
The objective is to define a physical Host, create multiple Virtual Machines (VMs), execute workloads (Cloudlets), and analyze system-level resource utilization such as CPU, RAM, and storage.

---

## Technologies Used
- **Simulator:** CloudSim 5.0 (Classic Toolkit)
- **Programming Language:** Java
- **IDE:** IntelliJ IDEA
- **JDK:** OpenJDK 8 (Eclipse Temurin)
- **Visualization Tool:** Microsoft Excel

---

## Resource Configuration Strategy

### Host Configuration (Physical Infrastructure)
A single high-capacity Host is modeled to represent a powerful physical server in a cloud data center.

- RAM: 16,384 MB (16 GB)
- CPU Cores: 8
- Processing Power: 10,000 MIPS per core
- Storage: 1,000,000 MB

**Rationale:**  
This configuration allows multiple virtual machines to be hosted simultaneously without immediate resource contention.

---

### VM Configurations (Virtual Resources)
To simulate a multi-tenant environment, three VM types are defined:

| VM Type | CPU Cores | RAM (MB) | Description |
|------|----------|----------|-------------|
| Small  | 1 | 512  | Low resource usage |
| Medium | 2 | 2048 | Moderate usage |
| Large  | 4 | 4096 | High performance |

---

## Host Utilization Analysis

### Metric 1: VM-to-Host Mapping
All three VMs (Small, Medium, Large) are successfully mapped to **Host 0**.  
The VM allocation policy accommodates all VMs since their combined resource requirements do not exceed the host capacity.

---

### Metric 2: CPU & RAM Utilization

| Resource | Used | Total | Utilization |
|-------|------|------|-------------|
| CPU Cores | 7 | 8 | 87.5% |
| RAM | 6,656 MB | 16,384 MB | 40.62% |
| Storage | 30,000 MB | 1,000,000 MB | 3% |

**Conclusion:**  
The system is **CPU-constrained** due to high CPU utilization, while memory and storage remain underutilized. This indicates limited remaining CPU capacity but sufficient availability for memory-intensive workloads.

---

## Simulation Outputs

### Source Code Implementation
The simulation logic is implemented in:

src/org/cloudbus/cloudsim/examples/CloudResourceModel.java


This file defines:
- Host configuration
- VM creation
- Cloudlet submission
- Resource utilization analysis
- Console-based reporting of metrics

---

### Console Output
The simulation console output displays:
- VM-to-Host mapping
- Cloudlet execution lifecycle
- Final CPU, RAM, and storage utilization percentages

**Note:**  
Although the console displays the message *"Starting CloudSim version 3.0"*, this is expected behavior.  
CloudSim 5.0 is built on the classic CloudSim 3.x core with extended features, and the log message does not indicate a version mismatch.

---

## Utilization Graphs
The following Excel-based visualizations are included in the assignment report:

1. **Host Resource Utilization (Pie Chart)**  
   - CPU: 87.5%
   - RAM: 40.62%
   - Storage: 3%  
   This visualization highlights CPU as the dominant bottleneck.

2. **VM-wise CPU Core Allocation (Bar Chart)**  
   Shows CPU core distribution across Small, Medium, and Large VMs.

3. **VM-to-Host Mapping Chart**  
   Confirms that all VMs are successfully allocated to a single host.

All graphs represent the final state of the simulation.

---

## Project Structure
src/
└── org/
└── cloudbus/
└── cloudsim/
└── examples/
      └── CloudResourceModel.java


---

## How to Run
1. Import the project into **IntelliJ IDEA**
2. Add CloudSim 5.0 libraries to the project classpath
3. Run `CloudResourceModel.java`
4. Observe the console output for simulation results

---

## Notes
- Only assignment-specific source code is included in this repository
- The CloudSim framework is treated as an external dependency
- Results are analyzed post-simulation using Excel

---

## GitHub Repository
🔗 https://github.com/Jagadeesh9110/CloudSim-Assignment-3

---

**End of Assignment 3**

# CloudSim Assignment 3 – Model Cloud Data Center Resources

## Course
Cloud Computing & Visualization

## Student Details
- **Name:** M. Jagadeeswar Reddy  
- **Roll No:** 23BDS033  

## Assignment Objective
This assignment focuses on modeling cloud data center resources using the CloudSim simulator. The objective is to define physical hosts and virtual machines, execute workloads, and analyze resource utilization metrics.

## Technologies Used
- **Simulator:** CloudSim (3.0.x)
- **Language:** Java
- **IDE:** IntelliJ IDEA
- **JDK Version:** OpenJDK 8 (Eclipse Temurin)

## Implementation Overview
The core implementation is provided in the `CloudResourceModel.java` file, which performs the following tasks:

- Defines a single high-capacity Host with CPU, RAM, and Storage resources
- Creates three VM configurations:
  - Small VM
  - Medium VM
  - Large VM
- Submits Cloudlets to simulate workload execution
- Analyzes and prints:
  - VM-to-Host mapping
  - CPU utilization
  - RAM utilization
  - Storage utilization

## Project Structure
src/
└── org/
└── cloudbus/
└── cloudsim/
└── examples/
└── CloudResourceModel.java


## Output Metrics
The simulation generates the following metrics:
- VM-to-Host Mapping
- CPU Utilization (%)
- RAM Utilization (%)
- Storage Utilization (%)

These metrics are visualized using Excel charts (Pie Chart and Bar Chart) as part of the assignment report.

## How to Run
1. Import the project into IntelliJ IDEA
2. Ensure CloudSim libraries are added to the project
3. Run `CloudResourceModel.java`
4. Observe the console output for simulation results

## Notes
- Only the assignment-specific source code is included in this repository.
- The CloudSim framework itself is treated as an external dependency and is not included.

---

**End of Assignment 3**

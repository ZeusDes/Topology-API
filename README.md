# Topology-API

This API Provides the functionality to Access, manage, store and delete device Topologies. 

> **Important Note:**  These functionalities are providied through *TopologyAPI.java* 

## Main Functionalities

- Read Topology from JSON file and store it memory

- Write a certain Topology from memory to JSON file

- Delete a certain Topology from memory

- List all Topologies Exists in memory

- List all Components exists in certain topology

- List all Components in a topology connected to a certain netlist

## API Methods

```java
void readJSON(String full_Path_of_File);
void writeJSON(String TopologyId);
void deleteTopology(String TopologyId);
ArrayList<Topology> queryTopology();
ArrayList<Component> queryDevices(String TopologyId);
ArrayList<Component> queryDevicesWithNetlistNode(String TopologyId, String netlistId);
```

> **Important Note:** User must enter absolute path to JSON file while using *readJSON(String full_Path_of_File)* method, Or can use relative path from the Project Directory



## Testing

This project has been tested by [JUnit 5](https://junit.org/junit5/) framework, including the testing source code in [test](https://github.com/ZeusDes/Topology-API/blob/main/src/test) project

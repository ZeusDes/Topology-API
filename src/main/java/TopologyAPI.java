import Components.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.String;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author Muhammad Ahmad
 * @version 1.0.0, 01/01/22
 */
public class TopologyAPI {
    private String id;
    private static ArrayList<Topology> topologyMem;

    TopologyAPI(){
        topologyMem = new ArrayList<Topology>();
    }

    /**
     * Read JSON File and Store Topology to memory
     * @param filename Take filename from user or file full path
     * @throws IOException throws Parameter Errors
     * @throws ParseException throws parsing JSON file Errors
     */
    public void readJSON(String filename) throws IOException, ParseException {
        JSONParser json = new JSONParser();
        FileReader readFile = new FileReader(filename);
        Object obj = json.parse(readFile);
        JSONObject jsonObject = (JSONObject) obj;
        Topology top = new Topology(jsonObject);
        topologyMem.add(top);
    }

    /**
     * Write Topology to JSON File
     * @param id Take the id of Topology to write from memory
     * @throws IOException Handles Input errors
     */
    public void writeJSON(String id) throws IOException {
        for(int i = 0; i < topologyMem.size(); i++){
            if(topologyMem.get(i).getId().equals(id)) {
                JSONObject Obj = topologyMem.get(i).getObj();
                FileWriter file = new FileWriter(topologyMem.get(i).getId()+".json");
                file.write(Obj.toJSONString());
                file.close();
                System.out.println("Done");
                break;
            }
        }
    }

    /**
     * Delete certain Topology from memory
     * @param TopologyId ID of the topology to be deleted
     */
    public void deleteTopology(String TopologyId){
        for(int i = 0; i < topologyMem.size(); i++){
            if(topologyMem.get(i).getId().equals(TopologyId)){
                topologyMem.remove(i);
                break;
            }
        }
    }

    /**
     * All Topologies in memory
     * @return ArrayList of topologies
     */
    public ArrayList<Topology> queryTopologies(){
        return topologyMem;
    }

    /**
     * All Components in Topology
     * @param TopologyId ID of topology requested to list its devices
     * @return ArrayList of components
     */
    public ArrayList<Component> queryDevices(String TopologyId){
        for(int i = 0; i < topologyMem.size(); i++){
            if(topologyMem.get(i).getId().equals(TopologyId)){
                return topologyMem.get(i).getComponents();
            }
        }
        return null;
    }

    /**
     * All components from certain topology connected to certain Netlist Node
     * @param TopologyId ID of Topology to make Query on
     * @param NodeID ID of Netlist Node to obtain components connected to it
     * @return List of Components
     */
    public ArrayList<Component> queryDevicesWithNetlistNode(String TopologyId, String NodeID){
        for(int i = 0; i < topologyMem.size(); i++){
            if(topologyMem.get(i).getId().equals(TopologyId)){
                return topologyMem.get(i).getNetlist(NodeID);
            }
        }
        return null;
    }
}

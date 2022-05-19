import Components.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;

/**
 * @author Muhammad Ahmad
 * @version 1.0.0, 01/01/22
 */
public class Topology {
    // Store topologyId, list of topology components and JSONObject to return it back to API when needed
    private final String id;
    private final ArrayList<Component> components;
    private final JSONObject obj;

    public Topology(JSONObject json) {
        components = new ArrayList<Component>();
        this.obj = json;
        this.id = json.get("id").toString(); // Get id value
        JSONArray arr = (JSONArray)json.get("components"); // Get Components list
        // iterating through components list and make the appropriate component object, and add them to components list
        for(int i = 0; i < arr.size(); i++){
            JSONObject comp = (JSONObject) arr.get(i);
            if(comp.get("type").toString().equals("nmos") || comp.get("type").toString().equals("pmos") || comp.get("type").toString().equals("cmos")){
                Transistors trans = new Transistors((JSONObject)arr.get(i));
                components.add(trans);
            }
            else{
                PassiveComponents elem = new PassiveComponents((JSONObject)arr.get(i));
                components.add(elem);
            }
        }
    }

    /**
     * Get Components List connected to certain Netlist Node
     * @param netlistId Netlist Node to return components connected to
     * @return ArrayList of components
     */
    public ArrayList<Component> getNetlist(String netlistId){
        ArrayList<Component> list = null;
        for(int i = 0; i < components.size(); i++){
            if(components.get(i).isConnected(netlistId)) list.add(components.get(i));
        }
        return list;
    }

    /**
     * Get Topology ID
     * @return String
     */
    public String getId() { return id; }

    /**
     * Get Topology as a JSON ObjectS
     * @return JSONObject
     */
    public JSONObject getObj() {
        return obj;
    }

    /**
     * Get All components in Topology
     * @return ArrayList component
     */
    public ArrayList<Component> getComponents() {
        ArrayList<Component> compList = null;
        for(int i = 0; i < components.size(); i++){
            compList.add(components.get(i));
        }
        return compList;
    }
}

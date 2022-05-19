package Components;
import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * @author Muhammad Ahmad
 * @version 1.0.0, 01/01/22
 */
public class PassiveComponents extends Component{
    private final String t1; // Store the netlist node of t1
    private final String t2; // Store the netlist node of t2
    private final ArrayList<String> val; // Store list of values [default, min, max]

    public PassiveComponents(JSONObject comp) throws NullPointerException {
        super(comp.get("type").toString(), comp.get("id").toString());
        val = new ArrayList<String>();
        JSONObject obj;
        // Get values object according to the type of component
        if(comp.get("type").toString().equals("resistor")) obj = (JSONObject)comp.get("resistance");
        else if(comp.get("type").toString().equals("capacitor")) obj = (JSONObject)comp.get("capacitance");
        else obj = (JSONObject)comp.get("inductance");
        val.add(obj.get("default").toString());
        val.add(obj.get("min").toString());
        val.add(obj.get("max").toString());
        // Get netlist nodes from "netlist" Object
        obj = (JSONObject)comp.get("netlist");
        this.t1 = obj.get("t1").toString();
        this.t2 = obj.get("t2").toString();
    }

    /**
     * Get Netlist Node connected to t1
     * @return String
     */
    public String getT1() {
        return t1;
    }

    /**
     * Get Netlist Node connected to t2
     * @return String
     */
    public String getT2() {
        return t2;
    }

    @Override
    public boolean isConnected(String netlistID) {
        return t1.equals(netlistID) || t2.equals(netlistID);
    }
}

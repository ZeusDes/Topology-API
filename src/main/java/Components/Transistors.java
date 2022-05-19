package Components;
import org.json.simple.JSONObject;
import java.util.ArrayList;

/**
 * @author Muhammad Ahmad
 * @version 1.0.0, 01/01/22
 */
public class Transistors extends Component{
    // Data Members
    private final String drain;
    private final String gate;
    private final String source;
    private final ArrayList<String> val;

    // Transistor Components Constructor
    public Transistors(JSONObject comp){
        super(comp.get("type").toString(), comp.get("id").toString());
        // Get list of values from "m(l)" Object
        val = new ArrayList<String>();
        JSONObject obj = (JSONObject)comp.get("m(l)");
        val.add(obj.get("default").toString());
        val.add(obj.get("min").toString());
        val.add(obj.get("max").toString());
        // Get netlist nodes from "netlist" Object
        obj = (JSONObject)comp.get("netlist");
        this.drain = obj.get("drain").toString();
        this.gate = obj.get("gate").toString();
        this.source = obj.get("source").toString();
    }

    /**
     * Method to Get Drain
     * @return String
     */
    public String getDrain() { return drain; }

    /**
     * Method to Get Gate
     * @return String
     */
    public String getGate() { return gate; }

    /**
     * Method to Get Source
     * @return String
     */
    public String getSource(){ return source; }

    @Override
    public boolean isConnected(String netlistID) {
        return (drain.equals(netlistID)) || (source.equals(netlistID)) || (gate.equals(netlistID));
    }

}

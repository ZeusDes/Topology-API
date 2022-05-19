import junit.framework.TestCase;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class TopologyAPITest extends TestCase {

    public void testWriteJSON() throws IOException, ParseException {
        TopologyAPI myAPI = new TopologyAPI();
        myAPI.readJSON("Topology.json");
        myAPI.writeJSON("top1");
    }

    public void testDeleteTopology() {
        
    }

    public void testQueryTopologies() {
    }

    public void testQueryDevices() {
    }

    public void testQueryDevicesWithNetlistNode() {
    }
}
import Components.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class TopologyAPITest extends TestCase {

    @Test
    public void testReadWriteJSON() throws IOException, ParseException {
        TopologyAPI myAPI = new TopologyAPI();
        myAPI.readJSON("Topology.json");
        myAPI.writeJSON("top1");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode OriginalJson = mapper.readTree(new File("Topology.json"));
        JsonNode NewJson = mapper.readTree(new File("top1.json"));
        boolean isEqual = NewJson.equals(OriginalJson);
        Assert.assertTrue(isEqual);
    }

    @Test
    public void testqueryTopologies() throws IOException, ParseException {
        TopologyAPI myAPI = new TopologyAPI();
        myAPI.readJSON("Topology.json");
        myAPI.readJSON("Topology1.json");
        myAPI.readJSON("Topology3.json");
        ArrayList<Topology> arr= myAPI.queryTopologies();
        assertEquals(arr.get(0).getId(), "top1");
        assertEquals(arr.get(1).getId(), "top2");
        assertEquals(arr.get(2).getId(), "top3");
    }

    @Test
    public void testDeleteTopology() throws IOException, ParseException {
        TopologyAPI myAPI = new TopologyAPI();
        myAPI.readJSON("Topology.json");
        myAPI.readJSON("Topology1.json");
        myAPI.readJSON("Topology3.json");
        myAPI.deleteTopology("top1");
        ArrayList<Topology> arr= myAPI.queryTopologies();
        boolean isFound = false;
        for(Topology i : arr){
            if(i.getId().equals("top1")){
                isFound = true;
                break;
            }
        }
        assertFalse(isFound);
    }

    @Test
    public void testQueryDevices() throws IOException, ParseException {
        TopologyAPI myAPI = new TopologyAPI();
        myAPI.readJSON("Topology1.json");
        ArrayList<Component> arr = myAPI.queryDevices("top2");
        assertEquals(arr.get(0).getComponentId(), "res2");
        assertEquals(arr.get(1).getComponentId(), "m2");
        assertEquals(arr.size(), 2);
    }

    @Test
    public void testQueryDevicesWithNetlistNode() throws IOException, ParseException {
        TopologyAPI myAPI = new TopologyAPI();
        myAPI.readJSON("Topology3.json");
        ArrayList<Component> comp = myAPI.queryDevicesWithNetlistNode("top3", "vss");
        assertEquals(comp.size(), 1);
        assertEquals(comp.get(0).getComponentId(), "m3");
    }
}
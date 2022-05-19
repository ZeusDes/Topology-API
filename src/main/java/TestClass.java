import org.json.simple.parser.ParseException;

import java.io.IOException;

public class TestClass {
    public static void main(String[] args) throws IOException, ParseException {
        TopologyAPI myAPI = new TopologyAPI();
        myAPI.readJSON("Topology.json");
        myAPI.writeJSON("top1");
    }
}

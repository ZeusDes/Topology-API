package Components;

/**
 * @author Muhammad Ahmad
 * @version 1.0.0, 01/01/22
 */
public abstract class Component {
    // Data Members
    private final String componentType;
    private final String componentId;

    // Class Constructor
    Component(String componentType, String componentId) {
        this.componentType = componentType;
        this.componentId = componentId;
    }

    /**
     * Get Component Type
     * @return String
     */
    public String getComponentType(){ return componentType; }

    /**
     * Get Component Id
     * @return String
     */
    public String getComponentId() { return componentId; }

    /**
     * Check if component connected to Netlist Node or not
     * @param netlistID Netlist Node to check terminals connectivity to it
     * @return Boolean
     */
    public abstract boolean isConnected(String netlistID);
}

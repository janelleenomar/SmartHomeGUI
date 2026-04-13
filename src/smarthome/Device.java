package smarthome;
import java.awt.Component;

public abstract class Device {
    protected String name;

    public Device(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Returns the state as a String for the GUI JTextArea
    public abstract String viewState();

    // ADDED 'abstract' BACK HERE:
    public abstract boolean modifySettings(Component parent);

    // Returns a String so the GUI prints what "really happened"
    public abstract String execute();
}
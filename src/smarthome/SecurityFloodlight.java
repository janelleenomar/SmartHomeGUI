package smarthome;

import javax.swing.JOptionPane;
import java.awt.Component;

public class SecurityFloodlight extends SmartLight {

    private String sensitivity = "MEDIUM";

    public SecurityFloodlight() {
        super();
        this.name = "Security Floodlight";
    }

    @Override
    public String viewState() {
        return super.viewState() + " | Sensitivity: " + sensitivity;
    }

    @Override
    public boolean modifySettings(Component parent) {
        String[] options = {"Trigger Motion Alert", "Change Sensitivity"};
        int choice = JOptionPane.showOptionDialog(parent, "Select Action", name, 0, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            JOptionPane.showMessageDialog(parent, "ALERT: Motion detected by Floodlight!");
            return true; // The behavior was triggered
        } else if (choice == 1) {
            String[] levels = {"LOW", "MEDIUM", "HIGH"};
            int sChoice = JOptionPane.showOptionDialog(parent, "Sensitivity Level", name, 0, JOptionPane.QUESTION_MESSAGE, null, levels, sensitivity);
            if (sChoice != -1) {
                sensitivity = levels[sChoice];
                return true; // Sensitivity changed
            }
        }
        return false;
    }

    @Override
    public String execute() {
        this.brightness = 100;
        return "brightness set to full + arm motion detection";
    }
}
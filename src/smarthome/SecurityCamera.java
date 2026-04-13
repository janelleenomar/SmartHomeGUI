package smarthome;

import javax.swing.JOptionPane;
import java.awt.Component;

public class SecurityCamera extends Device {

    private boolean powerOn = true;
    private boolean recording = false;

    public SecurityCamera() {
        super("Security Camera");
    }

    @Override
    public String viewState() {
        return "Power: " + (powerOn ? "ON" : "OFF") + " | Recording: " + (recording ? "ACTIVE" : "STOPPED");
    }

    @Override
    public boolean modifySettings(Component parent) {
        String[] options = {"Toggle Power", "Start/Stop Recording"};
        int choice = JOptionPane.showOptionDialog(parent, "Camera Controls", name,
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            powerOn = !powerOn;
            return true; // Power toggled
        } else if (choice == 1) {
            if (powerOn) {
                recording = !recording;
                return true; // Recording toggled
            } else {
                JOptionPane.showMessageDialog(parent, "Error: Camera is Powered Off");
                return false; // Action failed because power was off
            }
        }
        return false; // User closed the dialog
    }

    @Override
    public String execute() {
        if (powerOn) {
            recording = true;
            return "Recording turned on";
        } else{
            return "Security Camera is off";
        }
        
    }
}
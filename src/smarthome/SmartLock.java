package smarthome;

import javax.swing.JOptionPane;
import java.awt.Component;

public class SmartLock extends Device {

    private boolean locked = true;
    private final int pin = 1234; // 'final' since the PIN doesn't change here
    private int failedAttempts = 0; // Moved to the top with other fields

    public SmartLock() {
        super("Smart Lock");
    }

    @Override
    public String viewState() {
        return "Locked: " + (locked ? "YES" : "NO") + " | Failed Attempts: " + failedAttempts;
    }

    @Override
    public boolean modifySettings(Component parent) {
        String input = JOptionPane.showInputDialog(parent, "Enter PIN to unlock:");
        if (input != null && !input.isEmpty()) {
            try {
                int enteredPin = Integer.parseInt(input);
                if (enteredPin == pin) {
                    locked = false;
                    return true; // Success
                } else {
                    failedAttempts++;
                    return true; // Even a failed attempt is a "change" in state (logging)
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(parent, "Numbers only!");
            }
        }
        return false;
    }

    @Override
    public String execute() {
        this.locked = true;
        return "Device is now LOCKED."; // This matches your table perfectly
    }
}
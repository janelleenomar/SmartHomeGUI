package smarthome;

import javax.swing.JOptionPane;
import java.awt.Component;

public class Thermostat extends Device {

    private int currentTemp = 22;
    private int targetTemp = 22;

    public Thermostat() {
        super("Thermostat");
    }

    @Override
    public String viewState() {
        return "Current: " + currentTemp + "°C | Target: " + targetTemp + "°C";
    }

    @Override
    public boolean modifySettings(Component parent) {
        String input = JOptionPane.showInputDialog(parent, "Set target temp (16-30°C):", targetTemp);
        if (input != null) {
            try {
                int val = Integer.parseInt(input);
                if (val >= 16 && val <= 30) {
                    targetTemp = val;
                    return true; // Return true here!
                } else {
                    JOptionPane.showMessageDialog(parent, "Invalid Range!");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(parent, "Invalid Number!");
            }
        }
        return false; // Return false if they hit cancel or error
    }

    @Override
    public String execute() {
        currentTemp = targetTemp;
        return "activated target temp";
    }
}
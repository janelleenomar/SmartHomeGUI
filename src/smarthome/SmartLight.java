package smarthome;

import javax.swing.JOptionPane;
import java.awt.Component;

public class SmartLight extends Device {

    protected int brightness = 50;
    protected String color = "White";

    public SmartLight() {
        super("Smart Light");
    }

    @Override
    public String viewState() {
        return "Brightness: " + brightness + "% | Color: " + color;
    }

    @Override
    public boolean modifySettings(Component parent) {
        String[] options = {"Dim by Percentage", "Change Color"};
        int choice = JOptionPane.showOptionDialog(parent, "Select Action", name, 0, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            String input = JOptionPane.showInputDialog(parent, "Enter percentage to dim (0-100):");

            if (input != null) {
                try {
                    int dim = Integer.parseInt(input);
                    if (dim <= 0 || dim > 100) {
                        JOptionPane.showMessageDialog(parent, "Invalid number.");
                        return false;
                    }
                    this.brightness = Math.max(0, this.brightness - (this.brightness * dim / 100));
                    return true; // Successfully dimmed
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(parent, "Invalid number.");
                }
            }

        } else if (choice == 1) {
            String newColor = JOptionPane.showInputDialog(parent, "Enter color:", color);
            if (newColor != null) {
                color = newColor;
                return true; // Successfully changed color
            }
        }
        return false; // User cancelled or error occurred
    }

    @Override
    public String execute() {
        this.brightness = 100;
        return "Brightness has been set to full";
    }
}

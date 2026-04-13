package smarthome;

import javax.swing.JOptionPane;
import java.awt.Component;

public class SmartSpeaker extends Device {

    private int volume = 50;
    private String track = "None";
    private boolean playing = false;

    public SmartSpeaker() {
        super("Smart Speaker");
    }

    @Override
    public String viewState() {
        return "Volume: " + volume + " | Track: " + track + " | Playing: " + playing;
    }

    @Override
    public boolean modifySettings(Component parent) {
        String[] options = {"Play", "Pause", "Skip", "Set Volume"};
        int choice = JOptionPane.showOptionDialog(parent, "Speaker Controls", name, 0,
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        if (choice != -1) { // If they picked any button
            if (choice == 0) {
                playing = true;
            } else if (choice == 1) {
                playing = false;
            } else if (choice == 2) {
                track = JOptionPane.showInputDialog(parent, "Enter track:");
            } else if (choice == 3) {
                String v = JOptionPane.showInputDialog(parent, "Volume (0-100):");
                if (v != null) {
                    volume = Integer.parseInt(v);
                }
            }
            return true; // Picked a button, so something changed!
        }
        return false; // Closed window/Cancelled
    }

    @Override
    public String execute() {
        this.playing = true;
        return "Speaker playback resumed.";
    }
}
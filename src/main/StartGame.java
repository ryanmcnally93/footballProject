package main;
import javax.swing.SwingUtilities;

import visuals.MatchFrames.GameWindow;

public class StartGame {

	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            initialSetup start = new initialSetup();
        });
    }

}

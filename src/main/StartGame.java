package main;
import javax.swing.SwingUtilities;

public class StartGame {

	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	initialSetup start = new initialSetup();
        });
    }
}
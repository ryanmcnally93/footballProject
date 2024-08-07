package main;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import visuals.MatchFrames.GameWindow;

public class StartGame {

	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	initialSetup start = new initialSetup();
        });
        
        
        
    }

}

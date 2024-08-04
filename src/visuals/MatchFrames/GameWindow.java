package visuals.MatchFrames;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class GameWindow extends JFrame {
    private static final long serialVersionUID = 157462818597402652L;

    public GameWindow() {
    	setTitle("My Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // A page not included in Match Frames
        
//        JPanel otherPanel = new JPanel();
//        otherPanel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
//        otherPanel.setBackground(Color.LIGHT_GRAY);
//        otherPanel.setLayout(new BorderLayout(5, 5));
        
        // Test Area
        
        
        // Last page setups
        
        setSize(800, 600);
        setVisible(true);
    }
}
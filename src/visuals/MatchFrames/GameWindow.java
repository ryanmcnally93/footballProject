package visuals.MatchFrames;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import general.LeagueTable;

public class GameWindow extends JFrame {
    private static final long serialVersionUID = 157462818597402652L;

    public GameWindow() {
    	setTitle("My Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        
        // Test Area

        
        
        // Last page setups
        
        setSize(800, 600);
        setVisible(true);
    }
}
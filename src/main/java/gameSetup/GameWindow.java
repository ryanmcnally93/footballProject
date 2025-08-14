package main.java.gameSetup;
import java.awt.Dimension;
import javax.swing.*;

public class GameWindow extends JFrame {
    private static final long serialVersionUID = 157462818597402652L;

    public GameWindow() {
    	setTitle("My Game");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(320, 500));
        setSize(800, 600);

        // For now
        setResizable(false);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
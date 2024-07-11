package visuals;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GameWindow extends JFrame {
	private static final long serialVersionUID = -6549197179020210093L;
	private JPanel jpanel;
	private CardLayout cardLayout;
	private JButton prev;
	private JButton next;
	
	public GameWindow() {
		//Let's setup the main window to hold the whole game
		// Title
		setTitle("Ryan's Football Game");
		// Exit Button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Set up 'panels' to switch through
		jpanel = new JPanel();
		cardLayout = new CardLayout();
		jpanel.setLayout(cardLayout);
		
		// Main will show the match
		// Events will list events
		GamePanel main = new GamePanel("main");
		GamePanel events = new GamePanel("events");
		jpanel.add(main, "main");
		jpanel.add(events, "events");
		
		// Navigation buttons
        prev = new JButton("Previous");
        next = new JButton("Next");

        // Create a box for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(prev);
        buttonPanel.add(next);

        // Previous button functionality
        prev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.previous(jpanel);
            }
        });

        // Next button functionality
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.previous(jpanel);
            }
        });
        
        // Layout setup
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(jpanel, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        setContentPane(contentPane);

        pack();
        // Center the window
        setLocationRelativeTo(null); 
        setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new GameWindow());
	}

}

package visuals;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GameWindow extends JFrame {
    private static final long serialVersionUID = 157462818597402652L;
	private CardLayout layout;
    private JPanel pages;

    public GameWindow() {
    	layout = new CardLayout();
        pages = new JPanel(layout);

        // Create instances of your panels
        MatchEvents eventsPanel = new MatchEvents(layout, pages);
        MatchWatch watchPanel = new MatchWatch(layout, pages);
        JPanel otherPanel = new JPanel();  // Another panel without buttons

        // Set up otherPanel
        otherPanel.setBackground(Color.GREEN);
        otherPanel.add(new JLabel("Other Panel"));

        // Add panels to the main panel
        pages.add(watchPanel, "Watch");
        pages.add(eventsPanel, "Events");
        pages.add(otherPanel, "OtherPanel");
        watchPanel.setEventsPanel(eventsPanel);

        // Initialize with the main page
        add(pages);
        layout.show(pages, "Watch");
        
        setTitle("My Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the window
    }

    public void switchToPanel(GamePanel panel) {
        if (pages != null) {
            remove(pages);
        }
        pages = panel;
        add(pages);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameWindow window = new GameWindow();
            window.setVisible(true);
        });
    }
}
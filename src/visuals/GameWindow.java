package visuals;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.Dimension;

public class GameWindow extends JFrame {
    private static final long serialVersionUID = 157462818597402652L;
	private CardLayout layout;
    private JPanel pages;
    private Map<String, JPanel> cardMap; // Map to store card names and their panels
    
    public GameWindow() {
    	setTitle("My Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the window
    	
    	cardMap = new HashMap<>();
    	layout = new CardLayout();
        pages = new JPanel(layout);
        
        // Create instances of your panels
        MatchEvents eventsPanel = new MatchEvents(layout, pages, cardMap);
        MatchWatch watchPanel = new MatchWatch(layout, pages, cardMap);

        // Add panels to the main panel
        pages.add(watchPanel, "Watch");
        cardMap.put("Watch", watchPanel);
        pages.add(eventsPanel, "Events");
        cardMap.put("Events", eventsPanel);

        // Initialize with the main page
        getContentPane().add(pages);
        layout.show(pages, "Watch");
        
        JPanel otherPanel = new JPanel();  // Another panel without buttons
        otherPanel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
        
        // Set up otherPanel
        otherPanel.setBackground(Color.LIGHT_GRAY);
        otherPanel.setLayout(new BorderLayout(5, 5));
        pages.add(otherPanel, "OtherPanel");
        cardMap.put("Other Panel", otherPanel);
        
        pack();
        setVisible(true);
    }

    public void switchToPanel(GamePanel panel) {
        if (pages != null) {
            remove(pages);
        }
        pages = panel;
        getContentPane().add(pages);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameWindow window = new GameWindow();
            window.setVisible(true);
        });
    }

	public CardLayout getLayout() {
		return layout;
	}

	public void setLayout(CardLayout layout) {
		this.layout = layout;
	}

	public JPanel getPages() {
		return pages;
	}

	public void setPages(JPanel pages) {
		this.pages = pages;
	}

	public Map<String, JPanel> getCardMap() {
		return cardMap;
	}

	public void setCardMap(Map<String, JPanel> cardMap) {
		this.cardMap = cardMap;
	}
}
package visuals;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import java.awt.GridBagConstraints;

public class GameWindow extends JFrame {
    private static final long serialVersionUID = 157462818597402652L;
	private CardLayout layout;
    private JPanel matchPages;
    private Map<String, JPanel> cardMap;

    public GameWindow() {
    	setTitle("My Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        cardMap = new HashMap<>();
        layout = new CardLayout();
        matchPages = new JPanel(layout);

        // Create MainFrame instances
        
        MatchScorers scorerPanel = new MatchScorers(layout, matchPages, cardMap);
        MatchEvents eventsPanel = new MatchEvents(layout, matchPages, cardMap);
        MatchWatch watchPanel = new MatchWatch(layout, matchPages, cardMap);

        // Add MatchFrame instances to the MatchFrames main panel
        
        matchPages.add(scorerPanel, "Scorers");
        cardMap.put("Scorers", scorerPanel);
        matchPages.add(watchPanel, "Watch");
        cardMap.put("Watch", watchPanel);
        matchPages.add(eventsPanel, "Events");
        cardMap.put("Events", eventsPanel);

        // Initialize with the main page, this will change multiple times
        
        getContentPane().add(matchPages, BorderLayout.CENTER);
        layout.show(matchPages, "Watch");

        // A page not included in Match Frames
        
        JPanel otherPanel = new JPanel();
        otherPanel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
        otherPanel.setBackground(Color.LIGHT_GRAY);
        otherPanel.setLayout(new BorderLayout(5, 5));
        cardMap.put("Other Panel", otherPanel);
        
        // Test Area
        
        
        
        // Last page setups
        
        setSize(800, 600);
        setVisible(true);
    }

    public void switchToPanel(GamePanel panel) {
        if (matchPages != null) {
            remove(matchPages);
        }
        matchPages = panel;
        getContentPane().add(matchPages);
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
		return matchPages;
	}

	public void setPages(JPanel pages) {
		this.matchPages = pages;
	}

	public Map<String, JPanel> getCardMap() {
		return cardMap;
	}

	public void setCardMap(Map<String, JPanel> cardMap) {
		this.cardMap = cardMap;
	}
}
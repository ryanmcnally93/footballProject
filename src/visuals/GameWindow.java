package visuals;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.Font;

public class GameWindow extends JFrame {
    private static final long serialVersionUID = 157462818597402652L;
	private CardLayout layout;
    private JPanel pages;
    private Map<String, JPanel> cardMap;

    public GameWindow() {
        setTitle("My Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        cardMap = new HashMap<>();
        layout = new CardLayout();
        pages = new JPanel(layout);

        // Create MainFrame instances
        
        MatchScorers scorerPanel = new MatchScorers(layout, pages, cardMap);
        MatchEvents eventsPanel = new MatchEvents(layout, pages, cardMap);
        MatchWatch watchPanel = new MatchWatch(layout, pages, cardMap);

        // Add MatchFrame instances to the MatchFrames main panel
        
        pages.add(scorerPanel, "Scorers");
        cardMap.put("Scorers", scorerPanel);
        pages.add(watchPanel, "Watch");
        cardMap.put("Watch", watchPanel);
        pages.add(eventsPanel, "Events");
        cardMap.put("Events", eventsPanel);

        // Initialize with the main page, this will change multiple times
        
        getContentPane().add(pages, BorderLayout.CENTER);
        layout.show(pages, "Watch");

        // A page not included in Match Frames
        
        JPanel otherPanel = new JPanel();
        otherPanel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
        otherPanel.setBackground(Color.LIGHT_GRAY);
        otherPanel.setLayout(new BorderLayout(5, 5));
        pages.add(otherPanel, "OtherPanel");
        cardMap.put("Other Panel", otherPanel);
        
        // Last page setups
        
        setSize(800, 600);
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
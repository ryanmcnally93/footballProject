package visuals;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class MatchFrames extends GamePanel {
	private static final long serialVersionUID = -7779250965738495855L;
	private CardLayout layout;
	private JPanel pages;
	private Map<String, JPanel> cardMap;
	private SlidingPanel slidingPanel;
//	private JLayeredPane layeredPane;

    public MatchFrames(CardLayout cardLayout, JPanel mainPanel, Map<String, JPanel> cardMap) {
    	super();
    	setLayout(new BorderLayout(5, 5));
    	this.layout = cardLayout;
        this.pages = mainPanel;
        this.cardMap = cardMap;
        
        // Change layout to border and instantiate JLayeredPane
//        layeredPane = new JLayeredPane();
//        layeredPane.setLayout(null);
        
        pages.setBounds(0, 0, 800, 600); // Adjust size as needed
//        layeredPane.add(pages, JLayeredPane.DEFAULT_LAYER);
        
        // Needs to be in frame not panel!
        slidingPanel = new SlidingPanel();
//        layeredPane.add(slidingPanel, JLayeredPane.PALETTE_LAYER);
        
//        add(layeredPane, BorderLayout.CENTER);
//        add(pages);
        Initialize();
    }
    
	public void Initialize() {
		
		JButton prevButton = new JButton("Prev");
        JButton nextButton = new JButton("Next");

        prevButton.addActionListener(e -> {
            System.out.println("You clicked previous");
            layout.previous(pages);
        });

        nextButton.addActionListener(e -> {
            System.out.println("You clicked next");
            layout.next(pages);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);

        //ADD A TIMER THEN ADD THIS TO CARDMAP.GET("WATCH")
        add(buttonPanel, BorderLayout.SOUTH);
    }
	
	public void goalAlert(String name, int minute) {
		add(getSlidingPanel());
    	getSlidingPanel().startSliding(name, minute);
    }

	public JPanel getPages() {
		return pages;
	}

	public void setPages(JPanel pages) {
		this.pages = pages;
	}

	public CardLayout getLayout() {
		return layout;
	}

	public void setLayout(CardLayout layout) {
		this.layout = layout;
	}

	public Map<String, JPanel> getCardMap() {
		return cardMap;
	}

	public void setCardMap(Map<String, JPanel> cardMap) {
		this.cardMap = cardMap;
	}

	public SlidingPanel getSlidingPanel() {
		return slidingPanel;
	}

	public void setSlidingPanel(SlidingPanel slidingPanel) {
		this.slidingPanel = slidingPanel;
	}

}

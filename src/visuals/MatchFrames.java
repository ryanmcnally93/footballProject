package visuals;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class MatchFrames extends GamePanel {
	private static final long serialVersionUID = -7779250965738495855L;
	private CardLayout layout;
	private JPanel pages;
	private Map<String, JPanel> cardMap;
	private SlidingPanel slidingPanel;
	private Box headerBox;
	private JLabel liveScore;

    public MatchFrames(CardLayout cardLayout, JPanel mainPanel, Map<String, JPanel> cardMap) {
    	super();
    	this.layout = cardLayout;
        this.pages = mainPanel;
        this.cardMap = cardMap;
        
        setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());
        
        headerBox = Box.createVerticalBox();
        headerBox.setPreferredSize(new Dimension(headerBox.getWidth(), 80));
        liveScore = new JLabel("ARSENAL 0 - 0 TOTTENHAM");
        liveScore.setAlignmentX(Component.CENTER_ALIGNMENT);
        liveScore.setHorizontalAlignment(SwingConstants.CENTER);
        liveScore.setBorder(new EmptyBorder(10, 20, 10, 20));
        liveScore.setForeground(new Color(0, 51, 204));
        liveScore.setFont(new Font("Menlo", Font.BOLD, 30));
        headerBox.add(liveScore);
        add(headerBox, BorderLayout.NORTH);
        
        slidingPanel = new SlidingPanel();
        
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
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.setBorder(new EmptyBorder(20, 0, 20, 0));
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

	public JLabel getLiveScore() {
		return liveScore;
	}

	public void setLiveScore(String score) {
		this.liveScore.setText(score);
	}

	public void updateScoreBoard(int home, int away) {
		System.out.println();
		setLiveScore("ARSENAL " + home + " - " + away + " TOTTENHAM");
		repaint();
	}

	public void setLiveScore(JLabel liveScore) {
		this.liveScore = liveScore;
	}
	
}

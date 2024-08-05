package visuals.MatchFrames;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import general.Match;
import people.Footballer;

public class MatchScorers extends MatchFrames {

	private static final long serialVersionUID = 1903388672588119012L;
	private ArrayList<String> homeScorers, awayScorers;
    private JPanel leftBox, rightBox;
    private Box centerBox, container;

	public MatchScorers(CardLayout cardLayout, JPanel pages, Map<String, JPanel> cardMap, Match match) {
		super(cardLayout, pages, cardMap, match);
		
		JLayeredPane layeredPane = getLayeredPane();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
		
		homeScorers = new ArrayList<String>();
		awayScorers = new ArrayList<String>();
		
		centerBox = Box.createVerticalBox();
		container = Box.createHorizontalBox();
		
		leftBox = new JPanel();
		leftBox.setBackground(Color.LIGHT_GRAY);
        leftBox.setLayout(new BoxLayout(leftBox, BoxLayout.Y_AXIS));
        
        leftBox.setPreferredSize(new Dimension(300, 409));
        leftBox.setMinimumSize(new Dimension(300, 409));
        leftBox.setMaximumSize(new Dimension(300, 409));
        
        rightBox = new JPanel();
        rightBox.setBackground(Color.LIGHT_GRAY);
        rightBox.setLayout(new BoxLayout(rightBox, BoxLayout.Y_AXIS));
 
        rightBox.setPreferredSize(new Dimension(300, 409));
        rightBox.setMinimumSize(new Dimension(300, 409));
        rightBox.setMaximumSize(new Dimension(300, 409));

        container.add(leftBox);
        container.add(rightBox);
        centerBox.add(container);
		
        Box west = Box.createHorizontalBox();
		west.setPreferredSize(new Dimension(100,200));
        mainPanel.add(west, BorderLayout.WEST);
        Box east = Box.createHorizontalBox();
        east.setPreferredSize(new Dimension(100,200));
        mainPanel.add(east, BorderLayout.EAST); 
        
        mainPanel.add(centerBox, BorderLayout.CENTER);
        
        mainPanel.setBounds(0, 80, 800, 440);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(mainPanel, JLayeredPane.DEFAULT_LAYER);
		
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustPanelSize(west);
                adjustPanelSize(east);
            }
        });
        
        setVisible(true);
	}
	
	private void adjustPanelSize(Box box) {
		Dimension screenSize = getSize();
        int width = screenSize.width;
        int eighth = width/8;
        box.setPreferredSize(new Dimension(eighth, box.getPreferredSize().height));
        box.setMinimumSize(new Dimension(eighth, box.getMinimumSize().height));
        box.setMaximumSize(new Dimension(eighth, box.getMaximumSize().height));
        
        // This isn't working
        int seventyfive = (int) (width*0.75);
        int half = (int) (seventyfive/2);
        
        container.setSize(new Dimension(seventyfive, container.getPreferredSize().height));
        container.revalidate();
        container.repaint();
        
        
        centerBox.setSize(new Dimension(seventyfive, container.getPreferredSize().height));
        centerBox.revalidate();
        centerBox.repaint();
        
        int height = screenSize.height;
        int newHeight = height-163;
        
        leftBox.setPreferredSize(new Dimension(half, newHeight));
        leftBox.setMinimumSize(new Dimension(half, newHeight));
        leftBox.setMaximumSize(new Dimension(half, newHeight));
        rightBox.setPreferredSize(new Dimension(half, newHeight));
        rightBox.setMinimumSize(new Dimension(half, newHeight));
        rightBox.setMaximumSize(new Dimension(half, newHeight));
        leftBox.revalidate();
        leftBox.repaint();
        rightBox.revalidate();
        rightBox.repaint();
        
        revalidate();
        repaint();
    }
	
	public void displayLeftGoalScorers(Footballer player, int minute) {
		if(leftBox != null) {
			leftBox.removeAll();
		}
		
		boolean aFound = false;
		for (int i = 0;i<getHomeScorers().size();i++) {
			String scorer = homeScorers.get(i);
			
			if (scorer.contains(player.getName()) ) {
				aFound = true;
				String minutes = scorer.substring(0, scorer.length() - 1);
				String updatedMinutes = minutes + ", " + minute + ")";
				homeScorers.set(i, updatedMinutes);
				break;
			}
		}
		
		if (!aFound) {
			homeScorers.add(player.getName() + "(" + minute + ")");
		}

//		int gridy = 0;
		for(String goal : homeScorers) {
			JLabel result = new JLabel(goal);
			if(hattrickCheck(goal)) {
				result.setForeground(Color.decode("#A0830E"));
				Font f = result.getFont();
				result.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
//				((MatchEvents) super.getCardMap().get("Events")).addHomeEvents(getMinute(), player, "save");
			}
			
			result.setBorder(new EmptyBorder(2, 20, 2, 20));
            result.setAlignmentX(Component.RIGHT_ALIGNMENT); // Align to the right
            leftBox.add(result);
	        containScorerSections();
		}
		
	}
	
	public static boolean hattrickCheck(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }

        String[] values = input.split(",");
        return values.length >= 3;
    }
	
	public void containScorerSections() {
		int seventyfive = (int) (getWidth()*0.75);
        int half = (int) (seventyfive/2);
        leftBox.setPreferredSize(new Dimension(half, getHeight()));
        rightBox.setPreferredSize(new Dimension(half, getHeight()));
	}
	
	public void displayRightGoalScorers(Footballer player, int minute) {
		if(rightBox != null) {
			rightBox.removeAll();
		}
		
		boolean bFound = false;
		for (int i = 0;i<getAwayScorers().size();i++) {
			String scorer = awayScorers.get(i);
			
			if (scorer.contains(player.getName()) ) {
				bFound = true;
				String minutes = scorer.substring(0, scorer.length() - 1);
				String updatedMinutes = minutes + ", " + minute + ")";
				awayScorers.set(i, updatedMinutes);
				break;
			}
		}
		
		if (!bFound) {
			awayScorers.add(player.getName() + "(" + minute + ")");
		}

//		int gridy = 0;
		for(String goal : awayScorers) {
			JLabel result = new JLabel(goal);
			if(hattrickCheck(goal)) {
				result.setForeground(Color.decode("#A0830E"));
				Font f = result.getFont();
				result.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
			}
			
			result.setBorder(new EmptyBorder(2, 20, 2, 20));
            result.setAlignmentX(Component.LEFT_ALIGNMENT); // Align to the right
            rightBox.add(result);
	        containScorerSections();

		}
		
	}
	

	public ArrayList<String> getHomeScorers() {
		return homeScorers;
	}

	
	public void setHomeScorers(ArrayList<String> homeScorers) {
		this.homeScorers = homeScorers;
	}

	public ArrayList<String> getAwayScorers() {
		return awayScorers;
	}

	public void setAwayScorers(ArrayList<String> awayScorers) {
		this.awayScorers = awayScorers;
	}
	
}

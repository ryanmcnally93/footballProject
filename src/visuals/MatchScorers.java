package visuals;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import people.Footballer;

public class MatchScorers extends MatchFrames {

	private static final long serialVersionUID = 1903388672588119012L;
	private Box centerBox;
	private ArrayList<String> homeScorers;
	private ArrayList<String> awayScorers;
    private GridBagConstraints left;
    private JPanel leftBox;
    private GridBagConstraints right;
    private JPanel rightBox;

	public MatchScorers(CardLayout cardLayout, JPanel mainPanel, Map<String, JPanel> cardMap) {
		super(cardLayout, mainPanel, cardMap);
		homeScorers = new ArrayList<String>();
		awayScorers = new ArrayList<String>();
		
		Box centerBox = Box.createVerticalBox();
		add(centerBox, BorderLayout.CENTER);
		
		Box container = Box.createHorizontalBox();
		
		left = new GridBagConstraints();
        left.gridx = 0;
        left.gridy = 0;
        left.gridwidth = 1; // Span all columns
        left.gridheight = GridBagConstraints.REMAINDER; // Span all rows
        left.weightx = 0.5;
        left.weighty = 1.0;
        left.fill = GridBagConstraints.BOTH;
        
        right = new GridBagConstraints();
        right.gridx = 1; // Position in the second column
        right.gridy = 0; // Start at the top row
        right.gridwidth = GridBagConstraints.REMAINDER; // Span all remaining columns
        right.gridheight = GridBagConstraints.REMAINDER; // Span all rows
        right.weightx = 0.5; // Take up the remaining width
        right.weighty = 1.0; // Take up the full height
        right.fill = GridBagConstraints.BOTH; // Resize both horizontally and vertical

        leftBox = new JPanel();
        leftBox.setBackground(Color.BLUE);
        
        rightBox = new JPanel();
        rightBox.setBackground(Color.GREEN);

        container.add(leftBox, left);
        container.add(rightBox, right);
        centerBox.add(container);
		
		Box west = Box.createHorizontalBox();
        west.setPreferredSize(new Dimension(100,200));
        add(west, BorderLayout.WEST);
        Box east = Box.createHorizontalBox();
        east.setPreferredSize(new Dimension(100,200));
        add(east, BorderLayout.EAST); 
		
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustPanelSize(west);
                adjustPanelSize(east);
            }
        });
	}
	
	private void adjustPanelSize(Box box) {
    	Dimension screenSize = getSize();
        int width = screenSize.width;
        int eighth = width/8;
        box.setPreferredSize(new Dimension(eighth, box.getPreferredSize().height));
        box.setMinimumSize(new Dimension(eighth, box.getMinimumSize().height));
        box.setMaximumSize(new Dimension(eighth, box.getMaximumSize().height));
        // Revalidate and repaint to apply changes
        box.revalidate();
        box.repaint();
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

		for(String goal : homeScorers) {
			JLabel result = new JLabel(goal);
			leftBox.add(result);
		}
		
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

		for(String goal : awayScorers) {
			JLabel result = new JLabel(goal);
			rightBox.add(result);
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

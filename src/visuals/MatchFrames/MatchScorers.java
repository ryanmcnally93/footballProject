package visuals.MatchFrames;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.UsersMatch;
import people.Footballer;

public class MatchScorers extends MatchFrames {

	private ArrayList<String> homeScorers, awayScorers;
    private JPanel leftBox, rightBox;
    private Box centerBox, container;

	public MatchScorers(CardLayout cardLayout, JPanel pages, UsersMatch match) {
		super(cardLayout, pages, match);
		
		JLayeredPane layeredPane = getLayeredPane();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
		
		homeScorers = new ArrayList<>();
		awayScorers = new ArrayList<>();
		
		centerBox = Box.createVerticalBox();
		container = Box.createHorizontalBox();
		
		leftBox = new JPanel();
		leftBox.setBackground(Color.LIGHT_GRAY);
        leftBox.setLayout(new BoxLayout(leftBox, BoxLayout.Y_AXIS));

		setPermanentWidthAndHeight(leftBox, 300, 409);
        
        rightBox = new JPanel();
        rightBox.setBackground(Color.LIGHT_GRAY);
        rightBox.setLayout(new BoxLayout(rightBox, BoxLayout.Y_AXIS));
 
        setPermanentWidthAndHeight(rightBox, 300, 409);

        container.add(leftBox);
        container.add(rightBox);
        centerBox.add(container);
		
        appendEastAndWest(mainPanel);
        
        mainPanel.add(centerBox, BorderLayout.CENTER);
        
        mainPanel.setBounds(0, 80, 800, 440);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(mainPanel, JLayeredPane.DEFAULT_LAYER);
		
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustPanelSize(getWest());
                adjustPanelSize(getEast());
            }
        });
        
        setVisible(true);
	}
	
	private void adjustPanelSize(Box box) {
		Dimension screenSize = getSize();
        int width = screenSize.width;
        int eighth = width/8;
        setPermanentWidth(box, eighth);
        
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

		setPermanentWidthAndHeight(leftBox, half, newHeight);
		setPermanentWidthAndHeight(rightBox, half, newHeight);
        leftBox.revalidate();
        leftBox.repaint();
        rightBox.revalidate();
        rightBox.repaint();
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
	
	public void displayGoalScorers(Footballer player, String time, String side) {
		JPanel box;
		ArrayList<String> scorers;
		if(side.equals("Home")){
			box = leftBox;
			scorers = getHomeScorers();
		} else{
			box = rightBox;
			scorers = getAwayScorers();
		}

		if(box != null) {
			box.removeAll();
		}
		
		boolean bFound = false;
		for (int i = 0;i<scorers.size();i++) {
			String scorer = scorers.get(i);
			
			if (scorer.contains(player.getName()) ) {
				bFound = true;
				String minutes = scorer.substring(0, scorer.length() - 1);
				String updatedMinutes = minutes + ", " + time + ")";
				scorers.set(i, updatedMinutes);
				break;
			}
		}
		
		if (!bFound) {
			scorers.add(player.getName() + "(" + time + ")");
		}

		for(String goal : scorers) {
			JLabel result = new JLabel(goal);
			if(hattrickCheck(goal)) {
				result.setForeground(Color.decode("#A0830E"));
				Font f = result.getFont();
				result.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
			}
			
			result.setBorder(new EmptyBorder(2, 20, 2, 20));
			if(side.equals("Home")) {
				result.setAlignmentX(Component.RIGHT_ALIGNMENT); // Align to the right
			} else {
				result.setAlignmentX(Component.LEFT_ALIGNMENT); // Align to the right
			}
            assert box != null;
            box.add(result);
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

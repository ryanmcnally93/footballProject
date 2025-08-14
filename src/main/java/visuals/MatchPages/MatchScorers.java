package visuals.MatchPages;

import visuals.CustomizedElements.CustomizedButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class MatchScorers extends MatchFrames {

    private JPanel leftBox, rightBox;
    private Box centerBox, container;

	public MatchScorers(CardLayout cardLayout, JPanel pages, Speedometer speedometer, ArrayList<CustomizedButton> buttons) {
		super(cardLayout, pages, speedometer, buttons);
		
		JLayeredPane layeredPane = getLayeredPane();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
		
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

	@Override
	public void populateMatchFramesContentForNewMatch() {
		super.populateMatchFramesContentForNewMatch();
		displayGoalScorers("Home", getMatch().getHomeScorers());
		displayGoalScorers("Away", getMatch().getAwayScorers());
	}

	@Override
	public void removeMatchFramesContentWhenLeavingMatch() {
		super.removeMatchFramesContentWhenLeavingMatch();
		leftBox.removeAll();
		rightBox.removeAll();
		leftBox.revalidate();
		leftBox.repaint();
		rightBox.revalidate();
		rightBox.repaint();
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
	
	public void displayGoalScorers(String side, ArrayList<String> scorers) {
		JPanel box;
		if(side.equals("Home")){
			box = leftBox;
		} else{
			box = rightBox;
		}

		if(box != null) {
			box.removeAll();
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

	@Override
	public String getMatchFrameName() {
		return "Scorers";
	}

}

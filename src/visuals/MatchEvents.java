package visuals;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import people.Footballer;

public class MatchEvents extends MatchFrames {
	
    private static final long serialVersionUID = 5937268249853937276L;
    private Box container;
    private JPanel leftIconBox, leftBox, middleBox, rightBox, rightIconBox;
    private ArrayList<JLabel> leftIcons, leftLabels, middleLabels, rightLabels, rightIcons;
    private static int events = 0;

    public MatchEvents(CardLayout layout, JPanel pages, Map<String, JPanel> cardMap) {
    	super(layout, pages, cardMap);
    	
    	JLayeredPane layeredPane = getLayeredPane();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
    	
        container = Box.createHorizontalBox();
        
        leftIconBox = new JPanel();
		leftIconBox.setBackground(Color.LIGHT_GRAY);
        leftIconBox.setLayout(new BoxLayout(leftIconBox, BoxLayout.Y_AXIS));
        
        leftIconBox.setPreferredSize(new Dimension(80, 409));
        leftIconBox.setMinimumSize(new Dimension(80, 409));
        leftIconBox.setMaximumSize(new Dimension(80, 409));
        
        leftBox = new JPanel();
		leftBox.setBackground(Color.LIGHT_GRAY);
        leftBox.setLayout(new BoxLayout(leftBox, BoxLayout.Y_AXIS));
        
        leftBox.setPreferredSize(new Dimension(275, 409));
        leftBox.setMinimumSize(new Dimension(275, 409));
        leftBox.setMaximumSize(new Dimension(275, 409));
        
        middleBox = new JPanel();
        middleBox.setBackground(Color.LIGHT_GRAY);
        middleBox.setLayout(new BoxLayout(middleBox, BoxLayout.Y_AXIS));
        
        middleBox.setPreferredSize(new Dimension(50, 409));
        middleBox.setMinimumSize(new Dimension(50, 409));
        middleBox.setMaximumSize(new Dimension(50, 409));
        
        rightBox = new JPanel();
        rightBox.setBackground(Color.LIGHT_GRAY);
        rightBox.setLayout(new BoxLayout(rightBox, BoxLayout.Y_AXIS));
        
        rightBox.setPreferredSize(new Dimension(275, 409));
        rightBox.setMinimumSize(new Dimension(275, 409));
        rightBox.setMaximumSize(new Dimension(275, 409));
        
        rightIconBox = new JPanel();
        rightIconBox.setBackground(Color.LIGHT_GRAY);
        rightIconBox.setLayout(new BoxLayout(rightIconBox, BoxLayout.Y_AXIS));
        
        rightIconBox.setPreferredSize(new Dimension(80, 409));
        rightIconBox.setMinimumSize(new Dimension(80, 409));
        rightIconBox.setMaximumSize(new Dimension(80, 409));
        
        int rows = 400;
        
        leftIcons = new ArrayList<JLabel>();
        leftLabels = new ArrayList<JLabel>();
        middleLabels = new ArrayList<JLabel>();
        rightLabels = new ArrayList<JLabel>();
        rightIcons = new ArrayList<JLabel>();

        for(int i=0;i<rows;i++) {
        	JLabel result = new JLabel();
        	result.setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
        	result.setPreferredSize(new Dimension(80, 30));
            result.setMinimumSize(new Dimension(80, 30));
            result.setMaximumSize(new Dimension(80, 30));
        	leftIcons.add(result);
        	leftIconBox.add(result);
        }
        
        for(int i=0;i<rows;i++) {
        	JLabel result = new JLabel();
        	result.setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
        	result.setPreferredSize(new Dimension(275, 30));
            result.setMinimumSize(new Dimension(275, 30));
            result.setMaximumSize(new Dimension(275, 30));
        	leftLabels.add(result);
        	leftBox.add(result);
        }
        
        for(int i=0;i<rows;i++) {
        	JLabel result = new JLabel();
        	result.setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
        	result.setPreferredSize(new Dimension(50, 30));
            result.setMinimumSize(new Dimension(50, 30));
            result.setMaximumSize(new Dimension(50, 30));
        	middleLabels.add(result);
        	middleBox.add(result);
        }
        
        for(int i=0;i<rows;i++) {
        	JLabel result = new JLabel();
        	result.setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
        	result.setPreferredSize(new Dimension(275, 30));
            result.setMinimumSize(new Dimension(275, 30));
            result.setMaximumSize(new Dimension(275, 30));
        	rightLabels.add(result);
        	rightBox.add(result);
        }
        
        for(int i=0;i<rows;i++) {
        	JLabel result = new JLabel();
        	result.setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
        	result.setPreferredSize(new Dimension(80, 30));
            result.setMinimumSize(new Dimension(80, 30));
            result.setMaximumSize(new Dimension(80, 30));
        	rightIcons.add(result);
        	rightIconBox.add(result);
        }
        
        container.add(leftIconBox);
        container.add(leftBox);
        container.add(middleBox);
        container.add(rightBox);
        container.add(rightIconBox);
        
        mainPanel.add(container, BorderLayout.CENTER);
        
        Box west = Box.createHorizontalBox();
		west.setPreferredSize(new Dimension(20,200));
        mainPanel.add(west, BorderLayout.WEST);
        Box east = Box.createHorizontalBox();
        east.setPreferredSize(new Dimension(20,200));
        mainPanel.add(east, BorderLayout.EAST); 
        
        mainPanel.setBounds(0, 80, 800, 440);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(mainPanel, JLayeredPane.DEFAULT_LAYER);
        
        setVisible(true);
        
    }
    
    public void addHomeEvents(int minute, Footballer player, String type) {
    	if(type.equals("goal")) {
    		middleLabels.get(events).setText("GOAL");
    		middleLabels.get(events).setBackground(Color.GREEN);
    		middleLabels.get(events).setOpaque(true);
    		leftLabels.get(events).setText(player.getName());
    		leftLabels.get(events).setBackground(Color.GREEN);
    		leftLabels.get(events).setOpaque(true);
        	events++;
    	} else if (type.equals("save")) {
    		middleLabels.get(events).setText("SAVE");
    		leftLabels.get(events).setText(player.getName());
    		events++;
    	}
    }
    
    public void addAwayEvents(int minute, Footballer player, String type) {
    	if(type.equals("goal")) {
    		middleLabels.get(events).setText("GOAL");
	    	middleLabels.get(events).setBackground(Color.GREEN);
	    	middleLabels.get(events).setOpaque(true);
    		rightLabels.get(events).setText(player.getName());
    		rightLabels.get(events).setBackground(Color.GREEN);
    		rightLabels.get(events).setOpaque(true);
	    	events++;
    	} else if (type.equals("save")) {
    		middleLabels.get(events).setText("SAVE");
    		rightLabels.get(events).setText(player.getName());
    		events++;
    	}
    }

	public Box getContainer() {
		return container;
	}

	public void setContainer(Box container) {
		this.container = container;
	}

	public JPanel getLeftBox() {
		return leftBox;
	}

	public void setLeftBox(JPanel leftBox) {
		this.leftBox = leftBox;
	}

	public JPanel getMiddleBox() {
		return middleBox;
	}

	public void setMiddleBox(JPanel middleBox) {
		this.middleBox = middleBox;
	}

	public JPanel getRightBox() {
		return rightBox;
	}

	public void setRightBox(JPanel rightBox) {
		this.rightBox = rightBox;
	}

	public ArrayList<JLabel> getLeftLabels() {
		return leftLabels;
	}

	public void setLeftLabels(ArrayList<JLabel> leftLabels) {
		this.leftLabels = leftLabels;
	}

	public ArrayList<JLabel> getMiddleLabels() {
		return middleLabels;
	}

	public void setMiddleLabels(ArrayList<JLabel> middleLabels) {
		this.middleLabels = middleLabels;
	}

	public ArrayList<JLabel> getRightLabels() {
		return rightLabels;
	}

	public void setRightLabels(ArrayList<JLabel> rightLabels) {
		this.rightLabels = rightLabels;
	}

}
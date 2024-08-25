package visuals.MatchFrames;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.BevelBorder;

import entities.UsersMatch;
import people.Footballer;

public class MatchEvents extends MatchFrames {
	
    private static final long serialVersionUID = 5937268249853937276L;
    private Box container;
    private JPanel leftIconBox, leftBox, middleBox, rightBox, rightIconBox;
    private ArrayList<JLabel> leftIcons, leftLabels, middleLabels, rightLabels, rightIcons;
    private int events;
    private JScrollPane scroller;
	private static final String UP = "up";
	private static final String DOWN = "down";
	private int button;
	private InputMap inputMap;
	private ActionMap actionMap;
	private int rows = 14;

    public MatchEvents(CardLayout layout, JPanel pages, UsersMatch match) {
    	super(layout, pages, match);
    	
    	this.events = 0;
    	this.button = 0;
    	
    	JLayeredPane layeredPane = getLayeredPane();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
    	
        container = Box.createHorizontalBox();
        
        leftIconBox = newBox();
        leftBox = newBox();
        middleBox = newBox();
        rightBox = newBox();
        rightIconBox = newBox();
        
        leftIcons = new ArrayList<>();
        leftLabels = new ArrayList<>();
        middleLabels = new ArrayList<>();
        rightLabels = new ArrayList<>();
        rightIcons = new ArrayList<>();

		makeRows(leftIcons, leftIconBox, 70);
		makeRows(leftLabels, leftBox, 275);
		makeRows(middleLabels, middleBox, 50);
		makeRows(rightLabels, rightBox, 275);
		makeRows(rightIcons, rightIconBox, 70);

        container.add(Box.createHorizontalGlue());
        container.add(leftIconBox);
        container.add(leftBox);
        container.add(middleBox);
        container.add(rightBox);
        container.add(rightIconBox);
        container.add(Box.createHorizontalGlue());
        container.setBorder(null);
        
        scroller = makeScroller(container);

		appendEastAndWest(mainPanel, 20);
        
        mainPanel.setBounds(0, 80, 800, 440);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(mainPanel, JLayeredPane.DEFAULT_LAYER);
        
        inputMap = middleLabels.get(button).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        actionMap = middleLabels.get(button).getActionMap();
        
		JLabel label = middleLabels.get(button);
		label.setVisible(true);
        label.requestFocusInWindow();
        label.setBackground(Color.YELLOW);
        label.setOpaque(true);
        
        Action upAction = new AbstractAction() {
			@Override
            public void actionPerformed(ActionEvent e) {
				if (button > 0) {
                    button--;
                    updateFocus(UP);
                }
            }
        };
        
        Action downAction = new AbstractAction() {
			@Override
            public void actionPerformed(ActionEvent e) {
				if (button < middleBox.getComponentCount() - 1) {
                    button++;
                    updateFocus(DOWN);
                }
            }
        };
        
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), UP);
        actionMap.put(UP, upAction);
        
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), DOWN);
        actionMap.put(DOWN, downAction);
        
        mainPanel.add(scroller, BorderLayout.CENTER);
        
        setVisible(true);
        
    }
    
    private void updateFocus(String direction) {
		if(direction.equals("up")) {
			JLabel oldLabel = middleLabels.get(button+1);
	        if(leftIcons.get(button+1).getText().equals("GOAL") || rightIcons.get(button+1).getText().equals("GOAL")) {
	        	oldLabel.setBackground(Color.GREEN);
	        	oldLabel.setOpaque(true);
	        } else {
	        	oldLabel.setBackground(Color.LIGHT_GRAY);
	            oldLabel.setOpaque(true);
	        }
		} else if (direction.equals("down")) {
			JLabel oldLabel = middleLabels.get(button-1);
			if(leftIcons.get(button-1).getText().equals("GOAL") || rightIcons.get(button-1).getText().equals("GOAL")) {
	        	oldLabel.setBackground(Color.GREEN);
	        	oldLabel.setOpaque(true);
	        } else {
	        	oldLabel.setBackground(Color.LIGHT_GRAY);
	            oldLabel.setOpaque(true);
	        }
		}
        	
    	JLabel label = middleLabels.get(button);
        label.setBackground(Color.YELLOW);
        label.setOpaque(true);
        
     // Calculate and adjust the rectangle for scrolling
        Rectangle rect = label.getBounds();
        SwingUtilities.invokeLater(() -> {
            // Adjust rectangle to be relative to viewport coordinates
            Point location = SwingUtilities.convertPoint(label.getParent(), label.getLocation(), scroller.getViewport());
            rect.setLocation(location);
            rect.setSize(label.getSize());

            // Include padding to ensure visibility
            int padding = 10; // Adjust padding as needed
            rect.grow(padding, padding);

            // Scroll to make the label visible
            scroller.getViewport().scrollRectToVisible(rect);
        });
    }
    
    public void addHomeEvents(int minute, Footballer player, String type) {
    	String s = String.valueOf(minute);
    	if(events > 13) {
    		addRow();
    	}
    	if(type.equals("goal")) {
    		leftIcons.get(events).setText("GOAL");
    		leftIcons.get(events).setBackground(Color.GREEN);
    		leftLabels.get(events).setBackground(Color.GREEN);
    		leftLabels.get(events).setOpaque(true);
    		leftIcons.get(events).setOpaque(true);
    	} else if (type.equals("save")) {
    		leftIcons.get(events).setText("SAVE");
    	} else if (type.equals("corner")) {
    		leftIcons.get(events).setText("CORNA");
    	}
		leftLabels.get(events).setText(player.getName());
		middleLabels.get(events).setText(s);
    	events++;
    	JLabel oldLabel = middleLabels.get(button);
    	if(leftIcons.get(button).getText().equals("GOAL") || rightIcons.get(button).getText().equals("GOAL")) {
        	oldLabel.setBackground(Color.GREEN);
        	oldLabel.setOpaque(true);
        } else {
        	oldLabel.setBackground(Color.LIGHT_GRAY);
            oldLabel.setOpaque(true);
        }
        button = events-1;
        JLabel label = middleLabels.get(button);
		label.setBackground(Color.YELLOW);
        label.setOpaque(true);
        getScroller().getViewport().scrollRectToVisible(label.getBounds());
    }
    
    
    public void addAwayEvents(int minute, Footballer player, String type) {
    	String s = String.valueOf(minute);
    	if(events > 13) {
    		addRow();
    	}
    	if(type.equals("goal")) {
    		rightLabels.get(events).setBackground(Color.GREEN);
    		rightLabels.get(events).setOpaque(true);
    		rightIcons.get(events).setText("GOAL");
    		rightIcons.get(events).setBackground(Color.GREEN);
    		rightIcons.get(events).setOpaque(true);
    	} else if (type.equals("save")) {
    		rightIcons.get(events).setText("SAVE");
    	} else if (type.equals("corner")) {
    		rightIcons.get(events).setText("CORNA");
    	}
		middleLabels.get(events).setText(s);
		rightLabels.get(events).setText(player.getName());
    	events++;
    	JLabel oldLabel = middleLabels.get(button);
    	if(leftIcons.get(button).getText().equals("GOAL") || rightIcons.get(button).getText().equals("GOAL")) {
        	oldLabel.setBackground(Color.GREEN);
        	oldLabel.setOpaque(true);
        } else {
        	oldLabel.setBackground(Color.LIGHT_GRAY);
            oldLabel.setOpaque(true);
        }
        button = events-1;
        JLabel label = middleLabels.get(button);
		label.setBackground(Color.YELLOW);
        label.setOpaque(true);    
        }
    
    public void addRow() {
    	JLabel first = new JLabel();
    	first.setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
    	first.setPreferredSize(new Dimension(70, 30));
        first.setMinimumSize(new Dimension(70, 30));
        first.setMaximumSize(new Dimension(70, 30));
    	leftIcons.add(first);
    	leftIconBox.add(first);
        
    	JLabel second = new JLabel();
    	second.setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
    	second.setPreferredSize(new Dimension(275, 30));
        second.setMinimumSize(new Dimension(275, 30));
        second.setMaximumSize(new Dimension(275, 30));
    	leftLabels.add(second);
    	leftBox.add(second);
        
    	JLabel third = new JLabel();
    	third.setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
    	third.setPreferredSize(new Dimension(50, 30));
        third.setMinimumSize(new Dimension(50, 30));
        third.setMaximumSize(new Dimension(50, 30));
    	middleLabels.add(third);
    	middleBox.add(third);
        
    	JLabel fourth = new JLabel();
    	fourth.setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
    	fourth.setPreferredSize(new Dimension(275, 30));
        fourth.setMinimumSize(new Dimension(275, 30));
        fourth.setMaximumSize(new Dimension(275, 30));
    	rightLabels.add(fourth);
    	rightBox.add(fourth);
        
    	JLabel fifth = new JLabel();
    	fifth.setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
    	fifth.setPreferredSize(new Dimension(70, 30));
        fifth.setMinimumSize(new Dimension(70, 30));
        fifth.setMaximumSize(new Dimension(70, 30));
    	rightIcons.add(fifth);
    	rightIconBox.add(fifth);
    	
    	leftIconBox.setPreferredSize(new Dimension(leftIconBox.getPreferredSize().width, leftIconBox.getPreferredSize().height+30));
    	leftIconBox.setMinimumSize(new Dimension(leftIconBox.getMinimumSize().width, leftIconBox.getMinimumSize().height+30));
    	leftIconBox.setMaximumSize(new Dimension(leftIconBox.getMaximumSize().width, leftIconBox.getMaximumSize().height+30));
        
    	leftBox.setPreferredSize(new Dimension(leftBox.getPreferredSize().width, leftBox.getPreferredSize().height+30));
    	leftBox.setMinimumSize(new Dimension(leftBox.getMinimumSize().width, leftBox.getMinimumSize().height+30));
    	leftBox.setMaximumSize(new Dimension(leftBox.getMaximumSize().width, leftBox.getMaximumSize().height+30));
        
    	middleBox.setPreferredSize(new Dimension(middleBox.getPreferredSize().width, middleBox.getPreferredSize().height+30));
    	middleBox.setMinimumSize(new Dimension(middleBox.getMinimumSize().width, middleBox.getMinimumSize().height+30));
    	middleBox.setMaximumSize(new Dimension(middleBox.getMaximumSize().width, middleBox.getMaximumSize().height+30));
        
    	rightBox.setPreferredSize(new Dimension(rightBox.getPreferredSize().width, rightBox.getPreferredSize().height+30));
    	rightBox.setMinimumSize(new Dimension(rightBox.getMinimumSize().width, rightBox.getMinimumSize().height+30));
    	rightBox.setMaximumSize(new Dimension(rightBox.getMaximumSize().width, rightBox.getMaximumSize().height+30));
        
    	rightIconBox.setPreferredSize(new Dimension(rightIconBox.getPreferredSize().width, rightIconBox.getPreferredSize().height+30));
        rightIconBox.setMinimumSize(new Dimension(rightIconBox.getMinimumSize().width, rightIconBox.getMinimumSize().height+30));
        rightIconBox.setMaximumSize(new Dimension(rightIconBox.getMaximumSize().width, rightIconBox.getMaximumSize().height+30));
    	
    	container.setPreferredSize(new Dimension(container.getPreferredSize().width, container.getPreferredSize().height+=30));
        container.setMinimumSize(new Dimension(container.getMinimumSize().width, container.getMinimumSize().height+=30));
        container.setMaximumSize(new Dimension(container.getMaximumSize().width, container.getMaximumSize().height+=30));
        
        JLabel label = middleLabels.get(button);
        // Calculate and adjust the rectangle for scrolling
        Rectangle rect = label.getBounds();
        SwingUtilities.invokeLater(() -> {
            // Adjust rectangle to be relative to viewport coordinates
            Point location = SwingUtilities.convertPoint(label.getParent(), label.getLocation(), scroller.getViewport());
            rect.setLocation(location);
            rect.setSize(label.getSize());

            // Include padding to ensure visibility
            int padding = 40; // Adjust padding as needed
            rect.grow(padding, padding);

            // Scroll to make the label visible
            scroller.getViewport().scrollRectToVisible(rect);
        });
    }

	public void makeRows(ArrayList<JLabel> list, JPanel box, int width){
		for(int i=0;i<rows;i++) {
			JLabel result = new JLabel();
			result.setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
			result.setPreferredSize(new Dimension(width, 30));
			result.setMinimumSize(new Dimension(width, 30));
			result.setMaximumSize(new Dimension(width, 30));
			list.add(result);
			box.add(result);
		}
		setPermanentWidth(box, width);
	}

	public JPanel newBox(){
		JPanel newBox = new JPanel();
		newBox.setBackground(Color.LIGHT_GRAY);
		newBox.setLayout(new BoxLayout(newBox, BoxLayout.Y_AXIS));
		return newBox;
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

	public JPanel getLeftIconBox() {
		return leftIconBox;
	}

	public void setLeftIconBox(JPanel leftIconBox) {
		this.leftIconBox = leftIconBox;
	}

	public JPanel getRightIconBox() {
		return rightIconBox;
	}

	public void setRightIconBox(JPanel rightIconBox) {
		this.rightIconBox = rightIconBox;
	}

	public ArrayList<JLabel> getLeftIcons() {
		return leftIcons;
	}

	public void setLeftIcons(ArrayList<JLabel> leftIcons) {
		this.leftIcons = leftIcons;
	}

	public ArrayList<JLabel> getRightIcons() {
		return rightIcons;
	}

	public void setRightIcons(ArrayList<JLabel> rightIcons) {
		this.rightIcons = rightIcons;
	}

	public int getEvents() {
		return events;
	}

	public void setEvents(int events) {
		this.events = events;
	}

	public JScrollPane getScroller() {
		return scroller;
	}

	public void setScroller(JScrollPane scroller) {
		this.scroller = scroller;
	}

	public int getButton() {
		return button;
	}

	public void setButton(int button) {
		this.button = button;
	}

}
package visuals.MatchPages;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import entities.UsersMatch;
import people.Footballer;
import visuals.CustomizedElements.CustomizedButton;

public class MatchEvents extends MatchFrames {

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

    public MatchEvents(CardLayout layout, JPanel pages, Speedometer speedometer, ArrayList<CustomizedButton> buttons) {
    	super(layout, pages, speedometer, buttons);
    	
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

		makeRows(leftIcons, leftIconBox, 70, "none");
		makeRows(leftLabels, leftBox, 275, "home");
		makeRows(middleLabels, middleBox, 50, "none");
		makeRows(rightLabels, rightBox, 275, "away");
		makeRows(rightIcons, rightIconBox, 70, "none");

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
        
        mainPanel.setBounds(0, 80, 800, 420);
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

	@Override
	public void removeContentForChildClass() {
		boolean needToRemoveExtraRows = leftIcons.size() > 13;
		while (needToRemoveExtraRows) {
			leftIcons.removeLast();
			leftLabels.removeLast();
			middleLabels.removeLast();
			rightLabels.removeLast();
			rightIcons.removeLast();
			needToRemoveExtraRows = leftIcons.size() > 13;
		}
		this.events = 0;
		this.button = 0;
		for (JLabel label : leftIcons) {
			clearLabel(label);
		}
		for (JLabel label : leftLabels) {
			clearLabel(label);
		}
		for (JLabel label : middleLabels) {
			clearLabel(label);
		}
		for (JLabel label : rightLabels) {
			clearLabel(label);
		}
		for (JLabel label : rightIcons) {
			clearLabel(label);
		}
	}

	public void clearLabel(JLabel label) {
		if (label.getBackground() == Color.GREEN || label.getBackground() == Color.YELLOW || !label.getText().isEmpty()) {
			label.setBackground(Color.LIGHT_GRAY);
			label.setText("");
		}
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
    
    public void addHomeEvents(String time, Footballer player, String type) {
		addEvents(time, player, type, leftIcons, leftLabels, middleLabels, rightIcons, rightLabels, middleBox, "home");
    }

    public void addAwayEvents(String time, Footballer player, String type) {
		addEvents(time, player, type, rightIcons, rightLabels, middleLabels, leftIcons, leftLabels, middleBox, "away");
	}

	public void addEvents(String time, Footballer player, String type, ArrayList<JLabel> firstLabels, ArrayList<JLabel> secondLabels, ArrayList<JLabel> thirdLabels, ArrayList<JLabel> fourthLabels, ArrayList<JLabel> fifthLabels, JPanel middle, String homeOrAway) {
		int roundedUp = findRoundedInt(time);

		if(events > 13) {
			addRow();
		}
		switch (type) {
			case "goal" -> {
				firstLabels.get(events).setText("GOAL");
				firstLabels.get(events).setBackground(Color.GREEN);
				secondLabels.get(events).setBackground(Color.GREEN);
				secondLabels.get(events).setOpaque(true);
				firstLabels.get(events).setOpaque(true);
			}
			case "save" -> firstLabels.get(events).setText("SAVE");
			case "corner" -> firstLabels.get(events).setText("CORNA");
		}
		firstLabels.get(events).setHorizontalAlignment(SwingConstants.CENTER);

		thirdLabels.get(events).setText(String.valueOf(roundedUp));
		thirdLabels.get(events).setHorizontalAlignment(SwingConstants.CENTER);

		secondLabels.get(events).setText(player.getName());
		if(homeOrAway.equals("away")) {
			secondLabels.get(events).setHorizontalAlignment(SwingConstants.RIGHT);
		} else {
			secondLabels.get(events).setHorizontalAlignment(SwingConstants.LEFT);
		}

		events++;
		JLabel oldLabel = thirdLabels.get(button);
		if(fourthLabels.get(button).getText().equals("GOAL") || firstLabels.get(button).getText().equals("GOAL")) {
			oldLabel.setBackground(Color.GREEN);
			oldLabel.setOpaque(true);
		} else {
			oldLabel.setBackground(Color.LIGHT_GRAY);
			oldLabel.setOpaque(true);
		}
		button = events-1;
		JLabel label = thirdLabels.get(button);
		label.setBackground(Color.YELLOW);
		label.setOpaque(true);
		getScroller().getViewport().scrollRectToVisible(label.getBounds());
	}

    public void addRow() {
    	JLabel first = new JLabel();
    	first.setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
		setPermanentWidthAndHeight(first, 70, 30);
    	leftIcons.add(first);
    	leftIconBox.add(first);

    	JLabel second = new JLabel();
		second.setBorder(new CompoundBorder(
				new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY),
				new EmptyBorder(0, 10, 0, 0)
		));
		setPermanentWidthAndHeight(second, 275, 30);
    	leftLabels.add(second);
    	leftBox.add(second);

    	JLabel third = new JLabel();
    	third.setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
		setPermanentWidthAndHeight(third, 50, 30);
    	middleLabels.add(third);
    	middleBox.add(third);

    	JLabel fourth = new JLabel();
		fourth.setBorder(new CompoundBorder(
				new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY),
				new EmptyBorder(0, 0, 0, 10)
		));
		setPermanentWidthAndHeight(fourth, 275, 30);
    	rightLabels.add(fourth);
    	rightBox.add(fourth);

    	JLabel fifth = new JLabel();
    	fifth.setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
		setPermanentWidthAndHeight(fifth, 70, 30);
    	rightIcons.add(fifth);
    	rightIconBox.add(fifth);

		setPermanentExtendedHeight(leftIconBox, 30);
		setPermanentExtendedHeight(leftBox, 30);
		setPermanentExtendedHeight(middleBox, 30);
		setPermanentExtendedHeight(rightBox, 30);
		setPermanentExtendedHeight(rightIconBox, 30);
		setPermanentExtendedHeight(container, 30);

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

	public void makeRows(ArrayList<JLabel> list, JPanel box, int width, String homeOrAway){
		for(int i=0;i<rows;i++) {
			JLabel result = new JLabel();
			if(homeOrAway.equals("home")) {
				result.setBorder(new CompoundBorder(
						new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY),
						new EmptyBorder(0, 5, 0, 0)
				));
			} else if (homeOrAway.equals("away")){
				result.setBorder(new CompoundBorder(
						new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY),
						new EmptyBorder(0, 0, 0, 5)
				));
			} else {
				result.setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
			}
			setPermanentWidthAndHeight(result, width, 30);
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
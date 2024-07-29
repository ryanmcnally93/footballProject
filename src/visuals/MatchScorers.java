package visuals;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MatchScorers extends MatchFrames {

	private static final long serialVersionUID = 1903388672588119012L;
	private Box centerBox;
	private List<String> leftMessages;
	private List<String> rightMessages;
    private int messageIncOne = 250;
    private int messageIncTwo = 30;

	public MatchScorers(CardLayout cardLayout, JPanel mainPanel, Map<String, JPanel> cardMap) {
		super(cardLayout, mainPanel, cardMap);
		
		leftMessages = new CopyOnWriteArrayList<>();
		rightMessages = new CopyOnWriteArrayList<>();
		
		centerBox = Box.createVerticalBox();
		add(centerBox, BorderLayout.CENTER);
		
		Box left = Box.createVerticalBox();
		int half = (int) getWidth()/2;
		left.setPreferredSize(new Dimension(half, getHeight()));
		
		for(String message : leftMessages) {
			JLabel result = new JLabel(message);
			left.add(result);
		}
		
		centerBox.add(left);
		
		Box right = Box.createVerticalBox();
		right.setPreferredSize(new Dimension(half, getHeight()));
		
		for(String message : rightMessages) {
			JLabel result = new JLabel(message);
			right.add(result);
		}
		
		centerBox.add(right);
		
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

	public void displayLeftGoalScorers(String newmsg) {
    	leftMessages.add(newmsg);
    	System.out.println("ITS THIS " + leftMessages);
		repaint();
		
	}
	
	public void displayRightGoalScorers(String newmsg) {
		rightMessages = new ArrayList<String>();
    	rightMessages.add(newmsg);
		repaint();
		
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        messageIncTwo = 25;
        if(leftMessages != null) {
        	g.setColor(Color.BLACK);
        	for(String mess : leftMessages) {
        		g.drawString(mess, messageIncOne, messageIncTwo);
        		messageIncTwo += 25;
        	}
        }
    }
	
}

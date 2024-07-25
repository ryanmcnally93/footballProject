package visuals;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MatchEvents extends MatchFrames {
    private static final long serialVersionUID = 5937268249853937276L;
    private List<String> messages;
    private int messageIncOne = 250;
    private int messageIncTwo = 30;

    public MatchEvents(CardLayout layout, JPanel pages, Map<String, JPanel> cardMap) {
    	super(layout, pages, cardMap);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        messageIncTwo = 25;
        if(messages != null) {
        	g.setColor(Color.BLACK);
        	for(String mess : messages) {
        		g.drawString(mess, messageIncOne, messageIncTwo);
        		messageIncTwo += 25;
        	}
        }
    }
    
 // Add common methods for rendering, updating, etc.
    public void displayGoal(String newmsg) {
    	if(messages == null) {
    		messages = new CopyOnWriteArrayList<>();
    		messages.add(newmsg);
    	} else {
    		messages.add(newmsg);
    	}
		repaint();
		
	}

	public void handleClick() {
		match.startMatch(getGraphics(), super.getCardMap());
	}
}
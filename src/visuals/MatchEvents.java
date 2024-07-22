package visuals;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MatchEvents extends MatchFrames {
    private static final long serialVersionUID = 5937268249853937276L;
	private static final int SQUARE_X = 100;
    private static final int SQUARE_Y = 100;
    private static final int SQUARE_SIZE = 50;
    private ArrayList<String> message;
    private int messageIncOne = 250;
    private int messageIncTwo = 30;
//    private GridBagConstraints c;

    public MatchEvents(CardLayout layout, JPanel pages) {
    	super(layout, pages);
        addGameMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                if (mouseX >= SQUARE_X && mouseX <= SQUARE_X + SQUARE_SIZE &&
                        mouseY >= SQUARE_Y && mouseY <= SQUARE_Y + SQUARE_SIZE) {
                    handleClick();
                    System.out.println("Blue square clicked!");
                }
            }
        });
//        c = new GridBagConstraints();
//        c.insets = new Insets(10,10,10,10);
//        c.gridx = 0;
//        c.gridy = 1;
//        c.gridx = 1;
//        c.gridy = 1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(SQUARE_X, SQUARE_Y, SQUARE_SIZE, SQUARE_SIZE);
        
        messageIncTwo = 0;
        if(message != null) {
        	g.setColor(Color.BLACK);
        	for(String mess : message) {
        		g.drawString(mess, messageIncOne, messageIncTwo);
        		messageIncTwo += 25;
        	}
        }
    }
    
 // Add common methods for rendering, updating, etc.
    public void displayGoal(String newmsg) {
    	if(message == null) {
    		message = new ArrayList<String>();
    		this.message.add(newmsg);
    	}
		this.message.add(newmsg);
		repaint();
		
	}

	public void handleClick() {
		match.startMatch(getGraphics(), this);
	}
}
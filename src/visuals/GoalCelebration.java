package visuals;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;

class SlidingPanel extends JPanel {
    private static final long serialVersionUID = 7790773713887698024L;
	private int yPosition;
    private final int panelHeight = 400;
    private Timer slideUpTimer;
    private Timer slideDownTimer;
    private final int slideSpeed = 5;
    private final int delayBeforeSlideDown = 3000; // 2 seconds
    private int minute;
    private String name;

    public SlidingPanel() {
        yPosition = 600;
        setOpaque(false);
    }

    public void startSliding(String name, int minute) {
    	this.name = name;
    	this.minute = minute;
        if (slideUpTimer != null && slideUpTimer.isRunning()) {
            return; // Prevent overlapping animations
        }
        slideUp();
    }

    private void slideUp() {
        slideUpTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// First time round, this is true
            	if(yPosition == 600) {
            		yPosition = getHeight();
            	}
            	System.out.println(yPosition);
                yPosition -= slideSpeed;
                System.out.println("yPosition after we take slidespeed off:" + yPosition);
                if (yPosition <= getHeight() - panelHeight) {
                	System.out.println("yPosition is smaller then getheight - panelheight" + yPosition + ", " + getHeight() + ", " + panelHeight);
                    yPosition = getHeight() - panelHeight;
                    slideUpTimer.stop();
                    Timer delayTimer = new Timer(delayBeforeSlideDown, ae -> slideDown());
                    delayTimer.setRepeats(false);
                    delayTimer.start();
                }
                repaint();
            }
        });
        slideUpTimer.start();
    }

    private void slideDown() {
        slideDownTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("Starting slide down");
                yPosition += slideSpeed;
                if (yPosition >= getHeight()) {
                    yPosition = getHeight();
                    slideDownTimer.stop();
                }
                repaint();
            }
        });
        slideDownTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        int seventyfivepercent = (int )(getWidth()*0.75);
        g.fillRect((int) ((getWidth()-seventyfivepercent)/2), yPosition, seventyfivepercent, panelHeight/2);
        g.setColor(Color.WHITE);
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        String message = "GOAL!" + name + minute;
        g.drawString(message, (getWidth() - metrics.stringWidth(message)) / 2, yPosition + ((panelHeight - metrics.getHeight()) / 2) + metrics.getAscent());
    }
    
}
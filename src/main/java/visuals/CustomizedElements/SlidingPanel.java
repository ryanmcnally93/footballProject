package main.java.visuals.CustomizedElements;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;

public class SlidingPanel extends JPanel {
	private int yPosition;
    private final int panelHeight = 300;
    private Timer slideUpTimer;
    private Timer slideDownTimer;
    private final int slideSpeed = 5;
    private final int delayBeforeSlideDown = 3000; // 2 seconds
    private String time;
    private String name;

    public SlidingPanel() {
        yPosition = 600;
        setOpaque(false);
        setDoubleBuffered(true);
    }

    public void startSliding(String name, String time) {
    	this.name = name;
    	this.time = time;
        if (slideUpTimer != null && slideUpTimer.isRunning()) {
            return; // Prevent overlapping animations
        }
        slideUp();
    }

    private void slideUp() {
        System.out.println("RUNNING slideUp METHOD");
        slideUpTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// First time round, this is true
            	if(yPosition == 600) {
            		yPosition = getHeight();
            	}
                yPosition -= slideSpeed;
                if (yPosition <= getHeight() - 500) {
                    yPosition = getHeight() - 500;
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
        System.out.println("RUNNING slideDown METHOD");
        slideDownTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        g.fillRect((int) ((getWidth()-seventyfivepercent)/2), yPosition, seventyfivepercent, panelHeight);
        g.setColor(Color.WHITE);
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        String message = "GOAL!" + name + time;
        g.drawString(message, (getWidth() - metrics.stringWidth(message)) / 2, yPosition + ((panelHeight - metrics.getHeight()) / 2) + metrics.getAscent());
    }
    
}
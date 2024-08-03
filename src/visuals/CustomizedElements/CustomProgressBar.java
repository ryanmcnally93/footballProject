package visuals.CustomizedElements;

import javax.swing.*;
import java.awt.*;

public class CustomProgressBar extends JProgressBar {
    private static final long serialVersionUID = -9140075168622999308L;
    private static final int CORNER_RADIUS = 8;

	public CustomProgressBar() {
        super();
        setOpaque(false);
        setBorderPainted(false);
    }
	
	@Override
    public Dimension getPreferredSize() {
        // Set a preferred size that fits the rounded corners
        return new Dimension(300, 30); // Adjust dimensions as needed
    }

    @Override
    protected void paintComponent(Graphics g) {

        // Convert Graphics to Graphics2D for more control
        Graphics2D g2d = (Graphics2D) g.create();
        int width = getWidth();
        int height = getHeight();
//        int barWidth = (int) (width * 0.75); // 75% of the width of the container
        int progressWidth = (int) (width * ((double) getValue() / getMaximum()));
        
        // Center the progress bar
        int x = (width - width) / 2;
        int y = (height - CORNER_RADIUS * 2)/2;
        
     // Anti-aliasing for smoother corners
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the background with rounded corners
        g2d.setColor(Color.GRAY);
        g2d.fillRoundRect(x, y, width, CORNER_RADIUS * 2, CORNER_RADIUS, CORNER_RADIUS);

        // Draw the progress with rounded corners
        g2d.setColor(Color.GREEN);
        g2d.fillRoundRect(x, y, progressWidth, CORNER_RADIUS * 2, CORNER_RADIUS, CORNER_RADIUS);

        // Optional: Draw the border around the progress bar
        g2d.setColor(Color.BLACK);
        g2d.drawRoundRect(x, y, width, CORNER_RADIUS * 2, CORNER_RADIUS, CORNER_RADIUS);

        g2d.dispose();
    }
    
}
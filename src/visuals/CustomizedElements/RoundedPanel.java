package visuals.CustomizedElements;
import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends GamePanel {
    private int cornerRadius;

    public RoundedPanel(int radius) {
        super();
        this.cornerRadius = radius;
        setOpaque(false); // Make sure the panel is transparent so that the background color isn't drawn
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Smooths the edges
        g2.setColor(getBackground()); // Set the background color of the panel
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius); // Draw rounded corners
    }
}
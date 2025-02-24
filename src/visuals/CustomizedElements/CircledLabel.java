package visuals.CustomizedElements;

import javax.swing.*;
import java.awt.*;

public class CircledLabel extends JLabel {

    private boolean drawCircle;

    public CircledLabel(String text) {
        super(text);
        setOpaque(false);
        this.drawCircle = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Get the dimensions of the label's text
        FontMetrics fm = g.getFontMetrics();
        int width = fm.stringWidth(getText());
        int height = fm.getHeight() - 3;

        // Calculate the diameter of the circle
        int diameter = Math.max(width, height) + 1;  // Extra padding around the text

        // Get the coordinates for the circle (center it around the text)
        int x = ((getWidth() + 3) - diameter) / 2;
        int y = (getHeight() - diameter) / 2;

        // Draw the circle
        if (drawCircle) {
            g.setColor(Color.BLACK);
            g.drawOval(x, y, diameter, diameter);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        FontMetrics fm = getFontMetrics(getFont());
        int width = fm.stringWidth(getText());
        int height = fm.getHeight() - 3;
        int diameter = Math.max(width, height) + 1;
        return new Dimension(diameter + 6, diameter + 6);  // Add padding around the circle
    }

    public void removeCircle() {
        this.drawCircle = false;
        repaint();
    }

    public void addCircle() {
        this.drawCircle = true;
        repaint();
    }

}

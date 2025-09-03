package visuals.CustomizedElements;

import javax.swing.*;
import java.awt.*;

public class CustomProgressBar extends JProgressBar {
    private static final long serialVersionUID = -9140075168622999308L;
    private int CORNER_RADIUS = 8;
    private Color one;
    private Color two;
    private boolean border;

	public CustomProgressBar(Color one, Color two) {
        super();
        this.one = one;
        this.two = two;
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
        Graphics2D g2d = (Graphics2D) g.create();
        int width = getWidth();
        int height = getHeight();
        int progressWidth = (int) (width * ((double) getValue() / getMaximum()));

        // Draw background
        g2d.setColor(two);
        g2d.fillRoundRect(0, 0, width, height, CORNER_RADIUS, CORNER_RADIUS);

        // Draw progress
        g2d.setColor(one);
        g2d.fillRoundRect(0, 0, progressWidth, height, CORNER_RADIUS, CORNER_RADIUS);

        // Optional border
        if (isBorder()) {
            g2d.setColor(Color.BLACK);
            g2d.drawRoundRect(0, 0, width - 1, height - 1, CORNER_RADIUS, CORNER_RADIUS); // -1 to fit inside bounds
        }

        g2d.dispose();
    }

    public int getCornerRadius() {
        return CORNER_RADIUS;
    }

    public void setCornerRadius(int cornerRadius) {
        CORNER_RADIUS = cornerRadius;
    }

    public boolean isBorder() {
        return border;
    }

    public void setBorder(boolean border) {
        this.border = border;
    }

    public Color getOne() {
        return one;
    }

    public void setOne(Color one) {
        this.one = one;
    }
}
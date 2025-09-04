package visuals.CustomizedElements;

import javax.swing.*;
import java.awt.*;

import static visuals.CustomizedElements.GamePanel.getCharcoal;

public class CircledLabel extends JLabel {

    private boolean drawCircle;
    private int offset = 0;
    private int padding;

    public CircledLabel(String text) {
        super(text);
        setOpaque(false);
        this.drawCircle = false;
        this.padding = 1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // still clears background

        Graphics2D g2d = (Graphics2D) g.create();

        String text = getText();
        Font font = getFont();
        g2d.setFont(font);

        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent(); // baseline to top
        int totalHeight = fm.getHeight() - offset; // full height including descenders

        // Diameter of circle with padding
        int diameter = Math.max(textWidth, totalHeight) + padding;

        // Calculate center positions
        int centerX = (getWidth() + offset) / 2;
        int centerY = getHeight() / 2;

        // Top-left of circle
        int circleX = centerX - diameter / 2;
        int circleY = centerY - diameter / 2;

        fillOval(g, circleX, circleY, diameter);
        drawOvalBorder(g2d, circleX, circleY, diameter);

        // Now draw text centered
        int textX = centerX - textWidth / 2;
        int textY = centerY + (textHeight / 2) - 2; // Adjust -2 for fine vertical tuning

        drawText(g2d, text, textX, textY);

        g2d.dispose();
    }

    protected void drawOvalBorder(Graphics2D g2d, int circleX, int circleY, int diameter) {
        if (isDrawCircle()) {
            g2d.setColor(Color.BLACK);
            g2d.drawOval(circleX, circleY, diameter, diameter);
        }
    }

    protected void drawText(Graphics g2d, String text, int textX, int textY) {}

    protected void fillOval(Graphics g, int x, int y, int diameter) {}

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
        revalidate();
        repaint();
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public boolean isDrawCircle() {
        return drawCircle;
    }

    public void setDrawCircle(boolean drawCircle) {
        this.drawCircle = drawCircle;
    }
}

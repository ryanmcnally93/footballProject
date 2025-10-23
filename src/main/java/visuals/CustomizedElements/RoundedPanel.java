package visuals.CustomizedElements;

import java.awt.*;

public class RoundedPanel extends GamePanel {
    private int cornerRadius;
    private Color borderColor = Color.BLACK;
    private int borderThickness = 1;

    public RoundedPanel(int radius) {
        super();
        this.cornerRadius = radius;
        setOpaque(false); // Make sure the panel is transparent so that the background color isn't drawn
    }

    public void setBorder(Color color, int thickness) {
        this.borderColor = color;
        this.borderThickness = thickness;
        repaint(); // Repaint the component to show the updated border
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Smooths the edges

        // Fill the panel with a rounded background color
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        // Draw the rounded border if thickness > 0
        if (borderThickness > 0) {
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(borderThickness));
            g2.drawRoundRect(
                    borderThickness / 2,
                    borderThickness / 2,
                    getWidth() - borderThickness,
                    getHeight() - borderThickness,
                    cornerRadius,
                    cornerRadius
            );
        }
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public int getBorderThickness() {
        return borderThickness;
    }

    public void setBorderThickness(int borderThickness) {
        this.borderThickness = borderThickness;
    }
}
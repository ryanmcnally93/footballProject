package visuals.CustomizedElements;

import java.awt.*;

public class RoundedPanel extends GamePanel {
    private int cornerRadius;
    private Color borderColor = Color.BLACK;
    private int borderThickness = 1;

    public RoundedPanel(int radius) {
        super();
        this.cornerRadius = radius;
        setOpaque(false);
    }

    public void setBorder(Color color, int thickness) {
        this.borderColor = color;
        this.borderThickness = thickness;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Clear with transparent background
        g2.setComposite(AlphaComposite.Clear);
        g2.fillRect(0, 0, getWidth(), getHeight());

        // Switch back to normal paint mode
        g2.setComposite(AlphaComposite.SrcOver);

        // Fill the rounded background
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        // Draw border if needed
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

        g2.dispose();

        super.paintChildren(g);
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
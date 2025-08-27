package visuals.CustomizedElements;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class CustomizedTitle extends JLabel {

    private float opacity = 0.7f;
    private int cornerRadius = 10;
    private int padding = 10;

    public CustomizedTitle(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
        setOpaque(false);
        setFont(GamePanel.getBebasNeueFont());
        setForeground(new Color(0x36, 0x45, 0x4F));
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    public CustomizedTitle(String text) {
        this(text, SwingConstants.CENTER);
    }

    public void setFontSize(float size) {
        setFont(GamePanel.getBebasNeueFontWithSize(size));
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        return new Dimension(d.width + (padding*2), d.height + padding); // add padding
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Gather graphics element
        Graphics2D g2d = (Graphics2D) g.create();

        // This sets opacity
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity)); // 55% transparency

        // This makes the object more smooth
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(getBackground()); // Use the component's background color
        g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius));

        g2d.dispose();
        super.paintComponent(g);
    }

    @Override
    public Dimension getMaximumSize() {
        Dimension d = getPreferredSize(); // use your padded height
        return new Dimension(d.width, d.height); // lock width & height
    }

}

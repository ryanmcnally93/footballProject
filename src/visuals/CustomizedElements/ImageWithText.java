package visuals.CustomizedElements;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageWithText extends ImageIcon {
    private String text;
    private float opacity;

    public ImageWithText(BufferedImage image, String text, float opacity){
        super(image);
        this.text = text;
        this.opacity = opacity;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f)); // Set full opacity for text
        g2d.setColor(Color.BLACK); // Text color
        g2d.setFont(new Font("Arial", Font.BOLD, 12));

        FontMetrics fm = g2d.getFontMetrics();
        int textX = x + (getIconWidth() - fm.stringWidth(text)) / 2;
        int textY = y + (getIconHeight() + fm.getAscent()) / 2 - 2;
        g2d.drawString(text, textX, textY);

        g2d.dispose();
    }

}

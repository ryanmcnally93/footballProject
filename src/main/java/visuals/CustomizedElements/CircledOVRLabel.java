package visuals.CustomizedElements;

import javax.swing.*;
import java.awt.*;

import static visuals.CustomizedElements.GamePanel.getBebasNeueFontWithSize;
import static visuals.CustomizedElements.GamePanel.getCharcoal;
import static visuals.CustomizedElements.PlayerAttributeBox.getStatColour;

public class CircledOVRLabel extends CircledLabel{

    public CircledOVRLabel(int ovr) {
        super(String.valueOf(ovr));
        setFont(getFont().deriveFont(28f));
        setPadding(20);
        addCircle();
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    protected void fillOval(Graphics g, int x, int y, int diameter) {
        if (isDrawCircle()) {
            g.setColor(getStatColour(Integer.parseInt(getText())));
            g.fillOval(x, y, diameter, diameter);
        }
    }

    @Override
    protected void drawText(Graphics g2d, String text, int textX, int textY) {
        g2d.setFont(getBebasNeueFontWithSize(28));
        g2d.setColor(getCharcoal());
        g2d.drawString(text, textX + 6, textY - 2);
    }

    @Override
    protected void drawOvalBorder(Graphics2D g2d, int circleX, int circleY, int diameter) {}
}

package visuals.CustomizedElements;

import Interfaces.Hoverable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static visuals.CustomizedElements.GamePanel.getOppositeImage;

public class CustomizedButton extends JButton implements Hoverable {

    private boolean hasImage;
    private ImageIcon icon;
    private boolean selected;
    private int arcHeight = 10;
    private int arcWidth = 10;
    private boolean borderWanted = true;
    private boolean fillWanted = true;

    public CustomizedButton(String text) {
        super(text);
        setFont(GamePanel.getBebasNeueFont());
        setMargin(new Insets(7, 3, 2, 3));
        init();
    }

    public CustomizedButton(String text, int size) {
        super(text);
        setFont(GamePanel.getBebasNeueFontWithSizeBold((float) size));
        setMargin(new Insets(size/4, 0, 0, 0));
        init();
    }

    public CustomizedButton(ImageIcon image) {
        super(image);
        hasImage = true;
        icon = image;
        setFont(GamePanel.getBebasNeueFont());
        setMargin(new Insets(7, 0, 0, 0));
        init();
    }

    public void init() {
        setForeground(getSecondaryColor());
        setBackground(getPrimaryColor());
        setFocusPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setContentAreaFilled(false);

        if (getText().equals("<") || getText().equals(">")) {
            setBorder(new EmptyBorder(4, 0, 0, 0));
            setContentAreaFilled(false);
        } else if (hasImage) {
            setBorder(new EmptyBorder(0, 0, 0, 0));
            setContentAreaFilled(false);
        } else {
            setBorderPainted(false);
        }

        if (hasImage) {
            addHoverEffectWithImageSwap(this, this::repaint);
        } else {
            addHoverEffect(this, this::repaint);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        paintHoverableBackground(g, this, getBackground(), getArcWidth(), getArcHeight(), borderWanted, fillWanted);
        super.paintComponent(g);
    }

    @Override
    public boolean isSelected() { return selected; }

    @Override
    public void setSelected(boolean selected) { this.selected = selected; }

    public void triggerColorReverse() {
        ImageIcon oppositeImage = getOppositeImage((ImageIcon) getIcon());
        icon = oppositeImage;
        setIcon(oppositeImage);
        this.init();
        this.revalidate();
        this.repaint();
    }

    public ImageIcon getOtherIcon() {
        return icon;
    }

    public void setOtherIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public int getArcWidth() {
        return arcWidth;
    }

    public int getArcHeight() {
        return arcHeight;
    }

    public void setArcWidth(int arcWidth) {
        this.arcWidth = arcWidth;
    }

    public void setArcHeight(int arcHeight) {
        this.arcHeight = arcHeight;
    }

    public boolean isBorderWanted() {
        return borderWanted;
    }

    public void setBorderWanted(boolean borderWanted) {
        this.borderWanted = borderWanted;
    }

    public boolean isFillWanted() {
        return fillWanted;
    }

    public void setFillWanted(boolean fillWanted) {
        this.fillWanted = fillWanted;
    }
}
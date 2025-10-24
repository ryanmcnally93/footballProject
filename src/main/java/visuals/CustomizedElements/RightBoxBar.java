package visuals.CustomizedElements;

import javax.swing.*;
import java.awt.*;

import static visuals.CustomizedElements.GamePanel.getBebasNeueFontWithSize;

public class RightBoxBar extends CustomizedButton {

    private int size;

    public RightBoxBar(int size) {
        super("", size);
        this.size = size;
    }

    public void createBar() {
        setArcHeight(size);
        setArcWidth(size);
        revalidate();
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        // keep your text-based width/height here; do NOT read parent/getWidth()
        return new Dimension(d.width-3, d.height);
    }

    @Override
    public Dimension getMaximumSize() {
        Dimension d = super.getPreferredSize();
        // allow horizontal stretch, keep natural height
        return new Dimension(Integer.MAX_VALUE, d.height);
    }

//    @Override
//    protected void childMouseExited() {
//        setForeground(getSecondaryColor());
//    }
//
//    @Override
//    protected void childMouseEntered() {
//        setForeground(getPrimaryColor());
//    }

    public void setAsSelected(boolean bool) {
        setSelected(bool);
        // This should be done ideally within the CustomisedButton class
        if (bool) {
            setForeground(getPrimaryColor());
            setBackground(getSecondaryColor());
        } else {
            setForeground(getSecondaryColor());
            setBackground(getPrimaryColor());
        }
    }

}

package visuals.CustomizedElements;

import javax.swing.*;

import static visuals.CustomizedElements.GamePanel.getBebasNeueFontWithSizeBold;
import static visuals.CustomizedElements.GamePanel.getCharcoal;

public class CustomizedLabel extends JLabel {
    public CustomizedLabel(String s, float fontSize) {
        setFont(getBebasNeueFontWithSizeBold(fontSize));
        setForeground(getCharcoal());
    }
}

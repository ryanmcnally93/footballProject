package visuals.CustomizedElements;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

import static visuals.CustomizedElements.GamePanel.*;

public class PlayerAttributeBox extends Box {

    private String attributeName;
    private int value;

    public PlayerAttributeBox(String attributeName, String value) {
        super(BoxLayout.Y_AXIS);
        this.attributeName = attributeName;
        this.value = Integer.parseInt(value);

        if (attributeName.length() > 9) {
            attributeName = getShorterAttributeName(attributeName);
        }

        Box attributeTitle = Box.createHorizontalBox();
        JLabel title = new JLabel(attributeName);
        title.setFont(getBebasNeueFontWithSize(16f));
        title.setForeground(getCharcoal());
        title.setBorder(new EmptyBorder(0, 4, 0, 0));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        setPermanentWidth(title, 75);
        JLabel stat = new JLabel(value);
        stat.setFont(getBebasNeueFontWithSize(16f));
        stat.setBorder(new EmptyBorder(0, 0, 0, 4));
        stat.setHorizontalAlignment(SwingConstants.RIGHT);
        stat.setForeground(getCharcoal());
        setPermanentWidth(stat, 25);

        attributeTitle.add(title);
        attributeTitle.add(stat);

        SwingUtilities.invokeLater(() -> {
            setPermanentWidth(this, 110);
        });

        add(attributeTitle);

        CustomProgressBar statBar = new CustomProgressBar(getStatColour(), Color.LIGHT_GRAY);
        setPermanentWidthAndHeight(statBar, 100, 10);
        statBar.setValue(getValue());
        statBar.setCornerRadius(10);
        statBar.setBorder(false);

        add(statBar);

    }

    private String getShorterAttributeName(String attributeName) {
        if (attributeName.equals("Standing Tackle")) {
            return "St. Tackle";
        } else if (attributeName.equals("Sliding Tackle")) {
            return "Sl. Tackle";
        } else if (attributeName.equals("Short Passing")) {
            return "Short Pass";
        } else {
            throw new IllegalArgumentException("Invalid attribute name: " + attributeName);
        }
    }

    private Color getStatColour() {
        if (getValue() == 99) {
            return new Color(0, 0, 0);
        } else if (getValue() > 89) {
            return new Color(255, 215, 0);
        } else if (getValue() > 79) {
            return new Color(5, 163, 0);
        } else if (getValue() > 69) {
            return new Color(183, 255, 0);
        } else if (getValue() > 59) {
            return new Color(255, 149, 1);
        } else {
            return new Color(255, 1, 5);
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

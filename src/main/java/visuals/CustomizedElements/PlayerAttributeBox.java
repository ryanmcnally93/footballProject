package visuals.CustomizedElements;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

import static visuals.CustomizedElements.GamePanel.*;

public class PlayerAttributeBox extends Box {

    private CustomProgressBar statBar;
    private JLabel title, stat;

    public PlayerAttributeBox() {
        super(BoxLayout.Y_AXIS);

        Box attributeTitle = Box.createHorizontalBox();
        title = new JLabel("Default");
        title.setFont(getBebasNeueFontWithSize(16f));
        title.setForeground(getCharcoal());
        title.setBorder(new EmptyBorder(0, 4, 0, 0));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        setPermanentWidth(title, 75);
        stat = new JLabel(String.valueOf(0));
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

        statBar = new CustomProgressBar(getStatColour(0), Color.LIGHT_GRAY);
        setPermanentWidthAndHeight(statBar, 100, 10);
        statBar.setCornerRadius(10);
        statBar.setBorder(false);

        add(statBar);
    };

    public void setAttribute(String attributeName, String value) {
        stat.setText(value);

        if (attributeName.length() > 9) {
            attributeName = getShorterAttributeName(attributeName);
        }

        title.setText(attributeName);
        statBar.setValue(Integer.parseInt(value));
        statBar.setOne(getStatColour(Integer.parseInt(value)));
    }

    private String getShorterAttributeName(String attributeName) {
        if (attributeName.equals("Standing Tackle")) {
            return "St. Tackle";
        } else if (attributeName.equals("Sliding Tackle")) {
            return "Sl. Tackle";
        } else if (attributeName.equals("Short Passing")) {
            return "Short Pass";
        } else if(attributeName.equals("GK Reflexes")) {
            return "GK Reflex";
        } else if(attributeName.equals("GK Kicking")) {
            return "GK Kick";
        } else if(attributeName.equals("GK Handling")) {
            return "GK Handle";
        } else if (attributeName.equals("GK Positioning")) {
            return "GK Pos.";
        } else if (attributeName.equals("Ball Control")) {
            return "Ball Cont.";
        } else if (attributeName.equals("Acceleration")) {
            return "Accelerate";
        } else if (attributeName.equals("Sprint Speed")) {
            return "Speed";
        } else {
            throw new IllegalArgumentException("Invalid attribute name: " + attributeName);
        }
    }

    private Color getStatColour(int value) {
        if (value == 99) {
            return new Color(0, 0, 0);
        } else if (value > 89) {
            return new Color(255, 215, 0);
        } else if (value > 79) {
            return new Color(5, 163, 0);
        } else if (value > 69) {
            return new Color(183, 255, 0);
        } else if (value > 59) {
            return new Color(255, 149, 1);
        } else {
            return new Color(255, 1, 5);
        }
    }
}

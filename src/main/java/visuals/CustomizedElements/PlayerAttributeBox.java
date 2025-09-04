package visuals.CustomizedElements;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

import static visuals.CustomizedElements.GamePanel.*;

public class PlayerAttributeBox extends Box {

    private CustomProgressBar statBar;
    private CustomizedLabel title, stat;
    private JLabel increase;

    public PlayerAttributeBox() {
        super(BoxLayout.Y_AXIS);

        Box attributeTitle = Box.createHorizontalBox();
        title = new CustomizedLabel("Default", 16f);
        title.setBorder(new EmptyBorder(0, 4, 0, 0));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        increase = new JLabel();
        increase.setHorizontalAlignment(SwingConstants.RIGHT);

        stat = new CustomizedLabel(String.valueOf(0), 16f);
        stat.setBorder(new EmptyBorder(0, 0, 0, 4));
        stat.setHorizontalAlignment(SwingConstants.RIGHT);

        attributeTitle.add(title);
        attributeTitle.add(increase);
        attributeTitle.add(stat);

        SwingUtilities.invokeLater(() -> {
            setPermanentWidthAndHeight(title, 60, 20);
            setPermanentWidthAndHeight(increase, 20, 20);
            setPermanentWidthAndHeight(stat, 20, 20);
            setPermanentWidth(this, 110);
        });

        add(attributeTitle);

        statBar = new CustomProgressBar(getStatColour(0), Color.LIGHT_GRAY);
        setPermanentWidthAndHeight(statBar, 100, 10);
        statBar.setCornerRadius(10);
        statBar.setBorder(false);

        add(statBar);
    };

    public void addSingleIncrease() {
        ImageIcon newIcon = new ImageIcon("./src/main/java/visuals/Images/single-attribute.png", "SingleIncrease");
        increase.setIcon(alterImageSizeWithTarget(newIcon, 12));
        increase.revalidate();
        increase.repaint();
    }

    public void addDoubleIncrease() {
        ImageIcon newIcon = new ImageIcon("./src/main/java/visuals/Images/double-attribute.png", "DoubleIncrease");
        increase.setIcon(alterImageSizeWithTarget(newIcon, 12));
        increase.revalidate();
        increase.repaint();
    }

    public void addTripleIncrease() {
        ImageIcon newIcon = new ImageIcon("./src/main/java/visuals/Images/triple-attribute.png", "TripleIncrease");
        increase.setIcon(alterImageSizeWithTarget(newIcon, 12));
        increase.revalidate();
        increase.repaint();
    }

    public void setAttribute(String attributeName, String value) {
        stat.setText(value);

        if (attributeName.length() > 10) {
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
        } else if (attributeName.equals("Long Passing")) {
            return "Long Pass";
        } else if(attributeName.equals("GK Reflexes")) {
            return "GK Reflex";
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
        } else if (attributeName.equals("Attacking Positioning")) {
            return "Att. Pos.";
        } else if (attributeName.equals("Defensive Positioning")) {
            return "Def. Pos.";
        } else if (attributeName.equals("Interceptions")) {
            return "Intercept";
        } else if (attributeName.equals("Heading Accuracy")) {
            return "Heading";
        } else if (attributeName.equals("Free Kick Accuracy")) {
            return "Free Kicks";
        } else {
            throw new IllegalArgumentException("Invalid attribute name: " + attributeName);
        }
    }

    public static Color getStatColour(int value) {
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

    public CustomizedLabel getTitle() {
        return title;
    }

    public void setTitle(CustomizedLabel title) {
        this.title = title;
    }

    public CustomizedLabel getStat() {
        return stat;
    }

    public void setStat(CustomizedLabel stat) {
        this.stat = stat;
    }

    public CustomProgressBar getStatBar() {
        return statBar;
    }

    public void setStatBar(CustomProgressBar statBar) {
        this.statBar = statBar;
    }
}

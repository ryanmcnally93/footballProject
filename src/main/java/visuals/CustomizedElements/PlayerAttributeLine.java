package visuals.CustomizedElements;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

import static visuals.CustomizedElements.GamePanel.setPermanentWidthAndHeight;

public class PlayerAttributeLine extends Box {

    private PlayerAttributeBox firstBox, secondBox, thirdBox, fourthBox, fifthBox;
    private CustomizedLabel title;
    private boolean smallerLine = false;
    private CircledOVRLabel ovr;

    public PlayerAttributeLine(boolean smallerLine) {
        super(BoxLayout.Y_AXIS);
        this.smallerLine = smallerLine;
        title = new CustomizedLabel("", 16f);

        firstBox = new PlayerAttributeBox();
        secondBox = new PlayerAttributeBox();
        ovr = new CircledOVRLabel(0);
        setPermanentWidthAndHeight(ovr, 100, 136);

        add(Box.createVerticalStrut(18));
        add(ovr);
        add(Box.createVerticalStrut(20));
        add(firstBox);
        add(Box.createVerticalStrut(14));
        add(secondBox);
        add(Box.createVerticalStrut(50));

        ovr.setAlignmentX(Component.CENTER_ALIGNMENT);
        firstBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        secondBox.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public PlayerAttributeLine() {
        super(BoxLayout.Y_AXIS);
        title = new CustomizedLabel("", 16f);

        firstBox = new PlayerAttributeBox();
        secondBox = new PlayerAttributeBox();
        thirdBox = new PlayerAttributeBox();
        fourthBox = new PlayerAttributeBox();
        fifthBox = new PlayerAttributeBox();

        add(Box.createVerticalStrut(18));
        add(title);
        add(Box.createVerticalStrut(4));
        add(firstBox);
        add(Box.createVerticalStrut(14));
        add(secondBox);
        add(Box.createVerticalStrut(14));
        add(thirdBox);
        add(Box.createVerticalStrut(14));
        add(fourthBox);
        add(Box.createVerticalStrut(14));
        add(fifthBox);
        add(Box.createVerticalStrut(50));
    }

    public void changeContent(Map<String, String> attributes, String titleValue) {
        int i = 0;

        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            switch (i) {
                case 0 -> firstBox.setAttribute(entry.getKey(), entry.getValue());
                case 1 -> secondBox.setAttribute(entry.getKey(), entry.getValue());
                case 2 -> thirdBox.setAttribute(entry.getKey(), entry.getValue());
                case 3 -> fourthBox.setAttribute(entry.getKey(), entry.getValue());
                case 4 -> fifthBox.setAttribute(entry.getKey(), entry.getValue());
                default -> throw new IllegalArgumentException("Invalid attribute amount: " + i);
            }
            i++;
        }
        if (smallerLine) {
            ovr.setText(titleValue);
        } else {
            title.setText(titleValue);
        }
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        System.out.println("Box size: " + firstBox.getSize());
        System.out.println("Title: " + firstBox.getTitle().getText());
        System.out.println("Stat: " + firstBox.getStat().getText());

        revalidate();
        repaint();
    }
}

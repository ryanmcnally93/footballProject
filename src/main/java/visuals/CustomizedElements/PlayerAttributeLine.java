package visuals.CustomizedElements;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class PlayerAttributeLine extends Box {

    private PlayerAttributeBox firstBox, secondBox, thirdBox, fourthBox, fifthBox;
    private CustomizedLabel title;

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

    public void changeContent(Map<String, String> gkAttributes, String titleValue) {
        int i = 0;

        for (Map.Entry<String, String> entry : gkAttributes.entrySet()) {
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
        title.setText(titleValue);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        revalidate();
        repaint();
    }
}

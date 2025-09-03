package visuals.CustomizedElements;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class PlayerAttributeLine extends Box {

    private PlayerAttributeBox firstBox, secondBox, thirdBox, fourthBox, fifthBox;

    public PlayerAttributeLine() {
        super(BoxLayout.Y_AXIS);

        firstBox = new PlayerAttributeBox();
        secondBox = new PlayerAttributeBox();
        thirdBox = new PlayerAttributeBox();
        fourthBox = new PlayerAttributeBox();
        fifthBox = new PlayerAttributeBox();

        add(Box.createVerticalStrut(50));
        add(firstBox);
        add(Box.createVerticalStrut(10));
        add(secondBox);
        add(Box.createVerticalStrut(10));
        add(thirdBox);
        add(Box.createVerticalStrut(10));
        add(fourthBox);
        add(Box.createVerticalStrut(10));
        add(fifthBox);
        add(Box.createVerticalStrut(50));

        setOpaque(true);
        setBackground(Color.white);
    }

    public void changeContent(Map<String, String> gkAttributes) {
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

        revalidate();
        repaint();
    }
}

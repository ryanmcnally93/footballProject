package visuals.CustomizedElements;

import javax.swing.*;

public class PlayerAttributeBox extends Box {

    private String attributeName;
    private int value;

    public PlayerAttributeBox(String attribute, String value) {
        super(BoxLayout.X_AXIS);
        this.attributeName = attribute;
        this.value = Integer.parseInt(value);

        add(new CustomizedTitle(attributeName));
        add(new CustomizedTitle(value));

    }

}

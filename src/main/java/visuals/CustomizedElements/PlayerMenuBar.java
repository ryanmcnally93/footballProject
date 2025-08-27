package visuals.CustomizedElements;

import people.Footballer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PlayerMenuBar extends RoundedPanel {

    public PlayerMenuBar(Footballer player) {
        super(20);
        add(new JLabel(player.getName()));

        // This created padding, not margin!
        setBorder(new EmptyBorder(5, 5, 5, 5));
    }

}

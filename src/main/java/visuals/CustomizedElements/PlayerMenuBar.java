package visuals.CustomizedElements;

import people.Footballer;

import javax.swing.*;
import java.awt.*;

import static visuals.CustomizedElements.GamePanel.getBebasNeueFontWithSize;
import static visuals.CustomizedElements.GamePanel.getCharcoal;

public class PlayerMenuBar extends CustomizedButton {

    private JLabel squadNo;

    public PlayerMenuBar(Footballer player) {
        super("       " + player.getName().charAt(0) + " " + player.getName().substring(player.getName().lastIndexOf(' ') + 1), 20);
        if(Integer.parseInt(player.getSquadNo()) < 10) {
            squadNo = new JLabel(" " + player.getSquadNo());
        } else {
            squadNo = new JLabel(player.getSquadNo());
        }
        squadNo.setFont(getBebasNeueFontWithSize(20));
        squadNo.setForeground(getSecondaryColor());
        add(squadNo);
        setHorizontalAlignment(SwingConstants.LEFT);
        setArcHeight(20);
        setArcWidth(20);
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

    @Override
    protected void childMouseExited() {
        squadNo.setForeground(getSecondaryColor());
    }

    @Override
    protected void childMouseEntered() {
        squadNo.setForeground(getPrimaryColor());
    }

}

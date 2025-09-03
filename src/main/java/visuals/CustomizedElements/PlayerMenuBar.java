package visuals.CustomizedElements;

import people.Footballer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static visuals.CustomizedElements.GamePanel.*;

public class PlayerMenuBar extends CustomizedButton {

    private Footballer player;
    private JLabel squadNo;
    private JLabel playerName;

    public PlayerMenuBar(Footballer player, String title) {
        super("", 20);
        this.player = player;
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0)); // left align, 5px gap

        squadNo = new JLabel(player.getSquadNo());
        playerName = new JLabel(title);

        if (player.getSquadNo().length() > 1) {
            squadNo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 2)); // top, left, bottom, right
        }

        squadNo.setFont(getBebasNeueFontWithSize(20));
        squadNo.setForeground(getSecondaryColor());
        squadNo.setHorizontalAlignment(SwingConstants.CENTER);
        setPermanentWidth(squadNo, 25);

        playerName.setFont(getBebasNeueFontWithSize(20));
        playerName.setForeground(getSecondaryColor());
        playerName.setHorizontalAlignment(SwingConstants.LEFT);

        add(squadNo, BorderLayout.WEST);
        add(playerName, BorderLayout.CENTER);
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
        playerName.setForeground(getSecondaryColor());
    }

    @Override
    protected void childMouseEntered() {
        squadNo.setForeground(getPrimaryColor());
        playerName.setForeground(getPrimaryColor());
    }

    public Footballer getPlayer() {
        return player;
    }

    public void setPlayer(Footballer player) {
        this.player = player;
    }

    public void setAsSelected() {
        setSelected();
        // This should be done ideally within the CustomisedButton class
        squadNo.setForeground(getPrimaryColor());
        playerName.setForeground(getPrimaryColor());
        setBackground(getSecondaryColor());
    }

}

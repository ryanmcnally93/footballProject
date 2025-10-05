package visuals.CustomizedElements;

import people.Footballer;

import javax.swing.*;
import java.awt.*;

import static visuals.CustomizedElements.GamePanel.*;

public class PlayerMenuBar extends RightBoxBar {

    private Footballer player;
    private JLabel squadNo;
    private JLabel playerName;

    public PlayerMenuBar(Footballer player, String title) {
        super(20);
        this.player = player;
        playerName = new JLabel(title);
    }

    @Override
    public void createBar() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0)); // left align, 5px gap

        squadNo = new JLabel(player.getSquadNo());

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

    @Override
    public void setAsSelected(boolean bool) {
        setSelected(bool);
        // This should be done ideally within the CustomisedButton class
        if (bool) {
            squadNo.setForeground(getPrimaryColor());
            playerName.setForeground(getPrimaryColor());
            setBackground(getSecondaryColor());
        } else {
            squadNo.setForeground(getSecondaryColor());
            playerName.setForeground(getSecondaryColor());
            setBackground(getPrimaryColor());
        }
    }

}

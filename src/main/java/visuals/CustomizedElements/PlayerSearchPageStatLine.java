package visuals.CustomizedElements;

import entities.Match;
import people.Footballer;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class PlayerSearchPageStatLine extends AbstractStatBar {

    private CustomizedButton selectIcon;
    private Footballer player;

    public PlayerSearchPageStatLine(){
        super(20, false);
        setBackground(getDarkGrey());
        setOpaque(false);
    }

    public PlayerSearchPageStatLine(Footballer player){
        super(20, false);
        this.player = player;
        buildColumns();
        setBackground(Color.LIGHT_GRAY);
    }

    public static PlayerSearchPageStatLine createTitleLine() {
        PlayerSearchPageStatLine line = new PlayerSearchPageStatLine();
        line.createColumn("Pos.", 50);
        line.createColumn("Player Name", 230, SwingConstants.LEFT);
        line.createColumn("Age", 50);
        line.createColumn("Value", 120);
        line.createColumn("Rat.", 50);
        line.createColumn("Club", 50);
        line.createColumn("", 50);
        for (JLabel column : line.getColumns()) {
            column.setForeground(Color.WHITE);
        }
        return line;
    }

    @Override
    protected void buildColumns() {
        createColumn(player.getLikedPosition(), 50);
        createColumn(player.getName(), 230, SwingConstants.LEFT);
        createColumn(String.valueOf(player.getCurrentAge()), 50);
        createColumn(String.valueOf(player.getValue()), 120);
        createColumn(String.valueOf(player.getOVR()), 50);
        createColumn("CR", 50); // player.getTeam().getCrest();
        ImageIcon buttonIcon = getIconWithSpecificSize("./src/main/java/visuals/Images/select_icon.png", "Select", 20);
        selectIcon = createColumn(buttonIcon, 30);
        selectIcon.setBorderWanted(false);
        selectIcon.setFillWanted(false);
        selectIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public CustomizedButton getSelectIcon() {
        return selectIcon;
    }

    public void setSelectIcon(CustomizedButton selectIcon) {
        this.selectIcon = selectIcon;
    }

    public Footballer getPlayer() {
        return player;
    }

    public void setPlayer(Footballer player) {
        this.player = player;
    }
}

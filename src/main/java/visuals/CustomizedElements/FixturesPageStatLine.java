package visuals.CustomizedElements;

import entities.Match;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class FixturesPageStatLine extends AbstractStatBar {

    private Match match;
    private CustomizedButton selectIcon;
    private String leftText;
    private boolean usersMatch;

    public FixturesPageStatLine(){
        super(20, false);
        setBackground(getDarkGrey());
        setOpaque(false);
    }

    public FixturesPageStatLine(Match match, String leftText, boolean usersMatch){
        super(20, false);
        this.match = match;
        this.leftText = leftText;
        this.usersMatch = usersMatch;
        buildColumns();
        setBackground(Color.LIGHT_GRAY);
    }

    public static FixturesPageStatLine createTitleLine() {
        FixturesPageStatLine line = new FixturesPageStatLine();
        line.createColumn("", 50);
        line.createColumn("Home", 225);
        line.createColumn("Score", 50);
        line.createColumn("Away", 225);
        line.createColumn("", 50);
        for (JLabel column : line.getColumns()) {
            column.setForeground(Color.WHITE);
        }
        return line;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    @Override
    protected void buildColumns() {
        createColumn(leftText, 50, usersMatch); // Blank Box Column, save width as button 50px?
        createColumn(match.getHome().getName(), 225, usersMatch);
        LocalDateTime date = match.getDateTime();
        createColumn(match.getTimer().getTime().equals("00:00") ? date.getDayOfMonth() + "/" + date.getMonthValue()
                : match.getHomeScore() + " - " + match.getAwayScore(), 50,  usersMatch);
        createColumn(match.getAway().getName(), 225, usersMatch);
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

    public String getLeftText() {
        return leftText;
    }

    public void setLeftText(String leftText) {
        this.leftText = leftText;
    }
}

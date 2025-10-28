package visuals.CustomizedElements;

import entities.Match;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class FixturesPageStatLine extends AbstractStatBar {

    private Match match;
    private CustomizedButton selectIcon;

    public FixturesPageStatLine(Match match){
        super(20, true);
        this.match = match;
        buildColumns();
        setBackground(new Color(245, 245, 245));
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public void gameComplete() {
        setBackground(Color.GREEN);
        getColumns().get(0).setText("FT");
        getColumns().get(2).setText(match.getHomeScore() + " - " + match.getAwayScore());
        refresh();
    }

    @Override
    protected void buildColumns() {
        createColumn("", 50); // Blank Box Column, save width as button 50px?
        createColumn(match.getHome().getName(), 225);
        if (match.getTimer().getTime().equals("00:00")) {
            LocalDateTime date = match.getDateTime();
            createColumn(date.getDayOfMonth() + "/" + date.getMonthValue(), 50);
        } else {
            createColumn(match.getHomeScore() + " - " + match.getAwayScore(), 50);
        }
        createColumn(match.getAway().getName(), 225);
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
}

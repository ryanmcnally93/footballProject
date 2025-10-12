package visuals.CustomizedElements;

import entities.Match;

import javax.swing.*;
import java.awt.*;

public class FixturesPageStatLine extends AbstractStatBar {

    private JLabel title;
    private Match match;

    public FixturesPageStatLine(Match match){
        super(20, true);
        this.match = match;
        buildColumns(); // Let subclasses define which columns to add
    }

    public String getMatchTitle() {
        return title.getText();
    }

    public void setMatchTitle(JLabel name) {
        this.title = name;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public void gameComplete() {
        setBackground(Color.GREEN);
        refresh();
    }

    @Override
    protected void buildColumns() {
        if (match.getTimer().getTime().equals("00:00")) {
            title = createColumn(match.getHome().getName() + " vs " + match.getAway().getName(), 200);
        } else {
            title = createColumn(match.getHome().getName() + " " + match.getHomeScore() + " - " + match.getAwayScore() + " " + match.getAway().getName(), 200);
        }
    }
}

package visuals.CustomizedElements;

import entities.Match;
import people.Footballer;

import javax.swing.*;
import java.awt.*;

public class MatchLineOnFixturesPages extends RoundedPanel {

    private JLabel title;

    public MatchLineOnFixturesPages(Match match){
        super(20);

        setBackground(Color.LIGHT_GRAY);

        if (match.getTimer().getTime().equals("00:00")) {
            title = new JLabel(match.getHome().getName() + " vs " + match.getAway().getName());
        } else {
            title = new JLabel(match.getHome().getName() + " " + match.getHomeScore() + " - " + match.getAwayScore() + " " + match.getAway().getName());
        }

        add(title);

        setPermanentHeight(this, 30);
        revalidate();
        repaint();
    }

    public String getMatchTitle() {
        return title.getText();
    }

    public void setMatchTitle(JLabel name) {
        this.title = name;
    }

}
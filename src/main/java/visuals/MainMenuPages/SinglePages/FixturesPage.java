package visuals.MainMenuPages.SinglePages;

import entities.Match;
import entities.UsersMatch;
import visuals.CustomizedElements.MatchLineOnFixturesPages;
import visuals.MatchPages.MatchPageTemplate;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

public class FixturesPage extends LeftContentRightScrollPagesTemplate {

    private ArrayList<MatchLineOnFixturesPages> lines;

    public FixturesPage(Scheduler scheduler) {
        super(scheduler);
        getHeaderPanel().setTitle("My Fixtures");
        lines = new ArrayList<>();

        setVisible(true);
    }

    @Override
    public boolean isFromScheduler() {
        return true;
    }

    public void addFixtureLine(UsersMatch child) {
        MatchLineOnFixturesPages matchLine = new MatchLineOnFixturesPages(child);
        updateMatchLineListener(matchLine, child);
        lines.add(matchLine);
    }

    public void updateMatchLineListener(MatchLineOnFixturesPages line, UsersMatch matchToView) {
        if (line.getMouseListeners().length > 1) {
            MouseListener[] listeners = line.getMouseListeners();
            line.removeMouseListener(listeners[listeners.length - 1]);
        }
        line.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (Map.Entry<String, JPanel> page : getScheduler().getMatchFramesMap().entrySet()) {
                    MatchPageTemplate frame = (MatchPageTemplate) page.getValue();
                    matchToView.setScheduler(getScheduler());
                    frame.setMatch(matchToView);
                    frame.setFromScheduler(true);
                }
                // Provide back button for the first viewed MatchPage when viewing through main menu
                if (matchToView.isMatchHasPlayed()) {
                    getScheduler().getStatsPanel().getFooterPanel().getBackButtonBox().add(getScheduler().getStatsPanel().getBackButton());
                } else {
                    getScheduler().getTablePanel().getFooterPanel().getBackButtonBox().add(getScheduler().getTablePanel().getBackButton());
                }
                getScheduler().setMatch(matchToView);
                getScheduler().displayMatchFrames(matchToView);
            }
        });
    }

    public void organiseMyFixtures() {
        lines.sort(new Comparator<MatchLineOnFixturesPages>() {
            @Override
            public int compare(MatchLineOnFixturesPages line1, MatchLineOnFixturesPages line2) {
                return line1.getMatch().getDateTime().compareTo(line2.getMatch().getDateTime());
            }
        });

        for (MatchLineOnFixturesPages eachLine : lines) {
            getLeftBox().add(eachLine);
        }
        getLeftBox().revalidate();
        getLeftBox().repaint();
    }

    public MatchLineOnFixturesPages getLine(Match match) {
        for (MatchLineOnFixturesPages eachLine : lines) {
            if (eachLine.getMatch().toString().equals(match.toString())) {
                return eachLine;
            }
        }
        System.out.println("ERROR You haven't found your match line");
        return new MatchLineOnFixturesPages(match);
    }
}

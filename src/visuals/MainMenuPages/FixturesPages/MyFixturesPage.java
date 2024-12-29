package visuals.MainMenuPages.FixturesPages;
import entities.UsersMatch;
import visuals.CustomizedElements.MatchLineOnFixturesPages;
import visuals.CustomizedElements.PlayerAchievementLine;
import visuals.MainMenuPages.MainMenuPageTemplate;
import visuals.MatchPages.MatchFrames;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

public class MyFixturesPage extends MainMenuPageTemplate {

    private JPanel mainPanel;
    private Box centerBox;
    private JScrollPane scroller;
    private ArrayList<MatchLineOnFixturesPages> lines;

    public MyFixturesPage(CardLayout cardLayout, JPanel pages, Scheduler scheduler, boolean fromScheduler){
        super(cardLayout, pages, scheduler, fromScheduler);
        mainPanel = pages;
        getHeaderPanel().setTitle("My Fixtures");
        lines = new ArrayList<>();

        JLayeredPane layeredPane = getLayeredPane();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        centerBox = Box.createVerticalBox();
        mainPanel.add(centerBox);

        scroller = makeScroller(centerBox);
        mainPanel.add(scroller);

        mainPanel.setBounds(35, 90, 730, 420);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(mainPanel, JLayeredPane.DEFAULT_LAYER);

        setVisible(true);
    }

    public void addFixtureLine(UsersMatch child) {
        MatchLineOnFixturesPages matchLine = new MatchLineOnFixturesPages(child);
        matchLine.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (Map.Entry<String, JPanel> page : getScheduler().getMatchFramesMap().entrySet()) {
                    MatchFrames frame = (MatchFrames) page.getValue();
                    child.setScheduler(getScheduler());
                    frame.setMatch(child);
                    frame.setFromScheduler(true);
                }
                // Provide back button for the first viewed MatchPage when viewing through main menu
                if (child.isMatchHasPlayed()) {
                    getScheduler().getStatsPanel().getFooterPanel().getBackButtonBox().add(getScheduler().getStatsPanel().getBackButton());
                } else {
                    getScheduler().getTablePanel().getFooterPanel().getBackButtonBox().add(getScheduler().getTablePanel().getBackButton());
                }
                getScheduler().displayMatchFrames(child);
            }
        });
        lines.add(matchLine);
    }

    public void organiseMyFixtures() {
        lines.sort(new Comparator<MatchLineOnFixturesPages>() {
            @Override
            public int compare(MatchLineOnFixturesPages line1, MatchLineOnFixturesPages line2) {
                return line1.getMatch().getDateTime().compareTo(line2.getMatch().getDateTime());
            }
        });

        for (MatchLineOnFixturesPages eachLine : lines) {
            centerBox.add(eachLine);
        }
        centerBox.revalidate();
        centerBox.repaint();
    }

    public MatchLineOnFixturesPages getLine(UsersMatch match) {
        for (MatchLineOnFixturesPages eachLine : lines) {
            if (eachLine.getMatch() == match) {
                return eachLine;
            }
        }
        System.out.println("ERROR You haven't found your match line");
        return new MatchLineOnFixturesPages(match);
    }
}

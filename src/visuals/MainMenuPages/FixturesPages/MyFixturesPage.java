package visuals.MainMenuPages.FixturesPages;
import entities.UsersMatch;
import visuals.CustomizedElements.MatchLineOnFixturesPages;
import visuals.MainMenuPages.MainMenuPageTemplate;
import visuals.MatchPages.MatchFrames;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

public class MyFixturesPage extends MainMenuPageTemplate {

    private JPanel mainPanel;
    private Box centerBox;
    private JScrollPane scroller;

    public MyFixturesPage(CardLayout cardLayout, JPanel pages, Scheduler scheduler, boolean fromScheduler){
        super(cardLayout, pages, scheduler, fromScheduler);
        mainPanel = pages;
        getHeaderPanel().setTitle("My Fixtures");

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
                // Provide back button for MatchPages when viewing through main menu
                getScheduler().getStatsPanel().getFooterPanel().getBackButtonBox().add(getScheduler().getStatsPanel().getBackButton());
                getScheduler().displayMatchFrames(child, true);
            }
        });
        centerBox.add(matchLine);
        centerBox.revalidate();
        centerBox.repaint();
    }
}

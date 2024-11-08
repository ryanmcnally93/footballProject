package visuals.MainMenuPages.LeaderboardPages;
import visuals.CustomizedElements.PlayerLeaderboards;
import visuals.MainMenuPages.MainMenuPageTemplate;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import java.awt.*;

public class TopGoalscorersPage extends MainMenuPageTemplate {

    private JPanel mainPanel;
    private PlayerLeaderboards leaderboard;

    public TopGoalscorersPage(CardLayout cardLayout, JPanel pages, Scheduler scheduler, boolean fromScheduler){
        super(cardLayout, pages, scheduler, fromScheduler);
        this.leaderboard = scheduler.getLeague().getPlayerLeaderboard();
        getHeaderPanel().setTitle("Player Leaderboards");
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        appendEastAndWest(mainPanel);

        Box centerBox = Box.createVerticalBox();
        centerBox.setBackground(Color.LIGHT_GRAY);
        centerBox.add(leaderboard);

        JScrollPane scroller = makeScroller(centerBox);

        mainPanel.add(scroller);

        mainPanel.setBounds(0, 80, 800, 420);
        mainPanel.setBackground(Color.LIGHT_GRAY);

        getLayeredPane().add(mainPanel);
    }

}

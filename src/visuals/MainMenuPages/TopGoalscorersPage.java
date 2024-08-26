package visuals.MainMenuPages;
import visuals.CustomizedElements.PlayerLeaderboards;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import java.awt.*;

public class TopGoalscorersPage extends MainMenuPageTemplate {

    private JPanel mainPanel;

    public TopGoalscorersPage(CardLayout cardLayout, JPanel pages, Scheduler scheduler){
        super(cardLayout, pages, scheduler);
        getHeaderPanel().setTitle("Player Leaderboards");
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        appendEastAndWest(mainPanel);

        Box centerBox = Box.createVerticalBox();
        centerBox.setBackground(Color.LIGHT_GRAY);
        PlayerLeaderboards leaderboard = new PlayerLeaderboards(scheduler);
        centerBox.add(leaderboard);

        JScrollPane scroller = makeScroller(centerBox);

        mainPanel.add(scroller);

        mainPanel.setBounds(0, 80, 800, 420);
        mainPanel.setBackground(Color.LIGHT_GRAY);

        getLayeredPane().add(mainPanel);
    }

}

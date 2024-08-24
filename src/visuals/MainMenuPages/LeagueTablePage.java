package visuals.MainMenuPages;
import visuals.CustomizedElements.LeagueTable;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import java.awt.*;

public class LeagueTablePage extends MainMenuPageTemplate {

    private JPanel mainPanel;
    private Box centerBox;
    private JScrollPane scroller;
    private LeagueTable table;

    public LeagueTablePage(CardLayout cardLayout, JPanel pages, Scheduler scheduler){
        super(cardLayout, pages, scheduler);
        getHeaderPanel().setTitle("League Table");
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        appendEastAndWest(mainPanel);

        centerBox = Box.createVerticalBox();
        centerBox.setBackground(Color.LIGHT_GRAY);
        table = scheduler.getLeague().getLeagueTable();
        centerBox.add(table);
        Box padding = Box.createVerticalBox();
        padding.add(Box.createVerticalStrut(20));  // 20 pixels of padding, adjust as needed
        centerBox.add(padding);

        scroller = makeScroller(centerBox);

        mainPanel.add(scroller);

        mainPanel.setBounds(0, 80, 800, 420);
        mainPanel.setBackground(Color.LIGHT_GRAY);

        getLayeredPane().add(mainPanel);
    }

}

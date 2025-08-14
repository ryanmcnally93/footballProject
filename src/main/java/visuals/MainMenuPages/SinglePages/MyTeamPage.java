package main.java.visuals.MainMenuPages.SinglePages;

import main.java.visuals.CustomizedElements.MainPageTemplate;
import main.java.visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import java.awt.*;

public class MyTeamPage extends MainPageTemplate {

    private JPanel mainPanel;
    private Box centerBox;
    private JScrollPane scroller;

    public MyTeamPage(Scheduler scheduler) {
        super(scheduler);
        getHeaderPanel().setTitle(scheduler.getTeam().getName());

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

        getFooterPanel().addBackButton();
        updateBackButtonFunctionality();

        setVisible(true);
    }

    @Override
    public boolean isFromScheduler() {
        return true;
    }
}

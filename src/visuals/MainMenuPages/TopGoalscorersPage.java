package visuals.MainMenuPages;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import java.awt.*;

public class TopGoalscorersPage extends MainMenuPageTemplate {

    private JPanel mainPanel;

    public TopGoalscorersPage(CardLayout cardLayout, JPanel pages, Scheduler scheduler){
        super(cardLayout, pages, scheduler);
        mainPanel = pages;
        getHeaderPanel().setTitle("Top Goalscorers");
    }

}

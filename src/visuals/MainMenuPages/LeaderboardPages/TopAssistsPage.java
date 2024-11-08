package visuals.MainMenuPages.LeaderboardPages;
import visuals.MainMenuPages.MainMenuPageTemplate;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import java.awt.*;

public class TopAssistsPage extends MainMenuPageTemplate {

    private JPanel mainPanel;

    public TopAssistsPage(CardLayout cardLayout, JPanel pages, Scheduler scheduler, boolean fromScheduler){
        super(cardLayout, pages, scheduler, fromScheduler);
        mainPanel = pages;
        getHeaderPanel().setTitle("Top Assists");
    }

}

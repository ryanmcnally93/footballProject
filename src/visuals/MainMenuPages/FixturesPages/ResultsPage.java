package visuals.MainMenuPages.FixturesPages;
import visuals.MainMenuPages.MainMenuPageTemplate;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import java.awt.*;

public class ResultsPage extends MainMenuPageTemplate {

    private JPanel mainPanel;

    public ResultsPage(CardLayout cardLayout, JPanel pages, Scheduler scheduler, boolean fromScheduler){
        super(cardLayout, pages, scheduler, fromScheduler);
        mainPanel = pages;
        getHeaderPanel().setTitle("Results");
    }

}

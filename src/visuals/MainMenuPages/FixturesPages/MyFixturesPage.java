package visuals.MainMenuPages.FixturesPages;
import visuals.MainMenuPages.MainMenuPageTemplate;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import java.awt.*;

public class MyFixturesPage extends MainMenuPageTemplate {

    private JPanel mainPanel;

    public MyFixturesPage(CardLayout cardLayout, JPanel pages, Scheduler scheduler, boolean fromScheduler){
        super(cardLayout, pages, scheduler, fromScheduler);
        mainPanel = pages;
        getHeaderPanel().setTitle("My Fixtures");
    }

}

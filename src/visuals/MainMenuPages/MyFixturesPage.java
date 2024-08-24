package visuals.MainMenuPages;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import java.awt.*;

public class MyFixturesPage extends MainMenuPageTemplate {

    private JPanel mainPanel;

    public MyFixturesPage(CardLayout cardLayout, JPanel pages, Scheduler scheduler){
        super(cardLayout, pages, scheduler);
        mainPanel = pages;
        getHeaderPanel().setTitle("My Fixtures");
    }

}

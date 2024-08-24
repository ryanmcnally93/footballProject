package visuals.MainMenuPages;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import java.awt.*;

public class AllFixturesPage extends MainMenuPageTemplate {

    private JPanel mainPanel;

    public AllFixturesPage(CardLayout cardLayout, JPanel pages, Scheduler scheduler){
        super(cardLayout, pages, scheduler);
        mainPanel = pages;
        getHeaderPanel().setTitle("All Fixtures");
    }

}

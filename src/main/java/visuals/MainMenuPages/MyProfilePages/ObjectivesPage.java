package visuals.MainMenuPages.MyProfilePages;

import visuals.MainMenuPages.TacticsPageTemplate;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import java.awt.*;

public class ObjectivesPage extends TacticsPageTemplate {

    private JPanel mainPanel;

    public ObjectivesPage(CardLayout cardLayout, JPanel pages, Scheduler scheduler, boolean fromScheduler){
        super(cardLayout, pages, scheduler, fromScheduler);
        mainPanel = pages;
        getHeaderPanel().setTitle("Objectives");
    }

}

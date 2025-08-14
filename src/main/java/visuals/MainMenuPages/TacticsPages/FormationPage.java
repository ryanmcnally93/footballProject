package main.java.visuals.MainMenuPages.TacticsPages;
import main.java.visuals.MainMenuPages.MainMenuPageTemplate;
import main.java.visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import java.awt.*;

public class FormationPage extends MainMenuPageTemplate {

    private JPanel mainPanel;

    public FormationPage(CardLayout cardLayout, JPanel pages, Scheduler scheduler, boolean fromScheduler){
        super(cardLayout, pages, scheduler, fromScheduler);
        mainPanel = pages;
        getHeaderPanel().setTitle("Formation");
    }

}

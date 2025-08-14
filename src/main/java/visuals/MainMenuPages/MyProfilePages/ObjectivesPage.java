package main.java.visuals.MainMenuPages.MyProfilePages;

import main.java.visuals.MainMenuPages.MainMenuPageTemplate;
import main.java.visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import java.awt.*;

public class ObjectivesPage extends MainMenuPageTemplate {

    private JPanel mainPanel;

    public ObjectivesPage(CardLayout cardLayout, JPanel pages, Scheduler scheduler, boolean fromScheduler){
        super(cardLayout, pages, scheduler, fromScheduler);
        mainPanel = pages;
        getHeaderPanel().setTitle("Objectives");
    }

}

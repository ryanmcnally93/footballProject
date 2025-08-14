package main.java.visuals.MainMenuPages.MyClubPages;

import main.java.visuals.MainMenuPages.MainMenuPageTemplate;
import main.java.visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import java.awt.*;

public class FacilitiesPage extends MainMenuPageTemplate {

    private JPanel mainPanel;

    public FacilitiesPage(CardLayout cardLayout, JPanel pages, Scheduler scheduler, boolean fromScheduler){
        super(cardLayout, pages, scheduler, fromScheduler);
        mainPanel = pages;
        getHeaderPanel().setTitle("Facilities");
    }

}

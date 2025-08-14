package main.java.visuals.MainMenuPages.MyProfilePages;

import main.java.visuals.MainMenuPages.MainMenuPageTemplate;
import main.java.visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import java.awt.*;

public class TrophyPage extends MainMenuPageTemplate {

    private JPanel mainPanel;

    public TrophyPage(CardLayout cardLayout, JPanel pages, Scheduler scheduler, boolean fromScheduler){
        super(cardLayout, pages, scheduler, fromScheduler);
        mainPanel = pages;
        getHeaderPanel().setTitle("Achievements");
    }

}

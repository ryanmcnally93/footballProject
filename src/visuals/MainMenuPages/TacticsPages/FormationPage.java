package visuals.MainMenuPages.TacticsPages;
import visuals.MainMenuPages.MainMenuPageTemplate;
import visuals.ScheduleFrames.Scheduler;

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
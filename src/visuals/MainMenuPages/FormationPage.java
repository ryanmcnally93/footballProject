package visuals.MainMenuPages;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import java.awt.*;

public class FormationPage extends MainMenuPageTemplate {

    private JPanel mainPanel;

    public FormationPage(CardLayout cardLayout, JPanel pages, Scheduler scheduler){
        super(cardLayout, pages, scheduler);
        mainPanel = pages;
        getHeaderPanel().setTitle("Formation");
    }

}

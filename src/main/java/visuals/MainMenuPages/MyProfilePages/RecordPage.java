package visuals.MainMenuPages.MyProfilePages;

import visuals.MainMenuPages.TacticsPageTemplate;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import java.awt.*;

public class RecordPage extends TacticsPageTemplate {

    private JPanel mainPanel;

    public RecordPage(CardLayout cardLayout, JPanel pages, Scheduler scheduler, boolean fromScheduler){
        super(cardLayout, pages, scheduler, fromScheduler);
        mainPanel = pages;
        getHeaderPanel().setTitle("Record");
    }

}

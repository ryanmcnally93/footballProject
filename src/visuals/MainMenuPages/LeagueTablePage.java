package visuals.MainMenuPages;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import java.awt.*;

public class LeagueTablePage extends MainMenuPageTemplate {

    private JPanel mainPanel;

    public LeagueTablePage(CardLayout cardLayout, JPanel pages, Scheduler scheduler){
        super(cardLayout, pages, scheduler);
        mainPanel = pages;
        getHeaderPanel().setTitle("League Table");
    }

}

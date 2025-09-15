package visuals.MainMenuPages.TacticsPages;

import visuals.MainMenuPages.TacticsPageTemplate;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import java.awt.*;

public class MatchRolesPage extends TacticsPageTemplate {

    private JPanel mainPanel;

    public MatchRolesPage(CardLayout cardLayout, JPanel pages, Scheduler scheduler, boolean fromScheduler){
        super(cardLayout, pages, scheduler, fromScheduler);
        mainPanel = pages;
        getHeaderPanel().setTitle("Match Roles");
    }

}

package visuals.MainMenuPages.MyClubPages;

import visuals.MainMenuPages.TacticsPageTemplate;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import java.awt.*;

public class BudgetPage extends TacticsPageTemplate {

    private JPanel mainPanel;

    public BudgetPage(CardLayout cardLayout, JPanel pages, Scheduler scheduler, boolean fromScheduler){
        super(cardLayout, pages, scheduler, fromScheduler);
        mainPanel = pages;
        getHeaderPanel().setTitle("Budget");
    }

}

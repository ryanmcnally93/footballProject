package visuals.MainMenuPages;
import entities.UsersMatch;
import visuals.CustomizedElements.CardmapMainPageTemplate;
import visuals.ScheduleFrames.Scheduler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenuPageTemplate extends CardmapMainPageTemplate {

    private Scheduler scheduler;
    private JPanel mainPanel;
    private JButton back;
    private UsersMatch match;
    private boolean fromScheduler = true;

    public MainMenuPageTemplate(CardLayout cardLayout, JPanel pages, Scheduler scheduler, boolean fromScheduler){
        super(cardLayout, pages);
        this.scheduler = scheduler;
        this.fromScheduler = fromScheduler;

        // This right box in the footer will help push the other elements central
        JPanel backButtonBox = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        backButtonBox.setBackground(Color.LIGHT_GRAY);
        setPermanentWidth(backButtonBox, 115);

        // This left box contains the back button, which we only want to view in the main menu's cardmaps
        JPanel leftBlankBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftBlankBox.setBackground(Color.LIGHT_GRAY);
        setPermanentWidth(leftBlankBox, 115);
        back = new JButton("Back");
        backButtonBox.add(back);

        getFooterPanel().add(backButtonBox, BorderLayout.EAST);
        getFooterPanel().add(leftBlankBox, BorderLayout.WEST);

        addBackButtonFunctionality();

    }

    public void addBackButtonFunctionality(){
        if(fromScheduler) {
            back.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    scheduler.displayPage(scheduler.getWindow());
                }
            });
        } else {
            back.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    match.displayGame(scheduler.getWindow(), scheduler);
                }
            });
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}

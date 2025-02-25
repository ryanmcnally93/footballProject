package visuals.MainMenuPages;
import entities.UsersMatch;
import visuals.CustomizedElements.CardmapMainPageTemplate;
import visuals.MatchPages.MatchFrames;
import visuals.ScheduleFrames.Events;
import visuals.ScheduleFrames.Scheduler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

public class MainMenuPageTemplate extends CardmapMainPageTemplate {

    private Scheduler scheduler;
    private JPanel mainPanel;
    private JButton back;
    private UsersMatch match;
    private Events event;

    public MainMenuPageTemplate(CardLayout cardLayout, JPanel pages, Scheduler scheduler, boolean fromScheduler){
        super(cardLayout, pages);
        this.scheduler = scheduler;
        setFromScheduler(fromScheduler);

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

        updateBackButtonFunctionality();
    }

    public void updateBackButtonFunctionality(){
        if (event == null) {
            if (back.getMouseListeners().length == 1) {
                addBackButtonListener(back);
            } else {
                MouseListener[] listeners = back.getMouseListeners();
                back.removeMouseListener(listeners[listeners.length - 1]);
                addBackButtonListener(back);
            }
        } else {
            if (back.getMouseListeners().length == 1) {
                addPlayFunctionalityToBackButton(back);
            } else {
                MouseListener[] listeners = back.getMouseListeners();
                back.removeMouseListener(listeners[listeners.length - 1]);
                addPlayFunctionalityToBackButton(back);
            }
        }
    }

    public void addPlayFunctionalityToBackButton(JButton back) {
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (Map.Entry<String, JPanel> page : scheduler.getMatchFramesMap().entrySet()) {
                    MatchFrames frame = (MatchFrames) page.getValue();
                    event.getMatch().setScheduler(scheduler);
                    frame.setMatch(event.getMatch());
                }
                scheduler.displayMatchFrames(event.getMatch());
                setEvent(null);
            }
        });
    }

    public void addBackButtonListener(JButton back) {
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isFromScheduler()) {
                    scheduler.displayPage(scheduler.getWindow());
                } else {
                    scheduler.displayMatchFrames(scheduler.getMatch());
                }
            }
        });
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

    public UsersMatch getMatch() {
        return match;
    }

    public void setMatch(UsersMatch match) {
        this.match = match;
    }

    public JButton getBackButton() {
        return back;
    }

    public void setBackButton(JButton back) {
        this.back = back;
    }

    public Events getEvent() {
        return event;
    }

    public void setEvent(Events event) {
        if (event != null) {
            this.event = event;
            back.setText("Kick Off");
            updateBackButtonFunctionality();
        } else {
            this.event = event;
            back.setText("Back");
            updateBackButtonFunctionality();
        }
    }
}

package visuals.CustomizedElements;

import entities.Match;
import visuals.ScheduleFrames.Events;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class MessageViewer extends GamePanel {

    private CustomizedButton advance, playGame, simGame, dismiss, advanceToGame;
    private Scheduler scheduler;
    private Box buttonContainer;
    private JPanel messageContainer;
    private Image backgroundImage;

    public MessageViewer(Scheduler scheduler) {
        setPermanentWidthAndHeight(this, 600, 210);
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        ImageIcon image = new ImageIcon("./src/visuals/Images/message_viewer_tablet.png");
        backgroundImage = image.getImage().getScaledInstance(600, 210, Image.SCALE_SMOOTH);

        this.scheduler = scheduler;

        advance = new CustomizedButton("Advance", 16);
        advance.setAlignmentX(Component.CENTER_ALIGNMENT);
        advance.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                scheduler.addDay();
            }
        });

        playGame = new CustomizedButton("Play", 16);
        playGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        simGame = new CustomizedButton("Simulate", 16);
        simGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        dismiss = new CustomizedButton("Dismiss", 16);
        dismiss.setAlignmentX(Component.CENTER_ALIGNMENT);
        advanceToGame = new CustomizedButton("Skip to Matchday", 16);
        advanceToGame.setAlignmentX(Component.CENTER_ALIGNMENT);

        messageContainer = new JPanel(new BorderLayout());
        messageContainer.setOpaque(false);
        setPermanentWidthAndHeight(messageContainer, 600, 155);
        messageContainer.setBorder(new EmptyBorder(20,20,0,0));
        add(messageContainer);

        buttonContainer = Box.createHorizontalBox();
        buttonContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonContainer.setOpaque(false);
        setPermanentWidthAndHeight(buttonContainer, 250, 40);
        add(buttonContainer);
        buttonContainer.add(Box.createHorizontalGlue());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getCharcoal()); // Or set a custom color for the rounded corners
        Shape roundedRectangle = new RoundRectangle2D.Float(2, 2, getWidth() - 4, getHeight(), 55, 55);
        g2d.fill(roundedRectangle);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void addAdvanceToGameButton(ArrayList<Events> events) {
        // Check to see if this is already visible
        if (!buttonContainer.isAncestorOf(advanceToGame)) {
            buttonContainer.add(advanceToGame);
            buttonContainer.add(Box.createHorizontalGlue());
        }

        // Remove previous listener
        MouseListener[] listeners = advanceToGame.getMouseListeners();
        for (int i = listeners.length - 1; i >= 0; i--) {
            advanceToGame.removeMouseListener(listeners[i]);
        }

        // Update with this event
        advanceToGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int counter = 0;
                while(counter == 0){
                    scheduler.addDay();
                    for(Events each : events) {
                        if (each.getDate().toLocalDate().equals(scheduler.getDate().toLocalDate())) {
                            counter++;
                        }
                    }
                }
                removeAdvanceToGameButton();
            }
        });
    }

    public void removeAdvanceToGameButton() {
        if (buttonContainer.isAncestorOf(advanceToGame)) {
            removeButton(advanceToGame);
        }
    }

    public void removeButton(CustomizedButton button) {
        if (buttonContainer.isAncestorOf(button)) {
            Component[] components = buttonContainer.getComponents();
            for (int i = 0; i < components.length; i++) {
                Component component = components[i];
                // Check if the component is the button we want to remove
                if (component == button) {
                    // Remove glue after the button
                    if (i + 1 < buttonContainer.getComponentCount() && buttonContainer.getComponent(i + 1) instanceof Box.Filler) {
                        buttonContainer.remove(i + 1);
                    }
                    buttonContainer.remove(i);  // Remove the button itself
                    break;
                }
            }
        }
    }

    public void displayEvent(Events event) {
        JLabel sender = event.getSender();
        sender.setOpaque(false);
        sender.setBorder(new EmptyBorder(5,10,0,0));
        sender.setForeground(Color.WHITE);
        setPermanentWidthAndHeight(sender,600, sender.getPreferredSize().height + 5);

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        // Not working?
        setPermanentWidthAndHeight(separator, 300, 1);
        separator.setBackground(Color.WHITE);
        separator.setForeground(Color.WHITE);

        messageContainer.add(sender, BorderLayout.NORTH);
        messageContainer.add(separator, BorderLayout.CENTER);
        JLabel description = event.getDescription();
        description.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageContainer.add(description, BorderLayout.SOUTH);
        messageContainer.revalidate();
        messageContainer.repaint();
    }

    public void addDismissButton(Events event, ArrayList<Events> todaysEvents) {
        // Check to see if this is already visible
        if (!buttonContainer.isAncestorOf(dismiss)) {
            buttonContainer.add(dismiss);
            buttonContainer.add(Box.createHorizontalGlue());
        }

        // Remove previous listener
        MouseListener[] listeners = dismiss.getMouseListeners();
        for (int i = listeners.length - 1; i >= 0; i--) {
            dismiss.removeMouseListener(listeners[i]);
        }

        // Update with this event
        dismiss.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                removeDismissButton();
                addAdvanceButton();

                messageContainer.removeAll();
                event.setRemoveEvent(true);
                scheduler.showTodaysEvents(todaysEvents);

                revalidate();
                repaint();
            }
        });
    }

    public void removeDismissButton() {
        if (buttonContainer.isAncestorOf(dismiss)) {
            removeButton(dismiss);
        }
    }

    public void addPlayGameButton(Events event) {
        // Check to see if this is already visible
        if (!buttonContainer.isAncestorOf(playGame)) {
            buttonContainer.add(playGame);
            buttonContainer.add(Box.createHorizontalGlue());
        }

        // Remove previous listener
        MouseListener[] listeners = playGame.getMouseListeners();
        for (int i = listeners.length - 1; i >= 0; i--) {
            playGame.removeMouseListener(listeners[i]);
        }

        // Update with this event
        playGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                scheduler.viewTacticsPages(true, event);
                event.setRemoveEvent(true);
            }
        });
    }

    public void removePlayGameButton() {
        if (buttonContainer.isAncestorOf(playGame)) {
            removeButton(playGame);
        }
    }

    public void addSimGameButton(Events event, ArrayList<Events> todaysEvents) {
        // Check to see if this is already visible
        if (!buttonContainer.isAncestorOf(simGame)) {
            buttonContainer.add(simGame);
            buttonContainer.add(Box.createHorizontalGlue());
        }

        // Remove previous listener
        MouseListener[] listeners = simGame.getMouseListeners();
        for (int i = listeners.length - 1; i >= 0; i--) {
            simGame.removeMouseListener(listeners[i]);
        }

        // Update with this event
        simGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                removeSimGameButton();
                removePlayGameButton();
                if (event.getMatch() != null) {
                    // Change back to a normal match as this is not being shown to user
                    Match convertedMatch = new Match(event.getMatch());
                    convertedMatch.startMatch(scheduler, "instant");

                    for(Match eachMatch : event.getMatch().getSameDayMatches()){
                        CompletableFuture.runAsync(() -> eachMatch.startMatch("instant"));
                    }
                    scheduler.getMyFixtures().getLine(event.getMatch()).gameComplete();
                }

                event.setRemoveEvent(true);
                scheduler.showTodaysEvents(todaysEvents);
            }
        });
    }

    public void removeSimGameButton() {
        if (buttonContainer.isAncestorOf(simGame)) {
            removeButton(simGame);
        }
    }

    public void addAdvanceButton() {
        if (!buttonContainer.isAncestorOf(advance)) {
            buttonContainer.add(advance);
            buttonContainer.add(Box.createHorizontalGlue());
        }
    }

    public void removeAdvanceButton() {
        if (buttonContainer.isAncestorOf(advance)) {
            removeButton(advance);
        }
    }

    public CustomizedButton getAdvanceButton() {
        return advance;
    }

    public void setAdvanceButton(CustomizedButton advance) {
        this.advance = advance;
    }

    public void removeMessage() {
        messageContainer.removeAll();
    }
}

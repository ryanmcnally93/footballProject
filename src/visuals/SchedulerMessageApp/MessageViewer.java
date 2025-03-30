package visuals.SchedulerMessageApp;

import entities.Match;
import visuals.CustomizedElements.CustomizedButton;
import visuals.CustomizedElements.GamePanel;
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
    private Events event;
    private ArrayList<Events> todaysEvents;
    private boolean isWaitingForEventToUpdate = true;

    public MessageViewer(Scheduler scheduler) {
        this.scheduler = scheduler;
        setPermanentWidthAndHeight(this, 491, 208);
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        ImageIcon image = new ImageIcon("./src/visuals/Images/message_viewer_tablet.png");
        backgroundImage = image.getImage().getScaledInstance(491, 208, Image.SCALE_SMOOTH);

        createButtonsAndListeners();

        messageContainer = new JPanel(new BorderLayout());
        messageContainer.setOpaque(false);
        setPermanentWidthAndHeight(messageContainer, 491, 155);
        messageContainer.setBorder(new EmptyBorder(20,30,0,30));
        add(messageContainer);

        buttonContainer = Box.createHorizontalBox();
        buttonContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonContainer.setOpaque(false);
        setPermanentWidthAndHeight(buttonContainer, 250, 40);
        add(buttonContainer);
        buttonContainer.add(Box.createHorizontalGlue());
    }

    private void createButtonsAndListeners() {
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
        playGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                scheduler.viewTacticsPages(true, event);
                event.setRemoveEvent(true);
            }
        });

        simGame = new CustomizedButton("Simulate", 16);
        simGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        simGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                removeSimGameButton();
                removePlayGameButton();
                if (event.getMatch() != null) {
                    // Change back to a normal match as this is not being shown to user
                    Match convertedMatch = new Match(event.getMatch());
                    convertedMatch.startMatch(scheduler, "instant");

                    System.out.println("REACHED THE SIMGAME LINE AND BOOLEAN IS " + String.valueOf(isWaitingForEventToUpdate).toUpperCase());
                    for(Match eachMatch : event.getMatch().getSameDayMatches()){
                        CompletableFuture.runAsync(() -> eachMatch.startMatch("instant"));
                    }
                    scheduler.getMyFixtures().getLine(event.getMatch()).gameComplete();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            isWaitingForEventToUpdate = false;
                        }
                    });
                }

                event.setRemoveEvent(true);
                scheduler.showTodaysEvents(todaysEvents);
            }
        });

        dismiss = new CustomizedButton("Dismiss", 16);
        dismiss.setAlignmentX(Component.CENTER_ALIGNMENT);
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

        advanceToGame = new CustomizedButton("Skip to Matchday", 16);
        advanceToGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        advanceToGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int counter = 0;
                while (counter == 0) {
                    scheduler.addDay();
                    for (Events each : todaysEvents) {
                        if (each.getDate().toLocalDate().equals(scheduler.getDate().toLocalDate())) {
                            counter++;
                        }
                    }
                }
                removeAdvanceToGameButton();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(235, 235, 235)); // Or set a custom color for the rounded corners
        Shape roundedRectangle = new RoundRectangle2D.Float(2, 2, getWidth() - 4, getHeight(), 55, 55);
        g2d.fill(roundedRectangle);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void addAdvanceToGameButton(ArrayList<Events> events) {
        this.todaysEvents = events;
        // Check to see if this is already visible
        if (!buttonContainer.isAncestorOf(advanceToGame)) {
            buttonContainer.add(advanceToGame);
            buttonContainer.add(Box.createHorizontalGlue());
        }
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
        sender.setForeground(getGreenCharcoal());
        setPermanentWidthAndHeight(sender,491, sender.getPreferredSize().height + 5);
        messageContainer.add(sender, BorderLayout.NORTH);

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        setPermanentWidthAndHeight(separator,491, 1);
        separator.setBackground(getGreenCharcoal());
        separator.setForeground(getGreenCharcoal());
        messageContainer.add(separator, BorderLayout.CENTER);

        JLabel description = event.getDescription();
        description.setAlignmentX(Component.CENTER_ALIGNMENT);

        messageContainer.add(description, BorderLayout.SOUTH);
        messageContainer.revalidate();
        messageContainer.repaint();
    }

    public void addDismissButton(Events event, ArrayList<Events> todaysEvents) {
        // If we are adding a dismiss button after a match, we need to be sure
        // that all the background matches have been completed otherwise we will
        // reset the event, and still try to call event.getMatch() somewhere else
        if (event.getType().equals("Result")) {
            isWaitingForEventToUpdate = true;
            Timer timer = new Timer(250, e -> {
                if (!isWaitingForEventToUpdate) {
                    ((Timer) e.getSource()).stop();
                    addDismissButtonAfterChecks(event, todaysEvents);
                }
            });
            timer.start();
        } else {
            addDismissButtonAfterChecks(event, todaysEvents);
        }
    }

    public void addDismissButtonAfterChecks(Events event, ArrayList<Events> todaysEvents) {
        this.event = event;
        this.todaysEvents = todaysEvents;
        // Check to see if this is already visible
        if (!buttonContainer.isAncestorOf(dismiss)) {
            buttonContainer.add(dismiss);
            buttonContainer.add(Box.createHorizontalGlue());
        }
        buttonContainer.revalidate();
        buttonContainer.repaint();
    }

    public void removeDismissButton() {
        if (buttonContainer.isAncestorOf(dismiss)) {
            removeButton(dismiss);
        }
    }

    public void addPlayGameButton(Events event) {
        this.event = event;
        // Check to see if this is already visible
        if (!buttonContainer.isAncestorOf(playGame)) {
            buttonContainer.add(playGame);
            buttonContainer.add(Box.createHorizontalGlue());
        }
    }

    public void removePlayGameButton() {
        if (buttonContainer.isAncestorOf(playGame)) {
            removeButton(playGame);
        }
    }

    public void addSimGameButton(Events event, ArrayList<Events> todaysEvents) {
        this.event = event;
        this.todaysEvents = todaysEvents;
        // Check to see if this is already visible
        if (!buttonContainer.isAncestorOf(simGame)) {
            buttonContainer.add(simGame);
            buttonContainer.add(Box.createHorizontalGlue());
        }
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

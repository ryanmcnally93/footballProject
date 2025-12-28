package visuals.MainMenuPages.SinglePages;

import entities.Competition;
import entities.Match;
import entities.UsersMatch;
import people.Footballer;
import visuals.CustomizedElements.*;
import visuals.MatchPages.MatchPageTemplate;
import visuals.ScheduleFrames.Scheduler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class PlayerSearchPage extends LeftContentRightScrollPagesTemplate {

    private List<Footballer> allFootballers = new ArrayList<>();
    private List<OptionBar> createdBars;
    private OptionBar firstOption, secondOption, thirdOption, fourthOption;
    private ArrayList<PlayerSearchPageStatLine> currentPlayerLines;
    private boolean firstTime = true;

    public PlayerSearchPage(Scheduler scheduler) {
        super(scheduler, true);
        getHeaderPanel().setTitle("Player Search");

        ImageIcon buttonIcon = getIconWithSpecificSize("./src/main/java/visuals/Images/player_search_icon.png", "PlayerSearch", 16);
        getHeaderPanel().getPageIcon().setIcon(buttonIcon);

        initialOptions = createInitialOptions();
        createdBars = new ArrayList<>();

        setupPlayerListOnRight(
                initialOptions,
                (option, i) -> option.getFirst(),
                (option, title) -> {
                    OptionBar bar = new OptionBar(option, getBarHeights(initialOptions), initialOptions);
                    createdBars.add(bar);
                    return bar;
                }
        );

        firstOption = createdBars.getFirst();
        secondOption = createdBars.get(1);
        thirdOption = createdBars.get(2);
        fourthOption = createdBars.get(3);

        buildDependencyMaps();
        currentPlayerLines = new ArrayList<>();

        // These also need to be triggered when components inside these boxes are clicked
        addFocusListeners(getLeftBox(), true);
        addFocusListeners(getRightBox(), false);

        addScrollButton("leftDown");
        addScrollButton("leftUp");

        addKeyListeners();
        setVisible(true);

        PlayerSearchPageStatLine titleLine = PlayerSearchPageStatLine.createTitleLine();
        getLeftHeader().add(titleLine);
        getLeftBox().revalidate();
        getLeftBox().repaint();
    }

    @Override
    public void addKeyListeners() {
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("LEFT"), LEFT);
        actionMap.put(LEFT, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveRightScroller("left");
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), RIGHT);
        actionMap.put(RIGHT, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveRightScroller("right");
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("pressed UP"), "pressUp");
        actionMap.put("pressUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isLeftFocused() && getLeftBox().getComponentCount() != 0) {
                    scrollUpOrDownOnHover("leftUp", null);
                } else {
                    moveRightScroller("up");
                }
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("pressed DOWN"), "pressDown");
        actionMap.put("pressDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isLeftFocused() && getLeftBox().getComponentCount() != 0) {
                    scrollUpOrDownOnHover("leftDown", null);
                } else {
                    moveRightScroller("down");
                }
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("released DOWN"), "releaseDown");
        actionMap.put("releaseDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getTimer() != null && getTimer().isRunning()) {
                    getTimer().stop();
                }
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("released UP"), "releaseUp");
        actionMap.put("releaseUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getTimer() != null && getTimer().isRunning()) {
                    getTimer().stop();
                }
            }
        });
    }

    private void buildDependencyMaps() {
        for (int i = 0; i < createdBars.size() - 1; i++) {
            createdBars.get(i).setDependant(createdBars.get(i + 1));
        }

        // Add listeners to the optionBars for when they change value
        if (firstTime) {
            assignListenerToOptionFields(firstOption.getOptionField());
            assignListenerToOptionFields(secondOption.getOptionField());
            assignListenerToOptionFields(thirdOption.getOptionField());
            assignListenerToOptionFields(fourthOption.getOptionField());
            firstTime = false;
        } else {
            // Calling the listener to populate the first group of players
            secondOption.onSelectionChanged();
            secondOption.getOptionField().triggerUpdate();
        }
    }

    private void assignListenerToOptionFields(CustomizedOptionField chosenField) {
        chosenField.setOnSelectionChange(attribute -> updateDisplayedFixtures());
    }

    private String getCurrentValueFromOptionBar(OptionBar optionBar) {
        return optionBar.getOptionField().getOptions().get(optionBar.getOptionField().getCurrentOption());
    }

    private void updateDisplayedFixtures() {
        String chosenCountry = getCurrentValueFromOptionBar(firstOption);
        String chosenPosition = getCurrentValueFromOptionBar(secondOption);
        String chosenAge = getCurrentValueFromOptionBar(thirdOption);
        String chosenValue = getCurrentValueFromOptionBar(fourthOption);
        clearPlayers();

        List<Footballer> sourceList = allFootballers;

        // How can I alter this to add the following restrictions?
        // We only want the 8 results with the highest 'value'
        //
        List<Footballer> resultList = sourceList.stream()
                .filter(p -> p.getLikedPosition().equals(chosenPosition))
                .filter(p -> isInGroup(p.getAge(), chosenAge))
                .filter(p -> isInGroup(p.getValue(), chosenValue))
                .toList();

        for (Footballer player : resultList) {
            PlayerSearchPageStatLine playerLine = new PlayerSearchPageStatLine(player);
            updatePlayerLineListener(playerLine);
            currentPlayerLines.add(playerLine);
        }
        organiseMyPlayers();
    }

    private void organiseMyPlayers() {
        currentPlayerLines.sort(new Comparator<PlayerSearchPageStatLine>() {
            @Override
            public int compare(PlayerSearchPageStatLine line1, PlayerSearchPageStatLine line2) {
                return Long.compare(
                        line1.getPlayer().getValue(),
                        line2.getPlayer().getValue()
                );
            }
        });

        for (PlayerSearchPageStatLine eachLine : currentPlayerLines) {
            getLeftBox().add(eachLine);
        }

        // Re-evaluate the size of leftBox so the scrolling functionality works
        SwingUtilities.invokeLater(() -> {
            int height = 5;
            for (Component c : getLeftBox().getComponents()) {
                height += c.getPreferredSize().height;
            }

            Insets insets = getLeftBox().getInsets();
            height += insets.top + insets.bottom;

            int totalMargin = getLeftBox().getComponentCount() * 5;

            setPermanentWidthAndHeight(getLeftBox(), 621, height + totalMargin);

            scrollToComponent((JComponent) getLeftBox().getComponent(0), getLeftScroller(), 5);
        });

        getLeftBox().revalidate();
        getLeftBox().repaint();
    }

    private void updatePlayerLineListener(PlayerSearchPageStatLine line) {
        CustomizedButton icon = line.getSelectIcon();
        icon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("This is where we would open a player options modal");
                icon.triggerColorReverse();
            }
        });
    }

    @Override
    public HashMap<String, List<String>> createInitialOptions() {
        HashMap<String, List<String>> options = new HashMap<>();

        options.put("First Options", List.of("Any Country", "England", "France", "Spain", "Italy"));
        options.put("Second Options", List.of("Any Position", "GK", "RB", "CB", "LB", "CDM", "RM", "CM", "CAM", "LM", "RW", "ST", "LW"));
        options.put("Third Options", List.of("Any Age", "16-20", "21-25", "26-30", "30+"));
        options.put("Fourth Options", List.of("Any Value", "< £500k", "£500k - £750k", "£750k - £1 MILL", "£1 MILL - 1.5 MILL", "£1.5 MILL - £2 MILL", "£2 MILL +"));

        return options;
    }

    public boolean isInGroup(long value, String group) {
        group = group.toUpperCase().trim();

        // Remove currency symbols and spaces
        group = group.replace("£", "").replace(" ", "");

        // Helper to parse numbers with K / MILL
        java.util.function.Function<String, Long> parseNumber = s -> {
            if (s.endsWith("K")) {
                return Long.parseLong(s.replace("K", "")) * 1_000;
            }
            if (s.endsWith("MILL")) {
                return Long.parseLong(s.replace("MILL", "")) * 1_000_000;
            }
            return Long.parseLong(s);
        };

        // Less than (e.g. "<700K")
        if (group.startsWith("<")) {
            long limit = parseNumber.apply(group.substring(1));
            return value < limit;
        }

        // Greater than (e.g. "20MILL>")
        if (group.endsWith("+")) {
            long limit = parseNumber.apply(group.substring(0, group.length() - 1));
            return value > limit;
        }

        // Range (e.g. "700K-799K", "1MILL-1.9MILL")
        if (group.contains("-")) {
            String[] parts = group.split("-");
            long min = parseNumber.apply(parts[0]);
            long max = parseNumber.apply(parts[1]);
            return value >= min && value <= max;
        }

        return false;
    }

    private void clearPlayers() {
        currentPlayerLines.clear();
        getLeftBox().removeAll();
        getLeftBox().revalidate();
        getLeftBox().repaint();
    }

    public List<Footballer> getAllFootballers() {
        return allFootballers;
    }

    public void setAllFootballers(List<Footballer> allFootballers) {
        this.allFootballers = allFootballers;
    }
}

package visuals.MainMenuPages.SinglePages;

import entities.Competition;
import entities.Match;
import entities.UsersMatch;
import visuals.CustomizedElements.CustomizedButton;
import visuals.CustomizedElements.CustomizedOptionField;
import visuals.CustomizedElements.FixturesPageStatLine;
import visuals.CustomizedElements.OptionBar;
import visuals.MatchPages.MatchPageTemplate;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FixturesPage extends LeftContentRightScrollPagesTemplate {

    public static final List<String> FIRST_OPTIONS = List.of("My Fixtures", "All Fixtures");
    private List<Competition> myCompetitions = new ArrayList<>();
    private List<Competition> otherCompetitions = new ArrayList<>();
    private ArrayList<FixturesPageStatLine> currentMatchLines;
    List<OptionBar> createdBars;
    private boolean firstTime = true;
    private static HashMap<String, List<String>> initialOptions;
    private int baseHeight;
    private boolean fixturesUpdateScheduled = false;

    public FixturesPage(Scheduler scheduler) {
        super(scheduler, true);
        getHeaderPanel().setTitle("My Fixtures");

        ImageIcon buttonIcon = getIconWithSpecificSize("./src/main/java/visuals/Images/fixtures_icon.png", "Fixtures", 16);
        getHeaderPanel().getPageIcon().setIcon(buttonIcon);

        initialOptions = createInitialOptions();
        createdBars = new ArrayList<>();

        setupPlayerListOnRight(
                initialOptions,
                (option, i) -> option.getFirst(),
                (option, title) -> {
                    OptionBar bar = new OptionBar(option, getBarHeights(initialOptions));
                    createdBars.add(bar);
                    return bar;
                }
        );

        buildDependencyMaps();
        currentMatchLines = new ArrayList<>();

        // These also need to be triggered when components inside these boxes are clicked
        addFocusListeners(getLeftBox(), true);
        addFocusListeners(getRightBox(), false);

        addScrollButton("leftDown");
        addScrollButton("leftUp");

        addKeyListeners();
        setVisible(true);

        FixturesPageStatLine titleLine = FixturesPageStatLine.createTitleLine();
        getLeftHeader().add(titleLine);
        getLeftBox().revalidate();
        getLeftBox().repaint();
        baseHeight = (int) getLeftBox().getPreferredSize().getHeight();
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

    public void buildDependencyMaps() {
        Map<String, List<String>> competitionMap = Map.of(
                "My Fixtures", myCompetitions.stream().map(Competition::getName).toList(),
                "All Fixtures", otherCompetitions.stream().map(Competition::getName).toList()
        );

        Map<String, List<String>> roundNameMap = Stream.concat(myCompetitions.stream(), otherCompetitions.stream())
                .collect(Collectors.toMap(
                        Competition::getName,
                        Competition::getRoundNames
                ));

        List<Map<String, List<String>>> dependencyMaps = List.of(competitionMap, roundNameMap);

        for (int i = 0; i < dependencyMaps.size(); i++) {
            createdBars.get(i).setDependant(createdBars.get(i + 1));
            createdBars.get(i).setOptionsMap(dependencyMaps.get(i));
        }

        // Add listeners to the optionBars for when they change value
        CustomizedOptionField chosenField = createdBars.get(2).getOptionField();
        if (firstTime) {
            assignListenerToOptionFields(chosenField);
            createdBars.getFirst().setOnFallbackTriggered(this::clearFixtures);
            createdBars.get(1).setOnFallbackTriggered(this::clearFixtures);
            firstTime = false;
        } else {
            // Calling the listener to populate the first group of fixtures
            createdBars.getFirst().onSelectionChanged();
            chosenField.triggerUpdate();
        }
    }

    public void requestFixturesUpdate() {
        if (fixturesUpdateScheduled) return;

        fixturesUpdateScheduled = true;

        SwingUtilities.invokeLater(() -> {
            fixturesUpdateScheduled = false;

            if (currentMatchLines != null) {
                OptionBar firstBar = createdBars.getFirst();
                String topChoice = getCurrentValueFromOptionBar(firstBar);
                OptionBar secondBar = createdBars.get(1);
                String competitionName = getCurrentValueFromOptionBar(secondBar);
                String roundName = getCurrentValueFromOptionBar(createdBars.get(2));

                updateDisplayedFixtures(topChoice, competitionName, roundName);
            }
        });
    }

    private void assignListenerToOptionFields(CustomizedOptionField chosenField) {
        chosenField.setOnSelectionChange(roundName -> requestFixturesUpdate());
    }

    private String getCurrentValueFromOptionBar(OptionBar optionBar) {
        return optionBar.getOptionField().getOptions().get(optionBar.getOptionField().getCurrentOption());
    }

    private void updateDisplayedFixtures(String topChoice, String competitionName, String roundName) {
        clearFixtures();
        Competition selectedCompetition = null;

        if (topChoice.equals("My Fixtures")) {
            List<Competition> sourceList = myCompetitions;

            selectedCompetition = sourceList.stream()
                    .filter(c -> c.getName().equals(competitionName))
                    .findFirst()
                    .orElse(null);

            if (selectedCompetition == null) return;
        } else {
            List<Competition> sourceList = otherCompetitions;

            selectedCompetition = sourceList.stream()
                    .filter(c -> c.getName().equals(competitionName))
                    .findFirst()
                    .orElse(null);

            if (selectedCompetition == null) return;
        }

        int roundInt = selectedCompetition.getRoundNames().indexOf(roundName);
        Map<String, Match> matchesForRound = selectedCompetition.getMatchWeeksMatches().get(roundInt + 1);

        for (Map.Entry<String, Match> eachMatch : matchesForRound.entrySet()) {
            // New Lines Are Created Here, The Match May Be Re-Created At Some Point, Hence The Timer Being 00:00
            addMyFixtureLine(eachMatch.getValue());
        }
        organiseMyFixtures();
    }

    private HashMap<String, List<String>> createInitialOptions() {
        HashMap<String, List<String>> options = new HashMap<>();

        options.put("First Options", List.of("My Fixtures", "All Fixtures"));
        options.put("Second Options", List.of("Select Competition"));
        options.put("Third Options", List.of("Select Round"));

        return options;
    }

    public void addMyFixtureLine(Match child) {
        FixturesPageStatLine matchLine = new FixturesPageStatLine(child);
        if (child instanceof UsersMatch) {
            updateMatchLineListener(matchLine, (UsersMatch) child);
        } else {
            UsersMatch temporaryUsersMatch = new UsersMatch(child);
            updateMatchLineListener(matchLine, temporaryUsersMatch);
        }
        currentMatchLines.add(matchLine);
    }

    public void updateMatchLineListener(FixturesPageStatLine line, UsersMatch matchToView) {
        CustomizedButton icon = line.getSelectIcon();
        icon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (Map.Entry<String, JPanel> page : getScheduler().getMatchFramesMap().entrySet()) {
                    MatchPageTemplate frame = (MatchPageTemplate) page.getValue();
                    matchToView.setScheduler(getScheduler());
                    frame.setMatch(matchToView);
                    frame.setFromScheduler(true);
                }
                // Provide back button for the first viewed MatchPage when viewing through main menu
                if (matchToView.isMatchFinished()) {
                    getScheduler().getStatsPanel().getFooterPanel().getBackButtonBox().add(getScheduler().getStatsPanel().getBackButton());
                } else {
                    getScheduler().getTablePanel().getFooterPanel().getBackButtonBox().add(getScheduler().getTablePanel().getBackButton());
                }
                getScheduler().setMatch(matchToView);
                getScheduler().displayMatchFrames(matchToView);
                icon.triggerColorReverse();
            }
        });
    }

    public void organiseMyFixtures() {
        currentMatchLines.sort(new Comparator<FixturesPageStatLine>() {
            @Override
            public int compare(FixturesPageStatLine line1, FixturesPageStatLine line2) {
                return line1.getMatch().getDateTime().compareTo(line2.getMatch().getDateTime());
            }
        });

        for (FixturesPageStatLine eachLine : currentMatchLines) {
            getLeftBox().add(eachLine);
        }

        // Re-evaluate the size of leftBox so the scrolling functionality works
        int nineItemsHeight = baseHeight - 7;
        if (getLeftBox().getComponentCount() == 9) {
            setPermanentWidthAndHeight(getLeftBox(), 621, nineItemsHeight);
        } else if (getLeftBox().getComponentCount() > 9) {
            int furtherOptionsHeight = (getLeftBox().getComponentCount() - 8) * 29;
            setPermanentWidthAndHeight(getLeftBox(), 621, nineItemsHeight + furtherOptionsHeight);
        }

        getLeftBox().revalidate();
        getLeftBox().repaint();
    }

    public FixturesPageStatLine getLine(Match match) {
        for (FixturesPageStatLine eachLine : currentMatchLines) {
            if (eachLine.getMatch().toString().equals(match.toString())) {
                return eachLine;
            }
        }
        System.out.println("ERROR You haven't found your match line");
        return new FixturesPageStatLine(match);
    }

    @Override
    protected boolean directionEqualsPage(String direction) {
        return false;
    }

    public List<Competition> getMyCompetitions() {
        return this.myCompetitions;
    }

    public void setMyCompetitions(List<Competition> myCompetitions) {
        this.myCompetitions = myCompetitions;
    }

    public List<Competition> getOtherCompetitions() {
        return this.otherCompetitions;
    }

    public void setOtherCompetitions(List<Competition> otherCompetitions) {
        this.otherCompetitions = otherCompetitions;
    }

    public static HashMap<String, List<String>> getInitialOptions() {
        return initialOptions;
    }

    private void clearFixtures() {
        currentMatchLines.clear();
        getLeftBox().removeAll();
        getLeftBox().revalidate();
        getLeftBox().repaint();
    }
}

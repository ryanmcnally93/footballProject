package visuals.MainMenuPages.SinglePages;

import entities.Competition;
import entities.Match;
import entities.UsersMatch;
import visuals.CustomizedElements.CustomizedButton;
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

    private static final List<String> FIRST_OPTIONS = List.of("My Fixtures", "All Fixtures");
    private static List<String> MY_COMPETITIONS = new ArrayList<>();
    private static List<String> ALL_COMPETITIONS = new ArrayList<>();
    private static Map<String, List<String>> competitionRounds = new HashMap<>();;
    private static Map<String, List<String>> otherCompetitionRounds = new HashMap<>();;
    private ArrayList<FixturesPageStatLine> lines;

    public FixturesPage(Scheduler scheduler) {
        super(scheduler);
        getHeaderPanel().setTitle("My Fixtures");

        ImageIcon buttonIcon = getIconWithSpecificSize("./src/main/java/visuals/Images/fixtures_icon.png", "Fixtures", 16);
        getHeaderPanel().getPageIcon().setIcon(buttonIcon);

        createCompetitionOptions();
        HashMap<String, List<String>> initialOptions = createInitialOptions();
        List<OptionBar> createdBars = new ArrayList<>();

        setupPlayerListOnRight(
                initialOptions,
                (option, i) -> option.getFirst(),
                (option, title) -> {
                    OptionBar bar = new OptionBar(option, getBarHeights(initialOptions));
                    createdBars.add(bar);
                    return bar;
                }
        );

        buildDependencyMaps(createdBars);
        lines = new ArrayList<>();

        // These also need to be triggered when components inside these boxes are clicked
        // Should we check bounds instead?
        addFocusListeners(getLeftBox(), true);
        addFocusListeners(getRightBox(), false);

        addScrollButton("leftDown");
        addScrollButton("leftUp");

        addKeyListeners();
        setVisible(true);
        setFocusable(true);
        setRequestFocusEnabled(true);
        requestFocusInWindow();
    }

    @Override
    public void addKeyListeners() {
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("LEFT"), LEFT);
        actionMap.put(LEFT, getLeftClickAction());

        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), RIGHT);
        actionMap.put(RIGHT, getRightClickAction());

        inputMap.put(KeyStroke.getKeyStroke("pressed UP"), "pressUp");
        actionMap.put("pressUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isLeftFocused()) {
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
                if (isLeftFocused()) {
                    scrollUpOrDownOnHover("leftDown", null);
                } else {
                    moveRightScroller("down");
                }
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("released DOWN"), "releaseDown");
        actionMap.put("releaseDown", getDownReleaseAction());

        inputMap.put(KeyStroke.getKeyStroke("released UP"), "releaseUp");
        actionMap.put("releaseUp", getUpReleaseAction());
    }

    @Override
    protected AbstractAction getUpReleaseAction() {
        return new FixturesPage.UpRelease();
    }

    public class UpRelease extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (getTimer() != null && getTimer().isRunning()) {
                getTimer().stop();
            }
        }
    }

    @Override
    protected AbstractAction getDownReleaseAction() {
        return new FixturesPage.DownRelease();
    }

    public class DownRelease extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (getTimer() != null && getTimer().isRunning()) {
                getTimer().stop();
            }
        }
    }

    @Override
    protected AbstractAction getLeftClickAction() {
        return new FixturesPage.CustomLeftClick();
    }

    public class CustomLeftClick extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            moveRightScroller("left");
        }
    }

    @Override
    protected AbstractAction getRightClickAction() {
        return new FixturesPage.CustomRightClick();
    }

    public class CustomRightClick extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            moveRightScroller("right");
        }
    }

    private void buildDependencyMaps(List<OptionBar> bars) {
        // Define dependency maps in order
        List<Map<String, List<String>>> dependencyMaps = List.of(
                Map.of("My Fixtures", MY_COMPETITIONS, "All Fixtures", ALL_COMPETITIONS),
                Stream.concat(competitionRounds.entrySet().stream(), otherCompetitionRounds.entrySet().stream())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
        );

        // Wire bars to dependants automatically
        for (int i = 0; i < dependencyMaps.size(); i++) {
            bars.get(i).setDependant(bars.get(i + 1));
            bars.get(i).setOptionsMap(dependencyMaps.get(i));
        }
    }

    private void createCompetitionOptions() {
        for (Competition competition : getScheduler().getPlayersCompetitions()) {
            String name = competition.getName();
            MY_COMPETITIONS.add(name);
            ALL_COMPETITIONS.add(name);
            competitionRounds.put(name, new ArrayList<>(competition.getRoundNames()));
        }

        for (Competition competition : getScheduler().getOtherCompetitions()) {
            String name = competition.getName();
            ALL_COMPETITIONS.add(name);
            otherCompetitionRounds.put(name, new ArrayList<>(competition.getRoundNames()));
        }
    }

    private HashMap<String, List<String>> createInitialOptions() {
        HashMap<String, List<String>> options = new HashMap<>();
        options.put("First Options", FIRST_OPTIONS);
        options.put("Second Options", MY_COMPETITIONS);
        options.put("Third Options", competitionRounds.get(getScheduler().getLeague().getName()));
        return options;
    }

    public void addFixtureLine(UsersMatch child) {
        FixturesPageStatLine matchLine = new FixturesPageStatLine(child);
        updateMatchLineListener(matchLine, child);
        lines.add(matchLine);
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
                if (matchToView.isMatchHasPlayed()) {
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
        lines.sort(new Comparator<FixturesPageStatLine>() {
            @Override
            public int compare(FixturesPageStatLine line1, FixturesPageStatLine line2) {
                return line1.getMatch().getDateTime().compareTo(line2.getMatch().getDateTime());
            }
        });

        for (FixturesPageStatLine eachLine : lines) {
            getLeftBox().add(eachLine);
            // Re-evaluate the size of leftBox so the scrolling functionality works
            if (getLeftBox().getComponentCount() == 10) {
                setPermanentWidthAndHeight(getLeftBox(), 621, (int) getLeftBox().getPreferredSize().getHeight() + 10);
            } else if (getLeftBox().getComponentCount() > 10) {
                setPermanentWidthAndHeight(getLeftBox(), 621, (int) getLeftBox().getPreferredSize().getHeight() + 35);
            }
        }
        getLeftBox().revalidate();
        getLeftBox().repaint();
    }

    public FixturesPageStatLine getLine(Match match) {
        for (FixturesPageStatLine eachLine : lines) {
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
}

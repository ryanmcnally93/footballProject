package visuals.MainMenuPages.SinglePages;

import entities.Competition;
import entities.Match;
import entities.UsersMatch;
import visuals.CustomizedElements.MatchLineOnFixturesPages;
import visuals.CustomizedElements.OptionBar;
import visuals.MatchPages.MatchPageTemplate;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FixturesPage extends LeftContentRightScrollPagesTemplate {

    private static final List<String> FIRST_OPTIONS = List.of("My Fixtures", "All Fixtures");
    private static List<String> MY_COMPETITIONS = new ArrayList<>();
    private static List<String> ALL_COMPETITIONS = new ArrayList<>();
    private static Map<String, List<String>> competitionRounds = new HashMap<>();;
    private static Map<String, List<String>> otherCompetitionRounds = new HashMap<>();;
    private ArrayList<MatchLineOnFixturesPages> lines;

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
        setVisible(true);
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
        MatchLineOnFixturesPages matchLine = new MatchLineOnFixturesPages(child);
        updateMatchLineListener(matchLine, child);
        lines.add(matchLine);
    }

    public void updateMatchLineListener(MatchLineOnFixturesPages line, UsersMatch matchToView) {
        if (line.getMouseListeners().length > 1) {
            MouseListener[] listeners = line.getMouseListeners();
            line.removeMouseListener(listeners[listeners.length - 1]);
        }
        line.addMouseListener(new MouseAdapter() {
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
            }
        });
    }

    public void organiseMyFixtures() {
        lines.sort(new Comparator<MatchLineOnFixturesPages>() {
            @Override
            public int compare(MatchLineOnFixturesPages line1, MatchLineOnFixturesPages line2) {
                return line1.getMatch().getDateTime().compareTo(line2.getMatch().getDateTime());
            }
        });

        for (MatchLineOnFixturesPages eachLine : lines) {
            getLeftBox().add(eachLine);
        }
        getLeftBox().revalidate();
        getLeftBox().repaint();
    }

    public MatchLineOnFixturesPages getLine(Match match) {
        for (MatchLineOnFixturesPages eachLine : lines) {
            if (eachLine.getMatch().toString().equals(match.toString())) {
                return eachLine;
            }
        }
        System.out.println("ERROR You haven't found your match line");
        return new MatchLineOnFixturesPages(match);
    }
}

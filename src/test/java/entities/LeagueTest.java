package entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import people.Footballer;
import people.Goalkeeper;
import visuals.CustomizedElements.LeagueTable;
import visuals.CustomizedElements.PlayerLeaderboards;
import visuals.CustomizedElements.TeamAchievementLine;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LeagueTest {

    private Map<String, Team> teams;

    @Mock
    private Season season;

    @Mock
    private Map<String, Footballer> firstTeam;

    @InjectMocks
    private League league;

    @Test
    public void testConstructorWithIncorrectParams() {
        setupTeams(20);
        league = new League("name", "england", teams, 1, season);

        assertNotNull(league);

        ArrayList<TeamAchievementLine> lines = league.getLeagueTable().getAllLines();
        List<String> expectedOrder = List.of("A", "Team 1", "Team 10", "Team 11", "Team 12", "Team 13", "Team 14", "Team 15", "Team 16", "Team 17", "Team 18", "Team 19", "Team 2", "Team 3", "Team 4", "Team 5", "Team 6", "Team 7", "Team 8", "Team 9");

        for (int i = 0; i < expectedOrder.size(); i++) {
            String expectedName = expectedOrder.get(i);
            assertEquals(teams.get(expectedName), lines.get(i).getTeam(), "Team mismatch at index " + i);
        }
    }

    @Test
    public void testConstructorWithNoTeams() {
        teams = new HashMap<>();
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new League("name", "england", teams, 1, season);
        });
        assertEquals("You cannot start a League with no teams", thrown.getMessage());
    }

    @Test
    public void testConstructorWithOddNumberOfTeams() {
        setupTeams(3);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new League("name", "england", teams, 1, season);
        });
        assertEquals("You must have an even number of teams when creating a league", thrown.getMessage());
    }

    @Test
    public void testAssignFixturesToWeekNumber() {
        setupTeams(20);
        league = new League("name", "england", teams, 1, season);
        league.assignFixturesToWeekNumber();

        assertFalse(league.getMatchWeeksMatches().isEmpty());
        assertEquals(38, league.getMatchWeeksMatches().size());

        int i = 0;
        for(Map.Entry<Integer, Map<String, Match>> eachMatchWeek : league.getMatchWeeksMatches().entrySet()) {
            i++;
            assertEquals(i, eachMatchWeek.getKey());
            assertEquals(10, eachMatchWeek.getValue().size());
        }
    }

    @Test
    public void testAssignFixturesToWeekNumber_WithTwentyFourTeams() {
        setupTeams(24);
        league = new League("name", "england", teams, 1, season);
        league.assignFixturesToWeekNumber();

        assertFalse(league.getMatchWeeksMatches().isEmpty());
        assertEquals(46, league.getMatchWeeksMatches().size());

        int i = 0;
        for(Map.Entry<Integer, Map<String, Match>> eachMatchWeek : league.getMatchWeeksMatches().entrySet()) {
            i++;
            assertEquals(i, eachMatchWeek.getKey());
            assertEquals(12, eachMatchWeek.getValue().size());
        }
    }

    @Test
    public void testAssignDatetimesToWeekNumber() {
        setupTeams(20);
        league = new League("name", "england", teams, 1, season);
        league.assignDatetimesToWeekNumber();

        assertFalse(league.getMatchWeeksSlots().isEmpty());
        assertEquals(38, league.getMatchWeeksSlots().size());

        int i = 0;
        for(Map.Entry<Integer, Map<Integer, LocalDateTime>> eachMatchSlots : league.getMatchWeeksSlots().entrySet()) {
            i++;
            assertEquals(i, eachMatchSlots.getKey());
            assertEquals(10, eachMatchSlots.getValue().size());
        }
    }

    @Test
    public void testAssignDatetimesToWeekNumber_FirstDayIsSaturday() {
        setupTeams(20);
        when(season.getNumber()).thenReturn(3);
        league = new League("name", "england", teams, 1, season);
        league.assignDatetimesToWeekNumber();

        assertFalse(league.getMatchWeeksSlots().isEmpty());
        assertEquals(38, league.getMatchWeeksSlots().size());

        int i = 0;
        for(Map.Entry<Integer, Map<Integer, LocalDateTime>> eachMatchSlots : league.getMatchWeeksSlots().entrySet()) {
            i++;
            assertEquals(i, eachMatchSlots.getKey());
            assertEquals(10, eachMatchSlots.getValue().size());
        }
    }

    @Test
    public void testAssignDatetimesToWeekNumber_FirstDayIsAMinusNumber() {
        setupTeams(20);
        when(season.getNumber()).thenReturn(4);
        league = new League("name", "england", teams, 1, season);
        league.assignDatetimesToWeekNumber();

        assertFalse(league.getMatchWeeksSlots().isEmpty());
        assertEquals(38, league.getMatchWeeksSlots().size());

        int i = 0;
        for(Map.Entry<Integer, Map<Integer, LocalDateTime>> eachMatchSlots : league.getMatchWeeksSlots().entrySet()) {
            i++;
            assertEquals(i, eachMatchSlots.getKey());
            assertEquals(10, eachMatchSlots.getValue().size());
        }
    }

    @Test
    public void testGetFixturesToString() {
        setupTeams(2);
        when(firstTeam.get("GK")).thenReturn(new Goalkeeper());
        Match match = new Match(teams.get("A"), teams.get("Team 1"));
        Match otherMatch = new Match(teams.get("Team 1"), teams.get("A"));
        league.setFixtures(Map.of("1", match, "2", otherMatch));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        league.getFixturesToString();

        System.setOut(originalOut);
        String printed = outContent.toString();

        assertTrue(printed.contains("A vs Team 1"));
        assertTrue(printed.contains("Team 1 vs A"));
    }

    @Test
    public void testGetTeamFixturesToString() {
        setupTeams(4);
        when(firstTeam.get("GK")).thenReturn(new Goalkeeper());
        Match match = new Match(teams.get("A"), teams.get("Team 1"));
        Match otherMatch = new Match(teams.get("Team 1"), teams.get("A"));
        Match thirdMatch = new Match(teams.get("Team 2"), teams.get("Team 3"));
        league.setFixtures(Map.of("1", match, "2", otherMatch, "3", thirdMatch));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        league.getTeamFixturesToString(teams.get("A"));

        System.setOut(originalOut);
        String printed = outContent.toString();

        assertTrue(printed.contains("A vs Team 1"));
        assertTrue(printed.contains("Team 1 vs A"));
        assertFalse(printed.contains("Team 2 vs Team 3"));

        String[] lines = printed.split("\\R");
        assertEquals(2, lines.length, "Expected 2 printed lines");
    }

    @Test
    void testGettersAndSetters() {
        League league = new League();
        LeagueTable table = new LeagueTable();
        Map<String, Match> matches = new HashMap<>();
        Season season = new Season();
        PlayerLeaderboards leaderboards = new PlayerLeaderboards();
        Map<String, Team> teams = new HashMap<>();
        ArrayList<Match> matchesList = new ArrayList<>();
        Map<Integer, Map<String, Match>> matchWeeksMatches = new HashMap<>();
        Map<Integer, Map<Integer, LocalDateTime>> slots = new HashMap<>();

        league.setLeagueTable(table);
        league.setName("League");
        league.setCountry("England");
        league.setFixtures(matches);
        league.setTier(5);
        league.setSeason(season);
        league.setLeaderboard(leaderboards);
        league.setRestartWholeProcess(true);
        league.setTeams(teams);
        league.setMainFixtureList(matchesList);
        league.setTemporaryFixtureList(matchesList);
        league.setMatchWeeksMatches(matchWeeksMatches);
        league.setMatchWeeksSlots(slots);

        assertEquals(table, league.getLeagueTable());
        assertEquals("League", league.getName());
        assertEquals("England", league.getCountry());
        assertEquals(matches, league.getFixtures());
        assertEquals(5, league.getTier());
        assertEquals(season, league.getSeason());
        assertEquals(leaderboards, league.getPlayerLeaderboard());
        assertTrue(league.isRestartWholeProcess());
        assertEquals(teams, league.getTeams());
        assertEquals(matchesList, league.getMainFixtureList());
        assertEquals(matchesList, league.getTemporaryFixtureList());
        assertEquals(matchWeeksMatches, league.getMatchWeeksMatches());
        assertEquals(slots, league.getMatchWeeksSlots());
    }

    @Test
    public void testAssignSlotsToMatches_AllMatchesAssignedSlots() {
        setupForAssignSlotsMethod();
        league.assignSlotsToMatches();

        Map<String, Match> week1Matches = league.getMatchWeeksMatches().get(1);

        for (Match match : week1Matches.values()) {
            assertNotNull(match.getDateTime(), "Match was not assigned a slot.");
        }
    }

    @Test
    public void testAssignSlotsToMatches_NoDuplicateSlots() {
        setupForAssignSlotsMethod();
        league.assignSlotsToMatches();

        Map<String, Match> week1Matches = league.getMatchWeeksMatches().get(1);
        Set<LocalDateTime> usedSlots = new HashSet<>();

        for (Match match : week1Matches.values()) {
            LocalDateTime matchDateTime = match.getDateTime();
            assertNotNull(matchDateTime);
            assertFalse(usedSlots.contains(matchDateTime), "Duplicate slot assigned!");
            usedSlots.add(matchDateTime);
        }
    }

    @Test
    void testAssignSlotsToMatches_CreatesCorrectNumberOfAssignedSlots() {
        setupForAssignSlotsMethod();
        league.assignSlotsToMatches();

        Map<String, Match> week1Matches = league.getMatchWeeksMatches().get(1);
        assertEquals(2, week1Matches.size());

        Set<LocalDateTime> usedSlots = new HashSet<>();
        for (Match match : week1Matches.values()) {
            usedSlots.add(match.getDateTime());
        }

        assertEquals(2, usedSlots.size(), "Each match should have a unique slot.");
    }

    @Test
    void testSingleMatchAndSlot() {
        setupTeams(2);
        League singleMatchLeague = new League();

        Map<String, Match> week1Matches = new HashMap<>();
        week1Matches.put("1", new Match(teams.get("Team 1"), teams.get("A")));

        Map<Integer, LocalDateTime> week1Slots = new HashMap<>();
        week1Slots.put(1, LocalDateTime.of(2025, 8, 21, 18, 0));

        singleMatchLeague.setMatchWeeksMatches(Map.of(1, week1Matches));
        singleMatchLeague.setMatchWeeksSlots(Map.of(1, week1Slots));

        singleMatchLeague.assignSlotsToMatches();

        Match onlyMatch = singleMatchLeague.getMatchWeeksMatches().get(1).get("1");
        assertEquals(LocalDateTime.of(2025, 8, 21, 18, 0), onlyMatch.getDateTime());
    }

    private void setupTeams(int numberOfTeams) {
        teams = new HashMap<>();
        int teamCount = 0;
        while (teams.size() < numberOfTeams-1) {
            teamCount++;
            Team currentTeam = new Team();
            currentTeam.setName("Team " + teamCount);
            currentTeam.setStadium("Stadium " + teamCount);
            currentTeam.setFirstTeam(firstTeam);
            teams.put(currentTeam.getName(), currentTeam);
        }
        Team firstTeamInAlphabet = new Team();
        firstTeamInAlphabet.setName("A");
        firstTeamInAlphabet.setStadium("Stadium");
        firstTeamInAlphabet.setFirstTeam(firstTeam);
        teams.put(firstTeamInAlphabet.getName(), firstTeamInAlphabet);
    }

    private void setupForAssignSlotsMethod() {
        setupTeams(4);
        league = new League();

        // Week 1 matches
        Map<String, Match> week1Matches = new HashMap<>();
        week1Matches.put("1", new Match(teams.get("A"), teams.get("Team 1")));
        week1Matches.put("2", new Match(teams.get("Team 2"), teams.get("Team 3")));

        // Week 1 slots
        Map<Integer, LocalDateTime> week1Slots = new HashMap<>();
        week1Slots.put(1, LocalDateTime.of(2025, 8, 20, 15, 0));
        week1Slots.put(2, LocalDateTime.of(2025, 8, 20, 17, 0));

        // Set up maps
        Map<Integer, Map<String, Match>> matchWeeksMatches = new HashMap<>();
        matchWeeksMatches.put(1, week1Matches);

        Map<Integer, Map<Integer, LocalDateTime>> matchWeeksSlots = new HashMap<>();
        matchWeeksSlots.put(1, week1Slots);

        // Assuming setters or direct access
        league.setMatchWeeksMatches(matchWeeksMatches);
        league.setMatchWeeksSlots(matchWeeksSlots);
    }
}

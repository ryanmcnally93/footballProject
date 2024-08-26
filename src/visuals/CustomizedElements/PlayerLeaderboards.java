package visuals.CustomizedElements;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import entities.League;
import entities.Team;
import people.Footballer;
import visuals.ScheduleFrames.Scheduler;

public class PlayerLeaderboards extends GamePanel {

    private ArrayList<PlayerAchievementLine> lines;
    private PlayerAchievementLine errorLine;
    private League league;
    private Box mainButtonContainer, rowAboveNames;
    private JPanel tableContainer;
    private Scheduler scheduler;

    public PlayerLeaderboards(Scheduler scheduler) {
        this.lines = new ArrayList<PlayerAchievementLine>();
        this.scheduler = scheduler;
        this.league = scheduler.getLeague();

        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());

        tableContainer = new JPanel();
        tableContainer.setLayout(new BoxLayout(tableContainer, BoxLayout.Y_AXIS)); // Vertical layout
        tableContainer.setBackground(Color.LIGHT_GRAY);

        mainButtonContainer = Box.createHorizontalBox();
        setPermanentWidthAndHeight(mainButtonContainer, 600, 20);

        JLabel seasonsLabel = new JLabel("Seasons");
        JButton seasonDropdown = new JButton("All");
        JLabel competitionLabel = new JLabel("Competition");
        JButton competitionDropdown = new JButton("All");
        JLabel teamLabel = new JLabel("Team");
        JButton teamDropdown = new JButton("All");
        mainButtonContainer.add(Box.createHorizontalGlue());
        mainButtonContainer.add(seasonsLabel);
        mainButtonContainer.add(seasonDropdown);
        mainButtonContainer.add(Box.createHorizontalGlue());
        mainButtonContainer.add(competitionLabel);
        mainButtonContainer.add(competitionDropdown);
        mainButtonContainer.add(Box.createHorizontalGlue());
        mainButtonContainer.add(teamLabel);
        mainButtonContainer.add(teamDropdown);
        mainButtonContainer.add(Box.createHorizontalGlue());

        rowAboveNames = Box.createHorizontalBox();
        setPermanentWidthAndHeight(rowAboveNames, 600, 20);
        // These must show as clickable labels rather than buttons
        JLabel number = new JLabel("No.");
        setPermanentWidth(number, 40);
        JButton filterByPosition = new JButton("Pos.");
        setPermanentWidth(filterByPosition, 40);
        JButton filterByOverallRating = new JButton("OVR");
        setPermanentWidth(filterByOverallRating, 40);
        JButton filterByName = new JButton("Name");
        setPermanentWidth(filterByName, 180);
        JButton filterByTeamname = new JButton("Team");
        setPermanentWidth(filterByTeamname, 140);
        JButton filterByGoal = new JButton("Gls");
        setPermanentWidth(filterByGoal, 40);
        JButton filterByAssists = new JButton("Asts");
        setPermanentWidth(filterByAssists, 40);
        JButton filterByYellowCards = new JButton("Ylw");
        setPermanentWidth(filterByYellowCards, 40);
        JButton filterByRedCards = new JButton("Red");
        setPermanentWidth(filterByRedCards, 40);
        rowAboveNames.add(number);
        rowAboveNames.add(filterByPosition);
        rowAboveNames.add(filterByOverallRating);
        rowAboveNames.add(filterByName);
        rowAboveNames.add(filterByTeamname);
        rowAboveNames.add(filterByGoal);
        rowAboveNames.add(filterByAssists);
        rowAboveNames.add(filterByYellowCards);
        rowAboveNames.add(filterByRedCards);

        tableContainer.add(mainButtonContainer, BorderLayout.CENTER);
        tableContainer.add(rowAboveNames, BorderLayout.CENTER);

        for(Map.Entry<String, Team> eachTeam : league.getTeams().entrySet()){
            Team thisTeam = eachTeam.getValue();
            for(Map.Entry<String, Footballer> eachPlayer : thisTeam.getPlayers().entrySet()) {
                Footballer thisPlayer = eachPlayer.getValue();
                PlayerAchievementLine line = new PlayerAchievementLine(thisPlayer);
                lines.add(line);
            }
        }

        // For containing the rows of players
        add(tableContainer, BorderLayout.CENTER);
        updateLinesInTableLogic("Goals");

        filterByPosition.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                updateLinesInTableLogic("Position");
            }
        });

    }

    // This could be used to add a searchbar looking for the player
    public PlayerAchievementLine getLine(Footballer player) {
        for(PlayerAchievementLine tableLine : lines) {
            if(player.getName().equals(tableLine.getName())) {
                return tableLine;
            }
        }
        return errorLine;
    }

    public void updateLinesInTableLogic(String filterType) {

        if(filterType.equals("Goals")){
            lines.sort(new Comparator<PlayerAchievementLine>() {
                @Override
                public int compare(PlayerAchievementLine t1, PlayerAchievementLine t2) {
                    int goalComparison = t2.getGoals().compareTo(t1.getGoals());

                    // If amount of goals is the same, place those with most assists at the top
                    if (goalComparison == 0) {
                        return t2.getAssists().compareTo(t1.getAssists());
                    }
                    return goalComparison;
                }
            });
        } else if (filterType.equals("Position")){
            lines.sort(new Comparator<PlayerAchievementLine>() {
                @Override
                public int compare(PlayerAchievementLine t1, PlayerAchievementLine t2) {
                    int positionComparison = t2.getPositionByNumber().compareTo(t1.getPositionByNumber());

                    if (positionComparison == 0) {
                        return t2.getName().compareTo(t1.getName());
                    }
                    return positionComparison;
                }
            });
        }

        System.out.println("List has been sorted");

        // Numbers the results of the search
        for(int i=0;i<lines.size();i++) {
            lines.get(i).setNumber(i+1);
        }

        tableContainer.removeAll();
        tableContainer.add(mainButtonContainer, BorderLayout.CENTER);
        tableContainer.add(rowAboveNames, BorderLayout.CENTER);

        for(PlayerAchievementLine eachLine : lines) {

            Box row = Box.createHorizontalBox();
            row.setPreferredSize(new Dimension(600,20));

            JLabel number = new JLabel(String.valueOf(eachLine.getNumber()));
            setPermanentWidthAndHeight(number, 40, 20);
            JLabel position = new JLabel(String.valueOf(eachLine.getPosition()));
            setPermanentWidthAndHeight(position, 40, 20);
            JLabel ovr = new JLabel(String.valueOf(eachLine.getOVR()));
            setPermanentWidthAndHeight(ovr, 40, 20);
            JLabel playerName = new JLabel(eachLine.getName());
            setPermanentWidthAndHeight(playerName, 180, 20);
            JLabel clubName = new JLabel(String.valueOf(eachLine.getTeam()));
            setPermanentWidthAndHeight(clubName, 140, 20);
            JLabel goals = new JLabel(String.valueOf(eachLine.getGoals()));
            setPermanentWidthAndHeight(goals, 40, 20);
            JLabel assists = new JLabel(String.valueOf(eachLine.getAssists()));
            setPermanentWidthAndHeight(assists, 40, 20);
            JLabel yellows = new JLabel(String.valueOf(eachLine.getYellows()));
            setPermanentWidthAndHeight(yellows, 40, 20);
            JLabel reds = new JLabel(String.valueOf(eachLine.getReds()));
            setPermanentWidthAndHeight(reds, 40, 20);

            row.add(number);
            row.add(position);
            row.add(ovr);
            row.add(playerName);
            row.add(clubName);
            row.add(goals);
            row.add(assists);
            row.add(yellows);
            row.add(reds);
            tableContainer.add(row);
        };

        tableContainer.revalidate();
        tableContainer.repaint();
    }

    public ArrayList<PlayerAchievementLine> getAllLines() {
        return lines;
    }

    public void setLines(ArrayList<PlayerAchievementLine> lines) {
        this.lines = lines;
    }

    public PlayerAchievementLine getErrorLine() {
        return errorLine;
    }

    public void setErrorLine(PlayerAchievementLine errorLine) {
        this.errorLine = errorLine;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Box getMainButtonContainer() {
        return mainButtonContainer;
    }

    public void setMainButtonContainer(Box mainButtonContainer) {
        this.mainButtonContainer = mainButtonContainer;
    }

    public Box getRowAboveNames() {
        return rowAboveNames;
    }

    public void setRowAboveNames(Box rowAboveNames) {
        this.rowAboveNames = rowAboveNames;
    }

    public JPanel getTableContainer() {
        return tableContainer;
    }

    public void setTableContainer(JPanel tableContainer) {
        this.tableContainer = tableContainer;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
// We may need this to display or update
//    public void updateTableVisually() {
//        centerBox.removeAll();
//        table = getMatch().getLeague().getLeagueTable();
//        centerBox.add(table);
//        Box padding = Box.createVerticalBox();
//        padding.add(Box.createVerticalStrut(20));  // 20 pixels of padding, adjust as needed
//        centerBox.add(padding);
//        centerBox.revalidate();
//        centerBox.repaint();
//    }

}

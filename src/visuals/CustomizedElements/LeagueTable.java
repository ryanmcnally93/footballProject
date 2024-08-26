package visuals.CustomizedElements;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.*;

import entities.League;
import entities.Team;

public class LeagueTable extends GamePanel {

	private static final long serialVersionUID = -737891404507970413L;
	private ArrayList<TableLine> lines;
	private TableLine errorLine;
	private League league;
	private Box container;
	private JPanel teamBox;
	private JLabel title;
	private JPanel tableContainer;
	private Box titleRow;
	
	public LeagueTable(League league) {
		this.lines = new ArrayList<>();
		this.league = league;

		setBackground(Color.LIGHT_GRAY);
		setLayout(new BorderLayout());
		
		JPanel titleBox = new JPanel();
		titleBox.setPreferredSize(new Dimension(600,30));
    	titleBox.setLayout(new BorderLayout());
    	titleBox.setBackground(Color.LIGHT_GRAY);
        title = new JLabel(league.getName(), SwingConstants.CENTER);
        title.setFont(new Font("Menlo", Font.BOLD, 16));
        title.setForeground(new Color(0, 51, 204));
        titleBox.add(title, BorderLayout.CENTER);
        add(titleBox, BorderLayout.NORTH);

		tableContainer = new JPanel();
		tableContainer.setLayout(new BoxLayout(tableContainer, BoxLayout.Y_AXIS)); // Vertical layout
		tableContainer.setBackground(Color.LIGHT_GRAY);

        titleRow = Box.createHorizontalBox();
		titleRow.setPreferredSize(new Dimension(600,20));
		JLabel position = new JLabel("Pos.");
    	setPermanentWidthAndHeight(position, 30, 20);
    	JLabel clubName = new JLabel("Club");
		setPermanentWidthAndHeight(clubName, 360, 20);
        JLabel matchesPlayed = new JLabel("MP");
		setPermanentWidthAndHeight(matchesPlayed, 30, 20);
        JLabel wins = new JLabel("W");
		setPermanentWidthAndHeight(wins, 30, 20);
    	JLabel draws = new JLabel("D");
		setPermanentWidthAndHeight(draws, 30, 20);
        JLabel losses = new JLabel("L");
		setPermanentWidthAndHeight(losses, 30, 20);
        JLabel goalsFor = new JLabel("GF");
		setPermanentWidthAndHeight(goalsFor, 30, 20);
        JLabel goalsAgainst = new JLabel("GA");
		setPermanentWidthAndHeight(goalsAgainst, 30, 20);
        JLabel goalDifference = new JLabel("GD");
		setPermanentWidthAndHeight(goalDifference, 30, 20);
        
    	JLabel points = new JLabel("Pts");
    	Font newFont = new Font(points.getFont().getName(), Font.BOLD, points.getFont().getSize());
    	points.setFont(newFont);
		setPermanentWidthAndHeight(points, 30, 20);
		titleRow.add(position);
		titleRow.add(clubName);
		titleRow.add(matchesPlayed);
		titleRow.add(wins);
		titleRow.add(draws);
		titleRow.add(losses);
		titleRow.add(goalsFor);
		titleRow.add(goalsAgainst);
		titleRow.add(goalDifference);
		titleRow.add(points);
    	tableContainer.add(titleRow);

    	add(tableContainer, BorderLayout.CENTER);
	}

	public TableLine getLine(Team team) {
		for(TableLine tableline : lines) {
			if(team.getName().equals(tableline.getTeamName())) {
				return tableline;
			}
		}
		return errorLine;
	}
	
	public ArrayList<TableLine> getAllLines(){
		return lines;
	}
	
	public void updateLinesInTableLogic() {

		// Implement logic to 'sort' your array list by points and GD
		lines.sort(new Comparator<TableLine>() {
            @Override
            public int compare(TableLine t1, TableLine t2) {
                int pointComparison = t2.getPoints().compareTo(t1.getPoints());

                // If points are the same, compare by goal difference
                if (pointComparison == 0) {
                    return t2.getGoalDifference().compareTo(t1.getGoalDifference());
                }
                return pointComparison;
            }
        });
		
		for(int i=0;i<lines.size();i++) {
			lines.get(i).setPosition(i+1);
		}
		
		tableContainer.removeAll();
		tableContainer.add(titleRow);
		for(TableLine eachLine : lines) {
			Box row = Box.createHorizontalBox();
			row.setPreferredSize(new Dimension(600,20));
        	JLabel position = new JLabel(String.valueOf(eachLine.getPosition()));
			setPermanentWidthAndHeight(position, 30, 20);
        	JLabel clubName = new JLabel(eachLine.getTeamName());
			setPermanentWidthAndHeight(clubName, 360, 20);
            JLabel matchesPlayed = new JLabel(String.valueOf(eachLine.getGamesPlayed()));
			setPermanentWidthAndHeight(matchesPlayed, 30, 20);
        	JLabel wins = new JLabel(String.valueOf(eachLine.getWins()));
			setPermanentWidthAndHeight(wins, 30, 20);
        	JLabel draws = new JLabel(String.valueOf(eachLine.getDraws()));
			setPermanentWidthAndHeight(draws, 30, 20);
        	JLabel losses = new JLabel(String.valueOf(eachLine.getLosses()));
			setPermanentWidthAndHeight(losses, 30, 20);
            JLabel goalsFor = new JLabel(String.valueOf(eachLine.getGoalsScored()));
			setPermanentWidthAndHeight(goalsFor, 30, 20);
            JLabel goalsAgainst = new JLabel(String.valueOf(eachLine.getGoalsConceded()));
			setPermanentWidthAndHeight(goalsAgainst, 30, 20);
            JLabel goalDifference = new JLabel(String.valueOf(eachLine.getGoalDifference()));
			setPermanentWidthAndHeight(goalDifference, 30, 20);
        	JLabel points = new JLabel(String.valueOf(eachLine.getPoints()));
        	Font newFont = new Font(points.getFont().getName(), Font.BOLD, points.getFont().getSize());
        	points.setFont(newFont);
			setPermanentWidthAndHeight(points, 30, 20);
            row.add(position);
        	row.add(clubName);
        	row.add(matchesPlayed);
        	row.add(wins);
        	row.add(draws);
        	row.add(losses);
        	row.add(goalsFor);
        	row.add(goalsAgainst);
        	row.add(goalDifference);
        	row.add(points);
        	tableContainer.add(row);
        };
		
        tableContainer.revalidate();
        tableContainer.repaint();
	}

	public void setLine(ArrayList<TableLine> line) {
		this.lines = line;
	}

	@Override
	public String toString() {
		return "Definitely generating the right item";
	}

	public ArrayList<TableLine> getLines() {
		return lines;
	}

	public void setLines(ArrayList<TableLine> lines) {
		this.lines = lines;
	}

	public TableLine getErrorLine() {
		return errorLine;
	}

	public void setErrorLine(TableLine errorLine) {
		this.errorLine = errorLine;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public Box getContainer() {
		return container;
	}

	public void setContainer(Box container) {
		this.container = container;
	}

	public JPanel getTeamBox() {
		return teamBox;
	}

	public void setTeamBox(JPanel teamBox) {
		this.teamBox = teamBox;
	}

	public JLabel getTitle() {
		return title;
	}

	public void setTitle(JLabel title) {
		this.title = title;
	}

	public JPanel getTableContainer() {
		return tableContainer;
	}

	public void setTableContainer(JPanel tableContainer) {
		this.tableContainer = tableContainer;
	}
	
}

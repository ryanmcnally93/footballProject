package general;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import visuals.CustomizedElements.GamePanel;

public class LeagueTable extends GamePanel {

	private static final long serialVersionUID = -737891404507970413L;
	private ArrayList<TableLine> lines;
	private TableLine errorLine;
	private League league;
	private Box container;
	private JPanel teamBox;
	private JLabel title;
	private JPanel tableContainer;
	
	public LeagueTable(League league) {
		this.lines = new ArrayList<>();
		this.league = league;
		
		setBackground(Color.LIGHT_GRAY);
		
		JPanel titleBox = new JPanel();
		titleBox.setPreferredSize(new Dimension(600,30));
    	titleBox.setLayout(new BorderLayout());
    	titleBox.setBackground(Color.LIGHT_GRAY);
        title = new JLabel(league.getName(), SwingConstants.CENTER);
        title.setFont(new Font("Menlo", Font.BOLD, 16));
        title.setForeground(new Color(0, 51, 204));
        titleBox.add(title, BorderLayout.CENTER);
        add(titleBox);
        
        Box row = Box.createHorizontalBox();
    	row.setPreferredSize(new Dimension(600,20));
    	JLabel position = new JLabel("Pos.");
    	position.setPreferredSize(new Dimension(30,20));
        position.setMinimumSize(new Dimension(30,20));
        position.setMaximumSize(new Dimension(30,20));
    	JLabel clubName = new JLabel("Club");
    	clubName.setPreferredSize(new Dimension(330,20));
        clubName.setMinimumSize(new Dimension(330,20));
        clubName.setMaximumSize(new Dimension(330,20));
        JLabel matchesPlayed = new JLabel("MP");
        matchesPlayed.setPreferredSize(new Dimension(30,20));
        matchesPlayed.setMinimumSize(new Dimension(30,20));
        matchesPlayed.setMaximumSize(new Dimension(30,20));
        JLabel wins = new JLabel("W");
    	wins.setPreferredSize(new Dimension(30,20));
        wins.setMinimumSize(new Dimension(30,20));
        wins.setMaximumSize(new Dimension(30,20));
    	JLabel draws = new JLabel("D");
    	draws.setPreferredSize(new Dimension(30,20));
        draws.setMinimumSize(new Dimension(30,20));
        draws.setMaximumSize(new Dimension(30,20));
        JLabel losses = new JLabel("L");
        losses.setPreferredSize(new Dimension(30,20));
        losses.setMinimumSize(new Dimension(30,20));
        losses.setMaximumSize(new Dimension(30,20));
        JLabel goalsFor = new JLabel("GF");
        goalsFor.setPreferredSize(new Dimension(30,20));
        goalsFor.setMinimumSize(new Dimension(30,20));
        goalsFor.setMaximumSize(new Dimension(30,20));
        JLabel goalsAgainst = new JLabel("GA");
        goalsAgainst.setPreferredSize(new Dimension(30,20));
        goalsAgainst.setMinimumSize(new Dimension(30,20));
        goalsAgainst.setMaximumSize(new Dimension(30,20));
        JLabel goalDifference = new JLabel("GD");
        goalDifference.setPreferredSize(new Dimension(30,20));
        goalDifference.setMinimumSize(new Dimension(30,20));
        goalDifference.setMaximumSize(new Dimension(30,20));
        
//    	JLabel position = new JLabel(String.valueOf(eachLine.getPosition()));
//    	JLabel position = new JLabel(String.valueOf(eachLine.getPosition()));
//    	JLabel position = new JLabel(String.valueOf(eachLine.getPosition()));
        
    	JLabel points = new JLabel("Pts");
    	Font newFont = new Font(points.getFont().getName(), Font.BOLD, points.getFont().getSize());
    	points.setFont(newFont);
    	points.setPreferredSize(new Dimension(30,20));
        points.setMinimumSize(new Dimension(30,20));
        points.setMaximumSize(new Dimension(30,20));
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
    	add(row);
    	
        tableContainer = new JPanel();
        tableContainer.setPreferredSize(new Dimension(600,390));
        tableContainer.setBackground(Color.LIGHT_GRAY);
    	add(tableContainer);
        
		setVisible(true);
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
		Collections.sort(lines, new Comparator<TableLine>() {
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
		for(TableLine eachLine : lines) {
			Box row = Box.createHorizontalBox();
			row.setPreferredSize(new Dimension(600,20));
        	JLabel position = new JLabel(String.valueOf(eachLine.getPosition()));
        	position.setPreferredSize(new Dimension(30,20));
            position.setMinimumSize(new Dimension(30,20));
            position.setMaximumSize(new Dimension(30,20));
        	JLabel clubName = new JLabel(eachLine.getTeamName());
        	clubName.setPreferredSize(new Dimension(330,20));
            clubName.setMinimumSize(new Dimension(330,20));
            clubName.setMaximumSize(new Dimension(330,20));
            JLabel matchesPlayed = new JLabel(String.valueOf(eachLine.getGamesPlayed()));
            matchesPlayed.setPreferredSize(new Dimension(30,20));
            matchesPlayed.setMinimumSize(new Dimension(30,20));
            matchesPlayed.setMaximumSize(new Dimension(30,20));
        	JLabel wins = new JLabel(String.valueOf(eachLine.getWins()));
        	wins.setPreferredSize(new Dimension(30,20));
            wins.setMinimumSize(new Dimension(30,20));
            wins.setMaximumSize(new Dimension(30,20));
        	JLabel draws = new JLabel(String.valueOf(eachLine.getDraws()));
        	draws.setPreferredSize(new Dimension(30,20));
            draws.setMinimumSize(new Dimension(30,20));
            draws.setMaximumSize(new Dimension(30,20));
        	JLabel losses = new JLabel(String.valueOf(eachLine.getLosses()));
        	losses.setPreferredSize(new Dimension(30,20));
            losses.setMinimumSize(new Dimension(30,20));
            losses.setMaximumSize(new Dimension(30,20));
            JLabel goalsFor = new JLabel(String.valueOf(eachLine.getGoalsScored()));
            goalsFor.setPreferredSize(new Dimension(30,20));
            goalsFor.setMinimumSize(new Dimension(30,20));
            goalsFor.setMaximumSize(new Dimension(30,20));
            JLabel goalsAgainst = new JLabel(String.valueOf(eachLine.getGoalsConceded()));
            goalsAgainst.setPreferredSize(new Dimension(30,20));
            goalsAgainst.setMinimumSize(new Dimension(30,20));
            goalsAgainst.setMaximumSize(new Dimension(30,20));
            JLabel goalDifference = new JLabel(String.valueOf(eachLine.getGoalDifference()));
            goalDifference.setPreferredSize(new Dimension(30,20));
            goalDifference.setMinimumSize(new Dimension(30,20));
            goalDifference.setMaximumSize(new Dimension(30,20));
        	JLabel points = new JLabel(String.valueOf(eachLine.getPoints()));
        	Font newFont = new Font(points.getFont().getName(), Font.BOLD, points.getFont().getSize());
        	points.setFont(newFont);
        	points.setPreferredSize(new Dimension(30,20));
            points.setMinimumSize(new Dimension(30,20));
            points.setMaximumSize(new Dimension(30,20));
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

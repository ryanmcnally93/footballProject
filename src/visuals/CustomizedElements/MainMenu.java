package visuals.CustomizedElements;
import main.GameWindow;
import visuals.MainMenuPages.FixturesPages.AllFixturesPage;
import visuals.MainMenuPages.FixturesPages.MyFixturesPage;
import visuals.MainMenuPages.FixturesPages.ResultsPage;
import visuals.MainMenuPages.LeaderboardPages.LeagueTablePage;
import visuals.MainMenuPages.LeaderboardPages.TopAssistsPage;
import visuals.MainMenuPages.LeaderboardPages.TopGoalscorersPage;
import visuals.MainMenuPages.TacticsPages.FirstTeamPage;
import visuals.MainMenuPages.TacticsPages.FormationPage;
import visuals.MainMenuPages.TacticsPages.MatchRolesPage;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MainMenu extends GamePanel {

    private Scheduler scheduler;
    private GameWindow window;
    private JButton leagueTableButton, goalscorersButton, assistsButton, allFixturesButton, myFixturesButton, resultsButton, firstTeamButton, formationButton, matchRolesButton;

    public MainMenu(GameWindow newWindow, Scheduler scheduler) {
        this.window = newWindow;
        this.scheduler = scheduler;
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.LIGHT_GRAY);

        Box container = Box.createVerticalBox();

        // Make the row of buttons
        Box firstRow = Box.createHorizontalBox();
        leagueTableButton = new JButton("League Table");
        goalscorersButton = new JButton("Top Goals");
        assistsButton = new JButton("Top Assists");
        makeMenuRow(firstRow, leagueTableButton, goalscorersButton, assistsButton);

        Box secondRow = Box.createHorizontalBox();
        allFixturesButton = new JButton("All Fixtures");
        myFixturesButton = new JButton("My Fixtures");
        resultsButton = new JButton("Results");
        makeMenuRow(secondRow, allFixturesButton, myFixturesButton, resultsButton);

        Box thirdRow = Box.createHorizontalBox();
        firstTeamButton = new JButton("First Team");
        formationButton = new JButton("Formation");
        matchRolesButton = new JButton("Match Roles");
        makeMenuRow(thirdRow, firstTeamButton, formationButton, matchRolesButton);

        setPermanentWidthAndHeight(firstRow, 200, 40);
        setPermanentWidthAndHeight(secondRow, 200, 40);
        setPermanentWidthAndHeight(thirdRow, 200, 40);

        container.add(Box.createVerticalGlue());
        container.add(firstRow);
        container.add(secondRow);
        container.add(thirdRow);
        container.add(Box.createVerticalGlue());

        mainPanel.add(container);
        add(mainPanel, BorderLayout.CENTER);
        repaint();
    }

    public void makeMenuRow(Box row, JButton buttonOne, JButton buttonTwo, JButton buttonThree){
        CardLayout layout = new CardLayout();
        JPanel buttonBox = new JPanel(layout);

        JPanel buttonOneContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buttonOneContainer.add(buttonOne);
        buttonOneContainer.setBackground(Color.LIGHT_GRAY);
        JPanel buttonTwoContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buttonTwoContainer.add(buttonTwo);
        buttonTwoContainer.setBackground(Color.LIGHT_GRAY);
        JPanel buttonThreeContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buttonThreeContainer.add(buttonThree);
        buttonThreeContainer.setBackground(Color.LIGHT_GRAY);

        buttonBox.add(buttonOneContainer, buttonOne.getText());
        buttonBox.add(buttonTwoContainer, buttonTwo.getText());
        buttonBox.add(buttonThreeContainer, buttonThree.getText());

        JPanel leftTriggerContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        leftTriggerContainer.setBackground(Color.LIGHT_GRAY);
        JButton leftTrigger = new JButton("<");
        setPermanentWidth(leftTrigger, 30);
        leftTriggerContainer.add(leftTrigger);
        JPanel rightTriggerContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        rightTriggerContainer.setBackground(Color.LIGHT_GRAY);
        JButton rightTrigger = new JButton(">");
        setPermanentWidth(rightTrigger, 30);
        rightTriggerContainer.add(rightTrigger);

        row.add(leftTriggerContainer);
        row.add(buttonBox);
        row.add(rightTriggerContainer);

        leftTrigger.addActionListener(e -> {
            layout.previous(buttonBox);
        });

        rightTrigger.addActionListener(e -> {
            layout.next(buttonBox);
        });

    }

    public JButton getFirstTeamButton() {
        return firstTeamButton;
    }

    public void setFirstTeamButton(JButton firstTeamButton) {
        this.firstTeamButton = firstTeamButton;
    }

    public JButton getLeagueTableButton() {
        return leagueTableButton;
    }

    public void setLeagueTableButton(JButton leagueTableButton) {
        this.leagueTableButton = leagueTableButton;
    }

    public JButton getGoalscorersButton() {
        return goalscorersButton;
    }

    public void setGoalscorersButton(JButton goalscorersButton) {
        this.goalscorersButton = goalscorersButton;
    }

    public JButton getAssistsButton() {
        return assistsButton;
    }

    public void setAssistsButton(JButton assistsButton) {
        this.assistsButton = assistsButton;
    }

    public JButton getAllFixturesButton() {
        return allFixturesButton;
    }

    public void setAllFixturesButton(JButton allFixturesButton) {
        this.allFixturesButton = allFixturesButton;
    }

    public JButton getMyFixturesButton() {
        return myFixturesButton;
    }

    public void setMyFixturesButton(JButton myFixturesButton) {
        this.myFixturesButton = myFixturesButton;
    }

    public JButton getResultsButton() {
        return resultsButton;
    }

    public void setResultsButton(JButton resultsButton) {
        this.resultsButton = resultsButton;
    }

    public JButton getFormationButton() {
        return formationButton;
    }

    public void setFormationButton(JButton formationButton) {
        this.formationButton = formationButton;
    }

    public JButton getMatchRolesButton() {
        return matchRolesButton;
    }

    public void setMatchRolesButton(JButton matchRolesButton) {
        this.matchRolesButton = matchRolesButton;
    }
}

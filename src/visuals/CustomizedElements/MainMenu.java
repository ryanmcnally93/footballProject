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
    private FirstTeamPage firstTeam;
    private FormationPage formation;
    private MatchRolesPage matchRoles;

    public MainMenu(GameWindow newWindow, Scheduler scheduler) {
        window = newWindow;
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
        ArrayList<JButton> firstButtons = new ArrayList<>();
        firstButtons.add(leagueTableButton);
        firstButtons.add(goalscorersButton);
        firstButtons.add(assistsButton);

        // Make the card layout for the pages of these buttons
        CardLayout firstRowLayout = new CardLayout();
        JPanel firstPages = new JPanel(firstRowLayout);
        LeagueTablePage league = new LeagueTablePage(firstRowLayout, firstPages, scheduler, true);
        TopGoalscorersPage goals = new TopGoalscorersPage(firstRowLayout, firstPages, scheduler, true);
        TopAssistsPage assists = new TopAssistsPage(firstRowLayout, firstPages, scheduler, true);
        firstPages.add(league, "League Table");
        firstPages.add(goals, "Top Goals");
        firstPages.add(assists, "Top Assists");
        addListeners(firstButtons, firstPages, firstRowLayout);

        Box secondRow = Box.createHorizontalBox();
        allFixturesButton = new JButton("All Fixtures");
        myFixturesButton = new JButton("My Fixtures");
        resultsButton = new JButton("Results");
        makeMenuRow(secondRow, allFixturesButton, myFixturesButton, resultsButton);
        ArrayList<JButton> secondButtons = new ArrayList<>();
        secondButtons.add(allFixturesButton);
        secondButtons.add(myFixturesButton);
        secondButtons.add(resultsButton);

        // Make the card layout for the pages of these buttons
        CardLayout secondRowLayout = new CardLayout();
        JPanel secondPages = new JPanel(secondRowLayout);
        AllFixturesPage allFixtures = new AllFixturesPage(secondRowLayout, secondPages, scheduler, true);
        MyFixturesPage myFixtures = new MyFixturesPage(secondRowLayout, secondPages, scheduler, true);
        ResultsPage results = new ResultsPage(secondRowLayout, secondPages, scheduler, true);
        secondPages.add(allFixtures, "All Fixtures");
        secondPages.add(myFixtures, "My Fixtures");
        secondPages.add(results, "Results");
        addListeners(secondButtons, secondPages, secondRowLayout);

        Box thirdRow = Box.createHorizontalBox();
        firstTeamButton = new JButton("First Team");
        formationButton = new JButton("Formation");
        matchRolesButton = new JButton("Match Roles");
        makeMenuRow(thirdRow, firstTeamButton, formationButton, matchRolesButton);
        ArrayList<JButton> thirdButtons = new ArrayList<>();
        thirdButtons.add(firstTeamButton);
        thirdButtons.add(formationButton);
        thirdButtons.add(matchRolesButton);

        // Make the card layout for the pages of these buttons
        CardLayout thirdRowLayout = new CardLayout();
        JPanel thirdPages = new JPanel(thirdRowLayout);
        firstTeam = new FirstTeamPage(thirdRowLayout, thirdPages, scheduler, true);
        formation = new FormationPage(thirdRowLayout, thirdPages, scheduler, true);
        matchRoles = new MatchRolesPage(thirdRowLayout, thirdPages, scheduler, true);
        thirdPages.add(firstTeam, "First Team");
        thirdPages.add(formation, "Formation");
        thirdPages.add(matchRoles, "Match Roles");
        addListeners(thirdButtons, thirdPages, thirdRowLayout);

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

    public void addListeners(ArrayList<JButton> buttons, JPanel pages, CardLayout thisLayout){
        for(JButton button : buttons){
            button.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    window.getContentPane().removeAll();
                    window.getContentPane().add(pages, BorderLayout.CENTER);
                    thisLayout.show(pages, button.getText());
                    window.revalidate();
                    window.repaint();
                }
            });
        }
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

    public FirstTeamPage getFirstTeamPage() {
        return firstTeam;
    }

    public MatchRolesPage getMatchRolesPage() {
        return matchRoles;
    }

    public FormationPage getFormationPage() {
        return formation;
    }

}

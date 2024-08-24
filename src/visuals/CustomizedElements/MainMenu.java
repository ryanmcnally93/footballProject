package visuals.CustomizedElements;
import main.GameWindow;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends GamePanel {

    private JButton leagueTableButton, goalscorersButton, assistsButton, allFixturesButton, myFixturesButton, resultsButton, firstTeamButton, formationButton, matchRolesButton;

    public MainMenu() {
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.LIGHT_GRAY);

        Box container = Box.createVerticalBox();

        Box firstRow = Box.createHorizontalBox();
        leagueTableButton = new JButton("<League Table>");
        goalscorersButton = new JButton("Top Goalscorers");
        assistsButton = new JButton("Top Assists");
        makeMenuRow(firstRow, leagueTableButton, goalscorersButton, assistsButton);

        Box secondRow = Box.createHorizontalBox();
        // Add filters per competition
        allFixturesButton = new JButton("All Fixtures");
        // Add filters per competition
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

        container.add(firstRow);
        container.add(secondRow);
        container.add(thirdRow);

        mainPanel.add(container);
        add(mainPanel, BorderLayout.CENTER);
        repaint();
    }

    public void makeMenuRow(Box row, JButton buttonOne, JButton buttonTwo, JButton buttonThree){
        CardLayout layout = new CardLayout();
        JPanel buttonBox = new JPanel(layout);
        buttonBox.setBackground(Color.LIGHT_GRAY);

        JPanel buttonOneContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buttonOneContainer.add(buttonOne);
        JPanel buttonTwoContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buttonTwoContainer.add(buttonTwo);
        JPanel buttonThreeContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buttonThreeContainer.add(buttonThree);

        buttonBox.add(buttonOneContainer, buttonOne.getText());
        buttonBox.add(buttonTwoContainer, buttonTwo.getText());
        buttonBox.add(buttonThreeContainer, buttonThree.getText());

        JButton leftTrigger = new JButton("<");
        setPermanentWidth(leftTrigger, 30);
        JButton rightTrigger = new JButton(">");
        setPermanentWidth(rightTrigger, 30);

        row.add(leftTrigger);
        row.add(buttonBox);
        row.add(rightTrigger);

        leftTrigger.addActionListener(e -> {
            layout.previous(buttonBox);
        });

        rightTrigger.addActionListener(e -> {
            layout.next(buttonBox);
        });

    }

}

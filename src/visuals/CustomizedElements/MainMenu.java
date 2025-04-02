package visuals.CustomizedElements;
import main.GameWindow;
import visuals.ScheduleFrames.Scheduler;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends GamePanel {

    private Scheduler scheduler;
    private GameWindow window;

    public MainMenu(GameWindow newWindow, Scheduler scheduler) {
        this.window = newWindow;
        this.scheduler = scheduler;
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.LIGHT_GRAY);

        Box container = Box.createVerticalBox();

        Box firstRow = makeMenuRow(new JButton(), new JButton(), new JButton());

        container.add(Box.createVerticalGlue());
        container.add(firstRow);
        container.add(Box.createVerticalGlue());

        mainPanel.add(container);
        add(mainPanel, BorderLayout.CENTER);
        repaint();
    }

    public Box makeMenuRow(JButton buttonOne, JButton buttonTwo, JButton buttonThree){
        Box row = Box.createHorizontalBox();
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

        setPermanentWidthAndHeight(row, 200, 40);
        return row;
    }
}

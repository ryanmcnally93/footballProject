package visuals.CustomizedElements;
import javax.swing.*;

import general.UsersMatch;

import java.awt.*;
import java.awt.event.MouseAdapter;

public abstract class GamePanel extends JPanel {
    private static final long serialVersionUID = -8911764479146802449L;
    private Box east;
    private Box west;

    public GamePanel() {
    };

    protected void addGameMouseListener(MouseAdapter mouseAdapter) {
        addMouseListener(mouseAdapter);
    }

    public void appendEastAndWest(JPanel mainPanel){
        west = Box.createHorizontalBox();
        west.setPreferredSize(new Dimension(100,200));
        mainPanel.add(west, BorderLayout.WEST);
        east = Box.createHorizontalBox();
        east.setPreferredSize(new Dimension(100,200));
        mainPanel.add(east, BorderLayout.EAST);
    }

    public Box getWest() {
        return west;
    }

    public void setWest(Box west) {
        this.west = west;
    }

    public Box getEast() {
        return east;
    }

    public void setEast(Box east) {
        this.east = east;
    }
}
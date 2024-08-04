package visuals.CustomizedElements;
import javax.swing.*;

import main.Match;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;

public abstract class GamePanel extends JPanel {
    private static final long serialVersionUID = -8911764479146802449L;

    public GamePanel() {
    };

    protected void addGameMouseListener(MouseAdapter mouseAdapter) {
        addMouseListener(mouseAdapter);
    }

}
package visuals;
import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;

public abstract class GamePanel extends JPanel {
    private static final long serialVersionUID = -8911764479146802449L;
	protected Match match;

    public GamePanel() {
    	this.match = new Match();
    };
    
    public GamePanel(Match match) {
        this.match = match;
    }

    protected void addGameMouseListener(MouseAdapter mouseAdapter) {
        addMouseListener(mouseAdapter);
    }

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

}
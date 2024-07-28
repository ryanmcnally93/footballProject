package visuals;
import java.util.List;
import java.util.Map;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class MatchWatch extends MatchFrames {
    private static final long serialVersionUID = 5937268249853937276L;
    private JLabel liveScore;
    private CustomProgressBar shotsOnBar;
    private CustomProgressBar allShotsBar;

    public MatchWatch(CardLayout layout, JPanel pages, Map<String, JPanel> cardMap) {
        super(layout, pages, cardMap);
        
        setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));
        setBackground(Color.LIGHT_GRAY);

        // Header Box
        

//        SlidingPanel slidingPanel = super.getSlidingPanel();
//        slidingPanel.setBounds(0, 0, 800, 600);
    }
    
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        
//    }

	public void handleClick() {
		match.startMatch(getGraphics(), super.getCardMap());
	}
	
	public void updateScoreBoard(int home, int away) {
		System.out.println();
		setLiveScore("Arsenal " + home + " : " + away + " Tottenham");
		repaint();
	}
	
	public void updateShotsOnBar(int home, int away) {
		int total = home + away;
		if(total != 0) {
			if(away == 0) {
				setShotsOnBar(100);
			} else {
				int segments = 100/total;
				setShotsOnBar(segments*home);
			}
			
		}
		repaint();
	}
	
	public void updateAllShotsBar(int home, int away) {
		int total = home + away;
		if(total != 0) {
			if(away == 0) {
				setAllShotsBar(100);
			} else {
				int segments = 100/total;
				setAllShotsBar(segments*home);
			}
		}
		repaint();
	}

	public JLabel getLiveScore() {
		return liveScore;
	}

	public void setLiveScore(String score) {
		this.liveScore.setText(score);
	}

	public CustomProgressBar getShotsOnBar() {
		return shotsOnBar;
	}

	public void setShotsOnBar(int percentage) {
		shotsOnBar.setValue(percentage);
	}

	public CustomProgressBar getAllShotsBar() {
		return allShotsBar;
	}

	public void setAllShotsBar(int percentage) {
		allShotsBar.setValue(percentage);
	}

}
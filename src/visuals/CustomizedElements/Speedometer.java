package visuals.CustomizedElements;
import entities.Match;
import javax.swing.*;
import java.awt.*;


public class Speedometer extends GamePanel {

    private Match match;

    public Speedometer(Match match){
        this.match = match;
        setLayout(null);

        JButton slowDown = new JButton("Slower");
        slowDown.setMargin(new Insets(0, 0, 0, 0));
        slowDown.setBounds(30, 0, 80, 20);
        add(slowDown);

        PanelOfCircles circles = new PanelOfCircles();
        circles.changeCircleColor(match.getSpeed());
        circles.setBounds(145, 0, 110, 20);
        add(circles);

        JButton speedUp = new JButton("Faster");
        speedUp.setMargin(new Insets(0, 0, 0, 0));
        speedUp.setBounds(290, 0, 80, 20);
        add(speedUp);

        setPreferredSize(new Dimension(600, 70));
        setBackground(Color.LIGHT_GRAY);
        setBounds(0, 0, 600, 100);
        revalidate();
        repaint();
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}

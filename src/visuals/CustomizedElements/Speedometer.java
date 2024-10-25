package visuals.CustomizedElements;
import entities.Match;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;


public class Speedometer extends GamePanel {

    private Match match;
    private PanelOfCircles circles;
    private int speedIndex;

    public Speedometer(Match match){
        this.match = match;
        setLayout(null);

        JButton slowDown = new JButton("Slower");
        slowDown.setMargin(new Insets(0, 0, 0, 0));
        slowDown.setBounds(30, 0, 80, 20);
        add(slowDown);

        circles = new PanelOfCircles();
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

        speedIndex = 0;
        ArrayList<String> speeds = new ArrayList<>(Arrays.asList("slowest", "slow", "fast", "fastest"));
        for(int i=0; i<speeds.size(); i++){
            if(getMatch().getSpeed().equals(speeds.get(i))){
                speedIndex = i;
                break;
            }
        }

        slowDown.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if(speedIndex == 0){
                    speedIndex = 3;
                } else {
                    speedIndex--;
                }
                getCircles().changeCircleColor(speeds.get(speedIndex));
            }
        });

        speedUp.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if(speedIndex == 3){
                    speedIndex = 0;
                } else {
                    speedIndex++;
                }
                getCircles().changeCircleColor(speeds.get(speedIndex));
            }
        });

        revalidate();
        repaint();
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public PanelOfCircles getCircles() {
        return circles;
    }

    public void setCircles(PanelOfCircles circles) {
        this.circles = circles;
    }
}

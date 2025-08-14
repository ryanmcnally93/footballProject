package visuals.MatchPages;

import entities.Match;
import visuals.CustomizedElements.CustomizedButton;
import visuals.CustomizedElements.GamePanel;
import visuals.CustomizedElements.PanelOfCircles;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class Speedometer extends GamePanel {

        private Match match;
        private PanelOfCircles circles;
        private int speedIndex;
        private ArrayList<String> speeds;
        private CustomizedButton slowDown, speedUp;

        public Speedometer(){
            setLayout(null);

            slowDown = new CustomizedButton("Slower");
            slowDown.setMargin(new Insets(0, 0, 0, 0));
            slowDown.setBounds(55, 0, 80, 20);
            add(slowDown);

            circles = new PanelOfCircles();
            circles.setBounds(171, 0, 58, 20);
            add(circles);

            speedUp = new CustomizedButton("Faster");
            speedUp.setMargin(new Insets(0, 0, 0, 0));
            speedUp.setBounds(265, 0, 80, 20);
            add(speedUp);

            setPreferredSize(new Dimension(600, 70));
            setBackground(Color.LIGHT_GRAY);
            setBounds(400, 0, 400, 20);

            speedIndex = 0;
            speeds = new ArrayList<>(Arrays.asList("slowest", "slow", "fast", "fastest"));

            slowDown.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    if(speedIndex != 0){
                        speedIndex--;
                        if(speedIndex ==0 ){
                            remove(slowDown);
                            revalidate();
                            repaint();
                        }
                    }
                    if (speedIndex == 2){
                        add(speedUp);
                        revalidate();
                        repaint();
                    }
                    getCircles().changeCircleColor(speeds.get(speedIndex));
                    for(Match each : getMatch().getSameDayMatches()){
                        each.setSpeed(speeds.get(speedIndex));
                        each.getTimer().changeSpeedDuringMatch(speedIndex+1);
                    }
                    System.out.println("Speed is: " + getMatch().getSpeed());
                }
            });

            speedUp.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    if(speedIndex != 3){
                        speedIndex++;
                        if (speedIndex == 3) {
                            remove(speedUp);
                            revalidate();
                            repaint();
                        }
                    }
                    if (speedIndex == 1) {
                        add(slowDown);
                        revalidate();
                        repaint();
                    }
                    getCircles().changeCircleColor(speeds.get(speedIndex));
                    for(Match each : getMatch().getSameDayMatches()){
                        each.setSpeed(speeds.get(speedIndex));
                        each.getTimer().changeSpeedDuringMatch(speedIndex+1);
                    }
                    System.out.println("Speed is: " + getMatch().getSpeed());
                }
            });
            setPermanentWidthAndHeight(this, 400, 20);

            revalidate();
            repaint();
        }

        public Match getMatch() {
            return match;
        }

        public void setMatch(Match newMatch) {
            this.match = newMatch;
            circles.changeCircleColor(match.getSpeed());
            for(int i=0; i<speeds.size(); i++){
                if(getMatch().getSpeed().equals(speeds.get(i))){
                    speedIndex = i;
                    break;
                }
            }
            if(match.getSpeed().equals("slowest")) {
                remove(slowDown);
                if (!this.isAncestorOf(speedUp)) {
                    add(speedUp);
                }
            }
            if(match.getSpeed().equals("fastest")) {
                remove(speedUp);
                if (!this.isAncestorOf(slowDown)) {
                    add(slowDown);
                }
            }
            revalidate();
            repaint();
        }

        public PanelOfCircles getCircles() {
            return circles;
        }

        public void setCircles(PanelOfCircles circles) {
            this.circles = circles;
        }

}

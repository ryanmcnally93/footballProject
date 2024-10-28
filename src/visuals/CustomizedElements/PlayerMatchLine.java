package visuals.CustomizedElements;

import people.Footballer;

import javax.swing.*;
import java.awt.*;

public class PlayerMatchLine extends GamePanel {

//    private Box line;
    private JLabel name, saves, duelsWon, passingAccuracy, shootingAccuracy, fitness, rating;

    public PlayerMatchLine(Footballer player){

        setBackground(Color.LIGHT_GRAY);
        setOpaque(true);

        name = new JLabel(player.getName());
        setPermanentWidth(name, 200);

        saves = new JLabel("N/A");
        setPermanentWidth(saves, 60);

        passingAccuracy = new JLabel("N/A");
        setPermanentWidth(passingAccuracy, 60);

        shootingAccuracy = new JLabel("N/A");
        setPermanentWidth(shootingAccuracy, 60);

        duelsWon = new JLabel("N/A");
        setPermanentWidth(duelsWon, 60);

        fitness = new JLabel(player.getStamina() + "%");
        setPermanentWidth(fitness, 60);

        rating = new JLabel("N/A");
        setPermanentWidth(rating, 60);

        add(name);
        add(duelsWon);
        add(passingAccuracy);
        add(shootingAccuracy);
        add(fitness);
        add(rating);

        setPermanentHeight(this, 30);
        revalidate();
        repaint();
    }

    public String getPlayerName() {
        return name.getText();
    }

    public void setName(JLabel name) {
        this.name = name;
    }

    public JLabel getSaves() {
        return saves;
    }

    public void setSaves(String saves) {
        this.saves.setText(saves);
        this.saves.revalidate();
        this.saves.repaint();
    }

    public JLabel getDuelsWon() {
        return duelsWon;
    }

    public void setDuelsWon(String duelsWon) {
        this.duelsWon.setText(duelsWon);
        this.duelsWon.revalidate();
        this.duelsWon.repaint();
    }

    public JLabel getPassingAccuracy() {
        return passingAccuracy;
    }

    public void setPassingAccuracy(String passingAccuracy) {
        this.passingAccuracy.setText(passingAccuracy);
        this.passingAccuracy.revalidate();
        this.passingAccuracy.repaint();
    }

    public JLabel getShootingAccuracy() {
        return shootingAccuracy;
    }

    public void setShootingAccuracy(String shootingAccuracy) {
        this.shootingAccuracy.setText(shootingAccuracy);
        this.shootingAccuracy.revalidate();
        this.shootingAccuracy.repaint();
    }

    public JLabel getFitness() {
        return fitness;
    }

    public void setFitness(String fitness) {
        this.fitness.setText(fitness);
        this.fitness.revalidate();
        this.fitness.repaint();
    }

    public JLabel getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating.setText(rating);
        this.rating.revalidate();
        this.rating.repaint();
    }
}

package visuals.CustomizedElements;

import people.Footballer;

import javax.swing.*;
import java.awt.*;

public class PlayerStatsLineOnRatingsPage extends RoundedPanel {

    private JLabel name, saves, duelsWon, passingAccuracy, shootingAccuracy, fitness, rating, pos;

    public PlayerStatsLineOnRatingsPage (Footballer player){
        super(20);

        setBackground(Color.LIGHT_GRAY);

        pos = new JLabel(player.getLikedPosition());
        setPermanentWidth(pos, 30);
        pos.setHorizontalAlignment(SwingConstants.CENTER);

        name = new JLabel(player.getName());
        setPermanentWidth(name, 130);

        saves = new JLabel("N/A");
        setPermanentWidth(saves, 50);
        saves.setHorizontalAlignment(SwingConstants.CENTER);

        passingAccuracy = new JLabel("N/A");
        setPermanentWidth(passingAccuracy, 50);
        passingAccuracy.setHorizontalAlignment(SwingConstants.CENTER);

        shootingAccuracy = new JLabel("N/A");
        setPermanentWidth(shootingAccuracy, 50);
        shootingAccuracy.setHorizontalAlignment(SwingConstants.CENTER);

        duelsWon = new JLabel("N/A");
        setPermanentWidth(duelsWon, 50);
        duelsWon.setHorizontalAlignment(SwingConstants.CENTER);

        fitness = new JLabel(player.getStamina() + "%");
        setPermanentWidth(fitness, 50);
        fitness.setHorizontalAlignment(SwingConstants.CENTER);

        rating = new JLabel("N/A");
        setPermanentWidth(rating, 50);
        rating.setHorizontalAlignment(SwingConstants.CENTER);

        add(pos);
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

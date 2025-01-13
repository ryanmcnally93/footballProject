package visuals.CustomizedElements;

import people.Footballer;

import javax.swing.*;
import java.awt.*;

public class PlayerStatsLineOnRatingsPage extends RoundedPanel {

    private JLabel nameLabel, savesLabel, duelsWonLabel, passingAccuracyLabel, shootingAccuracyLabel, fitnessLabel, ratingLabel, posLabel;
    private Footballer player;

    public PlayerStatsLineOnRatingsPage (){
        super(20);

        setBackground(Color.LIGHT_GRAY);

        posLabel = new JLabel("N/A");
        setPermanentWidth(posLabel, 30);
        posLabel.setHorizontalAlignment(SwingConstants.CENTER);

        nameLabel = new JLabel("N/A");
        setPermanentWidth(nameLabel, 130);

        savesLabel = new JLabel("N/A");
        setPermanentWidth(savesLabel, 50);
        savesLabel.setHorizontalAlignment(SwingConstants.CENTER);

        passingAccuracyLabel = new JLabel("N/A");
        setPermanentWidth(passingAccuracyLabel, 50);
        passingAccuracyLabel.setHorizontalAlignment(SwingConstants.CENTER);

        shootingAccuracyLabel = new JLabel("N/A");
        setPermanentWidth(shootingAccuracyLabel, 50);
        shootingAccuracyLabel.setHorizontalAlignment(SwingConstants.CENTER);

        duelsWonLabel = new JLabel("N/A");
        setPermanentWidth(duelsWonLabel, 50);
        duelsWonLabel.setHorizontalAlignment(SwingConstants.CENTER);

        fitnessLabel = new JLabel(100 + "%");
        setPermanentWidth(fitnessLabel, 50);
        fitnessLabel.setHorizontalAlignment(SwingConstants.CENTER);

        ratingLabel = new JLabel("N/A");
        setPermanentWidth(ratingLabel, 50);
        ratingLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(posLabel);
        add(nameLabel);
        add(duelsWonLabel);
        add(passingAccuracyLabel);
        add(shootingAccuracyLabel);
        add(fitnessLabel);
        add(ratingLabel);

        setPermanentHeight(this, 30);
        revalidate();
        repaint();
    }

    public String getPlayerName() {
        return nameLabel.getText();
    }

    public void setNameLabel(JLabel nameLabel) {
        this.nameLabel = nameLabel;
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public void setSavesLabel(JLabel savesLabel) {
        this.savesLabel = savesLabel;
    }

    public void setDuelsWonLabel(JLabel duelsWonLabel) {
        this.duelsWonLabel = duelsWonLabel;
    }

    public void setPassingAccuracyLabel(JLabel passingAccuracyLabel) {
        this.passingAccuracyLabel = passingAccuracyLabel;
    }

    public void setShootingAccuracyLabel(JLabel shootingAccuracyLabel) {
        this.shootingAccuracyLabel = shootingAccuracyLabel;
    }

    public void setFitnessLabel(JLabel fitnessLabel) {
        this.fitnessLabel = fitnessLabel;
    }

    public void setRatingLabel(JLabel ratingLabel) {
        this.ratingLabel = ratingLabel;
    }

    public JLabel getSavesLabel() {
        return savesLabel;
    }

    public void setSavesLabel(String savesLabel) {
        this.savesLabel.setText(savesLabel);
        this.savesLabel.revalidate();
        this.savesLabel.repaint();
    }

    public JLabel getDuelsWonLabel() {
        return duelsWonLabel;
    }

    public void setDuelsWonLabel(String duelsWonLabel) {
        this.duelsWonLabel.setText(duelsWonLabel);
        this.duelsWonLabel.revalidate();
        this.duelsWonLabel.repaint();
    }

    public JLabel getPassingAccuracyLabel() {
        return passingAccuracyLabel;
    }

    public void setPassingAccuracyLabel(String passingAccuracyLabel) {
        this.passingAccuracyLabel.setText(passingAccuracyLabel);
        this.passingAccuracyLabel.revalidate();
        this.passingAccuracyLabel.repaint();
    }

    public JLabel getShootingAccuracyLabel() {
        return shootingAccuracyLabel;
    }

    public void setShootingAccuracyLabel(String shootingAccuracyLabel) {
        this.shootingAccuracyLabel.setText(shootingAccuracyLabel);
        this.shootingAccuracyLabel.revalidate();
        this.shootingAccuracyLabel.repaint();
    }

    public JLabel getNameAsJLabel(){
        return nameLabel;
    }

    public JLabel getFitnessLabel() {
        return fitnessLabel;
    }

    public void setFitnessLabel(String fitnessLabel) {
        this.fitnessLabel.setText(fitnessLabel);
        this.fitnessLabel.revalidate();
        this.fitnessLabel.repaint();
    }

    public JLabel getRatingLabel() {
        return ratingLabel;
    }

    public void setRatingLabel(String ratingLabel) {
        this.ratingLabel.setText(ratingLabel);
        this.ratingLabel.revalidate();
        this.ratingLabel.repaint();
    }

    public JLabel getPosLabel() {
        return posLabel;
    }

    public void setPosLabel(JLabel posLabel) {
        this.posLabel = posLabel;
    }

    public Footballer getPlayer() {
        return player;
    }

    public void setPlayer(Footballer player) {
        this.player = player;
    }
}

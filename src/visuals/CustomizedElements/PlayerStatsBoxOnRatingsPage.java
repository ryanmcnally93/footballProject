package visuals.CustomizedElements;
import people.Footballer;

import javax.swing.*;
import java.awt.*;

public class PlayerStatsBoxOnRatingsPage extends RoundedPanel {

    private JLabel name, saves, shotsOn, shotsOff, offsides, successfulPasses, failedPasses, duelsWon, duelsLost, fouls, substituted, yellowCard, goals, assists, redCard, injury;
    private Box mainBox, leftBox, rightBox;

    public PlayerStatsBoxOnRatingsPage(Footballer player){
        super(20);

        setBackground(Color.LIGHT_GRAY);

        name = new JLabel(player.getName());
        setPermanentWidthAndHeight(name, 250, 30);
        name.setHorizontalAlignment(SwingConstants.CENTER);
        add(name);

        mainBox = Box.createHorizontalBox();

        leftBox = Box.createVerticalBox();
        setPermanentWidthAndHeight(leftBox, 125, 345);
        leftBox.setBackground(Color.PINK);
        leftBox.setOpaque(true);

//        saves = new JLabel("N/A");
//        setPermanentWidth(saves, 50);
//        saves.setHorizontalAlignment(SwingConstants.CENTER);

        goals = new JLabel("N/A");
        setPermanentWidthAndHeight(goals, 125, 50);
        goals.setHorizontalAlignment(SwingConstants.LEFT);
        goals.setToolTipText("Goals Scored");
        leftBox.add(goals);

        shotsOn = new JLabel("N/A");
        setPermanentWidthAndHeight(shotsOn, 125, 50);
        shotsOn.setHorizontalAlignment(SwingConstants.LEFT);
        shotsOn.setToolTipText("Shots On Target");
        leftBox.add(shotsOn);

        shotsOff = new JLabel("N/A");
        setPermanentWidthAndHeight(shotsOff, 125, 50);
        shotsOff.setHorizontalAlignment(SwingConstants.LEFT);
        shotsOff.setToolTipText("Shots Off Target");
        leftBox.add(shotsOff);

        offsides = new JLabel("N/A");
        setPermanentWidthAndHeight(offsides, 125, 50);
        offsides.setHorizontalAlignment(SwingConstants.LEFT);
        offsides.setToolTipText("Offsides");
        leftBox.add(offsides);

        duelsWon = new JLabel("N/A");
        setPermanentWidthAndHeight(duelsWon, 125, 50);
        duelsWon.setHorizontalAlignment(SwingConstants.LEFT);
        duelsWon.setToolTipText("Duels Won");
        leftBox.add(duelsWon);

        duelsLost = new JLabel("N/A");
        setPermanentWidthAndHeight(duelsLost, 125, 50);
        duelsLost.setHorizontalAlignment(SwingConstants.LEFT);
        duelsLost.setToolTipText("Duels Lost");
        leftBox.add(duelsLost);

        substituted = new JLabel("N/A");
        setPermanentWidthAndHeight(substituted, 125, 50);
        substituted.setHorizontalAlignment(SwingConstants.LEFT);
        substituted.setToolTipText("Substituted");
        leftBox.add(substituted);

        mainBox.add(leftBox);

        // RightBox

        rightBox = Box.createVerticalBox();
        setPermanentWidthAndHeight(rightBox, 125, 345);
        rightBox.setBackground(Color.BLUE);
        rightBox.setOpaque(true);

        assists = new JLabel("N/A");
        setPermanentWidthAndHeight(assists, 125, 50);
        assists.setHorizontalAlignment(SwingConstants.LEFT);
        assists.setToolTipText("Assists");
        rightBox.add(assists);

        successfulPasses = new JLabel("N/A");
        setPermanentWidthAndHeight(successfulPasses, 125, 50);
        successfulPasses.setHorizontalAlignment(SwingConstants.LEFT);
        successfulPasses.setToolTipText("Successful Passes");
        rightBox.add(successfulPasses);

        failedPasses = new JLabel("N/A");
        setPermanentWidthAndHeight(failedPasses, 125, 50);
        failedPasses.setHorizontalAlignment(SwingConstants.LEFT);
        failedPasses.setToolTipText("Failed Passes");
        rightBox.add(failedPasses);

        fouls = new JLabel("N/A");
        setPermanentWidthAndHeight(fouls, 125, 50);
        fouls.setHorizontalAlignment(SwingConstants.LEFT);
        fouls.setToolTipText("Fouls");
        rightBox.add(fouls);

        yellowCard = new JLabel("N/A");
        setPermanentWidthAndHeight(yellowCard, 125, 50);
        yellowCard.setHorizontalAlignment(SwingConstants.LEFT);
        yellowCard.setToolTipText("Yellow Cards");
        rightBox.add(yellowCard);

        redCard = new JLabel("N/A");
        setPermanentWidthAndHeight(redCard, 125, 50);
        redCard.setHorizontalAlignment(SwingConstants.LEFT);
        redCard.setToolTipText("Red Card");
        rightBox.add(redCard);

        injury = new JLabel("N/A");
        setPermanentWidthAndHeight(injury, 125, 50);
        injury.setHorizontalAlignment(SwingConstants.LEFT);
        injury.setToolTipText("Injury");
        rightBox.add(injury);

        mainBox.add(rightBox);

        add(mainBox);

        revalidate();
        repaint();
    }

    public String getPlayerName() {
        return name.getText();
    }

    public void setName(String name) {
        this.name.setText(name);
        this.name.revalidate();
        this.name.repaint();
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

    public JLabel getShotsOn() {
        return shotsOn;
    }

    public void setShotsOn(String shotsOn) {
        this.shotsOn.setText(shotsOn);
        this.shotsOn.revalidate();
        this.shotsOn.repaint();
    }

    public JLabel getShotsOff() {
        return shotsOff;
    }

    public void setShotsOff(String shotsOff) {
        this.shotsOff.setText(shotsOff);
        this.shotsOff.revalidate();
        this.shotsOff.repaint();
    }

    public JLabel getOffsides() {
        return offsides;
    }

    public void setOffsides(String offsides) {
        this.offsides.setText(offsides);
        this.offsides.revalidate();
        this.offsides.repaint();
    }

    public JLabel getSuccessfulPasses() {
        return successfulPasses;
    }

    public void setSuccessfulPasses(String successfulPasses) {
        this.successfulPasses.setText(successfulPasses);
        this.successfulPasses.revalidate();
        this.successfulPasses.repaint();
    }

    public JLabel getFailedPasses() {
        return failedPasses;
    }

    public void setFailedPasses(String failedPasses) {
        this.failedPasses.setText(failedPasses);
        this.failedPasses.revalidate();
        this.failedPasses.repaint();
    }

    public void setDuelsWon(String duelsWon) {
        this.duelsWon.setText(duelsWon);
        this.duelsWon.revalidate();
        this.duelsWon.repaint();
    }

    public JLabel getDuelsLost() {
        return duelsLost;
    }

    public void setDuelsLost(String duelsLost) {
        this.duelsLost.setText(duelsLost);
        this.duelsLost.revalidate();
        this.duelsLost.repaint();
    }

    public JLabel getFouls() {
        return fouls;
    }

    public void setFouls(String fouls) {
        this.fouls.setText(fouls);
        this.fouls.revalidate();
        this.fouls.repaint();
    }

    public JLabel getSubstituted() {
        return substituted;
    }

    public void setSubstituted(String substituted) {
        this.substituted.setText(substituted);
        this.substituted.revalidate();
        this.substituted.repaint();
    }

    public JLabel getYellowCard() {
        return yellowCard;
    }

    public void setYellowCard(String yellowCard) {
        this.yellowCard.setText(yellowCard);
        this.yellowCard.revalidate();
        this.yellowCard.repaint();
    }

    public JLabel getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals.setText(goals);
        this.goals.revalidate();
        this.goals.repaint();
    }

    public JLabel getAssists() {
        return assists;
    }

    public void setAssists(String assists) {
        this.assists.setText(assists);
        this.assists.revalidate();
        this.assists.repaint();
    }

    public JLabel getRedCard() {
        return redCard;
    }

    public void setRedCard(String redCard) {
        this.redCard.setText(redCard);
        this.redCard.revalidate();
        this.redCard.repaint();
    }

    public JLabel getInjury() {
        return injury;
    }

    public void setInjury(String injury) {
        this.injury.setText(injury);
        this.injury.revalidate();
        this.injury.repaint();
    }

    public Box getMainBox() {
        return mainBox;
    }

    public void setMainBox(Box mainBox) {
        this.mainBox = mainBox;
    }

    public Box getLeftBox() {
        return leftBox;
    }

    public void setLeftBox(Box leftBox) {
        this.leftBox = leftBox;
    }

    public Box getRightBox() {
        return rightBox;
    }

    public void setRightBox(Box rightBox) {
        this.rightBox = rightBox;
    }

    public void updateFocus(PlayerStatsBoxOnRatingsPage thisLine) {

    }
}

package main.java.visuals.CustomizedElements;
import main.java.people.Footballer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PlayerStatsBoxOnRatingsPage extends RoundedPanel {

    private JLabel name, saves, shotsOn, shotsOff, offsides, successfulPasses, failedPasses, duelsWon, duelsLost, fouls, substituted, yellowCard, goals, assists, redCard, injury;
    private Box mainBox, leftBox, rightBox;

    public PlayerStatsBoxOnRatingsPage(){
        super(20);

        setBackground(Color.LIGHT_GRAY);

        // This gives some bottom margin to the playerBox
        setPermanentWidthAndHeight(this, 250, 395);
        setBorder(new EmptyBorder(0, 0, 10, 0));
        setBorder(Color.BLACK, 2);

        name = new JLabel();
        setPermanentWidthAndHeight(name, 250, 30);
        name.setHorizontalAlignment(SwingConstants.CENTER);
        add(name);

        mainBox = Box.createHorizontalBox();

        leftBox = Box.createVerticalBox();
        setPermanentWidthAndHeight(leftBox, 125, 345);

//        saves = new JLabel("N/A");
//        setPermanentWidth(saves, 50);
//        saves.setHorizontalAlignment(SwingConstants.CENTER);

        goals = new JLabel("N/A");
        goals.setToolTipText("Goals Scored");
        createField(goals, "Goals: ", leftBox);

        shotsOn = new JLabel("N/A");
        shotsOn.setToolTipText("Shots On Target");
        createField(shotsOn, "Shots On: ", leftBox);

        shotsOff = new JLabel("N/A");
        shotsOff.setToolTipText("Shots Off Target");
        createField(shotsOff, "Shots Off: ", leftBox);

        offsides = new JLabel("N/A");
        offsides.setToolTipText("Offsides");
        createField(offsides, "Offsides: ", leftBox);

        duelsWon = new JLabel("N/A");
        duelsWon.setToolTipText("Duels Won");
        createField(duelsWon, "Duels Won: ", leftBox);

        duelsLost = new JLabel("N/A");
        duelsLost.setToolTipText("Duels Lost");
        createField(duelsLost, "Duels Lost: ", leftBox);

        substituted = new JLabel("N/A");
        substituted.setToolTipText("Substituted");
        createField(substituted, "Substituted: ", leftBox);
        leftBox.setBorder(new EmptyBorder(0,9,0,0));

        mainBox.add(leftBox);

        // RightBox

        rightBox = Box.createVerticalBox();
        setPermanentWidthAndHeight(rightBox, 125, 345);

        assists = new JLabel("N/A");
        assists.setToolTipText("Assists");
        createField(assists, "Assists: ", rightBox);

        successfulPasses = new JLabel("N/A");
        successfulPasses.setToolTipText("Successful Passes");
        createField(successfulPasses, "S. Passes: ", rightBox);

        failedPasses = new JLabel("N/A");
        failedPasses.setToolTipText("Failed Passes");
        createField(failedPasses, "F. Passes: ", rightBox);

        fouls = new JLabel("N/A");
        fouls.setToolTipText("Fouls");
        createField(fouls, "Fouls: ", rightBox);

        yellowCard = new JLabel("N/A");
        yellowCard.setToolTipText("Yellow Cards");
        createField(yellowCard, "Ylw. Cards: ", rightBox);

        redCard = new JLabel("N/A");
        redCard.setToolTipText("Red Cards");
        createField(redCard, "Red Cards: ", rightBox);

        injury = new JLabel("N/A");
        injury.setToolTipText("Injury");
        createField(injury, "Injury: ", rightBox);

        mainBox.add(rightBox);

        add(mainBox);

        revalidate();
        repaint();
    }

    public void createField(JLabel field, String fieldLabel, Box box) {
        Box line = Box.createHorizontalBox();
        JLabel label = new JLabel(fieldLabel);
        setPermanentWidthAndHeight(label, 85, 46);
        label.setToolTipText(field.getToolTipText());
        line.add(label);
        setPermanentWidthAndHeight(field, 50, 46);
        line.add(field);
        box.add(line);
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

    public void setPlayerStats(Footballer player) {
        setName(player.getName());

        setShotsOn(String.valueOf(player.getShotsOnTargetThisMatch()));
        setShotsOff(String.valueOf(player.getShotsOffTargetThisMatch()));

        setDuelsWon(String.valueOf(player.getDuelsWonThisMatch()));
        setDuelsLost(String.valueOf(player.getDuelsLostThisMatch()));

        setSuccessfulPasses(String.valueOf(player.getSuccessfulPassesThisMatch()));
        setFailedPasses(String.valueOf(player.getFailedPassesThisMatch()));

        setYellowCard(String.valueOf(player.getYellowCardThisMatch()));
        setRedCard(String.valueOf(player.getRedCardThisMatch()));

        setGoals(String.valueOf(player.getGoalsThisMatch()));
        setAssists(String.valueOf(player.getAssistsThisMatch()));

        setOffsides(String.valueOf(player.getOffsidesThisMatch()));
        setFouls(String.valueOf(player.getFoulsThisMatch()));
        setSubstituted(String.valueOf(player.getSubstitutedThisMatch()));
        setInjury(String.valueOf(player.getInjuryTimeThisMatch()));
    }
}

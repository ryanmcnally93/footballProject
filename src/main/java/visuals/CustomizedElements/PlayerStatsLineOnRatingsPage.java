package visuals.CustomizedElements;

import entities.Match;
import people.Footballer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class PlayerStatsLineOnRatingsPage extends RoundedPanel {

    private JLabel nameLabel, savesLabel, duelsWonLabel, passingAccuracyLabel, shootingAccuracyLabel, fitnessLabel, ratingLabel, posLabel;
    private CircledLabel captaincyLabel;
    private JButton playerViewButton;
    private Footballer player;
    private ImageIcon icon;
    private BufferedImage bufferedScaledImage;
    private ImageIcon playerViewIcon = new ImageIcon("./src/main/java/visuals/images/playerViewIcon.png");

    public PlayerStatsLineOnRatingsPage (){
        super(20);

        setBackground(Color.LIGHT_GRAY);

        captaincyLabel = new CircledLabel("");
        captaincyLabel.setOffset(3);
        setPermanentWidth(captaincyLabel, 18);
        captaincyLabel.setHorizontalAlignment(SwingConstants.CENTER);

        posLabel = new JLabel("N/A");
        setPermanentWidth(posLabel, 30);
        posLabel.setHorizontalAlignment(SwingConstants.CENTER);

        nameLabel = new JLabel("N/A");
        setPermanentWidth(nameLabel, 127);

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
        setPermanentWidth(ratingLabel, 35);
        ratingLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(captaincyLabel);
        add(posLabel);
        add(nameLabel);
        add(duelsWonLabel);
        add(passingAccuracyLabel);
        add(shootingAccuracyLabel);
        add(fitnessLabel);
        add(ratingLabel);

        // This is for the football image
        bufferedScaledImage = getIconWithSpecificSizeAsBufferedScaledImage("./src/main/java/visuals/images/ratings_page_goal.png", 20);
        icon = new ImageIcon(bufferedScaledImage);
        icon.setDescription("Goal");

        setPermanentHeight(this, 30);
        revalidate();
        repaint();
    }

    public void addCaptaincy() {
        this.captaincyLabel.setText("C");
        this.captaincyLabel.addCircle();
        this.captaincyLabel.revalidate();
        this.captaincyLabel.repaint();
    }

    public void removeCaptaincy() {
        this.captaincyLabel.setText("");
        this.captaincyLabel.removeCircle();
        this.captaincyLabel.revalidate();
        this.captaincyLabel.repaint();
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

    public int viewGoalsScoredWhenMatchFinished(Footballer player, Map<String, Footballer> team, Match match) {
        int goals = 0;
        if (team == match.getHomeTeam()) {
            for (String goal : match.getHomeScorers()) {
                if (goal.contains(player.getName())) {
                    String[] values = goal.split(",");
                    goals = values.length;
                }
            }
        } else {
            for (String goal : match.getAwayScorers()) {
                if (goal.contains(player.getName())) {
                    String[] values = goal.split(",");
                    goals = values.length;
                }
            }
        }
        return goals;
    }

    public void updateLineWhenMatchFinished(Footballer player, Map<String, Footballer> team, Match match) {
        setPlayer(player);
        getNameLabel().setText(player.getName());

        // The match's teams are saved and not altered after match is completed
        // So we can access what position the player played in
        if (team == match.getHomeTeam()) {
            for (Map.Entry<String, Footballer> homePlayer : match.getHomeTeam().entrySet()) {
                if (player == homePlayer) {
                    getPosLabel().setText(homePlayer.getKey());
                    break;
                }
            }
        } else {
            for (Map.Entry<String, Footballer> awayPlayer : match.getAwayTeam().entrySet()) {
                if (player == awayPlayer) {
                    getPosLabel().setText(awayPlayer.getKey());
                    break;
                }
            }
        }

        int goals = viewGoalsScoredWhenMatchFinished(player, team, match);

        // This is for the football image
        if(goals == 1) {
            getNameAsJLabel().setIcon(icon);
            getNameAsJLabel().setHorizontalTextPosition(SwingConstants.LEFT);
        } else if (goals > 1) {
            ImageWithText multipleGoals = new ImageWithText(bufferedScaledImage, String.valueOf(goals), 0.4f);
            getNameAsJLabel().setIcon(multipleGoals);
            getNameAsJLabel().setHorizontalTextPosition(SwingConstants.LEFT);
        } else {
            getNameAsJLabel().setIcon(null);
        }

        setFitnessLabel("");
        setDuelsWonLabel("");
        setPassingAccuracyLabel("");
        setShootingAccuracyLabel("");

        if (team == match.getHomeTeam()) {
            setRatingLabel(String.valueOf(match.getHomeRatings().get(player)));
        } else {
            setRatingLabel(String.valueOf(match.getAwayRatings().get(player)));
        }
    }

    public void updateLine(Footballer player){
        setPlayer(player);
        getNameLabel().setText(player.getName());
        getPosLabel().setText(player.getPositionPlaced());
        // This is for the football image
        if(player.getGoalsThisMatch() == 1) {
            getNameAsJLabel().setIcon(icon);
            getNameAsJLabel().setHorizontalTextPosition(SwingConstants.LEFT);
        } else if (player.getGoalsThisMatch() > 1) {
            ImageWithText multipleGoals = new ImageWithText(bufferedScaledImage, String.valueOf(player.getGoalsThisMatch()), 0.4f);
            getNameAsJLabel().setIcon(multipleGoals);
            getNameAsJLabel().setHorizontalTextPosition(SwingConstants.LEFT);
        } else {
            getNameAsJLabel().setIcon(null);
        }

//        line.setSaves(String.valueOf(player.getSavesThisMatch()));
        setFitnessLabel(player.getStamina() + "%");

        player.updateDuelsPercentageThisMatch();
        setDuelsWonLabel(player.getDuelsPercentageThisMatch() + "%");

        player.updatePassingAccuracyThisMatch();
        setPassingAccuracyLabel(player.getPassingAccuracyThisMatch() + "%");

        player.updateShotAccuracyThisMatch();
        setShootingAccuracyLabel(player.getShotAccuracyThisMatch() + "%");

        if (player.getTeam().getCaptain().equals(player)) {
            addCaptaincy();
        } else if (getCaptaincyLabel().getText().equals("C")) {
            removeCaptaincy();
        }

        setRatingLabel(String.valueOf(10));
    }

    public CircledLabel getCaptaincyLabel() {
        return captaincyLabel;
    }

    public void setCaptaincyLabel(CircledLabel captaincyLabel) {
        this.captaincyLabel = captaincyLabel;
    }

    public void addPlayerViewButton() {
        Image image = playerViewIcon.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(image);
        playerViewButton = new JButton(resizedIcon);
        playerViewButton.setBackground(getBackground());
        playerViewButton.setOpaque(true);
        playerViewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        playerViewButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setPermanentWidthAndHeight(playerViewButton, 18, 18);
        add(playerViewButton);
        revalidate();
        repaint();
    }

    public void removePlayerViewButton() {
        if (playerViewButton != null) {
            Arrays.asList(getComponents()).remove(playerViewButton);
            playerViewButton = null;
            revalidate();
            repaint();
        }
    }

    public JButton getPlayerViewButton() {
        return playerViewButton;
    }

    public void setPlayerViewButton(JButton playerViewButton) {
        this.playerViewButton = playerViewButton;
    }
}

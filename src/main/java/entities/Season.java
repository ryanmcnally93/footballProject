package main.java.entities;
import main.java.visuals.CustomizedElements.PlayerAchievementLine;
import java.util.Map;

public class Season {

    private static int counter = 0;
    private int yearFrom;
    private int yearTo;
    private int number;
    private League premLeague;
    // private Competition ChampoLeague;
    // private Competition FaCup;
    // private Competition MikiMouseCup;
    // private Competition Otherleagues;

    // These need to be in Competition class
    // private Map<String, PlayerAchievementLine> playerAchievements;
    // private Map<String, TeamAchievementLine> teamAchievements;

    public Season(Map<String, Team> preTeams){
        counter++;
        this.number = counter;
        this.yearFrom = 2023 + counter;
        this.yearTo = 2024 + counter;
        premLeague = new League("Premia League", "England", 20, preTeams, 1, this);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getYearFrom() {
        return yearFrom;
    }

    public void setYearFrom(int yearFrom) {
        this.yearFrom = yearFrom;
    }

    public int getYearTo() {
        return yearTo;
    }

    public void setYearTo(int yearTo) {
        this.yearTo = yearTo;
    }

    public League getPremLeague() {
        return premLeague;
    }

    public void setPremLeague(League premLeague) {
        this.premLeague = premLeague;
    }
}

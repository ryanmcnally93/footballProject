package visuals.CustomizedElements;
import people.Footballer;

public class PlayerAchievementLine {

    private Footballer player;
    private int number;
    private String position;
    private Integer OVR;
    private String name;
    private String team;
    private Integer goals;
    private Integer assists;
    private Integer yellows;
    private Integer reds;
    private static int initialNum = 0;
    private Integer positionByNumber;

    // We will need to add competition to this eventually
    public PlayerAchievementLine(Footballer player) {
        this.position = player.getLikedPosition();
        this.OVR = 99;
        this.player = player;
        this.name = player.getName();
        this.team = player.getTeam().getName();
        this.goals = 0;
        this.assists = 0;
        this.yellows = 0;
        this.reds = 0;
        initialNum++;
        this.number = initialNum;
        if(position.equals("GK")){
            this.positionByNumber = 8;
        } else if(position.equals("RB")){
            this.positionByNumber = 7;
        } else if (position.equals("CB")){
            this.positionByNumber = 6;
        } else if (position.equals("LB")){
            this.positionByNumber = 5;
        } else if (position.equals("CM")){
            this.positionByNumber = 4;
        } else if (position.equals("CAM")){
            this.positionByNumber = 3;
        } else if (position.equals("RW")){
            this.positionByNumber = 2;
        } else if (position.equals("LW")){
            this.positionByNumber = 1;
        } else if (position.equals("ST")){
            this.positionByNumber = 0;
        }
    }

    public String getPosition() {
        return position;
    }



    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getOVR() {
        return OVR;
    }

    public void setOVR(Integer OVR) {
        this.OVR = OVR;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Integer getGoals() {
        return goals;
    }

    public void addToGoals() {
        this.goals += 1;
    }

    public Integer getAssists() {
        return assists;
    }

    public void addToAssists() {
        this.assists += 1;
    }

    public Integer getReds() {
        return reds;
    }

    public void setReds(Integer reds) {
        this.reds = reds;
    }

    public Integer getYellows() {
        return yellows;
    }

    public void setYellows(Integer yellows) {
        this.yellows = yellows;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public static int getInitialNum() {
        return initialNum;
    }

    public static void setInitialNum(int initialNum) {
        PlayerAchievementLine.initialNum = initialNum;
    }

    public Integer getPositionByNumber() {
        return positionByNumber;
    }

    public Footballer getPlayer() {
        return player;
    }

    public void setPlayer(Footballer player) {
        this.player = player;
    }

    public void setGoals(Integer goals) {
        this.goals = goals;
    }

    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    public void setPositionByNumber(Integer positionByNumber) {
        this.positionByNumber = positionByNumber;
    }
}

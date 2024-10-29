package people;

// Update Character like in vehicle speed
// Make startrun smaller using more methods
// Call more functions from other .java?

import entities.Team;

// Players are called Characters, because that's how we started
public class Footballer extends Individual {
	
	public int attack;
	public int defence;
	public String likedPosition;
	private Team team;

	private int savesThisMatch = 0;
	private int duelsPercentageThisMatch = 0;
	private int passingAccuracyThisMatch = 0;
	private int shotAccuracyThisMatch = 0;
	private int ratingThisMatch = 0;

	private int duelsWonThisMatch;
	private int duelsLostThisMatch;
	private int shotsOnTargetThisMatch;
	private int shotsOffTargetThisMatch;
	private int successfulPassesThisMatch;
	private int failedPassesThisMatch;

	public int stamina;

	// Define Player Characteristics
	
	public Footballer(String name, int age) {
		super(name,age);
		this.stamina = 100;
	}
	
	public Footballer(String name, int age, int attack, int defence, int stamina, String position) {
		super(name,age);
		this.attack = attack;
		this.defence = defence;
		this.stamina = stamina;
		this.likedPosition = position;
	}
	
	public int getStamina() {
		return this.stamina;
	}
	
	public void removeStamina(int number) {
		this.stamina -= number;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public String getLikedPosition() {
		return likedPosition;
	}

	public void setLikedPosition(String likedPosition) {
		this.likedPosition = likedPosition;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public int getPassingAccuracyThisMatch() {
		return passingAccuracyThisMatch;
	}

	public void updatePassingAccuracyThisMatch(){
		int passes = shotsOnTargetThisMatch + shotsOffTargetThisMatch;
		passingAccuracyThisMatch = ((int) 100 /passes)*shotsOnTargetThisMatch;
	}

	public void setPassingAccuracyThisMatch(int passingAccuracyThisMatch) {
		this.passingAccuracyThisMatch = passingAccuracyThisMatch;
	}

	public int getSavesThisMatch() {
		return savesThisMatch;
	}

	public void setSavesThisMatch(int savesThisMatch) {
		this.savesThisMatch = savesThisMatch;
	}

	public int getDuelsPercentageThisMatch() {
		return duelsPercentageThisMatch;
	}

	public void updateDuelsPercentageThisMatch(){
		int duels = duelsWonThisMatch + duelsLostThisMatch;
		duelsPercentageThisMatch = ((int) 100 /duels)*duelsWonThisMatch;
	}

	public void setDuelsPercentageThisMatch(int duelsPercentageThisMatch) {
		this.duelsPercentageThisMatch = duelsPercentageThisMatch;
	}

	public void updateShotAccuracyThisMatch() {
		int shots = shotsOnTargetThisMatch + shotsOffTargetThisMatch;
		shotAccuracyThisMatch = ((int) 100 /shots)*shotsOnTargetThisMatch;
	}

	public int getShotAccuracyThisMatch(){
		return shotAccuracyThisMatch;
	}

	public void setShotAccuracyThisMatch(int shotAccuracyThisMatch) {
		this.shotAccuracyThisMatch = shotAccuracyThisMatch;
	}

	public int getRatingThisMatch() {
		return ratingThisMatch;
	}

	public void setRatingThisMatch(int ratingThisMatch) {
		this.ratingThisMatch = ratingThisMatch;
	}

	public void newMatchReset() {
		setStamina(100);
		setDuelsPercentageThisMatch(0);
		setSavesThisMatch(0);
		setDuelsPercentageThisMatch(0);
		setPassingAccuracyThisMatch(0);
		setShotAccuracyThisMatch(0);
		setRatingThisMatch(0);
		setDuelsWonThisMatch(0);
		setDuelsLostThisMatch(0);
		setShotsOnTargetThisMatch(0);
		setShotsOffTargetThisMatch(0);
		setSuccessfulPassesThisMatch(0);
		setFailedPassesThisMatch(0);
	}

	public int getDuelsWonThisMatch() {
		return duelsWonThisMatch;
	}

	public void setDuelsWonThisMatch(int duelsWonThisMatch) {
		this.duelsWonThisMatch = duelsWonThisMatch;
	}

	public int getDuelsLostThisMatch() {
		return duelsLostThisMatch;
	}

	public void setDuelsLostThisMatch(int duelsLostThisMatch) {
		this.duelsLostThisMatch = duelsLostThisMatch;
	}

	public int getShotsOnTargetThisMatch() {
		return shotsOnTargetThisMatch;
	}

	public void setShotsOnTargetThisMatch(int shotsOnTargetThisMatch) {
		this.shotsOnTargetThisMatch = shotsOnTargetThisMatch;
	}

	public int getShotsOffTargetThisMatch() {
		return shotsOffTargetThisMatch;
	}

	public void setShotsOffTargetThisMatch(int shotsOffTargetThisMatch) {
		this.shotsOffTargetThisMatch = shotsOffTargetThisMatch;
	}

	public int getSuccessfulPassesThisMatch() {
		return successfulPassesThisMatch;
	}

	public void setSuccessfulPassesThisMatch(int successfulPassesThisMatch) {
		this.successfulPassesThisMatch = successfulPassesThisMatch;
	}

	public int getFailedPassesThisMatch() {
		return failedPassesThisMatch;
	}

	public void setFailedPassesThisMatch(int failedPassesThisMatch) {
		this.failedPassesThisMatch = failedPassesThisMatch;
	}

	public void addToDuelsWon() {
		this.duelsWonThisMatch++;
	}

	public void addToDuelsLost() {
		duelsLostThisMatch++;
	}

	public void addToShotOnTarget() {
		shotsOnTargetThisMatch++;
	}
}

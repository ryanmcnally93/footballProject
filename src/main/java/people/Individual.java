package main.java.people;

public class Individual {
	
	private String name;
	private int age;
	private int wealth;
	private String[] trophiesWon;
	
	public Individual(String name, int age) {
		setName(name);
		setAge(age);
		setWealth(0);
	}
	
	public Individual(String name, int age, int wealth) {
		setName(name);
		setAge(age);
		setWealth(wealth);
	}
	
	public String[] getTrophiesWon() {
		return trophiesWon;
	}
	public void setTrophiesWon(String[] trophiesWon) {
		this.trophiesWon = trophiesWon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getWealth() {
		return wealth;
	}
	public void setWealth(int wealth) {
		this.wealth = wealth;
	}
	

}

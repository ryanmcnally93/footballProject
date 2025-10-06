package entities;

import java.time.LocalDateTime;
import java.util.*;

public class Cup extends Competition {

	private ArrayList<Match> mainFixtureList, temporaryFixtureList;
	private Map<Integer, Map<String, Match>> matchWeeksMatches;
	private Map<Integer, Map<Integer, LocalDateTime>> matchWeeksSlots;
	private boolean restartWholeProcess = false;

    public Cup() {};

	protected Cup(String name, String country, Map<String, Team> teams, Season season) {
        super(name, country, teams, season);
		this.matchWeeksMatches = new HashMap<>();
		this.matchWeeksSlots = new HashMap<>();
	}

	public ArrayList<Match> getMainFixtureList() {
		return mainFixtureList;
	}

	public void setMainFixtureList(ArrayList<Match> mainFixtureList) {
		this.mainFixtureList = mainFixtureList;
	}

	public Map<Integer, Map<String, Match>> getMatchWeeksMatches() {
		return matchWeeksMatches;
	}

	public void setMatchWeeksMatches(Map<Integer, Map<String, Match>> matchWeeksMatches) {
		this.matchWeeksMatches = matchWeeksMatches;
	}

	public boolean isRestartWholeProcess() {
		return restartWholeProcess;
	}

	public void setRestartWholeProcess(boolean restartWholeProcess) {
		this.restartWholeProcess = restartWholeProcess;
	}

	public Map<Integer, Map<Integer, LocalDateTime>> getMatchWeeksSlots() {
		return matchWeeksSlots;
	}

	public void setMatchWeeksSlots(Map<Integer, Map<Integer, LocalDateTime>> matchWeeksSlots) {
		this.matchWeeksSlots = matchWeeksSlots;
	}

	public ArrayList<Match> getTemporaryFixtureList() {
		return temporaryFixtureList;
	}

	public void setTemporaryFixtureList(ArrayList<Match> temporaryFixtureList) {
		this.temporaryFixtureList = temporaryFixtureList;
	}

    @Override
    public List<String> getRoundNames() {
        List<String> result = new ArrayList<>();
        result.add("Round 1");
        result.add("Round 2");
        result.add("Round 3");
        result.add("Round 4");
        result.add("Quarter Finals");
        result.add("Semi Finals");
        result.add("Final");
        return result;
    }
}

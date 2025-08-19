package entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import people.Footballer;
import people.Goalkeeper;
import people.Manager;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TeamTest {

    @Test
    public void testConstructorWithCorrectParams() {
        Footballer footballer = new Footballer();
        Map<String, Footballer> players = new HashMap<>(Map.of("1", footballer));
        Team team = new Team("Name", new Manager("Person", 18, 20000), players, 20000000L, "Stadium", Color.RED, Color.WHITE);
        assertNotNull(team);
        for(Map.Entry<String, Footballer> each : players.entrySet()){
            Footballer thisPlayer = each.getValue();
            assertEquals(team.getName(), thisPlayer.getTeam().getName());
        }
    }

    @Test
    void testGettersAndSetters() {
        Team team = new Team();
        Manager manager = new Manager("Manager", 45, 100000);
        Formation formation = new Formation(4, 4, 2);
        Footballer captain = new Footballer();
        Goalkeeper goalkeeper = new Goalkeeper();
        Map<String, Footballer> players = new HashMap<>(Map.of("1", captain, "GK", goalkeeper));

        team.setName("Team One");
        team.setCaptain(captain);
        team.setManager(manager);
        team.setFormation(formation);
        team.setPlayers(players);
        team.setFirstTeam(players);
        team.setBudget(20000000L);
        team.setPrimaryColour(Color.RED);
        team.setSecondaryColour(Color.WHITE);
        team.setStadium("Test Stadium");

        assertEquals("Team One", team.getName());
        assertEquals("Team One", team.toString());
        assertEquals(captain, team.getCaptain());
        assertEquals(manager, team.getManager());
        assertEquals(formation, team.getFormation());
        assertEquals(players, team.getPlayers());
        assertTrue(team.getPlayers().containsKey("1"));
        assertEquals(captain, team.getPlayers().get("1"));
        assertEquals(players, team.getFirstTeam());
        assertTrue(team.getFirstTeam().containsKey("1"));
        assertEquals(captain, team.getFirstTeam().get("1"));
        assertEquals(20000000, team.getBudget());
        assertEquals(Color.RED, team.getPrimaryColour());
        assertEquals(Color.WHITE, team.getSecondaryColour());
        assertEquals("Test Stadium", team.getStadium());
        assertEquals(goalkeeper, team.getGoalkeeper());
    }

}

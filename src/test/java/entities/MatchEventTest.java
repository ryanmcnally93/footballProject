package entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;
import people.Footballer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class MatchEventTest {

    @ParameterizedTest
    @CsvSource({
            "home, goal, ryan, 2",
    })
    void testConstructorWithIncorrectParams(String a, String b, String c, int d) {
        Footballer footballer = new Footballer(c, 20);
        MatchEvent event = new MatchEvent(a, b, footballer, d);
        assertNotNull(event);
    }

    @Test
    void testGettersAndSetters() {
        MatchEvent matchEvent = new MatchEvent();
        matchEvent.setHomeOrAway("Home");
        matchEvent.setEventType("Goal");
        matchEvent.setMinute(45);

        Footballer player = new Footballer("Test Person", 18);
        matchEvent.setPlayer(player);

        assertEquals("Home", matchEvent.getHomeOrAway());
        assertEquals("Goal", matchEvent.getEventType());
        assertEquals(45, matchEvent.getMinute());
        assertEquals(player, matchEvent.getPlayer());
    }

}

package entities;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FormationTest {

    private final List<String> defenderPositions = Arrays.asList("RB", "CB1", "CB2", "LB");
    private final List<String> midfielderPositions = Arrays.asList("RM", "CM1", "CM2", "CAM", "CDM", "LM");
    private final List<String> attackerPositions = Arrays.asList("RW", "ST", "LW");

    @ParameterizedTest
    @CsvSource({
            "1, 2, 3",
            "4, 5, 6",
            "7, 8, 9"
    })
    void testConstructorWithIncorrectParams(int a, int b, int c) {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {new Formation(a, b, c);
                });

        System.setOut(originalOut);
        String printed = outContent.toString();

        assertTrue(printed.contains("Wrong number of outfield players were passed to Formation constructor"));
        assertEquals("Wrong number of outfield players were passed to Formation constructor", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "4, 3, 3",
            "4, 4, 2"
    })
    void testConstructorWithCorrectParams(int a, int b, int c) {
        Formation formation = assertDoesNotThrow(() -> new Formation(a, b, c));
        assertNotNull(formation.getPositionOrder());
        assertEquals("GK", formation.getPositionOrder().getFirst());
        List<String> team = formation.getPositionOrder();
        long defenderCount = team.stream().filter(defenderPositions::contains).count();
        long midfielderCount = team.stream().filter(midfielderPositions::contains).count();
        long attackerCount = team.stream().filter(attackerPositions::contains).count();

        assertEquals(a, defenderCount);
        assertEquals(b, midfielderCount);
        assertEquals(c, attackerCount);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 4, 5",
            "2, 4, 4",
            "3, 2, 5",
            "4, 6, 0"
    })
    void testConstructorWithUnrecognisedFormation(int a, int b, int c) {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {new Formation(a, b, c);
                });

        System.setOut(originalOut);
        String printed = outContent.toString();

        assertTrue(printed.contains("Formation not recognised"));
        assertEquals("Formation not recognised", exception.getMessage());
    }

}

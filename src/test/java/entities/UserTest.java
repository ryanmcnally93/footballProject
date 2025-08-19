package entities;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    @ParameterizedTest
    @CsvSource({
            "Person, 30, 20000000",
            "Another, 18, 10000"
    })
    void testConstructorWithCorrectParams(String a, int b, int c) {
        User user = new User(a, b, c);

        assertNotNull(user);
        assertEquals(a, user.getName());
        assertEquals(b, user.getAge());
        assertEquals(c, user.getWealth());
    }

}

package timnekk.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParsedCommandTest {
    @Test
    void nullNameShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new ParsedCommand(null, null));

        assertEquals("Name cannot be null", exception.getMessage());
    }

    @Test
    void emptyNameShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new ParsedCommand("", null));

        assertEquals("Name cannot be empty", exception.getMessage());
    }
}
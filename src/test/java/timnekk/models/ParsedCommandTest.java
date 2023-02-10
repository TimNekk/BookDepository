package timnekk.models;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParsedCommandTest {

    @Test
    void name() {
        ParsedCommand command = new ParsedCommand("name", null);
        assertEquals("name", command.name());
    }

    @Test
    void argumentsIsNull() {
        ParsedCommand command = new ParsedCommand("name", null);
        assertNull(command.arguments());
    }

    @Test
    void arguments() {
        ParsedCommand command = new ParsedCommand("name", List.of("arg1", "arg2"));
        assertEquals(List.of("arg1", "arg2"), command.arguments());
    }

    @Test
    void nameCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> new ParsedCommand(null, null));
    }
}
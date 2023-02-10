package timnekk.input;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import timnekk.exceptions.BadCommandException;
import timnekk.models.ParsedCommand;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleCommandParserTest {
    @Test
    void parseCommandWithNoArgs() throws BadCommandException {
        String input = "test";
        CommandParser parser = new ConsoleCommandParser();
        ParsedCommand command = parser.parseCommand(input);

        assertEquals("test", command.name());
        assertEquals(0, command.arguments().size());
    }

    @ParameterizedTest
    @ValueSource(strings = {"test arg1", "test \"arg1 arg2\"", "/test arg1", "/test \"arg1 arg2\""})
    void parseCommandWithOneArg(String input) throws BadCommandException {
        CommandParser parser = new ConsoleCommandParser();
        ParsedCommand command = parser.parseCommand(input);

        assertEquals("test", command.name());
        assertEquals(1, command.arguments().size());
    }

    @Test
    void parseCommandWithTwoArgs() throws BadCommandException {
        String input = "test arg1 arg2";
        CommandParser parser = new ConsoleCommandParser();
        ParsedCommand command = parser.parseCommand(input);

        assertEquals("test", command.name());
        assertEquals(2, command.arguments().size());
        assertEquals("arg1", command.arguments().get(0));
        assertEquals("arg2", command.arguments().get(1));
    }

    @Test
    void parseCommandWithSlash() throws BadCommandException {
        String input = "/test";
        CommandParser parser = new ConsoleCommandParser();
        ParsedCommand command = parser.parseCommand(input);

        assertEquals("test", command.name());
        assertEquals(0, command.arguments().size());
    }

    @Test
    void parseNullCommand() {
        String input = null;
        CommandParser parser = new ConsoleCommandParser();
        assertThrows(BadCommandException.class, () -> parser.parseCommand(input));
    }

    @Test
    void parseEmptyCommand() {
        String input = "";
        CommandParser parser = new ConsoleCommandParser();
        assertThrows(BadCommandException.class, () -> parser.parseCommand(input));
    }
}
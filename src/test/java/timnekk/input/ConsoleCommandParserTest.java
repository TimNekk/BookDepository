package timnekk.input;

import org.junit.jupiter.api.Test;
import timnekk.exceptions.BadCommandException;
import timnekk.models.ParsedCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConsoleCommandParserTest {
    private static final CommandParser parser = new ConsoleCommandParser();

    @Test
    void parseCommandWithoutSlash() throws BadCommandException {
        ParsedCommand command = parser.parseCommand("test");

        assertEquals("test", command.name());
    }

    @Test
    void parseCommandWithSlash() throws BadCommandException {
        ParsedCommand command = parser.parseCommand("/test1");

        assertEquals("test1", command.name());
    }

    @Test
    void parseCommandWithSingleWordArg() throws BadCommandException {
        ParsedCommand command = parser.parseCommand("test word");

        assertEquals("test", command.name());
        assertEquals(1, command.arguments().size());
        assertEquals("word", command.arguments().get(0));
    }

    @Test
    void parseCommandWithMultipleWordArg() throws BadCommandException {
        ParsedCommand command = parser.parseCommand("test \"multiple words\"");

        assertEquals("test", command.name());
        assertEquals(1, command.arguments().size());
        assertEquals("multiple words", command.arguments().get(0));
    }

    @Test
    void parseCommandWithMoreThanOneArg() throws BadCommandException {
        ParsedCommand command = parser.parseCommand("test arg1 \"arg2\" \"arg3 arg3.1\"");

        assertEquals("test", command.name());
        assertEquals(3, command.arguments().size());
        assertEquals("arg1", command.arguments().get(0));
        assertEquals("arg2", command.arguments().get(1));
        assertEquals("arg3 arg3.1", command.arguments().get(2));
    }

    @Test
    void parseNullCommandShouldThrowBadCommandException() {
        BadCommandException exception = assertThrows(BadCommandException.class, () -> parser.parseCommand(null));
        assertEquals("Command cannot be null", exception.getMessage());
    }

    @Test
    void parseEmptyCommandShouldThrowBadCommandException() {
        BadCommandException exception = assertThrows(BadCommandException.class, () -> parser.parseCommand(""));
        assertEquals("Command cannot be empty", exception.getMessage());
    }
}
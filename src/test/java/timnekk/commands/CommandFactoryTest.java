package timnekk.commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import timnekk.exceptions.BadCommandException;
import timnekk.models.ParsedCommand;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandFactoryTest {
    private final CommandFactory commandFactory = new CommandFactory(null, null);

    @ParameterizedTest
    @ValueSource(strings = {"get", "put"})
    void creatingTakingCommandOnlyWithOneOrTwoParamsShouldNotThrowBadCommandException(String commandName)
            throws BadCommandException {
        Command command = commandFactory.createCommand(new ParsedCommand(commandName, List.of("1")));
        assertEquals(TransferBookCommand.class, command.getClass());

        command = commandFactory.createCommand(new ParsedCommand(commandName, List.of("1", "2")));
        assertEquals(TransferBookCommand.class, command.getClass());

        assertThrows(BadCommandException.class,
                () -> commandFactory.createCommand(new ParsedCommand(commandName, List.of())));
        assertThrows(BadCommandException.class,
                () -> commandFactory.createCommand(new ParsedCommand(commandName, List.of("1", "2", "3"))));
    }

    @ParameterizedTest
    @ValueSource(strings = {"all", "list"})
    void creatingGettingAllCommandOnlyWithoutParamsShouldNotThrowBadCommandException(String commandName)
            throws BadCommandException {
        Command command = commandFactory.createCommand(new ParsedCommand(commandName, List.of()));
        assertEquals(GetAllBooksCommand.class, command.getClass());

        assertThrows(BadCommandException.class,
                () -> commandFactory.createCommand(new ParsedCommand(commandName, List.of("1"))));
    }

    @Test
    void creatingExitCommandOnlyWithoutParamsShouldNotThrowBadCommandException()
            throws BadCommandException {
        String commandName = "exit";

        Command command = commandFactory.createCommand(new ParsedCommand(commandName, List.of()));
        assertEquals(ExitCommand.class, command.getClass());

        assertThrows(BadCommandException.class,
                () -> commandFactory.createCommand(new ParsedCommand(commandName, List.of("1"))));
    }
}
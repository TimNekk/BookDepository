package timnekk.commands;

import timnekk.input.ParsedCommand;
import timnekk.storage.BookStorage;

public class CommandFactory {
    private final BookStorage storage;

    public CommandFactory(BookStorage storage) {
        this.storage = storage;
    }

    public Command createCommand(ParsedCommand parsedCommand) {
        switch (parsedCommand.name()) {
            case "get":
                verifyArguments(parsedCommand, 1);
                return new GetBookCommand(storage, parsedCommand.arguments().get(0));
            default:
                // TODO: Handle this better
                throw new IllegalArgumentException("Unknown command: " + parsedCommand.name());
        }
    }

    private void verifyArguments(ParsedCommand parsedCommand, int expectedCount) {
        if (parsedCommand.arguments().size() != expectedCount) {
            throw new IllegalArgumentException("Invalid number of arguments! " +
                    "Expected " + expectedCount + " but got " + parsedCommand.arguments().size());
        }
    }
}

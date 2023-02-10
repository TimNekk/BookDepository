package timnekk.commands;

import timnekk.exceptions.BadCommandException;
import timnekk.models.ParsedCommand;
import timnekk.models.Range;
import timnekk.storage.BookStorage;

public class CommandFactory {
    private final BookStorage library;
    private final BookStorage bundle;

    public CommandFactory(BookStorage library, BookStorage bundle) {
        this.library = library;
        this.bundle = bundle;
    }

    public Command createCommand(ParsedCommand parsedCommand) throws BadCommandException {
        switch (parsedCommand.name()) {
            case "get" -> {
                verifyArguments(parsedCommand, new Range(1, 2));
                return new TransferBookCommand(library, bundle, parsedCommand.arguments().get(0),
                        parsedCommand.arguments().size() == 2 ? parsedCommand.arguments().get(1) : null);
            }
            case "all" -> {
                verifyArguments(parsedCommand, 0);
                return new GetAllBooksCommand(library);
            }
            case "list" -> {
                verifyArguments(parsedCommand, 0);
                return new GetAllBooksCommand(bundle);
            }
            case "put" -> {
                verifyArguments(parsedCommand, new Range(1, 2));
                return new TransferBookCommand(bundle, library, parsedCommand.arguments().get(0),
                        parsedCommand.arguments().size() == 2 ? parsedCommand.arguments().get(1) : null);
            }
            case "exit" -> {
                verifyArguments(parsedCommand, 0);
                return new ExitCommand(bundle, library);
            }
            default -> throw new BadCommandException("Unknown command: " + parsedCommand.name());
        }
    }

    private void verifyArguments(ParsedCommand parsedCommand, Range expectedRange) throws BadCommandException {
        if (!expectedRange.contains(parsedCommand.arguments().size())) {
            throw new BadCommandException("Invalid number of arguments! " +
                    "Expected from " + expectedRange.start() + " to " + expectedRange.end() +
                    " but got " + parsedCommand.arguments().size());
        }
    }

    private void verifyArguments(ParsedCommand parsedCommand, int expectedCount) throws BadCommandException {
        if (parsedCommand.arguments().size() != expectedCount) {
            throw new BadCommandException("Invalid number of arguments! " +
                    "Expected " + expectedCount + " but got " + parsedCommand.arguments().size());
        }
    }
}

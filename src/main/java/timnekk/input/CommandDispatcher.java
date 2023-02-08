package timnekk.input;

import timnekk.commands.Command;
import timnekk.commands.CommandFactory;

public final class CommandDispatcher {
    private final CommandReader commandReader;
    private final CommandParser commandParser;

    public CommandDispatcher(CommandProviderFactory commandProviderFactory) {
        commandParser = commandProviderFactory.createCommandParser();
        commandReader = commandProviderFactory.createCommandReader();
    }

    public Command getCommand() {
        String command = commandReader.readCommand();
        ParsedCommand parsedCommand = commandParser.parseCommand(command);

        CommandFactory.createCommand(parsedCommand);
    }
}

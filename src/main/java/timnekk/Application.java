package timnekk;

import timnekk.commands.Command;
import timnekk.commands.CommandFactory;
import timnekk.input.*;
import timnekk.output.OutputWriter;

public class Application implements AutoCloseable {
    private final CommandParser commandParser;
    private final CommandReader commandReader;
    private final CommandFactory commandFactory;
    private final OutputWriter outputWriter;

    public Application(ApplicationSettings settings) {
        commandReader = settings.commandProviderFactory().createCommandReader();
        commandParser = settings.commandProviderFactory().createCommandParser();
        commandFactory = new CommandFactory(settings.bookStorage());
        outputWriter = settings.outputWriter();
    }

    public void run() {
        ParsedCommand parsedCommand = commandParser.parseCommand(commandReader.readCommand());
        Command command = commandFactory.createCommand(parsedCommand);
        command.execute();

        if (command.hasOutput()) {
            outputWriter.write(command.getOutput());
        }
    }

    @Override
    public void close() {
        commandReader.close();
    }
}

package timnekk;

import timnekk.commands.Command;
import timnekk.commands.CommandFactory;
import timnekk.exceptions.BadCommandException;
import timnekk.exceptions.CommandExecutionException;
import timnekk.input.CommandParser;
import timnekk.input.CommandReader;
import timnekk.models.ParsedCommand;
import timnekk.output.OutputWriter;

public final class Application implements AutoCloseable {
    private final CommandParser commandParser;
    private final CommandReader commandReader;
    private final CommandFactory commandFactory;
    private final OutputWriter outputWriter;

    public Application(ApplicationSettings settings) {
        commandReader = settings.commandProviderFactory().createCommandReader();
        commandParser = settings.commandProviderFactory().createCommandParser();
        commandFactory = new CommandFactory(settings.bookLibrary(), settings.bookBundle());
        outputWriter = settings.outputWriter();
    }

    public void run() {
        outputWriter.write("Welcome to the book Depository! Type commands below.");

        while (true) {
            processCommandFlow();
        }
    }

    private void processCommandFlow() {
        Command command;

        try {
            ParsedCommand parsedCommand = commandParser.parseCommand(commandReader.readCommand());
            command = commandFactory.createCommand(parsedCommand);
        } catch (BadCommandException e) {
            outputWriter.write(e.getMessage());
            return;
        }

        try {
            command.execute();
        } catch (CommandExecutionException e) {
            outputWriter.write(e.getMessage());
            return;
        }

        if (command.isFinishProgram()) {
            System.exit(0);
        }

        if (command.hasOutput()) {
            outputWriter.write(command.getOutput());
        }
    }

    @Override
    public void close() {
        commandReader.close();
    }
}

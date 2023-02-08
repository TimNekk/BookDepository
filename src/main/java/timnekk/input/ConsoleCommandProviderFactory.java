package timnekk.input;

public class ConsoleCommandProviderFactory implements CommandProviderFactory {
    @Override
    public CommandReader createCommandReader() {
        return new ConsoleCommandReader();
    }

    @Override
    public CommandParser createCommandParser() {
        return new ConsoleCommandParser();
    }
}

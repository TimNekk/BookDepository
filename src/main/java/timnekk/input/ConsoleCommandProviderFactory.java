package timnekk.input;

public final class ConsoleCommandProviderFactory implements CommandProviderFactory {
    @Override
    public CommandReader createCommandReader() {
        return new ConsoleCommandReader();
    }

    @Override
    public CommandParser createCommandParser() {
        return new ConsoleCommandParser();
    }
}

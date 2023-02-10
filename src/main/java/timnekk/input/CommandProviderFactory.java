package timnekk.input;

public interface CommandProviderFactory {
    CommandReader createCommandReader();
    CommandParser createCommandParser();
}

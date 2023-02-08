package timnekk.input;

public class ConsoleCommandParser implements CommandParser {

    @Override
    public ParsedCommand parseCommand(String command) {
        if (command == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
        return new ParsedCommand(command, null);
    }
}

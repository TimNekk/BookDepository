package timnekk.input;

import timnekk.exceptions.BadCommandException;
import timnekk.models.ParsedCommand;

public interface CommandParser {
    ParsedCommand parseCommand(String command) throws BadCommandException;
}

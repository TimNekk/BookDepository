package timnekk.input;

import timnekk.exceptions.BadCommandException;
import timnekk.models.ParsedCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleCommandParser implements CommandParser {
    private static final Pattern commandPattern = Pattern.compile("\"[^\"]+\"|[^ \"]+");

    @Override
    public ParsedCommand parseCommand(String command) throws BadCommandException {
        if (command == null) {
            throw new BadCommandException("Command cannot be null");
        }

        Matcher matcher = commandPattern.matcher(command);
        List<String> parts = new ArrayList<>();
        while (matcher.find()) {
            parts.add(removeSlashesAndQuotes(matcher.group()));
        }

        if (parts.isEmpty()) {
            throw new BadCommandException("Command cannot be empty");
        }

        String name = parts.get(0).toLowerCase();
        List<String> arguments = parts.subList(1, parts.size());

        return new ParsedCommand(name, arguments);
    }

    private static String removeSlashesAndQuotes(String commands) {
        return commands
                .replace("\"", "")
                .replace("/", "");
    }
}

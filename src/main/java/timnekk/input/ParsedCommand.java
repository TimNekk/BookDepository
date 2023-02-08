package timnekk.input;

import java.util.List;

public record ParsedCommand(String name, List<String> arguments) {
    public ParsedCommand {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
    }
}

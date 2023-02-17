package timnekk.models;

import java.util.List;

public record ParsedCommand(String name, List<String> arguments) {
    public ParsedCommand {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }

        if ("".equals(name)) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}

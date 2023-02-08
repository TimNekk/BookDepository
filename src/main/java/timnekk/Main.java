package timnekk;

import timnekk.input.ConsoleCommandProviderFactory;
import timnekk.output.ConsoleOutputWriter;
import timnekk.storage.JsonBookStorage;

public class Main {
    public static void main(String[] args) {
        ApplicationSettings settings = new ApplicationSettings(
                new ConsoleCommandProviderFactory(),
                new JsonBookStorage(),
                new ConsoleOutputWriter()
        );

        try (Application app = new Application(settings)) {
            app.run();
        }
    }
}

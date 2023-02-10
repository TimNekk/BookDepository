package timnekk;

import timnekk.input.ConsoleCommandProviderFactory;
import timnekk.output.ConsoleOutputWriter;
import timnekk.storage.MemoryBookBundle;
import timnekk.storage.JsonBookLibrary;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        ApplicationSettings settings = new ApplicationSettings(
                new ConsoleCommandProviderFactory(),
                new JsonBookLibrary(new File("src/main/resources/books.json").toPath()),
                new MemoryBookBundle(),
                new ConsoleOutputWriter()
        );

        try (Application app = new Application(settings)) {
            app.run();
        }
    }
}

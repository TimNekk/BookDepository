package timnekk.input;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleCommandReaderTest {
    @AfterAll
    static void tearDown() {
        System.setIn(System.in);
    }

    @Test
    void read() {
        String input = "test";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        try (CommandReader reader = new ConsoleCommandReader()) {
            assertEquals(input, reader.readCommand());
        }
    }

    @Test
    void readEmptyWithSpaces() {
        String input = "   ";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        try (CommandReader reader = new ConsoleCommandReader()) {
            assertEquals(input, reader.readCommand());
        }
    }
}
package timnekk.output;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleOutputWriterTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void write() {
        OutputWriter writer = new ConsoleOutputWriter();
        writer.write("test");
        assertEquals("test" + System.lineSeparator(), outputStreamCaptor.toString());
    }

    @Test
    void writeNull() {
        OutputWriter writer = new ConsoleOutputWriter();
        writer.write(null);
        assertEquals("null" + System.lineSeparator(), outputStreamCaptor.toString());
    }
}
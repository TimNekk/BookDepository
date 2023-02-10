package timnekk.output;

public final class ConsoleOutputWriter implements OutputWriter {
    @Override
    public void write(String output) {
        System.out.println(output);
    }
}

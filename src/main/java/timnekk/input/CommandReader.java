package timnekk.input;

public abstract class CommandReader implements AutoCloseable {
    public abstract String readCommand();

    @Override
    public abstract void close();
}

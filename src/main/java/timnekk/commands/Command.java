package timnekk.commands;

public abstract class Command {
    protected String output = null;

    public abstract void execute();

    public boolean hasOutput() {
        return output != null;
    }

    public String getOutput() {
        return output;
    }
}

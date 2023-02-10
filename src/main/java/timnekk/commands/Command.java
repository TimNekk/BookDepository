package timnekk.commands;

import timnekk.exceptions.CommandExecutionException;

public abstract class Command {
    private String output = null;

    public abstract void execute() throws CommandExecutionException;

    public boolean hasOutput() {
        return output != null;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}

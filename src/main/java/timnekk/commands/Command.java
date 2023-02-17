package timnekk.commands;

import timnekk.exceptions.CommandExecutionException;

public abstract class Command {
    private String output = null;
    private Boolean finishProgram = false;

    public abstract void execute() throws CommandExecutionException;

    public boolean hasOutput() {
        return output != null;
    }

    public String getOutput() {
        return output;
    }

    protected void setOutput(String output) {
        this.output = output;
    }

    protected void setFinishProgram(Boolean value) {
        finishProgram = value;
    }

    public Boolean isFinishProgram() {
        return finishProgram;
    }
}

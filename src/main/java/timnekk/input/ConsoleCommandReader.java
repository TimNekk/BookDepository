package timnekk.input;

import java.util.Scanner;

public final class ConsoleCommandReader extends CommandReader {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String readCommand() {
        return scanner.nextLine();
    }

    @Override
    public void close() {
        scanner.close();
    }
}

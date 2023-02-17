package timnekk.commands;

import timnekk.exceptions.CommandExecutionException;
import timnekk.exceptions.StorageException;
import timnekk.storage.BookStorage;
import timnekk.models.Book;

public final class ExitCommand extends Command {
    private final BookStorage bundle;
    private final BookStorage library;

    public ExitCommand(BookStorage bundle, BookStorage library) {
        this.bundle = bundle;
        this.library = library;
    }

    @Override
    public void execute() throws CommandExecutionException {
        try {
            for (Book book : bundle.getBooks()) {
                library.addBook(book);
            }
        } catch (StorageException e) {
            throw new CommandExecutionException(e);
        }

        setFinishProgram(true);
    }
}

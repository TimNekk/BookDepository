package timnekk.commands;

import timnekk.exceptions.CommandExecutionException;
import timnekk.exceptions.StorageException;
import timnekk.models.Book;
import timnekk.storage.BookStorage;

public final class TransferBookCommand extends Command {
    private final BookStorage fromStorage;
    private final BookStorage toStorage;
    private final String title;
    private final String author;

    public TransferBookCommand(BookStorage fromStorage, BookStorage toStorage, String title, String author) {
        this.fromStorage = fromStorage;
        this.toStorage = toStorage;
        this.title = title;
        this.author = author;
    }

    @Override
    public void execute() throws CommandExecutionException {
        Book book;

        try {
            if (author == null) {
                int count = fromStorage.countAuthorsWithTitle(title);

                if (count == 0) {
                    setOutput("Book with this title not found!");
                    return;
                }

                if (count > 1) {
                    setOutput("There are more than one book with this title. Please specify author.");
                    return;
                }
            }

            book = fromStorage.takeBook(title, author);

            if (book == null) {
                setOutput("Book with this title and author not found!");
                return;
            }

            toStorage.addBook(book);
        } catch (StorageException e) {
            throw new CommandExecutionException(e);
        }

        setOutput("Book successfully transferred!");
    }
}

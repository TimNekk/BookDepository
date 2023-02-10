package timnekk.commands;

import timnekk.exceptions.CommandExecutionException;
import timnekk.exceptions.StorageException;
import timnekk.models.Book;
import timnekk.storage.BookStorage;

import java.util.List;

public final class GetAllBooksCommand extends Command {
    private final BookStorage storage;

    public GetAllBooksCommand(BookStorage storage) {
        this.storage = storage;
    }

    @Override
    public void execute() throws CommandExecutionException {
        List<Book> books;
        try {
            books = storage.getBooks();
        } catch (StorageException e) {
            throw new CommandExecutionException(e);
        }

        if (books.isEmpty()) {
            setOutput("No books found");
            return;
        }

        setOutput(formatBooks(books));
    }

    private static String formatBooks(List<Book> books) {
        StringBuilder output = new StringBuilder();

        output.append("Total: ").append(books.size()).append(" books\n");

        int maxlength = Math.max(books.stream().mapToInt(b -> b.author().length()).max().orElse(0), 10);
        for (Book book : books) {
            output.append(String.format("%-" + maxlength + "s", book.author()))
                    .append(" │ \"")
                    .append(book.title())
                    .append("\", ")
                    .append(book.year())
                    .append(" y.\n");
        }

        output.deleteCharAt(output.length() - 1);

        return output.toString();
    }
}

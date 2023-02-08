package timnekk.commands;

import timnekk.storage.BookStorage;

public class GetBookCommand extends Command {
    private final BookStorage storage;
    private final String title;

    public GetBookCommand(BookStorage storage, String title) {
        this.storage = storage;
        this.title = title;
    }

    @Override
    public void execute() {
        System.out.println(storage.getBook(title));
        output = storage.getBook(title).toString();
    }
}

package timnekk.storage;

import java.util.List;

public interface BookStorage {
    Book getBook(String title);
    void addBook(Book book);

    List<Book> getAllBooks();
}

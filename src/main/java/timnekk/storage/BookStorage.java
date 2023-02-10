package timnekk.storage;

import timnekk.exceptions.StorageException;
import timnekk.models.Book;

import java.util.List;

public interface BookStorage {
    List<Book> getBooks() throws StorageException;

    Book takeBook(String title, String author) throws StorageException;

    int countAuthorsWithTitle(String title) throws StorageException;

    void addBook(Book book) throws StorageException;
}

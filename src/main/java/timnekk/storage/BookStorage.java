package timnekk.storage;

import timnekk.exceptions.StorageException;
import timnekk.models.Book;

import java.util.List;

public interface BookStorage {
    List<Book> getBooks() throws StorageException;

    /**
     * Finds book with passed title and author, removes it from storage and returns it.
     * If author is null, any book with matching title will be returned.
     *
     * @param title  Title of book
     * @param author Author of book
     * @return Book with passed title and author
     * @throws StorageException If some storage exception happens
     */
    Book takeBook(String title, String author) throws StorageException;

    int countAuthorsWithTitle(String title) throws StorageException;

    void addBook(Book book) throws StorageException;
}

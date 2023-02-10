package timnekk.storage;

import timnekk.models.Book;

import java.util.ArrayList;
import java.util.List;

public class MemoryBookBundle implements BookStorage {
    private final List<Book> books = new ArrayList<>();

    @Override
    public Book takeBook(String title, String author) {
        return books.stream()
                .filter(book -> book.title().equals(title) && (author == null || book.author().equals(author)))
                .findFirst()
                .map(book -> {
                    books.remove(book);
                    return book;
                })
                .orElse(null);
    }

    @Override
    public List<Book> getBooks() {
        return books;
    }

    @Override
    public int countAuthorsWithTitle(String title) {
        return (int) books.stream()
                .filter(book -> book.title().equals(title))
                .map(Book::author)
                .distinct()
                .count();
    }

    @Override
    public void addBook(Book book) {
        books.add(book);
    }
}

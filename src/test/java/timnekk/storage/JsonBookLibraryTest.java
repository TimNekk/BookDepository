package timnekk.storage;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import timnekk.exceptions.StorageException;
import timnekk.models.Book;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class JsonBookLibraryTest {
    private static final Path backup = new File("src/test/resources/books.json.bak").toPath();
    private static final Path file = new File("src/test/resources/books.json").toPath();
    private static BookStorage library;

    @BeforeEach
    void setUp() throws IOException {
        if (Files.exists(file)) {
            Files.delete(file);
        }

        Files.copy(backup, file);
        library = new JsonBookLibrary(file);
    }

    @AfterAll
    static void tearDown() throws IOException {
        if (Files.exists(file)) {
            Files.delete(file);
        }
    }

    @Test
    void gettingBooksShouldReturnAllBooksFromJsonFile() throws StorageException {
        List<Book> books = library.getBooks();

        assertEquals(5, books.size());

        assertEquals("Title 1", books.get(0).title());
        assertEquals("Author 1", books.get(0).author());

        assertEquals("Title 1", books.get(1).title());
        assertEquals("Author 2", books.get(1).author());

        assertEquals("Title 2", books.get(2).title());
        assertEquals("Author 2", books.get(2).author());

        assertEquals("Title 2", books.get(3).title());
        assertEquals("Author 2", books.get(3).author());

        assertEquals("Title 3", books.get(4).title());
        assertEquals("Author 3", books.get(4).author());
    }

    @Test
    void addingBookThatNotYetContainsShouldPutBookInLibrary() throws StorageException {
        String title = "Title 6";
        String author = "Author 6";
        library.addBook(new Book(title, author, 2019));

        assertEquals(6, library.getBooks().size());
        assertEquals(title, library.getBooks().get(5).title());
        assertEquals(author, library.getBooks().get(5).author());
    }

    @Test
    void addingBookThatAlreadyContainsShouldPutBookInLibrary() throws StorageException {
        String title = "Title 1";
        String author = "Author 1";
        library.addBook(new Book(title, author, 2019));

        assertEquals(6, library.getBooks().size());
        assertEquals(title, library.getBooks().get(5).title());
        assertEquals(author, library.getBooks().get(5).author());
    }

    @Test
    void takingBookThatContainsShouldReturnBookAndRemoveItFromStorage() throws StorageException {
        String title = "Title 3";
        String author = "Author 3";
        Book book = library.takeBook(title, author);

        assertEquals(title, book.title());
        assertEquals(author, book.author());

        assertEquals(4, library.getBooks().size());
        assertNull(library.takeBook(title, author));
    }

    @Test
    void takingBookThatNotContainsShouldReturnNull() throws StorageException {
        assertNull(library.takeBook("Title 5", "Author 1"));
        assertNull(library.takeBook("Title 1", "Author 5"));
        assertEquals(5, library.getBooks().size());
    }

    @Test
    void takingBookWithNullTitleShouldReturnNull() throws StorageException {
        assertNull(library.takeBook(null, "Author 1"));
        assertEquals(5, library.getBooks().size());
    }

    @Test
    void takingBookWithNullAuthorShouldReturnAnyBookWithSuckTitle() throws StorageException {
        String title = "Title 1";
        String author = null;
        Book book = library.takeBook(title, author);

        assertEquals(title, book.title());
        assertEquals(4, library.getBooks().size());
    }

    @Test
    void countingAuthorsWithTitleShouldReturnCorrectNumber() throws StorageException {
        assertEquals(1, library.countAuthorsWithTitle("Title 3"));
        assertEquals(2, library.countAuthorsWithTitle("Title 1"));
    }
}
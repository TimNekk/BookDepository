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

import static org.junit.jupiter.api.Assertions.*;

class JsonBookLibraryTest {
    private static final Path backup = new File("src/test/resources/books.json.bak").toPath();
    private static final Path file = new File("src/test/resources/books.json").toPath();

    @BeforeEach
    void setUp() throws IOException {
        if (Files.exists(file)) {
            Files.delete(file);
        }

        Files.copy(backup, file);
    }

    @AfterAll
    static void tearDown() throws IOException {
        if (Files.exists(file)) {
            Files.delete(file);
        }
    }
    @Test
    void booksCount() throws StorageException {
        BookStorage library = new JsonBookLibrary(file);

        assertEquals(5, library.getBooks().size());
    }

    @Test
    void firstBookContent() throws StorageException {
        BookStorage library = new JsonBookLibrary(file);

        assertEquals("Title 1", library.getBooks().get(0).title());
        assertEquals("Author 1", library.getBooks().get(0).author());
    }

    @Test
    void lastBookContent() throws StorageException {
        BookStorage library = new JsonBookLibrary(file);

        assertEquals("Title 3", library.getBooks().get(4).title());
        assertEquals("Author 3", library.getBooks().get(4).author());
    }

    @Test
    void addBook() throws StorageException {
        BookStorage library = new JsonBookLibrary(file);

        String title = "Title 6";
        String author = "Author 6";
        library.addBook(new Book(title, author, 2019));

        assertEquals(6, library.getBooks().size());
        assertEquals(title, library.getBooks().get(5).title());
        assertEquals(author, library.getBooks().get(5).author());
    }

    @Test
    void takeBook() throws StorageException {
        BookStorage library = new JsonBookLibrary(file);

        String title = "Title 3";
        String author = "Author 3";
        Book book = library.takeBook(title, author);

        assertEquals(title, book.title());
        assertEquals(author, book.author());
        assertEquals(4, library.getBooks().size());
        assertNull(library.takeBook(title, author));
    }

    @Test
    void takeBookWithoutAuthor() throws StorageException {
        BookStorage library = new JsonBookLibrary(file);

        String title = "Title 3";
        Book book = library.takeBook(title, null);

        assertEquals(title, book.title());
        assertEquals(4, library.getBooks().size());
        assertNull(library.takeBook(title, null));
    }

    @Test
    void takeBookWithoutAuthorAndTitle() throws StorageException {
        BookStorage library = new JsonBookLibrary(file);

        assertNull(library.takeBook(null, null));
    }

    @Test
    void takeBookWithSameTitleButDifferentAuthor() throws StorageException {
        BookStorage library = new JsonBookLibrary(file);

        String title = "Title 1";
        Book book = library.takeBook(title, null);

        assertEquals(title, book.title());
        assertEquals("Author 1", book.author());
        assertEquals(4, library.getBooks().size());

        Book book2 = library.takeBook(title, null);

        assertEquals(title, book2.title());
        assertEquals("Author 2", book2.author());
        assertEquals(3, library.getBooks().size());
    }

    @Test
    void takeTwoBooksThatAreMoreThanOne() throws StorageException {
        BookStorage library = new JsonBookLibrary(file);

        String title = "Title 2";
        String author = "Author 2";
        Book book = library.takeBook(title, author);

        assertEquals(title, book.title());
        assertEquals(author, book.author());
        assertEquals(4, library.getBooks().size());

        Book book2 = library.takeBook(title, author);

        assertEquals(title, book2.title());
        assertEquals(author, book2.author());
        assertEquals(3, library.getBooks().size());
    }

    @Test
    void takeTwoBooksThatAreOnlyOne() throws StorageException {
        BookStorage library = new JsonBookLibrary(file);

        String title = "Title 3";
        String author = "Author 3";
        Book book = library.takeBook(title, author);

        assertEquals(title, book.title());
        assertEquals(author, book.author());
        assertEquals(4, library.getBooks().size());

        Book book2 = library.takeBook(title, author);

        assertNull(book2);
        assertEquals(4, library.getBooks().size());
    }

    @Test
    void takeBookThatDoesNotExist() throws StorageException {
        BookStorage library = new JsonBookLibrary(file);

        Book book = library.takeBook("Title 6", "Author 6");

        assertNull(book);
        assertEquals(5, library.getBooks().size());
    }

    @Test
    void addBookThatAlreadyExists() throws StorageException {
        BookStorage library = new JsonBookLibrary(file);

        library.addBook(new Book("Title 1", "Author 1", 2019));

        assertEquals(6, library.getBooks().size());
    }

    @Test
    void addBookWithoutTitle() {
        BookStorage library = new JsonBookLibrary(file);

        assertThrows(StorageException.class, () -> library.addBook(new Book(null, "Author 1", 2019)));
    }

    @Test
    void addBookWithoutAuthor() {
        BookStorage library = new JsonBookLibrary(file);

        assertThrows(StorageException.class, () -> library.addBook(new Book("Title 1", null, 2019)));
    }

    @Test
    void addBookWithoutTitleAndAuthor() {
        BookStorage library = new JsonBookLibrary(file);

        assertThrows(StorageException.class, () -> library.addBook(new Book(null, null, 2019)));
    }
}
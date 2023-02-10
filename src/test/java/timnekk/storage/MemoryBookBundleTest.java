package timnekk.storage;

import org.junit.jupiter.api.Test;
import timnekk.exceptions.StorageException;
import timnekk.models.Book;

import static org.junit.jupiter.api.Assertions.*;

class MemoryBookBundleTest {
    @Test
    void sizeAfterAddingOneBook() throws StorageException {
        BookStorage bundle = new MemoryBookBundle();

        Book book = new Book("name", "author", 2023);

        bundle.addBook(book);

        assertEquals(1, bundle.getBooks().size());
    }

    @Test
    void sizeWithoutBooks() throws StorageException {
        BookStorage bundle = new MemoryBookBundle();

        assertEquals(0, bundle.getBooks().size());
    }

    @Test
    void containsAddedBook() throws StorageException {
        BookStorage bundle = new MemoryBookBundle();

        Book book = new Book("name", "author", 2023);

        bundle.addBook(book);

        assertTrue(bundle.getBooks().contains(book));
    }

    @Test
    void doesNotContainNotAddedBook() throws StorageException {
        BookStorage bundle = new MemoryBookBundle();

        Book book = new Book("name", "author", 2023);

        assertFalse(bundle.getBooks().contains(book));
    }

    @Test
    void containsAddedBooks() throws StorageException {
        BookStorage bundle = new MemoryBookBundle();

        Book book1 = new Book("name1", "author1", 2023);
        Book book2 = new Book("name2", "author2", 2023);
        Book book3 = new Book("name3", "author3", 2023);

        bundle.addBook(book1);
        bundle.addBook(book2);
        bundle.addBook(book3);

        assertTrue(bundle.getBooks().contains(book1));
        assertTrue(bundle.getBooks().contains(book2));
        assertTrue(bundle.getBooks().contains(book3));
    }

    @Test
    void takeOneBook() throws StorageException {
        BookStorage bundle = new MemoryBookBundle();

        Book book1 = new Book("name1", "author1", 2023);
        Book book2 = new Book("name2", "author2", 2023);
        Book book3 = new Book("name3", "author3", 2023);

        bundle.addBook(book1);
        bundle.addBook(book2);
        bundle.addBook(book3);

        bundle.takeBook(book2.title(), book2.author());

        assertTrue(bundle.getBooks().contains(book1));
        assertFalse(bundle.getBooks().contains(book2));
        assertTrue(bundle.getBooks().contains(book3));
    }

    @Test
    void takeAllBooks() throws StorageException {
        BookStorage bundle = new MemoryBookBundle();

        Book book1 = new Book("name1", "author1", 2023);
        Book book2 = new Book("name2", "author2", 2023);
        Book book3 = new Book("name3", "author3", 2023);

        bundle.addBook(book1);
        bundle.addBook(book2);
        bundle.addBook(book3);

        bundle.takeBook(book1.title(), book1.author());
        bundle.takeBook(book2.title(), book2.author());
        bundle.takeBook(book3.title(), book3.author());

        assertFalse(bundle.getBooks().contains(book1));
        assertFalse(bundle.getBooks().contains(book2));
        assertFalse(bundle.getBooks().contains(book3));
    }

    @Test
    void takeNotAddedBook() throws StorageException {
        BookStorage bundle = new MemoryBookBundle();

        Book book1 = new Book("name1", "author1", 2023);
        Book book2 = new Book("name2", "author2", 2023);
        Book book3 = new Book("name3", "author3", 2023);

        bundle.addBook(book1);
        bundle.addBook(book2);

        bundle.takeBook(book3.title(), book3.author());

        assertTrue(bundle.getBooks().contains(book1));
        assertTrue(bundle.getBooks().contains(book2));
        assertFalse(bundle.getBooks().contains(book3));
    }

    @Test
    void takeBookWithWrongAuthor() throws StorageException {
        BookStorage bundle = new MemoryBookBundle();

        Book book1 = new Book("name1", "author1", 2023);
        Book book2 = new Book("name2", "author2", 2023);
        Book book3 = new Book("name3", "author3", 2023);

        bundle.addBook(book1);
        bundle.addBook(book2);
        bundle.addBook(book3);

        bundle.takeBook(book2.title(), book3.author());

        assertTrue(bundle.getBooks().contains(book1));
        assertTrue(bundle.getBooks().contains(book2));
        assertTrue(bundle.getBooks().contains(book3));
    }

    @Test
    void takeBookWithWrongTitle() throws StorageException {
        BookStorage bundle = new MemoryBookBundle();

        Book book1 = new Book("name1", "author1", 2023);
        Book book2 = new Book("name2", "author2", 2023);
        Book book3 = new Book("name3", "author3", 2023);

        bundle.addBook(book1);
        bundle.addBook(book2);
        bundle.addBook(book3);

        bundle.takeBook(book3.title(), book2.author());

        assertTrue(bundle.getBooks().contains(book1));
        assertTrue(bundle.getBooks().contains(book2));
        assertTrue(bundle.getBooks().contains(book3));
    }

    @Test
    void takeBookWithWrongTitleAndAuthor() throws StorageException {
        BookStorage bundle = new MemoryBookBundle();

        Book book1 = new Book("name1", "author1", 2023);
        Book book2 = new Book("name2", "author2", 2023);
        Book book3 = new Book("name3", "author3", 2023);

        bundle.addBook(book1);
        bundle.addBook(book2);
        bundle.addBook(book3);

        bundle.takeBook(book3.title(), book1.author());

        assertTrue(bundle.getBooks().contains(book1));
        assertTrue(bundle.getBooks().contains(book2));
        assertTrue(bundle.getBooks().contains(book3));
    }

    @Test
    void takeBookWithNullTitle() throws StorageException {
        BookStorage bundle = new MemoryBookBundle();

        Book book1 = new Book("name1", "author1", 2023);
        Book book2 = new Book("name2", "author2", 2023);
        Book book3 = new Book("name3", "author3", 2023);

        bundle.addBook(book1);
        bundle.addBook(book2);
        bundle.addBook(book3);

        bundle.takeBook(null, book1.author());

        assertTrue(bundle.getBooks().contains(book1));
        assertTrue(bundle.getBooks().contains(book2));
        assertTrue(bundle.getBooks().contains(book3));
    }

    @Test
    void takeBookWithNullAuthor() throws StorageException {
        BookStorage bundle = new MemoryBookBundle();

        Book book1 = new Book("name1", "author1", 2023);
        Book book2 = new Book("name2", "author2", 2023);
        Book book3 = new Book("name3", "author3", 2023);

        bundle.addBook(book1);
        bundle.addBook(book2);
        bundle.addBook(book3);

        bundle.takeBook(book1.title(), null);

        assertFalse(bundle.getBooks().contains(book1));
        assertTrue(bundle.getBooks().contains(book2));
        assertTrue(bundle.getBooks().contains(book3));
    }

    @Test
    void takeBookWithNullTitleAndAuthor() throws StorageException {
        BookStorage bundle = new MemoryBookBundle();

        Book book1 = new Book("name1", "author1", 2023);
        Book book2 = new Book("name2", "author2", 2023);
        Book book3 = new Book("name3", "author3", 2023);

        bundle.addBook(book1);
        bundle.addBook(book2);
        bundle.addBook(book3);

        bundle.takeBook(null, null);

        assertTrue(bundle.getBooks().contains(book1));
        assertTrue(bundle.getBooks().contains(book2));
        assertTrue(bundle.getBooks().contains(book3));
    }
}
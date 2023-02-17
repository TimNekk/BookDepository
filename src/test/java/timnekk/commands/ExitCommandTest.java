package timnekk.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import timnekk.exceptions.CommandExecutionException;
import timnekk.exceptions.StorageException;
import timnekk.models.Book;
import timnekk.storage.BookStorage;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class ExitCommandTest {
    private BookStorage mockLibrary;
    private BookStorage mockBundle;

    @BeforeEach
    void setUp() {
        mockLibrary = mock(BookStorage.class);
        mockBundle = mock(BookStorage.class);
    }

    @Test
    void exitingShouldMoveAllBooksFromBundleToLibrary() throws CommandExecutionException, StorageException {
        List<Book> books = List.of(
                new Book("Title 1", "Author 1", 2000),
                new Book("Title 2", "Author 2", 2001)
        );
        when(mockBundle.getBooks()).thenReturn(books);

        Command command = new ExitCommand(mockBundle, mockLibrary);
        command.execute();

        verify(mockLibrary, times(1)).addBook(books.get(0));
        verify(mockLibrary, times(1)).addBook(books.get(1));
    }
}
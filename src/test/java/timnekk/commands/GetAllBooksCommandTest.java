package timnekk.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import timnekk.exceptions.CommandExecutionException;
import timnekk.exceptions.StorageException;
import timnekk.models.Book;
import timnekk.storage.BookStorage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GetAllBooksCommandTest {
    private BookStorage mockStorage;

    @BeforeEach
    void setUp() {
        mockStorage = mock(BookStorage.class);
    }

    @Test
    void gettingBooksFromEmptyStorageShouldPutAppropriateMessageToOutput() throws CommandExecutionException, StorageException {
        when(mockStorage.getBooks()).thenReturn(List.of());

        Command command = new GetAllBooksCommand(mockStorage);
        command.execute();

        assertEquals("No books found!", command.getOutput());
    }

    @Test
    void gettingBooksFromNonEmptyStorageShouldPutBooksToOutput() throws CommandExecutionException, StorageException {
        when(mockStorage.getBooks()).thenReturn(List.of(
                new Book("Title 1", "Author 1", 2000),
                new Book("Title 2", "Author 2", 2001)
        ));

        Command command = new GetAllBooksCommand(mockStorage);
        command.execute();

        assertEquals("""
                Total: 2 books
                Author 1   │ "Title 1", 2000 y.
                Author 2   │ "Title 2", 2001 y.""", command.getOutput());
    }
}
package timnekk.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import timnekk.exceptions.CommandExecutionException;
import timnekk.exceptions.StorageException;
import timnekk.models.Book;
import timnekk.storage.BookStorage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TransferBookCommandTest {
    private BookStorage mockLibrary;
    private BookStorage mockBundle;

    @BeforeEach
    void setUp() {
        mockLibrary = mock(BookStorage.class);
        mockBundle = mock(BookStorage.class);
    }

    @Test
    void transferringBookWithTitleAndAuthorShouldTakeBookFromLibraryAndAddItToBundle()
            throws CommandExecutionException, StorageException {
        String title = "Title 1";
        String author = "Author 1";
        Book book = new Book(title, author, 2000);

        when(mockLibrary.takeBook(title, author)).thenReturn(book);

        Command command = new TransferBookCommand(mockLibrary, mockBundle, title, author);
        command.execute();

        verify(mockBundle, times(1)).addBook(book);
    }

    @Test
    void transferringBookWithNullAuthorAndNoBooksWithSuchTitleShouldPutErrorMessageToOutput()
            throws CommandExecutionException, StorageException {
        String title = "Title 1";

        when(mockLibrary.countAuthorsWithTitle(title)).thenReturn(0);

        Command command = new TransferBookCommand(mockLibrary, mockBundle, title, null);
        command.execute();

        assertEquals("Book with this title not found!", command.getOutput());
    }

    @Test
    void transferringBookWithNullAuthorAndOneBookWithSuchTitleShouldReturnBookWithSuchTitle()
            throws CommandExecutionException, StorageException {
        String title = "Title 1";
        String author = null;
        Book book = new Book(title, "Author 1", 2000);

        when(mockLibrary.countAuthorsWithTitle(title)).thenReturn(1);
        when(mockLibrary.takeBook(title, author)).thenReturn(book);

        Command command = new TransferBookCommand(mockLibrary, mockBundle, title, author);
        command.execute();

        verify(mockBundle, times(1)).addBook(book);
    }

    @Test
    void transferringBookWithNullAuthorAndMoreThatOneBookWithSuchTitleShouldPutErrorMessageToOutput()
            throws CommandExecutionException, StorageException {
        String title = "Title 1";

        when(mockLibrary.countAuthorsWithTitle(title)).thenReturn(2);

        Command command = new TransferBookCommand(mockLibrary, mockBundle, title, null);
        command.execute();

        assertEquals("There are more than one book with this title. Please specify author.", command.getOutput());
    }

    @Test
    void successfullyTransferringBookShouldPutSuccessMessageToOutput() throws CommandExecutionException, StorageException {
        String title = "Title 1";
        String author = "Author 1";
        Book book = new Book(title, author, 2000);
        when(mockLibrary.takeBook(title, author)).thenReturn(book);

        Command command = new TransferBookCommand(mockLibrary, mockBundle, title, author);
        command.execute();

        assertEquals("Book successfully transferred!", command.getOutput());
    }
}
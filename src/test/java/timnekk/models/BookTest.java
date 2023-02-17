package timnekk.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookTest {
    @Test
    void nullTitleShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Book(null, "Author 1", 2000));

        assertEquals("Title cannot be null", exception.getMessage());
    }

    @Test
    void nullAuthorShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Book("Title 1", null, 2000));

        assertEquals("Author cannot be null", exception.getMessage());
    }
}
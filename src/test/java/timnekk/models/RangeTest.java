package timnekk.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangeTest {
    @Test
    void testNumbersThatAreInRange() {
        Range range = new Range(1, 10);

        assertTrue(range.contains(1));
        assertTrue(range.contains(5));
        assertTrue(range.contains(10));
    }

    @Test
    void testNumbersThatAreOutOfRange() {
        Range range = new Range(1, 10);

        assertFalse(range.contains(0));
        assertFalse(range.contains(11));
    }

    @Test
    void startGreaterThanEndShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Range(10, 1));
        assertEquals("Start cannot be greater than end", exception.getMessage());
    }
}
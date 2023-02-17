package timnekk.models;

public record Book(String title, String author, int year) {
    public Book {
        if (title == null) {
            throw new IllegalArgumentException("Title cannot be null");
        }

        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }
    }
}

package timnekk.storage;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import timnekk.exceptions.StorageException;
import timnekk.models.Book;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonBookLibrary implements BookStorage {
    private final Path pathToJsonFile;
    private static final String TITLE_KEY = "title";
    private static final String AUTHOR_KEY = "author";
    private static final String YEAR_KEY = "year";

    public JsonBookLibrary(Path pathToJsonFile) {
        this.pathToJsonFile = pathToJsonFile;
    }

    @Override
    public List<Book> getBooks() throws StorageException {
        List<JsonObject> jsonBooks = getJsonBooks();

        List<Book> books = new ArrayList<>();
        for (JsonObject jsonBook : jsonBooks) {
            books.add(new Book(
                    (String) jsonBook.get(TITLE_KEY),
                    (String) jsonBook.get(AUTHOR_KEY),
                    ((BigDecimal) jsonBook.get(YEAR_KEY)).intValue()));
        }

        return books;
    }

    @Override
    public Book takeBook(String title, String author) throws StorageException {
        List<JsonObject> jsonBooks = getJsonBooks();

        for (JsonObject jsonBook : jsonBooks) {
            if (!jsonBook.get(TITLE_KEY).equals(title) || (author != null && !jsonBook.get(AUTHOR_KEY).equals(author))) {
                continue;
            }

            jsonBooks.remove(jsonBook);
            saveJsonBooks(jsonBooks);
            return new Book(
                    (String) jsonBook.get(TITLE_KEY),
                    (String) jsonBook.get(AUTHOR_KEY),
                    ((BigDecimal) jsonBook.get(YEAR_KEY)).intValue());
        }

        return null;
    }

    @Override
    public int countAuthorsWithTitle(String title) throws StorageException {
        List<JsonObject> jsonBooks = getJsonBooks();

        int authorsWithTitle = 0;
        List<String> authors = new ArrayList<>();

        for (JsonObject jsonBook : jsonBooks) {
            String author = (String) jsonBook.get(AUTHOR_KEY);

            if (jsonBook.get(TITLE_KEY).equals(title) && !authors.contains(author)) {
                authorsWithTitle++;
                authors.add(author);
            }
        }

        return authorsWithTitle;
    }

    @Override
    public void addBook(Book book) throws StorageException {
        JsonObject jsonBook = new JsonObject();
        jsonBook.put(TITLE_KEY, book.title());
        jsonBook.put(AUTHOR_KEY, book.author());
        jsonBook.put(YEAR_KEY, book.year());

        List<JsonObject> jsonBooks = getJsonBooks();
        jsonBooks.add(jsonBook);

        saveJsonBooks(jsonBooks);
    }

    private List<JsonObject> getJsonBooks() throws StorageException {
        List<JsonObject> jsonBooks = new ArrayList<>();

        try (Reader reader = Files.newBufferedReader(pathToJsonFile)) {
            JsonArray books = (JsonArray) Jsoner.deserialize(reader);

            for (Object book : books) {
                JsonObject jsonBook = (JsonObject) book;
                validateBook(jsonBook);
                jsonBooks.add((JsonObject) book);
            }
        } catch (IOException | JsonException e) {
            throw new StorageException(e);
        }

        return jsonBooks;
    }

    private void saveJsonBooks(List<JsonObject> jsonBooks) throws StorageException {
        try (Writer write = Files.newBufferedWriter(pathToJsonFile)) {
             for (JsonObject jsonBook : jsonBooks) {
                 validateBook(jsonBook);
             }

            Jsoner.serialize(jsonBooks, write);
        } catch (IOException e) {
            throw new StorageException(e);
        }
    }

    private void validateBook(JsonObject book) throws StorageException {
        if (book.get(TITLE_KEY) == null || book.get(AUTHOR_KEY) == null || book.get(YEAR_KEY) == null) {
            throw new StorageException("Invalid book data: " + book);
        }
    }
}

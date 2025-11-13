package models;

import exceptions.*;
import java.util.Objects;

/**
 * Simple Book model.
 */
public class Book {
    private final String isbn;
    private final String title;
    private final String author;
    private final int totalCopies;
    private int availableCopies;

    public Book(String isbn, String title, String author, int totalCopies) {
        if (isbn == null || isbn.isBlank()) throw new IllegalArgumentException("ISBN required");
        if (title == null || title.isBlank()) throw new IllegalArgumentException("Title required");
        if (author == null || author.isBlank()) throw new IllegalArgumentException("Author required");
        if (totalCopies <= 0) throw new IllegalArgumentException("totalCopies must be > 0");

        this.isbn = isbn.trim();
        this.title = title.trim();
        this.author = author.trim();
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }

    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getTotalCopies() { return totalCopies; }
    public int getAvailableCopies() { return availableCopies; }

    // Decrease available copies; throw if none
    public void borrowOne() throws BookNotAvailableException {
        if (availableCopies <= 0) {
            throw new BookNotAvailableException("No copies available for ISBN: " + isbn);
        }
        availableCopies--;
    }

    // Increase available copies up to totalCopies
    public void returnOne() {
        if (availableCopies < totalCopies) {
            availableCopies++;
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] %s by %s (Available: %d/%d)", isbn, title, author, availableCopies, totalCopies);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book other = (Book) o;
        return Objects.equals(isbn, other.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}

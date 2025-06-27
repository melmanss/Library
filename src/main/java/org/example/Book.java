package org.example;

import java.util.Objects;

public class Book {
    private String title;
    private String author;

    public Book(String title, String author) {

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Назва книги не може бути порожньою або null.");
        }

        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Автор книги не може бути порожнім або null.");
        }
        this.title = title.trim();
        this.author = author.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Назва книги не може бути порожньою або null.");
        }
        this.title = title.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Автор книги не може бути порожнім або null.");
        }
        this.author = author.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return title.equalsIgnoreCase(book.title) &&
                author.equalsIgnoreCase(book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title.toLowerCase(), author.toLowerCase());
    }

    @Override
    public String toString() {
        return "Назва: '" + title + "', Автор: '" + author + "'";
    }
}

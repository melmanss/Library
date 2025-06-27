package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Library {
    private final List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public boolean addBook(Book book) {

        if (book == null) {
            System.out.println("Помилка: Неможливо додати null-книгу.");
            return false;
        }

        if (books.contains(book)) {
            System.out.println("Помилка: Книга '" + book.getTitle() + "' автора '" + book.getAuthor() + "' вже існує в бібліотеці.");
            return false;
        }

        books.add(book);
        System.out.println("Книга '" + book.getTitle() + "' успішно додана.");
        return true;
    }

    public boolean removeBook(Book book) {
        // Case: Null book
        if (book == null) {
            System.out.println("Помилка: Неможливо видалити null-книгу.");
            return false;
        }

        boolean wasRemoved = books.remove(book);

        if (wasRemoved) {
            System.out.println("Книга '" + book.getTitle() + "' успішно видалена.");
        } else {
            System.out.println("Помилка: Книги '" + book.getTitle() + "' автора '" + book.getAuthor() + "' не знайдено в бібліотеці.");
        }
        return wasRemoved;
    }

    public List<Book> getBooks() {

        return Collections.unmodifiableList(new ArrayList<>(books));
    }

    public int getBookCount() {
        return books.size();
    }
}

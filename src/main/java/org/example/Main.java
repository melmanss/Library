package org.example;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Запуск демонстрації бібліотеки...");

        Library myLibrary = new Library();

        Book book1 = new Book("Війна і мир", "Лев Толстой");
        Book book2 = new Book("1984", "Джордж Оруелл");
        Book book3 = new Book("Хоббіт", "Дж. Р. Р. Толкін");
        Book book4 = new Book("1984", "Джордж Оруелл");
        Book book5 = new Book("Майстер і Маргарита", "Михайло Булгаков");

        System.out.println("\n--- Демонстрація додавання книг ---");
        myLibrary.addBook(book1);
        myLibrary.addBook(book2);
        myLibrary.addBook(book3);
        myLibrary.addBook(book4);
        myLibrary.addBook(null);

        System.out.println("\nПоточна кількість книг у бібліотеці: " + myLibrary.getBookCount());
        System.out.println("Список книг у бібліотеці:");
        List<Book> allBooks = myLibrary.getBooks();
        for (int i = 0; i < allBooks.size(); i++) {
            System.out.println((i + 1) + ". " + allBooks.get(i));
        }

        System.out.println("\n--- Демонстрація видалення книг ---");
        myLibrary.removeBook(book1);
        myLibrary.removeBook(book1);
        myLibrary.removeBook(book5);
        myLibrary.removeBook(null);
        System.out.println("\nПоточна кількість книг у бібліотеці: " + myLibrary.getBookCount());
        System.out.println("Список книг у бібліотеці після видалення:");
        allBooks = myLibrary.getBooks();
        if (allBooks.isEmpty()) {
            System.out.println("Бібліотека порожня.");
        } else {
            for (int i = 0; i < allBooks.size(); i++) {
                System.out.println((i + 1) + ". " + allBooks.get(i));
            }
        }

        myLibrary.addBook(book5);
        System.out.println("\nПоточна кількість книг після додавання book5: " + myLibrary.getBookCount());
        System.out.println("Список книг у бібліотеці:");
        allBooks = myLibrary.getBooks();
        for (int i = 0; i < allBooks.size(); i++) {
            System.out.println((i + 1) + ". " + allBooks.get(i));
        }
    }
}

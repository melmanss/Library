package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class LibraryTest {

    private Library library;
    private Book book1;
    private Book book2;
    private Book book3;
    private Book duplicateBook1;

    @BeforeEach
    void setUp() {
        library = new Library();
        book1 = new Book("Війна і мир", "Лев Толстой");
        book2 = new Book("1984", "Джордж Оруелл");
        book3 = new Book("Хоббіт", "Дж. Р. Р. Толкін");
        duplicateBook1 = new Book("війна і мир", "лев толстой");
    }

    @Test
    @DisplayName("addBook: Повинен успішно додати нову книгу")
    void addBook_shouldAddNewBookSuccessfully() {
        assertTrue(library.addBook(book1), "Книга повинна бути додана успішно");
        assertEquals(1, library.getBookCount(), "Кількість книг повинна бути 1");
        assertTrue(library.getBooks().contains(book1), "Бібліотека повинна містити додану книгу");
    }

    @Test
    @DisplayName("addBook: Не повинен додавати null-книгу")
    void addBook_shouldNotAddNullBook() {
        assertFalse(library.addBook(null), "Null-книга не повинна бути додана");
        assertEquals(0, library.getBookCount(), "Кількість книг повинна залишатися 0");
    }

    @Test
    @DisplayName("addBook: Не повинен додавати книгу, яка вже існує")
    void addBook_shouldNotAddExistingBook() {
        library.addBook(book1);
        assertFalse(library.addBook(book1), "Існуюча книга не повинна бути додана знову");
        assertEquals(1, library.getBookCount(), "Кількість книг повинна залишатися 1");
    }

    @Test
    @DisplayName("addBook: Не повинен додавати книгу, яка є дублікатом (за equals)")
    void addBook_shouldNotAddDuplicateBook() {
        library.addBook(book1);
        assertTrue(book1.equals(duplicateBook1), "Книги повинні вважатися рівними");
        assertFalse(library.addBook(duplicateBook1), "Дублікат книги не повинен бути доданий");
        assertEquals(1, library.getBookCount(), "Кількість книг повинна залишатися 1");
    }

    @Test
    @DisplayName("addBook: Повинен додавати кілька унікальних книг")
    void addBook_shouldAddMultipleUniqueBooks() {
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        assertEquals(3, library.getBookCount(), "Кількість книг повинна бути 3");
        assertTrue(library.getBooks().contains(book1) &&
                library.getBooks().contains(book2) &&
                library.getBooks().contains(book3), "Бібліотека повинна містити всі три книги");
    }

    @Test
    @DisplayName("removeBook: Повинен успішно видалити існуючу книгу")
    void removeBook_shouldRemoveExistingBookSuccessfully() {
        library.addBook(book1);
        library.addBook(book2);
        assertTrue(library.removeBook(book1), "Книга повинна бути успішно видалена");
        assertEquals(1, library.getBookCount(), "Кількість книг повинна бути 1 після видалення");
        assertFalse(library.getBooks().contains(book1), "Бібліотека не повинна містити видалену книгу");
        assertTrue(library.getBooks().contains(book2), "Інша книга повинна залишатися в бібліотеці");
    }

    @Test
    @DisplayName("removeBook: Не повинен видаляти null-книгу")
    void removeBook_shouldNotRemoveNullBook() {
        library.addBook(book1);
        assertFalse(library.removeBook(null), "Null-книга не повинна бути видалена");
        assertEquals(1, library.getBookCount(), "Кількість книг повинна залишатися 1");
    }

    @Test
    @DisplayName("removeBook: Повинен повернути false, якщо книга не знайдена")
    void removeBook_shouldReturnFalseIfBookNotFound() {
        library.addBook(book1);
        assertFalse(library.removeBook(book2), "Книга, якої немає, не повинна бути видалена");
        assertEquals(1, library.getBookCount(), "Кількість книг повинна залишатися 1");
    }

    @Test
    @DisplayName("removeBook: Повинен успішно видалити єдину книгу")
    void removeBook_shouldRemoveOnlyBook() {
        library.addBook(book1);
        assertTrue(library.removeBook(book1), "Єдина книга повинна бути успішно видалена");
        assertEquals(0, library.getBookCount(), "Кількість книг повинна бути 0");
        assertTrue(library.getBooks().isEmpty(), "Бібліотека повинна бути порожньою");
    }

    @Test
    @DisplayName("removeBook: Повинен повернути false при видаленні з порожньої бібліотеки")
    void removeBook_shouldReturnFalseWhenRemovingFromEmptyLibrary() {
        assertFalse(library.removeBook(book1), "Неможливо видалити книгу з порожньої бібліотеки");
        assertEquals(0, library.getBookCount(), "Кількість книг повинна залишатися 0");
    }

    @Test
    @DisplayName("getBooks: Повинен повертати порожній список для порожньої бібліотеки")
    void getBooks_shouldReturnEmptyListForEmptyLibrary() {
        List<Book> books = library.getBooks();
        assertNotNull(books, "Список книг не повинен бути null");
        assertTrue(books.isEmpty(), "Список книг повинен бути порожнім");
    }

    @Test
    @DisplayName("getBooks: Повинен повертати список доданих книг")
    void getBooks_shouldReturnListOfAddedBooks() {
        library.addBook(book1);
        library.addBook(book2);
        List<Book> books = library.getBooks();
        assertEquals(2, books.size(), "Список повинен містити 2 книги");
        assertTrue(books.contains(book1) && books.contains(book2), "Список повинен містити додані книги");
    }

    @Test
    @DisplayName("getBooks: Повинен повертати незмінний список")
    void getBooks_shouldReturnUnmodifiableList() {
        library.addBook(book1);
        List<Book> books = library.getBooks();
        assertThrows(UnsupportedOperationException.class, () -> books.add(book2),
                "Повинен кинути UnsupportedOperationException при спробі модифікувати повернутий список");
    }

    @Test
    @DisplayName("getBookCount: Повинен повертати 0 для порожньої бібліотеки")
    void getBookCount_shouldReturnZeroForEmptyLibrary() {
        assertEquals(0, library.getBookCount(), "Кількість книг повинна бути 0 для порожньої бібліотеки");
    }

    @Test
    @DisplayName("getBookCount: Повинен повертати правильну кількість після додавання книг")
    void getBookCount_shouldReturnCorrectCountAfterAddingBooks() {
        library.addBook(book1);
        assertEquals(1, library.getBookCount(), "Кількість книг повинна бути 1");
        library.addBook(book2);
        assertEquals(2, library.getBookCount(), "Кількість книг повинна бути 2");
    }

    @Test
    @DisplayName("getBookCount: Повинен повертати правильну кількість після видалення книг")
    void getBookCount_shouldReturnCorrectCountAfterRemovingBooks() {
        library.addBook(book1);
        library.addBook(book2);
        library.removeBook(book1);
        assertEquals(1, library.getBookCount(), "Кількість книг повинна бути 1 після видалення");
        library.removeBook(book2);
        assertEquals(0, library.getBookCount(), "Кількість книг повинна бути 0 після видалення всіх книг");
    }
}

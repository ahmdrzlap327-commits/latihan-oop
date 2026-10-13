package id.ac.polinema.library;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** Overloading: nama method atau constructor sama, parameter berbeda. */
class P07OverloadTest {

    @Test
    @DisplayName("LibraryItem punya dua method extendLoan dengan parameter berbeda")
    void twoExtendLoan() {
        int count = 0;
        for (Method m : R.cls("LibraryItem").getDeclaredMethods()) {
            if (m.getName().equals("extendLoan")) {
                count++;
            }
        }
        assertEquals(2, count, "Harus ada extendLoan() dan extendLoan(int days)");
    }

    @Test
    @DisplayName("extendLoan() menambah 7 hari")
    void extendDefault() throws Throwable {
        Object dvd = R.create("Dvd", "Inception", 2010, 148);
        assertEquals(3, R.call(dvd, "getTotalLoanDays"));
        R.call(dvd, "extendLoan");
        assertEquals(10, R.call(dvd, "getTotalLoanDays"));
    }

    @Test
    @DisplayName("extendLoan(int) menambah sebanyak hari yang diberikan dan bisa berulang")
    void extendDays() throws Throwable {
        Book b = new Book("1", "Clean Code", 2008);
        assertEquals(14, R.call(b, "getTotalLoanDays"));
        R.call(b, "extendLoan", 3);
        R.call(b, "extendLoan", 2);
        assertEquals(19, R.call(b, "getTotalLoanDays"));
    }

    @Test
    @DisplayName("extendLoan(int) menolak 0 dan negatif; total tidak berubah")
    void extendRejectsNonPositive() throws Throwable {
        Object m = R.create("Magazine", "Tempo", 2024, 7);
        assertThrows(IllegalArgumentException.class, () -> R.call(m, "extendLoan", 0));
        assertThrows(IllegalArgumentException.class, () -> R.call(m, "extendLoan", -4));
        assertEquals(7, R.call(m, "getTotalLoanDays"));
    }

    @Test
    @DisplayName("Constructor Book(isbn, title, year, author) langsung mengisi penulis")
    void bookConstructorWithAuthor() throws Throwable {
        Author martin = new Author("Robert Martin", "USA");
        Object b = R.create("Book", "1", "Clean Code", 2008, martin);
        assertSame(martin, R.call(b, "getAuthor"));
        assertEquals("Clean Code", R.call(b, "getTitle"));
        assertEquals("1", R.call(b, "getIsbn"));
        assertEquals(true, R.call(b, "isAvailable"));
    }

    @Test
    @DisplayName("Constructor Magazine(title, year) memberi nomor edisi 1; versi 3 parameter tetap jalan")
    void magazineConstructors() throws Throwable {
        Object a = R.create("Magazine", "Tempo", 2024);
        assertEquals(1, R.call(a, "getIssueNumber"));
        Object b = R.create("Magazine", "Tempo", 2024, 9);
        assertEquals(9, R.call(b, "getIssueNumber"));
    }
}

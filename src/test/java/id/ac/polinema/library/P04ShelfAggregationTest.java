package id.ac.polinema.library;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Agregasi: Shelf menyimpan Book, tetapi Book ada di luar Shelf juga. */
class P04ShelfAggregationTest {

    private static Object shelf() throws Throwable {
        return R.create("Shelf", "A1");
    }

    @Test
    @DisplayName("Shelf ada, semua field-nya private, dan menyimpan kode rak")
    void basics() throws Throwable {
        R.assertAllFieldsPrivate(R.cls("Shelf"));
        Object s = shelf();
        assertEquals("A1", R.call(s, "getCode"));
        assertEquals(0, R.call(s, "getBookCount"));
    }

    @Test
    @DisplayName("addBook menambah buku dan getBookCount ikut naik")
    void addBook() throws Throwable {
        Object s = shelf();
        assertEquals(true, R.call(s, "addBook", new Book("1", "Clean Code", 2008)));
        assertEquals(true, R.call(s, "addBook", new Book("2", "Refactoring", 1999)));
        assertEquals(2, R.call(s, "getBookCount"));
    }

    @Test
    @DisplayName("Kapasitas rak 5 buku; buku ke-6 ditolak")
    void capacity() throws Throwable {
        Object s = shelf();
        for (int i = 1; i <= 5; i++) {
            assertEquals(true, R.call(s, "addBook", new Book("" + i, "Book " + i, 2000)));
        }
        assertEquals(false, R.call(s, "addBook", new Book("6", "Book 6", 2000)));
        assertEquals(5, R.call(s, "getBookCount"));
    }

    @Test
    @DisplayName("addBook(null) ditolak")
    void rejectsNull() throws Throwable {
        Object s = shelf();
        assertEquals(false, R.call(s, "addBook", (Object) null));
        assertEquals(0, R.call(s, "getBookCount"));
    }

    @Test
    @DisplayName("findByIsbn mengembalikan objek Book yang sama (agregasi), atau null")
    void findByIsbn() throws Throwable {
        Object s = shelf();
        Book clean = new Book("111", "Clean Code", 2008);
        Book refactoring = new Book("222", "Refactoring", 1999);
        R.call(s, "addBook", clean);
        R.call(s, "addBook", refactoring);
        assertSame(refactoring, R.call(s, "findByIsbn", "222"));
        assertSame(clean, R.call(s, "findByIsbn", "111"));
        assertNull(R.call(s, "findByIsbn", "999"));
    }

    @Test
    @DisplayName("countAvailable mengikuti status buku yang dipinjam lewat referensi luar")
    void countAvailable() throws Throwable {
        Object s = shelf();
        Book a = new Book("1", "Clean Code", 2008);
        Book b = new Book("2", "Refactoring", 1999);
        R.call(s, "addBook", a);
        R.call(s, "addBook", b);
        assertEquals(2, R.call(s, "countAvailable"));
        assertTrue(a.checkOut());
        assertEquals(1, R.call(s, "countAvailable"));
        a.returnItem();
        assertEquals(2, R.call(s, "countAvailable"));
        assertTrue(b.isAvailable());
    }
}

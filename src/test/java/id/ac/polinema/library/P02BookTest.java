package id.ac.polinema.library;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class P02BookTest {

    @Test
    @DisplayName("Constructor menyimpan isbn, title, dan year")
    void constructorAndGetters() {
        Book b = new Book("978-0132350884", "Clean Code", 2008);
        assertEquals("978-0132350884", b.getIsbn());
        assertEquals("Clean Code", b.getTitle());
        assertEquals(2008, b.getYear());
    }

    @Test
    @DisplayName("Buku baru langsung tersedia")
    void newBookIsAvailable() {
        assertTrue(new Book("1", "Clean Code", 2008).isAvailable());
    }

    @Test
    @DisplayName("checkOut: berhasil sekali, lalu gagal karena sudah dipinjam")
    void checkOut() {
        Book b = new Book("1", "Clean Code", 2008);
        assertTrue(b.checkOut());
        assertFalse(b.isAvailable());
        assertFalse(b.checkOut());
    }

    @Test
    @DisplayName("returnItem membuat buku tersedia lagi")
    void returnItem() {
        Book b = new Book("1", "Clean Code", 2008);
        b.checkOut();
        b.returnItem();
        assertTrue(b.isAvailable());
        assertTrue(b.checkOut());
    }

    @Test
    @DisplayName("Meminjam satu buku tidak mengubah buku lain")
    void objectsAreIndependent() {
        Book a = new Book("1", "Clean Code", 2008);
        Book b = new Book("2", "Refactoring", 1999);
        a.checkOut();
        assertFalse(a.isAvailable());
        assertTrue(b.isAvailable());
        assertEquals("Refactoring", b.getTitle());
    }
}

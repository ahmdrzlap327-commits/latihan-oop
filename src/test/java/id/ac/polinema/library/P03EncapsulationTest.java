package id.ac.polinema.library;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class P03EncapsulationTest {

    @Test
    @DisplayName("Semua field Book private")
    void bookFieldsPrivate() {
        R.assertAllFieldsPrivate(Book.class);
    }

    @Test
    @DisplayName("Semua field Author private")
    void authorFieldsPrivate() {
        R.assertAllFieldsPrivate(Author.class);
    }

    @Test
    @DisplayName("isbn read-only: Book tidak punya setIsbn, dan status tidak bisa di-set langsung")
    void readOnlyFields() {
        assertFalse(R.hasPublicMethod(Book.class, "setIsbn"), "isbn tidak boleh punya setter");
        assertFalse(R.hasPublicMethod(Book.class, "setAvailable"),
                "status tersedia hanya boleh berubah lewat checkOut dan returnItem");
    }

    @Test
    @DisplayName("setTitle menolak null, kosong, dan hanya spasi; judul lama tetap")
    void setTitleRejectsBlank() {
        Book b = new Book("1", "Clean Code", 2008);
        assertThrows(IllegalArgumentException.class, () -> b.setTitle(null));
        assertThrows(IllegalArgumentException.class, () -> b.setTitle(""));
        assertThrows(IllegalArgumentException.class, () -> b.setTitle("   "));
        assertEquals("Clean Code", b.getTitle());
    }

    @Test
    @DisplayName("setTitle menerima judul yang valid")
    void setTitleAcceptsValid() {
        Book b = new Book("1", "Clean Code", 2008);
        b.setTitle("Clean Architecture");
        assertEquals("Clean Architecture", b.getTitle());
    }

    @Test
    @DisplayName("setYear menolak 0 dan negatif; tahun lama tetap")
    void setYearRejectsNonPositive() {
        Book b = new Book("1", "Clean Code", 2008);
        assertThrows(IllegalArgumentException.class, () -> b.setYear(0));
        assertThrows(IllegalArgumentException.class, () -> b.setYear(-5));
        assertEquals(2008, b.getYear());
        b.setYear(2010);
        assertEquals(2010, b.getYear());
    }

    @Test
    @DisplayName("Constructor Book memvalidasi isbn, title, dan year")
    void constructorValidates() {
        assertThrows(IllegalArgumentException.class, () -> new Book("", "Clean Code", 2008));
        assertThrows(IllegalArgumentException.class, () -> new Book(null, "Clean Code", 2008));
        assertThrows(IllegalArgumentException.class, () -> new Book("1", " ", 2008));
        assertThrows(IllegalArgumentException.class, () -> new Book("1", "Clean Code", 0));
    }
}

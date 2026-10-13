package id.ac.polinema.library;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Inheritance: Book, Dvd, dan Magazine adalah LibraryItem (IS-A). */
class P06InheritanceTest {

    private static Object dvd() throws Throwable {
        return R.create("Dvd", "Inception", 2010, 148);
    }

    private static Object magazine() throws Throwable {
        return R.create("Magazine", "Tempo", 2024, 7);
    }

    @Test
    @DisplayName("Book, Dvd, dan Magazine extends LibraryItem")
    void superclass() {
        Class<?> item = R.cls("LibraryItem");
        assertEquals(item, Book.class.getSuperclass(), "Book harus extends LibraryItem");
        assertEquals(item, R.cls("Dvd").getSuperclass(), "Dvd harus extends LibraryItem");
        assertEquals(item, R.cls("Magazine").getSuperclass(), "Magazine harus extends LibraryItem");
    }

    @Test
    @DisplayName("LibraryItem menyimpan title dan year sebagai protected, dan tidak ada field public")
    void itemFields() {
        Class<?> item = R.cls("LibraryItem");
        for (Field f : item.getDeclaredFields()) {
            if (f.isSynthetic()) {
                continue;
            }
            assertFalse(Modifier.isPublic(f.getModifiers()), "Field " + f.getName() + " tidak boleh public");
        }
        assertTrue(Modifier.isProtected(field(item, "title").getModifiers()), "title harus protected");
        assertTrue(Modifier.isProtected(field(item, "year").getModifiers()), "year harus protected");
    }

    private static Field field(Class<?> c, String name) {
        try {
            return c.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            return org.junit.jupiter.api.Assertions.fail("Field " + name + " tidak ada di " + c.getSimpleName());
        }
    }

    @Test
    @DisplayName("Book tidak menyimpan ulang title, year, dan available (sudah ada di LibraryItem)")
    void bookDoesNotRepeatFields() {
        for (Field f : Book.class.getDeclaredFields()) {
            String n = f.getName();
            assertFalse(n.equals("title") || n.equals("year") || n.equals("available"),
                    "Field " + n + " sudah ada di LibraryItem. Hapus dari Book.");
        }
        R.assertAllFieldsPrivate(Book.class);
    }

    @Test
    @DisplayName("Method umum dipindah ke LibraryItem, bukan ditulis ulang di subclass")
    void methodsMovedUp() {
        for (String name : new String[] {"getTitle", "getYear", "isAvailable", "checkOut", "returnItem"}) {
            assertFalse(R.declaresMethod(Book.class, name), "Book tidak perlu mendeklarasikan " + name);
            assertFalse(R.declaresMethod(R.cls("Dvd"), name), "Dvd tidak perlu mendeklarasikan " + name);
            assertTrue(R.declaresMethod(R.cls("LibraryItem"), name), "LibraryItem harus punya " + name);
        }
    }

    @Test
    @DisplayName("LibraryItem(title, year) menyimpan data dan memvalidasinya")
    void itemConstructor() throws Throwable {
        Object item = R.create("LibraryItem", "Generic", 2000);
        assertEquals("Generic", R.call(item, "getTitle"));
        assertEquals(2000, R.call(item, "getYear"));
        assertEquals(true, R.call(item, "isAvailable"));
        assertThrows(IllegalArgumentException.class, () -> R.create("LibraryItem", " ", 2000));
        assertThrows(IllegalArgumentException.class, () -> R.create("LibraryItem", "Generic", 0));
    }

    @Test
    @DisplayName("Dvd menyimpan durasi dan memakai getter turunan dari LibraryItem")
    void dvdKeepsDuration() throws Throwable {
        Object d = dvd();
        assertEquals("Inception", R.call(d, "getTitle"));
        assertEquals(2010, R.call(d, "getYear"));
        assertEquals(148, R.call(d, "getDurationMinutes"));
        R.assertAllFieldsPrivate(R.cls("Dvd"));
    }

    @Test
    @DisplayName("Magazine menyimpan nomor edisi dan memakai getter turunan")
    void magazineKeepsIssue() throws Throwable {
        Object m = magazine();
        assertEquals("Tempo", R.call(m, "getTitle"));
        assertEquals(7, R.call(m, "getIssueNumber"));
        R.assertAllFieldsPrivate(R.cls("Magazine"));
    }

    @Test
    @DisplayName("checkOut dan returnItem diwarisi oleh semua subclass")
    void inheritedBehaviour() throws Throwable {
        Object[] items = {new Book("1", "Clean Code", 2008), dvd(), magazine()};
        for (Object item : items) {
            assertEquals(true, R.call(item, "isAvailable"));
            assertEquals(true, R.call(item, "checkOut"));
            assertEquals(false, R.call(item, "checkOut"));
            R.call(item, "returnItem");
            assertEquals(true, R.call(item, "isAvailable"));
        }
    }

    @Test
    @DisplayName("Validasi setter ikut diwarisi: setTitle kosong ditolak di Dvd")
    void validationInherited() throws Throwable {
        Object d = dvd();
        assertThrows(IllegalArgumentException.class, () -> R.call(d, "setTitle", ""));
        assertThrows(IllegalArgumentException.class, () -> R.call(d, "setYear", -1));
        assertEquals("Inception", R.call(d, "getTitle"));
    }

    @Test
    @DisplayName("Objek subclass bisa disimpan sebagai LibraryItem (polymorphism)")
    void isInstance() throws Throwable {
        Class<?> item = R.cls("LibraryItem");
        assertTrue(item.isInstance(new Book("1", "Clean Code", 2008)));
        assertTrue(item.isInstance(dvd()));
        assertTrue(item.isInstance(magazine()));
    }
}

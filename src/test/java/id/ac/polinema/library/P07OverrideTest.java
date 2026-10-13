package id.ac.polinema.library;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Overriding: subclass mengganti perilaku method yang diwarisi. */
class P07OverrideTest {

    private static Object item() throws Throwable {
        return R.create("LibraryItem", "Generic", 2000);
    }

    private static Object dvd() throws Throwable {
        return R.create("Dvd", "Inception", 2010, 148);
    }

    private static Object magazine() throws Throwable {
        return R.create("Magazine", "Tempo", 2024, 7);
    }

    @Test
    @DisplayName("loanDays: LibraryItem 7, Book 14, Dvd 3, Magazine mewarisi 7")
    void loanDays() throws Throwable {
        assertEquals(7, R.call(item(), "loanDays"));
        assertEquals(14, R.call(new Book("1", "Clean Code", 2008), "loanDays"));
        assertEquals(3, R.call(dvd(), "loanDays"));
        assertEquals(7, R.call(magazine(), "loanDays"));
    }

    @Test
    @DisplayName("Book dan Dvd meng-override loanDays; Magazine tidak")
    void whoOverrides() {
        assertTrue(R.declaresMethod(Book.class, "loanDays"), "Book harus override loanDays");
        assertTrue(R.declaresMethod(R.cls("Dvd"), "loanDays"), "Dvd harus override loanDays");
        assertFalse(R.declaresMethod(R.cls("Magazine"), "loanDays"),
                "Magazine tidak perlu override loanDays, ia memakai versi LibraryItem");
    }

    @Test
    @DisplayName("describe di LibraryItem: 'title (year)'")
    void itemDescribe() throws Throwable {
        assertEquals("Generic (2000)", R.call(item(), "describe"));
    }

    @Test
    @DisplayName("describe di Book memakai super.describe() lalu menambah ' by penulis'")
    void bookDescribe() throws Throwable {
        Book b = new Book("1", "Clean Code", 2008);
        assertEquals("Clean Code (2008) by Unknown", R.call(b, "describe"));
        R.call(b, "setAuthor", new Author("Robert Martin", "USA"));
        assertEquals("Clean Code (2008) by Robert Martin", R.call(b, "describe"));
    }

    @Test
    @DisplayName("describe di Dvd menambah durasi")
    void dvdDescribe() throws Throwable {
        assertEquals("Inception (2010) [148 min]", R.call(dvd(), "describe"));
    }

    @Test
    @DisplayName("describe di Magazine memakai versi LibraryItem")
    void magazineDescribe() throws Throwable {
        assertEquals("Tempo (2024)", R.call(magazine(), "describe"));
    }

    @Test
    @DisplayName("toString mengembalikan describe() untuk semua jenis item")
    void toStringUsesDescribe() throws Throwable {
        Object[] items = {item(), new Book("1", "Clean Code", 2008), dvd(), magazine()};
        for (Object it : items) {
            assertEquals(R.call(it, "describe"), it.toString(),
                    "toString harus mengembalikan describe() pada " + it.getClass().getSimpleName());
        }
    }

    @Test
    @DisplayName("Polymorphism: objek yang menentukan versi method, bukan tipe variabel")
    void polymorphism() throws Throwable {
        Object[] items = {item(), new Book("1", "Clean Code", 2008), dvd(), magazine()};
        int[] expected = {7, 14, 3, 7};
        for (int i = 0; i < items.length; i++) {
            assertEquals(expected[i], R.call(items[i], "loanDays"));
        }
    }
}

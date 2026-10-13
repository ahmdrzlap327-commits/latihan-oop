package id.ac.polinema.library;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

/** Asosiasi: Book mengenal Author, tetapi Author hidup sendiri. */
class P04AssociationTest {

    @Test
    @DisplayName("Buku baru belum punya penulis (null)")
    void newBookHasNoAuthor() throws Throwable {
        Book b = new Book("1", "Clean Code", 2008);
        assertNull(R.call(b, "getAuthor"));
    }

    @Test
    @DisplayName("getAuthor mengembalikan objek Author yang sama (asosiasi)")
    void sameAuthorObject() throws Throwable {
        Book b = new Book("1", "Clean Code", 2008);
        Author martin = new Author("Robert Martin", "USA");
        R.call(b, "setAuthor", martin);
        assertSame(martin, R.call(b, "getAuthor"));
    }

    @Test
    @DisplayName("Dua buku bisa berbagi satu Author")
    void booksShareAuthor() throws Throwable {
        Book a = new Book("1", "Clean Code", 2008);
        Book b = new Book("2", "Clean Architecture", 2017);
        Author martin = new Author("Robert Martin", "USA");
        R.call(a, "setAuthor", martin);
        R.call(b, "setAuthor", martin);
        assertSame(R.call(a, "getAuthor"), R.call(b, "getAuthor"));
        assertEquals("Robert Martin", martin.getName());
    }

    @Test
    @DisplayName("setAuthor(null) melepas penulis")
    void clearAuthor() throws Throwable {
        Book b = new Book("1", "Clean Code", 2008);
        R.call(b, "setAuthor", new Author("Robert Martin", "USA"));
        R.call(b, "setAuthor", (Object) null);
        assertNull(R.call(b, "getAuthor"));
    }
}

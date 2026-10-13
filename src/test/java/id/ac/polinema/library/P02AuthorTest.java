package id.ac.polinema.library;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class P02AuthorTest {

    @Test
    @DisplayName("Constructor menyimpan nama dan negara, getter mengembalikannya")
    void constructorAndGetters() {
        Author a = new Author("Robert Martin", "USA");
        assertEquals("Robert Martin", a.getName());
        assertEquals("USA", a.getCountry());
    }

    @Test
    @DisplayName("getInfo mengembalikan 'nama (negara)'")
    void getInfo() {
        Author a = new Author("Andrea Hirata", "Indonesia");
        assertEquals("Andrea Hirata (Indonesia)", a.getInfo());
    }

    @Test
    @DisplayName("Dua objek Author punya data sendiri-sendiri")
    void objectsAreIndependent() {
        Author a = new Author("Robert Martin", "USA");
        Author b = new Author("Andrea Hirata", "Indonesia");
        assertNotEquals(a.getName(), b.getName());
        assertEquals("Robert Martin", a.getName());
        assertEquals("Indonesia", b.getCountry());
    }
}

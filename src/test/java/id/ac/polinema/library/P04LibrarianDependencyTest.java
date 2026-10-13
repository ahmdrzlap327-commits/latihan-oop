package id.ac.polinema.library;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Dependensi: Librarian hanya memakai Member dan Book lewat parameter. */
class P04LibrarianDependencyTest {

    private static Object librarian() throws Throwable {
        return R.create("Librarian");
    }

    @Test
    @DisplayName("Librarian tidak menyimpan apa pun sebagai field (dependensi, bukan asosiasi)")
    void noFields() {
        Class<?> c = R.cls("Librarian");
        assertEquals(0, c.getDeclaredFields().length,
                "Librarian hanya BERGANTUNG pada Member dan Book lewat parameter, jangan simpan sebagai field");
    }

    @Test
    @DisplayName("lend berhasil: buku dipinjam dan jumlah pinjaman Member naik")
    void lendSuccess() throws Throwable {
        Object l = librarian();
        Member m = new Member("M001", "Budi");
        Book b = new Book("1", "Clean Code", 2008);
        assertEquals(true, R.call(l, "lend", m, b));
        assertFalse(b.isAvailable());
        assertEquals(1, m.getLoanCount());
    }

    @Test
    @DisplayName("lend gagal jika buku sudah dipinjam; jumlah pinjaman tidak berubah")
    void lendBookTaken() throws Throwable {
        Object l = librarian();
        Member a = new Member("M001", "Budi");
        Member b = new Member("M002", "Sari");
        Book book = new Book("1", "Clean Code", 2008);
        R.call(l, "lend", a, book);
        assertEquals(false, R.call(l, "lend", b, book));
        assertEquals(0, b.getLoanCount());
    }

    @Test
    @DisplayName("lend gagal jika Member sudah meminjam 3 buku; buku tetap tersedia")
    void lendMemberAtLimit() throws Throwable {
        Object l = librarian();
        Member m = new Member("M001", "Budi");
        for (int i = 1; i <= 3; i++) {
            R.call(l, "lend", m, new Book("" + i, "Book " + i, 2000));
        }
        Book fourth = new Book("4", "Book 4", 2000);
        assertEquals(false, R.call(l, "lend", m, fourth));
        assertTrue(fourth.isAvailable());
        assertEquals(3, m.getLoanCount());
    }

    @Test
    @DisplayName("receive berhasil: buku tersedia lagi dan jumlah pinjaman turun")
    void receiveSuccess() throws Throwable {
        Object l = librarian();
        Member m = new Member("M001", "Budi");
        Book b = new Book("1", "Clean Code", 2008);
        R.call(l, "lend", m, b);
        assertEquals(true, R.call(l, "receive", m, b));
        assertTrue(b.isAvailable());
        assertEquals(0, m.getLoanCount());
    }

    @Test
    @DisplayName("receive gagal untuk buku yang tidak sedang dipinjam")
    void receiveNotBorrowed() throws Throwable {
        Object l = librarian();
        Member m = new Member("M001", "Budi");
        assertEquals(false, R.call(l, "receive", m, new Book("1", "Clean Code", 2008)));
        assertEquals(0, m.getLoanCount());
    }
}

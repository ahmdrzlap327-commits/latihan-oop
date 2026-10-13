package id.ac.polinema.library;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Library berisi logika saja: tidak boleh mencetak apa pun. */
@Timeout(10)
class P08LibraryTest {

    private static Object library() throws Throwable {
        return R.create("Library", "Polinema Library");
    }

    private static Object dvd(String title) throws Throwable {
        return R.create("Dvd", title, 2010, 100);
    }

    private static Object magazine(String title) throws Throwable {
        return R.create("Magazine", title, 2024, 1);
    }

    @Test
    @DisplayName("Library menyimpan nama dan semua field-nya private")
    void basics() throws Throwable {
        R.assertAllFieldsPrivate(R.cls("Library"));
        assertEquals("Polinema Library", R.call(library(), "getName"));
    }

    @Test
    @DisplayName("addItem menerima Book, Dvd, dan Magazine dalam satu array (polymorphism)")
    void mixedItems() throws Throwable {
        Object lib = library();
        Book book = new Book("1", "Clean Code", 2008);
        Object dvd = dvd("Inception");
        Object mag = magazine("Tempo");
        assertEquals(true, R.call(lib, "addItem", book));
        assertEquals(true, R.call(lib, "addItem", dvd));
        assertEquals(true, R.call(lib, "addItem", mag));
        Object[] items = (Object[]) R.call(lib, "getItems");
        assertEquals(3, items.length, "getItems hanya mengembalikan item yang terisi");
        assertSame(book, items[0]);
        assertSame(dvd, items[1]);
        assertSame(mag, items[2]);
    }

    @Test
    @DisplayName("addItem menolak null dan menolak jika sudah penuh (20 item)")
    void itemCapacity() throws Throwable {
        Object lib = library();
        assertEquals(false, R.call(lib, "addItem", (Object) null));
        for (int i = 1; i <= 20; i++) {
            assertEquals(true, R.call(lib, "addItem", magazine("Issue " + i)));
        }
        assertEquals(false, R.call(lib, "addItem", magazine("One too many")));
        assertEquals(20, ((Object[]) R.call(lib, "getItems")).length);
    }

    @Test
    @DisplayName("addMember menolak id kembar dan null; findMember mengembalikan objek yang sama")
    void members() throws Throwable {
        Object lib = library();
        Member budi = new Member("M001", "Budi");
        assertEquals(true, R.call(lib, "addMember", budi));
        assertEquals(false, R.call(lib, "addMember", new Member("M001", "Orang Lain")));
        assertEquals(false, R.call(lib, "addMember", (Object) null));
        assertEquals(1, ((Object[]) R.call(lib, "getMembers")).length);
        assertSame(budi, R.call(lib, "findMember", "M001"));
        assertNull(R.call(lib, "findMember", "M999"));
    }

    @Test
    @DisplayName("addMember menolak jika sudah penuh (10 anggota)")
    void memberCapacity() throws Throwable {
        Object lib = library();
        for (int i = 1; i <= 10; i++) {
            assertEquals(true, R.call(lib, "addMember", new Member("M" + i, "Member " + i)));
        }
        assertEquals(false, R.call(lib, "addMember", new Member("M11", "Member 11")));
    }

    @Test
    @DisplayName("findItemByTitle tidak peduli huruf besar-kecil; null jika tidak ada")
    void findItem() throws Throwable {
        Object lib = library();
        Book book = new Book("1", "Clean Code", 2008);
        R.call(lib, "addItem", book);
        assertSame(book, R.call(lib, "findItemByTitle", "clean code"));
        assertSame(book, R.call(lib, "findItemByTitle", "CLEAN CODE"));
        assertNull(R.call(lib, "findItemByTitle", "Unknown"));
    }

    @Test
    @DisplayName("searchByKeyword mengembalikan semua item yang judulnya memuat kata kunci")
    void search() throws Throwable {
        Object lib = library();
        Book clean = new Book("1", "Clean Code", 2008);
        Book complete = new Book("2", "Code Complete", 2004);
        Object dvd = dvd("Inception");
        R.call(lib, "addItem", clean);
        R.call(lib, "addItem", dvd);
        R.call(lib, "addItem", complete);
        Object[] found = (Object[]) R.call(lib, "searchByKeyword", "code");
        assertEquals(2, found.length);
        assertSame(clean, found[0]);
        assertSame(complete, found[1]);
        Object[] none = (Object[]) R.call(lib, "searchByKeyword", "zzz");
        assertEquals(0, none.length, "Tanpa hasil, kembalikan array kosong, bukan null");
    }

    @Test
    @DisplayName("lend berhasil: item dipinjam dan jumlah pinjaman Member naik")
    void lendSuccess() throws Throwable {
        Object lib = library();
        Member budi = new Member("M001", "Budi");
        Book book = new Book("1", "Clean Code", 2008);
        R.call(lib, "addMember", budi);
        R.call(lib, "addItem", book);
        assertEquals(true, R.call(lib, "lend", "M001", "Clean Code"));
        assertEquals(false, book.isAvailable());
        assertEquals(1, budi.getLoanCount());
    }

    @Test
    @DisplayName("lend gagal untuk Member atau judul yang tidak ada, dan item yang sudah dipinjam")
    void lendFailures() throws Throwable {
        Object lib = library();
        R.call(lib, "addMember", new Member("M001", "Budi"));
        R.call(lib, "addMember", new Member("M002", "Sari"));
        R.call(lib, "addItem", new Book("1", "Clean Code", 2008));
        assertEquals(false, R.call(lib, "lend", "M999", "Clean Code"));
        assertEquals(false, R.call(lib, "lend", "M001", "Unknown"));
        assertEquals(true, R.call(lib, "lend", "M001", "Clean Code"));
        assertEquals(false, R.call(lib, "lend", "M002", "Clean Code"));
    }

    @Test
    @DisplayName("lend gagal jika Member sudah meminjam 3 item")
    void lendLimit() throws Throwable {
        Object lib = library();
        Member budi = new Member("M001", "Budi");
        R.call(lib, "addMember", budi);
        for (int i = 1; i <= 4; i++) {
            R.call(lib, "addItem", magazine("Issue " + i));
        }
        assertEquals(true, R.call(lib, "lend", "M001", "Issue 1"));
        assertEquals(true, R.call(lib, "lend", "M001", "Issue 2"));
        assertEquals(true, R.call(lib, "lend", "M001", "Issue 3"));
        assertEquals(false, R.call(lib, "lend", "M001", "Issue 4"));
        assertEquals(3, budi.getLoanCount());
        Object fourth = R.call(lib, "findItemByTitle", "Issue 4");
        assertEquals(true, R.call(fourth, "isAvailable"));
    }

    @Test
    @DisplayName("receive berhasil lalu gagal jika item tidak sedang dipinjam atau data tidak ada")
    void receive() throws Throwable {
        Object lib = library();
        Member budi = new Member("M001", "Budi");
        Object dvd = dvd("Inception");
        R.call(lib, "addMember", budi);
        R.call(lib, "addItem", dvd);
        assertEquals(false, R.call(lib, "receive", "M001", "Inception"));
        R.call(lib, "lend", "M001", "Inception");
        assertEquals(true, R.call(lib, "receive", "M001", "Inception"));
        assertEquals(true, R.call(dvd, "isAvailable"));
        assertEquals(0, budi.getLoanCount());
        assertEquals(false, R.call(lib, "receive", "M999", "Inception"));
        assertEquals(false, R.call(lib, "receive", "M001", "Unknown"));
    }

    @Test
    @DisplayName("Library tidak boleh mencetak ke layar (pisahkan logika dan tampilan)")
    void libraryDoesNotPrint() throws Throwable {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer, true, StandardCharsets.UTF_8));
        try {
            Object lib = library();
            R.call(lib, "addMember", new Member("M001", "Budi"));
            R.call(lib, "addItem", new Book("1", "Clean Code", 2008));
            R.call(lib, "lend", "M001", "Clean Code");
            R.call(lib, "receive", "M001", "Clean Code");
            R.call(lib, "searchByKeyword", "code");
            R.call(lib, "getItems");
        } finally {
            System.setOut(original);
        }
        assertTrue(buffer.toString(StandardCharsets.UTF_8).isEmpty(),
                "Library hanya berisi logika. Semua System.out.println ada di LibraryApp");
    }
}

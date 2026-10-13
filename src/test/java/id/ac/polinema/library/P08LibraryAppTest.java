package id.ac.polinema.library;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Menjalankan LibraryApp dengan input skrip (bukan keyboard sungguhan),
 * lalu memeriksa teks yang dicetak.
 */
@Timeout(10)
class P08LibraryAppTest {

    private static Object library() throws Throwable {
        return R.create("Library", "Polinema Library");
    }

    private static String run(Object library, String... lines) throws Throwable {
        String input = String.join("\n", lines) + "\n";
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(buffer, true, StandardCharsets.UTF_8);
        Object app = R.create("LibraryApp", library, new Scanner(input), out);
        R.call(app, "run");
        return buffer.toString(StandardCharsets.UTF_8);
    }

    private static void assertHas(String output, String expected) {
        assertTrue(output.contains(expected),
                "Output harus memuat \"" + expected + "\". Output sebenarnya:\n" + output);
    }

    @Test
    @DisplayName("Menu tampil lengkap dan pilihan 0 mencetak 'Goodbye'")
    void menuAndExit() throws Throwable {
        String out = run(library(), "0");
        assertHas(out, "=== Polinema Library ===");
        for (String line : new String[] {"1. List items", "2. Add book", "3. Add DVD", "4. Add magazine",
                "5. Register member", "6. Lend item", "7. Return item", "8. Search items",
                "9. List members", "0. Exit"}) {
            assertHas(out, line);
        }
        assertHas(out, "Goodbye");
    }

    @Test
    @DisplayName("Pilihan yang tidak dikenal mencetak 'Invalid choice' lalu menu muncul lagi")
    void invalidChoice() throws Throwable {
        String out = run(library(), "x", "42", "0");
        assertHas(out, "Invalid choice");
        assertEquals(3, out.split("0\\. Exit", -1).length - 1, "Menu harus dicetak ulang di setiap putaran");
    }

    @Test
    @DisplayName("Daftar kosong mencetak 'No items' dan 'No members'")
    void emptyLists() throws Throwable {
        String out = run(library(), "1", "9", "0");
        assertHas(out, "No items");
        assertHas(out, "No members");
    }

    @Test
    @DisplayName("Tambah buku lalu tampilkan: describe, status, dan lama pinjam")
    void addBookAndList() throws Throwable {
        Object lib = library();
        String out = run(lib, "2", "978-1", "Clean Code", "2008", "Robert Martin", "USA", "1", "0");
        assertHas(out, "Item added");
        assertHas(out, "Clean Code (2008) by Robert Martin | Available | 14 days");
        assertEquals(1, ((Object[]) R.call(lib, "getItems")).length);
    }

    @Test
    @DisplayName("Tambah buku tanpa penulis: nama penulis dikosongkan")
    void addBookWithoutAuthor() throws Throwable {
        String out = run(library(), "2", "978-2", "Refactoring", "1999", "", "1", "0");
        assertHas(out, "Item added");
        assertHas(out, "Refactoring (1999) by Unknown | Available | 14 days");
    }

    @Test
    @DisplayName("Tambah DVD dan majalah")
    void addDvdAndMagazine() throws Throwable {
        String out = run(library(), "3", "Inception", "2010", "148", "4", "Tempo", "2024", "7", "1", "0");
        assertHas(out, "Inception (2010) [148 min] | Available | 3 days");
        assertHas(out, "Tempo (2024) | Available | 7 days");
    }

    @Test
    @DisplayName("Tahun yang bukan angka positif mencetak 'Invalid number' dan item tidak ditambah")
    void invalidYear() throws Throwable {
        Object lib = library();
        String out = run(lib, "2", "978-1", "Clean Code", "abc", "3", "Inception", "-5", "0");
        assertHas(out, "Invalid number");
        assertEquals(0, ((Object[]) R.call(lib, "getItems")).length);
    }

    @Test
    @DisplayName("ISBN atau judul kosong mencetak 'Invalid input'")
    void blankInput() throws Throwable {
        Object lib = library();
        String out = run(lib, "2", "", "Clean Code", "2008", "0");
        assertHas(out, "Invalid input");
        assertEquals(0, ((Object[]) R.call(lib, "getItems")).length);
    }

    @Test
    @DisplayName("Daftar anggota baru, ID kembar ditolak")
    void registerMember() throws Throwable {
        String out = run(library(), "5", "M001", "Budi", "5", "M001", "Sari", "9", "0");
        assertHas(out, "Member registered");
        assertHas(out, "Member ID already exists");
        assertHas(out, "M001 - Budi (0 loans)");
    }

    @Test
    @DisplayName("Alur lengkap: tambah anggota dan item, pinjam, lihat status, kembalikan")
    void lendAndReturn() throws Throwable {
        Object lib = library();
        String out = run(lib,
                "5", "M001", "Budi",
                "3", "Inception", "2010", "148",
                "6", "M001", "Inception",
                "1", "9",
                "7", "M001", "Inception",
                "1", "9",
                "0");
        assertHas(out, "Loan successful");
        assertHas(out, "Inception (2010) [148 min] | Borrowed | 3 days");
        assertHas(out, "M001 - Budi (1 loans)");
        assertHas(out, "Return successful");
        assertHas(out, "Inception (2010) [148 min] | Available | 3 days");
        assertHas(out, "M001 - Budi (0 loans)");
    }

    @Test
    @DisplayName("Pinjam dan kembalikan yang tidak valid mencetak 'Loan failed' dan 'Return failed'")
    void failures() throws Throwable {
        String out = run(library(), "6", "X", "Y", "7", "X", "Y", "0");
        assertHas(out, "Loan failed");
        assertHas(out, "Return failed");
    }

    @Test
    @DisplayName("Cari item: tampilkan yang cocok, atau 'No items found'")
    void search() throws Throwable {
        Object lib = library();
        R.call(lib, "addItem", new Book("1", "Clean Code", 2008));
        R.call(lib, "addItem", R.create("Dvd", "Inception", 2010, 148));
        String out = run(lib, "8", "code", "8", "zzz", "0");
        assertHas(out, "Clean Code (2008) by Unknown");
        assertHas(out, "No items found");
        assertTrue(!out.contains("Inception (2010)"), "Inception tidak cocok dengan kata kunci 'code'");
    }

    @Test
    @DisplayName("Main mencetak sapaan, lalu menjalankan LibraryApp dengan data contoh")
    void mainStartsTheApp() {
        PrintStream originalOut = System.out;
        InputStream originalIn = System.in;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer, true, StandardCharsets.UTF_8));
        System.setIn(new ByteArrayInputStream("1\n0\n".getBytes(StandardCharsets.UTF_8)));
        try {
            Main.main(new String[0]);
        } finally {
            System.setOut(originalOut);
            System.setIn(originalIn);
        }
        String out = buffer.toString(StandardCharsets.UTF_8);
        assertTrue(out.startsWith("Welcome to Polinema Library"),
                "Baris pertama output Main harus sapaan. Output sebenarnya:\n" + out);
        assertHas(out, "=== Polinema Library ===");
        assertHas(out, "Goodbye");
        assertTrue(!out.contains("No items"),
                "Main harus mengisi data contoh sebelum menjalankan LibraryApp");
    }
}

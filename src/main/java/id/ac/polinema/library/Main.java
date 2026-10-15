package id.ac.polinema.library;

import java.util.Scanner;

/**
 * Titik awal program (satu-satunya method main di proyek ini).
 *
 * Bagian 1: baris PERTAMA output harus persis "Welcome to Polinema Library".
 * Setelah itu kamu bebas menambah baris lain untuk bereksperimen.
 *
 * Bagian 7: di bawah baris sapaan, kamu akan membuat Library, mengisi data
 * contoh, lalu menjalankan LibraryApp. Kerangkanya ada di README.
 */
public class Main {

    public static void main(String[] args) {
        // TODO: Tampilkan sebuah teks ke layar.
        System.out.println("Welcome to Polinema Library");
        // Di Java, perintahnya berbentuk: System.out.println("teks kamu");
        // Ganti teks di dalam tanda kutip dengan teks dari soal. Huruf besar-kecil dan
        // spasi harus persis sama. Tulis perintahnya di dalam kurung kurawal main ini,
        // lalu jalankan programnya.
        //
        // Kerangka:

        // Bagian 7: lanjutkan di sini (buat Library, isi data contoh, jalankan LibraryApp).
        Library library = new Library("Polinema Library");

        // Menambahkan data contoh sesuai instruksi
        Author author = new Author("Robert Martin", "USA");
        Book book = new Book("978-0132350884", "Clean Code", 2008, author);
        library.addItem(book);

        Dvd dvd = new Dvd("Inception", 2010, 148);
        library.addItem(dvd);

        Magazine magazine = new Magazine("Tempo", 2024, 7);
        library.addItem(magazine);

        Member member = new Member("M001", "Budi");
        library.addMember(member);

        // Menjalankan LibraryApp dengan input keyboard (System.in) dan output layar (System.out)
        Scanner scanner = new Scanner(System.in);
        LibraryApp app = new LibraryApp(library, scanner, System.out);
        app.run();
        System.out.println();
    }
}

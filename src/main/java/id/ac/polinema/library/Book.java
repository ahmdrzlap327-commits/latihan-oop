package id.ac.polinema.library;

/**
 * Bagian 2 dan 3: buku perpustakaan.
 *
 * Jangan ubah nama kelas, nama method, atau signature-nya. Autograder
 * memanggilnya persis seperti yang tertulis di sini.
 *
 * Field tidak dideklarasikan di sini: baca diagram kelas di README.
 * Method untuk bagian berikutnya (setAuthor, getAuthor, dan seterusnya) kamu
 * tambahkan sendiri sesuai diagram di bagian tersebut.
 *
 * Kerangka field (lengkapi tipe dan nama dari diagram), letakkan di atas constructor:
 *   public String isbn;
 *   public ____ title;
 *   public ____ year;
 *   public ____ available;
 */
public class Book extends LibraryItem {

    /**
     * Membuat buku baru yang masih tersedia (belum dipinjam).
     * Bagian 3: isbn tidak boleh kosong; title dan year divalidasi seperti setter-nya.
     */
    private String isbn;
    private Author author;

    public Book(String isbn, String title, int year) {
        // TODO Bantuan: isi semua field. Buku baru: apakah sedang dipinjam atau tidak?
        // Bagian 3: aturan validasi judul dan tahun sama dengan setter. Bisakah constructor
        // memakai setter-nya?
        super(title, year);
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN tidak boleh kosong");
        }
        this.isbn = isbn;
        // this.title = title;
        // this.year = year;
        //
        // Kerangka (Bagian 3): ganti dua baris title dan year dengan pemanggilan setter.
    }

    public Book(String isbn, String title, int year, Author author) {
        this(isbn, title, year); // Memanggil constructor 3 parameter milik class ini sendiri
        setAuthor(author);
    }

    @Override
    public int loanDays() {
        return 14; // Overriding: menimpa nilai 7 dari induk menjadi 14
    }

    @Override
    public String describe() {
        String authorName = "Unknown";
        if (getAuthor() != null) {
            authorName = getAuthor().getName();
        }
        // super.describe() mengambil format "Judul (Tahun)" dari LibraryItem
        return super.describe() + " by " + authorName;
    }

    public String getIsbn() {
        // TODO Bantuan: kembalikan data yang disimpan constructor.
        //
        // Kerangka:
          return isbn;
    }

    public String getTitle() {
        // TODO Bantuan: sama seperti getIsbn.
        return title;
    }

    public int getYear() {
        // TODO Bantuan: sama seperti getIsbn.
        return year;
    }

    /** True jika buku sedang ada di perpustakaan (tidak dipinjam). */
    

    /**
     * Meminjam buku. Berhasil (true) jika buku tersedia. Jika buku sedang
     * dipinjam, hasilnya false dan keadaan buku tidak berubah.
     */
    

    /** Mengembalikan buku: buku bisa dipinjam lagi. */

    /**
     * Bagian 3. Mengubah judul. Judul null, kosong, atau hanya spasi ditolak
     * dengan IllegalArgumentException, dan judul lama tetap.
     */
    public void setTitle(String title) {
        // TODO Bantuan: periksa dulu, baru isi field. Class String punya method untuk
        // memeriksa teks kosong atau hanya spasi. Jangan lupa kemungkinan null.
        //
        // Kerangka:
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Judul tidak boleh kosong");
        }
        this.title = title;
    }

    /**
     * Bagian 3. Mengubah tahun terbit. Tahun 0 atau negatif ditolak dengan
     * IllegalArgumentException, dan tahun lama tetap.
     */
    public void setYear(int year) {
        // TODO Bantuan: pola yang sama dengan setTitle. Apa syarat tahun yang valid?
        //
        // Kerangka:
        if (year <= 0) {
            throw new IllegalArgumentException("Tahun harus positif");
        }
        this.year = year;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}

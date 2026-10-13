package id.ac.polinema.library;

/**
 * Bagian 2: penulis buku.
 *
 * Jangan ubah nama kelas, nama method, atau signature-nya. Autograder
 * memanggilnya persis seperti yang tertulis di sini.
 *
 * Field tidak dideklarasikan di sini: baca diagram kelas di README.
 * Di Bagian 2 field boleh public. Di Bagian 3 kamu akan menutupnya.
 *
 * Kerangka field (lengkapi tipe dan nama dari diagram), letakkan di atas constructor:
 *   public ____ name;
 *   public ____ country;
 */
public class Author {
    private String name;
    private String country;

    /** Membuat penulis dengan nama dan negara asal. */
    public Author(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    /** Teks berbentuk "nama (negara)". Contoh: Andrea Hirata (Indonesia). */
    public String getInfo() {
        // TODO Bantuan: gabungkan dua data dengan operator + dan tanda kurung dalam String.
        //
        // Kerangka:
        return name + " (" + country + ")";
    }
}

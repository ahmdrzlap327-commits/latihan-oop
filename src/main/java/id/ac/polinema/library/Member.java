package id.ac.polinema.library;

/**
 * Bagian 3: anggota perpustakaan.
 *
 * Jangan ubah nama kelas, nama method, atau signature-nya. Autograder
 * memanggilnya persis seperti yang tertulis di sini.
 *
 * Field tidak dideklarasikan di sini: baca diagram kelas di README
 * (semua field harus private).
 *
 * Kerangka field (lengkapi dari diagram), letakkan di atas constructor:
 *   private ____ memberId;
 *   private ____ name;
 *   private int loanCount;   // nilai awal 0
 */
public class Member {

    /**
     * Membuat anggota yang belum punya pinjaman.
     * memberId dan name tidak boleh null atau kosong: lempar
     * IllegalArgumentException jika tidak valid.
     */
    public Member(String memberId, String name) {
        // TODO Bantuan: validasi dulu sebelum mengisi field. Aturan nama sama dengan setName.
        // Bagian 4: kartu anggota juga dibuat di sini (lihat diagram Bagian 4).
        //
        // Kerangka:
        //   if (memberId == null || ____) {
        //       throw new IllegalArgumentException("____");
        //   }
        //   this.memberId = memberId;
        //   setName(____);
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public String getMemberId() {
        // TODO Bantuan: kembalikan data yang disimpan constructor.
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public String getName() {
        // TODO Bantuan: sama seperti getMemberId.
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /** Nama tidak boleh null atau kosong: lempar IllegalArgumentException dan biarkan nama lama. */
    public void setName(String name) {
        // TODO Bantuan: periksa dulu, baru isi field.
        //
        // Kerangka:
        //   if (name == null || ____) {
        //       throw new IllegalArgumentException("____");
        //   }
        //   this.name = name;
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public int getLoanCount() {
        // TODO Bantuan: kembalikan jumlah pinjaman saat ini.
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /** True jika anggota masih boleh meminjam (batasnya 3 pinjaman). */
    public boolean canBorrow() {
        // TODO Bantuan: apakah perlu field baru, atau cukup dihitung dari data yang sudah ada?
        //
        // Kerangka:
        //   return loanCount ____ 3;
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /** Menambah jumlah pinjaman. Jika sudah di batas, tidak berubah dan hasilnya false. */
    public boolean addLoan() {
        // TODO Bantuan: periksa batas dulu. Adakah method lain di kelas ini yang sudah
        // menjawab pertanyaan "boleh atau tidak"?
        //
        // Kerangka:
        //   if (!____) {
        //       return false;
        //   }
        //   loanCount++;
        //   return true;
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /** Mengurangi jumlah pinjaman. Jika sudah tidak ada pinjaman, tidak berubah dan hasilnya false. */
    public boolean returnLoan() {
        // TODO Bantuan: batas bawah jumlah pinjaman adalah berapa?
        //
        // Kerangka:
        //   if (loanCount == ____) {
        //       return false;
        //   }
        //   loanCount--;
        //   return true;
        throw new UnsupportedOperationException("Not implemented yet");
    }
}

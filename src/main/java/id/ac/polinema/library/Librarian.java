package id.ac.polinema.library;

public class Librarian {
    public boolean lend(Member member, Book book) {
        // Guard clause: tolak jika member tidak bisa pinjam atau buku tidak tersedia
        if (!member.canBorrow() || !book.isAvailable()) {
            return false;
        }
        book.checkOut();
        member.addLoan();
        return true;
    }

    public boolean receive(Member member, Book book) {
        // Guard clause: tolak jika buku masih tersedia (belum dipinjam) atau member tidak punya pinjaman
        if (book.isAvailable() || member.getLoanCount() == 0) {
            return false;
        }
        book.returnItem();
        member.returnLoan();
        return true;
    }
}

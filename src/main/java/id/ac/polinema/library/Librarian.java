package id.ac.polinema.library;

public class Librarian {
    public boolean lend(Member member, Book book) {
        // Guard clause: tolak jika member tidak bisa pinjam atau buku tidak tersedia
        if (member == null || book == null) {
            return false;
        }
        if (member.canBorrow() && book.isAvailable()) {
            book.checkOut();
            member.addLoan();
            return true;
        }
        return false;
    }

    public boolean receive(Member member, Book book) {
        // Guard clause: tolak jika buku masih tersedia (belum dipinjam) atau member tidak punya pinjaman
        if (member == null || book == null) {
            return false;
        }
        if (!book.isAvailable() && member.getLoanCount() > 0) {
            book.returnItem();
            member.returnLoan();
            return true;
        }
        return false;
    }
}

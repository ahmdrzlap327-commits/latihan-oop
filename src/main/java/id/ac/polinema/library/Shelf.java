package id.ac.polinema.library;

public class Shelf {
    private String code;
    private Book[] books;
    private int count;

    public Shelf(String code) {
        this.code = code;
        this.books = new Book[5];
        this.count = 0;
    }

    public String getCode() {
        return code;
    }

    public int getBookCount() {
        return count;
    }

    public boolean addBook(Book book) {
        // Guard clause: tolak jika buku null atau rak penuh
        if (book == null || count == 5) {
            return false;
        }
        books[count] = book;
        count++;
        return true;
    }

    public Book findByIsbn(String isbn) {
        // Telusuri hanya sampai batas count
        for (int i = 0; i < count; i++) {
            if (books[i].getIsbn().equals(isbn)) {
                return books[i];
            }
        }
        return null;
    }

    public int countAvailable() {
        int availableCount = 0;
        for (int i = 0; i < count; i++) {
            if (books[i].isAvailable()) {
                availableCount++;
            }
        }
        return availableCount;
    }
}

package id.ac.polinema.library;

public class LibraryItem {
    protected String title;
    protected int year;
    private boolean available;
    private int extraDays;

    public LibraryItem(String title, int year) {
        setTitle(title);
        setYear(year);
        this.available = true;
    }

    public String getTitle() { 
        return title; 
    }

    public int loanDays() {
        return 7; // Standar peminjaman 7 hari
    }

    public String describe() {
        return title + " (" + year + ")";
    }

    public String toString() {
        return describe(); // Memanfaatkan metode describe() yang sudah ada
    }

    public void extendLoan(int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Perpanjangan harus positif");
        }
        this.extraDays += days;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Judul tidak boleh kosong");
        }
        this.title = title;
    }

    public int getYear() { return year; }

    public void setYear(int year) {
        if (year <= 0) {
            throw new IllegalArgumentException("Tahun harus positif");
        }
        this.year = year;
    }

    public void extendLoan() {
        extendLoan(7);
    }

    public int getTotalLoanDays() {
        return loanDays() + extraDays; 
    }

    public boolean isAvailable() { 
        return available; 
    }

    public boolean checkOut() {
        if (!available) {
            return false;
        }
        available = false;
        return true;
    }

    public void returnItem() {
        available = true;
    }
}

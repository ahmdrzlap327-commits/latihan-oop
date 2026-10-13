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
        this.extraDays = 0;
    }

    public String getTitle() { 
        return title; 
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

    public int loanDays() {
        return 7;
    }

    public String describe() {
        return title + " (" + year + ")";
    }

    public String toString() {
        return describe();
    }

    public void extendLoan(int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Hari perpanjangan harus positif");
        }
        this.extraDays += days;
    }

    public void extendLoan() {
        extendLoan(7);
    }

    public int getTotalLoanDays() {
        return loanDays() + extraDays;
    }
}
package id.ac.polinema.library;

public class Dvd extends LibraryItem {
    private int durationMinutes;

    public Dvd(String title, int year, int durationMinutes) {
        super(title, year);
        if (durationMinutes <= 0) {
            throw new IllegalArgumentException("Durasi harus positif");
        }
        this.durationMinutes = durationMinutes;
    }

    public int getDurationMinutes() { 
        return durationMinutes; 
    }

    @Override
    public int loanDays() {
        return 3;
    }

    @Override
    public String describe() {
        return super.describe() + " [" + getDurationMinutes() + " min]";
    }
}

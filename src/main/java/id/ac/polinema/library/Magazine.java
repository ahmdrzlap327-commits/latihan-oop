package id.ac.polinema.library;

public class Magazine extends LibraryItem {
    private int issueNumber;

    public Magazine(String title, int year, int issueNumber) {
        super(title, year);
        if (issueNumber <= 0) {
            throw new IllegalArgumentException("Nomor edisi harus positif");
        }
        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() { 
        return issueNumber; 
    }

    public Magazine(String title, int year) {
        this(title, year, 1); 
    }
}

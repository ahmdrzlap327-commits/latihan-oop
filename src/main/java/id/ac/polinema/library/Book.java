package id.ac.polinema.library;

public class Book extends LibraryItem {

    private String isbn;
    private Author author;

    public Book(String isbn, String title, int year) {
        super(title, year);
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN tidak boleh kosong");
        }
        this.isbn = isbn;
    }

    public Book(String isbn, String title, int year, Author author) {
        this(isbn, title, year);
        setAuthor(author);
    }

    @Override
    public int loanDays() {
        return 14; 
    }

    @Override
    public String describe() {
        String authorName = "Unknown";
        if (getAuthor() != null) {
            authorName = getAuthor().getName();
        }
        return super.describe() + " by " + authorName;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
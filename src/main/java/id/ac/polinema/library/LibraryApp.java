package id.ac.polinema.library;

import java.io.PrintStream;
import java.util.Scanner;

public class LibraryApp {

    private Library library;
    private Scanner in;
    private PrintStream out;

    public LibraryApp(Library library, Scanner in, PrintStream out) {
        this.library = library;
        this.in = in;
        this.out = out;
    }

    // Perulangan tanpa akhir: menu tampil lagi dan lagi sampai pengguna memilih 0.
    public void run() {
        while (true) {
            printMenu();
            if (!in.hasNextLine()) {      // input habis: berhenti, jangan crash
                return;
            }
            String choice = in.nextLine().trim();
            switch (choice) {
                case "1": showItems(); break;
                case "2": addBook(); break;
                case "3": addDvd(); break;
                case "4": addMagazine(); break;
                case "5": registerMember(); break;
                case "6": lendItem(); break;
                case "7": returnItem(); break;
                case "8": searchItems(); break;
                case "9": showMembers(); break;
                case "0":
                    out.println("Goodbye");
                    return;               // keluar dari run, perulangan berhenti
                default:
                    out.println("Invalid choice");
            }
        }
    }

    // Mencetak prompt (tanpa pindah baris), lalu membaca satu baris. Spasi di ujung dibuang.
    private String readLine(String prompt) {
        out.print(prompt);
        if (!in.hasNextLine()) {
            return "";
        }
        return in.nextLine().trim();
    }

    // Membaca bilangan bulat positif. Mengembalikan -1 jika isinya bukan bilangan positif.
    private int readInt(String prompt) {
        String text = readLine(prompt);
        if (text.isEmpty() || text.length() > 9) {
            return -1;
        }
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isDigit(text.charAt(i))) {
                return -1;
            }
        }
        int value = Integer.parseInt(text);
        return value > 0 ? value : -1;
    }

    private void printMenu() {
        out.println("=== " + library.getName() + " ===");
        out.println("1. List items");
        out.println("2. Add book");
        out.println("3. Add DVD");
        out.println("4. Add magazine");
        out.println("5. Register member");
        out.println("6. Lend item");
        out.println("7. Return item");
        out.println("8. Search items");
        out.println("9. List members");
        out.println("0. Exit");
        out.print("Choose: ");
    }

    // Satu baris untuk satu koleksi. Dipakai oleh dua menu berbeda, lihat Bantuan.
    private String line(LibraryItem item) {
        String status = item.isAvailable() ? "Available" : "Borrowed";
        return item.toString() + " | " + status + " | " + item.loanDays() + " days";
    }

    private void showItems() {
        LibraryItem[] items = library.getItems();
        if (items.length == 0) {
            out.println("No items");
            return;
        }
        for (LibraryItem item : items) {
            out.println(line(item));
        }
    }

    private void addBook() {
        String isbn = readLine("ISBN: ");
        String title = readLine("Title: ");
        int year = readInt("Year: ");
        
        if (isbn.isEmpty() || title.isEmpty()) {
            out.println("Invalid input");
            return;
        }
        if (year <= 0) {
            out.println("Invalid number");
            return;
        }

        String authorName = readLine("Author name (blank for none): ");
        Author author = null;
        if (!authorName.isEmpty()) {
            String authorCountry = readLine("Author country: ");
            author = new Author(authorName, authorCountry);
        }

        Book book = new Book(isbn, title, year, author);
        if (library.addItem(book)) {
            out.println("Item added");
        } else {
            out.println("Library is full");
        }
    }

    private void addDvd() {
        String title = readLine("Title: ");
        int year = readInt("Year: ");
        int duration = readInt("Duration (minutes): ");

        if (title.isEmpty()) {
            out.println("Invalid input");
            return;
        }
        if (year <= 0 || duration <= 0) {
            out.println("Invalid number");
            return;
        }

        Dvd dvd = new Dvd(title, year, duration);
        if (library.addItem(dvd)) {
            out.println("Item added");
        } else {
            out.println("Library is full");
        }
    }

    private void addMagazine() {
        String title = readLine("Title: ");
        int year = readInt("Year: ");
        int issue = readInt("Issue number: ");

        if (title.isEmpty()) {
            out.println("Invalid input");
            return;
        }
        if (year <= 0 || issue <= 0) {
            out.println("Invalid number");
            return;
        }

        Magazine magazine = new Magazine(title, year, issue);
        if (library.addItem(magazine)) {
            out.println("Item added");
        } else {
            out.println("Library is full");
        }
    }

    private void registerMember() {
        String id = readLine("Member ID: ");
        String name = readLine("Name: ");

        if (id.isEmpty() || name.isEmpty()) {
            out.println("Invalid input");
            return;
        }

        Member member = new Member(id, name);
        if (library.addMember(member)) {
            out.println("Member registered");
        } else {
            out.println("Member ID already exists");
        }
    }

    private void lendItem() {
        String memberId = readLine("Member ID: ");
        String title = readLine("Title: ");

        if (library.lend(memberId, title)) {
            out.println("Loan successful");
        } else {
            out.println("Loan failed");
        }
    }

    private void returnItem() {
        String memberId = readLine("Member ID: ");
        String title = readLine("Title: ");

        if (library.receive(memberId, title)) {
            out.println("Return successful");
        } else {
            out.println("Return failed");
        }
    }

    private void searchItems() {
        String keyword = readLine("Keyword: ");
        LibraryItem[] results = library.searchByKeyword(keyword);
        if (results.length == 0) {
            out.println("No items found");
            return;
        }
        for (LibraryItem item : results) {
            out.println(line(item));
        }
    }

    private void showMembers() {
        Member[] members = library.getMembers();
        if (members.length == 0) {
            out.println("No members");
            return;
        }
        for (Member m : members) {
            out.println(m.getMemberId() + " - " + m.getName() + " (" + m.getLoanCount() + " loans)");
        }
    }
}
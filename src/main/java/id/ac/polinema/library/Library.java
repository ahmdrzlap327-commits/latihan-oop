package id.ac.polinema.library;

import java.util.Arrays;

public class Library {
    private String name;
    private LibraryItem[] items;
    private int itemCount;
    private Member[] members;
    private int memberCount;

    public Library(String name) {
        this.name = name;
        this.items = new LibraryItem[20]; // Kapasitas maksimal 20 item
        this.members = new Member[10];    // Kapasitas maksimal 10 member
        this.itemCount = 0;
        this.memberCount = 0;
    }

    public String getName() {
        return name;
    }

    public boolean addItem(LibraryItem item) {
        // Gagal jika item null atau array sudah penuh (20)
        if (item == null || itemCount == 20) {
            return false;
        }
        items[itemCount] = item;
        itemCount++;
        return true;
    }

    public boolean addMember(Member member) {
        // Gagal jika null atau array penuh (10)
        if (member == null || memberCount == 10) {
            return false;
        }
        // Gagal jika ID sudah terdaftar
        if (findMember(member.getMemberId()) != null) {
            return false;
        }
        members[memberCount] = member;
        memberCount++;
        return true;
    }

    public LibraryItem[] getItems() {
        // Mengembalikan array baru yang hanya berisi elemen terisi
        return Arrays.copyOf(items, itemCount);
    }

    public Member[] getMembers() {
        // Mengembalikan array baru yang hanya berisi elemen terisi
        return Arrays.copyOf(members, memberCount);
    }

    public LibraryItem findItemByTitle(String title) {
        for (int i = 0; i < itemCount; i++) {
            // Pencocokan judul mengabaikan huruf besar-kecil
            if (items[i].getTitle().equalsIgnoreCase(title)) {
                return items[i];
            }
        }
        return null; // Tidak ketemu
    }

    public Member findMember(String memberId) {
        for (int i = 0; i < memberCount; i++) {
            // ID dicocokkan sama persis (case-sensitive)
            if (members[i].getMemberId().equals(memberId)) {
                return members[i];
            }
        }
        return null; // Tidak ketemu
    }

    public LibraryItem[] searchByKeyword(String keyword) {
        // Buat array sementara sebesar jumlah item yang ada
        LibraryItem[] temp = new LibraryItem[itemCount];
        int matchCount = 0;
        String lowerKeyword = keyword.toLowerCase();
        
        for (int i = 0; i < itemCount; i++) {
            // Cek apakah judul mengandung (contains) kata kunci, abaikan besar-kecil huruf
            if (items[i].getTitle().toLowerCase().contains(lowerKeyword)) {
                temp[matchCount] = items[i];
                matchCount++;
            }
        }
        // Potong array sementara agar ukurannya pas dengan jumlah yang ketemu
        return Arrays.copyOf(temp, matchCount);
    }

    public boolean lend(String memberId, String title) {
        Member member = findMember(memberId);
        LibraryItem item = findItemByTitle(title);
        
        // Cek semua syarat: objek ada, boleh pinjam, dan item tersedia
        if (member == null || item == null) {
            return false;
        }
        
        // Memanfaatkan Librarian atau aturan langsung
        if (member.canBorrow() && item.isAvailable()) {
            item.checkOut();
            member.addLoan();
            return true;
        }
        return false;
    }

    public boolean receive(String memberId, String title) {
        Member member = findMember(memberId);
        LibraryItem item = findItemByTitle(title);
        
        // Cek semua syarat: objek ada, item sedang dipinjam, dan member punya pinjaman
        if (member != null && item != null && !item.isAvailable() && member.getLoanCount() > 0) {
            item.returnItem();
            member.returnLoan();
            return true;
        }
        return false;
    }
}
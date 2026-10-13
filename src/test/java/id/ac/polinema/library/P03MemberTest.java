package id.ac.polinema.library;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class P03MemberTest {

    @Test
    @DisplayName("Semua field Member private")
    void fieldsPrivate() {
        R.assertAllFieldsPrivate(Member.class);
    }

    @Test
    @DisplayName("Constructor menyimpan id dan nama; jumlah pinjaman awal 0")
    void constructor() {
        Member m = new Member("M001", "Budi");
        assertEquals("M001", m.getMemberId());
        assertEquals("Budi", m.getName());
        assertEquals(0, m.getLoanCount());
    }

    @Test
    @DisplayName("Constructor menolak id atau nama null/kosong")
    void constructorValidates() {
        assertThrows(IllegalArgumentException.class, () -> new Member("", "Budi"));
        assertThrows(IllegalArgumentException.class, () -> new Member(null, "Budi"));
        assertThrows(IllegalArgumentException.class, () -> new Member("M001", "  "));
        assertThrows(IllegalArgumentException.class, () -> new Member("M001", null));
    }

    @Test
    @DisplayName("memberId dan loanCount read-only: tidak ada setter")
    void readOnlyFields() {
        assertFalse(R.hasPublicMethod(Member.class, "setMemberId"));
        assertFalse(R.hasPublicMethod(Member.class, "setLoanCount"));
    }

    @Test
    @DisplayName("setName menolak nama kosong dan menjaga nama lama")
    void setName() {
        Member m = new Member("M001", "Budi");
        assertThrows(IllegalArgumentException.class, () -> m.setName(""));
        assertThrows(IllegalArgumentException.class, () -> m.setName(null));
        assertEquals("Budi", m.getName());
        m.setName("Budi Santoso");
        assertEquals("Budi Santoso", m.getName());
    }

    @Test
    @DisplayName("addLoan sampai batas 3; pinjaman ke-4 ditolak")
    void addLoanLimit() {
        Member m = new Member("M001", "Budi");
        assertTrue(m.addLoan());
        assertTrue(m.addLoan());
        assertTrue(m.addLoan());
        assertFalse(m.addLoan());
        assertEquals(3, m.getLoanCount());
    }

    @Test
    @DisplayName("canBorrow dihitung dari jumlah pinjaman (derived getter)")
    void canBorrowIsDerived() {
        Member m = new Member("M001", "Budi");
        assertTrue(m.canBorrow());
        m.addLoan();
        m.addLoan();
        assertTrue(m.canBorrow());
        m.addLoan();
        assertFalse(m.canBorrow());
        m.returnLoan();
        assertTrue(m.canBorrow());
    }

    @Test
    @DisplayName("returnLoan tidak boleh membuat jumlah pinjaman negatif")
    void returnLoanFloor() {
        Member m = new Member("M001", "Budi");
        assertFalse(m.returnLoan());
        assertEquals(0, m.getLoanCount());
        m.addLoan();
        assertTrue(m.returnLoan());
        assertEquals(0, m.getLoanCount());
    }
}

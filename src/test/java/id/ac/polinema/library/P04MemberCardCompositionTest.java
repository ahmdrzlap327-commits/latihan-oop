package id.ac.polinema.library;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

/** Komposisi: LibraryCard dibuat di dalam Member dan tidak datang dari luar. */
class P04MemberCardCompositionTest {

    @Test
    @DisplayName("LibraryCard ada dan field-nya private")
    void cardClass() {
        R.assertAllFieldsPrivate(R.cls("LibraryCard"));
    }

    @Test
    @DisplayName("Member punya kartu bernomor 'CARD-' + memberId")
    void memberHasCard() throws Throwable {
        Member m = new Member("M001", "Budi");
        Object card = R.call(m, "getCard");
        assertNotNull(card, "Member harus membuat LibraryCard sendiri di constructor");
        assertEquals("CARD-M001", R.call(card, "getNumber"));
    }

    @Test
    @DisplayName("getCard selalu mengembalikan kartu yang sama untuk satu Member")
    void sameCardEveryTime() throws Throwable {
        Member m = new Member("M001", "Budi");
        assertSame(R.call(m, "getCard"), R.call(m, "getCard"));
    }

    @Test
    @DisplayName("Dua Member punya dua kartu berbeda")
    void differentMembersDifferentCards() throws Throwable {
        Member a = new Member("M001", "Budi");
        Member b = new Member("M002", "Sari");
        assertNotSame(R.call(a, "getCard"), R.call(b, "getCard"));
        assertEquals("CARD-M002", R.call(R.call(b, "getCard"), "getNumber"));
    }

    @Test
    @DisplayName("Constructor Member tidak menerima LibraryCard dari luar (komposisi)")
    void cardIsNotPassedIn() {
        Class<?> card = R.cls("LibraryCard");
        for (Constructor<?> k : Member.class.getConstructors()) {
            for (Class<?> p : k.getParameterTypes()) {
                org.junit.jupiter.api.Assertions.assertNotEquals(card, p,
                        "Member harus MEMBUAT LibraryCard sendiri, bukan menerimanya dari parameter");
            }
        }
    }
}

package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class NameTest {

    // Test valid names
    @Test
    void testValidName() {
        Name name = new Name("John");
        assertEquals("John", name.getName());
    }

    @Test
    void testValidNameWithAccents() {
        Name name = new Name("José");
        assertEquals("José", name.getName());
    }

    @Test
    void testValidNameWithSpaces() {
        Name name = new Name("John Galliano");
        assertEquals("John Galliano", name.getName());
    }

    @Test
    void testValidNameWithHyphens() {
        Name name = new Name("Jean-Pierre");
        assertEquals("Jean-Pierre", name.getName());
    }

    @Test
    void testValidNameWithApostrophes() {
        Name name = new Name("John O'Brien");
        assertEquals("John O'Brien", name.getName());
    }


    // Test invalid names
    @Test
    void testNullName() {
        assertThrows(IllegalArgumentException.class, () -> new Name(null));
    }

    @Test
    void testEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> new Name(""));
    }

    @Test
    void testBlankName() {
        assertThrows(IllegalArgumentException.class, () -> new Name("   "));
    }

    @Test
    void testNameTooShort() {
        assertThrows(IllegalArgumentException.class, () -> new Name("Jo"));
    }

    @Test
    void testNameTooLong() {
        String longName = "A".repeat(101); // String with 101 characters
        assertThrows(IllegalArgumentException.class, () -> new Name(longName));
    }

    @Test
    void testNameWithInvalidCharacter() {
        assertThrows(IllegalArgumentException.class, () -> new Name("J@hn"));
    }

    @Test
    void testNameWithInvalidCharacter2() {
        assertThrows(IllegalArgumentException.class, () -> new Name("John!"));
    }

    @Test
    void testNameWithNumbers() {
        assertThrows(IllegalArgumentException.class, () -> new Name("John123"));
    }

    @Test
    void testNameStartsWithLowerCase() {
        assertThrows(IllegalArgumentException.class, () -> new Name("john"));
    }

    @Test
    void testNameStartsWithHyphen() {
        assertThrows(IllegalArgumentException.class, () -> new Name("-John"));
    }

    @Test
    void testNameStartsWithApostrophe() {
        assertThrows(IllegalArgumentException.class, () -> new Name("'John"));
    }


    // Test trimming functionality
    @Test
    void testNameWithSpacesBefore() {
        Name name = new Name("  John");
        assertEquals("John", name.getName());
    }

    @Test
    void testNameWithSpacesAfter() {
        Name name = new Name("John  ");
        assertEquals("John", name.getName());
    }

    @Test
    void testNameWithSpacesBeforeAndAfter() {
        Name name = new Name("  John  ");
        assertEquals("John", name.getName());
    }
}


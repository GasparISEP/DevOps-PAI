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

    @Test
    void testGivenValidNameWithMinimumLength_whenCreatingName_thenSuccess() {
        // Name with exactly 3 characters should be valid
        assertDoesNotThrow(() -> new Name("Ana"));
    }

    @Test
    void testGivenValidNameWithMaximumLength_whenCreatingName_thenSuccess() {
        // Name with exactly 100 characters should be valid
        assertDoesNotThrow(() -> new Name("A".repeat(100)));
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
        // Name shorter than 3 characters should throw an exception
        assertThrows(IllegalArgumentException.class, () -> new Name("Jo"));
    }

    @Test
    void testNameTooLong() {
        // Name longer than 100 characters should throw an exception
        assertThrows(IllegalArgumentException.class, () -> new Name("A".repeat(101)));
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

    // Tests for equals() method
    @Test
    void testEquals_SameName() {
        // Test to check if two Name objects with the same name are equal
        Name name1 = new Name("John Galliano");
        Name name2 = new Name("John Galliano");
        assertEquals(name1, name2);
    }

    @Test
    void testEquals_DifferentName() {
        // Test to check if two Name objects with different names are not equal
        Name name1 = new Name("John Galliano");
        Name name2 = new Name("Jane Galliano");
        assertNotEquals(name1, name2);
    }

    @Test
    void testEquals_SameName_DifferentInstance() {
        // Test to check if two Name objects with the same name, but different instances, are equal
        Name name1 = new Name("John Galliano");
        Name name2 = new Name("John Galliano");
        assertTrue(name1.equals(name2));
    }

    @Test
    void testEquals_DifferentType() {
        // Test to check if equals() returns false when comparing with an object of a different type
        Name name = new Name("John Galliano");
        String otherObject = "John Galliano";
        assertFalse(name.equals(otherObject));
    }

    @Test
    void testEquals_SameObject() {
        // Test to check if equals() returns true when comparing with the same object
        Name name = new Name("John Galliano");
        assertTrue(name.equals(name));
    }


    // Tests for hashCode() method
    @Test
    void testHashCode_SameName() {
        // Test to check if hashCode() generates the same value for equal Name objects
        Name name1 = new Name("John Galliano");
        Name name2 = new Name("John Galliano");
        assertEquals(name1.hashCode(), name2.hashCode());
    }

    @Test
    void testHashCode_DifferentName() {
        // Test to check if hashCode() generates different values for Name objects with different names
        Name name1 = new Name("John Galliano");
        Name name2 = new Name("Jane Galliano");
        assertNotEquals(name1.hashCode(), name2.hashCode());
    }
}


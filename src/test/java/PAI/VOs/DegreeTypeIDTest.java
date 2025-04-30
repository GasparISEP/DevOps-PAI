package PAI.VOs;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class DegreeTypeIDTest {

    @Test
    void shouldCreateWithGeneratedID() {
        DegreeTypeID id1 = new DegreeTypeID();
        DegreeTypeID id2 = new DegreeTypeID();
        assertNotNull(id1.getDTID(), "Generated ID should not be null");
        assertNotEquals(id1.getDTID(), id2.getDTID(), "Generated IDs should be unique");
    }

    @Test
    void shouldStoreProvidedID() {
        String expected = "abc-123";
        DegreeTypeID id = new DegreeTypeID(expected);
        assertEquals(expected, id.getDTID(), "Provided ID should be stored correctly");
    }

    @Test
    void shouldThrowExceptionWhenIDIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new DegreeTypeID(null));
        assertEquals("ID cannot be null or blank.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenIDIsBlank() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new DegreeTypeID("  "));
        assertEquals("ID cannot be null or blank.", exception.getMessage());
    }

    @Test
    void shouldRecognizeEqualObjects() {
        DegreeTypeID id1 = new DegreeTypeID("same-id");
        DegreeTypeID id2 = new DegreeTypeID("same-id");
        assertEquals(id1, id2, "Objects with same ID should be equal");
        assertEquals(id1.hashCode(), id2.hashCode(), "Equal objects must have same hash code");
    }

    @Test
    void shouldRecognizeDifferentObjects() {
        DegreeTypeID id1 = new DegreeTypeID("id-1");
        DegreeTypeID id2 = new DegreeTypeID("id-2");
        assertNotEquals(id1, id2, "Objects with different IDs should not be equal");
        assertNotEquals(id1.hashCode(), id2.hashCode(), "Different objects may have different hash codes");
    }

    @Test
    void shouldReturnFalseWhenComparingWithNullInEquals() {
        DegreeTypeID id = new DegreeTypeID("some-id");
        assertNotEquals(null, id, "Object should not be equal to null");
    }

    @Test
    void shouldReturnFalseWhenComparingWithDifferentType() {
        DegreeTypeID id = new DegreeTypeID("type-id");
        assertNotEquals(id, "some string", "Object should not be equal to a different type");
    }

    @Test
    void shouldBeSameAsAnotherWithSameID() {
        DegreeTypeID id1 = new DegreeTypeID("shared-id");
        DegreeTypeID id2 = new DegreeTypeID("shared-id");
        assertTrue(id1.sameAs(id2), "sameAs should return true for same ID");
    }

    @Test
    void shouldNotBeSameAsWithDifferentID() {
        DegreeTypeID id1 = new DegreeTypeID("id1");
        DegreeTypeID id2 = new DegreeTypeID("id2");
        assertFalse(id1.sameAs(id2), "sameAs should return false for different IDs");
    }

    @Test
    void shouldNotBeSameAsNull() {
        DegreeTypeID id = new DegreeTypeID("id");
        assertFalse(id.sameAs(null), "sameAs should return false when compared with null");
    }

    @Test
    void toStringShouldContainID() {
        DegreeTypeID id = new DegreeTypeID("visible-id");
        String str = id.toString();
        assertTrue(str.contains("visible-id"), "toString should include the ID");
        assertTrue(str.contains("DegreeTypeID"), "toString should contain class name");
    }

    @Test
    void sameAsShouldReturnTrueForSameID() {
        DegreeTypeID id1 = new DegreeTypeID("abc");
        DegreeTypeID id2 = new DegreeTypeID("abc");

        assertTrue(id1.sameAs(id2));
    }

    @Test
    void sameAsShouldReturnFalseForDifferentIDs() {
        DegreeTypeID id1 = new DegreeTypeID("abc");
        DegreeTypeID id2 = new DegreeTypeID("xyz");

        assertFalse(id1.sameAs(id2));
    }

    @Test
    void sameAsShouldReturnFalseWhenOtherIsNull() {
        DegreeTypeID id = new DegreeTypeID("abc");

        assertFalse(id.sameAs(null));
    }

    @Test
    void equalsShouldReturnFalseForDifferentType() {
        DegreeTypeID id = new DegreeTypeID("abc");
        assertNotEquals(id, "abc");
    }

    @Test
    void hashCodeShouldMatchExpected() {
        DegreeTypeID id = new DegreeTypeID("abc");
        int expected = Objects.hash("abc");
        assertEquals(expected, id.hashCode());
    }

    @Test
    void toStringShouldReturnExpectedFormat() {
        DegreeTypeID id = new DegreeTypeID("abc");
        String result = id.toString();

        assertTrue(result.contains("abc"));
        assertTrue(result.contains("DegreeTypeID"));
    }

    @Test
    void shouldReturnTrueWhenComparingSameInstance() {
        DegreeTypeID id = new DegreeTypeID("same");
        assertEquals(id, id, "equals should return true when comparing the same instance");
    }
}
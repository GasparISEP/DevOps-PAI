package PAI.VOs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DegreeType_IDTest {

    @Test
    void shouldStoreProvidedID() throws Exception {
        String customID = "123e4567-e89b-12d3-a456-426614174000";
        DegreeTypeID id = new DegreeTypeID(customID);
        assertEquals(customID, id.getDTID(), "The stored ID does not match the expected one.");
    }

    @Test
    void shouldThrowExceptionForNullOrEmptyID() {
        assertThrows(Exception.class, () -> new DegreeTypeID(null), "Should throw exception for a null ID.");
        assertThrows(Exception.class, () -> new DegreeTypeID(""), "Should throw exception for a blank ID.");
        assertThrows(Exception.class, () -> new DegreeTypeID("   "), "Should throw exception for an ID with blank spaces.");
    }

    @Test
    void shouldRecognizeEqualIDsUsingSameAs() throws Exception {
        String sharedID = "550e8400-e29b-41d4-a716-446655440000";
        DegreeTypeID id1 = new DegreeTypeID(sharedID);
        DegreeTypeID id2 = new DegreeTypeID(sharedID);

        assertTrue(id1.sameAs(id2), "The objects must be considered equal.");
    }

    @Test
    void shouldReturnFalseForDifferentIDsUsingSameAs() throws Exception {
        DegreeTypeID id1 = new DegreeTypeID("123e4567-e89b-12d3-a456-426614174000");
        DegreeTypeID id2 = new DegreeTypeID("550e8400-e29b-41d4-a716-446655440000");

        assertFalse(id1.sameAs(id2), "Objects with different IDs must not be considered equal.");
    }

    @Test
    void shouldReturnFalseForNullSameAs() throws Exception {
        DegreeTypeID id = new DegreeTypeID("123e4567-e89b-12d3-a456-426614174000");
        assertFalse(id.sameAs(null), "The comparison with null should return false.");
    }
}
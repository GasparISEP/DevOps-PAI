package PAI.VOs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DegreeType_IDTest {

    @Test
    void shouldStoreProvidedID() throws Exception {
        String customID = "123e4567-e89b-12d3-a456-426614174000";
        DegreeType_ID id = new DegreeType_ID(customID);
        assertEquals(customID, id.getDTID(), "The stored ID does not match the expected one.");
    }

    @Test
    void shouldThrowExceptionForNullOrEmptyID() {
        assertThrows(Exception.class, () -> new DegreeType_ID(null), "Should throw exception for a null ID.");
        assertThrows(Exception.class, () -> new DegreeType_ID(""), "Should throw exception for a blank ID.");
        assertThrows(Exception.class, () -> new DegreeType_ID("   "), "Should throw exception for an ID with blank spaces.");
    }

    @Test
    void shouldRecognizeEqualIDsUsingSameAs() throws Exception {
        String sharedID = "550e8400-e29b-41d4-a716-446655440000";
        DegreeType_ID id1 = new DegreeType_ID(sharedID);
        DegreeType_ID id2 = new DegreeType_ID(sharedID);

        assertTrue(id1.sameAs(id2), "The objects must be considered equal.");
    }

    @Test
    void shouldReturnFalseForDifferentIDsUsingSameAs() throws Exception {
        DegreeType_ID id1 = new DegreeType_ID("123e4567-e89b-12d3-a456-426614174000");
        DegreeType_ID id2 = new DegreeType_ID("550e8400-e29b-41d4-a716-446655440000");

        assertFalse(id1.sameAs(id2), "Objects with different IDs must not be considered equal.");
    }

    @Test
    void shouldReturnFalseForNullSameAs() throws Exception {
        DegreeType_ID id = new DegreeType_ID("123e4567-e89b-12d3-a456-426614174000");
        assertFalse(id.sameAs(null), "The comparison with null should return false.");
    }
}
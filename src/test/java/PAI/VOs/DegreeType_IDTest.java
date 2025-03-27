package PAI.VOs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DegreeType_IDTest {

    @Test
    void shouldGenerateUniqueID() {
        DegreeType_ID id1 = new DegreeType_ID();
        DegreeType_ID id2 = new DegreeType_ID();
        assertNotNull(id1.getDTID(), "The generated ID must not be null.");
        assertNotNull(id2.getDTID(), "The generated ID must not be null.");
        assertNotEquals(id1.getDTID(), id2.getDTID(), "Automatically generated ID's must be different.");
    }

    @Test
    void shouldStoreProvidedID() {
        String customID = "123e4567-e89b-12d3-a456-426614174000";
        DegreeType_ID id = new DegreeType_ID(customID);
        assertEquals(customID, id.getDTID(), "The stored ID does not match the expected one.");
    }

    @Test
    void shouldThrowExceptionForNullOrEmptyID() {
        assertThrows(IllegalArgumentException.class, () -> new DegreeType_ID(null), "Should throw exception for a null ID.");
        assertThrows(IllegalArgumentException.class, () -> new DegreeType_ID(""), "Should throw exception for a blank ID.");
        assertThrows(IllegalArgumentException.class, () -> new DegreeType_ID("   "), "Should throw exception for an ID with blank spaces.");
    }

    @Test
    void shouldRecognizeEqualIDsUsingSameAs() {
        String sharedID = "550e8400-e29b-41d4-a716-446655440000";
        DegreeType_ID id1 = new DegreeType_ID(sharedID);
        DegreeType_ID id2 = new DegreeType_ID(sharedID);

        assertTrue(id1.sameAs(id2), "The objects must be considered equal.");
    }

    @Test
    void shouldReturnFalseForDifferentIDsUsingSameAs() {
        DegreeType_ID id1 = new DegreeType_ID("123e4567-e89b-12d3-a456-426614174000");
        DegreeType_ID id2 = new DegreeType_ID("550e8400-e29b-41d4-a716-446655440000");

        assertFalse(id1.sameAs(id2), "Objects with different ID's must not be considered equals");
    }

    @Test
    void shouldReturnFalseForNullSameAs() {
        DegreeType_ID id = new DegreeType_ID("123e4567-e89b-12d3-a456-426614174000");
        assertFalse(id.sameAs(null), "The comparison with null should return false.");
    }
}
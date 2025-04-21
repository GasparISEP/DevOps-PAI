package PAI.persistence.datamodel.schoolYear;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SchoolYearIDDataModelTest {

    // Test for the empty constructor
    @Test
    void testEmptyConstructor() {
        // Arrange
        // Act
        SchoolYearIDDataModel schoolYearID = new SchoolYearIDDataModel();

        // Assert
        assertNotNull(schoolYearID);
    }

    // Test for constructor with an ID value
    @Test
    void testConstructor() {
        // Arrange
        String idValue = "2024-2025";

        // Act
        SchoolYearIDDataModel schoolYearID = new SchoolYearIDDataModel(idValue);

        // Assert
        assertNotNull(schoolYearID);
        assertEquals(idValue, schoolYearID.getId());
    }

    // Test for equals and hashCode for two equal IDs
    @Test
    void testEqualsAndHashCode() {
        // Arrange
        String idValue1 = "2024-2025";
        String idValue2 = "2024-2025";
        String idValue3 = "2025-2026";

        SchoolYearIDDataModel schoolYearID1 = new SchoolYearIDDataModel(idValue1);
        SchoolYearIDDataModel schoolYearID2 = new SchoolYearIDDataModel(idValue2);
        SchoolYearIDDataModel schoolYearID3 = new SchoolYearIDDataModel(idValue3);

        // Act & Assert
        assertEquals(schoolYearID1, schoolYearID2);
        assertEquals(schoolYearID1.hashCode(), schoolYearID2.hashCode());

        assertNotEquals(schoolYearID1, schoolYearID3);
        assertNotEquals(schoolYearID1.hashCode(), schoolYearID3.hashCode());
    }

    // Test for equals with null and different class
    @Test
    void testEqualsWithNullAndDifferentClass() {
        // Arrange
        SchoolYearIDDataModel schoolYearID = new SchoolYearIDDataModel("2024-2025");

        // Act & Assert
        assertNotEquals(null, schoolYearID);

        assertNotEquals("some string", schoolYearID);

        assertEquals(schoolYearID, schoolYearID);
    }

    // Test for ID uniqueness (check if the same ID value is considered the same object)
    @Test
    void testIdUniqueness() {
        // Arrange
        SchoolYearIDDataModel schoolYearID1 = new SchoolYearIDDataModel("2024-2025");
        SchoolYearIDDataModel schoolYearID2 = new SchoolYearIDDataModel("2024-2025");

        // Act & Assert
        assertTrue(schoolYearID1.getId().equals(schoolYearID2.getId()));
    }
}
package PAI.persistence.datamodel.courseEdition;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseEditionGeneratedIDDataModelTest {

    // Test for the empty constructor
    @Test
    void testEmptyConstructor() {
        // Arrange + Act
        CourseEditionGeneratedIDDataModel dataModel = new CourseEditionGeneratedIDDataModel();
        // Assert
        assertNotNull(dataModel);
    }

    // Test for constructor with an ID value
    @Test
    void testConstructor() {
        // Arrange
        String idValue = "2024-2025";

        // Act
        CourseEditionGeneratedIDDataModel dataModel = new CourseEditionGeneratedIDDataModel(idValue);

        // Assert
        assertNotNull(dataModel);
        assertEquals(idValue, dataModel.getId());
    }

    // Test for equals and hashCode for two equal IDs
    @Test
    void testEqualsAndHashCode() {
        // Arrange
        String idValue1 = "2024-2025";
        String idValue2 = "2024-2025";
        String idValue3 = "2025-2026";

        CourseEditionGeneratedIDDataModel dataModel1 = new CourseEditionGeneratedIDDataModel(idValue1);
        CourseEditionGeneratedIDDataModel dataModel2 = new CourseEditionGeneratedIDDataModel(idValue2);
        CourseEditionGeneratedIDDataModel dataModel3 = new CourseEditionGeneratedIDDataModel(idValue3);

        // Act & Assert
        assertEquals(dataModel1, dataModel2);
        assertEquals(dataModel1.hashCode(), dataModel2.hashCode());

        assertNotEquals(dataModel1, dataModel3);
        assertNotEquals(dataModel1.hashCode(), dataModel3.hashCode());
    }

    // Test for equals with null and different class
    @Test
    void testEqualsWithNullAndDifferentClass() {
        // Arrange
        String idValue = "2024-2025";
        CourseEditionGeneratedIDDataModel dataModel1 = new CourseEditionGeneratedIDDataModel(idValue);

        // Act & Assert
        assertNotEquals(null, dataModel1);

        assertNotEquals("some string", dataModel1);

        assertEquals(dataModel1, dataModel1);
    }

    // Test for ID uniqueness (check if the same ID value is considered the same object)
    @Test
    void testIdUniqueness() {
        // Arrange
        String idValue = "2024-2025";
        CourseEditionGeneratedIDDataModel dataModel1 = new CourseEditionGeneratedIDDataModel(idValue);
        CourseEditionGeneratedIDDataModel dataModel2 = new CourseEditionGeneratedIDDataModel(idValue);

        // Act & Assert
        assertTrue(dataModel1.equals(dataModel2));
    }

    // Test that different instances of objects returns a false
    @Test
    void testDifferentTypesOfObjects() {
        // Arrange
        String idValue = "2024-2025";
        CourseEditionGeneratedIDDataModel dataModel1 = new CourseEditionGeneratedIDDataModel(idValue);

        // Act & Assert
        assertFalse(dataModel1.equals(idValue));
    }
}
package PAI.persistence.datamodel;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TeacherCategoryIDDataModelTest {

    @Test
    void shouldCreateInstanceWithValidUUID() {
        // Arrange
        UUID uuid = UUID.randomUUID();

        // Act
        TeacherCategoryIDDataModel dataModel = new TeacherCategoryIDDataModel(uuid);

        // Assert
        assertNotNull(dataModel);
        assertEquals(uuid, dataModel.getValue());
    }

    @Test
    void shouldThrowExceptionWhenUUIDIsNull() {
        // Arrange + Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new TeacherCategoryIDDataModel(null));
    }

    @Test
    void shouldReturnTrueWhenComparedWithItself() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        TeacherCategoryIDDataModel dataModel = new TeacherCategoryIDDataModel(uuid);

        // Act
        boolean result = dataModel.equals(dataModel);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWhenComparedWithEqualObject() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        TeacherCategoryIDDataModel a = new TeacherCategoryIDDataModel(uuid);
        TeacherCategoryIDDataModel b = new TeacherCategoryIDDataModel(uuid);

        // Act
        boolean result = a.equals(b);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenComparedWithDifferentUUID() {
        // Arrange
        TeacherCategoryIDDataModel a = new TeacherCategoryIDDataModel(UUID.randomUUID());
        TeacherCategoryIDDataModel b = new TeacherCategoryIDDataModel(UUID.randomUUID());

        // Act
        boolean result = a.equals(b);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenComparedWithDifferentType() {
        // Arrange
        TeacherCategoryIDDataModel dataModel = new TeacherCategoryIDDataModel(UUID.randomUUID());

        // Act
        boolean result = dataModel.equals("not a datamodel");

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnExpectedHashCode() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        TeacherCategoryIDDataModel dataModel1 = new TeacherCategoryIDDataModel(uuid);
        TeacherCategoryIDDataModel dataModel2 = new TeacherCategoryIDDataModel(uuid);

        // Act
        int hash1 = dataModel1.hashCode();
        int hash2 = dataModel2.hashCode();

        // Assert
        assertEquals(hash1, hash2);
    }

    @Test
    void getValueShouldReturnCorrectUUID() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        TeacherCategoryIDDataModel dataModel = new TeacherCategoryIDDataModel(uuid);

        // Act
        UUID result = dataModel.getValue();

        // Assert
        assertEquals(uuid, result);
    }

    @Test
    void jpaConstructorShouldCreateInstanceWithNullUUID() {
        // Act
        TeacherCategoryIDDataModel dataModel = new TeacherCategoryIDDataModel();

        // Assert
        assertNull(dataModel.getValue());
    }

}

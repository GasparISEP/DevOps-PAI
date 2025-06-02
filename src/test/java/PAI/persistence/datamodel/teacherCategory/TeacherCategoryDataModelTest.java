package PAI.persistence.datamodel.teacherCategory;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TeacherCategoryDataModelTest {

    //testing empty constructor

    @Test
    void shouldReturnAnEmptyTeacherCategoryDataModel() {
        //arrange

        //act & assert
        TeacherCategoryDataModel teacherCategoryDataModel = new TeacherCategoryDataModel();
    }

    @Test
    void testParameterizedConstructorAndGetters() {
        // Arrange
        UUID expectedUUID = UUID.randomUUID();
        TeacherCategoryIDDataModel doubleId = mock(TeacherCategoryIDDataModel.class);
        when (doubleId.getValue()).thenReturn(expectedUUID);
        String expectedName = "Assistant";

        // Act
        TeacherCategoryDataModel model = new TeacherCategoryDataModel(doubleId, expectedName);

        // Assert
        assertEquals(expectedUUID, model.getId().getValue());
        assertEquals(expectedName, model.getName());
    }

    @Test
    void testConstructorNullIdThrows() {
        // Arrange
        String name = "Assistente";

        // Act & Assert
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> new TeacherCategoryDataModel(null, name)
        );
        assertEquals("Teacher Category ID DataModel cannot be null.", ex.getMessage());
    }

    @Test
    void testConstructorNullNameThrows() {
        // Arrange
        TeacherCategoryIDDataModel id = new TeacherCategoryIDDataModel(UUID.randomUUID());

        // Act & Assert
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> new TeacherCategoryDataModel(id, null)
        );
        assertEquals("Teacher Category Name cannot be null.", ex.getMessage());
    }


    @Test
    void testGetIdReturnsExpectedValue() {
        // Arrange
        UUID expectedUUID = UUID.randomUUID();
        TeacherCategoryIDDataModel doubleId = mock(TeacherCategoryIDDataModel.class);
        when (doubleId.getValue()).thenReturn(expectedUUID);
        TeacherCategoryDataModel model = new TeacherCategoryDataModel(doubleId, "Auxiliar");

        // Act
        TeacherCategoryIDDataModel result = model.getId();

        // Assert
        assertEquals(doubleId, result);
    }

    @Test
    void testGetNameReturnsExpectedValue() {
        // Arrange
        UUID expectedUUID = UUID.randomUUID();
        TeacherCategoryIDDataModel doubleId = mock(TeacherCategoryIDDataModel.class);
        when (doubleId.getValue()).thenReturn(expectedUUID);
        TeacherCategoryDataModel model = new TeacherCategoryDataModel(doubleId, "Assistente");

        // Act
        String result = model.getName();

        // Assert
        assertEquals("Assistente", result);
    }

    @Test
    void testEqualsWithSameValuesReturnsTrue() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        TeacherCategoryIDDataModel id1 = new TeacherCategoryIDDataModel(uuid);
        TeacherCategoryIDDataModel id2 = new TeacherCategoryIDDataModel(uuid);

        // Act & Assert
        assertEquals(id1, id2);
    }

    @Test
    void testEqualsWithDifferentValuesReturnsFalse() {
        // Arrange
        TeacherCategoryIDDataModel id1 = new TeacherCategoryIDDataModel(UUID.randomUUID());
        TeacherCategoryIDDataModel id2 = new TeacherCategoryIDDataModel(UUID.randomUUID());

        // Act & Assert
        assertNotEquals(id1, id2);
    }

    @Test
    void testEqualsWithNullReturnsFalse() {
        // Arrange
        TeacherCategoryIDDataModel id = new TeacherCategoryIDDataModel(UUID.randomUUID());

        // Act & Assert
        assertNotEquals(id, null);
    }

    @Test
    void testEqualsWithDifferentClassReturnsFalse() {
        // Arrange
        TeacherCategoryIDDataModel id = new TeacherCategoryIDDataModel(UUID.randomUUID());

        // Act & Assert
        assertNotEquals(id, "notAnID");
    }

    @Test
    void testHashCodeForTheSameTeacherCategoryDataModel() {
        // Arrange
        UUID uuid = UUID.randomUUID();

        TeacherCategoryIDDataModel doubleId = mock(TeacherCategoryIDDataModel.class);
        when (doubleId.getValue()).thenReturn(uuid);

        TeacherCategoryIDDataModel doubleId1 = mock(TeacherCategoryIDDataModel.class);
        when (doubleId1.getValue()).thenReturn(uuid);

        TeacherCategoryDataModel model1 = new TeacherCategoryDataModel(doubleId, "Catedrático");
        TeacherCategoryDataModel model2 = model1;

        // Act & Assert
        assertEquals(model1.hashCode(), model2.hashCode());
    }

    @Test
    void testHashCodeForTeacherCategoryDataModel() {
        // Arrange
        UUID uuid = UUID.randomUUID();

        TeacherCategoryIDDataModel doubleId = mock(TeacherCategoryIDDataModel.class);
        when (doubleId.getValue()).thenReturn(uuid);

        TeacherCategoryIDDataModel doubleId1 = mock(TeacherCategoryIDDataModel.class);
        when (doubleId1.getValue()).thenReturn(uuid);

        TeacherCategoryDataModel model1 = new TeacherCategoryDataModel(doubleId, "Catedrático");
        TeacherCategoryDataModel model2 = new TeacherCategoryDataModel(doubleId1, "Catedrático");

        // Act & Assert
        assertNotEquals(model1.hashCode(), model2.hashCode());
    }

    @Test
    void testHashCodeForDifferentTeacherCategoryDataModel() {
        // Arrange
        UUID uuid = UUID.randomUUID();

        TeacherCategoryIDDataModel doubleId = mock(TeacherCategoryIDDataModel.class);
        when (doubleId.getValue()).thenReturn(uuid);

        UUID expectedUUID = UUID.randomUUID();

        TeacherCategoryIDDataModel doubleId1 = mock(TeacherCategoryIDDataModel.class);
        when (doubleId1.getValue()).thenReturn(expectedUUID);

        TeacherCategoryDataModel model1 = new TeacherCategoryDataModel(doubleId, "Catedrático");
        TeacherCategoryDataModel model2 = new TeacherCategoryDataModel(doubleId1, "Catedrático");

        // Act & Assert
        assertNotEquals(model1.hashCode(), model2.hashCode());
    }

    @Test
    void testEqualsForTheSameTeacherCategoryDataModel() {
        // Arrange
        UUID uuid = UUID.randomUUID();

        TeacherCategoryIDDataModel doubleId = mock(TeacherCategoryIDDataModel.class);
        when (doubleId.getValue()).thenReturn(uuid);

        TeacherCategoryIDDataModel doubleId1 = mock(TeacherCategoryIDDataModel.class);
        when (doubleId1.getValue()).thenReturn(uuid);

        TeacherCategoryDataModel model1 = new TeacherCategoryDataModel(doubleId, "Catedrático");
        TeacherCategoryDataModel model2 = model1;

        // Act & Assert
        assertTrue(model1.equals (model2));
    }

    @Test
    void testAreNotEqualsWhenAreNotOfTheSameInstance() {
        // Arrange
        UUID uuid = UUID.randomUUID();

        TeacherCategoryIDDataModel doubleId = mock(TeacherCategoryIDDataModel.class);
        when (doubleId.getValue()).thenReturn(uuid);

        TeacherCategoryIDDataModel doubleId1 = mock(TeacherCategoryIDDataModel.class);
        when (doubleId1.getValue()).thenReturn(uuid);

        TeacherCategoryDataModel model1 = new TeacherCategoryDataModel(doubleId, "Catedrático");
        Object doubleObject = mock (Object.class);

        // Act & Assert
        assertFalse(model1.equals (doubleObject));
    }

    @Test
    void testEqualsForDifferentTeacherCategoryDataModel() {
        // Arrange
        UUID uuid = UUID.randomUUID();

        TeacherCategoryIDDataModel doubleId = mock(TeacherCategoryIDDataModel.class);
        when (doubleId.getValue()).thenReturn(uuid);

        UUID expectedUUID = UUID.randomUUID();

        TeacherCategoryIDDataModel doubleId1 = mock(TeacherCategoryIDDataModel.class);
        when (doubleId1.getValue()).thenReturn(expectedUUID);

        TeacherCategoryDataModel model1 = new TeacherCategoryDataModel(doubleId, "Catedrático");
        TeacherCategoryDataModel model2 = new TeacherCategoryDataModel(doubleId1, "Catedrático");

        // Act & Assert
        assertFalse(model1.equals(model2));
    }

    @Test
    void testEqualsForEqualsTeacherCategoryDataModel() {
        // Arrange
        UUID uuid = UUID.randomUUID();

        TeacherCategoryIDDataModel doubleId = mock(TeacherCategoryIDDataModel.class);
        when (doubleId.getValue()).thenReturn(uuid);

        TeacherCategoryDataModel model1 = new TeacherCategoryDataModel(doubleId, "Catedrático");
        TeacherCategoryDataModel model2 = new TeacherCategoryDataModel(doubleId, "Catedrático");

        // Act & Assert
        assertTrue(model1.equals(model2));
    }

    @Test
    void testEqualsWithNullReturnsFalseForDataModel() {
        // Arrange
        UUID expectedUUID = UUID.randomUUID();

        TeacherCategoryIDDataModel doubleId = mock(TeacherCategoryIDDataModel.class);
        when (doubleId.getValue()).thenReturn(expectedUUID);

        TeacherCategoryDataModel model = new TeacherCategoryDataModel(doubleId, "Auxiliar");

        // Act & Assert
        assertFalse(model.equals(null));
    }
}

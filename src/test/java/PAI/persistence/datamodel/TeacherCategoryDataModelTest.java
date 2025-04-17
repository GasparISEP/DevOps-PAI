package PAI.persistence.datamodel;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeacherCategoryDataModelTest {
    @Test
    void testParameterizedConstructorAndGetters() {
        // Arrange
        UUID expectedId = UUID.randomUUID();
        String expectedName = "Matemática";

        // Act
        TeacherCategoryDataModel model = new TeacherCategoryDataModel(expectedId, expectedName);

        // Assert
        assertEquals(expectedId, model.getId(), "getId() deve retornar o valor passado no construtor");
        assertEquals(expectedName, model.getName(), "getName() deve retornar o valor passado no construtor");
    }
}

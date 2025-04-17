package PAI.persistence.datamodel;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void testConstructorNullIdThrows() {
        // Arrange
        String name = "Teste";

        // Act & Assert
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> new TeacherCategoryDataModel(null, name)
        );
        assertEquals("id não pode ser nulo", ex.getMessage());
    }

    @Test
    void testConstructorNullNameThrows() {
        // Arrange
        UUID id = UUID.randomUUID();

        // Act & Assert
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> new TeacherCategoryDataModel(id, null)
        );
        assertEquals("name não pode ser nulo", ex.getMessage());
    }
}

package PAI.persistence.datamodel;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testJPAAnnotationsPresent() {
        // Arrange & Act
        Entity entityAnno = TeacherCategoryDataModel.class.getAnnotation(Entity.class);
        Table tableAnno = TeacherCategoryDataModel.class.getAnnotation(Table.class);

        // Assert
        assertNotNull(entityAnno, "@Entity deve estar presente na classe");
        assertNotNull(tableAnno, "@Table deve estar presente na classe");
        assertEquals("teacher_category", tableAnno.name(), "@Table name deve corresponder à tabela do BD");
    }

    @Test
    void testDefaultConstructorIsProtected() throws NoSuchMethodException {
        // Arrange & Act
        Constructor<TeacherCategoryDataModel> ctor = TeacherCategoryDataModel.class.getDeclaredConstructor();

        // Assert
        int modifiers = ctor.getModifiers();
        assertTrue(Modifier.isProtected(modifiers), "Construtor sem-args deve ser protected");
    }

}

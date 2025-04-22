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
        UUID expectedUUID = UUID.randomUUID();
        TeacherCategoryIDDataModel expectedId = new TeacherCategoryIDDataModel(expectedUUID);
        String expectedName = "Matemática";

        // Act
        TeacherCategoryDataModel model = new TeacherCategoryDataModel(expectedId, expectedName);

        // Assert
        assertEquals(expectedUUID, model.getId().getValue(), "getId().getValue() deve retornar o UUID original");
        assertEquals(expectedName, model.getName(), "getName() deve retornar o valor passado no construtor");
    }

    @Test
    void testConstructorNullIdThrows() {
        // Arrange
        String name = "Assistente";

        // Act & Assert
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> new TeacherCategoryDataModel(null, name)
        );
        assertEquals("id não pode ser nulo", ex.getMessage());
    }

    @Test
    void testConstructorNullNameThrows() {
        // Arrange
        TeacherCategoryIDDataModel id = new TeacherCategoryIDDataModel(UUID.randomUUID());

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

    @Test
    void testGetIdReturnsExpectedValue() {
        // Arrange
        TeacherCategoryIDDataModel id = new TeacherCategoryIDDataModel(UUID.randomUUID());
        TeacherCategoryDataModel model = new TeacherCategoryDataModel(id, "Auxiliar");

        // Act
        TeacherCategoryIDDataModel result = model.getId();

        // Assert
        assertEquals(id, result);
    }

    @Test
    void testGetNameReturnsExpectedValue() {
        // Arrange
        TeacherCategoryIDDataModel id = new TeacherCategoryIDDataModel(UUID.randomUUID());
        TeacherCategoryDataModel model = new TeacherCategoryDataModel(id, "Assistente");

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
    void testHashCodeConsistencyForSameUUID() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        TeacherCategoryIDDataModel id1 = new TeacherCategoryIDDataModel(uuid);
        TeacherCategoryIDDataModel id2 = new TeacherCategoryIDDataModel(uuid);

        // Act & Assert
        assertEquals(id1.hashCode(), id2.hashCode());
    }

    @Test
    void testEqualsAndHashCodeForTeacherCategoryDataModel() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        TeacherCategoryIDDataModel id1 = new TeacherCategoryIDDataModel(uuid);
        TeacherCategoryIDDataModel id2 = new TeacherCategoryIDDataModel(uuid);
        TeacherCategoryDataModel model1 = new TeacherCategoryDataModel(id1, "Catedrático");
        TeacherCategoryDataModel model2 = new TeacherCategoryDataModel(id2, "Catedrático");

        // Act & Assert
        assertEquals(model1, model2);
        assertEquals(model1.hashCode(), model2.hashCode());
    }

    @Test
    void testEqualsReturnsFalseForDifferentTypeInTeacherCategoryDataModel() {
        // Arrange
        TeacherCategoryIDDataModel id = new TeacherCategoryIDDataModel(UUID.randomUUID());
        TeacherCategoryDataModel model = new TeacherCategoryDataModel(id, "Assistente");

        // Act & Assert
        assertNotEquals(model, "someString");
    }

    @Test
    void testEqualsWithSameObjectReturnsTrue() {
        // Arrange
        TeacherCategoryIDDataModel id = new TeacherCategoryIDDataModel(UUID.randomUUID());
        TeacherCategoryDataModel model = new TeacherCategoryDataModel(id, "Auxiliar");

        // Act & Assert
        assertEquals(model, model); // mesmo objeto
    }

    @Test
    void testEqualsWithNullReturnsFalseForDataModel() {
        // Arrange
        TeacherCategoryIDDataModel id = new TeacherCategoryIDDataModel(UUID.randomUUID());
        TeacherCategoryDataModel model = new TeacherCategoryDataModel(id, "Auxiliar");

        // Act & Assert
        assertNotEquals(model, null);
    }


}

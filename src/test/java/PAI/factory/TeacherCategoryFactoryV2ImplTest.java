package PAI.factory;

import PAI.VOs.Name;
import PAI.domain.TeacherCategoryV2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherCategoryFactoryV2ImplTest {

    private final TeacherCategoryFactoryV2Impl factory = new TeacherCategoryFactoryV2Impl();

    @Test
    void shouldCreateValidTeacherCategory() {
        // Arrange
        Name name = new Name("Professor Associado");

        // Act
        TeacherCategoryV2 category = factory.createTeacherCategory(name);

        // Assert
        assertNotNull(category);
        assertNotNull(category.getId());
        assertEquals(name, category.getName());
    }

    @Test
    void shouldThrowExceptionIfNameIsNull() {
        // Even though the factory accepts a Name object,
        // if we pass null, the TeacherCategoryV2 constructor will throw.
        assertThrows(IllegalArgumentException.class, () -> factory.createTeacherCategory(null));
    }
}

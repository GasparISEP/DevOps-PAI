package PAI.domain.teacherCategory;

import PAI.VOs.Name;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherCategoryFactoryImplTest {

    private final TeacherCategoryFactoryImpl factory = new TeacherCategoryFactoryImpl();

    @Test
    void shouldCreateValidTeacherCategory() {
        // Arrange
        Name name = new Name("Professor Associado");

        // Act
        TeacherCategory category = factory.createTeacherCategory(name);

        // Assert
        assertNotNull(category);
        assertNotNull(category.identity());
        assertEquals(name, category.getName());
    }

    @Test
    void shouldThrowExceptionIfNameIsNull() {
        // Even though the factory accepts a Name object,
        // if we pass null, the TeacherCategory constructor will throw.
        assertThrows(IllegalArgumentException.class, () -> factory.createTeacherCategory(null));
    }
}

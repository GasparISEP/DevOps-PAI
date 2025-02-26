package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TeacherCategoryFactoryTest {

    @Test
    void shouldCreateTeacherCategory() throws Exception {
        // Arrange
        TeacherCategoryFactory factory = new TeacherCategoryFactory();
        String categoryName = "Professor Adjunto";

        // Act
        TeacherCategory category = factory.createTeacherCategory(categoryName);

        // Assert
        assertNotNull(category); //checks that TeacherCategory object is not null
        assertEquals(categoryName, category.getName()); //checks if name of category is the same as the one passed as parameter
    }
    @Test
    void shouldThrowExceptionWithNullName() {
        // Arrange
        TeacherCategoryFactory factory = new TeacherCategoryFactory();

        // Act & Assert
        assertThrows(Exception.class, () -> factory.createTeacherCategory(null)); //checks if exception is thrown when name is null
    }

    @Test
    void shouldThrowExceptionWithEmptyName() {
        // Arrange
        TeacherCategoryFactory factory = new TeacherCategoryFactory();

        // Act & Assert
        assertThrows(Exception.class, () -> factory.createTeacherCategory(""));//checks if exception is thrown when name is empty
    }

    @Test
    void shouldThrowExceptionWithBlankName() {
        // Arrange
        TeacherCategoryFactory factory = new TeacherCategoryFactory();

        // Act & Assert
        assertThrows(Exception.class, () -> factory.createTeacherCategory("   "));//checks if exception is thrown when name is blank

    }
}

package PAI.controller;

import PAI.domain.TeacherCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class US01_ConfigureTeacherCategoryControllerTest {

    private TeacherCategoryRepository repository;
    private US01_ConfigureTeacherCategoryController controller;

    @BeforeEach
    public void setUp() {
        // Arrange
        repository = new TeacherCategoryRepository();
        controller = new US01_ConfigureTeacherCategoryController(repository);
    }

    @Test
    public void testConstructorWithNullRepository() {
        // Arrange
        TeacherCategoryRepository nullRepository = null;

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new US01_ConfigureTeacherCategoryController(nullRepository);
        });
        assertEquals("Repository cannot be null", exception.getMessage());
    }

    @Test
    public void testAddDuplicateCategory() {
        // Arrange
        String categoryName = "Math";

        // Act & Assert
        assertThrows(Exception.class, () -> {
            controller.configureTeacherCategory(categoryName);
            controller.configureTeacherCategory(categoryName);
        });
    }

    @Test
    public void testAddNewCategory() throws Exception {
        // Arrange
        String categoryName = "Science";

        // Act
        boolean result = controller.configureTeacherCategory(categoryName);

        // Assert
        assertTrue(result);
    }
}
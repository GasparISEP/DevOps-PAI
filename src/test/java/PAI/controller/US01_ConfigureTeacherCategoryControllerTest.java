package PAI.controller;

import PAI.domain.TeacherCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class US01_ConfigureTeacherCategoryControllerTest {

    private TeacherCategoryRepository repository; // Mocked repository
    private US01_ConfigureTeacherCategoryController controller;

    @BeforeEach
    public void setUp() {
        // Arrange
        repository = mock(TeacherCategoryRepository.class); //  Mock the repository
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
    public void testAddDuplicateCategory() throws Exception {
        // Arrange
        String categoryName = "Math";

        when(repository.registerTeacherCategory(categoryName))
                .thenReturn(true) // First call succeeds
                .thenReturn(false); // Return false if category already exists

        // Act & Assert
        controller.configureTeacherCategory(categoryName); // First call works
        assertThrows(Exception.class, () -> controller.configureTeacherCategory(categoryName)); // Second call should throw
    };


    @Test
    public void testAddNewCategory() throws Exception {
        // Arrange
        String categoryName = "Science";
        // Tell the mock to return 'true' when we try to add the category
        when(repository.registerTeacherCategory(categoryName)).thenReturn(true);

        // Act
        boolean result = controller.configureTeacherCategory(categoryName);

        // Assert
        assertTrue(result);
        verify(repository).registerTeacherCategory(categoryName); // Verify that the method was called
    }

    @Test
    public void testAddCategoryWithSpecialCharacters() throws Exception { //Ensures that category names with special characters are handled properly
        // Arrange
        String specialCharCategory = "Comp#Sci!";

        when(repository.registerTeacherCategory(specialCharCategory)).thenReturn(true);

        // Act
        boolean result = controller.configureTeacherCategory(specialCharCategory);

        // Assert
        assertTrue(result);
        verify(repository).registerTeacherCategory(specialCharCategory);
    }

    @Test
    public void testCaseSensitivityInCategoryNames() throws Exception { //Ensures that the system handles case sensitivity properly.
        // Arrange
        String categoryLower = "math";
        String categoryUpper = "Math";

        when(repository.registerTeacherCategory(categoryLower)).thenReturn(true);
        when(repository.registerTeacherCategory(categoryUpper)).thenReturn(true);

        // Act
        boolean resultLower = controller.configureTeacherCategory(categoryLower);
        boolean resultUpper = controller.configureTeacherCategory(categoryUpper);

        // Assert
        assertTrue(resultLower);
        assertTrue(resultUpper);
        verify(repository).registerTeacherCategory(categoryLower);
        verify(repository).registerTeacherCategory(categoryUpper);
    }

}
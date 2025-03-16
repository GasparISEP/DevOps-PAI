package PAI.controller;

import PAI.repository.TeacherCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link US01_ConfigureTeacherCategoryController} class.
 * These tests verify the functionality of the controller's behavior in handling teacher category configurations.
 */
public class US01_ConfigureTeacherCategoryControllerTest {

    private TeacherCategoryRepository repository; // Mocked repository
    private US01_ConfigureTeacherCategoryController controller;

    /**
     * Setup method to initialize the mock repository and controller before each test.
     * This method is called before each test is run.
     */
    @BeforeEach
    public void setUp() {
        // Arrange
        repository = mock(TeacherCategoryRepository.class); //  Mock the repository
        controller = new US01_ConfigureTeacherCategoryController(repository);
    }

    /**
     * Tests the constructor of the controller with a null repository.
     * This test verifies that an {@link IllegalArgumentException} is thrown when the repository is null.
     */
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

    /**
     * Tests the behavior of the controller when attempting to add a duplicate category.
     * This test verifies that the controller throws an exception if the category already exists.
     *
     * @throws Exception if an error occurs while configuring the teacher category.
     */
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

    /**
     * Tests the behavior of the controller when adding a new teacher category.
     * This test ensures that the controller can successfully add a new category and interact with the repository.
     *
     * @throws Exception if an error occurs while configuring the teacher category.
     */
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

    /**
     * Tests the behavior of the controller when adding a category with special characters in its name.
     * This test verifies that the system handles special characters correctly in the category name.
     *
     * @throws Exception if an error occurs while configuring the teacher category.
     */
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

    /**
     * Tests the case sensitivity of teacher category names.
     * This test ensures that the system can handle category names with different cases, such as "math" and "Math".
     *
     * @throws Exception if an error occurs while configuring the teacher category.
     */
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
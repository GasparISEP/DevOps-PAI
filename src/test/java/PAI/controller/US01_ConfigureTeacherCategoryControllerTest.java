package PAI.controller;

import PAI.VOs.Name;
import PAI.repository.ITeacherCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class US01_ConfigureTeacherCategoryControllerTest {

    private ITeacherCategoryRepository repository;
    private US01_ConfigureTeacherCategoryController controller;

    @BeforeEach
    public void setUp() {
        repository = mock(ITeacherCategoryRepository.class);
        controller = new US01_ConfigureTeacherCategoryController(repository);
    }

    @Test
    public void testConstructorWithNullArgument() {
        assertThrows(IllegalArgumentException.class, () -> new US01_ConfigureTeacherCategoryController(null));
    }

    @Test
    public void testConfigureTeacherCategorySuccess() throws Exception {
        // Arrange
        Name expectedName = new Name("Matemática");
        when(repository.registerTeacherCategory(any(Name.class))).thenReturn(true);

        // Act
        boolean result = controller.configureTeacherCategory("Matemática");

        // Assert
        assertTrue(result);
        verify(repository).registerTeacherCategory(eq(expectedName));
    }

    @Test
    public void testConfigureTeacherCategoryDuplicateThrows() {
        // Arrange: note the exact same casing/spelling as passed to controller
        Name expectedName = new Name("Física fisica");
        when(repository.registerTeacherCategory(any(Name.class))).thenReturn(false);

        // Act & Assert
        Exception ex = assertThrows(Exception.class,
                () -> controller.configureTeacherCategory("Física fisica")
        );
        assertEquals("Category already exists or could not be registered.", ex.getMessage());

        // Verify that the repository was called with the exact Name VO
        verify(repository).registerTeacherCategory(eq(expectedName));
    }

    @Test
    public void testInvalidLowercaseNameThrows() {
        // Name VO rejects lowercase-first strings before hitting the repository
        assertThrows(IllegalArgumentException.class,
                () -> controller.configureTeacherCategory("história")
        );
        verify(repository, never()).registerTeacherCategory(any());
    }
}

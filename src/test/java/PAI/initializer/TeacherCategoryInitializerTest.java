package PAI.initializer;

import PAI.controller.US01_ConfigureTeacherCategoryController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class TeacherCategoryInitializerTest {

    @Mock
    private US01_ConfigureTeacherCategoryController controller;

    @InjectMocks
    private TeacherCategoryInitializer initializer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldInitializeAndSaveTeacherCategoriesFromCsvFile() throws Exception {
        // Arrange
        when(controller.configureTeacherCategory(anyString())).thenReturn(true);

        // Act
        initializer.init();

        // Assert
        verify(controller).configureTeacherCategory("Professor Catedrático");
        verify(controller).configureTeacherCategory("Professor Associado");
        verify(controller).configureTeacherCategory("Professor Auxiliar");
    }

    @Test
    void shouldContinueInitializationEvenIfOneCategoryFailsToPersist() throws Exception {
        // Arrange
        when(controller.configureTeacherCategory("Professor Catedrático")).thenReturn(false);
        when(controller.configureTeacherCategory("Professor Auxiliar")).thenReturn(true);

        // Act
        initializer.init();

        // Assert
        verify(controller).configureTeacherCategory("Professor Catedrático");
        verify(controller).configureTeacherCategory("Professor Auxiliar");
    }
}

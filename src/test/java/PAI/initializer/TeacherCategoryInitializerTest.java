package PAI.initializer;

import PAI.controller.US01_ConfigureTeacherCategoryController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class TeacherCategoryInitializerTest {

    @Mock
    private US01_ConfigureTeacherCategoryController controller;

    @InjectMocks
    private TeacherCategoryInitializer initializer;

    private final InputStream originalIn = System.in;
    private final PrintStream originalErr = System.err;

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

    @Test
    void shouldPrintStackTraceWhenControllerThrows() throws Exception {
        // Arrange: force controller to throw
        doThrow(new RuntimeException("boom")).when(controller).configureTeacherCategory(anyString());

        // Capture System.err
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        // Act
        initializer.init();

        // Restore
        System.setErr(originalErr);

        // Assert stack trace printed
        String output = errContent.toString();
        assertTrue(output.contains("boom"), "Expected exception message in stderr");
    }
}

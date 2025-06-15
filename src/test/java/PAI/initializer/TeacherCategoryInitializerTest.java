package PAI.initializer;

import PAI.VOs.Name;
import PAI.controller.US01_ConfigureTeacherCategoryController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class TeacherCategoryInitializerTest {

    @Mock
    private US01_ConfigureTeacherCategoryController controller;

    @InjectMocks
    private TeacherCategoryInitializer initializer;

    private final InputStream originalIn = System.in;
    private final PrintStream originalErr = System.err;

    private String _csvPath;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        _csvPath = "src/main/resources/TeacherCategory.csv";
    }

    @Test
    void shouldInitializeAndSaveTeacherCategoriesFromCsvFile() throws Exception {
        // Arrange
        when(controller.configureTeacherCategory(any(Name.class))).thenReturn(true);

        // Act
        initializer.loadTeacherCategory(controller, _csvPath);

        // Assert
        ArgumentCaptor<Name> captor = ArgumentCaptor.forClass(Name.class);
        verify(controller, times(7)).configureTeacherCategory(captor.capture());

        List<String> actualNames = captor.getAllValues().stream()
                .map(n -> n.getName().trim())
                .toList();

        List<String> expectedNames = List.of(
                "Professor Auxiliar",
                "Professor Assistente",
                "Professor Adjunto",
                "Professor Associado",
                "Professor Titular",
                "Professor Coordenador",
                "Professor Catedrático"
        );

        assertTrue(actualNames.containsAll(expectedNames));
    }


    @Test
    void shouldContinueInitializationEvenIfOneCategoryFailsToPersist() throws Exception {
        // Arrange:
        when(controller.configureTeacherCategory(argThat(name ->
                name != null && "Professor Auxiliar".equals(name.getName())
        ))).thenReturn(false);

        when(controller.configureTeacherCategory(argThat(name ->
                name != null && !"Professor Auxiliar".equals(name.getName())
        ))).thenReturn(true);

        // Act
        initializer.loadTeacherCategory(controller, _csvPath);

        // Assert
        ArgumentCaptor<Name> captor = ArgumentCaptor.forClass(Name.class);
        verify(controller, times(7)).configureTeacherCategory(captor.capture());

        List<String> actualNames = captor.getAllValues().stream()
                .map(Name::getName)
                .toList();

        List<String> expectedNames = List.of(
                "Professor Auxiliar",
                "Professor Assistente",
                "Professor Adjunto",
                "Professor Associado",
                "Professor Titular",
                "Professor Coordenador",
                "Professor Catedrático"
        );

        assertTrue(actualNames.containsAll(expectedNames), "Nem todos os nomes esperados foram processados");
    }


    @Test
    void shouldPrintStackTraceWhenControllerThrows() throws Exception {
        // Arrange: force controller to throw
        doThrow(new RuntimeException("boom")).when(controller).configureTeacherCategory(any(Name.class));

        // Capture System.err
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        // Act
        initializer.loadTeacherCategory(controller, _csvPath);

        // Restore
        System.setErr(originalErr);

        // Assert stack trace printed
        String output = errContent.toString();
        assertTrue(output.contains("boom"), "Expected exception message in stderr");
    }
}

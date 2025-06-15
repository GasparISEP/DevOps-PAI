package PAI.initializer;

import PAI.controller.US02_ConfigureAccessMethodController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;


class AccessMethodInitializerTest {

    @Mock
    private US02_ConfigureAccessMethodController controller;

    @InjectMocks
    private AccessMethodInitializer initializer;

    private String _csvPath;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        _csvPath = "src/main/resources/AccessMethodData.csv";
    }

    @Test
    void shouldInitializeAndSaveAccessMethodsFromCsvFile() {
        // arrange
        when(controller.configureAccessMethod(anyString())).thenReturn(true);

        // act
        initializer.loadAccessMethod(controller, _csvPath);

        // assert
        verify(controller).configureAccessMethod("Maiores de 23 anos");
        verify(controller).configureAccessMethod("Titulares de Cursos Superiores");
        verify(controller).configureAccessMethod("Titulares de Cursos Médios");
        verify(controller).configureAccessMethod("Diplomas de Especialização Tecnológica");
        verify(controller).configureAccessMethod("Cursos Técnicos Superiores Profissionais");
        verify(controller).configureAccessMethod("Outros cursos superiores");
        verify(controller).configureAccessMethod("Licenciado para acesso a cursos de Medicina");
        verify(controller).configureAccessMethod("Comissão Nacional de Acesso ao Ensino Superior");
    }

    @Test
    void shouldContinueInitializationAndSaveWhenOneAccessMethodFailsToPersist() {
        // arrange
        List<String> savedMethods = new ArrayList<>();

        // Set default behavior first
        when(controller.configureAccessMethod(anyString())).thenReturn(true);

        // Then override specific cases
        when(controller.configureAccessMethod("Maiores de 23 anos"))
                .thenReturn(false);
        when(controller.configureAccessMethod("Comissão Nacional de Acesso ao Ensino Superior"))
                .thenAnswer(invocation -> {
                    savedMethods.add(invocation.getArgument(0));
                    return true;
                });

        // act
        initializer.loadAccessMethod(controller, _csvPath);

        // assert
        assertFalse(savedMethods.contains("Maiores de 23 anos"));
        assertTrue(savedMethods.contains("Comissão Nacional de Acesso ao Ensino Superior"));
    }
}
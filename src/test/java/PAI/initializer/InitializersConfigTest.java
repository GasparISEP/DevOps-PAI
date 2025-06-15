package PAI.initializer;

import PAI.controller.US10_IWantToConfigureDegreeTypesLevelsController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.CommandLineRunner;

import static org.mockito.Mockito.*;

class InitializersConfigTest {

    private InitializersConfig _initializer;

    @BeforeEach
    void setup() {
        _initializer = new InitializersConfig();
    }

    @Test
    void shouldInvokeLoadDegreeTypeWhenCommandLineRunnerRuns() throws Exception {
        // Arrange
        US10_IWantToConfigureDegreeTypesLevelsController controllerDouble = mock(US10_IWantToConfigureDegreeTypesLevelsController.class);
        DegreeTypeInitializer initializerDouble = mock(DegreeTypeInitializer.class);

        // Act
        CommandLineRunner commandLineRunner = _initializer.loadDataDegreeType(controllerDouble, initializerDouble);
        commandLineRunner.run(new String[]{});

        // Assert
        verify(initializerDouble).loadDegreeType(controllerDouble, "src/main/resources/DegreeTypeData.csv");
        verifyNoMoreInteractions(initializerDouble);
    }
}
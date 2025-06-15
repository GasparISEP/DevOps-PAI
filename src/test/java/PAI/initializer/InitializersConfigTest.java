package PAI.initializer;

import PAI.controller.US02_ConfigureAccessMethodController;
import PAI.controller.US10_IWantToConfigureDegreeTypesLevelsController;
import PAI.service.course.ICourseService;
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

    @Test
    void shouldInvokeLoadCourseWhenCommandLineRunnerRuns() throws Exception {
        // Arrange
        ICourseService serviceDouble = mock(ICourseService.class);
        CourseInitializer initializerDouble = mock(CourseInitializer.class);

        // Act
        CommandLineRunner commandLineRunner = _initializer.loadDataCourse(serviceDouble, initializerDouble);
        commandLineRunner.run(new String[]{});

        // Assert
        verify(initializerDouble).loadCourse(serviceDouble, "src/main/resources/CourseData.csv");
        verifyNoMoreInteractions(initializerDouble);
    }

    @Test
    void shouldInvokeLoadAccessMethodWhenCommandLineRunnerRuns() throws Exception {
        // Arrange
        US02_ConfigureAccessMethodController controller = mock(US02_ConfigureAccessMethodController.class);
        AccessMethodInitializer initializerDouble = mock(AccessMethodInitializer.class);

        // Act
        CommandLineRunner commandLineRunner = _initializer.loadDataAccessMethod(controller, initializerDouble);
        commandLineRunner.run(new String[]{});

        // Assert
        verify(initializerDouble).loadAccessMethod(controller, "src/main/resources/AccessMethodData.csv");
        verifyNoMoreInteractions(initializerDouble);
    }
}
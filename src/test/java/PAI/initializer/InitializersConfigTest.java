package PAI.initializer;

import PAI.controller.*;
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

    @Test
    void shouldInvokeLoadDataDepartmentWhenCommandLineRunnerRuns() throws Exception {
        // Arrange
        US05_DepartmentRegistryController controller = mock(US05_DepartmentRegistryController.class);
        DepartmentInitializer initializerDouble = mock(DepartmentInitializer.class);

        // Act
        CommandLineRunner commandLineRunner = _initializer.loadDataDepartment(controller, initializerDouble);
        commandLineRunner.run(new String[]{});

        // Assert
        verify(initializerDouble).loadDepartment(controller, "src/main/resources/Department.csv");
        verifyNoMoreInteractions(initializerDouble);
    }

    @Test
    void shouldInvokeLoadDataSchoolYearWhenCommandLineRunnerRuns() throws Exception {
        // Arrange
        US07_IWantToCreateASchoolYearController controller = mock(US07_IWantToCreateASchoolYearController.class);
        SchoolYearInitializer initializerDouble = mock(SchoolYearInitializer.class);

        // Act
        CommandLineRunner commandLineRunner = _initializer.loadDataSchoolYear(controller, initializerDouble);
        commandLineRunner.run(new String[]{});

        // Assert
        verify(initializerDouble).loadSchoolYear(controller, "src/main/resources/SchoolYear.csv");
        verifyNoMoreInteractions(initializerDouble);
    }

    @Test
    void shouldInvokeLoadStudentsWhenCommandLineRunnerRuns() throws Exception {
        // Arrange
        US08_IWantToRegisterAStudentInTheSystemController controller = mock(US08_IWantToRegisterAStudentInTheSystemController.class);
        StudentInitializer initializerDouble = mock(StudentInitializer.class);

        // Act
        CommandLineRunner commandLineRunner = _initializer.loadDataRegisterStudent(controller, initializerDouble);
        commandLineRunner.run(new String[]{});

        // Assert
        verify(initializerDouble).loadStudents(controller, "src/main/resources/Student_Data.csv");
        verifyNoMoreInteractions(initializerDouble);
    }
}
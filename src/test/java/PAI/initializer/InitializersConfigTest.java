package PAI.initializer;

import PAI.controller.*;
import PAI.domain.repositoryInterfaces.degreeType.IDegreeTypeRepository;
import PAI.domain.repositoryInterfaces.schoolYear.ISchoolYearRepository;
import PAI.domain.repositoryInterfaces.studentGrade.IStudentGradeRepository;
import PAI.domain.studentGrade.StudentGradeFactoryImpl;
import PAI.service.course.ICourseService;
import PAI.service.courseInStudyPlan.ICourseInStudyPlanService;
import PAI.service.programmeEdition.IProgrammeEditionService;
import PAI.service.programmeEditionEnrolment.IProgrammeEditionEnrolmentService;
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

    @Test
    void shouldInvokeLoadDataTeacherCategoryWhenCommandLineRunnerRuns() throws Exception {
        // Arrange
        US01_ConfigureTeacherCategoryController controller = mock(US01_ConfigureTeacherCategoryController.class);
        TeacherCategoryInitializer initializerDouble = mock(TeacherCategoryInitializer.class);

        // Act
        CommandLineRunner commandLineRunner = _initializer.loadDataTeacherCategory(controller, initializerDouble);
        commandLineRunner.run(new String[]{});

        // Assert
        verify(initializerDouble).loadTeacherCategory(controller, "src/main/resources/TeacherCategory.csv");
        verifyNoMoreInteractions(initializerDouble);
    }

    @Test
    void shouldInvokeLoadTeachersWhenCommandLineRunnerRuns() throws Exception {
        // Arrange
        US13_RegisterTeacherAndRelevantDataController controller = mock(US13_RegisterTeacherAndRelevantDataController.class);
        TeacherInitializer initializerDouble = mock(TeacherInitializer.class);

        // Act
        CommandLineRunner commandLineRunner = _initializer.loadDataRegisterTeacher(controller, initializerDouble);
        commandLineRunner.run(new String[]{});

        // Assert
        verify(initializerDouble).loadTeachers(controller, "src/main/resources/Teacher_Data.csv");
        verifyNoMoreInteractions(initializerDouble);
    }

    @Test
    void shouldInvokeLoadDataProgrammeWhenCommandLineRunnerRuns() throws Exception {
        // Arrange
        US11_RegisterProgrammeInTheSystemController controllerDouble = mock(US11_RegisterProgrammeInTheSystemController.class);
        IDegreeTypeRepository degreeTypeRepositoryDouble = mock(IDegreeTypeRepository.class);
        ProgrammeInitializer initializerDouble = mock(ProgrammeInitializer.class);

        // Act
        CommandLineRunner commandLineRunner = _initializer.loadDataProgramme(controllerDouble, degreeTypeRepositoryDouble, initializerDouble);
        commandLineRunner.run(new String[]{});

        // Assert
        verify(initializerDouble).loadProgramme(controllerDouble, degreeTypeRepositoryDouble, "src/main/resources/ProgrammeData.csv");
        verifyNoMoreInteractions(initializerDouble);
    }

    @Test
    void shouldInvokeLoadProgrammeEnrolmentWhenCommandLineRunnerRuns() throws Exception {
        // Arrange
        US09_EnrolStudentInProgrammeController controllerDouble = mock(US09_EnrolStudentInProgrammeController.class);
        ProgrammeEnrolmentInitializer initializerDouble = mock(ProgrammeEnrolmentInitializer.class);

        // Act
        CommandLineRunner commandLineRunner = _initializer.loadDataProgrammeEnrolment(controllerDouble, initializerDouble);
        commandLineRunner.run(new String[]{});

        // Assert
        verify(initializerDouble).loadProgrammeEnrolment(controllerDouble, "src/main/resources/ProgrammeEnrolment_Data.csv");
        verifyNoMoreInteractions(initializerDouble);
    }

    @Test
    void shouldInvokeLoadProgrammeEditionWhenCommandLineRunnerRuns() throws Exception {
        // Arrange
        IProgrammeEditionService serviceDouble = mock(IProgrammeEditionService.class);
        ProgrammeEditionInitializer initializerDouble = mock(ProgrammeEditionInitializer.class);

        // Act
        CommandLineRunner commandLineRunner = _initializer.loadDataProgrammeEdition(serviceDouble, initializerDouble);
        commandLineRunner.run(new String[]{});

        // Assert
        verify(initializerDouble).loadProgrammeEdition(serviceDouble, "src/main/resources/ProgrammeEdition.csv");
        verifyNoMoreInteractions(initializerDouble);
    }

    @Test
    void shouldInvokeLoadStudyPlanWhenCommandLineRunnerRuns() throws Exception {
        // Arrange
        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController controllerDouble = mock(US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController.class);
        StudyPlanInitializer initializerDouble = mock(StudyPlanInitializer.class);

        // Act
        CommandLineRunner commandLineRunner = _initializer.loadDataRegisterStudyPlan(controllerDouble, initializerDouble);
        commandLineRunner.run(new String[]{});

        // Assert
        verify(initializerDouble).loadStudyPlan(controllerDouble, "src/main/resources/StudyPlan_Data.csv");
        verifyNoMoreInteractions(initializerDouble);
    }

    @Test
    void shouldInvokeLoadDataRegisterCourseEditionWhenCommandLineRunnerRuns() throws Exception {
        // Arrange
        US19_CreateCourseEditionController controllerDouble = mock(US19_CreateCourseEditionController.class);
        ISchoolYearRepository schoolYearRepositoryDouble = mock(ISchoolYearRepository.class);
        CourseEditionInitializer initializerDouble = mock(CourseEditionInitializer.class);

        // Act
        CommandLineRunner commandLineRunner = _initializer.loadDataRegisterCourseEdition(controllerDouble,
                                                                    schoolYearRepositoryDouble, initializerDouble);
        commandLineRunner.run(new String[]{});

        // Assert
        verify(initializerDouble).loadCourseEdition(controllerDouble, schoolYearRepositoryDouble,
                                                            "src/main/resources/CourseEdition.csv");
        verifyNoMoreInteractions(initializerDouble);
    }

    @Test
    void shouldInvokeLoadProgrammeEditionEnrolmentWhenCommandLineRunnerRuns() throws Exception {
        // Arrange
        IProgrammeEditionEnrolmentService serviceDouble = mock(IProgrammeEditionEnrolmentService.class);
        ProgrammeEditionEnrolmentInitializer initializerDouble = mock(ProgrammeEditionEnrolmentInitializer.class);

        // Act
        CommandLineRunner commandLineRunner = _initializer.loadDataProgrammeEditionEnrolment(serviceDouble, initializerDouble);
        commandLineRunner.run(new String[]{});

        // Assert
        verify(initializerDouble).loadProgrammeEditionEnrolment(serviceDouble,
                                        "src/main/resources/ProgrammeEditionEnrolment.csv");
        verifyNoMoreInteractions(initializerDouble);
    }

    @Test
    void shouldInvokeLoadCourseInStudyPlanWhenCommandLineRunnerRuns() throws Exception {
        // Arrange
        ICourseInStudyPlanService serviceDouble = mock(ICourseInStudyPlanService.class);
        CourseInStudyPlanInitializer initializerDouble = mock(CourseInStudyPlanInitializer.class);

        // Act
        CommandLineRunner commandLineRunner = _initializer.loadDataCourseInStudyPlan(serviceDouble, initializerDouble);
        commandLineRunner.run(new String[]{});

        // Assert
        verify(initializerDouble).loadCourseInStudyPlan(serviceDouble, "src/main/resources/CourseInStudyPlan.csv");
        verifyNoMoreInteractions(initializerDouble);
    }

    @Test
    void shouldInvokeLoadCourseEditionEnrolmentsWhenCommandLineRunnerRuns() throws Exception {
        // Arrange
        US16_EnrolAStudentInACourseEditionController controllerDouble = mock(US16_EnrolAStudentInACourseEditionController.class);
        CourseEditionEnrolmentInitializer initializerDouble = mock(CourseEditionEnrolmentInitializer.class);

        // Act
        CommandLineRunner commandLineRunner = _initializer.loadDataCourseEditionEnrolment(controllerDouble, initializerDouble);
        commandLineRunner.run(new String[]{});

        // Assert
        verify(initializerDouble).loadCourseEditionEnrolments(controllerDouble, "src/main/resources/CourseEditionEnrolment.csv");
        verifyNoMoreInteractions(initializerDouble);
    }

    @Test
    void shouldInvokeLoadStudentGradeWhenCommandLineRunnerRuns() throws Exception {
        // Arrange
        StudentGradeFactoryImpl factoryDouble = mock(StudentGradeFactoryImpl.class);
        IStudentGradeRepository studentGradeRepositoryDouble = mock(IStudentGradeRepository.class);
        StudentGradeInitializer initializerDouble = mock(StudentGradeInitializer.class);

        // Act
        CommandLineRunner commandLineRunner = _initializer.loadStudentGrades(factoryDouble, studentGradeRepositoryDouble, initializerDouble);
        commandLineRunner.run(new String[]{});

        // Assert
        verify(initializerDouble).loadStudentGrade(factoryDouble, studentGradeRepositoryDouble, "src/main/resources/StudentGrade.csv");
        verifyNoMoreInteractions(initializerDouble);
    }
}
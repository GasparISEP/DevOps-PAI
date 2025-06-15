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
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class InitializersConfig {

    @Bean
    @Order(1)
    public CommandLineRunner loadDataDegreeType(US10_IWantToConfigureDegreeTypesLevelsController controller,
                                                DegreeTypeInitializer initializer) {
        return (args) -> {
            initializer.loadDegreeType(controller, "src/main/resources/DegreeTypeData.csv");
        };
    }

    @Bean
    @Order(1)
    public CommandLineRunner loadDataCourse(ICourseService service, CourseInitializer initializer) {
        return (args) -> {
            initializer.loadCourse(service, "src/main/resources/CourseData.csv");
        };
    }

    @Bean
    @Order(1)
    public CommandLineRunner loadDataAccessMethod(US02_ConfigureAccessMethodController controller, AccessMethodInitializer initializer) {
        return (args) -> {
            initializer.loadAccessMethod(controller, "src/main/resources/AccessMethodData.csv");
        };
    }

    @Bean
    @Order(1)
    public CommandLineRunner loadDataDepartment(US05_DepartmentRegistryController controller, DepartmentInitializer initializer) {
        return (args) -> {
            initializer.loadDepartment(controller, "src/main/resources/Department.csv");
        };
    }

    @Bean
    @Order(1)
    public CommandLineRunner loadDataSchoolYear(US07_IWantToCreateASchoolYearController controller, SchoolYearInitializer initializer) {
        return args -> {
            initializer.loadSchoolYear(controller,"src/main/resources/SchoolYear.csv");
        };
    }

    @Bean
    @Order(1)
    public CommandLineRunner loadDataRegisterStudent(US08_IWantToRegisterAStudentInTheSystemController controller, StudentInitializer initializer) {
        return (args) -> {
            initializer.loadStudents(controller, "src/main/resources/Student_Data.csv");
        };
    }

    @Bean
    @Order(1)
    public CommandLineRunner loadDataTeacherCategory(US01_ConfigureTeacherCategoryController controller, TeacherCategoryInitializer initializer) {
        return (args) -> {
            initializer.loadTeacherCategory(controller, "src/main/resources/TeacherCategory.csv");
        };
    }

    @Bean
    @Order(2)
    public CommandLineRunner loadDataRegisterTeacher(US13_RegisterTeacherAndRelevantDataController controller, TeacherInitializer initializer) {
        return (args) -> {
            initializer.loadTeachers(controller, "src/main/resources/Teacher_Data.csv");
        };
    }

    @Bean
    @Order(3)
    public CommandLineRunner loadDataProgramme(US11_RegisterProgrammeInTheSystemController controller,
                                               IDegreeTypeRepository degreeTypeRepository, ProgrammeInitializer initializer) {
        return (args) -> {
            initializer.loadProgramme(controller, degreeTypeRepository, "src/main/resources/ProgrammeData.csv");
        };
    }

    @Bean
    @Order(4)
    public CommandLineRunner loadDataProgrammeEnrolment(US09_EnrolStudentInProgrammeController controller, ProgrammeEnrolmentInitializer initializer) {
        return (args) -> {
            initializer.loadProgrammeEnrolment(controller, "src/main/resources/ProgrammeEnrolment_Data.csv");
        };
    }

    @Bean
    @Order(4)
    public CommandLineRunner loadDataProgrammeEdition(IProgrammeEditionService service, ProgrammeEditionInitializer initializer) {
        return args -> {
            initializer.loadProgrammeEdition(service, "src/main/resources/ProgrammeEdition.csv");
        };
    }

    @Bean
    @Order(4)
    public CommandLineRunner loadDataRegisterStudyPlan(US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController controller,
                                                       StudyPlanInitializer initializer) {
        return (args) -> {
            initializer.loadStudyPlan(controller, "src/main/resources/StudyPlan_Data.csv");
        };
    }

    @Bean
    @Order(5)
    public CommandLineRunner loadDataRegisterCourseEdition(US19_CreateCourseEditionController controller,
                                                           ISchoolYearRepository schoolYearRepository, CourseEditionInitializer initializer) {
        return args -> {
            initializer.loadCourseEdition(controller, schoolYearRepository, "src/main/resources/CourseEdition.csv");
        };
    }

    @Bean
    @Order(5)
    public CommandLineRunner loadDataProgrammeEditionEnrolment(IProgrammeEditionEnrolmentService service,
                                                               ProgrammeEditionEnrolmentInitializer initializer) {
        return args -> {
            initializer.loadProgrammeEditionEnrolment(service,
                    "src/main/resources/ProgrammeEditionEnrolment.csv");
        };
    }

    @Bean
    @Order(5)
    public CommandLineRunner loadDataCourseInStudyPlan(ICourseInStudyPlanService service, CourseInStudyPlanInitializer initializer) {
        return (args) -> {
            initializer.loadCourseInStudyPlan(service, "src/main/resources/CourseInStudyPlan.csv");
        };
    }

    @Bean
    @Order(6)
    public CommandLineRunner loadDataCourseEditionEnrolment(US16_EnrolAStudentInACourseEditionController controller,
                                                            CourseEditionEnrolmentInitializer initializer) {
        return (args) -> {
            initializer.loadCourseEditionEnrolments(controller, "src/main/resources/CourseEditionEnrolment.csv");
        };
    }

    @Bean
    @Order(7)
    public CommandLineRunner loadStudentGrades(StudentGradeFactoryImpl factory, IStudentGradeRepository studentGradeRepository,
                                                                                    StudentGradeInitializer initializer) {
        return args -> {
            initializer.loadStudentGrade(factory, studentGradeRepository,"src/main/resources/StudentGrade.csv");
        };
    }
}

package PAI.initializer;

import PAI.controller.*;
import PAI.domain.repositoryInterfaces.degreeType.IDegreeTypeRepository;
import PAI.domain.repositoryInterfaces.schoolYear.ISchoolYearRepository;
import PAI.domain.repositoryInterfaces.studentGrade.IStudentGradeRepository;
import PAI.domain.repositoryInterfaces.teacherCategory.ITeacherCategoryRepository;
import PAI.domain.studentGrade.StudentGradeFactoryImpl;
import PAI.service.course.ICourseService;
import PAI.service.courseInStudyPlan.ICourseInStudyPlanService;
import PAI.service.programmeEdition.IProgrammeEditionService;
import PAI.service.programmeEditionEnrolment.IProgrammeEditionEnrolmentService;
import PAI.service.studentGrade.IStudentGradeService;
import PAI.service.studentGrade.StudentGradeServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

@Configuration
public class InitializersConfig {

    @Bean
    @Order(1)
    @Profile("degree-type")
    public CommandLineRunner loadDataDegreeType(US10_IWantToConfigureDegreeTypesLevelsController controller,
                                                DegreeTypeInitializer initializer) {
        return (args) -> {
            initializer.loadDegreeType(controller, "src/main/resources/DegreeTypeData.csv");
        };
    }

    @Bean
    @Order(1)
    @Profile("course")
    public CommandLineRunner loadDataCourse(ICourseService service, CourseInitializer initializer) {
        return (args) -> {
            initializer.loadCourse(service, "src/main/resources/CourseData.csv");
        };
    }

    @Bean
    @Order(1)
    @Profile("access-method")
    public CommandLineRunner loadDataAccessMethod(US02_ConfigureAccessMethodController controller, AccessMethodInitializer initializer) {
        return (args) -> {
            initializer.loadAccessMethod(controller, "src/main/resources/AccessMethodData.csv");
        };
    }

    @Bean
    @Order(1)
    @Profile("department")
    public CommandLineRunner loadDataDepartment(US05_DepartmentRegistryController controller, DepartmentInitializer initializer) {
        return (args) -> {
            initializer.loadDepartment(controller, "src/main/resources/Department.csv");
        };
    }

    @Bean
    @Order(1)
    @Profile("school-year")
    public CommandLineRunner loadDataSchoolYear(US07_IWantToCreateASchoolYearController controller, SchoolYearInitializer initializer) {
        return args -> {
            initializer.loadSchoolYear(controller,"src/main/resources/SchoolYear.csv");
        };
    }

    @Bean
    @Order(1)
    @Profile("student")
    public CommandLineRunner loadDataRegisterStudent(US08_IWantToRegisterAStudentInTheSystemController controller, StudentInitializer initializer) {
        return (args) -> {
            initializer.loadStudents(controller, "src/main/resources/Student_Data.csv");
        };
    }

    @Bean
    @Order(1)
    @Profile("teacher-category")
    public CommandLineRunner loadDataTeacherCategory(US01_ConfigureTeacherCategoryController controller, TeacherCategoryInitializer initializer) {
        return (args) -> {
            initializer.loadTeacherCategory(controller, "src/main/resources/TeacherCategory.csv");
        };
    }

    @Bean
    @Order(2)
    @Profile("teacher")
    public CommandLineRunner loadDataRegisterTeacher(US13_RegisterTeacherAndRelevantDataController controller, ITeacherCategoryRepository repository, TeacherInitializer initializer) {
        return (args) -> {
            initializer.loadTeachers(controller, repository, "src/main/resources/Teacher_Data.csv");
        };
    }

    @Bean
    @Order(3)
    @Profile("programme")
    public CommandLineRunner loadDataProgramme(US11_RegisterProgrammeInTheSystemController controller,
                                               IDegreeTypeRepository degreeTypeRepository, ProgrammeInitializer initializer) {
        return (args) -> {
            initializer.loadProgramme(controller, degreeTypeRepository, "src/main/resources/ProgrammeData.csv");
        };
    }

    @Bean
    @Order(4)
    @Profile("programmeEnrolment")
    public CommandLineRunner loadDataProgrammeEnrolment(US09_EnrolStudentInProgrammeController controller, ProgrammeEnrolmentInitializer initializer) {
        return (args) -> {
            initializer.loadProgrammeEnrolment(controller, "src/main/resources/ProgrammeEnrolment_Data.csv");
        };
    }

    @Bean
    @Order(4)
    @Profile("programme-edition")
    public CommandLineRunner loadDataProgrammeEdition(IProgrammeEditionService service, ProgrammeEditionInitializer initializer) {
        return args -> {
            initializer.loadProgrammeEdition(service, "src/main/resources/ProgrammeEdition.csv");
        };
    }

    @Bean
    @Order(4)
    @Profile("studyPlan")
    public CommandLineRunner loadDataRegisterStudyPlan(US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController controller,
                                                       StudyPlanInitializer initializer) {
        return (args) -> {
            initializer.loadStudyPlan(controller, "src/main/resources/StudyPlan_Data.csv");
        };
    }

    @Bean
    @Order(5)
    @Profile("course-edition")
    public CommandLineRunner loadDataRegisterCourseEdition(US19_CreateCourseEditionController controller,
                                                           ISchoolYearRepository schoolYearRepository, CourseEditionInitializer initializer) {
        return args -> {
            initializer.loadCourseEdition(controller, schoolYearRepository, "src/main/resources/CourseEdition.csv");
        };
    }

    @Bean
    @Order(5)
    @Profile("programme-edition-enrolment")
    public CommandLineRunner loadDataProgrammeEditionEnrolment(IProgrammeEditionEnrolmentService service,
                                                               ProgrammeEditionEnrolmentInitializer initializer) {
        return args -> {
            initializer.loadProgrammeEditionEnrolment(service,
                    "src/main/resources/ProgrammeEditionEnrolment.csv");
        };
    }

    @Bean
    @Order(5)
    @Profile("course-in-study-plan")
    public CommandLineRunner loadDataCourseInStudyPlan(ICourseInStudyPlanService service, CourseInStudyPlanInitializer initializer) {
        return (args) -> {
            initializer.loadCourseInStudyPlan(service, "src/main/resources/CourseInStudyPlan.csv");
        };
    }

    @Bean
    @Order(6)
    @Profile("course-edition-enrolment")
    public CommandLineRunner loadDataCourseEditionEnrolment(US16_EnrolAStudentInACourseEditionController controller,
                                                            CourseEditionEnrolmentInitializer initializer) {
        return (args) -> {
            initializer.loadCourseEditionEnrolments(controller, "src/main/resources/CourseEditionEnrolment.csv");
        };
    }

    @Bean
    @Order(7)
    @Profile("studentGrade")
    public CommandLineRunner loadStudentGrades( IStudentGradeService studentGradeService,
                                               StudentGradeInitializer initializer) {
        return args -> {
            initializer.loadStudentGrade(studentGradeService ,"src/main/resources/StudentGrade.csv");
        };
    }
}

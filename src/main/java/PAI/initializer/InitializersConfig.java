package PAI.initializer;

import PAI.controller.*;
import PAI.domain.repositoryInterfaces.degreeType.IDegreeTypeRepository;
import PAI.service.course.ICourseService;
import PAI.service.programmeEdition.IProgrammeEditionService;
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
}

package PAI.initializer;

import PAI.controller.*;
import PAI.service.course.ICourseService;
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
}

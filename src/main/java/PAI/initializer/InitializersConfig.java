package PAI.initializer;

import PAI.controller.*;
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
}

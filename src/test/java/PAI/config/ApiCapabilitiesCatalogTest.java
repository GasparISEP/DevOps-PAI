package PAI.config;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

    class ApiCapabilitiesCatalogTest {

        @Test
        void shouldContainAllExpectedResources() {
            //Arrange & Act
            Map<String, Object> capabilities = ApiCapabilitiesCatalog.getCapabilities();

            //Assert
            assertTrue(capabilities.containsKey("teachers"));
            assertTrue(capabilities.containsKey("departments"));
            assertTrue(capabilities.containsKey("programmeeditions"));
            assertTrue(capabilities.containsKey("courseeditions"));
            assertTrue(capabilities.containsKey("teachercategories"));
            assertTrue(capabilities.containsKey("accessmethods"));
            assertTrue(capabilities.containsKey("programmes"));
            assertTrue(capabilities.containsKey("degreetypes"));
            assertTrue(capabilities.containsKey("schoolyears"));
            assertTrue(capabilities.containsKey("students"));

        }

        @Test
        @SuppressWarnings("unchecked")
        void shouldContainCorrectMethodsForAdministratorOnTeachers() {
            // Arrange
            Map<String, Object> capabilities = ApiCapabilitiesCatalog.getCapabilities();
            Map<String, Object> teachers = (Map<String, Object>) capabilities.get("teachers");
            Map<String, List<String>> roles = (Map<String, List<String>>) teachers.get("roles");

            // Act
            List<String> adminMethods = roles.get("administrator");

            // Assert
            assertTrue(adminMethods.contains("GET"));
            assertTrue(adminMethods.contains("POST"));
        }

        @Test
        @SuppressWarnings("unchecked")
        void shouldIncludeUrlInEachResourceIfContextAvailable() {
            // Act + Assert
            Map<String, Object> capabilities = ApiCapabilitiesCatalog.getCapabilities();

            for (Map.Entry<String, Object> entry : capabilities.entrySet()) {
                Map<String, Object> resource = (Map<String, Object>) entry.getValue();
                assertTrue(resource.containsKey("url"), "Resource " + entry.getKey() + " does not contain url");
                assertTrue(resource.containsKey("roles"), "Resource " + entry.getKey() + " does not contain roles");
            }
        }
    }
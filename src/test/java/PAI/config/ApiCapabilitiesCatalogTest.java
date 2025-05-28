package PAI.config;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

    class ApiCapabilitiesCatalogTest {

        @Test
        void shouldContainAllExpectedResources() {
            Map<String, Object> capabilities = ApiCapabilitiesCatalog.getCapabilities();

            assertTrue(capabilities.containsKey("teachers"));
            assertTrue(capabilities.containsKey("departments"));
            assertTrue(capabilities.containsKey("students"));
        }

        @Test
        @SuppressWarnings("unchecked")
        void shouldContainCorrectMethodsForAdministratorOnTeachers() {
            Map<String, Object> capabilities = ApiCapabilitiesCatalog.getCapabilities();
            Map<String, Object> teachers = (Map<String, Object>) capabilities.get("teachers");

            assertNotNull(teachers);
            Map<String, List<String>> roles = (Map<String, List<String>>) teachers.get("roles");

            List<String> adminMethods = roles.get("administrator");
            assertTrue(adminMethods.contains("GET"));
            assertTrue(adminMethods.contains("POST"));
        }

        @Test
        @SuppressWarnings("unchecked")
        void shouldIncludeUrlInEachResourceIfContextAvailable() {
            Map<String, Object> capabilities = ApiCapabilitiesCatalog.getCapabilities();

            for (Map.Entry<String, Object> entry : capabilities.entrySet()) {
                Map<String, Object> resource = (Map<String, Object>) entry.getValue();
                assertTrue(resource.containsKey("url"), "Resource " + entry.getKey() + " does not contain url");
                assertTrue(resource.containsKey("roles"), "Resource " + entry.getKey() + " does not contain roles");
            }
        }
    }
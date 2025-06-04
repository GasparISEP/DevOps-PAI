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
        Map<String, Object> capabilities = ApiCapabilitiesCatalog.getCapabilities();
        Map<String, Object> teachers = (Map<String, Object>) capabilities.get("teachers");
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

    @Test
    void shouldReturnOnlyAllowedResourcesForAdministratorRole() {
        Map<String, Object> filtered = ApiCapabilitiesCatalog.getCapabilitiesForRole("administrator");

        assertTrue(filtered.containsKey("teachers"));
        assertTrue(filtered.containsKey("departments"));
        assertTrue(filtered.containsKey("students"));
        assertFalse(filtered.containsKey("info"));
    }

    @Test
    void shouldReturnInfoMessageWhenRoleIsUnknown() {
        Map<String, Object> filtered = ApiCapabilitiesCatalog.getCapabilitiesForRole("ghost");

        assertTrue(filtered.containsKey("info"));
        assertEquals("Role not recognized. Only basic capabilities exposed.", filtered.get("info"));
    }

    @Test
    void shouldNormalizeRoleCaseInsensitive() {
        Map<String, Object> lower = ApiCapabilitiesCatalog.getCapabilitiesForRole("administrator");
        Map<String, Object> upper = ApiCapabilitiesCatalog.getCapabilitiesForRole("ADMINISTRATOR");
        Map<String, Object> mixed = ApiCapabilitiesCatalog.getCapabilitiesForRole("AdminIstrator");

        assertEquals(lower, upper);
        assertEquals(lower, mixed);
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldContainOnlyValidHttpMethods() {
        List<String> validMethods = List.of("GET", "POST", "PATCH", "DELETE");
        Map<String, Object> filtered = ApiCapabilitiesCatalog.getCapabilitiesForRole("administrator");

        for (Object value : filtered.values()) {
            if (value instanceof Map<?, ?> resource) {
                List<String> methods = (List<String>) resource.get("methods");
                assertNotNull(methods);
                for (String method : methods) {
                    assertTrue(validMethods.contains(method), "Invalid HTTP method: " + method);
                }
            }
        }
    }
}

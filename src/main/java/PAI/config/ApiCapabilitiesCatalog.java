package PAI.config;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

public class ApiCapabilitiesCatalog {

    public static Map<String, Object> getCapabilities() {
        Map<String, Object> api = new LinkedHashMap<>();

        api.put("teachers", Map.of(
                "url", linkTo(PAI.controllerRest.TeacherRestController.class).toUri().toString(),
                "roles", Map.of(
                        "administrator", List.of("GET", "POST"),
                        "humanResourcesCollaborator", List.of("GET", "POST", "PATCH"),
                        "teacher", List.of("GET"),
                        "student", List.of("GET"),
                        "departmentDirector", List.of("GET"),
                        "academicServicesCollaborator", List.of("GET"),
                        "ruc", List.of("GET"),
                        "programmeDirector", List.of("GET")
                )
        ));

        api.put("departments", Map.of(
                "url", linkTo(PAI.controllerRest.DepartmentRestController.class).toUri().toString(),
                "roles", Map.of(
                        "administrator", List.of("GET", "POST", "PATCH"),
                        "departmentDirector", List.of("GET"),
                        "academicServicesCollaborator", List.of("GET"),
                        "ruc", List.of("GET"),
                        "teacher", List.of("GET"),
                        "student", List.of("GET"),
                        "programmeDirector", List.of("GET"),
                        "humanResourcesCollaborator", List.of("GET")
                )
        ));

        api.put("programmeeditions", Map.of(
                "url", linkTo(PAI.controllerRest.ProgrammeEditionRestController.class).toUri().toString(),
                "roles", Map.of(
                        "administrator", List.of("GET"),
                        "academicServicesCollaborator", List.of("GET", "POST"),
                        "departmentDirector", List.of("GET"),
                        "ruc", List.of("GET"),
                        "teacher", List.of("GET"),
                        "student", List.of("GET"),
                        "programmeDirector", List.of("GET"),
                        "humanResourcesCollaborator", List.of("GET")
                )
        ));

        api.put("courseeditions", Map.of(
                "url", linkTo(PAI.controllerRest.CourseEditionRestController.class).toUri().toString(),
                "roles", Map.of(
                        "administrator", List.of("GET"),
                        "academicServicesCollaborator", List.of("GET", "POST", "DELETE"),
                        "ruc", List.of("GET", "PATCH"),
                        "programmeDirector", List.of("GET"),
                        "teacher", List.of("GET"),
                        "student", List.of("GET"),
                        "humanResourcesCollaborator", List.of("GET"),
                        "departmentDirector", List.of("GET")
                )
        ));

        api.put("teachercategories", Map.of(
                "url", linkTo(PAI.controllerRest.TeacherCategoryRestController.class).toUri().toString(),
                "roles", Map.of(
                        "administrator", List.of("GET", "POST"),
                        "humanResourcesCollaborator", List.of("GET"),
                        "departmentDirector", List.of("GET"),
                        "ruc", List.of("GET"),
                        "teacher", List.of("GET"),
                        "student", List.of("GET"),
                        "programmeDirector", List.of("GET"),
                        "academicServicesCollaborator", List.of("GET")
                )
        ));

        api.put("accessmethods", Map.of(
                "url", linkTo(PAI.controllerRest.AccessMethodRestController.class).toUri().toString(),
                "roles", Map.of(
                        "administrator", List.of("GET", "POST"),
                        "academicServicesCollaborator", List.of("GET"),
                        "humanResourcesCollaborator", List.of("GET"),
                        "departmentDirector", List.of("GET"),
                        "ruc", List.of("GET"),
                        "teacher", List.of("GET"),
                        "programmeDirector", List.of("GET")
                )
        ));

        api.put("programmes", Map.of(
                "url", linkTo(PAI.controllerRest.ProgrammeRestController.class).toUri().toString(),
                "roles", Map.of(
                        "administrator", List.of("GET"),
                        "academicServicesCollaborator", List.of("GET", "POST", "PATCH"),
                        "programmeDirector", List.of("GET"),
                        "humanResourcesCollaborator", List.of("GET"),
                        "departmentDirector", List.of("GET"),
                        "ruc", List.of("GET"),
                        "teacher", List.of("GET"),
                        "student", List.of("GET")
                )
        ));

        api.put("degreetypes", Map.of(
                "url", linkTo(PAI.controllerRest.DegreeTypeRestController.class).toUri().toString(),
                "roles", Map.of(
                        "administrator", List.of("GET", "POST"),
                        "humanResourcesCollaborator", List.of("GET"),
                        "departmentDirector", List.of("GET"),
                        "ruc", List.of("GET"),
                        "teacher", List.of("GET"),
                        "student", List.of("GET"),
                        "programmeDirector", List.of("GET"),
                        "academicServicesCollaborator", List.of("GET")
                )
        ));

        api.put("schoolyears", Map.of(
                "url", linkTo(PAI.controllerRest.SchoolYearRestController.class).toUri().toString(),
                "roles", Map.of(
                        "administrator", List.of("GET", "POST"),
                        "academicServicesCollaborator", List.of("GET"),
                        "humanResourcesCollaborator", List.of("GET"),
                        "departmentDirector", List.of("GET"),
                        "ruc", List.of("GET"),
                        "teacher", List.of("GET"),
                        "student", List.of("GET"),
                        "programmeDirector", List.of("GET")
                )
        ));

        api.put("students", Map.of(
                "url", linkTo(PAI.controllerRest.StudentRestController.class).toUri().toString(),
                "roles", Map.of(
                        "administrator", List.of("GET"),
                        "academicServicesCollaborator", List.of("GET", "POST", "PATCH"),
                        "student", List.of("GET"),
                        "humanResourcesCollaborator", List.of("GET"),
                        "departmentDirector", List.of("GET"),
                        "ruc", List.of("GET"),
                        "teacher", List.of("GET"),
                        "programmeDirector", List.of("GET")
                )
        ));

        return api;
    }


    public static Map<String, Object> getCapabilitiesForRole(String role) {
        String normalizedRole = role.toLowerCase();
        Map<String, Object> all = getCapabilities();
        Map<String, Object> filtered = new LinkedHashMap<>();

        for (Map.Entry<String, Object> entry : all.entrySet()) {
            String resource = entry.getKey();
            Map<String, Object> value = (Map<String, Object>) entry.getValue();
            String url = (String) value.get("url");

            Map<String, List<String>> roles = (Map<String, List<String>>) value.get("roles");

            if (roles.containsKey(normalizedRole)) {
                filtered.put(resource, Map.of(
                        "url", url,
                        "methods", roles.get(normalizedRole)
                ));
            }
        }

        if (filtered.isEmpty()) {
            filtered.put("info", "Role not recognized. Only basic capabilities exposed.");
        }

        return filtered;
    }
}
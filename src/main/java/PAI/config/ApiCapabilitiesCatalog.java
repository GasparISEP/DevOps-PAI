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
                        "humanResourcesCollaborator", List.of("GET", "POST", "PATCH")
                )
        ));

        api.put("departments", Map.of(
                "url", linkTo(PAI.controllerRest.DepartmentRestController.class).toUri().toString(),
                "roles", Map.of(
                        "administrator", List.of("GET", "POST", "PATCH", "DELETE"),
                        "departmentDirector", List.of("GET")
                )
        ));

        api.put("programmeeditions", Map.of(
                "url", linkTo(PAI.controllerRest.ProgrammeEditionRestController.class).toUri().toString(),
                "roles", Map.of(
                        "administrator", List.of("GET"),
                        "academicServicesCollaborator", List.of("GET", "POST"),
                        "departmentDirector", List.of("GET")
                )
        ));

        api.put("courseeditions", Map.of(
                "url", linkTo(PAI.controllerRest.CourseEditionRestController.class).toUri().toString(),
                "roles", Map.of(
                        "administrator", List.of("GET"),
                        "academicServicesCollaborator", List.of("GET", "POST", "DELETE"),
                        "ruc", List.of("GET", "PATCH"),
                        "programmeDirector", List.of("GET")
                )
        ));

        api.put("teachercategories", Map.of(
                "url", linkTo(PAI.controllerRest.TeacherCategoryRestController.class).toUri().toString(),
                "roles", Map.of(
                        "administrator", List.of("GET", "POST"),
                        "humanResourcesCollaborator", List.of("GET")
                )
        ));

        api.put("accessmethods", Map.of(
                "url", linkTo(PAI.controllerRest.AccessMethodRestController.class).toUri().toString(),
                "roles", Map.of(
                        "administrator", List.of("GET", "POST"),
                        "academicServicesCollaborator", List.of("GET")
                )
        ));

        api.put("programmes", Map.of(
                "url", linkTo(PAI.controllerRest.ProgrammeRestController.class).toUri().toString(),
                "roles", Map.of(
                        "administrator", List.of("GET"),
                        "academicServicesCollaborator", List.of("GET", "POST", "PATCH"),
                        "programmeDirector", List.of("GET")
                )
        ));

        api.put("degreetypes", Map.of(
                "url", linkTo(PAI.controllerRest.DegreeTypeRestController.class).toUri().toString(),
                "roles", Map.of(
                        "administrator", List.of("GET", "POST")
                )
        ));

        api.put("schoolyears", Map.of(
                "url", linkTo(PAI.controllerRest.SchoolYearRestController.class).toUri().toString(),
                "roles", Map.of(
                        "administrator", List.of("GET", "POST"),
                        "academicServicesCollaborator", List.of("GET")
                )
        ));

        api.put("students", Map.of(
                "url", linkTo(PAI.controllerRest.StudentRestController.class).toUri().toString(),
                "roles", Map.of(
                        "administrator", List.of("GET"),
                        "academicServicesCollaborator", List.of("GET", "POST", "PATCH"),
                        "student", List.of("GET")
                )
        ));

        return api;
    }
}
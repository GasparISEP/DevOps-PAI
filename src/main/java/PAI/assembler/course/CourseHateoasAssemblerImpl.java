package PAI.assembler.course;

import PAI.controllerRest.TeacherRestController;
import PAI.controllerRest.CourseRESTController;
import PAI.dto.course.CourseResponseDTO;
import PAI.dto.teacher.TeacherDTO;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class CourseHateoasAssemblerImpl implements ICourseHateoasAssembler{

    @Override
    public EntityModel<CourseResponseDTO> toModel (CourseResponseDTO courseResponseDTO) {
        if (courseResponseDTO == null) {
            throw new IllegalArgumentException("CourseResponseDTO must not be null");
        }
        return EntityModel.of(courseResponseDTO,
                linkTo(methodOn(CourseRESTController.class)
                        .getAllCourses())
                        .withRel("all"));
    }
}

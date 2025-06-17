package PAI.assembler.ProgrammeAndCourses;

import PAI.controllerRest.StudentProgrammeEditionEnrolmentRestController;
import PAI.controllerRest.StudentRestController;
import PAI.dto.ProgrammeAndCourses.StudentEnrolmentResultDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProgrammeAndCoursesHateoasAssembler implements IProgrammeAndCoursesHateoasAssembler{
    @Override
    public EntityModel<StudentEnrolmentResultDto> toModel(StudentEnrolmentResultDto dto) {
        return EntityModel.of(dto,
       linkTo(methodOn(StudentRestController.class)
                .findEnrolledCourseEditionsForStudent(dto.programmeEditionEnrolment().studentId()))
                .withRel("enrolled-course-editions"),
        linkTo(methodOn(StudentProgrammeEditionEnrolmentRestController.class)
                .getProgrammeEditionEnrollmentsByStudentID(dto.programmeEditionEnrolment().studentId()))
                .withRel("enrolled-programme-editions"));

    }
}

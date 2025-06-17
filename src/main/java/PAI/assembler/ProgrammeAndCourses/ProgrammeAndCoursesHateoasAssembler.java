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
        EntityModel<StudentEnrolmentResultDto> model = EntityModel.of(dto);

        int studentId = dto.programmeEditionEnrolment().studentId();
        UUID programmeEnrolmentGID = dto.programmeEditionEnrolment().genID();

        // Link to course editions the student is enrolled in
        model.add(linkTo(methodOn(StudentRestController.class)
                .findEnrolledCourseEditionsForStudent(studentId))
                .withRel("enrolled-course-editions"));

        // Link to all programme editions the student is enrolled in
        model.add(linkTo(methodOn(StudentProgrammeEditionEnrolmentRestController.class)
                .getProgrammeEditionEnrollmentsByStudentID(studentId))
                .withRel("enrolled-programme-editions"));

        return model;
    }


}

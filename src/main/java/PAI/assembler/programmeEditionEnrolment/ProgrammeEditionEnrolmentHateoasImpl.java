package PAI.assembler.programmeEditionEnrolment;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import PAI.controllerRest.StudentProgrammeEditionEnrolmentRestController;
import PAI.dto.programmeEditionEnrolment.ProgrammeEditionEnrolmentDetailDto;
import PAI.controllerRest.CourseEditionRestController;

@Component
public class ProgrammeEditionEnrolmentHateoasImpl implements IProgrammeEditionEnrolmentHateoasAssembler, RepresentationModelAssembler<ProgrammeEditionEnrolmentDetailDto, EntityModel<ProgrammeEditionEnrolmentDetailDto>> {
    @Override
    @NonNull
    public EntityModel<ProgrammeEditionEnrolmentDetailDto> toModel(@NonNull ProgrammeEditionEnrolmentDetailDto programmeEditionEnrolment) {
        return EntityModel.of(programmeEditionEnrolment,
            linkTo(methodOn(StudentProgrammeEditionEnrolmentRestController.class)
                .getProgrammeEditionEnrollmentsByStudentID(programmeEditionEnrolment.studentID()))
                .withSelfRel()
                .withRel("get-programme-edition-enrolments-by-student-id")
        );
    }

    @Override
    public CollectionModel<EntityModel<ProgrammeEditionEnrolmentDetailDto>> toCollectionModel(
            List<ProgrammeEditionEnrolmentDetailDto> programmeEditionEnrolments) {
        List<EntityModel<ProgrammeEditionEnrolmentDetailDto>> listOfProgrammeEditionEnrolmentDetailDtosWithHypermedia = programmeEditionEnrolments.stream()
            .map(programmeEditionEnrolment -> EntityModel.of(programmeEditionEnrolment,
                linkTo(methodOn(StudentProgrammeEditionEnrolmentRestController.class)
                    .getProgrammeEditionEnrollmentsByStudentID(programmeEditionEnrolment.studentID()))
                    .withSelfRel(),
                linkTo(methodOn(CourseEditionRestController.class)
                    .findAllCourseEditions())
                    .withRel("get-all-course-editions")
            ))
            .collect(Collectors.toList());
        return CollectionModel.of(listOfProgrammeEditionEnrolmentDetailDtosWithHypermedia);
    }
}

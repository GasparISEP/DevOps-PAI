package PAI.assembler.programmeEnrolment;

import PAI.controllerRest.StudentRestController;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentDTO;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProgrammeEnrolmentHATEOASAssembler implements RepresentationModelAssembler<ProgrammeEnrolmentResponseDTO, EntityModel<ProgrammeEnrolmentResponseDTO>>,
        IProgrammeEnrolmentHATEOASAssembler {

    @Override
    public EntityModel<ProgrammeEnrolmentResponseDTO> toModel(ProgrammeEnrolmentResponseDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(StudentRestController.class)
                        .getEnrolmentByStudentAndProgramme(dto.getProgrammeEnrolmentGID()))
                        .withSelfRel(),

                linkTo(methodOn(StudentRestController.class)
                        .getEnrolmentByStudentAndProgramme(dto.getProgrammeEnrolmentGID()))
                        .withRel("viewDetails"),

                linkTo(methodOn(StudentRestController.class)
                        .enrolStudent(null))
                        .withRel("enrol-programme-courses")
                        .withType("POST")
        );
    }
}

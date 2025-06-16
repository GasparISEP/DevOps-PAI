package PAI.assembler.student;

import PAI.controllerRest.StudentRestController;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentDTO;
import PAI.dto.student.StudentResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class StudentHateoasAssemblerImpl
        implements RepresentationModelAssembler<StudentResponseDTO, EntityModel<StudentResponseDTO>>,
        IStudentHateoasAssembler {

    @Override
    public EntityModel<StudentResponseDTO> toModel(StudentResponseDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(StudentRestController.class)
                        .getLastStudentID())
                        .withRel("last-student-id"),

                linkTo(methodOn(StudentRestController.class)
                        .getAllStudents())
                        .withRel("all"),

                linkTo(methodOn(StudentRestController.class)
                        .getStudentByID(dto.getStudentID()))
                        .withSelfRel()
        );
    }
}
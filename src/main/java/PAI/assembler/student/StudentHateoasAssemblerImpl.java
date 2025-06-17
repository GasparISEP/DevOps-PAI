package PAI.assembler.student;

import PAI.controllerRest.StudentRestController;
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
                        .getStudentByID(dto.getStudentID()))
                        .withSelfRel(),

                linkTo(methodOn(StudentRestController.class)
                        .getStudentByID(dto.getStudentID()))
                        .withRel("view-details"),

                linkTo(methodOn(StudentRestController.class)
                        .getAllStudents())
                        .withRel("all-students"),

                linkTo(methodOn(StudentRestController.class)
                        .enrolStudentInProgramme(null))
                        .withRel("enrol-in-programme")
        );
    }
}
package PAI.assembler.student;

import PAI.controllerRest.StudentRestController;
import PAI.dto.student.StudentResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.Link;

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

                Link.of("http://localhost:3000/students/display").withRel("viewAll"),

                linkTo(methodOn(StudentRestController.class)
                        .getStudentByID(dto.getStudentID()))
                        .withSelfRel(),

                linkTo(methodOn(StudentRestController.class)
                        .enrolStudentInProgramme(null))
                        .withRel("enrol-in-programme")

        );
    }
}
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
                Link.of("/students/" + dto.getStudentID()).withRel("viewDetails"),
                Link.of("/students/display").withRel("viewAll"),
                linkTo(methodOn(StudentRestController.class).getStudentByID(dto.getStudentID())).withSelfRel(),
                linkTo(methodOn(StudentRestController.class).enrolStudentInProgramme(null)).withRel("enrol-in-programme")
        );
    }
}
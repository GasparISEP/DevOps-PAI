package PAI.assembler.student;

import PAI.controllerRest.StudentRestController;
import PAI.domain.student.Student;
import PAI.assembler.student.StudentDTOAssemblerImpl;
import PAI.dto.student.StudentResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class StudentHateoasAssemblerImpl implements IStudentHateoasAssembler {

    private final StudentDTOAssemblerImpl dtoAssembler;

    public StudentHateoasAssemblerImpl(StudentDTOAssemblerImpl dtoAssembler) {
        this.dtoAssembler = dtoAssembler;
    }

    @Override
    public EntityModel<StudentResponseDTO> toModel(Student student) {
        StudentResponseDTO dto = dtoAssembler.toStudentResponseDTO(student);

        return EntityModel.of(dto,
                // Adiciona o link para consultar o último ID registado
                linkTo(methodOn(StudentRestController.class).getLastStudentID()).withRel("last-student-id")
        );
    }
}
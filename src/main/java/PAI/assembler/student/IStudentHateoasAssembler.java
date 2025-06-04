package PAI.assembler.student;

import PAI.dto.student.StudentResponseDTO;
import org.springframework.hateoas.EntityModel;

public interface IStudentHateoasAssembler {
    EntityModel<StudentResponseDTO> toModel(StudentResponseDTO dto);
}
package PAI.assembler.student;

import PAI.domain.student.Student;
import PAI.dto.student.StudentResponseDTO;
import org.springframework.hateoas.EntityModel;

public interface IStudentHateoasAssembler {
    EntityModel<StudentResponseDTO> toModel(Student student);
}
package PAI.assembler.courseEdition;

import PAI.dto.courseEdition.StudentCountDTO;
import org.springframework.stereotype.Component;

@Component
public class StudentCountAssembler implements IStudentCountAssembler{

    public StudentCountDTO fromDomainToDTO(int studentCount) {

        return new StudentCountDTO(studentCount);
    }
}

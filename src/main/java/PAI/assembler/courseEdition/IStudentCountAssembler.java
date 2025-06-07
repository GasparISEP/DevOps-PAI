package PAI.assembler.courseEdition;

import PAI.dto.courseEdition.StudentCountDTO;

public interface IStudentCountAssembler {

    StudentCountDTO fromDomainToDTO(int studentCount);
}

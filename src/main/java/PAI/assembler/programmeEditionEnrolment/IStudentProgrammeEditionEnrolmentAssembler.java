package PAI.assembler.programmeEditionEnrolment;

import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.dto.programmeEditionEnrolment.StudentProgrammeEditionEnrolmentDTO;

public interface IStudentProgrammeEditionEnrolmentAssembler {
    StudentProgrammeEditionEnrolmentDTO toDTO(ProgrammeEdition programmeEdition);
}

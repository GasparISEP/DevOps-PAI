package PAI.assembler.programmeEditionEnrolment;

import java.util.List;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.StudentID;
import PAI.dto.programmeEditionEnrolment.ProgrammeEditionEnrolmentDetailDto;

public interface IProgrammeEditionEnrolmentAssembler {
    
    List<ProgrammeEditionEnrolmentDetailDto> toDtoList(List<ProgrammeEditionID> programmeEditions, StudentID studentID) throws Exception;
}


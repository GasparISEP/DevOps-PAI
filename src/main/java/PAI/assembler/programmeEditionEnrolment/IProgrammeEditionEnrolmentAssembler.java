package PAI.assembler.programmeEditionEnrolment;

import PAI.VOs.ProgrammeEditionEnrolmentID;
import PAI.dto.programmeEditionEnrolment.ProgrammeEditionEnrolmentDetailDto;

public interface IProgrammeEditionEnrolmentAssembler {
    
    ProgrammeEditionEnrolmentID toProgrammeEditionEnrolmentID(ProgrammeEditionEnrolmentDetailDto programmeEditionEnrolmentResponseDto) throws Exception;
}


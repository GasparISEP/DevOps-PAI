package PAI.assembler.programmeEditionEnrolment;

import PAI.VOs.ProgrammeEditionEnrolmentID;
import PAI.dto.programmeEditionEnrolment.ProgrammeEditionEnrolmentResponseDto;

public interface IProgrammeEditionEnrolmentAssembler {
    
    ProgrammeEditionEnrolmentID toProgrammeEditionEnrolmentID(ProgrammeEditionEnrolmentResponseDto programmeEditionEnrolmentResponseDto) throws Exception;
}


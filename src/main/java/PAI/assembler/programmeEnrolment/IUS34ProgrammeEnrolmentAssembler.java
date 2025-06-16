package PAI.assembler.programmeEnrolment;

import PAI.VOs.US34ListOfProgrammes;
import PAI.dto.programmeEnrolment.US34ListOfProgrammesDTO;

public interface IUS34ProgrammeEnrolmentAssembler {

    US34ListOfProgrammesDTO toDto(US34ListOfProgrammes domain);
}

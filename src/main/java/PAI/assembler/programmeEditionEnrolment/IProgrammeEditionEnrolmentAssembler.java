package PAI.assembler.programmeEditionEnrolment;

import java.util.List;

import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;
import PAI.dto.programmeEditionEnrolment.ProgrammeEditionEnrolmentDetailDto;

public interface IProgrammeEditionEnrolmentAssembler {
    
    List<ProgrammeEditionEnrolmentDetailDto> toDtoList(List<ProgrammeEditionEnrolment> programmeEditionEnrolment) throws Exception;
}


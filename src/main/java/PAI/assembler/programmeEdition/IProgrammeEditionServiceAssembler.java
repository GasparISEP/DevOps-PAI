package PAI.assembler.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.dto.programmeEdition.CountStudentsRequestDto;
import PAI.dto.programmeEdition.ProgrammeEditionServiceDTO;
import PAI.dto.programmeEdition.ProgrammeEditionIdDto;

public interface IProgrammeEditionServiceAssembler {
    CountStudentsRequestDto toCountStudentsInProgrammeEditionDTO(ProgrammeEdition programmeEdition);
    ProgrammeEdition countStudentsInProgrammeEditionDTOtoDomain(CountStudentsRequestDto dto) throws Exception;
    SchoolYearID toSchoolYearID(ProgrammeEditionServiceDTO programmeEditionServiceDTO);
    ProgrammeID toProgrammeID(ProgrammeEditionServiceDTO programmeEditionServiceDTO);
    ProgrammeEditionServiceDTO toDTO(ProgrammeID programmeID, SchoolYearID schoolYearID);
    ProgrammeEditionID toProgrammeEditionID(ProgrammeEditionIdDto programmeEditionIdDto) throws Exception;
}

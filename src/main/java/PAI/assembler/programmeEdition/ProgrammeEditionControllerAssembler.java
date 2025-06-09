package PAI.assembler.programmeEdition;

import PAI.VOs.*;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.Programme.ProgrammeIDResponseDTO;
import PAI.dto.programmeEdition.CountStudentsDto;
import PAI.dto.programmeEdition.ProgrammeEditionServiceDTO;
import PAI.dto.programmeEdition.ProgrammeEditionRequestDTO;
import PAI.dto.programmeEdition.ProgrammeEditionResponseDTO;
import PAI.dto.schoolYear.SchoolYearIDDTO;
import PAI.dto.schoolYear.SchoolYearIDResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ProgrammeEditionControllerAssembler implements IProgrammeEditionControllerAssembler {

    @Override
    public ProgrammeEditionServiceDTO toDTO(ProgrammeEditionRequestDTO programmeEditionRequest) {
        if (programmeEditionRequest == null) {
            throw new IllegalArgumentException("ProgrammeEditionRequestDTO cannot be null");
        }
        ProgrammeIDDTO idDto = new ProgrammeIDDTO(programmeEditionRequest.programme().acronym());
        SchoolYearIDDTO schoolYearIDDTO = new SchoolYearIDDTO(programmeEditionRequest.schoolYear().id());
        return new ProgrammeEditionServiceDTO(idDto,schoolYearIDDTO);
    }

    @Override
    public ProgrammeEditionResponseDTO toResponseDTO(ProgrammeEditionServiceDTO programmeEdition) {
        if (programmeEdition == null) {
            throw new IllegalArgumentException("ProgrammeEditionDTO cannot be null");
        }
        ProgrammeIDResponseDTO programmeResponseDTO = new ProgrammeIDResponseDTO(programmeEdition.programme().acronym());
        SchoolYearIDResponseDTO syResponseDTO = new SchoolYearIDResponseDTO(programmeEdition.schoolYear().id());
        return new ProgrammeEditionResponseDTO(programmeResponseDTO, syResponseDTO);
    }

    @Override
    public ProgrammeEditionServiceDTO toDTOFromIDs(ProgrammeID programmeID, SchoolYearID schoolYearID) {
        if (programmeID == null || schoolYearID == null) {
            throw new IllegalArgumentException("programmeID and or schoolYearID cannot be null");
        }
        String programmeAcronym = programmeID.getProgrammeAcronym();
        String schoolYearId = schoolYearID.getSchoolYearID().toString();
        ProgrammeIDDTO programmeIDDTO = new ProgrammeIDDTO(programmeAcronym);
        SchoolYearIDDTO schoolYearIDRequestDTO = new SchoolYearIDDTO(schoolYearId);
        return new ProgrammeEditionServiceDTO(programmeIDDTO, schoolYearIDRequestDTO);
    }
    @Override
    public CountStudentsDto toCountDTO(ProgrammeEdition programmeEdition) {
        if (programmeEdition == null) {
            throw new IllegalArgumentException("ProgrammeEdition cannot be null");
        }
        ProgrammeEditionID id = programmeEdition.identity();
        String programmeAcronym = id.getProgrammeID().getAcronym().getAcronym();
        String  schoolYearID = id.getSchoolYearID().getSchoolYearID().toString();

        return new CountStudentsDto(programmeAcronym, schoolYearID);
    }
}

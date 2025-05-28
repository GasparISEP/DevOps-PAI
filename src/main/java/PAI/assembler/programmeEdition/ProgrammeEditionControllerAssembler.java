package PAI.assembler.programmeEdition;

import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.Programme.ProgrammeIDResponseDTO;
import PAI.dto.programmeEdition.ProgrammeEditionDTO;
import PAI.dto.programmeEdition.ProgrammeEditionRequestDTO;
import PAI.dto.programmeEdition.ProgrammeEditionResponseDTO;
import PAI.dto.schoolYear.SchoolYearIDDTO;
import PAI.dto.schoolYear.SchoolYearIDResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ProgrammeEditionControllerAssembler implements IProgrammeEditionControllerAssembler {

    @Override
    public ProgrammeEditionDTO toDTO(ProgrammeEditionRequestDTO programmeEditionRequest) {
        if (programmeEditionRequest == null) {
            throw new IllegalArgumentException("ProgrammeEditionRequestDTO cannot be null");
        }
        ProgrammeIDDTO idDto = new ProgrammeIDDTO(programmeEditionRequest.programme().name(), programmeEditionRequest.programme().acronym());
        SchoolYearIDDTO schoolYearIDDTO = new SchoolYearIDDTO(programmeEditionRequest.schoolYear().id());
        return new ProgrammeEditionDTO(idDto,schoolYearIDDTO);
    }

    @Override
    public ProgrammeEditionResponseDTO toResponseDTO(ProgrammeEditionDTO programmeEdition) {
        if (programmeEdition == null) {
            throw new IllegalArgumentException("ProgrammeEditionDTO cannot be null");
        }
        ProgrammeIDResponseDTO programmeResponseDTO = new ProgrammeIDResponseDTO(programmeEdition.programme().name(), programmeEdition.programme().acronym());
        SchoolYearIDResponseDTO syResponseDTO = new SchoolYearIDResponseDTO(programmeEdition.schoolYear().id());
        return new ProgrammeEditionResponseDTO(programmeResponseDTO, syResponseDTO);
    }

    @Override
    public ProgrammeEditionDTO toDTOFromIDs(ProgrammeID programmeID, SchoolYearID schoolYearID) {
        if (programmeID == null || schoolYearID == null) {
            throw new IllegalArgumentException("programmeID and or schoolYearID cannot be null");
        }
        String programmeName = programmeID.getProgrammeName();
        String programmeAcronym = programmeID.getProgrammeAcronym();
        String schoolYearId = schoolYearID.getSchoolYearID().toString();
        ProgrammeIDDTO programmeIDDTO = new ProgrammeIDDTO(programmeName, programmeAcronym);
        SchoolYearIDDTO schoolYearIDRequestDTO = new SchoolYearIDDTO(schoolYearId);
        return new ProgrammeEditionDTO(programmeIDDTO, schoolYearIDRequestDTO);
    }
}

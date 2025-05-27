package PAI.assembler.programmeEdition;

import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.programmeEdition.ProgrammeEditionDTO;
import PAI.dto.programmeEdition.ProgrammeEditionRequestDTO;
import PAI.dto.programmeEdition.ProgrammeEditionResponseDTO;
import PAI.dto.schoolYear.SchoolYearIDRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class ProgrammeEditionControllerAssembler implements IProgrammeEditionControllerAssembler{

    @Override
    public ProgrammeEditionDTO toDTO(ProgrammeEditionRequestDTO programmeEditionRequest) {
        if (programmeEditionRequest == null){
            throw new IllegalArgumentException("ProgrammeEditionRequestDTO cannot be null");
        }
        return new ProgrammeEditionDTO(programmeEditionRequest.programme(),programmeEditionRequest.schoolYear());
    }

    @Override
    public ProgrammeEditionResponseDTO toResponseDTO(ProgrammeEditionDTO programmeEdition) {
        if (programmeEdition == null){
            throw new IllegalArgumentException("ProgrammeEditionDTO cannot be null");
        }
      return new ProgrammeEditionResponseDTO(programmeEdition.programme(),programmeEdition.schoolYear());
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
        SchoolYearIDRequestDTO schoolYearIDRequestDTO = new SchoolYearIDRequestDTO(schoolYearId);
        return new ProgrammeEditionDTO(programmeIDDTO, schoolYearIDRequestDTO);
    }
}

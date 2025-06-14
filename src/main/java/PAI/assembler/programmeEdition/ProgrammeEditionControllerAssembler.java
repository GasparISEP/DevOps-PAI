package PAI.assembler.programmeEdition;

import PAI.VOs.*;
import PAI.domain.programme.Programme;
import PAI.domain.schoolYear.SchoolYear;
import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.programmeEdition.*;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ProgrammeEditionControllerAssembler implements IProgrammeEditionControllerAssembler {

    @Override
    public ProgrammeEditionRequestServiceDTO toServiceDTOFromRequestDTO(ProgrammeEditionRequestDTO programmeEditionRequest) {
        if (programmeEditionRequest == null) {
            throw new IllegalArgumentException("ProgrammeEditionRequestDTO cannot be null");
        }
        ProgrammeIDDTO idDto = new ProgrammeIDDTO(programmeEditionRequest.programme().acronym());
        return new ProgrammeEditionRequestServiceDTO(idDto);
    }

    @Override
    public ProgrammeEditionResponseDTO toResponseDTOFromServiceDTO(ProgrammeEditionResponseServiceDTO responseDTO) {
        if (responseDTO == null) {
            throw new IllegalArgumentException("ProgrammeEditionResponseServiceDTO cannot be null");
        }
        String acronym = responseDTO.programme().acronym();
        String schoolYearId = responseDTO.schoolYearId();
        ProgrammeIDDTO programmeIDDTO = new ProgrammeIDDTO(acronym);
        return new ProgrammeEditionResponseDTO(programmeIDDTO, schoolYearId);
    }

    @Override
    public List<ProgrammeEditionResponseDTO> toResponseDTOList(ProgrammeEditionResponseServiceDTO responseDTO) {
        return List.of();
    }

    @Override
    public ProgrammeEditionIdDto toIdDto(ProgrammeEditionID id) {
        if (id == null) {
            throw new IllegalArgumentException("ProgrammeEditionID cannot be null");
        }
        String acronym = id.getProgrammeID().getAcronym().getAcronym();
        String schoolYearId = id.getSchoolYearID().getSchoolYearID().toString();
        return new ProgrammeEditionIdDto(acronym, schoolYearId);
    }

    public ProgrammeEditionWithNameAndDescriptionResponseDTO toProgrammeEditionIdResponseDto(Programme programme, SchoolYear schoolYear) {
        if (programme == null) {
            throw new IllegalArgumentException("ProgrammeEdition cannot be null");
        }

        if (schoolYear == null) {
            throw new IllegalArgumentException("ProgrammeEdition cannot be null");
        }

        String name = programme.getProgrammeName().getNameWithNumbersAndSpecialChars();
        String acronym = programme.getAcronym().getAcronym();
        String schoolYearId = schoolYear.identity().toString();
        String description = schoolYear.getDescription().getDescription();

        return new ProgrammeEditionWithNameAndDescriptionResponseDTO(acronym,schoolYearId, name, description);
    }
}
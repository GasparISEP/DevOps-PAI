package PAI.assembler.programmeEdition;

import PAI.VOs.*;
import PAI.domain.programmeEdition.ProgrammeEdition;
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

//    @Override
//    public CountStudentsRequestDto toCountDTO(ProgrammeEdition programmeEdition) {
//        if (programmeEdition == null) {
//            throw new IllegalArgumentException("ProgrammeEdition cannot be null");
//        }
//        ProgrammeEditionID id = programmeEdition.identity();
//        String programmeAcronym = id.getProgrammeID().getAcronym().getAcronym();
//        String  schoolYearID = id.getSchoolYearID().getSchoolYearID().toString();
//
//        return new CountStudentsRequestDto(programmeAcronym, schoolYearID);
//    }
}

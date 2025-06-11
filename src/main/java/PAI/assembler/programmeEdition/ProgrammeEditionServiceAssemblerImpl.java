package PAI.assembler.programmeEdition;

import PAI.VOs.*;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.programmeEdition.RequestServiceDto;
import PAI.dto.programmeEdition.ProgrammeEditionRequestServiceDTO;
import PAI.dto.programmeEdition.ProgrammeEditionResponseServiceDTO;
import PAI.dto.programmeEdition.ProgrammeEditionIdDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static PAI.utils.ValidationUtils.validateNotNull;

@Component
public class ProgrammeEditionServiceAssemblerImpl implements IProgrammeEditionServiceAssembler {

    public ProgrammeEditionServiceAssemblerImpl() {
    }

    @Override
    public ProgrammeEditionResponseServiceDTO toResponseServiceDTOFromProgrammeEdition(ProgrammeEdition programmeEdition) {
        if (programmeEdition == null) {
            throw new IllegalArgumentException("ProgrammeEdition cannot be null");
        }

        ProgrammeEditionID id = programmeEdition.identity();
        String programmeAcronym = id.getProgrammeID().getAcronym().getAcronym();
        String schoolYearID = id.getSchoolYearID().getSchoolYearID().toString();

        return new ProgrammeEditionResponseServiceDTO(
                new ProgrammeIDDTO(programmeAcronym),
                schoolYearID
        );
    }

    @Override
    public ProgrammeEdition toProgrammeEditionFromRequestServiceDTO(RequestServiceDto dto) throws Exception {
        if (dto == null) {
            throw new IllegalArgumentException("ProgrammeEditionDTO cannot be null");
        }
        Acronym programmeAcronym = new Acronym(dto.programmeAcronym());
        ProgrammeID programmeID = new ProgrammeID(programmeAcronym);
        SchoolYearID schoolYearID = new SchoolYearID(UUID.fromString(dto.schoolYearID()));
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);
        ProgrammeEditionGeneratedID programmeEditionGeneratedID = new ProgrammeEditionGeneratedID();

        return new ProgrammeEdition(programmeEditionID, programmeID, schoolYearID, programmeEditionGeneratedID);
    }

    @Override
    public ProgrammeID toProgrammeID(ProgrammeEditionRequestServiceDTO programmeEditionRequestServiceDTO) {
        if (programmeEditionRequestServiceDTO == null) {
            throw new IllegalArgumentException("ProgrammeEditionDTO cannot be null");
        }
        String programmeAcronym = programmeEditionRequestServiceDTO.programme().acronym();
        return new ProgrammeID(new Acronym(programmeAcronym));
    }

    @Override
    public ProgrammeEditionResponseServiceDTO toServiceResponseDTOFromIDs(ProgrammeID programmeID, SchoolYearID schoolYearID) {
        if (programmeID == null || schoolYearID == null) {
            throw new IllegalArgumentException("programmeID, schoolYearID, and programmeEditionID cannot be null");
        }
        String programmeAcronym = programmeID.getProgrammeAcronym();
        String schoolYearId = schoolYearID.getSchoolYearID().toString();
        ProgrammeIDDTO programmeIDDTO = new ProgrammeIDDTO(programmeAcronym);
        return new ProgrammeEditionResponseServiceDTO(programmeIDDTO, schoolYearId);
    }

}

package PAI.assembler.programmeEdition;

import PAI.VOs.*;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.programmeEdition.CountStudentsRequestDto;
import PAI.dto.programmeEdition.ProgrammeEditionServiceDTO;
import PAI.dto.programmeEdition.ProgrammeEditionIdDto;
import PAI.dto.schoolYear.SchoolYearIDDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProgrammeEditionServiceAssemblerImpl implements IProgrammeEditionServiceAssembler {

    public ProgrammeEditionServiceAssemblerImpl() {
    }

    @Override
    public CountStudentsRequestDto toCountStudentsInProgrammeEditionDTO(ProgrammeEdition programmeEdition) {
        if (programmeEdition == null) {
            throw new IllegalArgumentException("ProgrammeEdition cannot be null");
        }
        ProgrammeEditionID id = programmeEdition.identity();
        String programmeAcronym = id.getProgrammeID().getAcronym().getAcronym();
        String schoolYearID = id.getSchoolYearID().getSchoolYearID().toString();

        return new CountStudentsRequestDto(programmeAcronym, schoolYearID);
    }

    @Override
    public ProgrammeEdition CountStudentsInProgrammeEditionDTOtoDomain(CountStudentsRequestDto dto) throws Exception {
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
    public SchoolYearID toSchoolYearID(ProgrammeEditionServiceDTO programmeEditionServiceDTO) {
        if (programmeEditionServiceDTO == null) {
            throw new IllegalArgumentException("ProgrammeEditionDTO cannot be null");
        }
        String schoolYearId = programmeEditionServiceDTO.schoolYear().id();
        return new SchoolYearID(UUID.fromString(schoolYearId));
    }

    @Override
    public ProgrammeID toProgrammeID(ProgrammeEditionServiceDTO programmeEditionServiceDTO) {
        if (programmeEditionServiceDTO == null) {
            throw new IllegalArgumentException("ProgrammeEditionDTO cannot be null");
        }
        String programmeAcronym = programmeEditionServiceDTO.programme().acronym();
        return new ProgrammeID(new Acronym(programmeAcronym));
    }

    @Override
    public ProgrammeEditionServiceDTO toDTO(ProgrammeID programmeID, SchoolYearID schoolYearID) {
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
    public ProgrammeEditionID toProgrammeEditionID(ProgrammeEditionIdDto programmeEditionIdDto) throws Exception {
        if (programmeEditionIdDto == null) {
            throw new IllegalArgumentException("ProgrammeEditionIdDto cannot be null");
        }
        ProgrammeID programmeID = new ProgrammeID(new Acronym(programmeEditionIdDto.programmeAcronym()));
        SchoolYearID schoolYearID = new SchoolYearID(UUID.fromString(programmeEditionIdDto.schoolYearId()));
        return new ProgrammeEditionID(programmeID, schoolYearID);
    }
}

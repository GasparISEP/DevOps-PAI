package PAI.assembler.programmeEdition;

import PAI.VOs.*;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.programmeEdition.CountStudentsInProgrammeEditionDto;
import PAI.dto.programmeEdition.ProgrammeEditionDTO;
import PAI.dto.programmeEdition.ProgrammeEditionIdDto;
import PAI.dto.schoolYear.SchoolYearIDRequestDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ProgrammeEditionAssemblerImpl implements IProgrammeEditionAssembler {
    public ProgrammeEditionAssemblerImpl() {
    }

    @Override
    public CountStudentsInProgrammeEditionDto toCountStudentsInProgrammeEditionDTO(ProgrammeEdition programmeEdition) {
        if (programmeEdition == null) {
            throw new IllegalArgumentException("ProgrammeEdition cannot be null");
        }
        ProgrammeEditionID id = programmeEdition.identity();
        String programmeName = id.getProgrammeID().getName().toString();
        String programmeAcronym = id.getProgrammeID().getAcronym().getAcronym();
        UUID schoolYearID = id.getSchoolYearID().getSchoolYearID();

        return new CountStudentsInProgrammeEditionDto(programmeName, programmeAcronym, schoolYearID);
    }

    @Override
    public ProgrammeEdition CountStudentsInProgrammeEditionDTOtoDomain(CountStudentsInProgrammeEditionDto dto) throws Exception {
        if (dto == null) {
            throw new IllegalArgumentException("ProgrammeEditionDTO cannot be null");
        }
        Acronym programmeAcronym = new Acronym(dto.programmeAcronym());
        NameWithNumbersAndSpecialChars programmeName = new NameWithNumbersAndSpecialChars(dto.programmeName());
        ProgrammeID programmeID = new ProgrammeID(programmeName, programmeAcronym);
        SchoolYearID schoolYearID = new SchoolYearID(dto.schoolYearID());
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);

        return new ProgrammeEdition(programmeEditionID, programmeID, schoolYearID);
    }

    @Override
    public List<CountStudentsInProgrammeEditionDto> toCountStudentsInProgrammeEditionDTOList(Iterable<ProgrammeEdition> editions) {
        if (editions == null) {
            throw new IllegalArgumentException("programmeEditions cannot be null");
        }
        return StreamSupport.stream(editions.spliterator(), false)
                .map(this::toCountStudentsInProgrammeEditionDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SchoolYearID toSchoolYearID(ProgrammeEditionDTO programmeEditionDTO) {
        if (programmeEditionDTO == null) {
            throw new IllegalArgumentException("ProgrammeEditionDTO cannot be null");
        }
        String schoolYearId = programmeEditionDTO.schoolYear().id();
        return new SchoolYearID(UUID.fromString(schoolYearId));
    }

    @Override
    public ProgrammeID toProgrammeID(ProgrammeEditionDTO programmeEditionDTO) {
        if (programmeEditionDTO == null) {
            throw new IllegalArgumentException("ProgrammeEditionDTO cannot be null");
        }
        String programmeName = programmeEditionDTO.programme().name();
        String programmeAcronym = programmeEditionDTO.programme().acronym();
        return new ProgrammeID(new NameWithNumbersAndSpecialChars(programmeName), new Acronym(programmeAcronym));
    }

    @Override
    public ProgrammeEditionDTO toDTO(ProgrammeID programmeID, SchoolYearID schoolYearID) {
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

    @Override
    public ProgrammeEditionID toProgrammeEditionID(ProgrammeEditionIdDto programmeEditionIdDto) throws Exception {
        if (programmeEditionIdDto == null) {
            throw new IllegalArgumentException("ProgrammeEditionIdDto cannot be null");
        }
        ProgrammeID programmeID = new ProgrammeID(new NameWithNumbersAndSpecialChars(programmeEditionIdDto.programmeName()), new Acronym(programmeEditionIdDto.programmeAcronym()));
        SchoolYearID schoolYearID = new SchoolYearID(UUID.fromString(programmeEditionIdDto.schoolYearId()));
        return new ProgrammeEditionID(programmeID, schoolYearID);
    }
}

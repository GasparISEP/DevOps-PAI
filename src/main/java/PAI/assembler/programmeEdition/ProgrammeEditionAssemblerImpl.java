package PAI.assembler.programmeEdition;

import PAI.VOs.*;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.dto.programmeEdition.CountStudentsInProgrammeEditionDto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ProgrammeEditionAssemblerImpl implements IProgrammeEditionAssembler {
    public ProgrammeEditionAssemblerImpl(){}

    @Override
    public CountStudentsInProgrammeEditionDto toCountStudentsInProgrammeEditionDTO(ProgrammeEdition programmeEdition) {
        if (programmeEdition == null) {
            throw new IllegalArgumentException("ProgrammeEdition cannot be null");
        }
        ProgrammeEditionID id = programmeEdition.identity();
        String programmeName = id.getProgrammeID().getName().toString();
        String programmeAcronym = id.getProgrammeID().getAcronym().getAcronym();
        String schoolYearID = id.getSchoolYearID().toString();

        return new CountStudentsInProgrammeEditionDto(programmeName, programmeAcronym, schoolYearID);
    }

    @Override
    public ProgrammeEdition CountStudentsInProgrammeEditionDTOtoDomain(CountStudentsInProgrammeEditionDto dto) throws Exception {
        if (dto == null) {
            throw new IllegalArgumentException("ProgrammeEditionDTO cannot be null");}
        Acronym programmeAcronym = new Acronym(dto.programmeAcronym());
        NameWithNumbersAndSpecialChars programmeName = new NameWithNumbersAndSpecialChars(dto.programmeName());
        ProgrammeID programmeID = new ProgrammeID(programmeName, programmeAcronym);
        SchoolYearID schoolYearID = new SchoolYearID(UUID.fromString(dto.schoolYearID()));
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
}

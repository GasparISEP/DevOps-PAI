package PAI.assembler.programmeEditionEnrolment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;
import PAI.domain.repositoryInterfaces.schoolYear.ISchoolYearRepository;
import PAI.domain.schoolYear.SchoolYear;
import PAI.dto.programmeEditionEnrolment.ProgrammeEditionEnrolmentDetailDto;

public class ProgrammeEditionEnrolmentAssemblerImpl implements IProgrammeEditionEnrolmentAssembler {

    private final ISchoolYearRepository iSchoolYearRepository;

    public ProgrammeEditionEnrolmentAssemblerImpl(ISchoolYearRepository iSchoolYearRepository) {
        if (iSchoolYearRepository == null) {
            throw new IllegalArgumentException("SchoolYear repository cannot be null.");
        }
        this.iSchoolYearRepository = iSchoolYearRepository;
    }

    
    @Override
    public List<ProgrammeEditionEnrolmentDetailDto> toDtoList(List<ProgrammeEditionEnrolment> programmeEditionEnrolments) throws Exception {
        if (programmeEditionEnrolments == null) {
            throw new Exception("Programme edition enrolment is null");
        }
        List<ProgrammeEditionEnrolmentDetailDto> programmeEditionEnrolmentDetailDtos = new ArrayList<>();

        for(ProgrammeEditionEnrolment programmeEditionEnrolment : programmeEditionEnrolments) {
            String programmeName = programmeEditionEnrolment.identity().getProgrammeEditionId().getProgrammeID().getProgrammeName();
            String programmeAcronym = programmeEditionEnrolment.identity().getProgrammeEditionId().getProgrammeID().getProgrammeAcronym();
            SchoolYearID schoolYearID = programmeEditionEnrolment.identity().getProgrammeEditionId().getSchoolYearID();
            String schoolYearDescription = getSchoolYearDescription(schoolYearID);
            int studentID = programmeEditionEnrolment.identity().getStudentiD().getUniqueNumber();
            ProgrammeEditionEnrolmentDetailDto programmeEditionEnrolmentDetailDto = new ProgrammeEditionEnrolmentDetailDto(
                studentID,
                programmeName,
                programmeAcronym,
                schoolYearDescription,
                schoolYearID.getSchoolYearID().toString()
            );
            programmeEditionEnrolmentDetailDtos.add(programmeEditionEnrolmentDetailDto);
        }
        return programmeEditionEnrolmentDetailDtos;
    }

    private String getSchoolYearDescription(SchoolYearID schoolYearID) {
        Optional<SchoolYear> schoolYear = iSchoolYearRepository.getCurrentSchoolYear();
        if (schoolYear.isPresent()) {
            return schoolYear.get().getDescription().getDescription();
        }
        return null;
    }
}

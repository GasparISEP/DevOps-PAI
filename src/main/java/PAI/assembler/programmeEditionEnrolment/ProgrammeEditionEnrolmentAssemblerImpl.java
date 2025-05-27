package PAI.assembler.programmeEditionEnrolment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.SchoolYearID;
import PAI.VOs.StudentID;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;
import PAI.domain.repositoryInterfaces.schoolYear.ISchoolYearRepository;
import PAI.domain.schoolYear.SchoolYear;
import PAI.dto.programmeEditionEnrolment.ProgrammeEditionEnrolmentDetailDto;

@Component
public class ProgrammeEditionEnrolmentAssemblerImpl implements IProgrammeEditionEnrolmentAssembler {

    private final ISchoolYearRepository iSchoolYearRepository;

    public ProgrammeEditionEnrolmentAssemblerImpl(ISchoolYearRepository iSchoolYearRepository) {
        if (iSchoolYearRepository == null) {
            throw new IllegalArgumentException("SchoolYear repository cannot be null.");
        }
        this.iSchoolYearRepository = iSchoolYearRepository;
    }

    
    @Override
    public List<ProgrammeEditionEnrolmentDetailDto> toDtoList(List<ProgrammeEditionID> programmeEditionEnrolments, StudentID studentID) throws Exception {
        if (programmeEditionEnrolments == null) {
            throw new Exception("Programme edition enrolment is null");
        }
        List<ProgrammeEditionEnrolmentDetailDto> programmeEditionEnrolmentDetailDtos = new ArrayList<>();

        for(ProgrammeEditionID programmeEditionId : programmeEditionEnrolments) {
            String programmeName =  programmeEditionId.getProgrammeID().getProgrammeName();
            String programmeAcronym = programmeEditionId.getProgrammeID().getProgrammeAcronym();
            SchoolYearID schoolYearID = programmeEditionId.getSchoolYearID();
            String schoolYearDescription = getSchoolYearDescription(schoolYearID);
            int studentUniqueNumber = studentID.getUniqueNumber();
            ProgrammeEditionEnrolmentDetailDto programmeEditionEnrolmentDetailDto = new ProgrammeEditionEnrolmentDetailDto(
                studentUniqueNumber,
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
        Optional<SchoolYear> schoolYear = iSchoolYearRepository.findBySchoolYearID(schoolYearID);
        if (schoolYear.isPresent()) {
            return schoolYear.get().getDescription().getDescription();
        }
        return null;
    }
}

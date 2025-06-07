package PAI.assembler.studyPlan;

import PAI.VOs.*;
import PAI.domain.studyPlan.StudyPlan;
import PAI.dto.studyPlan.RegisterStudyPlanCommand;
import PAI.dto.studyPlan.StudyPlanDTO;
import PAI.dto.studyPlan.StudyPlanResponseDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class StudyPlanAssemblerImpl implements IStudyPlanAssembler {

    public RegisterStudyPlanCommand toCommand(String programmeName, String programmeAcronym, LocalDate startDate) {
        Acronym programmeAcronymVO = new Acronym(programmeAcronym);
        ProgrammeID programmeId = new ProgrammeID(programmeAcronymVO);
        Date startDateVO = new Date(startDate);

        return new RegisterStudyPlanCommand(programmeId, startDateVO);
    }

    public StudyPlanDTO toDTO(StudyPlan studyPlan) {
        ProgrammeID programmeId = studyPlan.getProgrammeID();
        Date startDate = studyPlan.getStartDate();
        MaxEcts maxEcts = studyPlan.getMaxEcts();
        DurationInYears durationInYears = studyPlan.getDurationInYears();

        return new StudyPlanDTO(programmeId.getProgrammeAcronym(),
                    startDate.getLocalDate(), maxEcts.getMaxEcts(), durationInYears.getDurationInYears());
    }

    public StudyPlanResponseDTO toResponseDTO(StudyPlanDTO studyPlanDTO) {
        String programmeAcronym = studyPlanDTO.getProgrammeAcronym();
        LocalDate startDate = studyPlanDTO.getStartDate();
        int durationInYears = studyPlanDTO.getDurationInYears();
        int maxEcts = studyPlanDTO.getMaxEcts();

        return new StudyPlanResponseDTO(programmeAcronym, startDate, durationInYears, maxEcts);
    }
}

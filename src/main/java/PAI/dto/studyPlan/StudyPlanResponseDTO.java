package PAI.dto.studyPlan;

import java.time.LocalDate;

public class StudyPlanResponseDTO {
    private final String programmeAcronym;
    private final LocalDate startDate;
    private final int durationInYears;
    private final int maxEcts;

    public StudyPlanResponseDTO(String programmeAcronym, LocalDate startDate, int durationInYears, int maxEcts) {
        this.programmeAcronym = programmeAcronym;
        this.startDate = startDate;
        this.durationInYears = durationInYears;
        this.maxEcts = maxEcts;
    }

    public String getProgrammeAcronym() {
        return programmeAcronym;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public int getDurationInYears() {
        return durationInYears;
    }

    public int getMaxEcts() {
        return maxEcts;
    }
}

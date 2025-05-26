package PAI.dto.studyPlan;

import java.time.LocalDate;

public class StudyPlanDTO {
    private final String programmeName;
    private final String programmeAcronym;
    private final LocalDate startDate;
    private final int durationInYears;
    private final int maxEcts;

    public StudyPlanDTO(String programmeName, String programmeAcronym, LocalDate startDate, int durationInYears, int maxEcts) {
        this.programmeName = programmeName;
        this.programmeAcronym = programmeAcronym;
        this.startDate = startDate;
        this.durationInYears = durationInYears;
        this.maxEcts = maxEcts;
    }

    public String getProgrammeName() {
        return programmeName;
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

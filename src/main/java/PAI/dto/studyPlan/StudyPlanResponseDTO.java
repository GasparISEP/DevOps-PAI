package PAI.dto.studyPlan;

import java.time.LocalDate;
import java.util.UUID;

public class StudyPlanResponseDTO {
    private final String programmeAcronym;
    private final LocalDate startDate;
    private final int durationInYears;
    private final int maxEcts;
    private final UUID uuid;

    public StudyPlanResponseDTO(String programmeAcronym, LocalDate startDate, int durationInYears, int maxEcts, UUID uuid) {
        this.programmeAcronym = programmeAcronym;
        this.startDate = startDate;
        this.durationInYears = durationInYears;
        this.maxEcts = maxEcts;
        this.uuid = uuid;
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

    public UUID getUUID() {
        return uuid;
    }
}

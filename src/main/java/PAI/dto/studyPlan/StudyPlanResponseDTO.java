package PAI.dto.studyPlan;

import java.time.LocalDate;

public class StudyPlanResponseDTO {
    private LocalDate startDate;
    private int durationInYears;
    private int quantityOfEtcs;

    public StudyPlanResponseDTO(){}

    public StudyPlanResponseDTO(LocalDate startDate, int durationInYears, int quantityOfEtcs){
        this.startDate = startDate;
        this.durationInYears = durationInYears;
        this.quantityOfEtcs = quantityOfEtcs;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public int getDurationInYears() {
        return durationInYears;
    }

    public int getQuantityOfEtcs() {
        return quantityOfEtcs;
    }
}

package PAI.dto.schoolYear;

public class SchoolYearDTO {

    private String description;
    private String endDate;
    private String startDate;

    public SchoolYearDTO() {}

    public SchoolYearDTO (String description, String startDate, String endDate) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStartDate() {
        return startDate;
    }
}

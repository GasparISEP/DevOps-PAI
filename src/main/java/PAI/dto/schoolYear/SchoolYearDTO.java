package PAI.dto.schoolYear;

import java.time.LocalDate;

public class SchoolYearDTO {

    private String _description;
    private String _endDate;
    private String _startDate;

    public SchoolYearDTO() {}

    public SchoolYearDTO (String description, String startDate, String endDate) {
        _description = description;
        _startDate = startDate;
        _endDate = endDate;
    }

    public String getDescription() {
        return _description;
    }

    public String getEndDate() {
        return _endDate;
    }

    public String getStartDate() {
        return _startDate;
    }
}

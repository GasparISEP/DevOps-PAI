package PAI.dto.SchoolYear;

import java.time.LocalDate;

public class SchoolYearDTO {

    private String _description;
    private LocalDate _endDate;
    private LocalDate _startDate;

    public SchoolYearDTO (String description, LocalDate startDate, LocalDate endDate) {
        _description = description;
        _startDate = startDate;
        _endDate = endDate;
    }

    public String getDescription() {
        return _description;
    }

    public LocalDate getEndDate() {
        return _endDate;
    }
}

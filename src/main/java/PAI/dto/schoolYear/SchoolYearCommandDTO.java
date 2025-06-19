package PAI.dto.schoolYear;

import PAI.VOs.Date;
import PAI.VOs.Description;

public class SchoolYearCommandDTO {

    private final Description _description;
    private final Date _startDate;
    private final Date _endDate;

    public SchoolYearCommandDTO(Description description, Date startDate, Date endDate) {
        this._description = description;
        this._startDate = startDate;
        this._endDate = endDate;
    }

    public Description getDescription() {
        return _description;
    }

    public Date getStartDate() {
        return _startDate;
    }

}

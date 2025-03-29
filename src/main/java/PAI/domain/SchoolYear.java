package PAI.domain;

import PAI.VOs.Date;
import PAI.VOs.Description;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class SchoolYear {

    private Description _description;
    private Date _startDate;
    private Date _endDate;

    // Constructor
    public SchoolYear(Description description, Date startDate, Date endDate) {

        if (startDate.equals(endDate)) {
            throw new IllegalArgumentException("Start date and end date cannot be the same.");
        }
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date.");
        }

        try {
            _startDate = startDate;
            _endDate = endDate;
            _description = description;
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    // Method to compare if School Years are equal
    public boolean isSameSchoolYear(SchoolYear newSchoolYear) {
        return _startDate.equals(newSchoolYear._startDate) && _endDate.equals(newSchoolYear._endDate);
    }

    public Date getEndDate() {
        Date endDate = _endDate;
        return endDate;
    }

    public Date getStartDate() {
        Date startDate = _startDate;
        return startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SchoolYear that = (SchoolYear) o;
        return Objects.equals(_description, that._description) && Objects.equals(_startDate, that._startDate) &&
                Objects.equals(_endDate, that._endDate);
    }
}
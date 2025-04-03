package PAI.domain;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.VOs.SchoolYearID;
import PAI.ddd.AggregateRoot;

import java.util.Objects;

public class SchoolYear implements AggregateRoot<SchoolYearID> {

    private SchoolYearID _schoolYearID;
    private Description _description;
    private Date _startDate;
    private Date _endDate;

    // Constructor
    public SchoolYear(Description description, Date startDate, Date endDate) {

        _schoolYearID = new SchoolYearID();

        if (startDate.equals(endDate)) {
            throw new IllegalArgumentException("Start date and end date cannot be the same.");
        }
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date.");
        }
            _startDate = startDate;
            _endDate = endDate;
            _description = description;

    }

    // Method to compare if School Years are equal
    public boolean isSameSchoolYear(SchoolYear newSchoolYear) {
        return _startDate.equals(newSchoolYear._startDate) && _endDate.equals(newSchoolYear._endDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SchoolYear schoolYear = (SchoolYear) o;
        return _schoolYearID.equals(schoolYear._schoolYearID);
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
    public SchoolYearID identity() {
        return _schoolYearID;
    }

    @Override
    public boolean sameAs(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        SchoolYear that = (SchoolYear) object;
        return Objects.equals(_startDate, that._startDate) &&
                Objects.equals(_endDate, that._endDate);
    }
}
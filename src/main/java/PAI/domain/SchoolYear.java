package PAI.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class SchoolYear {

    private String _description;
    private LocalDate _startDate;
    private LocalDate _endDate;

    // Constructor
    public SchoolYear(String description, String startDate, String endDate) {

        try {
            validateParameters(description, startDate, endDate);
            _description = description;
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    // Validation of parameters
    private void validateParameters(String description, String startDate, String endDate) {

        if (description == null || description.isBlank())
            throw new IllegalArgumentException("Description cannot be null or blank.");

        if (startDate == null || startDate.isBlank())
            throw new IllegalArgumentException("Start date cannot be null or blank.");

        if (endDate == null || endDate.isBlank())
            throw new IllegalArgumentException("End date cannot be null or blank.");

        // Validates format and logic of startDate and endDate
        validateDateFormat(startDate, endDate);
    }

    // Validation of date format
    private void validateDateFormat(String startDate, String endDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            // Conversion of startDate & endDate to Java Object LocalDate
            _startDate = LocalDate.parse(startDate, formatter);
            _endDate = LocalDate.parse(endDate, formatter);

            // Verifies if startDate is not greater than endDate
            if (!_endDate.isAfter(_startDate)) {
                throw new IllegalArgumentException("End date must be greater than start date.");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Use the following format: dd-MM-yyyy.");
        }
    }

    // Method to compare if School Years are equal
    public boolean isSameSchoolYear(SchoolYear newSchoolYear) {
        return _startDate.equals(newSchoolYear._startDate) && _endDate.equals(newSchoolYear._endDate);
    }

    public LocalDate getEndDate() {
        LocalDate endDate = _endDate;
        return endDate;
    }

    public LocalDate getStartDate() {
        LocalDate startDate = _startDate;
        return startDate;
    }

    public String getDescription() {
        return _description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SchoolYear that = (SchoolYear) o;
        return Objects.equals(_description, that._description) && Objects.equals(_startDate, that._startDate) &&
                Objects.equals(_endDate, that._endDate);
    }
}
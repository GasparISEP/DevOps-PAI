package PAI.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SchoolYear {

    private String _description;
    private String _startDate;
    private String _endDate;

    // Constructor
    public SchoolYear(String description, String startDate, String endDate) throws Exception {

        validateParameters(description, startDate, endDate);

        _description = description;
        _startDate = startDate;
        _endDate = endDate;
    }



    // Validation of parameters
    private void validateParameters(String description, String startDate, String endDate) throws Exception {

        if (description == null || description.isBlank())
            throw new Exception("Description cannot be null or blank.");

        if (startDate == null || startDate.isBlank())
            throw new Exception("Start date cannot be null or blank.");

        if (endDate == null || endDate.isBlank())
            throw new Exception("End date cannot be null or blank.");

        // Validates format and logic of startDate and endDate
        validateDateFormat(startDate, endDate);
    }

    // Validation of date format
    private void validateDateFormat(String startDate, String endDate) throws Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            // Conversion of startDate & endDate to Java Object LocalDate
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);

            // Verifies if startDate is not greater than endDate
            if (!end.isAfter(start)) {
                throw new Exception("End date must be greater than start date.");
            }
        } catch (DateTimeParseException e) {
            throw new Exception("Invalid date format. Use the following format: dd-MM-yyyy.");
        }
    }

    // Method to compare if School Years are equal
    public boolean isSameSchoolYear(SchoolYear newSchoolYear) {
        return _startDate.equals(newSchoolYear._startDate) && _endDate.equals(newSchoolYear._endDate);
    }
}
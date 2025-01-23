package PAI.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TeacherCareerProgression {

    private LocalDate _date;
    private TeacherCategory _category;
    private int _workingPercentage;

    //constructor
    public TeacherCareerProgression (String date, TeacherCategory category, int workingPercentage) throws IllegalArgumentException {

        //validate date
        if (isDateInvalid(date))
            throw new IllegalArgumentException("Date cannot be empty!");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate formattedDate;

        try {
            formattedDate = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Use the following format: dd-MM-yyyy.");
        }

        _date = formattedDate;

        _category = category;

        //validate working percentage
        if (isWorkingPercentageInvalid(workingPercentage))
            throw new IllegalArgumentException("Working Percentage must be a value between 0 and 100.");

        _workingPercentage = workingPercentage;

    }

    private boolean isDateInvalid (String date) {

        return date == null || date.isBlank();
    }

    private boolean isWorkingPercentageInvalid (int workingPercentage) {

        return workingPercentage < 0 || workingPercentage > 100;
    }

}

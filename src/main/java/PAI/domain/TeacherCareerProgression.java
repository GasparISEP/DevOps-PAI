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
        LocalDate formattedDate = validateAndFormatDate(date);

        _date = formattedDate;

        if (isTeacherCategoryInvalid(category))
            throw new IllegalArgumentException("Teacher Category cannot be null");

        _category = category;

        //validate working percentage
        if (isWorkingPercentageInvalid(workingPercentage))
            throw new IllegalArgumentException("Working Percentage must be a value between 0 and 100.");

        _workingPercentage = workingPercentage;
    }

    private boolean isTeacherCategoryInvalid (TeacherCategory category) {

        return category == null;
    }

    private boolean isWorkingPercentageInvalid (int workingPercentage) {

        return workingPercentage < 0 || workingPercentage > 100;
    }

    private LocalDate validateAndFormatDate(String date) throws IllegalArgumentException {

        if (date == null || date.isBlank())
            throw new IllegalArgumentException("Date cannot be empty!");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date. Please check whether the day, month, year or date format (dd-MM-yyyy) are correct.");
        }
    }

    public TeacherCategory getCategory () {

        return _category;
    }

    public int getWorkingPercentage () {

        return _workingPercentage;
    }

    public LocalDate getDate () {

        return _date;
    }

    public boolean isDateAfter(TeacherCareerProgression tcp) {

        if(_date.isAfter(tcp._date))
            return true;

        return false;
    }
}

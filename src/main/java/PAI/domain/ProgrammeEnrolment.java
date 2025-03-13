package PAI.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ProgrammeEnrolment {

    private Student _student;
    private AccessMethod _accessMethod;
    private Programme _programme;
    private LocalDate _date;

    public ProgrammeEnrolment(Student student, AccessMethod accessMethod, Programme programme, String date) throws IllegalArgumentException {

        if (student == null || accessMethod == null || programme == null){
            throw new IllegalArgumentException ("Argument can't be null");
        }

        _student = student;
        _accessMethod = accessMethod;
        _programme = programme;
        _date = validateAndFormatDate(date);
    }

    private LocalDate validateAndFormatDate(String date) throws IllegalArgumentException {

        if (date == null || date.isBlank())
            throw new IllegalArgumentException("Date cannot be empty!");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate formattedDate;
        try {
            formattedDate = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date. Please check whether the day, month, year or date format (dd-MM-yyyy) are correct.");
        }
        return formattedDate;
    }

    public boolean hasSameStudent(Student student){

        return _student.hasSameUniqueNumber(student);

    }

    public boolean hasSameEnrolment(ProgrammeEnrolment programmeEnrolment){
        return this._student.hasSameUniqueNumber(programmeEnrolment._student) &&
                this._programme.isEquals(programmeEnrolment._programme);
    }

    public boolean hasSameProgramme(Programme programme) {
        return _programme.isEquals(programme);
    }
}



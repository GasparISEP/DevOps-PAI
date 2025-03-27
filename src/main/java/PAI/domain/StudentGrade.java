package PAI.domain;

import PAI.VOs.Grade;
import PAI.VOs.StudentGradeID;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class StudentGrade {

    private Grade _grade;
    private LocalDate _date;
    private Student _student;
    private CourseEdition _courseEdition;
    private final StudentGradeID _studentGrade_id;

    public StudentGrade(Grade grade, String date, Student student, CourseEdition courseEdition) throws Exception {
        if (grade == null) throw new IllegalArgumentException("Grade cannot be null");
        _grade = grade;

        if (date == null || date.isEmpty()) throw new IllegalArgumentException("Date cannot be empty");
        isDateValid(date);

        if (student == null) throw new IllegalArgumentException("Student cannot be null");
        _student = student;

        if (courseEdition == null) throw new IllegalArgumentException("Course Edition cannot be null");
        _courseEdition = courseEdition;

        this._studentGrade_id = new StudentGradeID();
    }

    public Grade get_grade() {
        return _grade;
    }

    public StudentGradeID get_studentGrade_id() {
        return _studentGrade_id;
    }

    public boolean hasThisCourseEdition(CourseEdition courseEdition) {
        return _courseEdition.equals(courseEdition);
    }

    public boolean hasThisStudent(Student student) {
        return _student.equals(student);
    }

    public CourseEdition KnowCourseEdition() {
        return _courseEdition;
    }

    public void isDateValid(String date) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            _date = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new Exception("Time format must be dd-MM-YYYY");
        }
    }

    public LocalDate get_date() {
        return _date;
    }

    public Student get_student() {
        return _student;
    }

    public CourseEdition get_courseEdition() {
        return _courseEdition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentGrade that = (StudentGrade) o;

        if (this._studentGrade_id.equals(that._studentGrade_id)) {
            return true;
        }
        return false;
    }

    public boolean sameAs(Object object) {

        if (object instanceof StudentGrade) {
            StudentGrade studentGrade = (StudentGrade) object;

            if( this._student.equals(studentGrade._student) && (this._courseEdition.equals(studentGrade._courseEdition)) )
                return true;
        }
        return false;
    }

}





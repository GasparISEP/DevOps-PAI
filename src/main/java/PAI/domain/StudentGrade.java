package PAI.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class StudentGrade {

        private double _grade;
        private LocalDate _date;
        private Student _student;
        private CourseEdition _courseEdition;

        public StudentGrade(double grade, String date, Student student, CourseEdition courseEdition) throws Exception {
            if (!isGradeValid(grade)) throw new IllegalArgumentException("Grade should be between 0 and 20");
            _grade = grade;

            if ( date == null || date.isEmpty()) throw new IllegalArgumentException("Date cannot be empty");
            isDateValid(date);

            if (student == null) throw new IllegalArgumentException("Student cannot be null");
            _student = student;

            if (courseEdition == null) throw new IllegalArgumentException("Course Edition cannot be null");
            _courseEdition = courseEdition;
        }

        private boolean isGradeValid (double grade){
            return grade >= 0 && grade <=20;
        }



        public double knowGrade() {
            return _grade;
        }

        public boolean hasThisCourseEdition(CourseEdition courseEdition) {
            return _courseEdition.equals(courseEdition);
        }

        public boolean hasThisStudent (Student student) {
            return _student.equals(student);
        }

        public CourseEdition KnowCourseEdition() {
            return _courseEdition;
        }

        public void isDateValid (String date) throws Exception{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            try {
                _date = LocalDate.parse(date,formatter);
            }
            catch (DateTimeParseException e){
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
        return Objects.equals(_student, that._student) && Objects.equals(_courseEdition, that._courseEdition);
    }
}




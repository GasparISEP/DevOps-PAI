package PAI.domain;

import PAI.VOs.Date;
import PAI.VOs.Grade;
import PAI.VOs.StudentGradeID;
import PAI.VOs.StudentID;
import PAI.ddd.AggregateRoot;

public class StudentGrade implements AggregateRoot<StudentGradeID> {

    private Grade _grade;
    private Date _date;
    private StudentID _student;
    private CourseEdition _courseEdition;
    private final StudentGradeID _studentGrade_id;

    public StudentGrade(Grade grade, Date date, StudentID student, CourseEdition courseEdition) throws Exception {
        if (grade == null) throw new IllegalArgumentException("Grade cannot be null");
        _grade = grade;

        if (date == null) throw new IllegalArgumentException("Date cannot be null");
        _date = date;

        if (student == null) throw new IllegalArgumentException("Student cannot be null");
        _student = student;

        if (courseEdition == null) throw new IllegalArgumentException("Course Edition cannot be null");
        _courseEdition = courseEdition;

        this._studentGrade_id = new StudentGradeID();
    }

    public Grade get_grade() {
        return _grade;
    }

    public boolean hasThisCourseEdition(CourseEdition courseEdition) {
        return _courseEdition.equals(courseEdition);
    }

    public boolean hasThisStudent(StudentID student) {
        return _student.equals(student);
    }

    public CourseEdition KnowCourseEdition() {
        return _courseEdition;
    }

    @Override
    public StudentGradeID identity(){
        return _studentGrade_id;
    }

    public Date get_date() {
        return _date;
    }

    public StudentID get_student() {
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

    public double knowGrade () {
        return _grade.knowGrade();
    }
}





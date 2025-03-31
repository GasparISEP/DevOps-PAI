package PAI.domain;

import PAI.VOs.*;
import PAI.ddd.AggregateRoot;

public class StudentGrade implements AggregateRoot<StudentGradeID> {

    private Grade _grade;
    private Date _date;
    private StudentID _studentID;
    private CourseEditionID _courseEditionID;
    private final StudentGradeID _studentGrade_id;

    public StudentGrade(Grade grade, Date date, StudentID studentID, CourseEditionID courseEditionID) throws Exception {
        if (grade == null) throw new IllegalArgumentException("Grade cannot be null");
        _grade = grade;

        if (date == null) throw new IllegalArgumentException("Date cannot be null");
        _date = date;

        if (studentID == null) throw new IllegalArgumentException("Student cannot be null");
        _studentID = studentID;

        if (courseEditionID == null) throw new IllegalArgumentException("Course Edition cannot be null");
        _courseEditionID = courseEditionID;

        this._studentGrade_id = new StudentGradeID(studentID,courseEditionID);
    }

    public Grade get_grade() {
        return _grade;
    }

    public boolean hasThisCourseEditionID(CourseEditionID courseEditionID) {
        return _courseEditionID.equals(courseEditionID);
    }

    public boolean hasThisStudentID(StudentID student) {
        return _studentID.equals(student);
    }

    public CourseEditionID KnowCourseEditionID() {
        return _courseEditionID;
    }

    @Override
    public StudentGradeID identity(){
        return _studentGrade_id;
    }

    public Date get_date() {
        return _date;
    }

    public StudentID get_studentID() {
        return _studentID;
    }

    public CourseEditionID get_courseEditionID() {
        return _courseEditionID;
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

            if( this._studentID.equals(studentGrade._studentID) && (this._courseEditionID.equals(studentGrade._courseEditionID)) )
                return true;
        }
        return false;
    }

    public double knowGrade () {
        return _grade.knowGrade();
    }
}





package PAI.domain.studentGrade;

import PAI.VOs.*;
import PAI.ddd.AggregateRoot;

public class StudentGrade implements AggregateRoot<StudentGradeID> {

    private Grade grade;
    private Date date;
    private StudentID studentID;
    private CourseEditionID courseEditionID;
    private final StudentGradeID studentGradeID;
    private final StudentGradeGeneratedID studentGradeGeneratedID;

    public StudentGrade(Grade grade, Date date, StudentID studentID, CourseEditionID courseEditionID, StudentGradeID studentGradeID, StudentGradeGeneratedID studentGradeGeneratedID) throws Exception {
        if (grade == null) throw new IllegalArgumentException("Grade cannot be null");
        this.grade = grade;

        if (date == null) throw new IllegalArgumentException("Date cannot be null");
        this.date = date;

        if (studentID == null) throw new IllegalArgumentException("Student cannot be null");
        this.studentID = studentID;

        if (courseEditionID == null) throw new IllegalArgumentException("Course Edition cannot be null");
        this.courseEditionID = courseEditionID;

        if (studentGradeID == null) throw new IllegalArgumentException("StudentGradeID cannot be null");
        this.studentGradeID = studentGradeID;

        if (studentGradeGeneratedID == null) throw new IllegalArgumentException("Student Grade Generated Id cannot be null");
        this.studentGradeGeneratedID = studentGradeGeneratedID;
    }

    @Override
    public StudentGradeID identity(){
        return studentGradeID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentGrade that = (StudentGrade) o;

        if (this.studentGradeID.equals(that.studentGradeID)) {
            return true;
        }
        return false;
    }

    public boolean sameAs(Object object) {

        if (object instanceof StudentGrade) {
            StudentGrade studentGrade = (StudentGrade) object;

            if( this.studentID.equals(studentGrade.studentID) && (this.courseEditionID.equals(studentGrade.courseEditionID)) )
                return true;
        }
        return false;
    }

    public Grade getGrade() {
        return grade;
    }

    public Date getDate() {
        return date;
    }

    public StudentID getStudentID() {
        return studentID;
    }

    public CourseEditionID getCourseEditionID() {
        return courseEditionID;
    }

    public StudentGradeGeneratedID getStudentGradeGeneratedID() {
        return studentGradeGeneratedID;
    }

    public boolean hasThisCourseEditionID(CourseEditionID courseEditionID) {
        return this.courseEditionID.equals(courseEditionID);
    }

    public boolean hasThisStudentID(StudentID student) {
        return studentID.equals(student);
    }

    public double knowGrade () {
        return grade.knowGrade();
    }
}
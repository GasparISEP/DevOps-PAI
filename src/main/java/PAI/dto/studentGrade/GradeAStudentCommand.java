package PAI.dto.studentGrade;

import PAI.VOs.CourseEditionID;
import PAI.VOs.Date;
import PAI.VOs.Grade;
import PAI.VOs.StudentID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class GradeAStudentCommand {

    @Positive(message = "Grade value must be positive")
    private Grade grade;

    @NotBlank(message = "Date cannot be blank")
    private Date date;

    @NotBlank(message = "Student ID cannot be blank")
    private StudentID studentID;

    @NotBlank(message = "Course Edition ID cannot be blank")
    private CourseEditionID courseEditionID;

    public GradeAStudentCommand(Grade grade, Date date, StudentID studentID, CourseEditionID courseEditionID) {
        this.grade = grade;
        this.date = date;
        this.studentID = studentID;
        this.courseEditionID = courseEditionID;
    }

    public Grade getGrade() { return grade; }

    public Date getDate() { return date; }

    public StudentID getStudentID() { return studentID; }

    public CourseEditionID getCourseEditionID() { return courseEditionID; }
}

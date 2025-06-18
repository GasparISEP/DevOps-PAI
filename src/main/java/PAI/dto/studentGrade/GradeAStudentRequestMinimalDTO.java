package PAI.dto.studentGrade;

public class GradeAStudentRequestMinimalDTO {
    private int studentUniqueNumber;
    private double grade;
    private String courseEditionGeneratedID;

    public GradeAStudentRequestMinimalDTO() {
    }

    public GradeAStudentRequestMinimalDTO(int studentUniqueNumber, double grade, String courseEditionGeneratedID) {
        this.studentUniqueNumber = studentUniqueNumber;
        this.grade = grade;
        this.courseEditionGeneratedID = courseEditionGeneratedID;
    }

    public int getStudentUniqueNumber() {
        return studentUniqueNumber;
    }

    public void setStudentUniqueNumber(int studentUniqueNumber) {
        this.studentUniqueNumber = studentUniqueNumber;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getCourseEditionGeneratedID() {
        return courseEditionGeneratedID;
    }

    public void setCourseEditionGeneratedID(String courseEditionGeneratedID) {
        this.courseEditionGeneratedID = courseEditionGeneratedID;
    }
}

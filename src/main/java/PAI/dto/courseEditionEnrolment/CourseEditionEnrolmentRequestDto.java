package PAI.dto.courseEditionEnrolment;

class CourseEditionEnrolmentRequestDto {

    private String courseEditionId;
    private String studentId;

    public CourseEditionEnrolmentRequestDto(String courseEditionId, String studentId) {
        if(courseEditionId == null) {
            throw new IllegalArgumentException("Course edition ID cannot be null");
        }
        if(studentId == null) {
            throw new IllegalArgumentException("Student ID cannot be null");
        }
        this.courseEditionId = courseEditionId;
        this.studentId = studentId;
    }

    public String getCourseEditionId() {
        return courseEditionId;
    }

    public String getStudentId() {
        return studentId;
    }   
}
package PAI.dto.course;

public class CourseRequestDTO {

    private String _courseId;

    public CourseRequestDTO() {}

    public CourseRequestDTO (String courseId) {

        _courseId = courseId;
    }

    public String getCourseId () {
        return _courseId;
    }
}

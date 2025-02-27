package PAI.domain;

public class CourseEditionFactory {

    public CourseEdition newCourseEdition (Course course, ProgrammeEdition programmeEdition) throws Exception {
        return new CourseEdition(course, programmeEdition);
    }
}

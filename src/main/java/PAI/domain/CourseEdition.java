package PAI.domain;

public class CourseEdition {

    private Course _course;
    private ProgrammeEdition _programmeEdition;
    private Teacher _ruc;


    public CourseEdition (Course course, ProgrammeEdition programmeEdition) throws Exception {
        if (programmeEdition == null || course==null) {
            throw new Exception ("Course and ProgrammeEdition are required");
        }
        _course = course;
        _programmeEdition = programmeEdition;
    }


    @Override
    public boolean equals(Object objectToCompare) {

        if (!(objectToCompare instanceof CourseEdition)) {
            return false;
        }

        CourseEdition courseEditionTest = (CourseEdition) objectToCompare;

        return _course.equals(courseEditionTest._course) &&
                _programmeEdition.equals(courseEditionTest._programmeEdition);
    }
}

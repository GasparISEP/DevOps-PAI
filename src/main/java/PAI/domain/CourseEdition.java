package PAI.domain;

import PAI.VOs.ProgrammeEditionID;

public class CourseEdition {

    private Course _course;
    private ProgrammeEdition _programmeEdition;
    private Teacher _ruc;
    private ProgrammeEditionID _programmeEditionId;

    public CourseEdition (Course course, ProgrammeEdition programmeEdition) throws Exception {
        if (programmeEdition == null || course==null) {
            throw new Exception ("ProgrammeEdition and Course must be valid");
        }
        if (!programmeEdition.isCourseInProgrammeCourseListByProgrammeEdition(programmeEdition, course)) {
            throw new Exception ("Course must be in Programme's Course List");
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

    // US20 - sets the RUC for this course edition
    public boolean setRuc(Teacher ruc) {
        if (_ruc != null)
            return false;
        this._ruc = ruc;
        return true;
    }

    public ProgrammeEdition whatProgrammeEditionBelongsThisCourseEdition () {
        return _programmeEdition;
    }

    public Course getCourse() {
        return _course;
    }
}


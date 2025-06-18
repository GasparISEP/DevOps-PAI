package PAI.domain.courseEdition;
import PAI.VOs.*;
import PAI.ddd.AggregateRoot;

public class CourseEdition implements AggregateRoot<CourseEditionID> {

    private final CourseEditionID _courseEditionID;
    private final CourseInStudyPlanID _courseInStudyPlanID;
    private final ProgrammeEditionID _programmeEditionID;
    private final CourseEditionGeneratedID _courseEditionGeneratedID;
    private TeacherID _ruc;

    public CourseEdition(CourseEditionID courseEditionID, CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID, CourseEditionGeneratedID courseEditionGeneratedID) {
        if (courseEditionID == null) {
            throw new IllegalArgumentException("CourseEditionID must be valid");
        }
        if (courseInStudyPlanID == null) {
            throw new IllegalArgumentException("CourseInStudyPlanID must be valid");
        }
        if (programmeEditionID == null) {
            throw new IllegalArgumentException("ProgrammeEdition must be valid");
        }
        if (courseEditionGeneratedID == null) {
            throw new IllegalArgumentException("CourseEditionGeneratedID must be valid");
        }
        this._courseEditionID = courseEditionID;
        this._courseInStudyPlanID = courseInStudyPlanID;
        this._programmeEditionID = programmeEditionID;
        this._courseEditionGeneratedID = courseEditionGeneratedID;
    }

    public CourseEdition(CourseEditionID courseEditionID, CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID, CourseEditionGeneratedID courseEditionGeneratedID, TeacherID teacherID) {
        if (courseEditionID == null) {
            throw new IllegalArgumentException("CourseEditionID must be valid");
        }
        if (courseInStudyPlanID == null) {
            throw new IllegalArgumentException("CourseInStudyPlanID must be valid");
        }
        if (programmeEditionID == null) {
            throw new IllegalArgumentException("ProgrammeEdition must be valid");
        }
        if (courseEditionGeneratedID == null) {
            throw new IllegalArgumentException("CourseEditionGeneratedID must be valid");
        }

        this._courseEditionID = courseEditionID;
        this._courseInStudyPlanID = courseInStudyPlanID;
        this._programmeEditionID = programmeEditionID;
        this._courseEditionGeneratedID = courseEditionGeneratedID;
        this._ruc = teacherID;
    }

    @Override
    public CourseEditionID identity() {
        return _courseEditionID;
    }

    @Override
    public boolean sameAs(Object objectToCompare) {
        if (!(objectToCompare instanceof CourseEdition)) {
            return false;
        }
        CourseEdition courseEditionTest = (CourseEdition) objectToCompare;

        return _courseInStudyPlanID.equals(courseEditionTest._courseInStudyPlanID) &&
                _programmeEditionID.equals(courseEditionTest._programmeEditionID);
    }

    public ProgrammeEditionID getProgrammeEditionID() {
        return _programmeEditionID;
    }

    public CourseEditionGeneratedID getCourseEditionGeneratedID() {
        return _courseEditionGeneratedID;
    }

    public CourseInStudyPlanID getCourseInStudyPlanID() {
        return _courseInStudyPlanID;
    }

    @Override
    public boolean equals(Object objectToCompare) {

        if (this == objectToCompare)
            return true;

        if (objectToCompare instanceof CourseEdition) {

            CourseEdition courseEdition = (CourseEdition) objectToCompare;

            if (_courseEditionID.equals(courseEdition._courseEditionID))
                return true;
        }
        return false;
    }

    // US20 - Define o RUC para esta edição do curso, referenciando um TeacherID.
    public boolean setRuc(TeacherID teacherID) {
        if (teacherID == null) {
            return false;
        }
        this._ruc = teacherID;
        return true;
    }

    public TeacherID getRuc() {
        return _ruc;
    }

    public CourseEditionID getCourseEditionID() {
        return _courseEditionID;
    }
}


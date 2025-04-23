package PAI.domain;
import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.TeacherID;
import PAI.ddd.AggregateRoot;

public class CourseEdition implements AggregateRoot<CourseEditionID> {

    private final CourseEditionID _courseEditionID;
    private final CourseInStudyPlanID _courseInStudyPlanID;
    private final ProgrammeEditionID _programmeEditionID;
    private TeacherID _ruc;


    public CourseEdition(CourseEditionID courseEditionID, CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) {
        if (courseEditionID == null) {
            throw new IllegalArgumentException("CourseInStudyPlanID must be valid");
        }
        if (courseInStudyPlanID == null) {
            throw new IllegalArgumentException("CourseInStudyPlanID must be valid");
        }
        if (programmeEditionID == null) {
            throw new IllegalArgumentException("ProgrammeEdition must be valid");
        }
        this._courseEditionID = courseEditionID;
        this._courseInStudyPlanID = courseInStudyPlanID;
        this._programmeEditionID = programmeEditionID;
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
}


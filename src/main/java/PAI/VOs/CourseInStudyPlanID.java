package PAI.VOs;

import PAI.ddd.DomainId;

import java.util.Objects;

public class CourseInStudyPlanID implements DomainId {

    private final CourseID _courseID;
    private final StudyPlanID _studyPlanID;

    public CourseInStudyPlanID(CourseID courseID, StudyPlanID studyPlanID) {
        this._courseID = courseID;
        this._studyPlanID = studyPlanID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseInStudyPlanID that)) return false;
        return Objects.equals(_courseID, that._courseID)
                && _studyPlanID.equals(that._studyPlanID);
    }

    @Override
    public String toString() {
        return "CourseInStudyPlanID{" +
                "_courseID=" + _courseID +
                ", _studyPlanID=" + _studyPlanID +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(_courseID, _studyPlanID);
    }

    public CourseID getCourseID() {
        return _courseID;
    }

    public StudyPlanID getStudyPlanID() {
        return _studyPlanID;
    }
}
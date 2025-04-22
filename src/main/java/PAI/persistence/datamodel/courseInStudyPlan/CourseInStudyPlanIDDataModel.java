package PAI.persistence.datamodel.courseInStudyPlan;

import PAI.persistence.datamodel.course.CourseIDDataModel;
import PAI.persistence.datamodel.studyPlan.StudyPlanIDDataModel;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.io.Serializable;

@Embeddable
public class CourseInStudyPlanIDDataModel implements Serializable {

    @Embedded
    private StudyPlanIDDataModel _studyPlanIDDataModel;
    @Embedded
    private CourseIDDataModel _courseID;

    public CourseInStudyPlanIDDataModel() {}

    public CourseInStudyPlanIDDataModel(StudyPlanIDDataModel studyPlanIDDataModel, CourseIDDataModel courseID) {
        this._studyPlanIDDataModel = studyPlanIDDataModel;
        this._courseID = courseID;
    }

    public CourseIDDataModel getCourseID() {
        return _courseID;
    }

    public StudyPlanIDDataModel getStudyPlanIDDataModel() {
        return _studyPlanIDDataModel;
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (this == objectToCompare) return true;
        if (!(objectToCompare instanceof CourseInStudyPlanIDDataModel)) return false;
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = (CourseInStudyPlanIDDataModel) objectToCompare;
        return _studyPlanIDDataModel.equals(courseInStudyPlanIDDataModel._studyPlanIDDataModel) &&
                _courseID.equals(courseInStudyPlanIDDataModel._courseID);
    }

    @Override
    public int hashCode() {
        return _studyPlanIDDataModel.hashCode() + _courseID.hashCode();
    }
}
package PAI.persistence.datamodel.courseInStudyPlan;

import PAI.persistence.datamodel.course.CourseIDDataModel;
import PAI.persistence.datamodel.studyPlan.StudyPlanIDDataModel;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table (name = "Course_In_Study_Plan")
public class CourseInStudyPlanDataModel {

    @EmbeddedId
    private CourseInStudyPlanIDDataModel _courseInStudyPlanID;

    @Embedded
    @AttributeOverride(
            name = "id",
            column = @Column(name = "study_plan_id", nullable = false)
    )
    private StudyPlanIDDataModel _studyPlanIDDataModel;

    @Embedded
    @AttributeOverride(
            name = "id",
            column = @Column(name = "course_id", nullable = false)
    )
    private CourseIDDataModel _courseID;

    @Column(name = "semester", nullable = false)
    private int _semester;

    @Column(name = "curricular_year", nullable = false)
    private int _curricularYear;

    protected CourseInStudyPlanDataModel() {
    }

    public CourseInStudyPlanDataModel (CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel, StudyPlanIDDataModel studyPlanIDDataModel, CourseIDDataModel courseIDDataModel, int semester, int curricularYear) {
        this._courseInStudyPlanID = courseInStudyPlanIDDataModel;
        this._studyPlanIDDataModel = studyPlanIDDataModel;
        this._courseID = courseIDDataModel;
        this._semester = semester;
        this._curricularYear = curricularYear;
    }

    @Override
    public boolean equals (Object other) {
        if (this == other) return true;
        if (!(other instanceof CourseInStudyPlanDataModel)) return false;
        CourseInStudyPlanDataModel otherCourseInStudyPlanDataModel = (CourseInStudyPlanDataModel) other;
        return _courseInStudyPlanID == otherCourseInStudyPlanDataModel._courseInStudyPlanID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_courseInStudyPlanID);
    }

    public CourseInStudyPlanIDDataModel getCourseInStudyPlanIDDataModel() {
        return _courseInStudyPlanID;
    }

    public StudyPlanIDDataModel getStudyPlanIDDataModel() {
        return _studyPlanIDDataModel;
    }

    public CourseIDDataModel getCourseIDDataModel() {
        return _courseID;
    }

    public int getSemester() {
        return _semester;
    }

    public int getCurricularYear() {
        return _curricularYear;
    }
}
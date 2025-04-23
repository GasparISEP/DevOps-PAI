package PAI.persistence.datamodel.courseInStudyPlan;

import PAI.persistence.datamodel.course.CourseIDDataModel;
import PAI.persistence.datamodel.studyPlan.StudyPlanIDDataModel;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table (name = "CourseInStudyPlan")
public class CourseInStudyPlanDataModel {

    @EmbeddedId
    private CourseInStudyPlanIDDataModel _courseInStudyPlanID;

    @Embedded
    @AttributeOverride(
            name = "ID",
            column = @Column(name = "studyPlanID", nullable = false)
    )
    private StudyPlanIDDataModel _studyPlanIDDataModel;

    @Embedded
    @AttributeOverride(
            name = "ID",
            column = @Column(name = "courseID", nullable = false)
    )
    private CourseIDDataModel _courseID;

    @Column(name = "semester", nullable = false)
    private int _semester;

    @Column(name = "curricularYear", nullable = false)
    private int _curricularYear;

    @Column (name = "courseDuration" , nullable = false)
    private int _durationOfCourse;

    @Column (name = "ECTSQuantity", nullable = false)
    private double _quantityOfCreditsEcts;

    protected CourseInStudyPlanDataModel() {
    }

    public CourseInStudyPlanDataModel (CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel, StudyPlanIDDataModel studyPlanIDDataModel,
                                       CourseIDDataModel courseIDDataModel, int semester, int curricularYear, int durationOfCourse, double quantityOfCreditsEcts) {

        if (courseInStudyPlanIDDataModel == null || studyPlanIDDataModel == null || courseIDDataModel == null) {
            throw new IllegalArgumentException("CourseInStudyPlanIDDataModel, StudyPlanIDDataModel and CourseIDDataModel cannot be null");
        }

        this._courseInStudyPlanID = courseInStudyPlanIDDataModel;
        this._studyPlanIDDataModel = studyPlanIDDataModel;
        this._courseID = courseIDDataModel;

        if (semester < 1 || curricularYear < 1 || durationOfCourse < 1 || quantityOfCreditsEcts < 1) {
            throw new IllegalArgumentException("Semester, CurricularYear, DurationOfCourse or QuantityOfCreditsEcts must be greater than 0");
        }

        this._semester = semester;
        this._curricularYear = curricularYear;
        this._durationOfCourse = durationOfCourse;
        this._quantityOfCreditsEcts = quantityOfCreditsEcts;
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

    public int getDurationOfCourse() {
        return _durationOfCourse;
    }

    public double getQuantityOfCreditsEcts() {
        return _quantityOfCreditsEcts;
    }
}
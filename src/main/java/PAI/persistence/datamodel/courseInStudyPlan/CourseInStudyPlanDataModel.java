package PAI.persistence.datamodel.courseInStudyPlan;

import PAI.persistence.datamodel.course.CourseIDDataModel;
import PAI.persistence.datamodel.studyPlan.StudyPlanIDDataModel;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table (name = "CourseInStudyPlan")
public class CourseInStudyPlanDataModel {

    @EmbeddedId
    private CourseInStudyPlanIDDataModel courseInStudyPlanID;

    @Column(name = "semester", nullable = false)
    private int semester;

    @Column(name = "curricularYear", nullable = false)
    private int curricularYear;

    @Column (name = "courseDuration" , nullable = false)
    private int durationOfCourse;

    @Column (name = "ECTSQuantity", nullable = false)
    private double quantityOfCreditsEcts;

    protected CourseInStudyPlanDataModel() {
    }

    public CourseInStudyPlanDataModel (CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel, int semester, int curricularYear, int durationOfCourse, double quantityOfCreditsEcts) {
        this.courseInStudyPlanID = courseInStudyPlanIDDataModel;

        if (semester < 1 || curricularYear < 1 || durationOfCourse < 1 || quantityOfCreditsEcts < 1) {
            throw new IllegalArgumentException("Semester, CurricularYear, DurationOfCourse or QuantityOfCreditsEcts must be greater than 0");
        }

        this.semester = semester;
        this.curricularYear = curricularYear;
        this.durationOfCourse = durationOfCourse;
        this.quantityOfCreditsEcts = quantityOfCreditsEcts;
    }

    @Override
    public boolean equals (Object other) {
        if (this == other) return true;
        if (!(other instanceof CourseInStudyPlanDataModel)) return false;
        CourseInStudyPlanDataModel otherCourseInStudyPlanDataModel = (CourseInStudyPlanDataModel) other;
        return courseInStudyPlanID == otherCourseInStudyPlanDataModel.courseInStudyPlanID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseInStudyPlanID);
    }

    public CourseInStudyPlanIDDataModel getCourseInStudyPlanIDDataModel() {
        return courseInStudyPlanID;
    }

    public StudyPlanIDDataModel getStudyPlanIDDataModel() {
        return courseInStudyPlanID.getStudyPlanIDDataModel();
    }

    public CourseIDDataModel getCourseIDDataModel() {
        return courseInStudyPlanID.getCourseID();
    }

    public int getSemester() {
        return semester;
    }

    public int getCurricularYear() {
        return curricularYear;
    }

    public int getDurationOfCourse() {
        return durationOfCourse;
    }

    public double getQuantityOfCreditsEcts() {
        return quantityOfCreditsEcts;
    }
}
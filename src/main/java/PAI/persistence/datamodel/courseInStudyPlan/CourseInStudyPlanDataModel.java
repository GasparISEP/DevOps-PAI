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

    @Embedded
    @AttributeOverride(
            name = "ID",
            column = @Column(name = "studyPlanID", nullable = false)
    )
    private StudyPlanIDDataModel CISPstudyPlanIDDataModel;

    @Embedded
    @AttributeOverride(
            name = "ID",
            column = @Column(name = "courseID", nullable = false)
    )
    private CourseIDDataModel CISPcourseID;

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

    public CourseInStudyPlanDataModel (CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel, StudyPlanIDDataModel studyPlanIDDataModel,
                                       CourseIDDataModel courseIDDataModel, int semester, int curricularYear, int durationOfCourse, double quantityOfCreditsEcts) {

        if (courseInStudyPlanIDDataModel == null || studyPlanIDDataModel == null || courseIDDataModel == null) {
            throw new IllegalArgumentException("CourseInStudyPlanIDDataModel, StudyPlanIDDataModel and CourseIDDataModel cannot be null");
        }

        this.courseInStudyPlanID = courseInStudyPlanIDDataModel;
        this.CISPstudyPlanIDDataModel = studyPlanIDDataModel;
        this.CISPcourseID = courseIDDataModel;

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
        return CISPstudyPlanIDDataModel;
    }

    public CourseIDDataModel getCourseIDDataModel() {
        return CISPcourseID;
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
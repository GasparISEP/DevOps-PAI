package PAI.domain.courseInStudyPlan;

import PAI.VOs.*;
import PAI.ddd.AggregateRoot;

import static PAI.utils.ValidationUtils.validateNotNull;

public class CourseInStudyPlan implements AggregateRoot<CourseInStudyPlanID> {

    private CourseID _courseID;
    private Semester _semester;
    private CurricularYear _curricularYear;
    private StudyPlanID _studyPlanID;
    private DurationCourseInCurricularYear _durationOfCourse;
    private CourseQuantityCreditsEcts _quantityOfCreditsEcts;
    private CourseInStudyPlanID _courseInStudyPlanID;
    private CourseInStudyPlanGeneratedID _generatedID;

    public CourseInStudyPlan(Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyplanID, CourseInStudyPlanID courseInStudyPlanID,
                             DurationCourseInCurricularYear durationOfCourse, CourseQuantityCreditsEcts quantityOfCreditsEcts, CourseInStudyPlanGeneratedID generatedID) {

        this._courseID = validateNotNull(courseID, "Course ID");
        this._semester = validateNotNull(semester, "Semester");
        this._curricularYear = validateNotNull(curricularYear, "Curricular Year");
        this._studyPlanID = validateNotNull(studyplanID, "Study Plan ID");
        this._durationOfCourse = validateNotNull(durationOfCourse, "Duration of Course");
        this._quantityOfCreditsEcts = validateNotNull(quantityOfCreditsEcts, "Quantity of Credits Ects");
        this._courseInStudyPlanID = validateNotNull(courseInStudyPlanID, "Course In Study Plan ID");
        this._generatedID = validateNotNull(generatedID, "Course In Study Plan Generated ID");
    }

    @Override
    public boolean equals(Object ObjectToCompare) {
        if (this == ObjectToCompare) {
            return true;
        }
        if (!(ObjectToCompare instanceof CourseInStudyPlan)) {
            return false;
        }
        CourseInStudyPlan courseToBeCompared = (CourseInStudyPlan) ObjectToCompare;

        return this._courseInStudyPlanID.equals(courseToBeCompared._courseInStudyPlanID);
    }

    public CourseID getCourseID() {
        return this._courseID;
    }

    public Semester getSemester() {
        return this._semester;
    }

    public CurricularYear getCurricularYear() {
        return this._curricularYear;
    }

    public StudyPlanID getStudyplanID() {
        return this._studyPlanID;
    }

    public DurationCourseInCurricularYear getDurationOfCourse() {
        return this._durationOfCourse;
    }

    public CourseQuantityCreditsEcts getQuantityOfCreditsEcts() {
        return this._quantityOfCreditsEcts;
    }

    public CourseInStudyPlanGeneratedID getGeneratedID() {
        return this._generatedID;
    }

    @Override
    public CourseInStudyPlanID identity() {
        return this._courseInStudyPlanID;
    }

    @Override
    public boolean sameAs(Object object) {
        if (this == object) return true;
        if (!(object instanceof CourseInStudyPlan courseInStudyPlan)) return false;
        return this._studyPlanID.equals(courseInStudyPlan._studyPlanID) &&
                this._courseID.equals(courseInStudyPlan._courseID);
    }
}
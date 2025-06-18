package PAI.domain.courseInStudyPlan;

import PAI.VOs.*;
import PAI.ddd.AggregateRoot;

import static PAI.utils.ValidationUtils.validateNotNull;

public class CourseInStudyPlan implements AggregateRoot<CourseInStudyPlanID> {

    private CourseID courseID;
    private Semester semester;
    private CurricularYear curricularYear;
    private StudyPlanID studyPlanID;
    private DurationCourseInCurricularYear durationOfCourse;
    private CourseQuantityCreditsEcts quantityOfCreditsEcts;
    private CourseInStudyPlanID courseInStudyPlanID;
    private CourseInStudyPlanGeneratedID generatedID;
    private ProgrammeID programmeID;

    public CourseInStudyPlan(Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyplanID,
                             CourseInStudyPlanID courseInStudyPlanID, DurationCourseInCurricularYear durationOfCourse,
                             CourseQuantityCreditsEcts quantityOfCreditsEcts, CourseInStudyPlanGeneratedID generatedID, ProgrammeID programmeID) {

        this.courseID = validateNotNull(courseID, "Course ID");
        this.semester = validateNotNull(semester, "Semester");
        this.curricularYear = validateNotNull(curricularYear, "Curricular Year");
        this.studyPlanID = validateNotNull(studyplanID, "Study Plan ID");
        this.durationOfCourse = validateNotNull(durationOfCourse, "Duration of Course");
        this.quantityOfCreditsEcts = validateNotNull(quantityOfCreditsEcts, "Quantity of Credits Ects");
        this.courseInStudyPlanID = validateNotNull(courseInStudyPlanID, "Course In Study Plan ID");
        this.generatedID = validateNotNull(generatedID, "Course In Study Plan Generated ID");
        this.programmeID = validateNotNull(programmeID, "Programme ID");
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

        return this.courseInStudyPlanID.equals(courseToBeCompared.courseInStudyPlanID);
    }

    public CourseID getCourseID() {
        return this.courseID;
    }

    public Semester getSemester() {
        return this.semester;
    }

    public CurricularYear getCurricularYear() {
        return this.curricularYear;
    }

    public StudyPlanID getStudyplanID() {
        return this.studyPlanID;
    }

    public DurationCourseInCurricularYear getDurationOfCourse() {
        return this.durationOfCourse;
    }

    public CourseQuantityCreditsEcts getQuantityOfCreditsEcts() {
        return this.quantityOfCreditsEcts;
    }

    public CourseInStudyPlanGeneratedID getGeneratedID() {
        return this.generatedID;
    }

    public ProgrammeID getProgrammeID() {return this.programmeID; }

    @Override
    public CourseInStudyPlanID identity() {
        return this.courseInStudyPlanID;
    }

    @Override
    public boolean sameAs(Object object) {
        if (this == object) return true;
        if (!(object instanceof CourseInStudyPlan courseInStudyPlan)) return false;
        return this.studyPlanID.equals(courseInStudyPlan.studyPlanID) &&
                this.courseID.equals(courseInStudyPlan.courseID);
    }
}
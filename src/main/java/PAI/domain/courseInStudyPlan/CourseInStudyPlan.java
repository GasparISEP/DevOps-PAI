package PAI.domain.courseInStudyPlan;

import PAI.VOs.*;
import PAI.ddd.AggregateRoot;

public class CourseInStudyPlan implements AggregateRoot<CourseInStudyPlanID> {

    private CourseID _courseID;
    private Semester _semester;
    private CurricularYear _curricularYear;
    private StudyPlanID _studyPlanID;
    private DurationCourseInCurricularYear _durationOfCourse;
    private CourseQuantityCreditsEcts _quantityOfCreditsEcts;
    private CourseInStudyPlanID _courseInStudyPlanID;

    public CourseInStudyPlan(Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyplanID, CourseInStudyPlanID courseInStudyPlanID,
                             DurationCourseInCurricularYear durationOfCourse, CourseQuantityCreditsEcts quantityOfCreditsEcts) {

        if (courseID == null) {
            throw new IllegalArgumentException("Course ID cannot be null");
        }
        this._courseID = courseID;

        if (semester == null) {
            throw new IllegalArgumentException("Semester cannot be null");
        }
        this._semester = semester;

        if(curricularYear == null) {
            throw new IllegalArgumentException("Curricular Year cannot be null");
        }
        this._curricularYear = curricularYear;

        if (studyplanID == null) {
            throw new IllegalArgumentException("Study Plan ID cannot be null");
        }
        this._studyPlanID = studyplanID;

        if (durationOfCourse == null) {
            throw new IllegalArgumentException("Duration of Course cannot be null");
        }
        this._durationOfCourse = durationOfCourse;

        if (quantityOfCreditsEcts == null) {
            throw new IllegalArgumentException("Quantity of Credits Ects cannot be null");
        }
        this._quantityOfCreditsEcts = quantityOfCreditsEcts;

        if (courseInStudyPlanID == null) {
            throw new IllegalArgumentException("Course In Study Plan ID cannot be null");
        }
        this._courseInStudyPlanID = courseInStudyPlanID;
    }

    @Override
    public boolean equals(Object ObjectToCompare) {
        // Verifica se o objeto a comparar é o mesmo
        if (this == ObjectToCompare) {
            return true;
        }
        // Verifica se o objeto não é uma instância de CourseInStudyPlan
        if (!(ObjectToCompare instanceof CourseInStudyPlan)) {
            return false;
        }
        // Faz o cast do objeto para CourseInStudyPlan
        CourseInStudyPlan courseToBeCompared = (CourseInStudyPlan) ObjectToCompare;

        // Compara os atributos relevantes
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
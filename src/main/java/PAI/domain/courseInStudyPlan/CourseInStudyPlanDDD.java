package PAI.domain.courseInStudyPlan;

import PAI.VOs.*;

public class CourseInStudyPlanDDD {

    private CourseID _courseID;
    private Semester _semester;
    private CurricularYear _curricularYear;
    private StudyPlanID _studyPlanID;
    private CourseInStudyPlanID _courseInStudyPlanID;

    public CourseInStudyPlanDDD(Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyplanID) {

        this._courseID = courseID;
        this._semester = semester;
        this._curricularYear = curricularYear;
        this._studyPlanID = studyplanID;
        this._courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyplanID);
    }

    @Override
    public boolean equals(Object ObjectToCompare) {
        // Verifica se o objeto a comparar é o mesmo
        if (this == ObjectToCompare) {
            return true;
        }
        // Verifica se o objeto não é uma instância de CourseInStudyPlan
        if (!(ObjectToCompare instanceof CourseInStudyPlanDDD)) {
            return false;
        }
        // Faz o cast do objeto para CourseInStudyPlan
        CourseInStudyPlanDDD courseToBeCompared = (CourseInStudyPlanDDD) ObjectToCompare;

        // Compara os atributos relevantes
        return this._courseID.equals(courseToBeCompared._courseID);
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

    public CourseInStudyPlanID getCourseInStudyPlanID() {
        return this._courseInStudyPlanID;
    }
}
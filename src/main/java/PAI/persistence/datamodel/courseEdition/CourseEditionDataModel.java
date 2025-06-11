package PAI.persistence.datamodel.courseEdition;

import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import PAI.persistence.datamodel.teacher.TeacherIDDataModel;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "courseEdition")
public class CourseEditionDataModel implements Serializable {

    @EmbeddedId
    private CourseEditionIDDataModel _courseEditionIDDataModel;

    @Embedded
    private CourseEditionGeneratedIDDataModel _courseEditionGeneratedIDDataModel;

    @Embedded
    private TeacherIDDataModel _teacherIDDataModel;

    protected CourseEditionDataModel() {}

    public CourseEditionDataModel(CourseEditionIDDataModel courseEditionIDDataModel, CourseEditionGeneratedIDDataModel courseEditionGeneratedIDDataModel) {

        if (courseEditionIDDataModel == null)
            throw new IllegalArgumentException("courseEditionIDDataModel cannot be null");
        if (courseEditionGeneratedIDDataModel == null)
            throw new IllegalArgumentException("courseEditionGeneratedIDDataModel cannot be null");

        this._courseEditionIDDataModel = courseEditionIDDataModel;
        this._courseEditionGeneratedIDDataModel = courseEditionGeneratedIDDataModel;
    }

    public CourseEditionDataModel(CourseEditionIDDataModel courseEditionIDDataModel, CourseEditionGeneratedIDDataModel courseEditionGeneratedIDDataModel, TeacherIDDataModel teacherIDDataModel) {

        if (courseEditionIDDataModel == null)
            throw new IllegalArgumentException("courseEditionIDDataModel cannot be null");
        if (courseEditionGeneratedIDDataModel == null)
            throw new IllegalArgumentException("courseEditionGeneratedIDDataModel cannot be null");
        if (teacherIDDataModel == null)
            throw new IllegalArgumentException("teacherIDDataModel cannot be null");

        this._courseEditionIDDataModel = courseEditionIDDataModel;
        this._courseEditionGeneratedIDDataModel = courseEditionGeneratedIDDataModel;
        this._teacherIDDataModel = teacherIDDataModel;
    }

    public CourseEditionIDDataModel getCourseEditionIDDataModel() {
        return _courseEditionIDDataModel;
    }

    public ProgrammeEditionIdDataModel getProgrammeEditionIDDataModel() {
        return _courseEditionIDDataModel.getProgrammeEditionIDDataModel();
    }

    public CourseInStudyPlanIDDataModel getCourseInStudyPlanIDDataModel() {
        return _courseEditionIDDataModel.getCourseInStudyPlanIDDataModel();
    }

    public CourseEditionGeneratedIDDataModel getCourseEditionGeneratedIDDataModel() {
        return this._courseEditionGeneratedIDDataModel;
    }

    public TeacherIDDataModel getTeacherIDDataModel() {
        return _teacherIDDataModel;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || !(obj instanceof CourseEditionDataModel))
            return false;
        CourseEditionDataModel other = (CourseEditionDataModel) obj;
        if (other._courseEditionIDDataModel.equals(this._courseEditionIDDataModel))
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return _courseEditionIDDataModel.hashCode();
    }
}

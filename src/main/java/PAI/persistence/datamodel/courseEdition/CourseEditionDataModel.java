package PAI.persistence.datamodel.courseEdition;

import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "courseEdition")
public class CourseEditionDataModel {

    @EmbeddedId
    private CourseEditionIDDataModel _courseEditionIDDataModel;

    @Embedded
    private ProgrammeEditionIdDataModel _programmeEditionIDDataModel;

    @Embedded
    private CourseInStudyPlanIDDataModel _courseInStudyPlanIDDataModel;

    protected CourseEditionDataModel() {}

    public CourseEditionDataModel(CourseEditionIDDataModel courseEditionIDDataModel, ProgrammeEditionIdDataModel programmeEditionIDDataModel,
                                  CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel) {

        if (courseEditionIDDataModel == null)
            throw new IllegalArgumentException("courseEditionIDDataModel cannot be null");
        if (programmeEditionIDDataModel == null)
            throw new IllegalArgumentException("programmeEditionIDDataModel cannot be null");
        if (courseInStudyPlanIDDataModel == null)
            throw new IllegalArgumentException("courseInStudyPlanIDDataModel cannot be null");

        this._courseEditionIDDataModel = courseEditionIDDataModel;
        this._programmeEditionIDDataModel = programmeEditionIDDataModel;
        this._courseInStudyPlanIDDataModel = courseInStudyPlanIDDataModel;
    }

    public CourseEditionIDDataModel getCourseEditionIDDataModel() {
        return _courseEditionIDDataModel;
    }

    public ProgrammeEditionIdDataModel getProgrammeEditionIDDataModel() {
        return _programmeEditionIDDataModel;
    }

    public CourseInStudyPlanIDDataModel getCourseInStudyPlanIDDataModel() {
        return _courseInStudyPlanIDDataModel;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}

package PAI.persistence.datamodel.courseEdition;

import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.io.Serializable;

@Embeddable
public class CourseEditionIDDataModel implements Serializable {

    @Embedded
    private ProgrammeEditionIdDataModel _programmeEditionIdDataModel;

    @Embedded
    private CourseInStudyPlanIDDataModel _courseInStudyPlanIDDataModel;

    public CourseEditionIDDataModel() {}

    public CourseEditionIDDataModel(ProgrammeEditionIdDataModel programmeEditionIdDataModel, CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel) {
        if (programmeEditionIdDataModel == null)
            throw new IllegalArgumentException("ProgrammeEditionIDDataModel cannot be null");
        if (courseInStudyPlanIDDataModel == null)
            throw new IllegalArgumentException("CourseInStudyPlanIDDataModel cannot be null");

        this._programmeEditionIdDataModel = programmeEditionIdDataModel;
        this._courseInStudyPlanIDDataModel = courseInStudyPlanIDDataModel;
    }

    public ProgrammeEditionIdDataModel getProgrammeEditionIDDataModel() {
        return _programmeEditionIdDataModel;
    }

    public CourseInStudyPlanIDDataModel getCourseInStudyPlanIDDataModel() {
        return _courseInStudyPlanIDDataModel;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || !(obj instanceof CourseEditionIDDataModel))
            return false;
        CourseEditionIDDataModel other = (CourseEditionIDDataModel) obj;
        return _programmeEditionIdDataModel.equals(other._programmeEditionIdDataModel) && _courseInStudyPlanIDDataModel.equals(other._courseInStudyPlanIDDataModel);
    }

    @Override
    public int hashCode() {
        return -1;
    }
}

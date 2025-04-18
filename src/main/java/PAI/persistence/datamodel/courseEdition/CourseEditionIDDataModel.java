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

    public ProgrammeEditionIdDataModel getProgrammeEditionIdDataModel() {
        return null;
    }

    public CourseInStudyPlanIDDataModel getCourseInStudyPlanIDDataModel() {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int hashCode() {
        return -1;
    }



}

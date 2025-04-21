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

    public CourseEditionIDDataModel getCourseEditionIDDataModel() {
        return null;
    }

    public ProgrammeEditionIdDataModel getProgrammeEditionIDDataModel() {
        return null;
    }

    public CourseInStudyPlanIDDataModel getCourseInStudyPlanIDDataModel() {
        return null;
    }
}

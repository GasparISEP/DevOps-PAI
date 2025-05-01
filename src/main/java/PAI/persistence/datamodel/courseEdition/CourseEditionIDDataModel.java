package PAI.persistence.datamodel.courseEdition;

import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class CourseEditionIDDataModel implements Serializable {

    @Embedded
    private ProgrammeEditionIdDataModel _programmeEditionIdDataModel;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "CISPstudyPlanIDDataModel._programmeIDDataModel.programmeAcronym",
                    column = @Column(name = "study_plan_programme_acronym")),
            @AttributeOverride(name = "CISPstudyPlanIDDataModel._programmeIDDataModel.programmeName",
                    column = @Column(name = "study_plan_programme_name")),
            @AttributeOverride(name = "CISPcourseID.courseAcronym",
                    column = @Column(name = "course_acronym")),
            @AttributeOverride(name = "CISPcourseID.courseName",
                    column = @Column(name = "course_name"))
    })
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
        return _programmeEditionIdDataModel.hashCode() + _courseInStudyPlanIDDataModel.hashCode();
    }
}

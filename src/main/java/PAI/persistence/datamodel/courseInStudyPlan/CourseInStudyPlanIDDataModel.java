package PAI.persistence.datamodel.courseInStudyPlan;

import PAI.persistence.datamodel.course.CourseIDDataModel;
import PAI.persistence.datamodel.studyPlan.StudyPlanIDDataModel;
import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class CourseInStudyPlanIDDataModel implements Serializable {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "implementationDate",
                    column = @Column(name = "local_date")),
            @AttributeOverride(name = "_programmeIDDataModel.programmeAcronym",
                    column = @Column(name = "programme_acronym")),
            @AttributeOverride(name = "_programmeIDDataModel.programmeName",
                    column = @Column(name = "programme_name")),
    })
    private StudyPlanIDDataModel CISPstudyPlanIDDataModel;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "courseAcronym",
                    column = @Column(name = "course_ID_acronym")),
            @AttributeOverride(name = "courseName",
                    column = @Column(name = "course_ID_name")),
    })

    private CourseIDDataModel CISPcourseID;
    public CourseInStudyPlanIDDataModel() {}

    public CourseInStudyPlanIDDataModel(StudyPlanIDDataModel studyPlanIDDataModel, CourseIDDataModel courseID) {
        this.CISPstudyPlanIDDataModel = studyPlanIDDataModel;
        this.CISPcourseID = courseID;
    }

    public CourseIDDataModel getCourseID() {
        return CISPcourseID;
    }

    public StudyPlanIDDataModel getStudyPlanIDDataModel() {
        return CISPstudyPlanIDDataModel;
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (this == objectToCompare) return true;
        if (!(objectToCompare instanceof CourseInStudyPlanIDDataModel)) return false;
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = (CourseInStudyPlanIDDataModel) objectToCompare;
        return CISPstudyPlanIDDataModel.equals(courseInStudyPlanIDDataModel.CISPstudyPlanIDDataModel) &&
                CISPcourseID.equals(courseInStudyPlanIDDataModel.CISPcourseID);
    }

    @Override
    public int hashCode() {
        return CISPstudyPlanIDDataModel.hashCode() + CISPcourseID.hashCode();
    }
}
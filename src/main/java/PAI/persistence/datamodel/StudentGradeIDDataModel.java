package PAI.persistence.datamodel;

import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudentGradeIDDataModel implements Serializable {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "uniqueNumber", column = @Column(name = "student_id"))
    })
    private StudentIDDataModel studentIDDataModel;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "_programmeEditionIdDataModel._programmeIDDataModel.programmeAcronym", column = @Column(name = "course_programme_acronym")),
            @AttributeOverride(name = "_programmeEditionIdDataModel._programmeIDDataModel.programmeName", column = @Column(name = "course_programme_name")),
            @AttributeOverride(name = "_courseInStudyPlanIDDataModel.CISPcourseID.courseAcronym", column = @Column(name = "course_id_acronym"))
    })
    private CourseEditionIDDataModel courseEditionIDDataModel;

    protected StudentGradeIDDataModel() {}

    public StudentGradeIDDataModel (StudentIDDataModel studentIDDataModel, CourseEditionIDDataModel courseEditionIDDataModel){
        if (studentIDDataModel == null || courseEditionIDDataModel == null){
            throw new IllegalArgumentException("Cannot be null");
        }
        this.studentIDDataModel = studentIDDataModel;
        this.courseEditionIDDataModel = courseEditionIDDataModel;
    }

    public StudentIDDataModel getStudentIDDataModel() {
        return studentIDDataModel;
    }

    public CourseEditionIDDataModel getCourseEditionIDDataModel() {
        return courseEditionIDDataModel;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentGradeIDDataModel that)) return false;
        return Objects.equals(studentIDDataModel, that.studentIDDataModel) && Objects.equals(courseEditionIDDataModel, that.courseEditionIDDataModel);
    }
    @Override
    public int hashCode() {
        return studentIDDataModel.hashCode() + courseEditionIDDataModel.hashCode();
    }

}

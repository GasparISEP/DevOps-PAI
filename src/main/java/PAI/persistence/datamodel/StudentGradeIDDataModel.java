package PAI.persistence.datamodel;

import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudentGradeIDDataModel implements Serializable {

    @Embedded
    private StudentIDDataModel studentIDDataModel;
    @Embedded
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

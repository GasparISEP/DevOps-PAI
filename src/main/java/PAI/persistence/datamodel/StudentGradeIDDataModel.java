package PAI.persistence.datamodel;

import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.io.Serializable;

@Embeddable
public class StudentGradeIDDataModel implements Serializable {

    @Embedded
    private StudentIDDataModel _studentIDDataModel;
    @Embedded
    private CourseEditionIDDataModel _courseEditionIDDataModel;

    protected StudentGradeIDDataModel() {}

}

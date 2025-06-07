package PAI.mapper.courseEditionEnrolment;

import PAI.VOs.*;
import PAI.persistence.datamodel.courseEditionEnrolment.CourseEditionEnrolmentGeneratedIDDataModel;
import org.springframework.stereotype.Component;

@Component
public class CourseEditionEnrolmentGeneratedIDMapperImpl {

    public CourseEditionEnrolmentGeneratedID toDomain(CourseEditionEnrolmentGeneratedIDDataModel courseEditionEnrolmentgeneratedIDDataModel) throws Exception{
        return new CourseEditionEnrolmentGeneratedID(courseEditionEnrolmentgeneratedIDDataModel.getGeneratedID());
    }

    public CourseEditionEnrolmentGeneratedIDDataModel toDataModel(CourseEditionEnrolmentGeneratedID courseEditionEnrolmentID) throws Exception {
        return new CourseEditionEnrolmentGeneratedIDDataModel(courseEditionEnrolmentID.getCourseEditionEnrolmentGeneratedID());
    }
}

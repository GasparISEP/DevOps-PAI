package PAI.mapper.courseEditionEnrolment;

import PAI.VOs.CourseEditionEnrolmentGeneratedID;
import PAI.persistence.datamodel.courseEditionEnrolment.CourseEditionEnrolmentGeneratedIDDataModel;

import java.util.Optional;

public interface ICourseEditionEnrolmentGeneratedIDMapper {

    CourseEditionEnrolmentGeneratedID toDomain(CourseEditionEnrolmentGeneratedIDDataModel courseEditionEnrolmentgeneratedIDDataModel) throws Exception;

    CourseEditionEnrolmentGeneratedIDDataModel toDataModel(CourseEditionEnrolmentGeneratedID courseEditionEnrolmentID) throws Exception;
}

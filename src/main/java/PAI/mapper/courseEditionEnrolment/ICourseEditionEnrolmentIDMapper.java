package PAI.mapper.courseEditionEnrolment;

import PAI.VOs.CourseEditionEnrolmentID;
import PAI.persistence.datamodel.courseEditionEnrolment.CourseEditionEnrolmentIDDataModel;

import java.util.Optional;

public interface ICourseEditionEnrolmentIDMapper {

    Optional<CourseEditionEnrolmentID> toDomain(CourseEditionEnrolmentIDDataModel courseEditionEnrolmentIDDataModel) throws Exception;

    Optional<CourseEditionEnrolmentIDDataModel> toDataModel(CourseEditionEnrolmentID courseEditionEnrolmentID) throws Exception;
}

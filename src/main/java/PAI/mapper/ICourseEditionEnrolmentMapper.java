package PAI.mapper;

import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.persistence.datamodel.CourseEditionEnrolmentDataModel;

import java.util.Optional;

public interface ICourseEditionEnrolmentMapper {

        Optional<CourseEditionEnrolmentDataModel> toDataModel(CourseEditionEnrolment courseEditionEnrolment) throws Exception;

        Optional<CourseEditionEnrolment> toDomain(CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel) throws Exception;
}

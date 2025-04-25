package PAI.mapper;

import PAI.domain.CourseEditionEnrolment;
import PAI.persistence.datamodel.CourseEditionEnrolmentDataModel;

import java.util.Optional;

public interface ICourseEditionEnrolmentMapper {

        Optional<CourseEditionEnrolmentDataModel> toDataModel(CourseEditionEnrolment enrolment) throws Exception;

        Optional<CourseEditionEnrolment> toDomain(CourseEditionEnrolmentDataModel dataModel) throws Exception;
}

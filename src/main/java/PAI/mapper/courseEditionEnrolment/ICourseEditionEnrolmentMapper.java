package PAI.mapper.courseEditionEnrolment;

import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.persistence.datamodel.courseEditionEnrolment.CourseEditionEnrolmentDataModel;

import java.util.Optional;

public interface ICourseEditionEnrolmentMapper {

        Optional<CourseEditionEnrolmentDataModel> toDataModel(CourseEditionEnrolment courseEditionEnrolment) throws Exception;

        Optional<CourseEditionEnrolment> toDomain(CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel) throws Exception;

        void updateDataModelFromDomain(CourseEditionEnrolment domain, CourseEditionEnrolmentDataModel dataModel);
}

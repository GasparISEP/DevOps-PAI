package PAI.assembler.courseEditionEnrolment;

import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentDto;

public interface ICourseEditionEnrolmentAssembler {
    CourseEditionEnrolment toDomain(CourseEditionEnrolmentDto courseEditionEnrolmentDto) throws Exception;
}   



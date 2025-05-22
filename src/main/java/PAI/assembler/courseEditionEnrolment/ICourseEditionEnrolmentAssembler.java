package PAI.assembler.courseEditionEnrolment;

import java.util.Optional;

import PAI.VOs.CourseEditionID;
import PAI.VOs.StudentID;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentDto;

public interface ICourseEditionEnrolmentAssembler {
    CourseEditionEnrolment toDomain(CourseEditionEnrolmentDto courseEditionEnrolmentDto) throws Exception;
    Optional<CourseEditionEnrolmentDto> toDto(StudentID studentID, CourseEditionID courseEditionID);
}   



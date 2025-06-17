package PAI.assembler.courseEditionEnrolment;

import PAI.VOs.CourseEditionID;
import PAI.VOs.StudentID;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentDto;
import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentMinimalDTO;

public interface ICourseEditionEnrolmentAssembler {
    CourseEditionID toCourseEditionID(CourseEditionEnrolmentDto courseEditionEnrolmentDto) throws Exception;
    StudentID toStudentID(int studentUniqueNumber) throws Exception;

    CourseEditionEnrolmentDto toDto(CourseEditionEnrolment enrolment);

    CourseEditionEnrolmentMinimalDTO toMinimalDTO(CourseEditionEnrolment enrolment);


}   



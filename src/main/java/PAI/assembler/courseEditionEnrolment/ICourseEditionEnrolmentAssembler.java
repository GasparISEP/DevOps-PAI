package PAI.assembler.courseEditionEnrolment;

import PAI.VOs.CourseEditionID;
import PAI.VOs.StudentID;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentDto;

public interface ICourseEditionEnrolmentAssembler {
    CourseEditionID toCourseEditionID(CourseEditionEnrolmentDto courseEditionEnrolmentDto) throws Exception;
    StudentID toStudentID(int studentUniqueNumber) throws Exception;
}   



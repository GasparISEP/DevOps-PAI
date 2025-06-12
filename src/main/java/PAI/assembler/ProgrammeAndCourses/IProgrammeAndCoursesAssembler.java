package PAI.assembler.ProgrammeAndCourses;

import PAI.VOs.CourseID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.StudentID;
import PAI.VOs.US34Response;
import PAI.dto.ProgrammeAndCourses.StudentEnrolmentResultDto;
import PAI.dto.ProgrammeAndCourses.StudentProgrammeEnrolmentRequestDto;

import java.util.List;

public interface IProgrammeAndCoursesAssembler {
    StudentEnrolmentResultDto toDto(US34Response domain);
    StudentID toStudentID(StudentProgrammeEnrolmentRequestDto dto);
    ProgrammeEditionID toProgrammeEditionID(StudentProgrammeEnrolmentRequestDto dto) throws Exception;
    List<CourseID> toCourseIDs(StudentProgrammeEnrolmentRequestDto dto);
}

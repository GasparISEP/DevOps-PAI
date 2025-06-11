package PAI.service.student;

import PAI.VOs.CourseID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.StudentID;
import PAI.VOs.US34Response;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;

import java.util.List;

public interface IProgrammeAndCoursesEnrolmentService {
    ProgrammeEditionEnrolment createProgrammeEditionEnrolment(StudentID studentID, ProgrammeEditionID programmeEditionID);
    US34Response enrollStudentInProgrammeAndCourses(StudentID studentID, ProgrammeEditionID programmeEditionID, List<CourseID> courseIDS)throws Exception;
}

package PAI.assembler.studentGrade;

import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentMinimalDTO;
import PAI.dto.studentGrade.GradeAStudentCommand;
import PAI.dto.studentGrade.GradeAStudentRequestMinimalDTO;

public interface IStudentGradeEnrolmentAssembler {
    CourseEditionEnrolmentMinimalDTO toMinimalDTO(CourseEditionEnrolment enrolment);

    GradeAStudentCommand toCommand(GradeAStudentRequestMinimalDTO dto);

}
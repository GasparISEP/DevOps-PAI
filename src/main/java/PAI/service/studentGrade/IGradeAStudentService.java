package PAI.service.studentGrade;

import PAI.VOs.CourseEditionID;
import PAI.dto.studentGrade.GradeAStudentCommand;
import PAI.dto.studentGrade.GradeAStudentRequestMinimalDTO;
import PAI.dto.studentGrade.GradeAStudentResponseDTO;

public interface IGradeAStudentService {

    GradeAStudentResponseDTO gradeAStudent (GradeAStudentCommand gradeAStudentCommand) throws Exception;

    double knowApprovalRate (CourseEditionID courseEditionID) throws Exception;

    Double getAverageGrade(CourseEditionID courseEditionID) throws Exception;

    GradeAStudentResponseDTO gradeAStudentMinimal(GradeAStudentRequestMinimalDTO dto) throws Exception;
}

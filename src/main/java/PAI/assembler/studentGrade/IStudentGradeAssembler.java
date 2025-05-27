package PAI.assembler.studentGrade;

import PAI.domain.studentGrade.StudentGrade;
import PAI.dto.studentGrade.GradeAStudentCommand;
import PAI.dto.studentGrade.GradeAStudentRequestDTO;
import PAI.dto.studentGrade.GradeAStudentResponseDTO;

public interface IStudentGradeAssembler {

    GradeAStudentCommand toDomain (GradeAStudentRequestDTO gradeAStudentRequestDTO) throws Exception;

    GradeAStudentResponseDTO toDTO (
            StudentGrade studentGrade, String programmeID, String schoolYearID, String courseID,
            String studyPlanID, String courseInStudyPlanID, String programmeEditionID, String courseEditionID
    );
}

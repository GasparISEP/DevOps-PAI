package PAI.assembler.studentGrade;

import PAI.domain.studentGrade.StudentGrade;
import PAI.dto.studentGrade.GradeAStudentCommand;
import PAI.dto.studentGrade.StudentGradeRequestDTO;
import PAI.dto.studentGrade.StudentGradeResponseDTO;

public interface IStudentGradeAssembler {

    GradeAStudentCommand toDomain (StudentGradeRequestDTO studentGradeRequestDTO) throws Exception;

    StudentGradeResponseDTO toDTO (
            StudentGrade studentGrade, String programmeID, String schoolYearID, String courseID,
            String studyPlanID, String courseInStudyPlanID, String programmeEditionID, String courseEditionID
    );
}

package PAI.assembler.studentGrade;

import PAI.dto.studentGrade.GradeAStudentCommand;
import PAI.dto.studentGrade.StudentGradeRequestDTO;
import PAI.dto.studentGrade.StudentGradeResponseDTO;

public interface IStudentGradeAssembler {

    GradeAStudentCommand toDomain (StudentGradeRequestDTO studentGradeRequestDTO) throws Exception;

    StudentGradeResponseDTO toDTO ();
}

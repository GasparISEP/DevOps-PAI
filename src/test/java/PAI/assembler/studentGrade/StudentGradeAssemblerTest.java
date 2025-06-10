package PAI.assembler.studentGrade;

import PAI.VOs.Date;
import PAI.VOs.Grade;
import PAI.VOs.StudentID;
import PAI.domain.studentGrade.StudentGrade;
import PAI.dto.studentGrade.GradeAStudentCommand;
import PAI.dto.studentGrade.GradeAStudentRequestDTO;
import PAI.dto.studentGrade.GradeAStudentResponseDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentGradeAssemblerTest {

    @Test
    void toDomainShouldConvertRequestDTOtoDomain() throws Exception {
        // Arrange
        GradeAStudentRequestDTO requestDTO = mock(GradeAStudentRequestDTO.class);

        when(requestDTO.grade()).thenReturn(18.5);
        when(requestDTO.date()).thenReturn("24-05-2025");
        when(requestDTO.studentUniqueNumber()).thenReturn(1234567);
        when(requestDTO.programmeName()).thenReturn("Computer Science");
        when(requestDTO.programmeAcronym()).thenReturn("CS");
        when(requestDTO.schoolYearId()).thenReturn("f81d4fae-7dec-11d0-a765-00a0c91e6bf6");
        when(requestDTO.courseAcronym()).thenReturn("DSOFT");
        when(requestDTO.courseName()).thenReturn("Desenvolvimento de Software");
        when(requestDTO.studyPlanImplementationDate()).thenReturn("15-08-2017");

        StudentGradeAssembler assembler = new StudentGradeAssembler();

        // Act
        GradeAStudentCommand result = assembler.toDomain(requestDTO);

        // Assert
        assertInstanceOf(GradeAStudentCommand.class, result);
    }

    @Test
    void toDTOShouldConvertDomainToDTO() {
        // Arrange
        StudentGrade studentGrade = mock(StudentGrade.class);
        Grade grade = mock(Grade.class);
        Date date = mock(Date.class);
        StudentID studentID = mock(StudentID.class);

        String programmeID = "pr456";
        String schoolYearID = "sy2025";
        String courseID = "c999";
        String studyPlanID = "sp2023";
        String courseInStudyPlanID = "csp101";
        String programmeEditionID = "pe789";
        String courseEditionID = "ce456";

        StudentGradeAssembler assembler = new StudentGradeAssembler();

        when(studentGrade.get_studentID()).thenReturn(studentID);
        when(studentGrade.getGrade()).thenReturn(grade);
        when(studentGrade.get_date()).thenReturn(date);

        // Act
        GradeAStudentResponseDTO result = assembler.toDTO(studentGrade, programmeID, schoolYearID, courseID, studyPlanID, courseInStudyPlanID, programmeEditionID, courseEditionID);

        // Assert
        assertInstanceOf(GradeAStudentResponseDTO.class, result);
    }
}
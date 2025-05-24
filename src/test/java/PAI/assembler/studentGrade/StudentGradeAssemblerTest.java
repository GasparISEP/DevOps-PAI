package PAI.assembler.studentGrade;

import PAI.VOs.*;
import PAI.dto.studentGrade.GradeAStudentCommand;
import PAI.dto.studentGrade.StudentGradeRequestDTO;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentGradeAssemblerTest {

    @Test
    void toDomainShouldConvertRequestDTOtoDomain() throws Exception {
        // Arrange
        StudentGradeRequestDTO requestDTO = mock(StudentGradeRequestDTO.class);

        when(requestDTO.grade()).thenReturn(18.5);
        when(requestDTO.date()).thenReturn("24-05-2025");
        when(requestDTO.studentUniqueNumber()).thenReturn(1234567);
        when(requestDTO.programmeName()).thenReturn("Computer Science");
        when(requestDTO.programmeAcronym()).thenReturn("CS");
        when(requestDTO.schoolYearId()).thenReturn("f81d4fae-7dec-11d0-a765-00a0c91e6bf6");
        when(requestDTO.courseAcronym()).thenReturn("DSOFT");
        when(requestDTO.courseName()).thenReturn("Desenvolvimento de Software");
        when(requestDTO.studyPlanDate()).thenReturn("15-08-2017");

        StudentGradeAssembler assembler = new StudentGradeAssembler();

        // Act
        GradeAStudentCommand result = assembler.toDomain(requestDTO);

        // Assert
        assertInstanceOf(GradeAStudentCommand.class, result);
    }

}
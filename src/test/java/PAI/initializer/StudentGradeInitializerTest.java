package PAI.initializer;

import PAI.VOs.*;
import PAI.service.studentGrade.IStudentGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StudentGradeInitializerTest {

    private StudentGradeInitializer initializer;
    private IStudentGradeService studentGradeServiceMock;
    private Path tempFile;

    @BeforeEach
    void setup() throws Exception {
        initializer = new StudentGradeInitializer();
        studentGradeServiceMock = mock(IStudentGradeService.class);
        tempFile = Files.createTempFile("student-grades", ".csv");
    }

    @AfterEach
    void cleanup() throws Exception {
        Files.deleteIfExists(tempFile);
    }

    @Test
    void shouldRegisterStudentGradesWithCorrectArguments() throws Exception {
        // Arrange
        String csvContent = "Date,Grade,LocalDate,StudentID,GeneratedID,CourseAcronym,CourseName,ProgrammeAcronym,SchoolYearID,ProgrammeAcronym\n" +
                "2025-06-14,18.5,2025-01-15,1234567,IGNORED,CS,Algorithms," +
                "CS," + UUID.randomUUID() + ",CS\n" +
                "2025-06-15,16.0,2025-01-15,1567890,IGNORED,CS,Data Structures," +
                "CS," + UUID.randomUUID() + ",CS\n";
        Files.write(tempFile, csvContent.getBytes());

        // Act
        initializer.loadStudentGrade(studentGradeServiceMock, tempFile.toString());

        // Assert — expect 2 calls
        verify(studentGradeServiceMock, times(2))
                .newStudentGrade(any(Grade.class), any(Date.class), any(StudentID.class), any(CourseEditionID.class));
    }

    @Test
    void shouldSkipInvalidLinesAndContinue() throws Exception {
        // Arrange — second line is invalid (missing fields)
        String csvContent = "Date,Grade,LocalDate,StudentID,GeneratedID,CourseAcronym,CourseName,ProgrammeAcronym,SchoolYearID,ProgrammeAcronym\n" +
                "2025-06-14,18.5,2025-01-15,1234567,IGNORED,CS,Algorithms,CS," + UUID.randomUUID() + ",CS\n" +
                "Invalid,Line,That,Will,Cause,An,Exception\n" +
                "2025-06-15,16.0,2025-01-15,1567890,IGNORED,CS,Data Structures,CS," + UUID.randomUUID() + ",CS\n";
        Files.write(tempFile, csvContent.getBytes());

        // Act
        initializer.loadStudentGrade(studentGradeServiceMock, tempFile.toString());

        // Assert — expect 2 successful calls
        verify(studentGradeServiceMock, times(2))
                .newStudentGrade(any(Grade.class), any(Date.class), any(StudentID.class), any(CourseEditionID.class));
    }

    @Test
    void shouldHandleMissingFileGracefully() {
        // Act & Assert
        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() ->
                initializer.loadStudentGrade(studentGradeServiceMock, "non-existent-file.csv"));
    }
}

package PAI.initializer;

import PAI.VOs.*;
import PAI.controller.US22_IWantToGradeAStudentInACourseEditionController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StudentGradeInitializerTest {

    private StudentGradeInitializer initializer;
    private US22_IWantToGradeAStudentInACourseEditionController controllerDouble;
    private Path tempFile;

    @BeforeEach
    void setup() throws Exception {
        initializer = new StudentGradeInitializer();
        controllerDouble = mock(US22_IWantToGradeAStudentInACourseEditionController.class);
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
        initializer.loadStudentGrade(controllerDouble, tempFile.toString());

        // Assert — expect 2 calls to controller
        verify(controllerDouble, times(2))
                .registerStudentGrade(any(Grade.class), any(Date.class), any(StudentID.class), any(CourseEditionID.class));
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
        initializer.loadStudentGrade(controllerDouble, tempFile.toString());

        // Assert — should have registered two valid lines, ignoring the bad one
        verify(controllerDouble, times(2))
                .registerStudentGrade(any(Grade.class), any(Date.class), any(StudentID.class), any(CourseEditionID.class));
    }

    @Test
    void shouldHandleMissingFileGracefully() {
        // Act & Assert
        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> {
            initializer.loadStudentGrade(controllerDouble, "non-existent-file.csv");
        });
    }
}

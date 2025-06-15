package PAI.initializer;

import PAI.controller.US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.mockito.Mockito.*;

class StudyPlanInitializerTest {

    private StudyPlanInitializer initializer;
    private US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController controllerDouble;
    private Path tempFile;

    @BeforeEach
    void setup() throws Exception {
        initializer = new StudyPlanInitializer();
        controllerDouble = mock(US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController.class);
        tempFile = Files.createTempFile("study-plan", ".csv");
    }

    @AfterEach
    void cleanup() throws Exception {
        Files.deleteIfExists(tempFile);
    }

    @Test
    void shouldRegisterStudyPlanWithCorrectArgumentsWhenLoadingValidCSV() throws Exception {
        // Arrange
        String csvContent = "ProgrammeName;Acronym;StartDate\n" +
                            "Computer Science;CS;2024-09-01\n" +
                            "Mathematics;MATH;2024-09-01\n";
        Files.write(tempFile, csvContent.getBytes());

        ArgumentCaptor<String> nameCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> acronymCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> dateCaptor = ArgumentCaptor.forClass(String.class);

        // Act
        initializer.loadStudyPlan(controllerDouble, tempFile.toString());

        // Assert
        verify(controllerDouble, times(2))
                .registerStudyPlan(nameCaptor.capture(), acronymCaptor.capture(), dateCaptor.capture());

        Assertions.assertTrue(
    nameCaptor.getAllValues().get(0).equals("Computer Science") &&
            acronymCaptor.getAllValues().get(0).equals("CS") &&
            dateCaptor.getAllValues().get(0).equals("2024-09-01") &&

            nameCaptor.getAllValues().get(1).equals("Mathematics") &&
            acronymCaptor.getAllValues().get(1).equals("MATH") &&
            dateCaptor.getAllValues().get(1).equals("2024-09-01")
        );
    }

    @Test
    void shouldSkipEmptyLines() throws Exception {
        // Arrange
        String csvContent = "ProgrammeName;Acronym;StartDate\n" +
                            "Computer Science;CS;2024-09-01\n" +
                            "\n" +
                            "Mathematics;MATH;2024-09-01\n";
        Files.write(tempFile, csvContent.getBytes());

        // Act
        initializer.loadStudyPlan(controllerDouble, tempFile.toString());

        // Assert
        verify(controllerDouble, times(2)).registerStudyPlan(any(), any(), any());
    }

    @Test
    void shouldHandleInvalidLinesAndContinue() throws Exception {
        // Arrange
        String csvContent = "ProgrammeName;Acronym;StartDate\n" +
                            "Computer Science;CS;2024-09-01\n" +
                            "IncompleteLineWithoutEnoughParts\n" +
                            "Mathematics;MATH;2024-09-01\n";
        Files.write(tempFile, csvContent.getBytes());

        // Act
        initializer.loadStudyPlan(controllerDouble, tempFile.toString());

        // Assert
        verify(controllerDouble, times(2)).registerStudyPlan(any(), any(), any());
    }

    @Test
    void shouldHandleExceptionWhenReadingFile() {
        // Act & Assert
        Assertions.assertDoesNotThrow(() -> {
            initializer.loadStudyPlan(controllerDouble, "non-existent-file.csv");
        });
    }
}

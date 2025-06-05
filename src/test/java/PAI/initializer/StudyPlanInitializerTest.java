package PAI.initializer;

import PAI.controller.US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.CommandLineRunner;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class StudyPlanInitializerTest {

    private US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController controller;
    private StudyPlanInitializer initializer;

    @BeforeEach
    void setUp() {
        controller = mock(US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController.class);
        initializer = new StudyPlanInitializer();
    }

    @Test
    void commandLineRunnerExecutesLoadStudyPlan() throws Exception {
        // Arrange

        StudyPlanInitializer initializer = spy(new StudyPlanInitializer());

        CommandLineRunner runner = initializer.loadDataRegisterStudyPlan(controller);

        // Act
        runner.run();

        // Assert
        verify(initializer).loadStudyPlan(eq(controller), eq(Path.of("src/main/resources/StudyPlan_Data.csv")));
    }

    @Test
    void shouldLoadAndRegisterStudyPlansFromCsvFile() throws Exception {

        File testFile = getTestFile();

        when(controller.registerStudyPlan(anyString(), anyString(), anyString())).thenReturn(true);

        initializer.loadStudyPlan(controller, testFile.toPath());

        ArgumentCaptor<String> programmeNameCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> programmeAcronymCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> startDateCaptor = ArgumentCaptor.forClass(String.class);

        verify(controller, times(3)).registerStudyPlan(
                programmeNameCaptor.capture(),
                programmeAcronymCaptor.capture(),
                startDateCaptor.capture()
        );

        List<String> capturedProgrammeNames = programmeNameCaptor.getAllValues();
        List<String> capturedProgrammeAcronyms = programmeAcronymCaptor.getAllValues();
        List<String> capturedStartDates = startDateCaptor.getAllValues();

        assertTrue(capturedProgrammeNames.containsAll(capturedProgrammeNames));
        assertTrue(capturedProgrammeAcronyms.containsAll(capturedProgrammeAcronyms));
        assertTrue(capturedStartDates.containsAll(capturedStartDates));
    }

    private static File getTestFile() throws IOException {
        File testFile = new File("src/test/resources/StudyPlan_DataTest.csv");
        testFile.getParentFile().mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(testFile), StandardCharsets.UTF_8))) {
            writer.write("ProgrammeName;ProgrammeAcronym;StartDate\n");
            writer.write("Computer Science;CS;2023-10-01\n");
            writer.write("Software Engineering;SE;2023-09-01\n");
            writer.write("Information Systems;IS;2023-08-01\n");
        }
        return testFile;
    }
}

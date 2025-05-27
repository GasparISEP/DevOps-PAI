package PAI.initializer;

import PAI.controller.US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class StudyPlanInitializerTest {

    @Mock
    private US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController controller;

    @InjectMocks
    private StudyPlanInitializer initializer;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        String csvContent = "ProgrammeName;Acronym;StartDate\n" +
                            "Programme 1;P1;2022-09-01\n" +
                            "Programme 2;P2;2023-09-01";

        Files.createDirectories(Paths.get("src/main/resources/"));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/StudyPlan_Data.csv"))) {
            writer.write(csvContent);
        }
    }

    @Test
    void shouldLoadAndRegisterStudyPlansFromCsvFile() throws Exception {
        // arrange
        when(controller.registerStudyPlan(anyString(), anyString(), anyString())).thenReturn(true);

        // act
        initializer.loadDataRegisterStudyPlan(controller).run();

        // assert
        verify(controller).registerStudyPlan("Programme 1", "P1", "2022-09-01");
        verify(controller).registerStudyPlan("Programme 2", "P2", "2023-09-01");
    }

    @Test
    void shouldContinueLoadingWhenARegistrationFails() throws Exception {
        // arrange
        when(controller.registerStudyPlan("Programme 1", "P1", "2022-09-01")).thenReturn(false);
        when(controller.registerStudyPlan("Programme 2", "P2", "2023-09-01")).thenReturn(true);

        // act
        initializer.loadDataRegisterStudyPlan(controller).run();

        // assert
        verify(controller).registerStudyPlan("Programme 1", "P1", "2022-09-01");
        verify(controller).registerStudyPlan("Programme 2", "P2", "2023-09-01");
    }
}

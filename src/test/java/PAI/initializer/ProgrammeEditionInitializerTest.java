package PAI.initializer;

import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.service.programmeEdition.IProgrammeEditionService;
import org.junit.jupiter.api.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProgrammeEditionInitializerTest {

    private ProgrammeEditionInitializer initializer;
    private IProgrammeEditionService service;
    private Path tempFile;

    @BeforeEach
    void setup() throws Exception {
        initializer = new ProgrammeEditionInitializer();
        service = mock(IProgrammeEditionService.class);
        tempFile = Files.createTempFile("programme-editions", ".csv");

        when(service.createProgrammeEdition(any(), any())).thenReturn(mock(ProgrammeEdition.class));
    }

    @AfterEach
    void cleanup() throws Exception {
        Files.deleteIfExists(tempFile);
    }

    @Test
    void shouldCreateAndSaveProgrammeEditionForValidCSVLines() throws Exception {
        // Arrange
        String csvContent = "Acronym,SchoolYearID\n" +
                "LEI," + UUID.randomUUID() + "\n" +
                "MIEI," + UUID.randomUUID() + "\n";
        Files.write(tempFile, csvContent.getBytes());

        // Act
        initializer.loadProgrammeEdition(service, tempFile.toString());

        // Assert
        verify(service, times(2)).createProgrammeEdition(any(ProgrammeID.class), any(SchoolYearID.class));
        verify(service, times(2)).saveProgrammeEdition(any(ProgrammeEdition.class));
    }

    @Test
    void shouldSkipInvalidLinesWithLessThanTwoParts() throws Exception {
        // Arrange
        String csvContent = "Acronym,SchoolYearID\n" +
                "LEI," + UUID.randomUUID() + "\n" +
                "InvalidLine\n";
        Files.write(tempFile, csvContent.getBytes());

        // Act
        initializer.loadProgrammeEdition(service, tempFile.toString());

        // Assert
        verify(service, times(1)).createProgrammeEdition(any(), any());
        verify(service, times(1)).saveProgrammeEdition(any());
    }

    @Test
    void shouldHandleExceptionWhileProcessingLineAndContinue() throws Exception {
        // Arrange
        String invalidUUID = "notAUUID";
        String csvContent = "Acronym,SchoolYearID\n" +
                "LEI," + invalidUUID + "\n" +
                "MIEI," + UUID.randomUUID() + "\n";
        Files.write(tempFile, csvContent.getBytes());

        // Act
        initializer.loadProgrammeEdition(service, tempFile.toString());

        // Assert
        verify(service, times(1)).createProgrammeEdition(any(), any());
        verify(service, times(1)).saveProgrammeEdition(any());
    }

    @Test
    void shouldHandleExceptionWhenReadingFile() {
        // Act & Assert
        Assertions.assertDoesNotThrow(() -> {
            initializer.loadProgrammeEdition(service, "non-existent-file.csv");
        });
    }
}

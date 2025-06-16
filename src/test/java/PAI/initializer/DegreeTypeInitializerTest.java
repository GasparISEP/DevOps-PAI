package PAI.initializer;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.controller.US10_IWantToConfigureDegreeTypesLevelsController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.mockito.Mockito.*;

class DegreeTypeInitializerTest {

    private DegreeTypeInitializer _initializer;
    private US10_IWantToConfigureDegreeTypesLevelsController _controllerDouble;
    private Path tempFile;

    @BeforeEach
    void setup() throws Exception {
        // Arrange
        _initializer = new DegreeTypeInitializer();
        _controllerDouble = mock(US10_IWantToConfigureDegreeTypesLevelsController.class);
        tempFile = Files.createTempFile("degree-types", ".csv");
    }

    @AfterEach
    void cleanup() throws Exception {
        Files.deleteIfExists(tempFile);
    }

    @Test
    void shouldRegisterDegreeTypeWithCorrectArgumentsWhenLoadingValidCSV() throws Exception {
        // Arrange
        String csvContent = "ECTS;Name;DegreeTypeID\n" +
                            "180;Computer Science;CS-01\n" +
                            "120;Mathematics;MATH-01\n";
        Files.write(tempFile, csvContent.getBytes());

        ArgumentCaptor<DegreeTypeID> idCaptor = ArgumentCaptor.forClass(DegreeTypeID.class);
        ArgumentCaptor<Name> nameCaptor = ArgumentCaptor.forClass(Name.class);
        ArgumentCaptor<MaxEcts> ectsCaptor = ArgumentCaptor.forClass(MaxEcts.class);

        // Act
        _initializer.loadDegreeType(_controllerDouble, tempFile.toString());

        // Assert
        verify(_controllerDouble, times(2))
                .registerDegreeTypeWithUUID(idCaptor.capture(), nameCaptor.capture(), ectsCaptor.capture());

        Assertions.assertTrue(
    idCaptor.getAllValues().get(0).getDTID().equals("CS-01") &&
            nameCaptor.getAllValues().get(0).getName().equals("Computer Science") &&
            ectsCaptor.getAllValues().get(0).getMaxEcts() == 180
        );
    }

    @Test
    void shouldRegisterSecondDegreeTypeWithCorrectArgumentsWhenLoadingValidCSV() throws Exception {
        // Arrange
        String csvContent = "ECTS;Name;DegreeTypeID\n" +
                            "180;Computer Science;CS-01\n" +
                            "120;Mathematics;MATH-01\n";
        Files.write(tempFile, csvContent.getBytes());

        ArgumentCaptor<DegreeTypeID> idCaptor = ArgumentCaptor.forClass(DegreeTypeID.class);
        ArgumentCaptor<Name> nameCaptor = ArgumentCaptor.forClass(Name.class);
        ArgumentCaptor<MaxEcts> ectsCaptor = ArgumentCaptor.forClass(MaxEcts.class);

        // Act
        _initializer.loadDegreeType(_controllerDouble, tempFile.toString());

        // Assert
        verify(_controllerDouble, times(2))
                .registerDegreeTypeWithUUID(idCaptor.capture(), nameCaptor.capture(), ectsCaptor.capture());

        Assertions.assertTrue(
            idCaptor.getAllValues().get(1).getDTID().equals("MATH-01") &&
            nameCaptor.getAllValues().get(1).getName().equals("Mathematics") &&
            ectsCaptor.getAllValues().get(1).getMaxEcts() == 120
        );
    }

    @Test
    void shouldSkipInvalidLinesWithLessThanThreeParts() throws Exception {
        // Arrange
        String csvContent = "ECTS;Name;DegreeTypeID\n" +
                            "180;Computer Science;CS-01\n" +
                            "InvalidLineWithoutEnoughParts\n" +
                            "120;Mathematics;MATH-01\n";
        Files.write(tempFile, csvContent.getBytes());

        // Act
        _initializer.loadDegreeType(_controllerDouble, tempFile.toString());

        // Assert
        verify(_controllerDouble, times(2)).registerDegreeTypeWithUUID(any(), any(), any());
    }

    @Test
    void shouldHandleExceptionWhileProcessingLineAndContinue() throws Exception {
        // Arrange
        String csvContent = "ECTS;Name;DegreeTypeID\n" +
                            "notANumber;Computer Science;CS-01\n" +
                            "120;Mathematics;MATH-01\n";
        Files.write(tempFile, csvContent.getBytes());

        // Act
        _initializer.loadDegreeType(_controllerDouble, tempFile.toString());

        // Assert
        verify(_controllerDouble, times(1)).registerDegreeTypeWithUUID(any(), any(), any());
    }

    @Test
    void shouldHandleExceptionWhenReadingFile() {
        // Arrange

        // Act & Assert
        Assertions.assertDoesNotThrow(() -> {
            _initializer.loadDegreeType(_controllerDouble, "non-existent-file.csv");
        });
    }
}
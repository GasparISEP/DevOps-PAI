package PAI.assembler.totalEnrolledStudentsInProgrammesByDepartmentAndSchoolYear;

import PAI.dto.totalEnrolledStudents.TotalEnrolledStudentsCommand;
import PAI.dto.totalEnrolledStudents.TotalEnrolledStudentsRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class TotalEnrolledStudentsAssemblerImplTest {

    @Test
    void shouldReturnNullWhenConvertFromRequestToCommand() {
        // Arrange
        ITotalEnrolledStudentsAssembler totalEnrolledStudentsAssembler = new TotalEnrolledStudentsAssemblerImpl();
        TotalEnrolledStudentsRequest request = mock(TotalEnrolledStudentsRequest.class);

        // Act
        TotalEnrolledStudentsCommand command = totalEnrolledStudentsAssembler.fromRequestToCommand(request);

        // Assert
        assertNull(command);
    }
}
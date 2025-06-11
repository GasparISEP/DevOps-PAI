package PAI.assembler.programmeEnrolment;

import PAI.VOs.ProgrammeEnrolmentGeneratedID;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentIdDTO;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeEnrolmentAssemblerTest {

    private final ProgrammeEnrolmentAssembler assembler = new ProgrammeEnrolmentAssembler();

    @Test
    void toProgrammeEnrolmentGeneratedID_shouldReturnValidObject() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        ProgrammeEnrolmentIdDTO dto = new ProgrammeEnrolmentIdDTO(uuid);

        // Act
        ProgrammeEnrolmentGeneratedID result = assembler.toProgrammeEnrolmentGeneratedID(dto);

        // Assert
        assertNotNull(result);
        assertEquals(uuid, result.getProgrammeEnrolmentGID());
    }
}
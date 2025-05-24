package PAI.assembler.programmeEditionEnrolment;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import PAI.VOs.ProgrammeEditionEnrolmentID;
import PAI.dto.programmeEditionEnrolment.ProgrammeEditionEnrolmentResponseDto;

public class ProgrammeEditionEnrolmentAssemblerImplTest {

    @Test
    public void shouldReturnProgrammeEditionEnrolmentID() throws Exception {
        // arrange
        ProgrammeEditionEnrolmentResponseDto programmeEditionEnrolmentResponseDto = new ProgrammeEditionEnrolmentResponseDto(
        1000001, 
        "Programme 1",
        "P1",
        "2024-2025",
        "550e8400-e29b-41d4-a716-446655440000"
        );
        ProgrammeEditionEnrolmentAssemblerImpl programmeEditionEnrolmentAssembler = new ProgrammeEditionEnrolmentAssemblerImpl();

        // act
        ProgrammeEditionEnrolmentID programmeEditionEnrolmentID = programmeEditionEnrolmentAssembler.toProgrammeEditionEnrolmentID(programmeEditionEnrolmentResponseDto);

        // assert
        assertEquals("Programme 1", programmeEditionEnrolmentID.getProgrammeEditionId().getProgrammeID().getProgrammeName());
    }   

    @Test
    public void shouldThrowExceptionWhenProgrammeEditionEnrolmentResponseDtoIsNull() {
        // arrange
        ProgrammeEditionEnrolmentResponseDto programmeEditionEnrolmentResponseDto = null;
        ProgrammeEditionEnrolmentAssemblerImpl programmeEditionEnrolmentAssembler = new ProgrammeEditionEnrolmentAssemblerImpl();

        // act
        Exception exception = assertThrows(Exception.class, () -> programmeEditionEnrolmentAssembler.toProgrammeEditionEnrolmentID(programmeEditionEnrolmentResponseDto));

        // assert
        assertEquals("Programme edition enrolment response dto is null", exception.getMessage());
    }
}

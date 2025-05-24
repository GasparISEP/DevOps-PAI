package PAI.dto.programmeEditionEnrolment;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ProgrammeEditionEnrolmentResponseDtoTest {

    @Test
    public void shouldCreateProgrammeEditionEnrolmentResponseDto() {
        // arrange
        int studentID = 1;
        String programmeName = "Programme 1";
        String programmeAcronym = "P1";
        String schoolYear = "2024-2025";

        // act
        ProgrammeEditionEnrolmentResponseDto programmeEditionEnrolmentResponseDto = new ProgrammeEditionEnrolmentResponseDto(studentID, programmeName, programmeAcronym, schoolYear);

        // assert
        assertEquals(studentID, programmeEditionEnrolmentResponseDto.studentID());
        assertEquals(programmeName, programmeEditionEnrolmentResponseDto.programmeName());
        assertEquals(programmeAcronym, programmeEditionEnrolmentResponseDto.programmeAcronym());
        assertEquals(schoolYear, programmeEditionEnrolmentResponseDto.schoolYear());
    }
}

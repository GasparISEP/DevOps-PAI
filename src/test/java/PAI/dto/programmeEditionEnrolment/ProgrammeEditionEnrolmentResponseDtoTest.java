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
        String schoolYearDescription = "2024-2025";
        String schoolYearID = "1234567890";

        // act
        ProgrammeEditionEnrolmentResponseDto programmeEditionEnrolmentResponseDto = new ProgrammeEditionEnrolmentResponseDto(studentID, programmeName, programmeAcronym, schoolYearDescription, schoolYearID);

        // assert
        assertEquals(studentID, programmeEditionEnrolmentResponseDto.studentID());
        assertEquals(programmeName, programmeEditionEnrolmentResponseDto.programmeName());
        assertEquals(programmeAcronym, programmeEditionEnrolmentResponseDto.programmeAcronym());
        assertEquals(schoolYearDescription, programmeEditionEnrolmentResponseDto.schoolYearDescription());
        assertEquals(schoolYearID, programmeEditionEnrolmentResponseDto.schoolYearID());
    }
}

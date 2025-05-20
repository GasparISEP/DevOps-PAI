package PAI.dto.programmeEnrolment;

import PAI.VOs.*;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeEnrolmentMapperTest {

    @Test
    void testToProgrammeEnrolment() {

        // arrange
        int studentIDValue = 1234567;
        String accessMethodUUID = "550e8400-e29b-41d4-a716-446655440000";
        String programmeNameValue = "Engenharia Informática";
        String programmeAcronymValue = "EI";
        LocalDate enrolmentDate = LocalDate.of(2025, 6, 1);

        ProgrammeEnrolmentRequestDTO dto = new ProgrammeEnrolmentRequestDTO(studentIDValue, accessMethodUUID, programmeNameValue, programmeAcronymValue, enrolmentDate);

        ProgrammeEnrolmentMapper mapper = new ProgrammeEnrolmentMapper();
        ProgrammeEnrolment result = mapper.toProgrammeEnrolment(dto);


        StudentID expectedStudentID = new StudentID(studentIDValue);
        AccessMethodID expectedAccessMethodID = new AccessMethodID(UUID.fromString(accessMethodUUID));
        NameWithNumbersAndSpecialChars expectedName = new NameWithNumbersAndSpecialChars(programmeNameValue);
        Acronym expectedAcronym = new Acronym(programmeAcronymValue);
        ProgrammeID expectedProgrammeID = new ProgrammeID(expectedName, expectedAcronym);
        Date expectedDate = new Date(enrolmentDate);

        //act
        ProgrammeEnrolment expected = new ProgrammeEnrolment(expectedStudentID, expectedAccessMethodID, expectedProgrammeID, expectedDate);

        //assert
        assertEquals(expected.getStudentID(), result.getStudentID());
        assertEquals(expected.getAccessMethodID(), result.getAccessMethodID());
        assertEquals(expected.getProgrammeID(), result.getProgrammeID());
        assertEquals(expected.getDate(), result.getDate());
    }
}
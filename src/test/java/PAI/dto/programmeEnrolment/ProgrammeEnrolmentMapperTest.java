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

        ProgrammeEnrolmentDTO dto = new ProgrammeEnrolmentDTO(studentIDValue, accessMethodUUID, programmeNameValue, programmeAcronymValue, enrolmentDate);

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

    @Test
    void testToProgrammeEnrolmentDTO() {
        // Arrange
        StudentID sid = new StudentID(1234567);
        AccessMethodID aid = new AccessMethodID(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"));
        NameWithNumbersAndSpecialChars nameVo = new NameWithNumbersAndSpecialChars("Engenharia Informática");
        Acronym acronymVo = new Acronym("EG");
        ProgrammeID pid = new ProgrammeID(nameVo, acronymVo);
        LocalDate localDate = LocalDate.of(2025, 5, 20);
        Date dateVo = new Date(localDate);


        ProgrammeEnrolment enrolment = new ProgrammeEnrolment(sid, aid, pid, dateVo);

        // Act
        ProgrammeEnrolmentMapper mapper = new ProgrammeEnrolmentMapper();
        ProgrammeEnrolmentResponseDTO dto = mapper.toProgrammeEnrolmentDTO(enrolment);

        // Assert
        assertEquals(1234567, dto.getStudentID());

    }

    @Test
    void testReturnStudentID() {

        // arrange
        int studentIDValue = 1234567;
        String accessMethodUUID = "550e8400-e29b-41d4-a716-446655440000";
        String programmeNameValue = "Engenharia Informática";
        String programmeAcronymValue = "EI";
        LocalDate enrolmentDate = LocalDate.of(2025, 6, 1);

        ProgrammeEnrolmentDTO dto = new ProgrammeEnrolmentDTO(studentIDValue, accessMethodUUID, programmeNameValue, programmeAcronymValue, enrolmentDate);
        ProgrammeEnrolmentMapper mapper = new ProgrammeEnrolmentMapper();


        //act
        StudentID expectedStudentID = mapper.toStudentID(dto);

        //assert
        assertEquals(studentIDValue, expectedStudentID.getUniqueNumber());
    }
}
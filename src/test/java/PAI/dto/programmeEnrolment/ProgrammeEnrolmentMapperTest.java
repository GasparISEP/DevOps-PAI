package PAI.dto.programmeEnrolment;

import PAI.VOs.*;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeEnrolmentMapperTest {


    @Test
    void testToProgrammeEnrolmentDTO() {
        // Arrange
        StudentID sid = new StudentID(1234567);
        AccessMethodID aid = new AccessMethodID(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"));
        Acronym acronymVo = new Acronym("EG");
        ProgrammeID pid = new ProgrammeID(acronymVo);
        LocalDate localDate = LocalDate.of(2025, 5, 20);
        Date dateVo = new Date(localDate);


        ProgrammeEnrolment enrolment = new ProgrammeEnrolment(sid, aid, pid, dateVo);

        // Act
        ProgrammeEnrolmentAssembler mapper = new ProgrammeEnrolmentAssembler();
        ProgrammeEnrolmentResponseDTO dto = mapper.toProgrammeEnrolmentDTO(enrolment);

        // Assert
        assertEquals(1234567, dto.getStudentID());

    }

    @Test
    void testReturnStudentID() {

        // arrange
        int studentIDValue = 1234567;
        String accessMethodUUID = "550e8400-e29b-41d4-a716-446655440000";
        String programmeAcronymValue = "EI";
        LocalDate enrolmentDate = LocalDate.of(2025, 6, 1);

        ProgrammeEnrolmentDTO dto = new ProgrammeEnrolmentDTO(studentIDValue, accessMethodUUID, programmeAcronymValue, enrolmentDate);
        ProgrammeEnrolmentAssembler mapper = new ProgrammeEnrolmentAssembler();


        //act
        StudentID expectedStudentID = mapper.toStudentID(dto);

        //assert
        assertEquals(studentIDValue, expectedStudentID.getUniqueNumber());
    }

    @Test
    void testReturnAccessMethodID() {

        // arrange
        int studentIDValue = 1234567;
        String accessMethodUUID = "550e8400-e29b-41d4-a716-446655440000";
        String programmeAcronymValue = "EI";
        LocalDate enrolmentDate = LocalDate.of(2025, 6, 1);

        ProgrammeEnrolmentDTO dto = new ProgrammeEnrolmentDTO(studentIDValue, accessMethodUUID, programmeAcronymValue, enrolmentDate);
        ProgrammeEnrolmentAssembler mapper = new ProgrammeEnrolmentAssembler();


        //act
        AccessMethodID expectedAccessMethodID = mapper.toAccessMethodID(dto);
        UUID expectedUuid = UUID.fromString(accessMethodUUID);

        //assert
        assertEquals(expectedUuid, expectedAccessMethodID.getAccessMethodID());    }

    @Test
    void testReturnProgrammeID() {

        // arrange
        int studentIDValue = 1234567;
        String accessMethodUUID = "550e8400-e29b-41d4-a716-446655440000";
        String programmeAcronymValue = "EI";
        LocalDate enrolmentDate = LocalDate.of(2025, 6, 1);
        ProgrammeID programmeIdExpected = new ProgrammeID(new Acronym(programmeAcronymValue));

        ProgrammeEnrolmentDTO dto = new ProgrammeEnrolmentDTO(studentIDValue, accessMethodUUID, programmeAcronymValue, enrolmentDate);
        ProgrammeEnrolmentAssembler mapper = new ProgrammeEnrolmentAssembler();

        //act
        ProgrammeID programmeID = mapper.toProgrammeID(dto);

        //assert
        assertEquals(programmeIdExpected, programmeID);
    }

    @Test
    void testReturnDateVO() {

        // arrange
        int studentIDValue = 1234567;
        String accessMethodUUID = "550e8400-e29b-41d4-a716-446655440000";
        String programmeAcronymValue = "EI";
        LocalDate enrolmentDate = LocalDate.of(2025, 6, 1);

        ProgrammeEnrolmentDTO dto = new ProgrammeEnrolmentDTO(studentIDValue, accessMethodUUID, programmeAcronymValue, enrolmentDate);
        ProgrammeEnrolmentAssembler mapper = new ProgrammeEnrolmentAssembler();

        //act
        Date date = mapper.toDateVO(dto);

        //assert
        assertEquals(enrolmentDate, date.getLocalDate());
    }
}


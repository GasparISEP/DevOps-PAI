package PAI.dto;

import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import PAI.persistence.datamodel.student.StudentIDDataModel;
import PAI.persistence.datamodel.studentGrade.StudentGradeDM;
import PAI.persistence.datamodel.studentGrade.StudentGradeIDDataModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProgrammeEnrolmentRequestDTOTest {

    @Test
    void shouldCreateValidDTO() {
        // Arrange
        int studentID = 1234567;
        String acessMethodID = "1";
        String programmeName = "1";
        String programmeAcronym = "LEI";
        LocalDate date = mock(LocalDate.class);


        // Act
        ProgrammeEnrolmentRequestDTO programmeEnrolmentRequestDTO = new ProgrammeEnrolmentRequestDTO(studentID,acessMethodID,programmeName,programmeAcronym,date);

        // Assert
        assertNotNull(programmeEnrolmentRequestDTO);
    }

}
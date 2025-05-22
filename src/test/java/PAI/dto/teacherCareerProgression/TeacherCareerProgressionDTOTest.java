package PAI.dto.teacherCareerProgression;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;


class TeacherCareerProgressionDTOTest {

    @Test
    void shouldCreatevalidDTO() {

        //Arrange
        String tcpid = "3f7bfe9a-d0e7-4b18-9b42-4b0a3f3e0c85";
        String date = "2025-05-22";
        String tcid = "Professor-Adjunto";
        int workingpercentage = 100;
        String teacherid = "ACM";

        //Act
        TeacherCareerProgressionDTO teacherCareerProgressionDTO = new TeacherCareerProgressionDTO(tcpid, date, tcid, workingpercentage, teacherid);

        //Assert
        assertNotNull(teacherCareerProgressionDTO);

    }

    @Test
    void testgetters() {
        //Arrange
        String tcpid = "3f7bfe9a-d0e7-4b18-9b42-4b0a3f3e0c85";
        String date = "2025-05-22";
        String tcid = "Professor-Adjunto";
        int workingpercentage = 100;
        String teacherid = "ACM";

        //Act
        TeacherCareerProgressionDTO teacherCareerProgressionDTO = new TeacherCareerProgressionDTO(tcpid, date, tcid, workingpercentage, teacherid);

        //Assert
        assertEquals(tcpid, teacherCareerProgressionDTO.getTcpid());
        assertEquals(date, teacherCareerProgressionDTO.getDate());
        assertEquals(tcid, teacherCareerProgressionDTO.getTcid());
        assertEquals(workingpercentage, teacherCareerProgressionDTO.getWorkingpercentage());
        assertEquals(teacherid, teacherCareerProgressionDTO.getTeacherid());
    }
}
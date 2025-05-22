package PAI.dto.teacherCareerProgression;

import PAI.VOs.*;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class TeacherCareerProgressionAssemblerTest {

    private TeacherCareerProgressionAssembler assembler;
    private TeacherCareerProgressionDTO dto;

    @BeforeEach
    void setUp() {
        assembler = new TeacherCareerProgressionAssembler();
        dto = new TeacherCareerProgressionDTO(
                "cb6349a2-a9ba-4434-be64-aeb7a6c54dcf",
                "22-05-2025",
                "b2c3d4e5-f6a7-4b9c-d0e1-f2a3b4c5d6e7",
                75,
                "ACM"
        );
    }

    @Test
    void testToTeacherCareerProgression() {
        TeacherCareerProgression progression = assembler.toTeacherCareerProgression(dto);

        assertNotNull(progression);
        assertEquals(dto.getTcpid(), progression.getID().toString());
        assertEquals(dto.getDate(), progression.getDate().toString());
        assertEquals(dto.getTcid(), progression.getTeacherCategoryID().toString());
        assertEquals(dto.getWorkingpercentage(), progression.getWorkingPercentage().getValue());
        assertEquals(dto.getTeacherid(), progression.getTeacherID().getTeacherAcronym().toString());
    }

    @Test
    void testToTeacherCareerProgressionDTO() {
        TeacherCareerProgression domain = assembler.toTeacherCareerProgression(dto);
        TeacherCareerProgressionResponseDTO responseDTO = assembler.toTeacherCareerProgressionDTO(domain);

        assertNotNull(responseDTO);
        assertEquals(dto.getTcpid(), responseDTO.getTcpid());
        assertEquals(dto.getDate(), responseDTO.getDate());
        assertEquals(dto.getTcid(), responseDTO.getTcid());
        assertEquals(dto.getWorkingpercentage(), responseDTO.getWorkingpercentage());
        assertEquals(dto.getTeacherid(), responseDTO.getTeacherid());
    }

    @Test
    void testToTeacherCareerProgressionID() {
        TeacherCareerProgressionID id = assembler.toTeacherCareerProgressionID(dto);
        assertEquals(dto.getTcpid(), id.toString());
    }

    @Test
    void testToDate() {
        Date date = assembler.todate(dto);
        assertEquals(dto.getDate(), date.toString());
    }

    @Test
    void testToTeacherCategoryID() {
        TeacherCategoryID categoryID = assembler.toTeacherCategoryID(dto);
        assertEquals(dto.getTcid(), categoryID.toString());
    }

    @Test
    void testToWorkingPercentage() {
        WorkingPercentage wp = assembler.toWorkingPercentage(dto);
        assertEquals(dto.getWorkingpercentage(), wp.getValue());
    }

    @Test
    void testToTeacherID() {
        TeacherID teacherID = assembler.toTeacherID(dto);
        assertEquals(dto.getTeacherid(), teacherID.getTeacherAcronym().toString());
    }
}

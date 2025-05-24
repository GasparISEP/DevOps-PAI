package PAI.dto.teacherCareerProgression;

import PAI.VOs.*;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    void testToDomain() {
        TeacherCareerProgression progression = assembler.toDomain(dto);

        assertNotNull(progression);
        assertEquals(dto.getTcpid(), progression.getID().toString());
        assertEquals(dto.getDate(), progression.getDate().toString());
        assertEquals(dto.getTcid(), progression.getTeacherCategoryID().toString());
        assertEquals(dto.getWorkingpercentage(), progression.getWorkingPercentage().getValue());
        assertEquals(dto.getTeacherid(), progression.getTeacherID().getTeacherAcronym().toString());
    }

    @Test
    void testToDomainDTO() {
        TeacherCareerProgression domain = assembler.toDomain(dto);
        TeacherWorkingPercentageUpdateDTO responseDTO = assembler.toTeacherWorkingPercentageUpdateDTO(domain);

        assertNotNull(responseDTO);
        assertEquals(dto.getDate(), responseDTO.getDate());
        assertEquals(dto.getWorkingpercentage(), responseDTO.getWorkingpercentage());
        assertEquals(dto.getTeacherid(), responseDTO.getTeacherid());
    }

    @Test
    void testToDomainID() {
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

    @Test
    void shouldConvertUpdatedDomainToDTO() {
        // Arrange
        TeacherCareerProgressionAssembler mapper = new TeacherCareerProgressionAssembler();

        Date date = mock(Date.class);
        TeacherID teacherID = mock(TeacherID.class);
        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);
        WorkingPercentage workingPercentage = mock(WorkingPercentage.class);
        TeacherCareerProgression teacherCareerProgression = mock(TeacherCareerProgression.class);

        when(teacherCareerProgression.getDate()).thenReturn(date);
        when(teacherCareerProgression.getTeacherID()).thenReturn(teacherID);
        when(teacherCareerProgression.getTeacherCategoryID()).thenReturn(teacherCategoryID);
        when(teacherCareerProgression.getWorkingPercentage()).thenReturn(workingPercentage);

        when(teacherID.toString()).thenReturn("teacher123");
        when(teacherCategoryID.toString()).thenReturn("12345678");
        when(workingPercentage.getValue()).thenReturn(80);
        when(date.toString()).thenReturn("12-02-2024");

        // Act
        UpdateTeacherCategoryResponseDTO dto = mapper.UpdateCategoryToDTO(teacherCareerProgression);

        // Assert
        assertNotNull(dto);
    }
}

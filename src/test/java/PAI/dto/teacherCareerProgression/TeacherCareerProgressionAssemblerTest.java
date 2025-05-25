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
        assertEquals(dto.getTcpID(), progression.getID().toString());
        assertEquals(dto.getDate(), progression.getDate().toString());
        assertEquals(dto.getTcID(), progression.getTeacherCategoryID().toString());
        assertEquals(dto.getWorkingPercentage(), progression.getWorkingPercentage().getValue());
        assertEquals(dto.getTeacherID(), progression.getTeacherID().getTeacherAcronym().toString());
    }

    @Test
    void testToDomainDTO() {
        TeacherCareerProgression domain = assembler.toDomain(dto);
        TeacherWorkingPercentageUpdateDTO responseDTO = assembler.toTeacherWorkingPercentageUpdateDTO(domain);

        assertNotNull(responseDTO);
        assertEquals(dto.getDate(), responseDTO.getDate());
        assertEquals(dto.getWorkingPercentage(), responseDTO.getWorkingPercentage());
        assertEquals(dto.getTeacherID(), responseDTO.getTeacherID());
    }

    @Test
    void testToDomainID() {
        TeacherCareerProgressionID id = assembler.toTeacherCareerProgressionID(dto);
        assertEquals(dto.getTcpID(), id.toString());
    }

    @Test
    void testToDate() {
        Date date = assembler.todate(dto);
        assertEquals(dto.getDate(), date.toString());
    }

    @Test
    void testToTeacherCategoryID() {
        TeacherCategoryID categoryID = assembler.toTeacherCategoryID(dto);
        assertEquals(dto.getTcID(), categoryID.toString());
    }

    @Test
    void testToWorkingPercentage() {
        WorkingPercentage wp = assembler.toWorkingPercentage(dto);
        assertEquals(dto.getWorkingPercentage(), wp.getValue());
    }

    @Test
    void testToTeacherID() {
        TeacherID teacherID = assembler.toTeacherID(dto);
        assertEquals(dto.getTeacherID(), teacherID.getTeacherAcronym().toString());
    }

    @Test
    void shouldReturnResponseDTO(){
        //arrange
        Date dateVO = mock(Date.class);
        TeacherID teacherIDVO = mock(TeacherID.class);
        TeacherAcronym acronym = mock(TeacherAcronym.class);
        TeacherCategoryID categoryID = mock(TeacherCategoryID.class);
        WorkingPercentage wp = mock(WorkingPercentage.class);

        TeacherCareerProgressionAssembler assembler = new TeacherCareerProgressionAssembler();
        TeacherCareerProgression teacherCareerProgression = mock(TeacherCareerProgression.class);
        String date = "02-02-2022";
        String teacherID = "ABV";
        String teacherCategoryId = "3f7bfe9a-d0e7-4b18-9b42-4b0a3f3e0c85";
        int workingPercentage = 20;

        when(teacherCareerProgression.getDate()).thenReturn(dateVO);
        when(dateVO.toString()).thenReturn(date);

        when(teacherCareerProgression.getTeacherID()).thenReturn(teacherIDVO);
        when(teacherIDVO.getTeacherAcronym()).thenReturn(acronym);
        when(acronym.getAcronym()).thenReturn(teacherID);


        when(teacherCareerProgression.getTeacherCategoryID()).thenReturn(categoryID);
        when(categoryID.toString()).thenReturn(teacherCategoryId);

        when(teacherCareerProgression.getWorkingPercentage()).thenReturn(wp);
        when(wp.getValue()).thenReturn(workingPercentage);

        UpdateTeacherCategoryResponseDTO result = assembler.toUpdateCategoryDTO(teacherCareerProgression);

        //assert
        assertNotNull(result);

    }

    @Test
    void shouldReturnCommand(){
        //arrange
        TeacherCareerProgressionAssembler assembler = new TeacherCareerProgressionAssembler();
        UpdateTeacherCategoryRequestDTO requestDTO = mock(UpdateTeacherCategoryRequestDTO.class);

        when(requestDTO.date()).thenReturn("22-02-2022");
        when(requestDTO.teacherID()).thenReturn("ABC");
        when(requestDTO.teacherCategoryID()).thenReturn("3f7bfe9a-d0e7-4b18-9b42-4b0a3f3e0c85");

        //act
        UpdateTeacherCategoryCommand result = assembler.toUpdateTeacherCategoryCommand(requestDTO);

        //assert
        assertNotNull(result);
    }
}

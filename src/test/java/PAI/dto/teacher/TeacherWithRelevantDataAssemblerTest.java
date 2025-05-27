package PAI.dto.teacher;

import PAI.VOs.Name;
import PAI.VOs.TeacherID;
import PAI.VOs.WorkingPercentage;
import PAI.domain.teacher.Teacher;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.dto.teacher.TeacherWithRelevantDataAssembler;
import PAI.dto.teacher.TeacherWithRelevantDataDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherWithRelevantDataAssemblerTest {
    @Test
    void testToDTOWithNameAndCategory() {
        //Arrange
        Teacher teacher = mock(Teacher.class);
        TeacherID teacherID = mock(TeacherID.class);
        Name teacherName = new Name("João Silva");

        TeacherCategory teacherCategory = mock(TeacherCategory.class);
        Name categoryName = new Name("Auxiliar");

        TeacherCareerProgression tcp = mock(TeacherCareerProgression.class);
        WorkingPercentage wp = new WorkingPercentage(100);

        when(teacher.getName()).thenReturn(teacherName);
        when(teacher.getTeacherID()).thenReturn(teacherID);
        when(teacherID.toString()).thenReturn("JS01");
        when(teacherCategory.getName()).thenReturn(categoryName);
        when(tcp.getWorkingPercentage()).thenReturn(wp);

        TeacherWithRelevantDataAssembler assembler = new TeacherWithRelevantDataAssembler();

        //Act
        TeacherWithRelevantDataDTO dto = assembler.toDTOWithNameAndCategory(teacher, teacherCategory, tcp);

        //Assert
        assertEquals("João Silva", dto.getName());
        assertEquals("JS01", dto.getAcronym());
        assertEquals("Auxiliar", dto.getCategory());
        assertEquals(100, dto.getWorkingPercentage());
    }
}
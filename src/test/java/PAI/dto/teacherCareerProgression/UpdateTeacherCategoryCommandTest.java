package PAI.dto.teacherCareerProgression;

import PAI.VOs.Date;
import PAI.VOs.TeacherCategoryID;
import PAI.VOs.TeacherID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UpdateTeacherCategoryCommandTest {
    @Test
    void shouldReturnConstructor(){
        //arrange
        Date date = mock(Date.class);
        TeacherID teacherID = mock(TeacherID.class);
        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);
        UpdateTeacherCategoryCommand command = new UpdateTeacherCategoryCommand(date,teacherID,teacherCategoryID);
        //assert
        assertNotNull(command);
        assertEquals(date, command.date());
        assertEquals(teacherID, command.teacherID());
        assertEquals(teacherCategoryID, command.teacherCategoryID());
    }

    @Test
    void shouldReturnExceptionWhenDateIsNull(){
        //arrange
        Date date = null;
        TeacherID teacherID = mock(TeacherID.class);
        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);

        //assert
        assertThrows(IllegalArgumentException.class, ()-> new UpdateTeacherCategoryCommand(date,teacherID,teacherCategoryID));
    }

    @Test
    void shouldReturnExceptionWhenTeacherIdIsNull(){
        //arrange
        Date date = mock(Date.class);
        TeacherID teacherID = null;
        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);

        //assert
        assertThrows(IllegalArgumentException.class, ()-> new UpdateTeacherCategoryCommand(date,teacherID,teacherCategoryID));
    }

    @Test
    void shouldReturnExceptionWhenTeacherCategoryIdIsNull(){
        //arrange
        Date date = mock(Date.class);
        TeacherID teacherID = mock(TeacherID.class);
        TeacherCategoryID teacherCategoryID = null;

        //assert
        assertThrows(IllegalArgumentException.class, ()-> new UpdateTeacherCategoryCommand(date,teacherID,teacherCategoryID));
    }

}
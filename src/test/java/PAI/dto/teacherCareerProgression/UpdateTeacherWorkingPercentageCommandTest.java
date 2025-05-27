package PAI.dto.teacherCareerProgression;

import PAI.VOs.Date;
import PAI.VOs.TeacherCategoryID;
import PAI.VOs.TeacherID;
import PAI.VOs.WorkingPercentage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UpdateTeacherWorkingPercentageCommandTest {

        @Test
        void shouldReturnConstructor(){
            //arrange
            Date date = mock(Date.class);
            TeacherID teacherID = mock(TeacherID.class);
            WorkingPercentage workingPercentage = mock(WorkingPercentage.class);
            UpdateTeacherWorkingPercentageCommand command = new UpdateTeacherWorkingPercentageCommand(date,teacherID,workingPercentage);
            //assert
            assertNotNull(command);
            assertEquals(date, command.date());
            assertEquals(teacherID, command.teacherID());
            assertEquals(workingPercentage, command.workingPercentage());
        }

        @Test
        void shouldReturnExceptionWhenDateIsNull(){
            //arrange
            Date date = null;
            TeacherID teacherID = mock(TeacherID.class);
            WorkingPercentage workingPercentage = mock(WorkingPercentage.class);

            //assert
            assertThrows(IllegalArgumentException.class, ()-> new UpdateTeacherWorkingPercentageCommand(date,teacherID,workingPercentage));
        }

        @Test
        void shouldReturnExceptionWhenTeacherIdIsNull(){
            //arrange
            Date date = mock(Date.class);
            TeacherID teacherID = null;
            WorkingPercentage workingPercentage = mock(WorkingPercentage.class);

            //assert
            assertThrows(IllegalArgumentException.class, ()-> new UpdateTeacherWorkingPercentageCommand(date,teacherID,workingPercentage));
        }

        @Test
        void shouldReturnExceptionWhenWorkingPercentageIsNull(){
            //arrange
            Date date = mock(Date.class);
            TeacherID teacherID = mock(TeacherID.class);
            WorkingPercentage workingPercentage = null;

            //assert
            assertThrows(IllegalArgumentException.class, ()-> new UpdateTeacherWorkingPercentageCommand(date,teacherID,workingPercentage));
        }

}
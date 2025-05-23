package PAI.dto.studentGrade;

import PAI.VOs.CourseEditionID;
import PAI.VOs.Date;
import PAI.VOs.Grade;
import PAI.VOs.StudentID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.mock;
class GradeAStudentCommandTest {

    private Grade gradeDouble;
    private Date dateDouble;
    private StudentID studentIDDouble;
    private CourseEditionID courseEditionIDDouble;

    private void createDoubles() {
        gradeDouble = mock(Grade.class);
        dateDouble = mock(Date.class);
        studentIDDouble = mock(StudentID.class);
        courseEditionIDDouble = mock(CourseEditionID.class);
    }

    @Test
    void shouldCreateGradeAStudentCommand() {
        //Arrange
        createDoubles();

        //Act
        GradeAStudentCommand dto = new GradeAStudentCommand(gradeDouble, dateDouble, studentIDDouble, courseEditionIDDouble);

        //Assert
        assertNotNull(dto);
    }

    @Test
    void shouldGetGradeThroughGetter() {
        //Arrange
        createDoubles();

        //Act
        GradeAStudentCommand dto = new GradeAStudentCommand(gradeDouble, dateDouble, studentIDDouble, courseEditionIDDouble);

        //Assert
        assertEquals(dto.getGrade(), gradeDouble);
    }

    @Test
    void shouldGetDateThroughGetter() {
        //Arrange
        createDoubles();

        //Act
        GradeAStudentCommand dto = new GradeAStudentCommand(gradeDouble, dateDouble, studentIDDouble, courseEditionIDDouble);

        //Assert
        assertEquals(dto.getDate(), dateDouble);
    }

    @Test
    void shouldGetStudentIDThroughGetter() {
        //Arrange
        createDoubles();

        //Act
        GradeAStudentCommand dto = new GradeAStudentCommand(gradeDouble, dateDouble, studentIDDouble, courseEditionIDDouble);

        //Assert
        assertEquals(dto.getStudentID(), studentIDDouble);
    }

    @Test
    void shouldGetCourseEditionThroughGetter() {
        //Arrange
        createDoubles();

        //Act
        GradeAStudentCommand dto = new GradeAStudentCommand(gradeDouble, dateDouble, studentIDDouble, courseEditionIDDouble);

        //Assert
        assertEquals(dto.getCourseEditionID(), courseEditionIDDouble);
    }
}
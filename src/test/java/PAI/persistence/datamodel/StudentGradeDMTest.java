package PAI.persistence.datamodel;

import PAI.VOs.CourseEditionID;
import PAI.domain.course.Course;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StudentGradeDMTest {

    @Test
    void shouldCreateValidStudentGradeConstructor() throws Exception {
        // Arrange
        LocalDate localDate = mock(LocalDate.class);
        CourseEditionIDDataModel courseEditionID = mock(CourseEditionIDDataModel.class);
        StudentIDDataModel studentIDDataModel = mock(StudentIDDataModel.class);

        // Act
        StudentGradeDM studentGrade1 = new StudentGradeDM(1L, 10, localDate, courseEditionID,studentIDDataModel);
        // Assert
        assertNotNull(studentGrade1);
    }

    @Test
    void shouldCreateValidStudentGradeNoArgsConstructor() throws Exception {
        // Arrange

        // Act
        StudentGradeDM studentGrade1 = new StudentGradeDM();
        // Assert
        assertNotNull(studentGrade1);
    }

    @Test
    void shouldCreateValidStudentGrade() throws Exception {
        // Arrange
        LocalDate localDate = mock(LocalDate.class);
        CourseEditionIDDataModel courseEditionID = mock(CourseEditionIDDataModel.class);
        StudentIDDataModel studentIDDataModel = mock(StudentIDDataModel.class);


        // Act
        StudentGradeDM studentGrade1 = new StudentGradeDM(1L, 10, localDate, courseEditionID,studentIDDataModel);
        double result = studentGrade1.get_grade();
        // Assert
        assertEquals(studentGrade1.get_grade(), result);
    }

    @Test
    void shouldCreateValidStudentGradeID() throws Exception {
        // Arrange
        LocalDate localDate = mock(LocalDate.class);
        CourseEditionIDDataModel courseEditionID = mock(CourseEditionIDDataModel.class);
        StudentIDDataModel studentIDDataModel = mock(StudentIDDataModel.class);

        // Act
        StudentGradeDM studentGrade1 = new StudentGradeDM(1L, 10, localDate, courseEditionID,studentIDDataModel);
        long result = studentGrade1.getId();
        // Assert
        assertEquals(studentGrade1.getId(), result);
    }

    @Test
    void shouldCreateValidStudentDate() throws Exception {
        // Arrange
        LocalDate localDate = mock(LocalDate.class);
        CourseEditionIDDataModel courseEditionID = mock(CourseEditionIDDataModel.class);
        StudentIDDataModel studentIDDataModel = mock(StudentIDDataModel.class);

        // Act
        StudentGradeDM studentGrade1 = new StudentGradeDM(1L, 10, localDate, courseEditionID,studentIDDataModel);
        LocalDate result = studentGrade1.get_date();
        // Assert
        assertEquals(studentGrade1.get_date(), result);
    }



    @Test
    void shouldCreateValidStudentID() throws Exception {
        // Arrange
        LocalDate localDate = mock(LocalDate.class);
        CourseEditionIDDataModel courseEditionID = mock(CourseEditionIDDataModel.class);
        StudentIDDataModel studentIDDataModel = mock(StudentIDDataModel.class);

        // Act
        StudentGradeDM studentGrade1 = new StudentGradeDM(1L, 10, localDate, courseEditionID,studentIDDataModel);
        StudentIDDataModel result = studentGrade1.getStudentId();
        // Assert
        assertEquals(studentGrade1.getStudentId(), result);
    }

}
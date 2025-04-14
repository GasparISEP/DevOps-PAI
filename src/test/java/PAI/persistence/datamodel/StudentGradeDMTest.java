package PAI.persistence.datamodel;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StudentGradeDMTest {

    @Test
    void shouldCreateValidStudentGradeConstructor() throws Exception {
        // Arrange
        LocalDate localDate = mock(LocalDate.class);

        // Act
        StudentGradeDM studentGrade1 = new StudentGradeDM(1L, 10, localDate, "1",0);
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

        // Act
        StudentGradeDM studentGrade1 = new StudentGradeDM(1L, 10, localDate, "1",0);
        double result = studentGrade1.get_grade();
        // Assert
        assertEquals(studentGrade1.get_grade(), result);
    }

    @Test
    void shouldCreateValidStudentGradeID() throws Exception {
        // Arrange
        LocalDate localDate = mock(LocalDate.class);

        // Act
        StudentGradeDM studentGrade1 = new StudentGradeDM(1L, 10, localDate, "1",0);
        long result = studentGrade1.getId();
        // Assert
        assertEquals(studentGrade1.getId(), result);
    }

    @Test
    void shouldCreateValidStudentDate() throws Exception {
        // Arrange
        LocalDate localDate = mock(LocalDate.class);

        // Act
        StudentGradeDM studentGrade1 = new StudentGradeDM(1L, 10, localDate, "1",0);
        LocalDate result = studentGrade1.get_date();
        // Assert
        assertEquals(studentGrade1.get_date(), result);
    }

    @Test
    void shouldCreateValidStudentCourseEdition() throws Exception {
        // Arrange
        LocalDate localDate = mock(LocalDate.class);

        // Act
        StudentGradeDM studentGrade1 = new StudentGradeDM(1L, 10, localDate, "1",0);
        String result = studentGrade1.getCourseEditionID();
        // Assert
        assertEquals(studentGrade1.getCourseEditionID(), result);
    }

    @Test
    void shouldCreateValidStudentID() throws Exception {
        // Arrange
        LocalDate localDate = mock(LocalDate.class);

        // Act
        StudentGradeDM studentGrade1 = new StudentGradeDM(1L, 10, localDate, "1",0);
        int result = studentGrade1.getStudentId();
        // Assert
        assertEquals(studentGrade1.getStudentId(), result);
    }

}
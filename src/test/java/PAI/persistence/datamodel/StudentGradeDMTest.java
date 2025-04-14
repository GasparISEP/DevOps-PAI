package PAI.persistence.datamodel;

import PAI.VOs.CourseEditionID;
import PAI.VOs.Date;
import PAI.VOs.Grade;
import PAI.VOs.StudentID;
import PAI.domain.StudentGrade;
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

}
package PAI.persistence.datamodel;

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
        StudentGradeIDDataModel studentGradeIDDataModel = mock(StudentGradeIDDataModel.class);

        // Act
        StudentGradeDM studentGrade1 = new StudentGradeDM(studentGradeIDDataModel, 10, localDate, courseEditionID,studentIDDataModel);
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
        StudentGradeIDDataModel studentGradeIDDataModel = mock(StudentGradeIDDataModel.class);



        // Act
        StudentGradeDM studentGrade1 = new StudentGradeDM(studentGradeIDDataModel, 10, localDate, courseEditionID,studentIDDataModel);
        double result = studentGrade1.getGrade();
        // Assert
        assertEquals(studentGrade1.getGrade(), result);
    }

    @Test
    void shouldCreateValidStudentGradeID() throws Exception {
        // Arrange
        LocalDate localDate = mock(LocalDate.class);
        CourseEditionIDDataModel courseEditionID = mock(CourseEditionIDDataModel.class);
        StudentIDDataModel studentIDDataModel = mock(StudentIDDataModel.class);
        StudentGradeIDDataModel studentGradeIDDataModel = mock(StudentGradeIDDataModel.class);

        // Act
        StudentGradeDM studentGrade1 = new StudentGradeDM(studentGradeIDDataModel, 10, localDate, courseEditionID,studentIDDataModel);

        // Assert
        assertEquals(studentGrade1.getId(), studentGradeIDDataModel);
    }

    @Test
    void shouldCreateValidStudentDate() throws Exception {
        // Arrange
        LocalDate localDate = mock(LocalDate.class);
        CourseEditionIDDataModel courseEditionID = mock(CourseEditionIDDataModel.class);
        StudentIDDataModel studentIDDataModel = mock(StudentIDDataModel.class);
        StudentGradeIDDataModel studentGradeIDDataModel = mock(StudentGradeIDDataModel.class);


        // Act
        StudentGradeDM studentGrade1 = new StudentGradeDM(studentGradeIDDataModel, 10, localDate, courseEditionID,studentIDDataModel);
        LocalDate result = studentGrade1.getDate();
        // Assert
        assertEquals(studentGrade1.getDate(), result);
    }



    @Test
    void shouldCreateValidStudentID() throws Exception {
        // Arrange
        LocalDate localDate = mock(LocalDate.class);
        CourseEditionIDDataModel courseEditionID = mock(CourseEditionIDDataModel.class);
        StudentIDDataModel studentIDDataModel = mock(StudentIDDataModel.class);
        StudentGradeIDDataModel studentGradeIDDataModel = mock(StudentGradeIDDataModel.class);


        // Act
        StudentGradeDM studentGrade1 = new StudentGradeDM(studentGradeIDDataModel, 10, localDate, courseEditionID,studentIDDataModel);
        StudentIDDataModel result = studentGrade1.getStudentId();
        // Assert
        assertEquals(studentGrade1.getStudentId(), result);
    }

    @Test
    void shouldCreateValidCourseEditionID() throws Exception {
        // Arrange
        LocalDate localDate = mock(LocalDate.class);
        CourseEditionIDDataModel courseEditionID = mock(CourseEditionIDDataModel.class);
        StudentIDDataModel studentIDDataModel = mock(StudentIDDataModel.class);
        StudentGradeIDDataModel studentGradeIDDataModel = mock(StudentGradeIDDataModel.class);


        // Act
        StudentGradeDM studentGrade1 = new StudentGradeDM(studentGradeIDDataModel, 10, localDate, courseEditionID,studentIDDataModel);
        CourseEditionIDDataModel result = studentGrade1.getCourseEditionID();
        // Assert
        assertEquals(studentGrade1.getCourseEditionID(), result);
    }

}
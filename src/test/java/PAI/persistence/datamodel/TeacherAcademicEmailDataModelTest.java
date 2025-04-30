package PAI.persistence.datamodel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherAcademicEmailDataModelTest {

    @Test
    void testEmptyConstructor () {
        // Arrange
        TeacherAcademicEmailDataModel teacherAcademicEmailDM = new TeacherAcademicEmailDataModel();

        // Assert
        assertNotNull(teacherAcademicEmailDM);
    }

    @Test
    void testConstructor () {
        // Arrange + Act
        String teacherAcademicEmail = "jorgecarvalho95";

        TeacherAcademicEmailDataModel teacherAcademicEmailDataModel = new TeacherAcademicEmailDataModel(teacherAcademicEmail);

        // Assert
        assertNotNull(teacherAcademicEmailDataModel);
    }


    @Test
    void testGetTeacherAcademicEmailDataModel() {
        // Arrange
        String teacherAcademicEmail = "jorgecarvalho95";

        TeacherAcademicEmailDataModel teacherAcademicEmailDataModel = new TeacherAcademicEmailDataModel(teacherAcademicEmail);

        // Act
        String result = teacherAcademicEmailDataModel.getTeacherAcademicEmail();

        // Assert
        assertEquals("jorgecarvalho95", result);
    }
}
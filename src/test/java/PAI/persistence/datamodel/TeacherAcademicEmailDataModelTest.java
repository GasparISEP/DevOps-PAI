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
        String emailDomain = "@isep.ipp.pt";
        String teacherAcademicEmail = "jorgecarvalho95";

        TeacherAcademicEmailDataModel teacherAcademicEmailDataModel = new TeacherAcademicEmailDataModel(emailDomain, teacherAcademicEmail);

        // Assert
        assertNotNull(teacherAcademicEmailDataModel);
    }

    @Test
    void testGetEmailDomain() {
        // Arrange
        String emailDomain = "@isep.ipp.pt";
        String teacherAcademicEmail = "jorgecarvalho95";

        TeacherAcademicEmailDataModel teacherAcademicEmailDataModel = new TeacherAcademicEmailDataModel(emailDomain, teacherAcademicEmail);

        // Act
        String result = teacherAcademicEmailDataModel.getEmailDomain();

        // Assert
        assertEquals("@isep.ipp.pt", result);
    }

    @Test
    void testGetTeacherAcademicEmailDataModel() {
        // Arrange
        String emailDomain = "@isep.ipp.pt";
        String teacherAcademicEmail = "jorgecarvalho95";

        TeacherAcademicEmailDataModel teacherAcademicEmailDataModel = new TeacherAcademicEmailDataModel(emailDomain, teacherAcademicEmail);

        // Act
        String result = teacherAcademicEmailDataModel.getTeacherAcademicEmail();

        // Assert
        assertEquals("jorgecarvalho95", result);
    }
}
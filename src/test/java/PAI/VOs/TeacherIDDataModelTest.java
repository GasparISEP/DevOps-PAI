package PAI.VOs;

import PAI.persistence.datamodel.TeacherIDDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherIDDataModelTest {

    @Test
    void shouldCreateTeacherIDDataModelWithNoParams() {
        // Assert
        TeacherIDDataModel teacherIDDataModel = new TeacherIDDataModel();

        // Act + Assert
        assertNotNull(teacherIDDataModel);
    }

    @Test
    void shouldCreateTeacherIDDataModelWithValidParams() {
        // Assert
        String teacherAcronym = "teacherID";
        String nif = "1234123";
        String country = "Portugal";
        TeacherIDDataModel teacherIDDataModel = new TeacherIDDataModel(teacherAcronym, nif, country);

        // Arrange
        String result = teacherIDDataModel.toString();

        //Assert
        assertNotNull(result);
    }

    @Test
    void shouldGetTeacherAcronymThroughGetterWithValidParams() {
        // Assert
        String teacherAcronym = "teacherID";
        String nif = "1234123";
        String country = "Portugal";

        // Act
        TeacherIDDataModel teacherIDDataModel = new TeacherIDDataModel(teacherAcronym, nif, country);

        // Arrange
        String result = teacherIDDataModel.getTeacherAcronym();

        //Assert
        assertEquals("teacherID", result);
    }

    @Test
    void shouldReturnNIFThroughGetterWithValidParams() {
        // Assert
        String teacherAcronym = "teacherID";
        String nif = "1234123";
        String country = "Portugal";

        // Act
        TeacherIDDataModel teacherIDDataModel = new TeacherIDDataModel(teacherAcronym, nif, country);

        // Arrange
        String result = teacherIDDataModel.getNIF();

        //Assert
        assertEquals("1234123", result);
    }

    @Test
    void shouldReturnCountryThroughGetterWithValidParams() {
        // Assert
        String teacherAcronym = "teacherID";
        String nif = "1234123";
        String country = "Portugal";

        // Act
        TeacherIDDataModel teacherIDDataModel = new TeacherIDDataModel(teacherAcronym, nif, country);

        // Arrange
        String result = teacherIDDataModel.getCountry();

        //Assert
        assertEquals("Portugal", result);
    }
}
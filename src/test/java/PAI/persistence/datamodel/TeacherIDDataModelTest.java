package PAI.persistence.datamodel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherIDDataModelTest {

    @Test
    void shouldCreateTeacherIDDataModelWithNoParams() {
        // Arrange
        TeacherIDDataModel teacherIDDataModel = new TeacherIDDataModel();

        // Act + Assert
        assertNotNull(teacherIDDataModel);
    }

    @Test
    void shouldCreateTeacherIDDataModelWithValidParams() {
        // Arrange
        TeacherIDDataModel teacherIDDataModel = new TeacherIDDataModel("teachers", "1234123", "Portugal");

        // Act + Assert
        assertNotNull(teacherIDDataModel);
    }

    @Test
    void shouldGetTeacherAcronymThroughGetterWithValidParams() {
        // Arrange
        TeacherIDDataModel teacherIDDataModel = new TeacherIDDataModel("teachers", "1234123", "Portugal");

        // Act
        String result = teacherIDDataModel.getTeacherAcronym();

        // Assert
        assertEquals("teachers", result);
    }

    @Test
    void shouldReturnNIFThroughGetterWithValidParams() {
        // Arrange
        TeacherIDDataModel teacherIDDataModel = new TeacherIDDataModel("teachers", "1234123", "Portugal");

        // Act
        String result = teacherIDDataModel.getNIF();

        // Assert
        assertEquals("1234123", result);
    }

    @Test
    void shouldReturnCountryThroughGetterWithValidParams() {
        // Arrange
        TeacherIDDataModel teacherIDDataModel = new TeacherIDDataModel("teachers", "1234123", "Portugal");

        // Act
        String result = teacherIDDataModel.getCountry();

        // Assert
        assertEquals("Portugal", result);
    }

    @Test
    void shouldReturnTrueForSameObject() {
        // Arrange
        TeacherIDDataModel teacherIDDataModel = new TeacherIDDataModel("teachers", "1234123", "Portugal");

        // Act
        boolean result = teacherIDDataModel.equals(teacherIDDataModel);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueForSameAttributesObject() {
        // Arrange
        TeacherIDDataModel teacherIDDataModel = new TeacherIDDataModel("teachers", "1234123", "Portugal");
        TeacherIDDataModel teacherIDDataModel2 = new TeacherIDDataModel("teachers", "1234123", "Portugal");

        // Act
        boolean result = teacherIDDataModel.equals(teacherIDDataModel2);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseForDifferentObject() {
        // Arrange
        TeacherIDDataModel teacherIDDataModel = new TeacherIDDataModel("teachers", "1234123", "Portugal");
        String s = "123";

        // Act
        boolean result = teacherIDDataModel.equals(s);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseDifferentTeacherAcronymObject() {
        // Arrange
        TeacherIDDataModel teacherIDDataModel = new TeacherIDDataModel("teachers", "1234123", "Portugal");
        TeacherIDDataModel teacherIDDataModel2 = new TeacherIDDataModel("teachersss", "1234123", "Portugal");

        // Act
        boolean result = teacherIDDataModel.equals(teacherIDDataModel2);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseDifferentNIFObject() {
        // Arrange
        TeacherIDDataModel teacherIDDataModel = new TeacherIDDataModel("teachers", "1234123", "Portugal");
        TeacherIDDataModel teacherIDDataModel2 = new TeacherIDDataModel("teachers", "123412123443", "Portugal");

        // Act
        boolean result = teacherIDDataModel.equals(teacherIDDataModel2);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseDifferentCountryObject() {
        // Arrange
        TeacherIDDataModel teacherIDDataModel = new TeacherIDDataModel("teachers", "1234123", "Portugal");
        TeacherIDDataModel teacherIDDataModel2 = new TeacherIDDataModel("teachers", "1234123", "Spain");

        // Act
        boolean result = teacherIDDataModel.equals(teacherIDDataModel2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldBeEqualIfObjectsHaveSameHashCode() {
        // Arrange
        TeacherIDDataModel teacherIDDataModel = new TeacherIDDataModel("teachers", "1234123", "Portugal");
        TeacherIDDataModel teacherIDDataModel2 = new TeacherIDDataModel("teachers", "1234123", "Portugal");

        // Act + Assert
        assertEquals(teacherIDDataModel.hashCode(), teacherIDDataModel2.hashCode());
    }

    @Test
    void shouldNotBeEqualIfObjectsHaveSameHashCode() {
        // Arrange
        TeacherIDDataModel teacherIDDataModel = new TeacherIDDataModel("teachers", "1234123", "Portugal");
        TeacherIDDataModel teacherIDDataModel2 = new TeacherIDDataModel("teachers", "1234123", "Portugal");

        // Act + Assert
        assertEquals(teacherIDDataModel.hashCode(), teacherIDDataModel2.hashCode());
    }
}
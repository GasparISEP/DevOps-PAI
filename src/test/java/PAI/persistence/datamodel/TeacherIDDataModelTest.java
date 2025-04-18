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
        TeacherIDDataModel teacherIDDataModel = new TeacherIDDataModel("teachers");

        // Act + Assert
        assertNotNull(teacherIDDataModel);
    }

    @Test
    void shouldGetTeacherAcronymThroughGetterWithValidParams() {
        // Arrange
        TeacherIDDataModel teacherIDDataModel = new TeacherIDDataModel("teachers");

        // Act
        String result = teacherIDDataModel.getTeacherAcronym();

        // Assert
        assertEquals("teachers", result);
    }

    @Test
    void shouldReturnTrueForSameObject() {
        // Arrange
        TeacherIDDataModel teacherIDDataModel = new TeacherIDDataModel("teachers");

        // Act
        boolean result = teacherIDDataModel.equals(teacherIDDataModel);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueForSameAttributesObject() {
        // Arrange
        TeacherIDDataModel teacherIDDataModel = new TeacherIDDataModel("teachers");
        TeacherIDDataModel teacherIDDataModel2 = new TeacherIDDataModel("teachers");

        // Act
        boolean result = teacherIDDataModel.equals(teacherIDDataModel2);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseForDifferentObject() {
        // Arrange
        TeacherIDDataModel teacherIDDataModel = new TeacherIDDataModel("teachers");
        String s = "123";

        // Act
        boolean result = teacherIDDataModel.equals(s);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseDifferentTeacherAcronymObject() {
        // Arrange
        TeacherIDDataModel teacherIDDataModel = new TeacherIDDataModel("teachers");
        TeacherIDDataModel teacherIDDataModel2 = new TeacherIDDataModel("teachersss");

        // Act
        boolean result = teacherIDDataModel.equals(teacherIDDataModel2);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldBeEqualIfObjectsHaveSameHashCode() {
        // Arrange
        TeacherIDDataModel teacherIDDataModel = new TeacherIDDataModel("teachers");
        TeacherIDDataModel teacherIDDataModel2 = new TeacherIDDataModel("teachers");

        // Act + Assert
        assertEquals(teacherIDDataModel.hashCode(), teacherIDDataModel2.hashCode());
    }

    @Test
    void shouldNotBeEqualIfObjectsHaveSameHashCode() {
        // Arrange
        TeacherIDDataModel teacherIDDataModel = new TeacherIDDataModel("teachers");
        TeacherIDDataModel teacherIDDataModel2 = new TeacherIDDataModel("teachers");

        // Act + Assert
        assertEquals(teacherIDDataModel.hashCode(), teacherIDDataModel2.hashCode());
    }
}
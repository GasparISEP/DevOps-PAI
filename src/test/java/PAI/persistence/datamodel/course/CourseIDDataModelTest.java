package PAI.persistence.datamodel.course;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CourseIDDataModelTest {

    @Test
    void shouldCreateCourseIDDataModel() {
        // Arrange + Act
        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel();

        // Assert
        assertNotNull(CourseIDDataModel);
    }

    @Test
    void shouldCreateCourseIDDataModelWithCourseID() {
        // Arrange
        String acronym = "DSOFT";
        String name = "Software Development";
        // Act
        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(acronym, name);

        // Assert
        assertNotNull(CourseIDDataModel);
    }

    @Test
    void shouldReturnTrueIfObjectsAreEquals() {
        // Arrange
        String acronym = "DSOFT";
        String name = "Software Development";
        // Act
        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(acronym, name);
        Object CourseIDDataModel2 = CourseIDDataModel;

        // Act
        boolean result = CourseIDDataModel.equals(CourseIDDataModel2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnID() {
        // Arrange
        String acronym = "DSOFT";
        String name = "Software Development";
        // Act
        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(acronym, name);

        // Act
        String result = CourseIDDataModel.getId();

        // Assert
        assertNotNull(result);
    }

    @Test
    void shouldReturnTrueIfCourseIDDataModelEquals() {
        // Arrange
        String acronym = "DSOFT";
        String name = "Software Development";

        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(acronym, name);
        CourseIDDataModel CourseIDDataModel2 = new CourseIDDataModel(acronym, name);

        // Act
        boolean result = CourseIDDataModel.equals(CourseIDDataModel2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfCourseIDDataModelNotEquals() {
        // Arrange
        // Arrange
        String acronym = "DSOFT";
        String name = "Software Development";

        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(acronym, name);

        String acronym2 = "DSOFTII";
        String name2 = "Software Development II";

        CourseIDDataModel CourseIDDataModel2 = new CourseIDDataModel(acronym2, name2);

        // Act
        boolean result = CourseIDDataModel.equals(CourseIDDataModel2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfCourseIDDataModelNotEqualsWithNull() {
        // Arrange
        String acronym = "DSOFT";
        String name = "Software Development";

        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(acronym, name);
        CourseIDDataModel CourseIDDataModel2 = null;

        // Act
        boolean result = CourseIDDataModel.equals(CourseIDDataModel2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfCourseIDDataModelNotEqualsWithDifferentClass() {
        // Arrange
        String acronym = "DSOFT";
        String name = "Software Development";

        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(acronym, name);
        Object CourseIDDataModel2 = new Object();

        // Act
        boolean result = CourseIDDataModel.equals(CourseIDDataModel2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfCourseIDDataModelEqualsWithSameAcronym() {
        // Arrange
        String acronym = "DSOFT";
        String name = "Software Development";

        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(acronym, name);

        String acronym2 = "DSOFT";
        String name2 = "Software Development II";

        CourseIDDataModel CourseIDDataModel2 = new CourseIDDataModel(acronym2, name2);

        // Act
        boolean result = CourseIDDataModel.equals(CourseIDDataModel2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfCourseIDDataModelEqualsWithSameName() {
        // Arrange
        String acronym = "DSOFT";
        String name = "Software Development";

        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(acronym, name);

        String acronym2 = "DSOFTI";
        String name2 = "Software Development";

        CourseIDDataModel CourseIDDataModel2 = new CourseIDDataModel(acronym2, name2);

        // Act
        boolean result = CourseIDDataModel.equals(CourseIDDataModel2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfCourseIDDataModelNotEqualsWithDifferentObject() {
        // Arrange
        String acronym = "DSOFT";
        String name = "Software Development";

        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(acronym, name);
        CourseIDDataModel CourseIDDataModel2 = new CourseIDDataModel();

        // Act
        boolean result = CourseIDDataModel.equals(CourseIDDataModel2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnHashCodeNotNull() {
        // Arrange
        String acronym = "DSOFT";
        String name = "Software Development";

        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(acronym, name);

        // Act
        int result = CourseIDDataModel.hashCode();

        // Assert
        assertNotNull(result);
    }

    @Test
    void shouldReturnAcronymForGetAcronymMethod() {
        // Arrange
        String acronym = "DSOFT";
        String name = "Software Development";

        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(acronym, name);

        // Act
        String result = CourseIDDataModel.getAcronym();

        // Assert
        assertNotNull(result);
    }


    @Test
    void shouldReturnAcronymForGetNameMethod() {
        // Arrange
        String acronym = "DSOFT";
        String name = "Software Development";

        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(acronym, name);

        // Act
        String result = CourseIDDataModel.getName();

        // Assert
        assertNotNull(result);
    }
}
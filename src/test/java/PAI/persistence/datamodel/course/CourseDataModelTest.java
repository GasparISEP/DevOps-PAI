package PAI.persistence.datamodel.course;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class CourseDataModelTest {

    @Test
    void should_create_a_CourseDataModel_without_Arguments() {
        // Arrange
        //Act
        CourseDataModel courseDataModel = new CourseDataModel();
        //Assert
        assertNotNull(courseDataModel);

    }

    @Test
    void should_create_a_CourseDataModel_with_Arguments() {
        // Arrange
        CourseIDDataModel courseIDDataModel = mock(CourseIDDataModel.class);
        String name = ("Alchemy");
        String acronym = ("ALC");

        //Act
        CourseDataModel courseDataModel = new CourseDataModel(courseIDDataModel,name, acronym);
        //Assert
        assertNotNull(courseDataModel);

    }

    @Test
    void should_return_name_from_CourseDataModel() {
        // Arrange
        CourseIDDataModel courseIDDataModel = mock(CourseIDDataModel.class);
        String name = ("Alchemy");
        String acronym = ("ALC");

        CourseDataModel courseDataModel = new CourseDataModel(courseIDDataModel,name, acronym);
        //Act
        String expected = courseDataModel.getName();
        //Assert
        assertEquals(name, expected);

    }

    @Test
    void should_return_acronym_from_CourseDataModel() {
        // Arrange
        CourseIDDataModel courseIDDataModel = mock(CourseIDDataModel.class);
        String name = ("Alchemy");
        String acronym = ("ALC");

        CourseDataModel courseDataModel = new CourseDataModel(courseIDDataModel,name, acronym);

        //Act
        String expected = courseDataModel.getAcronym();

        //Assert
        assertEquals(acronym, expected);

    }

    @Test
    void shouldReturnCourseIDFromCourseDataModel() {
        // Arrange
        CourseIDDataModel courseIDDataModel = mock(CourseIDDataModel.class);
        String name = ("Alchemy");
        String acronym = ("ALC");

        CourseDataModel courseDataModel = new CourseDataModel(courseIDDataModel,name, acronym);

        //Act
        CourseIDDataModel expected = courseDataModel.getCourseID();

        //Assert
        assertEquals(courseIDDataModel, expected);

    }

    @Test
    void shouldReturnNullCourseDataModelVersionForNonPersistedCourseDataModel() {
        // Arrange
        CourseIDDataModel courseIDDataModel = mock(CourseIDDataModel.class);
        String name = "Software Development";
        String acronym = "DSOFT";

        CourseDataModel courseDataModel = new CourseDataModel(courseIDDataModel, name, acronym);

        // Act
        Long expected = courseDataModel.getVersion();

        // Assert
        assertNull(expected);
    }

    @Test
    void shouldReturnFalseIfCourseDataModelHasDifferentCourseIDs() {
        // Arrange
        CourseIDDataModel courseIDDataModel1 = mock(CourseIDDataModel.class);
        CourseIDDataModel courseIDDataModel2 = mock(CourseIDDataModel.class);

        String acronym = "ALC";
        String name = "Alchemy";

        CourseDataModel courseDataModel1 = new CourseDataModel(courseIDDataModel1, name, acronym);
        CourseDataModel courseDataModel2 = new CourseDataModel(courseIDDataModel2, name, acronym);

        // Act
        boolean result = courseDataModel1.equals(courseDataModel2);

        // Assert
        assertFalse(result);
    }
    @Test
    void shouldReturnTrueIfCourseDataModelHasSameCourseID() {
        // Arrange
        CourseIDDataModel courseIDDataModel = mock(CourseIDDataModel.class);

        String acronym = "ALC";
        String name = "Alchemy";

        CourseDataModel courseDataModel1 = new CourseDataModel(courseIDDataModel, name, acronym);
        CourseDataModel courseDataModel2 = new CourseDataModel(courseIDDataModel, name, acronym);

        // Act
        boolean result = courseDataModel1.equals(courseDataModel2);

        // Assert
        assertTrue(result);
    }
    @Test
    void shouldReturnAValidResultWhenUsingHasCodeMethod(){
        //Arrange
        CourseIDDataModel courseIDDataModel = mock(CourseIDDataModel.class);
        String acronym = "ALC";
        String name = "Alchemy";

        CourseDataModel courseDataModel = new CourseDataModel(courseIDDataModel, name, acronym);

        //Act
        int result = courseDataModel.hashCode();

        //Assert
        assertEquals(courseDataModel.hashCode(),result);
    }
}
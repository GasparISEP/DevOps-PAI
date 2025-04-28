package PAI.persistence.datamodel.course;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


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
}
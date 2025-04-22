package PAI.persistence.datamodel;

import PAI.persistence.datamodel.course.CourseDataModel;
import PAI.persistence.datamodel.course.CourseIDDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        double courseQuantityCreditsEcts = 10;
        int durationCourseInCurricularYear = 1;
        //Act
        CourseDataModel courseDataModel = new CourseDataModel(courseIDDataModel,name, acronym,courseQuantityCreditsEcts,durationCourseInCurricularYear);
        //Assert
        assertNotNull(courseDataModel);

    }

    @Test
    void should_return_name_from_CourseDataModel() {
        // Arrange
        CourseIDDataModel courseIDDataModel = mock(CourseIDDataModel.class);
        String name = ("Alchemy");
        String acronym = ("ALC");
        double courseQuantityCreditsEcts = 10;
        int durationCourseInCurricularYear = 1;
        CourseDataModel courseDataModel = new CourseDataModel(courseIDDataModel,name, acronym,courseQuantityCreditsEcts,durationCourseInCurricularYear);
        //Act
        String expected = courseDataModel.get_name();
        //Assert
        assertEquals(name, expected);

    }

    @Test
    void should_return_acronym_from_CourseDataModel() {
        // Arrange
        CourseIDDataModel courseIDDataModel = mock(CourseIDDataModel.class);
        String name = ("Alchemy");
        String acronym = ("ALC");
        double courseQuantityCreditsEcts = 10;
        int durationCourseInCurricularYear = 1;
        CourseDataModel courseDataModel = new CourseDataModel(courseIDDataModel,name, acronym,courseQuantityCreditsEcts,durationCourseInCurricularYear);
        //Act
        String expected = courseDataModel.get_acronym();
        //Assert
        assertEquals(acronym, expected);

    }

    @Test
    void should_return_courseQuantityCreditsEcts_from_CourseDataModel() {
        // Arrange
        CourseIDDataModel courseIDDataModel = mock(CourseIDDataModel.class);
        String name = ("Alchemy");
        String acronym = ("ALC");
        double courseQuantityCreditsEcts = 10;
        int durationCourseInCurricularYear = 1;
        CourseDataModel courseDataModel = new CourseDataModel(courseIDDataModel,name, acronym,courseQuantityCreditsEcts,durationCourseInCurricularYear);
        //Act
        double expected = courseDataModel.get_quantityCreditsEcts();
        //Assert
        assertEquals(courseQuantityCreditsEcts, expected);

    }

    @Test
    void should_return_durationCourseInCurricularYear_from_CourseDataModel() {
        // Arrange
        CourseIDDataModel courseIDDataModel = mock(CourseIDDataModel.class);
        String name = ("Alchemy");
        String acronym = ("ALC");
        double courseQuantityCreditsEcts = 10;
        int durationCourseInCurricularYear = 1;
        CourseDataModel courseDataModel = new CourseDataModel(courseIDDataModel,name, acronym,courseQuantityCreditsEcts,durationCourseInCurricularYear);
        //Act
        double expected = courseDataModel.get_duration();
        //Assert
        assertEquals(durationCourseInCurricularYear, expected);

    }
}
package PAI.domain;

import PAI.VOs.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseDDDTest {

    @Test
    void shouldCreateValidCourse() throws Exception {
        //arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);
        //act
        CourseDDD courseDDD = new CourseDDD(courseID,name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);
        // Assert
        assertNotNull(courseDDD);
    }

    @Test
    void shouldReturnCorrectIdentity() throws Exception {
        // Arrange
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);
        CourseID courseID = mock(CourseID.class);

        CourseDDD courseDDD = new CourseDDD(courseID,name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);

        // Assert
        assertNotNull(courseDDD.identity());
    }

    @Test
    void shouldReturnSameAs() throws Exception {
        // Arrange
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);
        CourseID courseID = mock(CourseID.class);

        CourseDDD courseDDD = new CourseDDD(courseID,name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);
        CourseDDD courseDDD1 = courseDDD;

        // Assert
        assertFalse(courseDDD.sameAs(courseDDD1));
    }

}
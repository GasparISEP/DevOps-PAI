package PAI.domain.course;

import PAI.VOs.*;
import PAI.domain.accessMethodDDD.AccessMethodDDD;
import PAI.domain.accessMethodDDD.AccessMethodDDDFactoryImpl;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;

class CourseFactoryDDDImplTest {

    @Test
    void shouldCreateValidCourse() throws InstantiationException {
        //arrange
        CourseFactoryDDDImpl courseFactoryDDDImpl = new CourseFactoryDDDImpl();
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);

        try (MockedConstruction<CourseDDD> mockCourseDDD = mockConstruction(CourseDDD.class, (mock, context) ->{
        })) {
            //act
            CourseDDD courseDDD = courseFactoryDDDImpl.createCourse(courseID,name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);
            //assert
            assertNotNull(courseDDD);
        }
    }

    @Test
    void mockingConstructorThrowingException(){
        //arrange
        CourseFactoryDDDImpl courseFactoryDDDImpl = new CourseFactoryDDDImpl();
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);
        //Use try-with-resources to mock construction and throw an exception
        try (MockedConstruction<CourseDDD> mockCourseDDD = mockConstruction(CourseDDD.class, (mock, context) ->{
            //Define behavior: throwing an exception when a new instance of Location is created
            throw new RuntimeException(new InstantiationException("Course constructor failed"));
        })) {
            //Act: trying to create accessMethod will throw the exception
            try {
                courseFactoryDDDImpl.createCourse(courseID,name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);
                fail("Expect exception not thrown");
            } catch (Exception e) {
                //Assertion to check if the exception is thrown
                assertTrue(e.getCause().getMessage().contains("Course constructor failed"));
            }
        }
    }
}
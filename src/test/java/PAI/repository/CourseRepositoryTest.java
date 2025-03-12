package PAI.repository;

import PAI.domain.Course;
import PAI.factory.CourseFactoryImpl;
import PAI.factory.CourseListFactory;
import org.junit.jupiter.api.Test;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseRepositoryTest {

    @Test
    void testCourseRepositoryCreationValid() {
        //SUT CourseRepository

        //Arrange
        CourseFactoryImpl courseFactoryImpl = mock(CourseFactoryImpl.class);
        CourseListFactory courseListFactory = mock(CourseListFactory.class);
        //act
        CourseRepository cR1 = new CourseRepository(courseFactoryImpl, courseListFactory);
        //assert
        assertNotNull(cR1);
    }

    @Test
    void shouldReturnTrueIfCourseIsRegistered() throws Exception {

        //arrange
        CourseFactoryImpl courseFactoryImpl = mock(CourseFactoryImpl.class);
        CourseListFactory courseListFactory = mock(CourseListFactory.class);
        CourseRepository courseRepository = new CourseRepository(courseFactoryImpl,courseListFactory);


        String courseName = "Matemática";
        String acronym = "MAT";
        double quantityCreditsEcts = 10;
        int durationCourseInSemester = 1;

        Course courseMock = mock(Course.class);

        when(courseFactoryImpl.createCourse(courseName, acronym, quantityCreditsEcts, durationCourseInSemester)).thenReturn(courseMock);

        //act
        boolean result = courseRepository.registerCourse(courseName, acronym, quantityCreditsEcts, durationCourseInSemester);
        //assert
        assertTrue(result);
    }
    @Test
    void shouldReturnFalseIfCourseIsNotRegisteredBecauseItsAlreadyRegisted() throws Exception {
        //arrange

        CourseFactoryImpl courseFactoryImpl = mock(CourseFactoryImpl.class);
        CourseListFactory courseListFactory = mock(CourseListFactory.class);
        CourseRepository courseRepository = new CourseRepository(courseFactoryImpl,courseListFactory);

        Course course1 = mock(Course.class);

        String courseName1 = "Matemática";
        String acronym1 = "MAT";
        double quantityCreditsECTS1 = 10;
        int durationCourseInSemester1 = 1;

        when(courseFactoryImpl.createCourse(courseName1, acronym1, quantityCreditsECTS1, durationCourseInSemester1)).thenReturn(course1);
        courseRepository.registerCourse(courseName1, acronym1, quantityCreditsECTS1, durationCourseInSemester1);

        //act
        boolean result = courseRepository.registerCourse(courseName1, acronym1, quantityCreditsECTS1, durationCourseInSemester1);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnListWithRegisteredCourses() throws Exception {
        //SUT - CourseRepository

        //arrange
        CourseFactoryImpl courseFactoryImpl = mock(CourseFactoryImpl.class);
        CourseListFactory courseListFactory = mock(CourseListFactory.class);
        CourseRepository courseRepository = new CourseRepository(courseFactoryImpl,courseListFactory);

        String courseName1 = "Matemática";
        String acronym1 = "MAT";
        double quantityCreditsECTS1 = 10;
        int durationCourseInSemester1 = 1;
        Course course1 = mock(Course.class);

        String courseName2 = "Português";
        String acronym2 = "PT";
        double quantityCreditsECTS2 = 8;
        int durationCourseInSemester2 = 1;
        Course course2 = mock(Course.class);

        when(courseFactoryImpl.createCourse(courseName1, acronym1, quantityCreditsECTS1, durationCourseInSemester1)).thenReturn(course1);
        when(courseFactoryImpl.createCourse(courseName2, acronym2, quantityCreditsECTS2, durationCourseInSemester2)).thenReturn(course2);

        courseRepository.registerCourse(courseName1, acronym1, quantityCreditsECTS1, durationCourseInSemester1);
        courseRepository.registerCourse(courseName2, acronym2, quantityCreditsECTS2, durationCourseInSemester2);

        //Act

        List<Course> listCourses = courseRepository.getAllCourses();

        // Assert
        assertEquals(2, listCourses.size());
        assertTrue(listCourses.contains(course1));
        assertTrue(listCourses.contains(course2));
    }
}
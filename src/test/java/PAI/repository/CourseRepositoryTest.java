package PAI.repository;

import PAI.domain.Course;
import PAI.factory.CourseFactory;
import org.junit.jupiter.api.Test;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseRepositoryTest {

    @Test
    void testCourseRepositoryCreationValid() {
        //arrange
        CourseFactory courseFactory = mock(CourseFactory.class);
        //act
        CourseRepository cR1 = new CourseRepository(courseFactory);
        //assert
        assertNotNull(cR1);
    }

    @Test
    void shouldReturnTrueIfCourseIsRegistered() throws Exception {
        //arrange
        CourseFactory courseFactory = mock(CourseFactory.class);
        CourseRepository cR1 = new CourseRepository(courseFactory);

        String courseName = "Matemática";
        String acronym = "MAT";
        double quantityCreditsEcts = 10;
        int durationCourseInSemester = 1;

        Course courseMock = mock(Course.class);

        when(courseFactory.createCourse(courseName, acronym, quantityCreditsEcts, durationCourseInSemester)).thenReturn(courseMock);

        //act
        boolean result = cR1.registerCourse(courseName, acronym, quantityCreditsEcts, durationCourseInSemester);
        //assert
        assertTrue(result);
    }
    @Test
    void shouldReturnFalseIfCourseIsNotRegistered() throws Exception {
        //arrange
        CourseFactory courseFactory = mock(CourseFactory.class);
        CourseRepository courseRepository = new CourseRepository(courseFactory);

        String courseName1 = "Matemática";
        String acronym1 = "MAT";
        double quantityCreditsECTS1 = 10;
        int durationCourseInSemester1 = 1;
        Course course1 = new Course(courseName1, acronym1, quantityCreditsECTS1, durationCourseInSemester1);

        when(courseFactory.createCourse(courseName1, acronym1, quantityCreditsECTS1, durationCourseInSemester1)).thenReturn(course1);
        courseRepository.registerCourse(courseName1, acronym1, quantityCreditsECTS1, durationCourseInSemester1);

        //act
        boolean result = courseRepository.registerCourse(courseName1, acronym1, quantityCreditsECTS1, durationCourseInSemester1);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnCategoryListWithRegisteredCourses() throws Exception {
        //arrange
        CourseFactory courseFactory = mock(CourseFactory.class);
        CourseRepository courseRepository = new CourseRepository(courseFactory);

        String courseName1 = "Matemática";
        String acronym1 = "MAT";
        double quantityCreditsECTS1 = 10;
        int durationCourseInSemester1 = 1;
        Course course1 = new Course(courseName1, acronym1, quantityCreditsECTS1, durationCourseInSemester1);

        String courseName2 = "Português";
        String acronym2 = "PT";
        double quantityCreditsECTS2 = 8;
        int durationCourseInSemester2 = 1;
        Course course2 = new Course(courseName2, acronym2, quantityCreditsECTS2, durationCourseInSemester2);

        when(courseFactory.createCourse(courseName1, acronym1, quantityCreditsECTS1, durationCourseInSemester1)).thenReturn(course1);
        when(courseFactory.createCourse(courseName2, acronym2, quantityCreditsECTS2, durationCourseInSemester2)).thenReturn(course2);

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
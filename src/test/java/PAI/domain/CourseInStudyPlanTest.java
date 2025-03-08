package PAI.domain;

import PAI.factory.*;
import PAI.repository.CourseRepository;
import PAI.repository.StudyPlan;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseInStudyPlanTest {

    @Test
    void ShouldConstructACourseInStudyPlanWithMock() throws Exception {

        //Arrange
        Course course = mock(Course.class);
        Programme programme = mock(Programme.class);

        when(programme.getQuantityOfSemester()).thenReturn(6);
        when(programme.calculateNumberOfYears(6)).thenReturn(3);

        //Act
        CourseInStudyPlan courseInStudyPlan = new CourseInStudyPlan(1, 1, course, programme);

        //Assert
        assertNotNull(courseInStudyPlan);
    }

    @Test
    void testEqualsMethod() throws Exception {
        // Arrange
        Course course1 = new Course("Programming", "PROG", 5, 1);
        Course course2 = new Course("Mathematics", "MATH", 6, 1);
        DegreeType master = new DegreeType("Master", 240);
        Department cse = new Department("CSE", "Computer Science Engineer");
        Teacher teacher = mock(Teacher.class);
        ProgrammeCourseListFactory programmeCourseListFactory = mock(ProgrammeCourseListFactory.class);
        CourseInStudyPlanFactory courseInStudyPlanFactory = mock(CourseInStudyPlanFactory.class);
        StudyPlanArrayListFactory studyPlanArrayListFactory = mock(StudyPlanArrayListFactory.class);
        StudyPlanFactory studyPlanFactory = mock(StudyPlanFactory.class);
        CourseFactory courseFactory = mock(CourseFactory.class);

        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, master, cse, teacher, programmeCourseListFactory, courseInStudyPlanFactory ,studyPlanArrayListFactory, studyPlanFactory, courseFactory);

        programme.addCourseToAProgramme(course1);
        programme.addCourseToAProgramme(course2);

        CourseInStudyPlan courseInStudyPlan1 = new CourseInStudyPlan(1, 1, course1, programme);
        CourseInStudyPlan courseInStudyPlan2 = new CourseInStudyPlan(1, 1, course1, programme);
        CourseInStudyPlan courseInStudyPlan3 = new CourseInStudyPlan(1, 1, course2, programme);

        // Act & Assert
        assertEquals(courseInStudyPlan1, courseInStudyPlan2, "Objects with the same course should be equal");
        assertNotEquals(courseInStudyPlan1, courseInStudyPlan3, "Objects with different courses should not be equal");
        assertNotEquals(courseInStudyPlan1, new Object(), "Different types should not be equal");
        assertNotEquals(courseInStudyPlan1, null, "Comparison with null should return false");
    }

    @Test
    void testEqualsMethodWithMock() throws Exception {
        // Arrange
        Course course1 = mock(Course.class);
        Course course2 = mock(Course.class);
        Programme programme = mock(Programme.class);

        when(programme.getQuantityOfSemester()).thenReturn(6);
        when(programme.calculateNumberOfYears(6)).thenReturn(3);

        CourseInStudyPlan courseInStudyPlan1 = new CourseInStudyPlan(1, 1, course1, programme);
        CourseInStudyPlan courseInStudyPlan2 = new CourseInStudyPlan(1, 1, course1, programme);
        CourseInStudyPlan courseInStudyPlan3 = new CourseInStudyPlan(1, 1, course2, programme);

        // Act & Assert
        assertEquals(courseInStudyPlan1, courseInStudyPlan2, "Objects with the same course should be equal");
        assertNotEquals(courseInStudyPlan1, courseInStudyPlan3, "Objects with different courses should not be equal");
        assertNotEquals(courseInStudyPlan1, new Object(), "Different types should not be equal");
        assertNotEquals(courseInStudyPlan1, null, "Comparison with null should return false");
    }

    @Test
    void testEqualsMethodWithDifferentSemestersAndYears() throws Exception {
        CourseListFactory courseListFactory = mock(CourseListFactory.class);
        CourseFactory courseFactory = mock(CourseFactory.class);
        CourseRepository courseRepository = new CourseRepository(courseFactory, courseListFactory);
        Course course1 = new Course("Programming", "PROG", 5, 1);
        courseRepository.registerCourse("Programming", "PROG", 5, 1);
        DegreeType master = new DegreeType("Master", 240);
        Department cse = new Department("CSE", "Computer Science Engineer");
        Teacher teacher = mock(Teacher.class);
        ProgrammeCourseListFactory programmeCourseListFactory = mock(ProgrammeCourseListFactory.class);
        CourseInStudyPlanFactory courseInStudyPlanFactory = mock(CourseInStudyPlanFactory.class);
        StudyPlanArrayListFactory studyPlanArrayListFactory = mock(StudyPlanArrayListFactory.class);
        StudyPlanFactory studyPlanFactory = mock(StudyPlanFactory.class);

        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, master, cse, teacher, programmeCourseListFactory, courseInStudyPlanFactory ,studyPlanArrayListFactory, studyPlanFactory, courseFactory);

        programme.addCourseToAProgramme(course1);

        CourseInStudyPlan course1Sem1 = new CourseInStudyPlan(1, 1, course1, programme);
        CourseInStudyPlan course1Sem2 = new CourseInStudyPlan(2, 1, course1, programme); // Mesmo curso, diferente semestre
        CourseInStudyPlan course1Year2 = new CourseInStudyPlan(1, 2, course1, programme); // Mesmo curso, diferente ano

        assertEquals(course1Sem1, course1Sem2, "Courses in different semesters should not be equal");
        assertEquals(course1Sem1, course1Year2, "Courses in different years should not be equal");
    }

    @Test
    void testEqualsMethodWithDifferentSemestersAndYearsWithMock() throws Exception {
        Course course1 = mock(Course.class);
        Programme programme = mock(Programme.class);

        when(programme.getQuantityOfSemester()).thenReturn(6);
        when(programme.calculateNumberOfYears(6)).thenReturn(3);

        CourseInStudyPlan course1Sem1 = new CourseInStudyPlan(1, 1, course1, programme);
        CourseInStudyPlan course1Sem2 = new CourseInStudyPlan(2, 1, course1, programme); // Mesmo curso, diferente semestre
        CourseInStudyPlan course1Year2 = new CourseInStudyPlan(1, 2, course1, programme); // Mesmo curso, diferente ano

        assertEquals(course1Sem1, course1Sem2, "Courses in different semesters should not be equal");
        assertEquals(course1Sem1, course1Year2, "Courses in different years should not be equal");
    }
}
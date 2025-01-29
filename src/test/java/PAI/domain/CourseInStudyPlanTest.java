package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class CourseInStudyPlanTest {

    @Test
    void ShouldConstructACourseInStudyPlan() throws Exception {

        //Arrenge
        CourseRepository courseRepository = new CourseRepository();
        Course course1 = new Course("Programming", "PROG", 5, 1); // Curso com 5 créditos
        DegreeType master = new DegreeType("Master", 240);
        Department cse = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informática, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, cse);
        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, master, cse, teacher, courseRepository);
        StudyPlan studyPlan = new StudyPlan();
        courseRepository.registerCourse("Programming", "PROG", 5, 1);
        programme.addCourseToAProgramme(course1);
        boolean addCourse1ToStudyPlan = studyPlan.registerStudyPlan(1,1, course1, programme);

        //Act
        CourseInStudyPlan courseInStudyPlan = new CourseInStudyPlan(1, 1, course1, programme);

        //Assert
        assertNotNull(courseInStudyPlan);
        assertEquals(course1, courseInStudyPlan.getCourse());
        assertEquals(1, courseInStudyPlan.getSemester());
        assertEquals(1, courseInStudyPlan.getCurricularYear());
        assertEquals(programme, courseInStudyPlan.getProgramme());
    }

    @Test
    void testEqualsMethod() throws Exception {
        // Arrange
        CourseRepository courseRepository = new CourseRepository();
        Course course1 = new Course("Programming", "PROG", 5, 1);
        Course course2 = new Course("Mathematics", "MATH", 6, 1);
        courseRepository.registerCourse("Programming", "PROG", 5, 1);
        courseRepository.registerCourse("Mathematics", "MATH", 6, 1);
        DegreeType master = new DegreeType("Master", 240);
        Department cse = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informática, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, cse);
        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, master, cse, teacher, courseRepository);

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
}
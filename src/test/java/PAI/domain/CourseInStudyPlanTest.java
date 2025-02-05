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
        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, master, cse, teacher);
        StudyPlan studyPlan = new StudyPlan();
        courseRepository.registerCourse("Programming", "PROG", 5, 1);
        programme.addCourseToAProgramme(course1);
        boolean addCourse1ToStudyPlan = studyPlan.addCourseToStudyPlan(1,1, course1, programme);

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
        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, master, cse, teacher);

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
    void testEqualsMethodWithDifferentSemestersAndYears() throws Exception {
        CourseRepository courseRepository = new CourseRepository();
        Course course1 = new Course("Programming", "PROG", 5, 1);
        courseRepository.registerCourse("Programming", "PROG", 5, 1);
        DegreeType master = new DegreeType("Master", 240);
        Department cse = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informática, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, cse);
        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, master, cse, teacher);

        programme.addCourseToAProgramme(course1);

        CourseInStudyPlan course1Sem1 = new CourseInStudyPlan(1, 1, course1, programme);
        CourseInStudyPlan course1Sem2 = new CourseInStudyPlan(2, 1, course1, programme); // Mesmo curso, diferente semestre
        CourseInStudyPlan course1Year2 = new CourseInStudyPlan(1, 2, course1, programme); // Mesmo curso, diferente ano

        assertEquals(course1Sem1, course1Sem2, "Courses in different semesters should not be equal");
        assertEquals(course1Sem1, course1Year2, "Courses in different years should not be equal");
    }

    @Test
    void shouldNotAllowRegisterWhenEctsLimitExceeded() throws Exception {
        //arrange
        DegreeType master = new DegreeType("Master", 240);
        Department cse = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informática, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, cse);
        Programme programme = new Programme("Computer Engineering", "CE", 10, 6, master, cse, teacher);

        //act
        programme.addCourseToAProgramme(new Course("Math", "MATH", 5, 1));
        programme.addCourseToAProgramme(new Course("Physics", "PHYS", 6, 1)); // Excede o limite de 10 ECTS

        StudyPlan studyPlan = new StudyPlan();

        //assert
        assertTrue(studyPlan.addCourseToStudyPlan(1, 1, new Course("Math", "MATH", 5, 1), programme));
        assertThrows(Exception.class, () -> {
            studyPlan.addCourseToStudyPlan(1, 1, new Course("Physics", "PHYS", 6, 1), programme);
        }, "Should not allow course exceeding credit limit.");
    }

    @Test
    void shouldNotAllowAnnualCourseInFinalYearIfOddSemesters() throws Exception {
        //arrange
        DegreeType master = new DegreeType("Master", 240);
        Department cse = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informática, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, cse);
        Programme programme = new Programme("Computer Engineering", "CE", 10, 6, master, cse, teacher);
        Course annualCourse = new Course("Software Engineering", "SE", 6, 2);

        //act
        programme.addCourseToAProgramme(annualCourse);

        StudyPlan studyPlan = new StudyPlan();

        //assert
        assertThrows(Exception.class, () -> {
            studyPlan.addCourseToStudyPlan(1, 4, annualCourse, programme);
        }, "Annual courses should not be allowed in the final year if semesters are odd.");
    }

    @Test
    void shouldNotAllowDuplicateCourseRegistration() throws Exception {
        //arrange
        DegreeType master = new DegreeType("Master", 240);
        Department cse = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informática, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, cse);
        Programme programme = new Programme("Computer Engineering", "CE", 10, 6, master, cse, teacher);
        Course course = new Course("Databases", "DB", 5, 1);
        programme.addCourseToAProgramme(course);

        //act
        StudyPlan studyPlan = new StudyPlan();

        //assert
        assertTrue(studyPlan.addCourseToStudyPlan(1, 1, course, programme));
        Exception exception = assertThrows(Exception.class, () -> {
            studyPlan.addCourseToStudyPlan(1, 1, course, programme);
        });

        assertEquals("Cannot register course: Course already registered in Study Plan.", exception.getMessage());
    }

    @Test
    void shouldAllowRegisteringSingleSemesterAndAnnualCourses() throws Exception {
        //arrange
        DegreeType master = new DegreeType("Master", 240);
        Department cse = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informática, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, cse);
        Programme programme = new Programme("Computer Engineering", "CE", 10, 6, master, cse, teacher);
        Course singleSemesterCourse = new Course("Algorithms", "ALG", 5, 1);
        Course annualCourse = new Course("Software Development", "SD", 10, 2);


        //act
        programme.addCourseToAProgramme(singleSemesterCourse);
        programme.addCourseToAProgramme(annualCourse);

        StudyPlan studyPlan = new StudyPlan();

        //assert
        assertTrue(studyPlan.addCourseToStudyPlan(1, 1, singleSemesterCourse, programme));
        assertTrue(studyPlan.addCourseToStudyPlan(1, 1, annualCourse, programme));
    }
}
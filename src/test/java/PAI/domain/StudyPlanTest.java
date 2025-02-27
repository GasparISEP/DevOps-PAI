package PAI.domain;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class StudyPlanTest {

    @Test
    void shouldRegisterCourseInStudyPlan() throws Exception {

        // arrange
        StudyPlan studyPlan = new StudyPlan();
        Programme programme = mock(Programme.class);
        Course course = mock(Course.class);
        when(programme.getCourseList()).thenReturn(List.of(course));

        when(programme.getQuantityOfSemester()).thenReturn(6);
        when(programme.calculateNumberOfYears(6)).thenReturn(3);
        when(programme.getStudyPlan()).thenReturn(studyPlan);

        // act
        boolean addCourse1ToStudyPlan = studyPlan.addCourseToStudyPlan(1, 1, course, programme);

        // assert
        assertTrue(addCourse1ToStudyPlan);
    }

    @Test
    void shouldRegisterTwoCoursesInStudyPlan() throws Exception {
        // arrange
        StudyPlan studyPlan = new StudyPlan();
        Programme programme = mock(Programme.class);
        Course course1 = mock(Course.class);
        Course course2 = mock(Course.class);

        when(programme.getCourseList()).thenReturn(List.of(course1,course2));
        when(programme.getQuantityOfSemester()).thenReturn(6);
        when(programme.calculateNumberOfYears(6)).thenReturn(3);
        when(programme.getStudyPlan()).thenReturn(studyPlan);

        studyPlan.addCourseToStudyPlan(1, 1, course1, programme);

        // act
        boolean addCourse2ToStudyPlan = studyPlan.addCourseToStudyPlan(1, 1, course2, programme);

        // assert
        assertTrue(addCourse2ToStudyPlan);
    }

    @Test
    void shouldNotAllowDuplicateCoursesInStudyPlan() throws Exception {
        // arrange
        CourseRepository courseRepository = mock(CourseRepository.class);
        Course course1 = mock(Course.class);
        DegreeType master = mock(DegreeType.class);
        Department cse = mock(Department.class);
        Teacher teacher = mock(Teacher.class);
        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, master, cse, teacher);
        StudyPlan studyPlan = programme.getStudyPlan();


        courseRepository.registerCourse("Programming", "PROG", 5, 1);
        programme.addCourseToAProgramme(course1);
        studyPlan.addCourseToStudyPlan(1, 1, course1, programme);


        // assert
        assertThrows(Exception.class, () -> studyPlan.addCourseToStudyPlan(1,1,course1,programme));
    }

    @Test
    void shouldNotAllowCourseExceedingCreditLimitInSemester() throws Exception {
        // arrange
        CourseRepository courseRepository = new CourseRepository();
        Course course1 = new Course("Mathematics", "MATH", 5, 1); // Curso com 5 créditos
        Course course2 = new Course("Physics", "PHYS", 26, 1); // Curso com 26 créditos
        DegreeType master = new DegreeType("Master", 240);
        Department cse = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informática, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, cse);
        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, master, cse, teacher);
        StudyPlan studyPlan = programme.getStudyPlan();

        // act
        courseRepository.registerCourse("Mathematics", "MATH", 5, 1);
        courseRepository.registerCourse("Physics", "PHYS", 26, 1);

        programme.addCourseToAProgramme(course1);
        programme.addCourseToAProgramme(course2);

        boolean addCourse1ToStudyPlan = studyPlan.addCourseToStudyPlan(1, 1, course1, programme);

        // assert
        assertTrue(addCourse1ToStudyPlan);
        assertThrows(Exception.class, () -> studyPlan.addCourseToStudyPlan(1, 1, course2, programme));
    }

    @Test
    void shouldNotAllowCourseInInvalidSemester() throws Exception {
        // arrange
        StudyPlan studyPlan = new StudyPlan();
        Programme programme = mock(Programme.class);
        Course course1 = mock(Course.class);

        when(programme.getCourseList()).thenReturn(List.of(course1));
        when(programme.getQuantityOfSemester()).thenReturn(6);
        when(programme.calculateNumberOfYears(6)).thenReturn(3);
        when(programme.getStudyPlan()).thenReturn(studyPlan);


        // act + assert
        assertThrows(Exception.class, () -> studyPlan.addCourseToStudyPlan(0, 1, course1, programme));
    }

    @Test
    void shouldNotAllowCourseInInvalidCurricularYear() throws Exception {
        // arrange
        StudyPlan studyPlan = new StudyPlan();
        Programme programme = mock(Programme.class);
        Course course1 = mock(Course.class);

        when(programme.getCourseList()).thenReturn(List.of(course1));
        when(programme.getQuantityOfSemester()).thenReturn(6);
        when(programme.calculateNumberOfYears(6)).thenReturn(3);
        when(programme.getStudyPlan()).thenReturn(studyPlan);

        // act + assert
        assertThrows(Exception.class, () -> studyPlan.addCourseToStudyPlan(1, 0, course1, programme));
    }

    @Test
    void shouldNotAllowCourseInSecondSemesterOfLastYearInOddSemestersProgramme() throws Exception {
        // arrange
        StudyPlan studyPlan = new StudyPlan();
        Programme programme = mock(Programme.class);
        Course course1 = mock(Course.class);

        when(programme.getCourseList()).thenReturn(List.of(course1));
        when(programme.getQuantityOfSemester()).thenReturn(5);
        when(programme.calculateNumberOfYears(5)).thenReturn(3);
        when(programme.getStudyPlan()).thenReturn(studyPlan);


        // act + assert
        assertThrows(Exception.class, () -> studyPlan.addCourseToStudyPlan(2, 3, course1, programme));
    }

    @Test
    void shouldNotAllowAnnualCourseInLastYearOfOddSemestersProgramme() throws Exception {
        StudyPlan studyPlan = new StudyPlan();
        Programme programme = mock(Programme.class);
        Course course1 = mock(Course.class);

        when(course1.getDurationInSemester()).thenReturn(2);
        when(programme.getCourseList()).thenReturn(List.of(course1));
        when(programme.getQuantityOfSemester()).thenReturn(6);
        when(programme.calculateNumberOfYears(6)).thenReturn(3);
        when(programme.getStudyPlan()).thenReturn(studyPlan);

        // act + assert
        assertThrows(Exception.class, () -> studyPlan.addCourseToStudyPlan(1, 3, course1, programme));
    }

    @Test
    void shouldNotAllowRegisterNullCourseInStudyPlan() throws Exception {
        // arrange
        CourseRepository courseRepository = new CourseRepository();
        DegreeType master = new DegreeType("Master", 240);
        Department cse = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informática, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, cse);
        Programme programme = new Programme("Computer Engineering", "CE", 30, 5, master, cse, teacher);
        StudyPlan studyPlan = programme.getStudyPlan();

        // act
        Exception exception = assertThrows(Exception.class, () -> {
            studyPlan.addCourseToStudyPlan(1, 1, null, programme);
        });

        //assert
        assertEquals("Invalid course or programme.", exception.getMessage());
    }

    @Test
    void shouldNotAllowRegisterNullProgrammeInStudyPlan() throws Exception {
        // arrange
        CourseRepository courseRepository = new CourseRepository();
        Course course1 = new Course("Annual Course", "ANNUAL", 12, 2); // Curso anual com 12 créditos
        DegreeType master = new DegreeType("Master", 240);
        Department cse = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informática, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, cse);
        Programme programme = new Programme("Computer Engineering", "CE", 30, 5, master, cse, teacher);
        StudyPlan studyPlan = programme.getStudyPlan();

        // act
        Exception exception = assertThrows(Exception.class, () -> {
            studyPlan.addCourseToStudyPlan(1, 1, course1, null);
        });

        //assert
        assertEquals("Invalid course or programme.", exception.getMessage());
    }

    @Test
    void shouldAllowRegisterCoursesUntilCreditsReachLimit() throws Exception {
        // arrange
        StudyPlan studyPlan = new StudyPlan();
        Programme programme = mock(Programme.class);
        Course course1 = mock(Course.class);
        Course course2 = mock(Course.class);
        Course course3 = mock(Course.class);

        when(course1.getQuantityCreditsEcts()).thenReturn(10.0);
        when(course2.getQuantityCreditsEcts()).thenReturn(10.0);
        when(course3.getQuantityCreditsEcts()).thenReturn(10.0);
        when(programme.getQuantityOfEcts()).thenReturn(30);
        when(programme.getCourseList()).thenReturn(List.of(course1,course2,course3));
        when(programme.getQuantityOfSemester()).thenReturn(6);
        when(programme.calculateNumberOfYears(6)).thenReturn(3);
        when(programme.getStudyPlan()).thenReturn(studyPlan);

        studyPlan.addCourseToStudyPlan(1, 1, course1, programme);
        studyPlan.addCourseToStudyPlan(1, 1, course2, programme);

        // act
        boolean addCourse3ToStudyPlan = studyPlan.addCourseToStudyPlan(1, 1, course3, programme);

        // assert
        assertTrue(addCourse3ToStudyPlan);
    }

    @Test
    void shouldAllowRegisterAnnualCourseSpanningTwoSemesters() throws Exception {
        // arrange
        StudyPlan studyPlan = new StudyPlan();
        Programme programme = mock(Programme.class);
        Course course1 = mock(Course.class);

        when(course1.getDurationInSemester()).thenReturn(2);
        when(course1.getName()).thenReturn("Annual Course");
        when(course1.getAcronym()).thenReturn("ANNUAL");
        when(course1.getQuantityCreditsEcts()).thenReturn(10.0);
        when(programme.getQuantityOfEcts()).thenReturn(30);
        when(programme.getCourseList()).thenReturn(List.of(course1));
        when(programme.getQuantityOfSemester()).thenReturn(6);
        when(programme.calculateNumberOfYears(6)).thenReturn(3);
        when(programme.getStudyPlan()).thenReturn(studyPlan);

        // act
        boolean annualCourse = studyPlan.addCourseToStudyPlan(1, 3, course1, programme);

        // assert
        assertTrue(annualCourse);
    }

    @Test
    void shouldNotAllowRegisterAnnualCourseIfNotEnoughSpaceInBothSemesters() throws Exception {
        // arrange
        CourseRepository courseRepository = new CourseRepository();
        Course course1 = new Course("Programming", "PROG", 25, 1); // Curso no 1º semestre
        Course course2 = new Course("Mathematics", "MATH", 25, 1); // Curso no 2º semestre
        Course annualCourse = new Course("Software Engineering", "SENG", 20, 2); // Curso anual com 20 créditos
        DegreeType master = new DegreeType("Master", 240);
        Department cse = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informática, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, cse);
        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, master, cse, teacher);
        StudyPlan studyPlan = programme.getStudyPlan();

        // act
        courseRepository.registerCourse("Programming", "PROG", 25, 1);
        courseRepository.registerCourse("Mathematics", "MATH", 25, 1);
        courseRepository.registerCourse("Software Engineering", "SENG", 20, 2);

        programme.addCourseToAProgramme(course1);
        programme.addCourseToAProgramme(course2);
        programme.addCourseToAProgramme(annualCourse);

        boolean addCourse1ToStudyPlan = studyPlan.addCourseToStudyPlan(1, 1, course1, programme);
        boolean addCourse2ToStudyPlan = studyPlan.addCourseToStudyPlan(2, 1, course2, programme);

        //assert
        assertTrue(addCourse1ToStudyPlan);
        assertTrue(addCourse2ToStudyPlan);
        assertThrows(Exception.class, () -> studyPlan.addCourseToStudyPlan(1, 1, annualCourse, programme));
    }

    @Test
    void shouldNotAllowRegisterAnnualCourseIfNotEnoughSpaceInFirstSemester() throws Exception {
        // arrange
        CourseRepository courseRepository = new CourseRepository();
        Course course1 = new Course("Programming", "PROG", 25, 1);
        Course course2 = new Course("Mathematics", "MATH", 25, 2);
        DegreeType master = new DegreeType("Master", 240);
        Department cse = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informática, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, cse);
        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, master, cse, teacher);
        StudyPlan studyPlan = programme.getStudyPlan();

        // act
        courseRepository.registerCourse("Programming", "PROG", 25, 1);
        courseRepository.registerCourse("Mathematics", "MATH", 25, 2);

        programme.addCourseToAProgramme(course1);
        programme.addCourseToAProgramme(course2);

        boolean addCourse1ToStudyPlan = studyPlan.addCourseToStudyPlan(1, 1, course1, programme);

        //assert
        assertTrue(addCourse1ToStudyPlan);
        assertThrows(Exception.class, () -> studyPlan.addCourseToStudyPlan(1, 1, course2, programme));
    }

    @Test
    void shouldNotAllowRegisterAnnualCourseIfNotEnoughSpaceInSecondSemester() throws Exception {
        // arrange
        CourseRepository courseRepository = new CourseRepository();
        Course course1 = new Course("Programming", "PROG", 25, 1);
        Course course2 = new Course("Mathematics", "MATH", 25, 2);
        DegreeType master = new DegreeType("Master", 240);
        Department cse = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informática, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, cse);
        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, master, cse, teacher);
        StudyPlan studyPlan = programme.getStudyPlan();

        // act
        courseRepository.registerCourse("Programming", "PROG", 25, 1);
        courseRepository.registerCourse("Mathematics", "MATH", 25, 2);

        programme.addCourseToAProgramme(course1);
        programme.addCourseToAProgramme(course2);

        boolean addCourse1ToStudyPlan = studyPlan.addCourseToStudyPlan(2, 1, course1, programme);

        //assert
        assertTrue(addCourse1ToStudyPlan);
        assertThrows(Exception.class, () -> studyPlan.addCourseToStudyPlan(1, 1, course2, programme));
    }

    @Test
    void shouldNotAllowAnnualCourseInInvalidSemester() throws Exception {
        // arrange
        CourseRepository courseRepository = new CourseRepository();
        Course course1 = new Course("Programming", "PROG", 5, 2);
        DegreeType master = new DegreeType("Master", 240);
        Department cse = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informática, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, cse);
        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, master, cse, teacher);
        StudyPlan studyPlan = programme.getStudyPlan();

        // act
        courseRepository.registerCourse("Programming", "PROG", 5, 1);
        programme.addCourseToAProgramme(course1);

        // assert
        assertThrows(Exception.class, () -> studyPlan.addCourseToStudyPlan(2, 1, course1, programme));
    }

    @Test
    void shouldThrowExceptionWhenRegisteringCourseNotInProgramme() throws Exception {
        DegreeType master = new DegreeType("Master", 240);
        Department cse = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informática, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, cse);
        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, master, cse, teacher);

        Course invalidCourse = new Course("Physics", "PHYS", 6, 1); // Este curso não foi adicionado ao programa

        StudyPlan studyPlan = programme.getStudyPlan();

        Exception exception = assertThrows(Exception.class, () -> {
            studyPlan.addCourseToStudyPlan(1, 1, invalidCourse, programme);
        });

        assertEquals("Invalid course or programme.", exception.getMessage());
    }

    @Test
    void shouldAllowCourseThatExactlyMeetsEctsLimit() throws Exception {
        DegreeType master = new DegreeType("Master", 240);
        Department cse = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informática, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, cse);
        Programme programme = new Programme("Computer Engineering", "CE", 10, 6, master, cse, teacher);

        Course course1 = new Course("Mathematics", "MATH", 5, 1);
        Course course2 = new Course("Physics", "PHYS", 5, 1); // Soma dos créditos = limite de 10

        programme.addCourseToAProgramme(course1);
        programme.addCourseToAProgramme(course2);

        StudyPlan studyPlan = programme.getStudyPlan();

        assertTrue(studyPlan.addCourseToStudyPlan(1, 1, course1, programme));
        assertTrue(studyPlan.addCourseToStudyPlan(1, 1, course2, programme)); // Não deve lançar erro
    }
}
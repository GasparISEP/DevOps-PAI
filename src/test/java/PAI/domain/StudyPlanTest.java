package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class StudyPlanTest {

    @Test
    void shouldRegisterCourseInStudyPlan() throws Exception {
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

        // act
        boolean addCourse1ToStudyPlan = studyPlan.addCourseToStudyPlan(1, 1, course1, programme);

        // assert
        assertTrue(addCourse1ToStudyPlan);

    }

    @Test
    void shouldRegisterTwoCoursesInStudyPlan() throws Exception {
        // arrange
        CourseRepository courseRepository = mock(CourseRepository.class);
        Course course1 = mock(Course.class);
        Course course2 = mock(Course.class);
        DegreeType master = mock(DegreeType.class);
        Department cse = mock(Department.class);
        Teacher teacher = mock(Teacher.class);
        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, master, cse, teacher);
        StudyPlan studyPlan = programme.getStudyPlan();
        courseRepository.registerCourse("Programming", "PROG", 5, 1);
        courseRepository.registerCourse("Programminga", "PRO", 5, 1);
        programme.addCourseToAProgramme(course1);
        programme.addCourseToAProgramme(course2);
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
        CourseRepository courseRepository = new CourseRepository();
        Course course1 = new Course("Programming", "PROG", 5, 1);
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
        assertThrows(Exception.class, () -> studyPlan.addCourseToStudyPlan(0, 1, course1, programme));
        assertThrows(Exception.class, () -> studyPlan.addCourseToStudyPlan(3, 1, course1, programme));
    }

    @Test
    void shouldNotAllowCourseInInvalidCurricularYear() throws Exception {
        // arrange
        CourseRepository courseRepository = new CourseRepository();
        Course course1 = new Course("Programming", "PROG", 5, 1);
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
        assertThrows(Exception.class, () -> studyPlan.addCourseToStudyPlan(1, 0, course1, programme));
        assertThrows(Exception.class, () -> studyPlan.addCourseToStudyPlan(1, 4, course1, programme));
    }

    @Test
    void shouldNotAllowCourseInSecondSemesterOfLastYearInOddSemestersProgramme() throws Exception {
        // arrange
        CourseRepository courseRepository = new CourseRepository();
        Course course1 = new Course("Programming", "PROG", 5, 1);
        DegreeType master = new DegreeType("Master", 240);
        Department cse = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informática, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, cse);
        Programme programme = new Programme("Computer Engineering", "CE", 30, 5, master, cse, teacher);
        StudyPlan studyPlan = programme.getStudyPlan();

        // act
        courseRepository.registerCourse("Programming", "PROG", 5, 1);
        programme.addCourseToAProgramme(course1);

        // assert
        assertThrows(Exception.class, () -> studyPlan.addCourseToStudyPlan(2, 3, course1, programme));
    }

    @Test
    void shouldNotAllowAnnualCourseInLastYearOfOddSemestersProgramme() throws Exception {
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
        courseRepository.registerCourse("Annual Course", "ANNUAL", 12, 2);
        programme.addCourseToAProgramme(course1);

        // assert
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
        CourseRepository courseRepository = new CourseRepository();
        Course course1 = new Course("Physics", "PHYS", 10, 1); // Curso com 10 créditos
        Course course2 = new Course("Mathematics", "MATH", 10, 1); // Curso com 10 créditos
        Course course3 = new Course("Programming", "PROG", 10, 1); // Curso com 10 créditos
        DegreeType master = new DegreeType("Master", 240);
        Department cse = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informática, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, cse);

        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, master, cse, teacher);
        StudyPlan studyPlan = programme.getStudyPlan();

        // act
        courseRepository.registerCourse("Physics", "PHYS", 10, 1);
        courseRepository.registerCourse("Mathematics", "MATH", 10, 1);
        courseRepository.registerCourse("Programming", "PROG", 10, 1);

        programme.addCourseToAProgramme(course1);
        programme.addCourseToAProgramme(course2);
        programme.addCourseToAProgramme(course3);

        boolean addCourse1ToStudyPlan = studyPlan.addCourseToStudyPlan(1, 1, course1, programme);
        boolean addCourse2ToStudyPlan = studyPlan.addCourseToStudyPlan(1, 1, course2, programme);
        boolean addCourse3ToStudyPlan = studyPlan.addCourseToStudyPlan(1, 1, course3, programme);

        // assert
        assertTrue(addCourse1ToStudyPlan);
        assertTrue(addCourse2ToStudyPlan);
        assertTrue(addCourse3ToStudyPlan);
    }

    @Test
    void shouldAllowRegisterAnnualCourseSpanningTwoSemesters() throws Exception {
        // arrange
        CourseRepository courseRepository = new CourseRepository();
        Course course1 = new Course("Annual Course", "ANNUAL", 12, 2);
        DegreeType master = new DegreeType("Master", 240);
        Department cse = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informática, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, cse);
        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, master, cse, teacher);
        StudyPlan studyPlan = programme.getStudyPlan();

        // act
        courseRepository.registerCourse("Annual Course", "ANNUAL", 12, 2);
        programme.addCourseToAProgramme(course1);
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
package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CourseEditionTest {


    //US19
    @Test
    void shouldCreateCourseEdition() throws Exception {
        //SUT = CourseEdition - ProgrammeEdition and Course isolated
        //Arrange
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        Course courseDouble = mock (Course.class);

        //Act
        CourseEdition courseEdition1 = new CourseEdition(courseDouble, programmeEditionDouble);

        //Assert
        assertNotNull(courseEdition1);

    }

    @Test
    void shouldThowExceptionIfCourseIsNull() throws Exception {
        //SUT = CourseEdition - ProgrammeEdition isolated and Course forced to be null
        //Arrange
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        Course course = null;

        //Act + Assert
        assertThrows(Exception.class, () -> {new CourseEdition(course, programmeEditionDouble);});

    }

    @Test
    void shouldThowExceptionIfProgrammeEditionIsNull() throws Exception {
        //SUT = CourseEdition - ProgrammeEdition forced to be null and Course isolated
        //Arrange
        ProgrammeEdition programmeEdition = null;
        Course courseDouble = mock (Course.class);
        //Assert
        assertThrows(Exception.class, () -> {new CourseEdition(courseDouble, programmeEdition);});

    }

    @Test
    void shouldThowExceptionIfProgrammeEditionAndCourseAreNull() throws Exception {
        //SUT = CourseEdition - ProgrammeEdition and Course forced to be null
        //Arrange
        ProgrammeEdition programmeEditionDouble = null;
        Course course = null;
        //Act + Assert
        assertThrows(Exception.class, () -> {new CourseEdition(course, programmeEditionDouble);});

    }

    @Test
    void shouldReturnTrueIfCourseEditionEqualsObject()throws Exception {
        //SUT = CourseEdition - ProgrammeEdition and Course isolated
        //Arrange
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        Course courseDouble = mock (Course.class);
        CourseEdition courseEdition1 = new CourseEdition(courseDouble, programmeEditionDouble);
        Object courseEdition2 = courseEdition1;

        //Act
        boolean result = courseEdition1.equals(courseEdition2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfObjectIsNotCourseEdition() throws Exception{
        //SUT = CourseEdition - ProgrammeEdition, Course and Teacher Category isolated
        //Arrange
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        Course courseDouble = mock (Course.class);
        CourseEdition courseEdition1 = new CourseEdition(courseDouble, programmeEditionDouble);
        TeacherCategory teacherCategoryDouble = mock (TeacherCategory.class);

        //Act
        boolean result = courseEdition1.equals(teacherCategoryDouble);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfCourseAndProgrammeEditionOfBothObjectsAreEqual() throws Exception{
        //SUT = CourseEdition - ProgrammeEdition and Course isolated
        //Arrange
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        Course courseDouble = mock (Course.class);
        CourseEdition courseEdition1 = new CourseEdition(courseDouble, programmeEditionDouble);
        CourseEdition courseEdition2 = new CourseEdition(courseDouble, programmeEditionDouble);

        //Act
        boolean result = courseEdition1.equals(courseEdition2);
        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfCoursesAreNotEqualButProgrammeEditionsAreEqual() throws Exception{
        //SUT = CourseEdition - ProgrammeEdition and Course isolated
        //Arrange
        ProgrammeEdition programmeEditionDouble1 = mock(ProgrammeEdition.class);
        Course courseDouble1 = mock (Course.class);
        Course courseDouble2 = mock (Course.class);

        CourseEdition courseEdition1 = new CourseEdition(courseDouble1, programmeEditionDouble1);
        CourseEdition courseEdition2 = new CourseEdition(courseDouble2, programmeEditionDouble1);

        //Act
        boolean result = courseEdition1.equals(courseEdition2);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfCoursesAreEqualButProgrammeEditionsAreNotEqual() throws Exception{
        //SUT = CourseEdition - ProgrammeEdition and Course isolated
        //Arrange
        ProgrammeEdition programmeEditionDouble1 = mock(ProgrammeEdition.class);
        ProgrammeEdition programmeEditionDouble2 = mock(ProgrammeEdition.class);
        Course courseDouble1 = mock (Course.class);

        CourseEdition courseEdition1 = new CourseEdition(courseDouble1, programmeEditionDouble1);
        CourseEdition courseEdition2 = new CourseEdition(courseDouble1, programmeEditionDouble2);

        //Act
        boolean result = courseEdition1.equals(courseEdition2);

        //Assert
        assertFalse(result);
    }

    //US20
    @Test
    void shouldReturnTrueIfRucIsSet() throws Exception {
        //SUT = CourseEdition - ProgrammeEdition, Course and Teacher isolated
        //Arrange
        ProgrammeEdition programmeEditionDouble1 = mock(ProgrammeEdition.class);
        Course courseDouble1 = mock (Course.class);
        Teacher rucDouble = mock (Teacher.class);

        CourseEdition courseEdition1 = new CourseEdition(courseDouble1, programmeEditionDouble1);

        //Act
        boolean result = courseEdition1.setRuc(rucDouble);

        //Assert
        assertTrue(result);
    }

    //US16
    @Test
    void shouldReturnTheProgrammeEditionThatBelongsToACourseEdition () throws Exception {
        //arrange
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", "24-03-2010", assistantProfessor, 80, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        Course c1 = new Course("Informatics", "INF", 6, 1);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition(p1, sY1);
        CourseEdition courseEdition1 = new CourseEdition(c1, pE1);

        //act
        ProgrammeEdition result = courseEdition1.whatProgrammeEditionBelongsThisCourseEdition();

        //assert
        assertEquals(pE1, result);
    }
}
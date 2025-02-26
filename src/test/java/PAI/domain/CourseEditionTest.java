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
        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        Course course = mock (Course.class);

        //Act
        CourseEdition courseEdition1 = new CourseEdition(course,programmeEdition);

        //Assert
        assertNotNull(courseEdition1);

    }

    @Test
    void shouldNotCreateCourseEditionIfCourseIsNull() throws Exception {
        //SUT = CourseEdition - ProgrammeEdition isolated and Course forced to be null
        //Arrange
        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        Course course = null;

        //Act + Assert
        assertThrows(Exception.class, () -> {new CourseEdition(course, programmeEdition);});

    }

    @Test
    void shouldNotCreateCourseEditionIfProgrammeEditionIsNull() throws Exception {
        //SUT = CourseEdition - ProgrammeEdition forced to be null and Course isolated
        //Arrange
        ProgrammeEdition programmeEdition = null;
        Course course = mock (Course.class);
        //Assert
        assertThrows(Exception.class, () -> {new CourseEdition(course, programmeEdition);});

    }

    @Test
    void shouldNotCreateCourseEditionIfProgrammeEditionAndCourseAreNull() throws Exception {
        //SUT = CourseEdition - ProgrammeEdition and Course forced to be null
        //Arrange
        ProgrammeEdition programmeEdition = null;
        Course course = null;
        //Act + Assert
        assertThrows(Exception.class, () -> {new CourseEdition(course, programmeEdition);});

    }

    @Test
    void shouldReturnTrueIfCourseEditionEqualsObject()throws Exception {
        //SUT = CourseEdition - ProgrammeEdition and Course isolated
        //Arrange
        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        Course course = mock (Course.class);
        CourseEdition courseEdition1 = new CourseEdition(course,programmeEdition);
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
        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        Course course = mock (Course.class);
        CourseEdition courseEdition1 = new CourseEdition(course,programmeEdition);
        TeacherCategory teacherCategory = mock (TeacherCategory.class);

        //Act
        boolean result = courseEdition1.equals(teacherCategory);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfCourseAndProgrammeEditionOfBothObjectsAreEqual() throws Exception{
        //SUT = CourseEdition - ProgrammeEdition and Course isolated
        //Arrange
        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        Course course = mock (Course.class);
        CourseEdition courseEdition1 = new CourseEdition(course,programmeEdition);
        CourseEdition courseEdition2 = new CourseEdition(course,programmeEdition);

        //Act
        boolean result = courseEdition1.equals(courseEdition2);
        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfCoursesAreNotEqualButProgrammeEditionsAreEqual() throws Exception{
        //SUT = CourseEdition - ProgrammeEdition and Course isolated
        //Arrange
        ProgrammeEdition programmeEdition1 = mock(ProgrammeEdition.class);
        Course course1 = mock (Course.class);
        Course course2 = mock (Course.class);

        CourseEdition courseEdition1 = new CourseEdition(course1,programmeEdition1);
        CourseEdition courseEdition2 = new CourseEdition(course2,programmeEdition1);

        //Act
        boolean result = courseEdition1.equals(courseEdition2);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfCoursesAreEqualButProgrammeEditionsAreNotEqual() throws Exception{
        //SUT = CourseEdition - ProgrammeEdition and Course isolated
        //Arrange
        ProgrammeEdition programmeEdition1 = mock(ProgrammeEdition.class);
        ProgrammeEdition programmeEdition2 = mock(ProgrammeEdition.class);
        Course course1 = mock (Course.class);

        CourseEdition courseEdition1 = new CourseEdition(course1,programmeEdition1);
        CourseEdition courseEdition2 = new CourseEdition(course1,programmeEdition2);

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
        ProgrammeEdition programmeEdition1 = mock(ProgrammeEdition.class);
        Course course1 = mock (Course.class);
        Teacher ruc = mock (Teacher.class);

        CourseEdition courseEdition1 = new CourseEdition(course1, programmeEdition1);

        //Act
        boolean result = courseEdition1.setRuc(ruc);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTheProgrammeEditionThatBelongsToACourseEdition () throws Exception {
        //arrange
        Course doubleC1 = mock(Course.class);
        ProgrammeEdition doublePE1 = mock(ProgrammeEdition.class);
        CourseEdition courseEdition1 = new CourseEdition(doubleC1, doublePE1);

        //act
        ProgrammeEdition result = courseEdition1.whatProgrammeEditionBelongsThisCourseEdition();

        //assert
        assertEquals(doublePE1, result);
    }
}
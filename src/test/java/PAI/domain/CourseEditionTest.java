package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CourseEditionTest {


    //US19
    @Test
    void shouldCreateCourseEdition() throws Exception {
        //SUT = CourseEdition -> ProgrammeEdition and Course as Doubles
        //Arrange
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        Course courseDouble = mock (Course.class);

        //Act
        CourseEdition courseEdition1 = new CourseEdition(courseDouble, programmeEditionDouble);

        //Assert
        assertNotNull(courseEdition1);

    }

    @Test
    void shouldThrowExceptionIfCourseIsNull(){
        //SUT = CourseEdition -> ProgrammeEdition as Double and Course forced to be null
        //Arrange
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);

        //Act + Assert
        assertThrows(Exception.class, () -> {new CourseEdition(null, programmeEditionDouble);});

    }

    @Test
    void shouldThrowExceptionIfProgrammeEditionIsNull() {
        //SUT = CourseEdition -> ProgrammeEdition forced to be null and Course as Double
        //Arrange
        Course courseDouble = mock (Course.class);

        //Act + Assert
        assertThrows(Exception.class, () -> {new CourseEdition(courseDouble, null);});

    }

    @Test
    void shouldThrowExceptionIfProgrammeEditionAndCourseAreNull() {
        //SUT = CourseEdition -> ProgrammeEdition and Course forced to be null
        //Arrange
        //Act + Assert
        assertThrows(Exception.class, () -> {new CourseEdition(null, null);});

    }

    @Test
    void shouldReturnTrueIfCourseEditionEqualsObject()throws Exception {
        //SUT = CourseEdition -> ProgrammeEdition and Course as Doubles
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
        //SUT = CourseEdition -> ProgrammeEdition, Course and Teacher Category as Doubles
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
        //SUT = CourseEdition -> ProgrammeEdition and Course as Doubles
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
        //SUT = CourseEdition -> ProgrammeEdition and Course as Doubles
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
        //SUT = CourseEdition -> ProgrammeEdition and Course as Doubles
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
        //SUT = CourseEdition -> ProgrammeEdition, Course and Teacher as Doubles
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
package PAI.domain;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseEdition_2Test {

    //US19
    @Test
    void shouldCreateCourseEdition() throws Exception {
        //SUT = CourseEdition -> ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock (CourseInStudyPlanID.class);

        //Act
        CourseEdition_2 courseEdition = new CourseEdition_2(courseInStudyPlanIDDouble, programmeEditionIDDouble);

        //Assert
        assertNotNull(courseEdition);

    }

    @Test
    void shouldThrowExceptionIfCourseInStudyPlanIDIsNull(){
        //SUT = CourseEdition -> ProgrammeEditionID as Double and CourseInStudyPlanID forced to be null
        //Arrange
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

        //Act + Assert
        assertThrows(Exception.class, () -> {new CourseEdition_2(null, programmeEditionIDDouble);});

    }

    @Test
    void shouldThrowExceptionIfProgrammeEditionIDIsNull() {
        //SUT = CourseEdition -> ProgrammeEditionID forced to be null and CourseInStudyPlanID as Double
        //Arrange
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock (CourseInStudyPlanID.class);

        //Act + Assert
        assertThrows(Exception.class, () -> {new CourseEdition_2(courseInStudyPlanIDDouble, null);});

    }

    @Test
    void shouldThrowExceptionIfProgrammeEditionIDAndCourseInStudyPlanIDAreNull() {
        //SUT = CourseEdition -> ProgrammeEditionID and CourseInStudyPlanID forced to be null
        //Arrange
        //Act + Assert
        assertThrows(Exception.class, () -> {new CourseEdition_2(null, null);});

    }

    @Test
    void shouldReturnIdentityNotNull() throws Exception {
        //SUT = CourseEdition -> CourseEditionID, ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock (CourseInStudyPlanID.class);
        CourseEdition_2 courseEdition = new CourseEdition_2(courseInStudyPlanIDDouble, programmeEditionIDDouble);

        //Act
        CourseEditionID courseEditionID = courseEdition.identity();

        //Assert
        assertNotNull(courseEditionID);
    }

    @Test
    void shouldReturnCourseEditionID() throws Exception {
        //SUT = CourseEdition -> CourseEditionID, ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock (CourseInStudyPlanID.class);
        CourseEdition_2 courseEdition = new CourseEdition_2(courseInStudyPlanIDDouble, programmeEditionIDDouble);

        //Act
        CourseEditionID courseEditionID = courseEdition.identity();

        //Assert
        assertEquals(courseEditionID, courseEdition.identity());
    }

    @Test
    void testIdentityThrowsExceptionWithSpy() throws Exception {
        //Arrange
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock (CourseInStudyPlanID.class);

        //Act
            //Spy simulates error identity()
        CourseEdition_2 courseEdition = spy(new CourseEdition_2(courseInStudyPlanIDDouble, programmeEditionIDDouble));
        doThrow(new RuntimeException("sim error")).when(courseEdition).identity();

        //Assert
        assertThrows(RuntimeException.class, courseEdition::identity);
    }

    @Test
    void shouldReturnTrueIfCourseEditionSameAsObject()throws Exception {
        //SUT = CourseEdition -> ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock (CourseInStudyPlanID.class);

        CourseEdition_2 courseEdition = new CourseEdition_2(courseInStudyPlanIDDouble, programmeEditionIDDouble);
        Object courseEdition2 = courseEdition;

        //Act
        boolean result = courseEdition.sameAs(courseEdition2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfObjectIsNotSameAsCourseEdition() throws Exception{
        //SUT = CourseEdition -> ProgrammeEditionID, CourseinStudyPlanID and TeacherCategory as Doubles
        //Arrange
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock (CourseInStudyPlanID.class);

        CourseEdition_2 courseEdition = new CourseEdition_2(courseInStudyPlanIDDouble, programmeEditionIDDouble);
        TeacherCategory teacherCategoryDouble = mock (TeacherCategory.class);

        //Act
        boolean result = courseEdition.sameAs(teacherCategoryDouble);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfCourseInStudyPlanIDAndProgrammeEditionIDOfBothObjectsAreTheSame() throws Exception{
        //SUT = CourseEdition -> ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock (CourseInStudyPlanID.class);

        CourseEdition_2 courseEdition1 = new CourseEdition_2(courseInStudyPlanIDDouble, programmeEditionIDDouble);
        CourseEdition_2 courseEdition2 = new CourseEdition_2(courseInStudyPlanIDDouble, programmeEditionIDDouble);

        //Act
        boolean result = courseEdition1.sameAs(courseEdition2);
        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfCourseInStudyPlanIDAreNotSameButProgrammeEditionIDAreTheSame() throws Exception{
        //SUT = CourseEdition -> ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble1 = mock (CourseInStudyPlanID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble2 = mock (CourseInStudyPlanID.class);

        CourseEdition_2 courseEdition1 = new CourseEdition_2(courseInStudyPlanIDDouble1, programmeEditionIDDouble);
        CourseEdition_2 courseEdition2 = new CourseEdition_2(courseInStudyPlanIDDouble2, programmeEditionIDDouble);

        //Act
        boolean result = courseEdition1.sameAs(courseEdition2);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfCourseInStudyPlanIDAreTheSameButProgrammeEditionIDAreNotSame() throws Exception{
        //SUT = CourseEdition -> ProgrammeEditionID and CourseInStudyPlanID as Doubles
        //Arrange
        ProgrammeEditionID programmeEditionIDDouble1 = mock(ProgrammeEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble2 = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble1 = mock (CourseInStudyPlanID.class);

        CourseEdition_2 courseEdition1 = new CourseEdition_2(courseInStudyPlanIDDouble1, programmeEditionIDDouble1);
        CourseEdition_2 courseEdition2 = new CourseEdition_2(courseInStudyPlanIDDouble1, programmeEditionIDDouble2);

        //Act
        boolean result = courseEdition1.sameAs(courseEdition2);

        //Assert
        assertFalse(result);
    }

}
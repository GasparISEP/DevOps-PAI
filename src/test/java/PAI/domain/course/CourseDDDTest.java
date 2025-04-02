package PAI.domain.course;

import PAI.VOs.*;
import PAI.domain.CourseEdition_2;
import PAI.domain.TeacherCategory;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseDDDTest {

    @Test
    void shouldCreateValidCourse() throws Exception {
        //arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);
        //act
        CourseDDD courseDDD = new CourseDDD(courseID, name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);
        // Assert
        assertNotNull(courseDDD);
    }

    @Test
    void shouldThrowExceptionIfCourseIdIsInvalid() throws Exception{
        //arrange
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);
        //act + assert
        assertThrows(Exception.class, () -> new CourseDDD(null,name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear));
    }

    @Test
    void shouldThrowExceptionIfCourseNameIsInvalid() throws Exception{
        //arrange
        CourseID courseID = mock(CourseID.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);
        //act + assert
        assertThrows(Exception.class, () -> new CourseDDD(courseID,null, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear));
    }

    @Test
    void shouldThrowExceptionIfCourseAcronymIsInvalid() throws Exception{
        //arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);
        //act + assert
        assertThrows(Exception.class, () -> new CourseDDD(courseID,name, null, courseQuantityCreditsEcts, durationCourseInCurricularYear));
    }

    @Test
    void shouldThrowExceptionIfCourseQuantityCreditsEctsIsInvalid() throws Exception{
        //arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);
        //act + assert
        assertThrows(Exception.class, () -> new CourseDDD(courseID,name, acronym, null, durationCourseInCurricularYear));
    }

    @Test
    void shouldThrowExceptionIfCourseDurationInCurricularYearIsInvalid() throws Exception{
        //arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        //act + assert
        assertThrows(Exception.class, () -> new CourseDDD(courseID,name, acronym, courseQuantityCreditsEcts, null));
    }

    @Test
    void shouldReturnCorrectIdentity() throws Exception {
        // Arrange
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);
        CourseID courseID = mock(CourseID.class);

        //Act
        CourseDDD courseDDD = new CourseDDD(courseID,name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);

        // Assert
        assertNotNull(courseDDD.identity());
    }

    @Test
    void shouldReturnIdentityNotNull() {
        //SUT = Course -> CourseID, Name , Acronym, CourseQuantityCreditsEcts and DurationCourseInCurricularYear as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);

        CourseDDD courseDDD = new CourseDDD(courseID,name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);

        //Act
        CourseID courseIdentity = courseDDD.identity();

        //Assert
        assertNotNull(courseIdentity);
    }

    @Test
    void shouldReturnCourseID() {
        //SUT = Course -> CourseID, Name , Acronym, CourseQuantityCreditsEcts and DurationCourseInCurricularYear as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);

        CourseDDD courseDDD = new CourseDDD(courseID,name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);

        //Act
        CourseID courseIdentity = courseDDD.identity();

        //Assert
        assertEquals(courseIdentity, courseDDD.identity());
    }

    @Test
    void shouldReturnExceptionWithSpyInCourse() {
        //SUT = Course -> CourseID, Name , Acronym, CourseQuantityCreditsEcts and DurationCourseInCurricularYear as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);

        //Act
        //Spy simulates error identity()
        CourseDDD courseDDD = spy(new CourseDDD(courseID,name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear));
        doThrow(new RuntimeException("sim error")).when(courseDDD).identity();

        //Assert
        assertThrows(RuntimeException.class, courseDDD::identity);
    }

    @Test
    void shouldReturnTrueEqualsWhenComparedWithSameObject() {
        //SUT = Course -> CourseID, Name , Acronym, CourseQuantityCreditsEcts and DurationCourseInCurricularYear as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);

        CourseDDD courseDDD = new CourseDDD(courseID,name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);

        //Act
        boolean result = courseDDD.equals(courseDDD);
        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseForEqualsWithNullObjectToCompare() {
        //SUT = Course -> CourseID, Name , Acronym, CourseQuantityCreditsEcts and DurationCourseInCurricularYear as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);

        CourseDDD courseDDD = new CourseDDD(courseID,name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);
        //Act
        boolean result = courseDDD.equals(null);
        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueEqualsWhenComparedWithSameInstanceOfObject() {
        //SUT = Course -> CourseID, Name , Acronym, CourseQuantityCreditsEcts and DurationCourseInCurricularYear as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);

        CourseDDD courseDDD = new CourseDDD(courseID,name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);
        CourseDDD courseDDD2 = new CourseDDD(courseID, name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);
        //Act
        boolean result = courseDDD.equals(courseDDD2);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueForEqualsOfCourseWithSameParameters() {
        //SUT = Course -> CourseID, Name , Acronym, CourseQuantityCreditsEcts and DurationCourseInCurricularYear as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);

        CourseDDD courseDDD = new CourseDDD(courseID,name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);
        CourseDDD courseDDD2 = new CourseDDD(courseID, name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);
        //Act
        boolean result = courseDDD.equals(courseDDD2);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfClassesAreDifferent() {
        //SUT = Course -> CourseID, Name , Acronym, CourseQuantityCreditsEcts and DurationCourseInCurricularYear as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);

        CourseDDD courseDDD = new CourseDDD(courseID,name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);
        CourseEdition_2 courseEdition = mock (CourseEdition_2.class);

        //Act
        boolean result = courseDDD.equals(courseEdition);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfCourseIDsAreNotTheSame() {
        //SUT = Course -> CourseID, Name , Acronym, CourseQuantityCreditsEcts and DurationCourseInCurricularYear as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        CourseID courseID2 = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);

        CourseDDD courseDDD = new CourseDDD(courseID,name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);
        CourseDDD courseDDD2 = new CourseDDD(courseID2, name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);
        //Act
        boolean result = courseDDD.equals(courseDDD2);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfCourseSameAsObject() {
        //SUT = Course -> CourseID, Name , Acronym, CourseQuantityCreditsEcts and DurationCourseInCurricularYear as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);

        CourseDDD courseDDD = new CourseDDD(courseID,name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);
        Object courseDDD2 = courseDDD;

        //Act
        boolean result = courseDDD.sameAs(courseDDD2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfObjectIsNotSameAsCourse() {
        //SUT = Course -> CourseID, Name , Acronym, CourseQuantityCreditsEcts and DurationCourseInCurricularYear as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);

        CourseDDD courseDDD = new CourseDDD(courseID,name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);
        CourseEdition_2 courseEdition = mock(CourseEdition_2.class);

        //Act
        boolean result = courseDDD.sameAs(courseEdition);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfNameAcronymCourseQuantityCreditsEctsAndDurationCourseInCurricularYearOfBothObjectsAreTheSame() {
        //SUT = Course -> CourseID, Name , Acronym, CourseQuantityCreditsEcts and DurationCourseInCurricularYear as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);

        CourseDDD courseDDD = new CourseDDD(courseID,name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);
        CourseDDD courseDDD2 = new CourseDDD(courseID,name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);

        //Act
        boolean result = courseDDD.sameAs(courseDDD2);
        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfNameAreNotSameButAcronymCourseQuantityCreditsEctsAndDurationCourseInCurricularYearAreTheSame() {
        //SUT = Course -> CourseID, Name , Acronym, CourseQuantityCreditsEcts and DurationCourseInCurricularYear as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Name name2 = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);

        CourseDDD courseDDD = new CourseDDD(courseID,name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);
        CourseDDD courseDDD2 = new CourseDDD(courseID,name2, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);

        //Act
        boolean result = courseDDD.sameAs(courseDDD2);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfAcronymAreNotSameButNameCourseQuantityCreditsEctsAndDurationCourseInCurricularYearAreTheSame() {
        //SUT = Course -> CourseID, Name , Acronym, CourseQuantityCreditsEcts and DurationCourseInCurricularYear as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        Acronym acronym2 = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);

        CourseDDD courseDDD = new CourseDDD(courseID,name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);
        CourseDDD courseDDD2 = new CourseDDD(courseID,name, acronym2, courseQuantityCreditsEcts, durationCourseInCurricularYear);

        //Act
        boolean result = courseDDD.sameAs(courseDDD2);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfCourseQuantityCreditsEctsAreNotSameButNameAcronymAndDurationCourseInCurricularYearAreTheSame() {
        //SUT = Course -> CourseID, Name , Acronym, CourseQuantityCreditsEcts and DurationCourseInCurricularYear as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts2 = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);

        CourseDDD courseDDD = new CourseDDD(courseID,name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);
        CourseDDD courseDDD2 = new CourseDDD(courseID,name, acronym, courseQuantityCreditsEcts2, durationCourseInCurricularYear);

        //Act
        boolean result = courseDDD.sameAs(courseDDD2);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfNameAcronymAndCourseQuantityCreditsEctsAreTheSameButDurationCourseInCurricularYearAreNotSame() {
        //SUT = Course -> CourseID, Name , Acronym, CourseQuantityCreditsEcts and DurationCourseInCurricularYear as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear2 = mock(DurationCourseInCurricularYear.class);

        CourseDDD courseDDD = new CourseDDD(courseID,name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);
        CourseDDD courseDDD2 = new CourseDDD(courseID,name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear2);

        //Act
        boolean result = courseDDD.sameAs(courseDDD2);

        //Assert
        assertFalse(result);
    }

}
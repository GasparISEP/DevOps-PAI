package PAI.domain.course;

import PAI.VOs.*;
import PAI.domain.CourseEdition_2;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseDDDTest {

    @Test
    void shouldCreateCourseWithoutCourseIDAsParameter() {
        //SUT = Course -> Name , Acronym, CourseQuantityCreditsEcts and DurationCourseInCurricularYear as Doubles
        //Arrange
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);

        //Act
        CourseDDD courseDDD = new CourseDDD(name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);

        //Assert
        assertNotNull(courseDDD);
    }

    @Test
    void shouldCreateCourseWithCourseIDAsParameter() {
        //SUT = Course ->CourseID, Name , Acronym, CourseQuantityCreditsEcts and DurationCourseInCurricularYear as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);

        //Act
        CourseDDD courseDDD = new CourseDDD(courseID, name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);

        //Assert
        assertNotNull(courseDDD);
    }

    @Test
    void shouldCreateValidCourse() throws Exception {
        //SUT = Course -> CourseID, Name , Acronym, CourseQuantityCreditsEcts and DurationCourseInCurricularYear as Doubles
        //Arrange
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
        //SUT = Course -> Name , Acronym, CourseQuantityCreditsEcts and DurationCourseInCurricularYear as Doubles
        //Arrange
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);
        //act + assert
        assertThrows(Exception.class, () -> new CourseDDD(null,name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear));
    }

    @Test
    void shouldThrowExceptionIfCourseNameIsInvalidWithoutCourseIDAsParameter() throws Exception{
        //SUT = Course -> Acronym, CourseQuantityCreditsEcts and DurationCourseInCurricularYear as Doubles
        //Arrange
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);
        //act + assert
        assertThrows(Exception.class, () -> new CourseDDD(null, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear));
    }

    @Test
    void shouldThrowExceptionIfCourseNameIsInvalidWithCourseIDAsParameter() throws Exception{
        //SUT = Course -> CourseID, Acronym, CourseQuantityCreditsEcts and DurationCourseInCurricularYear as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);
        //act + assert
        assertThrows(Exception.class, () -> new CourseDDD(courseID,null, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear));
    }

    @Test
    void shouldThrowExceptionIfCourseAcronymIsInvalidWithoutCourseIDAsParameter() throws Exception{
        //SUT = Course -> Name, CourseQuantityCreditsEcts and DurationCourseInCurricularYear as Doubles
        //Arrange
        Name name = mock(Name.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);
        //act + assert
        assertThrows(Exception.class, () -> new CourseDDD(name, null, courseQuantityCreditsEcts, durationCourseInCurricularYear));
    }

    @Test
    void shouldThrowExceptionIfCourseAcronymIsInvalidWithCourseIDAsParameter() throws Exception{
        //SUT = Course -> CourseID, Name, CourseQuantityCreditsEcts and DurationCourseInCurricularYear as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);
        //act + assert
        assertThrows(Exception.class, () -> new CourseDDD(courseID,name, null, courseQuantityCreditsEcts, durationCourseInCurricularYear));
    }

    @Test
    void shouldThrowExceptionIfCourseQuantityCreditsEctsIsInvalidWithoutCourseIDAsParameter() throws Exception{
        //SUT = Course -> Name , Acronym and DurationCourseInCurricularYear as Doubles
        //Arrange
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);
        //act + assert
        assertThrows(Exception.class, () -> new CourseDDD(name, acronym, null, durationCourseInCurricularYear));
    }

    @Test
    void shouldThrowExceptionIfCourseQuantityCreditsEctsIsInvalidWithCourseIDAsParameter() throws Exception{
        //SUT = Course -> CourseID, Name , Acronym and DurationCourseInCurricularYear as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);
        //act + assert
        assertThrows(Exception.class, () -> new CourseDDD(courseID, name, acronym, null, durationCourseInCurricularYear));
    }

    @Test
    void shouldThrowExceptionIfCourseDurationInCurricularYearIsInvalidWithoutCourseIDAsParameter() throws Exception{
        //SUT = Course -> Name , Acronym and CourseQuantityCreditsEcts as Doubles
        //Arrange
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        //act + assert
        assertThrows(Exception.class, () -> new CourseDDD(name, acronym, courseQuantityCreditsEcts, null));
    }

    @Test
    void shouldThrowExceptionIfCourseDurationInCurricularYearIsInvalidWithCourseIDAsParameter() throws Exception{
        //SUT = Course -> CourseID, Name , Acronym and CourseQuantityCreditsEcts as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        //act + assert
        assertThrows(Exception.class, () -> new CourseDDD(courseID,name, acronym, courseQuantityCreditsEcts, null));
    }

    @Test
    void shouldReturnCorrectIdentity() throws Exception {
        //SUT = Course -> CourseID, Name , Acronym, CourseQuantityCreditsEcts and DurationCourseInCurricularYear as Doubles
        //Arrange
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

    @Test
    void shouldReturnNameFromCourse() {
        //SUT = Course -> CourseID, Name , Acronym, CourseQuantityCreditsEcts and DurationCourseInCurricularYear as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);

        //Act
        CourseDDD courseDDD = new CourseDDD(courseID,name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);

        //Assert
        assertEquals(name, courseDDD.getName());
    }

    @Test
    void shouldReturnAcronymFromCourse() {
        //SUT = Course -> CourseID, Name , Acronym, CourseQuantityCreditsEcts and DurationCourseInCurricularYear as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);

        //Act
        CourseDDD courseDDD = new CourseDDD(courseID,name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);

        //Assert
        assertEquals(acronym, courseDDD.getAcronym());
    }

    @Test
    void shouldReturnCourseQuantityCreditsEctsFromCourse() {
        //SUT = Course -> CourseID, Name , Acronym, CourseQuantityCreditsEcts and DurationCourseInCurricularYear as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);

        //Act
        CourseDDD courseDDD = new CourseDDD(courseID,name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);

        //Assert
        assertEquals(courseQuantityCreditsEcts, courseDDD.getCourseQuantityCreditsEcts());
    }

    @Test
    void shouldReturnDurationCourseInCurricularYearFromCourse() {
        //SUT = Course -> CourseID, Name , Acronym, CourseQuantityCreditsEcts and DurationCourseInCurricularYear as Doubles
        //Arrange
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);

        //Act
        CourseDDD courseDDD = new CourseDDD(courseID,name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);

        //Assert
        assertEquals(durationCourseInCurricularYear, courseDDD.getDurationCourseInCurricularYear());
    }

}
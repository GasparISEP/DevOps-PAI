package PAI.domain;

import PAI.VOs.*;
import PAI.domain.programme.ProgrammeDDD;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEnrolmentTest {

    private StudentID _studentIDDouble;
    private AccessMethodID _accessMethodIDDouble;
    private ProgrammeID _programmeIDDouble;
    private Date _dateDouble;

    private void createDoubles() {
        _studentIDDouble = mock(StudentID.class);
        _accessMethodIDDouble = mock(AccessMethodID.class);
        _programmeIDDouble = mock(ProgrammeID.class);
        _dateDouble = mock(Date.class);
    }

    @Test
    void constructorAlwaysCreatesAnObjectTest() {
        //arrange
        createDoubles();

        //act
        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(_studentIDDouble, _accessMethodIDDouble, _programmeIDDouble, _dateDouble);

        //assert
        assertNotNull(programmeEnrolment);
    }

    @Test
    void shouldReturnExceptionIfStudentNullTest () {
        //arrange
        createDoubles();

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolment(null, _accessMethodIDDouble, _programmeIDDouble, _dateDouble));
    }

    @Test
    void shouldReturnExceptionIfAccessMethodNullTest () {
        //arrange
        createDoubles();

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolment(_studentIDDouble, null, _programmeIDDouble, _dateDouble));
    }

    @Test
    void shouldReturnExceptionIfProgrammeNullTest () {
        //arrange
        createDoubles();

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolment(_studentIDDouble, _accessMethodIDDouble, null, _dateDouble));
    }

    @Test
    void invalidDateDoesNotCreateObjectAndThrowsExceptionTest() {
        //arrange
        createDoubles();

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolment(_studentIDDouble, _accessMethodIDDouble, _programmeIDDouble, null));
    }

    @Test
    void shouldReturnTrueWhenDateIsAfterTest() {
        // Arrange
        createDoubles();
        Date date2 = mock(Date.class);
        
        when(_dateDouble.getLocalDate()).thenReturn(LocalDate.of(2025, 4, 1));
        when(date2.getLocalDate()).thenReturn(LocalDate.of(2025, 3, 31));
        
        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(_studentIDDouble, _accessMethodIDDouble, _programmeIDDouble, _dateDouble);

        // Act
        boolean result = programmeEnrolment.isDateAfter(date2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenDateIsBeforeTest() {
        // Arrange
        createDoubles();
        Date date2 = mock(Date.class);
        
        when(_dateDouble.getLocalDate()).thenReturn(LocalDate.of(2024, 4, 1));
        when(date2.getLocalDate()).thenReturn(LocalDate.of(2025, 3, 31));
        
        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(_studentIDDouble, _accessMethodIDDouble, _programmeIDDouble, _dateDouble);

        // Act
        boolean result = programmeEnrolment.isDateAfter(date2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfStudentIsTheSameFromEnrolmentTestV2() {
        // arrange
        createDoubles();
        Student studentDouble = mock(Student.class);
        
        when(studentDouble.identity()).thenReturn(_studentIDDouble);

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(_studentIDDouble, _accessMethodIDDouble, _programmeIDDouble, _dateDouble);

        // act
        boolean result = programmeEnrolment.hasSameStudent(_studentIDDouble);

        // assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfStudentIsNotTheSameFromEnrolmentTest() {
        //arrange
        createDoubles();
        StudentID studentIDDouble2 = mock(StudentID.class);
        
        ProgrammeEnrolment programmeEnrolmentDouble = new ProgrammeEnrolment(_studentIDDouble, _accessMethodIDDouble, _programmeIDDouble, _dateDouble);

        //act
        boolean result = programmeEnrolmentDouble.hasSameStudent(studentIDDouble2);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfStudentIsNotTheSameFromEnrolmentTestV2() {
        //arrange
        createDoubles();
        StudentID studentIDDouble2 = mock(StudentID.class);

        ProgrammeEnrolment programmeEnrolmentDouble = new ProgrammeEnrolment(_studentIDDouble, _accessMethodIDDouble, _programmeIDDouble, _dateDouble);

        //act
        boolean result = programmeEnrolmentDouble.hasSameStudent(studentIDDouble2);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfEnrolmentHasTheSameStudentAndTheSameProgrammeTest() {
        //arrange
        createDoubles();

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(_studentIDDouble, _accessMethodIDDouble, _programmeIDDouble, _dateDouble);
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(_studentIDDouble, _accessMethodIDDouble, _programmeIDDouble, _dateDouble);

        //act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentHasDifferentStudentsButTheSameProgrammeTest() {
        //arrange
        createDoubles();
        StudentID studentDouble2 = mock(StudentID.class);
        

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(_studentIDDouble, _accessMethodIDDouble, _programmeIDDouble, _dateDouble);
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(studentDouble2, _accessMethodIDDouble, _programmeIDDouble, _dateDouble);

        when(_studentIDDouble.isEquals(studentDouble2)).thenReturn(false);

        //act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentHasTheSameStudentButDifferentProgrammesTest() {
        // arrange
        createDoubles();
        ProgrammeID programmeDouble2 = mock(ProgrammeID.class);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(_studentIDDouble, _accessMethodIDDouble, _programmeIDDouble, _dateDouble);
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(_studentIDDouble, _accessMethodIDDouble, programmeDouble2, _dateDouble);

        when(_studentIDDouble.isEquals(_studentIDDouble)).thenReturn(true);

        // act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        // assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentHasBothDifferentStudentsAndDifferentProgrammesTest() {
        // arrange
        createDoubles();
        StudentID studentDouble2 = mock(StudentID.class);
        ProgrammeID programmeDouble2 = mock(ProgrammeID.class);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(_studentIDDouble, _accessMethodIDDouble, _programmeIDDouble, _dateDouble);
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(studentDouble2, _accessMethodIDDouble, programmeDouble2, _dateDouble);

        // act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        // assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfProgrammesAreTheSameTestV2() {
        //arrange
        createDoubles();
        ProgrammeDDD programmeDouble = mock(ProgrammeDDD.class);

        when(programmeDouble.getProgrammeID()).thenReturn(_programmeIDDouble);

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(_studentIDDouble, _accessMethodIDDouble, _programmeIDDouble, _dateDouble);

        //act
        boolean result = programmeEnrolment.hasSameProgramme(programmeDouble.getProgrammeID());

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfProgrammesAreNotTheSameTestV2() {
        //arrange
        createDoubles();
        ProgrammeID programmeIDDouble2 = mock(ProgrammeID.class);

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(_studentIDDouble, _accessMethodIDDouble, programmeIDDouble2, _dateDouble);
        
        //act
        boolean result = programmeEnrolment.hasSameProgramme(_programmeIDDouble);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnStudentIDfromGetterTest() {
        //Arrange
        createDoubles();
        ProgrammeEnrolment pe1 = new ProgrammeEnrolment(_studentIDDouble, _accessMethodIDDouble, _programmeIDDouble, _dateDouble);

        //Act
        boolean result = pe1.getStudentID().equals(_studentIDDouble);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnAccessMethodIDfromGetterTest() {
        //Arrange
        createDoubles();
        ProgrammeEnrolment pe1 = new ProgrammeEnrolment(_studentIDDouble, _accessMethodIDDouble, _programmeIDDouble, _dateDouble);

        //Act
        boolean result = pe1.getAccessMethodID().equals(_accessMethodIDDouble);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnProgrammeIDfromGetterTest() {
        //Arrange
        createDoubles();
        ProgrammeEnrolment pe1 = new ProgrammeEnrolment(_studentIDDouble, _accessMethodIDDouble, _programmeIDDouble, _dateDouble);

        //Act
        boolean result = pe1.getProgrammeID().equals(_programmeIDDouble);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnDatefromGetterTest() {
        //Arrange
        createDoubles();
        ProgrammeEnrolment pe1 = new ProgrammeEnrolment(_studentIDDouble, _accessMethodIDDouble, _programmeIDDouble, _dateDouble);

        //Act
        boolean result = pe1.getDate().equals(_dateDouble);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnProgrammeEnrolmentIDTest() {
        //Arrange
        createDoubles();
        ProgrammeEnrolment pe1 = new ProgrammeEnrolment(_studentIDDouble, _accessMethodIDDouble, _programmeIDDouble, _dateDouble);
        ProgrammeEnrolmentID expectedPeID = pe1.identity();

        //Act
        ProgrammeEnrolmentID result = pe1.identity();

        //Assert
        assertEquals(expectedPeID, result);
        assertNotNull(result);
    }

    @Test
    void shouldReturnTrueForSameProgrammeEnrolmentTest() {
        //Arrange
        createDoubles();
        ProgrammeEnrolment pe1 = new ProgrammeEnrolment(_studentIDDouble, _accessMethodIDDouble, _programmeIDDouble, _dateDouble);

        //Act
        boolean result = pe1.sameAs(pe1);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseForDifferentProgrammeEnrolmentTest() {
        //Arrange
        createDoubles();

        ProgrammeEnrolment pe1 = new ProgrammeEnrolment(_studentIDDouble, _accessMethodIDDouble, _programmeIDDouble, _dateDouble);
        ProgrammeEnrolment pe2 = new ProgrammeEnrolment(_studentIDDouble, _accessMethodIDDouble, _programmeIDDouble, _dateDouble);

        //Act
        boolean result = pe1.sameAs(pe2);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseForDifferentObjectsTest() {
        //Arrange
        createDoubles();

        ProgrammeEnrolment pe1 = new ProgrammeEnrolment(_studentIDDouble, _accessMethodIDDouble, _programmeIDDouble, _dateDouble);
        Object toCompare = new Object();

        //Act
        boolean result = pe1.sameAs(toCompare);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnHashCodeProgrammeEnrolmentTest() {
        //Arrange
        StudentID studentID = new StudentID(1241204);
        AccessMethodID accessMethodID = new AccessMethodID();
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("ola");
        Acronym acronym = new Acronym("HI");
        ProgrammeID programmeID = new ProgrammeID(name, acronym);
        Date dateDouble = new Date(LocalDate.now());

        ProgrammeEnrolment pe1 = new ProgrammeEnrolment(studentID, accessMethodID, programmeID, dateDouble);
        ProgrammeEnrolment pe2 = new ProgrammeEnrolment(studentID, accessMethodID, programmeID, dateDouble);

        //Act + Assert
        assertNotEquals(pe1.hashCode(), pe2.hashCode());
    }

    @Test
    void shouldReturnNullIdentity() {
        // Arrange
        ProgrammeEnrolment programmeEnrolment = mock(ProgrammeEnrolment.class);
        when(programmeEnrolment.identity()).thenReturn(null);

        // Act
        ProgrammeEnrolmentID result = programmeEnrolment.identity();

        // Assert
        assertNull(result);
    }

    @Test
    void shouldReturnNullIdentity2() {
        // Arrange
        ProgrammeEnrolment programmeEnrolment = mock(ProgrammeEnrolment.class);
        when(programmeEnrolment.identity()).thenReturn(null);

        // Act
        ProgrammeEnrolmentID result = programmeEnrolment.identity();

        // Assert
        assertNull(result);
    }
}
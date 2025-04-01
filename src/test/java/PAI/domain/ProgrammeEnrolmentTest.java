package PAI.domain;

import PAI.VOs.*;
import PAI.domain.accessMethodDDD.AccessMethodDDDFactoryImpl;
import PAI.domain.programme.ProgrammeDDD;
import PAI.VOs.Location;
import PAI.factory.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEnrolmentTest {

    @Test
    void constructorAlwaysCreatesAnObjectTest() {
        //arrange
        StudentID studentDouble = mock(StudentID.class);
        AccessMethodID accessMethodDouble = mock(AccessMethodID.class);
        ProgrammeID programmeDouble = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);

        //act
        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, dateDouble);

        //assert
        assertNotNull(programmeEnrolment);
    }

    @Test
    void shouldReturnExceptionIfStudentNullTest () {
        //arrange
        AccessMethodID accessMethodDouble = mock(AccessMethodID.class);
        ProgrammeID programmeDouble = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolment(null, accessMethodDouble, programmeDouble, dateDouble));
    }

    @Test
    void shouldReturnExceptionIfAccessMethodNullTest () {
        //arrange
        StudentID studentDouble = mock(StudentID.class);
        ProgrammeID programmeDouble = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolment(studentDouble, null, programmeDouble, dateDouble));
    }

    @Test
    void shouldReturnExceptionIfProgrammeNullTest () {
        //arrange
        StudentID studentDouble = mock(StudentID.class);
        AccessMethodID accessMethodDouble = mock(AccessMethodID.class);
        Date dateDouble = mock(Date.class);

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolment(studentDouble, accessMethodDouble, null, dateDouble));
    }

    @Test
    void invalidDateDoesNotCreateObjectAndThrowsExceptionTest() {
        //arrange
        StudentID studentDouble = mock(StudentID.class);
        AccessMethodID accessMethodDouble = mock(AccessMethodID.class);
        ProgrammeID programmeDouble = mock(ProgrammeID.class);

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, null));
    }

    @Test
    void shouldReturnTrueWhenDateIsAfterTest() {
        // Arrange
        Date date1 = mock(Date.class);
        Date date2 = mock(Date.class);
        when(date1.getLocalDate()).thenReturn(LocalDate.of(2025, 4, 1));
        when(date2.getLocalDate()).thenReturn(LocalDate.of(2025, 3, 31));
        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(mock(StudentID.class), mock(AccessMethodID.class), mock(ProgrammeID.class), date1);

        // Act
        boolean result = programmeEnrolment.isDateAfter(date2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenDateIsBeforeTest() {
        // Arrange
        Date date1 = mock(Date.class);
        Date date2 = mock(Date.class);
        when(date1.getLocalDate()).thenReturn(LocalDate.of(2024, 4, 1));
        when(date2.getLocalDate()).thenReturn(LocalDate.of(2025, 3, 31));
        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(mock(StudentID.class), mock(AccessMethodID.class), mock(ProgrammeID.class), date1);

        // Act
        boolean result = programmeEnrolment.isDateAfter(date2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfStudentIsTheSameFromEnrolmentTest() {
        // arrange
        StudentID studentDoubleID = mock(StudentID.class);
        Student studentDouble = mock(Student.class);
        AccessMethodID accessMethodDouble = mock(AccessMethodID.class);
        ProgrammeID programmeDouble = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);
        when(studentDouble.identity()).thenReturn(studentDoubleID);

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(studentDoubleID, accessMethodDouble, programmeDouble, dateDouble);

        // act
        boolean result = programmeEnrolment.hasSameStudent(studentDouble);

        // assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueIfStudentIsTheSameFromEnrolmentTestV2() {
        // arrange
        StudentID studentDoubleID = mock(StudentID.class);
        Student studentDouble = mock(Student.class);
        AccessMethodID accessMethodDouble = mock(AccessMethodID.class);
        ProgrammeID programmeDouble = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);
        when(studentDouble.identity()).thenReturn(studentDoubleID);

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(studentDoubleID, accessMethodDouble, programmeDouble, dateDouble);

        // act
        boolean result = programmeEnrolment.hasSameStudent2(studentDoubleID);

        // assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfStudentIsNotTheSameFromEnrolmentTest() {
        //arrange

        StudentID studentDouble = mock(StudentID.class);
        Student studentDouble2 = mock(Student.class);
        AccessMethodID accessMethodDouble = mock(AccessMethodID.class);
        ProgrammeID programmeDouble = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);

        ProgrammeEnrolment programmeEnrolmentDouble = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, dateDouble);

        //act
        boolean result = programmeEnrolmentDouble.hasSameStudent(studentDouble2);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfStudentIsNotTheSameFromEnrolmentTestV2() {
        //arrange

        StudentID studentIDDouble = mock(StudentID.class);
        StudentID studentIDDouble2 = mock(StudentID.class);
        AccessMethodID accessMethodDouble = mock(AccessMethodID.class);
        ProgrammeID programmeDouble = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);

        ProgrammeEnrolment programmeEnrolmentDouble = new ProgrammeEnrolment(studentIDDouble, accessMethodDouble, programmeDouble, dateDouble);

        //act
        boolean result = programmeEnrolmentDouble.hasSameStudent2(studentIDDouble2);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfEnrolmentHasTheSameStudentAndTheSameProgrammeTest() {
        //arrange
        StudentID studentDouble = mock(StudentID.class);
        AccessMethodID accessMethodDouble = mock(AccessMethodID.class);
        ProgrammeID programmeDouble = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, dateDouble);
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, dateDouble);

        //act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentHasDifferentStudentsButTheSameProgrammeTest() {
        //arrange
        StudentID studentDouble = mock(StudentID.class);
        StudentID studentDouble2 = mock(StudentID.class);
        AccessMethodID accessMethodDouble = mock(AccessMethodID.class);
        ProgrammeID programmeDouble = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, dateDouble);
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(studentDouble2, accessMethodDouble, programmeDouble, dateDouble);

        when(studentDouble.isEquals(studentDouble2)).thenReturn(false);

        //act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentHasTheSameStudentButDifferentProgrammesTest() {
        // arrange
        StudentID studentDouble = mock(StudentID.class);
        AccessMethodID accessMethodDouble = mock(AccessMethodID.class);
        ProgrammeID programmeDouble = mock(ProgrammeID.class);
        ProgrammeID programmeDouble2 = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, dateDouble);
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble2, dateDouble);

        when(studentDouble.isEquals(studentDouble)).thenReturn(true);

        // act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        // assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentHasBothDifferentStudentsAndDifferentProgrammesTest() {
        // arrange
        StudentID studentDouble = mock(StudentID.class);
        StudentID studentDouble2 = mock(StudentID.class);
        AccessMethodID accessMethodDouble = mock(AccessMethodID.class);
        ProgrammeID programmeDouble = mock(ProgrammeID.class);
        ProgrammeID programmeDouble2 = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, dateDouble);
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(studentDouble2, accessMethodDouble, programmeDouble2, dateDouble);

        // act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        // assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfProgrammesAreTheSameTest() {
        //arrange
        StudentID studentDouble = mock(StudentID.class);
        AccessMethodID accessMethodDouble = mock(AccessMethodID.class);
        ProgrammeDDD programmeDouble = mock(ProgrammeDDD.class);
        Date dateDouble = mock(Date.class);
        ProgrammeID programmeDoubleID = mock(ProgrammeID.class);
        when(programmeDouble.getProgrammeID()).thenReturn(programmeDoubleID);

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDoubleID, dateDouble);

        //act
        boolean result = programmeEnrolment.hasSameProgramme2(programmeDouble.getProgrammeID());

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfProgrammesAreNotTheSameTest() {
        //arrange
        StudentID studentDouble = mock(StudentID.class);
        AccessMethodID accessMethodDouble = mock(AccessMethodID.class);
        Date dateDouble = mock(Date.class);
        ProgrammeID programmeDoubleID = mock(ProgrammeID.class);
        Programme programmeDouble = mock(Programme.class);

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDoubleID, dateDouble);

        when(programmeDouble.isEquals(programmeDouble)).thenReturn(false);

        //act
        boolean result = programmeEnrolment.hasSameProgramme(programmeDouble);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnStudentIDfromGetterTest() {
        //Arrange
        StudentID studentIDDouble = mock(StudentID.class);
        AccessMethodID accessMethodIDDouble = mock(AccessMethodID.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);
        ProgrammeEnrolment pe1 = new ProgrammeEnrolment(studentIDDouble, accessMethodIDDouble, programmeIDDouble, dateDouble);

        //Act
        boolean result = pe1.getStudentID().equals(studentIDDouble);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnAccessMethodIDfromGetterTest() {
        //Arrange
        StudentID studentIDDouble = mock(StudentID.class);
        AccessMethodID accessMethodIDDouble = mock(AccessMethodID.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);
        ProgrammeEnrolment pe1 = new ProgrammeEnrolment(studentIDDouble, accessMethodIDDouble, programmeIDDouble, dateDouble);

        //Act
        boolean result = pe1.getAccessMethodID().equals(accessMethodIDDouble);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnProgrammeIDfromGetterTest() {
        //Arrange
        StudentID studentIDDouble = mock(StudentID.class);
        AccessMethodID accessMethodIDDouble = mock(AccessMethodID.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);
        ProgrammeEnrolment pe1 = new ProgrammeEnrolment(studentIDDouble, accessMethodIDDouble, programmeIDDouble, dateDouble);

        //Act
        boolean result = pe1.getProgrammeID().equals(programmeIDDouble);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnDatefromGetterTest() {
        //Arrange
        StudentID studentIDDouble = mock(StudentID.class);
        AccessMethodID accessMethodIDDouble = mock(AccessMethodID.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);
        ProgrammeEnrolment pe1 = new ProgrammeEnrolment(studentIDDouble, accessMethodIDDouble, programmeIDDouble, dateDouble);

        //Act
        boolean result = pe1.getDate().equals(dateDouble);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnProgrammeEnrolmentIDTest() {
        //Arrange
        StudentID studentIDDouble = mock(StudentID.class);
        AccessMethodID accessMethodIDDouble = mock(AccessMethodID.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);
        ProgrammeEnrolment pe1 = new ProgrammeEnrolment(studentIDDouble, accessMethodIDDouble, programmeIDDouble, dateDouble);
        ProgrammeEnrolmentID expectedPeID = pe1.identity();

        //Act
        ProgrammeEnrolmentID result = pe1.identity();

        //Assert
        assertEquals(expectedPeID, result);
    }

    @Test
    void shouldReturnTrueForSameProgrammeEnrolmentTest() {
        //Arrange
        StudentID studentIDDouble = mock(StudentID.class);
        AccessMethodID accessMethodIDDouble = mock(AccessMethodID.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);
        ProgrammeEnrolment pe1 = new ProgrammeEnrolment(studentIDDouble, accessMethodIDDouble, programmeIDDouble, dateDouble);

        //Act
        boolean result = pe1.sameAs(pe1);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseForDifferentProgrammeEnrolmentTest() {
        //Arrange
        StudentID studentIDDouble = mock(StudentID.class);
        AccessMethodID accessMethodIDDouble = mock(AccessMethodID.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);
        ProgrammeEnrolment pe1 = new ProgrammeEnrolment(studentIDDouble, accessMethodIDDouble, programmeIDDouble, dateDouble);
        ProgrammeEnrolment pe2 = new ProgrammeEnrolment(studentIDDouble, accessMethodIDDouble, programmeIDDouble, dateDouble);

        //Act
        boolean result = pe1.sameAs(pe2);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseForDifferentObjectsTest() {
        //Arrange
        StudentID studentIDDouble = mock(StudentID.class);
        AccessMethodID accessMethodIDDouble = mock(AccessMethodID.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);
        ProgrammeEnrolment pe1 = new ProgrammeEnrolment(studentIDDouble, accessMethodIDDouble, programmeIDDouble, dateDouble);
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
}
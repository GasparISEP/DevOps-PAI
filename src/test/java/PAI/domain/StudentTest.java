package PAI.domain;

import PAI.VOs.*;
import PAI.VOs.Location;
import org.apache.commons.lang3.stream.Streams;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentTest {

    @Test
    void validAttributesCreateObject() {

        //arrange
        Address address1 = mock(Address.class);
        StudentID mockStudentID = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        //act
        Student student1 = new Student(mockStudentID, nameDouble, nifDouble, phoneDouble, emailDouble, address1, academicEmailDouble);
    }

    @Test
    void invalidStudentIDShouldReturnException() throws IllegalArgumentException {

        // Arrange
        Address address1 = mock(Address.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        // Act
        Exception exception = assertThrows(Exception.class, () -> new Student(null, nameDouble, nifDouble, phoneDouble, emailDouble, address1, academicEmailDouble));

        // Assert
        assertEquals("Student's ID is invalid.", exception.getMessage());
    }

    @Test
    void nullNameDoesNotCreateObject() {

        //arrange
        Address address1 = mock(Address.class);
        StudentID mockStudentID = mock(StudentID.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student(mockStudentID, null, nifDouble, phoneDouble, emailDouble, address1, academicEmailDouble));
    }

    @Test
    void nullNIFDoesNotCreateObject() {

        //arrange
        Address address1 = mock(Address.class);
        StudentID mockStudentID = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student(mockStudentID, nameDouble, null, phoneDouble, emailDouble, address1, academicEmailDouble));
    }

    @Test
    void nullPhoneDoesNotCreateObject() {

        //arrange
        Address address1 = mock(Address.class);
        StudentID mockStudentID = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student(mockStudentID, nameDouble, nifDouble, null, emailDouble, address1, academicEmailDouble));
    }

    @Test
    void nullEmailDoesNotCreateObject () {

        //arrange
        Address address1 = mock(Address.class);
        StudentID mockStudentID = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student(mockStudentID, nameDouble, nifDouble, phoneDouble, null, address1, academicEmailDouble));
    }

    @Test
    void shouldReturnTrueIfTwoStudentsHaveTheSameID() {
        // Arrange
        Address address1 = mock(Address.class);
        StudentID mockStudentID = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student1 = new Student(mockStudentID, nameDouble, nifDouble, phoneDouble, emailDouble, address1, academicEmailDouble);

        when(mockStudentID.getUniqueNumber()).thenReturn(1234567);

        Address address2 = mock(Address.class);
        Student student2 = new Student(mockStudentID, nameDouble, nifDouble, phoneDouble, emailDouble, address2, academicEmailDouble);

        when(mockStudentID.getUniqueNumber()).thenReturn(1234567);

        // Act
        boolean result = student1.equals(student2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTwoStudentsDontHaveTheSameID() {
        // Arrange
        Address address1 = mock(Address.class);
        StudentID mockStudentID1 = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student1 = new Student(mockStudentID1, nameDouble, nifDouble, phoneDouble, emailDouble, address1, academicEmailDouble);


        Address address2 = mock(Address.class);
        StudentID mockStudentID2 = mock(StudentID.class);
        Student student2 = new Student(mockStudentID2, nameDouble, nifDouble, phoneDouble, emailDouble, address2, academicEmailDouble);

        // Act
        boolean result = student1.equals(student2);

        // Assert
        assertFalse(result);
    }

    @Test
    void objectsToCompareInEqualsMethodAreTheSame () {
        // Arrange
        Address address1 = mock(Address.class);
        StudentID mockStudentID1 = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student = new Student(mockStudentID1, nameDouble, nifDouble, phoneDouble, emailDouble, address1, academicEmailDouble);

        // Act
        boolean result = student.equals(student);

        // Assert
        assertTrue(result);
    }

    @Test
    void objectsToCompareInEqualsMethodAreNotTheSame () {
        // Arrange
        Address address1 = mock(Address.class);
        StudentID mockStudentID1 = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student = new Student(mockStudentID1, nameDouble, nifDouble, phoneDouble, emailDouble, address1, academicEmailDouble);

        // Act
        boolean result = student.equals(student);

        // Assert
        assertTrue(result);
    }

    @Test
    void objectsToCompareInSameAsMethodAreTheSame () {
        // Arrange
        Address address1 = mock(Address.class);
        StudentID mockStudentID1 = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student = new Student(mockStudentID1, nameDouble, nifDouble, phoneDouble, emailDouble, address1, academicEmailDouble);

        // Act
        boolean result = student.sameAs(student);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueIfTwoStudentsHaveTheSameNIF() {
        // Arrange
        Address address1 = mock(Address.class);
        Address address2 = mock(Address.class);
        StudentID mockStudentID1 = mock(StudentID.class);
        StudentID mockStudentID2 = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student1 = new Student(mockStudentID1, nameDouble, nifDouble, phoneDouble, emailDouble, address1, academicEmailDouble);
        Student student2 = new Student(mockStudentID2, nameDouble, nifDouble, phoneDouble, emailDouble, address2, academicEmailDouble);

        // Act
        boolean result = student1.sameAs(student2);

        // Assert
        assertTrue(result);
    }

    @Test
    void objectsToCompareInSameAsMethodAreNotTheSame () {
        // Arrange
        Address address1 = mock(Address.class);
        StudentID mockStudentID1 = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student = new Student(mockStudentID1, nameDouble, nifDouble, phoneDouble, emailDouble, address1, academicEmailDouble);

        TeacherAcronym acronymDouble = mock(TeacherAcronym.class);
        AcademicBackground academicBackgroundDouble = mock(AcademicBackground.class);
        Department departmentDouble = mock(Department.class);
        AddressVO addressVODouble = mock(AddressVO.class);

        Teacher teacher = new Teacher(acronymDouble, nameDouble, emailDouble, nifDouble, phoneDouble, academicBackgroundDouble, addressVODouble, departmentDouble);

        // Act
        boolean result = student.sameAs(teacher);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfTwoStudentsDontHaveTheSameNIF() {
        // Arrange
        Address address1 = mock(Address.class);
        Address address2 = mock(Address.class);
        StudentID mockStudentID1 = mock(StudentID.class);
        StudentID mockStudentID2 = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        NIF nifDouble2 = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student1 = new Student(mockStudentID1, nameDouble, nifDouble, phoneDouble, emailDouble, address1, academicEmailDouble);
        Student student2 = new Student(mockStudentID2, nameDouble, nifDouble2, phoneDouble, emailDouble, address2, academicEmailDouble);

        // Act
        boolean result = student1.sameAs(student2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfTheIDIsFoundInAStudent() {
        // Arrange
        Address address1 = mock(Address.class);
        StudentID mockStudentID = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student1 = new Student(mockStudentID, nameDouble, nifDouble, phoneDouble, emailDouble, address1, academicEmailDouble);

        Address address2 = mock(Address.class);
        Student student2 = new Student(mockStudentID, nameDouble, nifDouble, phoneDouble, emailDouble, address2, academicEmailDouble);

        // Act
        boolean result = student1.equals(student2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTheIDIsNotFoundInAStudent() {
        // Arrange
        Address address1 = mock(Address.class);
        StudentID mockStudentID = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student1 = new Student(mockStudentID, nameDouble, nifDouble, phoneDouble, emailDouble, address1, academicEmailDouble);

        StudentID mockStudentID2 = mock(StudentID.class);
        // Act
        boolean result = student1.equals(mockStudentID2);

        // Assert
        assertFalse(result);
    }
}
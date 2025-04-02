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
        AddressVO addressDouble = mock(AddressVO.class);
        StudentID mockStudentID = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        //act
        Student student1 = new Student(mockStudentID, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);
    }

    @Test
    void invalidStudentIDShouldReturnException() throws IllegalArgumentException {

        // Arrange
        AddressVO addressDouble = mock(AddressVO.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        // Act
        Exception exception = assertThrows(Exception.class, () -> new Student(null, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble));

        // Assert
        assertEquals("Student's ID is invalid.", exception.getMessage());
    }

    @Test
    void nullNameDoesNotCreateObject() {

        //arrange
        AddressVO addressDouble = mock(AddressVO.class);
        StudentID mockStudentID = mock(StudentID.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student(mockStudentID, null, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble));
    }

    @Test
    void nullNIFDoesNotCreateObject() {

        //arrange
        AddressVO addressDouble = mock(AddressVO.class);
        StudentID mockStudentID = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student(mockStudentID, nameDouble, null, phoneDouble, emailDouble, addressDouble, academicEmailDouble));
    }

    @Test
    void nullPhoneDoesNotCreateObject() {

        //arrange
        AddressVO addressDouble = mock(AddressVO.class);
        StudentID mockStudentID = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student(mockStudentID, nameDouble, nifDouble, null, emailDouble, addressDouble, academicEmailDouble));
    }

    @Test
    void nullEmailDoesNotCreateObject () {

        //arrange
        AddressVO addressDouble = mock(AddressVO.class);
        StudentID mockStudentID = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student(mockStudentID, nameDouble, nifDouble, phoneDouble, null, addressDouble, academicEmailDouble));
    }

    @Test
    void nullAddressDoesNotCreateObject () {

        //arrange
        StudentID mockStudentID = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        Email emailDouble = mock(Email.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student(mockStudentID, nameDouble, nifDouble, phoneDouble, emailDouble, null, academicEmailDouble));
    }

    @Test
    void nullAcademicEmailDoesNotCreateObject () {

        //arrange
        AddressVO addressDouble = mock(AddressVO.class);
        StudentID mockStudentID = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        Email emailDouble = mock(Email.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student(mockStudentID, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, null));
    }

    @Test
    void shouldReturnTrueIfTwoStudentsHaveTheSameID() {
        // Arrange
        AddressVO addressDouble = mock(AddressVO.class);
        StudentID mockStudentID = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student1 = new Student(mockStudentID, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

        when(mockStudentID.getUniqueNumber()).thenReturn(1234567);

        Address address2 = mock(Address.class);
        Student student2 = new Student(mockStudentID, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

        when(mockStudentID.getUniqueNumber()).thenReturn(1234567);

        // Act
        boolean result = student1.equals(student2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTwoStudentsDontHaveTheSameID() {
        // Arrange
        AddressVO addressDouble = mock(AddressVO.class);
        StudentID mockStudentID1 = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student1 = new Student(mockStudentID1, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);


        Address address2 = mock(Address.class);
        StudentID mockStudentID2 = mock(StudentID.class);
        Student student2 = new Student(mockStudentID2, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

        // Act
        boolean result = student1.equals(student2);

        // Assert
        assertFalse(result);
    }

    @Test
    void objectsToCompareInEqualsMethodAreTheSame () {
        // Arrange
        AddressVO addressDouble = mock(AddressVO.class);
        StudentID mockStudentID1 = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student = new Student(mockStudentID1, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

        // Act
        boolean result = student.equals(student);

        // Assert
        assertTrue(result);
    }

    @Test
    void objectsToCompareInEqualsMethodAreNotTheSame () {
        // Arrange
        AddressVO addressDouble = mock(AddressVO.class);
        StudentID mockStudentID1 = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student = new Student(mockStudentID1, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

        // Act
        boolean result = student.equals(student);

        // Assert
        assertTrue(result);
    }

    @Test
    void objectToCompareIsNotInstanceOfStudent () {

        // Arrange
        StudentID studentIDDouble = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);
        AddressVO addressDouble = mock(AddressVO.class);

        Student student = new Student(studentIDDouble, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);
        AccessMethod accessMethodDouble = mock(AccessMethod.class);

        // Act
        boolean result = student.equals(accessMethodDouble);

        // Assert
        assertFalse(result);
    }

    @Test
    void objectsToCompareInSameAsMethodAreTheSame () {
        // Arrange
        AddressVO addressDouble = mock(AddressVO.class);
        StudentID mockStudentID1 = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student = new Student(mockStudentID1, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

        // Act
        boolean result = student.sameAs(student);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueIfTwoStudentsHaveTheSameNIF() {
        // Arrange
        AddressVO addressDouble = mock(AddressVO.class);
        StudentID mockStudentID1 = mock(StudentID.class);
        StudentID mockStudentID2 = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student1 = new Student(mockStudentID1, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);
        Student student2 = new Student(mockStudentID2, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

        // Act
        boolean result = student1.sameAs(student2);

        // Assert
        assertTrue(result);
    }

    @Test
    void objectsToCompareInSameAsMethodAreNotTheSame () {
        // Arrange
        AddressVO addressDouble = mock(AddressVO.class);
        StudentID mockStudentID1 = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student = new Student(mockStudentID1, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

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
        AddressVO addressDouble = mock(AddressVO.class);
        StudentID mockStudentID1 = mock(StudentID.class);
        StudentID mockStudentID2 = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        NIF nifDouble2 = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student1 = new Student(mockStudentID1, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);
        Student student2 = new Student(mockStudentID2, nameDouble, nifDouble2, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

        // Act
        boolean result = student1.sameAs(student2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfTheIDIsFoundInAStudent() {
        // Arrange
        AddressVO addressDouble = mock(AddressVO.class);
        StudentID mockStudentID = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student1 = new Student(mockStudentID, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);
        Student student2 = new Student(mockStudentID, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

        // Act
        boolean result = student1.isEquals(student2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTheIDIsNotFoundInAStudent() {
        // Arrange
        AddressVO addressDouble = mock(AddressVO.class);
        StudentID mockStudentID = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student studentDouble1 = new Student(mockStudentID, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

        StudentID studentIDDouble2 = mock(StudentID.class);
        NIF nifDouble2 = mock(NIF.class);

        Student studentDouble2 = new Student(studentIDDouble2, nameDouble, nifDouble2, phoneDouble, emailDouble, addressDouble, academicEmailDouble);


        // Act
        boolean result = studentDouble1.isEquals(studentDouble2);

        // Assert
        assertFalse(result);
    }
}
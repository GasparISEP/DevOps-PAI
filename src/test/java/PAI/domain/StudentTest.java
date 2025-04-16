package PAI.domain;

import PAI.VOs.*;
import PAI.domain.accessMethod.AccessMethod;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StudentTest {

    @Test
    void validAttributesCreateObject() {

        //arrange
        Address addressDouble = mock(Address.class);
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
        Address addressDouble = mock(Address.class);
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
        Address addressDouble = mock(Address.class);
        StudentID studentIDDouble = mock(StudentID.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student(studentIDDouble, null, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble));
    }

    @Test
    void nullNIFDoesNotCreateObject() {

        //arrange
        Address addressDouble = mock(Address.class);
        StudentID studentIDDouble = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student(studentIDDouble, nameDouble, null, phoneDouble, emailDouble, addressDouble, academicEmailDouble));
    }

    @Test
    void nullPhoneDoesNotCreateObject() {

        //arrange
        Address addressDouble = mock(Address.class);
        StudentID studentIDDouble = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student(studentIDDouble, nameDouble, nifDouble, null, emailDouble, addressDouble, academicEmailDouble));
    }

    @Test
    void nullEmailDoesNotCreateObject () {

        //arrange
        Address addressDouble = mock(Address.class);
        StudentID studentIDDouble = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student(studentIDDouble, nameDouble, nifDouble, phoneDouble, null, addressDouble, academicEmailDouble));
    }

    @Test
    void nullAddressDoesNotCreateObject () {

        //arrange
        StudentID studentIDDouble = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        Email emailDouble = mock(Email.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student(studentIDDouble, nameDouble, nifDouble, phoneDouble, emailDouble, null, academicEmailDouble));
    }

    @Test
    void nullAcademicEmailDoesNotCreateObject () {

        //arrange
        Address addressDouble = mock(Address.class);
        StudentID studentIDDouble = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        Email emailDouble = mock(Email.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student(studentIDDouble, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, null));
    }

    @Test
    void identityMethodShouldReturnStudentID () {
        // Arrange
        Address addressDouble = mock(Address.class);
        StudentID studentIDDouble = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        Email emailDouble = mock(Email.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student = new Student(studentIDDouble, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

        // Act
        StudentID studentID = student.identity();

        // Assert
        assertInstanceOf(StudentID.class, studentID);
    }

    @Test
    void shouldReturnTrueIfTwoStudentsHaveTheSameID() {
        // Arrange
        Address addressDouble = mock(Address.class);
        NIF nifDouble = mock(NIF.class);
        StudentID studentIDDouble = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student1 = new Student(studentIDDouble, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

        Student student2 = new Student(studentIDDouble, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

        // Act
        boolean result = student1.equals(student2);


        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTwoStudentsDontHaveTheSameID() {
        // Arrange
        Address addressDouble = mock(Address.class);
        StudentID studentIDDouble = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student1 = new Student(studentIDDouble, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

        StudentID studentIDDouble2 = mock(StudentID.class);
        Student student2 = new Student(studentIDDouble2, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

        // Act
        boolean result = student1.equals(student2);

        // Assert
        assertFalse(result);
    }

    @Test
    void objectsToCompareInEqualsMethodAreTheSame () {
        // Arrange
        Address addressDouble = mock(Address.class);
        StudentID mockStudentID = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student = new Student(mockStudentID, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

        // Act
        boolean result = student.equals(student);

        // Assert
        assertTrue(result);
    }

    @Test
    void objectsToCompareInEqualsMethodAreNotTheSame () {
        // Arrange
        Address addressDouble = mock(Address.class);
        StudentID studentIDDouble = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student = new Student(studentIDDouble, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

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
        Address addressDouble = mock(Address.class);

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
        Address addressDouble = mock(Address.class);
        StudentID studentIDDouble = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student = new Student(studentIDDouble, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

        // Act
        boolean result = student.sameAs(student);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueIfTwoStudentsHaveTheSameNIF() {
        // Arrange
        Address addressDouble = mock(Address.class);
        StudentID studentIDDouble1 = mock(StudentID.class);
        StudentID studentIDDouble2 = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student1 = new Student(studentIDDouble1, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);
        Student student2 = new Student(studentIDDouble2, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

        // Act
        boolean result = student1.sameAs(student2);

        // Assert
        assertTrue(result);
    }

    @Test
    void objectsToCompareInSameAsMethodAreNotTheSame () {
        // Arrange
        Address addressDouble = mock(Address.class);
        StudentID studentIDDouble = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student = new Student(studentIDDouble, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

        Object object = mock(Object.class);

        // Act
        boolean result = student.sameAs(object);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfTwoStudentsDontHaveTheSameNIF() {
        // Arrange
        Address addressDouble = mock(Address.class);
        StudentID studentIDDouble1 = mock(StudentID.class);
        StudentID studentIDDouble2 = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        NIF nifDouble2 = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student1 = new Student(studentIDDouble1, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);
        Student student2 = new Student(studentIDDouble2, nameDouble, nifDouble2, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

        // Act
        boolean result = student1.sameAs(student2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfTheIDIsFoundInAStudent() {
        // Arrange
        Address addressDouble = mock(Address.class);
        StudentID studentIDDouble = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student1 = new Student(studentIDDouble, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);
        Student student2 = new Student(studentIDDouble, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

        // Act
        boolean result = student1.isEquals(student2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTheIDIsNotFoundInAStudent() {
        // Arrange
        Address addressDouble = mock(Address.class);
        StudentID studentIDDouble = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student studentDouble1 = new Student(studentIDDouble, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

        StudentID studentIDDouble2 = mock(StudentID.class);
        NIF nifDouble2 = mock(NIF.class);

        Student studentDouble2 = new Student(studentIDDouble2, nameDouble, nifDouble2, phoneDouble, emailDouble, addressDouble, academicEmailDouble);


        // Act
        boolean result = studentDouble1.isEquals(studentDouble2);

        // Assert
        assertFalse(result);
    }

    //getters
    @Test
    void getStudentIDShouldReturnStudentID() {
        //arrange
        Address addressDouble = mock(Address.class);
        StudentID studentIDDouble = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student1 = new Student(studentIDDouble, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

        //act
        StudentID studentID = student1.getStudentID();

        //assert
        assertEquals(studentIDDouble, studentID);
    }

    @Test
    void getStudentNameShouldReturnName() {
        //arrange
        Address addressDouble = mock(Address.class);
        StudentID studentIDDouble = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student1 = new Student(studentIDDouble, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

        //act
        Name name = student1.getStudentName();

        //assert
        assertEquals(nameDouble, name);
    }

    @Test
    void getStudentNIFShouldReturnNIF() {
        //arrange
        Address addressDouble = mock(Address.class);
        StudentID studentIDDouble = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student1 = new Student(studentIDDouble, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

        //act
        NIF nif = student1.getStudentNIF();

        //assert
        assertEquals(nifDouble, nif);
    }

    @Test
    void getStudentPhoneNumberShouldReturnPhoneNumber() {
        //arrange
        Address addressDouble = mock(Address.class);
        StudentID studentIDDouble = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student1 = new Student(studentIDDouble, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

        //act
        PhoneNumber phoneNumber = student1.getStudentPhoneNumber();

        //assert
        assertEquals(phoneDouble, phoneNumber);
    }

    @Test
    void getStudentEmailShouldReturnEmail() {
        //arrange
        Address addressDouble = mock(Address.class);
        StudentID studentIDDouble = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student1 = new Student(studentIDDouble, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

        //act
        Email email = student1.getStudentEmail();

        //assert
        assertEquals(emailDouble, email);
    }

    @Test
    void getStudentAddressShouldReturnAddress() {
        //arrange
        Address addressDouble = mock(Address.class);
        StudentID studentIDDouble = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student1 = new Student(studentIDDouble, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

        //act
        Address address = student1.getStudentAddress();

        //assert
        assertEquals(addressDouble, address);
    }

    @Test
    void getStudentAcademicEmailShouldReturnAcademicEmail() {
        //arrange
        Address addressDouble = mock(Address.class);
        StudentID studentIDDouble = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student1 = new Student(studentIDDouble, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

        //act
        StudentAcademicEmail studentAcademicEmail = student1.getStudentAcademicEmail();

        //assert
        assertEquals(academicEmailDouble, studentAcademicEmail);
    }
}
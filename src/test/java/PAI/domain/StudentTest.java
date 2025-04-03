package PAI.domain;

import PAI.VOs.*;
import PAI.domain.accessMethod.AccessMethod;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        Address addressDouble = mock(Address.class);
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
        Address addressDouble = mock(Address.class);
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
        Address addressDouble = mock(Address.class);
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
        Address addressDouble = mock(Address.class);
        StudentID mockStudentID = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        Email emailDouble = mock(Email.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student(mockStudentID, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, null));
    }

    @Test
    void identityMethodShouldReturnStudentID () {
        // Arrange
        Address addressDouble = mock(Address.class);
        StudentID mockStudentID = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        Email emailDouble = mock(Email.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student = new Student(mockStudentID, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

        // Act
        StudentID studentID = student.identity();

        // Assert
        assertInstanceOf(StudentID.class, studentID);
    }

    @Test
    void shouldReturnTrueIfTwoStudentsHaveTheSameID() {
        // Arrange
        Address addressDouble = mock(Address.class);
        StudentID mockStudentID = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student1 = new Student(mockStudentID, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

        when(mockStudentID.getUniqueNumber()).thenReturn(1234567);

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
        Address addressDouble = mock(Address.class);
        StudentID mockStudentID1 = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student1 = new Student(mockStudentID1, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

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
        Address addressDouble = mock(Address.class);
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
        Address addressDouble = mock(Address.class);
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
        Address addressDouble = mock(Address.class);
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
        Address addressDouble = mock(Address.class);
        StudentID mockStudentID1 = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        Student student = new Student(mockStudentID1, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

        TeacherAcronym acronymDouble = mock(TeacherAcronym.class);
        AcademicBackground academicBackgroundDouble = mock(AcademicBackground.class);
        DepartmentID departmentIDDouble = mock(DepartmentID.class);
        Address addressVODouble = mock(Address.class);

        Teacher teacher = new Teacher(acronymDouble, nameDouble, emailDouble, nifDouble, phoneDouble, academicBackgroundDouble, addressVODouble, departmentIDDouble);

        // Act
        boolean result = student.sameAs(teacher);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfTwoStudentsDontHaveTheSameNIF() {
        // Arrange
        Address addressDouble = mock(Address.class);
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
        Address addressDouble = mock(Address.class);
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
        Address addressDouble = mock(Address.class);
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
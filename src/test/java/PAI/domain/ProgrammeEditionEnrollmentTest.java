package PAI.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEditionEnrollmentTest {

    @Test
    void should_return_a_valid_programme_edition_enrollment() throws Exception {
        //arrange
        Student studentDouble = mock(Student.class);
        ProgrammeEdition peDouble = mock(ProgrammeEdition.class);

        //act + assert
        ProgrammeEditionEnrollment pee1 = new ProgrammeEditionEnrollment(studentDouble,peDouble);
    }

    @Test
    void everythingNullGenerateException () throws Exception {
        //arrange

        //act + assert
        assertThrows(Exception.class, () -> new ProgrammeEditionEnrollment (null,null));
    }

    @Test
    void programmeNullGenerateException () throws Exception {
        //arrange
        Student studentDouble = mock(Student.class);
        //act + assert
        assertThrows(Exception.class, () -> new ProgrammeEditionEnrollment (studentDouble,null));
    }

    public static Stream<Arguments> provideInvalidEnrollmentDate() {
        return Stream.of(
                arguments(null, "Enrollment date cannot be null!"),
                arguments(LocalDate.of(2024,12,29), "Enrollment date must be the current day!"),
                arguments(LocalDate.of (2026,1,23), "Enrollment date must be the current day!")
        );
    }

    @Test
    void shouldReturnFalseIfProgrammeEditionIsNull_EqualsMethod() throws Exception {
        //arrange
        Student studentDouble = mock(Student.class);
        ProgrammeEdition peDouble = mock(ProgrammeEdition.class);

        //act
        ProgrammeEditionEnrollment enrollment1 = new ProgrammeEditionEnrollment(studentDouble, peDouble);

        //assert
        assertFalse(enrollment1.equals(null));
    }

    @Test
    void shouldReturnTrueIfProgrammeEditionIsEqualThis_EqualsMethod() throws Exception {
        //arrange
        Student studentDouble = mock(Student.class);
        ProgrammeEdition peDouble = mock(ProgrammeEdition.class);

        //act
        ProgrammeEditionEnrollment enrollment1 = new ProgrammeEditionEnrollment(studentDouble, peDouble);

        //assert
        assertTrue(enrollment1.equals(enrollment1));
    }

    @Test
    void shouldReturnTrueIfAllFieldsAreEqual_EqualsMethod() throws Exception {
        // Arrange with mock objects
        Student studentMock = mock(Student.class);
        ProgrammeEdition programmeEditionMock = mock(ProgrammeEdition.class);

        // Act
        ProgrammeEditionEnrollment enrollment1 = new ProgrammeEditionEnrollment(studentMock, programmeEditionMock);
        ProgrammeEditionEnrollment enrollment2 = new ProgrammeEditionEnrollment(studentMock, programmeEditionMock);

        // Assert
        assertTrue(enrollment1.equals(enrollment2));
    }

    @Test
    void shouldReturnProgrammeEditionFromEnrollment() throws Exception {
        // Arrange
        Student studentDouble = mock(Student.class);
        ProgrammeEdition peDouble = mock(ProgrammeEdition.class);

        ProgrammeEditionEnrollment enrollment = new ProgrammeEditionEnrollment(studentDouble, peDouble);

        // Act
        ProgrammeEdition foundProgrammeEdition = enrollment.findProgrammeEditionInEnrollment();

        // Assert
        assertNotNull(foundProgrammeEdition);
        assertEquals(peDouble, foundProgrammeEdition);
    }

    @Test
    void shouldReturnStudentFromProgrammeEdition() throws Exception {
        // Arrange
        Student studentDouble = mock(Student.class);
        ProgrammeEdition peDouble = mock(ProgrammeEdition.class);

        ProgrammeEditionEnrollment enrollment = new ProgrammeEditionEnrollment(studentDouble, peDouble);

        // Act
        Student foundStudent = enrollment.findStudentInProgrammeEdition();

        // Assert
        assertNotNull(foundStudent);
        assertEquals(studentDouble, foundStudent);
    }

    //US26
    // Test returns true when the department and school year are correctly associated with the enrollment
    @Test
    void shouldReturnTrueWhenDepartmentAndSchoolYearAreAssociated() {
        // arrange
        Student student1Double = mock(Student.class);
        Department departmentDouble = mock(Department.class);
        SchoolYear schoolYearDouble = mock(SchoolYear.class);
        ProgrammeEdition editionDouble = mock(ProgrammeEdition.class);

        when(editionDouble.isEditionAssociatedToDepartmentAndSchoolYear(departmentDouble,schoolYearDouble)).thenReturn(true);

        ProgrammeEditionEnrollment enrollment = new ProgrammeEditionEnrollment(student1Double,editionDouble);

        // act
        boolean result = enrollment.isEnrollmentAssociatedToDepartmentAndSchoolYear(departmentDouble, schoolYearDouble);

        // assert
        assertTrue(result);
    }

    // Test returns false when department and school year are not associated with the enrollment
    @Test
    void shouldReturnFalseWhenDepartmentAndSchoolYearAreNotAssociatedWithTheEnrollment() {
        // arrange
        Student student1Double = mock(Student.class);
        Department departmentDouble = mock(Department.class);
        SchoolYear schoolYearDouble = mock(SchoolYear.class);
        ProgrammeEdition editionDouble = mock(ProgrammeEdition.class);
        LocalDate enrollmentDate = LocalDate.now();

        when(editionDouble.isEditionAssociatedToDepartmentAndSchoolYear(departmentDouble,schoolYearDouble)).thenReturn(false);

        ProgrammeEditionEnrollment enrollment = new ProgrammeEditionEnrollment(student1Double,editionDouble);

        // act
        boolean result = enrollment.isEnrollmentAssociatedToDepartmentAndSchoolYear(departmentDouble, schoolYearDouble);

        // assert
        assertFalse(result);
    }

    @Test
    void shouldReturnStudentUniqueNumber() {
        // Arrange
        Student studentMock = mock(Student.class);
        when(studentMock.getUniqueNumber()).thenReturn("1234567");

        ProgrammeEdition editionMock = mock(ProgrammeEdition.class);
        ProgrammeEditionEnrollment enrollment = new ProgrammeEditionEnrollment(studentMock, editionMock);

        // Act
        String uniqueNumber = enrollment.getStudentUniqueNumber();

        // Assert
        assertEquals("1234567", uniqueNumber);
    }

    @Test
    void shouldReturnFalseIfObjectIsDifferent_EqualsMethod() {
        // Arrange
        Student st1 = mock(Student.class);
        ProgrammeEdition pe1 = mock(ProgrammeEdition.class);
        LocalDate currentDate = LocalDate.now();

        ProgrammeEditionEnrollment enrollment = new ProgrammeEditionEnrollment(st1, pe1);

        // Act & Assert
        assertFalse(enrollment.equals(new Object()));
    }

    @Test
    void shouldReturnFalseIfStudentsAreDifferent_EqualsMethod() {
        // Arrange
        Student student1 = mock(Student.class);
        Student student2 = mock(Student.class);
        ProgrammeEdition edition = mock(ProgrammeEdition.class);
        LocalDate currentDate = LocalDate.now();

        ProgrammeEditionEnrollment enrollment1 = new ProgrammeEditionEnrollment(student1, edition);
        ProgrammeEditionEnrollment enrollment2 = new ProgrammeEditionEnrollment(student2, edition);

        // Act & Assert
        assertFalse(enrollment1.equals(enrollment2));
    }


}
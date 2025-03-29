package PAI.domain;

import PAI.VOs.StudentID;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEditionEnrolmentTest {

    @Test
    void should_return_a_valid_programme_edition_enrollment() throws Exception {
        //arrange
        Student studentDouble = mock(Student.class);
        ProgrammeEdition peDouble = mock(ProgrammeEdition.class);

        //act + assert
        ProgrammeEditionEnrolment pee1 = new ProgrammeEditionEnrolment(studentDouble,peDouble);

    }

    @Test
    void everythingNullGenerateException () throws Exception {
        //arrange

        //act + assert
        assertThrows(Exception.class, () -> new ProgrammeEditionEnrolment(null,null));
    }

    @Test
    void programmeNullGenerateException () throws Exception {
        //arrange
        Student studentDouble = mock(Student.class);
        //act + assert
        assertThrows(Exception.class, () -> new ProgrammeEditionEnrolment(studentDouble,null));
    }

    @Test
    void shouldReturnFalseIfProgrammeEditionIsNull_EqualsMethod() throws Exception {
        //arrange
        Student studentDouble = mock(Student.class);
        ProgrammeEdition peDouble = mock(ProgrammeEdition.class);

        //act
        ProgrammeEditionEnrolment enrollment1 = new ProgrammeEditionEnrolment(studentDouble, peDouble);

        //assert
        assertFalse(enrollment1.equals(null));
    }

    @Test
    void shouldReturnTrueIfProgrammeEditionIsEqualThis_EqualsMethod() throws Exception {
        //arrange
        Student studentDouble = mock(Student.class);
        ProgrammeEdition peDouble = mock(ProgrammeEdition.class);

        //act
        ProgrammeEditionEnrolment enrollment1 = new ProgrammeEditionEnrolment(studentDouble, peDouble);

        //assert
        assertTrue(enrollment1.equals(enrollment1));
    }

    @Test
    void shouldReturnTrueIfAllFieldsAreEqual_EqualsMethod() throws Exception {
        // Arrange with mock objects
        Student studentMock = mock(Student.class);
        ProgrammeEdition programmeEditionMock = mock(ProgrammeEdition.class);

        // Act
        ProgrammeEditionEnrolment enrollment1 = new ProgrammeEditionEnrolment(studentMock, programmeEditionMock);
        ProgrammeEditionEnrolment enrollment2 = new ProgrammeEditionEnrolment(studentMock, programmeEditionMock);

        // Assert
        assertTrue(enrollment1.equals(enrollment2));
    }


    @Test
    void shouldReturnProgrammeEditionFromEnrolment_NotNull() throws Exception {
        // Arrange
        Student studentDouble = mock(Student.class);
        ProgrammeEdition peDouble = mock(ProgrammeEdition.class);

        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(studentDouble, peDouble);

        // Act
        ProgrammeEdition foundProgrammeEdition = enrollment.findProgrammeEditionInEnrolment();

        // Assert
        assertNotNull(foundProgrammeEdition, "The programme edition should not be null.");
    }

    @Test
    void shouldReturnProgrammeEditionFromEnrolment_Equals() throws Exception {
        // Arrange
        Student studentDouble = mock(Student.class);
        ProgrammeEdition peDouble = mock(ProgrammeEdition.class);

        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(studentDouble, peDouble);

        // Act
        ProgrammeEdition foundProgrammeEdition = enrollment.findProgrammeEditionInEnrolment();

        // Assert
        assertEquals(peDouble, foundProgrammeEdition, "The found programme edition should be the same as the mock programme edition.");
    }

    @Test
    void shouldReturnStudentFromProgrammeEdition_NotNull() throws Exception {
        // Arrange
        Student studentDouble = mock(Student.class);
        ProgrammeEdition peDouble = mock(ProgrammeEdition.class);

        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(studentDouble, peDouble);

        // Act
        Student foundStudent = enrollment.findStudentInProgrammeEdition();

        // Assert
        assertNotNull(foundStudent, "The student should not be null.");
    }

    @Test
    void shouldReturnStudentFromProgrammeEdition_Equals() throws Exception {
        // Arrange
        Student studentDouble = mock(Student.class);
        ProgrammeEdition peDouble = mock(ProgrammeEdition.class);

        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(studentDouble, peDouble);

        // Act
        Student foundStudent = enrollment.findStudentInProgrammeEdition();

        // Assert
        assertEquals(studentDouble, foundStudent, "The found student should be the same as the mock student.");
    }

    // Test returns true when the department and school year are correctly associated with the enrollment
    @Test
    void shouldReturnTrueWhenDepartmentAndSchoolYearAreAssociated() {
        // arrange
        Student student1Double = mock(Student.class);
        Department departmentDouble = mock(Department.class);
        SchoolYear schoolYearDouble = mock(SchoolYear.class);
        ProgrammeEdition editionDouble = mock(ProgrammeEdition.class);

        when(editionDouble.isEditionAssociatedToDepartmentAndSchoolYear(departmentDouble, schoolYearDouble)).thenReturn(true);

        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(student1Double, editionDouble);

        // act
        boolean result = enrollment.isEnrolmentAssociatedToDepartmentAndSchoolYear(departmentDouble, schoolYearDouble);

        // assert
        assertTrue(result);
    }

    // Test returns false when department and school year are not associated with the enrollment
    @Test
    void shouldReturnFalseWhenDepartmentAndSchoolYearAreNotAssociatedWithTheEnrolment() {
        // arrange
        Student student1Double = mock(Student.class);
        Department departmentDouble = mock(Department.class);
        SchoolYear schoolYearDouble = mock(SchoolYear.class);
        ProgrammeEdition editionDouble = mock(ProgrammeEdition.class);
        LocalDate enrollmentDate = LocalDate.now();

        when(editionDouble.isEditionAssociatedToDepartmentAndSchoolYear(departmentDouble, schoolYearDouble)).thenReturn(false);

        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(student1Double, editionDouble);

        // act
        boolean result = enrollment.isEnrolmentAssociatedToDepartmentAndSchoolYear(departmentDouble, schoolYearDouble);

        // assert
        assertFalse(result);
    }

    @Test
    void shouldReturnStudentUniqueNumber() {
        // Arrange
        Student studentMock = mock(Student.class);
        StudentID mockStudentID = mock(StudentID.class);
        when(studentMock.identity()).thenReturn(mockStudentID);

        ProgrammeEdition editionMock = mock(ProgrammeEdition.class);
        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(studentMock, editionMock);

        // Act
        StudentID studentID = enrollment.getStudentID();

        // Assert
        assertEquals(mockStudentID, studentID);
    }

    @Test
    void shouldReturnFalseIfObjectIsDifferent_EqualsMethod() {
        // Arrange
        Student st1 = mock(Student.class);
        ProgrammeEdition pe1 = mock(ProgrammeEdition.class);
        LocalDate currentDate = LocalDate.now();

        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(st1, pe1);

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

        ProgrammeEditionEnrolment enrollment1 = new ProgrammeEditionEnrolment(student1, edition);
        ProgrammeEditionEnrolment enrollment2 = new ProgrammeEditionEnrolment(student2, edition);

        // Act & Assert
        assertFalse(enrollment1.equals(enrollment2));
    }

    @Test
    void shouldReturnTrue_WhenSameStudentIsPassedAsParameter() {
        // Arrange
        Student doubleStudent = mock(Student.class);
        ProgrammeEdition doublePE = mock(ProgrammeEdition.class);
        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(doubleStudent, doublePE);

        // Act & Assert
        assertTrue(enrollment.hasSameStudent(doubleStudent));

    }

    @Test
    void shouldReturnTrue_WhenDifferentStudentIsPassedAsParameter() {
        // Arrange
        Student doubleStudent = mock(Student.class);
        Student doubleStudent1 = mock(Student.class);
        ProgrammeEdition doubleProgrammeEdition = mock(ProgrammeEdition.class);

        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(doubleStudent, doubleProgrammeEdition);

        // Act & Assert
        assertFalse(enrollment.hasSameStudent(doubleStudent1));
    }

    @Test
    void shouldReturnTrue_WhenSameProgrammeEditionIsPassedAsParameter() {
        // Arrange
        Student doubleStudent = mock(Student.class);
        ProgrammeEdition doublePE = mock(ProgrammeEdition.class);
        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(doubleStudent, doublePE);

        // Act & Assert
        assertTrue(enrollment.hasSameProgrammeEdition(doublePE));
    }

    @Test
    void shouldReturnFalse_WhenDifferentProgrammeEditionIsPassedAsParameter() {
        // Arrange
        Student doubleStudent = mock(Student.class);
        ProgrammeEdition doubleProgrammeEdition = mock(ProgrammeEdition.class);
        ProgrammeEdition doubleProgrammeEdition1 = mock(ProgrammeEdition.class);

        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(doubleStudent, doubleProgrammeEdition);

        // Act & Assert
        assertFalse(enrollment.hasSameProgrammeEdition(doubleProgrammeEdition1));
    }

}
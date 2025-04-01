package PAI.domain;

import PAI.VOs.ProgrammeEditionID;
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
        StudentID studentDoubleId = mock(StudentID.class);
        ProgrammeEditionID peDoubleId = mock(ProgrammeEditionID.class);

        //act + assert
        ProgrammeEditionEnrolment pee1 = new ProgrammeEditionEnrolment(studentDoubleId,peDoubleId);

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
        StudentID studentDoubleId = mock(StudentID.class);
        //act + assert
        assertThrows(Exception.class, () -> new ProgrammeEditionEnrolment(studentDoubleId,null));
    }

    @Test
    void shouldReturnFalseIfProgrammeEditionIsNull_EqualsMethod() throws Exception {
        //arrange
        StudentID studentDoubleId = mock(StudentID.class);
        ProgrammeEditionID peDoubleId = mock(ProgrammeEditionID.class);

        //act
        ProgrammeEditionEnrolment enrollment1 = new ProgrammeEditionEnrolment(studentDoubleId, peDoubleId);

        //assert
        assertFalse(enrollment1.equals(null));
    }

    @Test
    void shouldReturnTrueIfProgrammeEditionIsEqualThis_EqualsMethod() throws Exception {
        //arrange
        StudentID studentDoubleId = mock(StudentID.class);
        ProgrammeEditionID peDoubleId = mock(ProgrammeEditionID.class);

        //act
        ProgrammeEditionEnrolment enrollment1 = new ProgrammeEditionEnrolment(studentDoubleId, peDoubleId);

        //assert
        assertTrue(enrollment1.equals(enrollment1));
    }

    @Test
    void shouldReturnTrueIfAllFieldsAreEqual_EqualsMethod() throws Exception {
        // Arrange with mock objects
        StudentID studentMockId = mock(StudentID.class);
        ProgrammeEditionID programmeEditionMock = mock(ProgrammeEditionID.class);

        // Act
        ProgrammeEditionEnrolment enrollment1 = new ProgrammeEditionEnrolment(studentMockId, programmeEditionMock);
        ProgrammeEditionEnrolment enrollment2 = new ProgrammeEditionEnrolment(studentMockId, programmeEditionMock);

        // Assert
        assertTrue(enrollment1.equals(enrollment2));
    }


    @Test
    void shouldReturnProgrammeEditionFromEnrolment_NotNull() throws Exception {
        // Arrange
        StudentID studentDouble = mock(StudentID.class);
        ProgrammeEditionID peDouble = mock(ProgrammeEditionID.class);

        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(studentDouble, peDouble);

        // Act
        ProgrammeEditionID foundProgrammeEditionId = enrollment.findProgrammeEditionInEnrolment();

        // Assert
        assertNotNull(foundProgrammeEditionId, "The programme edition should not be null.");
    }

    @Test
    void shouldReturnProgrammeEditionFromEnrolment_Equals() throws Exception {
        // Arrange
        StudentID studentDoubleId = mock(StudentID.class);
        ProgrammeEditionID peDoubleId = mock(ProgrammeEditionID.class);

        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(studentDoubleId, peDoubleId);

        // Act
        ProgrammeEditionID foundProgrammeEdition = enrollment.findProgrammeEditionInEnrolment();

        // Assert
        assertEquals(peDoubleId, foundProgrammeEdition, "The found programme edition should be the same as the mock programme edition.");
    }

    @Test
    void shouldReturnStudentFromProgrammeEdition_NotNull() throws Exception {
        // Arrange
        StudentID studentDoubleId = mock(StudentID.class);
        ProgrammeEditionID peDoubleId = mock(ProgrammeEditionID.class);

        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(studentDoubleId, peDoubleId);

        // Act
        StudentID foundStudentId = enrollment.findStudentInProgrammeEdition();

        // Assert
        assertNotNull(foundStudentId, "The student should not be null.");
    }

    @Test
    void shouldReturnStudentFromProgrammeEdition_Equals() throws Exception {
        // Arrange
        StudentID studentDoubleId = mock(StudentID.class);
        ProgrammeEditionID peDouble = mock(ProgrammeEditionID.class);

        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(studentDoubleId, peDouble);

        // Act
        StudentID foundStudentId = enrollment.findStudentInProgrammeEdition();

        // Assert
        assertEquals(studentDoubleId, foundStudentId, "The found student should be the same as the mock student.");
    }

    // Test returns true when the department and school year are correctly associated with the enrollment
//    @Test
//    void shouldReturnTrueWhenDepartmentAndSchoolYearAreAssociated() {
//        // arrange
//        StudentID student1DoubleId = mock(StudentID.class);
//        Department departmentDouble = mock(Department.class);
//        SchoolYear schoolYearDouble = mock(SchoolYear.class);
//        ProgrammeEdition editionDouble = mock(ProgrammeEdition.class);
//        ProgrammeEditionID peDoubleID = mock(ProgrammeEditionID.class);
//
//        when(editionDouble.isEditionAssociatedToDepartmentAndSchoolYear(departmentDouble, schoolYearDouble)).thenReturn(true);
//
//        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(student1DoubleId, peDoubleID);
//
//        // act
//        boolean result = enrollment.isEnrolmentAssociatedToDepartmentAndSchoolYear(departmentDouble, schoolYearDouble);
//
//        // assert
//        assertTrue(result);
//    }
//
//    // Test returns false when department and school year are not associated with the enrollment
//    @Test
//    void shouldReturnFalseWhenDepartmentAndSchoolYearAreNotAssociatedWithTheEnrolment() {
//        // arrange
//        StudentID student1DoubleId = mock(StudentID.class);
//        Department departmentDouble = mock(Department.class);
//        SchoolYear schoolYearDouble = mock(SchoolYear.class);
//        ProgrammeEdition editionDouble = mock(ProgrammeEdition.class);
//        ProgrammeEditionID editionDoubleId = mock(ProgrammeEditionID.class);
//
//
//        when(editionDouble.isEditionAssociatedToDepartmentAndSchoolYear(departmentDouble, schoolYearDouble)).thenReturn(false);
//
//        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(student1DoubleId, editionDoubleId);
//
//        // act
//        boolean result = enrollment.isEnrolmentAssociatedToDepartmentAndSchoolYear(departmentDouble, schoolYearDouble);
//
//        // assert
//        assertFalse(result);
//    }

    @Test
    void shouldReturnStudentUniqueNumber() {
        // Arrange

        StudentID doubleSt1Id = mock(StudentID.class);

        ProgrammeEditionID doublePEId = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(doubleSt1Id, doublePEId);

        // Act
        StudentID studentId = enrollment.getStudentID();

        // Assert
        assertEquals(doubleSt1Id, studentId);
    }

    @Test
    void shouldReturnFalseIfObjectIsDifferent_EqualsMethod() {
        // Arrange
        Student st1 = mock(Student.class);
        StudentID st1Id = mock(StudentID.class);
        ProgrammeEditionID pe1Id = mock(ProgrammeEditionID.class);
        LocalDate currentDate = LocalDate.now();

        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(st1Id, pe1Id);

        // Act & Assert
        assertFalse(enrollment.equals(new Object()));
    }

    @Test
    void shouldReturnFalseIfStudentsAreDifferent_EqualsMethod() {
        // Arrange
        Student student1 = mock(Student.class);
        StudentID student1Id = mock(StudentID.class);
        Student student2 = mock(Student.class);
        StudentID student2Id = mock(StudentID.class);
        ProgrammeEdition edition = mock(ProgrammeEdition.class);
        ProgrammeEditionID editionId = mock(ProgrammeEditionID.class);
        LocalDate currentDate = LocalDate.now();

        ProgrammeEditionEnrolment enrollment1 = new ProgrammeEditionEnrolment(student1Id, editionId);
        ProgrammeEditionEnrolment enrollment2 = new ProgrammeEditionEnrolment(student2Id, editionId);

        // Act & Assert
        assertFalse(enrollment1.equals(enrollment2));
    }

    @Test
    void shouldReturnTrue_WhenSameStudentIsPassedAsParameter() {
        // Arrange
        Student doubleStudent = mock(Student.class);
        StudentID doubleStudentId = mock(StudentID.class);
        ProgrammeEdition doublePE = mock(ProgrammeEdition.class);
        ProgrammeEditionID doublePEId = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(doubleStudentId, doublePEId);

        // Act & Assert
        assertTrue(enrollment.hasSameStudent(doubleStudentId));

    }

    @Test
    void shouldReturnFalse_WhenDifferentStudentIsPassedAsParameter() {
        // Arrange

        StudentID doubleStudentId = mock(StudentID.class);
        StudentID doubleStudentId2 = mock(StudentID.class);
        ProgrammeEditionID doubleProgrammeEditionId = mock(ProgrammeEditionID.class);

        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(doubleStudentId, doubleProgrammeEditionId);

        // Act & Assert
        assertFalse(enrollment.hasSameStudent(doubleStudentId2));
    }

    @Test
    void shouldReturnTrue_WhenSameProgrammeEditionIsPassedAsParameter() {
        // Arrange
        Student doubleStudent = mock(Student.class);
        StudentID doubleStudentId = mock(StudentID.class);
        ProgrammeEdition doublePE = mock(ProgrammeEdition.class);
        ProgrammeEditionID doublePEId = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(doubleStudentId, doublePEId);

        // Act & Assert
        assertTrue(enrollment.hasSameProgrammeEdition(doublePEId));
    }

    @Test
    void shouldReturnFalse_WhenDifferentProgrammeEditionIsPassedAsParameter() {
        // Arrange
        Student doubleStudent = mock(Student.class);
        StudentID doubleStudentId = mock(StudentID.class);
        ProgrammeEdition doubleProgrammeEdition = mock(ProgrammeEdition.class);
        ProgrammeEditionID doubleProgrammeEditionId = mock(ProgrammeEditionID.class);
        ProgrammeEdition doubleProgrammeEdition1 = mock(ProgrammeEdition.class);
        ProgrammeEditionID doubleProgrammeEdition1Id = mock(ProgrammeEditionID.class);

        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(doubleStudentId, doubleProgrammeEditionId);

        // Act & Assert
        assertFalse(enrollment.hasSameProgrammeEdition(doubleProgrammeEdition1Id));
    }

}
package PAI.domain.programmeEditionEnrolment;

import PAI.VOs.*;
import PAI.persistence.mem.programmeEditionEnrolment.IProgrammeEditionEnrolmentListFactory;
import PAI.persistence.mem.programmeEditionEnrolment.ProgrammeEditionEnrolmentRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEditionEnrolmentTest {

    @Test
    void should_return_a_valid_programme_edition_enrollment() throws Exception {
        //arrange
        StudentID studentDoubleId = mock(StudentID.class);
        ProgrammeEditionID peDoubleId = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        //act + assert
        ProgrammeEditionEnrolment pee1 = new ProgrammeEditionEnrolment(studentDoubleId, peDoubleId, programmeEditionEnrolmentGeneratedID);

    }

    @Test
    void should_return_a_valid_programme_edition_enrollment_with_new_constructor() throws Exception {
        //arrange
        StudentID studentDoubleId = mock(StudentID.class);
        ProgrammeEditionID peDoubleId = mock(ProgrammeEditionID.class);
        Date localDate = mock(Date.class);
        EnrolmentStatus enrolmentStatus = mock(EnrolmentStatus.class);
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        //act + assert
        ProgrammeEditionEnrolment pee1 = new ProgrammeEditionEnrolment(studentDoubleId, peDoubleId, localDate, enrolmentStatus, programmeEditionEnrolmentGeneratedID);

    }

    @Test
    void everythingNullGenerateException() throws Exception {
        //arrange

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionEnrolment(null, null, null));
    }

    @Test
    void everythingNullGenerateException_new_constructor() throws Exception {
        //arrange

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionEnrolment(null, null,null, null, null));
    }

    @Test
    void programmeEditionIdAndDateNullGenerateException() throws Exception {

        StudentID studentDoubleId = mock(StudentID.class);
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionEnrolment(studentDoubleId, null, null, null,null));
    }

    @Test
    void shouldThrowExceptionWhenStudentIdIsNull_new_constructor() {
        ProgrammeEditionID doubleProgrammeEditionId = mock(ProgrammeEditionID.class);
        Date validDate = mock(Date.class);

        assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEditionEnrolment(null, doubleProgrammeEditionId, validDate, null, null);
        });
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionIdIsNull_new_constructor() {
        StudentID doubleStudentID = mock(StudentID.class);
        Date validDate = mock(Date.class);

        assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEditionEnrolment(doubleStudentID, null, validDate, null, null);
        });
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionIdAndStudentIDIsNull_new_constructor() {
        Date validDate = mock(Date.class);

        assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEditionEnrolment(null, null, validDate, null, null);
        });
    }

    @Test
    void should_use_LocalDate_now_if_null_is_passed_for_date() throws Exception {
        // Arrange
        StudentID studentDoubleId = mock(StudentID.class);
        ProgrammeEditionID peDoubleId = mock(ProgrammeEditionID.class);
        EnrolmentStatus enrolmentStatus = mock(EnrolmentStatus.class);

        // Act
        ProgrammeEditionEnrolment enrolment = new ProgrammeEditionEnrolment(studentDoubleId, peDoubleId, null, enrolmentStatus, null);

        // Assert
        assertEquals(Date.now(), enrolment.getEnrolmentDate());
    }

    @Test
    void programmeNullGenerateException() throws Exception {
        //arrange
        StudentID studentDoubleId = mock(StudentID.class);
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionEnrolment(studentDoubleId, null, null));
    }

    @Test
    void shouldReturnFalseIfProgrammeEditionIsNull_EqualsMethod() throws Exception {
        //arrange
        StudentID studentDoubleId = mock(StudentID.class);
        ProgrammeEditionID peDoubleId = mock(ProgrammeEditionID.class);

        //act
        ProgrammeEditionEnrolment enrollment1 = new ProgrammeEditionEnrolment(studentDoubleId, peDoubleId, null);

        //assert
        assertFalse(enrollment1.equals(null));
    }


    @Test
    void shouldReturnTrueIfProgrammeEditionIsEqualThis_EqualsMethod() throws Exception {
        //arrange
        StudentID studentDoubleId = mock(StudentID.class);
        ProgrammeEditionID peDoubleId = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        //act
        ProgrammeEditionEnrolment enrollment1 = new ProgrammeEditionEnrolment(studentDoubleId, peDoubleId, programmeEditionEnrolmentGeneratedID);

        //assert
        assertTrue(enrollment1.equals(enrollment1));
    }

    @Test
    void shouldReturnTrueIfAllFieldsAreEqual_EqualsMethod() throws Exception {
        // Arrange with mock objects
        StudentID studentMockId = mock(StudentID.class);
        ProgrammeEditionID programmeEditionMock = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        // Act
        ProgrammeEditionEnrolment enrollment1 = new ProgrammeEditionEnrolment(studentMockId, programmeEditionMock, programmeEditionEnrolmentGeneratedID);
        ProgrammeEditionEnrolment enrollment2 = new ProgrammeEditionEnrolment(studentMockId, programmeEditionMock, programmeEditionEnrolmentGeneratedID);

        // Assert
        assertTrue(enrollment1.equals(enrollment2));
    }


    @Test
    void shouldReturnProgrammeEditionFromEnrolment_NotNull() throws Exception {
        // Arrange
        StudentID studentDouble = mock(StudentID.class);
        ProgrammeEditionID peDouble = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(studentDouble, peDouble, programmeEditionEnrolmentGeneratedID);

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
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(studentDoubleId, peDoubleId, programmeEditionEnrolmentGeneratedID);

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
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(studentDoubleId, peDoubleId, programmeEditionEnrolmentGeneratedID);

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
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(studentDoubleId, peDouble, programmeEditionEnrolmentGeneratedID);

        // Act
        StudentID foundStudentId = enrollment.findStudentInProgrammeEdition();

        // Assert
        assertEquals(studentDoubleId, foundStudentId, "The found student should be the same as the mock student.");
    }

    @Test
    void shouldReturnStudentUniqueNumber() {
        // Arrange

        StudentID doubleSt1Id = mock(StudentID.class);

        ProgrammeEditionID doublePEId = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(doubleSt1Id, doublePEId, programmeEditionEnrolmentGeneratedID);

        // Act
        StudentID studentId = enrollment.findStudentInProgrammeEdition();

        // Assert
        assertEquals(doubleSt1Id, studentId);
    }

    @Test
    void shouldReturnFalseIfObjectIsDifferent_EqualsMethod() {
        // Arrange

        StudentID st1Id = mock(StudentID.class);
        ProgrammeEditionID pe1Id = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(st1Id, pe1Id, programmeEditionEnrolmentGeneratedID);

        // Act & Assert
        assertFalse(enrollment.equals(new Object()));
    }

    @Test
    void shouldReturnFalseIfStudentsAreDifferent_EqualsMethod() {
        // Arrange
        StudentID student1Id = mock(StudentID.class);
        StudentID student2Id = mock(StudentID.class);
        ProgrammeEditionID editionId = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolment enrollment1 = new ProgrammeEditionEnrolment(student1Id, editionId, programmeEditionEnrolmentGeneratedID);
        ProgrammeEditionEnrolment enrollment2 = new ProgrammeEditionEnrolment(student2Id, editionId, programmeEditionEnrolmentGeneratedID);

        // Act & Assert
        assertFalse(enrollment1.equals(enrollment2));
    }

    @Test
    void shouldReturnTrue_WhenSameStudentIsPassedAsParameter() {
        // Arrange
        StudentID doubleStudentId = mock(StudentID.class);
        ProgrammeEditionID doublePEId = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);
        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(doubleStudentId, doublePEId, programmeEditionEnrolmentGeneratedID);

        // Act & Assert
        assertTrue(enrollment.hasSameStudent(doubleStudentId));

    }

    @Test
    void shouldReturnFalse_WhenDifferentStudentIsPassedAsParameter() {
        // Arrange
        StudentID doubleStudentId = mock(StudentID.class);
        StudentID doubleStudentId2 = mock(StudentID.class);
        ProgrammeEditionID doubleProgrammeEditionId = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(doubleStudentId, doubleProgrammeEditionId, programmeEditionEnrolmentGeneratedID);

        // Act & Assert
        assertFalse(enrollment.hasSameStudent(doubleStudentId2));
    }

    @Test
    void shouldReturnTrue_WhenSameProgrammeEditionIsPassedAsParameter() {
        // Arrange
        StudentID doubleStudentId = mock(StudentID.class);
        ProgrammeEditionID doublePEId = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);
        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(doubleStudentId, doublePEId, programmeEditionEnrolmentGeneratedID);

        // Act & Assert
        assertTrue(enrollment.hasSameProgrammeEdition(doublePEId));
    }

    @Test
    void shouldReturnFalse_WhenDifferentProgrammeEditionIsPassedAsParameter() {
        // Arrange
        StudentID doubleStudentId = mock(StudentID.class);
        ProgrammeEditionID doubleProgrammeEditionId = mock(ProgrammeEditionID.class);
        ProgrammeEditionID doubleProgrammeEdition1Id = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(doubleStudentId, doubleProgrammeEditionId, programmeEditionEnrolmentGeneratedID);

        // Act & Assert
        assertFalse(enrollment.hasSameProgrammeEdition(doubleProgrammeEdition1Id));
    }

    @Test
    void should_return_correct_ID_when_several_exists() {

        //arrange
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepositoryImpl repository = new ProgrammeEditionEnrolmentRepositoryImpl(doubleIPEELF);

        ProgrammeEditionEnrolment enrolment1 = mock(ProgrammeEditionEnrolment.class);
        ProgrammeEditionEnrolment enrolment2 = mock(ProgrammeEditionEnrolment.class);
        ProgrammeEditionEnrolment enrolment3 = mock(ProgrammeEditionEnrolment.class);

        ProgrammeEditionEnrolmentID id1 = mock(ProgrammeEditionEnrolmentID.class);
        ProgrammeEditionEnrolmentID id2 = mock(ProgrammeEditionEnrolmentID.class);
        ProgrammeEditionEnrolmentID id3 = mock(ProgrammeEditionEnrolmentID.class);

        when(enrolment1.identity()).thenReturn(id1);
        when(enrolment2.identity()).thenReturn(id2);
        when(enrolment3.identity()).thenReturn(id3);

        repository.save(enrolment1);
        repository.save(enrolment2);
        repository.save(enrolment3);

        //act
        Optional<ProgrammeEditionEnrolment> idExists = repository.ofIdentity(id2);

        //assert
        assertTrue(idExists.isPresent());
        assertEquals(enrolment2, idExists.get());
    }

    @Test
    void testEqualsReflexivity() {
        //Arrange
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepositoryImpl repository = new ProgrammeEditionEnrolmentRepositoryImpl(doubleIPEELF);

        //Act + Assert
        assertEquals(repository, repository);
    }

    @Test
    void testEqualsNull() {
        //Arrange
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepositoryImpl repo1 = new ProgrammeEditionEnrolmentRepositoryImpl(doubleIPEELF);

        //Act+Assert
        assertNotEquals(repo1, null);
    }

    @Test
    void testEqualsDifferentClass() {
        //Arrange
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepositoryImpl repo1 = new ProgrammeEditionEnrolmentRepositoryImpl(doubleIPEELF);

        //Act
        String differentClassObject = "string";

        //Assert
        assertNotEquals(repo1, differentClassObject);
    }

    @Test
    void testHashCodeConsistency() {
        //Arrange
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepositoryImpl repo1 = new ProgrammeEditionEnrolmentRepositoryImpl(doubleIPEELF);

        //Act
        int hash1 = repo1.hashCode();
        int hash2 = repo1.hashCode();

        //Assert
        assertEquals(hash1, hash2);
    }

    @Test
    void testHashCodeInHashSet() {
        //Arrange
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepositoryImpl repo1 = new ProgrammeEditionEnrolmentRepositoryImpl(doubleIPEELF);

        IProgrammeEditionEnrolmentListFactory doubleIPEELF2 = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepositoryImpl repo2 = new ProgrammeEditionEnrolmentRepositoryImpl(doubleIPEELF2);

        //Act
        HashSet<ProgrammeEditionEnrolmentRepositoryImpl> set = new HashSet<>();
        set.add(repo1);
        set.add(repo2);

        //Assert
        assertTrue(set.contains(repo2));
    }

    @Test
    void shouldReturnFalseWhenComparingWithNull() {
        // Arrange
        ProgrammeEditionEnrolment enrolment = mock(ProgrammeEditionEnrolment.class);

        // Act & Assert
        assertFalse(enrolment.sameAs(null));
    }

    @Test
    void shouldReturnFalseWhenComparingDifferentClass() {
        // Arrange
        ProgrammeEditionEnrolment enrolment = mock(ProgrammeEditionEnrolment.class);

        // Act & Assert
        assertFalse(enrolment.sameAs("Some random string"));
    }

    @Test
    void shouldReturnSameHashCodeForEqualObjects() {
        // Arrange
        StudentID studentID = mock(StudentID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolment enrolment1 = new ProgrammeEditionEnrolment(studentID, programmeEditionID, programmeEditionEnrolmentGeneratedID);
        ProgrammeEditionEnrolment enrolment2 = new ProgrammeEditionEnrolment(studentID, programmeEditionID, programmeEditionEnrolmentGeneratedID);

        // Act & Assert
        assertEquals(enrolment1.hashCode(), enrolment2.hashCode());
    }

    @Test
    void should_return_a_ProgrammeEditionEnrolmentID(){

        // arrange
        StudentID studentIDDouble = mock(StudentID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolment programmeEditionEnrolment = new ProgrammeEditionEnrolment(studentIDDouble, programmeEditionIDDouble, programmeEditionEnrolmentGeneratedID);

        // act
        ProgrammeEditionEnrolmentID result = programmeEditionEnrolment.identity();

        // assert
        assertNotNull(result);
    }
    @Test
    void should_return_true_if_are_different_ProgrammeEditionEnrolment(){

        // arrange
        StudentID studentIDDouble = mock(StudentID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolment programmeEditionEnrolment = new ProgrammeEditionEnrolment(studentIDDouble, programmeEditionIDDouble, programmeEditionEnrolmentGeneratedID);
        ProgrammeEditionEnrolment programmeEditionEnrolment1 = new ProgrammeEditionEnrolment(studentIDDouble, programmeEditionIDDouble, programmeEditionEnrolmentGeneratedID);

        // act
        boolean result = programmeEditionEnrolment.sameAs(programmeEditionEnrolment1);

        // assert
        assertTrue(result);
    }

    @Test
    void should_return_false_if_ProgrammeEditionEnrolment_is_Null(){

        // arrange
        StudentID studentIDDouble = mock(StudentID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolment programmeEditionEnrolment = new ProgrammeEditionEnrolment(studentIDDouble, programmeEditionIDDouble, programmeEditionEnrolmentGeneratedID);

        // act
        boolean result = programmeEditionEnrolment.sameAs(null);

        // assert
        assertFalse(result);
    }

    @Test
    void should_return_true_if_are_the_same_memory_reference (){

        // arrange
        StudentID studentIDDouble = mock(StudentID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolment programmeEditionEnrolment = new ProgrammeEditionEnrolment(studentIDDouble, programmeEditionIDDouble, programmeEditionEnrolmentGeneratedID);


        // act
        boolean result = programmeEditionEnrolment.sameAs(programmeEditionEnrolment);

        // assert
        assertTrue(result);
    }

    @Test
    void should_return_false_if_are_not_the_same_object() {

        // arrange
        StudentID studentID1 = mock(StudentID.class);
        ProgrammeEditionID programmeEditionID1 = mock(ProgrammeEditionID.class);
        StudentID studentID2 = mock(StudentID.class);
        ProgrammeEditionID programmeEditionID2 = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolment programmeEditionEnrolment1 = new ProgrammeEditionEnrolment(studentID1, programmeEditionID1, programmeEditionEnrolmentGeneratedID);
        ProgrammeEditionEnrolment programmeEditionEnrolment2 = new ProgrammeEditionEnrolment(studentID2, programmeEditionID2, programmeEditionEnrolmentGeneratedID);

        // act
        boolean result = programmeEditionEnrolment1.sameAs(programmeEditionEnrolment2);

        // assert
        assertFalse(result);
    }

    @Test
    void should_return_false_if_has_not_the_same_StudentID() {

        // arrange
        StudentID studentID1 = mock(StudentID.class);
        ProgrammeEditionID programmeEditionID1 = mock(ProgrammeEditionID.class);
        StudentID studentID2 = mock(StudentID.class);
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolment programmeEditionEnrolment1 = new ProgrammeEditionEnrolment(studentID1, programmeEditionID1, programmeEditionEnrolmentGeneratedID);
        ProgrammeEditionEnrolment programmeEditionEnrolment2 = new ProgrammeEditionEnrolment(studentID2, programmeEditionID1, programmeEditionEnrolmentGeneratedID);

        // act
        boolean result = programmeEditionEnrolment1.sameAs(programmeEditionEnrolment2);

        // assert
        assertFalse(result);
    }

    @Test
    void should_return_false_if_has_not_the_same_ProgrammeEdition_ID() {

        // arrange
        StudentID studentID1 = mock(StudentID.class);
        ProgrammeEditionID programmeEditionID1 = mock(ProgrammeEditionID.class);
        ProgrammeEditionID programmeEditionID2 = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolment programmeEditionEnrolment1 = new ProgrammeEditionEnrolment(studentID1, programmeEditionID1, programmeEditionEnrolmentGeneratedID);
        ProgrammeEditionEnrolment programmeEditionEnrolment2 = new ProgrammeEditionEnrolment(studentID1, programmeEditionID2, programmeEditionEnrolmentGeneratedID);

        // act
        boolean result = programmeEditionEnrolment1.sameAs(programmeEditionEnrolment2);

        // assert
        assertFalse(result);
    }

    @Test
    void should_return_false_for_different_Students_in_ProgrammeEdition_Enrollment() {

        // arrange
        StudentID student1 = mock(StudentID.class);
        StudentID student2 = mock(StudentID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolment enrollment1 = new ProgrammeEditionEnrolment(student1, programmeEditionIDDouble, programmeEditionEnrolmentGeneratedID);

        // act
        boolean result = enrollment1.hasSameStudent(student2);

        // assert
        assertFalse(result);
    }

    @Test
    void should_return_false_when_Student_Id_is_null() {

        // arrange
        StudentID student1 = mock(StudentID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolment enrollment1 = new ProgrammeEditionEnrolment(student1, programmeEditionIDDouble, programmeEditionEnrolmentGeneratedID);

        // act
        boolean result = enrollment1.hasSameStudent(null);

        // assert
        assertFalse(result);
    }

    @Test
    void should_return_false_when_ProgrammeEdition_Id_is_Null() {

        // arrange
        StudentID student1 = mock(StudentID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(student1, programmeEditionIDDouble, programmeEditionEnrolmentGeneratedID);

        // act
        boolean result = enrollment.hasSameProgrammeEdition(null);

        // assert
        assertFalse(result);
    }

    @Test
    void should_return_same_hashCode_for_same_enrolmentID() {
        // Arrange
        StudentID studentID = mock(StudentID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolment enrolment1 = new ProgrammeEditionEnrolment(studentID, programmeEditionID, programmeEditionEnrolmentGeneratedID);
        ProgrammeEditionEnrolment enrolment2 = new ProgrammeEditionEnrolment(studentID, programmeEditionID, programmeEditionEnrolmentGeneratedID);

        // Act
        int hash1 = enrolment1.hashCode();
        int hash2 = enrolment2.hashCode();

        // Assert
        assertEquals(hash1, hash2);
    }

    @Test
    void should_return_different_hashCodes_for_different_enrolmentIDs() {
        // Arrange
        StudentID studentID1 = mock(StudentID.class);
        ProgrammeEditionID programmeEditionID1 = mock(ProgrammeEditionID.class);

        StudentID studentID2 = mock(StudentID.class);
        ProgrammeEditionID programmeEditionID2 = mock(ProgrammeEditionID.class);

        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolment enrolment1 = new ProgrammeEditionEnrolment(studentID1, programmeEditionID1, programmeEditionEnrolmentGeneratedID);
        ProgrammeEditionEnrolment enrolment2 = new ProgrammeEditionEnrolment(studentID2, programmeEditionID2, programmeEditionEnrolmentGeneratedID);

        // Act
        int hash1 = enrolment1.hashCode();
        int hash2 = enrolment2.hashCode();

        // Assert
        assertNotEquals(hash1, hash2);
    }

    @Test
    void shouldReturnTrueIfEnrolmentIsAssociatedToProgrammeAndSchoolYear() {
        //arrange
        ProgrammeID programmeID1 = mock(ProgrammeID.class);
        SchoolYearID schoolYear1 = mock(SchoolYearID.class);
        StudentID student1 = mock(StudentID.class);
        ProgrammeEditionID programmeEditionID1 = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        when(programmeEditionID1.isSameProgrammeEdition(programmeID1, schoolYear1)).thenReturn(true);
        ProgrammeEditionEnrolment pee1=new ProgrammeEditionEnrolment(student1,programmeEditionID1, programmeEditionEnrolmentGeneratedID);
        List<ProgrammeID> programmeIDs = List.of(programmeID1);
        //act
        boolean result = pee1.isEnrolmentAssociatedToProgrammeAndSchoolYear(schoolYear1,programmeIDs);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentIsNotAssociatedToProgrammeAndSchoolYear() {
        //arrange
        ProgrammeID programmeID1 = mock(ProgrammeID.class);
        SchoolYearID schoolYear1 = mock(SchoolYearID.class);
        StudentID student1 = mock(StudentID.class);
        ProgrammeEditionID programmeEditionID1 = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        when(programmeEditionID1.isSameProgrammeEdition(programmeID1, schoolYear1)).thenReturn(false);
        ProgrammeEditionEnrolment pee1=new ProgrammeEditionEnrolment(student1,programmeEditionID1, programmeEditionEnrolmentGeneratedID);
        List<ProgrammeID> programmeIDs = List.of(programmeID1);
        //act
        boolean result = pee1.isEnrolmentAssociatedToProgrammeAndSchoolYear(schoolYear1,programmeIDs);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfProgrammeListIsEmpty() {
        //arrange
        ProgrammeID programmeID1 = mock(ProgrammeID.class);
        SchoolYearID schoolYear1 = mock(SchoolYearID.class);
        StudentID student1 = mock(StudentID.class);
        ProgrammeEditionID programmeEditionID1 = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        when(programmeEditionID1.isSameProgrammeEdition(programmeID1, schoolYear1)).thenReturn(true);
        ProgrammeEditionEnrolment pee1=new ProgrammeEditionEnrolment(student1,programmeEditionID1, programmeEditionEnrolmentGeneratedID);
        List<ProgrammeID> programmeIDs = List.of();
        //act
        boolean result = pee1.isEnrolmentAssociatedToProgrammeAndSchoolYear(schoolYear1,programmeIDs);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfProgrammeListIsNull() {
        //arrange
        ProgrammeID programmeID1 = mock(ProgrammeID.class);
        SchoolYearID schoolYear1 = mock(SchoolYearID.class);
        StudentID student1 = mock(StudentID.class);
        ProgrammeEditionID programmeEditionID1 = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        when(programmeEditionID1.isSameProgrammeEdition(programmeID1, schoolYear1)).thenReturn(true);
        ProgrammeEditionEnrolment pee1=new ProgrammeEditionEnrolment(student1,programmeEditionID1, programmeEditionEnrolmentGeneratedID);
        List<ProgrammeID> programmeIDs = null;
        //act
        boolean result = pee1.isEnrolmentAssociatedToProgrammeAndSchoolYear(schoolYear1,programmeIDs);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfSchoolYearIsNull() {
        //arrange
        ProgrammeID programmeID1 = mock(ProgrammeID.class);
        SchoolYearID schoolYear1 = null;
        StudentID student1 = mock(StudentID.class);
        ProgrammeEditionID programmeEditionID1 = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        when(programmeEditionID1.isSameProgrammeEdition(programmeID1, schoolYear1)).thenReturn(true);
        ProgrammeEditionEnrolment pee1=new ProgrammeEditionEnrolment(student1,programmeEditionID1, programmeEditionEnrolmentGeneratedID);
        List<ProgrammeID> programmeIDs = List.of(programmeID1);
        //act
        boolean result = pee1.isEnrolmentAssociatedToProgrammeAndSchoolYear(schoolYear1,programmeIDs);

        //assert
        assertFalse(result);
    }

    @Test
    void newEnrollment_ShouldBeActiveByDefault() {
        // Arrange
        StudentID studentIDMock = mock(StudentID.class);
        ProgrammeEditionID programmeEditionIDMock = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        // Act
        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(studentIDMock, programmeEditionIDMock, programmeEditionEnrolmentGeneratedID);

        // Assert
        assertTrue(enrollment.isEnrolmentActive(), "New enrolment should be active by default");
    }

    @Test
    void deactivateEnrollment_ShouldSetEnrollmentToInactive() {
        // Arrange
        StudentID studentIDMock = mock(StudentID.class);
        ProgrammeEditionID programmeEditionIDMock = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(studentIDMock, programmeEditionIDMock, programmeEditionEnrolmentGeneratedID);

        // Act
        enrollment.deactivateEnrolment();

        // Assert
        assertFalse(enrollment.isEnrolmentActive());
    }

    @Test
    void deactivateEnrollment_ShouldRemainInactiveAfterMultipleDeactivations() {
        // Arrange
        StudentID studentIDMock = mock(StudentID.class);
        ProgrammeEditionID programmeEditionIDMock = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);

        ProgrammeEditionEnrolment enrollment = new ProgrammeEditionEnrolment(studentIDMock, programmeEditionIDMock, programmeEditionEnrolmentGeneratedID);

        // Act
        enrollment.deactivateEnrolment();
        enrollment.deactivateEnrolment();

        // Assert
        assertFalse(enrollment.isEnrolmentActive(), "Enrolment should remain inactive after multiple deactivations");
    }

}

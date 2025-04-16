package PAI.repository;

import PAI.VOs.*;
import PAI.VOs.Date;
import PAI.domain.*;
import PAI.factory.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CourseEditionEnrolmentRepositoryImplTest {

    //test enroll a student in a course edition method

    @Test
    void shouldReturnTrueWithAValidCourseEditionEnrollment() {
        //arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory doubleCeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleCeeFactory, doubleCeeListFactory);
        Set<CourseEditionEnrolment> doubleEnrolmentset = mock(Set.class);


        StudentID doubleStID1 = mock(StudentID.class);
        CourseEditionID doubleCeID1 = mock(CourseEditionID.class);
        CourseEditionEnrolment doubleCee1 = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStID1, doubleCeID1)).thenReturn(doubleCee1);
        when(doubleEnrolmentset.add(doubleCee1)).thenReturn(true);

        //act
        boolean result = repository.enrolStudentInACourseEdition(doubleStID1, doubleCeID1);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfAddFails() {
        // Arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory doubleCeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        Set<CourseEditionEnrolment> doubleEnrolmentSet = mock(Set.class);

        StudentID doubleStudentId = mock(StudentID.class);
        CourseEditionID doubleCourseEditionId = mock(CourseEditionID.class);
        CourseEditionEnrolment enrolment = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStudentId, doubleCourseEditionId)).thenReturn(enrolment);
        when(doubleCeeListFactory.getCourseEditionEnrolmentList()).thenReturn(doubleEnrolmentSet);
        when(doubleEnrolmentSet.add(enrolment)).thenReturn(false);

        CourseEditionEnrolmentRepositoryImpl repo = new CourseEditionEnrolmentRepositoryImpl(doubleCeeFactory, doubleCeeListFactory);

        // Act
        boolean result = repo.enrolStudentInACourseEdition(doubleStudentId, doubleCourseEditionId);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldAllowEnrollmentOfDifferentStudentsInDifferentCourseEditions() {
        //arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory doubleCeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleCeeFactory, doubleCeeListFactory);
        Set<CourseEditionEnrolment> doubleEnrolmentset = mock(Set.class);

        StudentID doubleStID1 = mock(StudentID.class);
        CourseEditionID doubleCeID1 = mock(CourseEditionID.class);
        CourseEditionEnrolment doubleCee1 = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStID1, doubleCeID1)).thenReturn(doubleCee1);

        StudentID doubleStID2 = mock(StudentID.class);
        CourseEditionID doubleCeID2 = mock(CourseEditionID.class);
        CourseEditionEnrolment doubleCee2 = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStID2, doubleCeID1)).thenReturn(doubleCee2);

        when(doubleEnrolmentset.add(doubleCee1)).thenReturn(true);
        when(doubleEnrolmentset.add(doubleCee2)).thenReturn(true);

        repository.enrolStudentInACourseEdition(doubleStID1, doubleCeID1);

        //act
        boolean result = repository.enrolStudentInACourseEdition(doubleStID2, doubleCeID2);

        //assert
        assertEquals(true, result);
    }

    @Test
    void shouldAllowEnrollmentOfDifferentStudentsInSameCourseEdition() {
        //arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory doubleCeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleCeeFactory, doubleCeeListFactory);
        Set<CourseEditionEnrolment> doubleEnrolmentset = mock(Set.class);

        StudentID doubleStID1 = mock(StudentID.class);
        CourseEditionID doubleCeID1 = mock(CourseEditionID.class);
        CourseEditionEnrolment doubleCee1 = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStID1, doubleCeID1)).thenReturn(doubleCee1);

        StudentID doubleStID2 = mock(StudentID.class);
        CourseEditionEnrolment doubleCee2 = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStID2, doubleCeID1)).thenReturn(doubleCee2);

        when(doubleEnrolmentset.add(doubleCee1)).thenReturn(true);
        when(doubleEnrolmentset.add(doubleCee2)).thenReturn(true);

        repository.enrolStudentInACourseEdition(doubleStID1, doubleCeID1);

        //act
        boolean result = repository.enrolStudentInACourseEdition(doubleStID2, doubleCeID1);

        //assert
        assertEquals(true, result);
    }

    @Test
    void shouldAllowEnrollmentOfSameStudentInDifferentCourseEditions() {
        //arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory doubleCeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleCeeFactory, doubleCeeListFactory);
        Set<CourseEditionEnrolment> doubleEnrolmentset = mock(Set.class);

        StudentID doubleStID1 = mock(StudentID.class);
        CourseEditionID doubleCeID1 = mock(CourseEditionID.class);
        CourseEditionEnrolment doubleCee1 = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStID1, doubleCeID1)).thenReturn(doubleCee1);

        CourseEditionID doubleCeID2 = mock(CourseEditionID.class);
        CourseEditionEnrolment doubleCee2 = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStID1, doubleCeID2)).thenReturn(doubleCee2);

        when(doubleEnrolmentset.add(doubleCee1)).thenReturn(true);
        when(doubleEnrolmentset.add(doubleCee2)).thenReturn(true);

        repository.enrolStudentInACourseEdition(doubleStID1, doubleCeID1);

        //act
        boolean result = repository.enrolStudentInACourseEdition(doubleStID1, doubleCeID2);

        //assert
        assertEquals(true, result);
    }

    @Test
    void shouldReturnFalseWhenCourseEditionEnrollmentAlreadyExists() {
        //arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory doubleCeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleCeeFactory, doubleCeeListFactory);
        Set<CourseEditionEnrolment> doubleEnrolmentSet = mock (Set.class);

        StudentID doubleStID1 = mock(StudentID.class);
        CourseEditionID doubleCeID1 = mock(CourseEditionID.class);

        CourseEditionEnrolment doubleCee1 = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolment doubleCee2 = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStID1, doubleCeID1)).thenReturn(doubleCee1);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStID1, doubleCeID1)).thenReturn(doubleCee2);
        when(doubleEnrolmentSet.add(doubleCee2)).thenReturn(false);
        repository.enrolStudentInACourseEdition(doubleStID1, doubleCeID1);

        //act
        boolean result2 = repository.enrolStudentInACourseEdition(doubleStID1, doubleCeID1);

        //assert
        assertFalse(result2);
    }

    @Test
    void shouldReturnFalseIfStudentIsNull() {
        //arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory doubleCeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleCeeFactory, doubleCeeListFactory);

        CourseEditionID doubleCeID1 = mock(CourseEditionID.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(null, doubleCeID1)).thenThrow();

        //act
        boolean result = repository.enrolStudentInACourseEdition(null, doubleCeID1);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfCourseEditionIsNull() {
        //arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory doubleCeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleCeeFactory, doubleCeeListFactory);

        StudentID doubleStID1 = mock(StudentID.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStID1, null)).thenThrow();

        //act
        boolean result = repository.enrolStudentInACourseEdition(doubleStID1, null);

        //assert
        assertFalse(result);
    }


    //test isStudentEnrolledInCourseEdition method
    @Test
    void shouldConfirmStudentIsEnrollInACourseEdition() throws Exception {
        // arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory ceeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);

        Set<CourseEditionEnrolment> mockSet = new HashSet<>();
        when(ceeListFactory.getCourseEditionEnrolmentList()).thenReturn(mockSet);

        CourseEditionEnrolmentRepositoryImpl repository =
                new CourseEditionEnrolmentRepositoryImpl(doubleCeeFactory, ceeListFactory);

        StudentID studentID = mock(StudentID.class);
        CourseEditionID ce1 = mock(CourseEditionID.class);

        CourseEditionEnrolment cee1 = mock(CourseEditionEnrolment.class);
        when(doubleCeeFactory.createCourseEditionEnrolment(studentID, ce1))
                .thenReturn(cee1);
        when(cee1.knowStudent()).thenReturn(studentID);
        when(cee1.knowCourseEdition()).thenReturn(ce1);
        when(cee1.isEnrolmentActive()).thenReturn(true);

        // act
        repository.enrolStudentInACourseEdition(studentID, ce1);

        // assert
        assertTrue(repository.isStudentEnrolledInCourseEdition(studentID, ce1));
    }


    @Test
    void shouldConfirmStudentIsNotEnrollInACourseEdition() throws Exception {
        // arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);

        Set<CourseEditionEnrolment> mockSet = new HashSet<>();
        when(CeeListFactory.getCourseEditionEnrolmentList()).thenReturn(mockSet);


        CourseEditionEnrolmentRepositoryImpl repository =
                new CourseEditionEnrolmentRepositoryImpl(doubleCeeFactory, CeeListFactory);

        StudentID studentID = mock(StudentID.class);
        CourseEditionID ce1 = mock(CourseEditionID.class);

        StudentID student2 = mock(StudentID.class);


        CourseEditionEnrolment cee1 = mock(CourseEditionEnrolment.class);


        when(doubleCeeFactory.createCourseEditionEnrolment(studentID, ce1)).thenReturn(cee1);


        when(cee1.knowStudent()).thenReturn(studentID);
        when(cee1.knowCourseEdition()).thenReturn(ce1);
        when(cee1.isEnrolmentActive()).thenReturn(true);


        repository.enrolStudentInACourseEdition(studentID, ce1);

        // act

        boolean result = repository.isStudentEnrolledInCourseEdition(student2, ce1);

        // assert

        assertFalse(result);
    }

    @Test
    void shouldReturnCourseEditionFromEnrollment() {
        // Arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleCeeFactory, CeeListFactory);

        StudentID doubleSt1 = mock(StudentID.class);
        CourseEditionID doubleCe1 = mock(CourseEditionID.class);
        CourseEditionEnrolment courseEEnrollments = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleSt1, doubleCe1)).thenReturn(courseEEnrollments);

        when(courseEEnrollments.hasStudent(doubleSt1)).thenReturn(true);

        when(courseEEnrollments.hasCourseEdition(doubleCe1)).thenReturn(true);

        repository.enrolStudentInACourseEdition(doubleSt1, doubleCe1);

        // Act
        Optional<CourseEditionEnrolment> result = repository.findByStudentAndEdition(doubleSt1, doubleCe1);

        // Assert
        assertTrue(result.isPresent(), "The student should be enrolled in the course edition.");

    }


    @Test
    void shouldThrowOptionalEmptyWhenStudentIsNull() {
        // Arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleCeeFactory, CeeListFactory);

        CourseEditionID doubleCe1 = mock(CourseEditionID.class);

        // Act
        Optional<CourseEditionEnrolment> result = repository.findByStudentAndEdition(null, doubleCe1);

        //Assert
        assertTrue(result.isEmpty(), "Expected Optional.empty() when student is null");

    }

    @Test
    void shouldThrowOptionalEmptyWhenCourseEditionIsNull() {
        // Arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleCeeFactory, CeeListFactory);

        StudentID doubleSt1 = mock(StudentID.class);

        // Act
        Optional<CourseEditionEnrolment> result = repository.findByStudentAndEdition(doubleSt1, null);

        //Assert
        assertTrue(result.isEmpty(), "Expected Optional.empty() when courseEdition is null");

    }

    @Test
    void shouldReturnOptionalEmptyWhenNoEnrollmentFound() {
        // Arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleCeeFactory, CeeListFactory);

        StudentID doubleSt1 = mock(StudentID.class);
        CourseEditionID doubleCe1 = mock(CourseEditionID.class);

        // Act
        Optional<CourseEditionEnrolment> result = repository.findByStudentAndEdition(doubleSt1, doubleCe1);

        // Assert
        assertFalse(result.isPresent(), "The result should be empty if the student is not enrolled in the course edition.");
    }


    @Test
    void shouldReturnEmptyWhenStudentIsNotEnrolledInCourseEdition() {
        // Arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleCeeFactory, CeeListFactory);

        StudentID doubleSt1 = mock(StudentID.class);
        CourseEditionID doubleCe1 = mock(CourseEditionID.class);

        // Act
        Optional<CourseEditionEnrolment> result = repository.findByStudentAndEdition(doubleSt1, doubleCe1);

        // Assert
        assertFalse(result.isPresent(), "Expected no enrollment to be found");
    }


    @Test
    void shouldReturnNumberOfStudentsEnrolledInCourseEdition() throws Exception {
        // Arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repo = new CourseEditionEnrolmentRepositoryImpl(doubleCeeFactory, CeeListFactory);

        CourseEditionID doubleCourseEdition1 = mock(CourseEditionID.class);
        StudentID doubleStudent1 = mock(StudentID.class);
        CourseEditionEnrolment cee1 = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStudent1, doubleCourseEdition1)).thenReturn(cee1);

        when(cee1.knowCourseEdition()).thenReturn(doubleCourseEdition1);

        repo.enrolStudentInACourseEdition(doubleStudent1, doubleCourseEdition1);

        // Act
        int studentsEnrolled = repo.numberOfStudentsEnrolledInCourseEdition(doubleCourseEdition1);

        // Assert
        assertEquals(1, studentsEnrolled);
    }

    @Test
    void ShouldReturnZeroWhenThereAreNoEnrolmentsInACourse() throws Exception {
        // Arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repo = new CourseEditionEnrolmentRepositoryImpl(doubleCeeFactory, CeeListFactory);

        CourseEditionID doubleCourseEdition1 = mock(CourseEditionID.class);
        CourseEditionID doubleCourseEdition2 = mock(CourseEditionID.class);

        StudentID doubleStudent1 = mock(StudentID.class);
        StudentID doubleStudent2 = mock(StudentID.class);

        CourseEditionEnrolment cee1 = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolment cee2 = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStudent1, doubleCourseEdition2)).thenReturn(cee1);
        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStudent2, doubleCourseEdition2)).thenReturn(cee2);

        when(cee1.knowCourseEdition()).thenReturn(doubleCourseEdition2);
        when(cee2.knowCourseEdition()).thenReturn(doubleCourseEdition2);

        repo.enrolStudentInACourseEdition(doubleStudent1, doubleCourseEdition2);
        repo.enrolStudentInACourseEdition(doubleStudent2, doubleCourseEdition2);

        // Act
        int studentsEnrolled = repo.numberOfStudentsEnrolledInCourseEdition(doubleCourseEdition1);

        // Assert
        assertEquals(0, studentsEnrolled);
    }

    @Test
    void shouldReturnZeroWhenThereAreNoStudents() throws Exception {
        // Arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repo = new CourseEditionEnrolmentRepositoryImpl(doubleCeeFactory, CeeListFactory);

        // Create a course edition
        CourseEditionID doubleCourseEdition1 = mock(CourseEditionID.class);

        // Act
        int studentsEnrolled = repo.numberOfStudentsEnrolledInCourseEdition(doubleCourseEdition1);

        // Assert
        assertEquals(0, studentsEnrolled);
    }


// ==============================
// Enrolment Removal Success Cases
// ==============================

    // Test Removing an Existing Enrolment
    // System should allow the successful removal of a student enrolled in a course edition
    @Test
    void removeExistingEnrolment_ShouldReturnTrue() {
        // Arrange
        ICourseEditionEnrolmentFactory enrolmentFactoryMock = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl enrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(enrolmentFactoryMock, CeeListFactory);

        StudentID mockStudentID = mock(StudentID.class);
        CourseEditionID mockCourseEditionID = mock(CourseEditionID.class);
        CourseEditionEnrolment mockCee = mock(CourseEditionEnrolment.class);

        when(enrolmentFactoryMock.createCourseEditionEnrolment(mockStudentID, mockCourseEditionID))
                .thenReturn(mockCee);

        when(mockCee.hasStudent(mockStudentID)).thenReturn(true);
        when(mockCee.hasCourseEdition(mockCourseEditionID)).thenReturn(true);
        when(mockCee.isEnrolmentActive()).thenReturn(true);

        enrolmentRepository.enrolStudentInACourseEdition(mockStudentID, mockCourseEditionID);

        // Act
        boolean result = enrolmentRepository.removeEnrolment(mockStudentID, mockCourseEditionID);

        // Assert
        assertTrue(result, "Enrolment should be removed successfully.");
        verify(mockCee).deactivateEnrolment(); // Verify that deactivateEnrolment() was called
    }

    // Multiple Course Edition Removals
    // Ensures that a student can be removed from multiple course editions correctly
    @Test
    void removeStudentFromMultipleCourseEditions_ShouldReturnTrueForBoth() {
        // Arrange
        ICourseEditionEnrolmentFactory enrolmentFactoryMock = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl enrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(enrolmentFactoryMock, CeeListFactory);

        StudentID mockStudentID = mock(StudentID.class);
        CourseEditionID mockCourseEditionID1 = mock(CourseEditionID.class);
        CourseEditionID mockCourseEditionID2 = mock(CourseEditionID.class);
        CourseEditionEnrolment mockCee1 = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolment mockCee2 = mock(CourseEditionEnrolment.class);

        when(enrolmentFactoryMock.createCourseEditionEnrolment(mockStudentID, mockCourseEditionID1)).thenReturn(mockCee1);
        when(enrolmentFactoryMock.createCourseEditionEnrolment(mockStudentID, mockCourseEditionID2)).thenReturn(mockCee2);

        when(mockCee1.hasStudent(mockStudentID)).thenReturn(true);
        when(mockCee1.hasCourseEdition(mockCourseEditionID1)).thenReturn(true);
        when(mockCee1.isEnrolmentActive()).thenReturn(true);

        when(mockCee2.hasStudent(mockStudentID)).thenReturn(true);
        when(mockCee2.hasCourseEdition(mockCourseEditionID2)).thenReturn(true);
        when(mockCee2.isEnrolmentActive()).thenReturn(true);

        enrolmentRepository.enrolStudentInACourseEdition(mockStudentID, mockCourseEditionID1);
        enrolmentRepository.enrolStudentInACourseEdition(mockStudentID, mockCourseEditionID2);

        // Act
        boolean firstRemoval = enrolmentRepository.removeEnrolment(mockStudentID, mockCourseEditionID1);
        boolean secondRemoval = enrolmentRepository.removeEnrolment(mockStudentID, mockCourseEditionID2);

        // Assert
        assertTrue(firstRemoval, "Student should be removed from the first course edition.");
        assertTrue(secondRemoval, "Student should be removed from the second course edition.");
        verify(mockCee1).deactivateEnrolment();
        verify(mockCee2).deactivateEnrolment();
    }

    // Batch Removal of Multiple Students
    // Ensures that different students enrolled in the same course edition can be removed without issues
    @Test
    void removeMultipleStudentsFromSameCourseEdition_ShouldReturnTrueForBoth() {
        // Arrange
        ICourseEditionEnrolmentFactory enrolmentFactoryMock = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl enrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(enrolmentFactoryMock, CeeListFactory);

        CourseEditionID mockCourseEditionID = mock(CourseEditionID.class);
        StudentID mockStudentID1 = mock(StudentID.class);
        StudentID mockStudentID2 = mock(StudentID.class);
        CourseEditionEnrolment mockCee1 = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolment mockCee2 = mock(CourseEditionEnrolment.class);

        when(enrolmentFactoryMock.createCourseEditionEnrolment(mockStudentID1, mockCourseEditionID))
                .thenReturn(mockCee1);
        when(enrolmentFactoryMock.createCourseEditionEnrolment(mockStudentID2, mockCourseEditionID))
                .thenReturn(mockCee2);

        when(mockCee1.hasStudent(mockStudentID1)).thenReturn(true);
        when(mockCee1.hasCourseEdition(mockCourseEditionID)).thenReturn(true);
        when(mockCee1.isEnrolmentActive()).thenReturn(true);

        when(mockCee2.hasStudent(mockStudentID2)).thenReturn(true);
        when(mockCee2.hasCourseEdition(mockCourseEditionID)).thenReturn(true);
        when(mockCee2.isEnrolmentActive()).thenReturn(true);

        enrolmentRepository.enrolStudentInACourseEdition(mockStudentID1, mockCourseEditionID);
        enrolmentRepository.enrolStudentInACourseEdition(mockStudentID2, mockCourseEditionID);

        // Act
        boolean firstRemoval = enrolmentRepository.removeEnrolment(mockStudentID1, mockCourseEditionID);
        boolean secondRemoval = enrolmentRepository.removeEnrolment(mockStudentID2, mockCourseEditionID);

        // Assert
        assertTrue(firstRemoval, "First student's enrolment should be removed successfully.");
        assertTrue(secondRemoval, "Second student's enrolment should be removed successfully.");
        verify(mockCee1).deactivateEnrolment();
        verify(mockCee2).deactivateEnrolment();
    }

// ==============================
// Enrolment Removal Failure Handling
// ==============================

    // Test Removing a Non-Existing Enrolment
    // Ensures that the system does not allow the removal of a non-existent enrolment
    @Test
    void removeNonExistingEnrolment_ShouldReturnFalse() {
        // Arrange
        ICourseEditionEnrolmentFactory enrolmentFactoryMock = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(enrolmentFactoryMock, CeeListFactory);

        StudentID mockStudentID = mock(StudentID.class);
        CourseEditionID mockCourseEditionID = mock(CourseEditionID.class);

        // Act
        boolean result = repository.removeEnrolment(mockStudentID, mockCourseEditionID);

        // Assert
        assertFalse(result, "Removing a non existing enrolment should return false.");
        verify(enrolmentFactoryMock, never()).createCourseEditionEnrolment(any(), any()); // Ensure no enrolment creation occurs
    }

    // Remove Already Inactive Enrolment
    // Ensures that the system does not allow the removal of an enrolment that has already been deactivated
    @Test
    void removeAlreadyInactiveEnrolment_ShouldReturnFalse() {
        // Arrange
        ICourseEditionEnrolmentFactory enrolmentFactoryMock = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl enrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(enrolmentFactoryMock, CeeListFactory);

        StudentID mockStudentID = mock(StudentID.class);
        CourseEditionID mockCourseEditionID = mock(CourseEditionID.class);
        CourseEditionEnrolment mockCee = mock(CourseEditionEnrolment.class);

        when(mockCee.hasStudent(mockStudentID)).thenReturn(true);
        when(mockCee.hasCourseEdition(mockCourseEditionID)).thenReturn(true);
        when(mockCee.isEnrolmentActive()).thenReturn(false); // Enrolment is already inactive

        // Act: Try removing an already inactive enrolment
        boolean result = enrolmentRepository.removeEnrolment(mockStudentID, mockCourseEditionID);

        // Assert
        assertFalse(result, "Removing an already inactive enrolment should return false.");
        verify(mockCee, never()).deactivateEnrolment(); // Ensure deactivateEnrolment is not called
    }

    // Multiple Removal Attempts of Same Enrolment
    // Confirms that removing the same enrolment multiple times should only succeed on the first attempt, while subsequent attempts should be denied
    @Test
    void removeEnrolmentTwice_ShouldReturnFalseOnSecondAttempt() {
        // Arrange
        ICourseEditionEnrolmentFactory enrolmentFactoryMock = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl enrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(enrolmentFactoryMock, CeeListFactory);

        StudentID mockStudentID = mock(StudentID.class);
        CourseEditionID mockCourseEditionID = mock(CourseEditionID.class);
        CourseEditionEnrolment mockCee = mock(CourseEditionEnrolment.class);

        when(enrolmentFactoryMock.createCourseEditionEnrolment(mockStudentID, mockCourseEditionID))
                .thenReturn(mockCee);

        when(mockCee.hasStudent(mockStudentID)).thenReturn(true);
        when(mockCee.hasCourseEdition(mockCourseEditionID)).thenReturn(true);

        // Simulate first call: Enrolment starts as active, then becomes inactive
        when(mockCee.isEnrolmentActive()).thenReturn(true).thenReturn(false);

        enrolmentRepository.enrolStudentInACourseEdition(mockStudentID, mockCourseEditionID);

        // Act
        boolean firstRemoval = enrolmentRepository.removeEnrolment(mockStudentID, mockCourseEditionID);

        // Assert first removal is successful
        assertTrue(firstRemoval, "Enrolment should be removed successfully.");
        verify(mockCee).deactivateEnrolment(); // Ensure deactivation was called

        // Act again: Try removing a second time
        boolean secondRemoval = enrolmentRepository.removeEnrolment(mockStudentID, mockCourseEditionID);

        // Assert second removal fails
        assertFalse(secondRemoval, "The second removal should not succeed.");
        verify(mockCee, times(1)).deactivateEnrolment(); // Ensure deactivation was called exactly once
    }

    // Null Information
    // If the student or course edition information is missing (null), the system should reject the operation
    @Test
    void removeEnrolment_WithNullStudentOrCourseEdition_ShouldReturnFalse() {
        // Arrange
        ICourseEditionEnrolmentFactory enrolmentFactoryMock = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(enrolmentFactoryMock, CeeListFactory);

        StudentID mockStudentID = mock(StudentID.class);
        CourseEditionID mockCourseEditionID = mock(CourseEditionID.class);

        // Act & Assert for null Student
        boolean result1 = repository.removeEnrolment(null, mockCourseEditionID);
        assertFalse(result1);

        // Act & Assert for null CourseEdition
        boolean result2 = repository.removeEnrolment(mockStudentID, null);
        assertFalse(result2);
    }


    @Test
    void shouldEnrollStudentWhenStudentNotEnrolled() {
        // Arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleCeeFactory, CeeListFactory);

        StudentID doubleStudent = mock(StudentID.class);
        CourseEditionID doubleCourseEdition1 = mock(CourseEditionID.class);
        CourseEditionEnrolment doubleEnrollment = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStudent, doubleCourseEdition1)).thenReturn(doubleEnrollment);
        when(doubleEnrollment.hasStudent(doubleStudent)).thenReturn(true);
        when(doubleEnrollment.hasCourseEdition(doubleCourseEdition1)).thenReturn(true);

        // Act
        repository.enrolStudentInACourseEdition(doubleStudent, doubleCourseEdition1);

        // Assert
        assertTrue(repository.findByStudentAndEdition(doubleStudent, doubleCourseEdition1).isPresent());
    }

    @Test
    void shouldThrowExceptionWhenStudentAlreadyEnrolled() {
        // Arrange
        ICourseEditionEnrolmentFactory doubleFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleFactory, CeeListFactory);

        StudentID doubleStudent = mock(StudentID.class);
        CourseEditionID doubleCourseEdition1 = mock(CourseEditionID.class);
        CourseEditionID doubleCourseEdition2 = mock(CourseEditionID.class);
        List<CourseEditionID> courseEditions = List.of(doubleCourseEdition1, doubleCourseEdition2);

        CourseEditionEnrolment doubleEnrollment1 = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolment doubleEnrollment2 = mock(CourseEditionEnrolment.class);

        when(doubleFactory.createCourseEditionEnrolment(doubleStudent, doubleCourseEdition1)).thenReturn(doubleEnrollment1);

        when(doubleFactory.createCourseEditionEnrolment(doubleStudent, doubleCourseEdition2)).thenReturn(doubleEnrollment2);

        when(doubleEnrollment1.hasStudent(doubleStudent)).thenReturn(true);

        when(doubleEnrollment2.hasStudent(doubleStudent)).thenReturn(true);

        when(doubleEnrollment1.hasCourseEdition(doubleCourseEdition1)).thenReturn(true);

        when(doubleEnrollment2.hasCourseEdition(doubleCourseEdition2)).thenReturn(true);

        //act
        repository.enrolStudentInProgrammeCourseEditions(doubleStudent, courseEditions);
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            repository.enrolStudentInProgrammeCourseEditions(doubleStudent, courseEditions);
        });

        //assert
        assertEquals("This course edition enrolment is already in the list.", exception.getMessage());
    }

    @Test
    void shouldReturnZeroWhenThereAreNoEnrollmentsInCourseEdition() throws Exception {
        // Arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleCeeFactory, CeeListFactory);

        StudentID doubleStudent = mock(StudentID.class);
        CourseEditionID doubleCourseEdition1 = mock(CourseEditionID.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStudent, doubleCourseEdition1)).thenReturn(null);

        // Act

        int studentsEnrolled = repository.numberOfStudentsEnrolledInCourseEdition(doubleCourseEdition1);

        // Assert
        assertEquals(0, studentsEnrolled);
    }


    //---------------Integration Tests--------------

// ==============================
// Enrolment Removal Success Cases
// ==============================

    // Test Removing an Existing Enrolment
    @Test
    void removeExistingEnrolment_ShouldReturnTrue_IntegrationTest() throws Exception {
        // Arrange
        ICourseEditionEnrolmentFactory enrolmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory CeeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl enrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(enrolmentFactory, CeeListFactory);

        StudentID studentID = mock(StudentID.class);
        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("Software Development");
        Acronym acronym = new Acronym("SWD");
        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronym);
        SchoolYearID schoolYearID = new SchoolYearID();
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);
        Date implementationDate = new Date("21-03-2022");
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);
        Acronym acronym2 = new Acronym("DSOFT");
        Name name2 = new Name("Software Development");
        CourseID courseID = new CourseID(acronym2, name2);
        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);
        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID, courseInStudyPlanID);

        enrolmentRepository.enrolStudentInACourseEdition(studentID, courseEditionID);

        // Act
        boolean result = enrolmentRepository.removeEnrolment(studentID, courseEditionID);

        // Assert
        assertTrue(result, "Enrolment should be removed successfully.");
    }

    // Multiple Course Edition Removals
    @Test
    void removeStudentFromMultipleCourseEditions_ShouldReturnTrueForBoth_IntegrationTest() throws Exception {
        // Arrange
        ICourseEditionEnrolmentFactory enrolmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory CeeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl enrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(enrolmentFactory, CeeListFactory);

        StudentID studentID = mock(StudentID.class);

        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("Software Development");
        Acronym acronym = new Acronym("SWD");
        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronym);
        SchoolYearID schoolYearID = new SchoolYearID();
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);
        Date implementationDate = new Date("21-03-2022");
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);
        Acronym acronym3 = new Acronym("DSOFT");
        Name name3 = new Name("Software Development");
        CourseID courseID = new CourseID(acronym3, name3);
        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);
        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID, courseInStudyPlanID);

        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars2 = new NameWithNumbersAndSpecialChars("Civil Engineering");
        Acronym acronym2 = new Acronym("CVE");
        ProgrammeID programme2ID = new ProgrammeID(nameWithNumbersAndSpecialChars2, acronym2);
        SchoolYearID schoolYear2ID = new SchoolYearID();
        ProgrammeEditionID programmeEdition2ID = new ProgrammeEditionID(programme2ID, schoolYear2ID);
        Date implementationDate2 = new Date("21-03-2022");
        StudyPlanID studyPlanID2 = new StudyPlanID(programme2ID, implementationDate2);
        Acronym acronym4 = new Acronym("DSOFT");
        Name name4 = new Name("Software Development");
        CourseID courseID2 = new CourseID(acronym4, name4);
        CourseInStudyPlanID courseInStudyPlanID2 = new CourseInStudyPlanID(courseID2, studyPlanID2);
        CourseEditionID courseEdition2ID = new CourseEditionID(programmeEdition2ID, courseInStudyPlanID2);

        enrolmentRepository.enrolStudentInACourseEdition(studentID, courseEditionID);
        enrolmentRepository.enrolStudentInACourseEdition(studentID, courseEdition2ID);

        // Act
        boolean firstRemoval = enrolmentRepository.removeEnrolment(studentID, courseEditionID);
        boolean secondRemoval = enrolmentRepository.removeEnrolment(studentID, courseEdition2ID);

        // Assert
        assertTrue(firstRemoval, "Student should be removed from the first course edition.");
        assertTrue(secondRemoval, "Student should be removed from the second course edition.");
    }

    // Batch Removal of Multiple Students
    @Test
    void removeMultipleStudentsFromSameCourseEdition_ShouldReturnTrueForBoth_IntegrationTest() throws Exception {
        // Arrange
        ICourseEditionEnrolmentFactory enrolmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory CeeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl enrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(enrolmentFactory, CeeListFactory);

        StudentID studentID = mock(StudentID.class);
        StudentID studentID2 = mock(StudentID.class);

        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("Software Development");
        Acronym acronym = new Acronym("SWD");
        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronym);
        SchoolYearID schoolYearID = new SchoolYearID();
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);
        Date implementationDate = new Date("21-03-2022");
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);
        Acronym acronym2 = new Acronym("DSOFT");
        Name name2 = new Name("Software Development");
        CourseID courseID = new CourseID(acronym2, name2);
        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);
        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID, courseInStudyPlanID);

        enrolmentRepository.enrolStudentInACourseEdition(studentID, courseEditionID);
        enrolmentRepository.enrolStudentInACourseEdition(studentID2, courseEditionID);

        // Act
        boolean firstRemoval = enrolmentRepository.removeEnrolment(studentID, courseEditionID);
        boolean secondRemoval = enrolmentRepository.removeEnrolment(studentID2, courseEditionID);

        // Assert
        assertTrue(firstRemoval, "First student's enrolment should be removed successfully.");
        assertTrue(secondRemoval, "Second student's enrolment should be removed successfully.");
    }

// ==============================
// Enrolment Removal Failure Handling
// ==============================

    // Test Removing a Non-Existing Enrolment
    @Test
    void removeNonExistingEnrollment_ShouldReturnFalse_IntegrationTest() throws Exception {
        // Arrange
        ICourseEditionEnrolmentFactory enrolmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory CeeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl enrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(enrolmentFactory, CeeListFactory);

        StudentID studentID = mock(StudentID.class);
        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("Software Development");
        Acronym acronym = new Acronym("SWD");
        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronym);
        SchoolYearID schoolYearID = new SchoolYearID();
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);
        Date implementationDate = new Date("21-03-2022");
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);
        Acronym acronym2 = new Acronym("DSOFT");
        Name name2 = new Name("Software Development");
        CourseID courseID = new CourseID(acronym2, name2);
        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);
        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID, courseInStudyPlanID);

        // Act
        boolean result = enrolmentRepository.removeEnrolment(studentID, courseEditionID);

        // Assert
        assertFalse(result, "Removing a non existing enrollment should return false.");
    }

    // Multiple Removal Attempts of Same Enrolment
    @Test
    void removeEnrolmentTwice_ShouldReturnFalseOnSecondAttempt_IntegrationTest() throws Exception {
        // Arrange
        ICourseEditionEnrolmentFactory enrolmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory CeeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl enrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(enrolmentFactory, CeeListFactory);

        StudentID studentID = mock(StudentID.class);
        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("Software Development");
        Acronym acronym = new Acronym("SWD");
        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronym);
        SchoolYearID schoolYearID = new SchoolYearID();
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);
        Date implementationDate = new Date("21-03-2022");
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);
        Acronym acronym2 = new Acronym("DSOFT");
        Name name2 = new Name("Software Development");
        CourseID courseID = new CourseID(acronym2, name2);
        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);
        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID, courseInStudyPlanID);

        enrolmentRepository.enrolStudentInACourseEdition(studentID, courseEditionID);

        // Act
        boolean firstRemoval = enrolmentRepository.removeEnrolment(studentID, courseEditionID);
        boolean secondRemoval = enrolmentRepository.removeEnrolment(studentID, courseEditionID);

        // Assert
        assertTrue(firstRemoval, "Enrollment should be removed successfully.");
        assertFalse(secondRemoval, "The second removal should not succeed.");
    }

    // Null Information
    @Test
    void removeEnrolment_WithNullStudentOrCourseEdition_ShouldReturnFalse_IntegrationTest() throws Exception {
        // Arrange
        ICourseEditionEnrolmentFactory enrolmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory CeeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl enrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(enrolmentFactory, CeeListFactory);

        StudentID studentID = mock(StudentID.class);
        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("Software Development");
        Acronym acronym = new Acronym("SWD");
        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronym);
        SchoolYearID schoolYearID = new SchoolYearID();
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);
        Date implementationDate = new Date("21-03-2022");
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);
        Acronym acronym2 = new Acronym("DSOFT");
        Name name2 = new Name("Software Development");
        CourseID courseID = new CourseID(acronym2, name2);
        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);
        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID, courseInStudyPlanID);

        enrolmentRepository.enrolStudentInACourseEdition(studentID, courseEditionID);

        // Act and assert
        // test for the case where Student is null
        boolean result1 = enrolmentRepository.removeEnrolment(null, courseEditionID);
        assertFalse(result1, "Removing a non existing enrollment should return false.");

        // test for the case where CourseEdition is null.
        boolean result2 = enrolmentRepository.removeEnrolment(studentID, null);
        assertFalse(result2, "Removing a non existing enrollment should return false.");
    }


    @Test
    void should_save_courseEditionEnrolment() {

        //arrange
        ICourseEditionEnrolmentFactory doubleICEEF = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory doubleICEELF = mock(ICourseEditionEnrolmentListFactory.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleICEEF, doubleICEELF);

        CourseEditionEnrolmentID enrolmentID = mock(CourseEditionEnrolmentID.class);
        CourseEditionEnrolment enrolment = mock(CourseEditionEnrolment.class);

        when(enrolment.identity()).thenReturn(enrolmentID);

        //act
        CourseEditionEnrolment enrolmentSaved = repository.save(enrolment);

        //assert
        assertNotNull(enrolmentSaved);
        assertTrue(repository.containsOfIdentity(enrolmentSaved.identity()));
    }

    @Test
    void should_return_all_courseEditionEnrolments() {

        // arrange
        ICourseEditionEnrolmentFactory doubleICEEF = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory doubleICEELF = mock(ICourseEditionEnrolmentListFactory.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleICEEF, doubleICEELF);

        CourseEditionEnrolment enrolment1 = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolment enrolment2 = mock(CourseEditionEnrolment.class);

        when(enrolment1.identity()).thenReturn(mock(CourseEditionEnrolmentID.class));
        when(enrolment2.identity()).thenReturn(mock(CourseEditionEnrolmentID.class));

        repository.save(enrolment1);
        repository.save(enrolment2);

        // act
        List<CourseEditionEnrolment> enrolments = new ArrayList<>();
        repository.findAll().forEach(enrolments::add);

        // assert
        assertEquals(2, enrolments.size());
        assertTrue(enrolments.contains(enrolment1));
        assertTrue(enrolments.contains(enrolment2));
    }


    @Test
    void should_find_enrolment_by_identity() {

        //arrange
        ICourseEditionEnrolmentFactory doubleICEEF = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory doubleICEELF = mock(ICourseEditionEnrolmentListFactory.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleICEEF, doubleICEELF);
        CourseEditionEnrolment enrolment = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolmentID enrolmentID = mock(CourseEditionEnrolmentID.class);

        when(enrolment.identity()).thenReturn(enrolmentID);
        repository.save(enrolment);

        //act
        Optional<CourseEditionEnrolment> enrolmentFound = repository.ofIdentity(enrolmentID);

        //assert
        assertTrue(enrolmentFound.isPresent());
        assertEquals(enrolment, enrolmentFound.get());
    }

    @Test
    void should_check_if_repository_contains_enrolment_by_identity() {

        //arrange
        ICourseEditionEnrolmentFactory doubleICEEF = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory doubleICEELF = mock(ICourseEditionEnrolmentListFactory.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleICEEF, doubleICEELF);
        CourseEditionEnrolment enrolment = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolmentID enrolmentID = mock(CourseEditionEnrolmentID.class);

        when(enrolment.identity()).thenReturn(enrolmentID);
        repository.save(enrolment);

        //act + assert
        assertTrue(repository.containsOfIdentity(enrolmentID));
    }

    @Test
    void should_return_true_when_ID_exists(){

        //arrange
        ICourseEditionEnrolmentFactory doubleICEEF = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory doubleICEELF = mock(ICourseEditionEnrolmentListFactory.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleICEEF,doubleICEELF);

        CourseEditionEnrolment enrolment = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolmentID enrolmentID = mock(CourseEditionEnrolmentID.class);

        when(enrolment.identity()).thenReturn(enrolmentID);

        repository.save(enrolment);

        //act
        boolean idExists = repository.containsOfIdentity(enrolmentID);

        //assert
        assertTrue(idExists);
    }

    @Test
    void should_return_false_if_ID_doesnt_exists(){

        //arrange
        ICourseEditionEnrolmentFactory doubleICEEF = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory doubleICEELF = mock(ICourseEditionEnrolmentListFactory.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleICEEF,doubleICEELF);

        CourseEditionEnrolmentID idDoesntExists = mock(CourseEditionEnrolmentID.class);

        //act
        boolean idExists = repository.containsOfIdentity(idDoesntExists);

        //arrange
        assertFalse(idExists);
    }

    @Test
    void should_return_false_if_ID_is_null(){

        //arrange
        ICourseEditionEnrolmentFactory doubleICEEF = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory doubleICEELF = mock(ICourseEditionEnrolmentListFactory.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleICEEF,doubleICEELF);

        //act
        boolean idExists = repository.containsOfIdentity(null);

        //assert
        assertFalse(idExists);
    }

    @Test
    void should_return_empty_if_ID_doesnt_exists() {

        //arrange
        ICourseEditionEnrolmentFactory doubleICEEF = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory doubleICEELF = mock(ICourseEditionEnrolmentListFactory.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleICEEF,doubleICEELF);

        CourseEditionEnrolmentID idDoesntExists = mock(CourseEditionEnrolmentID.class);

        //act
        Optional<CourseEditionEnrolment> idExists = repository.ofIdentity(idDoesntExists);

        //assert
        assertTrue(idExists.isEmpty());
    }

    @Test
    void should_return_empty_if_ID_is_null() {

        //arrange
        ICourseEditionEnrolmentFactory doubleICEEF = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory doubleICEELF = mock(ICourseEditionEnrolmentListFactory.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleICEEF,doubleICEELF);

        //act
        Optional<CourseEditionEnrolment> idExists = repository.ofIdentity(null);

        //assert
        assertTrue(idExists.isEmpty());
    }

    @Test
    void should_return_correct_ID_when_several_exists() {

        //arrange
        ICourseEditionEnrolmentFactory doubleICEEF = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory doubleICEELF = mock(ICourseEditionEnrolmentListFactory.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleICEEF,doubleICEELF);

        CourseEditionEnrolment enrolment1 = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolment enrolment2 = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolment enrolment3 = mock(CourseEditionEnrolment.class);

        CourseEditionEnrolmentID id1 = mock(CourseEditionEnrolmentID.class);
        CourseEditionEnrolmentID id2 = mock(CourseEditionEnrolmentID.class);
        CourseEditionEnrolmentID id3 = mock(CourseEditionEnrolmentID.class);

        when(enrolment1.identity()).thenReturn(id1);
        when(enrolment2.identity()).thenReturn(id2);
        when(enrolment3.identity()).thenReturn(id3);

        repository.save(enrolment1);
        repository.save(enrolment2);
        repository.save(enrolment3);

        //act
        Optional<CourseEditionEnrolment> idExists = repository.ofIdentity(id2);

        //assert
        assertTrue(idExists.isPresent());
        assertEquals(enrolment2,idExists.get());
    }

    @Test
    void should_throw_exception_if_identity_is_null() throws IllegalArgumentException {

        //arrange
        ICourseEditionEnrolmentFactory doubleCEEF = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory doubleCEELF = mock(ICourseEditionEnrolmentListFactory.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleCEEF,doubleCEELF);

        //act + assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->{
            repository.save(null);
        });
        assertEquals(exception.getMessage(),"Entity cannot be null");
    }

}

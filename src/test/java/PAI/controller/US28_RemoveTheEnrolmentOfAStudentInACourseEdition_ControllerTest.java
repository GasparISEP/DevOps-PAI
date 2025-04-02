package PAI.controller;

import PAI.VOs.*;
import PAI.domain.*;
import PAI.factory.*;
import PAI.repository.CourseEditionEnrolmentRepositoryImpl;
import PAI.repository.ICourseEditionEnrolmentRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US28_RemoveTheEnrolmentOfAStudentInACourseEdition_ControllerTest {

    //---------------Isolated Unit Tests--------------

// ==============================
// Enrolment Removal Success Cases
// ==============================

    // Test Removing an Existing Enrolment
    // System should allow the successful removal of a student enrolled in a course edition
    @Test
        void removeExistingEnrolment_ShouldReturnTrue() {
            // Arrange
            ICourseEditionEnrolmentRepository mockRepository = mock(ICourseEditionEnrolmentRepository.class);
            US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller(mockRepository);

            StudentID mockStudentID = mock(StudentID.class);
            CourseEditionID mockCourseEditionID = mock(CourseEditionID.class);

            when(mockRepository.removeEnrolment(mockStudentID, mockCourseEditionID)).thenReturn(true);

            // Act
            boolean result = controller.removeStudentEnrolment(mockStudentID, mockCourseEditionID);

            // Assert
            assertTrue(result, "Enrolment should be removed successfully.");
            verify(mockRepository).removeEnrolment(mockStudentID, mockCourseEditionID);
        }

    // Multiple Course Edition Removals
    // Ensures that a student can be removed from multiple course editions correctly
    @Test
    void removeStudentFromMultipleCourseEditions_ShouldReturnTrueForBoth() {
        // Arrange
        ICourseEditionEnrolmentRepository mockRepository = mock(ICourseEditionEnrolmentRepository.class);
        US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller(mockRepository);

        CourseEditionID mockCourseEditionID1 = mock(CourseEditionID.class);
        CourseEditionID mockCourseEditionID2 = mock(CourseEditionID.class);
        StudentID mockStudentID = mock(StudentID.class);

        when(mockRepository.removeEnrolment(mockStudentID, mockCourseEditionID1)).thenReturn(true);
        when(mockRepository.removeEnrolment(mockStudentID, mockCourseEditionID2)).thenReturn(true);

        // Act
        boolean firstRemoval = controller.removeStudentEnrolment(mockStudentID, mockCourseEditionID1);
        boolean secondRemoval = controller.removeStudentEnrolment(mockStudentID, mockCourseEditionID2);

        // Assert
        assertTrue(firstRemoval, "Student should be removed from the first course edition.");
        assertTrue(secondRemoval, "Student should be removed from the second course edition.");
        verify(mockRepository).removeEnrolment(mockStudentID, mockCourseEditionID1);
        verify(mockRepository).removeEnrolment(mockStudentID, mockCourseEditionID2);
    }

    // Batch Removal of Multiple Students
    // Ensures that different students enrolled in the same course edition can be removed without issues
    @Test
    void removeMultipleStudentsFromSameCourseEdition_ShouldReturnTrueForBoth() {
        // Arrange
        ICourseEditionEnrolmentRepository mockRepository = mock(CourseEditionEnrolmentRepositoryImpl.class);
        US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller(mockRepository);

        CourseEditionID mockCourseEditionID = mock(CourseEditionID.class);
        StudentID mockStudentID1 = mock(StudentID.class);
        StudentID mockStudentID2 = mock(StudentID.class);

        when(mockRepository.removeEnrolment(mockStudentID1, mockCourseEditionID)).thenReturn(true);
        when(mockRepository.removeEnrolment(mockStudentID2, mockCourseEditionID)).thenReturn(true);

        // Act
        boolean firstRemoval = controller.removeStudentEnrolment(mockStudentID1, mockCourseEditionID);
        boolean secondRemoval = controller.removeStudentEnrolment(mockStudentID2, mockCourseEditionID);

        // Assert
        assertTrue(firstRemoval, "First student's enrolment should be removed successfully.");
        assertTrue(secondRemoval, "Second student's enrolment should be removed successfully.");
        verify(mockRepository).removeEnrolment(mockStudentID1, mockCourseEditionID);
        verify(mockRepository).removeEnrolment(mockStudentID2, mockCourseEditionID);
    }


// ==============================
// Enrolment Removal Failure Handling
// ==============================

    // Test Removing a Non-Existing Enrolment
    // Ensures that the system does not allow the removal of a non-existent enrolment
    @Test
        void removeNonExistingEnrolment_ShouldReturnFalse() {
            // Arrange
            ICourseEditionEnrolmentRepository mockRepository = mock(CourseEditionEnrolmentRepositoryImpl.class);
            US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller(mockRepository);

            StudentID mockStudentID = mock (StudentID.class);
            CourseEditionID mockCourseEditionID = mock (CourseEditionID.class);

            // Act
            boolean result = controller.removeStudentEnrolment(mockStudentID, mockCourseEditionID);

            // Assert
            assertFalse(result, "Removing a non existing enrolment should return false.");
            verify(mockRepository).removeEnrolment(mockStudentID, mockCourseEditionID); // Ensure no enrolment creation occurs
        }

    // Remove Already Inactive Enrolment
    // Ensures that the system does not allow the removal of an enrolment that has already been deactivated
    @Test
    void removeAlreadyInactiveEnrolment_ShouldReturnFalse() {
        // Arrange
        ICourseEditionEnrolmentRepository mockRepository = mock(CourseEditionEnrolmentRepositoryImpl.class);
        US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller(mockRepository);

        StudentID mockStudentID = mock(StudentID.class);
        CourseEditionID mockCourseEditionID = mock(CourseEditionID.class);
        CourseEditionEnrolment mockCee = mock(CourseEditionEnrolment.class);

        when(mockRepository.findByStudentAndEdition(mockStudentID, mockCourseEditionID))
                .thenReturn(Optional.of(mockCee)); // Simulates that the repository found the enrolment

        when(mockCee.isEnrolmentActive()).thenReturn(false); // Enrolment is already inactive

        // Act: Try removing an already inactive enrolment
        boolean result = controller.removeStudentEnrolment(mockStudentID, mockCourseEditionID);

        // Assert
        assertFalse(result, "Removing an already inactive enrolment should return false.");
        verify(mockCee, never()).deactivateEnrolment(); // Ensure deactivateEnrolment is not called
    }

    // Multiple Removal Attempts of Same Enrolment
    // Confirms that removing the same enrolment multiple times should only succeed on the first attempt, while subsequent attempts should be denied
    @Test
    void removeEnrolmentTwice_ShouldReturnFalseOnSecondAttempt() {
        // Arrange
        ICourseEditionEnrolmentRepository mockRepository = mock(CourseEditionEnrolmentRepositoryImpl.class);
        US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller(mockRepository);

        StudentID mockStudentID = mock(StudentID.class);
        CourseEditionID mockCourseEditionID = mock(CourseEditionID.class);

        when(mockRepository.removeEnrolment(mockStudentID, mockCourseEditionID)).thenReturn(true)
                .thenReturn(false);
        // Act
        boolean firstRemoval = controller.removeStudentEnrolment(mockStudentID, mockCourseEditionID);

        // Assert first removal is successful
        assertTrue(firstRemoval, "Enrolment should be removed successfully.");

        // Act again: Try removing a second time
        boolean secondRemoval = controller.removeStudentEnrolment(mockStudentID, mockCourseEditionID);

        // Assert second removal fails
        assertFalse(secondRemoval, "The second removal should not succeed.");
        verify(mockRepository, times(2)).removeEnrolment(mockStudentID, mockCourseEditionID);
    }

    // Null Information
    // If the student or course edition information is missing (null), the system should reject the operation.
    @Test
    void removeEnrolment_WithNullCourseEditionOrStudent_ShouldReturnFalse() {
        // Arrange
        ICourseEditionEnrolmentRepository mockRepository = mock(CourseEditionEnrolmentRepositoryImpl.class);
        US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller(mockRepository);

        StudentID mockStudentID = mock(StudentID.class);
        CourseEditionID mockCourseEditionID = mock(CourseEditionID.class);

        // Act and assert
        // test for the case where Student is null.
        boolean result1 = controller.removeStudentEnrolment(null, mockCourseEditionID);
        assertFalse(result1, "Removing a non existing enrolment should return false.");

        // test for the case where CourseEdition is null
        boolean result2 = controller.removeStudentEnrolment(mockStudentID, null);
        assertFalse(result2, "Removing a non existing enrolment should return false.");
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
        US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller(enrolmentRepository);

        StudentID studentID = new StudentID(1000001);
        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("Software Development");
        Acronym acronym = new Acronym("SWD");
        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronym);
        SchoolYearID schoolYearID = new SchoolYearID();
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID,schoolYearID);
        Date implementationDate = new Date("21-03-2022");
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);
        CourseID courseID = new CourseID();
        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);
        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID,courseInStudyPlanID);

        enrolmentRepository.enrolStudentInACourseEdition(studentID, courseEditionID);

        // Act
        boolean result = controller.removeStudentEnrolment(studentID, courseEditionID);

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
        US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller(enrolmentRepository);

        StudentID studentID = new StudentID(1000001);

        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("Software Development");
        Acronym acronym = new Acronym("SWD");
        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronym);
        SchoolYearID schoolYearID = new SchoolYearID();
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID,schoolYearID);
        Date implementationDate = new Date("21-03-2022");
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);
        CourseID courseID = new CourseID();
        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);
        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID,courseInStudyPlanID);

        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars2 = new NameWithNumbersAndSpecialChars("Civil Engineering");
        Acronym acronym2 = new Acronym("CVE");
        ProgrammeID programme2ID = new ProgrammeID(nameWithNumbersAndSpecialChars2, acronym2);
        SchoolYearID schoolYear2ID = new SchoolYearID();
        ProgrammeEditionID programmeEdition2ID = new ProgrammeEditionID(programme2ID,schoolYear2ID);
        Date implementationDate2 = new Date("21-03-2022");
        StudyPlanID studyPlanID2 = new StudyPlanID(programme2ID, implementationDate2);
        CourseID courseID2 = new CourseID();
        CourseInStudyPlanID courseInStudyPlan2ID = new CourseInStudyPlanID(courseID2, studyPlanID2);
        CourseEditionID courseEdition2ID = new CourseEditionID(programmeEdition2ID,courseInStudyPlan2ID);

        enrolmentRepository.enrolStudentInACourseEdition(studentID, courseEditionID);
        enrolmentRepository.enrolStudentInACourseEdition(studentID, courseEdition2ID);

        // Act
        boolean firstRemoval = controller.removeStudentEnrolment(studentID, courseEditionID);
        boolean secondRemoval = controller.removeStudentEnrolment(studentID, courseEdition2ID);

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
        US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller(enrolmentRepository);

        StudentID studentID = new StudentID(1000001);
        StudentID student2ID = new StudentID(1000002);
        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("Software Development");
        Acronym acronym = new Acronym("SWD");
        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronym);
        SchoolYearID schoolYearID = new SchoolYearID();
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID,schoolYearID);
        Date implementationDate = new Date("21-03-2022");
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);
        CourseID courseID = new CourseID();
        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);
        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID,courseInStudyPlanID);

        enrolmentRepository.enrolStudentInACourseEdition(studentID, courseEditionID);
        enrolmentRepository.enrolStudentInACourseEdition(student2ID, courseEditionID);

        // Act
        boolean firstRemoval = controller.removeStudentEnrolment(studentID, courseEditionID);
        boolean secondRemoval = controller.removeStudentEnrolment(student2ID, courseEditionID);

        // Assert
        assertTrue(firstRemoval, "First student's enrolment should be removed successfully.");
        assertTrue(secondRemoval, "Second student's enrolment should be removed successfully.");
    }

// ==============================
// Enrolment Removal Failure Handling
// ==============================

    // Test Removing a Non-Existing Enrolment
    @Test
    void removeNonExistingEnrolment_ShouldReturnFalse_IntegrationTest() throws Exception {
        // Arrange
        ICourseEditionEnrolmentFactory enrolmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory CeeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl enrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(enrolmentFactory, CeeListFactory);
        US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller(enrolmentRepository);

        StudentID studentID = new StudentID(1000001);
        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("Software Development");
        Acronym acronym = new Acronym("SWD");
        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronym);
        SchoolYearID schoolYearID = new SchoolYearID();
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID,schoolYearID);
        Date implementationDate = new Date("21-03-2022");
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);
        CourseID courseID = new CourseID();
        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);
        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID,courseInStudyPlanID);

        // Act
        boolean result = controller.removeStudentEnrolment(studentID, courseEditionID);

        // Assert
        assertFalse(result, "Removing a non existing enrolment should return false.");
    }

    // Multiple Removal Attempts of Same Enrolment
    @Test
    void removeEnrolmentTwice_ShouldReturnFalseOnSecondAttempt_IntegrationTest() throws Exception {
        // Arrange
        ICourseEditionEnrolmentFactory enrolmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory CeeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl enrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(enrolmentFactory, CeeListFactory);
        US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller(enrolmentRepository);

        StudentID studentID = new StudentID(1000001);
        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("Software Development");
        Acronym acronym = new Acronym("SWD");
        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronym);
        SchoolYearID schoolYearID = new SchoolYearID();
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID,schoolYearID);
        Date implementationDate = new Date("21-03-2022");
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);
        CourseID courseID = new CourseID();
        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);
        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID,courseInStudyPlanID);

        enrolmentRepository.enrolStudentInACourseEdition(studentID, courseEditionID);

        // Act
        boolean firstRemoval = controller.removeStudentEnrolment(studentID, courseEditionID);
        boolean secondRemoval = controller.removeStudentEnrolment(studentID, courseEditionID);

        // Assert
        assertTrue(firstRemoval, "Enrolment should be removed successfully.");
        assertFalse(secondRemoval, "The second removal should not succeed.");
    }

    // Null Information
    @Test
    void removeEnrolment_WithNullStudentOrCourseEdition_ShouldReturnFalse_IntegrationTest() throws Exception {
        // Arrange
        ICourseEditionEnrolmentFactory enrolmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory CeeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl enrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(enrolmentFactory, CeeListFactory);
        US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller(enrolmentRepository);

        StudentID studentID = new StudentID(1000001);
        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("Software Development");
        Acronym acronym = new Acronym("SWD");
        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronym);
        SchoolYearID schoolYearID = new SchoolYearID();
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID,schoolYearID);
        Date implementationDate = new Date("21-03-2022");
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);
        CourseID courseID = new CourseID();
        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);
        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID,courseInStudyPlanID);

        enrolmentRepository.enrolStudentInACourseEdition(studentID, courseEditionID);

        // Act and assert
        // test for the case where Student is null
        boolean result1 = controller.removeStudentEnrolment(null, courseEditionID);
        assertFalse(result1, "Removing a non existing enrolment should return false.");

        // test for the case where CourseEdition is null.
        boolean result2 = controller.removeStudentEnrolment(studentID, null);
        assertFalse(result2, "Removing a non existing enrolment should return false.");
    }
}

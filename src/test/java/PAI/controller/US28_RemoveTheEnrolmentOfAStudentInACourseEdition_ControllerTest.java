package PAI.controller;

import PAI.VOs.*;
import PAI.domain.courseEditionEnrolment.*;
import PAI.persistence.mem.CourseEditionEnrolmentRepositoryImpl;
import PAI.service.*;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US28_RemoveTheEnrolmentOfAStudentInACourseEdition_ControllerTest {

    //---------------Isolated Unit Tests--------------

// ==============================
// Simple Tests
// ==============================

    // Remove Active Enrolment from a Course Edition
    @Test
    public void removeExistingEnrolment_ShouldReturnTrue() throws Exception {
        // Arrange
        ICourseEditionEnrolmentService mockService = mock(ICourseEditionEnrolmentService.class);
        StudentID mockStudentID = mock(StudentID.class);
        CourseEditionID mockCourseEditionID = mock(CourseEditionID.class);
        CourseEditionEnrolment mockCee = mock(CourseEditionEnrolment.class);

        US28_RemoveTheEnrolmentOfAStudentInACourseEditionController controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEditionController(mockService);

        when(mockService.removeCourseEditionEnrolment(mockStudentID, mockCourseEditionID)).thenReturn(true);
        when(mockCee.isEnrolmentActive()).thenReturn(true);

        // Act
        boolean result = controller.removeCourseEditionEnrolment(mockStudentID, mockCourseEditionID);

        // Assert
        assertTrue(result, "Active enrolment should removed successfully.");
        // verify(mockService).removeCourseEditionEnrolment(mockStudentID, mockCourseEditionID);
    }

    // Remove Already Inactive Enrolment
    // Ensures that the system does not allow the removal of an enrolment that has already been deactivated
    @Test
    void removeAlreadyInactiveEnrolment_ShouldReturnFalse() throws Exception {
        // Arrange
        ICourseEditionEnrolmentService mockService = mock(ICourseEditionEnrolmentService.class);
        US28_RemoveTheEnrolmentOfAStudentInACourseEditionController controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEditionController(mockService);

        StudentID mockStudentID = mock(StudentID.class);
        CourseEditionID mockCourseEditionID = mock(CourseEditionID.class);
        CourseEditionEnrolment mockCee = mock(CourseEditionEnrolment.class);

        when(mockService.removeCourseEditionEnrolment(mockStudentID, mockCourseEditionID))
                .thenReturn(false);
        when(mockCee.isEnrolmentActive()).thenReturn(false); // Enrolment is already inactive

        // Act: Try removing an already inactive enrolment
        boolean result = controller.removeCourseEditionEnrolment(mockStudentID, mockCourseEditionID);

        // Assert
        assertFalse(result, "Removing an already inactive enrolment should return false.");
        // verify(mockCee, never()).deactivateEnrolment(); // Ensure deactivateEnrolment is not called
    }


// ==============================
// Null Tests
// ==============================

    // Null Information
    // If the student or course edition information is missing (null), the system should reject the operation.
    @Test
    void removeEnrolment_WithNullCourseEditionOrStudent_ShouldReturnFalse() throws Exception {
        // Arrange
        ICourseEditionEnrolmentService mockService = mock(ICourseEditionEnrolmentService.class);
        US28_RemoveTheEnrolmentOfAStudentInACourseEditionController controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEditionController(mockService);

        StudentID mockStudentID = mock(StudentID.class);
        CourseEditionID mockCourseEditionID = mock(CourseEditionID.class);

        // Act and assert
        // test for the case where Student is null.
        boolean result1 = controller.removeCourseEditionEnrolment(null, mockCourseEditionID);
        assertFalse(result1, "Removing a non existing enrolment should return false.");

        // test for the case where CourseEdition is null
        boolean result2 = controller.removeCourseEditionEnrolment(mockStudentID, null);
        assertFalse(result2, "Removing a non existing enrolment should return false.");
    }

    // Test Removing a Non-Existing Enrolment
    // Ensures that the system does not allow the removal of a non-existent enrolment
    @Test
    void removeNonExistingEnrolment_ShouldReturnFalse() throws Exception {
        // Arrange
        ICourseEditionEnrolmentService mockService = mock(ICourseEditionEnrolmentService.class);
        US28_RemoveTheEnrolmentOfAStudentInACourseEditionController controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEditionController(mockService);

        StudentID mockStudentID = mock (StudentID.class);
        CourseEditionID mockCourseEditionID = mock (CourseEditionID.class);

        // Act
        boolean result = controller.removeCourseEditionEnrolment(mockStudentID, mockCourseEditionID);

        // Assert
        assertFalse(result, "Removing a non existing enrolment should return false.");
        // verify(mockService).removeCourseEditionEnrolment(mockStudentID, mockCourseEditionID); // Ensure no enrolment removal occurs
    }

// ==============================
// Multiple Removals of the Same Student
// ==============================

    // Multiple Course Edition Removals
    // Ensures that a student can be removed from multiple course editions correctly
    @Test
    void removeStudentFromMultipleCourseEditions_ShouldReturnTrueForBoth() throws Exception {
        // Arrange
        ICourseEditionEnrolmentService mockService = mock(ICourseEditionEnrolmentService.class);
        US28_RemoveTheEnrolmentOfAStudentInACourseEditionController controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEditionController(mockService);

        CourseEditionID mockCourseEditionID1 = mock(CourseEditionID.class);
        CourseEditionID mockCourseEditionID2 = mock(CourseEditionID.class);
        StudentID mockStudentID = mock(StudentID.class);

        when(mockService.removeCourseEditionEnrolment(mockStudentID, mockCourseEditionID1)).thenReturn(true);
        when(mockService.removeCourseEditionEnrolment(mockStudentID, mockCourseEditionID2)).thenReturn(true);

        // Act
        boolean firstRemoval = controller.removeCourseEditionEnrolment(mockStudentID, mockCourseEditionID1);
        boolean secondRemoval = controller.removeCourseEditionEnrolment(mockStudentID, mockCourseEditionID2);

        // Assert
        assertTrue(firstRemoval, "Student should be removed from the first course edition.");
        assertTrue(secondRemoval, "Student should be removed from the second course edition.");
        // verify(mockService).removeCourseEditionEnrolment(mockStudentID, mockCourseEditionID1);
        // verify(mockService).removeCourseEditionEnrolment(mockStudentID, mockCourseEditionID2);
    }

    // Multiple Removal Attempts of Same Enrolment
    // Confirms that removing the same enrolment multiple times should only succeed on the first attempt, while subsequent attempts should be denied
    @Test
    void removeEnrolmentTwice_ShouldReturnFalseOnSecondAttempt() throws Exception {
        // Arrange
        ICourseEditionEnrolmentService mockService = mock(ICourseEditionEnrolmentService.class);
        US28_RemoveTheEnrolmentOfAStudentInACourseEditionController controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEditionController(mockService);

        StudentID mockStudentID = mock(StudentID.class);
        CourseEditionID mockCourseEditionID = mock(CourseEditionID.class);

        when(mockService.removeCourseEditionEnrolment(mockStudentID, mockCourseEditionID)).thenReturn(true)
                .thenReturn(false);
        // Act
        boolean firstRemoval = controller.removeCourseEditionEnrolment(mockStudentID, mockCourseEditionID);

        // Assert first removal is successful
        assertTrue(firstRemoval, "Enrolment should be removed successfully.");

        // Act again: Try removing a second time
        boolean secondRemoval = controller.removeCourseEditionEnrolment(mockStudentID, mockCourseEditionID);

        // Assert second removal fails
        assertFalse(secondRemoval, "The second removal should not succeed.");
        // verify(mockService, times(2)).removeCourseEditionEnrolment(mockStudentID, mockCourseEditionID);
    }
//
//// ==============================
//// Multiple Removals for Several Students
//// ==============================

    // Batch Removal of Multiple Students
    // Ensures that different students enrolled in the same course edition can be removed without issues
    @Test
    void removeMultipleStudentsFromSameCourseEdition_ShouldReturnTrueForBoth() throws Exception {
        // Arrange
        ICourseEditionEnrolmentService mockService = mock(ICourseEditionEnrolmentService.class);
        US28_RemoveTheEnrolmentOfAStudentInACourseEditionController controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEditionController(mockService);

        CourseEditionID mockCourseEditionID = mock(CourseEditionID.class);
        StudentID mockStudentID1 = mock(StudentID.class);
        StudentID mockStudentID2 = mock(StudentID.class);

        when(mockService.removeCourseEditionEnrolment(mockStudentID1, mockCourseEditionID)).thenReturn(true);
        when(mockService.removeCourseEditionEnrolment(mockStudentID2, mockCourseEditionID)).thenReturn(true);

        // Act
        boolean firstRemoval = controller.removeCourseEditionEnrolment(mockStudentID1, mockCourseEditionID);
        boolean secondRemoval = controller.removeCourseEditionEnrolment(mockStudentID2, mockCourseEditionID);

        // Assert
        assertTrue(firstRemoval, "First student's enrolment should be removed successfully.");
        assertTrue(secondRemoval, "Second student's enrolment should be removed successfully.");
        // verify(mockService).removeCourseEditionEnrolment(mockStudentID1, mockCourseEditionID);
        // verify(mockService).removeCourseEditionEnrolment(mockStudentID2, mockCourseEditionID);
    }











    //---------------Integration Tests--------------
//
//
//    // Remove Active Enrolment from a Course Edition
//    @Test
//    void removeExistingEnrolment_ShouldReturnTrue_IntegrationTest() throws Exception {
//        // Arrange
//        ICourseEditionEnrolmentFactory CeeFactory = new ICourseEditionEnrolmentFactory();
//        ICourseEditionEnrolmentRepository enrolmentRepository = new ICourseEditionEnrolmentRepository(CeeFactory);
//        IProgrammeEditionEnrolmentRepository peeRepository = new IProgrammeEditionEnrolmentRepository();
//        IProgrammeEditionEnrolmentFactory PeeFactory = new IProgrammeEditionEnrolmentFactory();
//
//        ICourseEditionEnrolmentService service = new ICourseEditionEnrolmentService(enrolmentRepository, CeeFactory, peeRepository, peeFactory);
//        US28_RemoveTheEnrolmentOfAStudentInACourseEditionController controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEditionController(service);
//
//        StudentID studentID = mock(StudentID.class);
//        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("Software Development");
//        Acronym acronym = new Acronym("SWD");
//        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronym);
//        SchoolYearID schoolYearID = new SchoolYearID();
//        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID,schoolYearID);
//        Date implementationDate = new Date("21-03-2022");
//        StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);
//        Acronym acronym2 = new Acronym("DSOFT");
//        Name name = new Name("Software Development");
//        CourseID courseID = new CourseID(acronym2, name);
//        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);
//        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID,courseInStudyPlanID);
//        CourseEditionEnrolment courseEditionEnrolment = new CourseEditionEnrolment(studentID,courseEditionID);
//
//        enrolmentRepository.save(courseEditionEnrolment);
//
//        // Act
//        boolean result = controller.removeStudentEnrolment(studentID, courseEditionID);
//
//        // Assert
//        assertTrue(result, "Enrolment should be removed successfully.");
//    }

//    // Multiple Course Edition Removals
//    @Test
//    void removeStudentFromMultipleCourseEditions_ShouldReturnTrueForBoth_IntegrationTest() throws Exception {
//        // Arrange
//        ICourseEditionEnrolmentListFactory CeeListFactory = new CourseEditionEnrolmentListFactoryImpl();
//        CourseEditionEnrolmentRepositoryImpl enrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(CeeListFactory);
//        US28_RemoveTheEnrolmentOfAStudentInACourseEditionController controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEditionController(enrolmentRepository);
//
//        StudentID studentID = mock(StudentID.class);
//
//        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("Software Development");
//        Acronym acronym = new Acronym("SWD");
//        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronym);
//        SchoolYearID schoolYearID = new SchoolYearID();
//        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID,schoolYearID);
//        Date implementationDate = new Date("21-03-2022");
//        StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);
//        Acronym acronym3 = new Acronym("DSOFT");
//        Name name = new Name("Software Development");
//        CourseID courseID = new CourseID(acronym3, name);
//        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);
//        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID,courseInStudyPlanID);
//        CourseEditionEnrolment courseEditionEnrolment = new CourseEditionEnrolment(studentID,courseEditionID);
//
//        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars2 = new NameWithNumbersAndSpecialChars("Civil Engineering");
//        Acronym acronym2 = new Acronym("CVE");
//        ProgrammeID programme2ID = new ProgrammeID(nameWithNumbersAndSpecialChars2, acronym2);
//        SchoolYearID schoolYear2ID = new SchoolYearID();
//        ProgrammeEditionID programmeEdition2ID = new ProgrammeEditionID(programme2ID,schoolYear2ID);
//        Date implementationDate2 = new Date("21-03-2022");
//        StudyPlanID studyPlanID2 = new StudyPlanID(programme2ID, implementationDate2);
//        Acronym acronym4 = new Acronym("CENG");
//        Name name2 = new Name("Civil Engineering");
//        CourseID courseID2 = new CourseID(acronym4, name2);
//        CourseInStudyPlanID courseInStudyPlan2ID = new CourseInStudyPlanID(courseID2, studyPlanID2);
//        CourseEditionID courseEdition2ID = new CourseEditionID(programmeEdition2ID,courseInStudyPlan2ID);
//        CourseEditionEnrolment courseEditionEnrolment2 = new CourseEditionEnrolment(studentID, courseEdition2ID);
//
//        enrolmentRepository.save(courseEditionEnrolment);
//        enrolmentRepository.save(courseEditionEnrolment2);
//
//        // Act
//        boolean firstRemoval = controller.removeStudentEnrolment(studentID, courseEditionID);
//        boolean secondRemoval = controller.removeStudentEnrolment(studentID, courseEdition2ID);
//
//        // Assert
//        assertTrue(firstRemoval, "Student should be removed from the first course edition.");
//        assertTrue(secondRemoval, "Student should be removed from the second course edition.");
//    }
//
//    // Batch Removal of Multiple Students
//    @Test
//    void removeMultipleStudentsFromSameCourseEdition_ShouldReturnTrueForBoth_IntegrationTest() throws Exception {
//        // Arrange
//        ICourseEditionEnrolmentListFactory CeeListFactory = new CourseEditionEnrolmentListFactoryImpl();
//        CourseEditionEnrolmentRepositoryImpl enrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(CeeListFactory);
//        US28_RemoveTheEnrolmentOfAStudentInACourseEditionController controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEditionController(enrolmentRepository);
//
//        StudentID studentID = mock(StudentID.class);
//        StudentID studentID2 = mock(StudentID.class);
//
//        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("Software Development");
//        Acronym acronym = new Acronym("SWD");
//        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronym);
//        SchoolYearID schoolYearID = new SchoolYearID();
//        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID,schoolYearID);
//        Date implementationDate = new Date("21-03-2022");
//        StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);
//        Acronym acronym2 = new Acronym("DSOFT");
//        Name name = new Name("Software Development");
//        CourseID courseID = new CourseID(acronym2, name);
//        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);
//        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID,courseInStudyPlanID);
//        CourseEditionEnrolment courseEditionEnrolment = new CourseEditionEnrolment(studentID, courseEditionID);
//        CourseEditionEnrolment courseEditionEnrolment2 = new CourseEditionEnrolment(studentID2, courseEditionID);
//
//        enrolmentRepository.save(courseEditionEnrolment);
//        enrolmentRepository.save(courseEditionEnrolment2);
//
//        // Act
//        boolean firstRemoval = controller.removeStudentEnrolment(studentID, courseEditionID);
//        boolean secondRemoval = controller.removeStudentEnrolment(studentID2, courseEditionID);
//
//        // Assert
//        assertTrue(firstRemoval, "First student's enrolment should be removed successfully.");
//        assertTrue(secondRemoval, "Second student's enrolment should be removed successfully.");
//    }
//
//// ==============================
//// Enrolment Removal Failure Handling
//// ==============================
//
//    // Test Removing a Non-Existing Enrolment
//    @Test
//    void removeNonExistingEnrolment_ShouldReturnFalse_IntegrationTest() throws Exception {
//        // Arrange
//        ICourseEditionEnrolmentListFactory CeeListFactory = new CourseEditionEnrolmentListFactoryImpl();
//        CourseEditionEnrolmentRepositoryImpl enrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(CeeListFactory);
//        US28_RemoveTheEnrolmentOfAStudentInACourseEditionController controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEditionController(enrolmentRepository);
//
//        StudentID studentID = mock(StudentID.class);
//        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("Software Development");
//        Acronym acronym = new Acronym("SWD");
//        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronym);
//        SchoolYearID schoolYearID = new SchoolYearID();
//        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID,schoolYearID);
//        Date implementationDate = new Date("21-03-2022");
//        StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);
//        Acronym acronym2 = new Acronym("DSOFT");
//        Name name = new Name("Software Development");
//        CourseID courseID = new CourseID(acronym2, name);
//        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);
//        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID,courseInStudyPlanID);
//
//        // Act
//        boolean result = controller.removeStudentEnrolment(studentID, courseEditionID);
//
//        // Assert
//        assertFalse(result, "Removing a non existing enrolment should return false.");
//    }
//
//    // Multiple Removal Attempts of Same Enrolment
//    @Test
//    void removeEnrolmentTwice_ShouldReturnFalseOnSecondAttempt_IntegrationTest() throws Exception {
//        // Arrange
//        ICourseEditionEnrolmentListFactory CeeListFactory = new CourseEditionEnrolmentListFactoryImpl();
//        CourseEditionEnrolmentRepositoryImpl enrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(CeeListFactory);
//        US28_RemoveTheEnrolmentOfAStudentInACourseEditionController controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEditionController(enrolmentRepository);
//
//        StudentID studentID = mock(StudentID.class);
//        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("Software Development");
//        Acronym acronym = new Acronym("SWD");
//        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronym);
//        SchoolYearID schoolYearID = new SchoolYearID();
//        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID,schoolYearID);
//        Date implementationDate = new Date("21-03-2022");
//        StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);
//        Acronym acronym2 = new Acronym("DSOFT");
//        Name name = new Name("Software Development");
//        CourseID courseID = new CourseID(acronym2, name);
//        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);
//        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID,courseInStudyPlanID);
//        CourseEditionEnrolment courseEditionEnrolment = new CourseEditionEnrolment(studentID, courseEditionID);
//
//        enrolmentRepository.save(courseEditionEnrolment);
//
//        // Act
//        boolean firstRemoval = controller.removeStudentEnrolment(studentID, courseEditionID);
//        boolean secondRemoval = controller.removeStudentEnrolment(studentID, courseEditionID);
//
//        // Assert
//        assertTrue(firstRemoval, "Enrolment should be removed successfully.");
//        assertFalse(secondRemoval, "The second removal should not succeed.");
//    }
//
//    // Null Information
//    @Test
//    void removeEnrolment_WithNullStudentOrCourseEdition_ShouldReturnFalse_IntegrationTest() throws Exception {
//        // Arrange
//        ICourseEditionEnrolmentListFactory CeeListFactory = new CourseEditionEnrolmentListFactoryImpl();
//        CourseEditionEnrolmentRepositoryImpl enrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(CeeListFactory);
//        US28_RemoveTheEnrolmentOfAStudentInACourseEditionController controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEditionController(enrolmentRepository);
//
//        StudentID studentID = mock(StudentID.class);
//        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("Software Development");
//        Acronym acronym = new Acronym("SWD");
//        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronym);
//        SchoolYearID schoolYearID = new SchoolYearID();
//        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID,schoolYearID);
//        Date implementationDate = new Date("21-03-2022");
//        StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);
//        Acronym acronym2 = new Acronym("DSOFT");
//        Name name = new Name("Software Development");
//        CourseID courseID = new CourseID(acronym2, name);
//        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);
//        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID,courseInStudyPlanID);
//        CourseEditionEnrolment courseEditionEnrolment = new CourseEditionEnrolment(studentID, courseEditionID);
//
//        enrolmentRepository.save(courseEditionEnrolment);
//
//        // Act and assert
//        // test for the case where Student is null
//        boolean result1 = controller.removeStudentEnrolment(null, courseEditionID);
//        assertFalse(result1, "Removing a non existing enrolment should return false.");
//
//        // test for the case where CourseEdition is null.
//        boolean result2 = controller.removeStudentEnrolment(studentID, null);
//        assertFalse(result2, "Removing a non existing enrolment should return false.");
//    }
}

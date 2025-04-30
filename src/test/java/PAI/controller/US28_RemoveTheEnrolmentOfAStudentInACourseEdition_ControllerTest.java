package PAI.controller;

import PAI.VOs.*;
import PAI.domain.courseEditionEnrolment.*;
import PAI.factory.CourseEditionFactoryImpl;
import PAI.factory.CourseEditionListFactoryImpl;
import PAI.factory.ProgrammeEditionEnrolmentFactoryImpl;
import PAI.factory.ProgrammeEditionEnrolmentListFactoryImpl;
import PAI.persistence.mem.CourseEditionEnrolmentRepositoryImpl;
import PAI.repository.CourseEditionRepositoryImpl;
import PAI.repository.ProgrammeEditionEnrolmentRepositoryImpl;
import PAI.service.*;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US28_RemoveTheEnrolmentOfAStudentInACourseEdition_ControllerTest {

    //---------------Isolated Unit Tests (BLack Box Testing)--------------

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

// ==============================
// Multiple Removals for Several Students
// ==============================

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

// ==============================
// Simple Tests
// ==============================

    // Remove Active Enrolment from a Course Edition
    @Test
    void removeExistingEnrolment_ShouldReturnTrue_IntegrationTest() throws Exception {
        // Arrange
        ProgrammeEditionEnrolmentFactoryImpl peeFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl peeListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepositoryImpl peeRepository = new ProgrammeEditionEnrolmentRepositoryImpl(peeFactory, peeListFactory);

        CourseEditionEnrolmentFactoryImpl ceeFactory = new CourseEditionEnrolmentFactoryImpl();
        CourseEditionEnrolmentListFactoryImpl ceeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl ceeRepository = new CourseEditionEnrolmentRepositoryImpl(ceeListFactory);

        CourseEditionFactoryImpl ceFactory = new CourseEditionFactoryImpl();
        CourseEditionListFactoryImpl ceListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepositoryImpl courseEditionRepository = new CourseEditionRepositoryImpl(ceFactory, ceListFactory);

        CourseEditionEnrolmentServiceImpl ceeService = new CourseEditionEnrolmentServiceImpl(
                ceeFactory, ceeRepository, peeRepository, courseEditionRepository
        );

        US28_RemoveTheEnrolmentOfAStudentInACourseEditionController controller =
                new US28_RemoveTheEnrolmentOfAStudentInACourseEditionController(ceeService);

        StudentID studentID = new StudentID(1000001);

        NameWithNumbersAndSpecialChars name1 = new NameWithNumbersAndSpecialChars("Programme1");
        Acronym acronym1 = new Acronym("P1");
        ProgrammeID programmeID1 = new ProgrammeID(name1, acronym1);
        SchoolYearID schoolYearID1 = new SchoolYearID();
        ProgrammeEditionID programmeEditionID1 = new ProgrammeEditionID(programmeID1, schoolYearID1);

        Date date1 = new Date("12-12-2023");
        StudyPlanID studyPlanID1 = new StudyPlanID(programmeID1, date1);

        Acronym acronym = new Acronym("DSOFT");
        Name name = new Name("Software Development");
        CourseID courseID1 = new CourseID(acronym, name);

        CourseInStudyPlanID courseInStudyPlanID1 = new CourseInStudyPlanID(courseID1, studyPlanID1);

        CourseEditionID ceID1 = new CourseEditionID(programmeEditionID1, courseInStudyPlanID1);

        Optional<CourseEditionEnrolment> existingEnrolment = ceeRepository.findByStudentAndEdition(studentID, ceID1);
        if (!existingEnrolment.isPresent()) {
            CourseEditionEnrolment courseEditionEnrolment = new CourseEditionEnrolment(studentID, ceID1);
            ceeRepository.save(courseEditionEnrolment);
        }

        // Act
        boolean result = controller.removeCourseEditionEnrolment(studentID, ceID1);

        // Assert
        assertTrue(result, "Enrolment should be removed successfully.");
    }

// ==============================
// Null Tests
// ==============================

    // Null Information
    @Test
    void removeEnrolment_WithNullStudentOrCourseEdition_ShouldReturnFalse_IntegrationTest() throws Exception {
        // Arrange
        ProgrammeEditionEnrolmentFactoryImpl peeFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl peeListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepositoryImpl peeRepository = new ProgrammeEditionEnrolmentRepositoryImpl(peeFactory, peeListFactory);

        CourseEditionEnrolmentFactoryImpl ceeFactory = new CourseEditionEnrolmentFactoryImpl();
        CourseEditionEnrolmentListFactoryImpl ceeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl ceeRepository = new CourseEditionEnrolmentRepositoryImpl(ceeListFactory);

        CourseEditionFactoryImpl ceFactory = new CourseEditionFactoryImpl();
        CourseEditionListFactoryImpl ceListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepositoryImpl courseEditionRepository = new CourseEditionRepositoryImpl(ceFactory, ceListFactory);

        CourseEditionEnrolmentServiceImpl ceeService = new CourseEditionEnrolmentServiceImpl(
                ceeFactory, ceeRepository, peeRepository, courseEditionRepository
        );

        US28_RemoveTheEnrolmentOfAStudentInACourseEditionController controller =
                new US28_RemoveTheEnrolmentOfAStudentInACourseEditionController(ceeService);

        StudentID studentID = new StudentID(1000001);

        NameWithNumbersAndSpecialChars name1 = new NameWithNumbersAndSpecialChars("Programme1");
        Acronym acronym1 = new Acronym("P1");
        ProgrammeID programmeID1 = new ProgrammeID(name1, acronym1);
        SchoolYearID schoolYearID1 = new SchoolYearID();
        ProgrammeEditionID programmeEditionID1 = new ProgrammeEditionID(programmeID1, schoolYearID1);

        Date date1 = new Date("12-12-2023");
        StudyPlanID studyPlanID1 = new StudyPlanID(programmeID1, date1);

        Acronym acronym = new Acronym("DSOFT");
        Name name = new Name("Software Development");
        CourseID courseID1 = new CourseID(acronym, name);

        CourseInStudyPlanID courseInStudyPlanID1 = new CourseInStudyPlanID(courseID1, studyPlanID1);

        CourseEditionID ceID1 = new CourseEditionID(programmeEditionID1, courseInStudyPlanID1);

        Optional<CourseEditionEnrolment> existingEnrolment = ceeRepository.findByStudentAndEdition(studentID, ceID1);
        if (!existingEnrolment.isPresent()) {
            CourseEditionEnrolment courseEditionEnrolment = new CourseEditionEnrolment(studentID, ceID1);
            ceeRepository.save(courseEditionEnrolment);
        }

        // Act and assert
        // test for the case where Student is null
        boolean result1 = controller.removeCourseEditionEnrolment(null, ceID1);
        assertFalse(result1, "Removing a non existing enrolment should return false.");

        // test for the case where CourseEdition is null.
        boolean result2 = controller.removeCourseEditionEnrolment(studentID, null);
        assertFalse(result2, "Removing a non existing enrolment should return false.");
    }

    // Test Removing a Non-Existing Enrolment
    @Test
    void removeNonExistingEnrolment_ShouldReturnFalse_IntegrationTest() throws Exception {
        // Arrange
        ProgrammeEditionEnrolmentFactoryImpl peeFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl peeListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepositoryImpl peeRepository = new ProgrammeEditionEnrolmentRepositoryImpl(peeFactory, peeListFactory);

        CourseEditionEnrolmentFactoryImpl ceeFactory = new CourseEditionEnrolmentFactoryImpl();
        CourseEditionEnrolmentListFactoryImpl ceeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl ceeRepository = new CourseEditionEnrolmentRepositoryImpl(ceeListFactory);

        CourseEditionFactoryImpl ceFactory = new CourseEditionFactoryImpl();
        CourseEditionListFactoryImpl ceListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepositoryImpl courseEditionRepository = new CourseEditionRepositoryImpl(ceFactory, ceListFactory);

        CourseEditionEnrolmentServiceImpl ceeService = new CourseEditionEnrolmentServiceImpl(
                ceeFactory, ceeRepository, peeRepository, courseEditionRepository
        );

        US28_RemoveTheEnrolmentOfAStudentInACourseEditionController controller =
                new US28_RemoveTheEnrolmentOfAStudentInACourseEditionController(ceeService);

        StudentID studentID = new StudentID(1000001);

        NameWithNumbersAndSpecialChars name1 = new NameWithNumbersAndSpecialChars("Programme1");
        Acronym acronym1 = new Acronym("P1");
        ProgrammeID programmeID1 = new ProgrammeID(name1, acronym1);
        SchoolYearID schoolYearID1 = new SchoolYearID();
        ProgrammeEditionID programmeEditionID1 = new ProgrammeEditionID(programmeID1, schoolYearID1);

        Date date1 = new Date("12-12-2023");
        StudyPlanID studyPlanID1 = new StudyPlanID(programmeID1, date1);

        Acronym acronym = new Acronym("DSOFT");
        Name name = new Name("Software Development");
        CourseID courseID1 = new CourseID(acronym, name);

        CourseInStudyPlanID courseInStudyPlanID1 = new CourseInStudyPlanID(courseID1, studyPlanID1);

        CourseEditionID ceID1 = new CourseEditionID(programmeEditionID1, courseInStudyPlanID1);

        // Act
        boolean result = controller.removeCourseEditionEnrolment(studentID, ceID1);

        // Assert
        assertFalse(result, "Removing a non existing enrolment should return false.");
    }

// ==============================
// Multiple Removals of the Same Student
// ==============================

    // Multiple Course Edition Removals
    @Test
    void removeStudentFromMultipleCourseEditions_ShouldReturnTrueForBoth_IntegrationTest() throws Exception {
        // Arrange
        ProgrammeEditionEnrolmentFactoryImpl peeFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl peeListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepositoryImpl peeRepository = new ProgrammeEditionEnrolmentRepositoryImpl(peeFactory, peeListFactory);

        CourseEditionEnrolmentFactoryImpl ceeFactory = new CourseEditionEnrolmentFactoryImpl();
        CourseEditionEnrolmentListFactoryImpl ceeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl ceeRepository = new CourseEditionEnrolmentRepositoryImpl(ceeListFactory);

        CourseEditionFactoryImpl ceFactory = new CourseEditionFactoryImpl();
        CourseEditionListFactoryImpl ceListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepositoryImpl courseEditionRepository = new CourseEditionRepositoryImpl(ceFactory, ceListFactory);

        CourseEditionEnrolmentServiceImpl ceeService = new CourseEditionEnrolmentServiceImpl(
                ceeFactory, ceeRepository, peeRepository, courseEditionRepository
        );

        US28_RemoveTheEnrolmentOfAStudentInACourseEditionController controller =
                new US28_RemoveTheEnrolmentOfAStudentInACourseEditionController(ceeService);

        StudentID studentID = new StudentID(1000001);

        NameWithNumbersAndSpecialChars name1 = new NameWithNumbersAndSpecialChars("Programme1");
        Acronym acronym1 = new Acronym("P1");
        ProgrammeID programmeID1 = new ProgrammeID(name1, acronym1);
        SchoolYearID schoolYearID1 = new SchoolYearID();
        ProgrammeEditionID programmeEditionID1 = new ProgrammeEditionID(programmeID1, schoolYearID1);

        Date date1 = new Date("12-12-2023");
        StudyPlanID studyPlanID1 = new StudyPlanID(programmeID1, date1);

        Acronym acronym = new Acronym("DSOFT");
        Name name = new Name("Software Development");
        CourseID courseID1 = new CourseID(acronym, name);

        CourseInStudyPlanID courseInStudyPlanID1 = new CourseInStudyPlanID(courseID1, studyPlanID1);

        CourseEditionID ceID1 = new CourseEditionID(programmeEditionID1, courseInStudyPlanID1);

        Optional<CourseEditionEnrolment> existingEnrolment = ceeRepository.findByStudentAndEdition(studentID, ceID1);
        if (!existingEnrolment.isPresent()) {
            CourseEditionEnrolment courseEditionEnrolment = new CourseEditionEnrolment(studentID, ceID1);
            ceeRepository.save(courseEditionEnrolment);
        }

        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars2 = new NameWithNumbersAndSpecialChars("Civil Engineering");
        Acronym acronym2 = new Acronym("CVE");
        ProgrammeID programme2ID = new ProgrammeID(nameWithNumbersAndSpecialChars2, acronym2);
        SchoolYearID schoolYear2ID = new SchoolYearID();
        ProgrammeEditionID programmeEdition2ID = new ProgrammeEditionID(programme2ID,schoolYear2ID);
        Date implementationDate2 = new Date("21-03-2022");
        StudyPlanID studyPlanID2 = new StudyPlanID(programme2ID, implementationDate2);
        Acronym acronym4 = new Acronym("CENG");
        Name name2 = new Name("Civil Engineering");
        CourseID courseID2 = new CourseID(acronym4, name2);
        CourseInStudyPlanID courseInStudyPlan2ID = new CourseInStudyPlanID(courseID2, studyPlanID2);
        CourseEditionID ceID2 = new CourseEditionID(programmeEdition2ID,courseInStudyPlan2ID);

        Optional<CourseEditionEnrolment> existingEnrolment2 = ceeRepository.findByStudentAndEdition(studentID, ceID2);
        if (!existingEnrolment2.isPresent()) {
            CourseEditionEnrolment courseEditionEnrolment = new CourseEditionEnrolment(studentID, ceID2);
            ceeRepository.save(courseEditionEnrolment);
        }


        // Act
        boolean firstRemoval = controller.removeCourseEditionEnrolment(studentID, ceID1);
        boolean secondRemoval = controller.removeCourseEditionEnrolment(studentID, ceID2);

        // Assert
        assertTrue(firstRemoval, "Student should be removed from the first course edition.");
        assertTrue(secondRemoval, "Student should be removed from the second course edition.");
    }

    // Multiple Removal Attempts of Same Enrolment
    @Test
    void removeEnrolmentTwice_ShouldReturnFalseOnSecondAttempt_IntegrationTest() throws Exception {
        // Arrange
        ProgrammeEditionEnrolmentFactoryImpl peeFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl peeListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepositoryImpl peeRepository = new ProgrammeEditionEnrolmentRepositoryImpl(peeFactory, peeListFactory);

        CourseEditionEnrolmentFactoryImpl ceeFactory = new CourseEditionEnrolmentFactoryImpl();
        CourseEditionEnrolmentListFactoryImpl ceeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl ceeRepository = new CourseEditionEnrolmentRepositoryImpl(ceeListFactory);

        CourseEditionFactoryImpl ceFactory = new CourseEditionFactoryImpl();
        CourseEditionListFactoryImpl ceListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepositoryImpl courseEditionRepository = new CourseEditionRepositoryImpl(ceFactory, ceListFactory);

        CourseEditionEnrolmentServiceImpl ceeService = new CourseEditionEnrolmentServiceImpl(
                ceeFactory, ceeRepository, peeRepository, courseEditionRepository
        );

        US28_RemoveTheEnrolmentOfAStudentInACourseEditionController controller =
                new US28_RemoveTheEnrolmentOfAStudentInACourseEditionController(ceeService);

        StudentID studentID = new StudentID(1000001);

        NameWithNumbersAndSpecialChars name1 = new NameWithNumbersAndSpecialChars("Programme1");
        Acronym acronym1 = new Acronym("P1");
        ProgrammeID programmeID1 = new ProgrammeID(name1, acronym1);
        SchoolYearID schoolYearID1 = new SchoolYearID();
        ProgrammeEditionID programmeEditionID1 = new ProgrammeEditionID(programmeID1, schoolYearID1);

        Date date1 = new Date("12-12-2023");
        StudyPlanID studyPlanID1 = new StudyPlanID(programmeID1, date1);

        Acronym acronym = new Acronym("DSOFT");
        Name name = new Name("Software Development");
        CourseID courseID1 = new CourseID(acronym, name);

        CourseInStudyPlanID courseInStudyPlanID1 = new CourseInStudyPlanID(courseID1, studyPlanID1);

        CourseEditionID ceID1 = new CourseEditionID(programmeEditionID1, courseInStudyPlanID1);

        Optional<CourseEditionEnrolment> existingEnrolment = ceeRepository.findByStudentAndEdition(studentID, ceID1);
        if (!existingEnrolment.isPresent()) {
            CourseEditionEnrolment courseEditionEnrolment = new CourseEditionEnrolment(studentID, ceID1);
            ceeRepository.save(courseEditionEnrolment);
        }

        // Act
        boolean firstRemoval = controller.removeCourseEditionEnrolment(studentID, ceID1);
        boolean secondRemoval = controller.removeCourseEditionEnrolment(studentID, ceID1);

        // Assert
        assertTrue(firstRemoval, "Enrolment should be removed successfully.");
        assertFalse(secondRemoval, "The second removal should not succeed.");
    }

// ==============================
// Multiple Removals for Several Students
// ==============================

    // Batch Removal of Multiple Students
    @Test
    void removeMultipleStudentsFromSameCourseEdition_ShouldReturnTrueForBoth_IntegrationTest() throws Exception {
        // Arrange
        ProgrammeEditionEnrolmentFactoryImpl peeFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl peeListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepositoryImpl peeRepository = new ProgrammeEditionEnrolmentRepositoryImpl(peeFactory, peeListFactory);

        CourseEditionEnrolmentFactoryImpl ceeFactory = new CourseEditionEnrolmentFactoryImpl();
        CourseEditionEnrolmentListFactoryImpl ceeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl ceeRepository = new CourseEditionEnrolmentRepositoryImpl(ceeListFactory);

        CourseEditionFactoryImpl ceFactory = new CourseEditionFactoryImpl();
        CourseEditionListFactoryImpl ceListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepositoryImpl courseEditionRepository = new CourseEditionRepositoryImpl(ceFactory, ceListFactory);

        CourseEditionEnrolmentServiceImpl ceeService = new CourseEditionEnrolmentServiceImpl(
                ceeFactory, ceeRepository, peeRepository, courseEditionRepository
        );

        US28_RemoveTheEnrolmentOfAStudentInACourseEditionController controller =
                new US28_RemoveTheEnrolmentOfAStudentInACourseEditionController(ceeService);

        StudentID studentID = new StudentID(1000001);
        StudentID studentID2 = new StudentID(1000002);

        NameWithNumbersAndSpecialChars name1 = new NameWithNumbersAndSpecialChars("Programme1");
        Acronym acronym1 = new Acronym("P1");
        ProgrammeID programmeID1 = new ProgrammeID(name1, acronym1);
        SchoolYearID schoolYearID1 = new SchoolYearID();
        ProgrammeEditionID programmeEditionID1 = new ProgrammeEditionID(programmeID1, schoolYearID1);

        Date date1 = new Date("12-12-2023");
        StudyPlanID studyPlanID1 = new StudyPlanID(programmeID1, date1);

        Acronym acronym = new Acronym("DSOFT");
        Name name = new Name("Software Development");
        CourseID courseID1 = new CourseID(acronym, name);

        CourseInStudyPlanID courseInStudyPlanID1 = new CourseInStudyPlanID(courseID1, studyPlanID1);

        CourseEditionID ceID1 = new CourseEditionID(programmeEditionID1, courseInStudyPlanID1);

        Optional<CourseEditionEnrolment> existingEnrolment = ceeRepository.findByStudentAndEdition(studentID, ceID1);
        if (!existingEnrolment.isPresent()) {
            CourseEditionEnrolment courseEditionEnrolment = new CourseEditionEnrolment(studentID, ceID1);
            ceeRepository.save(courseEditionEnrolment);
        }

        Optional<CourseEditionEnrolment> existingEnrolment2 = ceeRepository.findByStudentAndEdition(studentID2, ceID1);
        if (!existingEnrolment2.isPresent()) {
            CourseEditionEnrolment courseEditionEnrolment = new CourseEditionEnrolment(studentID2, ceID1);
            ceeRepository.save(courseEditionEnrolment);
        }

        // Act
        boolean firstRemoval = controller.removeCourseEditionEnrolment(studentID, ceID1);
        boolean secondRemoval = controller.removeCourseEditionEnrolment(studentID2, ceID1);

        // Assert
        assertTrue(firstRemoval, "First student's enrolment should be removed successfully.");
        assertTrue(secondRemoval, "Second student's enrolment should be removed successfully.");
    }
}

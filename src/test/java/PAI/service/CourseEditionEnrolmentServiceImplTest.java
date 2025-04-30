package PAI.service;

import PAI.VOs.CourseEditionID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.StudentID;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.domain.ProgrammeEditionEnrolment;

import PAI.domain.courseEditionEnrolment.ICourseEditionEnrolmentFactory;
import PAI.domain.courseEditionEnrolment.ICourseEditionEnrolmentRepository;
import PAI.repository.ICourseEditionRepository;
import PAI.repository.IProgrammeEditionEnrolmentRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseEditionEnrolmentServiceImplTest {

    //testing constructor
    @Test
    void shouldReturnExceptionIfCourseEditionEnrollmentFactoryIsNull() {
        //arrange
        ICourseEditionEnrolmentRepository doubleCeeRepositoryInterface = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository doubleCeRepositoryInterface = mock(ICourseEditionRepository.class);
        IProgrammeEditionEnrolmentRepository doublePeeRepositoryInterface = mock(IProgrammeEditionEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CourseEditionEnrolmentServiceImpl(null, doubleCeeRepositoryInterface,doublePeeRepositoryInterface, doubleCeRepositoryInterface);
        });

        //assert
        assertEquals("Course Edition Enrolment Factory Interface cannot be null!", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfProgrammeEditionEnrollmentRepositoryInterfaceIsNull() {
        //arrange
        ICourseEditionEnrolmentFactory doubleCeeFactoryInterface = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentRepository doubleCeeRepositoryInterface = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository doubleCourseEditionRepositoryInterface = mock(ICourseEditionRepository.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CourseEditionEnrolmentServiceImpl(doubleCeeFactoryInterface,doubleCeeRepositoryInterface, null, doubleCourseEditionRepositoryInterface);
        });

        //assert
        assertEquals("Programme Edition Enrolment Repository Interface cannot be null!", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfCourseEditionRepositoryIsNull() {
        //arrange
        ICourseEditionEnrolmentFactory doubleCeeFactoryInterface = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentRepository doubleCeeRepositoryInterface = mock(ICourseEditionEnrolmentRepository.class);
        IProgrammeEditionEnrolmentRepository doublePeeRepositoryInterface = mock(IProgrammeEditionEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CourseEditionEnrolmentServiceImpl(doubleCeeFactoryInterface,doubleCeeRepositoryInterface, doublePeeRepositoryInterface, null);
        });

        //assert
        assertEquals("Course Edition Repository Interface cannot be null!", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfCourseEditionEnrollmentRepositoryIsNull() {
        //arrange
        ICourseEditionEnrolmentFactory doubleCeeFactoryInterface = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionRepository doubleCeRepositoryInterface = mock(ICourseEditionRepository.class);
        IProgrammeEditionEnrolmentRepository doublePeeRepositoryInterface = mock(IProgrammeEditionEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CourseEditionEnrolmentServiceImpl(doubleCeeFactoryInterface,null, doublePeeRepositoryInterface, doubleCeRepositoryInterface);
        });

        //assert
        assertEquals("Course Edition Enrolment Repository Interface cannot be null!", exception.getMessage());
    }

    //testing find Programme Editions that Student is Enrolled Method
    @Test
    void shouldReturnEmptyListWhenStudentIsNull() {
        //arrange
        ICourseEditionEnrolmentFactory doubleCeeFactoryInterface = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionRepository doubleCourseEditionRepositoryInterface = mock(ICourseEditionRepository.class);
        IProgrammeEditionEnrolmentRepository doublePeeRepositoryInterface = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentRepository doubleCeeRepositoryInterface = mock(ICourseEditionEnrolmentRepository.class);

        CourseEditionEnrolmentServiceImpl service = new CourseEditionEnrolmentServiceImpl(
                doubleCeeFactoryInterface,doubleCeeRepositoryInterface, doublePeeRepositoryInterface, doubleCourseEditionRepositoryInterface);

        //act
        List<ProgrammeEditionID> result = service.findProgrammeEditionIDsThatStudentIsEnrolled(null);

        //assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnAListOfProgrammeEditionsThatStudentIsEnrolled() {
        //arrange
        ICourseEditionEnrolmentFactory doubleCeeFactoryInterface = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionRepository doubleCourseEditionRepositoryInterface = mock(ICourseEditionRepository.class);
        IProgrammeEditionEnrolmentRepository doublePeeRepositoryInterface = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentRepository doubleCeeRepositoryInterface = mock(ICourseEditionEnrolmentRepository.class);

        CourseEditionEnrolmentServiceImpl service = new CourseEditionEnrolmentServiceImpl(
                doubleCeeFactoryInterface,doubleCeeRepositoryInterface, doublePeeRepositoryInterface, doubleCourseEditionRepositoryInterface);

        StudentID doubleStudentId = mock(StudentID.class);
        ProgrammeEditionID doublePee1Id = mock(ProgrammeEditionID.class);
        ProgrammeEditionID doublePee2Id = mock(ProgrammeEditionID.class);
        ProgrammeEditionID doublePee3Id = mock(ProgrammeEditionID.class);


        when(doublePeeRepositoryInterface.findProgrammeEditionsThatStudentIsEnrolled(doubleStudentId)).
                thenReturn(List.of(doublePee1Id, doublePee2Id, doublePee3Id));

        //act
        List<ProgrammeEditionID> result = service.findProgrammeEditionIDsThatStudentIsEnrolled(doubleStudentId);

        //assert
        assertEquals(3, result.size());
    }

    @Test
    void shouldReturnAnEmptyListWhenStudentIsNotEnrolledInAnyProgrammeEdition() {
        //arrange
        ICourseEditionEnrolmentFactory doubleCeeFactoryInterface = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionRepository doubleCourseEditionRepositoryInterface = mock(ICourseEditionRepository.class);
        IProgrammeEditionEnrolmentRepository doublePeeRepositoryInterface = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentRepository doubleCeeRepositoryInterface = mock(ICourseEditionEnrolmentRepository.class);

        CourseEditionEnrolmentServiceImpl service = new CourseEditionEnrolmentServiceImpl(
                doubleCeeFactoryInterface,doubleCeeRepositoryInterface, doublePeeRepositoryInterface, doubleCourseEditionRepositoryInterface);

        StudentID doubleStudentId = mock(StudentID.class);


        when(doublePeeRepositoryInterface.findProgrammeEditionsThatStudentIsEnrolled(doubleStudentId)).
                thenReturn(List.of());

        //act
        List<ProgrammeEditionID> result = service.findProgrammeEditionIDsThatStudentIsEnrolled(doubleStudentId);

        //assert
        assertEquals(0, result.size());
    }


    //testing find Course Editions by Programme Edition Method
    @Test
    void shouldReturnAListOfCourseEditionsThatBelongsToAProgrammeEdition() {
        //arrange
        ICourseEditionEnrolmentFactory doubleCeeFactoryInterface = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionRepository doubleCourseEditionRepositoryInterface = mock(ICourseEditionRepository.class);
        IProgrammeEditionEnrolmentRepository doublePeeRepositoryInterface = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentRepository doubleCeeRepositoryInterface = mock(ICourseEditionEnrolmentRepository.class);

        CourseEditionEnrolmentServiceImpl service = new CourseEditionEnrolmentServiceImpl(
                doubleCeeFactoryInterface,doubleCeeRepositoryInterface, doublePeeRepositoryInterface, doubleCourseEditionRepositoryInterface);

        ProgrammeEditionID doubleProgrammeEditionID = mock(ProgrammeEditionID.class);
        CourseEditionID doubleCourseEdition1 = mock(CourseEditionID.class);
        CourseEditionID doubleCourseEdition2 = mock(CourseEditionID.class);

        when(doubleCourseEditionRepositoryInterface.findCourseEditionsByProgrammeEditionID(doubleProgrammeEditionID)).thenReturn(List.of(doubleCourseEdition1, doubleCourseEdition2));

        //act
        List<CourseEditionID> result = service.findCourseEditionIDsByProgrammeEdition(doubleProgrammeEditionID);

        //assert
        assertEquals(2, result.size());
    }


    //testing enroll a student in a course edition method
    @Test
    void shouldReturnTrueIfIsAValidCourseEditionEnrollment() throws Exception {
        //arrange
        ICourseEditionEnrolmentFactory doubleCeeFactoryInterface = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionRepository doubleCourseEditionRepositoryInterface = mock(ICourseEditionRepository.class);
        IProgrammeEditionEnrolmentRepository doublePeeRepositoryInterface = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentRepository doubleCeeRepositoryInterface = mock(ICourseEditionEnrolmentRepository.class);

        CourseEditionEnrolmentServiceImpl service = new CourseEditionEnrolmentServiceImpl(
                doubleCeeFactoryInterface,doubleCeeRepositoryInterface, doublePeeRepositoryInterface, doubleCourseEditionRepositoryInterface);

        StudentID doubleStudentID = mock(StudentID.class);
        CourseEditionID doubleCEID = mock(CourseEditionID.class);
        CourseEditionEnrolment doubleCEE = mock (CourseEditionEnrolment.class);

        when(doubleCeeFactoryInterface.createCourseEditionEnrolment(doubleStudentID,doubleCEID)).thenReturn(doubleCEE);

        when(doubleCeeRepositoryInterface.isStudentEnrolledInCourseEdition(doubleStudentID, doubleCEID)).thenReturn(false);

        when(doubleCeeRepositoryInterface.save(doubleCEE)).thenReturn(doubleCEE);

        //act
        boolean result = service.enrolStudentInACourseEdition(doubleStudentID, doubleCEID);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWhenDifferentStudentsEnrollInSameCourseEdition() throws Exception {
        //arrange
        ICourseEditionEnrolmentFactory doubleCeeFactoryInterface = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionRepository doubleCourseEditionRepositoryInterface = mock(ICourseEditionRepository.class);
        IProgrammeEditionEnrolmentRepository doublePeeRepositoryInterface = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentRepository doubleCeeRepositoryInterface = mock(ICourseEditionEnrolmentRepository.class);

        CourseEditionEnrolmentServiceImpl service = new CourseEditionEnrolmentServiceImpl(
                doubleCeeFactoryInterface,doubleCeeRepositoryInterface, doublePeeRepositoryInterface, doubleCourseEditionRepositoryInterface);

        StudentID doubleStudentID1 = mock(StudentID.class);
        CourseEditionID doubleCEID = mock(CourseEditionID.class);
        StudentID doubleStudentID2 = mock(StudentID.class);
        CourseEditionEnrolment doubleCEE1 = mock (CourseEditionEnrolment.class);
        CourseEditionEnrolment doubleCEE2 = mock (CourseEditionEnrolment.class);

        when(doubleCeeFactoryInterface.createCourseEditionEnrolment(doubleStudentID1,doubleCEID)).thenReturn(doubleCEE1);
        when(doubleCeeFactoryInterface.createCourseEditionEnrolment(doubleStudentID2,doubleCEID)).thenReturn(doubleCEE2);

        when(doubleCeeRepositoryInterface.isStudentEnrolledInCourseEdition(doubleStudentID1,doubleCEID)).thenReturn(false);

        when(doubleCeeRepositoryInterface.save(doubleCEE1)).thenReturn(doubleCEE1);
        service.enrolStudentInACourseEdition(doubleStudentID1, doubleCEID);

        when(doubleCeeRepositoryInterface.save(doubleCEE2)).thenReturn(doubleCEE2);

        //act
        boolean result = service.enrolStudentInACourseEdition(doubleStudentID2, doubleCEID);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWhenDifferentStudentsEnrollInDifferentCourseEditions() throws Exception {
        //arrange
        ICourseEditionEnrolmentFactory doubleCeeFactoryInterface = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionRepository doubleCourseEditionRepositoryInterface = mock(ICourseEditionRepository.class);
        IProgrammeEditionEnrolmentRepository doublePeeRepositoryInterface = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentRepository doubleCeeRepositoryInterface = mock(ICourseEditionEnrolmentRepository.class);

        CourseEditionEnrolmentServiceImpl service = new CourseEditionEnrolmentServiceImpl(
                doubleCeeFactoryInterface,doubleCeeRepositoryInterface, doublePeeRepositoryInterface, doubleCourseEditionRepositoryInterface);

        StudentID doubleStudentID1 = mock(StudentID.class);
        CourseEditionID doubleCEID1 = mock(CourseEditionID.class);
        StudentID doubleStudentID2 = mock(StudentID.class);
        CourseEditionID doubleCEID2 = mock(CourseEditionID.class);
        CourseEditionEnrolment doubleCEE1 = mock (CourseEditionEnrolment.class);
        CourseEditionEnrolment doubleCEE2 = mock (CourseEditionEnrolment.class);

        when(doubleCeeFactoryInterface.createCourseEditionEnrolment(doubleStudentID1,doubleCEID1)).thenReturn(doubleCEE1);
        when(doubleCeeFactoryInterface.createCourseEditionEnrolment(doubleStudentID2,doubleCEID2)).thenReturn(doubleCEE2);

        when(doubleCeeRepositoryInterface.isStudentEnrolledInCourseEdition(doubleStudentID1,doubleCEID1)).thenReturn(false);

        when(doubleCeeRepositoryInterface.save(doubleCEE1)).thenReturn(doubleCEE1);
        service.enrolStudentInACourseEdition(doubleStudentID1, doubleCEID1);

        when(doubleCeeRepositoryInterface.save(doubleCEE2)).thenReturn(doubleCEE2);

        //act
        boolean result = service.enrolStudentInACourseEdition(doubleStudentID2, doubleCEID2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWhenSameStudentEnrollInDifferentCourseEditions() throws Exception {
        //arrange
        ICourseEditionEnrolmentFactory doubleCeeFactoryInterface = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionRepository doubleCourseEditionRepositoryInterface = mock(ICourseEditionRepository.class);
        IProgrammeEditionEnrolmentRepository doublePeeRepositoryInterface = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentRepository doubleCeeRepositoryInterface = mock(ICourseEditionEnrolmentRepository.class);

        CourseEditionEnrolmentServiceImpl service = new CourseEditionEnrolmentServiceImpl(
                doubleCeeFactoryInterface,doubleCeeRepositoryInterface, doublePeeRepositoryInterface, doubleCourseEditionRepositoryInterface);

        StudentID doubleStudentID1 = mock(StudentID.class);
        CourseEditionID doubleCEID1 = mock(CourseEditionID.class);
        CourseEditionID doubleCEID2 = mock(CourseEditionID.class);
        CourseEditionEnrolment doubleCEE1 = mock (CourseEditionEnrolment.class);
        CourseEditionEnrolment doubleCEE2 = mock (CourseEditionEnrolment.class);

        when(doubleCeeFactoryInterface.createCourseEditionEnrolment(doubleStudentID1,doubleCEID1)).thenReturn(doubleCEE1);
        when(doubleCeeFactoryInterface.createCourseEditionEnrolment(doubleStudentID1,doubleCEID2)).thenReturn(doubleCEE2);

        when(doubleCeeRepositoryInterface.isStudentEnrolledInCourseEdition(doubleStudentID1,doubleCEID1)).thenReturn(false);

        when(doubleCeeRepositoryInterface.save(doubleCEE1)).thenReturn(doubleCEE1);
        service.enrolStudentInACourseEdition(doubleStudentID1, doubleCEID1);

        when(doubleCeeRepositoryInterface.save(doubleCEE2)).thenReturn(doubleCEE2);

        //act
        boolean result = service.enrolStudentInACourseEdition(doubleStudentID1, doubleCEID2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenCourseEditionEnrolmentWasNotSaved() throws Exception {
        //arrange
        ICourseEditionEnrolmentFactory doubleCeeFactoryInterface = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionRepository doubleCourseEditionRepositoryInterface = mock(ICourseEditionRepository.class);
        IProgrammeEditionEnrolmentRepository doublePeeRepositoryInterface = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentRepository doubleCeeRepositoryInterface = mock(ICourseEditionEnrolmentRepository.class);

        CourseEditionEnrolmentServiceImpl service = new CourseEditionEnrolmentServiceImpl(
                doubleCeeFactoryInterface,doubleCeeRepositoryInterface, doublePeeRepositoryInterface, doubleCourseEditionRepositoryInterface);

        StudentID doubleStudentID = mock(StudentID.class);
        CourseEditionID doubleCEID = mock(CourseEditionID.class);
        CourseEditionEnrolment doubleCEE = mock (CourseEditionEnrolment.class);

        when(doubleCeeFactoryInterface.createCourseEditionEnrolment(doubleStudentID,doubleCEID)).thenReturn(doubleCEE);

        when(doubleCeeRepositoryInterface.isStudentEnrolledInCourseEdition(doubleStudentID, doubleCEID)).thenReturn(false);

        when(doubleCeeRepositoryInterface.save(doubleCEE)).thenThrow();

        //act
        boolean result = service.enrolStudentInACourseEdition(doubleStudentID, doubleCEID);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfStudentIDIsNull (){
        //arrange

        ICourseEditionEnrolmentFactory doubleCeeFactoryInterface = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionRepository doubleCourseEditionRepositoryInterface = mock(ICourseEditionRepository.class);
        IProgrammeEditionEnrolmentRepository doublePeeRepositoryInterface = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentRepository doubleCeeRepositoryInterface = mock(ICourseEditionEnrolmentRepository.class);

        CourseEditionEnrolmentServiceImpl service = new CourseEditionEnrolmentServiceImpl(
                doubleCeeFactoryInterface,doubleCeeRepositoryInterface, doublePeeRepositoryInterface, doubleCourseEditionRepositoryInterface);

        CourseEditionID doubleCEID = mock(CourseEditionID.class);

        //act
        boolean result = service.enrolStudentInACourseEdition(null, doubleCEID);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfCourseEditionIDIsNull (){
        //arrange

        ICourseEditionEnrolmentFactory doubleCeeFactoryInterface = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionRepository doubleCourseEditionRepositoryInterface = mock(ICourseEditionRepository.class);
        IProgrammeEditionEnrolmentRepository doublePeeRepositoryInterface = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentRepository doubleCeeRepositoryInterface = mock(ICourseEditionEnrolmentRepository.class);

        CourseEditionEnrolmentServiceImpl service = new CourseEditionEnrolmentServiceImpl(
                doubleCeeFactoryInterface,doubleCeeRepositoryInterface, doublePeeRepositoryInterface, doubleCourseEditionRepositoryInterface);

        StudentID doubleStudentID = mock(StudentID.class);

        //act
        boolean result = service.enrolStudentInACourseEdition(doubleStudentID, null);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfStudentIsAlreadyEnrolled() throws Exception {
        //arrange
        ICourseEditionEnrolmentFactory doubleCeeFactoryInterface = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionRepository doubleCourseEditionRepositoryInterface = mock(ICourseEditionRepository.class);
        IProgrammeEditionEnrolmentRepository doublePeeRepositoryInterface = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentRepository doubleCeeRepositoryInterface = mock(ICourseEditionEnrolmentRepository.class);

        CourseEditionEnrolmentServiceImpl service = new CourseEditionEnrolmentServiceImpl(
                doubleCeeFactoryInterface,doubleCeeRepositoryInterface, doublePeeRepositoryInterface, doubleCourseEditionRepositoryInterface);

        StudentID doubleStudentID = mock(StudentID.class);
        CourseEditionID doubleCEID = mock(CourseEditionID.class);
        CourseEditionEnrolment doubleCEE = mock (CourseEditionEnrolment.class);

        when(doubleCeeFactoryInterface.createCourseEditionEnrolment(doubleStudentID,doubleCEID)).thenReturn(doubleCEE);

        when(doubleCeeRepositoryInterface.isStudentEnrolledInCourseEdition(doubleStudentID, doubleCEID)).thenReturn(true);

        //act
        boolean result = service.enrolStudentInACourseEdition(doubleStudentID, doubleCEID);

        //assert
        assertFalse(result);
    }


    @Test
    void shouldReturnFalseIfIsNotPossibleTheCreationOfCourseEditionEnrolment (){
        //arrange
        ICourseEditionEnrolmentFactory doubleCeeFactoryInterface = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionRepository doubleCourseEditionRepositoryInterface = mock(ICourseEditionRepository.class);
        IProgrammeEditionEnrolmentRepository doublePeeRepositoryInterface = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentRepository doubleCeeRepositoryInterface = mock(ICourseEditionEnrolmentRepository.class);

        CourseEditionEnrolmentServiceImpl service = new CourseEditionEnrolmentServiceImpl(
                doubleCeeFactoryInterface,doubleCeeRepositoryInterface, doublePeeRepositoryInterface, doubleCourseEditionRepositoryInterface);

        StudentID doubleStudentID = mock(StudentID.class);
        CourseEditionID doubleCEID = mock(CourseEditionID.class);

        when(doubleCeeFactoryInterface.createCourseEditionEnrolment(doubleStudentID, doubleCEID)).thenThrow();

        //act
        boolean result = service.enrolStudentInACourseEdition(doubleStudentID, doubleCEID);

        //assert
        assertFalse(result);
    }


//---------------Enrolment Removal Tests--------------

// ==============================
// Simple Tests
// ==============================

    @Test
    void shouldRemoveActiveCourseEditionEnrolment() throws Exception {
        // Arrange
        ICourseEditionEnrolmentRepository ceeRepository = mock(ICourseEditionEnrolmentRepository.class);
        IProgrammeEditionEnrolmentRepository peeRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseEditionEnrolmentFactory factory = mock(ICourseEditionEnrolmentFactory.class);

        CourseEditionEnrolment enrolment = mock(CourseEditionEnrolment.class);
        StudentID studentID = mock(StudentID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        when(ceeRepository.findByStudentAndEdition(studentID, courseEditionID)).thenReturn(Optional.of(enrolment));
        when(enrolment.hasStudent(studentID)).thenReturn(true);
        when(enrolment.isEnrolmentActive()).thenReturn(true);
        when(enrolment.knowCourseEdition()).thenReturn(courseEditionID);
        when(courseEditionID.getProgrammeEditionID()).thenReturn(programmeEditionID);

        CourseEditionEnrolmentServiceImpl service = new CourseEditionEnrolmentServiceImpl(
                factory, ceeRepository, peeRepository, courseEditionRepository);

        // Act
        boolean result = service.removeCourseEditionEnrolment(studentID, courseEditionID);

        // Assert
        assertTrue(result);
        verify(enrolment, times(1)).deactivateEnrolment();
        verify(ceeRepository, times(1)).save(enrolment);
    }

    @Test
    void shouldRemoveLastActiveCourseEditionEnrolmentAndDeactivateProgrammeEdition() throws Exception {
        // Arrange
        ICourseEditionEnrolmentRepository ceeRepository = mock(ICourseEditionEnrolmentRepository.class);
        IProgrammeEditionEnrolmentRepository peeRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseEditionEnrolmentFactory factory = mock(ICourseEditionEnrolmentFactory.class);

        CourseEditionEnrolment enrolment = mock(CourseEditionEnrolment.class);
        StudentID studentID = mock(StudentID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        when(ceeRepository.findByStudentAndEdition(studentID, courseEditionID)).thenReturn(Optional.of(enrolment));
        when(enrolment.hasStudent(studentID)).thenReturn(true);
        when(enrolment.isEnrolmentActive()).thenReturn(true);
        when(enrolment.knowCourseEdition()).thenReturn(courseEditionID);
        when(courseEditionID.getProgrammeEditionID()).thenReturn(programmeEditionID);
        when(peeRepository.findByStudentAndProgrammeEdition(studentID, programmeEditionID)).thenReturn(Optional.of(mock(ProgrammeEditionEnrolment.class)));

        CourseEditionEnrolmentServiceImpl service = new CourseEditionEnrolmentServiceImpl(
                factory, ceeRepository, peeRepository, courseEditionRepository);

        // Act
        boolean result = service.removeCourseEditionEnrolment(studentID, courseEditionID);
        when(enrolment.isEnrolmentActive()).thenReturn(false);

        // Assert
        assertTrue(result);
        verify(peeRepository, times(1)).save(any(ProgrammeEditionEnrolment.class));
    }

    @Test
    void shouldNotRemoveInactiveEnrolment() throws Exception {
        // Arrange
        ICourseEditionEnrolmentRepository ceeRepository = mock(ICourseEditionEnrolmentRepository.class);
        IProgrammeEditionEnrolmentRepository peeRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseEditionEnrolmentFactory factory = mock(ICourseEditionEnrolmentFactory.class);

        CourseEditionEnrolment enrolment = mock(CourseEditionEnrolment.class);
        StudentID studentID = mock(StudentID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        when(ceeRepository.findByStudentAndEdition(studentID, courseEditionID)).thenReturn(Optional.of(enrolment));
        when(enrolment.isEnrolmentActive()).thenReturn(false); // Inactive enrolment

        CourseEditionEnrolmentServiceImpl service = new CourseEditionEnrolmentServiceImpl(
                factory, ceeRepository, peeRepository, courseEditionRepository);

        // Act
        boolean result = service.removeCourseEditionEnrolment(studentID, courseEditionID);

        // Assert
        assertFalse(result);
        verify(enrolment, never()).deactivateEnrolment();
        verify(ceeRepository, never()).save(enrolment);
    }

// ==============================
// Null Tests
// ==============================

    @Test
    void shouldReturnFalseForNullStudent() throws Exception {
        // Arrange
        ICourseEditionEnrolmentRepository ceeRepository = mock(ICourseEditionEnrolmentRepository.class);
        IProgrammeEditionEnrolmentRepository peeRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseEditionEnrolmentFactory factory = mock(ICourseEditionEnrolmentFactory.class);

        CourseEditionEnrolmentServiceImpl service = new CourseEditionEnrolmentServiceImpl(
                factory, ceeRepository, peeRepository, courseEditionRepository);

        // Act
        boolean result = service.removeCourseEditionEnrolment(null, mock(CourseEditionID.class));

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseForNullCourseEdition() throws Exception {
        // Arrange
        ICourseEditionEnrolmentRepository ceeRepository = mock(ICourseEditionEnrolmentRepository.class);
        IProgrammeEditionEnrolmentRepository peeRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseEditionEnrolmentFactory factory = mock(ICourseEditionEnrolmentFactory.class);

        CourseEditionEnrolmentServiceImpl service = new CourseEditionEnrolmentServiceImpl(
                factory, ceeRepository, peeRepository, courseEditionRepository);

        // Act
        boolean result = service.removeCourseEditionEnrolment(mock(StudentID.class), null);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseForNonExistentEnrolment() throws Exception {
        // Arrange
        ICourseEditionEnrolmentRepository ceeRepository = mock(ICourseEditionEnrolmentRepository.class);
        IProgrammeEditionEnrolmentRepository peeRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseEditionEnrolmentFactory factory = mock(ICourseEditionEnrolmentFactory.class);

        StudentID studentID = mock(StudentID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        when(ceeRepository.findByStudentAndEdition(studentID, courseEditionID)).thenReturn(Optional.empty());

        CourseEditionEnrolmentServiceImpl service = new CourseEditionEnrolmentServiceImpl(
                factory, ceeRepository, peeRepository, courseEditionRepository);

        // Act
        boolean result = service.removeCourseEditionEnrolment(studentID, courseEditionID);

        // Assert
        assertFalse(result);
    }

// ==============================
// Multiple Removals of the Same Student
// ==============================

    @Test
    void removeSameEnrolmentTwice_ShouldReturnFalseOnSecondAttempt() throws Exception {
        // Arrange
        ICourseEditionEnrolmentRepository ceeRepository = mock(ICourseEditionEnrolmentRepository.class);
        IProgrammeEditionEnrolmentRepository peeRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseEditionEnrolmentFactory factory = mock(ICourseEditionEnrolmentFactory.class);

        CourseEditionEnrolment enrolment = mock(CourseEditionEnrolment.class);
        StudentID studentID = mock(StudentID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        when(ceeRepository.findByStudentAndEdition(studentID, courseEditionID)).thenReturn(Optional.of(enrolment));
        when(enrolment.isEnrolmentActive()).thenReturn(true); // Enrolment is active initially

        CourseEditionEnrolmentServiceImpl service = new CourseEditionEnrolmentServiceImpl(
                factory, ceeRepository, peeRepository, courseEditionRepository);

        // Act - First removal attempt (should succeed)
        boolean firstResult = service.removeCourseEditionEnrolment(studentID, courseEditionID);

        // Assert that the first removal returns true (successful)
        assertTrue(firstResult, "First removal should return true");

        // Verify that the enrolment was deactivated and saved
        verify(ceeRepository, times(1)).save(enrolment); // Should be called once to save the deactivated enrolment

        // Change the state to inactive for the second removal attempt
        when(enrolment.isEnrolmentActive()).thenReturn(false); // Simulate that the enrolment is now inactive

        // Act - Second removal attempt (should fail because it's already inactive)
        boolean secondResult = service.removeCourseEditionEnrolment(studentID, courseEditionID);

        // Assert that the second removal returns false (it should fail since enrolment is inactive)
        assertFalse(secondResult, "Second removal should return false because enrolment is already inactive");

        // Verify that the enrolment was not saved again
        verify(ceeRepository, times(1)).save(enrolment); // Should not be called again, only once
    }

    @Test
    void removeStudentFromTwoCourseEditionsButLeaveOtherActiveEnrolments_ShouldNotDeactivateProgrammeEdition() throws Exception {
        // Arrange
        ICourseEditionEnrolmentRepository ceeRepository = mock(ICourseEditionEnrolmentRepository.class);
        IProgrammeEditionEnrolmentRepository peeRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseEditionEnrolmentFactory factory = mock(ICourseEditionEnrolmentFactory.class);

        CourseEditionEnrolment enrolment1 = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolment enrolment2 = mock(CourseEditionEnrolment.class);
        StudentID studentID = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        CourseEditionID courseEditionID2 = mock(CourseEditionID.class);
        CourseEditionID courseEditionID3 = mock(CourseEditionID.class); // Another active course edition
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        // Simulate the repository behavior for two course editions being removed, and another active enrolment
        when(ceeRepository.findByStudentAndEdition(studentID, courseEditionID1)).thenReturn(Optional.of(enrolment1));
        when(ceeRepository.findByStudentAndEdition(studentID, courseEditionID2)).thenReturn(Optional.of(enrolment2));
        when(ceeRepository.findByStudentAndEdition(studentID, courseEditionID3)).thenReturn(Optional.of(mock(CourseEditionEnrolment.class)));

        when(enrolment1.isEnrolmentActive()).thenReturn(true); // Enrolment 1 is active
        when(enrolment2.isEnrolmentActive()).thenReturn(true); // Enrolment 2 is active
        when(ceeRepository.findAll()).thenReturn(List.of(enrolment1, enrolment2)); // Simulate that there are still active enrolments

        // Mock the behaviour of Programme Edition Enrolment repository to check the existence of active enrolment
        ProgrammeEditionEnrolment programmeEnrolment = mock(ProgrammeEditionEnrolment.class);
        when(peeRepository.findByStudentAndProgrammeEdition(studentID, programmeEditionID)).thenReturn(Optional.of(programmeEnrolment));
        when(programmeEnrolment.isEnrolmentActive()).thenReturn(true); // Programme is active

        // Create the service instance
        CourseEditionEnrolmentServiceImpl service = new CourseEditionEnrolmentServiceImpl(
                factory, ceeRepository, peeRepository, courseEditionRepository);

        // Act - Remove the first course edition enrolment
        boolean result1 = service.removeCourseEditionEnrolment(studentID, courseEditionID1);

        // Assert that the first course edition removal returns true (successful)
        assertTrue(result1, "First removal should return true");

        // Act - Remove the second course edition enrolment
        boolean result2 = service.removeCourseEditionEnrolment(studentID, courseEditionID2);

        // Assert that the second course edition removal returns true (successful)
        assertTrue(result2, "Second removal should return true");

        // Verify that both Course Edition Enrolments were deactivated and saved
        verify(ceeRepository, times(1)).save(enrolment1); // First enrolment should be saved after deactivation
        verify(ceeRepository, times(1)).save(enrolment2); // Second enrolment should also be saved after deactivation

        // Verify that the Programme Edition Enrolment was NOT deactivated (because student still has active enrolments)
        verify(peeRepository, never()).save(programmeEnrolment); // Programme Enrolment should not be saved
    }

    @Test
    void shouldRemoveTwoActiveCourseEditionsForStudentAndDeactivateProgrammeEdition() throws Exception {
        // Arrange
        ICourseEditionEnrolmentRepository ceeRepository = mock(ICourseEditionEnrolmentRepository.class);
        IProgrammeEditionEnrolmentRepository peeRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseEditionEnrolmentFactory factory = mock(ICourseEditionEnrolmentFactory.class);

        StudentID studentID = mock(StudentID.class);
        CourseEditionID firstCourseEditionID = mock(CourseEditionID.class);
        CourseEditionID secondCourseEditionID = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        CourseEditionEnrolment firstEnrolment = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolment secondEnrolment = mock(CourseEditionEnrolment.class);

        // Setting up the behavior of the mocks
        when(ceeRepository.findByStudentAndEdition(studentID, firstCourseEditionID)).thenReturn(Optional.of(firstEnrolment));
        when(ceeRepository.findByStudentAndEdition(studentID, secondCourseEditionID)).thenReturn(Optional.of(secondEnrolment));
        when(firstEnrolment.hasStudent(studentID)).thenReturn(true);
        when(secondEnrolment.hasStudent(studentID)).thenReturn(true);
        when(firstEnrolment.isEnrolmentActive()).thenReturn(true);
        when(secondEnrolment.isEnrolmentActive()).thenReturn(true);
        when(firstEnrolment.knowCourseEdition()).thenReturn(firstCourseEditionID);
        when(secondEnrolment.knowCourseEdition()).thenReturn(secondCourseEditionID);
        when(firstCourseEditionID.getProgrammeEditionID()).thenReturn(programmeEditionID);
        when(secondCourseEditionID.getProgrammeEditionID()).thenReturn(programmeEditionID);
        when(peeRepository.findByStudentAndProgrammeEdition(studentID, programmeEditionID)).thenReturn(Optional.of(mock(ProgrammeEditionEnrolment.class)));

        CourseEditionEnrolmentServiceImpl service = new CourseEditionEnrolmentServiceImpl(
                factory, ceeRepository, peeRepository, courseEditionRepository);

        // Act - Remove the first course edition enrolment
        boolean firstRemoveResult = service.removeCourseEditionEnrolment(studentID, firstCourseEditionID);

        // Now simulate that the second course enrolment is the last one
        when(ceeRepository.findByStudentAndEdition(studentID, secondCourseEditionID)).thenReturn(Optional.of(secondEnrolment));
        when(firstEnrolment.isEnrolmentActive()).thenReturn(false); // Mark the first enrolment as no longer active after the first removal

        // Act - Remove the second course edition enrolment
        boolean secondRemoveResult = service.removeCourseEditionEnrolment(studentID, secondCourseEditionID);

        // Assert: Check that both removals are successful (return true)
        assertTrue(firstRemoveResult);
        assertTrue(secondRemoveResult);

        // Verify that the save method of ceeRepository was called twice (once for each course removal)
        verify(ceeRepository, times(2)).save(any(CourseEditionEnrolment.class));

        // Verify that ceeRepository.findByStudentAndEdition was called once for each course edition
        verify(ceeRepository, times(1)).findByStudentAndEdition(eq(studentID), eq(firstCourseEditionID));
        verify(ceeRepository, times(1)).findByStudentAndEdition(eq(studentID), eq(secondCourseEditionID));

        // Verify that the save method for ProgrammeEditionEnrolment was called to deactivate the programme enrolment for both courses
        verify(peeRepository, times(2)).save(any(ProgrammeEditionEnrolment.class));
    }

    @Test
    void shouldCorrectlyHandleMixedSequenceOfActiveAndInactiveRemovals() throws Exception {
        // Arrange
        ICourseEditionEnrolmentRepository ceeRepository = mock(ICourseEditionEnrolmentRepository.class);
        IProgrammeEditionEnrolmentRepository peeRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseEditionEnrolmentFactory factory = mock(ICourseEditionEnrolmentFactory.class);

        CourseEditionEnrolment enrolment1 = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolment enrolment2 = mock(CourseEditionEnrolment.class);

        StudentID studentID = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        CourseEditionID courseEditionID2 = mock(CourseEditionID.class);

        when(ceeRepository.findByStudentAndEdition(studentID, courseEditionID1)).thenReturn(Optional.of(enrolment1));
        when(ceeRepository.findByStudentAndEdition(studentID, courseEditionID2)).thenReturn(Optional.of(enrolment2));

        when(enrolment1.isEnrolmentActive()).thenReturn(true);
        when(enrolment2.isEnrolmentActive()).thenReturn(false); // Inactive

        CourseEditionEnrolmentServiceImpl service = new CourseEditionEnrolmentServiceImpl(
                factory, ceeRepository, peeRepository, courseEditionRepository);

        // Act - Mixed removal
        boolean result1 = service.removeCourseEditionEnrolment(studentID, courseEditionID1); // Active
        boolean result2 = service.removeCourseEditionEnrolment(studentID, courseEditionID2); // Inactive

        // Assert
        assertTrue(result1);
        assertFalse(result2);
    }

// ==============================
// Multiple Removals for Several Students
// ==============================

    @Test
    void shouldRemoveMultipleStudentsFromCourseEditionWithoutAffectingProgrammeEdition() throws Exception {
        // Arrange
        ICourseEditionEnrolmentRepository ceeRepository = mock(ICourseEditionEnrolmentRepository.class);
        IProgrammeEditionEnrolmentRepository peeRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseEditionEnrolmentFactory factory = mock(ICourseEditionEnrolmentFactory.class);

        CourseEditionEnrolment enrolment1 = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolment enrolment2 = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolment enrolment3 = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolment enrolment4 = mock(CourseEditionEnrolment.class);
        StudentID studentID1 = mock(StudentID.class);
        StudentID studentID2 = mock(StudentID.class);

        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        CourseEditionID courseEditionID2 = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        // Simulate the repository behavior for enrolments
        when(ceeRepository.findByStudentAndEdition(studentID1, courseEditionID1)).thenReturn(Optional.of(enrolment1));
        when(ceeRepository.findByStudentAndEdition(studentID2, courseEditionID1)).thenReturn(Optional.of(enrolment3));

        when(ceeRepository.findByStudentAndEdition(studentID1, courseEditionID2)).thenReturn(Optional.of(enrolment2));
        when(ceeRepository.findByStudentAndEdition(studentID2, courseEditionID2)).thenReturn(Optional.of(enrolment4));

        // Simulate active enrolments
        when(enrolment1.isEnrolmentActive()).thenReturn(true);
        when(enrolment2.isEnrolmentActive()).thenReturn(true);
        when(enrolment3.isEnrolmentActive()).thenReturn(true);
        when(enrolment4.isEnrolmentActive()).thenReturn(true);

        // Mock ProgrammeEditionEnrolment behavior to ensure no effect on the programme enrolment
        ProgrammeEditionEnrolment programmeEnrolment = mock(ProgrammeEditionEnrolment.class);
        when(peeRepository.findByStudentAndProgrammeEdition(studentID1, programmeEditionID)).thenReturn(Optional.of(programmeEnrolment));
        when(peeRepository.findByStudentAndProgrammeEdition(studentID2, programmeEditionID)).thenReturn(Optional.of(programmeEnrolment));

        // Create the service instance
        CourseEditionEnrolmentServiceImpl service = new CourseEditionEnrolmentServiceImpl(
                factory, ceeRepository, peeRepository, courseEditionRepository);

        // Act - Remove the first course edition enrolment
        boolean result1 = service.removeCourseEditionEnrolment(studentID1, courseEditionID1);

        // Assert that the first course edition removal returns true (successful)
        assertTrue(result1, "First removal should return true");

        // Act - Remove the second course edition enrolment
        boolean result2 = service.removeCourseEditionEnrolment(studentID2, courseEditionID1);

        // Assert that the second course edition removal returns true (successful)
        assertTrue(result2, "Second removal should return true");

        // Verify interactions with the repositories
        verify(ceeRepository, times(1)).findByStudentAndEdition(studentID1, courseEditionID1);
        verify(ceeRepository, times(1)).findByStudentAndEdition(studentID2, courseEditionID1);
        verify(peeRepository, times(0)).save(programmeEnrolment); // No need to save ProgrammeEditionEnrolment, it shouldn't be affected
    }




}
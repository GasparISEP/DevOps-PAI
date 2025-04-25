package PAI.service;

import PAI.VOs.CourseEditionID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.StudentID;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;

import PAI.domain.courseEditionEnrolment.ICourseEditionEnrolmentFactory;
import PAI.domain.courseEditionEnrolment.ICourseEditionEnrolmentRepository;
import PAI.repository.ICourseEditionRepository;
import PAI.repository.IProgrammeEditionEnrolmentRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

        when(doubleCeeRepositoryInterface.enrolStudentInACourseEdition(doubleCEE)).thenReturn(true);

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

        when(doubleCeeRepositoryInterface.enrolStudentInACourseEdition(doubleCEE1)).thenReturn(true);
        service.enrolStudentInACourseEdition(doubleStudentID1, doubleCEID);

        when(doubleCeeRepositoryInterface.enrolStudentInACourseEdition(doubleCEE2)).thenReturn(true);

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

        when(doubleCeeRepositoryInterface.enrolStudentInACourseEdition(doubleCEE1)).thenReturn(true);
        service.enrolStudentInACourseEdition(doubleStudentID1, doubleCEID1);

        when(doubleCeeRepositoryInterface.enrolStudentInACourseEdition(doubleCEE2)).thenReturn(true);

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

        when(doubleCeeRepositoryInterface.enrolStudentInACourseEdition(doubleCEE1)).thenReturn(true);
        service.enrolStudentInACourseEdition(doubleStudentID1, doubleCEID1);

        when(doubleCeeRepositoryInterface.enrolStudentInACourseEdition(doubleCEE2)).thenReturn(true);

        //act
        boolean result = service.enrolStudentInACourseEdition(doubleStudentID1, doubleCEID2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenStudentIsAlreadyEnrolledInCourseEdition() throws Exception {
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


        when(doubleCeeRepositoryInterface.enrolStudentInACourseEdition(doubleCEE)).thenReturn(false);

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
}
package PAI.service;

import PAI.VOs.*;
import PAI.domain.ProgrammeEditionEnrolment;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.domain.courseEditionEnrolment.ICourseEditionEnrolmentRepository;
import PAI.domain.programmeEdition.IProgrammeEditionFactory;
import PAI.factory.IProgrammeEditionEnrolmentFactory;
import PAI.repository.*;
import PAI.repository.programmeEditionRepository.IProgrammeEditionRepository;
import PAI.repository.programmeRepository.IProgrammeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

class ProgrammeEditionEnrolmentServiceImplTest {


    @Test
    void shouldReturnFalseWhenStudentIsNull() {
        // Arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeRepository doubleProgrammeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionRepository doubleProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository doubleCourseEditionRepository = mock(ICourseEditionRepository.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        IProgrammeEditionEnrolmentFactory doubleProgrammeEditionEnrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);

        ProgrammeEditionEnrolmentServiceImpl service = new ProgrammeEditionEnrolmentServiceImpl(
                doubleProgrammeEditionEnrolmentRepository,
                doubleProgrammeEditionRepository,
                doubleCourseEditionEnrolmentRepository,
                doubleCourseEditionRepository,
                doubleSchoolYearRepository,
                doubleProgrammeEnrolmentRepository,
                doubleProgrammeRepository,
                doubleProgrammeEditionEnrolmentFactory);

        ProgrammeEditionID peId1 = mock(ProgrammeEditionID.class);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.enrolStudentInProgrammeEdition(null, peId1);
        });
        assertEquals("ProgrammeEdition and Student cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnFalseWhenProgrammeEditionIsNull() {
        // Arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeRepository doubleProgrammeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionRepository doubleProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository doubleCourseEditionRepository = mock(ICourseEditionRepository.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        IProgrammeEditionEnrolmentFactory doubleProgrammeEditionEnrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);

        ProgrammeEditionEnrolmentServiceImpl service = new ProgrammeEditionEnrolmentServiceImpl(
                doubleProgrammeEditionEnrolmentRepository,
                doubleProgrammeEditionRepository,
                doubleCourseEditionEnrolmentRepository,
                doubleCourseEditionRepository,
                doubleSchoolYearRepository,
                doubleProgrammeEnrolmentRepository,
                doubleProgrammeRepository,
                doubleProgrammeEditionEnrolmentFactory);

        StudentID stId1 = mock(StudentID.class);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.enrolStudentInProgrammeEdition(stId1, null);
        });

        assertEquals("ProgrammeEdition and Student cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnAnExceptionWhenProgrammeEditionEnrolmentAlreadyExists() throws Exception {
        // Arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeRepository doubleProgrammeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionRepository doubleProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository doubleCourseEditionRepository = mock(ICourseEditionRepository.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        IProgrammeEditionEnrolmentFactory doubleProgrammeEditionEnrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);

        ProgrammeEditionEnrolmentServiceImpl service = new ProgrammeEditionEnrolmentServiceImpl(
                doubleProgrammeEditionEnrolmentRepository,
                doubleProgrammeEditionRepository,
                doubleCourseEditionEnrolmentRepository,
                doubleCourseEditionRepository,
                doubleSchoolYearRepository,
                doubleProgrammeEnrolmentRepository,
                doubleProgrammeRepository,
                doubleProgrammeEditionEnrolmentFactory);

        StudentID stId1 = mock(StudentID.class);
        ProgrammeEditionID peId1 = mock(ProgrammeEditionID.class);

        when(doubleProgrammeEditionEnrolmentRepository.isStudentEnrolledInThisProgrammeEdition(stId1, peId1))
                .thenReturn(true);

        // Act + Assert
        assertThrows(IllegalStateException.class, () -> {
            service.enrolStudentInProgrammeEdition(stId1, peId1);
        });

    }

    @Test
    void shouldReturnAValidProgrammeEditionEnrolment() throws Exception {
        // Arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeRepository doubleProgrammeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionRepository doubleProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository doubleCourseEditionRepository = mock(ICourseEditionRepository.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        IProgrammeEditionEnrolmentFactory doubleProgrammeEditionEnrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);

        ProgrammeEditionEnrolmentServiceImpl service = new ProgrammeEditionEnrolmentServiceImpl(
                doubleProgrammeEditionEnrolmentRepository,
                doubleProgrammeEditionRepository,
                doubleCourseEditionEnrolmentRepository,
                doubleCourseEditionRepository,
                doubleSchoolYearRepository,
                doubleProgrammeEnrolmentRepository,
                doubleProgrammeRepository,
                doubleProgrammeEditionEnrolmentFactory);

        StudentID stId1 = mock(StudentID.class);
        ProgrammeEditionID peId1 = mock(ProgrammeEditionID.class);


        ProgrammeEditionEnrolment enrolMock = mock(ProgrammeEditionEnrolment.class);
        when(doubleProgrammeEditionEnrolmentFactory.newProgrammeEditionEnrolment(stId1, peId1)).thenReturn(enrolMock);

        //act
        boolean result = service.enrolStudentInProgrammeEdition(stId1, peId1);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnATwoValidProgrammeEditionEnrollments() throws Exception {
        //arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeRepository doubleProgrammeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionRepository doubleProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository doubleCourseEditionRepository = mock(ICourseEditionRepository.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        IProgrammeEditionEnrolmentFactory doubleProgrammeEditionEnrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);

        ProgrammeEditionEnrolmentServiceImpl service = new ProgrammeEditionEnrolmentServiceImpl(
                doubleProgrammeEditionEnrolmentRepository,
                doubleProgrammeEditionRepository,
                doubleCourseEditionEnrolmentRepository,
                doubleCourseEditionRepository,
                doubleSchoolYearRepository,
                doubleProgrammeEnrolmentRepository,
                doubleProgrammeRepository,
                doubleProgrammeEditionEnrolmentFactory);
        StudentID stId1 = mock(StudentID.class);
        StudentID stId2 = mock(StudentID.class);

        ProgrammeEditionID peId1 = mock(ProgrammeEditionID.class);
        ProgrammeEditionID peId2 = mock(ProgrammeEditionID.class);



        ProgrammeEditionEnrolment enrolMock1 = mock(ProgrammeEditionEnrolment.class);
        ProgrammeEditionEnrolment enrolMock2 = mock(ProgrammeEditionEnrolment.class);
        when(doubleProgrammeEditionEnrolmentFactory.newProgrammeEditionEnrolment(stId1, peId1)).thenReturn(enrolMock1);
        when(doubleProgrammeEditionEnrolmentFactory.newProgrammeEditionEnrolment(stId2, peId2)).thenReturn(enrolMock2);
        //act
        boolean result1 = service.enrolStudentInProgrammeEdition(stId1, peId2);
        boolean result2 = service.enrolStudentInProgrammeEdition(stId2, peId2);

        //assert
        assertTrue(result1);
        assertTrue(result2);

    }

    @Test
    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_Success() throws Exception{
        // Arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ProgrammeID doubleProgrammeId = mock(ProgrammeID.class);
        SchoolYearID doubleSchoolYearId = mock(SchoolYearID.class);
        StudentID doubleStudentId = mock(StudentID.class);
        ProgrammeEditionID doubleProgrammeEditionId = mock(ProgrammeEditionID.class);
        IProgrammeRepository doubleProgrammeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionRepository doubleProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository doubleCourseEditionRepository = mock(ICourseEditionRepository.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        IProgrammeEditionEnrolmentFactory doubleProgrammeEditionEnrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);

        ProgrammeEditionEnrolmentServiceImpl service = new ProgrammeEditionEnrolmentServiceImpl(
                doubleProgrammeEditionEnrolmentRepository,
                doubleProgrammeEditionRepository,
                doubleCourseEditionEnrolmentRepository,
                doubleCourseEditionRepository,
                doubleSchoolYearRepository,
                doubleProgrammeEnrolmentRepository,
                doubleProgrammeRepository,
                doubleProgrammeEditionEnrolmentFactory);

        CourseEditionID doubleCe1Id = mock(CourseEditionID.class);
        CourseEditionID doubleCe2Id = mock(CourseEditionID.class);

        when(doubleProgrammeEnrolmentRepository.isStudentEnrolled(doubleStudentId, doubleProgrammeId))
                .thenReturn(true);

        when(doubleProgrammeEditionRepository.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(doubleProgrammeId, doubleSchoolYearId))
                .thenReturn(Optional.of(doubleProgrammeEditionId));

        when(doubleCourseEditionRepository.findCourseEditionsByProgrammeEditionID(doubleProgrammeEditionId)).thenReturn(List.of(doubleCe1Id, doubleCe2Id));

        when(doubleProgrammeEditionEnrolmentRepository.isStudentEnrolledInThisProgrammeEdition(doubleStudentId, doubleProgrammeEditionId)).thenReturn(false);

        when(doubleCourseEditionEnrolmentRepository.isStudentEnrolledInCourseEdition(doubleStudentId, doubleCe1Id)).thenReturn(true);

        when(doubleCourseEditionEnrolmentRepository.isStudentEnrolledInCourseEdition(doubleStudentId, doubleCe2Id)).thenReturn(true);

        // Act
        boolean result = service.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(doubleStudentId, doubleProgrammeId, doubleSchoolYearId);
        boolean result2 = doubleCourseEditionEnrolmentRepository.isStudentEnrolledInCourseEdition(doubleStudentId, doubleCe1Id);
        boolean result3 = doubleCourseEditionEnrolmentRepository.isStudentEnrolledInCourseEdition(doubleStudentId, doubleCe2Id);

        // Assert
        assertTrue(result, "Student should be enrolled in Programme.");
        assertTrue(result2, "The Student should be enrolled in CourseEdition 1.");
        assertTrue(result3, "The Student should be enrolled in CourseEdition 2.");
    }

    @Test
    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_StudentNotEnrolledInProgramme() throws Exception {
        // arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ProgrammeID doubleProgrammeId = mock(ProgrammeID.class);
        SchoolYearID doubleSchoolYearId = mock(SchoolYearID.class);
        StudentID doubleStudentId = mock(StudentID.class);
        ProgrammeEditionID doubleProgrammeEditionId = mock(ProgrammeEditionID.class);
        IProgrammeRepository doubleProgrammeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionRepository doubleProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository doubleCourseEditionRepository = mock(ICourseEditionRepository.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        IProgrammeEditionFactory doubleProgrammeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionEnrolmentFactory doubleProgrammeEditionEnrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);

        ProgrammeEditionEnrolmentServiceImpl service = new ProgrammeEditionEnrolmentServiceImpl(
                doubleProgrammeEditionEnrolmentRepository,
                doubleProgrammeEditionRepository,
                doubleCourseEditionEnrolmentRepository,
                doubleCourseEditionRepository,
                doubleSchoolYearRepository,
                doubleProgrammeEnrolmentRepository,
                doubleProgrammeRepository,
                doubleProgrammeEditionEnrolmentFactory
        );

        doubleProgrammeEditionFactory.createProgrammeEdition(doubleProgrammeId, doubleSchoolYearId);

        // act
        boolean result = service.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(doubleStudentId, doubleProgrammeId, doubleSchoolYearId);

        //assert
        assertFalse(result);
    }

    @Test
    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_ProgrammeEditionNotFound() throws Exception {
        // arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ProgrammeID doubleProgrammeId = mock(ProgrammeID.class);
        SchoolYearID doubleSchoolYearId = mock(SchoolYearID.class);
        StudentID doubleStudentId = mock(StudentID.class);
        IProgrammeRepository doubleProgrammeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionRepository doubleProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository doubleCourseEditionRepository = mock(ICourseEditionRepository.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        IProgrammeEditionEnrolmentFactory doubleProgrammeEditionEnrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);

        ProgrammeEditionEnrolmentServiceImpl service = new ProgrammeEditionEnrolmentServiceImpl(
                doubleProgrammeEditionEnrolmentRepository,
                doubleProgrammeEditionRepository,
                doubleCourseEditionEnrolmentRepository,
                doubleCourseEditionRepository,
                doubleSchoolYearRepository,
                doubleProgrammeEnrolmentRepository,
                doubleProgrammeRepository,
                doubleProgrammeEditionEnrolmentFactory
        );

        when(doubleProgrammeEnrolmentRepository.isStudentEnrolled(doubleStudentId, doubleProgrammeId))
                .thenReturn(true);

        // act

        boolean result = service.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(doubleStudentId, doubleProgrammeId, doubleSchoolYearId);

        //assert
        assertFalse(result);
    }

    @Test
    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_StudentAlreadyEnrolledInProgrammeEdition() throws Exception{
        // arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ProgrammeID doubleProgrammeId = mock(ProgrammeID.class);
        SchoolYearID doubleSchoolYearId = mock(SchoolYearID.class);
        StudentID doubleStudentId = mock(StudentID.class);
        ProgrammeEditionID doubleProgrammeEditionId = mock(ProgrammeEditionID.class);
        IProgrammeRepository doubleProgrammeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionRepository doubleProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository doubleCourseEditionRepository = mock(ICourseEditionRepository.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        IProgrammeEditionEnrolmentFactory doubleProgrammeEditionEnrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);

        ProgrammeEditionEnrolmentServiceImpl service = new ProgrammeEditionEnrolmentServiceImpl(
                doubleProgrammeEditionEnrolmentRepository,
                doubleProgrammeEditionRepository,
                doubleCourseEditionEnrolmentRepository,
                doubleCourseEditionRepository,
                doubleSchoolYearRepository,
                doubleProgrammeEnrolmentRepository,
                doubleProgrammeRepository,
                doubleProgrammeEditionEnrolmentFactory
        );

        when(doubleProgrammeEnrolmentRepository.isStudentEnrolled(doubleStudentId, doubleProgrammeId)).thenReturn(true);
        when(doubleProgrammeEditionRepository.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(doubleProgrammeId, doubleSchoolYearId)).thenReturn(Optional.of(doubleProgrammeEditionId));

        when(doubleProgrammeEditionEnrolmentRepository.isStudentEnrolledInThisProgrammeEdition(doubleStudentId, doubleProgrammeEditionId)).thenReturn(true);
        // act
        boolean result = service.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(doubleStudentId, doubleProgrammeId, doubleSchoolYearId);

        //assert
        assertFalse(result);
    }

    @Test
    void testEnrollStudentInCourseEditionAndSetOfCoursesEditions_StudentAlreadyEnrolledInCourseEdition() throws Exception{
        // Arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ProgrammeID doubleProgrammeId = mock(ProgrammeID.class);
        SchoolYearID doubleSchoolYearId = mock(SchoolYearID.class);
        StudentID doubleStudentId = mock(StudentID.class);
        ProgrammeEditionID doubleProgrammeEditionId = mock(ProgrammeEditionID.class);
        IProgrammeRepository doubleProgrammeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionRepository doubleProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository doubleCourseEditionRepository = mock(ICourseEditionRepository.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        IProgrammeEditionEnrolmentFactory doubleProgrammeEditionEnrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);

        ProgrammeEditionEnrolmentServiceImpl service = new ProgrammeEditionEnrolmentServiceImpl(
                doubleProgrammeEditionEnrolmentRepository,
                doubleProgrammeEditionRepository,
                doubleCourseEditionEnrolmentRepository,
                doubleCourseEditionRepository,
                doubleSchoolYearRepository,
                doubleProgrammeEnrolmentRepository,
                doubleProgrammeRepository,
                doubleProgrammeEditionEnrolmentFactory
        );

        CourseEditionID ce1Id = mock(CourseEditionID.class);
        CourseEditionID ce2Id = mock(CourseEditionID.class);

        when(doubleProgrammeEnrolmentRepository.isStudentEnrolled(doubleStudentId, doubleProgrammeId))
                .thenReturn(true);


        when(doubleProgrammeEditionRepository.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(doubleProgrammeId, doubleSchoolYearId))
                .thenReturn(Optional.of(doubleProgrammeEditionId));


        when(doubleCourseEditionRepository.findCourseEditionsByProgrammeEditionID(doubleProgrammeEditionId))
                .thenReturn(List.of(ce1Id, ce2Id));


        when(doubleCourseEditionEnrolmentRepository.findByStudentAndEdition(doubleStudentId, ce1Id))
                .thenReturn(Optional.of(new CourseEditionEnrolment(doubleStudentId, ce1Id)));

        when(doubleCourseEditionEnrolmentRepository.findByStudentAndEdition(doubleStudentId, ce2Id))
                .thenReturn(Optional.of(new CourseEditionEnrolment(doubleStudentId, ce2Id)));


        doThrow(new IllegalStateException("This course edition enrolment is already in the list."))
                .when(doubleCourseEditionEnrolmentRepository)
                .enrolStudentInProgrammeCourseEditions(any(StudentID.class), anyList());

        // Act + Assert
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            service.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(doubleStudentId, doubleProgrammeId, doubleSchoolYearId);
        });

        assertEquals("This course edition enrolment is already in the list.", exception.getMessage());
    }

    @Test
    void testGetAllProgrammes() {
        // Arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeRepository doubleProgrammeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionRepository doubleProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository doubleCourseEditionRepository = mock(ICourseEditionRepository.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        IProgrammeEditionEnrolmentFactory doubleProgrammeEditionEnrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);

        ProgrammeEditionEnrolmentServiceImpl service = new ProgrammeEditionEnrolmentServiceImpl(
                doubleProgrammeEditionEnrolmentRepository,
                doubleProgrammeEditionRepository,
                doubleCourseEditionEnrolmentRepository,
                doubleCourseEditionRepository,
                doubleSchoolYearRepository,
                doubleProgrammeEnrolmentRepository,
                doubleProgrammeRepository,
                doubleProgrammeEditionEnrolmentFactory
        );

        ProgrammeID doubleProgramme1 = mock(ProgrammeID.class);
        ProgrammeID doubleProgramme2 = mock(ProgrammeID.class);

        when(doubleProgrammeRepository.getAllProgrammesIDs()).thenReturn(List.of(doubleProgramme1, doubleProgramme2));

        // Act
        List<ProgrammeID> doubleProgrammes = service.getAllProgrammesIDs();

        // Assert
        assertNotNull(doubleProgrammes, "The list of programmes should not be null.");
    }

    @Test
    void testGetAllProgrammes_SizeEqualsTwo()  {
        // Arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeRepository doubleProgrammeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionRepository doubleProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository doubleCourseEditionRepository = mock(ICourseEditionRepository.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        IProgrammeEditionEnrolmentFactory doubleProgrammeEditionEnrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);

        ProgrammeEditionEnrolmentServiceImpl service = new ProgrammeEditionEnrolmentServiceImpl(
                doubleProgrammeEditionEnrolmentRepository,
                doubleProgrammeEditionRepository,
                doubleCourseEditionEnrolmentRepository,
                doubleCourseEditionRepository,
                doubleSchoolYearRepository,
                doubleProgrammeEnrolmentRepository,
                doubleProgrammeRepository,
                doubleProgrammeEditionEnrolmentFactory
        );

        ProgrammeID doubleProgramme1 = mock(ProgrammeID.class);
        ProgrammeID doubleProgramme2 = mock(ProgrammeID.class);

        when(doubleProgrammeRepository.getAllProgrammesIDs()).thenReturn(List.of(doubleProgramme1, doubleProgramme2));

        // Act
        List<ProgrammeID> doubleProgrammes = service.getAllProgrammesIDs();

        // Assert
        assertEquals(2, doubleProgrammes.size(), "The list of programmes should contain exactly 2 programmes.");
    }

    @Test
    void testGetAllProgrammes_ContainsAllProgrammes() {
        // Arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeRepository doubleProgrammeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionRepository doubleProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository doubleCourseEditionRepository = mock(ICourseEditionRepository.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        IProgrammeEditionEnrolmentFactory doubleProgrammeEditionEnrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);

        ProgrammeEditionEnrolmentServiceImpl service = new ProgrammeEditionEnrolmentServiceImpl(
                doubleProgrammeEditionEnrolmentRepository,
                doubleProgrammeEditionRepository,
                doubleCourseEditionEnrolmentRepository,
                doubleCourseEditionRepository,
                doubleSchoolYearRepository,
                doubleProgrammeEnrolmentRepository,
                doubleProgrammeRepository,
                doubleProgrammeEditionEnrolmentFactory
        );

        ProgrammeID doubleProgramme1 = mock(ProgrammeID.class);
        ProgrammeID doubleProgramme2 = mock(ProgrammeID.class);

        when(doubleProgrammeRepository.getAllProgrammesIDs()).thenReturn(List.of(doubleProgramme1, doubleProgramme2));

        // Act
        List<ProgrammeID> doubleProgrammes = service.getAllProgrammesIDs();

        // Assert
        assertTrue(doubleProgrammes.contains(doubleProgramme1));
        assertTrue(doubleProgrammes.contains(doubleProgramme2));

    }

    @Test
    void testGetAllSchoolYears_NotNull() {
        // Arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeRepository doubleProgrammeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionRepository doubleProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository doubleCourseEditionRepository = mock(ICourseEditionRepository.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        IProgrammeEditionEnrolmentFactory doubleProgrammeEditionEnrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);

        ProgrammeEditionEnrolmentServiceImpl service = new ProgrammeEditionEnrolmentServiceImpl(
                doubleProgrammeEditionEnrolmentRepository,
                doubleProgrammeEditionRepository,
                doubleCourseEditionEnrolmentRepository,
                doubleCourseEditionRepository,
                doubleSchoolYearRepository,
                doubleProgrammeEnrolmentRepository,
                doubleProgrammeRepository,
                doubleProgrammeEditionEnrolmentFactory
        );

        SchoolYearID doubleSchoolYear1 = mock(SchoolYearID.class);
        SchoolYearID doubleSchoolYear2 = mock(SchoolYearID.class);

        when(doubleSchoolYearRepository.getAllSchoolYearsIDs()).thenReturn(List.of(doubleSchoolYear1, doubleSchoolYear2));

        // Act
        List<SchoolYearID> doubleSchoolYears = service.getAllSchoolYearIDs();

        // Assert
        assertNotNull(doubleSchoolYears, "The list of school years should not be null.");
    }

    @Test
    void testGetAllSchoolYears_SizeEqualsTwo()  {
        // Arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeRepository doubleProgrammeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionRepository doubleProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository doubleCourseEditionRepository = mock(ICourseEditionRepository.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        IProgrammeEditionEnrolmentFactory doubleProgrammeEditionEnrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);

        ProgrammeEditionEnrolmentServiceImpl service = new ProgrammeEditionEnrolmentServiceImpl(
                doubleProgrammeEditionEnrolmentRepository,
                doubleProgrammeEditionRepository,
                doubleCourseEditionEnrolmentRepository,
                doubleCourseEditionRepository,
                doubleSchoolYearRepository,
                doubleProgrammeEnrolmentRepository,
                doubleProgrammeRepository,
                doubleProgrammeEditionEnrolmentFactory
        );

        SchoolYearID doubleSchoolYear1 = mock(SchoolYearID.class);
        SchoolYearID doubleSchoolYear2 = mock(SchoolYearID.class);

        when(doubleSchoolYearRepository.getAllSchoolYearsIDs()).thenReturn(List.of(doubleSchoolYear1, doubleSchoolYear2));

        // Act
        List<SchoolYearID> doubleSchoolYears = service.getAllSchoolYearIDs();

        // Assert
        assertEquals(2, doubleSchoolYears.size(), "The list of school years should contain exactly 2 years.");
    }

    @Test
    void testGetAllSchoolYears_ContainsAllSchoolYears()  {
        // Arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeRepository doubleProgrammeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionRepository doubleProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository doubleCourseEditionRepository = mock(ICourseEditionRepository.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        IProgrammeEditionEnrolmentFactory doubleProgrammeEditionEnrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);

        ProgrammeEditionEnrolmentServiceImpl service = new ProgrammeEditionEnrolmentServiceImpl(
                doubleProgrammeEditionEnrolmentRepository,
                doubleProgrammeEditionRepository,
                doubleCourseEditionEnrolmentRepository,
                doubleCourseEditionRepository,
                doubleSchoolYearRepository,
                doubleProgrammeEnrolmentRepository,
                doubleProgrammeRepository,
                doubleProgrammeEditionEnrolmentFactory
        );

        SchoolYearID doubleSchoolYear1 = mock(SchoolYearID.class);
        SchoolYearID doubleSchoolYear2 = mock(SchoolYearID.class);

        when(doubleSchoolYearRepository.getAllSchoolYearsIDs()).thenReturn(List.of(doubleSchoolYear1, doubleSchoolYear2));

        // Act
        List<SchoolYearID> doubleSchoolYears = service.getAllSchoolYearIDs();

        // Assert
        assertTrue(doubleSchoolYears.contains(doubleSchoolYear1), "The list should contain doubleSchoolYear1.");
        assertTrue(doubleSchoolYears.contains(doubleSchoolYear2), "The list should contain doubleSchoolYear2.");
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionEnrolmentRepositoryIsNull() throws Exception {
        // arrange
        IProgrammeEditionRepository doubleProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository doubleCourseEditionRepository = mock(ICourseEditionRepository.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        IProgrammeRepository doubleProgrammeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionEnrolmentFactory doubleProgrammeEditionEnrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEditionEnrolmentServiceImpl(
                    null,
                    doubleProgrammeEditionRepository,
                    doubleCourseEditionEnrolmentRepository,
                    doubleCourseEditionRepository,
                    doubleSchoolYearRepository,
                    doubleProgrammeEnrolmentRepository,
                    doubleProgrammeRepository,
                    doubleProgrammeEditionEnrolmentFactory
            );
        });
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionRepositoryIsNull() throws Exception {
        // arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository doubleCourseEditionRepository = mock(ICourseEditionRepository.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        IProgrammeRepository doubleProgrammeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionEnrolmentFactory doubleProgrammeEditionEnrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEditionEnrolmentServiceImpl(
                    doubleProgrammeEditionEnrolmentRepository,
                    null,
                    doubleCourseEditionEnrolmentRepository,
                    doubleCourseEditionRepository,
                    doubleSchoolYearRepository,
                    doubleProgrammeEnrolmentRepository,
                    doubleProgrammeRepository,
                    doubleProgrammeEditionEnrolmentFactory
            );
        });
    }

    @Test
    void shouldThrowExceptionWhenCourseEditionEnrolmentRepositoryIsNull() throws Exception {
        // arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionRepository doubleProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        ICourseEditionRepository doubleCourseEditionRepository = mock(ICourseEditionRepository.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        IProgrammeRepository doubleProgrammeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionEnrolmentFactory doubleProgrammeEditionEnrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEditionEnrolmentServiceImpl(
                    doubleProgrammeEditionEnrolmentRepository,
                    doubleProgrammeEditionRepository,
                    null,
                    doubleCourseEditionRepository,
                    doubleSchoolYearRepository,
                    doubleProgrammeEnrolmentRepository,
                    doubleProgrammeRepository,
                    doubleProgrammeEditionEnrolmentFactory
            );
        });
    }

    @Test
    void shouldThrowExceptionWhenCourseEditionRepositoryIsNull() throws Exception {
        // arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionRepository doubleProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        IProgrammeRepository doubleProgrammeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionEnrolmentFactory doubleProgrammeEditionEnrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEditionEnrolmentServiceImpl(
                    doubleProgrammeEditionEnrolmentRepository,
                    doubleProgrammeEditionRepository,
                    doubleCourseEditionEnrolmentRepository,
                    null,
                    doubleSchoolYearRepository,
                    doubleProgrammeEnrolmentRepository,
                    doubleProgrammeRepository,
                    doubleProgrammeEditionEnrolmentFactory
            );
        });
    }

    @Test
    void shouldThrowExceptionWhenSchoolYearRepositoryIsNull() throws Exception {
        // arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionRepository doubleProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository doubleCourseEditionRepository = mock(ICourseEditionRepository.class);
        IProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        IProgrammeRepository doubleProgrammeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionEnrolmentFactory doubleProgrammeEditionEnrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEditionEnrolmentServiceImpl(
                    doubleProgrammeEditionEnrolmentRepository,
                    doubleProgrammeEditionRepository,
                    doubleCourseEditionEnrolmentRepository,
                    doubleCourseEditionRepository,
                    null,
                    doubleProgrammeEnrolmentRepository,
                    doubleProgrammeRepository,
                    doubleProgrammeEditionEnrolmentFactory
            );
        });
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEnrolmentRepositoryIsNull() throws Exception {
        // arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionRepository doubleProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository doubleCourseEditionRepository = mock(ICourseEditionRepository.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeRepository doubleProgrammeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionEnrolmentFactory doubleProgrammeEditionEnrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEditionEnrolmentServiceImpl(
                    doubleProgrammeEditionEnrolmentRepository,
                    doubleProgrammeEditionRepository,
                    doubleCourseEditionEnrolmentRepository,
                    doubleCourseEditionRepository,
                    doubleSchoolYearRepository,
                    null,
                    doubleProgrammeRepository,
                    doubleProgrammeEditionEnrolmentFactory
            );
        });
    }

    @Test
    void shouldThrowExceptionWhenProgrammeRepositoryIsNull() throws Exception {
        // arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionRepository doubleProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository doubleCourseEditionRepository = mock(ICourseEditionRepository.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        IProgrammeEditionEnrolmentFactory doubleProgrammeEditionEnrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEditionEnrolmentServiceImpl(
                    doubleProgrammeEditionEnrolmentRepository,
                    doubleProgrammeEditionRepository,
                    doubleCourseEditionEnrolmentRepository,
                    doubleCourseEditionRepository,
                    doubleSchoolYearRepository,
                    doubleProgrammeEnrolmentRepository,
                    null,
                    doubleProgrammeEditionEnrolmentFactory
            );
        });
    }

    @Test
    void shouldThrowExceptionWhenFactoryIsNull() throws Exception {
        // arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionRepository doubleProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository doubleCourseEditionRepository = mock(ICourseEditionRepository.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        IProgrammeRepository doubleProgrammeRepository = mock(IProgrammeRepository.class);


        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEditionEnrolmentServiceImpl(
                    doubleProgrammeEditionEnrolmentRepository,
                    doubleProgrammeEditionRepository,
                    doubleCourseEditionEnrolmentRepository,
                    doubleCourseEditionRepository,
                    doubleSchoolYearRepository,
                    doubleProgrammeEnrolmentRepository,
                    doubleProgrammeRepository,
                    null
            );
        });
    }

    @Test
    void shouldGetTotalOfProgrammeEditionEnrolmentsInSpecificProgrammeEdition() throws Exception {
        // Arrange
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        IProgrammeEditionEnrolmentRepository mockProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionRepository mockProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        ICourseEditionEnrolmentRepository mockCourseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository mockCourseEditionRepository = mock(ICourseEditionRepository.class);
        ISchoolYearRepository mockSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository mockProgrammeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        IProgrammeRepository mockProgrammeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionEnrolmentFactory mockProgrammeEditionEnrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);

        ProgrammeEditionEnrolmentServiceImpl programmeEditionEnrolmentService = new ProgrammeEditionEnrolmentServiceImpl(
                mockProgrammeEditionEnrolmentRepository,
                mockProgrammeEditionRepository,
                mockCourseEditionEnrolmentRepository,
                mockCourseEditionRepository,
                mockSchoolYearRepository,
                mockProgrammeEnrolmentRepository,
                mockProgrammeRepository,
                mockProgrammeEditionEnrolmentFactory
        );
        ProgrammeEditionEnrolment programmeEditionEnrolment = mock(ProgrammeEditionEnrolment.class);
        List<ProgrammeEditionEnrolment> programmeEditionEnrolments = List.of(programmeEditionEnrolment);
        when(mockProgrammeEditionEnrolmentRepository.getAllProgrammeEditionsEnrollmentByProgrammeEditionID(programmeEditionID)).thenReturn(programmeEditionEnrolments);
        // Act
        int result = programmeEditionEnrolmentService.totalStudentsInProgrammeEdition(programmeEditionID);

        // Assert
        assertEquals(1, result);
    }

    @Test
    void shouldReturnZeroIfProgrammeEditionIdNotFoundInAnyProgrammeEditionEnrollment() throws Exception {
        // Arrange
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        IProgrammeEditionEnrolmentRepository mockProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionRepository mockProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        ICourseEditionEnrolmentRepository mockCourseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository mockCourseEditionRepository = mock(ICourseEditionRepository.class);
        ISchoolYearRepository mockSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository mockProgrammeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        IProgrammeRepository mockProgrammeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionEnrolmentFactory mockProgrammeEditionEnrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);

        ProgrammeEditionEnrolmentServiceImpl programmeEditionEnrolmentService = new ProgrammeEditionEnrolmentServiceImpl(
                mockProgrammeEditionEnrolmentRepository,
                mockProgrammeEditionRepository,
                mockCourseEditionEnrolmentRepository,
                mockCourseEditionRepository,
                mockSchoolYearRepository,
                mockProgrammeEnrolmentRepository,
                mockProgrammeRepository,
                mockProgrammeEditionEnrolmentFactory
        );
        List<ProgrammeEditionEnrolment> programmeEditionEnrolments = List.of();
        when(mockProgrammeEditionEnrolmentRepository.getAllProgrammeEditionsEnrollmentByProgrammeEditionID(programmeEditionID)).thenReturn(programmeEditionEnrolments);
        // Act
        int result = programmeEditionEnrolmentService.totalStudentsInProgrammeEdition(programmeEditionID);

        // Assert
        assertEquals(0, result);
    }

}
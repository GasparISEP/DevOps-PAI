package PAI.service;

import PAI.VOs.*;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.domain.courseEditionEnrolment.ICourseEditionEnrolmentRepository;
import PAI.domain.programmeEdition.IProgrammeEditionFactory;
import PAI.repository.*;
import PAI.repository.programmeEditionRepository.IProgrammeEditionRepository;
import PAI.repository.programmeRepository.IProgrammeRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

class ProgrammeEditionEnrolmentServiceImplTest {

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

        ProgrammeEditionEnrolmentServiceImpl service = new ProgrammeEditionEnrolmentServiceImpl(
                doubleProgrammeEditionEnrolmentRepository,
                doubleProgrammeEditionRepository,
                doubleCourseEditionEnrolmentRepository,
                doubleCourseEditionRepository,
                doubleSchoolYearRepository,
                doubleProgrammeEnrolmentRepository,
                doubleProgrammeRepository);

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

        ProgrammeEditionEnrolmentServiceImpl service = new ProgrammeEditionEnrolmentServiceImpl(
                doubleProgrammeEditionEnrolmentRepository,
                doubleProgrammeEditionRepository,
                doubleCourseEditionEnrolmentRepository,
                doubleCourseEditionRepository,
                doubleSchoolYearRepository,
                doubleProgrammeEnrolmentRepository,
                doubleProgrammeRepository);

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
        ProgrammeEditionID doubleProgrammeEditionId = mock(ProgrammeEditionID.class);
        IProgrammeRepository doubleProgrammeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionRepository doubleProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository doubleCourseEditionRepository = mock(ICourseEditionRepository.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);

        ProgrammeEditionEnrolmentServiceImpl service = new ProgrammeEditionEnrolmentServiceImpl(
                doubleProgrammeEditionEnrolmentRepository,
                doubleProgrammeEditionRepository,
                doubleCourseEditionEnrolmentRepository,
                doubleCourseEditionRepository,
                doubleSchoolYearRepository,
                doubleProgrammeEnrolmentRepository,
                doubleProgrammeRepository);

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

        ProgrammeEditionEnrolmentServiceImpl service = new ProgrammeEditionEnrolmentServiceImpl(
                doubleProgrammeEditionEnrolmentRepository,
                doubleProgrammeEditionRepository,
                doubleCourseEditionEnrolmentRepository,
                doubleCourseEditionRepository,
                doubleSchoolYearRepository,
                doubleProgrammeEnrolmentRepository,
                doubleProgrammeRepository);

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

        ProgrammeEditionEnrolmentServiceImpl service = new ProgrammeEditionEnrolmentServiceImpl(
                doubleProgrammeEditionEnrolmentRepository,
                doubleProgrammeEditionRepository,
                doubleCourseEditionEnrolmentRepository,
                doubleCourseEditionRepository,
                doubleSchoolYearRepository,
                doubleProgrammeEnrolmentRepository,
                doubleProgrammeRepository);

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

        ProgrammeEditionEnrolmentServiceImpl service = new ProgrammeEditionEnrolmentServiceImpl(
                doubleProgrammeEditionEnrolmentRepository,
                doubleProgrammeEditionRepository,
                doubleCourseEditionEnrolmentRepository,
                doubleCourseEditionRepository,
                doubleSchoolYearRepository,
                doubleProgrammeEnrolmentRepository,
                doubleProgrammeRepository);

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

        ProgrammeEditionEnrolmentServiceImpl service = new ProgrammeEditionEnrolmentServiceImpl(
                doubleProgrammeEditionEnrolmentRepository,
                doubleProgrammeEditionRepository,
                doubleCourseEditionEnrolmentRepository,
                doubleCourseEditionRepository,
                doubleSchoolYearRepository,
                doubleProgrammeEnrolmentRepository,
                doubleProgrammeRepository);

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

        ProgrammeEditionEnrolmentServiceImpl service = new ProgrammeEditionEnrolmentServiceImpl(
                doubleProgrammeEditionEnrolmentRepository,
                doubleProgrammeEditionRepository,
                doubleCourseEditionEnrolmentRepository,
                doubleCourseEditionRepository,
                doubleSchoolYearRepository,
                doubleProgrammeEnrolmentRepository,
                doubleProgrammeRepository);

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

        ProgrammeEditionEnrolmentServiceImpl service = new ProgrammeEditionEnrolmentServiceImpl(
                doubleProgrammeEditionEnrolmentRepository,
                doubleProgrammeEditionRepository,
                doubleCourseEditionEnrolmentRepository,
                doubleCourseEditionRepository,
                doubleSchoolYearRepository,
                doubleProgrammeEnrolmentRepository,
                doubleProgrammeRepository);

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

        ProgrammeEditionEnrolmentServiceImpl service = new ProgrammeEditionEnrolmentServiceImpl(
                doubleProgrammeEditionEnrolmentRepository,
                doubleProgrammeEditionRepository,
                doubleCourseEditionEnrolmentRepository,
                doubleCourseEditionRepository,
                doubleSchoolYearRepository,
                doubleProgrammeEnrolmentRepository,
                doubleProgrammeRepository);

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

        ProgrammeEditionEnrolmentServiceImpl service = new ProgrammeEditionEnrolmentServiceImpl(
                doubleProgrammeEditionEnrolmentRepository,
                doubleProgrammeEditionRepository,
                doubleCourseEditionEnrolmentRepository,
                doubleCourseEditionRepository,
                doubleSchoolYearRepository,
                doubleProgrammeEnrolmentRepository,
                doubleProgrammeRepository);

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

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEditionEnrolmentServiceImpl(
                    null,
                    doubleProgrammeEditionRepository,
                    doubleCourseEditionEnrolmentRepository,
                    doubleCourseEditionRepository,
                    doubleSchoolYearRepository,
                    doubleProgrammeEnrolmentRepository,
                    doubleProgrammeRepository
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

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEditionEnrolmentServiceImpl(
                    doubleProgrammeEditionEnrolmentRepository,
                    null,
                    doubleCourseEditionEnrolmentRepository,
                    doubleCourseEditionRepository,
                    doubleSchoolYearRepository,
                    doubleProgrammeEnrolmentRepository,
                    doubleProgrammeRepository
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

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEditionEnrolmentServiceImpl(
                    doubleProgrammeEditionEnrolmentRepository,
                    doubleProgrammeEditionRepository,
                    null,
                    doubleCourseEditionRepository,
                    doubleSchoolYearRepository,
                    doubleProgrammeEnrolmentRepository,
                    doubleProgrammeRepository
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

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEditionEnrolmentServiceImpl(
                    doubleProgrammeEditionEnrolmentRepository,
                    doubleProgrammeEditionRepository,
                    doubleCourseEditionEnrolmentRepository,
                    null,
                    doubleSchoolYearRepository,
                    doubleProgrammeEnrolmentRepository,
                    doubleProgrammeRepository
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

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEditionEnrolmentServiceImpl(
                    doubleProgrammeEditionEnrolmentRepository,
                    doubleProgrammeEditionRepository,
                    doubleCourseEditionEnrolmentRepository,
                    doubleCourseEditionRepository,
                    null,
                    doubleProgrammeEnrolmentRepository,
                    doubleProgrammeRepository
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

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEditionEnrolmentServiceImpl(
                    doubleProgrammeEditionEnrolmentRepository,
                    doubleProgrammeEditionRepository,
                    doubleCourseEditionEnrolmentRepository,
                    doubleCourseEditionRepository,
                    doubleSchoolYearRepository,
                    null,
                    doubleProgrammeRepository
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

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEditionEnrolmentServiceImpl(
                    doubleProgrammeEditionEnrolmentRepository,
                    doubleProgrammeEditionRepository,
                    doubleCourseEditionEnrolmentRepository,
                    doubleCourseEditionRepository,
                    doubleSchoolYearRepository,
                    doubleProgrammeEnrolmentRepository,
                    null
            );
        });
    }

}
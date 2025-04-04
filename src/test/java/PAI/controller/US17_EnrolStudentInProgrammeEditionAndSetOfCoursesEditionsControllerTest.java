package PAI.controller;


import PAI.VOs.*;

import PAI.VOs.Location;
import PAI.domain.*;
import PAI.domain.accessMethod.AccessMethodFactoryImpl;
import PAI.domain.accessMethod.IAccessMethodFactory;
import PAI.domain.programme.IProgrammeDDDFactory;
import PAI.domain.programme.ProgrammeDDD;
import PAI.domain.programme.ProgrammeDDDFactoryImpl;
import PAI.domain.programmeEdition.IProgrammeEditionDDDFactory;
import PAI.domain.programmeEdition.ProgrammeEditionDDDFactoryImpl;
import PAI.factory.*;
import PAI.repository.*;
import PAI.repository.accessMethodRepository.AccessMethodListFactoryImpl;
import PAI.repository.accessMethodRepository.IAccessMethodListFactory;
import PAI.repository.programmeEditionRepository.IProgrammeEditionDDDListFactory;
import PAI.repository.programmeEditionRepository.IProgrammeEditionRepositoryDDD;
import PAI.repository.programmeEditionRepository.ProgrammeEditionDDDListFactoryImpl;
import PAI.repository.programmeEditionRepository.ProgrammeEditionRepositoryDDDImpl;
import PAI.repository.programmeRepo.IProgrammeDDDRepository;
import PAI.repository.programmeRepo.IProgrammeDDDRepositoryListFactory;
import PAI.repository.programmeRepo.ProgrammeDDDRepositoryImpl;
import PAI.repository.programmeRepo.ProgrammeDDDRepositoryListFactoryImpl;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

class US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsControllerTest {

    @Test
    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_Success() {
        // Arrange
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ProgrammeID doubleProgrammeId = mock(ProgrammeID.class);
        SchoolYearID doubleSchoolYearId = mock(SchoolYearID.class);
        StudentID doubleStudentId = mock(StudentID.class);
        ProgrammeEditionID doubleProgrammeEditionId = mock(ProgrammeEditionID.class);
        IProgrammeDDDRepository doubleProgrammeList = mock(IProgrammeDDDRepository.class);
        IProgrammeEditionRepositoryDDD doubleProgrammeEditionRepository = mock(IProgrammeEditionRepositoryDDD.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepositoryImpl = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepositoryDDD doubleCourseEditionRepository = mock(ICourseEditionRepositoryDDD.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);

        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrolmentRepository,
                        doubleProgrammeEditionRepository,
                        doubleProgrammeList,
                        doubleCourseEditionEnrolmentRepositoryImpl,
                        doubleCourseEditionRepository,
                        doubleSchoolYearRepository,
                        doubleProgrammeEnrolmentRepository);

        CourseEditionID doubleCe1Id = mock(CourseEditionID.class);
        CourseEditionID doubleCe2Id = mock(CourseEditionID.class);

        when(doubleProgrammeEnrolmentRepository.isStudentEnrolled(doubleStudentId, doubleProgrammeId))
                .thenReturn(true);

        when(doubleProgrammeEditionRepository.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(doubleProgrammeId, doubleSchoolYearId))
                .thenReturn(Optional.of(doubleProgrammeEditionId));

        when(doubleCourseEditionRepository.findCourseEditionsByProgrammeEdition(doubleProgrammeEditionId)).thenReturn(List.of(doubleCe1Id, doubleCe2Id));

        when(programmeEditionEnrolmentRepository.isStudentEnrolledInThisProgrammeEdition(doubleStudentId, doubleProgrammeEditionId)).thenReturn(false);

        when(doubleCourseEditionEnrolmentRepositoryImpl.isStudentEnrolledInCourseEdition(doubleStudentId, doubleCe1Id)).thenReturn(true);

        when(doubleCourseEditionEnrolmentRepositoryImpl.isStudentEnrolledInCourseEdition(doubleStudentId, doubleCe2Id)).thenReturn(true);

        // Act
        boolean result = controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(doubleStudentId, doubleProgrammeId, doubleSchoolYearId);
        boolean result2 = doubleCourseEditionEnrolmentRepositoryImpl.isStudentEnrolledInCourseEdition(doubleStudentId, doubleCe1Id);
        boolean result3 = doubleCourseEditionEnrolmentRepositoryImpl.isStudentEnrolledInCourseEdition(doubleStudentId, doubleCe2Id);

        // Assert
        assertTrue(result, "Student should be enrolled in Programme.");
        assertTrue(result2, "The Student should be enrolled in CourseEdition 1.");
        assertTrue(result3, "The Student should be enrolled in CourseEdition 2.");
    }

    @Test
    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_StudentNotEnrolledInProgramme() {
        // arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ProgrammeDDD doubleProgramme = mock(ProgrammeDDD.class);
        ProgrammeID doubleProgrammeId = mock(ProgrammeID.class);
        SchoolYear doubleSchoolYear = mock(SchoolYear.class);
        SchoolYearID doubleSchoolYearId = mock(SchoolYearID.class);
        StudentID doubleStudentId = mock(StudentID.class);
        IProgrammeDDDRepository doubleProgrammeList = mock(IProgrammeDDDRepository.class);
        IProgrammeEditionRepositoryDDD doubleProgrammeEditionRepository = mock(IProgrammeEditionRepositoryDDD.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepositoryImpl = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepositoryDDD doubleCourseEditionRepository = mock(ICourseEditionRepositoryDDD.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);

        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        doubleProgrammeEditionEnrolmentRepository,
                        doubleProgrammeEditionRepository,
                        doubleProgrammeList,
                        doubleCourseEditionEnrolmentRepositoryImpl,
                        doubleCourseEditionRepository,
                        doubleSchoolYearRepository,
                        doubleProgrammeEnrolmentRepository);

        doubleProgrammeEditionRepository.createProgrammeEdition(doubleProgrammeId, doubleSchoolYearId);

        // act
        boolean result = controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(doubleStudentId, doubleProgrammeId, doubleSchoolYearId);

        //assert
        assertFalse(result);
    }

    @Test
    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_ProgrammeEditionNotFound() {
        // arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ProgrammeID doubleProgrammeId = mock(ProgrammeID.class);
        SchoolYearID doubleSchoolYearId = mock(SchoolYearID.class);
        StudentID doubleStudentId = mock(StudentID.class);
        IProgrammeDDDRepository doubleProgrammeList = mock(IProgrammeDDDRepository.class);
        IProgrammeEditionRepositoryDDD doubleProgrammeEditionRepository = mock(IProgrammeEditionRepositoryDDD.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepositoryImpl = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepositoryDDD doubleCourseEditionRepository = mock(ICourseEditionRepositoryDDD.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);

        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        doubleProgrammeEditionEnrolmentRepository,
                        doubleProgrammeEditionRepository,
                        doubleProgrammeList,
                        doubleCourseEditionEnrolmentRepositoryImpl,
                        doubleCourseEditionRepository,
                        doubleSchoolYearRepository,
                        doubleProgrammeEnrolmentRepository);

        when(doubleProgrammeEnrolmentRepository.isStudentEnrolled(doubleStudentId, doubleProgrammeId))
                .thenReturn(true);

        // act

        boolean result = controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(doubleStudentId, doubleProgrammeId, doubleSchoolYearId);

        //assert
        assertFalse(result);
    }

    @Test
    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_StudentAlreadyEnrolledInProgrammeEdition() {
        // arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(ProgrammeEditionEnrolmentRepositoryImpl.class);
        ProgrammeID doubleProgrammeId = mock(ProgrammeID.class);
        SchoolYearID doubleSchoolYearId = mock(SchoolYearID.class);
        StudentID doubleStudentId = mock(StudentID.class);
        ProgrammeEditionID doubleProgrammeEditionId = mock(ProgrammeEditionID.class);
        IProgrammeDDDRepository doubleProgrammeList = mock(IProgrammeDDDRepository.class);
        IProgrammeEditionRepositoryDDD doubleProgrammeEditionRepository = mock(IProgrammeEditionRepositoryDDD.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepositoryImpl = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepositoryDDD doubleCourseEditionRepository = mock(ICourseEditionRepositoryDDD.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        doubleProgrammeEditionEnrolmentRepository,
                        doubleProgrammeEditionRepository,
                        doubleProgrammeList,
                        doubleCourseEditionEnrolmentRepositoryImpl,
                        doubleCourseEditionRepository,
                        doubleSchoolYearRepository,
                        doubleProgrammeEnrolmentRepository);

        when(doubleProgrammeEnrolmentRepository.isStudentEnrolled(doubleStudentId, doubleProgrammeId)).thenReturn(true);
        when(doubleProgrammeEditionRepository.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(doubleProgrammeId, doubleSchoolYearId)).thenReturn(Optional.of(doubleProgrammeEditionId));

        when(doubleProgrammeEditionEnrolmentRepository.isStudentEnrolledInThisProgrammeEdition(doubleStudentId, doubleProgrammeEditionId)).thenReturn(true);
        // act
        boolean result = controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(doubleStudentId, doubleProgrammeId, doubleSchoolYearId);

        //assert
        assertFalse(result);
    }

    @Test
    void testEnrollStudentInCourseEditionAndSetOfCoursesEditions_StudentAlreadyEnrolledInCourseEdition() {
        // Arrange
        ProgrammeEditionEnrolmentRepositoryImpl doubleProgrammeEditionEnrolmentRepository = mock(ProgrammeEditionEnrolmentRepositoryImpl.class);

        ProgrammeDDD doubleProgramme = mock(ProgrammeDDD.class);
        ProgrammeID doubleProgrammeId = mock(ProgrammeID.class);
        SchoolYear doubleSchoolYear = mock(SchoolYear.class);
        SchoolYearID doubleSchoolYearId = mock(SchoolYearID.class);
        Student doubleStudent = mock(Student.class);
        StudentID doubleStudentId = mock(StudentID.class);
        ProgrammeEdition doubleProgrammeEdition = mock(ProgrammeEdition.class);
        ProgrammeEditionID doubleProgrammeEditionId = mock(ProgrammeEditionID.class);
        IProgrammeDDDRepository doubleProgrammeList = mock(IProgrammeDDDRepository.class);
        IProgrammeEditionRepositoryDDD doubleProgrammeEditionRepository = mock(IProgrammeEditionRepositoryDDD.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepositoryImpl = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepositoryDDD doubleCourseEditionRepository = mock(ICourseEditionRepositoryDDD.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);


        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        doubleProgrammeEditionEnrolmentRepository,
                        doubleProgrammeEditionRepository,
                        doubleProgrammeList,
                        doubleCourseEditionEnrolmentRepositoryImpl,
                        doubleCourseEditionRepository,
                        doubleSchoolYearRepository,
                        doubleProgrammeEnrolmentRepository);

        CourseEditionID ce1Id = mock(CourseEditionID.class);
        CourseEditionID ce2Id = mock(CourseEditionID.class);

        when(doubleProgrammeEnrolmentRepository.isStudentEnrolled(doubleStudentId, doubleProgrammeId))
                .thenReturn(true);


        when(doubleProgrammeEditionRepository.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(doubleProgrammeId, doubleSchoolYearId))
                .thenReturn(Optional.of(doubleProgrammeEditionId));


        when(doubleCourseEditionRepository.findCourseEditionsByProgrammeEdition(doubleProgrammeEditionId))
                .thenReturn(List.of(ce1Id, ce2Id));


        when(doubleCourseEditionEnrolmentRepositoryImpl.findByStudentAndEdition(doubleStudentId, ce1Id))
                .thenReturn(Optional.of(new CourseEditionEnrolment(doubleStudentId, ce1Id)));

        when(doubleCourseEditionEnrolmentRepositoryImpl.findByStudentAndEdition(doubleStudentId, ce2Id))
                .thenReturn(Optional.of(new CourseEditionEnrolment(doubleStudentId, ce2Id)));


        doThrow(new IllegalStateException("This course edition enrolment is already in the list."))
                .when(doubleCourseEditionEnrolmentRepositoryImpl)
                .enrolStudentInProgrammeCourseEditions(any(StudentID.class), anyList());

        // Act + Assert
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(doubleStudentId, doubleProgrammeId, doubleSchoolYearId);
        });

        assertEquals("This course edition enrolment is already in the list.", exception.getMessage());
    }

    @Test
    void testGetAllProgrammes() {
        // Arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeDDDRepository doubleProgrammeList = mock(IProgrammeDDDRepository.class);
        IProgrammeEditionRepositoryDDD doubleProgrammeEditionRepository = mock(IProgrammeEditionRepositoryDDD.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepositoryImpl = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepositoryDDD doubleCourseEditionRepository = mock(ICourseEditionRepositoryDDD.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        doubleProgrammeEditionEnrolmentRepository,
                        doubleProgrammeEditionRepository,
                        doubleProgrammeList,
                        doubleCourseEditionEnrolmentRepositoryImpl,
                        doubleCourseEditionRepository,
                        doubleSchoolYearRepository,
                        doubleEnrolmentRepository);

        ProgrammeID doubleProgramme1 = mock(ProgrammeID.class);
        ProgrammeID doubleProgramme2 = mock(ProgrammeID.class);

        when(doubleProgrammeList.getAllProgrammesIDs()).thenReturn(List.of(doubleProgramme1, doubleProgramme2));

        // Act
        List<ProgrammeID> doubleProgrammes = controller.getAllProgrammesIDs();

        // Assert
        assertNotNull(doubleProgrammes, "The list of programmes should not be null.");
    }

    @Test
    void testGetAllProgrammes_SizeEqualsTwo() throws Exception {
        // Arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeDDDRepository doubleProgrammeList = mock(IProgrammeDDDRepository.class);
        IProgrammeEditionRepositoryDDD doubleProgrammeEditionRepository = mock(IProgrammeEditionRepositoryDDD.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepositoryImpl = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepositoryDDD doubleCourseEditionRepository = mock(ICourseEditionRepositoryDDD.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        doubleProgrammeEditionEnrolmentRepository,
                        doubleProgrammeEditionRepository,
                        doubleProgrammeList,
                        doubleCourseEditionEnrolmentRepositoryImpl,
                        doubleCourseEditionRepository,
                        doubleSchoolYearRepository,
                        doubleEnrolmentRepository);

        ProgrammeID doubleProgramme1 = mock(ProgrammeID.class);
        ProgrammeID doubleProgramme2 = mock(ProgrammeID.class);

        when(doubleProgrammeList.getAllProgrammesIDs()).thenReturn(List.of(doubleProgramme1, doubleProgramme2));

        // Act
        List<ProgrammeID> doubleProgrammes = controller.getAllProgrammesIDs();

        // Assert
        assertEquals(2, doubleProgrammes.size(), "The list of programmes should contain exactly 2 programmes.");
    }

    @Test
    void testGetAllProgrammes_ContainsAllProgrammes() {
        // Arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeDDDRepository doubleProgrammeList = mock(IProgrammeDDDRepository.class);
        IProgrammeEditionRepositoryDDD doubleProgrammeEditionRepository = mock(IProgrammeEditionRepositoryDDD.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepositoryImpl = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepositoryDDD doubleCourseEditionRepository = mock(ICourseEditionRepositoryDDD.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        doubleProgrammeEditionEnrolmentRepository,
                        doubleProgrammeEditionRepository,
                        doubleProgrammeList,
                        doubleCourseEditionEnrolmentRepositoryImpl,
                        doubleCourseEditionRepository,
                        doubleSchoolYearRepository,
                        doubleEnrolmentRepository);

        ProgrammeID doubleProgramme1 = mock(ProgrammeID.class);
        ProgrammeID doubleProgramme2 = mock(ProgrammeID.class);

        when(doubleProgrammeList.getAllProgrammesIDs()).thenReturn(List.of(doubleProgramme1, doubleProgramme2));

        // Act
        List<ProgrammeID> doubleProgrammes = controller.getAllProgrammesIDs();

        // Assert
        assertTrue(doubleProgrammes.contains(doubleProgramme1));
        assertTrue(doubleProgrammes.contains(doubleProgramme2));

    }

    @Test
    void testGetAllSchoolYears_NotNull() {
        // Arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeDDDRepository doubleProgrammeList = mock(IProgrammeDDDRepository.class);
        IProgrammeEditionRepositoryDDD doubleProgrammeEditionRepository = mock(IProgrammeEditionRepositoryDDD.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepositoryImpl = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepositoryDDD doubleCourseEditionRepository = mock(ICourseEditionRepositoryDDD.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        doubleProgrammeEditionEnrolmentRepository,
                        doubleProgrammeEditionRepository,
                        doubleProgrammeList,
                        doubleCourseEditionEnrolmentRepositoryImpl,
                        doubleCourseEditionRepository,
                        doubleSchoolYearRepository,
                        doubleEnrolmentRepository);

        SchoolYearID doubleSchoolYear1 = mock(SchoolYearID.class);
        SchoolYearID doubleSchoolYear2 = mock(SchoolYearID.class);

        when(doubleSchoolYearRepository.getAllSchoolYearsIDs()).thenReturn(List.of(doubleSchoolYear1, doubleSchoolYear2));

        // Act
        List<SchoolYearID> doubleSchoolYears = controller.getAllSchoolYearsIDs();

        // Assert
        assertNotNull(doubleSchoolYears, "The list of school years should not be null.");
    }

    @Test
    void testGetAllSchoolYears_SizeEqualsTwo() throws Exception {
        // Arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeDDDRepository doubleProgrammeList = mock(IProgrammeDDDRepository.class);
        IProgrammeEditionRepositoryDDD doubleProgrammeEditionRepository = mock(IProgrammeEditionRepositoryDDD.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepositoryImpl = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepositoryDDD doubleCourseEditionRepository = mock(ICourseEditionRepositoryDDD.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        doubleProgrammeEditionEnrolmentRepository,
                        doubleProgrammeEditionRepository,
                        doubleProgrammeList,
                        doubleCourseEditionEnrolmentRepositoryImpl,
                        doubleCourseEditionRepository,
                        doubleSchoolYearRepository,
                        doubleEnrolmentRepository);

        SchoolYearID doubleSchoolYear1 = mock(SchoolYearID.class);
        SchoolYearID doubleSchoolYear2 = mock(SchoolYearID.class);

        when(doubleSchoolYearRepository.getAllSchoolYearsIDs()).thenReturn(List.of(doubleSchoolYear1, doubleSchoolYear2));

        // Act
        List<SchoolYearID> doubleSchoolYears = controller.getAllSchoolYearsIDs();

        // Assert
        assertEquals(2, doubleSchoolYears.size(), "The list of school years should contain exactly 2 years.");
    }

    @Test
    void testGetAllSchoolYears_ContainsAllSchoolYears() throws Exception {
        // Arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeDDDRepository doubleProgrammeList = mock(IProgrammeDDDRepository.class);
        IProgrammeEditionRepositoryDDD doubleProgrammeEditionRepository = mock(IProgrammeEditionRepositoryDDD.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepositoryImpl = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepositoryDDD doubleCourseEditionRepository = mock(ICourseEditionRepositoryDDD.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        doubleProgrammeEditionEnrolmentRepository,
                        doubleProgrammeEditionRepository,
                        doubleProgrammeList,
                        doubleCourseEditionEnrolmentRepositoryImpl,
                        doubleCourseEditionRepository,
                        doubleSchoolYearRepository,
                        doubleEnrolmentRepository);

        SchoolYearID doubleSchoolYear1 = mock(SchoolYearID.class);
        SchoolYearID doubleSchoolYear2 = mock(SchoolYearID.class);

        when(doubleSchoolYearRepository.getAllSchoolYearsIDs()).thenReturn(List.of(doubleSchoolYear1, doubleSchoolYear2));

        // Act
        List<SchoolYearID> doubleSchoolYears = controller.getAllSchoolYearsIDs();

        // Assert
        assertTrue(doubleSchoolYears.contains(doubleSchoolYear1), "The list should contain doubleSchoolYear1.");
        assertTrue(doubleSchoolYears.contains(doubleSchoolYear2), "The list should contain doubleSchoolYear2.");
    }

    @Test
    void shouldReturnExceptionIfProgrammeEditionEnrollmentRepoIsNull() {
        //arrange
        IProgrammeDDDRepository doubleProgrammeList = mock(IProgrammeDDDRepository.class);
        IProgrammeEditionRepositoryDDD doubleProgrammeEditionRepository = mock(IProgrammeEditionRepositoryDDD.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepositoryImpl = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepositoryDDD doubleCourseEditionRepository = mock(ICourseEditionRepositoryDDD.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(null, doubleProgrammeEditionRepository,
                    doubleProgrammeList, doubleCourseEditionEnrolmentRepositoryImpl, doubleCourseEditionRepository, doubleSchoolYearRepository, doubleEnrolmentRepository);
        });
        //assert
        assertEquals("Programme edition enrolment repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfProgrammeEditionRepositoryIsNull() {
        //arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeDDDRepository doubleProgrammeList = mock(IProgrammeDDDRepository.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepositoryImpl = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepositoryDDD doubleCourseEditionRepository = mock(ICourseEditionRepositoryDDD.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(doubleProgrammeEditionEnrolmentRepository, null,
                    doubleProgrammeList, doubleCourseEditionEnrolmentRepositoryImpl, doubleCourseEditionRepository, doubleSchoolYearRepository, doubleEnrolmentRepository);
        });

        //assert
        assertEquals("Programme edition repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfProgrammeListIsNull() {
        //arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionRepositoryDDD doubleProgrammeEditionRepository = mock(IProgrammeEditionRepositoryDDD.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepositoryImpl = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepositoryDDD doubleCourseEditionRepository = mock(ICourseEditionRepositoryDDD.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(doubleProgrammeEditionEnrolmentRepository, doubleProgrammeEditionRepository,
                    null, doubleCourseEditionEnrolmentRepositoryImpl, doubleCourseEditionRepository, doubleSchoolYearRepository, doubleEnrolmentRepository);
        });

        //assert
        assertEquals("Programme list cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfCourseEditionEnrollmentRepositoryIsNull() {
        //arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionRepositoryDDD doubleProgrammeEditionRepository = mock(IProgrammeEditionRepositoryDDD.class);
        IProgrammeDDDRepository doubleProgrammeList = mock(IProgrammeDDDRepository.class);
        ICourseEditionRepositoryDDD doubleCourseEditionRepository = mock(ICourseEditionRepositoryDDD.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(doubleProgrammeEditionEnrolmentRepository, doubleProgrammeEditionRepository,
                    doubleProgrammeList, null, doubleCourseEditionRepository, doubleSchoolYearRepository, doubleEnrolmentRepository);
        });

        //assert
        assertEquals("Course edition enrolment repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfCourseEditionRepositoryIsNull() {
        //arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionRepositoryDDD doubleProgrammeEditionRepository = mock(IProgrammeEditionRepositoryDDD.class);
        IProgrammeDDDRepository doubleProgrammeList = mock(IProgrammeDDDRepository.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepositoryImpl = mock(ICourseEditionEnrolmentRepository.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(doubleProgrammeEditionEnrolmentRepository, doubleProgrammeEditionRepository,
                    doubleProgrammeList, doubleCourseEditionEnrolmentRepositoryImpl, null, doubleSchoolYearRepository, doubleEnrolmentRepository);
        });

        //assert
        assertEquals("Course edition repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfSchoolYearRepositoryIsNull() {
        //arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionRepositoryDDD doubleProgrammeEditionRepository = mock(IProgrammeEditionRepositoryDDD.class);
        IProgrammeDDDRepository doubleProgrammeList = mock(IProgrammeDDDRepository.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepositoryImpl = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepositoryDDD doubleCourseEditionRepository = mock(ICourseEditionRepositoryDDD.class);
        IProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(doubleProgrammeEditionEnrolmentRepository, doubleProgrammeEditionRepository,
                    doubleProgrammeList, doubleCourseEditionEnrolmentRepositoryImpl, doubleCourseEditionRepository, null, doubleEnrolmentRepository);
        });

        //assert
        assertEquals("School year repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfEnrolmentRepositoryIsNull() {
        //arrange
        IProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionRepositoryDDD doubleProgrammeEditionRepository = mock(IProgrammeEditionRepositoryDDD.class);
        IProgrammeDDDRepository doubleProgrammeList = mock(IProgrammeDDDRepository.class);
        ICourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepositoryImpl = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepositoryDDD doubleCourseEditionRepository = mock(ICourseEditionRepositoryDDD.class);
        ISchoolYearRepository doubleSchoolYearRepository = mock(ISchoolYearRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(doubleProgrammeEditionEnrolmentRepository, doubleProgrammeEditionRepository,
                    doubleProgrammeList, doubleCourseEditionEnrolmentRepositoryImpl, doubleCourseEditionRepository, doubleSchoolYearRepository, null);
        });

        //assert
        assertEquals("Enrolment repository cannot be null.", exception.getMessage());
    }


    //----------------------INTEGRATION TESTS------------------------------

    @Test
    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_Success_IntegrationTest() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionDDDListFactory = new ProgrammeEditionDDDListFactoryImpl();
        IProgrammeEditionDDDFactory programmeEditionDDDFactory = new ProgrammeEditionDDDFactoryImpl();
        ProgrammeEditionRepositoryDDDImpl doubleProgrammeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionDDDListFactory, programmeEditionDDDFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
        IProgrammeDDDFactory IProgrammeFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDRepository programmeRepository = new ProgrammeDDDRepositoryImpl(IProgrammeFactory, IProgrammeRepositoryListFactory);
        ICourseEditionFactoryDDD ICourseEditionFactory_2 = new CourseEditionFactoryDDDImpl();
        ICourseEditionListFactoryDDD ICourseEditionListFactory_2 = new CourseEditionListFactoryDDDImpl();
        ICourseEditionRepositoryDDD courseEditionRepositoryImpl = new CourseEditionRepositoryDDDImpl(ICourseEditionFactory_2, ICourseEditionListFactory_2);
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);

        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrolmentRepository,
                        doubleProgrammeEditionRepository,
                        programmeRepository,
                        courseEditionEnrolmentRepositoryImpl,
                        courseEditionRepositoryImpl,
                        schoolYearRepository,
                        programmeEnrolmentRepository);
        Date date = new Date("20-12-2010");
        TeacherCategoryID tcID = new TeacherCategoryID();
        WorkingPercentage wp = new WorkingPercentage(100);
        TeacherAcronym tAcronym = new TeacherAcronym("ACR");
        TeacherID teacherID = new TeacherID(tAcronym);
        DegreeType master = new DegreeType("Master", 240);
        DepartmentAcronym dAcronym = new DepartmentAcronym("DEI");
        DepartmentID departmentID = new DepartmentID(dAcronym);
        Name dName = new Name("Departamento Engenharia Informática");
        Department department1 = new Department(dAcronym, dName);
        Street street1 = new Street("Rua São Tomé Porto");
        PostalCode postalCode = new PostalCode("4249-015");
        Location location = new Location("Porto");
        Country country = new Country("United States");
        Address add1 = new Address(street1, postalCode, location, country);
        Name tName = new Name("Joe Doe");
        Email email = new Email("joe@doe.com");
        Country tCountry = new Country("Portugal");
        NIF tNIF = new NIF("123666789", tCountry);
        PhoneNumber phoneNumber = new PhoneNumber("+351", "999999999");
        AcademicBackground tAcademicBackground = new AcademicBackground("Doutoramento em Engenharia Informatica, 2005, ISEP");
        Teacher teacher1 = new Teacher(tAcronym, tName, email, tNIF, phoneNumber, tAcademicBackground, add1, departmentID);
        Description description = new Description("School Year 24/25");
        Date startDate = new Date("23-11-2024");
        Date endDate = new Date("09-12-2025");
        schoolYearRepository.addSchoolYear(description, startDate, endDate);
        SchoolYear schoolYear = schoolYearRepository.getCurrentSchoolYear();
        SchoolYearID schoolYearId = new SchoolYearID();
        IAccessMethodFactory accessMethodFactory = new AccessMethodFactoryImpl();
        IAccessMethodListFactory accessMethodListFactory = new AccessMethodListFactoryImpl();
        AccessMethodID amId = new AccessMethodID();
        StudentID studentId = new StudentID(1500000);
        Name studentName = new Name("Joe Doe");
        Country tCountry1 = new Country("Portugal");
        NIF stNIF = new NIF("123666789", tCountry);
        StudentAcademicEmail studentAcademicEmail = new StudentAcademicEmail(studentId);
        Student student = new Student(studentId, studentName, stNIF, phoneNumber, email, add1, studentAcademicEmail);

        Course c1 = new Course("Development", "DEV", 5, 1);
        Course c2 = new Course("Development1", "DEV1", 5, 1);

        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("Computer Engineering");
        Acronym pAcronym = new Acronym("CEN");
        ProgrammeID programmeId = new ProgrammeID(nameWithNumbersAndSpecialChars, pAcronym);
        QuantEcts quantEcts = new QuantEcts(5);
        QuantSemesters quantSemesters = new QuantSemesters(5);
        DegreeTypeID degreeTypeID = new DegreeTypeID("Master");
        TeacherID teacherID1 = new TeacherID(tAcronym);
        ProgrammeDDD programme1 = new ProgrammeDDD(nameWithNumbersAndSpecialChars, pAcronym, quantEcts, quantSemesters,
                degreeTypeID, departmentID, teacherID1);

        if (!programmeEnrolmentRepository.isStudentEnrolled(studentId, programmeId)) {
            programmeEnrolmentRepository.enrolStudents(studentId, amId, programmeId, date);
        }
        Date date1 = new Date("01-04-2023");
        Date date2 = new Date("01-04-2024");
        StudyPlanID studyPlanID1 = new StudyPlanID(programmeId, date1);
        StudyPlanID studyPlanID2 = new StudyPlanID(programmeId, date2);
        doubleProgrammeEditionRepository.createProgrammeEdition(programmeId, schoolYearId);
        Optional<ProgrammeEditionID> pe1Opt = doubleProgrammeEditionRepository.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(programmeId, schoolYearId);
        ProgrammeEditionID pe1 = pe1Opt.get();

        CourseID courseId2 = new CourseID();
        CourseID courseId1 = new CourseID();
        ProgrammeEditionID programmeEditionId = new ProgrammeEditionID(programmeId, schoolYearId);
        CourseInStudyPlanID courseInStudyPlanID1 = new CourseInStudyPlanID(courseId1, studyPlanID1);
        CourseInStudyPlanID courseInStudyPlanID2 = new CourseInStudyPlanID(courseId2, studyPlanID2);
        CourseEditionID courseEditionId1 = new CourseEditionID(programmeEditionId, courseInStudyPlanID1);
        CourseEditionID courseEditionId2 = new CourseEditionID(programmeEditionId, courseInStudyPlanID2);
        CourseEditionDDD courseEdition1 = new CourseEditionDDD(courseEditionId1, courseInStudyPlanID1, programmeEditionId);
        CourseEditionDDD courseEdition2 = new CourseEditionDDD(courseEditionId2, courseInStudyPlanID1, programmeEditionId);
        // CourseEditionID ce1Id = courseEditionRepositoryImpl.findIdByCourseEdition(courseEdition1).get();
        //   CourseEditionID ce2Id = courseEditionRepositoryImpl.findIdByCourseEdition(courseEdition2).get();
        courseEditionRepositoryImpl.createAndSaveCourseEdition(courseInStudyPlanID1, pe1);
        courseEditionRepositoryImpl.createAndSaveCourseEdition(courseInStudyPlanID2, pe1);

        // Act
        boolean result = controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(studentId, programmeId, schoolYearId);
        boolean result2 = courseEditionEnrolmentRepositoryImpl.isStudentEnrolledInCourseEdition(studentId, courseEditionId1);
        boolean result3 = courseEditionEnrolmentRepositoryImpl.isStudentEnrolledInCourseEdition(studentId, courseEditionId2);

        // Assert
        assertTrue(result, "The student is enrolled in the ProgrammeEdition.");
        assertTrue(result2, "The Student is enrolled in the CourseEdition.");
        assertTrue(result3, "The Student is enrolled in the CourseEdition.");
    }

    @Test
    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_StudentNotEnrolledInProgramme_IntegrationTest() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionDDDListFactory = new ProgrammeEditionDDDListFactoryImpl();
        IProgrammeEditionDDDFactory programmeEditionDDDFactory = new ProgrammeEditionDDDFactoryImpl();
        IProgrammeEditionRepositoryDDD doubleProgrammeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionDDDListFactory, programmeEditionDDDFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
        IProgrammeDDDFactory IProgrammeFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDRepository programmeRepository = new ProgrammeDDDRepositoryImpl(IProgrammeFactory, IProgrammeRepositoryListFactory);
        ICourseEditionFactoryDDD ICourseEditionFactory_2 = new CourseEditionFactoryDDDImpl();
        ICourseEditionListFactoryDDD ICourseEditionListFactory_2 = new CourseEditionListFactoryDDDImpl();
        ICourseEditionRepositoryDDD courseEditionRepositoryImpl = new CourseEditionRepositoryDDDImpl(ICourseEditionFactory_2, ICourseEditionListFactory_2);
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);

        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrolmentRepository,
                        doubleProgrammeEditionRepository,
                        programmeRepository,
                        courseEditionEnrolmentRepositoryImpl,
                        courseEditionRepositoryImpl,
                        schoolYearRepository,
                        programmeEnrolmentRepository);

        Date date = new Date("20-12-2010");
        TeacherCategoryID tcID = new TeacherCategoryID();
        WorkingPercentage wp = new WorkingPercentage(100);
        TeacherAcronym tAcronym = new TeacherAcronym("ACR");
        TeacherID teacherID = new TeacherID(tAcronym);
        DegreeType master = new DegreeType("Master", 240);
        DepartmentAcronym dAcronym = new DepartmentAcronym("DEI");
        DepartmentID departmentID = new DepartmentID(dAcronym);
        Name dName = new Name("Departamento Engenharia Informática");
        Department department1 = new Department(dAcronym, dName);
        Street street1 = new Street("Rua São Tomé Porto");
        PostalCode postalCode = new PostalCode("4249-015");
        Location location = new Location("Porto");
        Country country = new Country("United States");
        Address add1 = new Address(street1, postalCode, location, country);
        Name tName = new Name("Joe Doe");
        Email email = new Email("joe@doe.com");
        Country tCountry = new Country("Portugal");
        NIF tNIF = new NIF("123666789", tCountry);
        PhoneNumber phoneNumber = new PhoneNumber("+351", "999999999");
        AcademicBackground tAcademicBackground = new AcademicBackground("Doutoramento em Engenharia Informatica, 2005, ISEP");
        Teacher teacher1 = new Teacher(tAcronym, tName, email, tNIF, phoneNumber, tAcademicBackground, add1, departmentID);
        Description description = new Description("School Year 24/25");
        Date startDate = new Date("23-11-2024");
        Date endDate = new Date("09-12-2025");
        schoolYearRepository.addSchoolYear(description, startDate, endDate);
        SchoolYear schoolYear = schoolYearRepository.getCurrentSchoolYear();
        SchoolYearID schoolYearId = new SchoolYearID();
        IAccessMethodFactory accessMethodFactory = new AccessMethodFactoryImpl();
        IAccessMethodListFactory accessMethodListFactory = new AccessMethodListFactoryImpl();
        AccessMethodID amId = new AccessMethodID();
        StudentID studentId = new StudentID(1500000);
        Name studentName = new Name("Joe Doe");
        Country tCountry1 = new Country("Portugal");
        NIF stNIF = new NIF("123666789", tCountry);
        StudentAcademicEmail studentAcademicEmail = new StudentAcademicEmail(studentId);
        Student student = new Student(studentId, studentName, stNIF, phoneNumber, email, add1, studentAcademicEmail);

        Course c1 = new Course("Development", "DEV", 5, 1);
        Course c2 = new Course("Development1", "DEV1", 5, 1);

        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("Computer Engineering");
        Acronym pAcronym = new Acronym("CEN");
        ProgrammeID programmeId = new ProgrammeID(nameWithNumbersAndSpecialChars, pAcronym);
        QuantEcts quantEcts = new QuantEcts(5);
        QuantSemesters quantSemesters = new QuantSemesters(5);
        DegreeTypeID degreeTypeID = new DegreeTypeID("Master");
        TeacherID teacherID1 = new TeacherID(tAcronym);
        ProgrammeDDD programme1 = new ProgrammeDDD(nameWithNumbersAndSpecialChars, pAcronym, quantEcts, quantSemesters,
                degreeTypeID, departmentID, teacherID1);


        doubleProgrammeEditionRepository.createProgrammeEdition(programmeId, schoolYearId);

        // Act
        boolean result = controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(studentId, programmeId, schoolYearId);

        //Assert
        assertFalse(result);
    }

    @Test
    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_ProgrammeEditionNotFound_IntegrationTests() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionDDDListFactory = new ProgrammeEditionDDDListFactoryImpl();
        IProgrammeEditionDDDFactory programmeEditionDDDFactory = new ProgrammeEditionDDDFactoryImpl();
        IProgrammeEditionRepositoryDDD doubleProgrammeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionDDDListFactory, programmeEditionDDDFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
        IProgrammeDDDFactory IProgrammeFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDRepository programmeRepository = new ProgrammeDDDRepositoryImpl(IProgrammeFactory, IProgrammeRepositoryListFactory);
        ICourseEditionFactoryDDD ICourseEditionFactory_2 = new CourseEditionFactoryDDDImpl();
        ICourseEditionListFactoryDDD ICourseEditionListFactory_2 = new CourseEditionListFactoryDDDImpl();
        ICourseEditionRepositoryDDD courseEditionRepositoryImpl = new CourseEditionRepositoryDDDImpl(ICourseEditionFactory_2, ICourseEditionListFactory_2);
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);

        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrolmentRepository,
                        doubleProgrammeEditionRepository,
                        programmeRepository,
                        courseEditionEnrolmentRepositoryImpl,
                        courseEditionRepositoryImpl,
                        schoolYearRepository,
                        programmeEnrolmentRepository);

        Date date = new Date("20-12-2010");
        TeacherCategoryID tcID = new TeacherCategoryID();
        WorkingPercentage wp = new WorkingPercentage(100);
        TeacherAcronym tAcronym = new TeacherAcronym("ACR");
        TeacherID teacherID = new TeacherID(tAcronym);
        DegreeType master = new DegreeType("Master", 240);
        DepartmentAcronym dAcronym = new DepartmentAcronym("DEI");
        DepartmentID departmentID = new DepartmentID(dAcronym);
        Name dName = new Name("Departamento Engenharia Informática");
        Department department1 = new Department(dAcronym, dName);
        Street street1 = new Street("Rua São Tomé Porto");
        PostalCode postalCode = new PostalCode("4249-015");
        Location location = new Location("Porto");
        Country country = new Country("United States");
        Address add1 = new Address(street1, postalCode, location, country);
        Name tName = new Name("Joe Doe");
        Email email = new Email("joe@doe.com");
        Country tCountry = new Country("Portugal");
        NIF tNIF = new NIF("123666789", tCountry);
        PhoneNumber phoneNumber = new PhoneNumber("+351", "999999999");
        AcademicBackground tAcademicBackground = new AcademicBackground("Doutoramento em Engenharia Informatica, 2005, ISEP");
        Teacher teacher1 = new Teacher(tAcronym, tName, email, tNIF, phoneNumber, tAcademicBackground, add1, departmentID);
        Description description = new Description("School Year 24/25");
        Date startDate = new Date("23-11-2024");
        Date endDate = new Date("09-12-2025");
        schoolYearRepository.addSchoolYear(description, startDate, endDate);
        SchoolYear schoolYear = schoolYearRepository.getCurrentSchoolYear();
        SchoolYearID schoolYearId = new SchoolYearID();
        IAccessMethodFactory accessMethodFactory = new AccessMethodFactoryImpl();
        IAccessMethodListFactory accessMethodListFactory = new AccessMethodListFactoryImpl();
        AccessMethodID amId = new AccessMethodID();
        StudentID studentId = new StudentID(1500000);
        Name studentName = new Name("Joe Doe");
        Country tCountry1 = new Country("Portugal");
        NIF stNIF = new NIF("123666789", tCountry);
        StudentAcademicEmail studentAcademicEmail = new StudentAcademicEmail(studentId);
        Student student = new Student(studentId, studentName, stNIF, phoneNumber, email, add1, studentAcademicEmail);


        StudentID studentID = new StudentID(1000001);
        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("Computer Engineering");
        Acronym pAcronym = new Acronym("CEN");
        ProgrammeID programmeId = new ProgrammeID(nameWithNumbersAndSpecialChars, pAcronym);


        QuantEcts quantEcts = new QuantEcts(5);
        QuantSemesters quantSemesters = new QuantSemesters(5);
        DegreeTypeID degreeTypeID = new DegreeTypeID("Master");
        TeacherID teacherID1 = new TeacherID(tAcronym);
        ProgrammeDDD programme1 = new ProgrammeDDD(nameWithNumbersAndSpecialChars, pAcronym, quantEcts, quantSemesters,
                degreeTypeID, departmentID, teacherID1);

        if (!programmeEnrolmentRepository.isStudentEnrolled(studentId, programmeId)) {
            programmeEnrolmentRepository.enrolStudents(studentId, amId, programmeId, date);
        }
        // Act
        boolean result = controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(studentId, programmeId, schoolYearId);

        assertFalse(result);
    }


    @Test
    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_StudentAlreadyEnrolledInProgrammeEdition_IntegrationTest() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionDDDListFactory = new ProgrammeEditionDDDListFactoryImpl();
        IProgrammeEditionDDDFactory programmeEditionDDDFactory = new ProgrammeEditionDDDFactoryImpl();
        IProgrammeEditionRepositoryDDD doubleProgrammeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionDDDListFactory, programmeEditionDDDFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
        IProgrammeDDDFactory IProgrammeFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        ProgrammeDDDRepositoryImpl programmeRepository = new ProgrammeDDDRepositoryImpl(IProgrammeFactory, IProgrammeRepositoryListFactory);
        ICourseEditionFactoryDDD ICourseEditionFactory_2 = new CourseEditionFactoryDDDImpl();
        ICourseEditionListFactoryDDD ICourseEditionListFactory_2 = new CourseEditionListFactoryDDDImpl();
        ICourseEditionRepositoryDDD courseEditionRepositoryImpl = new CourseEditionRepositoryDDDImpl(ICourseEditionFactory_2, ICourseEditionListFactory_2);
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);

        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrolmentRepository,
                        doubleProgrammeEditionRepository,
                        programmeRepository,
                        courseEditionEnrolmentRepositoryImpl,
                        courseEditionRepositoryImpl,
                        schoolYearRepository,
                        programmeEnrolmentRepository);

        Date date = new Date("20-12-2010");
        TeacherCategoryID tcID = new TeacherCategoryID();
        WorkingPercentage wp = new WorkingPercentage(100);
        TeacherAcronym tAcronym = new TeacherAcronym("ACR");
        TeacherID teacherID = new TeacherID(tAcronym);
        DegreeType master = new DegreeType("Master", 240);
        DepartmentAcronym dAcronym = new DepartmentAcronym("DEI");
        DepartmentID departmentID = new DepartmentID(dAcronym);
        Name dName = new Name("Departamento Engenharia Informática");
        Department department1 = new Department(dAcronym, dName);
        Street street1 = new Street("Rua São Tomé Porto");
        PostalCode postalCode = new PostalCode("4249-015");
        Location location = new Location("Porto");
        Country country = new Country("United States");
        Address add1 = new Address(street1, postalCode, location, country);
        Name tName = new Name("Joe Doe");
        Email email = new Email("joe@doe.com");
        Country tCountry = new Country("Portugal");
        NIF tNIF = new NIF("123666789", tCountry);
        PhoneNumber phoneNumber = new PhoneNumber("+351", "999999999");
        AcademicBackground tAcademicBackground = new AcademicBackground("Doutoramento em Engenharia Informatica, 2005, ISEP");
        Teacher teacher1 = new Teacher(tAcronym, tName, email, tNIF, phoneNumber, tAcademicBackground, add1, departmentID);
        Description description = new Description("School Year 24/25");
        Date startDate = new Date("23-11-2024");
        Date endDate = new Date("09-12-2025");
        schoolYearRepository.addSchoolYear(description, startDate, endDate);
        SchoolYear schoolYear = schoolYearRepository.getCurrentSchoolYear();
        SchoolYearID schoolYearId = new SchoolYearID();
        IAccessMethodFactory accessMethodFactory = new AccessMethodFactoryImpl();
        IAccessMethodListFactory accessMethodListFactory = new AccessMethodListFactoryImpl();
        AccessMethodID amId = new AccessMethodID();
        StudentID studentId = new StudentID(1500000);
        Name studentName = new Name("Joe Doe");
        Country tCountry1 = new Country("Portugal");
        NIF stNIF = new NIF("123666789", tCountry);
        StudentAcademicEmail studentAcademicEmail = new StudentAcademicEmail(studentId);
        Student student = new Student(studentId, studentName, stNIF, phoneNumber, email, add1, studentAcademicEmail);


        StudentID studentID = new StudentID(1000001);
        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("Computer Engineering");
        Acronym pAcronym = new Acronym("CEN");
        ProgrammeID programmeId = new ProgrammeID(nameWithNumbersAndSpecialChars, pAcronym);


        QuantEcts quantEcts = new QuantEcts(5);
        QuantSemesters quantSemesters = new QuantSemesters(5);
        DegreeTypeID degreeTypeID = new DegreeTypeID("Master");
        TeacherID teacherID1 = new TeacherID(tAcronym);
        ProgrammeDDD programme1 = new ProgrammeDDD(nameWithNumbersAndSpecialChars, pAcronym, quantEcts, quantSemesters,
                degreeTypeID, departmentID, teacherID1);

        if (!programmeEnrolmentRepository.isStudentEnrolled(studentId, programmeId)) {
            programmeEnrolmentRepository.enrolStudents(studentId, amId, programmeId, date);
        }

        doubleProgrammeEditionRepository.createProgrammeEdition(programmeId, schoolYearId);
        Optional<ProgrammeEditionID> peOptional = doubleProgrammeEditionRepository.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(programmeId, schoolYearId);
        ProgrammeEditionID programmeEditionId = peOptional.get();
        programmeEditionEnrolmentRepository.enrolStudentInProgrammeEdition(studentId, programmeEditionId);

        // Act
        boolean result = controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(studentId, programmeId, schoolYearId);

        //assert
        assertFalse(result);
    }


    @Test
    void testEnrollStudentInCourseEditionAndSetOfCoursesEditions_StudentAlreadyEnrolledInCourseEdition_IntegrationTest() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionDDDListFactory = new ProgrammeEditionDDDListFactoryImpl();
        IProgrammeEditionDDDFactory programmeEditionDDDFactory = new ProgrammeEditionDDDFactoryImpl();
        IProgrammeEditionRepositoryDDD doubleProgrammeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionDDDListFactory, programmeEditionDDDFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
        IProgrammeDDDFactory IProgrammeFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDRepository programmeRepository = new ProgrammeDDDRepositoryImpl(IProgrammeFactory, IProgrammeRepositoryListFactory);
        ICourseEditionFactoryDDD ICourseEditionFactory_2 = new CourseEditionFactoryDDDImpl();
        ICourseEditionListFactoryDDD ICourseEditionListFactory_2 = new CourseEditionListFactoryDDDImpl();
        ICourseEditionRepositoryDDD courseEditionRepositoryImpl = new CourseEditionRepositoryDDDImpl(ICourseEditionFactory_2, ICourseEditionListFactory_2);
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);


        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrolmentRepository,
                        doubleProgrammeEditionRepository,
                        programmeRepository,
                        courseEditionEnrolmentRepositoryImpl,
                        courseEditionRepositoryImpl,
                        schoolYearRepository,
                        programmeEnrolmentRepository);

        Date date = new Date("20-12-2010");
        SchoolYearID schoolYearId = new SchoolYearID();
        AccessMethodID amId = new AccessMethodID();
        StudentID studentId = new StudentID(1500000);

        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("Computer Engineering");
        Acronym pAcronym = new Acronym("CEN");
        ProgrammeID programmeId = new ProgrammeID(nameWithNumbersAndSpecialChars, pAcronym);

        if (!programmeEnrolmentRepository.isStudentEnrolled(studentId, programmeId)) {
            programmeEnrolmentRepository.enrolStudents(studentId, amId, programmeId, date);
        }
        Date date1 = new Date("01-04-2023");
        Date date2 = new Date("01-04-2024");
        StudyPlanID studyPlanID1 = new StudyPlanID(programmeId, date1);
        StudyPlanID studyPlanID2 = new StudyPlanID(programmeId, date2);
        doubleProgrammeEditionRepository.createProgrammeEdition(programmeId, schoolYearId);
        Optional<ProgrammeEditionID> pe1Opt = doubleProgrammeEditionRepository.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(programmeId, schoolYearId);
        ProgrammeEditionID pe1 = pe1Opt.get();

        CourseID courseId2 = new CourseID();
        CourseID courseId1 = new CourseID();
        ProgrammeEditionID programmeEditionId = new ProgrammeEditionID(programmeId, schoolYearId);
        CourseInStudyPlanID courseInStudyPlanID1 = new CourseInStudyPlanID(courseId1, studyPlanID1);
        CourseInStudyPlanID courseInStudyPlanID2 = new CourseInStudyPlanID(courseId2, studyPlanID2);
        CourseEditionID courseEditionId1 = new CourseEditionID(programmeEditionId, courseInStudyPlanID1);
        CourseEditionID courseEditionId2 = new CourseEditionID(programmeEditionId, courseInStudyPlanID2);

        courseEditionRepositoryImpl.createAndSaveCourseEdition(courseInStudyPlanID1, pe1);
        courseEditionRepositoryImpl.createAndSaveCourseEdition(courseInStudyPlanID2, pe1);

        courseEditionRepositoryImpl.createAndSaveCourseEdition(courseInStudyPlanID1, pe1);
        courseEditionRepositoryImpl.createAndSaveCourseEdition(courseInStudyPlanID2, pe1);

        courseEditionEnrolmentRepositoryImpl.enrolStudentInACourseEdition(studentId, courseEditionId1);
        courseEditionEnrolmentRepositoryImpl.enrolStudentInACourseEdition(studentId, courseEditionId2);
        // Act + Assert
        assertThrows(IllegalStateException.class, () -> {
            controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(studentId, programmeId, schoolYearId);
        });
    }

    @Test
    void testGetAllProgrammes_NotNull_IntegrationTest() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionDDDListFactory = new ProgrammeEditionDDDListFactoryImpl();
        IProgrammeEditionDDDFactory programmeEditionDDDFactory = new ProgrammeEditionDDDFactoryImpl();
        IProgrammeEditionRepositoryDDD doubleProgrammeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionDDDListFactory, programmeEditionDDDFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
        IProgrammeDDDFactory IProgrammeFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDRepository programmeRepository = new ProgrammeDDDRepositoryImpl(IProgrammeFactory, IProgrammeRepositoryListFactory);
        ICourseEditionFactoryDDD ICourseEditionFactory_2 = new CourseEditionFactoryDDDImpl();
        ICourseEditionListFactoryDDD ICourseEditionListFactory_2 = new CourseEditionListFactoryDDDImpl();
        ICourseEditionRepositoryDDD courseEditionRepositoryImpl = new CourseEditionRepositoryDDDImpl(ICourseEditionFactory_2, ICourseEditionListFactory_2);
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);

        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrolmentRepository,
                        doubleProgrammeEditionRepository,
                        programmeRepository,
                        courseEditionEnrolmentRepositoryImpl,
                        courseEditionRepositoryImpl,
                        schoolYearRepository,
                        programmeEnrolmentRepository);

        DepartmentAcronym dptAcronym = new DepartmentAcronym("DEI");
        Name dName = new Name("Department");
        Department department1 = new Department(dptAcronym, dName);
        Acronym acronym1 = new Acronym("CSE");
        Acronym acronym2 = new Acronym("CVE");
        NameWithNumbersAndSpecialChars name1 = new NameWithNumbersAndSpecialChars("Computer Engineering");
        NameWithNumbersAndSpecialChars name2 = new NameWithNumbersAndSpecialChars("Civil Engineering");
        QuantEcts quantEcts = new QuantEcts(25);
        QuantSemesters quantSemesters = new QuantSemesters(6);
        DepartmentAcronym dAcronym = new DepartmentAcronym("DEI");
        DepartmentID departmentID = new DepartmentID(dAcronym);
        DegreeTypeID degreeTypeID = new DegreeTypeID("Master");
        TeacherAcronym tAcronym = new TeacherAcronym("AAA");
        TeacherID teacherID1 = new TeacherID(tAcronym);

        programmeRepository.registerProgramme(name1, acronym1, quantEcts, quantSemesters, degreeTypeID, departmentID, teacherID1);
        programmeRepository.registerProgramme(name2, acronym2, quantEcts, quantSemesters, degreeTypeID, departmentID, teacherID1);

        // Act
        List<ProgrammeID> programmes = controller.getAllProgrammesIDs();

        // Assert
        assertNotNull(programmes, "The list of programmes should not be null.");
    }

    @Test
    void testGetAllProgrammes_ListSize_IntegrationTest() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionDDDListFactory = new ProgrammeEditionDDDListFactoryImpl();
        IProgrammeEditionDDDFactory programmeEditionDDDFactory = new ProgrammeEditionDDDFactoryImpl();
        IProgrammeEditionRepositoryDDD doubleProgrammeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionDDDListFactory, programmeEditionDDDFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
        IProgrammeDDDFactory IProgrammeFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDRepository programmeRepository = new ProgrammeDDDRepositoryImpl(IProgrammeFactory, IProgrammeRepositoryListFactory);
        ICourseEditionFactoryDDD ICourseEditionFactory_2 = new CourseEditionFactoryDDDImpl();
        ICourseEditionListFactoryDDD ICourseEditionListFactory_2 = new CourseEditionListFactoryDDDImpl();
        ICourseEditionRepositoryDDD courseEditionRepositoryImpl = new CourseEditionRepositoryDDDImpl(ICourseEditionFactory_2, ICourseEditionListFactory_2);
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);

        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrolmentRepository,
                        doubleProgrammeEditionRepository,
                        programmeRepository,
                        courseEditionEnrolmentRepositoryImpl,
                        courseEditionRepositoryImpl,
                        schoolYearRepository,
                        programmeEnrolmentRepository);

        DepartmentAcronym dptAcronym = new DepartmentAcronym("DEI");
        Name dName = new Name("Department");
        Department department1 = new Department(dptAcronym, dName);
        Acronym acronym1 = new Acronym("CSE");
        Acronym acronym2 = new Acronym("CVE");
        NameWithNumbersAndSpecialChars name1 = new NameWithNumbersAndSpecialChars("Computer Engineering");
        NameWithNumbersAndSpecialChars name2 = new NameWithNumbersAndSpecialChars("Civil Engineering");
        QuantEcts quantEcts = new QuantEcts(25);
        QuantSemesters quantSemesters = new QuantSemesters(6);
        DepartmentAcronym dAcronym = new DepartmentAcronym("DEI");
        DepartmentID departmentID = new DepartmentID(dAcronym);
        DegreeTypeID degreeTypeID = new DegreeTypeID("Master");
        TeacherAcronym tAcronym = new TeacherAcronym("AAA");
        TeacherID teacherID1 = new TeacherID(tAcronym);

        programmeRepository.registerProgramme(name1, acronym1, quantEcts, quantSemesters, degreeTypeID, departmentID, teacherID1);
        programmeRepository.registerProgramme(name2, acronym2, quantEcts, quantSemesters, degreeTypeID, departmentID, teacherID1);

        // Act
        List<ProgrammeID> programmes = controller.getAllProgrammesIDs();

        // Assert
        assertEquals(2, programmes.size(), "The list of programmes should contain exactly 2 programmes.");

    }

    @Test
    void testGetAllSchoolYears_NotNullList_IntegrationTest() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionDDDListFactory = new ProgrammeEditionDDDListFactoryImpl();
        IProgrammeEditionDDDFactory programmeEditionDDDFactory = new ProgrammeEditionDDDFactoryImpl();
        IProgrammeEditionRepositoryDDD doubleProgrammeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionDDDListFactory, programmeEditionDDDFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
        IProgrammeDDDFactory IProgrammeFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDRepository programmeRepository = new ProgrammeDDDRepositoryImpl(IProgrammeFactory, IProgrammeRepositoryListFactory);
        ICourseEditionFactoryDDD ICourseEditionFactory_2 = new CourseEditionFactoryDDDImpl();
        ICourseEditionListFactoryDDD ICourseEditionListFactory_2 = new CourseEditionListFactoryDDDImpl();
        ICourseEditionRepositoryDDD courseEditionRepositoryImpl = new CourseEditionRepositoryDDDImpl(ICourseEditionFactory_2, ICourseEditionListFactory_2);
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);

        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrolmentRepository,
                        doubleProgrammeEditionRepository,
                        programmeRepository,
                        courseEditionEnrolmentRepositoryImpl,
                        courseEditionRepositoryImpl,
                        schoolYearRepository,
                        programmeEnrolmentRepository);

        Description description1 = new Description("School Year 24/25");
        Description description2 = new Description("School Year 25/26");
        Date startDate1 = new Date("23-11-2024");
        Date endDate1 = new Date("09-12-2025");
        Date startDate2 = new Date("10-11-2025");
        Date endDate2 = new Date("09-12-2026");
        schoolYearRepository.addSchoolYear(description1, startDate1, endDate1);
        schoolYearRepository.addSchoolYear(description2, startDate2, endDate2);

        // Act
        List<SchoolYearID> schoolYears = controller.getAllSchoolYearsIDs();

        // Assert
        assertNotNull(schoolYears, "The list of school years should not be null.");

    }

    @Test
    void testGetAllSchoolYears_ListSize_IntegrationTest() throws Exception {

        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionDDDListFactory = new ProgrammeEditionDDDListFactoryImpl();
        IProgrammeEditionDDDFactory programmeEditionDDDFactory = new ProgrammeEditionDDDFactoryImpl();
        IProgrammeEditionRepositoryDDD doubleProgrammeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionDDDListFactory, programmeEditionDDDFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
        IProgrammeDDDFactory IProgrammeFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDRepository programmeRepository = new ProgrammeDDDRepositoryImpl(IProgrammeFactory, IProgrammeRepositoryListFactory);
        ICourseEditionFactoryDDD ICourseEditionFactory_2 = new CourseEditionFactoryDDDImpl();
        ICourseEditionListFactoryDDD ICourseEditionListFactory_2 = new CourseEditionListFactoryDDDImpl();
        ICourseEditionRepositoryDDD courseEditionRepositoryImpl = new CourseEditionRepositoryDDDImpl(ICourseEditionFactory_2, ICourseEditionListFactory_2);
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);

        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrolmentRepository,
                        doubleProgrammeEditionRepository,
                        programmeRepository,
                        courseEditionEnrolmentRepositoryImpl,
                        courseEditionRepositoryImpl,
                        schoolYearRepository,
                        programmeEnrolmentRepository);
        Description description1 = new Description("School Year 24/25");
        Description description2 = new Description("School Year 25/26");
        Date startDate1 = new Date("23-11-2024");
        Date endDate1 = new Date("09-12-2025");
        Date startDate2 = new Date("10-11-2025");
        Date endDate2 = new Date("09-12-2026");
        schoolYearRepository.addSchoolYear(description1, startDate1, endDate1);
        schoolYearRepository.addSchoolYear(description2, startDate2, endDate2);

        // Act
        List<SchoolYearID> schoolYears = controller.getAllSchoolYearsIDs();

        // Assert
        assertEquals(2, schoolYears.size(), "The list of school years should contain exactly 2 years.");

    }

}
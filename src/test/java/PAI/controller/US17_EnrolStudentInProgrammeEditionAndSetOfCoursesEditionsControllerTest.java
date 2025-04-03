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
import PAI.repository.programmeEditionRepository.ProgrammeEditionDDDListFactoryImpl;
import PAI.repository.programmeEditionRepository.ProgrammeEditionRepositoryDDDImpl;
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
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(ProgrammeEditionEnrolmentRepository.class);
        ProgrammeID doubleProgrammeId = mock(ProgrammeID.class);
        SchoolYearID doubleSchoolYearId = mock(SchoolYearID.class);
        StudentID doubleStudentId = mock(StudentID.class);
        ProgrammeEditionID doubleProgrammeEditionId = mock(ProgrammeEditionID.class);
        ProgrammeDDDRepositoryImpl doubleProgrammeList = mock(ProgrammeDDDRepositoryImpl.class);
        ProgrammeEditionRepositoryDDDImpl doubleProgrammeEditionRepository = mock(ProgrammeEditionRepositoryDDDImpl.class);
        CourseEditionEnrolmentRepositoryImpl doubleCourseEditionEnrolmentRepositoryImpl = mock(CourseEditionEnrolmentRepositoryImpl.class);
        CourseEditionRepositoryDDDImpl doubleCourseEditionRepository = mock(CourseEditionRepositoryDDDImpl.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

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
        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(ProgrammeEditionEnrolmentRepository.class);
        ProgrammeDDD doubleProgramme = mock(ProgrammeDDD.class);
        ProgrammeID doubleProgrammeId = mock(ProgrammeID.class);
        SchoolYear doubleSchoolYear = mock(SchoolYear.class);
        SchoolYearID doubleSchoolYearId = mock(SchoolYearID.class);
        StudentID doubleStudentId = mock(StudentID.class);
        ProgrammeDDDRepositoryImpl doubleProgrammeList = mock(ProgrammeDDDRepositoryImpl.class);
        ProgrammeEditionRepositoryDDDImpl doubleProgrammeEditionRepository = mock(ProgrammeEditionRepositoryDDDImpl.class);
        CourseEditionEnrolmentRepositoryImpl doubleCourseEditionEnrolmentRepositoryImpl = mock(CourseEditionEnrolmentRepositoryImpl.class);
        CourseEditionRepositoryDDDImpl doubleCourseEditionRepository = mock(CourseEditionRepositoryDDDImpl.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

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
        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(ProgrammeEditionEnrolmentRepository.class);
        ProgrammeID doubleProgrammeId = mock(ProgrammeID.class);
        SchoolYearID doubleSchoolYearId = mock(SchoolYearID.class);
        StudentID doubleStudentId = mock(StudentID.class);
        ProgrammeDDDRepositoryImpl doubleProgrammeList = mock(ProgrammeDDDRepositoryImpl.class);
        ProgrammeEditionRepositoryDDDImpl doubleProgrammeEditionRepository = mock(ProgrammeEditionRepositoryDDDImpl.class);
        CourseEditionEnrolmentRepositoryImpl doubleCourseEditionEnrolmentRepositoryImpl = mock(CourseEditionEnrolmentRepositoryImpl.class);
        CourseEditionRepositoryDDDImpl doubleCourseEditionRepository = mock(CourseEditionRepositoryDDDImpl.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

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

        boolean result =controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(doubleStudentId, doubleProgrammeId, doubleSchoolYearId);

        //assert
        assertFalse(result);
    }

    @Test
    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_StudentAlreadyEnrolledInProgrammeEdition() {
        // arrange
        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(ProgrammeEditionEnrolmentRepository.class);
        ProgrammeID doubleProgrammeId = mock(ProgrammeID.class);
        SchoolYearID doubleSchoolYearId = mock(SchoolYearID.class);
        StudentID doubleStudentId = mock(StudentID.class);
        ProgrammeEditionID doubleProgrammeEditionId = mock(ProgrammeEditionID.class);
        ProgrammeDDDRepositoryImpl doubleProgrammeList = mock(ProgrammeDDDRepositoryImpl.class);
        ProgrammeEditionRepositoryDDDImpl doubleProgrammeEditionRepository = mock(ProgrammeEditionRepositoryDDDImpl.class);
        CourseEditionEnrolmentRepositoryImpl doubleCourseEditionEnrolmentRepositoryImpl = mock(CourseEditionEnrolmentRepositoryImpl.class);
        CourseEditionRepositoryDDDImpl doubleCourseEditionRepository = mock(CourseEditionRepositoryDDDImpl.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
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
        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(ProgrammeEditionEnrolmentRepository.class);

        ProgrammeDDD doubleProgramme = mock(ProgrammeDDD.class);
        ProgrammeID doubleProgrammeId = mock(ProgrammeID.class);
        SchoolYear doubleSchoolYear = mock(SchoolYear.class);
        SchoolYearID doubleSchoolYearId = mock(SchoolYearID.class);
        Student doubleStudent = mock(Student.class);
        StudentID doubleStudentId = mock(StudentID.class);
        ProgrammeEdition doubleProgrammeEdition = mock(ProgrammeEdition.class);
        ProgrammeEditionID doubleProgrammeEditionId = mock(ProgrammeEditionID.class);
        ProgrammeDDDRepositoryImpl doubleProgrammeList = mock(ProgrammeDDDRepositoryImpl.class);
        ProgrammeEditionRepositoryDDDImpl doubleProgrammeEditionRepository = mock(ProgrammeEditionRepositoryDDDImpl.class);
        CourseEditionEnrolmentRepositoryImpl doubleCourseEditionEnrolmentRepositoryImpl = mock(CourseEditionEnrolmentRepositoryImpl.class);
        CourseEditionRepositoryDDDImpl doubleCourseEditionRepository = mock(CourseEditionRepositoryDDDImpl.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);


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
    void testGetAllProgrammes()  {
        // Arrange
         ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock (ProgrammeEditionEnrolmentRepository.class);
         ProgrammeDDDRepositoryImpl doubleProgrammeList = mock(ProgrammeDDDRepositoryImpl.class);
         ProgrammeEditionRepositoryDDDImpl doubleProgrammeEditionRepository = mock(ProgrammeEditionRepositoryDDDImpl.class);
         CourseEditionEnrolmentRepositoryImpl doubleCourseEditionEnrolmentRepositoryImpl = mock(CourseEditionEnrolmentRepositoryImpl.class);
         CourseEditionRepositoryDDDImpl doubleCourseEditionRepository = mock(CourseEditionRepositoryDDDImpl.class);
         SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
         ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
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
        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock (ProgrammeEditionEnrolmentRepository.class);
        ProgrammeDDDRepositoryImpl doubleProgrammeList = mock(ProgrammeDDDRepositoryImpl.class);
        ProgrammeEditionRepositoryDDDImpl doubleProgrammeEditionRepository = mock(ProgrammeEditionRepositoryDDDImpl.class);
        CourseEditionEnrolmentRepositoryImpl doubleCourseEditionEnrolmentRepositoryImpl = mock(CourseEditionEnrolmentRepositoryImpl.class);
        CourseEditionRepositoryDDDImpl doubleCourseEditionRepository = mock(CourseEditionRepositoryDDDImpl.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
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
        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock (ProgrammeEditionEnrolmentRepository.class);
        ProgrammeDDDRepositoryImpl doubleProgrammeList = mock(ProgrammeDDDRepositoryImpl.class);
        ProgrammeEditionRepositoryDDDImpl doubleProgrammeEditionRepository = mock(ProgrammeEditionRepositoryDDDImpl.class);
        CourseEditionEnrolmentRepositoryImpl doubleCourseEditionEnrolmentRepositoryImpl = mock(CourseEditionEnrolmentRepositoryImpl.class);
        CourseEditionRepositoryDDDImpl doubleCourseEditionRepository = mock(CourseEditionRepositoryDDDImpl.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
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
        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock (ProgrammeEditionEnrolmentRepository.class);
        ProgrammeDDDRepositoryImpl doubleProgrammeList = mock(ProgrammeDDDRepositoryImpl.class);
        ProgrammeEditionRepositoryDDDImpl doubleProgrammeEditionRepository = mock(ProgrammeEditionRepositoryDDDImpl.class);
        CourseEditionEnrolmentRepositoryImpl doubleCourseEditionEnrolmentRepositoryImpl = mock(CourseEditionEnrolmentRepositoryImpl.class);
        CourseEditionRepositoryDDDImpl doubleCourseEditionRepository = mock(CourseEditionRepositoryDDDImpl.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
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
        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock (ProgrammeEditionEnrolmentRepository.class);
        ProgrammeDDDRepositoryImpl doubleProgrammeList = mock(ProgrammeDDDRepositoryImpl.class);
        ProgrammeEditionRepositoryDDDImpl doubleProgrammeEditionRepository = mock(ProgrammeEditionRepositoryDDDImpl.class);
        CourseEditionEnrolmentRepositoryImpl doubleCourseEditionEnrolmentRepositoryImpl = mock(CourseEditionEnrolmentRepositoryImpl.class);
        CourseEditionRepositoryDDDImpl doubleCourseEditionRepository = mock(CourseEditionRepositoryDDDImpl.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
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
        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock (ProgrammeEditionEnrolmentRepository.class);
        ProgrammeDDDRepositoryImpl doubleProgrammeList = mock(ProgrammeDDDRepositoryImpl.class);
        ProgrammeEditionRepositoryDDDImpl doubleProgrammeEditionRepository = mock(ProgrammeEditionRepositoryDDDImpl.class);
        CourseEditionEnrolmentRepositoryImpl doubleCourseEditionEnrolmentRepositoryImpl = mock(CourseEditionEnrolmentRepositoryImpl.class);
        CourseEditionRepositoryDDDImpl doubleCourseEditionRepository = mock(CourseEditionRepositoryDDDImpl.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
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
    void shouldReturnExceptionIfProgrammeEditionEnrollmentRepoIsNull (){
        //arrange
        ProgrammeDDDRepositoryImpl doubleProgrammeList = mock(ProgrammeDDDRepositoryImpl.class);
        ProgrammeEditionRepositoryDDDImpl doubleProgrammeEditionRepository = mock(ProgrammeEditionRepositoryDDDImpl.class);
        CourseEditionEnrolmentRepositoryImpl doubleCourseEditionEnrolmentRepositoryImpl = mock(CourseEditionEnrolmentRepositoryImpl.class);
        CourseEditionRepositoryDDDImpl doubleCourseEditionRepository = mock(CourseEditionRepositoryDDDImpl.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(null,doubleProgrammeEditionRepository,
                    doubleProgrammeList, doubleCourseEditionEnrolmentRepositoryImpl, doubleCourseEditionRepository, doubleSchoolYearRepository, doubleEnrolmentRepository);
        });
        //assert
        assertEquals("Programme edition enrolment repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfProgrammeEditionRepositoryIsNull (){
        //arrange
        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(ProgrammeEditionEnrolmentRepository.class);
        ProgrammeDDDRepositoryImpl doubleProgrammeList = mock(ProgrammeDDDRepositoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl doubleCourseEditionEnrolmentRepositoryImpl = mock(CourseEditionEnrolmentRepositoryImpl.class);
        CourseEditionRepositoryDDDImpl doubleCourseEditionRepository = mock(CourseEditionRepositoryDDDImpl.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(doubleProgrammeEditionEnrolmentRepository,null,
                    doubleProgrammeList, doubleCourseEditionEnrolmentRepositoryImpl, doubleCourseEditionRepository, doubleSchoolYearRepository, doubleEnrolmentRepository);
        });

        //assert
        assertEquals("Programme edition repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfProgrammeListIsNull (){
        //arrange
        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(ProgrammeEditionEnrolmentRepository.class);
        ProgrammeEditionRepositoryDDDImpl doubleProgrammeEditionRepository = mock(ProgrammeEditionRepositoryDDDImpl.class);
        CourseEditionEnrolmentRepositoryImpl doubleCourseEditionEnrolmentRepositoryImpl = mock(CourseEditionEnrolmentRepositoryImpl.class);
        CourseEditionRepositoryDDDImpl doubleCourseEditionRepository = mock(CourseEditionRepositoryDDDImpl.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(doubleProgrammeEditionEnrolmentRepository, doubleProgrammeEditionRepository,
                    null, doubleCourseEditionEnrolmentRepositoryImpl, doubleCourseEditionRepository, doubleSchoolYearRepository, doubleEnrolmentRepository);
        });

        //assert
        assertEquals("Programme list cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfCourseEditionEnrollmentRepositoryIsNull (){
        //arrange
        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(ProgrammeEditionEnrolmentRepository.class);
        ProgrammeEditionRepositoryDDDImpl doubleProgrammeEditionRepository = mock(ProgrammeEditionRepositoryDDDImpl.class);
        ProgrammeDDDRepositoryImpl doubleProgrammeList = mock(ProgrammeDDDRepositoryImpl.class);
        CourseEditionRepositoryDDDImpl doubleCourseEditionRepository = mock(CourseEditionRepositoryDDDImpl.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(doubleProgrammeEditionEnrolmentRepository, doubleProgrammeEditionRepository,
                    doubleProgrammeList, null, doubleCourseEditionRepository, doubleSchoolYearRepository, doubleEnrolmentRepository);
        });

        //assert
        assertEquals("Course edition enrolment repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfCourseEditionRepositoryIsNull (){
        //arrange
        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(ProgrammeEditionEnrolmentRepository.class);
        ProgrammeEditionRepositoryDDDImpl doubleProgrammeEditionRepository = mock(ProgrammeEditionRepositoryDDDImpl.class);
        ProgrammeDDDRepositoryImpl doubleProgrammeList = mock(ProgrammeDDDRepositoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl doubleCourseEditionEnrolmentRepositoryImpl = mock(CourseEditionEnrolmentRepositoryImpl.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(doubleProgrammeEditionEnrolmentRepository, doubleProgrammeEditionRepository,
                    doubleProgrammeList, doubleCourseEditionEnrolmentRepositoryImpl, null, doubleSchoolYearRepository, doubleEnrolmentRepository);
        });

        //assert
        assertEquals("Course edition repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfSchoolYearRepositoryIsNull (){
        //arrange
        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(ProgrammeEditionEnrolmentRepository.class);
        ProgrammeEditionRepositoryDDDImpl doubleProgrammeEditionRepository = mock(ProgrammeEditionRepositoryDDDImpl.class);
        ProgrammeDDDRepositoryImpl doubleProgrammeList = mock(ProgrammeDDDRepositoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl doubleCourseEditionEnrolmentRepositoryImpl = mock(CourseEditionEnrolmentRepositoryImpl.class);
        CourseEditionRepositoryDDDImpl doubleCourseEditionRepository = mock(CourseEditionRepositoryDDDImpl.class);
        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(doubleProgrammeEditionEnrolmentRepository, doubleProgrammeEditionRepository,
                    doubleProgrammeList, doubleCourseEditionEnrolmentRepositoryImpl, doubleCourseEditionRepository, null, doubleEnrolmentRepository);
        });

        //assert
        assertEquals("School year repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfEnrolmentRepositoryIsNull (){
        //arrange
        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(ProgrammeEditionEnrolmentRepository.class);
        ProgrammeEditionRepositoryDDDImpl doubleProgrammeEditionRepository = mock(ProgrammeEditionRepositoryDDDImpl.class);
        ProgrammeDDDRepositoryImpl doubleProgrammeList = mock(ProgrammeDDDRepositoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl doubleCourseEditionEnrolmentRepositoryImpl = mock(CourseEditionEnrolmentRepositoryImpl.class);
        CourseEditionRepositoryDDDImpl doubleCourseEditionRepository = mock(CourseEditionRepositoryDDDImpl.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);

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
        ProgrammeEditionRepositoryDDDImpl doubleProgrammeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionDDDListFactory,programmeEditionDDDFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
        IProgrammeDDDFactory IProgrammeFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        ProgrammeDDDRepositoryImpl programmeRepository = new ProgrammeDDDRepositoryImpl(IProgrammeFactory, IProgrammeRepositoryListFactory);
        ICourseEditionFactoryDDD ICourseEditionFactory_2 = new CourseEditionFactoryDDDImpl();
        ICourseEditionListFactoryDDD ICourseEditionListFactory_2 = new CourseEditionListFactoryDDDImpl();
        CourseEditionRepositoryDDDImpl courseEditionRepositoryImpl = new CourseEditionRepositoryDDDImpl(ICourseEditionFactory_2,ICourseEditionListFactory_2);
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory =  new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);

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
        Location location  = new Location("Porto");
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
        Date startDate = new Date ("23-11-2024");
        Date endDate = new Date ("09-12-2025");
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
        ProgrammeDDD programme1 = new ProgrammeDDD(nameWithNumbersAndSpecialChars,pAcronym, quantEcts, quantSemesters,
                degreeTypeID, departmentID, teacherID1);

        if (!programmeEnrolmentRepository.isStudentEnrolled(studentId, programmeId)) {
            programmeEnrolmentRepository.enrolStudents(studentId, amId, programmeId,date);
        }
        Date date1 = new Date ("01-04-2023");
        Date date2 = new Date ("01-04-2024");
        StudyPlanID studyPlanID1 = new StudyPlanID(programmeId,date1);
        StudyPlanID studyPlanID2 = new StudyPlanID(programmeId,date2);
        doubleProgrammeEditionRepository.createProgrammeEdition(programmeId, schoolYearId);
        Optional<ProgrammeEditionID> pe1Opt = doubleProgrammeEditionRepository.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(programmeId, schoolYearId);
        ProgrammeEditionID pe1 = pe1Opt.get();

        CourseID courseId2 = new CourseID();
        CourseID courseId1 = new CourseID();
        ProgrammeEditionID programmeEditionId = new ProgrammeEditionID(programmeId,schoolYearId);
        CourseInStudyPlanID courseInStudyPlanID1 = new CourseInStudyPlanID (courseId1,studyPlanID1);
        CourseInStudyPlanID courseInStudyPlanID2 = new CourseInStudyPlanID (courseId2,studyPlanID2);
        CourseEditionID courseEditionId1 = new CourseEditionID(programmeEditionId,courseInStudyPlanID1);
        CourseEditionID courseEditionId2 = new CourseEditionID(programmeEditionId,courseInStudyPlanID2);
        CourseEditionDDD courseEdition1 = new CourseEditionDDD(courseEditionId1,courseInStudyPlanID1,programmeEditionId);
        CourseEditionDDD courseEdition2 = new CourseEditionDDD(courseEditionId2,courseInStudyPlanID1,programmeEditionId);
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
        ProgrammeEditionRepositoryDDDImpl doubleProgrammeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionDDDListFactory,programmeEditionDDDFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
        IProgrammeDDDFactory IProgrammeFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        ProgrammeDDDRepositoryImpl programmeRepository = new ProgrammeDDDRepositoryImpl(IProgrammeFactory, IProgrammeRepositoryListFactory);
        ICourseEditionFactoryDDD ICourseEditionFactory_2 = new CourseEditionFactoryDDDImpl();
        ICourseEditionListFactoryDDD ICourseEditionListFactory_2 = new CourseEditionListFactoryDDDImpl();
        CourseEditionRepositoryDDDImpl courseEditionRepositoryImpl = new CourseEditionRepositoryDDDImpl(ICourseEditionFactory_2,ICourseEditionListFactory_2);
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory =  new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);

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
        Location location  = new Location("Porto");
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
        Date startDate = new Date ("23-11-2024");
        Date endDate = new Date ("09-12-2025");
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
        ProgrammeDDD programme1 = new ProgrammeDDD(nameWithNumbersAndSpecialChars,pAcronym, quantEcts, quantSemesters,
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
        ProgrammeEditionRepositoryDDDImpl doubleProgrammeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionDDDListFactory,programmeEditionDDDFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
        IProgrammeDDDFactory IProgrammeFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        ProgrammeDDDRepositoryImpl programmeRepository = new ProgrammeDDDRepositoryImpl(IProgrammeFactory, IProgrammeRepositoryListFactory);
        ICourseEditionFactoryDDD ICourseEditionFactory_2 = new CourseEditionFactoryDDDImpl();
        ICourseEditionListFactoryDDD ICourseEditionListFactory_2 = new CourseEditionListFactoryDDDImpl();
        CourseEditionRepositoryDDDImpl courseEditionRepositoryImpl = new CourseEditionRepositoryDDDImpl(ICourseEditionFactory_2,ICourseEditionListFactory_2);
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory =  new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);

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
        Location location  = new Location("Porto");
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
        Date startDate = new Date ("23-11-2024");
        Date endDate = new Date ("09-12-2025");
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
        ProgrammeDDD programme1 = new ProgrammeDDD(nameWithNumbersAndSpecialChars,pAcronym, quantEcts, quantSemesters,
                degreeTypeID, departmentID, teacherID1);

        if (!programmeEnrolmentRepository.isStudentEnrolled(studentId, programmeId)) {
            programmeEnrolmentRepository.enrolStudents(studentId, amId, programmeId,date);
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
        ProgrammeEditionRepositoryDDDImpl doubleProgrammeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionDDDListFactory,programmeEditionDDDFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
        IProgrammeDDDFactory IProgrammeFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        ProgrammeDDDRepositoryImpl programmeRepository = new ProgrammeDDDRepositoryImpl(IProgrammeFactory, IProgrammeRepositoryListFactory);
        ICourseEditionFactoryDDD ICourseEditionFactory_2 = new CourseEditionFactoryDDDImpl();
        ICourseEditionListFactoryDDD ICourseEditionListFactory_2 = new CourseEditionListFactoryDDDImpl();
        CourseEditionRepositoryDDDImpl courseEditionRepositoryImpl = new CourseEditionRepositoryDDDImpl(ICourseEditionFactory_2,ICourseEditionListFactory_2);
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory =  new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);

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
        Location location  = new Location("Porto");
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
        Date startDate = new Date ("23-11-2024");
        Date endDate = new Date ("09-12-2025");
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
        ProgrammeDDD programme1 = new ProgrammeDDD(nameWithNumbersAndSpecialChars,pAcronym, quantEcts, quantSemesters,
                degreeTypeID, departmentID, teacherID1);

        if (!programmeEnrolmentRepository.isStudentEnrolled(studentId, programmeId)) {
            programmeEnrolmentRepository.enrolStudents(studentId, amId, programmeId,date);
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
        ProgrammeEditionRepositoryDDDImpl doubleProgrammeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionDDDListFactory,programmeEditionDDDFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
        IProgrammeDDDFactory IProgrammeFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        ProgrammeDDDRepositoryImpl programmeRepository = new ProgrammeDDDRepositoryImpl(IProgrammeFactory, IProgrammeRepositoryListFactory);
        ICourseEditionFactoryDDD ICourseEditionFactory_2 = new CourseEditionFactoryDDDImpl();
        ICourseEditionListFactoryDDD ICourseEditionListFactory_2 = new CourseEditionListFactoryDDDImpl();
        CourseEditionRepositoryDDDImpl courseEditionRepositoryImpl = new CourseEditionRepositoryDDDImpl(ICourseEditionFactory_2,ICourseEditionListFactory_2);
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory =  new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);


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
            programmeEnrolmentRepository.enrolStudents(studentId, amId, programmeId,date);
        }
        Date date1 = new Date ("01-04-2023");
        Date date2 = new Date ("01-04-2024");
        StudyPlanID studyPlanID1 = new StudyPlanID(programmeId,date1);
        StudyPlanID studyPlanID2 = new StudyPlanID(programmeId,date2);
        doubleProgrammeEditionRepository.createProgrammeEdition(programmeId, schoolYearId);
        Optional<ProgrammeEditionID> pe1Opt = doubleProgrammeEditionRepository.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(programmeId, schoolYearId);
        ProgrammeEditionID pe1 = pe1Opt.get();

        CourseID courseId2 = new CourseID();
        CourseID courseId1 = new CourseID();
        ProgrammeEditionID programmeEditionId = new ProgrammeEditionID(programmeId,schoolYearId);
        CourseInStudyPlanID courseInStudyPlanID1 = new CourseInStudyPlanID (courseId1,studyPlanID1);
        CourseInStudyPlanID courseInStudyPlanID2 = new CourseInStudyPlanID (courseId2,studyPlanID2);
        CourseEditionID courseEditionId1 = new CourseEditionID(programmeEditionId,courseInStudyPlanID1);
        CourseEditionID courseEditionId2 = new CourseEditionID(programmeEditionId,courseInStudyPlanID2);

        courseEditionRepositoryImpl.createAndSaveCourseEdition(courseInStudyPlanID1, pe1);
        courseEditionRepositoryImpl.createAndSaveCourseEdition(courseInStudyPlanID2, pe1);

        courseEditionRepositoryImpl.createAndSaveCourseEdition(courseInStudyPlanID1, pe1);
        courseEditionRepositoryImpl.createAndSaveCourseEdition(courseInStudyPlanID2, pe1);

        courseEditionEnrolmentRepositoryImpl.enrolStudentInACourseEdition(studentId,courseEditionId1);
        courseEditionEnrolmentRepositoryImpl.enrolStudentInACourseEdition(studentId,courseEditionId2);
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
        ProgrammeEditionRepositoryDDDImpl doubleProgrammeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionDDDListFactory,programmeEditionDDDFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
        IProgrammeDDDFactory IProgrammeFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        ProgrammeDDDRepositoryImpl programmeRepository = new ProgrammeDDDRepositoryImpl(IProgrammeFactory, IProgrammeRepositoryListFactory);
        ICourseEditionFactoryDDD ICourseEditionFactory_2 = new CourseEditionFactoryDDDImpl();
        ICourseEditionListFactoryDDD ICourseEditionListFactory_2 = new CourseEditionListFactoryDDDImpl();
        CourseEditionRepositoryDDDImpl courseEditionRepositoryImpl = new CourseEditionRepositoryDDDImpl(ICourseEditionFactory_2,ICourseEditionListFactory_2);
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory =  new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);

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
    ProgrammeEditionRepositoryDDDImpl doubleProgrammeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionDDDListFactory,programmeEditionDDDFactory);
    ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
    ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
    ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
    IProgrammeDDDFactory IProgrammeFactory = new ProgrammeDDDFactoryImpl();
    IProgrammeDDDRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
    ProgrammeDDDRepositoryImpl programmeRepository = new ProgrammeDDDRepositoryImpl(IProgrammeFactory, IProgrammeRepositoryListFactory);
    ICourseEditionFactoryDDD ICourseEditionFactory_2 = new CourseEditionFactoryDDDImpl();
    ICourseEditionListFactoryDDD ICourseEditionListFactory_2 = new CourseEditionListFactoryDDDImpl();
    CourseEditionRepositoryDDDImpl courseEditionRepositoryImpl = new CourseEditionRepositoryDDDImpl(ICourseEditionFactory_2,ICourseEditionListFactory_2);
    ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
    ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory =  new CourseEditionEnrolmentListFactoryImpl();
    CourseEditionEnrolmentRepositoryImpl courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
    SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
    SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
    SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
    IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
    IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
    ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);

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
        ProgrammeEditionRepositoryDDDImpl doubleProgrammeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionDDDListFactory,programmeEditionDDDFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
        IProgrammeDDDFactory IProgrammeFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        ProgrammeDDDRepositoryImpl programmeRepository = new ProgrammeDDDRepositoryImpl(IProgrammeFactory, IProgrammeRepositoryListFactory);
        ICourseEditionFactoryDDD ICourseEditionFactory_2 = new CourseEditionFactoryDDDImpl();
        ICourseEditionListFactoryDDD ICourseEditionListFactory_2 = new CourseEditionListFactoryDDDImpl();
        CourseEditionRepositoryDDDImpl courseEditionRepositoryImpl = new CourseEditionRepositoryDDDImpl(ICourseEditionFactory_2,ICourseEditionListFactory_2);
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory =  new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);

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
        Date startDate1 = new Date ("23-11-2024");
        Date endDate1 = new Date ("09-12-2025");
        Date startDate2 = new Date ("10-11-2025");
        Date endDate2 = new Date ("09-12-2026");
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
        ProgrammeEditionRepositoryDDDImpl doubleProgrammeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionDDDListFactory,programmeEditionDDDFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
        IProgrammeDDDFactory IProgrammeFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        ProgrammeDDDRepositoryImpl programmeRepository = new ProgrammeDDDRepositoryImpl(IProgrammeFactory, IProgrammeRepositoryListFactory);
        ICourseEditionFactoryDDD ICourseEditionFactory_2 = new CourseEditionFactoryDDDImpl();
        ICourseEditionListFactoryDDD ICourseEditionListFactory_2 = new CourseEditionListFactoryDDDImpl();
        CourseEditionRepositoryDDDImpl courseEditionRepositoryImpl = new CourseEditionRepositoryDDDImpl(ICourseEditionFactory_2,ICourseEditionListFactory_2);
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory =  new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);

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
        Date startDate1 = new Date ("23-11-2024");
        Date endDate1 = new Date ("09-12-2025");
        Date startDate2 = new Date ("10-11-2025");
        Date endDate2 = new Date ("09-12-2026");
        schoolYearRepository.addSchoolYear(description1, startDate1, endDate1);
        schoolYearRepository.addSchoolYear(description2, startDate2, endDate2);

        // Act
        List<SchoolYearID> schoolYears = controller.getAllSchoolYearsIDs();

        // Assert
        assertEquals(2, schoolYears.size(), "The list of school years should contain exactly 2 years.");

    }

//    @Test
//    void shouldReturnExceptionIfProgrammeEditionEnrollmentRepoIsNull_IntegrationTest (){
//        //arrange
//        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
//        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
//        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
//        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
//        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
//        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
//        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
//        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
//        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
//        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
//        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
//        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
//        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
//        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
//        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);
//
//        //act
//        Exception exception = assertThrows(IllegalStateException.class, () -> {
//            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(null,programmeEditionRepository,
//                    programmeRepository, courseEditionEnrolmentRepository, courseEditionRepository, schoolYearRepository, programmeEnrolmentRepository);
//        });
//        //assert
//        assertEquals("Programme edition enrolment repository cannot be null.", exception.getMessage());
//    }
//
//    @Test
//    void shouldReturnExceptionIfProgrammeEditionRepositoryIsNull_IntegrationTest (){
//        //arrange
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();;
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
//        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
//        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
//        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
//        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
//        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
//        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
//        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
//        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
//        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
//        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
//        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
//        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
//        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);
//
//        //act
//        Exception exception = assertThrows(IllegalStateException.class, () -> {
//            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrolmentRepository,null,
//                    programmeRepository, courseEditionEnrolmentRepository, courseEditionRepository, schoolYearRepository, programmeEnrolmentRepository);
//        });
//
//        //assert
//        assertEquals("Programme edition repository cannot be null.", exception.getMessage());
//    }
//
//    @Test
//    void shouldReturnExceptionIfProgrammeListIsNull_IntegrationTest (){
//        //arrange
//        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
//        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();;
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
//        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
//        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
//        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
//        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
//        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
//        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
//        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
//        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
//        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
//        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
//        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
//        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);
//
//        //act
//        Exception exception = assertThrows(IllegalStateException.class, () -> {
//            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrolmentRepository,programmeEditionRepository,
//                    null, courseEditionEnrolmentRepository, courseEditionRepository, schoolYearRepository,programmeEnrolmentRepository);
//        });
//
//        //assert
//        assertEquals("Programme list cannot be null.", exception.getMessage());
//    }
//
//    @Test
//    void shouldReturnExceptionIfCourseEditionEnrollmentRepositoryIsNull_IntegrationTest (){
//        //arranje
//        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
//        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
//        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
//        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
//        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
//        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
//        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
//        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
//        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
//        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
//        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
//        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);
//        //act
//        Exception exception = assertThrows(IllegalStateException.class, () -> {
//            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrolmentRepository,programmeEditionRepository,
//                    programmeRepository, null, courseEditionRepository, schoolYearRepository,programmeEnrolmentRepository);
//        });
//
//        //assert
//        assertEquals("Course edition enrolment repository cannot be null.", exception.getMessage());
//    }
//
//    @Test
//    void shouldReturnExceptionIfCourseEditionRepositoryIsNull_IntegrationTest (){
//        //arranje
//        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
//        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
//        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
//        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
//        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
//        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
//        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
//        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
//        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
//        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
//        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
//        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);
//
//        //act
//        Exception exception = assertThrows(IllegalStateException.class, () -> {
//            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrolmentRepository,programmeEditionRepository,
//                    programmeRepository, courseEditionEnrolmentRepository, null, schoolYearRepository,programmeEnrolmentRepository);
//        });
//
//        //assert
//        assertEquals("Course edition repository cannot be null.", exception.getMessage());
//    }
//
//    @Test
//    void shouldReturnExceptionIfSchoolYearRepositoryIsNull_IntegrationTest (){
//        //arranje
//        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
//        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
//        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
//        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
//        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
//        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
//        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
//        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
//        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
//        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
//        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
//        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);
//
//        //act
//        Exception exception = assertThrows(IllegalStateException.class, () -> {
//            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrolmentRepository,programmeEditionRepository,
//                    programmeRepository, courseEditionEnrolmentRepository, courseEditionRepository, null, programmeEnrolmentRepository);
//        });
//
//        //assert
//        assertEquals("School year repository cannot be null.", exception.getMessage());
//    }

//    @Test
//    void shouldReturnExceptionIfProgrammeEnrollmentRepositoryIsNull_IntegrationTest (){
//        //arranje
//        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
//        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
//        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
//        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
//        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
//        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
//        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
//        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
//        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
//        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
//        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
//        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
//
//        //act
//        Exception exception = assertThrows(IllegalStateException.class, () -> {
//            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrolmentRepository,programmeEditionRepository,
//                    programmeRepository, courseEditionEnrolmentRepository, courseEditionRepository, schoolYearRepository, null);
//        });
//
//        //assert
//        assertEquals("Enrolment repository cannot be null.", exception.getMessage());
//    }

}


//package PAI.controller;
//
//
//import PAI.VOs.*;
//
//import PAI.domain.*;
//import PAI.factory.*;
//import PAI.repository.*;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.*;
//
//class US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsControllerTest {
//
//    @Test
//    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_Success() {
//        // Arrange
//        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(ProgrammeEditionEnrolmentRepository.class);
//        Programme doubleProgramme = mock(Programme.class);
//        SchoolYear doubleSchoolYear = mock(SchoolYear.class);
//        StudentID doubleStudent = mock(StudentID.class);
//        ProgrammeEdition doubleProgrammeEdition = mock(ProgrammeEdition.class);
//        ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
//        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
//        CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
//        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
//        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
//        ProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
//
//        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
//                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
//                        programmeEditionEnrolmentRepository,
//                        doubleProgrammeEditionRepository,
//                        doubleProgrammeList,
//                        doubleCourseEditionEnrolmentRepository,
//                        doubleCourseEditionRepository,
//                        doubleSchoolYearRepository,
//                        doubleProgrammeEnrolmentRepository);
//
//        CourseEdition doubleCe1 = mock(CourseEdition.class);
//        CourseEdition doubleCe2 = mock(CourseEdition.class);
//
//        when(doubleProgrammeEnrolmentRepository.isStudentEnrolled(doubleStudent, doubleProgramme))
//                .thenReturn(true);
//
//        when(doubleProgrammeEditionRepository.findProgrammeEditionBySchoolYearAndProgramme(doubleProgramme, doubleSchoolYear))
//                .thenReturn(Optional.of(doubleProgrammeEdition));
//
//        when(doubleCourseEditionRepository.findCourseEditionsByProgrammeEdition(doubleProgrammeEdition)).thenReturn(List.of(doubleCe1, doubleCe2));
//
//        when(programmeEditionEnrolmentRepository.isStudentEnrolledInThisProgrammeEdition(doubleStudent, doubleProgrammeEdition)).thenReturn(false);
//
//        when(doubleCourseEditionEnrolmentRepository.isStudentEnrolledInCourseEdition(doubleStudent, doubleCe1)).thenReturn(true);
//
//        when(doubleCourseEditionEnrolmentRepository.isStudentEnrolledInCourseEdition(doubleStudent, doubleCe2)).thenReturn(true);
//
//        // Act
//        boolean result = controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(doubleStudent, doubleProgramme, doubleSchoolYear);
//        boolean result2 = doubleCourseEditionEnrolmentRepository.isStudentEnrolledInCourseEdition(doubleStudent, doubleCe1);
//        boolean result3 = doubleCourseEditionEnrolmentRepository.isStudentEnrolledInCourseEdition(doubleStudent, doubleCe2);
//
//        // Assert
//        assertTrue(result, "Student should be enrolled in Programme.");
//        assertTrue(result2, "The Student should be enrolled in CourseEdition 1.");
//        assertTrue(result3, "The Student should be enrolled in CourseEdition 2.");
//    }
//
//    @Test
//    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_StudentNotEnrolledInProgramme() {
//        // arrange
//        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(ProgrammeEditionEnrolmentRepository.class);
//        Programme doubleProgramme = mock(Programme.class);
//        SchoolYear doubleSchoolYear = mock(SchoolYear.class);
//        Student doubleStudent = mock(Student.class);
//        ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
//        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
//        CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
//        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
//        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
//        ProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
//
//        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
//                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
//                        doubleProgrammeEditionEnrolmentRepository,
//                        doubleProgrammeEditionRepository,
//                        doubleProgrammeList,
//                        doubleCourseEditionEnrolmentRepository,
//                        doubleCourseEditionRepository,
//                        doubleSchoolYearRepository,
//                        doubleProgrammeEnrolmentRepository);
//
//        doubleProgrammeEditionRepository.createProgrammeEdition(doubleProgramme, doubleSchoolYear);
//
//        // act
//        boolean result = controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(doubleStudent, doubleProgramme, doubleSchoolYear);
//
//        //assert
//        assertFalse(result);
//    }
//
//    @Test
//    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_ProgrammeEditionNotFound() {
//        // arrange
//        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(ProgrammeEditionEnrolmentRepository.class);
//        Programme doubleProgramme = mock(Programme.class);
//        SchoolYear doubleSchoolYear = mock(SchoolYear.class);
//        Student doubleStudent = mock(Student.class);
//        ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
//        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
//        CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
//        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
//        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
//        ProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
//
//        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
//                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
//                        doubleProgrammeEditionEnrolmentRepository,
//                        doubleProgrammeEditionRepository,
//                        doubleProgrammeList,
//                        doubleCourseEditionEnrolmentRepository,
//                        doubleCourseEditionRepository,
//                        doubleSchoolYearRepository,
//                        doubleProgrammeEnrolmentRepository);
//
//        when(doubleProgrammeEnrolmentRepository.isStudentEnrolled(doubleStudent, doubleProgramme))
//                .thenReturn(true);
//
//        // act
//
//        boolean result =controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(doubleStudent, doubleProgramme, doubleSchoolYear);
//
//        //assert
//        assertFalse(result);
//    }
//
//    @Test
//    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_StudentAlreadyEnrolledInProgrammeEdition() {
//        // arrange
//        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(ProgrammeEditionEnrolmentRepository.class);
//        Programme doubleProgramme = mock(Programme.class);
//        SchoolYear doubleSchoolYear = mock(SchoolYear.class);
//        Student doubleStudent = mock(Student.class);
//        ProgrammeEdition doubleProgrammeEdition = mock(ProgrammeEdition.class);
//        ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
//        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
//        CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
//        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
//        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
//        ProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
//        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
//                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
//                        doubleProgrammeEditionEnrolmentRepository,
//                        doubleProgrammeEditionRepository,
//                        doubleProgrammeList,
//                        doubleCourseEditionEnrolmentRepository,
//                        doubleCourseEditionRepository,
//                        doubleSchoolYearRepository,
//                        doubleProgrammeEnrolmentRepository);
//
//        when(doubleProgrammeEnrolmentRepository.isStudentEnrolled(doubleStudent, doubleProgramme)).thenReturn(true);
//        when(doubleProgrammeEditionRepository.findProgrammeEditionBySchoolYearAndProgramme(doubleProgramme, doubleSchoolYear)).thenReturn(Optional.of(doubleProgrammeEdition));
//
//        when(doubleProgrammeEditionEnrolmentRepository.isStudentEnrolledInThisProgrammeEdition(doubleStudent, doubleProgrammeEdition)).thenReturn(true);
//        // act
//        boolean result = controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(doubleStudent, doubleProgramme, doubleSchoolYear);
//
//        //assert
//        assertFalse(result);
//    }
//
//    @Test
//    void testEnrollStudentInCourseEditionAndSetOfCoursesEditions_StudentAlreadyEnrolledInCourseEdition() {
//        // Arrange
//        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(ProgrammeEditionEnrolmentRepository.class);
//        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
//        Programme doubleProgramme = mock(Programme.class);
//        SchoolYear doubleSchoolYear = mock(SchoolYear.class);
//        Student doubleStudent = mock(Student.class);
//        ProgrammeEdition doubleProgrammeEdition = mock(ProgrammeEdition.class);
//        ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
//        CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
//        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
//        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
//        ProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
//
//
//        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
//                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
//                        doubleProgrammeEditionEnrolmentRepository,
//                        doubleProgrammeEditionRepository,
//                        doubleProgrammeList,
//                        doubleCourseEditionEnrolmentRepository,
//                        doubleCourseEditionRepository,
//                        doubleSchoolYearRepository,
//                        doubleProgrammeEnrolmentRepository);
//
//        CourseEdition ce1 = mock(CourseEdition.class);
//        CourseEdition ce2 = mock(CourseEdition.class);
//
//        when(doubleProgrammeEnrolmentRepository.isStudentEnrolled(doubleStudent, doubleProgramme))
//                .thenReturn(true);
//
//
//        when(doubleProgrammeEditionRepository.findProgrammeEditionBySchoolYearAndProgramme(doubleProgramme, doubleSchoolYear))
//                .thenReturn(Optional.of(doubleProgrammeEdition));
//
//
//        when(doubleCourseEditionRepository.findCourseEditionsByProgrammeEdition(doubleProgrammeEdition))
//                .thenReturn(List.of(ce1, ce2));
//
//
//        when(doubleCourseEditionEnrolmentRepository.findByStudentAndEdition(doubleStudent, ce1))
//                .thenReturn(Optional.of(new CourseEditionEnrolment(doubleStudent, ce1)));
//
//        when(doubleCourseEditionEnrolmentRepository.findByStudentAndEdition(doubleStudent, ce2))
//                .thenReturn(Optional.of(new CourseEditionEnrolment(doubleStudent, ce2)));
//
//
//        doThrow(new IllegalStateException("This course edition enrolment is already in the list."))
//                .when(doubleCourseEditionEnrolmentRepository)
//                .enrolStudentInProgrammeCourseEditions(any(Student.class), anyList());
//
//        // Act + Assert
//        Exception exception = assertThrows(IllegalStateException.class, () -> {
//            controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(doubleStudent, doubleProgramme, doubleSchoolYear);
//        });
//
//        assertEquals("This course edition enrolment is already in the list.", exception.getMessage());
//    }
//
//    @Test
//    void testGetAllProgrammes()  {
//        // Arrange
//         ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
//         ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock (ProgrammeEditionEnrolmentRepository.class);
//         ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
//         CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
//         CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
//         SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
//         ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
//         US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
//                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
//                        doubleProgrammeEditionEnrolmentRepository,
//                        doubleProgrammeEditionRepository,
//                        doubleProgrammeList,
//                        doubleCourseEditionEnrolmentRepository,
//                        doubleCourseEditionRepository,
//                        doubleSchoolYearRepository,
//                        doubleEnrolmentRepository);
//
//        Programme doubleProgramme1 = mock(Programme.class);
//        Programme doubleProgramme2 = mock(Programme.class);
//
//        when(doubleProgrammeList.getAllProgrammes()).thenReturn(List.of(doubleProgramme1, doubleProgramme2));
//
//        // Act
//        List<Programme> doubleProgrammes = controller.getAllProgrammes();
//
//        // Assert
//        assertNotNull(doubleProgrammes, "The list of programmes should not be null.");
//    }
//
//    @Test
//    void testGetAllProgrammes_SizeEqualsTwo() throws Exception {
//        // Arrange
//        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
//        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock (ProgrammeEditionEnrolmentRepository.class);
//        ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
//        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
//        CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
//        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
//        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
//        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
//                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
//                        doubleProgrammeEditionEnrolmentRepository,
//                        doubleProgrammeEditionRepository,
//                        doubleProgrammeList,
//                        doubleCourseEditionEnrolmentRepository,
//                        doubleCourseEditionRepository,
//                        doubleSchoolYearRepository,
//                        doubleEnrolmentRepository);
//
//        Programme doubleProgramme1 = mock(Programme.class);
//        Programme doubleProgramme2 = mock(Programme.class);
//
//        when(doubleProgrammeList.getAllProgrammes()).thenReturn(List.of(doubleProgramme1, doubleProgramme2));
//
//        // Act
//        List<Programme> doubleProgrammes = controller.getAllProgrammes();
//
//        // Assert
//        assertEquals(2, doubleProgrammes.size(), "The list of programmes should contain exactly 2 programmes.");
//    }
//
//    @Test
//    void testGetAllProgrammes_ContainsAllProgrammes() throws Exception {
//        // Arrange
//        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
//        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock (ProgrammeEditionEnrolmentRepository.class);
//        ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
//        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
//        CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
//        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
//        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
//        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
//                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
//                        doubleProgrammeEditionEnrolmentRepository,
//                        doubleProgrammeEditionRepository,
//                        doubleProgrammeList,
//                        doubleCourseEditionEnrolmentRepository,
//                        doubleCourseEditionRepository,
//                        doubleSchoolYearRepository,
//                        doubleEnrolmentRepository);
//
//        Programme doubleProgramme1 = mock(Programme.class);
//        Programme doubleProgramme2 = mock(Programme.class);
//
//        when(doubleProgrammeList.getAllProgrammes()).thenReturn(List.of(doubleProgramme1, doubleProgramme2));
//
//        // Act
//        List<Programme> doubleProgrammes = controller.getAllProgrammes();
//
//        // Assert
//        assertTrue(doubleProgrammes.contains(doubleProgramme1));
//        assertTrue(doubleProgrammes.contains(doubleProgramme2));
//
//    }
//
//    @Test
//    void testGetAllSchoolYears_NotNull() throws Exception {
//        // Arrange
//        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
//        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock (ProgrammeEditionEnrolmentRepository.class);
//        ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
//        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
//        CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
//        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
//        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
//        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
//                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
//                        doubleProgrammeEditionEnrolmentRepository,
//                        doubleProgrammeEditionRepository,
//                        doubleProgrammeList,
//                        doubleCourseEditionEnrolmentRepository,
//                        doubleCourseEditionRepository,
//                        doubleSchoolYearRepository,
//                        doubleEnrolmentRepository);
//
//        SchoolYear doubleSchoolYear1 = mock(SchoolYear.class);
//        SchoolYear doubleSchoolYear2 = mock(SchoolYear.class);
//
//        when(doubleSchoolYearRepository.getAllSchoolYears()).thenReturn(List.of(doubleSchoolYear1, doubleSchoolYear2));
//
//        // Act
//        List<SchoolYear> doubleSchoolYears = controller.getAllSchoolYears();
//
//        // Assert
//        assertNotNull(doubleSchoolYears, "The list of school years should not be null.");
//    }
//
//    @Test
//    void testGetAllSchoolYears_SizeEqualsTwo() throws Exception {
//        // Arrange
//        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
//        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock (ProgrammeEditionEnrolmentRepository.class);
//        ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
//        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
//        CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
//        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
//        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
//        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
//                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
//                        doubleProgrammeEditionEnrolmentRepository,
//                        doubleProgrammeEditionRepository,
//                        doubleProgrammeList,
//                        doubleCourseEditionEnrolmentRepository,
//                        doubleCourseEditionRepository,
//                        doubleSchoolYearRepository,
//                        doubleEnrolmentRepository);
//
//        SchoolYear doubleSchoolYear1 = mock(SchoolYear.class);
//        SchoolYear doubleSchoolYear2 = mock(SchoolYear.class);
//
//        when(doubleSchoolYearRepository.getAllSchoolYears()).thenReturn(List.of(doubleSchoolYear1, doubleSchoolYear2));
//
//        // Act
//        List<SchoolYear> doubleSchoolYears = controller.getAllSchoolYears();
//
//        // Assert
//        assertEquals(2, doubleSchoolYears.size(), "The list of school years should contain exactly 2 years.");
//    }
//
//    @Test
//    void testGetAllSchoolYears_ContainsAllSchoolYears() throws Exception {
//        // Arrange
//        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
//        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock (ProgrammeEditionEnrolmentRepository.class);
//        ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
//        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
//        CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
//        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
//        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
//        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
//                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
//                        doubleProgrammeEditionEnrolmentRepository,
//                        doubleProgrammeEditionRepository,
//                        doubleProgrammeList,
//                        doubleCourseEditionEnrolmentRepository,
//                        doubleCourseEditionRepository,
//                        doubleSchoolYearRepository,
//                        doubleEnrolmentRepository);
//
//        SchoolYear doubleSchoolYear1 = mock(SchoolYear.class);
//        SchoolYear doubleSchoolYear2 = mock(SchoolYear.class);
//
//        when(doubleSchoolYearRepository.getAllSchoolYears()).thenReturn(List.of(doubleSchoolYear1, doubleSchoolYear2));
//
//        // Act
//        List<SchoolYear> doubleSchoolYears = controller.getAllSchoolYears();
//
//        // Assert
//        assertTrue(doubleSchoolYears.contains(doubleSchoolYear1), "The list should contain doubleSchoolYear1.");
//        assertTrue(doubleSchoolYears.contains(doubleSchoolYear2), "The list should contain doubleSchoolYear2.");
//    }
//
//    @Test
//    void shouldReturnExceptionIfProgrammeEditionEnrollmentRepoIsNull (){
//        //arrange
//        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
//        ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
//        CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
//        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
//        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
//        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
//
//        //act
//        Exception exception = assertThrows(IllegalStateException.class, () -> {
//            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(null,doubleProgrammeEditionRepository,
//                    doubleProgrammeList, doubleCourseEditionEnrolmentRepository, doubleCourseEditionRepository, doubleSchoolYearRepository, doubleEnrolmentRepository);
//        });
//        //assert
//        assertEquals("Programme edition enrolment repository cannot be null.", exception.getMessage());
//    }
//
//    @Test
//    void shouldReturnExceptionIfProgrammeEditionRepositoryIsNull (){
//        //arrange
//        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(ProgrammeEditionEnrolmentRepository.class);
//        ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
//        CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
//        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
//        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
//        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
//
//        //act
//        Exception exception = assertThrows(IllegalStateException.class, () -> {
//            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(doubleProgrammeEditionEnrolmentRepository,null,
//                    doubleProgrammeList, doubleCourseEditionEnrolmentRepository, doubleCourseEditionRepository, doubleSchoolYearRepository, doubleEnrolmentRepository);
//        });
//
//        //assert
//        assertEquals("Programme edition repository cannot be null.", exception.getMessage());
//    }
//
//    @Test
//    void shouldReturnExceptionIfProgrammeListIsNull (){
//        //arrange
//        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(ProgrammeEditionEnrolmentRepository.class);
//        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
//        CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
//        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
//        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
//        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
//
//        //act
//        Exception exception = assertThrows(IllegalStateException.class, () -> {
//            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(doubleProgrammeEditionEnrolmentRepository, doubleProgrammeEditionRepository,
//                    null, doubleCourseEditionEnrolmentRepository, doubleCourseEditionRepository, doubleSchoolYearRepository, doubleEnrolmentRepository);
//        });
//
//        //assert
//        assertEquals("Programme list cannot be null.", exception.getMessage());
//    }
//
//    @Test
//    void shouldReturnExceptionIfCourseEditionEnrollmentRepositoryIsNull (){
//        //arrange
//        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(ProgrammeEditionEnrolmentRepository.class);
//        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
//        ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
//        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
//        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
//        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
//
//        //act
//        Exception exception = assertThrows(IllegalStateException.class, () -> {
//            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(doubleProgrammeEditionEnrolmentRepository, doubleProgrammeEditionRepository,
//                    doubleProgrammeList, null, doubleCourseEditionRepository, doubleSchoolYearRepository, doubleEnrolmentRepository);
//        });
//
//        //assert
//        assertEquals("Course edition enrolment repository cannot be null.", exception.getMessage());
//    }
//
//    @Test
//    void shouldReturnExceptionIfCourseEditionRepositoryIsNull (){
//        //arrange
//        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(ProgrammeEditionEnrolmentRepository.class);
//        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
//        ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
//        CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
//        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
//        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
//
//        //act
//        Exception exception = assertThrows(IllegalStateException.class, () -> {
//            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(doubleProgrammeEditionEnrolmentRepository, doubleProgrammeEditionRepository,
//                    doubleProgrammeList, doubleCourseEditionEnrolmentRepository, null, doubleSchoolYearRepository, doubleEnrolmentRepository);
//        });
//
//        //assert
//        assertEquals("Course edition repository cannot be null.", exception.getMessage());
//    }
//
//    @Test
//    void shouldReturnExceptionIfSchoolYearRepositoryIsNull (){
//        //arrange
//        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(ProgrammeEditionEnrolmentRepository.class);
//        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
//        ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
//        CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
//        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
//        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
//
//        //act
//        Exception exception = assertThrows(IllegalStateException.class, () -> {
//            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(doubleProgrammeEditionEnrolmentRepository, doubleProgrammeEditionRepository,
//                    doubleProgrammeList, doubleCourseEditionEnrolmentRepository, doubleCourseEditionRepository, null, doubleEnrolmentRepository);
//        });
//
//        //assert
//        assertEquals("School year repository cannot be null.", exception.getMessage());
//    }
//
//    @Test
//    void shouldReturnExceptionIfEnrolmentRepositoryIsNull (){
//        //arrange
//        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(ProgrammeEditionEnrolmentRepository.class);
//        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
//        ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
//        CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
//        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
//        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
//
//        //act
//        Exception exception = assertThrows(IllegalStateException.class, () -> {
//            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(doubleProgrammeEditionEnrolmentRepository, doubleProgrammeEditionRepository,
//                    doubleProgrammeList, doubleCourseEditionEnrolmentRepository, doubleCourseEditionRepository, doubleSchoolYearRepository, null);
//        });
//
//        //assert
//        assertEquals("Enrolment repository cannot be null.", exception.getMessage());
//    }
//
//
//    //----------------------INTEGRATION TESTS------------------------------
//
//    @Test
//    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_Success_IntegrationTest() throws Exception {
//        // Arrange
//        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
//        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
//        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
//        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
//        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
//        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
//        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
//        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory =  new CourseEditionEnrolmentListFactoryImpl();
//        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
//        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
//        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
//        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
//        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
//        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
//        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);
//
//        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
//                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
//                        programmeEditionEnrolmentRepository,
//                        programmeEditionRepository,
//                        programmeRepository,
//                        courseEditionEnrolmentRepository,
//                        courseEditionRepository,
//                        schoolYearRepository,
//                        programmeEnrolmentRepository);
//        ;
//        Date date = new Date("20-12-2010");
//        TeacherCategoryID tcID = new TeacherCategoryID();
//        WorkingPercentage wp = new WorkingPercentage(100);
//        TeacherID teacherID = TeacherID.createNew();
//        DegreeType master = new DegreeType("Master", 240);
//        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
//        IAddressFactory addressFactory = new AddressFactoryImpl();
//        Address add1 = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
//        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "+351 912 345 678",
//                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto",
//                "Portugal", addressFactory,date, tcID, wp, teacherID, department1, new TeacherCareerProgressionFactoryImpl(),
//                new TeacherCareerProgressionListFactoryImpl());
//
//        Description description = new Description("School Year 24/25");
//        Date startDate = new Date ("23-11-2024");
//        Date endDate = new Date ("09-12-2025");
//        schoolYearRepository.addSchoolYear(description, startDate, endDate);
//        SchoolYear schoolYear = schoolYearRepository.getCurrentSchoolYear();
//
//        IAccessMethodFactory accessMethodFactory = new AccessMethodFactoryImpl();
//        IAccessMethodListFactory accessMethodListFactory = new AccessMethodListFactoryImpl();
//        AccessMethodRepository amr = new AccessMethodRepository(accessMethodFactory, accessMethodListFactory);
//        AccessMethod am1 = new AccessMethod("Over 23");
//        amr.registerAccessMethod("Over 23");
//        StudentID studentID = new StudentID(1500000);
//
//        Student student = new Student(studentID, "João Silva", "999999999", "221234567", "joao123@gmail.com", add1);
//
//        Course c1 = new Course("Development", "DEV", 5, 1);
//        Course c2 = new Course("Development1", "DEV1", 5, 1);
//
//        Programme programme1 = new Programme("Computer Engineering", "CSE", 25, 6, master, department1, teacher1,
//                new ProgrammeCourseListFactoryImpl (), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
//                new CourseFactoryImpl());
//
//        programme1.addCourseToAProgramme(c1);
//        programme1.addCourseToAProgramme(c2);
//
//        if (!programmeEnrolmentRepository.isStudentEnrolled(student, programme1)) {
//            programmeEnrolmentRepository.enrolStudents(student, am1, programme1,date);
//        }
//
//        programmeEditionRepository.createProgrammeEdition(programme1, schoolYear);
//        Optional<ProgrammeEdition> pe1Opt = programmeEditionRepository.findProgrammeEditionBySchoolYearAndProgramme(programme1, schoolYear);
//        ProgrammeEdition pe1 = pe1Opt.get();
//
//        courseEditionRepository.createAndSaveCourseEdition(c1, pe1);
//        courseEditionRepository.createAndSaveCourseEdition(c2, pe1);
//
//        CourseEdition ce1 = courseEditionRepository.getCourseEditions().get(0);
//        CourseEdition ce2 = courseEditionRepository.getCourseEditions().get(1);
//
//        // Act
//        boolean result = controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(student, programme1, schoolYear);
//        boolean result2 = courseEditionEnrolmentRepository.isStudentEnrolledInCourseEdition(student, ce1);
//        boolean result3 = courseEditionEnrolmentRepository.isStudentEnrolledInCourseEdition(student, ce2);
//
//        // Assert
//        assertTrue(result, "The student is enrolled in the ProgrammeEdition.");
//        assertTrue(result2, "The Student is enrolled in the CourseEdition.");
//        assertTrue(result3, "The Student is enrolled in the CourseEdition.");
//    }
//
//    @Test
//    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_StudentNotEnrolledInProgramme_IntegrationTest() throws Exception {
//        // Arrange
//        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
//        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
//        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
//        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
//        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
//        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
//        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
//        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory =  new CourseEditionEnrolmentListFactoryImpl();
//        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
//        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
//        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
//        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
//        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
//        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
//        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);
//
//        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
//                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
//                        programmeEditionEnrolmentRepository,
//                        programmeEditionRepository,
//                        programmeRepository,
//                        courseEditionEnrolmentRepository,
//                        courseEditionRepository,
//                        schoolYearRepository,
//                        programmeEnrolmentRepository);
//
//        Date date = new Date("20-12-2010");
//        TeacherCategoryID tcID = new TeacherCategoryID();
//        WorkingPercentage wp = new WorkingPercentage(100);
//        TeacherID teacherID = TeacherID.createNew();
//        DegreeType master = new DegreeType("Master", 240);
//        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
//        IAddressFactory addressFactory = new AddressFactoryImpl();
//        Address add1 = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
//        StudentID studentID = new StudentID(1000001);
//        Student student = new Student(studentID, "João Silva", "999999999", "221234567", "joao123@gmail.com", add1);
//        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "+351 912 345 678",
//                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto",
//                "Portugal", addressFactory,date, tcID, wp, teacherID, department1, new TeacherCareerProgressionFactoryImpl(),
//                new TeacherCareerProgressionListFactoryImpl());
//
//        Description description = new Description("School Year 24/25");
//        Date startDate = new Date ("23-11-2024");
//        Date endDate = new Date ("09-12-2025");
//        schoolYearRepository.addSchoolYear(description, startDate,endDate);
//        SchoolYear schoolYear = schoolYearRepository.getCurrentSchoolYear();
//
//        IAccessMethodFactory accessMethodFactory = new AccessMethodFactoryImpl();
//        IAccessMethodListFactory accessMethodListFactory = new AccessMethodListFactoryImpl();
//        AccessMethodRepository amr = new AccessMethodRepository(accessMethodFactory, accessMethodListFactory);
//        amr.registerAccessMethod("Over 23");
//
//        Programme programme1 = new Programme("Computer Engineering", "CSE", 25, 6, master, department1, teacher1,
//                new ProgrammeCourseListFactoryImpl (), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
//                new CourseFactoryImpl());
//
//        programmeEditionRepository.createProgrammeEdition(programme1, schoolYear);
//
//        // Act
//        boolean result = controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(student, programme1, schoolYear);
//
//        //Assert
//        assertFalse(result);
//    }
//
//    @Test
//    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_ProgrammeEditionNotFound_IntegrationTests() throws Exception {
//        // Arrange
//        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
//        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
//        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
//        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
//        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
//        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
//        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
//        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory =  new CourseEditionEnrolmentListFactoryImpl();
//        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
//        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
//        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
//        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
//        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
//        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
//        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);
//
//        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
//                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
//                        programmeEditionEnrolmentRepository,
//                        programmeEditionRepository,
//                        programmeRepository,
//                        courseEditionEnrolmentRepository,
//                        courseEditionRepository,
//                        schoolYearRepository,
//                        programmeEnrolmentRepository);
//
//        Date date = new Date("20-12-2010");
//        TeacherCategoryID tcID = new TeacherCategoryID();
//        WorkingPercentage wp = new WorkingPercentage(100);
//        TeacherID teacherID = TeacherID.createNew();
//        DegreeType master = new DegreeType("Master", 240);
//        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
//        IAddressFactory addressFactory = new AddressFactoryImpl();
//        Address add1 = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
//        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "+351 912 345 678",
//                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto",
//                "Portugal", addressFactory,date, tcID, wp, teacherID, department1, new TeacherCareerProgressionFactoryImpl(),
//                new TeacherCareerProgressionListFactoryImpl());
//
//        Description description = new Description("School Year 24/25");
//        Date startDate = new Date ("23-12-2024");
//        Date endDate = new Date ("09-12-2025");
//        schoolYearRepository.addSchoolYear(description, startDate, endDate);
//        SchoolYear schoolYear = schoolYearRepository.getCurrentSchoolYear();
//
//        IAccessMethodFactory accessMethodFactory = new AccessMethodFactoryImpl();
//        IAccessMethodListFactory accessMethodListFactory = new AccessMethodListFactoryImpl();
//        AccessMethodRepository amr = new AccessMethodRepository(accessMethodFactory, accessMethodListFactory);
//        AccessMethod am1 = new AccessMethod("Over 23");
//        amr.registerAccessMethod("Over 23");
//        StudentID studentID = new StudentID(1000001);
//
//        Student student = new Student(studentID, "João Silva", "999999999", "221234567", "joao123@gmail.com", add1);
//        Programme programme1 = new Programme("Computer Engineering", "CSE", 25, 6, master, department1, teacher1,
//                new ProgrammeCourseListFactoryImpl (), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
//                new CourseFactoryImpl());
//
//        if (!programmeEnrolmentRepository.isStudentEnrolled(student, programme1)) {
//            programmeEnrolmentRepository.enrolStudents(student, am1, programme1,date);
//        }
//        // Act
//        boolean result = controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(student, programme1, schoolYear);
//
//        assertFalse(result);
//    }
//
//
//    @Test
//    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_StudentAlreadyEnrolledInProgrammeEdition_IntegrationTest() throws Exception {
//        // Arrange
//        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
//        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
//        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
//        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
//        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
//        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
//        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
//        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory =  new CourseEditionEnrolmentListFactoryImpl();
//        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
//        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
//        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
//        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
//        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
//        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
//        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);
//
//        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
//                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
//                        programmeEditionEnrolmentRepository,
//                        programmeEditionRepository,
//                        programmeRepository,
//                        courseEditionEnrolmentRepository,
//                        courseEditionRepository,
//                        schoolYearRepository,
//                        programmeEnrolmentRepository);
//
//        Date date = new Date("20-12-2010");
//        TeacherCategoryID tcID = new TeacherCategoryID();
//        WorkingPercentage wp = new WorkingPercentage(100);
//        TeacherID teacherID = TeacherID.createNew();
//        DegreeType master = new DegreeType("Master", 240);
//        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
//        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
//        IAddressFactory addressFactory = new AddressFactoryImpl();
//        Address add1 = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
//        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "+351 912 345 678",
//                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto",
//                "Portugal", addressFactory,date, tcID, wp, teacherID, department1, new TeacherCareerProgressionFactoryImpl(),
//                new TeacherCareerProgressionListFactoryImpl());
//
//        Description description = new Description("School Year 24/25");
//        Date startDate = new Date ("23-11-2024");
//        Date endDate = new Date ("09-12-2025");
//        schoolYearRepository.addSchoolYear(description, startDate,endDate);
//        SchoolYear schoolYear = schoolYearRepository.getCurrentSchoolYear();
//
//        IAccessMethodFactory accessMethodFactory = new AccessMethodFactoryImpl();
//        IAccessMethodListFactory accessMethodListFactory = new AccessMethodListFactoryImpl();
//        AccessMethodRepository amr = new AccessMethodRepository(accessMethodFactory, accessMethodListFactory);
//        AccessMethod am1 = new AccessMethod("Over 23");
//        amr.registerAccessMethod("Over 23");
//        StudentID studentID = new StudentID(1000001);
//
//        Student student = new Student(studentID, "João Silva", "999999999", "221234567", "joao123@gmail.com", add1);
//        Programme programme1 = new Programme("Computer Engineering", "CSE", 25, 6, master, department1, teacher1,
//                new ProgrammeCourseListFactoryImpl (), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
//                new CourseFactoryImpl());
//
//        if (!programmeEnrolmentRepository.isStudentEnrolled(student, programme1)) {
//            programmeEnrolmentRepository.enrolStudents(student, am1, programme1,date);
//        }
//
//        programmeEditionRepository.createProgrammeEdition(programme1, schoolYear);
//        Optional<ProgrammeEdition> peOptional = programmeEditionRepository.findProgrammeEditionBySchoolYearAndProgramme(programme1, schoolYear);
//        ProgrammeEdition programmeEdition = peOptional.get();
//        programmeEditionEnrolmentRepository.enrolStudentInProgrammeEdition(student, programmeEdition);
//
//        // Act
//        boolean result = controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(student, programme1, schoolYear);
//
//        //assert
//        assertFalse(result);
//    }
//
//
//    @Test
//    void testEnrollStudentInCourseEditionAndSetOfCoursesEditions_StudentAlreadyEnrolledInCourseEdition_IntegrationTest() throws Exception {
//        // Arrange
//        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
//        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
//        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
//        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
//        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
//        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
//        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
//        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory =  new CourseEditionEnrolmentListFactoryImpl();
//        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
//        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
//        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
//        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
//        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
//        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
//        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);
//
//        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
//                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
//                        programmeEditionEnrolmentRepository,
//                        programmeEditionRepository,
//                        programmeRepository,
//                        courseEditionEnrolmentRepository,
//                        courseEditionRepository,
//                        schoolYearRepository,
//                        programmeEnrolmentRepository);
//
//        Date date = new Date("20-12-2010");
//        TeacherCategoryID tcID = new TeacherCategoryID();
//        WorkingPercentage wp = new WorkingPercentage(100);
//        TeacherID teacherID = TeacherID.createNew();
//        DegreeType master = new DegreeType("Master", 240);
//        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
//        IAddressFactory addressFactory = new AddressFactoryImpl();
//        Address add1 = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
//        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "+351 912 345 678",
//                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto",
//                "Portugal", addressFactory,date, tcID, wp, teacherID, department1, new TeacherCareerProgressionFactoryImpl(),
//                new TeacherCareerProgressionListFactoryImpl());
//
//        Description description = new Description("School Year 24/25");
//        Date startDate = new Date ("23-11-2024");
//        Date endDate = new Date ("09-12-2025");
//        schoolYearRepository.addSchoolYear(description, startDate,endDate);
//        SchoolYear schoolYear = schoolYearRepository.getCurrentSchoolYear();
//
//        IAccessMethodFactory accessMethodFactory = new AccessMethodFactoryImpl();
//        IAccessMethodListFactory accessMethodListFactory = new AccessMethodListFactoryImpl();
//        AccessMethodRepository amr = new AccessMethodRepository(accessMethodFactory, accessMethodListFactory);
//        AccessMethod am1 = new AccessMethod("Over 23");
//        amr.registerAccessMethod("Over 23");
//        StudentID studentID = new StudentID(1000001);
//
//        Student student = new Student(studentID, "João Silva", "999999999", "221234567", "joao123@gmail.com", add1);
//        Programme programme1 = new Programme("Computer Engineering", "CSE", 25, 6, master, department1, teacher1,
//                new ProgrammeCourseListFactoryImpl (), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
//                new CourseFactoryImpl());
//
//        if (!programmeEnrolmentRepository.isStudentEnrolled(student, programme1)) {
//            programmeEnrolmentRepository.enrolStudents(student, am1, programme1,date);
//        }
//        programmeEditionRepository.createProgrammeEdition(programme1, schoolYear);
//        Optional<ProgrammeEdition> pe1Opt = programmeEditionRepository.findProgrammeEditionBySchoolYearAndProgramme(programme1, schoolYear);
//        ProgrammeEdition pe1 = pe1Opt.get();
//        Course c1 = new Course("Development", "DEV", 5, 1);
//        Course c2 = new Course("Development1", "DEV1", 5, 1);
//        programme1.addCourseToAProgramme(c1);
//        programme1.addCourseToAProgramme(c2);
//        courseEditionRepository.createAndSaveCourseEdition(c1, pe1);
//        courseEditionRepository.createAndSaveCourseEdition(c2, pe1);
//        CourseEdition ce1 = courseEditionRepository.getCourseEditions().get(0);
//        CourseEdition ce2 = courseEditionRepository.getCourseEditions().get(1);
//        courseEditionEnrolmentRepository.enrolStudentInACourseEdition(student, ce1);
//        courseEditionEnrolmentRepository.enrolStudentInACourseEdition(student, ce2);
//        // Act + Assert
//        Exception exception = assertThrows(IllegalStateException.class, () -> {
//            controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(student, programme1, schoolYear);
//        });
//        assertEquals("This course edition enrolment is already in the list.", exception.getMessage());
//    }
//
//
//    @Test
//    void testGetAllProgrammes_NotNull_IntegrationTest() throws Exception {
//        // Arrange
//        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
//        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
//        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
//        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
//        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
//        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
//        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
//        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
//        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
//        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
//        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
//        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
//        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
//        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
//        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);
//
//        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
//                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
//                        programmeEditionEnrolmentRepository,
//                        programmeEditionRepository,
//                        programmeRepository,
//                        courseEditionEnrolmentRepository,
//                        courseEditionRepository,
//                        schoolYearRepository,
//                        programmeEnrolmentRepository);
//
//
//        Date date = new Date("20-12-2010");
//        TeacherCategoryID tcID = new TeacherCategoryID();
//        WorkingPercentage wp = new WorkingPercentage(100);
//        TeacherID teacherID = TeacherID.createNew();
//        DegreeType master = new DegreeType("Master", 240);
//        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
//        IAddressFactory addressFactory = new AddressFactoryImpl();
//        Acronym acronym1 = new Acronym("CSE");
//        Acronym acronym2 = new Acronym("CVE");
//        NameWithNumbersAndSpecialChars name1 = new NameWithNumbersAndSpecialChars("Computer Engineering");
//        NameWithNumbersAndSpecialChars name2 = new NameWithNumbersAndSpecialChars("Civil Engineering");
//        QuantEcts quantEcts = new QuantEcts(25);
//        QuantSemesters quantSemesters = new QuantSemesters(6);
//        Address add1 = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
//        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "+351 912 345 678",
//                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto",
//                "Portugal", addressFactory, date, tcID, wp, teacherID, department1, new TeacherCareerProgressionFactoryImpl(),
//                new TeacherCareerProgressionListFactoryImpl());
//
//        Programme programme1 = new Programme(name1, acronym1, quantEcts, quantSemesters, master, department1, teacher1,
//                new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
//                new CourseFactoryImpl());
//        programmeRepository.registerProgramme(name1, acronym1, quantEcts, quantSemesters, master, department1, teacher1,
//                new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
//                new CourseFactoryImpl());
//        programmeRepository.registerProgramme(name2, acronym2, quantEcts, quantSemesters, master, department1, teacher1,
//                new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
//                new CourseFactoryImpl());
//
//        // Act
//        List<Programme> programmes = controller.getAllProgrammes();
//
//        // Assert
//        assertNotNull(programmes, "The list of programmes should not be null.");
//    }
//
//@Test
//void testGetAllProgrammes_ListSize_IntegrationTest() throws Exception {
//    // Arrange
//    IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
//    IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
//    ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
//    ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
//    ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//    ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
//    IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
//    IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//    ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
//    ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
//    ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
//    CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
//    ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
//    ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
//    CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
//    SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
//    SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
//    SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
//    IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
//    IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
//    ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);
//
//    US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
//            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
//                    programmeEditionEnrolmentRepository,
//                    programmeEditionRepository,
//                    programmeRepository,
//                    courseEditionEnrolmentRepository,
//                    courseEditionRepository,
//                    schoolYearRepository,
//                    programmeEnrolmentRepository);
//
//    Date date = new Date("20-12-2010");
//    TeacherCategoryID tcID = new TeacherCategoryID();
//    WorkingPercentage wp = new WorkingPercentage(100);
//    TeacherID teacherID = TeacherID.createNew();
//    DegreeType master = new DegreeType("Master", 240);
//    Department department1 = new Department("DEI", "Departamento Engenharia Informática");
//    IAddressFactory addressFactory = new AddressFactoryImpl();
//    Acronym acronym1 = new Acronym("CSE");
//    Acronym acronym2 = new Acronym("CVE");
//    NameWithNumbersAndSpecialChars name1 = new NameWithNumbersAndSpecialChars("Computer Engineering");
//    NameWithNumbersAndSpecialChars name2 = new NameWithNumbersAndSpecialChars("Civil Engineering");
//    QuantEcts quantEcts = new QuantEcts(30);
//    QuantSemesters quantSemesters = new QuantSemesters(6);
//    Address add1 = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
//    Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "+351 912 345 678",
//            "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto",
//            "Portugal", addressFactory, date, tcID, wp, teacherID, department1, new TeacherCareerProgressionFactoryImpl(),
//            new TeacherCareerProgressionListFactoryImpl());
//    Programme programme1 = new Programme(name1, acronym1, quantEcts, quantSemesters, master, department1, teacher1,
//            new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
//            new CourseFactoryImpl());
//    programmeRepository.registerProgramme(name1, acronym1, quantEcts, quantSemesters, master, department1, teacher1,
//            new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
//            new CourseFactoryImpl());
//    programmeRepository.registerProgramme(name2, acronym2, quantEcts, quantSemesters, master, department1, teacher1,
//            new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
//            new CourseFactoryImpl());
//
//    // Act
//    List<Programme> programmes = controller.getAllProgrammes();
//
//    // Assert
//    assertEquals(2, programmes.size(), "The list of programmes should contain exactly 2 programmes.");
//
//    }
//
//    @Test
//    void testGetAllProgrammes_IntegrationTest() throws Exception {
//        // Arrange
//        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
//        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
//        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
//        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
//        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
//        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
//        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
//        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
//        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
//        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
//        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
//        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
//        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
//        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
//        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);
//
//        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
//                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
//                        programmeEditionEnrolmentRepository,
//                        programmeEditionRepository,
//                        programmeRepository,
//                        courseEditionEnrolmentRepository,
//                        courseEditionRepository,
//                        schoolYearRepository,
//                        programmeEnrolmentRepository);
//
//        Date date = new Date("20-12-2010");
//        TeacherCategoryID tcID = new TeacherCategoryID();
//        WorkingPercentage wp = new WorkingPercentage(100);
//        TeacherID teacherID = TeacherID.createNew();
//        DegreeType master = new DegreeType("Master", 240);
//        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
//        IAddressFactory addressFactory = new AddressFactoryImpl();
//        Acronym acronym1 = new Acronym("CSE");
//        Acronym acronym2 = new Acronym("CVE");
//        NameWithNumbersAndSpecialChars name1 = new NameWithNumbersAndSpecialChars("Computer Engineering");
//        NameWithNumbersAndSpecialChars name2 = new NameWithNumbersAndSpecialChars("Civil Engineering");
//        QuantEcts quantEcts = new QuantEcts(30);
//        QuantSemesters quantSemesters = new QuantSemesters(6);
//        Address add1 = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
//        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "+351 912 345 678",
//                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto",
//                "Portugal", addressFactory, date, tcID, wp, teacherID, department1, new TeacherCareerProgressionFactoryImpl(),
//                new TeacherCareerProgressionListFactoryImpl());
//        Programme programme1 = new Programme(name1, acronym1, quantEcts, quantSemesters, master, department1, teacher1,
//                new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
//                new CourseFactoryImpl());
//        programmeRepository.registerProgramme(name1, acronym1, quantEcts, quantSemesters, master, department1, teacher1,
//                new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
//                new CourseFactoryImpl());
//        programmeRepository.registerProgramme(name2, acronym2, quantEcts, quantSemesters, master, department1, teacher1,
//                new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
//                new CourseFactoryImpl());
//
//        // Act
//        List<Programme> programmes = controller.getAllProgrammes();
//
//        // Assert
//        assertTrue(programmes.contains(new Programme(name1, acronym1, quantEcts, quantSemesters, master, department1, teacher1,
//                new ProgrammeCourseListFactoryImpl (), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
//                new CourseFactoryImpl())));
//        assertTrue(programmes.contains(new Programme(name2, acronym2, quantEcts, quantSemesters, master, department1, teacher1,
//               new ProgrammeCourseListFactoryImpl (), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
//                new CourseFactoryImpl())));
//
//    }
//    @Test
//    void testGetAllSchoolYears_IntegrationTest() throws Exception {
//        // Arrange
//        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
//        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
//        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
//        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
//        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
//        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
//        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
//        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
//        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
//        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
//        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
//        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
//        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
//        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
//        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);
//
//        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
//                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
//                        programmeEditionEnrolmentRepository,
//                        programmeEditionRepository,
//                        programmeRepository,
//                        courseEditionEnrolmentRepository,
//                        courseEditionRepository,
//                        schoolYearRepository,
//                        programmeEnrolmentRepository);
//
//        Description description1 = new Description("School Year 24/25");
//        Description description2 = new Description("School Year 25/26");
//        Date startDate1 = new Date ("23-11-2024");
//        Date endDate1 = new Date ("09-12-2025");
//        Date startDate2 = new Date ("10-11-2025");
//        Date endDate2 = new Date ("09-12-2026");
//        schoolYearRepository.addSchoolYear(description1, startDate1, endDate1);
//        schoolYearRepository.addSchoolYear(description2, startDate2, endDate2);
//
//        // Act
//        List<SchoolYear> schoolYears = controller.getAllSchoolYears();
//
//        // Assert
//        assertTrue(schoolYears.contains(new SchoolYear(description1, startDate1,endDate1)),
//                "The list should contain the school year '24/25'.");
//        assertTrue(schoolYears.contains(new SchoolYear(description2, startDate2, endDate2)),
//                "The list should contain the school year '25/26'.");
//    }
//
//    @Test
//    void testGetAllSchoolYears_NotNullList_IntegrationTest() throws Exception {
//        // Arrange
//        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
//        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
//        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
//        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
//        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
//        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
//        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
//        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
//        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
//        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
//        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
//        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
//        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
//        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
//        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);
//
//        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
//                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
//                        programmeEditionEnrolmentRepository,
//                        programmeEditionRepository,
//                        programmeRepository,
//                        courseEditionEnrolmentRepository,
//                        courseEditionRepository,
//                        schoolYearRepository,
//                        programmeEnrolmentRepository);
//
//        Description description1 = new Description("School Year 24/25");
//        Description description2 = new Description("School Year 25/26");
//        Date startDate1 = new Date ("23-11-2024");
//        Date endDate1 = new Date ("09-12-2025");
//        Date startDate2 = new Date ("10-11-2025");
//        Date endDate2 = new Date ("09-12-2026");
//        schoolYearRepository.addSchoolYear(description1, startDate1, endDate1);
//        schoolYearRepository.addSchoolYear(description2, startDate2, endDate2);
//
//        // Act
//        List<SchoolYear> schoolYears = controller.getAllSchoolYears();
//
//        // Assert
//        assertNotNull(schoolYears, "The list of school years should not be null.");
//
//    }
//
//    @Test
//    void testGetAllSchoolYears_ListSize_IntegrationTest() throws Exception {
//
//        // Arrange
//        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
//        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
//        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
//        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
//        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
//        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
//        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
//        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
//        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
//        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
//        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
//        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
//        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
//        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
//        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);
//
//        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
//                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
//                        programmeEditionEnrolmentRepository,
//                        programmeEditionRepository,
//                        programmeRepository,
//                        courseEditionEnrolmentRepository,
//                        courseEditionRepository,
//                        schoolYearRepository,
//                        programmeEnrolmentRepository);
//        Description description1 = new Description("School Year 24/25");
//        Description description2 = new Description("School Year 25/26");
//        Date startDate1 = new Date ("23-11-2024");
//        Date endDate1 = new Date ("09-12-2025");
//        Date startDate2 = new Date ("10-11-2025");
//        Date endDate2 = new Date ("09-12-2026");
//        schoolYearRepository.addSchoolYear(description1, startDate1, endDate1);
//        schoolYearRepository.addSchoolYear(description2, startDate2, endDate2);
//
//        // Act
//        List<SchoolYear> schoolYears = controller.getAllSchoolYears();
//
//        // Assert
//        assertEquals(2, schoolYears.size(), "The list of school years should contain exactly 2 years.");
//
//    }
//
//    @Test
//    void shouldReturnExceptionIfProgrammeEditionEnrollmentRepoIsNull_IntegrationTest (){
//        //arrange
//        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
//        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
//        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
//        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
//        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
//        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
//        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
//        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
//        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
//        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
//        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
//        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
//        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
//        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
//        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);
//
//        //act
//        Exception exception = assertThrows(IllegalStateException.class, () -> {
//            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(null,programmeEditionRepository,
//                    programmeRepository, courseEditionEnrolmentRepository, courseEditionRepository, schoolYearRepository, programmeEnrolmentRepository);
//        });
//        //assert
//        assertEquals("Programme edition enrolment repository cannot be null.", exception.getMessage());
//    }
//
//    @Test
//    void shouldReturnExceptionIfProgrammeEditionRepositoryIsNull_IntegrationTest (){
//        //arrange
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();;
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
//        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
//        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
//        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
//        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
//        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
//        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
//        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
//        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
//        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
//        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
//        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
//        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
//        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);
//
//        //act
//        Exception exception = assertThrows(IllegalStateException.class, () -> {
//            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrolmentRepository,null,
//                    programmeRepository, courseEditionEnrolmentRepository, courseEditionRepository, schoolYearRepository, programmeEnrolmentRepository);
//        });
//
//        //assert
//        assertEquals("Programme edition repository cannot be null.", exception.getMessage());
//    }
//
//    @Test
//    void shouldReturnExceptionIfProgrammeListIsNull_IntegrationTest (){
//        //arrange
//        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
//        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();;
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
//        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
//        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
//        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
//        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
//        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
//        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
//        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
//        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
//        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
//        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
//        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
//        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);
//
//        //act
//        Exception exception = assertThrows(IllegalStateException.class, () -> {
//            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrolmentRepository,programmeEditionRepository,
//                    null, courseEditionEnrolmentRepository, courseEditionRepository, schoolYearRepository,programmeEnrolmentRepository);
//        });
//
//        //assert
//        assertEquals("Programme list cannot be null.", exception.getMessage());
//    }
//
//    @Test
//    void shouldReturnExceptionIfCourseEditionEnrollmentRepositoryIsNull_IntegrationTest (){
//        //arranje
//        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
//        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
//        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
//        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
//        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
//        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
//        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
//        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
//        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
//        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
//        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
//        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);
//        //act
//        Exception exception = assertThrows(IllegalStateException.class, () -> {
//            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrolmentRepository,programmeEditionRepository,
//                    programmeRepository, null, courseEditionRepository, schoolYearRepository,programmeEnrolmentRepository);
//        });
//
//        //assert
//        assertEquals("Course edition enrolment repository cannot be null.", exception.getMessage());
//    }
//
//    @Test
//    void shouldReturnExceptionIfCourseEditionRepositoryIsNull_IntegrationTest (){
//        //arranje
//        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
//        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
//        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
//        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
//        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
//        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
//        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
//        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
//        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
//        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
//        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
//        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);
//
//        //act
//        Exception exception = assertThrows(IllegalStateException.class, () -> {
//            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrolmentRepository,programmeEditionRepository,
//                    programmeRepository, courseEditionEnrolmentRepository, null, schoolYearRepository,programmeEnrolmentRepository);
//        });
//
//        //assert
//        assertEquals("Course edition repository cannot be null.", exception.getMessage());
//    }
//
//    @Test
//    void shouldReturnExceptionIfSchoolYearRepositoryIsNull_IntegrationTest (){
//        //arranje
//        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
//        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
//        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
//        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
//        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
//        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
//        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
//        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
//        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
//        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
//        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
//        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);
//
//        //act
//        Exception exception = assertThrows(IllegalStateException.class, () -> {
//            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrolmentRepository,programmeEditionRepository,
//                    programmeRepository, courseEditionEnrolmentRepository, courseEditionRepository, null, programmeEnrolmentRepository);
//        });
//
//        //assert
//        assertEquals("School year repository cannot be null.", exception.getMessage());
//    }
//
//    @Test
//    void shouldReturnExceptionIfProgrammeEnrollmentRepositoryIsNull_IntegrationTest (){
//        //arranje
//        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
//        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
//        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
//        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
//        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
//        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
//        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
//        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
//        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
//        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
//        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
//        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
//
//        //act
//        Exception exception = assertThrows(IllegalStateException.class, () -> {
//            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrolmentRepository,programmeEditionRepository,
//                    programmeRepository, courseEditionEnrolmentRepository, courseEditionRepository, schoolYearRepository, null);
//        });
//
//        //assert
//        assertEquals("Enrolment repository cannot be null.", exception.getMessage());
//    }
//
//}
//
//

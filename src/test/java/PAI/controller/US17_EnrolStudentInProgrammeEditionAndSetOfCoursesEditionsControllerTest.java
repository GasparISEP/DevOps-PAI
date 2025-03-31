package PAI.controller;


import PAI.VOs.*;

import PAI.domain.*;
import PAI.factory.*;
import PAI.repository.*;
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
        Programme doubleProgramme = mock(Programme.class);
        SchoolYear doubleSchoolYear = mock(SchoolYear.class);
        Student doubleStudent = mock(Student.class);
        ProgrammeEdition doubleProgrammeEdition = mock(ProgrammeEdition.class);
        ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
        CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrolmentRepository,
                        doubleProgrammeEditionRepository,
                        doubleProgrammeList,
                        doubleCourseEditionEnrolmentRepository,
                        doubleCourseEditionRepository,
                        doubleSchoolYearRepository,
                        doubleProgrammeEnrolmentRepository);

        CourseEdition doubleCe1 = mock(CourseEdition.class);
        CourseEdition doubleCe2 = mock(CourseEdition.class);

        when(doubleProgrammeEnrolmentRepository.isStudentEnrolled(doubleStudent, doubleProgramme))
                .thenReturn(true);

        when(doubleProgrammeEditionRepository.findProgrammeEditionBySchoolYearAndProgramme(doubleProgramme, doubleSchoolYear))
                .thenReturn(Optional.of(doubleProgrammeEdition));

        when(doubleCourseEditionRepository.findCourseEditionsByProgrammeEdition(doubleProgrammeEdition)).thenReturn(List.of(doubleCe1, doubleCe2));

        when(programmeEditionEnrolmentRepository.isStudentEnrolledInThisProgrammeEdition(doubleStudent, doubleProgrammeEdition)).thenReturn(false);

        when(doubleCourseEditionEnrolmentRepository.isStudentEnrolledInCourseEdition(doubleStudent, doubleCe1)).thenReturn(true);

        when(doubleCourseEditionEnrolmentRepository.isStudentEnrolledInCourseEdition(doubleStudent, doubleCe2)).thenReturn(true);

        // Act
        boolean result = controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(doubleStudent, doubleProgramme, doubleSchoolYear);
        boolean result2 = doubleCourseEditionEnrolmentRepository.isStudentEnrolledInCourseEdition(doubleStudent, doubleCe1);
        boolean result3 = doubleCourseEditionEnrolmentRepository.isStudentEnrolledInCourseEdition(doubleStudent, doubleCe2);

        // Assert
        assertTrue(result, "Student should be enrolled in Programme.");
        assertTrue(result2, "The Student should be enrolled in CourseEdition 1.");
        assertTrue(result3, "The Student should be enrolled in CourseEdition 2.");
    }

    @Test
    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_StudentNotEnrolledInProgramme() {
        // arrange
        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(ProgrammeEditionEnrolmentRepository.class);
        Programme doubleProgramme = mock(Programme.class);
        SchoolYear doubleSchoolYear = mock(SchoolYear.class);
        Student doubleStudent = mock(Student.class);
        ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
        CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        doubleProgrammeEditionEnrolmentRepository,
                        doubleProgrammeEditionRepository,
                        doubleProgrammeList,
                        doubleCourseEditionEnrolmentRepository,
                        doubleCourseEditionRepository,
                        doubleSchoolYearRepository,
                        doubleProgrammeEnrolmentRepository);

        doubleProgrammeEditionRepository.createProgrammeEdition(doubleProgramme, doubleSchoolYear);

        // act
        boolean result = controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(doubleStudent, doubleProgramme, doubleSchoolYear);

        //assert
        assertFalse(result);
    }

    @Test
    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_ProgrammeEditionNotFound() {
        // arrange
        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(ProgrammeEditionEnrolmentRepository.class);
        Programme doubleProgramme = mock(Programme.class);
        SchoolYear doubleSchoolYear = mock(SchoolYear.class);
        Student doubleStudent = mock(Student.class);
        ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
        CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        doubleProgrammeEditionEnrolmentRepository,
                        doubleProgrammeEditionRepository,
                        doubleProgrammeList,
                        doubleCourseEditionEnrolmentRepository,
                        doubleCourseEditionRepository,
                        doubleSchoolYearRepository,
                        doubleProgrammeEnrolmentRepository);

        when(doubleProgrammeEnrolmentRepository.isStudentEnrolled(doubleStudent, doubleProgramme))
                .thenReturn(true);

        // act

        boolean result =controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(doubleStudent, doubleProgramme, doubleSchoolYear);

        //assert
        assertFalse(result);
    }

    @Test
    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_StudentAlreadyEnrolledInProgrammeEdition() {
        // arrange
        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(ProgrammeEditionEnrolmentRepository.class);
        Programme doubleProgramme = mock(Programme.class);
        SchoolYear doubleSchoolYear = mock(SchoolYear.class);
        Student doubleStudent = mock(Student.class);
        ProgrammeEdition doubleProgrammeEdition = mock(ProgrammeEdition.class);
        ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
        CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        doubleProgrammeEditionEnrolmentRepository,
                        doubleProgrammeEditionRepository,
                        doubleProgrammeList,
                        doubleCourseEditionEnrolmentRepository,
                        doubleCourseEditionRepository,
                        doubleSchoolYearRepository,
                        doubleProgrammeEnrolmentRepository);

        when(doubleProgrammeEnrolmentRepository.isStudentEnrolled(doubleStudent, doubleProgramme)).thenReturn(true);
        when(doubleProgrammeEditionRepository.findProgrammeEditionBySchoolYearAndProgramme(doubleProgramme, doubleSchoolYear)).thenReturn(Optional.of(doubleProgrammeEdition));

        when(doubleProgrammeEditionEnrolmentRepository.isStudentEnrolledInThisProgrammeEdition(doubleStudent, doubleProgrammeEdition)).thenReturn(true);
        // act
        boolean result = controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(doubleStudent, doubleProgramme, doubleSchoolYear);

        //assert
        assertFalse(result);
    }

    @Test
    void testEnrollStudentInCourseEditionAndSetOfCoursesEditions_StudentAlreadyEnrolledInCourseEdition() {
        // Arrange
        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(ProgrammeEditionEnrolmentRepository.class);
        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
        Programme doubleProgramme = mock(Programme.class);
        SchoolYear doubleSchoolYear = mock(SchoolYear.class);
        Student doubleStudent = mock(Student.class);
        ProgrammeEdition doubleProgrammeEdition = mock(ProgrammeEdition.class);
        ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
        CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);


        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        doubleProgrammeEditionEnrolmentRepository,
                        doubleProgrammeEditionRepository,
                        doubleProgrammeList,
                        doubleCourseEditionEnrolmentRepository,
                        doubleCourseEditionRepository,
                        doubleSchoolYearRepository,
                        doubleProgrammeEnrolmentRepository);

        CourseEdition ce1 = mock(CourseEdition.class);
        CourseEdition ce2 = mock(CourseEdition.class);

        when(doubleProgrammeEnrolmentRepository.isStudentEnrolled(doubleStudent, doubleProgramme))
                .thenReturn(true);


        when(doubleProgrammeEditionRepository.findProgrammeEditionBySchoolYearAndProgramme(doubleProgramme, doubleSchoolYear))
                .thenReturn(Optional.of(doubleProgrammeEdition));


        when(doubleCourseEditionRepository.findCourseEditionsByProgrammeEdition(doubleProgrammeEdition))
                .thenReturn(List.of(ce1, ce2));


        when(doubleCourseEditionEnrolmentRepository.findByStudentAndEdition(doubleStudent, ce1))
                .thenReturn(Optional.of(new CourseEditionEnrolment(doubleStudent, ce1)));

        when(doubleCourseEditionEnrolmentRepository.findByStudentAndEdition(doubleStudent, ce2))
                .thenReturn(Optional.of(new CourseEditionEnrolment(doubleStudent, ce2)));


        doThrow(new IllegalStateException("This course edition enrolment is already in the list."))
                .when(doubleCourseEditionEnrolmentRepository)
                .enrolStudentInProgrammeCourseEditions(any(Student.class), anyList());

        // Act + Assert
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(doubleStudent, doubleProgramme, doubleSchoolYear);
        });

        assertEquals("This course edition enrolment is already in the list.", exception.getMessage());
    }

    @Test
    void testGetAllProgrammes()  {
        // Arrange
         ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
         ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock (ProgrammeEditionEnrolmentRepository.class);
         ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
         CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
         CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
         SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
         ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
         US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        doubleProgrammeEditionEnrolmentRepository,
                        doubleProgrammeEditionRepository,
                        doubleProgrammeList,
                        doubleCourseEditionEnrolmentRepository,
                        doubleCourseEditionRepository,
                        doubleSchoolYearRepository,
                        doubleEnrolmentRepository);

        Programme doubleProgramme1 = mock(Programme.class);
        Programme doubleProgramme2 = mock(Programme.class);

        when(doubleProgrammeList.getAllProgrammes()).thenReturn(List.of(doubleProgramme1, doubleProgramme2));

        // Act
        List<Programme> doubleProgrammes = controller.getAllProgrammes();

        // Assert
        assertNotNull(doubleProgrammes, "The list of programmes should not be null.");
    }

    @Test
    void testGetAllProgrammes_SizeEqualsTwo() throws Exception {
        // Arrange
        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock (ProgrammeEditionEnrolmentRepository.class);
        ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        doubleProgrammeEditionEnrolmentRepository,
                        doubleProgrammeEditionRepository,
                        doubleProgrammeList,
                        doubleCourseEditionEnrolmentRepository,
                        doubleCourseEditionRepository,
                        doubleSchoolYearRepository,
                        doubleEnrolmentRepository);

        Programme doubleProgramme1 = mock(Programme.class);
        Programme doubleProgramme2 = mock(Programme.class);

        when(doubleProgrammeList.getAllProgrammes()).thenReturn(List.of(doubleProgramme1, doubleProgramme2));

        // Act
        List<Programme> doubleProgrammes = controller.getAllProgrammes();

        // Assert
        assertEquals(2, doubleProgrammes.size(), "The list of programmes should contain exactly 2 programmes.");
    }

    @Test
    void testGetAllProgrammes_ContainsAllProgrammes() throws Exception {
        // Arrange
        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock (ProgrammeEditionEnrolmentRepository.class);
        ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        doubleProgrammeEditionEnrolmentRepository,
                        doubleProgrammeEditionRepository,
                        doubleProgrammeList,
                        doubleCourseEditionEnrolmentRepository,
                        doubleCourseEditionRepository,
                        doubleSchoolYearRepository,
                        doubleEnrolmentRepository);

        Programme doubleProgramme1 = mock(Programme.class);
        Programme doubleProgramme2 = mock(Programme.class);

        when(doubleProgrammeList.getAllProgrammes()).thenReturn(List.of(doubleProgramme1, doubleProgramme2));

        // Act
        List<Programme> doubleProgrammes = controller.getAllProgrammes();

        // Assert
        assertTrue(doubleProgrammes.contains(doubleProgramme1));
        assertTrue(doubleProgrammes.contains(doubleProgramme2));

    }

    @Test
    void testGetAllSchoolYears_NotNull() throws Exception {
        // Arrange
        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock (ProgrammeEditionEnrolmentRepository.class);
        ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        doubleProgrammeEditionEnrolmentRepository,
                        doubleProgrammeEditionRepository,
                        doubleProgrammeList,
                        doubleCourseEditionEnrolmentRepository,
                        doubleCourseEditionRepository,
                        doubleSchoolYearRepository,
                        doubleEnrolmentRepository);

        SchoolYear doubleSchoolYear1 = mock(SchoolYear.class);
        SchoolYear doubleSchoolYear2 = mock(SchoolYear.class);

        when(doubleSchoolYearRepository.getAllSchoolYears()).thenReturn(List.of(doubleSchoolYear1, doubleSchoolYear2));

        // Act
        List<SchoolYear> doubleSchoolYears = controller.getAllSchoolYears();

        // Assert
        assertNotNull(doubleSchoolYears, "The list of school years should not be null.");
    }

    @Test
    void testGetAllSchoolYears_SizeEqualsTwo() throws Exception {
        // Arrange
        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock (ProgrammeEditionEnrolmentRepository.class);
        ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        doubleProgrammeEditionEnrolmentRepository,
                        doubleProgrammeEditionRepository,
                        doubleProgrammeList,
                        doubleCourseEditionEnrolmentRepository,
                        doubleCourseEditionRepository,
                        doubleSchoolYearRepository,
                        doubleEnrolmentRepository);

        SchoolYear doubleSchoolYear1 = mock(SchoolYear.class);
        SchoolYear doubleSchoolYear2 = mock(SchoolYear.class);

        when(doubleSchoolYearRepository.getAllSchoolYears()).thenReturn(List.of(doubleSchoolYear1, doubleSchoolYear2));

        // Act
        List<SchoolYear> doubleSchoolYears = controller.getAllSchoolYears();

        // Assert
        assertEquals(2, doubleSchoolYears.size(), "The list of school years should contain exactly 2 years.");
    }

    @Test
    void testGetAllSchoolYears_ContainsAllSchoolYears() throws Exception {
        // Arrange
        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock (ProgrammeEditionEnrolmentRepository.class);
        ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        doubleProgrammeEditionEnrolmentRepository,
                        doubleProgrammeEditionRepository,
                        doubleProgrammeList,
                        doubleCourseEditionEnrolmentRepository,
                        doubleCourseEditionRepository,
                        doubleSchoolYearRepository,
                        doubleEnrolmentRepository);

        SchoolYear doubleSchoolYear1 = mock(SchoolYear.class);
        SchoolYear doubleSchoolYear2 = mock(SchoolYear.class);

        when(doubleSchoolYearRepository.getAllSchoolYears()).thenReturn(List.of(doubleSchoolYear1, doubleSchoolYear2));

        // Act
        List<SchoolYear> doubleSchoolYears = controller.getAllSchoolYears();

        // Assert
        assertTrue(doubleSchoolYears.contains(doubleSchoolYear1), "The list should contain doubleSchoolYear1.");
        assertTrue(doubleSchoolYears.contains(doubleSchoolYear2), "The list should contain doubleSchoolYear2.");
    }

    @Test
    void shouldReturnExceptionIfProgrammeEditionEnrollmentRepoIsNull (){
        //arrange
        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
        ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
        CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(null,doubleProgrammeEditionRepository,
                    doubleProgrammeList, doubleCourseEditionEnrolmentRepository, doubleCourseEditionRepository, doubleSchoolYearRepository, doubleEnrolmentRepository);
        });
        //assert
        assertEquals("Programme edition enrolment repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfProgrammeEditionRepositoryIsNull (){
        //arrange
        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(ProgrammeEditionEnrolmentRepository.class);
        ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
        CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(doubleProgrammeEditionEnrolmentRepository,null,
                    doubleProgrammeList, doubleCourseEditionEnrolmentRepository, doubleCourseEditionRepository, doubleSchoolYearRepository, doubleEnrolmentRepository);
        });

        //assert
        assertEquals("Programme edition repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfProgrammeListIsNull (){
        //arrange
        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(ProgrammeEditionEnrolmentRepository.class);
        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
        CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(doubleProgrammeEditionEnrolmentRepository, doubleProgrammeEditionRepository,
                    null, doubleCourseEditionEnrolmentRepository, doubleCourseEditionRepository, doubleSchoolYearRepository, doubleEnrolmentRepository);
        });

        //assert
        assertEquals("Programme list cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfCourseEditionEnrollmentRepositoryIsNull (){
        //arrange
        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(ProgrammeEditionEnrolmentRepository.class);
        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
        ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
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
        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
        ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
        CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(doubleProgrammeEditionEnrolmentRepository, doubleProgrammeEditionRepository,
                    doubleProgrammeList, doubleCourseEditionEnrolmentRepository, null, doubleSchoolYearRepository, doubleEnrolmentRepository);
        });

        //assert
        assertEquals("Course edition repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfSchoolYearRepositoryIsNull (){
        //arrange
        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(ProgrammeEditionEnrolmentRepository.class);
        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
        ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
        CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(doubleProgrammeEditionEnrolmentRepository, doubleProgrammeEditionRepository,
                    doubleProgrammeList, doubleCourseEditionEnrolmentRepository, doubleCourseEditionRepository, null, doubleEnrolmentRepository);
        });

        //assert
        assertEquals("School year repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfEnrolmentRepositoryIsNull (){
        //arrange
        ProgrammeEditionEnrolmentRepository doubleProgrammeEditionEnrolmentRepository = mock(ProgrammeEditionEnrolmentRepository.class);
        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
        ProgrammeRepository doubleProgrammeList = mock(ProgrammeRepository.class);
        CourseEditionEnrolmentRepository doubleCourseEditionEnrolmentRepository = mock(CourseEditionEnrolmentRepository.class);
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(doubleProgrammeEditionEnrolmentRepository, doubleProgrammeEditionRepository,
                    doubleProgrammeList, doubleCourseEditionEnrolmentRepository, doubleCourseEditionRepository, doubleSchoolYearRepository, null);
        });

        //assert
        assertEquals("Enrolment repository cannot be null.", exception.getMessage());
    }


    //----------------------INTEGRATION TESTS------------------------------

   /*
    @Test
    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_Success_IntegrationTest() throws Exception {
        // Arrange
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory =  new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);

        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrolmentRepository,
                        programmeEditionRepository,
                        programmeRepository,
                        courseEditionEnrolmentRepository,
                        courseEditionRepository,
                        schoolYearRepository,
                        programmeEnrolmentRepository);
        ;
        Date date = new Date("20-12-2010");
        TeacherCategoryID tcID = new TeacherCategoryID();
        WorkingPercentage wp = new WorkingPercentage(100);
        TeacherID teacherID = TeacherID.createNew();
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        IAddressFactory addressFactory = new AddressFactoryImpl();
        Address add1 = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto",
                "Portugal", addressFactory,date, tcID, wp, teacherID, department1, new TeacherCareerProgressionFactoryImpl(),
                new TeacherCareerProgressionListFactoryImpl());

        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("23-11-2024");
        Date endDate = new Date ("09-12-2025");
        schoolYearRepository.addSchoolYear(description, startDate, endDate);
        SchoolYear schoolYear = schoolYearRepository.getCurrentSchoolYear();

        IAccessMethodFactory accessMethodFactory = new AccessMethodFactoryImpl();
        IAccessMethodListFactory accessMethodListFactory = new AccessMethodListFactoryImpl();
        AccessMethodRepository amr = new AccessMethodRepository(accessMethodFactory, accessMethodListFactory);
        AccessMethod am1 = new AccessMethod("Over 23");
        amr.registerAccessMethod("Over 23");
        StudentID studentID = new StudentID(1500000);

        Student student = new Student(studentID, "João Silva", "999999999", "221234567", "joao123@gmail.com", add1);

        Course c1 = new Course("Development", "DEV", 5, 1);
        Course c2 = new Course("Development1", "DEV1", 5, 1);

        Programme programme1 = new Programme("Computer Engineering", "CSE", 25, 6, master, department1, teacher1,
                new ProgrammeCourseListFactoryImpl (), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
                new CourseFactoryImpl());

        programme1.addCourseToAProgramme(c1);
        programme1.addCourseToAProgramme(c2);

        if (!programmeEnrolmentRepository.isStudentEnrolled(student, programme1)) {
            programmeEnrolmentRepository.enrolStudents(student, am1, programme1,date);
        }

        programmeEditionRepository.createProgrammeEdition(programme1, schoolYear);
        Optional<ProgrammeEdition> pe1Opt = programmeEditionRepository.findProgrammeEditionBySchoolYearAndProgramme(programme1, schoolYear);
        ProgrammeEdition pe1 = pe1Opt.get();

        courseEditionRepository.createAndSaveCourseEdition(c1, pe1);
        courseEditionRepository.createAndSaveCourseEdition(c2, pe1);

        CourseEdition ce1 = courseEditionRepository.getCourseEditions().get(0);
        CourseEdition ce2 = courseEditionRepository.getCourseEditions().get(1);

        // Act
        boolean result = controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(student, programme1, schoolYear);
        boolean result2 = courseEditionEnrolmentRepository.isStudentEnrolledInCourseEdition(student, ce1);
        boolean result3 = courseEditionEnrolmentRepository.isStudentEnrolledInCourseEdition(student, ce2);

        // Assert
        assertTrue(result, "The student is enrolled in the ProgrammeEdition.");
        assertTrue(result2, "The Student is enrolled in the CourseEdition.");
        assertTrue(result3, "The Student is enrolled in the CourseEdition.");
    }

    @Test
    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_StudentNotEnrolledInProgramme_IntegrationTest() throws Exception {
        // Arrange
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory =  new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);

        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrolmentRepository,
                        programmeEditionRepository,
                        programmeRepository,
                        courseEditionEnrolmentRepository,
                        courseEditionRepository,
                        schoolYearRepository,
                        programmeEnrolmentRepository);

        Date date = new Date("20-12-2010");
        TeacherCategoryID tcID = new TeacherCategoryID();
        WorkingPercentage wp = new WorkingPercentage(100);
        TeacherID teacherID = TeacherID.createNew();
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        IAddressFactory addressFactory = new AddressFactoryImpl();
        Address add1 = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
        StudentID studentID = new StudentID(1000001);
        Student student = new Student(studentID, "João Silva", "999999999", "221234567", "joao123@gmail.com", add1);
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto",
                "Portugal", addressFactory,date, tcID, wp, teacherID, department1, new TeacherCareerProgressionFactoryImpl(),
                new TeacherCareerProgressionListFactoryImpl());

        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("23-11-2024");
        Date endDate = new Date ("09-12-2025");
        schoolYearRepository.addSchoolYear(description, startDate,endDate);
        SchoolYear schoolYear = schoolYearRepository.getCurrentSchoolYear();

        IAccessMethodFactory accessMethodFactory = new AccessMethodFactoryImpl();
        IAccessMethodListFactory accessMethodListFactory = new AccessMethodListFactoryImpl();
        AccessMethodRepository amr = new AccessMethodRepository(accessMethodFactory, accessMethodListFactory);
        amr.registerAccessMethod("Over 23");

        Programme programme1 = new Programme("Computer Engineering", "CSE", 25, 6, master, department1, teacher1,
                new ProgrammeCourseListFactoryImpl (), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
                new CourseFactoryImpl());

        programmeEditionRepository.createProgrammeEdition(programme1, schoolYear);

        // Act
        boolean result = controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(student, programme1, schoolYear);

        //Assert
        assertFalse(result);
    }

    @Test
    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_ProgrammeEditionNotFound_IntegrationTests() throws Exception {
        // Arrange
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory =  new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);

        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrolmentRepository,
                        programmeEditionRepository,
                        programmeRepository,
                        courseEditionEnrolmentRepository,
                        courseEditionRepository,
                        schoolYearRepository,
                        programmeEnrolmentRepository);

        Date date = new Date("20-12-2010");
        TeacherCategoryID tcID = new TeacherCategoryID();
        WorkingPercentage wp = new WorkingPercentage(100);
        TeacherID teacherID = TeacherID.createNew();
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        IAddressFactory addressFactory = new AddressFactoryImpl();
        Address add1 = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto",
                "Portugal", addressFactory,date, tcID, wp, teacherID, department1, new TeacherCareerProgressionFactoryImpl(),
                new TeacherCareerProgressionListFactoryImpl());

        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("23-12-2024");
        Date endDate = new Date ("09-12-2025");
        schoolYearRepository.addSchoolYear(description, startDate, endDate);
        SchoolYear schoolYear = schoolYearRepository.getCurrentSchoolYear();

        IAccessMethodFactory accessMethodFactory = new AccessMethodFactoryImpl();
        IAccessMethodListFactory accessMethodListFactory = new AccessMethodListFactoryImpl();
        AccessMethodRepository amr = new AccessMethodRepository(accessMethodFactory, accessMethodListFactory);
        AccessMethod am1 = new AccessMethod("Over 23");
        amr.registerAccessMethod("Over 23");
        StudentID studentID = new StudentID(1000001);

        Student student = new Student(studentID, "João Silva", "999999999", "221234567", "joao123@gmail.com", add1);
        Programme programme1 = new Programme("Computer Engineering", "CSE", 25, 6, master, department1, teacher1,
                new ProgrammeCourseListFactoryImpl (), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
                new CourseFactoryImpl());

        if (!programmeEnrolmentRepository.isStudentEnrolled(student, programme1)) {
            programmeEnrolmentRepository.enrolStudents(student, am1, programme1,date);
        }
        // Act
        boolean result = controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(student, programme1, schoolYear);

        assertFalse(result);
    }


    @Test
    void testEnrolStudentInProgrammeEditionAndSetOfCoursesEditions_StudentAlreadyEnrolledInProgrammeEdition_IntegrationTest() throws Exception {
        // Arrange
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory =  new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);

        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrolmentRepository,
                        programmeEditionRepository,
                        programmeRepository,
                        courseEditionEnrolmentRepository,
                        courseEditionRepository,
                        schoolYearRepository,
                        programmeEnrolmentRepository);

        Date date = new Date("20-12-2010");
        TeacherCategoryID tcID = new TeacherCategoryID();
        WorkingPercentage wp = new WorkingPercentage(100);
        TeacherID teacherID = TeacherID.createNew();
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        IAddressFactory addressFactory = new AddressFactoryImpl();
        Address add1 = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto",
                "Portugal", addressFactory,date, tcID, wp, teacherID, department1, new TeacherCareerProgressionFactoryImpl(),
                new TeacherCareerProgressionListFactoryImpl());

        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("23-11-2024");
        Date endDate = new Date ("09-12-2025");
        schoolYearRepository.addSchoolYear(description, startDate,endDate);
        SchoolYear schoolYear = schoolYearRepository.getCurrentSchoolYear();

        IAccessMethodFactory accessMethodFactory = new AccessMethodFactoryImpl();
        IAccessMethodListFactory accessMethodListFactory = new AccessMethodListFactoryImpl();
        AccessMethodRepository amr = new AccessMethodRepository(accessMethodFactory, accessMethodListFactory);
        AccessMethod am1 = new AccessMethod("Over 23");
        amr.registerAccessMethod("Over 23");
        StudentID studentID = new StudentID(1000001);

        Student student = new Student(studentID, "João Silva", "999999999", "221234567", "joao123@gmail.com", add1);
        Programme programme1 = new Programme("Computer Engineering", "CSE", 25, 6, master, department1, teacher1,
                new ProgrammeCourseListFactoryImpl (), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
                new CourseFactoryImpl());

        if (!programmeEnrolmentRepository.isStudentEnrolled(student, programme1)) {
            programmeEnrolmentRepository.enrolStudents(student, am1, programme1,date);
        }

        programmeEditionRepository.createProgrammeEdition(programme1, schoolYear);
        Optional<ProgrammeEdition> peOptional = programmeEditionRepository.findProgrammeEditionBySchoolYearAndProgramme(programme1, schoolYear);
        ProgrammeEdition programmeEdition = peOptional.get();
        programmeEditionEnrolmentRepository.enrolStudentInProgrammeEdition(student, programmeEdition);

        // Act
        boolean result = controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(student, programme1, schoolYear);

        //assert
        assertFalse(result);
    }


    @Test
    void testEnrollStudentInCourseEditionAndSetOfCoursesEditions_StudentAlreadyEnrolledInCourseEdition_IntegrationTest() throws Exception {
        // Arrange
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory =  new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);

        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrolmentRepository,
                        programmeEditionRepository,
                        programmeRepository,
                        courseEditionEnrolmentRepository,
                        courseEditionRepository,
                        schoolYearRepository,
                        programmeEnrolmentRepository);

        Date date = new Date("20-12-2010");
        TeacherCategoryID tcID = new TeacherCategoryID();
        WorkingPercentage wp = new WorkingPercentage(100);
        TeacherID teacherID = TeacherID.createNew();
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        IAddressFactory addressFactory = new AddressFactoryImpl();
        Address add1 = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto",
                "Portugal", addressFactory,date, tcID, wp, teacherID, department1, new TeacherCareerProgressionFactoryImpl(),
                new TeacherCareerProgressionListFactoryImpl());

        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("23-11-2024");
        Date endDate = new Date ("09-12-2025");
        schoolYearRepository.addSchoolYear(description, startDate,endDate);
        SchoolYear schoolYear = schoolYearRepository.getCurrentSchoolYear();

        IAccessMethodFactory accessMethodFactory = new AccessMethodFactoryImpl();
        IAccessMethodListFactory accessMethodListFactory = new AccessMethodListFactoryImpl();
        AccessMethodRepository amr = new AccessMethodRepository(accessMethodFactory, accessMethodListFactory);
        AccessMethod am1 = new AccessMethod("Over 23");
        amr.registerAccessMethod("Over 23");
        StudentID studentID = new StudentID(1000001);

        Student student = new Student(studentID, "João Silva", "999999999", "221234567", "joao123@gmail.com", add1);
        Programme programme1 = new Programme("Computer Engineering", "CSE", 25, 6, master, department1, teacher1,
                new ProgrammeCourseListFactoryImpl (), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
                new CourseFactoryImpl());

        if (!programmeEnrolmentRepository.isStudentEnrolled(student, programme1)) {
            programmeEnrolmentRepository.enrolStudents(student, am1, programme1,date);
        }
        programmeEditionRepository.createProgrammeEdition(programme1, schoolYear);
        Optional<ProgrammeEdition> pe1Opt = programmeEditionRepository.findProgrammeEditionBySchoolYearAndProgramme(programme1, schoolYear);
        ProgrammeEdition pe1 = pe1Opt.get();
        Course c1 = new Course("Development", "DEV", 5, 1);
        Course c2 = new Course("Development1", "DEV1", 5, 1);
        programme1.addCourseToAProgramme(c1);
        programme1.addCourseToAProgramme(c2);
        courseEditionRepository.createAndSaveCourseEdition(c1, pe1);
        courseEditionRepository.createAndSaveCourseEdition(c2, pe1);
        CourseEdition ce1 = courseEditionRepository.getCourseEditions().get(0);
        CourseEdition ce2 = courseEditionRepository.getCourseEditions().get(1);
        courseEditionEnrolmentRepository.enrolStudentInACourseEdition(student, ce1);
        courseEditionEnrolmentRepository.enrolStudentInACourseEdition(student, ce2);
        // Act + Assert
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(student, programme1, schoolYear);
        });
        assertEquals("This course edition enrolment is already in the list.", exception.getMessage());
    }


    @Test
    void testGetAllProgrammes_NotNull_IntegrationTest() throws Exception {
        // Arrange
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);

        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrolmentRepository,
                        programmeEditionRepository,
                        programmeRepository,
                        courseEditionEnrolmentRepository,
                        courseEditionRepository,
                        schoolYearRepository,
                        programmeEnrolmentRepository);


        Date date = new Date("20-12-2010");
        TeacherCategoryID tcID = new TeacherCategoryID();
        WorkingPercentage wp = new WorkingPercentage(100);
        TeacherID teacherID = TeacherID.createNew();
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        IAddressFactory addressFactory = new AddressFactoryImpl();
        Acronym acronym1 = new Acronym("CSE");
        Acronym acronym2 = new Acronym("CVE");
        NameWithNumbersAndSpecialChars name1 = new NameWithNumbersAndSpecialChars("Computer Engineering");
        NameWithNumbersAndSpecialChars name2 = new NameWithNumbersAndSpecialChars("Civil Engineering");
        QuantEcts quantEcts = new QuantEcts(25);
        QuantSemesters quantSemesters = new QuantSemesters(6);
        Address add1 = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto",
                "Portugal", addressFactory, date, tcID, wp, teacherID, department1, new TeacherCareerProgressionFactoryImpl(),
                new TeacherCareerProgressionListFactoryImpl());

        Programme programme1 = new Programme(name1, acronym1, quantEcts, quantSemesters, master, department1, teacher1,
                new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
                new CourseFactoryImpl());
        programmeRepository.registerProgramme(name1, acronym1, quantEcts, quantSemesters, master, department1, teacher1,
                new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
                new CourseFactoryImpl());
        programmeRepository.registerProgramme(name2, acronym2, quantEcts, quantSemesters, master, department1, teacher1,
                new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
                new CourseFactoryImpl());

        // Act
        List<Programme> programmes = controller.getAllProgrammes();

        // Assert
        assertNotNull(programmes, "The list of programmes should not be null.");
    }

@Test
void testGetAllProgrammes_ListSize_IntegrationTest() throws Exception {
    // Arrange
    IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
    IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
    ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
    ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
    ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
    ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
    IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
    IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
    ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
    ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
    ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
    CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
    ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
    ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
    CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
    SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
    SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
    SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
    IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
    IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
    ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);

    US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                    programmeEditionEnrolmentRepository,
                    programmeEditionRepository,
                    programmeRepository,
                    courseEditionEnrolmentRepository,
                    courseEditionRepository,
                    schoolYearRepository,
                    programmeEnrolmentRepository);

    Date date = new Date("20-12-2010");
    TeacherCategoryID tcID = new TeacherCategoryID();
    WorkingPercentage wp = new WorkingPercentage(100);
    TeacherID teacherID = TeacherID.createNew();
    DegreeType master = new DegreeType("Master", 240);
    Department department1 = new Department("DEI", "Departamento Engenharia Informática");
    IAddressFactory addressFactory = new AddressFactoryImpl();
    Acronym acronym1 = new Acronym("CSE");
    Acronym acronym2 = new Acronym("CVE");
    NameWithNumbersAndSpecialChars name1 = new NameWithNumbersAndSpecialChars("Computer Engineering");
    NameWithNumbersAndSpecialChars name2 = new NameWithNumbersAndSpecialChars("Civil Engineering");
    QuantEcts quantEcts = new QuantEcts(30);
    QuantSemesters quantSemesters = new QuantSemesters(6);
    Address add1 = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
    Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "+351 912 345 678",
            "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto",
            "Portugal", addressFactory, date, tcID, wp, teacherID, department1, new TeacherCareerProgressionFactoryImpl(),
            new TeacherCareerProgressionListFactoryImpl());
    Programme programme1 = new Programme(name1, acronym1, quantEcts, quantSemesters, master, department1, teacher1,
            new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
            new CourseFactoryImpl());
    programmeRepository.registerProgramme(name1, acronym1, quantEcts, quantSemesters, master, department1, teacher1,
            new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
            new CourseFactoryImpl());
    programmeRepository.registerProgramme(name2, acronym2, quantEcts, quantSemesters, master, department1, teacher1,
            new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
            new CourseFactoryImpl());

    // Act
    List<Programme> programmes = controller.getAllProgrammes();

    // Assert
    assertEquals(2, programmes.size(), "The list of programmes should contain exactly 2 programmes.");

    }

    @Test
    void testGetAllProgrammes_IntegrationTest() throws Exception {
        // Arrange
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);

        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrolmentRepository,
                        programmeEditionRepository,
                        programmeRepository,
                        courseEditionEnrolmentRepository,
                        courseEditionRepository,
                        schoolYearRepository,
                        programmeEnrolmentRepository);

        Date date = new Date("20-12-2010");
        TeacherCategoryID tcID = new TeacherCategoryID();
        WorkingPercentage wp = new WorkingPercentage(100);
        TeacherID teacherID = TeacherID.createNew();
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        IAddressFactory addressFactory = new AddressFactoryImpl();
        Acronym acronym1 = new Acronym("CSE");
        Acronym acronym2 = new Acronym("CVE");
        NameWithNumbersAndSpecialChars name1 = new NameWithNumbersAndSpecialChars("Computer Engineering");
        NameWithNumbersAndSpecialChars name2 = new NameWithNumbersAndSpecialChars("Civil Engineering");
        QuantEcts quantEcts = new QuantEcts(30);
        QuantSemesters quantSemesters = new QuantSemesters(6);
        Address add1 = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto",
                "Portugal", addressFactory, date, tcID, wp, teacherID, department1, new TeacherCareerProgressionFactoryImpl(),
                new TeacherCareerProgressionListFactoryImpl());
        Programme programme1 = new Programme(name1, acronym1, quantEcts, quantSemesters, master, department1, teacher1,
                new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
                new CourseFactoryImpl());
        programmeRepository.registerProgramme(name1, acronym1, quantEcts, quantSemesters, master, department1, teacher1,
                new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
                new CourseFactoryImpl());
        programmeRepository.registerProgramme(name2, acronym2, quantEcts, quantSemesters, master, department1, teacher1,
                new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
                new CourseFactoryImpl());

        // Act
        List<Programme> programmes = controller.getAllProgrammes();

        // Assert
        assertTrue(programmes.contains(new Programme(name1, acronym1, quantEcts, quantSemesters, master, department1, teacher1,
                new ProgrammeCourseListFactoryImpl (), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
                new CourseFactoryImpl())));
        assertTrue(programmes.contains(new Programme(name2, acronym2, quantEcts, quantSemesters, master, department1, teacher1,
               new ProgrammeCourseListFactoryImpl (), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
                new CourseFactoryImpl())));

    }
    @Test
    void testGetAllSchoolYears_IntegrationTest() throws Exception {
        // Arrange
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);

        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrolmentRepository,
                        programmeEditionRepository,
                        programmeRepository,
                        courseEditionEnrolmentRepository,
                        courseEditionRepository,
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
        List<SchoolYear> schoolYears = controller.getAllSchoolYears();

        // Assert
        assertTrue(schoolYears.contains(new SchoolYear(description1, startDate1,endDate1)),
                "The list should contain the school year '24/25'.");
        assertTrue(schoolYears.contains(new SchoolYear(description2, startDate2, endDate2)),
                "The list should contain the school year '25/26'.");
    }

    */
    @Test
    void testGetAllSchoolYears_NotNullList_IntegrationTest() throws Exception {
        // Arrange
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);

        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrolmentRepository,
                        programmeEditionRepository,
                        programmeRepository,
                        courseEditionEnrolmentRepository,
                        courseEditionRepository,
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
        List<SchoolYear> schoolYears = controller.getAllSchoolYears();

        // Assert
        assertNotNull(schoolYears, "The list of school years should not be null.");

    }

    @Test
    void testGetAllSchoolYears_ListSize_IntegrationTest() throws Exception {

        // Arrange
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);

        US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrolmentRepository,
                        programmeEditionRepository,
                        programmeRepository,
                        courseEditionEnrolmentRepository,
                        courseEditionRepository,
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
        List<SchoolYear> schoolYears = controller.getAllSchoolYears();

        // Assert
        assertEquals(2, schoolYears.size(), "The list of school years should contain exactly 2 years.");

    }

    @Test
    void shouldReturnExceptionIfProgrammeEditionEnrollmentRepoIsNull_IntegrationTest (){
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(null,programmeEditionRepository,
                    programmeRepository, courseEditionEnrolmentRepository, courseEditionRepository, schoolYearRepository, programmeEnrolmentRepository);
        });
        //assert
        assertEquals("Programme edition enrolment repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfProgrammeEditionRepositoryIsNull_IntegrationTest (){
        //arrange
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();;
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrolmentRepository,null,
                    programmeRepository, courseEditionEnrolmentRepository, courseEditionRepository, schoolYearRepository, programmeEnrolmentRepository);
        });

        //assert
        assertEquals("Programme edition repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfProgrammeListIsNull_IntegrationTest (){
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();;
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrolmentRepository,programmeEditionRepository,
                    null, courseEditionEnrolmentRepository, courseEditionRepository, schoolYearRepository,programmeEnrolmentRepository);
        });

        //assert
        assertEquals("Programme list cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfCourseEditionEnrollmentRepositoryIsNull_IntegrationTest (){
        //arranje
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);
        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrolmentRepository,programmeEditionRepository,
                    programmeRepository, null, courseEditionRepository, schoolYearRepository,programmeEnrolmentRepository);
        });

        //assert
        assertEquals("Course edition enrolment repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfCourseEditionRepositoryIsNull_IntegrationTest (){
        //arranje
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrolmentRepository,programmeEditionRepository,
                    programmeRepository, courseEditionEnrolmentRepository, null, schoolYearRepository,programmeEnrolmentRepository);
        });

        //assert
        assertEquals("Course edition repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfSchoolYearRepositoryIsNull_IntegrationTest (){
        //arranje
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = new ProgrammeEnrolmentFactoryImpl();
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentList);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrolmentRepository,programmeEditionRepository,
                    programmeRepository, courseEditionEnrolmentRepository, courseEditionRepository, null, programmeEnrolmentRepository);
        });

        //assert
        assertEquals("School year repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfProgrammeEnrollmentRepositoryIsNull_IntegrationTest (){
        //arranje
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactory, programmeEditionEnrolmentListFactory);
        IProgrammeFactory IProgrammeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        ProgrammeRepository programmeRepository = new ProgrammeRepository(IProgrammeFactory, IProgrammeRepositoryListFactory);
        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);
        SchoolYearFactoryImpl schoolYearFactoryImpl = new SchoolYearFactoryImpl();
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrolmentRepository,programmeEditionRepository,
                    programmeRepository, courseEditionEnrolmentRepository, courseEditionRepository, schoolYearRepository, null);
        });

        //assert
        assertEquals("Enrolment repository cannot be null.", exception.getMessage());
    }

}



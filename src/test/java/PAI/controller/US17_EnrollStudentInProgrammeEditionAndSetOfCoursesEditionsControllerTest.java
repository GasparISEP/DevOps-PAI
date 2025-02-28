package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

class US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsControllerTest {

    @Test
    void testEnrollStudentInProgrammeEditionAndSetOfCoursesEditions_Success() {
        // Arrange
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = mock(ProgrammeEditionEnrollmentRepo.class);
        Programme doubleProgramme = mock(Programme.class);
        SchoolYear doubleSchoolYear = mock(SchoolYear.class);
        Student doubleStudent = mock(Student.class);
        ProgrammeEdition doubleProgrammeEdition = mock(ProgrammeEdition.class);
        ProgrammeList doubleProgrammeList = mock(ProgrammeList.class);
        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
        CourseEditionEnrollmentRepository doubleCourseEditionEnrollmentRepository = mock(CourseEditionEnrollmentRepository.class);
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrollmentRepo,
                        doubleProgrammeEditionRepository,
                        doubleProgrammeList,
                        doubleCourseEditionEnrollmentRepository,
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

        when(programmeEditionEnrollmentRepo.isStudentEnrolledInThisProgrammeEdition(doubleStudent, doubleProgrammeEdition)).thenReturn(false);

        when(doubleCourseEditionEnrollmentRepository.isStudentEnrolledInCourseEdition(doubleStudent, doubleCe1)).thenReturn(true);

        when(doubleCourseEditionEnrollmentRepository.isStudentEnrolledInCourseEdition(doubleStudent, doubleCe2)).thenReturn(true);

        // Act
        boolean result = controller.enrollStudentInProgrammeEditionAndSetOfCoursesEditions(doubleStudent, doubleProgramme, doubleSchoolYear);
        boolean result2 = doubleCourseEditionEnrollmentRepository.isStudentEnrolledInCourseEdition(doubleStudent, doubleCe1);
        boolean result3 = doubleCourseEditionEnrollmentRepository.isStudentEnrolledInCourseEdition(doubleStudent, doubleCe2);

        // Assert
        assertTrue(result, "Student should be enrolled in Programme.");
        assertTrue(result2, "The Student should be enrolled in CourseEdition 1.");
        assertTrue(result3, "The Student should be enrolled in CourseEdition 2.");
    }

    @Test
    void testEnrollStudentInProgrammeEditionAndSetOfCoursesEditions_StudentNotEnrolledInProgramme() {
        // Arrange
        ProgrammeEditionEnrollmentRepo doubleProgrammeEditionEnrollmentRepo = mock(ProgrammeEditionEnrollmentRepo.class);
        Programme doubleProgramme = mock(Programme.class);
        SchoolYear doubleSchoolYear = mock(SchoolYear.class);
        Student doubleStudent = mock(Student.class);
        ProgrammeList doubleProgrammeList = mock(ProgrammeList.class);
        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
        CourseEditionEnrollmentRepository doubleCourseEditionEnrollmentRepository = mock(CourseEditionEnrollmentRepository.class);
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        doubleProgrammeEditionEnrollmentRepo,
                        doubleProgrammeEditionRepository,
                        doubleProgrammeList,
                        doubleCourseEditionEnrollmentRepository,
                        doubleCourseEditionRepository,
                        doubleSchoolYearRepository,
                        doubleProgrammeEnrolmentRepository);

        doubleProgrammeEditionRepository.createProgrammeEdition(doubleProgramme, doubleSchoolYear);
        // Act & Assert:
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            controller.enrollStudentInProgrammeEditionAndSetOfCoursesEditions(doubleStudent, doubleProgramme, doubleSchoolYear);
        });
        assertEquals("Student should be enrolled in Programme.", exception.getMessage());
    }

    @Test
    void testEnrollStudentInProgrammeEditionAndSetOfCoursesEditions_ProgrammeEditionNotFound() {
        // Arrange
        ProgrammeEditionEnrollmentRepo doubleProgrammeEditionEnrollmentRepo = mock(ProgrammeEditionEnrollmentRepo.class);
        Programme doubleProgramme = mock(Programme.class);
        SchoolYear doubleSchoolYear = mock(SchoolYear.class);
        Student doubleStudent = mock(Student.class);
        ProgrammeList doubleProgrammeList = mock(ProgrammeList.class);
        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
        CourseEditionEnrollmentRepository doubleCourseEditionEnrollmentRepository = mock(CourseEditionEnrollmentRepository.class);
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        doubleProgrammeEditionEnrollmentRepo,
                        doubleProgrammeEditionRepository,
                        doubleProgrammeList,
                        doubleCourseEditionEnrollmentRepository,
                        doubleCourseEditionRepository,
                        doubleSchoolYearRepository,
                        doubleProgrammeEnrolmentRepository);

        when(doubleProgrammeEnrolmentRepository.isStudentEnrolled(doubleStudent, doubleProgramme))
                .thenReturn(true);
        // Act & Assert
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            controller.enrollStudentInProgrammeEditionAndSetOfCoursesEditions(doubleStudent, doubleProgramme, doubleSchoolYear);
        });
        assertEquals("Programme edition not found for the given school year.", exception.getMessage());
    }

    @Test
    void testEnrollStudentInProgrammeEditionAndSetOfCoursesEditions_StudentAlreadyEnrolledInProgrammeEdition() {
        // Arrange
        ProgrammeEditionEnrollmentRepo doubleProgrammeEditionEnrollmentRepo = mock(ProgrammeEditionEnrollmentRepo.class);
        Programme doubleProgramme = mock(Programme.class);
        SchoolYear doubleSchoolYear = mock(SchoolYear.class);
        Student doubleStudent = mock(Student.class);
        ProgrammeEdition doubleProgrammeEdition = mock(ProgrammeEdition.class);
        ProgrammeList doubleProgrammeList = mock(ProgrammeList.class);
        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
        CourseEditionEnrollmentRepository doubleCourseEditionEnrollmentRepository = mock(CourseEditionEnrollmentRepository.class);
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        doubleProgrammeEditionEnrollmentRepo,
                        doubleProgrammeEditionRepository,
                        doubleProgrammeList,
                        doubleCourseEditionEnrollmentRepository,
                        doubleCourseEditionRepository,
                        doubleSchoolYearRepository,
                        doubleProgrammeEnrolmentRepository);

        when(doubleProgrammeEnrolmentRepository.isStudentEnrolled(doubleStudent, doubleProgramme)).thenReturn(true);
        when(doubleProgrammeEditionRepository.findProgrammeEditionBySchoolYearAndProgramme(doubleProgramme, doubleSchoolYear)).thenReturn(Optional.of(doubleProgrammeEdition));

        when(doubleProgrammeEditionEnrollmentRepo.isStudentEnrolledInThisProgrammeEdition(doubleStudent, doubleProgrammeEdition)).thenReturn(true);
        // Act & Assert
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            controller.enrollStudentInProgrammeEditionAndSetOfCoursesEditions(doubleStudent, doubleProgramme, doubleSchoolYear);
        });
        assertEquals("Student is already enrolled in the programme edition.", exception.getMessage());
    }

    @Test
    void testEnrollStudentInCourseEditionAndSetOfCoursesEditions_StudentAlreadyEnrolledInCourseEdition() {
        // Mocks
        ProgrammeEditionEnrollmentRepo doubleProgrammeEditionEnrollmentRepo = mock(ProgrammeEditionEnrollmentRepo.class);
        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
        Programme doubleProgramme = mock(Programme.class);
        SchoolYear doubleSchoolYear = mock(SchoolYear.class);
        Student doubleStudent = mock(Student.class);
        ProgrammeEdition doubleProgrammeEdition = mock(ProgrammeEdition.class);
        ProgrammeList doubleProgrammeList = mock(ProgrammeList.class);
        CourseEditionEnrollmentRepository doubleCourseEditionEnrollmentRepository = mock(CourseEditionEnrollmentRepository.class);
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleProgrammeEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);


        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        doubleProgrammeEditionEnrollmentRepo,
                        doubleProgrammeEditionRepository,
                        doubleProgrammeList,
                        doubleCourseEditionEnrollmentRepository,
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


        when(doubleCourseEditionEnrollmentRepository.findByStudentAndEdition(doubleStudent, ce1))
                .thenReturn(Optional.of(new CourseEditionEnrollment(doubleStudent, ce1, LocalDate.now())));

        when(doubleCourseEditionEnrollmentRepository.findByStudentAndEdition(doubleStudent, ce2))
                .thenReturn(Optional.of(new CourseEditionEnrollment(doubleStudent, ce2, LocalDate.now())));


        doThrow(new IllegalStateException("This course edition enrollment is already in the list."))
                .when(doubleCourseEditionEnrollmentRepository)
                .enrollStudentInProgrammeCourseEditions(any(Student.class), anyList());

        // Act + Assert
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            controller.enrollStudentInProgrammeEditionAndSetOfCoursesEditions(doubleStudent, doubleProgramme, doubleSchoolYear);
        });

        assertEquals("This course edition enrollment is already in the list.", exception.getMessage());
    }

    @Test
    void testGetAllProgrammes() throws Exception {
        // Arrange
         ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
         ProgrammeEditionEnrollmentRepo doubleProgrammeEditionEnrollmentRepo = mock (ProgrammeEditionEnrollmentRepo.class);
         ProgrammeList doubleProgrammeList = mock(ProgrammeList.class);
         CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
         CourseEditionEnrollmentRepository doubleCourseEditionEnrollmentRepository = mock(CourseEditionEnrollmentRepository.class);
         SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
         ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
         US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        doubleProgrammeEditionEnrollmentRepo,
                        doubleProgrammeEditionRepository,
                        doubleProgrammeList,
                        doubleCourseEditionEnrollmentRepository,
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
        assertEquals(2, doubleProgrammes.size(), "The list of programmes should contain exactly 2 programmes.");
        assertTrue(doubleProgrammes.contains(doubleProgramme1));
        assertTrue(doubleProgrammes.contains(doubleProgramme2));
    }

    @Test
    void testGetAllSchoolYears() throws Exception {

        // Arrange
        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
        ProgrammeEditionEnrollmentRepo doubleProgrammeEditionEnrollmentRepo = mock (ProgrammeEditionEnrollmentRepo.class);
        ProgrammeList doubleProgrammeList = mock(ProgrammeList.class);
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        CourseEditionEnrollmentRepository doubleCourseEditionEnrollmentRepository = mock(CourseEditionEnrollmentRepository.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        doubleProgrammeEditionEnrollmentRepo,
                        doubleProgrammeEditionRepository,
                        doubleProgrammeList,
                        doubleCourseEditionEnrollmentRepository,
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
        assertEquals(2, doubleSchoolYears.size(), "The list of school years should contain exactly 2 years.");
        assertTrue(doubleSchoolYears.contains(doubleSchoolYear1));
        assertTrue(doubleSchoolYears.contains(doubleSchoolYear2));
    }

    @Test
    void shouldReturnExceptionIfProgrammeEditionEnrollmentRepoIsNull (){
        //arrange
        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
        ProgrammeList doubleProgrammeList = mock(ProgrammeList.class);
        CourseEditionEnrollmentRepository doubleCourseEditionEnrollmentRepository = mock(CourseEditionEnrollmentRepository.class);
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(null,doubleProgrammeEditionRepository,
                    doubleProgrammeList, doubleCourseEditionEnrollmentRepository, doubleCourseEditionRepository, doubleSchoolYearRepository, doubleEnrolmentRepository);
        });
        //assert
        assertEquals("Programme edition enrollment repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfProgrammeEditionRepositoryIsNull (){
        //arrange
        ProgrammeEditionEnrollmentRepo doubleProgrammeEditionEnrollmentRepo = mock(ProgrammeEditionEnrollmentRepo.class);
        ProgrammeList doubleProgrammeList = mock(ProgrammeList.class);
        CourseEditionEnrollmentRepository doubleCourseEditionEnrollmentRepository = mock(CourseEditionEnrollmentRepository.class);
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(doubleProgrammeEditionEnrollmentRepo,null,
                    doubleProgrammeList, doubleCourseEditionEnrollmentRepository, doubleCourseEditionRepository, doubleSchoolYearRepository, doubleEnrolmentRepository);
        });

        //assert
        assertEquals("Programme edition repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfProgrammeListIsNull (){
        //arrange
        ProgrammeEditionEnrollmentRepo doubleProgrammeEditionEnrollmentRepo = mock(ProgrammeEditionEnrollmentRepo.class);
        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
        CourseEditionEnrollmentRepository doubleCourseEditionEnrollmentRepository = mock(CourseEditionEnrollmentRepository.class);
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(doubleProgrammeEditionEnrollmentRepo, doubleProgrammeEditionRepository,
                    null, doubleCourseEditionEnrollmentRepository, doubleCourseEditionRepository, doubleSchoolYearRepository, doubleEnrolmentRepository);
        });

        //assert
        assertEquals("Programme list cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfCourseEditionEnrollmentRepositoryIsNull (){
        //arrange
        ProgrammeEditionEnrollmentRepo doubleProgrammeEditionEnrollmentRepo = mock(ProgrammeEditionEnrollmentRepo.class);
        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
        ProgrammeList doubleProgrammeList = mock(ProgrammeList.class);
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(doubleProgrammeEditionEnrollmentRepo, doubleProgrammeEditionRepository,
                    doubleProgrammeList, null, doubleCourseEditionRepository, doubleSchoolYearRepository, doubleEnrolmentRepository);
        });

        //assert
        assertEquals("Course edition enrollment repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfCourseEditionRepositoryIsNull (){
        //arrange
        ProgrammeEditionEnrollmentRepo doubleProgrammeEditionEnrollmentRepo = mock(ProgrammeEditionEnrollmentRepo.class);
        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
        ProgrammeList doubleProgrammeList = mock(ProgrammeList.class);
        CourseEditionEnrollmentRepository doubleCourseEditionEnrollmentRepository = mock(CourseEditionEnrollmentRepository.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(doubleProgrammeEditionEnrollmentRepo, doubleProgrammeEditionRepository,
                    doubleProgrammeList, doubleCourseEditionEnrollmentRepository, null, doubleSchoolYearRepository, doubleEnrolmentRepository);
        });

        //assert
        assertEquals("Course edition repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfSchoolYearRepositoryIsNull (){
        //arrange
        ProgrammeEditionEnrollmentRepo doubleProgrammeEditionEnrollmentRepo = mock(ProgrammeEditionEnrollmentRepo.class);
        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
        ProgrammeList doubleProgrammeList = mock(ProgrammeList.class);
        CourseEditionEnrollmentRepository doubleCourseEditionEnrollmentRepository = mock(CourseEditionEnrollmentRepository.class);
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        ProgrammeEnrolmentRepository doubleEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(doubleProgrammeEditionEnrollmentRepo, doubleProgrammeEditionRepository,
                    doubleProgrammeList, doubleCourseEditionEnrollmentRepository, doubleCourseEditionRepository, null, doubleEnrolmentRepository);
        });

        //assert
        assertEquals("School year repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfEnrolmentRepositoryIsNull (){
        //arrange
        ProgrammeEditionEnrollmentRepo doubleProgrammeEditionEnrollmentRepo = mock(ProgrammeEditionEnrollmentRepo.class);
        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);
        ProgrammeList doubleProgrammeList = mock(ProgrammeList.class);
        CourseEditionEnrollmentRepository doubleCourseEditionEnrollmentRepository = mock(CourseEditionEnrollmentRepository.class);
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(doubleProgrammeEditionEnrollmentRepo, doubleProgrammeEditionRepository,
                    doubleProgrammeList, doubleCourseEditionEnrollmentRepository, doubleCourseEditionRepository, doubleSchoolYearRepository, null);
        });

        //assert
        assertEquals("Enrolment repository cannot be null.", exception.getMessage());
    }

}



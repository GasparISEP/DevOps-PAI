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
    void testEnrollStudentInProgrammeEditionAndSetOfCoursesEditions_Success() throws Exception {
        // Arrange
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = mock(ProgrammeEditionEnrollmentRepo.class);
        Programme programme = mock(Programme.class);
        SchoolYear schoolYear = mock(SchoolYear.class);
        Student student = mock(Student.class);
        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        ProgrammeList programmeList = mock(ProgrammeList.class);
        ProgrammeEditionRepository programmeEditionRepository = mock(ProgrammeEditionRepository.class);
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = mock(CourseEditionEnrollmentRepository.class);
        CourseEditionRepository courseEditionRepository = mock(CourseEditionRepository.class);
        SchoolYearRepository schoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrollmentRepo,
                        programmeEditionRepository,
                        programmeList,
                        courseEditionEnrollmentRepository,
                        courseEditionRepository,
                        schoolYearRepository,
                        programmeEnrolmentRepository);

        CourseEdition ce1 = mock(CourseEdition.class);
        CourseEdition ce2 = mock(CourseEdition.class);

        when(programmeEnrolmentRepository.isStudentEnrolled(student, programme))
                .thenReturn(true);

        when(programmeEditionRepository.findProgrammeEditionBySchoolYearAndProgramme(programme, schoolYear))
                .thenReturn(Optional.of(programmeEdition));

        when(courseEditionRepository.findCourseEditionsByProgrammeEdition(programmeEdition)).thenReturn(List.of(ce1, ce2));

        when(programmeEditionEnrollmentRepo.isStudentEnrolledInThisProgrammeEdition(student, programmeEdition)).thenReturn(false);

        when(courseEditionEnrollmentRepository.isStudentEnrolledInCourseEdition(student, ce1)).thenReturn(true);

        when(courseEditionEnrollmentRepository.isStudentEnrolledInCourseEdition(student, ce2)).thenReturn(true);

        // Act
        boolean result = controller.enrollStudentInProgrammeEditionAndSetOfCoursesEditions(student, programme, schoolYear);
        boolean result2 = courseEditionEnrollmentRepository.isStudentEnrolledInCourseEdition(student, ce1);
        boolean result3 = courseEditionEnrollmentRepository.isStudentEnrolledInCourseEdition(student, ce2);

        // Assert
        assertTrue(result, "Student should be enrolled in Programme.");
        assertTrue(result2, "The Student should be enrolled in CourseEdition 1.");
        assertTrue(result3, "The Student should be enrolled in CourseEdition 2.");
    }

    @Test
    void testEnrollStudentInProgrammeEditionAndSetOfCoursesEditions_StudentNotEnrolledInProgramme() throws Exception {
        // Arrange
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = mock(ProgrammeEditionEnrollmentRepo.class);
        Programme programme = mock(Programme.class);
        SchoolYear schoolYear = mock(SchoolYear.class);
        Student student = mock(Student.class);
        ProgrammeList programmeList = mock(ProgrammeList.class);
        ProgrammeEditionRepository programmeEditionRepository = mock(ProgrammeEditionRepository.class);
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = mock(CourseEditionEnrollmentRepository.class);
        CourseEditionRepository courseEditionRepository = mock(CourseEditionRepository.class);
        SchoolYearRepository schoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrollmentRepo,
                        programmeEditionRepository,
                        programmeList,
                        courseEditionEnrollmentRepository,
                        courseEditionRepository,
                        schoolYearRepository,
                        programmeEnrolmentRepository);

        programmeEditionRepository.createProgrammeEdition(programme, schoolYear);
        // Act & Assert:
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            controller.enrollStudentInProgrammeEditionAndSetOfCoursesEditions(student, programme, schoolYear);
        });
        assertEquals("Student should be enrolled in Programme.", exception.getMessage());
    }

    @Test
    void testEnrollStudentInProgrammeEditionAndSetOfCoursesEditions_ProgrammeEditionNotFound() throws Exception {
        // Arrange
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = mock(ProgrammeEditionEnrollmentRepo.class);
        Programme programme = mock(Programme.class);
        SchoolYear schoolYear = mock(SchoolYear.class);
        Student student = mock(Student.class);
        ProgrammeList programmeList = mock(ProgrammeList.class);
        ProgrammeEditionRepository programmeEditionRepository = mock(ProgrammeEditionRepository.class);
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = mock(CourseEditionEnrollmentRepository.class);
        CourseEditionRepository courseEditionRepository = mock(CourseEditionRepository.class);
        SchoolYearRepository schoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrollmentRepo,
                        programmeEditionRepository,
                        programmeList,
                        courseEditionEnrollmentRepository,
                        courseEditionRepository,
                        schoolYearRepository,
                        programmeEnrolmentRepository);

        when(programmeEnrolmentRepository.isStudentEnrolled(student, programme))
                .thenReturn(true);
        // Act & Assert
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            controller.enrollStudentInProgrammeEditionAndSetOfCoursesEditions(student, programme, schoolYear);
        });
        assertEquals("Programme edition not found for the given school year.", exception.getMessage());
    }

    @Test
    void testEnrollStudentInProgrammeEditionAndSetOfCoursesEditions_StudentAlreadyEnrolledInProgrammeEdition() throws Exception {
        // Arrange
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = mock(ProgrammeEditionEnrollmentRepo.class);
        Programme programme = mock(Programme.class);
        SchoolYear schoolYear = mock(SchoolYear.class);
        Student student = mock(Student.class);
        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        ProgrammeList programmeList = mock(ProgrammeList.class);
        ProgrammeEditionRepository programmeEditionRepository = mock(ProgrammeEditionRepository.class);
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = mock(CourseEditionEnrollmentRepository.class);
        CourseEditionRepository courseEditionRepository = mock(CourseEditionRepository.class);
        SchoolYearRepository schoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrollmentRepo,
                        programmeEditionRepository,
                        programmeList,
                        courseEditionEnrollmentRepository,
                        courseEditionRepository,
                        schoolYearRepository,
                        programmeEnrolmentRepository);

        when(programmeEnrolmentRepository.isStudentEnrolled(student, programme)).thenReturn(true);
        when(programmeEditionRepository.findProgrammeEditionBySchoolYearAndProgramme(programme, schoolYear)).thenReturn(Optional.of(programmeEdition));

        when(programmeEditionEnrollmentRepo.isStudentEnrolledInThisProgrammeEdition(student, programmeEdition)).thenReturn(true);
        // Act & Assert
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            controller.enrollStudentInProgrammeEditionAndSetOfCoursesEditions(student, programme, schoolYear);
        });
        assertEquals("Student is already enrolled in the programme edition.", exception.getMessage());
    }

    @Test
    void testEnrollStudentInCourseEditionAndSetOfCoursesEditions_StudentAlreadyEnrolledInCourseEdition() throws Exception {
        // Mocks
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = mock(ProgrammeEditionEnrollmentRepo.class);
        ProgrammeEditionRepository programmeEditionRepository = mock(ProgrammeEditionRepository.class);
        Programme programme = mock(Programme.class);
        SchoolYear schoolYear = mock(SchoolYear.class);
        Student student = mock(Student.class);
        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        ProgrammeList programmeList = mock(ProgrammeList.class);
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = mock(CourseEditionEnrollmentRepository.class);
        CourseEditionRepository courseEditionRepository = mock(CourseEditionRepository.class);
        SchoolYearRepository schoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);


        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrollmentRepo,
                        programmeEditionRepository,
                        programmeList,
                        courseEditionEnrollmentRepository,
                        courseEditionRepository,
                        schoolYearRepository,
                        programmeEnrolmentRepository);

        CourseEdition ce1 = mock(CourseEdition.class);
        CourseEdition ce2 = mock(CourseEdition.class);

        when(programmeEnrolmentRepository.isStudentEnrolled(student, programme))
                .thenReturn(true);


        when(programmeEditionRepository.findProgrammeEditionBySchoolYearAndProgramme(programme, schoolYear))
                .thenReturn(Optional.of(programmeEdition));


        when(courseEditionRepository.findCourseEditionsByProgrammeEdition(programmeEdition))
                .thenReturn(List.of(ce1, ce2));


        when(courseEditionEnrollmentRepository.findByStudentAndEdition(student, ce1))
                .thenReturn(Optional.of(new CourseEditionEnrollment(student, ce1, LocalDate.now())));

        when(courseEditionEnrollmentRepository.findByStudentAndEdition(student, ce2))
                .thenReturn(Optional.of(new CourseEditionEnrollment(student, ce2, LocalDate.now())));


        doThrow(new IllegalStateException("This course edition enrollment is already in the list."))
                .when(courseEditionEnrollmentRepository)
                .enrollStudentInProgrammeCourseEditions(any(Student.class), anyList());

        // Act + Assert
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            controller.enrollStudentInProgrammeEditionAndSetOfCoursesEditions(student, programme, schoolYear);
        });

        assertEquals("This course edition enrollment is already in the list.", exception.getMessage());
    }

    @Test
    void testGetAllProgrammes() throws Exception {
        // Arrange
         ProgrammeEditionRepository programmeEditionRepository = mock(ProgrammeEditionRepository.class);
         ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = mock (ProgrammeEditionEnrollmentRepo.class);
         ProgrammeList programmeList = mock(ProgrammeList.class);
         CourseEditionRepository courseEditionRepository = mock(CourseEditionRepository.class);
         CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = mock(CourseEditionEnrollmentRepository.class);
         SchoolYearRepository schoolYearRepository = mock(SchoolYearRepository.class);
         ProgrammeEnrolmentRepository enrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
         US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrollmentRepo,
                        programmeEditionRepository,
                        programmeList,
                        courseEditionEnrollmentRepository,
                        courseEditionRepository,
                        schoolYearRepository,
                        enrolmentRepository);

        Programme programme1 = mock(Programme.class);
        Programme programme2 = mock(Programme.class);

        when(programmeList.getAllProgrammes()).thenReturn(List.of(programme1, programme2));

        // Act
        List<Programme> programmes = controller.getAllProgrammes();
        // Assert
        assertNotNull(programmes, "The list of programmes should not be null.");
        assertEquals(2, programmes.size(), "The list of programmes should contain exactly 2 programmes.");
        assertTrue(programmes.contains(programme1));
        assertTrue(programmes.contains(programme2));
    }

    @Test
    void testGetAllSchoolYears() throws Exception {

        // Arrange
        ProgrammeEditionRepository programmeEditionRepository = mock(ProgrammeEditionRepository.class);
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = mock (ProgrammeEditionEnrollmentRepo.class);
        ProgrammeList programmeList = mock(ProgrammeList.class);
        CourseEditionRepository courseEditionRepository = mock(CourseEditionRepository.class);
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = mock(CourseEditionEnrollmentRepository.class);
        SchoolYearRepository schoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository enrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
        US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController controller =
                new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(
                        programmeEditionEnrollmentRepo,
                        programmeEditionRepository,
                        programmeList,
                        courseEditionEnrollmentRepository,
                        courseEditionRepository,
                        schoolYearRepository,
                        enrolmentRepository);
        SchoolYear schoolYear1 = mock(SchoolYear.class);
        SchoolYear schoolYear2 = mock(SchoolYear.class);

        when(schoolYearRepository.getAllSchoolYears()).thenReturn(List.of(schoolYear1, schoolYear2));
        // Act
        List<SchoolYear> schoolYears = controller.getAllSchoolYears();
        // Assert
        assertNotNull(schoolYears, "The list of school years should not be null.");
        assertEquals(2, schoolYears.size(), "The list of school years should contain exactly 2 years.");
        assertTrue(schoolYears.contains(schoolYear1));
        assertTrue(schoolYears.contains(schoolYear2));
    }

    @Test
    void shouldReturnExceptionIfProgrammeEditionEnrollmentRepoIsNull (){
        //arrange
        ProgrammeEditionRepository programmeEditionRepository = mock(ProgrammeEditionRepository.class);
        ProgrammeList programmeList = mock(ProgrammeList.class);
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = mock(CourseEditionEnrollmentRepository.class);
        CourseEditionRepository courseEditionRepository = mock(CourseEditionRepository.class);
        SchoolYearRepository schoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository enrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(null,programmeEditionRepository,
                    programmeList, courseEditionEnrollmentRepository, courseEditionRepository, schoolYearRepository, enrolmentRepository);
        });
        //assert
        assertEquals("Programme edition enrollment repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfProgrammeEditionRepositoryIsNull (){
        //arrange
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = mock(ProgrammeEditionEnrollmentRepo.class);
        ProgrammeList programmeList = mock(ProgrammeList.class);
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = mock(CourseEditionEnrollmentRepository.class);
        CourseEditionRepository courseEditionRepository = mock(CourseEditionRepository.class);
        SchoolYearRepository schoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository enrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrollmentRepo,null,
                    programmeList, courseEditionEnrollmentRepository, courseEditionRepository, schoolYearRepository, enrolmentRepository);
        });

        //assert
        assertEquals("Programme edition repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfProgrammeListIsNull (){
        //arrange
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = mock(ProgrammeEditionEnrollmentRepo.class);
        ProgrammeEditionRepository programmeEditionRepository = mock(ProgrammeEditionRepository.class);
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = mock(CourseEditionEnrollmentRepository.class);
        CourseEditionRepository courseEditionRepository = mock(CourseEditionRepository.class);
        SchoolYearRepository schoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository enrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrollmentRepo,programmeEditionRepository,
                    null, courseEditionEnrollmentRepository, courseEditionRepository, schoolYearRepository, enrolmentRepository);
        });

        //assert
        assertEquals("Programme list cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfCourseEditionEnrollmentRepositoryIsNull (){
        //arrange
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = mock(ProgrammeEditionEnrollmentRepo.class);
        ProgrammeEditionRepository programmeEditionRepository = mock(ProgrammeEditionRepository.class);
        ProgrammeList programmeList = mock(ProgrammeList.class);
        CourseEditionRepository courseEditionRepository = mock(CourseEditionRepository.class);
        SchoolYearRepository schoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository enrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrollmentRepo,programmeEditionRepository,
                    programmeList, null, courseEditionRepository, schoolYearRepository, enrolmentRepository);
        });

        //assert
        assertEquals("Course edition enrollment repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfCourseEditionRepositoryIsNull (){
        //arrange
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = mock(ProgrammeEditionEnrollmentRepo.class);
        ProgrammeEditionRepository programmeEditionRepository = mock(ProgrammeEditionRepository.class);
        ProgrammeList programmeList = mock(ProgrammeList.class);
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = mock(CourseEditionEnrollmentRepository.class);
        SchoolYearRepository schoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeEnrolmentRepository enrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrollmentRepo,programmeEditionRepository,
                    programmeList, courseEditionEnrollmentRepository, null, schoolYearRepository, enrolmentRepository);
        });

        //assert
        assertEquals("Course edition repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfSchoolYearRepositoryIsNull (){
        //arrange
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = mock(ProgrammeEditionEnrollmentRepo.class);
        ProgrammeEditionRepository programmeEditionRepository = mock(ProgrammeEditionRepository.class);
        ProgrammeList programmeList = mock(ProgrammeList.class);
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = mock(CourseEditionEnrollmentRepository.class);
        CourseEditionRepository courseEditionRepository = mock(CourseEditionRepository.class);
        ProgrammeEnrolmentRepository enrolmentRepository = mock(ProgrammeEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrollmentRepo,programmeEditionRepository,
                    programmeList, courseEditionEnrollmentRepository, courseEditionRepository, null, enrolmentRepository);
        });

        //assert
        assertEquals("School year repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfEnrolmentRepositoryIsNull (){
        //arrange
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = mock(ProgrammeEditionEnrollmentRepo.class);
        ProgrammeEditionRepository programmeEditionRepository = mock(ProgrammeEditionRepository.class);
        ProgrammeList programmeList = mock(ProgrammeList.class);
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository = mock(CourseEditionEnrollmentRepository.class);
        CourseEditionRepository courseEditionRepository = mock(CourseEditionRepository.class);
        SchoolYearRepository schoolYearRepository = mock(SchoolYearRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US17_EnrollStudentInProgrammeEditionAndSetOfCoursesEditionsController(programmeEditionEnrollmentRepo,programmeEditionRepository,
                    programmeList, courseEditionEnrollmentRepository, courseEditionRepository, schoolYearRepository, null);
        });

        //assert
        assertEquals("Enrolment repository cannot be null.", exception.getMessage());
    }

}



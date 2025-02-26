package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US16_EnrollAStudentInACourseEditionControllerTest {

    @Test
    void shouldReturnExceptionIfProgrammeEditionEnrollmentRepoIsNull (){
        //arrange
        CourseEditionEnrollmentRepository ceeRepository = mock (CourseEditionEnrollmentRepository.class);
        CourseEditionRepository courseEditionRepository = mock (CourseEditionRepository.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new US16_EnrollAStudentInACourseEditionController(ceeRepository, null, courseEditionRepository);
        });

        //assert
        assertEquals("Programme edition enrollment repository cannot be null!", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfCourseEditionRepositoryIsNull (){
        //arrange
        CourseEditionEnrollmentRepository ceeRepository = mock (CourseEditionEnrollmentRepository.class);
        ProgrammeEditionEnrollmentRepo peeRepository = mock (ProgrammeEditionEnrollmentRepo.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new US16_EnrollAStudentInACourseEditionController(ceeRepository, peeRepository, null);
        });

        //assert
        assertEquals("Course edition repository cannot be null!", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfCourseEditionEnrollmentRepositoryIsNull (){
        //arrange
        CourseEditionRepository ceRepository = mock(CourseEditionRepository.class);
        ProgrammeEditionEnrollmentRepo peeRepository = mock (ProgrammeEditionEnrollmentRepo.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new US16_EnrollAStudentInACourseEditionController(null, peeRepository, ceRepository);
        });

        //assert
        assertEquals("Course edition enrollment repository cannot be null!", exception.getMessage());
    }


    @Test
    void shouldReturnFalseIfStudentIsNotInProgrammeEditionThatHasCourseEdition() throws Exception {

        //arrange
        CourseEditionRepository courseEditionRepository = mock(CourseEditionRepository.class);
        ProgrammeEditionEnrollmentRepo peeRepository = mock(ProgrammeEditionEnrollmentRepo.class);
        CourseEditionEnrollmentRepository ceeRepository = mock(CourseEditionEnrollmentRepository.class);

        US16_EnrollAStudentInACourseEditionController controller = new US16_EnrollAStudentInACourseEditionController(
                ceeRepository, peeRepository, courseEditionRepository);

        Student st1 = mock (Student.class);
        Student st2 = mock (Student.class);
        CourseEdition ce1 = mock (CourseEdition.class);
        ProgrammeEdition pe1 = mock (ProgrammeEdition.class);

        when (courseEditionRepository.findWhichProgrammeEditionBelongsToACourseEdition(ce1)).thenReturn(pe1);
        when (peeRepository.isStudentEnrolledInThisProgrammeEdition(st1, pe1)).thenReturn(false);

        //act
        boolean result = controller.enrollStudentInCourseEdition(st2, ce1);

        //assert
        assertFalse(result);
    }


    @Test
    void shouldReturnTrueIfIsAValidCourseEditionEnrollment () throws Exception {

        //arrange
        CourseEditionRepository courseEditionRepository = mock(CourseEditionRepository.class);
        ProgrammeEditionEnrollmentRepo peeRepository = mock(ProgrammeEditionEnrollmentRepo.class);
        CourseEditionEnrollmentRepository ceeRepository = mock(CourseEditionEnrollmentRepository.class);

        US16_EnrollAStudentInACourseEditionController controller = new US16_EnrollAStudentInACourseEditionController(
                ceeRepository, peeRepository, courseEditionRepository);

        Student st1 = mock (Student.class);
        CourseEdition ce1 = mock (CourseEdition.class);
        ProgrammeEdition pe1 = mock (ProgrammeEdition.class);
        LocalDate enrollmentDate = LocalDate.now();

        when (courseEditionRepository.findWhichProgrammeEditionBelongsToACourseEdition(ce1)).thenReturn(pe1);
        when (peeRepository.isStudentEnrolledInThisProgrammeEdition(st1, pe1)).thenReturn(true);
        when (ceeRepository.enrollStudentInACourseEdition(st1,ce1, enrollmentDate)).thenReturn (true);

        //act
        boolean result = controller.enrollStudentInCourseEdition(st1,ce1);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseInStudentAndCourseEditionAreNull () throws Exception {
        //arrange
        CourseEditionRepository courseEditionRepository = mock(CourseEditionRepository.class);
        ProgrammeEditionEnrollmentRepo peeRepository = mock(ProgrammeEditionEnrollmentRepo.class);
        CourseEditionEnrollmentRepository ceeRepository = mock(CourseEditionEnrollmentRepository.class);

        US16_EnrollAStudentInACourseEditionController controller = new US16_EnrollAStudentInACourseEditionController(
                ceeRepository, peeRepository, courseEditionRepository);

        //act
        boolean result = controller.enrollStudentInCourseEdition (null, null);

        //assert
        assertFalse(result);
    }
}
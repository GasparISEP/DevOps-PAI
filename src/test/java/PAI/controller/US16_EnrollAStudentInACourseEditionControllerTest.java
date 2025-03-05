package PAI.controller;

import PAI.domain.*;
import PAI.repository.CourseEditionEnrollmentRepository;
import PAI.repository.ProgrammeEditionEnrollmentRepo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US16_EnrollAStudentInACourseEditionControllerTest {

    @Test
    void shouldReturnExceptionIfProgrammeEditionEnrollmentRepoIsNull (){
        //arrange
        CourseEditionEnrollmentRepository doubleCeeRepository = mock (CourseEditionEnrollmentRepository.class);
        CourseEditionRepository doubleCourseEditionRepository = mock (CourseEditionRepository.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new US16_EnrollAStudentInACourseEditionController(doubleCeeRepository, null, doubleCourseEditionRepository);
        });

        //assert
        assertEquals("Programme edition enrollment repository cannot be null!", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfCourseEditionRepositoryIsNull (){
        //arrange
        CourseEditionEnrollmentRepository doubleCeeRepository = mock (CourseEditionEnrollmentRepository.class);
        ProgrammeEditionEnrollmentRepo doublePeeRepository = mock (ProgrammeEditionEnrollmentRepo.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new US16_EnrollAStudentInACourseEditionController(doubleCeeRepository, doublePeeRepository, null);
        });

        //assert
        assertEquals("Course edition repository cannot be null!", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfCourseEditionEnrollmentRepositoryIsNull (){
        //arrange
        CourseEditionRepository doubleCeRepository = mock(CourseEditionRepository.class);
        ProgrammeEditionEnrollmentRepo doublePeeRepository = mock (ProgrammeEditionEnrollmentRepo.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new US16_EnrollAStudentInACourseEditionController(null, doublePeeRepository, doubleCeRepository);
        });

        //assert
        assertEquals("Course edition enrollment repository cannot be null!", exception.getMessage());
    }


    @Test
    void shouldReturnFalseIfStudentIsNotInProgrammeEditionThatHasCourseEdition() throws Exception {

        //arrange
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        ProgrammeEditionEnrollmentRepo doublePeeRepository = mock(ProgrammeEditionEnrollmentRepo.class);
        CourseEditionEnrollmentRepository doubleCeeRepository = mock(CourseEditionEnrollmentRepository.class);

        US16_EnrollAStudentInACourseEditionController controller = new US16_EnrollAStudentInACourseEditionController(
                doubleCeeRepository, doublePeeRepository, doubleCourseEditionRepository);

        Student doubleSt1 = mock (Student.class);
        Student doubleSt2 = mock (Student.class);
        CourseEdition doubleCe1 = mock (CourseEdition.class);
        ProgrammeEdition doublePe1 = mock (ProgrammeEdition.class);

        when (doubleCourseEditionRepository.findWhichProgrammeEditionBelongsToACourseEdition(doubleCe1)).thenReturn(doublePe1);
        when (doublePeeRepository.isStudentEnrolledInThisProgrammeEdition(doubleSt1, doublePe1)).thenReturn(false);

        //act
        boolean result = controller.enrollStudentInCourseEdition(doubleSt2, doubleCe1);

        //assert
        assertFalse(result);
    }


    @Test
    void shouldReturnTrueIfIsAValidCourseEditionEnrollment () throws Exception {

        //arrange
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        ProgrammeEditionEnrollmentRepo doublePeeRepository = mock(ProgrammeEditionEnrollmentRepo.class);
        CourseEditionEnrollmentRepository doubleCeeRepository = mock(CourseEditionEnrollmentRepository.class);

        US16_EnrollAStudentInACourseEditionController controller = new US16_EnrollAStudentInACourseEditionController(
                doubleCeeRepository , doublePeeRepository, doubleCourseEditionRepository);

        Student doubleSt1 = mock (Student.class);
        CourseEdition doubleCe1 = mock (CourseEdition.class);
        ProgrammeEdition doublePe1 = mock (ProgrammeEdition.class);
        LocalDate enrollmentDate = LocalDate.now();

        when (doubleCourseEditionRepository.findWhichProgrammeEditionBelongsToACourseEdition(doubleCe1)).thenReturn(doublePe1);
        when (doublePeeRepository.isStudentEnrolledInThisProgrammeEdition(doubleSt1, doublePe1)).thenReturn(true);
        when (doubleCeeRepository .enrollStudentInACourseEdition(doubleSt1,doubleCe1 , enrollmentDate)).thenReturn (true);

        //act
        boolean result = controller.enrollStudentInCourseEdition(doubleSt1,doubleCe1 );

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseInStudentAndCourseEditionAreNull () throws Exception {
        //arrange
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        ProgrammeEditionEnrollmentRepo doublePeeRepository = mock(ProgrammeEditionEnrollmentRepo.class);
        CourseEditionEnrollmentRepository doubleCeeRepository = mock(CourseEditionEnrollmentRepository.class);

        US16_EnrollAStudentInACourseEditionController controller = new US16_EnrollAStudentInACourseEditionController(
                doubleCeeRepository, doublePeeRepository, doubleCourseEditionRepository);

        //act
        boolean result = controller.enrollStudentInCourseEdition (null, null);

        //assert
        assertFalse(result);
    }
}
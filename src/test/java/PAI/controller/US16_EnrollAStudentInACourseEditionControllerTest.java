package PAI.controller;

import PAI.domain.*;
import PAI.repository.CourseEditionEnrollmentRepository;
import PAI.repository.CourseEditionRepository;
import PAI.repository.ProgrammeEditionEnrollmentRepo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US16_EnrollAStudentInACourseEditionControllerTest {

    //testing constructor of US16 controller
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

    //testing find Programme Editions that Student is Enrolled Method
    @Test
    void shouldReturnAListOfProgrammeEditionsThatStudentIsEnrolled (){
        //arrange
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        ProgrammeEditionEnrollmentRepo doublePeeRepository = mock(ProgrammeEditionEnrollmentRepo.class);
        CourseEditionEnrollmentRepository doubleCeeRepository = mock(CourseEditionEnrollmentRepository.class);

        US16_EnrollAStudentInACourseEditionController controller = new US16_EnrollAStudentInACourseEditionController(
                doubleCeeRepository, doublePeeRepository, doubleCourseEditionRepository);

        Student doubleStudent = mock(Student.class);
        ProgrammeEdition doublePee1 = mock (ProgrammeEdition.class);
        ProgrammeEdition doublePee2 = mock (ProgrammeEdition.class);
        ProgrammeEdition doublePee3 = mock (ProgrammeEdition.class);


        when (doublePeeRepository.findProgrammeEditionsThatStudentIsEnrolled(doubleStudent)).
                thenReturn(List.of(doublePee1,doublePee2,doublePee3));

        //act
        List<ProgrammeEdition> result = controller.findProgrammeEditionsThatStudentIsEnrolled(doubleStudent);

        //assert
        assertEquals(3, result.size());
    }

    //testing find Course Editions by Programme Edition Method
    @Test
    void shouldReturnAListOfCourseEditionsThatBelongsToAProgrammeEdition(){
        //arrange
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        ProgrammeEditionEnrollmentRepo doublePeeRepository = mock(ProgrammeEditionEnrollmentRepo.class);
        CourseEditionEnrollmentRepository doubleCeeRepository = mock(CourseEditionEnrollmentRepository.class);

        US16_EnrollAStudentInACourseEditionController controller = new US16_EnrollAStudentInACourseEditionController(
                doubleCeeRepository, doublePeeRepository, doubleCourseEditionRepository);

        ProgrammeEdition doubleProgrammeEdition = mock (ProgrammeEdition.class);
        CourseEdition doubleCourseEdition1 = mock (CourseEdition.class);
        CourseEdition doubleCourseEdition2 = mock (CourseEdition.class);

        when (doubleCourseEditionRepository.findCourseEditionsByProgrammeEdition(doubleProgrammeEdition)).thenReturn(List.of(doubleCourseEdition1, doubleCourseEdition2));

        //act
        List<CourseEdition> result = controller.findCourseEditionsByProgrammeEdition(doubleProgrammeEdition);

        //assert
        assertEquals (2, result.size());
    }

    //testing enroll a student in a course edition method
    @Test
    void shouldReturnTrueIfIsAValidCourseEditionEnrollment () {

        //arrange
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        ProgrammeEditionEnrollmentRepo doublePeeRepository = mock(ProgrammeEditionEnrollmentRepo.class);
        CourseEditionEnrollmentRepository doubleCeeRepository = mock(CourseEditionEnrollmentRepository.class);

        US16_EnrollAStudentInACourseEditionController controller = new US16_EnrollAStudentInACourseEditionController(
                doubleCeeRepository , doublePeeRepository, doubleCourseEditionRepository);

        Student doubleSt1 = mock (Student.class);
        CourseEdition doubleCe1 = mock (CourseEdition.class);

        when (doubleCeeRepository .enrollStudentInACourseEdition(doubleSt1,doubleCe1)).thenReturn (true);

        //act
        boolean result = controller.enrollStudentInCourseEdition(doubleSt1,doubleCe1 );

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenStudentIsAlreadyEnrolledInCourseEdition () {
        //arrange
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        ProgrammeEditionEnrollmentRepo doublePeeRepository = mock(ProgrammeEditionEnrollmentRepo.class);
        CourseEditionEnrollmentRepository doubleCeeRepository = mock(CourseEditionEnrollmentRepository.class);

        US16_EnrollAStudentInACourseEditionController controller = new US16_EnrollAStudentInACourseEditionController(
                doubleCeeRepository, doublePeeRepository, doubleCourseEditionRepository);

        Student doubleStudent = mock (Student.class);
        CourseEdition doubleCe = mock (CourseEdition.class);

        when (doubleCeeRepository.enrollStudentInACourseEdition(doubleStudent,doubleCe)).thenReturn(false);

        //act
        boolean result = controller.enrollStudentInCourseEdition (doubleStudent, doubleCe);

        //assert
        assertFalse(result);
    }
}
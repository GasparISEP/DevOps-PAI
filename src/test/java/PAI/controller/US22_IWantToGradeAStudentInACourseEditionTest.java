
package PAI.controller;

import PAI.domain.*;
import PAI.factory.GradeStudentFactory;
import PAI.repository.GradeStudentRepository;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US22_IWantToGradeAStudentInACourseEditionTest {
    @Test
    void gradeStudentInRepository () {
        //arrange
        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentListFactory gradeStudentListFactory = mock(GradeStudentListFactory.class);

        List<GradeStudent> mockGradeList = spy(new ArrayList<>());

        when(gradeStudentListFactory.newArrayList()).thenReturn((ArrayList<GradeStudent>) mockGradeList);

        GradeStudentRepository list = new GradeStudentRepository(gradeStudentFactory, gradeStudentListFactory);
        CourseEditionEnrollmentFactory courseEditionEnrollmentFactory = mock(CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository1 = new CourseEditionEnrollmentRepository(courseEditionEnrollmentFactory);
        //act
        US22_IWantToGradeAStudentInACourseEdition G1 = new US22_IWantToGradeAStudentInACourseEdition(list,courseEditionEnrollmentRepository1);
        //assert
        assertNotNull(G1);
    }

    @Test
    void iWantToGradeAStudentInACourseEdition () throws Exception {
        //arrange
        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentListFactory gradeStudentListFactory = mock(GradeStudentListFactory.class);

        List<GradeStudent> mockGradeList = spy(new ArrayList<>());

        when(gradeStudentListFactory.newArrayList()).thenReturn((ArrayList<GradeStudent>) mockGradeList);

        GradeStudentRepository list = new GradeStudentRepository(gradeStudentFactory, gradeStudentListFactory);
        CourseEditionEnrollmentFactory courseEditionEnrollmentFactory = mock(CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository enrollmentRepository = new CourseEditionEnrollmentRepository(courseEditionEnrollmentFactory);


        //act
        US22_IWantToGradeAStudentInACourseEdition controller1 = new US22_IWantToGradeAStudentInACourseEdition(list,enrollmentRepository);

        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);
        LocalDate currentDate = LocalDate.now();

        GradeStudent gradeStudent1 = mock(GradeStudent.class);


        when(gradeStudentFactory.newGradeStudent(20, "10-10-2025", student1, courseEdition1)).thenReturn(gradeStudent1);

        when(gradeStudent1.knowGrade()).thenReturn(20.0);

        when(gradeStudent1.hasThisCourseEdition(courseEdition1)).thenReturn(true);

        CourseEditionEnrollment enrollment1 = mock(CourseEditionEnrollment.class);

        when(enrollment1.knowStudent()).thenReturn(student1);
        when(enrollment1.knowCourseEdition()).thenReturn(courseEdition1);

        when(courseEditionEnrollmentFactory.createCourseEditionEnrollment(student1, courseEdition1, currentDate)).thenReturn(enrollment1);


        enrollmentRepository.enrollStudentInACourseEdition(student1, courseEdition1,currentDate);

        Optional<GradeStudent> optc1 = controller1.iWantToGradeAStudent(20,"10-10-2025",student1,courseEdition1);

        //assert

        assertTrue(optc1.isPresent());
    }

    @Test
    void iWantToCheckIfStudentIsEnrolledInCourseEdition () throws Exception {
        //arrange
        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentListFactory gradeStudentListFactory = mock(GradeStudentListFactory.class);

        List<GradeStudent> mockGradeList = spy(new ArrayList<>());

        when(gradeStudentListFactory.newArrayList()).thenReturn((ArrayList<GradeStudent>) mockGradeList);

        GradeStudentRepository list = new GradeStudentRepository(gradeStudentFactory, gradeStudentListFactory);
        CourseEditionEnrollmentFactory courseEditionEnrollmentFactory = mock(CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository enrollmentRepository = new CourseEditionEnrollmentRepository(courseEditionEnrollmentFactory);

        //act
        US22_IWantToGradeAStudentInACourseEdition controller1 = new US22_IWantToGradeAStudentInACourseEdition(list,enrollmentRepository);

        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);
        LocalDate currentDate = LocalDate.now();
        GradeStudent gradeStudent1 = mock(GradeStudent.class);


        when(gradeStudentFactory.newGradeStudent(8, "10-10-2025", student1, courseEdition1)).thenReturn(gradeStudent1);

        when(gradeStudent1.knowGrade()).thenReturn(8.0);

        when(gradeStudent1.hasThisCourseEdition(courseEdition1)).thenReturn(true);

        CourseEditionEnrollment enrollment1 = mock(CourseEditionEnrollment.class);

        when(enrollment1.knowStudent()).thenReturn(student1);
        when(enrollment1.knowCourseEdition()).thenReturn(courseEdition1);

        when(courseEditionEnrollmentFactory.createCourseEditionEnrollment(student1, courseEdition1, currentDate)).thenReturn(enrollment1);

        enrollmentRepository.enrollStudentInACourseEdition(student1, courseEdition1,currentDate);

        Boolean result = controller1.isStudentEnrolledInCourseEdition(student1,courseEdition1);

        //assert

        assertTrue(result);
    }

    @Test
    void iWantToCheckIfStudentIsNotEnrolledInCourseEdition () throws Exception {
        //arrange
        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentListFactory gradeStudentListFactory = mock(GradeStudentListFactory.class);

        List<GradeStudent> mockGradeList = spy(new ArrayList<>());

        when(gradeStudentListFactory.newArrayList()).thenReturn((ArrayList<GradeStudent>) mockGradeList);

        GradeStudentRepository list = new GradeStudentRepository(gradeStudentFactory, gradeStudentListFactory);
        CourseEditionEnrollmentFactory courseEditionEnrollmentFactory = mock(CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository enrollmentRepository = new CourseEditionEnrollmentRepository(courseEditionEnrollmentFactory);

        //act
        US22_IWantToGradeAStudentInACourseEdition controller1 = new US22_IWantToGradeAStudentInACourseEdition(list,enrollmentRepository);

        Student student1 = mock(Student.class);
        Student student2 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);
        LocalDate currentDate = LocalDate.now();

        CourseEditionEnrollment enrollment1 = mock(CourseEditionEnrollment.class);

        when(enrollment1.knowStudent()).thenReturn(student1);
        when(enrollment1.knowCourseEdition()).thenReturn(courseEdition1);

        when(courseEditionEnrollmentFactory.createCourseEditionEnrollment(student1, courseEdition1, currentDate)).thenReturn(enrollment1);

        enrollmentRepository.enrollStudentInACourseEdition(student1, courseEdition1,currentDate);

        GradeStudent gradeStudent1 = mock(GradeStudent.class);
        GradeStudent gradeStudent2 = mock(GradeStudent.class);


        when(gradeStudentFactory.newGradeStudent(8, "10-10-2025", student1, courseEdition1)).thenReturn(gradeStudent1);
        when(gradeStudentFactory.newGradeStudent(20, "10-10-2025", student2, courseEdition1)).thenReturn(gradeStudent2);

        when(gradeStudent1.knowGrade()).thenReturn(8.0);
        when(gradeStudent2.knowGrade()).thenReturn(20.0);

        when(gradeStudent1.hasThisCourseEdition(courseEdition1)).thenReturn(true);
        when(gradeStudent2.hasThisCourseEdition(courseEdition1)).thenReturn(true);

        Boolean result = controller1.isStudentEnrolledInCourseEdition(student2,courseEdition1);

        //assert

        assertFalse(result);
    }


    @Test
    void iWantoToGradeAStudentInCourseEditionNotEnrolledInCourseEdition () throws Exception {
        //arrange

        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentListFactory gradeStudentListFactory = mock(GradeStudentListFactory.class);

        List<GradeStudent> mockGradeList = spy(new ArrayList<>());

        when(gradeStudentListFactory.newArrayList()).thenReturn((ArrayList<GradeStudent>) mockGradeList);

        GradeStudentRepository list = new GradeStudentRepository(gradeStudentFactory, gradeStudentListFactory);
        CourseEditionEnrollmentFactory courseEditionEnrollmentFactory = mock(CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository enrollmentRepository = new CourseEditionEnrollmentRepository(courseEditionEnrollmentFactory);

        //act
        US22_IWantToGradeAStudentInACourseEdition controller1 = new US22_IWantToGradeAStudentInACourseEdition(list,enrollmentRepository);

        Student student1 = mock(Student.class);
        Student student2 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        LocalDate currentDate = LocalDate.now();

        CourseEditionEnrollment enrollment1 = mock(CourseEditionEnrollment.class);

        when(enrollment1.knowStudent()).thenReturn(student1);
        when(enrollment1.knowCourseEdition()).thenReturn(courseEdition1);

        when(courseEditionEnrollmentFactory.createCourseEditionEnrollment(student1, courseEdition1, currentDate)).thenReturn(enrollment1);

        enrollmentRepository.enrollStudentInACourseEdition(student1, courseEdition1,currentDate);

        GradeStudent gradeStudent1 = mock(GradeStudent.class);


        when(gradeStudentFactory.newGradeStudent(20, "10-10-2025", student1, courseEdition1)).thenReturn(gradeStudent1);


        when(gradeStudent1.knowGrade()).thenReturn(20.0);


        when(gradeStudent1.hasThisCourseEdition(courseEdition1)).thenReturn(true);


        Optional <GradeStudent> opt1 = controller1.iWantToGradeAStudent(20,"20/11/2024",student2,courseEdition1);

        //assert

        assertTrue(opt1.isEmpty());
    }



    @Test
    void iWantToGradeAStudentInACourseEditionWithNullGradeStudentRepo () throws Exception {
        //arrange

        GradeStudentRepository gradeStudentRepository1 = null;
        CourseEditionEnrollmentFactory courseEditionEnrollmentFactory = mock(CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository1 = new CourseEditionEnrollmentRepository(courseEditionEnrollmentFactory);


        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new US22_IWantToGradeAStudentInACourseEdition(gradeStudentRepository1, courseEditionEnrollmentRepository1);
        });
        assertEquals("Cannot be null", exception.getMessage());

    }

    @Test
    void iWantToGradeAStudentInACourseEditionWithNullCourseEditionEnrollRepo () throws Exception {
        //arrange
        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentListFactory gradeStudentListFactory = mock(GradeStudentListFactory.class);

        List<GradeStudent> mockGradeList = spy(new ArrayList<>());

        when(gradeStudentListFactory.newArrayList()).thenReturn((ArrayList<GradeStudent>) mockGradeList);

        GradeStudentRepository list = new GradeStudentRepository(gradeStudentFactory, gradeStudentListFactory);
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository1 = null;


        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new US22_IWantToGradeAStudentInACourseEdition(list, courseEditionEnrollmentRepository1);
        });
        assertEquals("Cannot be null", exception.getMessage());

    }
}


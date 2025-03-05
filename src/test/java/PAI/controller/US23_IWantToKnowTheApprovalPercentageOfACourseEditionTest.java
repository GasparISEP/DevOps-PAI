
package PAI.controller;

import PAI.domain.*;
import PAI.factory.GradeStudentFactory;
import PAI.repository.GradeStudentRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US23_IWantToKnowTheApprovalPercentageOfACourseEditionTest {

    @Test
    void gradeStudentInRepository() {
        //arrange
        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentRepository gradeStudentRepository = new GradeStudentRepository(gradeStudentFactory);

        //act
        US23_IWantToKnowTheApprovalPercentageOfACourseEdition approval1 = new US23_IWantToKnowTheApprovalPercentageOfACourseEdition(gradeStudentRepository);

        //assert
        assertNotNull(approval1);
    }


    @Test
    void approvalRateInACourseEdition () throws Exception {
        //arrange

        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentRepository gradeStudentRepository = new GradeStudentRepository(gradeStudentFactory);
        CourseEditionEnrollmentFactory factoryDouble = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository enrollmentRepository= new CourseEditionEnrollmentRepository (factoryDouble);


        //act
        US23_IWantToKnowTheApprovalPercentageOfACourseEdition controlador1 = new US23_IWantToKnowTheApprovalPercentageOfACourseEdition(gradeStudentRepository);

        Student student1 = mock(Student.class);
        Student student2 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);


        GradeStudent gradeStudent1 = mock(GradeStudent.class);
        GradeStudent gradeStudent2 = mock(GradeStudent.class);
        LocalDate localDate = LocalDate.now();


        when(gradeStudentFactory.newGradeStudent(8, "10-10-2025", student1, courseEdition1)).thenReturn(gradeStudent1);
        when(gradeStudentFactory.newGradeStudent(20, "10-10-2025", student2, courseEdition1)).thenReturn(gradeStudent2);

        when(gradeStudent1.knowGrade()).thenReturn(8.0);
        when(gradeStudent2.knowGrade()).thenReturn(20.0);

        when(gradeStudent1.hasThisCourseEdition(courseEdition1)).thenReturn(true);
        when(gradeStudent2.hasThisCourseEdition(courseEdition1)).thenReturn(true);


        enrollmentRepository.enrollStudentInACourseEdition(student1, courseEdition1,localDate);
        enrollmentRepository.enrollStudentInACourseEdition(student2, courseEdition1,localDate);

        gradeStudentRepository.addGradeToStudent(8, "10-10-2025", student1, courseEdition1);
        gradeStudentRepository.addGradeToStudent(20, "10-10-2025", student2, courseEdition1);


        double optC1 = controlador1.IWantToKnowTheApprovalPercentageOfACourseEdition(courseEdition1);

        //assert
        assertEquals(50.0,optC1,0.01);
    }

}




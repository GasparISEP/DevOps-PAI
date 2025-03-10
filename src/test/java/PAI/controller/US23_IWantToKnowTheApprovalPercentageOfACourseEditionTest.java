
package PAI.controller;

import PAI.domain.*;
import PAI.factory.CourseEditionEnrollmentFactory;
import PAI.factory.CourseEditionEnrollmentListFactory;
import PAI.factory.GradeStudentFactory;
import PAI.factory.GradeStudentListFactory;
import PAI.repository.GradeStudentRepository;
import PAI.repository.CourseEditionEnrollmentRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US23_IWantToKnowTheApprovalPercentageOfACourseEditionTest {

    @Test
    void gradeStudentInRepository() {
        //arrange
        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentListFactory gradeStudentListFactory = mock(GradeStudentListFactory.class);

        List<GradeStudent> mockGradeList = spy(new ArrayList<>());

        when(gradeStudentListFactory.newArrayList()).thenReturn((ArrayList<GradeStudent>) mockGradeList);

        GradeStudentRepository list = new GradeStudentRepository(gradeStudentFactory, gradeStudentListFactory);

        //act
        US23_IWantToKnowTheApprovalPercentageOfACourseEdition approval1 = new US23_IWantToKnowTheApprovalPercentageOfACourseEdition(list);

        //assert
        assertNotNull(approval1);
    }


    @Test
    void approvalRateInACourseEdition () throws Exception {
        //arrange

        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentListFactory gradeStudentListFactory = mock(GradeStudentListFactory.class);

        List<GradeStudent> mockGradeList = spy(new ArrayList<>());

        when(gradeStudentListFactory.newArrayList()).thenReturn((ArrayList<GradeStudent>) mockGradeList);

        GradeStudentRepository list = new GradeStudentRepository(gradeStudentFactory, gradeStudentListFactory);
        CourseEditionEnrollmentFactory factoryDouble = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentListFactory listFactoryDouble = mock(CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository enrollmentRepository= new CourseEditionEnrollmentRepository (factoryDouble, listFactoryDouble);


        //act
        US23_IWantToKnowTheApprovalPercentageOfACourseEdition controlador1 = new US23_IWantToKnowTheApprovalPercentageOfACourseEdition(list);

        Student student1 = mock(Student.class);
        Student student2 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);


        GradeStudent gradeStudent1 = mock(GradeStudent.class);
        GradeStudent gradeStudent2 = mock(GradeStudent.class);


        when(gradeStudentFactory.newGradeStudent(8, "10-10-2025", student1, courseEdition1)).thenReturn(gradeStudent1);
        when(gradeStudentFactory.newGradeStudent(20, "10-10-2025", student2, courseEdition1)).thenReturn(gradeStudent2);

        when(gradeStudent1.knowGrade()).thenReturn(8.0);
        when(gradeStudent2.knowGrade()).thenReturn(20.0);

        when(gradeStudent1.hasThisCourseEdition(courseEdition1)).thenReturn(true);
        when(gradeStudent2.hasThisCourseEdition(courseEdition1)).thenReturn(true);


        enrollmentRepository.enrollStudentInACourseEdition(student1, courseEdition1);
        enrollmentRepository.enrollStudentInACourseEdition(student2, courseEdition1);

        list.addGradeToStudent(8, "10-10-2025", student1, courseEdition1);
        list.addGradeToStudent(20, "10-10-2025", student2, courseEdition1);


        double optC1 = controlador1.IWantToKnowTheApprovalPercentageOfACourseEdition(courseEdition1);

        //assert
        assertEquals(50.0,optC1,0.01);
    }

}






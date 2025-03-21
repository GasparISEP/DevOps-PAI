
package PAI.controller;

import PAI.domain.*;
import PAI.factory.*;
import PAI.repository.GradeStudentRepository;
import PAI.repository.CourseEditionEnrolmentRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US23_IWantToKnowTheApprovalPercentageOfACourseEditionTest {

    @Test
    void gradeStudentInRepository() {
        //arrange
        GradeStudentListFactory gradeStudentListFactory = mock(GradeStudentListFactory.class);

        List<StudentGrade> mockGradeList = spy(new ArrayList<>());
        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);
        StudentGrade studentGrade1 = mock(StudentGrade.class);

        when(gradeStudentListFactory.newArrayList()).thenReturn( mockGradeList);

        GradeStudentRepository list = mock(GradeStudentRepository.class);
        when(list.addGradeToStudent(10, "13-03-2025", student1, courseEdition1)).thenReturn(Optional.of(studentGrade1));

        //act
        US23_IWantToKnowTheApprovalPercentageOfACourseEdition approval1 = mock(US23_IWantToKnowTheApprovalPercentageOfACourseEdition.class);
        when(approval1.IWantToKnowTheApprovalPercentageOfACourseEdition(courseEdition1)).thenReturn(100.0);

        //assert
        assertNotNull(approval1);
    }


    @Test
    void approvalRateInACourseEdition () throws Exception {

        //arrange
        StudentGradeFactory studentGradeFactory = mock(StudentGradeFactory.class);
        GradeStudentListFactory gradeStudentListFactory = mock(GradeStudentListFactory.class);

        List<StudentGrade> mockGradeList = spy(new ArrayList<>());
        when(gradeStudentListFactory.newArrayList()).thenReturn(mockGradeList);

        GradeStudentRepository list = mock(GradeStudentRepository.class);

        CourseEditionEnrolmentRepository enrollmentRepository= mock(CourseEditionEnrolmentRepository.class);

        US23_IWantToKnowTheApprovalPercentageOfACourseEdition controlador1 = new US23_IWantToKnowTheApprovalPercentageOfACourseEdition(list);

        Student student1 = mock(Student.class);
        Student student2 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);
        StudentGrade studentGrade1 = mock(StudentGrade.class);
        StudentGrade studentGrade2 = mock(StudentGrade.class);


        when(studentGradeFactory.newGradeStudent(8, "10-10-2025", student1, courseEdition1)).thenReturn(studentGrade1);
        when(studentGradeFactory.newGradeStudent(20, "10-10-2025", student2, courseEdition1)).thenReturn(studentGrade2);

        when(studentGrade1.knowGrade()).thenReturn(8.0);
        when(studentGrade2.knowGrade()).thenReturn(20.0);

        when(studentGrade1.hasThisCourseEdition(courseEdition1)).thenReturn(true);
        when(studentGrade2.hasThisCourseEdition(courseEdition1)).thenReturn(true);


        enrollmentRepository.enrolStudentInACourseEdition(student1, courseEdition1);
        enrollmentRepository.enrolStudentInACourseEdition(student2, courseEdition1);

        list.addGradeToStudent(8, "10-10-2025", student1, courseEdition1);
        list.addGradeToStudent(20, "10-10-2025", student2, courseEdition1);
        when(list.knowApprovalRate(courseEdition1)).thenReturn(50.0);


        //act
        double optC1 = controlador1.IWantToKnowTheApprovalPercentageOfACourseEdition(courseEdition1);

        //assert
        assertEquals(50.0,optC1,0.01);
    }

}






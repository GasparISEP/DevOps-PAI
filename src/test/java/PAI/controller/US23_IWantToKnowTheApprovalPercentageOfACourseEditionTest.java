
package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.VOs.Date;
import PAI.VOs.Grade;
import PAI.VOs.StudentID;
import PAI.domain.*;
import PAI.factory.*;
import PAI.repository.StudentGradeRepository;
import PAI.repository.CourseEditionEnrolmentRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US23_IWantToKnowTheApprovalPercentageOfACourseEditionTest {

    @Test
    void gradeStudentInRepository() throws Exception {
        //arrange
        IStudentGradeListFactory IStudentGradeListFactory = mock(IStudentGradeListFactory.class);

        List<StudentGrade> mockGradeList = spy(new ArrayList<>());
        StudentID student1 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        StudentGrade studentGrade1 = mock(StudentGrade.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);

        when(IStudentGradeListFactory.newArrayList()).thenReturn( mockGradeList);

        StudentGradeRepository list = mock(StudentGradeRepository.class);
        when(list.addGradeToStudent(grade, dateDouble, student1, courseEditionID1)).thenReturn(true);

        //act
        US23_IWantToKnowTheApprovalPercentageOfACourseEdition approval1 = mock(US23_IWantToKnowTheApprovalPercentageOfACourseEdition.class);
        when(approval1.IWantToKnowTheApprovalPercentageOfACourseEdition(courseEditionID1)).thenReturn(100.0);

        //assert
        assertNotNull(approval1);
    }


    @Test
    void approvalRateInACourseEdition () throws Exception {

        //arrange
        IStudentGradeFactory IStudentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeListFactory IStudentGradeListFactory = mock(IStudentGradeListFactory.class);

        List<StudentGrade> mockGradeList = spy(new ArrayList<>());
        when(IStudentGradeListFactory.newArrayList()).thenReturn(mockGradeList);

        StudentGradeRepository list = mock(StudentGradeRepository.class);

        CourseEditionEnrolmentRepository enrollmentRepository= mock(CourseEditionEnrolmentRepository.class);

        US23_IWantToKnowTheApprovalPercentageOfACourseEdition controlador1 = new US23_IWantToKnowTheApprovalPercentageOfACourseEdition(list);

        StudentID student1 = mock(StudentID.class);
        StudentID student2 = mock(StudentID.class);
        Student student3 = mock(Student.class);
        Student student4 = mock(Student.class);
        Grade grade1 = mock(Grade.class);
        Grade grade2 = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        CourseEdition courseEdition = mock(CourseEdition.class);
        StudentGrade studentGrade1 = mock(StudentGrade.class);
        StudentGrade studentGrade2 = mock(StudentGrade.class);

        when(grade1.knowGrade()).thenReturn(8.0);
        when(grade2.knowGrade()).thenReturn(20.0);

        when(studentGrade1.get_grade()).thenReturn(grade1);
        when(studentGrade2.get_grade()).thenReturn(grade2);


        when(IStudentGradeFactory.newGradeStudent(grade1, dateDouble, student1, courseEditionID1)).thenReturn(studentGrade1);
        when(IStudentGradeFactory.newGradeStudent(grade2, dateDouble, student2, courseEditionID1)).thenReturn(studentGrade2);

        when(studentGrade1.hasThisCourseEditionID(courseEditionID1)).thenReturn(true);
        when(studentGrade2.hasThisCourseEditionID(courseEditionID1)).thenReturn(true);


        enrollmentRepository.enrolStudentInACourseEdition(student3, courseEdition);
        enrollmentRepository.enrolStudentInACourseEdition(student4, courseEdition);

        list.addGradeToStudent(grade1, dateDouble, student1, courseEditionID1);
        list.addGradeToStudent(grade2, dateDouble, student2, courseEditionID1);
        when(list.knowApprovalRate(courseEditionID1)).thenReturn(50.0);


        //act
        double optC1 = controlador1.IWantToKnowTheApprovalPercentageOfACourseEdition(courseEditionID1);

        //assert
        assertEquals(50.0,optC1,0.01);
    }

}
//
//
//
//
//
//
//

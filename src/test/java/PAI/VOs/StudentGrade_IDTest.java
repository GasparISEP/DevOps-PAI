package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentGrade_IDTest {

    @Test
    void shouldRandomNumber (){
        //arrange
        StudentGrade_ID studentGradeId = new StudentGrade_ID();
        //assert
        assertNotNull(studentGradeId);
    }

    // equals

    @Test
    void shouldReturnTrueIfSameLoc(){
        //arrange
        StudentGrade_ID studentGradeId1 = new StudentGrade_ID();
        StudentGrade_ID studentGradeId2 = studentGradeId1;
        //act
        boolean result = studentGradeId1.equals(studentGradeId2);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfFromDifferentInstances(){
        //arrange
        StudentGrade_ID studentGradeId1 = new StudentGrade_ID();
        Object o = new Object();
        //act
        boolean result = studentGradeId1.equals(o);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfDifferentContent(){
        //arrange
        StudentGrade_ID studentGradeId1 = new StudentGrade_ID();
        StudentGrade_ID studentGradeId2 = new StudentGrade_ID();
        //act
        boolean result = studentGradeId1.equals(studentGradeId2);
        //assert
        assertFalse(result);
    }









}
package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentGradeIDTest {

    @Test
    void shouldRandomNumber (){
        //arrange
        StudentGradeID studentGradeId = new StudentGradeID();
        //assert
        assertNotNull(studentGradeId);
    }

    // equals

    @Test
    void shouldReturnTrueIfSameLoc(){
        //arrange
        StudentGradeID studentGradeId1 = new StudentGradeID();
        StudentGradeID studentGradeId2 = studentGradeId1;
        //act
        boolean result = studentGradeId1.equals(studentGradeId2);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfFromDifferentInstances(){
        //arrange
        StudentGradeID studentGradeId1 = new StudentGradeID();
        Object o = new Object();
        //act
        boolean result = studentGradeId1.equals(o);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfDifferentContent(){
        //arrange
        StudentGradeID studentGradeId1 = new StudentGradeID();
        StudentGradeID studentGradeId2 = new StudentGradeID();
        //act
        boolean result = studentGradeId1.equals(studentGradeId2);
        //assert
        assertFalse(result);
    }









}
package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SemesterTest {

    @Test
    void testValidSemesterNumber1() {
        //arrange + act
        Semester semester = new Semester(1);
        //assert
        assertEquals(1, semester.toInt());
    }

    @Test
    void testValidSemesterNumber2() {
        //arrange + act
        Semester semester = new Semester(2);
        //assert
        assertEquals(2, semester.toInt());
    }

    @Test
    void testInvalidSemesterEqualZero() {
        //arrange + act + assert
        assertThrows(IllegalArgumentException.class, () -> new Semester(0));
    }

    @Test
    void testInvalidSemesterGreaterThanTwo() {
        //arrange + act + assert
        assertThrows(IllegalArgumentException.class, () -> new Semester(3));
    }

    @Test
    void testEquals() {
        //arrange + act
        Semester s1 = new Semester(1);
        Semester s2 = new Semester(1);
        Semester s3 = new Semester(2);
        //assert - Verifica que dois semestres com o mesmo valor são iguais
        assertEquals(s1, s2);
        //assert - Verifica que semestres com valores diferentes não são iguais
        assertNotEquals(s1, s3);
    }

    @Test
    void testHashCode() {
        //arrange + act
        Semester s1 = new Semester(1);
        Semester s2 = new Semester(1);
        //assert - Dois objetos iguais devem ter o mesmo hashCode
        assertEquals(s1.hashCode(), s2.hashCode());
    }


}
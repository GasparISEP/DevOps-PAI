package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SemesterTest {

    // Test to valid object
    @Test
    void createASemester () throws Exception {
        // arrange + act + assert
        Semester one = new Semester(2);

    }

    // Test to invalid quantity of semesters
    @Test
    void zeroQuantityOfSemestersDontCreateASemester () throws Exception {
        //arrange + act + assert
        assertThrows(Exception.class, () -> new Semester(0));

    }

    // Test to invalid quantity of semesters
    @Test
    void lessThanZeroQuantityOfSemestersDontCreateASemester () throws Exception {
        //arrange + act + assert
        assertThrows(Exception.class, () -> new Semester(-1));

    }
}
package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TeacherCareerProgressionIDTest {

    @Test
    void shouldCreateTeacherCareerProgressionID() {
        //arrange

        //act
        TeacherCareerProgressionID tcpID = new TeacherCareerProgressionID();

        //assert
        assertNotNull(tcpID);
    }

    @Test
    void shouldReturnFalseIfObjectToCompareIsNotATeacherCareerProgressionID() {
        //arrange
        TeacherCareerProgressionID tcpID1 = new TeacherCareerProgressionID();
        Object o = mock(Object.class);

        //act
        boolean result = tcpID1.equals(o);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfObjectsAreTheSame() {
        //arrange
        TeacherCareerProgressionID tcpID = new TeacherCareerProgressionID();

        //act
        boolean result = tcpID.equals(tcpID);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTeacherCareerProgressionIDAreNotTheSame() {
        //arrange
        TeacherCareerProgressionID tcp1 = new TeacherCareerProgressionID();
        TeacherCareerProgressionID tcp2 = new TeacherCareerProgressionID();

        //act
        boolean result = tcp1.equals(tcp2);

        //assert
        assertFalse(result);
    }

}
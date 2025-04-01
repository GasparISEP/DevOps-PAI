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
    void shouldReturnTrueIfTeacherCareerProgressionObjectsHaveTheSameID() {
        //arrange
        TeacherCareerProgressionID teacherCareerProgressionID1 = new TeacherCareerProgressionID();
        String id = teacherCareerProgressionID1.getIDValue();
        TeacherCareerProgressionID teacherCareerProgressionID2;

        try {
            var constructor = TeacherCareerProgressionID.class.getDeclaredConstructor();
            teacherCareerProgressionID2 = constructor.newInstance();

            var field = TeacherCareerProgressionID.class.getDeclaredField("_ID");
            field.setAccessible(true);
            field.set(teacherCareerProgressionID2, id);
        } catch (Exception e) {
            teacherCareerProgressionID2 = null;
        }

        //act
        boolean result = teacherCareerProgressionID1.equals(teacherCareerProgressionID2);

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

    @Test
    void shouldReturnTrueIfTeacherCareerProgressionIDIsTheSameObject() {
        //arrange
        TeacherCareerProgressionID tcp1 = new TeacherCareerProgressionID();

        //act
        boolean result = tcp1.equals(tcp1);

        //assert
        assertTrue(result);
    }

    @Test
    void getIDValueShouldReturnIDValue() {
        //arrange
        TeacherCareerProgressionID tcpID = new TeacherCareerProgressionID();

        //act
        String id = tcpID.getIDValue();

        //assert
        assertNotNull(id);

    }

}
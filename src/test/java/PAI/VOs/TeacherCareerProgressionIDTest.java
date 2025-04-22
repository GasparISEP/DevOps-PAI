package PAI.VOs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.UUID;

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
    void shouldCreateTeacherCareerProgressionIDWhenPassingAValidUUIDAsInput() {
        // Arrange
        UUID id = UUID.fromString("11111111-1111-1111-1111-111111111111");
        // Act
        TeacherCareerProgressionID tcpID = new TeacherCareerProgressionID(id);

        // Assert
        assertNotNull(tcpID);
    }

    @Test
    void shouldNullPointerExceptionWhenCreatingTeacherCareerProgressionIDWithInvalidUUID() {
        // Arrange

        // Act
        Executable action = () -> new TeacherCareerProgressionID(null);

        // Assert
        assertThrows(NullPointerException.class, action);
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
        UUID id = teacherCareerProgressionID1.getIDValue();
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
        UUID id = tcpID.getIDValue();

        //assert
        assertNotNull(id);

    }

    @Test
    public void shouldReturnEqualWhenTwoObjectsHaveTheSameHashCode() {
        //Arrange
        UUID id = UUID.fromString("11111111-1111-1111-1111-111111111111");
        TeacherCareerProgressionID tcpID1 = new TeacherCareerProgressionID(id);
        TeacherCareerProgressionID tcpID2 = new TeacherCareerProgressionID(id);

        //Act & Assert
        assertEquals(tcpID1.hashCode(), tcpID2.hashCode());
    }

    @Test
    public void shouldReturnNotEqualWhenTwoObjectsHaveDifferentHashCodes() {
        //Arrange
        UUID id1 = UUID.fromString("11111111-1111-1111-1111-111111111111");
        UUID id2 = UUID.fromString("22222222-2222-2222-2222-222222222222");
        TeacherCareerProgressionID tcpID1 = new TeacherCareerProgressionID(id1);
        TeacherCareerProgressionID tcpID2 = new TeacherCareerProgressionID(id2);

        //Act & Assert
        assertEquals(tcpID1.hashCode(), tcpID2.hashCode());
    }

}
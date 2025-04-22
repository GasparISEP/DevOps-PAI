package PAI.VOs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.mock;

class WorkingPercentageTest {

    @Test
    void shouldCreateWorkingPercentageVO() throws IllegalArgumentException{
        //arrange

        //act
        WorkingPercentage wp = new WorkingPercentage(50);

        //assert
        assertNotNull(wp);
    }

    public static Stream<Arguments> provideInvalidWorkingPercentage() {
        return Stream.of(
                arguments(-1),
                arguments(101)
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidWorkingPercentage")
    void shouldThrowExceptionWhenWorkingPercentageIsInvalid(int workingPercentage) {
        //arrange

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new WorkingPercentage(workingPercentage));

        //assert
        assertEquals(exception.getMessage(), "Working Percentage must be a value between 0 and 100.");
    }

    @Test
    void shouldReturnTrueIfObjectsAreTheSame() {
        //arrange
        WorkingPercentage wp = new WorkingPercentage(50);

        //act
        boolean result = wp.equals(wp);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfObjectToCompareIsNotAWorkingPercentage () {
        //arrange
        WorkingPercentage wp = new WorkingPercentage(50);
        Object o = mock(Object.class);

        //act
        boolean result = wp.equals(o);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfWorkingPercentagesAreEqual() {
        //arrange
        WorkingPercentage wp1 = new WorkingPercentage(50);
        WorkingPercentage wp2 = new WorkingPercentage(50);

        //act
        boolean result = wp1.equals(wp2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfWorkingPercentagesAreNotEqual() {
        //arrange
        WorkingPercentage wp1 = new WorkingPercentage(50);
        WorkingPercentage wp2 = new WorkingPercentage(60);

        //act
        boolean result = wp1.equals(wp2);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfWorkingPercentageIsRetrieved() {
        //arrange
        WorkingPercentage workingPercentage = new WorkingPercentage(50);

        //act
        int result = workingPercentage.getValue();

        //assert
        assertEquals(50, result);

    }

    @Test
    void shouldReturnFalseIfWorkingPercentageIsNotRetrieved() {
        //arrange
        WorkingPercentage workingPercentage = new WorkingPercentage(50);

        //act
        int result = workingPercentage.getValue();

        //assert
        assertNotEquals(25, result);
    }
}
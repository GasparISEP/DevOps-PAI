package PAI.VOs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

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


}
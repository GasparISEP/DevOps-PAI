package PAI.service;

import PAI.factory.ITeacherCareerProgressionFactory;
import PAI.repository.ITeacherCareerProgressionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TeacherCareerProgressionServiceTest {

    private ITeacherCareerProgressionRepository _repositoryDouble;
    private ITeacherCareerProgressionFactory _factoryDouble;

    //Arrange
    @BeforeEach
    void setup(){
        _repositoryDouble = mock(ITeacherCareerProgressionRepository.class);
        _factoryDouble = mock(ITeacherCareerProgressionFactory.class);
    }

    @Test
    void shouldCreateTeacherCareerProgressionServiceWhenPassingValidInputs(){
        //Arrange

        //Act & Assert
        new TeacherCareerProgressionService(_repositoryDouble, _factoryDouble);
    }

    static Stream<Arguments> testWithNullInputs(){
        return Stream.of(
                Arguments.of(null, mock(ITeacherCareerProgressionFactory.class), "Teacher Career Progression Repository cannot be null!"),
                Arguments.of(mock(ITeacherCareerProgressionRepository.class), null, "Teacher Career Progression Factory cannot be null!")
        );
    }
    @ParameterizedTest
    @MethodSource("testWithNullInputs")
    void shouldCreateTeacherCareerProgressionServiceWhenPassingValidInputs(ITeacherCareerProgressionRepository repoDouble,
                                                   ITeacherCareerProgressionFactory factoryDouble, String expectedMessage){
        //Arrange

        //Act
        Executable action = () -> new TeacherCareerProgressionService(repoDouble, factoryDouble);

        //Assert
        Throwable result = assertThrows(NullPointerException.class, action);
        assertEquals(expectedMessage, result.getMessage());
    }
}
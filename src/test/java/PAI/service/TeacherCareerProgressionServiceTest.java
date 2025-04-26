package PAI.service;

import PAI.VOs.Date;
import PAI.VOs.TeacherCategoryID;
import PAI.VOs.TeacherID;
import PAI.VOs.WorkingPercentage;
import PAI.domain.TeacherCareerProgression;
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
import static org.mockito.Mockito.when;

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


    static Stream<Arguments> provideNullArgumentsForCreate() {
        return Stream.of(
                Arguments.of(null, mock(TeacherCategoryID.class), mock(WorkingPercentage.class), mock(TeacherID.class)),
                Arguments.of(mock(Date.class), null, mock(WorkingPercentage.class), mock(TeacherID.class)),
                Arguments.of(mock(Date.class), mock(TeacherCategoryID.class), null, mock(TeacherID.class)),
                Arguments.of(mock(Date.class), mock(TeacherCategoryID.class), mock(WorkingPercentage.class), null)
        );
    }

    @ParameterizedTest
    @MethodSource("provideNullArgumentsForCreate")
    void shouldThrowExceptionWhenAnyArgumentIsNull(Date date, TeacherCategoryID categoryID, WorkingPercentage wp, TeacherID teacherID) throws Exception {
        //Arrange
        TeacherCareerProgressionService service = new TeacherCareerProgressionService(_repositoryDouble, _factoryDouble);

        //Act
        Executable action = () -> service.createTeacherCareerProgression(date, categoryID, wp, teacherID);

        //Assert
        assertThrows(IllegalArgumentException.class, action);
    }

    @Test
    void shouldReturnFalseWhenTeacherCareerProgressionAlreadyExists() throws Exception {
        //Arrange
        TeacherCareerProgressionService service = new TeacherCareerProgressionService(_repositoryDouble, _factoryDouble);
        Date date = mock(Date.class);
        TeacherCategoryID categoryID = mock(TeacherCategoryID.class);
        WorkingPercentage wp = mock(WorkingPercentage.class);
        TeacherID teacherID = mock(TeacherID.class);
        TeacherCareerProgression tcp = mock(TeacherCareerProgression.class);

        when(_factoryDouble.createTeacherCareerProgression(date, categoryID, wp, teacherID)).thenReturn(tcp);
        when(_repositoryDouble.containsOfIdentity(tcp.getID())).thenReturn(true);

        //Act
        boolean result = service.createTeacherCareerProgression(date, categoryID, wp, teacherID);

        //Assert
        assertFalse(result);
    }


    @Test
    void shouldSaveTeacherCareerProgressionAndReturnTrueWhenNotExists() throws Exception {
        //Arrange
        TeacherCareerProgressionService service = new TeacherCareerProgressionService(_repositoryDouble, _factoryDouble);
        Date date = mock(Date.class);
        TeacherCategoryID categoryID = mock(TeacherCategoryID.class);
        WorkingPercentage wp = mock(WorkingPercentage.class);
        TeacherID teacherID = mock(TeacherID.class);
        TeacherCareerProgression tcp = mock(TeacherCareerProgression.class);

        when(_factoryDouble.createTeacherCareerProgression(date, categoryID, wp, teacherID)).thenReturn(tcp);
        when(_repositoryDouble.containsOfIdentity(tcp.getID())).thenReturn(false);

        //Act
        boolean result = service.createTeacherCareerProgression(date, categoryID, wp, teacherID);

        //Assert
        assertTrue(result);
    }

}
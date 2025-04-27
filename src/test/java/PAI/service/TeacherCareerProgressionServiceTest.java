package PAI.service;

import PAI.VOs.Date;
import PAI.VOs.TeacherCategoryID;
import PAI.VOs.TeacherID;
import PAI.VOs.WorkingPercentage;
import PAI.domain.TeacherCareerProgression;
import PAI.factory.ITeacherCareerProgressionFactory;
import PAI.repository.ITeacherCareerProgressionRepository;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherCareerProgressionServiceTest {

    private ITeacherCareerProgressionRepository _repositoryDouble;
    private ITeacherCareerProgressionFactory _factoryDouble;
    private TeacherCareerProgression _lastTCPDouble;
    private TeacherCareerProgression _TCPDouble;
    private TeacherID _teacherIDDouble;
    private Date _dateDouble;
    private WorkingPercentage _lastWorkingPercentageDouble;
    private WorkingPercentage _workingPercentageDouble;
    private TeacherCategoryID _lastTeacherCategoryIDDouble;
    private TeacherCategoryID _teacherCategoryIDDouble;

    //Arrange
    @BeforeEach
    void setup() {
        _repositoryDouble = mock(ITeacherCareerProgressionRepository.class);
        _factoryDouble = mock(ITeacherCareerProgressionFactory.class);
    }

    private void createDoubles(){
        _lastTCPDouble = mock(TeacherCareerProgression.class);
        _TCPDouble = mock(TeacherCareerProgression.class);
        _teacherIDDouble = mock(TeacherID.class);
        _dateDouble = mock(Date.class);
        _lastWorkingPercentageDouble = mock(WorkingPercentage.class);
        _workingPercentageDouble = mock(WorkingPercentage.class);
        _lastTeacherCategoryIDDouble = mock(TeacherCategoryID.class);
        _teacherCategoryIDDouble = mock(TeacherCategoryID.class);
    }

    //TCPService
    @Test
    void shouldCreateTeacherCareerProgressionServiceWhenPassingValidInputs(){
        //Arrange

        //Act & Assert
        new TeacherCareerProgressionService(_repositoryDouble, _factoryDouble);
    }

    static Stream<Arguments> testWithNullInputs() {
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

    //CreateTCP
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
        createDoubles();
        TeacherCareerProgression teacherCareerProgressionDouble = mock(TeacherCareerProgression.class);

        when(_factoryDouble.createTeacherCareerProgression(_dateDouble, _teacherCategoryIDDouble,
                _workingPercentageDouble, _teacherIDDouble)).thenReturn(teacherCareerProgressionDouble);

        when(_repositoryDouble.containsOfIdentity(teacherCareerProgressionDouble.getID())).thenReturn(true);

        //Act
        boolean result = service.createTeacherCareerProgression(_dateDouble, _teacherCategoryIDDouble,
                                                            _workingPercentageDouble, _teacherIDDouble);

        //Assert
        assertFalse(result);
    }


    @Test
    void shouldSaveTeacherCareerProgressionAndReturnTrueWhenNotExists() throws Exception {
        //Arrange
        TeacherCareerProgressionService service = new TeacherCareerProgressionService(_repositoryDouble, _factoryDouble);
        createDoubles();
        TeacherCareerProgression teacherCareerProgressionDouble = mock(TeacherCareerProgression.class);


        when(_factoryDouble.createTeacherCareerProgression(_dateDouble, _teacherCategoryIDDouble,
                _workingPercentageDouble, _teacherIDDouble)).thenReturn(teacherCareerProgressionDouble);

        when(_repositoryDouble.containsOfIdentity(teacherCareerProgressionDouble.getID())).thenReturn(false);

        //Act
        boolean result = service.createTeacherCareerProgression(_dateDouble, _teacherCategoryIDDouble,
                                                            _workingPercentageDouble, _teacherIDDouble);

        //Assert
        assertTrue(result);
    }

    //updateTeacherCategory
    @Test
    void shouldReturnTrueIfTeacherCategoryWasSuccessfullyUpdated() throws Exception {
        //Arrange
        TeacherCareerProgressionService service = new TeacherCareerProgressionService(_repositoryDouble, _factoryDouble);
        createDoubles();

        when(_repositoryDouble.findLastTCPFromTeacherID(_teacherIDDouble)).thenReturn(Optional.of(_lastTCPDouble));
        when(_lastTCPDouble.isLastDateEqualOrBeforeNewDate(_dateDouble)).thenReturn(true);
        when(_lastTCPDouble.getWorkingPercentage()).thenReturn(_lastWorkingPercentageDouble);
        when(_lastTCPDouble.getTeacherCategoryID()).thenReturn(_lastTeacherCategoryIDDouble);

        when(_factoryDouble.createTeacherCareerProgression(_dateDouble, _teacherCategoryIDDouble,
                _lastWorkingPercentageDouble, _teacherIDDouble)).thenReturn(_TCPDouble);
        when(_TCPDouble.getTeacherID()).thenReturn(_teacherIDDouble);
        when(_repositoryDouble.save(_TCPDouble)).thenReturn(_TCPDouble);

        //Act
        boolean result = service.updateTeacherCategoryInTeacherCareerProgression(_dateDouble, _teacherCategoryIDDouble, _teacherIDDouble);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfLastTeacherCareerProgressionDoesNotExistWhenUpdatingTeachersCategory() throws Exception {
        //Arrange
        TeacherCareerProgressionService service = new TeacherCareerProgressionService(_repositoryDouble, _factoryDouble);
        createDoubles();

        when(_repositoryDouble.findLastTCPFromTeacherID(_teacherIDDouble)).thenReturn(Optional.empty());

        //Act
        boolean result = service.updateTeacherCategoryInTeacherCareerProgression(_dateDouble, _teacherCategoryIDDouble, _teacherIDDouble);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfDateIsTheSameOrOlderThanLastTCPWhenUpdatingTeachersCategory() throws Exception {
        //Arrange
        TeacherCareerProgressionService service = new TeacherCareerProgressionService(_repositoryDouble, _factoryDouble);
        createDoubles();

        when(_repositoryDouble.findLastTCPFromTeacherID(_teacherIDDouble)).thenReturn(Optional.of(_lastTCPDouble));
        when(_lastTCPDouble.isLastDateEqualOrBeforeNewDate(_dateDouble)).thenReturn(false);

        //Act
        boolean result = service.updateTeacherCategoryInTeacherCareerProgression(_dateDouble, _teacherCategoryIDDouble, _teacherIDDouble);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfTeacherCategoryIsTheSameAsTheLastOneRegisteredWhenUpdatingTeachersCategory() throws Exception {
        //Arrange
        TeacherCareerProgressionService service = new TeacherCareerProgressionService(_repositoryDouble, _factoryDouble);
        createDoubles();

        when(_repositoryDouble.findLastTCPFromTeacherID(_teacherIDDouble)).thenReturn(Optional.of(_lastTCPDouble));
        when(_lastTCPDouble.isLastDateEqualOrBeforeNewDate(_dateDouble)).thenReturn(true);
        when(_lastTCPDouble.getWorkingPercentage()).thenReturn(_lastWorkingPercentageDouble);
        when(_lastTCPDouble.getTeacherCategoryID()).thenReturn(_teacherCategoryIDDouble);

        //Act
        boolean result = service.updateTeacherCategoryInTeacherCareerProgression(_dateDouble, _teacherCategoryIDDouble, _teacherIDDouble);

        //Assert
        assertFalse(result);
    }

    static Stream<Arguments> provideInvalidInputs() {
        return Stream.of(
                Arguments.of(new IllegalArgumentException("Teacher Career Progression Id cannot be null!")),
                Arguments.of(new IllegalArgumentException("Date cannot be null!")),
                Arguments.of(new IllegalArgumentException("Teacher Category cannot be null!")),
                Arguments.of(new IllegalArgumentException("Working Percentage cannot be null!")),
                Arguments.of(new IllegalArgumentException("Teacher ID cannot be null!"))
        );
    }
    @ParameterizedTest
    @MethodSource("provideInvalidInputs")
    void shouldPropagateIllegalArgumentExceptionThrownWhenCreatingNewTeacherCareerProgression(IllegalArgumentException expectedException) {
        // Arrange
        TeacherCareerProgressionService service = new TeacherCareerProgressionService(_repositoryDouble, _factoryDouble);
        createDoubles();

        when(_repositoryDouble.findLastTCPFromTeacherID(_teacherIDDouble)).thenReturn(Optional.of(_lastTCPDouble));
        when(_lastTCPDouble.isLastDateEqualOrBeforeNewDate(_dateDouble)).thenReturn(true);
        when(_lastTCPDouble.getWorkingPercentage()).thenReturn(_lastWorkingPercentageDouble);
        when(_lastTCPDouble.getTeacherCategoryID()).thenReturn(_lastTeacherCategoryIDDouble);

        when(_factoryDouble.createTeacherCareerProgression(_dateDouble, _teacherCategoryIDDouble,
                _lastWorkingPercentageDouble, _teacherIDDouble)).thenThrow(expectedException);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                service.updateTeacherCategoryInTeacherCareerProgression(_dateDouble, _teacherCategoryIDDouble, _teacherIDDouble)
        );
    }

    @Test
    void shouldPropagateIllegalArgumentExceptionThrownWhenSavingNewTeacherCareerProgressionWithNullParameter() throws Exception {
        // Arrange
        TeacherCareerProgressionService service = new TeacherCareerProgressionService(_repositoryDouble, _factoryDouble);
        createDoubles();

        when(_repositoryDouble.findLastTCPFromTeacherID(_teacherIDDouble)).thenReturn(Optional.of(_lastTCPDouble));
        when(_lastTCPDouble.isLastDateEqualOrBeforeNewDate(_dateDouble)).thenReturn(true);
        when(_lastTCPDouble.getWorkingPercentage()).thenReturn(_lastWorkingPercentageDouble);
        when(_lastTCPDouble.getTeacherCategoryID()).thenReturn(_lastTeacherCategoryIDDouble);

        when(_factoryDouble.createTeacherCareerProgression(_dateDouble, _teacherCategoryIDDouble,
                _lastWorkingPercentageDouble, _teacherIDDouble)).thenReturn(_TCPDouble);
        when(_TCPDouble.getTeacherID()).thenReturn(_teacherIDDouble);
        when(_repositoryDouble.save(any())).thenThrow(new IllegalArgumentException());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                service.updateTeacherCategoryInTeacherCareerProgression(_dateDouble, _teacherCategoryIDDouble, _teacherIDDouble)
        );
    }

    @Test
    void shouldPropagateIllegalArgumentExceptionWhenSavingNewTeacherCareerProgressionWithNullParameter() throws Exception {
        // Arrange
        TeacherCareerProgressionService service = new TeacherCareerProgressionService(_repositoryDouble, _factoryDouble);
        createDoubles();

        when(_repositoryDouble.findLastTCPFromTeacherID(_teacherIDDouble)).thenReturn(Optional.of(_lastTCPDouble));
        when(_lastTCPDouble.isLastDateEqualOrBeforeNewDate(_dateDouble)).thenReturn(true);
        when(_lastTCPDouble.getWorkingPercentage()).thenReturn(_lastWorkingPercentageDouble);
        when(_lastTCPDouble.getTeacherCategoryID()).thenReturn(_lastTeacherCategoryIDDouble);

        when(_factoryDouble.createTeacherCareerProgression(_dateDouble, _teacherCategoryIDDouble,
                _lastWorkingPercentageDouble, _teacherIDDouble)).thenReturn(_TCPDouble);
        when(_TCPDouble.getTeacherID()).thenReturn(_teacherIDDouble);
        when(_repositoryDouble.save(_TCPDouble)).thenThrow(new PersistenceException());

        // Act & Assert
        assertThrows(PersistenceException.class, () ->
                service.updateTeacherCategoryInTeacherCareerProgression(_dateDouble, _teacherCategoryIDDouble, _teacherIDDouble)
        );
    }


//updateWorkingPercentage
@Test
void shouldReturnTrueWhenSuccessfullyUpdatesWorkingPercentageInTeacherCareerProgression() throws Exception {
    // Arrange
    createDoubles();
    TeacherCareerProgressionService tcpService = new TeacherCareerProgressionService(
            _repositoryDouble, _factoryDouble
    );

    Optional<TeacherCareerProgression> optionalTcp = Optional.of(_lastTCPDouble);
    when(_repositoryDouble.findLastTCPFromTeacherID(_teacherIDDouble)).thenReturn(optionalTcp);
    when(_lastTCPDouble.isLastDateEqualOrBeforeNewDate(_dateDouble)).thenReturn(true);
    when(_lastTCPDouble.getWorkingPercentage()).thenReturn(_lastWorkingPercentageDouble);
    when(_lastTCPDouble.getTeacherCategoryID()).thenReturn(_lastTeacherCategoryIDDouble);

    when(_factoryDouble.createTeacherCareerProgression(_dateDouble, _lastTeacherCategoryIDDouble, _workingPercentageDouble,
                                                       _teacherIDDouble)).thenReturn(_TCPDouble);

    // Act
    boolean result = tcpService.updateWorkingPercentageInTeacherCareerProgression(_dateDouble, _workingPercentageDouble,
                                                                                  _teacherIDDouble);

    // Assert
    assertTrue(result);
}

@Test
void shouldReturnFalseIfDateIsNullWhenUpdatingWorkingPercentage() throws Exception {
    // Arrange
    createDoubles();
    TeacherCareerProgressionService tcpService = new TeacherCareerProgressionService(_repositoryDouble, _factoryDouble);


    // Act
    boolean result = tcpService.updateWorkingPercentageInTeacherCareerProgression(null, _workingPercentageDouble,
                                                                                  _teacherIDDouble);

    // Assert
    assertFalse(result);
}

@Test
void shouldReturnFalseIfWorkingPercentageIsNullWhenUpdatingWorkingPercentage() throws Exception {
    // Arrange
    createDoubles();
    TeacherCareerProgressionService tcpService = new TeacherCareerProgressionService(_repositoryDouble, _factoryDouble);


    // Act
    boolean result = tcpService.updateWorkingPercentageInTeacherCareerProgression(_dateDouble, null, _teacherIDDouble);

    // Assert
    assertFalse(result);
}

@Test
void shouldReturnFalseIfTeacherIDIsNullWhenUpdatingWorkingPercentage() throws Exception {
    // Arrange
    createDoubles();
    TeacherCareerProgressionService tcpService = new TeacherCareerProgressionService(_repositoryDouble, _factoryDouble);


    // Act
    boolean result = tcpService.updateWorkingPercentageInTeacherCareerProgression(_dateDouble, _workingPercentageDouble, null);
    // Assert
    assertFalse(result);
}

    @Test
    void shouldReturnFalseWhenThereIsNoLastTCPToUpdateWorkingPercentage() throws Exception {
        // Arrange
        createDoubles();
        TeacherCareerProgressionService tcpService = new TeacherCareerProgressionService(_repositoryDouble, _factoryDouble);

        when(_repositoryDouble.findLastTCPFromTeacherID(_teacherIDDouble)).thenReturn(Optional.empty());

        // Act
        boolean result = tcpService.updateWorkingPercentageInTeacherCareerProgression(_dateDouble, _workingPercentageDouble, _teacherIDDouble);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenGivenDateIsBeforeLastTCPDateToUpdateWorkingPercentage() throws Exception {
        // Arrange
        createDoubles();
        TeacherCareerProgressionService tcpService = new TeacherCareerProgressionService(_repositoryDouble, _factoryDouble);

        when(_repositoryDouble.findLastTCPFromTeacherID(_teacherIDDouble)).thenReturn(Optional.of(_lastTCPDouble));
        when(_lastTCPDouble.isLastDateEqualOrBeforeNewDate(_dateDouble)).thenReturn(false);

        // Act
        boolean result = tcpService.updateWorkingPercentageInTeacherCareerProgression(_dateDouble, _workingPercentageDouble, _teacherIDDouble);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenGivenWorkingPercentageIsTheSameAsLastTCPWorkingPercentage() throws Exception {
        // Arrange
        createDoubles();
        TeacherCareerProgressionService tcpService = new TeacherCareerProgressionService(_repositoryDouble, _factoryDouble);

        when(_repositoryDouble.findLastTCPFromTeacherID(_teacherIDDouble)).thenReturn(Optional.of(_lastTCPDouble));
        when(_lastTCPDouble.isLastDateEqualOrBeforeNewDate(_dateDouble)).thenReturn(true);
        when(_lastTCPDouble.getTeacherCategoryID()).thenReturn(_lastTeacherCategoryIDDouble);
        when(_lastTCPDouble.getWorkingPercentage()).thenReturn(_workingPercentageDouble);

        when(_factoryDouble.createTeacherCareerProgression(_dateDouble, _teacherCategoryIDDouble, _workingPercentageDouble, _teacherIDDouble)).thenReturn(_TCPDouble);

        // Act
        boolean result = tcpService.updateWorkingPercentageInTeacherCareerProgression(_dateDouble, _workingPercentageDouble, _teacherIDDouble);

        // Assert
        assertFalse(result);
    }
}
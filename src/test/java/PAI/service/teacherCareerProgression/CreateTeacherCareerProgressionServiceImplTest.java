package PAI.service.teacherCareerProgression;

import PAI.VOs.*;
import PAI.assembler.teacherCareerProgression.ITeacherCareerProgressionInternalAssembler;
import PAI.domain.repositoryInterfaces.teacher.ITeacherRepository;
import PAI.domain.repositoryInterfaces.teacherCareerProgression.ITeacherCareerProgressionRepository;
import PAI.domain.repositoryInterfaces.teacherCategory.ITeacherCategoryRepository;
import PAI.domain.teacherCareerProgression.ITeacherCareerProgressionFactory;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryCommand;
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryDTO;
import PAI.dto.teacherCareerProgression.UpdateTeacherWorkingPercentageCommand;
import PAI.exception.BusinessRuleViolationException;
import PAI.exception.NotFoundException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CreateTeacherCareerProgressionServiceImplTest {

    // testing constructor method

    @Test
    void shouldReturnConstructor() {
        //arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCareerProgressionInternalAssembler doubleTCPInternalAssemblerInterface = mock(ITeacherCareerProgressionInternalAssembler.class);
        ITeacherCategoryRepository doubleTeacherCategoryRepository = mock (ITeacherCategoryRepository.class);

        //act
        CreateTeacherCareerProgressionServiceImpl service = new CreateTeacherCareerProgressionServiceImpl
                (iTeacherCareerProgressionRepository,factory,teacherRepository, doubleTCPInternalAssemblerInterface,
                        doubleTeacherCategoryRepository);

        //assert
        assertNotNull(service);
    }

    @Test
    void shouldReturnAnExceptionIfTeacherCareerProgressionRepositoryIsNull() {
        //arrange
        ITeacherCareerProgressionFactory doubleTeacherCareerProgressionFactoryInterface
                = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository doubleTeacherRepositoryInterface = mock(ITeacherRepository.class);
        ITeacherCareerProgressionInternalAssembler doubleTCPInternalAssemblerInterface = mock(ITeacherCareerProgressionInternalAssembler.class);
        ITeacherCategoryRepository doubleTeacherCategoryRepository = mock (ITeacherCategoryRepository.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CreateTeacherCareerProgressionServiceImpl
                    (null,doubleTeacherCareerProgressionFactoryInterface,doubleTeacherRepositoryInterface,
                            doubleTCPInternalAssemblerInterface, doubleTeacherCategoryRepository);
        });

        //assert
        assertEquals("Teacher Career Progression Repository Interface cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnAnExceptionIfTeacherCareerProgressionFactoryIsNull() {
        //arrange
        ITeacherCareerProgressionRepository doubleTeacherCareerProgressionRepositoryInterface
                = mock(ITeacherCareerProgressionRepository.class);
        ITeacherRepository doubleTeacherRepositoryInterface = mock(ITeacherRepository.class);
        ITeacherCareerProgressionInternalAssembler doubleTCPInternalAssemblerInterface = mock(ITeacherCareerProgressionInternalAssembler.class);
        ITeacherCategoryRepository doubleTeacherCategoryRepository = mock (ITeacherCategoryRepository.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CreateTeacherCareerProgressionServiceImpl
                    (doubleTeacherCareerProgressionRepositoryInterface,null,doubleTeacherRepositoryInterface,
                            doubleTCPInternalAssemblerInterface, doubleTeacherCategoryRepository);
        });

        //assert
        assertEquals("Teacher Career Progression Factory Interface cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnAnExceptionIfTeacherRepositoryIsNull() {
        //arrange
        ITeacherCareerProgressionRepository doubleTeacherCareerProgressionRepositoryInterface
                = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory doubleTeacherCareerProgressionFactoryInterface
                = mock(ITeacherCareerProgressionFactory.class);
        ITeacherCareerProgressionInternalAssembler doubleTCPInternalAssemblerInterface = mock(ITeacherCareerProgressionInternalAssembler.class);
        ITeacherCategoryRepository doubleTeacherCategoryRepository = mock (ITeacherCategoryRepository.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CreateTeacherCareerProgressionServiceImpl
                    (doubleTeacherCareerProgressionRepositoryInterface,doubleTeacherCareerProgressionFactoryInterface,null,
                            doubleTCPInternalAssemblerInterface, doubleTeacherCategoryRepository);
        });

        //assert
        assertEquals("Teacher Repository Interface cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnAnExceptionIfTeacherCareerProgressionInternalAssemblerIsNull() {
        //arrange
        ITeacherCareerProgressionRepository doubleTeacherCareerProgressionRepositoryInterface
                = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory doubleTeacherCareerProgressionFactoryInterface
                = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository doubleTeacherRepositoryInterface = mock(ITeacherRepository.class);
        ITeacherCategoryRepository doubleTeacherCategoryRepository = mock (ITeacherCategoryRepository.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CreateTeacherCareerProgressionServiceImpl
                    (doubleTeacherCareerProgressionRepositoryInterface,doubleTeacherCareerProgressionFactoryInterface,
                            doubleTeacherRepositoryInterface, null, doubleTeacherCategoryRepository);
        });

        //assert
        assertEquals("Teacher Career Progression Internal Assembler Interface cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnAnExceptionIfTeacherCategoryRepositoryIsNull() {
        //arrange
        ITeacherCareerProgressionRepository doubleTeacherCareerProgressionRepositoryInterface
                = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory doubleTeacherCareerProgressionFactoryInterface
                = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository doubleTeacherRepositoryInterface = mock(ITeacherRepository.class);
        ITeacherCareerProgressionInternalAssembler doubleTCPInternalAssemblerInterface = mock(ITeacherCareerProgressionInternalAssembler.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CreateTeacherCareerProgressionServiceImpl
                    (doubleTeacherCareerProgressionRepositoryInterface,doubleTeacherCareerProgressionFactoryInterface,
                            doubleTeacherRepositoryInterface, doubleTCPInternalAssemblerInterface, null);
        });

        //assert
        assertEquals("Teacher Category Repository Interface cannot be null.", exception.getMessage());
    }

    // testing createTeacherCareerProgression method

    @Test
    void shouldReturnAnOptionalOfTeacherCareerProgression() throws Exception{
        //arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCareerProgressionInternalAssembler doubleTCPInternalAssemblerInterface = mock(ITeacherCareerProgressionInternalAssembler.class);
        ITeacherCategoryRepository doubleTeacherCategoryRepository = mock (ITeacherCategoryRepository.class);

        CreateTeacherCareerProgressionServiceImpl service = new CreateTeacherCareerProgressionServiceImpl(iTeacherCareerProgressionRepository,
                factory,teacherRepository, doubleTCPInternalAssemblerInterface, doubleTeacherCategoryRepository);

        Date date = mock(Date.class);
        TeacherCategoryID teacherCategoryID= mock(TeacherCategoryID.class);
        WorkingPercentage workingPercentage = mock(WorkingPercentage.class);
        TeacherID teacherID = mock(TeacherID.class);
        TeacherCareerProgression teacherCareerProgression = mock(TeacherCareerProgression.class);
        TeacherCareerProgressionID teacherCareerProgressionID = mock(TeacherCareerProgressionID.class);

        when(factory.createTeacherCareerProgression(date,teacherCategoryID,workingPercentage,teacherID)).thenReturn(teacherCareerProgression);
        when(teacherCareerProgression.getID()).thenReturn(teacherCareerProgressionID);
        when(iTeacherCareerProgressionRepository.containsOfIdentity(teacherCareerProgressionID)).thenReturn(false);
        when(iTeacherCareerProgressionRepository.save(teacherCareerProgression)).thenReturn(teacherCareerProgression);

        //act
        Optional<TeacherCareerProgression> result = service.createTeacherCareerProgression(date,teacherCategoryID,workingPercentage,teacherID);
        //assert
        assertTrue(result.isPresent());
    }

    @Test
    void shouldReturnAnExceptionWhenDateIsNull()  {
        //arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCareerProgressionInternalAssembler doubleTCPInternalAssemblerInterface = mock(ITeacherCareerProgressionInternalAssembler.class);
        ITeacherCategoryRepository doubleTeacherCategoryRepository = mock (ITeacherCategoryRepository.class);

        CreateTeacherCareerProgressionServiceImpl service = new CreateTeacherCareerProgressionServiceImpl
                (iTeacherCareerProgressionRepository,factory,teacherRepository, doubleTCPInternalAssemblerInterface,
                        doubleTeacherCategoryRepository);

        Date date = null;
        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);
        WorkingPercentage workingPercentage = mock(WorkingPercentage.class);
        TeacherID teacherID = mock(TeacherID.class);
        //assert
        assertThrows(Exception.class, ()-> service.createTeacherCareerProgression(date,teacherCategoryID,workingPercentage,teacherID));
    }

    @Test
    void shouldReturnAnExceptionWhenTeacherIDIsNull()  {
        //arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCareerProgressionInternalAssembler doubleTCPInternalAssemblerInterface = mock(ITeacherCareerProgressionInternalAssembler.class);
        ITeacherCategoryRepository doubleTeacherCategoryRepository = mock (ITeacherCategoryRepository.class);

        CreateTeacherCareerProgressionServiceImpl service = new CreateTeacherCareerProgressionServiceImpl
                (iTeacherCareerProgressionRepository,factory,teacherRepository, doubleTCPInternalAssemblerInterface, doubleTeacherCategoryRepository);

        Date date = mock(Date.class);
        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);
        WorkingPercentage workingPercentage = mock(WorkingPercentage.class);
        TeacherID teacherID = null;
        //assert
        assertThrows(Exception.class, ()-> service.createTeacherCareerProgression(date,teacherCategoryID,workingPercentage,teacherID));
    }

    @Test
    void shouldReturnAnExceptionWhenTeacherCategoryIDIsNull() {
        //arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCareerProgressionInternalAssembler doubleTCPInternalAssemblerInterface = mock(ITeacherCareerProgressionInternalAssembler.class);
        ITeacherCategoryRepository doubleTeacherCategoryRepository = mock (ITeacherCategoryRepository.class);

        CreateTeacherCareerProgressionServiceImpl service = new CreateTeacherCareerProgressionServiceImpl
                (iTeacherCareerProgressionRepository,factory,teacherRepository, doubleTCPInternalAssemblerInterface,
                        doubleTeacherCategoryRepository);

        Date date = mock(Date.class);
        TeacherCategoryID teacherCategoryID = null;
        WorkingPercentage workingPercentage = mock(WorkingPercentage.class);
        TeacherID teacherID = mock(TeacherID.class);
        //assert
        assertThrows(Exception.class, ()-> service.createTeacherCareerProgression(date,teacherCategoryID,workingPercentage,teacherID));
    }

    @Test
    void shouldReturnAnExceptionWhenWorkingPercentageIsNull() {
        //arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCareerProgressionInternalAssembler doubleTCPInternalAssemblerInterface = mock(ITeacherCareerProgressionInternalAssembler.class);
        ITeacherCategoryRepository doubleTeacherCategoryRepository = mock (ITeacherCategoryRepository.class);

        CreateTeacherCareerProgressionServiceImpl service = new CreateTeacherCareerProgressionServiceImpl
                (iTeacherCareerProgressionRepository,factory,teacherRepository, doubleTCPInternalAssemblerInterface, doubleTeacherCategoryRepository);

        Date date = mock(Date.class);
        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);
        WorkingPercentage workingPercentage = null;
        TeacherID teacherID = mock(TeacherID.class);
        //assert
        assertThrows(Exception.class, ()-> service.createTeacherCareerProgression(date,teacherCategoryID,workingPercentage,teacherID));
    }

    @Test
    void shouldReturnAnOptionalEmptyWhenTeacherAlreadyHaveThatCategory() throws Exception{
        //arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCareerProgressionInternalAssembler doubleTCPInternalAssemblerInterface = mock(ITeacherCareerProgressionInternalAssembler.class);
        ITeacherCategoryRepository doubleTeacherCategoryRepository = mock (ITeacherCategoryRepository.class);

        CreateTeacherCareerProgressionServiceImpl service = new CreateTeacherCareerProgressionServiceImpl
                (iTeacherCareerProgressionRepository,factory,teacherRepository, doubleTCPInternalAssemblerInterface, doubleTeacherCategoryRepository);

        Date date = mock(Date.class);
        TeacherCategoryID teacherCategoryID= mock(TeacherCategoryID.class);
        WorkingPercentage workingPercentage = mock(WorkingPercentage.class);
        TeacherID teacherID = mock(TeacherID.class);
        TeacherCareerProgression teacherCareerProgression = mock(TeacherCareerProgression.class);
        TeacherCareerProgressionID teacherCareerProgressionID = mock(TeacherCareerProgressionID.class);

        when(factory.createTeacherCareerProgression(date,teacherCategoryID,workingPercentage,teacherID)).thenReturn(teacherCareerProgression);
        when(teacherCareerProgression.getID()).thenReturn(teacherCareerProgressionID);
        when(iTeacherCareerProgressionRepository.containsOfIdentity(teacherCareerProgressionID)).thenReturn(true);
        //act
        Optional<TeacherCareerProgression> result = service.createTeacherCareerProgression(date,teacherCategoryID,workingPercentage,teacherID);
        //assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnDTOWhenWasPossibleUpdateTeacherCategory() throws Exception {
        //arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCareerProgressionInternalAssembler doubleTCPInternalAssemblerInterface = mock(ITeacherCareerProgressionInternalAssembler.class);
        ITeacherCategoryRepository doubleTeacherCategoryRepository = mock (ITeacherCategoryRepository.class);

        CreateTeacherCareerProgressionServiceImpl service = new CreateTeacherCareerProgressionServiceImpl
                (iTeacherCareerProgressionRepository,factory,teacherRepository, doubleTCPInternalAssemblerInterface, doubleTeacherCategoryRepository);

        Date date = mock(Date.class);
        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);
        TeacherCategoryID teacherCategoryID2 = mock(TeacherCategoryID.class);
        TeacherID teacherID = mock(TeacherID.class);
        UpdateTeacherCategoryCommand command = mock(UpdateTeacherCategoryCommand.class);
        TeacherCareerProgression previousTCP = mock(TeacherCareerProgression.class);
        WorkingPercentage wp = mock(WorkingPercentage.class);
        TeacherCareerProgression newTCP = mock(TeacherCareerProgression.class);

        when (doubleTeacherCategoryRepository.containsOfIdentity(teacherCategoryID)).thenReturn(true);
        when (doubleTeacherCategoryRepository.containsOfIdentity(teacherCategoryID2)).thenReturn(true);
        when(command.date()).thenReturn(date);
        when(command.teacherCategoryID()).thenReturn(teacherCategoryID);
        when(command.teacherID()).thenReturn(teacherID);

        when(iTeacherCareerProgressionRepository.findLastTCPFromTeacherID(teacherID)).thenReturn(Optional.of(previousTCP));
        when(previousTCP.isLastDateEqualOrBeforeNewDate(date)).thenReturn(true);
        when(previousTCP.getWorkingPercentage()).thenReturn(wp);
        when(previousTCP.getTeacherCategoryID()).thenReturn(teacherCategoryID2);
        when(factory.createTeacherCareerProgression(date, teacherCategoryID, wp, teacherID)).thenReturn(newTCP);
        when(iTeacherCareerProgressionRepository.save(newTCP)).thenReturn(newTCP);

        UpdateTeacherCategoryDTO doubleUpdateTeacherCategoryDTO = mock (UpdateTeacherCategoryDTO.class);

        when(doubleTCPInternalAssemblerInterface.toDTO(newTCP)).thenReturn(doubleUpdateTeacherCategoryDTO);

        // Act
        UpdateTeacherCategoryDTO result = service.updateTeacherCategory(command);

        // Assert
        assertEquals (result, doubleUpdateTeacherCategoryDTO);
    }

    @Test
    void shouldReturnAnExceptionWhenNoPreviousTCP() {
        //arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCareerProgressionInternalAssembler doubleTCPInternalAssemblerInterface = mock(ITeacherCareerProgressionInternalAssembler.class);
        ITeacherCategoryRepository doubleTeacherCategoryRepository = mock (ITeacherCategoryRepository.class);

        CreateTeacherCareerProgressionServiceImpl service = new CreateTeacherCareerProgressionServiceImpl
                (iTeacherCareerProgressionRepository,factory,teacherRepository,doubleTCPInternalAssemblerInterface, doubleTeacherCategoryRepository);

        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);
        when (doubleTeacherCategoryRepository.containsOfIdentity(teacherCategoryID)).thenReturn(true);

        when(iTeacherCareerProgressionRepository.findLastTCPFromTeacherID(any())).thenReturn(Optional.empty());
        UpdateTeacherCategoryCommand command = mock(UpdateTeacherCategoryCommand.class);
        when(command.teacherCategoryID()).thenReturn(teacherCategoryID);

        // Act
        Exception exception = assertThrows(NotFoundException.class, () -> {
            service.updateTeacherCategory(command);
        });

        // Assert
        assertEquals("This teacher has no previous career progression record. Please create one before attempting an update.", exception.getMessage());
    }

    @Test
    void shouldReturnAnExceptionWhenTeacherCategoryDoesNotExist() {
        //arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCareerProgressionInternalAssembler doubleTCPInternalAssemblerInterface = mock(ITeacherCareerProgressionInternalAssembler.class);
        ITeacherCategoryRepository doubleTeacherCategoryRepository = mock (ITeacherCategoryRepository.class);

        CreateTeacherCareerProgressionServiceImpl service = new CreateTeacherCareerProgressionServiceImpl
                (iTeacherCareerProgressionRepository,factory,teacherRepository,doubleTCPInternalAssemblerInterface, doubleTeacherCategoryRepository);

        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);
        when (doubleTeacherCategoryRepository.containsOfIdentity(teacherCategoryID)).thenReturn(false);

        UpdateTeacherCategoryCommand command = mock(UpdateTeacherCategoryCommand.class);
        when(command.teacherCategoryID()).thenReturn(teacherCategoryID);

        // Act
        Exception exception = assertThrows(NotFoundException.class, () -> {
            service.updateTeacherCategory(command);
        });

        // Assert
        assertEquals("This Teacher Category ID does not exist!", exception.getMessage());
    }

    @Test
    void shouldReturnAnExceptionWhenDateNotAfterLast() {
        //arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCareerProgressionInternalAssembler doubleTCPInternalAssemblerInterface = mock(ITeacherCareerProgressionInternalAssembler.class);
        ITeacherCategoryRepository doubleTeacherCategoryRepository = mock (ITeacherCategoryRepository.class);

        CreateTeacherCareerProgressionServiceImpl service = new CreateTeacherCareerProgressionServiceImpl
                (iTeacherCareerProgressionRepository,factory,teacherRepository, doubleTCPInternalAssemblerInterface, doubleTeacherCategoryRepository);

        TeacherCareerProgression tcp = mock(TeacherCareerProgression.class);

        UpdateTeacherCategoryCommand command = mock(UpdateTeacherCategoryCommand.class);

        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);
        when (doubleTeacherCategoryRepository.containsOfIdentity(teacherCategoryID)).thenReturn(true);
        when(command.teacherCategoryID()).thenReturn(teacherCategoryID);

        when(iTeacherCareerProgressionRepository.findLastTCPFromTeacherID(any())).thenReturn(Optional.of(tcp));
        when(tcp.isLastDateEqualOrBeforeNewDate(any())).thenReturn(false);

        // Act
        Exception exception = assertThrows(BusinessRuleViolationException.class, () -> {
            service.updateTeacherCategory(command);
        });

        // Assert
        assertEquals("The date must be equal to or later than the previous update.", exception.getMessage());
    }

    @Test
    void shouldReturnAnExceptionWhenCategoryIsSame()  {
        // Arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCareerProgressionInternalAssembler doubleTCPInternalAssemblerInterface = mock(ITeacherCareerProgressionInternalAssembler.class);
        ITeacherCategoryRepository doubleTeacherCategoryRepository = mock (ITeacherCategoryRepository.class);

        CreateTeacherCareerProgressionServiceImpl service = new CreateTeacherCareerProgressionServiceImpl
                (iTeacherCareerProgressionRepository,factory,teacherRepository, doubleTCPInternalAssemblerInterface, doubleTeacherCategoryRepository);

        Date date = mock(Date.class);
        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);
        TeacherID teacherID = mock(TeacherID.class);
        UpdateTeacherCategoryCommand command = mock(UpdateTeacherCategoryCommand.class);
        TeacherCareerProgression previousTCP = mock(TeacherCareerProgression.class);
        WorkingPercentage wp = mock(WorkingPercentage.class);
        TeacherCareerProgression newTCP = mock(TeacherCareerProgression.class);

        when (doubleTeacherCategoryRepository.containsOfIdentity(teacherCategoryID)).thenReturn(true);

        when(command.date()).thenReturn(date);
        when(command.teacherCategoryID()).thenReturn(teacherCategoryID);
        when(command.teacherID()).thenReturn(teacherID);

        when(iTeacherCareerProgressionRepository.findLastTCPFromTeacherID(teacherID)).thenReturn(Optional.of(previousTCP));
        when(previousTCP.isLastDateEqualOrBeforeNewDate(date)).thenReturn(true);
        when(previousTCP.getWorkingPercentage()).thenReturn(wp);
        when(previousTCP.getTeacherCategoryID()).thenReturn(teacherCategoryID);
        when(factory.createTeacherCareerProgression(date, teacherCategoryID, wp, teacherID)).thenReturn(newTCP);

        // Act
        Exception exception = assertThrows(BusinessRuleViolationException.class, () -> {
            service.updateTeacherCategory(command);
        });

        // Assert
        assertEquals("The Teacher Category must be different to the previous update.", exception.getMessage());
    }


    @Test
    void shouldReturnOptionalOfTeacherCareerProgressionWhenWorkingPercentageChanges() throws Exception {
        //Arrange
        ITeacherCareerProgressionRepository repo = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepo = mock(ITeacherRepository.class);
        ITeacherCareerProgressionInternalAssembler doubleTCPInternalAssemblerInterface = mock(ITeacherCareerProgressionInternalAssembler.class);
        ITeacherCategoryRepository doubleTeacherCategoryRepository = mock (ITeacherCategoryRepository.class);

        CreateTeacherCareerProgressionServiceImpl service = new CreateTeacherCareerProgressionServiceImpl
                (repo, factory, teacherRepo, doubleTCPInternalAssemblerInterface, doubleTeacherCategoryRepository);

        UpdateTeacherWorkingPercentageCommand command = mock(UpdateTeacherWorkingPercentageCommand.class);
        TeacherID teacherID = mock(TeacherID.class);
        Date date = mock(Date.class);
        WorkingPercentage oldWP = mock(WorkingPercentage.class);
        WorkingPercentage newWP = mock(WorkingPercentage.class);
        TeacherCareerProgression lastTCP = mock(TeacherCareerProgression.class);
        TeacherCategoryID categoryID = mock(TeacherCategoryID.class);
        TeacherCareerProgression newTCP = mock(TeacherCareerProgression.class);

        when(command.teacherID()).thenReturn(teacherID);
        when(command.date()).thenReturn(date);
        when(command.workingPercentage()).thenReturn(newWP);
        when(teacherRepo.containsOfIdentity(teacherID)).thenReturn(true);
        when(repo.findLastTCPFromTeacherID(teacherID)).thenReturn(Optional.of(lastTCP));
        when(lastTCP.isLastDateEqualOrBeforeNewDate(date)).thenReturn(true);
        when(lastTCP.getTeacherCategoryID()).thenReturn(categoryID);
        when(lastTCP.getWorkingPercentage()).thenReturn(oldWP);
        when(factory.createTeacherCareerProgression(date, categoryID, newWP, teacherID)).thenReturn(newTCP);
        when(repo.save(newTCP)).thenReturn(newTCP);

        //Act
        Optional<TeacherCareerProgression> result = service.updateTeacherWorkingPercentageInTeacherCareerProgression(command);

        //Assert
        assertTrue(result.isPresent());
        assertEquals(newTCP, result.get());
    }

    @Test
    void shouldReturnEmptyWhenTeacherNotFoundForWorkingPercentage() throws Exception {
        // Arrange
        ITeacherCareerProgressionRepository repo = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepo = mock(ITeacherRepository.class);
        ITeacherCareerProgressionInternalAssembler doubleTCPInternalAssemblerInterface = mock(ITeacherCareerProgressionInternalAssembler.class);
        ITeacherCategoryRepository doubleTeacherCategoryRepository = mock (ITeacherCategoryRepository.class);

        CreateTeacherCareerProgressionServiceImpl service = new CreateTeacherCareerProgressionServiceImpl
                (repo, factory, teacherRepo, doubleTCPInternalAssemblerInterface, doubleTeacherCategoryRepository);

        UpdateTeacherWorkingPercentageCommand command = mock(UpdateTeacherWorkingPercentageCommand.class);
        when(teacherRepo.containsOfIdentity(any())).thenReturn(false);

        // Act
        Optional<TeacherCareerProgression> result = service.updateTeacherWorkingPercentageInTeacherCareerProgression(command);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyWhenNoPreviousTCPForWorkingPercentage() throws Exception {
        // Arrange
        ITeacherCareerProgressionRepository repo = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepo = mock(ITeacherRepository.class);
        ITeacherCareerProgressionInternalAssembler doubleTCPInternalAssemblerInterface = mock(ITeacherCareerProgressionInternalAssembler.class);
        ITeacherCategoryRepository doubleTeacherCategoryRepository = mock (ITeacherCategoryRepository.class);

        CreateTeacherCareerProgressionServiceImpl service = new CreateTeacherCareerProgressionServiceImpl
                (repo, factory, teacherRepo, doubleTCPInternalAssemblerInterface, doubleTeacherCategoryRepository);

        UpdateTeacherWorkingPercentageCommand command = mock(UpdateTeacherWorkingPercentageCommand.class);
        when(teacherRepo.containsOfIdentity(any())).thenReturn(true);
        when(repo.findLastTCPFromTeacherID(any())).thenReturn(Optional.empty());

        // Act
        Optional<TeacherCareerProgression> result = service.updateTeacherWorkingPercentageInTeacherCareerProgression(command);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyWhenDateNotAfterLastForWorkingPercentage() throws Exception {
        // Arrange
        ITeacherCareerProgressionRepository repo = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepo = mock(ITeacherRepository.class);
        ITeacherCareerProgressionInternalAssembler doubleTCPInternalAssemblerInterface = mock(ITeacherCareerProgressionInternalAssembler.class);
        ITeacherCategoryRepository doubleTeacherCategoryRepository = mock (ITeacherCategoryRepository.class);

        CreateTeacherCareerProgressionServiceImpl service = new CreateTeacherCareerProgressionServiceImpl
                (repo, factory, teacherRepo, doubleTCPInternalAssemblerInterface, doubleTeacherCategoryRepository);

        UpdateTeacherWorkingPercentageCommand command = mock(UpdateTeacherWorkingPercentageCommand.class);
        TeacherID teacherID = mock(TeacherID.class);
        TeacherCareerProgression lastTCP = mock(TeacherCareerProgression.class);

        when(command.teacherID()).thenReturn(teacherID);
        when(teacherRepo.containsOfIdentity(teacherID)).thenReturn(true);
        when(repo.findLastTCPFromTeacherID(teacherID)).thenReturn(Optional.of(lastTCP));
        when(lastTCP.isLastDateEqualOrBeforeNewDate(any())).thenReturn(false);

        // Act
        Optional<TeacherCareerProgression> result = service.updateTeacherWorkingPercentageInTeacherCareerProgression(command);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyWhenWorkingPercentageIsSame() throws Exception {
        // Arrange
        ITeacherCareerProgressionRepository repo = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepo = mock(ITeacherRepository.class);
        ITeacherCareerProgressionInternalAssembler doubleTCPInternalAssemblerInterface = mock(ITeacherCareerProgressionInternalAssembler.class);
        ITeacherCategoryRepository doubleTeacherCategoryRepository = mock (ITeacherCategoryRepository.class);

        CreateTeacherCareerProgressionServiceImpl service = new CreateTeacherCareerProgressionServiceImpl
                (repo, factory, teacherRepo, doubleTCPInternalAssemblerInterface, doubleTeacherCategoryRepository);

        UpdateTeacherWorkingPercentageCommand command = mock(UpdateTeacherWorkingPercentageCommand.class);
        TeacherID teacherID = mock(TeacherID.class);
        Date date = mock(Date.class);
        WorkingPercentage wp = mock(WorkingPercentage.class);
        TeacherCareerProgression lastTCP = mock(TeacherCareerProgression.class);
        TeacherCategoryID categoryID = mock(TeacherCategoryID.class);

        when(command.teacherID()).thenReturn(teacherID);
        when(command.date()).thenReturn(date);
        when(command.workingPercentage()).thenReturn(wp);
        when(teacherRepo.containsOfIdentity(teacherID)).thenReturn(true);
        when(repo.findLastTCPFromTeacherID(teacherID)).thenReturn(Optional.of(lastTCP));
        when(lastTCP.isLastDateEqualOrBeforeNewDate(date)).thenReturn(true);
        when(lastTCP.getTeacherCategoryID()).thenReturn(categoryID);
        when(lastTCP.getWorkingPercentage()).thenReturn(wp);

        // Act
        Optional<TeacherCareerProgression> result = service.updateTeacherWorkingPercentageInTeacherCareerProgression(command);

        // Assert
        assertTrue(result.isEmpty());
    }

    // testing getAllTeacherCareerProgression method

    @Test
    void shouldReturnAListOfTeacherCareerProgression () {
        // arrange
        ITeacherCareerProgressionRepository doubleTeacherCareerProgressionRepositoryInterface
                = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory doubleTeacherCareerProgressionFactoryInterface
                = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository doubleTeacherRepositoryInterface = mock(ITeacherRepository.class);
        ITeacherCareerProgressionInternalAssembler doubleTCPInternalAssemblerInterface =
                mock(ITeacherCareerProgressionInternalAssembler.class);
        ITeacherCategoryRepository doubleTeacherCategoryRepository = mock (ITeacherCategoryRepository.class);

        CreateTeacherCareerProgressionServiceImpl service = new CreateTeacherCareerProgressionServiceImpl(doubleTeacherCareerProgressionRepositoryInterface,
                doubleTeacherCareerProgressionFactoryInterface, doubleTeacherRepositoryInterface, doubleTCPInternalAssemblerInterface,doubleTeacherCategoryRepository);

        TeacherCareerProgression doubleTeacherCareerProgression1 = mock(TeacherCareerProgression.class);
        TeacherCareerProgression doubleTeacherCareerProgression2 = mock(TeacherCareerProgression.class);
        Iterable<TeacherCareerProgression> doubleList = Arrays.asList(doubleTeacherCareerProgression1, doubleTeacherCareerProgression2);
        when(doubleTeacherCareerProgressionRepositoryInterface.findAll()).thenReturn(doubleList);

        UpdateTeacherCategoryDTO doubleDTO1 = mock(UpdateTeacherCategoryDTO.class);
        UpdateTeacherCategoryDTO doubleDTO2 = mock(UpdateTeacherCategoryDTO.class);
        when (doubleTCPInternalAssemblerInterface.toDTOList(doubleList)).thenReturn(List.of(doubleDTO1, doubleDTO2));

        // act
        List <UpdateTeacherCategoryDTO> result = service.getAllTeacherCareerProgression();

        // assert
        assertEquals(List.of(doubleDTO1, doubleDTO2), result);
    }

    @Test
    void shouldReturnAnEmptyListOfTeacherCareerProgression () {
        // arrange
        ITeacherCareerProgressionRepository doubleTeacherCareerProgressionRepositoryInterface
                = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory doubleTeacherCareerProgressionFactoryInterface
                = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository doubleTeacherRepositoryInterface = mock(ITeacherRepository.class);
        ITeacherCareerProgressionInternalAssembler doubleTCPInternalAssemblerInterface =
                mock(ITeacherCareerProgressionInternalAssembler.class);
        ITeacherCategoryRepository doubleTeacherCategoryRepository = mock (ITeacherCategoryRepository.class);

        CreateTeacherCareerProgressionServiceImpl service = new CreateTeacherCareerProgressionServiceImpl(doubleTeacherCareerProgressionRepositoryInterface,
                doubleTeacherCareerProgressionFactoryInterface, doubleTeacherRepositoryInterface, doubleTCPInternalAssemblerInterface,
                doubleTeacherCategoryRepository);

        Iterable<TeacherCareerProgression> doubleList = Arrays.asList();
        when(doubleTeacherCareerProgressionRepositoryInterface.findAll()).thenReturn(doubleList);

        // act
        List <UpdateTeacherCategoryDTO> result = service.getAllTeacherCareerProgression();

        // assert
        assertFalse(result.iterator().hasNext());
    }

    // testing getTeacherCareerProgressionByID method

    @Test
    void shouldReturnADTOIfTeacherCareerProgressionIdExists () {
        // arrange
        ITeacherCareerProgressionRepository doubleTeacherCareerProgressionRepositoryInterface
                = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory doubleTeacherCareerProgressionFactoryInterface
                = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository doubleTeacherRepositoryInterface = mock(ITeacherRepository.class);
        ITeacherCareerProgressionInternalAssembler doubleTCPInternalAssemblerInterface =
                mock(ITeacherCareerProgressionInternalAssembler.class);
        ITeacherCategoryRepository doubleTeacherCategoryRepository = mock (ITeacherCategoryRepository.class);

        CreateTeacherCareerProgressionServiceImpl service = new CreateTeacherCareerProgressionServiceImpl
                (doubleTeacherCareerProgressionRepositoryInterface, doubleTeacherCareerProgressionFactoryInterface,
                        doubleTeacherRepositoryInterface, doubleTCPInternalAssemblerInterface, doubleTeacherCategoryRepository);

        TeacherCareerProgressionID doubleTeacherCareerProgressionId = mock(TeacherCareerProgressionID.class);
        TeacherCareerProgression doubleTeacherCareerProgression = mock(TeacherCareerProgression.class);
        UpdateTeacherCategoryDTO doubleUpdateTeacherCategoryDTO = mock(UpdateTeacherCategoryDTO.class);

        when(doubleTeacherCareerProgressionRepositoryInterface.ofIdentity(doubleTeacherCareerProgressionId)).
                thenReturn(Optional.of(doubleTeacherCareerProgression));

        when(doubleTCPInternalAssemblerInterface.toDTO(doubleTeacherCareerProgression)).thenReturn(doubleUpdateTeacherCategoryDTO);

        // act
        UpdateTeacherCategoryDTO result = service.getTeacherCareerProgressionByID(doubleTeacherCareerProgressionId);

        // assert
        assertEquals(doubleUpdateTeacherCategoryDTO, result);
    }

    @Test
    void shouldReturnAnExceptionIfInputIsNull () {
        // arrange
        ITeacherCareerProgressionRepository doubleTeacherCareerProgressionRepositoryInterface
                = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory doubleTeacherCareerProgressionFactoryInterface
                = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository doubleTeacherRepositoryInterface = mock(ITeacherRepository.class);
        ITeacherCareerProgressionInternalAssembler doubleTCPInternalAssemblerInterface =
                mock(ITeacherCareerProgressionInternalAssembler.class);
        ITeacherCategoryRepository doubleTeacherCategoryRepository = mock (ITeacherCategoryRepository.class);

        CreateTeacherCareerProgressionServiceImpl service = new CreateTeacherCareerProgressionServiceImpl
                (doubleTeacherCareerProgressionRepositoryInterface, doubleTeacherCareerProgressionFactoryInterface,
                        doubleTeacherRepositoryInterface, doubleTCPInternalAssemblerInterface, doubleTeacherCategoryRepository);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.getTeacherCareerProgressionByID(null);
        });

        // Assert
        assertEquals("Teacher Career Progression ID is required!", exception.getMessage());
    }

    @Test
    void shouldReturnAnExceptionIfIdDoesNotExist () {
        // arrange
        ITeacherCareerProgressionRepository doubleTeacherCareerProgressionRepositoryInterface
                = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory doubleTeacherCareerProgressionFactoryInterface
                = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository doubleTeacherRepositoryInterface = mock(ITeacherRepository.class);
        ITeacherCareerProgressionInternalAssembler doubleTCPInternalAssemblerInterface =
                mock(ITeacherCareerProgressionInternalAssembler.class);
        ITeacherCategoryRepository doubleTeacherCategoryRepository = mock (ITeacherCategoryRepository.class);

        CreateTeacherCareerProgressionServiceImpl service = new CreateTeacherCareerProgressionServiceImpl
                (doubleTeacherCareerProgressionRepositoryInterface, doubleTeacherCareerProgressionFactoryInterface,
                        doubleTeacherRepositoryInterface, doubleTCPInternalAssemblerInterface, doubleTeacherCategoryRepository);

        TeacherCareerProgressionID doubleTeacherCareerProgressionId = mock(TeacherCareerProgressionID.class);

        when(doubleTeacherCareerProgressionRepositoryInterface.ofIdentity(doubleTeacherCareerProgressionId)).
                thenReturn(Optional.empty());

        // Act
        Exception exception = assertThrows(NotFoundException.class, () -> {
            service.getTeacherCareerProgressionByID(doubleTeacherCareerProgressionId);
        });

        // Assert
        assertEquals("This teacher career progression id does not exist!", exception.getMessage());
    }

}
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherCareerProgressionServiceImplV2Test {

    // testing constructor method

    @Test
    void shouldReturnConstructor() {
        //arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCareerProgressionInternalAssembler doubleTCPInternalAssemblerInterface = mock(ITeacherCareerProgressionInternalAssembler.class);

        //act
        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2
                (iTeacherCareerProgressionRepository,factory,teacherRepository, doubleTCPInternalAssemblerInterface);

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

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TeacherCareerProgressionServiceImplV2
                    (null,doubleTeacherCareerProgressionFactoryInterface,doubleTeacherRepositoryInterface,
                            doubleTCPInternalAssemblerInterface);
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

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TeacherCareerProgressionServiceImplV2
                    (doubleTeacherCareerProgressionRepositoryInterface,null,doubleTeacherRepositoryInterface,
                            doubleTCPInternalAssemblerInterface);
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

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TeacherCareerProgressionServiceImplV2
                    (doubleTeacherCareerProgressionRepositoryInterface,doubleTeacherCareerProgressionFactoryInterface,null,
                            doubleTCPInternalAssemblerInterface);
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

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TeacherCareerProgressionServiceImplV2
                    (doubleTeacherCareerProgressionRepositoryInterface,doubleTeacherCareerProgressionFactoryInterface,
                            doubleTeacherRepositoryInterface, null);
        });

        //assert
        assertEquals("Teacher Career Progression Internal Assembler Interface cannot be null.", exception.getMessage());
    }

    // testing createTeacherCareerProgression method

    @Test
    void shouldReturnAnOptionalOfTeacherCareerProgression() throws Exception{
        //arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCareerProgressionInternalAssembler doubleTCPInternalAssemblerInterface = mock(ITeacherCareerProgressionInternalAssembler.class);

        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2(iTeacherCareerProgressionRepository,
                factory,teacherRepository, doubleTCPInternalAssemblerInterface);

        Date date = mock(Date.class);
        TeacherCategoryID teacherCategoryID= mock(TeacherCategoryID.class);
        WorkingPercentage workingPercentage = mock(WorkingPercentage.class);
        TeacherID teacherID = mock(TeacherID.class);
        TeacherCareerProgression teacherCareerProgression = mock(TeacherCareerProgression.class);
        TeacherCareerProgressionID teacherCareerProgressionID = mock(TeacherCareerProgressionID.class);

        when(factory.createTeacherCareerProgression(date,teacherCategoryID,workingPercentage,teacherID)).thenReturn(teacherCareerProgression);
        when(teacherCareerProgression.getID()).thenReturn(teacherCareerProgressionID);
        when(iTeacherCareerProgressionRepository.containsOfIdentity(teacherCareerProgressionID)).thenReturn(false);
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

        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2
                (iTeacherCareerProgressionRepository,factory,teacherRepository, doubleTCPInternalAssemblerInterface);

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

        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2
                (iTeacherCareerProgressionRepository,factory,teacherRepository, doubleTCPInternalAssemblerInterface);

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

        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2
                (iTeacherCareerProgressionRepository,factory,teacherRepository, doubleTCPInternalAssemblerInterface);

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

        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2
                (iTeacherCareerProgressionRepository,factory,teacherRepository, doubleTCPInternalAssemblerInterface);

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

        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2
                (iTeacherCareerProgressionRepository,factory,teacherRepository, doubleTCPInternalAssemblerInterface);

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

        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2
                (iTeacherCareerProgressionRepository,factory,teacherRepository, doubleTCPInternalAssemblerInterface);

        Date date = mock(Date.class);
        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);
        TeacherCategoryID teacherCategoryID2 = mock(TeacherCategoryID.class);
        TeacherID teacherID = mock(TeacherID.class);
        UpdateTeacherCategoryCommand command = mock(UpdateTeacherCategoryCommand.class);
        TeacherCareerProgression previousTCP = mock(TeacherCareerProgression.class);
        WorkingPercentage wp = mock(WorkingPercentage.class);
        TeacherCareerProgression newTCP = mock(TeacherCareerProgression.class);

        when(command.date()).thenReturn(date);
        when(command.teacherCategoryID()).thenReturn(teacherCategoryID);
        when(command.teacherID()).thenReturn(teacherID);

        when(iTeacherCareerProgressionRepository.findLastTCPFromTeacherID(teacherID)).thenReturn(Optional.of(previousTCP));
        when(previousTCP.isLastDateEqualOrBeforeNewDate(date)).thenReturn(true);
        when(previousTCP.getWorkingPercentage()).thenReturn(wp);
        when(previousTCP.getTeacherCategoryID()).thenReturn(teacherCategoryID2);
        when(factory.createTeacherCareerProgression(date, teacherCategoryID, wp, teacherID)).thenReturn(newTCP);

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

        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2
                (iTeacherCareerProgressionRepository,factory,teacherRepository,doubleTCPInternalAssemblerInterface);

        when(iTeacherCareerProgressionRepository.findLastTCPFromTeacherID(any())).thenReturn(Optional.empty());
        UpdateTeacherCategoryCommand command = mock(UpdateTeacherCategoryCommand.class);

        // Act
        Exception exception = assertThrows(NotFoundException.class, () -> {
            service.updateTeacherCategory(command);
        });

        // Assert
        assertEquals("This teacher has no previous career progression record. Please create one before attempting an update.", exception.getMessage());
    }

    @Test
    void shouldReturnAnExceptionWhenDateNotAfterLast() {
        //arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCareerProgressionInternalAssembler doubleTCPInternalAssemblerInterface = mock(ITeacherCareerProgressionInternalAssembler.class);

        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2
                (iTeacherCareerProgressionRepository,factory,teacherRepository, doubleTCPInternalAssemblerInterface);

        TeacherCareerProgression tcp = mock(TeacherCareerProgression.class);

        UpdateTeacherCategoryCommand command = mock(UpdateTeacherCategoryCommand.class);

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

        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2
                (iTeacherCareerProgressionRepository,factory,teacherRepository, doubleTCPInternalAssemblerInterface);

        Date date = mock(Date.class);
        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);
        TeacherID teacherID = mock(TeacherID.class);
        UpdateTeacherCategoryCommand command = mock(UpdateTeacherCategoryCommand.class);
        TeacherCareerProgression previousTCP = mock(TeacherCareerProgression.class);
        WorkingPercentage wp = mock(WorkingPercentage.class);
        TeacherCareerProgression newTCP = mock(TeacherCareerProgression.class);

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

        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2
                (repo, factory, teacherRepo, doubleTCPInternalAssemblerInterface);

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

        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2
                (repo, factory, teacherRepo, doubleTCPInternalAssemblerInterface);

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

        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2
                (repo, factory, teacherRepo, doubleTCPInternalAssemblerInterface);

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

        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2
                (repo, factory, teacherRepo, doubleTCPInternalAssemblerInterface);

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

        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2
                (repo, factory, teacherRepo, doubleTCPInternalAssemblerInterface);

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

}
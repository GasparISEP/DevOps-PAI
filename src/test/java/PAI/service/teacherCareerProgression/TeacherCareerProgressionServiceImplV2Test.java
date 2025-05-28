package PAI.service.teacherCareerProgression;

import PAI.VOs.*;
import PAI.domain.repositoryInterfaces.teacher.ITeacherRepository;
import PAI.domain.repositoryInterfaces.teacherCareerProgression.ITeacherCareerProgressionRepository;
import PAI.domain.repositoryInterfaces.teacherCategory.ITeacherCategoryRepository;
import PAI.domain.teacherCareerProgression.ITeacherCareerProgressionFactory;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import PAI.dto.teacherCareerProgression.ITeacherCareerProgressionAssembler;
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryCommand;
import PAI.dto.teacherCareerProgression.UpdateTeacherWorkingPercentageCommand;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherCareerProgressionServiceImplV2Test {
    @Test
    void shouldReturnConstructor() {
        //arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepository = mock(ITeacherCategoryRepository.class);
        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2(iTeacherCareerProgressionRepository,factory,teacherRepository,teacherCategoryRepository);
        //assert
        assertNotNull(service);
    }

    @Test
    void shouldReturnAnOptionalOfTeacherCareerProgression() throws Exception{
        //arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepository = mock(ITeacherCategoryRepository.class);
        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2(iTeacherCareerProgressionRepository,factory,teacherRepository,teacherCategoryRepository);

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
    void shouldReturnAnExceptionWhenDateIsNull() throws Exception {
        //arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepository = mock(ITeacherCategoryRepository.class);
        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2(iTeacherCareerProgressionRepository,factory,teacherRepository,teacherCategoryRepository);

        Date date = null;
        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);
        WorkingPercentage workingPercentage = mock(WorkingPercentage.class);
        TeacherID teacherID = mock(TeacherID.class);
        //assert
        assertThrows(Exception.class, ()-> service.createTeacherCareerProgression(date,teacherCategoryID,workingPercentage,teacherID));
    }

    @Test
    void shouldReturnAnExceptionWhenTeacherIDIsNull() throws Exception {
        //arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepository = mock(ITeacherCategoryRepository.class);
        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2(iTeacherCareerProgressionRepository,factory,teacherRepository,teacherCategoryRepository);

        Date date = mock(Date.class);
        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);
        WorkingPercentage workingPercentage = mock(WorkingPercentage.class);
        TeacherID teacherID = null;
        //assert
        assertThrows(Exception.class, ()-> service.createTeacherCareerProgression(date,teacherCategoryID,workingPercentage,teacherID));
    }

    @Test
    void shouldReturnAnExceptionWhenTeacherCategoryIDIsNull() throws Exception {
        //arrange
        //arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepository = mock(ITeacherCategoryRepository.class);
        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2(iTeacherCareerProgressionRepository,factory,teacherRepository,teacherCategoryRepository);

        Date date = mock(Date.class);
        TeacherCategoryID teacherCategoryID = null;
        WorkingPercentage workingPercentage = mock(WorkingPercentage.class);
        TeacherID teacherID = mock(TeacherID.class);
        //assert
        assertThrows(Exception.class, ()-> service.createTeacherCareerProgression(date,teacherCategoryID,workingPercentage,teacherID));
    }
//
    @Test
    void shouldReturnAnExceptionWhenWorkingPercentageIsNull() throws Exception {
        //arrange
        //arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepository = mock(ITeacherCategoryRepository.class);
        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2(iTeacherCareerProgressionRepository,factory,teacherRepository,teacherCategoryRepository);

        Date date = mock(Date.class);
        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);
        WorkingPercentage workingPercentage = null;
        TeacherID teacherID = mock(TeacherID.class);
        //assert
        assertThrows(Exception.class, ()-> service.createTeacherCareerProgression(date,teacherCategoryID,workingPercentage,teacherID));
    }
//
    @Test
    void shouldReturnAnOptionalEmptyWhenTeacherAlreadyHaveThatCategory() throws Exception{
        //arrange
        //arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepository = mock(ITeacherCategoryRepository.class);
        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2(iTeacherCareerProgressionRepository,factory,teacherRepository,teacherCategoryRepository);

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
    void shouldReturnOptionalOfTeacherCareerProgression() throws Exception {
        //arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepository = mock(ITeacherCategoryRepository.class);
        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2(iTeacherCareerProgressionRepository,factory,teacherRepository,teacherCategoryRepository);

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
        when(teacherRepository.containsOfIdentity(teacherID)).thenReturn(true);
        when(teacherCategoryRepository.containsOfIdentity(teacherCategoryID)).thenReturn(true);
        when(iTeacherCareerProgressionRepository.findLastTCPFromTeacherID(teacherID)).thenReturn(Optional.of(previousTCP));
        when(previousTCP.isLastDateEqualOrBeforeNewDate(date)).thenReturn(true);
        when(previousTCP.getWorkingPercentage()).thenReturn(wp);
        when(previousTCP.getTeacherCategoryID()).thenReturn(teacherCategoryID2);
        when(factory.createTeacherCareerProgression(date, teacherCategoryID, wp, teacherID)).thenReturn(newTCP);

        // Act
        Optional<TeacherCareerProgression> result = service.updateTeacherCategoryInTeacherCareerProgression(command);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(newTCP, result.get());
    }



    @Test
    void shouldReturnEmptyWhenTeacherNotFound() throws Exception {

        //arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepository = mock(ITeacherCategoryRepository.class);
        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2(iTeacherCareerProgressionRepository,factory,teacherRepository,teacherCategoryRepository);
        UpdateTeacherCategoryCommand command = mock(UpdateTeacherCategoryCommand.class);
        when(teacherRepository.containsOfIdentity(any())).thenReturn(false);

        // Act
        Optional<TeacherCareerProgression> result = service.updateTeacherCategoryInTeacherCareerProgression(command);
        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyWhenCategoryNotFound() throws Exception {
        //arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepository = mock(ITeacherCategoryRepository.class);
        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2(iTeacherCareerProgressionRepository,factory,teacherRepository,teacherCategoryRepository);
        UpdateTeacherCategoryCommand command = mock(UpdateTeacherCategoryCommand.class);

        when(teacherRepository.containsOfIdentity(any())).thenReturn(true);
        when(teacherCategoryRepository.containsOfIdentity(any())).thenReturn(false);

        // Act
        Optional<TeacherCareerProgression> result = service.updateTeacherCategoryInTeacherCareerProgression(command);
        // Assert
        assertTrue(result.isEmpty());
    }
    @Test
    void shouldReturnEmptyWhenNoPreviousTCP() throws Exception {
        //arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepository = mock(ITeacherCategoryRepository.class);
        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2(iTeacherCareerProgressionRepository,factory,teacherRepository,teacherCategoryRepository);

        when(teacherRepository.containsOfIdentity(any())).thenReturn(true);
        when(teacherCategoryRepository.containsOfIdentity(any())).thenReturn(true);
        when(iTeacherCareerProgressionRepository.findLastTCPFromTeacherID(any())).thenReturn(Optional.empty());
        UpdateTeacherCategoryCommand command = mock(UpdateTeacherCategoryCommand.class);

        // Act
        Optional<TeacherCareerProgression> result = service.updateTeacherCategoryInTeacherCareerProgression(command);
        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyWhenDateNotAfterLast() throws Exception {
        //arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepository = mock(ITeacherCategoryRepository.class);
        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2(iTeacherCareerProgressionRepository,factory,teacherRepository,teacherCategoryRepository);

        TeacherCareerProgression tcp = mock(TeacherCareerProgression.class);
        Date date = mock(Date.class);
        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);
        TeacherID teacherID = mock(TeacherID.class);
        UpdateTeacherCategoryCommand command = mock(UpdateTeacherCategoryCommand.class);

        when(iTeacherCareerProgressionRepository.findLastTCPFromTeacherID(any())).thenReturn(Optional.of(tcp));
        when(tcp.isLastDateEqualOrBeforeNewDate(any())).thenReturn(false);

        when(teacherRepository.containsOfIdentity(any())).thenReturn(true);
        when(teacherCategoryRepository.containsOfIdentity(any())).thenReturn(true);

        // Act
        Optional<TeacherCareerProgression> result = service.updateTeacherCategoryInTeacherCareerProgression(command);
        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyWhenCategoryIsSame() throws Exception {
        //arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepository = mock(ITeacherCategoryRepository.class);
        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2(iTeacherCareerProgressionRepository,factory,teacherRepository,teacherCategoryRepository);

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
        when(teacherRepository.containsOfIdentity(teacherID)).thenReturn(true);
        when(teacherCategoryRepository.containsOfIdentity(teacherCategoryID)).thenReturn(true);
        when(iTeacherCareerProgressionRepository.findLastTCPFromTeacherID(teacherID)).thenReturn(Optional.of(previousTCP));
        when(previousTCP.isLastDateEqualOrBeforeNewDate(date)).thenReturn(true);
        when(previousTCP.getWorkingPercentage()).thenReturn(wp);
        when(previousTCP.getTeacherCategoryID()).thenReturn(teacherCategoryID);
        when(factory.createTeacherCareerProgression(date, teacherCategoryID, wp, teacherID)).thenReturn(newTCP);

        // Act
        Optional<TeacherCareerProgression> result = service.updateTeacherCategoryInTeacherCareerProgression(command);
        // Assert
        assertTrue(result.isEmpty());
    }


    @Test
    void shouldReturnOptionalOfTeacherCareerProgressionWhenWorkingPercentageChanges() throws Exception {
        ITeacherCareerProgressionRepository repo = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepo = mock(ITeacherRepository.class);
        ITeacherCategoryRepository categoryRepo = mock(ITeacherCategoryRepository.class);
        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2(repo, factory, teacherRepo, categoryRepo);

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

        Optional<TeacherCareerProgression> result = service.updateTeacherWorkingPercentageInTeacherCareerProgression(command);

        assertTrue(result.isPresent());
        assertEquals(newTCP, result.get());
    }

    @Test
    void shouldReturnEmptyWhenTeacherNotFoundForWorkingPercentage() throws Exception {
        ITeacherCareerProgressionRepository repo = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepo = mock(ITeacherRepository.class);
        ITeacherCategoryRepository categoryRepo = mock(ITeacherCategoryRepository.class);
        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2(repo, factory, teacherRepo, categoryRepo);

        UpdateTeacherWorkingPercentageCommand command = mock(UpdateTeacherWorkingPercentageCommand.class);
        when(teacherRepo.containsOfIdentity(any())).thenReturn(false);

        Optional<TeacherCareerProgression> result = service.updateTeacherWorkingPercentageInTeacherCareerProgression(command);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyWhenNoPreviousTCPForWorkingPercentage() throws Exception {
        ITeacherCareerProgressionRepository repo = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepo = mock(ITeacherRepository.class);
        ITeacherCategoryRepository categoryRepo = mock(ITeacherCategoryRepository.class);
        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2(repo, factory, teacherRepo, categoryRepo);

        UpdateTeacherWorkingPercentageCommand command = mock(UpdateTeacherWorkingPercentageCommand.class);
        when(teacherRepo.containsOfIdentity(any())).thenReturn(true);
        when(repo.findLastTCPFromTeacherID(any())).thenReturn(Optional.empty());

        Optional<TeacherCareerProgression> result = service.updateTeacherWorkingPercentageInTeacherCareerProgression(command);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyWhenDateNotAfterLastForWorkingPercentage() throws Exception {
        ITeacherCareerProgressionRepository repo = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepo = mock(ITeacherRepository.class);
        ITeacherCategoryRepository categoryRepo = mock(ITeacherCategoryRepository.class);
        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2(repo, factory, teacherRepo, categoryRepo);

        UpdateTeacherWorkingPercentageCommand command = mock(UpdateTeacherWorkingPercentageCommand.class);
        TeacherID teacherID = mock(TeacherID.class);
        TeacherCareerProgression lastTCP = mock(TeacherCareerProgression.class);

        when(command.teacherID()).thenReturn(teacherID);
        when(teacherRepo.containsOfIdentity(teacherID)).thenReturn(true);
        when(repo.findLastTCPFromTeacherID(teacherID)).thenReturn(Optional.of(lastTCP));
        when(lastTCP.isLastDateEqualOrBeforeNewDate(any())).thenReturn(false);

        Optional<TeacherCareerProgression> result = service.updateTeacherWorkingPercentageInTeacherCareerProgression(command);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyWhenWorkingPercentageIsSame() throws Exception {
        ITeacherCareerProgressionRepository repo = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepo = mock(ITeacherRepository.class);
        ITeacherCategoryRepository categoryRepo = mock(ITeacherCategoryRepository.class);
        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2(repo, factory, teacherRepo, categoryRepo);

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

        Optional<TeacherCareerProgression> result = service.updateTeacherWorkingPercentageInTeacherCareerProgression(command);

        assertTrue(result.isEmpty());
    }

}
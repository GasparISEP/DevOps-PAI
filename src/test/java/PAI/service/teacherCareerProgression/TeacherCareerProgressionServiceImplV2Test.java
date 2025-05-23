package PAI.service.teacherCareerProgression;

import PAI.VOs.*;
import PAI.domain.repositoryInterfaces.teacher.ITeacherRepository;
import PAI.domain.repositoryInterfaces.teacherCareerProgression.ITeacherCareerProgressionRepository;
import PAI.domain.repositoryInterfaces.teacherCategory.ITeacherCategoryRepository;
import PAI.domain.teacherCareerProgression.ITeacherCareerProgressionFactory;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import PAI.dto.teacherCareerProgression.ITeacherCareerProgressionAssembler;
import PAI.dto.teacherCareerProgression.TeacherCategoryUpdateResponseDTO;
import PAI.dto.teacherCareerProgression.TeacherWorkingPercentageUpdateDTO;
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
        ITeacherCareerProgressionAssembler assembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2(iTeacherCareerProgressionRepository,factory,teacherRepository,teacherCategoryRepository,assembler);
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
        ITeacherCareerProgressionAssembler assembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2(iTeacherCareerProgressionRepository,factory,teacherRepository,teacherCategoryRepository,assembler);

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
        ITeacherCareerProgressionAssembler assembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2(iTeacherCareerProgressionRepository,factory,teacherRepository,teacherCategoryRepository,assembler);

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
        ITeacherCareerProgressionAssembler assembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2(iTeacherCareerProgressionRepository,factory,teacherRepository,teacherCategoryRepository,assembler);

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
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepository = mock(ITeacherCategoryRepository.class);
        ITeacherCareerProgressionAssembler assembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2(iTeacherCareerProgressionRepository,factory,teacherRepository,teacherCategoryRepository,assembler);

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
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepository = mock(ITeacherCategoryRepository.class);
        ITeacherCareerProgressionAssembler assembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2(iTeacherCareerProgressionRepository,factory,teacherRepository,teacherCategoryRepository,assembler);

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
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepository = mock(ITeacherCategoryRepository.class);
        ITeacherCareerProgressionAssembler assembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2(iTeacherCareerProgressionRepository,factory,teacherRepository,teacherCategoryRepository,assembler);

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
    void shouldReturnOptionalOfTeacherCareerProgressionDTO() throws Exception {
        // Arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepository = mock(ITeacherCategoryRepository.class);
        ITeacherCareerProgressionAssembler assembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2(iTeacherCareerProgressionRepository,factory,teacherRepository,teacherCategoryRepository,assembler);

        Date date = mock(Date.class);
        TeacherCategoryID teacherCategoryID= mock(TeacherCategoryID.class);
        TeacherID teacherID = mock(TeacherID.class);
        TeacherCareerProgression previousTCP = mock(TeacherCareerProgression.class);
        WorkingPercentage wp = mock(WorkingPercentage.class);
        TeacherCareerProgression newTCP = mock(TeacherCareerProgression.class);
        TeacherCategoryUpdateResponseDTO teacherCategoryUpdateResponseDTO = mock(TeacherCategoryUpdateResponseDTO.class);



        when(teacherRepository.containsOfIdentity(teacherID)).thenReturn(true);
        when(teacherCategoryRepository.containsOfIdentity(teacherCategoryID)).thenReturn(true);
        when(iTeacherCareerProgressionRepository.findLastTCPFromTeacherID(teacherID)).thenReturn(Optional.of(previousTCP));
        when(previousTCP.isLastDateEqualOrBeforeNewDate(date)).thenReturn(false);
        when(previousTCP.getWorkingPercentage()).thenReturn(wp);
        when(previousTCP.getTeacherCategoryID()).thenReturn(mock(TeacherCategoryID.class));
        when(factory.createTeacherCareerProgression(date, teacherCategoryID, wp, teacherID)).thenReturn(newTCP);
        when(assembler.UpdateCategoryToDTO(newTCP)).thenReturn(teacherCategoryUpdateResponseDTO);

        // Act
        Optional<TeacherCategoryUpdateResponseDTO> result = service.updateTeacherCategoryInTeacherCareerProgression(date, teacherCategoryID, teacherID);
        // Assert
        assertTrue(result.isPresent());
        assertEquals(teacherCategoryUpdateResponseDTO, result.get());
    }


    @Test
    void shouldReturnEmptyWhenTeacherNotFound() throws Exception {

        // Arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepository = mock(ITeacherCategoryRepository.class);
        ITeacherCareerProgressionAssembler assembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2(iTeacherCareerProgressionRepository,factory,teacherRepository,teacherCategoryRepository,assembler);
        Date date = mock(Date.class);
        TeacherCategoryID teacherCategoryID= mock(TeacherCategoryID.class);
        TeacherID teacherID = mock(TeacherID.class);


        when(teacherRepository.containsOfIdentity(any())).thenReturn(false);

        // Act
        Optional<TeacherCategoryUpdateResponseDTO> result = service.updateTeacherCategoryInTeacherCareerProgression(date,teacherCategoryID,teacherID);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyWhenCategoryNotFound() throws Exception {
        // Arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepository = mock(ITeacherCategoryRepository.class);
        ITeacherCareerProgressionAssembler assembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2(iTeacherCareerProgressionRepository,factory,teacherRepository,teacherCategoryRepository,assembler);

        Date date = mock(Date.class);
        TeacherCategoryID teacherCategoryID= mock(TeacherCategoryID.class);
        TeacherID teacherID = mock(TeacherID.class);

        when(teacherRepository.containsOfIdentity(any())).thenReturn(true);
        when(teacherCategoryRepository.containsOfIdentity(any())).thenReturn(false);

        // Act
        Optional<TeacherCategoryUpdateResponseDTO> result = service.updateTeacherCategoryInTeacherCareerProgression(date,teacherCategoryID,teacherID);

        // Assert
        assertTrue(result.isEmpty());
    }
    @Test
    void shouldReturnEmptyWhenNoPreviousTCP() throws Exception {
        // Arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepository = mock(ITeacherCategoryRepository.class);
        ITeacherCareerProgressionAssembler assembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2(iTeacherCareerProgressionRepository,factory,teacherRepository,teacherCategoryRepository,assembler);
        TeacherCategoryUpdateResponseDTO teacherCategoryUpdateResponseDTO = mock(TeacherCategoryUpdateResponseDTO.class);

        when(teacherRepository.containsOfIdentity(any())).thenReturn(true);
        when(teacherCategoryRepository.containsOfIdentity(any())).thenReturn(true);
        when(iTeacherCareerProgressionRepository.findLastTCPFromTeacherID(any())).thenReturn(Optional.empty());

        Date date = mock(Date.class);
        TeacherCategoryID teacherCategoryID= mock(TeacherCategoryID.class);
        TeacherID teacherID = mock(TeacherID.class);

        // Act
        Optional<TeacherCategoryUpdateResponseDTO> result = service.updateTeacherCategoryInTeacherCareerProgression(date, teacherCategoryID, teacherID);

        // Assert
        assertTrue(result.isEmpty());
    }
    @Test
    void shouldReturnEmptyWhenDateNotAfterLast() throws Exception {
        // Arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepository = mock(ITeacherCategoryRepository.class);
        ITeacherCareerProgressionAssembler assembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2(iTeacherCareerProgressionRepository,factory,teacherRepository,teacherCategoryRepository,assembler);
        TeacherCategoryUpdateResponseDTO teacherCategoryUpdateResponseDTO = mock(TeacherCategoryUpdateResponseDTO.class);

        TeacherCareerProgression tcp = mock(TeacherCareerProgression.class);
        Date date = mock(Date.class);
        TeacherCategoryID teacherCategoryID= mock(TeacherCategoryID.class);
        TeacherID teacherID = mock(TeacherID.class);
        when(tcp.isLastDateEqualOrBeforeNewDate(any())).thenReturn(true);

        when(teacherRepository.containsOfIdentity(any())).thenReturn(true);
        when(teacherCategoryRepository.containsOfIdentity(any())).thenReturn(true);
        when(iTeacherCareerProgressionRepository.findLastTCPFromTeacherID(any())).thenReturn(Optional.of(tcp));

        // Act
        Optional<TeacherCategoryUpdateResponseDTO> result = service.updateTeacherCategoryInTeacherCareerProgression(date,teacherCategoryID,teacherID);

        // Assert
        assertTrue(result.isEmpty());
    }
    @Test
    void shouldReturnEmptyWhenCategoryIsSame() throws Exception {
        // Arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepository = mock(ITeacherCategoryRepository.class);
        ITeacherCareerProgressionAssembler assembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2(iTeacherCareerProgressionRepository,factory,teacherRepository,teacherCategoryRepository,assembler);
        TeacherCategoryUpdateResponseDTO teacherCategoryUpdateResponseDTO = mock(TeacherCategoryUpdateResponseDTO.class);

        Date date = mock(Date.class);
        TeacherCategoryID teacherCategoryID= mock(TeacherCategoryID.class);
        TeacherID teacherID = mock(TeacherID.class);
        TeacherCareerProgression tcp = mock(TeacherCareerProgression.class);

        when(tcp.isLastDateEqualOrBeforeNewDate(any())).thenReturn(false);
        when(tcp.getTeacherCategoryID()).thenReturn(teacherCategoryID);

        when(teacherRepository.containsOfIdentity(any())).thenReturn(true);
        when(teacherCategoryRepository.containsOfIdentity(any())).thenReturn(true);
        when(iTeacherCareerProgressionRepository.findLastTCPFromTeacherID(any())).thenReturn(Optional.of(tcp));

        // Act
        Optional<TeacherCategoryUpdateResponseDTO> result = service.updateTeacherCategoryInTeacherCareerProgression(date, teacherCategoryID, teacherID);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnOptionalOfTeacherWorkingPercentageDTO () throws Exception {
        // Arrange
        ITeacherCareerProgressionRepository iTeacherCareerProgressionRepository = mock(ITeacherCareerProgressionRepository.class);
        ITeacherCareerProgressionFactory factory = mock(ITeacherCareerProgressionFactory.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepository = mock(ITeacherCategoryRepository.class);
        ITeacherCareerProgressionAssembler assembler = mock(ITeacherCareerProgressionAssembler.class);

        TeacherCareerProgressionServiceImplV2 service = new TeacherCareerProgressionServiceImplV2(
                iTeacherCareerProgressionRepository, factory, teacherRepository, teacherCategoryRepository, assembler);

        Date date =mock(Date.class);
        TeacherID teacherID = mock(TeacherID.class);
        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);
        TeacherCareerProgression previousTCP = mock(TeacherCareerProgression.class);
        TeacherCareerProgression newTCP = mock(TeacherCareerProgression.class);
        TeacherWorkingPercentageUpdateDTO dto = mock(TeacherWorkingPercentageUpdateDTO.class);

        WorkingPercentage oldWP = new WorkingPercentage(50);
        WorkingPercentage newWP = new WorkingPercentage(75);

        when(teacherRepository.containsOfIdentity(teacherID)).thenReturn(true);
        when(teacherCategoryRepository.containsOfIdentity(teacherCategoryID)).thenReturn(true);
        when(iTeacherCareerProgressionRepository.findLastTCPFromTeacherID(teacherID)).thenReturn(Optional.of(previousTCP));
        when(previousTCP.isLastDateEqualOrBeforeNewDate(date)).thenReturn(true);
        when(previousTCP.getWorkingPercentage()).thenReturn(oldWP);
        when(previousTCP.getTeacherCategoryID()).thenReturn(teacherCategoryID);
        when(factory.createTeacherCareerProgression(date, teacherCategoryID, newWP, teacherID)).thenReturn(newTCP);
        when(assembler.toTeacherWorkingPercentageUpdateDTO(newTCP)).thenReturn(dto);

        // Act
        Optional<TeacherWorkingPercentageUpdateDTO> result = service.updateTeacherWorkingPercentageInTeacherCareerProgression(date, newWP, teacherID);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(dto, result.get());
    }

}
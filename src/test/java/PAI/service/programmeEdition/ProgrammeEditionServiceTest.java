package PAI.service.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEdition.IProgrammeEditionFactory;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.domain.programmeEdition.ProgrammeEditionFactoryImpl;
import PAI.repository.programmeEditionRepository.IProgrammeEditionRepository;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEditionServiceTest {
    @Test
    void shouldCreateProgrammeEditionService() {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        //act
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository);
        //assert
        assertNotNull(programmeEditionService);
    }

    @Test
    void nullProgrammeEditionFactoryThrowsException() {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = null;
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository));
    }

    @Test
    void nullProgrammeEditionRepositoryThrowsException() {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = null;
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository));
    }

    @Test
    void shouldCreateProgrammeEdition() throws Exception {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);

        when(programmeEditionFactory.createProgrammeEdition(programmeID, schoolYearID)).thenReturn(mock(ProgrammeEdition.class));
        //act
        ProgrammeEdition programmeEdition = programmeEditionService.createProgrammeEdition(programmeID, schoolYearID);
        //assert
        assertNotNull(programmeEdition);
    }

    @Test
    void shouldThrowExceptionWhenProgrammeIdIsNullAtProgrammeEditionCreationIsolationTest() throws Exception {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository);

        ProgrammeID programmeID = null;
        SchoolYearID schoolYearID = mock(SchoolYearID.class);

        when(programmeEditionFactory.createProgrammeEdition(programmeID, schoolYearID)).thenThrow(IllegalArgumentException.class);
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> programmeEditionService.createProgrammeEdition(programmeID, schoolYearID));
    }

    @Test
    void shouldThrowExceptionWhenProgrammeIdIsNullAtProgrammeEditionCreation() {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository);

        ProgrammeID programmeID = null;
        SchoolYearID schoolYearID = mock(SchoolYearID.class);

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> programmeEditionService.createProgrammeEdition(programmeID, schoolYearID));
    }

    @Test
    void shouldSaveProgrammeEdition() throws Exception {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository);

        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        when(programmeEditionRepository.containsOfIdentity(programmeEditionID)).thenReturn(false);

        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        when(programmeEditionRepository.save(programmeEdition)).thenReturn(programmeEdition);
        //act
        Optional<ProgrammeEdition> programmeEditionSaved = programmeEditionService.saveProgrammeEdition(programmeEdition);
        //assert
        assertTrue(programmeEditionSaved.isPresent());
    }

    @Test
    void shouldReturnOptionalEmptyWhenProgrammeEditionIsNull() throws Exception {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository);

        ProgrammeEdition programmeEdition = null;
        //act
        Optional<ProgrammeEdition> programmeEditionSaved = programmeEditionService.saveProgrammeEdition(programmeEdition);
        //assert
        assertFalse(programmeEditionSaved.isPresent());
        assertEquals(Optional.empty(), programmeEditionSaved);
    }

    @Test
    void shouldReturnOptionalEmptyWhenProgrammeEditionIsAlreadyRegistered() throws Exception {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository);

        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        when(programmeEditionRepository.containsOfIdentity(programmeEdition.identity())).thenReturn(true);

        //act
        Optional<ProgrammeEdition> programmeEditionSaved = programmeEditionService.saveProgrammeEdition(programmeEdition);
        //assert
        assertFalse(programmeEditionSaved.isPresent());
        assertEquals(Optional.empty(), programmeEditionSaved);
    }

    @Test
    void shouldReturnOptionalEmptyWhenProgrammeEditionSavedIsNull() throws Exception {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository);

        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        when(programmeEditionRepository.containsOfIdentity(programmeEditionID)).thenReturn(false);

        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        when(programmeEditionRepository.save(programmeEdition)).thenReturn(null);
        //act
        Optional<ProgrammeEdition> programmeEditionSaved = programmeEditionService.saveProgrammeEdition(programmeEdition);
        //assert
        assertFalse(programmeEditionSaved.isPresent());
        assertEquals(Optional.empty(), programmeEditionSaved);
    }

    @Test
    void shouldReturnEmptyListWhenProgrammeIDIsNull() throws Exception {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository);
        ProgrammeID programmeID = null;
        //act
        List<ProgrammeEdition> result = programmeEditionService.getProgrammeEditionsByProgrammeID(programmeID);
        //assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnListWithProgrammeEditions() throws Exception {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository);

        ProgrammeID programmeID1 = mock(ProgrammeID.class);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        ProgrammeEdition programmeEdition1 = mock(ProgrammeEdition.class);
        ProgrammeEdition programmeEdition2 = mock(ProgrammeEdition.class);

        when(programmeEditionRepository.getProgrammeEditionsByProgrammeID(programmeID))
                .thenReturn(List.of(programmeEdition1, programmeEdition2));
        //act
        List<ProgrammeEdition> result = programmeEditionService.getProgrammeEditionsByProgrammeID(programmeID);

        // assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(programmeEdition1));
        assertTrue(result.contains(programmeEdition2));
    }
}
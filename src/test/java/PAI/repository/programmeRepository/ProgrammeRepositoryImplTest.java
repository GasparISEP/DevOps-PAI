package PAI.repository.programmeRepository;

import PAI.VOs.*;
import PAI.factory.IProgrammeFactory;
import PAI.domain.programme.Programme;
import PAI.persistence.mem.programmeEdition.IProgrammeRepositoryListFactory;
import PAI.persistence.mem.programmeEdition.ProgrammeRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgrammeRepositoryImplTest {

    @Test
    void testSaveAddsProgramme() {
        Programme programme1 = mock(Programme.class);

        IProgrammeFactory factory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory listFactory = mock(IProgrammeRepositoryListFactory.class);
        List<Programme> ProgrammeList = new ArrayList<>();
        when(listFactory.newProgrammeArrayList()).thenReturn(ProgrammeList);

        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(factory, listFactory);
        ProgrammeID id = mock(ProgrammeID.class);
        when(programme1.identity()).thenReturn(id);

        Programme saved = repository.save(programme1);

        assertEquals(programme1, saved);
        assertTrue(repository.containsOfIdentity(id));
    }

    @Test
    void testFindAllReturnsAllProgrammes() {
        Programme programme1 = mock(Programme.class);
        Programme programme2 = mock(Programme.class);

        IProgrammeFactory factory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory listFactory = mock(IProgrammeRepositoryListFactory.class);
        List<Programme> programmeList = new ArrayList<>();
        when(listFactory.newProgrammeArrayList()).thenReturn(programmeList);

        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(factory, listFactory);

        repository.save(programme1);
        repository.save(programme2);

        //act
        List<Programme> all = (List<Programme>) repository.findAll();

        //assert
        assertEquals(2, all.size());
        assertTrue(all.contains(programme1) && all.contains(programme2));
    }

    @Test
    void testOfIdentityReturnsCorrectPlan() {
        Programme programme1 = mock(Programme.class);


        IProgrammeFactory factory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory listFactory = mock(IProgrammeRepositoryListFactory.class);
        List<Programme> programmeList = new ArrayList<>();
        when(listFactory.newProgrammeArrayList()).thenReturn(programmeList);

        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(factory, listFactory);
        ProgrammeID id = mock(ProgrammeID.class);
        when(programme1.identity()).thenReturn(id);

        repository.save(programme1);

        Optional<Programme> found = repository.ofIdentity(id);

        assertTrue(found.isPresent());
        assertEquals(programme1, found.get());
    }

    @Test
    void testOfIdentityReturnsEmptyWhenNotFound() {

        IProgrammeFactory factory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory listFactory = mock(IProgrammeRepositoryListFactory.class);
        List<Programme> programmeList = new ArrayList<>();
        when(listFactory.newProgrammeArrayList()).thenReturn(programmeList);
        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(factory, listFactory);
        ProgrammeID id = mock(ProgrammeID.class);

        Optional<Programme> found = repository.ofIdentity(id);

        assertFalse(found.isPresent());
    }

    @Test
    void testContainsOfIdentityReturnsTrueWhenExists() {
        Programme programme1 = mock(Programme.class);

        IProgrammeFactory factory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory listFactory = mock(IProgrammeRepositoryListFactory.class);
        List<Programme> programmeList = new ArrayList<>();
        when(listFactory.newProgrammeArrayList()).thenReturn(programmeList);

        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(factory, listFactory);
        ProgrammeID id = mock(ProgrammeID.class);
        when(programme1.identity()).thenReturn(id);

        repository.save(programme1);

        assertTrue(repository.containsOfIdentity(id));
    }

    @Test
    void testContainsOfIdentityReturnsFalseWhenNotExists() {

        IProgrammeFactory factory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory listFactory = mock(IProgrammeRepositoryListFactory.class);
        List<Programme> programmeList = new ArrayList<>();
        when(listFactory.newProgrammeArrayList()).thenReturn(programmeList);
        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(factory, listFactory);
        ProgrammeID id = mock(ProgrammeID.class);

        boolean result = repository.containsOfIdentity(id);

        assertFalse(result);
    }
}
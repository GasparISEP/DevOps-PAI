package PAI.persistence.springdata;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.degreeType.DegreeType;
import PAI.mapper.DegreeTypeMapper;
import PAI.persistence.datamodel.DegreeTypeDM;
import PAI.persistence.springdata.DegreeTypeRepoSpringData;
import PAI.persistence.springdata.IDegreeTypeRepoSpringData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DegreeTypeRepoSpringDataTest {

    private IDegreeTypeRepoSpringData repoJPA;
    private DegreeTypeMapper mapper;
    private DegreeTypeRepoSpringData repo;

    private final DegreeTypeID id = new DegreeTypeID(UUID.randomUUID().toString());
    private final Name name = new Name("Licenciatura");
    private final MaxEcts maxEcts = new MaxEcts(180);
    private final DegreeType domain = new DegreeType(id, name, maxEcts);
    private final DegreeTypeDM dm = new DegreeTypeDM(id.getDTID(), name.getName(), maxEcts.getMaxEcts());

    @BeforeEach
    void setUp() {
        repoJPA = mock(IDegreeTypeRepoSpringData.class);
        mapper = mock(DegreeTypeMapper.class);
        repo = new DegreeTypeRepoSpringData(repoJPA, mapper);
    }

    @Test
    void testOfIdentity_WhenExists() {
        when(repoJPA.findById(id.getDTID())).thenReturn(Optional.of(dm));
        when(mapper.toDomainModel(dm)).thenReturn(domain);

        Optional<DegreeType> result = repo.ofIdentity(id);

        assertTrue(result.isPresent());
        assertEquals(domain, result.get());
    }

    @Test
    void testOfIdentity_WhenNotExists() {
        when(repoJPA.findById(id.getDTID())).thenReturn(Optional.empty());

        Optional<DegreeType> result = repo.ofIdentity(id);

        assertTrue(result.isEmpty());
    }

    @Test
    void testContainsOfIdentity_WhenExists() {
        when(repoJPA.existsById(id.getDTID())).thenReturn(true);

        assertTrue(repo.containsOfIdentity(id));
    }

    @Test
    void testContainsOfIdentity_WhenNotExists() {
        when(repoJPA.existsById(id.getDTID())).thenReturn(false);

        assertFalse(repo.containsOfIdentity(id));
    }

    @Test
    void testSave() {
        when(mapper.toDataModel(domain)).thenReturn(dm);
        when(repoJPA.save(dm)).thenReturn(dm);
        when(mapper.toDomainModel(dm)).thenReturn(domain);

        DegreeType result = repo.save(domain);

        assertEquals(domain, result);
    }

    @Test
    void testFindAll() {
        List<DegreeTypeDM> dms = List.of(dm);
        when(repoJPA.findAll()).thenReturn(dms);
        when(mapper.toDomainModel(dm)).thenReturn(domain);

        List<DegreeType> result = repo.findAll();

        assertEquals(1, result.size());
        assertEquals(domain, result.get(0));
    }

    @Test
    void testRegisterDegreeType_WhenNotExists() throws Exception {
        when(repoJPA.existsById(id.getDTID())).thenReturn(false);

        boolean result = repo.registerDegreeType(id, name, maxEcts);

        assertTrue(result);
        verify(repoJPA).save(any(DegreeTypeDM.class));
    }

    @Test
    void testRegisterDegreeType_WhenExists() throws Exception {
        when(repoJPA.existsById(id.getDTID())).thenReturn(true);

        boolean result = repo.registerDegreeType(id, name, maxEcts);

        assertFalse(result);
        verify(repoJPA, never()).save(any());
    }

    @Test
    void testGetAllDegreeTypes() {
        List<DegreeTypeDM> dms = List.of(dm);
        when(repoJPA.findAll()).thenReturn(dms);
        when(mapper.toDomainModel(dm)).thenReturn(domain);

        List<DegreeType> result = repo.getAllDegreeTypes();

        assertEquals(1, result.size());
        assertEquals(domain, result.get(0));
    }
}
package PAI.persistence.springdata;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.degreeType.DegreeType;
import PAI.mapper.DegreeType.DegreeTypeMapper;
import PAI.persistence.datamodel.DegreeType.DegreeTypeDataModel;
import PAI.persistence.datamodel.DegreeType.DegreeTypeIDDataModel;
import PAI.persistence.springdata.DegreeType.DegreeTypeRepoSpringData;
import PAI.persistence.springdata.DegreeType.IDegreeTypeRepoSpringData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    private final DegreeTypeIDDataModel idDataModel = new DegreeTypeIDDataModel(id.getDTID());
    private final Name name = new Name("Licenciatura");
    private final MaxEcts maxEcts = new MaxEcts(180);
    private final DegreeType domain = new DegreeType(id, name, maxEcts);
    private final DegreeTypeDataModel dm = new DegreeTypeDataModel(idDataModel, name.getName(), maxEcts.getMaxEcts());

    @BeforeEach
    void setUp() {
        repoJPA = mock(IDegreeTypeRepoSpringData.class);
        mapper = mock(DegreeTypeMapper.class);
        repo = new DegreeTypeRepoSpringData(repoJPA, mapper);
    }

    @Test
    void testOfIdentity_WhenExists() {
        when(repoJPA.findById(idDataModel)).thenReturn(Optional.of(dm));
        when(mapper.toDomainModel(dm)).thenReturn(domain);

        Optional<DegreeType> result = repo.ofIdentity(id);

        assertTrue(result.isPresent());
        assertEquals(domain, result.get());
    }

    @Test
    void testOfIdentity_WhenNotExists() {
        when(repoJPA.findById(idDataModel)).thenReturn(Optional.empty());

        Optional<DegreeType> result = repo.ofIdentity(id);

        assertTrue(result.isEmpty());
    }

    @Test
    void testContainsOfIdentity_WhenExists() {
        when(repoJPA.existsById(idDataModel)).thenReturn(true);

        assertTrue(repo.containsOfIdentity(id));
    }

    @Test
    void testContainsOfIdentity_WhenNotExists() {
        when(repoJPA.existsById(idDataModel)).thenReturn(false);

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
        List<DegreeTypeDataModel> dms = List.of(dm);
        when(repoJPA.findAll()).thenReturn(dms);
        when(mapper.toDomainModel(dm)).thenReturn(domain);

        List<DegreeType> result = repo.findAll();

        assertEquals(1, result.size());
        assertEquals(domain, result.get(0));
    }

    @Test
    void testRegisterDegreeType_WhenNotExists() throws Exception {
        when(repoJPA.existsById(idDataModel)).thenReturn(false);

        boolean result = repo.registerDegreeType(id, name, maxEcts);

        assertTrue(result);
        verify(repoJPA).save(any(DegreeTypeDataModel.class));
    }

    @Test
    void testRegisterDegreeType_WhenExists() throws Exception {
        when(repoJPA.existsById(idDataModel)).thenReturn(true);

        boolean result = repo.registerDegreeType(id, name, maxEcts);

        assertFalse(result);
        verify(repoJPA, never()).save(any());
    }

    @Test
    void testGetAllDegreeTypes() {
        List<DegreeTypeDataModel> dms = List.of(dm);
        when(repoJPA.findAll()).thenReturn(dms);
        when(mapper.toDomainModel(dm)).thenReturn(domain);

        List<DegreeType> result = repo.getAllDegreeTypes();

        assertEquals(1, result.size());
        assertEquals(domain, result.get(0));
    }
}

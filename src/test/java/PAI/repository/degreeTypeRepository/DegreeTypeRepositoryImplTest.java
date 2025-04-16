package PAI.repository.degreeTypeRepository;

import static org.junit.jupiter.api.Assertions.*;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.degreeType.DegreeType;
import PAI.factory.DegreeTypeFactory.IDegreeTypeFactory;
import PAI.factory.DegreeTypeFactory.IDegreeTypeListFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class DegreeTypeRepositoryImplTest {

    private DegreeTypeRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        IDegreeTypeFactory factory = (name, ects) -> DegreeType.create(name, ects);
        IDegreeTypeListFactory listFactory = new IDegreeTypeListFactory() {
            @Override
            public List<DegreeType> createEmptyList() {
                return new ArrayList<>();
            }

            @Override
            public List<DegreeType> createFromExisting(List<DegreeType> existing) {
                return new ArrayList<>(existing);
            }
        };

        repository = new DegreeTypeRepositoryImpl(factory, listFactory);
    }

    @Test
    void testRegisterNewDegreeType() {
        DegreeTypeID id = new DegreeTypeID("DT001");
        Name name = new Name("Licenciatura");
        MaxEcts ects = new MaxEcts(180);

        assertTrue(repository.registerDegreeType(id, name, ects));
        assertTrue(repository.containsOfIdentity(id));
    }

    @Test
    void testRegisterDuplicateReturnsFalse() {
        DegreeTypeID id = new DegreeTypeID("DT002");
        Name name = new Name("Mestrado");
        MaxEcts ects = new MaxEcts(120);

        repository.registerDegreeType(id, name, ects);
        assertFalse(repository.registerDegreeType(id, name, ects));
    }

    @Test
    void testSaveAndFindAll() {
        DegreeType degreeType = DegreeType.create(new Name("Engenharia"), new MaxEcts(180));
        repository.save(degreeType);

        Iterable<DegreeType> all = repository.findAll();
        assertTrue(all.iterator().hasNext());
    }

    @Test
    void testOfIdentityReturnsCorrectType() {
        DegreeType degreeType = DegreeType.create(new Name("Design"), new MaxEcts(180));
        repository.save(degreeType);

        Optional<DegreeType> found = repository.ofIdentity(degreeType.identity());
        assertTrue(found.isPresent());
        assertEquals(degreeType, found.get());
    }

    @Test
    void testOfIdentityReturnsEmptyIfNotFound() {
        DegreeTypeID id = new DegreeTypeID("NOT_FOUND");
        assertTrue(repository.ofIdentity(id).isEmpty());
    }

    @Test
    void testContainsOfIdentity() {
        DegreeType degreeType = DegreeType.create(new Name("Biologia"), new MaxEcts(180));
        repository.save(degreeType);

        assertTrue(repository.containsOfIdentity(degreeType.identity()));
        assertFalse(repository.containsOfIdentity(new DegreeTypeID("FAKE")));
    }

    @Test
    void testGetAllDegreeTypes() {
        DegreeType d1 = DegreeType.create(new Name("Psicologia"), new MaxEcts(180));
        DegreeType d2 = DegreeType.create(new Name("Arquitetura"), new MaxEcts(180));

        repository.save(d1);
        repository.save(d2);

        List<DegreeType> all = repository.getAllDegreeTypes();

        assertEquals(2, all.size());
        assertTrue(all.contains(d1));
        assertTrue(all.contains(d2));
    }
}
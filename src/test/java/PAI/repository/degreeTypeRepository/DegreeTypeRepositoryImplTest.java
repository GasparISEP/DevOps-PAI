package PAI.repository.degreeTypeRepository;

import static org.junit.jupiter.api.Assertions.*;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.degreeType.DegreeType;
import PAI.factory.DegreeTypeFactory.IDegreeTypeListFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class DegreeTypeRepositoryImplTest {

    private DegreeTypeRepositoryImpl repository;

    @BeforeEach
    void setUp() {
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

        repository = new DegreeTypeRepositoryImpl(listFactory);
    }

    @Test
    void testSaveAndGetAllDegreeTypes() {
        DegreeTypeID id = new DegreeTypeID("DT001");
        Name name = new Name("Licenciatura");
        MaxEcts ects = new MaxEcts(180);
        DegreeType degreeType = new DegreeType(id, name, ects);

        DegreeType saved = repository.save(degreeType);

        assertEquals(degreeType, saved);
        List<DegreeType> all = repository.getAllDegreeTypes();
        assertEquals(1, all.size());
        assertTrue(all.contains(degreeType));
    }

    @Test
    void testFindAll() {
        DegreeTypeID id = new DegreeTypeID("DT002");
        Name name = new Name("Mestrado");
        MaxEcts ects = new MaxEcts(120);
        DegreeType degreeType = new DegreeType(id, name, ects);

        repository.save(degreeType);

        Iterable<DegreeType> all = repository.findAll();
        assertTrue(all.iterator().hasNext());
        assertEquals(degreeType, all.iterator().next());
    }

    @Test
    void testOfIdentity_WhenExists() {
        DegreeTypeID id = new DegreeTypeID("DT003");
        Name name = new Name("Engenharia");
        MaxEcts ects = new MaxEcts(180);
        DegreeType degreeType = new DegreeType(id, name, ects);

        repository.save(degreeType);

        Optional<DegreeType> found = repository.ofIdentity(id);

        assertTrue(found.isPresent());
        assertEquals(degreeType, found.get());
    }

    @Test
    void testOfIdentity_WhenNotExists() {
        DegreeTypeID id = new DegreeTypeID("NON_EXISTENT");

        Optional<DegreeType> found = repository.ofIdentity(id);

        assertTrue(found.isEmpty());
    }

    @Test
    void testContainsOfIdentity_WhenExists() {
        DegreeTypeID id = new DegreeTypeID("DT004");
        Name name = new Name("Arquitetura");
        MaxEcts ects = new MaxEcts(180);
        DegreeType degreeType = new DegreeType(id, name, ects);

        repository.save(degreeType);

        assertTrue(repository.containsOfIdentity(id));
    }

    @Test
    void testContainsOfIdentity_WhenNotExists() {
        DegreeTypeID id = new DegreeTypeID("NOT_FOUND");

        assertFalse(repository.containsOfIdentity(id));
    }
}
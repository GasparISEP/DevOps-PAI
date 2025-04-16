package PAI.service;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.degreeType.DegreeType;
import PAI.factory.DegreeTypeFactory.IDegreeTypeFactory;
import PAI.repository.degreeTypeRepository.IDegreeTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DegreeTypeServiceTest {

    private IDegreeTypeRepository repository;
    private IDegreeTypeFactory factory;
    private DegreeTypeService service;

    private Name name;
    private MaxEcts ects;
    private DegreeType degreeType;

    @BeforeEach
    void setUp() {
        repository = mock(IDegreeTypeRepository.class);
        factory = mock(IDegreeTypeFactory.class);
        service = new DegreeTypeService(repository, factory);

        name = new Name("Engenharia Informática");
        ects = new MaxEcts(180);
        degreeType = DegreeType.create(name, ects);
    }

    @Test
    void testRegisterDegreeType_Success() throws Exception {
        when(factory.create(name, ects)).thenReturn(degreeType);
        when(repository.registerDegreeType(
                degreeType.identity(), name, ects)).thenReturn(true);

        boolean result = service.registerDegreeType(name, ects);

        assertTrue(result);
        verify(factory).create(name, ects);
        verify(repository).registerDegreeType(degreeType.identity(), name, ects);
    }

    @Test
    void testRegisterDegreeType_FailsIfAlreadyExists() throws Exception {
        when(factory.create(name, ects)).thenReturn(degreeType);
        when(repository.registerDegreeType(
                degreeType.identity(), name, ects)).thenReturn(false);

        boolean result = service.registerDegreeType(name, ects);

        assertFalse(result);
        verify(factory).create(name, ects);
        verify(repository).registerDegreeType(degreeType.identity(), name, ects);
    }

    @Test
    void testGetAllDegreeTypes() {
        List<DegreeType> list = List.of(degreeType);
        when(repository.getAllDegreeTypes()).thenReturn(list);

        List<DegreeType> result = service.getAllDegreeTypes();

        assertEquals(1, result.size());
        assertEquals(degreeType, result.get(0));
    }

    @Test
    void testGetDegreeTypeById_WhenExists() {
        DegreeTypeID id = degreeType.identity();
        when(repository.ofIdentity(id)).thenReturn(Optional.of(degreeType));

        Optional<DegreeType> result = service.getDegreeTypeById(id);

        assertTrue(result.isPresent());
        assertEquals(degreeType, result.get());
    }

    @Test
    void testGetDegreeTypeById_WhenNotExists() {
        DegreeTypeID id = new DegreeTypeID(UUID.randomUUID().toString());
        when(repository.ofIdentity(id)).thenReturn(Optional.empty());

        Optional<DegreeType> result = service.getDegreeTypeById(id);

        assertTrue(result.isEmpty());
    }
}
package PAI.service;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.degreeType.DegreeType;
import PAI.factory.DegreeTypeFactory.IDegreeTypeFactory;
import PAI.repository.degreeTypeRepository.IDegreeTypeRepository;
import PAI.service.DegreeType.DegreeTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        degreeType = new DegreeType(new DegreeTypeID(), name, ects);
    }

    @Test
    void testRegisterDegreeType_Success() throws Exception {
        // Arrange
        when(factory.create(name, ects)).thenReturn(degreeType);
        when(repository.containsOfIdentity(degreeType.identity())).thenReturn(false);

        // Act
        boolean result = service.registerDegreeType(name, ects);

        // Assert
        assertTrue(result);
        verify(factory).create(name, ects);
        verify(repository).containsOfIdentity(degreeType.identity());
        verify(repository).save(degreeType);
        verifyNoMoreInteractions(factory, repository);
    }

    @Test
    void testRegisterDegreeType_FailsIfAlreadyExists() throws Exception {
        // Arrange
        when(factory.create(name, ects)).thenReturn(degreeType);
        when(repository.containsOfIdentity(degreeType.identity())).thenReturn(true);

        // Act
        boolean result = service.registerDegreeType(name, ects);

        // Assert
        assertFalse(result);
        verify(factory).create(name, ects);
        verify(repository).containsOfIdentity(degreeType.identity());
        verifyNoMoreInteractions(factory, repository);
    }

    @Test
    void testRegisterDegreeType_ThrowsException() throws Exception {
        // Arrange
        when(factory.create(name, ects)).thenThrow(new Exception("Erro de criação"));

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> service.registerDegreeType(name, ects));
        assertEquals("Erro de criação", exception.getMessage());
        verify(factory).create(name, ects);
        verifyNoMoreInteractions(factory, repository);
    }

    @Test
    void testGetAllDegreeTypes() {
        // Arrange
        List<DegreeType> list = List.of(degreeType);
        when(repository.getAllDegreeTypes()).thenReturn(list);

        // Act
        List<DegreeType> result = service.getAllDegreeTypes();

        // Assert
        assertEquals(1, result.size());
        assertEquals(degreeType, result.get(0));
        verify(repository).getAllDegreeTypes();
        verifyNoMoreInteractions(repository, factory);
    }

    @Test
    void testGetDegreeTypeById_WhenExists() {
        // Arrange
        DegreeTypeID id = degreeType.identity();
        when(repository.ofIdentity(id)).thenReturn(Optional.of(degreeType));

        // Act
        Optional<DegreeType> result = service.getDegreeTypeById(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(degreeType, result.get());
        verify(repository).ofIdentity(id);
        verifyNoMoreInteractions(repository, factory);
    }

    @Test
    void testGetDegreeTypeById_WhenNotExists() {
        // Arrange
        DegreeTypeID id = new DegreeTypeID(UUID.randomUUID().toString());
        when(repository.ofIdentity(id)).thenReturn(Optional.empty());

        // Act
        Optional<DegreeType> result = service.getDegreeTypeById(id);

        // Assert
        assertTrue(result.isEmpty());
        verify(repository).ofIdentity(id);
        verifyNoMoreInteractions(repository, factory);
    }
}

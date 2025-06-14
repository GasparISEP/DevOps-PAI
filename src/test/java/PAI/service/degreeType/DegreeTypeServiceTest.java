package PAI.service.degreeType;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.degreeType.DegreeType;
import PAI.domain.degreeType.IDegreeTypeFactory;
import PAI.domain.repositoryInterfaces.degreeType.IDegreeTypeRepository;
import PAI.service.programme.IProgrammeService;
import PAI.service.programme.ProgrammeServiceImpl;
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

    @Test
    void testRegisterDegreeTypeWithUUID_Success() throws Exception {
        // Arrange
        DegreeTypeID id = new DegreeTypeID(UUID.randomUUID().toString());
        DegreeType recreated = new DegreeType(id, name, ects);

        when(factory.recreate(id, name, ects)).thenReturn(recreated);
        when(repository.containsOfIdentity(id)).thenReturn(false);

        // Act
        boolean result = service.registerDegreeTypeWithUUID(id, name, ects);

        // Assert
        assertTrue(result);
        verify(factory).recreate(id, name, ects);
        verify(repository).containsOfIdentity(id);
        verify(repository).save(recreated);
        verifyNoMoreInteractions(factory, repository);
    }

    @Test
    void testRegisterDegreeTypeWithUUID_AlreadyExists() throws Exception {
        // Arrange
        DegreeTypeID id = new DegreeTypeID(UUID.randomUUID().toString());
        DegreeType recreated = new DegreeType(id, name, ects);

        when(factory.recreate(id, name, ects)).thenReturn(recreated);
        when(repository.containsOfIdentity(id)).thenReturn(true);

        // Act
        boolean result = service.registerDegreeTypeWithUUID(id, name, ects);

        // Assert
        assertFalse(result);
        verify(factory).recreate(id, name, ects);
        verify(repository).containsOfIdentity(id);
        verifyNoMoreInteractions(factory, repository);
    }

    @Test
    void testRegisterDegreeTypeWithUUID_ThrowsException() {
        // Arrange
        DegreeTypeID id = new DegreeTypeID(UUID.randomUUID().toString());
        when(factory.recreate(id, name, ects)).thenThrow(new RuntimeException("Erro ao recriar"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                service.registerDegreeTypeWithUUID(id, name, ects)
        );

        assertEquals("Erro ao recriar", exception.getMessage());
        verify(factory).recreate(id, name, ects);
        verifyNoMoreInteractions(factory, repository);
    }

    @Test
    void shouldReturnTrueWhenContainsIdentity(){
        //Arrange
        DegreeTypeID idDouble = mock(DegreeTypeID.class);
        when(repository.containsOfIdentity(idDouble)).thenReturn(true);

        //Act
        boolean result = service.containsOfIdentity(idDouble);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenDoesntContainsIdentity(){
        //Arrange
        DegreeTypeID idDouble = mock(DegreeTypeID.class);
        when(repository.containsOfIdentity(idDouble)).thenReturn(false);

        //Act
        boolean result = service.containsOfIdentity(idDouble);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldThrowExceptionIfNullParameterForContainsOfIdentity(){
        //Arrange

        //Act
        assertThrows(IllegalArgumentException.class,() -> service.containsOfIdentity(null));
    }
}

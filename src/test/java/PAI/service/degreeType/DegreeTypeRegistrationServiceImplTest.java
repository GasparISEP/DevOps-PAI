package PAI.service.degreeType;

import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.degreeType.DegreeType;
import PAI.domain.degreeType.IDegreeTypeFactory;
import PAI.domain.repositoryInterfaces.degreeType.IDegreeTypeRepository;
import PAI.dto.degreeType.RegisterDegreeTypeCommand;
import PAI.exception.BusinessRuleViolationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DegreeTypeRegistrationServiceImplTest {

    @Test
    void shouldCreateAndSaveDegreeType() throws Exception {
        // Arrange
        IDegreeTypeFactory factory = mock(IDegreeTypeFactory.class);
        IDegreeTypeRepository repository = mock(IDegreeTypeRepository.class);
        DegreeType degreeType = mock(DegreeType.class);
        Name name = mock(Name.class);
        MaxEcts maxEcts = mock(MaxEcts.class);

        RegisterDegreeTypeCommand command = mock(RegisterDegreeTypeCommand.class);
        when(command.name()).thenReturn(name);
        when(command.maxEcts()).thenReturn(maxEcts);
        when(factory.create(name, maxEcts)).thenReturn(degreeType);
        when(repository.containsOfIdentity(degreeType.identity())).thenReturn(false);

        IDegreeTypeRegistrationService service = new DegreeTypeRegistrationServiceImpl(factory, repository);

        // Act
        DegreeType result = service.createAndSaveDegreeType(command);

        // Assert
        assertNotNull(result);
        assertEquals(degreeType, result);
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        // Arrange
        IDegreeTypeFactory factory = mock(IDegreeTypeFactory.class);
        IDegreeTypeRepository repository = mock(IDegreeTypeRepository.class);
        MaxEcts maxEcts = mock(MaxEcts.class);

        RegisterDegreeTypeCommand command = mock(RegisterDegreeTypeCommand.class);
        when(command.name()).thenReturn(null);
        when(command.maxEcts()).thenReturn(maxEcts);

        IDegreeTypeRegistrationService service = new DegreeTypeRegistrationServiceImpl(factory, repository);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> service.createAndSaveDegreeType(command));
    }

    @Test
    void shouldThrowExceptionWhenMaxEctsIsNull() {
        // Arrange
        IDegreeTypeFactory factory = mock(IDegreeTypeFactory.class);
        IDegreeTypeRepository repository = mock(IDegreeTypeRepository.class);
        Name name = mock(Name.class);

        RegisterDegreeTypeCommand command = mock(RegisterDegreeTypeCommand.class);
        when(command.name()).thenReturn(name);
        when(command.maxEcts()).thenReturn(null);

        IDegreeTypeRegistrationService service = new DegreeTypeRegistrationServiceImpl(factory, repository);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> service.createAndSaveDegreeType(command));
    }

    @Test
    void shouldThrowExceptionWhenFactoryIsNull() {
        // Arrange
        IDegreeTypeRepository repository = mock(IDegreeTypeRepository.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new DegreeTypeRegistrationServiceImpl(null, repository));
    }

    @Test
    void shouldThrowExceptionWhenRepositoryIsNull() {
        // Arrange
        IDegreeTypeFactory factory = mock(IDegreeTypeFactory.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new DegreeTypeRegistrationServiceImpl(factory, null));
    }

    @Test
    void shouldThrowExceptionWhenDegreeTypeIsNull() throws Exception {
        // Arrange
        IDegreeTypeFactory factory = mock(IDegreeTypeFactory.class);
        IDegreeTypeRepository repository = mock(IDegreeTypeRepository.class);
        Name name = mock(Name.class);
        MaxEcts maxEcts = mock(MaxEcts.class);

        RegisterDegreeTypeCommand command = mock(RegisterDegreeTypeCommand.class);
        when(command.name()).thenReturn(name);
        when(command.maxEcts()).thenReturn(maxEcts);
        when(factory.create(name, maxEcts)).thenReturn(null);

        IDegreeTypeRegistrationService service = new DegreeTypeRegistrationServiceImpl(factory, repository);

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> service.createAndSaveDegreeType(command));
    }

    @Test
    void shouldThrowExceptionWhenDegreeTypeAlreadyExists() throws Exception {
        // Arrange
        IDegreeTypeFactory factory = mock(IDegreeTypeFactory.class);
        IDegreeTypeRepository repository = mock(IDegreeTypeRepository.class);
        DegreeType degreeType = mock(DegreeType.class);
        Name name = mock(Name.class);
        MaxEcts maxEcts = mock(MaxEcts.class);

        RegisterDegreeTypeCommand command = mock(RegisterDegreeTypeCommand.class);
        when(command.name()).thenReturn(name);
        when(command.maxEcts()).thenReturn(maxEcts);
        when(factory.create(name, maxEcts)).thenReturn(degreeType);
        when(repository.containsOfIdentity(degreeType.identity())).thenReturn(true);

        IDegreeTypeRegistrationService service = new DegreeTypeRegistrationServiceImpl(factory, repository);

        // Act & Assert
        assertThrows(BusinessRuleViolationException.class, () -> service.createAndSaveDegreeType(command));
    }

    @Test
    void shouldReturnEmptyListWhenNoDegreeTypesExist() {
        // Arrange
        IDegreeTypeFactory factory = mock(IDegreeTypeFactory.class);
        IDegreeTypeRepository repository = mock(IDegreeTypeRepository.class);

        IDegreeTypeRegistrationService service = new DegreeTypeRegistrationServiceImpl(factory, repository);
        when(repository.findAll()).thenReturn(List.of());

        // Act
        Iterable<DegreeType> result = service.getAllDegreeTypes();

        // Assert
        assertNotNull(result);
        assertFalse(result.iterator().hasNext());
    }

    @Test
    void shouldReturnListOfDegreeTypes() {
        // Arrange
        IDegreeTypeFactory factory = mock(IDegreeTypeFactory.class);
        IDegreeTypeRepository repository = mock(IDegreeTypeRepository.class);
        DegreeType dt1 = mock(DegreeType.class);
        DegreeType dt2 = mock(DegreeType.class);
        DegreeType dt3 = mock(DegreeType.class);

        IDegreeTypeRegistrationService service = new DegreeTypeRegistrationServiceImpl(factory, repository);
        when(repository.findAll()).thenReturn(List.of(dt1, dt2, dt3));

        // Act
        Iterable<DegreeType> result = service.getAllDegreeTypes();

        // Assert
        assertNotNull(result);
        assertTrue(result.iterator().hasNext());
        List<DegreeType> listResult = new java.util.ArrayList<>();
        result.forEach(listResult::add);
        assertEquals(3, listResult.size());
        assertTrue(listResult.contains(dt1));
        assertTrue(listResult.contains(dt2));
        assertTrue(listResult.contains(dt3));
    }
}

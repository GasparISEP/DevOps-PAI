package PAI.service.accessMethod;

import PAI.VOs.AccessMethodID;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.assembler.accessMethod.IAccessMethodControllerAssembler;
import PAI.assembler.accessMethod.IAccessMethodServiceAssembler;
import PAI.domain.accessMethod.AccessMethod;
import PAI.domain.accessMethod.IAccessMethodFactory;
import PAI.domain.repositoryInterfaces.accessMethod.IRepositoryAccessMethod;
import PAI.dto.accessMethod.AccessMethodServiceDTO;
import PAI.dto.accessMethod.RegisterAccessMethodCommand;
import PAI.exception.AlreadyRegisteredException;
import PAI.exception.BusinessRuleViolationException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccessMethodServiceImplTest {

    @Test
    void shouldReturnAccessMethodServiceImpl(){
        // arrange
        IAccessMethodFactory iAccessMethodFactory = mock(IAccessMethodFactory.class);
        IRepositoryAccessMethod iRepositoryAccessMethod = mock(IRepositoryAccessMethod.class);
        IAccessMethodServiceAssembler iAccessMethodServiceAssembler = mock(IAccessMethodServiceAssembler.class);

        // act
        AccessMethodServiceImpl accessMethodServiceImpl = new AccessMethodServiceImpl(iAccessMethodFactory,
                iRepositoryAccessMethod, iAccessMethodServiceAssembler);

        // assert
        assertNotNull(accessMethodServiceImpl);
    }

    @Test
    void shouldNotReturnAccessMethodServiceImplIfRepositoryAccessMethodIsNull(){
        // arrange
        IAccessMethodFactory iAccessMethodFactory = mock(IAccessMethodFactory.class);
        IAccessMethodServiceAssembler iAccessMethodServiceAssembler = mock(IAccessMethodServiceAssembler.class);


        // act & assert
        assertThrows(IllegalArgumentException.class, ()->{
            new AccessMethodServiceImpl(iAccessMethodFactory, null, iAccessMethodServiceAssembler);
        });
    }

    @Test
    void shouldNotReturnAccessMethodServiceImplIfAccessMethodFactoryIsNull(){
        // arrange
        IRepositoryAccessMethod iRepositoryAccessMethod = mock(IRepositoryAccessMethod.class);
        IAccessMethodServiceAssembler iAccessMethodServiceAssembler = mock(IAccessMethodServiceAssembler.class);

        // act & assert
        assertThrows(IllegalArgumentException.class, ()->{
            new AccessMethodServiceImpl(null, iRepositoryAccessMethod, iAccessMethodServiceAssembler);
        });
    }

    @Test
    void shouldReturnAccessMethodOptionalIfRegisterSuccessfully(){
        // arrange
        IAccessMethodFactory iAccessMethodFactory = mock(IAccessMethodFactory.class);
        IRepositoryAccessMethod iRepositoryAccessMethod = mock(IRepositoryAccessMethod.class);
        IAccessMethodServiceAssembler iAccessMethodServiceAssembler = mock(IAccessMethodServiceAssembler.class);

        AccessMethodServiceImpl accessMethodServiceImpl = new AccessMethodServiceImpl(iAccessMethodFactory,
                iRepositoryAccessMethod, iAccessMethodServiceAssembler);
        String accessMethodName = "M23";
        NameWithNumbersAndSpecialChars vo = new NameWithNumbersAndSpecialChars(accessMethodName);
        RegisterAccessMethodCommand command = new RegisterAccessMethodCommand(vo);

        AccessMethodServiceDTO expectedDto = mock(AccessMethodServiceDTO.class);
        AccessMethod accessMethod = mock(AccessMethod.class);

        when(iRepositoryAccessMethod.getAccessMethodByName(any(NameWithNumbersAndSpecialChars.class)))
                .thenReturn(Optional.empty());

        when(iAccessMethodFactory.createAccessMethod(any(NameWithNumbersAndSpecialChars.class)))
                .thenReturn(accessMethod);

        when(iRepositoryAccessMethod.saveAccessMethod(eq(accessMethod)))
                .thenReturn(Optional.of(accessMethod));

        when(iAccessMethodServiceAssembler.toDTO(eq(accessMethod)))
                .thenReturn(expectedDto);

        // act
        AccessMethodServiceDTO result = accessMethodServiceImpl.configureAccessMethod(command);

        // assert
        assertEquals(expectedDto, result);
    }

    @Test
    void shouldThrowAlreadyRegisteredExceptionWhenSaveFails(){
        // arrange
        IAccessMethodFactory iAccessMethodFactory = mock(IAccessMethodFactory.class);
        IRepositoryAccessMethod iRepositoryAccessMethod = mock(IRepositoryAccessMethod.class);
        IAccessMethodServiceAssembler iAccessMethodServiceAssembler = mock(IAccessMethodServiceAssembler.class);
        AccessMethodServiceImpl accessMethodServiceImpl = new AccessMethodServiceImpl(iAccessMethodFactory,
                iRepositoryAccessMethod, iAccessMethodServiceAssembler);
        String accessMethodName = "M23";
        NameWithNumbersAndSpecialChars vo = new NameWithNumbersAndSpecialChars(accessMethodName);
        RegisterAccessMethodCommand command = new RegisterAccessMethodCommand(vo);
        AccessMethod accessMethod = mock(AccessMethod.class);

        when(iRepositoryAccessMethod.getAccessMethodByName(vo)).thenReturn(Optional.empty());
        when(iAccessMethodFactory.createAccessMethod(vo)).thenReturn(accessMethod);
        when(iRepositoryAccessMethod.saveAccessMethod(accessMethod)).thenReturn(Optional.empty());

        // act
        AlreadyRegisteredException exception = assertThrows(AlreadyRegisteredException.class, () -> {
            accessMethodServiceImpl.configureAccessMethod(command);
        });

        // assert
        assertEquals("Access Method is already registered.", exception.getMessage());
    }

    @Test
    void shouldReturnEmptyOptionalIfAccessMethodAlreadyInRepository(){
        // arrange
        IAccessMethodFactory iAccessMethodFactory = mock(IAccessMethodFactory.class);
        IRepositoryAccessMethod iRepositoryAccessMethod = mock(IRepositoryAccessMethod.class);
        IAccessMethodServiceAssembler iAccessMethodServiceAssembler = mock(IAccessMethodServiceAssembler.class);
        AccessMethodServiceImpl accessMethodServiceImpl = new AccessMethodServiceImpl(iAccessMethodFactory,
                iRepositoryAccessMethod, iAccessMethodServiceAssembler);

        String accessMethodName = "M23";
        NameWithNumbersAndSpecialChars vo = new NameWithNumbersAndSpecialChars(accessMethodName);
        RegisterAccessMethodCommand command = new RegisterAccessMethodCommand(vo);
        AccessMethod existingAccessMethod = mock(AccessMethod.class);

        when(iRepositoryAccessMethod.getAccessMethodByName(any(NameWithNumbersAndSpecialChars.class)))
                .thenReturn(Optional.of(existingAccessMethod));

        // act
        AlreadyRegisteredException exception = assertThrows(AlreadyRegisteredException.class, () -> {
            accessMethodServiceImpl.configureAccessMethod(command);
        });

        // assert
        assertEquals("Access method is already registered.", exception.getMessage());
    }

    @Test
    void constructor_ShouldThrowException_WhenAssemblerIsNull() {
        // Arrange
        IAccessMethodFactory factoryMock = org.mockito.Mockito.mock(IAccessMethodFactory.class);
        IRepositoryAccessMethod repositoryMock = org.mockito.Mockito.mock(IRepositoryAccessMethod.class);

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new AccessMethodServiceImpl(factoryMock, repositoryMock, null);
        });

        assertEquals("AccessMethodServiceAssembler cannot be null.", thrown.getMessage());
    }

    @Test
    void shouldReturnNullWhenIdIsInvalidUUID() {
        // Arrange
        AccessMethodServiceImpl service = new AccessMethodServiceImpl(
                mock(IAccessMethodFactory.class),
                mock(IRepositoryAccessMethod.class),
                mock(IAccessMethodServiceAssembler.class)
        );

        String invalidId = "not-a-uuid";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.getAccessMethodById(invalidId);
        });

        assertEquals("Invalid UUID format for AccessMethod ID.", exception.getMessage());
    }

    @Test
    void shouldThrowBusinessRuleViolationExceptionWhenAccessMethodNotFound() {
        // Arrange
        IRepositoryAccessMethod repositoryMock = mock(IRepositoryAccessMethod.class);
        IAccessMethodServiceAssembler assemblerMock = mock(IAccessMethodServiceAssembler.class);
        IAccessMethodFactory factoryMock = mock(IAccessMethodFactory.class);

        AccessMethodServiceImpl service = new AccessMethodServiceImpl(factoryMock, repositoryMock, assemblerMock);

        UUID validUUID = UUID.randomUUID();
        String id = validUUID.toString();

        when(repositoryMock.ofIdentity(new AccessMethodID(validUUID))).thenReturn(Optional.empty());

        // Act & Assert
        BusinessRuleViolationException exception = assertThrows(BusinessRuleViolationException.class, () -> {
            service.getAccessMethodById(id);
        });

        assertEquals("Access method not found with ID " + id, exception.getMessage());
    }

    @Test
    void shouldReturnDtoWhenAccessMethodFound() {
        // Arrange
        IRepositoryAccessMethod repositoryMock = mock(IRepositoryAccessMethod.class);
        IAccessMethodServiceAssembler assemblerMock = mock(IAccessMethodServiceAssembler.class);
        IAccessMethodFactory factoryMock = mock(IAccessMethodFactory.class);

        AccessMethodServiceImpl service = new AccessMethodServiceImpl(factoryMock, repositoryMock, assemblerMock);

        UUID validUUID = UUID.randomUUID();
        String id = validUUID.toString();
        AccessMethodID accessMethodID = new AccessMethodID(validUUID);

        AccessMethod domainMock = mock(AccessMethod.class);
        AccessMethodServiceDTO expectedDto = mock(AccessMethodServiceDTO.class);

        when(repositoryMock.ofIdentity(accessMethodID))
                .thenReturn(Optional.of(domainMock));
        when(assemblerMock.toDTO(domainMock))
                .thenReturn(expectedDto);

        // Act
        AccessMethodServiceDTO result = service.getAccessMethodById(id);

        // Assert
        assertEquals(expectedDto, result);
    }

    @Test
    void shouldThrowExceptionWhenCommandIsNull() {
        // Arrange
        IAccessMethodFactory factory = mock(IAccessMethodFactory.class);
        IRepositoryAccessMethod repository = mock(IRepositoryAccessMethod.class);
        IAccessMethodServiceAssembler assembler = mock(IAccessMethodServiceAssembler.class);

        AccessMethodServiceImpl service = new AccessMethodServiceImpl(factory, repository, assembler);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.configureAccessMethod(null);
        });

        assertEquals("RegisterAccessMethodCommand cannot be null.", exception.getMessage());
    }

    @Test
    void constructor_ShouldThrowException_WhenAllArgumentsAreNull() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new AccessMethodServiceImpl(null, null, null);
        });
        assertNotNull(exception.getMessage());
    }

    @Test
    void shouldReturnNullWhenIdIsEmpty() {
        // Arrange
        AccessMethodServiceImpl service = new AccessMethodServiceImpl(
                mock(IAccessMethodFactory.class),
                mock(IRepositoryAccessMethod.class),
                mock(IAccessMethodServiceAssembler.class)
        );

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.getAccessMethodById("");
        });

        assertEquals("AccessMethod ID cannot be null or blank.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        // Arrange
        AccessMethodServiceImpl service = new AccessMethodServiceImpl(
                mock(IAccessMethodFactory.class),
                mock(IRepositoryAccessMethod.class),
                mock(IAccessMethodServiceAssembler.class)
        );

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.getAccessMethodById(null);
        });

        assertEquals("AccessMethod ID cannot be null or blank.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenIdIsInvalidUUID() {
        // Arrange
        AccessMethodServiceImpl service = new AccessMethodServiceImpl(
                mock(IAccessMethodFactory.class),
                mock(IRepositoryAccessMethod.class),
                mock(IAccessMethodServiceAssembler.class)
        );

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.getAccessMethodById("invalid-uuid");
        });

        assertEquals("Invalid UUID format for AccessMethod ID.", exception.getMessage());
    }

    @Test
    void shouldReturnListOfAccessMethodServiceDTOsWhenRepositoryIsNotEmpty() {
        // Arrange
        IRepositoryAccessMethod repositoryMock = mock(IRepositoryAccessMethod.class);
        IAccessMethodServiceAssembler assemblerMock = mock(IAccessMethodServiceAssembler.class);
        IAccessMethodFactory factoryMock = mock(IAccessMethodFactory.class);

        AccessMethodServiceImpl service = new AccessMethodServiceImpl(factoryMock, repositoryMock, assemblerMock);

        AccessMethod accessMethod1 = mock(AccessMethod.class);
        AccessMethod accessMethod2 = mock(AccessMethod.class);

        List<AccessMethod> domainList = List.of(accessMethod1, accessMethod2);
        List<AccessMethodServiceDTO> dtoList = List.of(
                mock(AccessMethodServiceDTO.class),
                mock(AccessMethodServiceDTO.class)
        );

        when(repositoryMock.findAll()).thenReturn(domainList);
        when(assemblerMock.toDTOList(domainList)).thenReturn(dtoList);

        // Act
        List<AccessMethodServiceDTO> result = service.getAllAccessMethods();

        // Assert
        assertNotNull(result);
        assertEquals(dtoList.size(), result.size());
        assertEquals(dtoList, result);
    }

    @Test
    void shouldThrowBusinessRuleViolationExceptionWhenNoAccessMethodsFound() {
        // Arrange
        IRepositoryAccessMethod repositoryMock = mock(IRepositoryAccessMethod.class);
        IAccessMethodServiceAssembler assemblerMock = mock(IAccessMethodServiceAssembler.class);
        IAccessMethodFactory factoryMock = mock(IAccessMethodFactory.class);

        AccessMethodServiceImpl service = new AccessMethodServiceImpl(factoryMock, repositoryMock, assemblerMock);

        when(repositoryMock.findAll()).thenReturn(List.of()); // empty list

        // Act & Assert
        BusinessRuleViolationException exception = assertThrows(BusinessRuleViolationException.class, service::getAllAccessMethods);

        assertEquals("No access methods found.", exception.getMessage());
    }
}

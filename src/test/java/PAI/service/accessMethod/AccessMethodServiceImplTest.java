package PAI.service.accessMethod;

import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.assembler.accessMethod.IAccessMethodAssembler;
import PAI.domain.accessMethod.AccessMethod;
import PAI.domain.accessMethod.IAccessMethodFactory;
import PAI.domain.repositoryInterfaces.accessMethod.IRepositoryAccessMethod;
import PAI.dto.accessMethod.AccessMethodResponseDTO;
import PAI.dto.accessMethod.RegisterAccessMethodCommand;
import PAI.utils.ServiceResponse;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

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
        IAccessMethodAssembler iAccessMethodAssembler = mock(IAccessMethodAssembler.class);
        // act
        AccessMethodServiceImpl accessMethodServiceImpl = new AccessMethodServiceImpl(iAccessMethodFactory,
                iRepositoryAccessMethod, iAccessMethodAssembler);
        // assert
        assertNotNull(accessMethodServiceImpl);
    }

    @Test
    void shouldNotReturnAccessMethodServiceImplIfRepositoryAccessMethodIsNull(){
        // arrange
        IAccessMethodFactory iAccessMethodFactory = mock(IAccessMethodFactory.class);
        IAccessMethodAssembler iAccessMethodAssembler = mock(IAccessMethodAssembler.class);
        // act & assert
        assertThrows(IllegalArgumentException.class, ()->{
            new AccessMethodServiceImpl(iAccessMethodFactory, null,iAccessMethodAssembler);
        });
    }

    @Test
    void shouldNotReturnAccessMethodServiceImplIfAccessMethodFactoryIsNull(){
        // arrange
        IRepositoryAccessMethod iRepositoryAccessMethod = mock(IRepositoryAccessMethod.class);
        IAccessMethodAssembler iAccessMethodAssembler = mock(IAccessMethodAssembler.class);
        // act & assert
        assertThrows(IllegalArgumentException.class, ()->{
            new AccessMethodServiceImpl(null, iRepositoryAccessMethod, iAccessMethodAssembler);
        });
    }

    @Test
    void shouldReturnAccessMethodOptionalIfRegisterSuccessfully(){
        // arrange
        IAccessMethodFactory iAccessMethodFactory = mock(IAccessMethodFactory.class);
        IRepositoryAccessMethod iRepositoryAccessMethod = mock(IRepositoryAccessMethod.class);
        IAccessMethodAssembler iAccessMethodAssembler = mock(IAccessMethodAssembler.class);
        AccessMethodServiceImpl accessMethodServiceImpl = new AccessMethodServiceImpl(iAccessMethodFactory,
                iRepositoryAccessMethod, iAccessMethodAssembler);
        String accessMethodName = "M23";
        RegisterAccessMethodCommand command = new RegisterAccessMethodCommand(accessMethodName);
        AccessMethodResponseDTO responseDTO = mock(AccessMethodResponseDTO.class);
        NameWithNumbersAndSpecialChars vo = new NameWithNumbersAndSpecialChars(accessMethodName);
        AccessMethod accessMethod = mock(AccessMethod.class);

        when(iRepositoryAccessMethod.getAccessMethodByName(any(NameWithNumbersAndSpecialChars.class)))
                .thenReturn(Optional.empty());

        when(iAccessMethodFactory.createAccessMethod(any(NameWithNumbersAndSpecialChars.class)))
                .thenReturn(accessMethod);

        when(iRepositoryAccessMethod.saveAccessMethod(eq(accessMethod)))
                .thenReturn(Optional.of(accessMethod));

        when(iAccessMethodAssembler.toDto(eq(accessMethod)))
                .thenReturn(responseDTO);

        // act
        ServiceResponse<AccessMethodResponseDTO> result = accessMethodServiceImpl.configureAccessMethod(command);

        // assert
        assertEquals(responseDTO, result.getObject());
    }

    @Test
    void shouldReturnEmptyOptionalIfAccessMethodNotRegisteredSuccessfully(){
        // arrange
        IAccessMethodFactory iAccessMethodFactory = mock(IAccessMethodFactory.class);
        IRepositoryAccessMethod iRepositoryAccessMethod = mock(IRepositoryAccessMethod.class);
        IAccessMethodAssembler iAccessMethodAssembler = mock(IAccessMethodAssembler.class);
        AccessMethodServiceImpl accessMethodServiceImpl = new AccessMethodServiceImpl(iAccessMethodFactory,
                iRepositoryAccessMethod, iAccessMethodAssembler);
        String accessMethodName = "M23";
        RegisterAccessMethodCommand command = new RegisterAccessMethodCommand(accessMethodName);
        NameWithNumbersAndSpecialChars vo = new NameWithNumbersAndSpecialChars(accessMethodName);
        AccessMethod accessMethod = mock(AccessMethod.class);

        when(iRepositoryAccessMethod.getAccessMethodByName(vo)).thenReturn(Optional.empty());
        when(iAccessMethodFactory.createAccessMethod(vo)).thenReturn(accessMethod);
        when(iRepositoryAccessMethod.saveAccessMethod(accessMethod)).thenReturn(Optional.empty());

        // act
        ServiceResponse<AccessMethodResponseDTO> result = accessMethodServiceImpl.configureAccessMethod(command);

        // assert
        assertFalse(result.isSuccess());
        assertNull(result.getObject());
        assertTrue(result.getMessages().contains("Failed to save access method."));
    }

    @Test
    void shouldReturnEmptyOptionalIfAccessMethodAlreadyInRepository(){
        // arrange
        IAccessMethodFactory iAccessMethodFactory = mock(IAccessMethodFactory.class);
        IRepositoryAccessMethod iRepositoryAccessMethod = mock(IRepositoryAccessMethod.class);
        IAccessMethodAssembler iAccessMethodAssembler = mock(IAccessMethodAssembler.class);
        AccessMethodServiceImpl accessMethodServiceImpl = new AccessMethodServiceImpl(iAccessMethodFactory,
                iRepositoryAccessMethod, iAccessMethodAssembler);

        String accessMethodName = "M23";
        RegisterAccessMethodCommand command = new RegisterAccessMethodCommand(accessMethodName);
        NameWithNumbersAndSpecialChars vo = new NameWithNumbersAndSpecialChars(accessMethodName);
        AccessMethod existingAccessMethod = mock(AccessMethod.class);

        when(iRepositoryAccessMethod.getAccessMethodByName(any(NameWithNumbersAndSpecialChars.class)))
                .thenReturn(Optional.of(existingAccessMethod));

        // act
        ServiceResponse<AccessMethodResponseDTO> result = accessMethodServiceImpl.configureAccessMethod(command);

        // assert
        assertFalse(result.isSuccess());
        assertNull(result.getObject());
        assertEquals(List.of("Access method already exists."), result.getMessages());
    }

    @Test
    void constructor_ShouldThrowException_WhenAssemblerIsNull() {
        IAccessMethodFactory factoryMock = org.mockito.Mockito.mock(IAccessMethodFactory.class);
        IRepositoryAccessMethod repositoryMock = org.mockito.Mockito.mock(IRepositoryAccessMethod.class);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new AccessMethodServiceImpl(factoryMock, repositoryMock, null);
        });

        assertEquals("AccessMethodAssembler cannot be null.", thrown.getMessage());
    }
}

package PAI.service.accessMethod;

import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.domain.accessMethod.AccessMethod;
import PAI.domain.accessMethod.AccessMethodFactoryImpl;
import PAI.domain.accessMethod.IAccessMethodFactory;
import PAI.repository.accessMethodRepository.IRepositoryAccessMethod;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccessMethodServiceImplTest {

    @Test
    void shouldReturnAccessMethodServiceImpl(){
        // arrange
        IAccessMethodFactory iAccessMethodFactory = mock(IAccessMethodFactory.class);
        IRepositoryAccessMethod iRepositoryAccessMethod = mock(IRepositoryAccessMethod.class);
        // act
        AccessMethodServiceImpl accessMethodServiceImpl = new AccessMethodServiceImpl(iAccessMethodFactory, iRepositoryAccessMethod);
        // assert
        assertNotNull(accessMethodServiceImpl);
    }

    @Test
    void shouldNotReturnAccessMethodServiceImplIfRepositoryAccessMethodIsNull(){
        // arrange
        IAccessMethodFactory iAccessMethodFactory = mock(IAccessMethodFactory.class);
        // act & assert
        assertThrows(IllegalArgumentException.class, ()->{
            new AccessMethodServiceImpl(iAccessMethodFactory, null);
        });
    }

    @Test
    void shouldNotReturnAccessMethodServiceImplIfAccessMethodFactoryIsNull(){
        // arrange
        IRepositoryAccessMethod iRepositoryAccessMethod = mock(IRepositoryAccessMethod.class);
        // act & assert
        assertThrows(IllegalArgumentException.class, ()->{
            new AccessMethodServiceImpl(null, iRepositoryAccessMethod);
        });
    }

    @Test
    void shouldReturnAccessMethodOptionalIfRegisterSuccessfully(){
        // arrange
        IAccessMethodFactory iAccessMethodFactory = mock(IAccessMethodFactory.class);
        IRepositoryAccessMethod iRepositoryAccessMethod = mock(IRepositoryAccessMethod.class);
        AccessMethodServiceImpl accessMethodServiceImpl = new AccessMethodServiceImpl(iAccessMethodFactory, iRepositoryAccessMethod);
        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethod accessMethod = mock(AccessMethod.class);

        when(iAccessMethodFactory.createAccessMethod(nameWithNumbersAndSpecialChars)).thenReturn(accessMethod);
        when(iRepositoryAccessMethod.saveAccessMethod(accessMethod)).thenReturn(Optional.of(accessMethod));

        // act
        Optional<AccessMethod> result = accessMethodServiceImpl.registerAccessMethodInMemoryRepository(nameWithNumbersAndSpecialChars);
        // assert
        assertTrue(result.isPresent());
    }

    @Test
    void shouldReturnEmptyOptionalIfAccessMethodNotRegisteredSuccessfully(){
        // arrange
        IAccessMethodFactory iAccessMethodFactory = mock(IAccessMethodFactory.class);
        IRepositoryAccessMethod iRepositoryAccessMethod = mock(IRepositoryAccessMethod.class);
        AccessMethodServiceImpl accessMethodServiceImpl = new AccessMethodServiceImpl(iAccessMethodFactory, iRepositoryAccessMethod);
        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethod accessMethod = mock(AccessMethod.class);

        when(iAccessMethodFactory.createAccessMethod(nameWithNumbersAndSpecialChars)).thenReturn(accessMethod);
        when(iRepositoryAccessMethod.saveAccessMethod(accessMethod)).thenReturn(Optional.empty());

        // act
        Optional<AccessMethod> result = accessMethodServiceImpl.registerAccessMethodInMemoryRepository(nameWithNumbersAndSpecialChars);
        // assert
        assertTrue(result.isEmpty());
    }
}
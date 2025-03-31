package PAI.repository.accessMethodRepositoryDDD;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.domain.accessMethodDDD.AccessMethodDDD;
import PAI.domain.accessMethodDDD.AccessMethodDDDFactoryImpl;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccessMethodDDDRepositoryTest {

    @Test
    void shouldCreateAccessMethodRepository(){
        //arrange
        AccessMethodDDDFactoryImpl doubleAccessMethodFactory = mock(AccessMethodDDDFactoryImpl.class);
        AccessMethodDDDListFactoryImpl doubleAccessMethodListFactory = mock(AccessMethodDDDListFactoryImpl.class);
        //act
        AccessMethodDDDRepository accessMethodRepository = new AccessMethodDDDRepository(doubleAccessMethodFactory, doubleAccessMethodListFactory);
        //assert
        assertNotNull(accessMethodRepository);
    }

    @Test
    void shouldNotRegisterAccessMethodIfIsAlreadyRegisteredInTheAccessMethodRepository(){
        //arrange
        AccessMethodDDDFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodDDDFactoryImpl.class);
        AccessMethodDDDListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodDDDListFactoryImpl.class);
        AccessMethodDDDRepository accessMethodRepository = new AccessMethodDDDRepository (doubleAccessMethodFactoryImpl, doubleAccessMethodListFactoryImpl);
        AccessMethodDDD doubleAccessMethod = mock(AccessMethodDDD.class);
        NameWithNumbersAndSpecialChars doubleAccessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName)).thenReturn(doubleAccessMethod);
        accessMethodRepository.registerAccessMethod(doubleAccessMethodName);
        //act
        boolean result = accessMethodRepository.registerAccessMethod(doubleAccessMethodName);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldRegisterAccessMethodIfNotRegisteredInAccessMethodRepository() {
        //arrange
        AccessMethodDDDFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodDDDFactoryImpl.class);
        AccessMethodDDDListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodDDDListFactoryImpl.class);
        AccessMethodDDDRepository accessMethodRepository = new AccessMethodDDDRepository (doubleAccessMethodFactoryImpl, doubleAccessMethodListFactoryImpl);
        AccessMethodDDD doubleAccessMethod = mock(AccessMethodDDD.class);
        NameWithNumbersAndSpecialChars doubleAccessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName)).thenReturn(doubleAccessMethod);
        //act
        boolean result = accessMethodRepository.registerAccessMethod(doubleAccessMethodName);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfAccessMethodNameIsInvalid(){
        //arrange
        AccessMethodDDDFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodDDDFactoryImpl.class);
        AccessMethodDDDListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodDDDListFactoryImpl.class);
        AccessMethodDDDRepository accessMethodRepository = new AccessMethodDDDRepository (doubleAccessMethodFactoryImpl, doubleAccessMethodListFactoryImpl);
        NameWithNumbersAndSpecialChars doubleAccessMethodName = null;
        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName)).thenThrow(new IllegalArgumentException("Cannot create Access Method, because name is invalid."));
        //act
        boolean result = accessMethodRepository.registerAccessMethod(doubleAccessMethodName);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldGetAccessMethodByName() {
        //arrange
        AccessMethodDDDFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodDDDFactoryImpl.class);
        AccessMethodDDDListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodDDDListFactoryImpl.class);
        AccessMethodDDDRepository accessMethodRepository = new AccessMethodDDDRepository (doubleAccessMethodFactoryImpl, doubleAccessMethodListFactoryImpl);
        AccessMethodDDD doubleAccessMethod = mock(AccessMethodDDD.class);
        NameWithNumbersAndSpecialChars doubleAccessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName)).thenReturn(doubleAccessMethod);
        accessMethodRepository.registerAccessMethod(doubleAccessMethodName);
        when(doubleAccessMethod.hasThisAccessMethodName(doubleAccessMethodName)).thenReturn(true);
        //act
        Optional<AccessMethodDDD> optionalAccessMethod = accessMethodRepository.getAccessMethodByName(doubleAccessMethodName);
        AccessMethodDDD result = optionalAccessMethod.get();
        //assert
        assertEquals(doubleAccessMethod, result);
    }

    @Test
    void shouldNotGetAccessMethodByNameIfRepositoryIsEmpty(){
        AccessMethodDDDFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodDDDFactoryImpl.class);
        AccessMethodDDDListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodDDDListFactoryImpl.class);
        AccessMethodDDDRepository accessMethodRepository = new AccessMethodDDDRepository (doubleAccessMethodFactoryImpl, doubleAccessMethodListFactoryImpl);
        NameWithNumbersAndSpecialChars doubleAccessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        //act
        Optional<AccessMethodDDD> optionalAccessMethod = accessMethodRepository.getAccessMethodByName(doubleAccessMethodName);
        //assert
        assertEquals(Optional.empty(),optionalAccessMethod);
    }

    @Test
    void shouldNotGetAccessMethodByNameIfNameNotFound(){
        AccessMethodDDDFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodDDDFactoryImpl.class);
        AccessMethodDDDListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodDDDListFactoryImpl.class);
        AccessMethodDDDRepository accessMethodRepository = new AccessMethodDDDRepository (doubleAccessMethodFactoryImpl, doubleAccessMethodListFactoryImpl);
        AccessMethodDDD doubleAccessMethod = mock(AccessMethodDDD.class);
        NameWithNumbersAndSpecialChars doubleAccessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName)).thenReturn(doubleAccessMethod);
        accessMethodRepository.registerAccessMethod(doubleAccessMethodName);
        when(doubleAccessMethod.hasThisAccessMethodName(doubleAccessMethodName)).thenReturn(false);
        //act
        Optional<AccessMethodDDD> optionalAccessMethod = accessMethodRepository.getAccessMethodByName(doubleAccessMethodName);
        //assert
        assertEquals(Optional.empty(), optionalAccessMethod);
    }


}
package PAI.repository.accessMethodRepositoryDDD;
import PAI.VOs.AccessMethodID;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.domain.accessMethodDDD.AccessMethodDDD;
import PAI.domain.accessMethodDDD.AccessMethodDDDFactoryImpl;
import PAI.domain.accessMethodDDD.IAccessMethodDDDFactory;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
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
    void shouldNotRegisterAccessMethodIfSameIDIsAlreadyRegisteredInTheAccessMethodRepository(){
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
    void shouldNotRegisterAccessMethodIfSameAccessMethodNameIsAlreadyRegistered(){
        //arrange
        IAccessMethodDDDFactory accessMethodFactory = new AccessMethodDDDFactoryImpl();
        IAccessMethodDDDListFactory accessMethodListFactory = new AccessMethodDDDListFactoryImpl();
        AccessMethodDDDRepository accessMethodDDDRepository = new AccessMethodDDDRepository(accessMethodFactory, accessMethodListFactory);
        NameWithNumbersAndSpecialChars accessMethodName1 = new NameWithNumbersAndSpecialChars("Maiores 23");
        accessMethodDDDRepository.registerAccessMethod(accessMethodName1);
        //act
        boolean result = accessMethodDDDRepository.registerAccessMethod(accessMethodName1);
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
        when(doubleAccessMethod.sameAs(doubleAccessMethodName)).thenReturn(false);
        //act
        Optional<AccessMethodDDD> optionalAccessMethod = accessMethodRepository.getAccessMethodByName(doubleAccessMethodName);
        //assert
        assertEquals(Optional.empty(), optionalAccessMethod);
    }

    @Test
    void shouldNotGetAccessMethodByIDIfRepositoryIsEmpty(){
        AccessMethodDDDFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodDDDFactoryImpl.class);
        AccessMethodDDDListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodDDDListFactoryImpl.class);
        AccessMethodDDDRepository accessMethodRepository = new AccessMethodDDDRepository (doubleAccessMethodFactoryImpl, doubleAccessMethodListFactoryImpl);
        AccessMethodID doubleAccessMethodID = mock(AccessMethodID.class);
        //act
        Optional<AccessMethodDDD> optionalAccessMethod = accessMethodRepository.ofIdentity(doubleAccessMethodID);
        //assert
        assertEquals(Optional.empty(),optionalAccessMethod);
    }

    @Test
    void shouldReturnAllAccessMethods(){
        //arrange
        AccessMethodDDDFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodDDDFactoryImpl.class);
        AccessMethodDDDListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodDDDListFactoryImpl.class);
        AccessMethodDDDRepository accessMethodDDDRepository = new AccessMethodDDDRepository(doubleAccessMethodFactoryImpl, doubleAccessMethodListFactoryImpl);
        NameWithNumbersAndSpecialChars doubleAccessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethodDDD doubleAccessMethod = mock(AccessMethodDDD.class);
        AccessMethodDDD doubleAccessMethod2 = mock(AccessMethodDDD.class);
        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName)).thenReturn(doubleAccessMethod);
        accessMethodDDDRepository.registerAccessMethod(doubleAccessMethodName);
        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName)).thenReturn(doubleAccessMethod2);
        accessMethodDDDRepository.registerAccessMethod(doubleAccessMethodName);
        //act
        Iterable <AccessMethodDDD> accessMethods = accessMethodDDDRepository.findAll();
        Iterator<AccessMethodDDD> iterator = accessMethods.iterator();
        AccessMethodDDD get1 = iterator.next();
        AccessMethodDDD get2 = iterator.next();
        //assert
        assertNotNull(get1);
        assertNotNull(get2);
        assertEquals(doubleAccessMethod, get1);
        assertEquals(doubleAccessMethod2, get2);
    }

    @Test
    void shouldReturnOptionalAccessMethodById(){
        //arrange
        AccessMethodDDDFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodDDDFactoryImpl.class);
        AccessMethodDDDListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodDDDListFactoryImpl.class);
        AccessMethodDDDRepository accessMethodDDDRepository = new AccessMethodDDDRepository(doubleAccessMethodFactoryImpl, doubleAccessMethodListFactoryImpl);

        NameWithNumbersAndSpecialChars doubleAccessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethodDDD doubleAccessMethod = mock(AccessMethodDDD.class);
        NameWithNumbersAndSpecialChars doubleAccessMethodName2 = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethodDDD doubleAccessMethod2 = mock(AccessMethodDDD.class);
        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName)).thenReturn(doubleAccessMethod);
        accessMethodDDDRepository.registerAccessMethod(doubleAccessMethodName);

        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName2)).thenReturn(doubleAccessMethod2);
        accessMethodDDDRepository.registerAccessMethod(doubleAccessMethodName2);

        AccessMethodID doubleAccessMethodID = mock(AccessMethodID.class);
        when(doubleAccessMethod.identity()).thenReturn(mock(AccessMethodID.class));
        when(doubleAccessMethod2.identity()).thenReturn(doubleAccessMethodID);
        //act
        Optional<AccessMethodDDD> accessMethod = accessMethodDDDRepository.ofIdentity(doubleAccessMethodID);
        //assert
        assertEquals(Optional.of(doubleAccessMethod2), accessMethod);
    }

    @Test
    void shouldNotReturnOptionalAccessMethodById(){
        //arrange
        AccessMethodDDDFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodDDDFactoryImpl.class);
        AccessMethodDDDListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodDDDListFactoryImpl.class);
        AccessMethodDDDRepository accessMethodDDDRepository = new AccessMethodDDDRepository(doubleAccessMethodFactoryImpl, doubleAccessMethodListFactoryImpl);
        NameWithNumbersAndSpecialChars doubleAccessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethodDDD doubleAccessMethod = mock(AccessMethodDDD.class);
        AccessMethodDDD doubleAccessMethod2 = mock(AccessMethodDDD.class);
        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName)).thenReturn(doubleAccessMethod);
        accessMethodDDDRepository.registerAccessMethod(doubleAccessMethodName);
        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName)).thenReturn(doubleAccessMethod2);
        accessMethodDDDRepository.registerAccessMethod(doubleAccessMethodName);
        AccessMethodID doubleAccessMethodID = mock(AccessMethodID.class);
        when(doubleAccessMethod.identity()).thenReturn(doubleAccessMethodID);
        when(doubleAccessMethod2.identity()).thenReturn(doubleAccessMethodID);
        AccessMethodID doubleAccessMethodID2 = mock(AccessMethodID.class);
        //act
        Optional<AccessMethodDDD> accessMethod = accessMethodDDDRepository.ofIdentity(doubleAccessMethodID2);
        //assert
        assertEquals(Optional.empty(), accessMethod);
    }

    @Test
    void shouldReturnTrueIfAccessMethodExist(){
        //arrange
        AccessMethodDDDFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodDDDFactoryImpl.class);
        AccessMethodDDDListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodDDDListFactoryImpl.class);
        AccessMethodDDDRepository accessMethodDDDRepository = new AccessMethodDDDRepository(doubleAccessMethodFactoryImpl, doubleAccessMethodListFactoryImpl);

        NameWithNumbersAndSpecialChars doubleAccessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethodDDD doubleAccessMethod = mock(AccessMethodDDD.class);
        NameWithNumbersAndSpecialChars doubleAccessMethodName2 = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethodDDD doubleAccessMethod2 = mock(AccessMethodDDD.class);
        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName)).thenReturn(doubleAccessMethod);
        accessMethodDDDRepository.registerAccessMethod(doubleAccessMethodName);

        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName2)).thenReturn(doubleAccessMethod2);
        accessMethodDDDRepository.registerAccessMethod(doubleAccessMethodName2);

        AccessMethodID doubleAccessMethodID = mock(AccessMethodID.class);
        when(doubleAccessMethod.identity()).thenReturn(mock(AccessMethodID.class));
        when(doubleAccessMethod2.identity()).thenReturn(doubleAccessMethodID);

        //act
        boolean result = accessMethodDDDRepository.containsOfIdentity(doubleAccessMethodID);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfAccessDoesNotMethodExist(){
        //arrange
        AccessMethodDDDFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodDDDFactoryImpl.class);
        AccessMethodDDDListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodDDDListFactoryImpl.class);
        AccessMethodDDDRepository accessMethodDDDRepository = new AccessMethodDDDRepository(doubleAccessMethodFactoryImpl, doubleAccessMethodListFactoryImpl);

        NameWithNumbersAndSpecialChars doubleAccessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethodDDD doubleAccessMethod = mock(AccessMethodDDD.class);
        NameWithNumbersAndSpecialChars doubleAccessMethodName2 = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethodDDD doubleAccessMethod2 = mock(AccessMethodDDD.class);
        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName)).thenReturn(doubleAccessMethod);
        accessMethodDDDRepository.registerAccessMethod(doubleAccessMethodName);

        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName2)).thenReturn(doubleAccessMethod2);
        accessMethodDDDRepository.registerAccessMethod(doubleAccessMethodName2);

        AccessMethodID doubleAccessMethodID = mock(AccessMethodID.class);
        when(doubleAccessMethod.identity()).thenReturn(mock(AccessMethodID.class));
        when(doubleAccessMethod2.identity()).thenReturn(mock(AccessMethodID.class));

        //act
        boolean result = accessMethodDDDRepository.containsOfIdentity(doubleAccessMethodID);
        //assert
        assertFalse(result);
    }


}
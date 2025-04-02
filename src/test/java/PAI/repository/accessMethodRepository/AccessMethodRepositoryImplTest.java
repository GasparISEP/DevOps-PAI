package PAI.repository.accessMethodRepository;
import PAI.VOs.AccessMethodID;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.domain.accessMethod.AccessMethod;
import PAI.domain.accessMethod.AccessMethodFactoryImpl;
import PAI.domain.accessMethod.IAccessMethodFactory;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccessMethodRepositoryImplTest {

    @Test
    void shouldCreateAccessMethodRepository(){
        //arrange
        AccessMethodFactoryImpl doubleAccessMethodFactory = mock(AccessMethodFactoryImpl.class);
        AccessMethodListFactoryImpl doubleAccessMethodListFactory = mock(AccessMethodListFactoryImpl.class);
        //act
        AccessMethodRepositoryImpl accessMethodRepository = new AccessMethodRepositoryImpl(doubleAccessMethodFactory, doubleAccessMethodListFactory);
        //assert
        assertNotNull(accessMethodRepository);
    }

    @Test
    void shouldNotRegisterAccessMethodIfSameIDIsAlreadyRegisteredInTheAccessMethodRepository(){
        //arrange
        AccessMethodFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodFactoryImpl.class);
        AccessMethodListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodListFactoryImpl.class);
        AccessMethodRepositoryImpl accessMethodRepository = new AccessMethodRepositoryImpl(doubleAccessMethodFactoryImpl, doubleAccessMethodListFactoryImpl);
        AccessMethod doubleAccessMethod = mock(AccessMethod.class);
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
        IAccessMethodFactory accessMethodFactory = new AccessMethodFactoryImpl();
        IAccessMethodListFactory accessMethodListFactory = new AccessMethodListFactoryImpl();
        AccessMethodRepositoryImpl accessMethodRepositoryImpl = new AccessMethodRepositoryImpl(accessMethodFactory, accessMethodListFactory);
        NameWithNumbersAndSpecialChars accessMethodName1 = new NameWithNumbersAndSpecialChars("Maiores 23");
        accessMethodRepositoryImpl.registerAccessMethod(accessMethodName1);
        //act
        boolean result = accessMethodRepositoryImpl.registerAccessMethod(accessMethodName1);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldRegisterAccessMethodIfNotRegisteredInAccessMethodRepository() {
        //arrange
        AccessMethodFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodFactoryImpl.class);
        AccessMethodListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodListFactoryImpl.class);
        AccessMethodRepositoryImpl accessMethodRepository = new AccessMethodRepositoryImpl(doubleAccessMethodFactoryImpl, doubleAccessMethodListFactoryImpl);
        AccessMethod doubleAccessMethod = mock(AccessMethod.class);
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
        AccessMethodFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodFactoryImpl.class);
        AccessMethodListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodListFactoryImpl.class);
        AccessMethodRepositoryImpl accessMethodRepository = new AccessMethodRepositoryImpl(doubleAccessMethodFactoryImpl, doubleAccessMethodListFactoryImpl);
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
        AccessMethodFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodFactoryImpl.class);
        AccessMethodListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodListFactoryImpl.class);
        AccessMethodRepositoryImpl accessMethodRepository = new AccessMethodRepositoryImpl(doubleAccessMethodFactoryImpl, doubleAccessMethodListFactoryImpl);
        AccessMethod doubleAccessMethod = mock(AccessMethod.class);
        NameWithNumbersAndSpecialChars doubleAccessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName)).thenReturn(doubleAccessMethod);
        accessMethodRepository.registerAccessMethod(doubleAccessMethodName);
        when(doubleAccessMethod.hasThisAccessMethodName(doubleAccessMethodName)).thenReturn(true);
        //act
        Optional<AccessMethod> optionalAccessMethod = accessMethodRepository.getAccessMethodByName(doubleAccessMethodName);
        AccessMethod result = optionalAccessMethod.get();
        //assert
        assertEquals(doubleAccessMethod, result);
    }

    @Test
    void shouldNotGetAccessMethodByNameIfRepositoryIsEmpty(){
        AccessMethodFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodFactoryImpl.class);
        AccessMethodListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodListFactoryImpl.class);
        AccessMethodRepositoryImpl accessMethodRepository = new AccessMethodRepositoryImpl(doubleAccessMethodFactoryImpl, doubleAccessMethodListFactoryImpl);
        NameWithNumbersAndSpecialChars doubleAccessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        //act
        Optional<AccessMethod> optionalAccessMethod = accessMethodRepository.getAccessMethodByName(doubleAccessMethodName);
        //assert
        assertEquals(Optional.empty(),optionalAccessMethod);
    }

    @Test
    void shouldNotGetAccessMethodByNameIfNameNotFound(){
        AccessMethodFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodFactoryImpl.class);
        AccessMethodListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodListFactoryImpl.class);
        AccessMethodRepositoryImpl accessMethodRepository = new AccessMethodRepositoryImpl(doubleAccessMethodFactoryImpl, doubleAccessMethodListFactoryImpl);
        AccessMethod doubleAccessMethod = mock(AccessMethod.class);
        NameWithNumbersAndSpecialChars doubleAccessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName)).thenReturn(doubleAccessMethod);
        accessMethodRepository.registerAccessMethod(doubleAccessMethodName);
        when(doubleAccessMethod.sameAs(doubleAccessMethodName)).thenReturn(false);
        //act
        Optional<AccessMethod> optionalAccessMethod = accessMethodRepository.getAccessMethodByName(doubleAccessMethodName);
        //assert
        assertEquals(Optional.empty(), optionalAccessMethod);
    }

    @Test
    void shouldNotGetAccessMethodByIDIfRepositoryIsEmpty(){
        AccessMethodFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodFactoryImpl.class);
        AccessMethodListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodListFactoryImpl.class);
        AccessMethodRepositoryImpl accessMethodRepository = new AccessMethodRepositoryImpl(doubleAccessMethodFactoryImpl, doubleAccessMethodListFactoryImpl);
        AccessMethodID doubleAccessMethodID = mock(AccessMethodID.class);
        //act
        Optional<AccessMethod> optionalAccessMethod = accessMethodRepository.ofIdentity(doubleAccessMethodID);
        //assert
        assertEquals(Optional.empty(),optionalAccessMethod);
    }

    @Test
    void shouldReturnAllAccessMethods(){
        //arrange
        AccessMethodFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodFactoryImpl.class);
        AccessMethodListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodListFactoryImpl.class);
        AccessMethodRepositoryImpl accessMethodRepositoryImpl = new AccessMethodRepositoryImpl(doubleAccessMethodFactoryImpl, doubleAccessMethodListFactoryImpl);
        NameWithNumbersAndSpecialChars doubleAccessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethod doubleAccessMethod = mock(AccessMethod.class);
        AccessMethod doubleAccessMethod2 = mock(AccessMethod.class);
        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName)).thenReturn(doubleAccessMethod);
        accessMethodRepositoryImpl.registerAccessMethod(doubleAccessMethodName);
        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName)).thenReturn(doubleAccessMethod2);
        accessMethodRepositoryImpl.registerAccessMethod(doubleAccessMethodName);
        //act
        Iterable <AccessMethod> accessMethods = accessMethodRepositoryImpl.findAll();
        Iterator<AccessMethod> iterator = accessMethods.iterator();
        AccessMethod get1 = iterator.next();
        AccessMethod get2 = iterator.next();
        //assert
        assertNotNull(get1);
        assertNotNull(get2);
        assertEquals(doubleAccessMethod, get1);
        assertEquals(doubleAccessMethod2, get2);
    }

    @Test
    void shouldReturnOptionalAccessMethodById(){
        //arrange
        AccessMethodFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodFactoryImpl.class);
        AccessMethodListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodListFactoryImpl.class);
        AccessMethodRepositoryImpl accessMethodRepositoryImpl = new AccessMethodRepositoryImpl(doubleAccessMethodFactoryImpl, doubleAccessMethodListFactoryImpl);

        NameWithNumbersAndSpecialChars doubleAccessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethod doubleAccessMethod = mock(AccessMethod.class);
        NameWithNumbersAndSpecialChars doubleAccessMethodName2 = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethod doubleAccessMethod2 = mock(AccessMethod.class);
        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName)).thenReturn(doubleAccessMethod);
        accessMethodRepositoryImpl.registerAccessMethod(doubleAccessMethodName);

        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName2)).thenReturn(doubleAccessMethod2);
        accessMethodRepositoryImpl.registerAccessMethod(doubleAccessMethodName2);

        AccessMethodID doubleAccessMethodID = mock(AccessMethodID.class);
        when(doubleAccessMethod.identity()).thenReturn(mock(AccessMethodID.class));
        when(doubleAccessMethod2.identity()).thenReturn(doubleAccessMethodID);
        //act
        Optional<AccessMethod> accessMethod = accessMethodRepositoryImpl.ofIdentity(doubleAccessMethodID);
        //assert
        assertEquals(Optional.of(doubleAccessMethod2), accessMethod);
    }

    @Test
    void shouldNotReturnOptionalAccessMethodById(){
        //arrange
        AccessMethodFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodFactoryImpl.class);
        AccessMethodListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodListFactoryImpl.class);
        AccessMethodRepositoryImpl accessMethodRepositoryImpl = new AccessMethodRepositoryImpl(doubleAccessMethodFactoryImpl, doubleAccessMethodListFactoryImpl);
        NameWithNumbersAndSpecialChars doubleAccessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethod doubleAccessMethod = mock(AccessMethod.class);
        AccessMethod doubleAccessMethod2 = mock(AccessMethod.class);
        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName)).thenReturn(doubleAccessMethod);
        accessMethodRepositoryImpl.registerAccessMethod(doubleAccessMethodName);
        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName)).thenReturn(doubleAccessMethod2);
        accessMethodRepositoryImpl.registerAccessMethod(doubleAccessMethodName);
        AccessMethodID doubleAccessMethodID = mock(AccessMethodID.class);
        when(doubleAccessMethod.identity()).thenReturn(doubleAccessMethodID);
        when(doubleAccessMethod2.identity()).thenReturn(doubleAccessMethodID);
        AccessMethodID doubleAccessMethodID2 = mock(AccessMethodID.class);
        //act
        Optional<AccessMethod> accessMethod = accessMethodRepositoryImpl.ofIdentity(doubleAccessMethodID2);
        //assert
        assertEquals(Optional.empty(), accessMethod);
    }

    @Test
    void shouldReturnTrueIfAccessMethodExist(){
        //arrange
        AccessMethodFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodFactoryImpl.class);
        AccessMethodListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodListFactoryImpl.class);
        AccessMethodRepositoryImpl accessMethodRepositoryImpl = new AccessMethodRepositoryImpl(doubleAccessMethodFactoryImpl, doubleAccessMethodListFactoryImpl);

        NameWithNumbersAndSpecialChars doubleAccessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethod doubleAccessMethod = mock(AccessMethod.class);
        NameWithNumbersAndSpecialChars doubleAccessMethodName2 = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethod doubleAccessMethod2 = mock(AccessMethod.class);
        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName)).thenReturn(doubleAccessMethod);
        accessMethodRepositoryImpl.registerAccessMethod(doubleAccessMethodName);

        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName2)).thenReturn(doubleAccessMethod2);
        accessMethodRepositoryImpl.registerAccessMethod(doubleAccessMethodName2);

        AccessMethodID doubleAccessMethodID = mock(AccessMethodID.class);
        when(doubleAccessMethod.identity()).thenReturn(mock(AccessMethodID.class));
        when(doubleAccessMethod2.identity()).thenReturn(doubleAccessMethodID);

        //act
        boolean result = accessMethodRepositoryImpl.containsOfIdentity(doubleAccessMethodID);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfAccessDoesNotMethodExist(){
        //arrange
        AccessMethodFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodFactoryImpl.class);
        AccessMethodListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodListFactoryImpl.class);
        AccessMethodRepositoryImpl accessMethodRepositoryImpl = new AccessMethodRepositoryImpl(doubleAccessMethodFactoryImpl, doubleAccessMethodListFactoryImpl);

        NameWithNumbersAndSpecialChars doubleAccessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethod doubleAccessMethod = mock(AccessMethod.class);
        NameWithNumbersAndSpecialChars doubleAccessMethodName2 = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethod doubleAccessMethod2 = mock(AccessMethod.class);
        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName)).thenReturn(doubleAccessMethod);
        accessMethodRepositoryImpl.registerAccessMethod(doubleAccessMethodName);

        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName2)).thenReturn(doubleAccessMethod2);
        accessMethodRepositoryImpl.registerAccessMethod(doubleAccessMethodName2);

        AccessMethodID doubleAccessMethodID = mock(AccessMethodID.class);
        when(doubleAccessMethod.identity()).thenReturn(mock(AccessMethodID.class));
        when(doubleAccessMethod2.identity()).thenReturn(mock(AccessMethodID.class));

        //act
        boolean result = accessMethodRepositoryImpl.containsOfIdentity(doubleAccessMethodID);
        //assert
        assertFalse(result);
    }


}
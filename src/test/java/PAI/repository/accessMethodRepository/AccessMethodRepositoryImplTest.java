package PAI.repository.accessMethodRepository;
import PAI.VOs.AccessMethodID;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.domain.accessMethod.AccessMethod;
import PAI.domain.accessMethod.AccessMethodFactoryImpl;
import PAI.domain.accessMethod.IAccessMethodFactory;
import PAI.persistence.mem.accessMethod.AccessMethodListFactoryImpl;
import PAI.persistence.mem.accessMethod.AccessMethodRepositoryImpl;
import PAI.persistence.mem.accessMethod.IAccessMethodListFactory;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccessMethodRepositoryImplTest {

    @Test
    void shouldCreateAccessMethodRepository(){
        //arrange
        AccessMethodListFactoryImpl doubleAccessMethodListFactory = mock(AccessMethodListFactoryImpl.class);
        //act
        AccessMethodRepositoryImpl accessMethodRepository = new AccessMethodRepositoryImpl(doubleAccessMethodListFactory);
        //assert
        assertNotNull(accessMethodRepository);
    }

    @Test
    void shouldRegisterAccessMethodSuccessfully() {
        //arrange
        AccessMethodListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodListFactoryImpl.class);
        AccessMethodRepositoryImpl accessMethodRepository = new AccessMethodRepositoryImpl(doubleAccessMethodListFactoryImpl);
        AccessMethod accessMethod = mock(AccessMethod.class);
        //act
        Optional<AccessMethod> result = accessMethodRepository.saveAccessMethod(accessMethod);
        //assert
        assertTrue(result.isPresent());
    }

    @Test
    void shouldNotRegisterAccessMethodIfSameAccessMethodIsAlreadyRegisteredInTheRepository() {
        // arrange
        IAccessMethodListFactory accessMethodListFactory = mock(IAccessMethodListFactory.class);
        AccessMethodRepositoryImpl accessMethodRepository = new AccessMethodRepositoryImpl(accessMethodListFactory);

        AccessMethod firstAccessMethod = mock(AccessMethod.class);
        accessMethodRepository.saveAccessMethod(firstAccessMethod);

        AccessMethod secondAccessMethod = mock(AccessMethod.class);

        when(firstAccessMethod.sameAs(secondAccessMethod)).thenReturn(true);

        // act
        Optional<AccessMethod> result = accessMethodRepository.saveAccessMethod(secondAccessMethod);

        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldNotRegisterIfAccessMethodProvidedIsNull() {
        //arrange
        AccessMethodListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodListFactoryImpl.class);
        AccessMethodRepositoryImpl accessMethodRepository = new AccessMethodRepositoryImpl(doubleAccessMethodListFactoryImpl);
        //act
        Optional<AccessMethod> result = accessMethodRepository.saveAccessMethod(null);
        //assert
        assertFalse(result.isPresent());
    }

    @Test
    void shouldGetAccessMethodByName() {
        //arrange
        AccessMethodFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodFactoryImpl.class);
        AccessMethodListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodListFactoryImpl.class);
        AccessMethodRepositoryImpl accessMethodRepository = new AccessMethodRepositoryImpl(doubleAccessMethodListFactoryImpl);
        AccessMethod doubleAccessMethod = mock(AccessMethod.class);
        NameWithNumbersAndSpecialChars doubleAccessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName)).thenReturn(doubleAccessMethod);
        accessMethodRepository.saveAccessMethod(doubleAccessMethod);
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
        AccessMethodRepositoryImpl accessMethodRepository = new AccessMethodRepositoryImpl(doubleAccessMethodListFactoryImpl);
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
        AccessMethodRepositoryImpl accessMethodRepository = new AccessMethodRepositoryImpl(doubleAccessMethodListFactoryImpl);
        AccessMethod doubleAccessMethod = mock(AccessMethod.class);
        NameWithNumbersAndSpecialChars doubleAccessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName)).thenReturn(doubleAccessMethod);
        accessMethodRepository.saveAccessMethod(doubleAccessMethod);
        when(doubleAccessMethod.sameAs(doubleAccessMethodName)).thenReturn(false);
        //act
        Optional<AccessMethod> optionalAccessMethod = accessMethodRepository.getAccessMethodByName(doubleAccessMethodName);
        //assert
        assertEquals(Optional.empty(), optionalAccessMethod);
    }

    @Test
    void shouldNotGetAccessMethodByIDIfRepositoryIsEmpty(){
        AccessMethodListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodListFactoryImpl.class);
        AccessMethodRepositoryImpl accessMethodRepository = new AccessMethodRepositoryImpl(doubleAccessMethodListFactoryImpl);
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
        AccessMethodRepositoryImpl accessMethodRepositoryImpl = new AccessMethodRepositoryImpl(doubleAccessMethodListFactoryImpl);
        NameWithNumbersAndSpecialChars doubleAccessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethod doubleAccessMethod = mock(AccessMethod.class);
        AccessMethod doubleAccessMethod2 = mock(AccessMethod.class);
        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName)).thenReturn(doubleAccessMethod);
        accessMethodRepositoryImpl.saveAccessMethod(doubleAccessMethod);
        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName)).thenReturn(doubleAccessMethod2);
        accessMethodRepositoryImpl.saveAccessMethod(doubleAccessMethod2);
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
        AccessMethodRepositoryImpl accessMethodRepositoryImpl = new AccessMethodRepositoryImpl(doubleAccessMethodListFactoryImpl);

        NameWithNumbersAndSpecialChars doubleAccessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethod doubleAccessMethod = mock(AccessMethod.class);
        NameWithNumbersAndSpecialChars doubleAccessMethodName2 = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethod doubleAccessMethod2 = mock(AccessMethod.class);
        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName)).thenReturn(doubleAccessMethod);
        accessMethodRepositoryImpl.saveAccessMethod(doubleAccessMethod);

        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName2)).thenReturn(doubleAccessMethod2);
        accessMethodRepositoryImpl.saveAccessMethod(doubleAccessMethod2);

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
        AccessMethodRepositoryImpl accessMethodRepositoryImpl = new AccessMethodRepositoryImpl(doubleAccessMethodListFactoryImpl);
        NameWithNumbersAndSpecialChars doubleAccessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethod doubleAccessMethod = mock(AccessMethod.class);
        AccessMethod doubleAccessMethod2 = mock(AccessMethod.class);
        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName)).thenReturn(doubleAccessMethod);
        accessMethodRepositoryImpl.saveAccessMethod(doubleAccessMethod);
        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName)).thenReturn(doubleAccessMethod2);
        accessMethodRepositoryImpl.saveAccessMethod(doubleAccessMethod2);
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
        AccessMethodRepositoryImpl accessMethodRepositoryImpl = new AccessMethodRepositoryImpl(doubleAccessMethodListFactoryImpl);

        NameWithNumbersAndSpecialChars doubleAccessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethod doubleAccessMethod = mock(AccessMethod.class);
        NameWithNumbersAndSpecialChars doubleAccessMethodName2 = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethod doubleAccessMethod2 = mock(AccessMethod.class);
        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName)).thenReturn(doubleAccessMethod);
        accessMethodRepositoryImpl.saveAccessMethod(doubleAccessMethod);

        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName2)).thenReturn(doubleAccessMethod2);
        accessMethodRepositoryImpl.saveAccessMethod(doubleAccessMethod2);

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
        AccessMethodRepositoryImpl accessMethodRepositoryImpl = new AccessMethodRepositoryImpl(doubleAccessMethodListFactoryImpl);

        NameWithNumbersAndSpecialChars doubleAccessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethod doubleAccessMethod = mock(AccessMethod.class);
        NameWithNumbersAndSpecialChars doubleAccessMethodName2 = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethod doubleAccessMethod2 = mock(AccessMethod.class);
        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName)).thenReturn(doubleAccessMethod);
        accessMethodRepositoryImpl.saveAccessMethod(doubleAccessMethod);

        when(doubleAccessMethodFactoryImpl.createAccessMethod(doubleAccessMethodName2)).thenReturn(doubleAccessMethod2);
        accessMethodRepositoryImpl.saveAccessMethod(doubleAccessMethod2);

        AccessMethodID doubleAccessMethodID = mock(AccessMethodID.class);
        when(doubleAccessMethod.identity()).thenReturn(mock(AccessMethodID.class));
        when(doubleAccessMethod2.identity()).thenReturn(mock(AccessMethodID.class));

        //act
        boolean result = accessMethodRepositoryImpl.containsOfIdentity(doubleAccessMethodID);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldSaveAccessMethod(){
        //arrange
        AccessMethodListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodListFactoryImpl.class);
        AccessMethodRepositoryImpl accessMethodRepositoryImpl = new AccessMethodRepositoryImpl(doubleAccessMethodListFactoryImpl);

        AccessMethod doubleAccessMethod = mock(AccessMethod.class);
        //act
        AccessMethod result = accessMethodRepositoryImpl.save(doubleAccessMethod);
        //assert
        assertNotNull(result);
        assertEquals(doubleAccessMethod, result);
    }
}
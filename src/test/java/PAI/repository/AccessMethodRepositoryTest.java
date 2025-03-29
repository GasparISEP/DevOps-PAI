package PAI.repository;

import PAI.domain.AccessMethod;
import PAI.factory.AccessMethodFactoryImpl;
import PAI.factory.AccessMethodListFactoryImpl;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccessMethodRepositoryTest {


    @Test
    void shouldNotRegisterAccessMethodIfIsAlreadyRegisteredInTheAccessMethodRepository() throws Exception {
        //arrange
        AccessMethodFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodFactoryImpl.class);
        AccessMethodListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodListFactoryImpl.class);
        AccessMethodRepository accessMethodRepository = new AccessMethodRepository (doubleAccessMethodFactoryImpl, doubleAccessMethodListFactoryImpl);
        AccessMethod doubleAccessMethod = mock(AccessMethod.class);
        when(doubleAccessMethodFactoryImpl.createAccessMethod("Maiores 23")).thenReturn(doubleAccessMethod);
        accessMethodRepository.registerAccessMethod("Maiores 23");
        //act
        boolean result = accessMethodRepository.registerAccessMethod("Maiores 23");
        //assert
        assertFalse(result);
    }

    @Test
    void shouldRegisterAccessMethodIfNotRegisteredInAccessMethodRepository() throws Exception {
        //arrange
        AccessMethodFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodFactoryImpl.class);
        AccessMethodListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodListFactoryImpl.class);
        AccessMethodRepository accessMethodRepository = new AccessMethodRepository (doubleAccessMethodFactoryImpl, doubleAccessMethodListFactoryImpl);
        AccessMethod doubleAccessMethod = mock(AccessMethod.class);
        when(doubleAccessMethodFactoryImpl.createAccessMethod("Maiores 23")).thenReturn(doubleAccessMethod);
        //act
        boolean result = accessMethodRepository.registerAccessMethod("Maiores 23");
        //assert
        assertTrue(result);
    }

    @Test
    void testAccessMethodListCreationValid() {
        //arrange
        AccessMethodFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodFactoryImpl.class);
        AccessMethodListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodListFactoryImpl.class);
        //act
        AccessMethodRepository accessMethodRepository = new AccessMethodRepository(doubleAccessMethodFactoryImpl, doubleAccessMethodListFactoryImpl);
        //assert
        assertNotNull(accessMethodRepository);
    }

    @Test
    void shouldReturnTrueIfAccessMethodIsRegistered() throws Exception {
        //arrange
        AccessMethodFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodFactoryImpl.class);
        AccessMethodListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodListFactoryImpl.class);
        AccessMethodRepository accessMethodRepository = new AccessMethodRepository(doubleAccessMethodFactoryImpl, doubleAccessMethodListFactoryImpl);
        AccessMethod doubleAccessMethod = mock(AccessMethod.class);

        when(doubleAccessMethodFactoryImpl.createAccessMethod("Maiores 23")).thenReturn(doubleAccessMethod);
        //act
        boolean result = accessMethodRepository.registerAccessMethod("Maiores 23");
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfAccessMethodCannotBeRegistered() throws Exception {
        //arrange
        AccessMethodFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodFactoryImpl.class);
        AccessMethodListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodListFactoryImpl.class);
        AccessMethodRepository accessMethodRepository = new AccessMethodRepository(doubleAccessMethodFactoryImpl, doubleAccessMethodListFactoryImpl);
        AccessMethod doubleAccessMethod = mock(AccessMethod.class);
        when(doubleAccessMethodFactoryImpl.createAccessMethod("Maiores 23")).thenReturn(doubleAccessMethod);
        accessMethodRepository.registerAccessMethod("Maiores 23");
        //act
        boolean result = accessMethodRepository.registerAccessMethod("Maiores 23");
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfAccessMethodNameIsInvalid() throws Exception{
        //arrange
        AccessMethodFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodFactoryImpl.class);
        AccessMethodListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodListFactoryImpl.class);
        AccessMethodRepository accessMethodRepository = new AccessMethodRepository(doubleAccessMethodFactoryImpl, doubleAccessMethodListFactoryImpl);
        when(doubleAccessMethodFactoryImpl.createAccessMethod("")).thenThrow(new InstantiationException("Cannot create AccessMethod, because invalid name"));
        //act
        boolean result = accessMethodRepository.registerAccessMethod("");
        //asser
        assertFalse(result);
    }

    @Test
    void shouldGetAccessMethodByName() throws InstantiationException {
        //arrange
        AccessMethodFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodFactoryImpl.class);
        AccessMethodListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodListFactoryImpl.class);
        AccessMethodRepository accessMethodRepository = new AccessMethodRepository(doubleAccessMethodFactoryImpl, doubleAccessMethodListFactoryImpl);
        AccessMethod doubleAccessMethod = mock(AccessMethod.class);
        when(doubleAccessMethodFactoryImpl.createAccessMethod("Maiores 23")).thenReturn(doubleAccessMethod);
        accessMethodRepository.registerAccessMethod("Maiores 23");
        when(doubleAccessMethod.hasThisAccessMethodName("Maiores 23")).thenReturn(true);
        //act
        Optional<AccessMethod> optionalAccessMethod = accessMethodRepository.getAccessMethodByName("Maiores 23");
        AccessMethod result = optionalAccessMethod.get();
        //assert
        assertEquals(doubleAccessMethod,result);
    }

    @Test
    void shouldNotGetAccessMethodByNameIfNotInRepository() throws InstantiationException {
        //arrange
        AccessMethodFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodFactoryImpl.class);
        AccessMethodListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodListFactoryImpl.class);
        AccessMethodRepository accessMethodRepository = new AccessMethodRepository(doubleAccessMethodFactoryImpl, doubleAccessMethodListFactoryImpl);
        AccessMethod doubleAccessMethod = mock(AccessMethod.class);
        when(doubleAccessMethodFactoryImpl.createAccessMethod("Maiores 23")).thenReturn(doubleAccessMethod);
        accessMethodRepository.registerAccessMethod("Maiores 23");
        when(doubleAccessMethod.hasThisAccessMethodName("Maiores 23")).thenReturn(false);
        //act
        Optional<AccessMethod> optionalAccessMethod = accessMethodRepository.getAccessMethodByName("Maiores 23");

        //assert
        assertEquals(Optional.empty(),optionalAccessMethod);
    }

//    @Test
//    void shouldReturnsOptionalWithAccessMethodIfCanBeCreated() throws InstantiationException {
//        // arrange
//        AccessMethodFactory doubleAccessMethodFactory = mock(AccessMethodFactory.class);
//        AccessMethodArrayListFactory doubleAccessMethodArrayListFactory = mock(AccessMethodArrayListFactory.class);
//        AccessMethodRepository accessMethodRepository = new AccessMethodRepository(doubleAccessMethodFactory, doubleAccessMethodArrayListFactory);
//        AccessMethod doubleAccessMethod = mock(AccessMethod.class);
//        when(doubleAccessMethodFactory.createAccessMethod("Maiores 23")).thenReturn(doubleAccessMethod);
//
//        // act
//        Optional<AccessMethod> result = accessMethodRepository.createAccessMethod("Maiores 23");
//
//        // assert
//        assertTrue(result.isPresent(), "Expected Optional to be present");
//    }
//
//    @Test
//    void shouldReturnEmptyOptionalIfAccessMethodAlreadyRegistered() throws InstantiationException {
//        // arrange
//        AccessMethodFactory doubleAccessMethodFactory = mock(AccessMethodFactory.class);
//        AccessMethodArrayListFactory doubleAccessMethodArrayListFactory = mock(AccessMethodArrayListFactory.class);
//        AccessMethodRepository accessMethodRepository = new AccessMethodRepository(doubleAccessMethodFactory, doubleAccessMethodArrayListFactory);
//        AccessMethod doubleAccessMethod = mock(AccessMethod.class);
//        when(doubleAccessMethodFactory.createAccessMethod("Maiores 23")).thenReturn(doubleAccessMethod);
//        accessMethodRepository.createAccessMethod("Maiores 23");
//
//        // act
//        Optional<AccessMethod> result = accessMethodRepository.createAccessMethod("Maiores 23");
//
//        // assert
//        assertFalse(result.isPresent(), "Expected Optional to be empty");
//    }
//
//    @Test
//    void shouldReturnEmptyOptionalIfAccessMethodCannotBeCreated() throws InstantiationException {
//        // arrange
//        AccessMethodFactory doubleAccessMethodFactory = mock(AccessMethodFactory.class);
//        AccessMethodArrayListFactory doubleAccessMethodArrayListFactory = mock(AccessMethodArrayListFactory.class);
//        AccessMethodRepository accessMethodRepository = new AccessMethodRepository(doubleAccessMethodFactory, doubleAccessMethodArrayListFactory);
//        when(doubleAccessMethodFactory.createAccessMethod("")).thenThrow(new InstantiationException("Cannot create AccessMethod"));
//
//        // act
//        Optional<AccessMethod> result = accessMethodRepository.createAccessMethod("");
//
//        // assert
//        assertFalse(result.isPresent(), "Expected Optional to be empty");
//    }
}

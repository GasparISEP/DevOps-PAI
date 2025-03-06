package PAI.repository;

import PAI.domain.AccessMethod;
import PAI.factory.AccessMethodFactory;
import PAI.factory.AccessMethodArrayListFactory;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccessMethodRepositoryTest {


    @Test
    void shouldReturnTrueIfIsAlreadyRegisteredInTheAccessMethodRepository() throws Exception {
        //arrange
        AccessMethodFactory doubleAccessMethodFactory = mock(AccessMethodFactory.class);
        AccessMethodArrayListFactory doubleAccessMethodArrayListFactory = mock(AccessMethodArrayListFactory.class);
        AccessMethodRepository accessMethodRepository = new AccessMethodRepository (doubleAccessMethodFactory, doubleAccessMethodArrayListFactory);
        AccessMethod doubleAccessMethod = mock(AccessMethod.class);
        when(doubleAccessMethodFactory.createAccessMethod("Maiores 23")).thenReturn(doubleAccessMethod);
        accessMethodRepository.registerAccessMethod("Maiores 23");
        //act
        boolean result = accessMethodRepository.isAccessMethodRegistered(doubleAccessMethod);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfAccessMethodIsNotRegisteredInAccessMethodRepository() throws Exception {
        //arrange
        AccessMethodFactory doubleAccessMethodFactory = mock(AccessMethodFactory.class);
        AccessMethodArrayListFactory doubleAccessMethodArrayListFactory = mock(AccessMethodArrayListFactory.class);
        AccessMethodRepository accessMethodRepository = new AccessMethodRepository (doubleAccessMethodFactory, doubleAccessMethodArrayListFactory);
        AccessMethod doubleAccessMethod1 = mock(AccessMethod.class);
        AccessMethod doubleAccessMethod2 = mock(AccessMethod.class);
        when(doubleAccessMethodFactory.createAccessMethod("Maiores 23")).thenReturn(doubleAccessMethod1);
        accessMethodRepository.registerAccessMethod("Maiores 23");
        //act
        boolean result = accessMethodRepository.isAccessMethodRegistered(doubleAccessMethod2);
        //assert
        assertFalse(result);
    }

    @Test
    void testAccessMethodListCreationValid() {
        //arrange
        AccessMethodFactory doubleAccessMethodFactory = mock(AccessMethodFactory.class);
        AccessMethodArrayListFactory doubleAccessMethodArrayListFactory = mock(AccessMethodArrayListFactory.class);
        //act
        AccessMethodRepository accessMethodRepository = new AccessMethodRepository(doubleAccessMethodFactory, doubleAccessMethodArrayListFactory);
        //assert
        assertNotNull(accessMethodRepository);
    }

    @Test
    void shouldReturnTrueIfAccessMethodIsRegistered() throws Exception {
        //arrange
        AccessMethodFactory doubleAccessMethodFactory = mock(AccessMethodFactory.class);
        AccessMethodArrayListFactory doubleAccessMethodArrayListFactory = mock(AccessMethodArrayListFactory.class);
        AccessMethodRepository accessMethodRepository = new AccessMethodRepository(doubleAccessMethodFactory, doubleAccessMethodArrayListFactory);
        AccessMethod doubleAccessMethod = mock(AccessMethod.class);

        when(doubleAccessMethodFactory.createAccessMethod("Maiores 23")).thenReturn(doubleAccessMethod);
        //act
        boolean result = accessMethodRepository.registerAccessMethod("Maiores 23");
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfAccessMethodCannotBeRegistered() throws Exception {
        //arrange
        AccessMethodFactory doubleAccessMethodFactory = mock(AccessMethodFactory.class);
        AccessMethodArrayListFactory doubleAccessMethodArrayListFactory = mock(AccessMethodArrayListFactory.class);
        AccessMethodRepository accessMethodRepository = new AccessMethodRepository(doubleAccessMethodFactory, doubleAccessMethodArrayListFactory);
        AccessMethod doubleAccessMethod = mock(AccessMethod.class);
        when(doubleAccessMethodFactory.createAccessMethod("Maiores 23")).thenReturn(doubleAccessMethod);
        accessMethodRepository.registerAccessMethod("Maiores 23");
        //act
        boolean result = accessMethodRepository.registerAccessMethod("Maiores 23");
        //assert
        assertFalse(result);
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

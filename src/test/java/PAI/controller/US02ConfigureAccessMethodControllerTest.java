package PAI.controller;

import PAI.domain.AccessMethod;
import PAI.factory.AccessMethodFactoryImpl;
import PAI.factory.AccessMethodListFactoryImpl;
import PAI.repository.AccessMethodRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US02ConfigureAccessMethodControllerTest {

    @Test
    void shouldCreateThisController() {
        //arrange
        AccessMethodRepository doubleAccessMethodRepository = mock(AccessMethodRepository.class);
        //act
        US02_ConfigureAccessMethodController ctrl1 = new US02_ConfigureAccessMethodController(doubleAccessMethodRepository);
        //assert
        assertNotNull(ctrl1);

    }
    @Test
    void shouldNotCreateThisController() {
        //arrange
        //act
        US02_ConfigureAccessMethodController ctrl1 = new US02_ConfigureAccessMethodController(null);
        //assert
        assertNotNull(ctrl1);
    }

    @Test
    void shouldNotConfigureAccessMethodIfAccessMethodRepositoryIsNull() throws Exception {
        //arrange
        AccessMethodFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodFactoryImpl.class);
        AccessMethod doubleAccessMethod = mock(AccessMethod.class);
        when(doubleAccessMethodFactoryImpl.createAccessMethod("Maiores 23")).thenReturn(doubleAccessMethod);
        //act
        US02_ConfigureAccessMethodController ctrl1 = new US02_ConfigureAccessMethodController(null);

        boolean isConfigured = ctrl1.configureAccessMethod("Maiores 23");
        //assert
        assertFalse(isConfigured);
    }

    @Test
    void shouldConfigureAnAccessMethod() throws Exception {
        //arrange
        AccessMethodListFactoryImpl doubleAccessMethodListFactoryImpl = mock(AccessMethodListFactoryImpl.class);
        AccessMethodFactoryImpl doubleAccessMethodFactoryImpl = mock(AccessMethodFactoryImpl.class);
        AccessMethodRepository accessMethodRepository = new AccessMethodRepository(doubleAccessMethodFactoryImpl, doubleAccessMethodListFactoryImpl);
        AccessMethod doubleAccessMethod = mock(AccessMethod.class);
        when(doubleAccessMethodFactoryImpl.createAccessMethod("Maiores 23")).thenReturn(doubleAccessMethod);
        US02_ConfigureAccessMethodController ctrl1 = new US02_ConfigureAccessMethodController(accessMethodRepository);
        //act
        boolean isConfigured = ctrl1.configureAccessMethod("Maiores 23");
        //assert
        assertTrue(isConfigured);
    }

    @Test
    void shouldNotConfigureAnAccessMethodIfAccessMethodNameIsNull() throws Exception {
        //arrange
        AccessMethodRepository doubleAccessMethodRepository = mock(AccessMethodRepository.class);
        US02_ConfigureAccessMethodController ctrl1 = new US02_ConfigureAccessMethodController(doubleAccessMethodRepository);
        //act
        boolean isConfigured = ctrl1.configureAccessMethod(null);
        //assert
        assertFalse(isConfigured);
    }
}
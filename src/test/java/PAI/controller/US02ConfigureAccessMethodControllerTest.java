package PAI.controller;

import PAI.domain.AccessMethod;
import PAI.domain.AccessMethodFactory;
import PAI.domain.AccessMethodRepository;
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
    void shouldNotConfigureAccessMethodIfAccessMethodRepositoryIsNull() throws InstantiationException {
        //arrange
        AccessMethodFactory doubleAccessMethodFactory = mock(AccessMethodFactory.class);
        AccessMethod doubleAccessMethod = mock(AccessMethod.class);
        when(doubleAccessMethodFactory.createAccessMethod("Maiores 23")).thenReturn(doubleAccessMethod);
        //act
        US02_ConfigureAccessMethodController ctrl1 = new US02_ConfigureAccessMethodController(null);

        boolean isConfigure = ctrl1.configureAccessMethod("Maiores 23");
        //assert
        assertFalse(isConfigure);
    }

    @Test
    void shouldConfigureAnAccessMethod() throws InstantiationException {
        //arrange
        AccessMethodFactory doubleAccessMethodFactory = mock(AccessMethodFactory.class);
        AccessMethodRepository accessMethodRepository = new AccessMethodRepository(doubleAccessMethodFactory);
        AccessMethod doubleAccessMethod = mock(AccessMethod.class);
        when(doubleAccessMethodFactory.createAccessMethod("Maiores 23")).thenReturn(doubleAccessMethod);
        US02_ConfigureAccessMethodController ctrl1 = new US02_ConfigureAccessMethodController(accessMethodRepository);
        //act
        boolean isConfigured = ctrl1.configureAccessMethod("Maiores 23");
        //assert
        assertTrue(isConfigured);
    }

    @Test
    void shouldNotConfigureAnAccessMethod(){
        //arrange
        AccessMethodFactory accessMethodFactory = new AccessMethodFactory();
        AccessMethodRepository amr1 = new AccessMethodRepository(accessMethodFactory);
        US02_ConfigureAccessMethodController ctrl1 = new US02_ConfigureAccessMethodController(amr1);
        //act
        boolean isConfigured = ctrl1.configureAccessMethod(null);
        //assert
        assertFalse(isConfigured);
    }
}
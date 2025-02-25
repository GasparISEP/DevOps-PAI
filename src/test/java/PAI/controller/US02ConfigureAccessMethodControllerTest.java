package PAI.controller;

import PAI.domain.AccessMethodFactory;
import PAI.domain.AccessMethodRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

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
        AccessMethodRepository doubleAccessMethodRepository = mock(AccessMethodRepository.class);
        //act
        US02_ConfigureAccessMethodController ctrl1 = new US02_ConfigureAccessMethodController(null);
        //assert
        assertNotNull(ctrl1);
    }

    @Test
    void shouldConfigureAccessMethod(){
        //arrange
        //act
        US02_ConfigureAccessMethodController ctrl1 = new US02_ConfigureAccessMethodController(null);

        boolean isConfigure = ctrl1.configureAccessMethod("BlaBla");
        //assert
        assertFalse(isConfigure);
    }
    @Test
    void shouldConfigureAnAccessMethod(){
        //arrange
        AccessMethodFactory accessMethodFactory = new AccessMethodFactory();
        AccessMethodRepository amr1 = new AccessMethodRepository(accessMethodFactory);
        US02_ConfigureAccessMethodController ctrl1 = new US02_ConfigureAccessMethodController(amr1);
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
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
        AccessMethodFactory doubleAccessMethodFactory = mock(AccessMethodFactory.class);
        AccessMethodRepository accessMethodRepository = new AccessMethodRepository(doubleAccessMethodFactory);
        //act
        US02_ConfigureAccessMethodController ctrl1 = new US02_ConfigureAccessMethodController(accessMethodRepository);
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
        AccessMethodFactory doubleAccessMethodFactory = mock(AccessMethodFactory.class);
        AccessMethodRepository amr1 = new AccessMethodRepository(doubleAccessMethodFactory);
        US02_ConfigureAccessMethodController ctrl1 = new US02_ConfigureAccessMethodController(amr1);
        //act
        boolean isConfigured = ctrl1.configureAccessMethod("Maiores 23");
        //assert
        assertTrue(isConfigured);
    }

    @Test
    void shouldNotConfigureAnAccessMethod(){
        //arrange
        AccessMethodFactory doubleAccessMethodFactory = mock(AccessMethodFactory.class);
        AccessMethodRepository amr1 = new AccessMethodRepository(doubleAccessMethodFactory);
        US02_ConfigureAccessMethodController ctrl1 = new US02_ConfigureAccessMethodController(amr1);
        //act
        boolean isConfigured = ctrl1.configureAccessMethod(null);
        //assert
        assertFalse(isConfigured);
    }
}
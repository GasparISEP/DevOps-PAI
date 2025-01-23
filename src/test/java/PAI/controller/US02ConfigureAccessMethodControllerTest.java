package PAI.controller;

import PAI.domain.AccessMethodRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class US02ConfigureAccessMethodControllerTest {

    @Test
    void shouldCreateThisController() {
        //arrange
        AccessMethodRepository amr1 = new AccessMethodRepository();
        //act
        US02_ConfigureAccessMethodController ctrl1 = new US02_ConfigureAccessMethodController(amr1);
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
        AccessMethodRepository amr1 = new AccessMethodRepository();
        US02_ConfigureAccessMethodController ctrl1 = new US02_ConfigureAccessMethodController(amr1);
        //act
        boolean isConfigured = ctrl1.configureAccessMethod("Maiores 23");
        //assert
        assertTrue(isConfigured);
    }

    @Test
    void shouldNotConfigureAnAccessMethod(){
        //arrange
        AccessMethodRepository amr1 = new AccessMethodRepository();
        US02_ConfigureAccessMethodController ctrl1 = new US02_ConfigureAccessMethodController(amr1);
        //act
        boolean isConfigured = ctrl1.configureAccessMethod(null);
        //assert
        assertFalse(isConfigured);
    }
}
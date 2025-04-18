package PAI.controller;

import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.domain.accessMethod.AccessMethod;
import PAI.service.accessMethod.IAccessMethodService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US02ConfigureAccessMethodControllerTest {

    @Test
    void shouldCreateThisController() {
        //arrange
        IAccessMethodService accessMethodService = mock(IAccessMethodService.class);
        //act
        US02_ConfigureAccessMethodController ctrl1 = new US02_ConfigureAccessMethodController(accessMethodService);
        //assert
        assertNotNull(ctrl1);

    }

    @Test
    void shouldNotCreateControllerIfAccessMethodServiceIsNull() {
        //arrange
        //act
        //assert
        assertThrows(IllegalArgumentException.class, () -> new US02_ConfigureAccessMethodController(null));
    }

    @Test
    void shouldConfigureAnAccessMethod() {
        //arrange
        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = mock(NameWithNumbersAndSpecialChars.class);
        IAccessMethodService accessMethodService = mock(IAccessMethodService.class);
        US02_ConfigureAccessMethodController ctrl1 = new US02_ConfigureAccessMethodController(accessMethodService);
        AccessMethod accessMethod = mock(AccessMethod.class);
        when(accessMethodService.registerAccessMethod(nameWithNumbersAndSpecialChars)).thenReturn(Optional.of(accessMethod));
        //act
        boolean result = ctrl1.configureAccessMethod(nameWithNumbersAndSpecialChars);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldNotConfigureAnAccessMethodIfNameAlreadyExists() {
        //arrange
        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = mock(NameWithNumbersAndSpecialChars.class);
        IAccessMethodService accessMethodService = mock(IAccessMethodService.class);
        US02_ConfigureAccessMethodController ctrl1 = new US02_ConfigureAccessMethodController(accessMethodService);
        when(accessMethodService.registerAccessMethod(nameWithNumbersAndSpecialChars)).thenReturn(Optional.empty());
        //act
        boolean result = ctrl1.configureAccessMethod(nameWithNumbersAndSpecialChars);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldNotConfigureAnAccessMethofIfNameIsNull() {
        //arrange
        IAccessMethodService accessMethodService = mock(IAccessMethodService.class);
        US02_ConfigureAccessMethodController ctrl1 = new US02_ConfigureAccessMethodController(accessMethodService);
        //act
        boolean result = ctrl1.configureAccessMethod(null);
        //assert
        assertFalse(result);
    }
}
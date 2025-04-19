package PAI.controller;

import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.domain.accessMethod.AccessMethod;
import PAI.service.accessMethod.IAccessMethodService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

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
        // arrange
        String accessMethodName = "M23";
        IAccessMethodService accessMethodService = mock(IAccessMethodService.class);
        US02_ConfigureAccessMethodController ctrl1 = new US02_ConfigureAccessMethodController(accessMethodService);
        AccessMethod accessMethod = mock(AccessMethod.class);

        ArgumentCaptor<NameWithNumbersAndSpecialChars> nameCaptor = ArgumentCaptor.forClass(NameWithNumbersAndSpecialChars.class);
        when(accessMethodService.registerAccessMethod(nameCaptor.capture())).thenReturn(Optional.of(accessMethod));

        // act
        boolean result = ctrl1.configureAccessMethod(accessMethodName);

        // assert
        assertTrue(result);
        assertNotNull(nameCaptor.getValue());
    }

    @Test
    void shouldNotConfigureAnAccessMethodIfNameAlreadyExists() {
        // arrange
        String accessMethodName = "M23";
        IAccessMethodService accessMethodService = mock(IAccessMethodService.class);
        US02_ConfigureAccessMethodController ctrl1 = new US02_ConfigureAccessMethodController(accessMethodService);

        ArgumentCaptor<NameWithNumbersAndSpecialChars> nameCaptor = ArgumentCaptor.forClass(NameWithNumbersAndSpecialChars.class);
        when(accessMethodService.registerAccessMethod(nameCaptor.capture())).thenReturn(Optional.empty());

        // act
        boolean result = ctrl1.configureAccessMethod(accessMethodName);

        // assert
        assertFalse(result);
        assertNotNull(nameCaptor.getValue());
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
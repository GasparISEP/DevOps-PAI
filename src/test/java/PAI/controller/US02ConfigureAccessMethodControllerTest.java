package PAI.controller;

import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.domain.accessMethod.AccessMethod;
import PAI.domain.accessMethod.AccessMethodFactoryImpl;
import PAI.domain.accessMethod.IAccessMethodFactory;
import PAI.persistence.mem.accessMethod.AccessMethodListFactoryImpl;
import PAI.persistence.mem.accessMethod.AccessMethodRepositoryImpl;
import PAI.persistence.mem.accessMethod.IAccessMethodListFactory;
import PAI.repository.accessMethodRepository.IRepositoryAccessMethod;
import PAI.service.accessMethod.AccessMethodServiceImpl;
import PAI.service.accessMethod.IAccessMethodService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
    void shouldNotConfigureAnAccessMethodIfNameIsNull() {
        //arrange
        IAccessMethodService accessMethodService = mock(IAccessMethodService.class);
        US02_ConfigureAccessMethodController ctrl1 = new US02_ConfigureAccessMethodController(accessMethodService);
        //act
        boolean result = ctrl1.configureAccessMethod(null);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfServiceThrowsException() {
        // arrange
        IAccessMethodService accessMethodService = mock(IAccessMethodService.class);
        US02_ConfigureAccessMethodController ctrl = new US02_ConfigureAccessMethodController(accessMethodService);
        String accessMethodName = "M23";
        when(accessMethodService.registerAccessMethod(any()))
            .thenThrow(new IllegalArgumentException("Service error"));

        // act
        boolean result = ctrl.configureAccessMethod(accessMethodName);

        // assert
        assertFalse(result);
    }

    @Test
    void shouldConfigureAccessMethodInMemoryRepository() {
        // arrange
        IAccessMethodListFactory memoryListFactory = new AccessMethodListFactoryImpl();
        IRepositoryAccessMethod repository = new AccessMethodRepositoryImpl(memoryListFactory);
        IAccessMethodFactory factory = new AccessMethodFactoryImpl();
        IAccessMethodService service = new AccessMethodServiceImpl(factory, repository);
        US02_ConfigureAccessMethodController controller = new US02_ConfigureAccessMethodController(service);

        String accessMethodName = "M23";

        // act
        boolean result = controller.configureAccessMethod(accessMethodName);

        // assert
        assertTrue(result);
        Optional<AccessMethod> stored = repository.getAccessMethodByName(new NameWithNumbersAndSpecialChars(accessMethodName));
        assertTrue(stored.isPresent());
        assertEquals(accessMethodName, stored.get().getAccessMethodName().getnameWithNumbersAndSpecialChars());
    }

    @Test
    void shouldNotConfigureAccessMethodIfNameAlreadyExists() {
        // arrange
        IAccessMethodListFactory memoryListFactory = new AccessMethodListFactoryImpl();
        IRepositoryAccessMethod repository = new AccessMethodRepositoryImpl(memoryListFactory);
        IAccessMethodFactory factory = new AccessMethodFactoryImpl();
        IAccessMethodService service = new AccessMethodServiceImpl(factory, repository);
        US02_ConfigureAccessMethodController controller = new US02_ConfigureAccessMethodController(service);

        String accessMethodName = "M23";
        controller.configureAccessMethod(accessMethodName);
        // act
        boolean result = controller.configureAccessMethod(accessMethodName);
        // assert
        assertFalse(result);
        Optional<AccessMethod> stored = repository.getAccessMethodByName(new NameWithNumbersAndSpecialChars(accessMethodName));
        assertTrue(stored.isPresent());
        assertEquals(1, ((List<AccessMethod>)repository.findAll()).size());
    }
}
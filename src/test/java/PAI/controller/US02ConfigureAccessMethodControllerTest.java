package PAI.controller;

import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.assembler.accessMethod.IAccessMethodAssembler;
import PAI.domain.accessMethod.AccessMethod;
import PAI.domain.accessMethod.AccessMethodFactoryImpl;
import PAI.domain.accessMethod.IAccessMethodFactory;
import PAI.dto.accessMethod.AccessMethodResponseDTO;
import PAI.dto.accessMethod.RegisterAccessMethodCommand;
import PAI.persistence.mem.accessMethod.AccessMethodListFactoryImpl;
import PAI.persistence.mem.accessMethod.AccessMethodRepositoryImpl;
import PAI.persistence.mem.accessMethod.IAccessMethodListFactory;
import PAI.domain.repositoryInterfaces.accessMethod.IRepositoryAccessMethod;
import PAI.service.accessMethod.AccessMethodServiceImpl;
import PAI.service.accessMethod.IAccessMethodService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("access-method")
class US02ConfigureAccessMethodControllerTest {


    @Autowired private IAccessMethodFactory accessMethodFactory;
    @Autowired private IRepositoryAccessMethod accessMethodRepository;
    @Autowired private IAccessMethodAssembler accessMethodAssembler;

    private IAccessMethodService accessMethodService;
    private US02_ConfigureAccessMethodController controller;

    @BeforeEach
    void setUp() {
        accessMethodService = new AccessMethodServiceImpl(accessMethodFactory, accessMethodRepository, accessMethodAssembler );
        controller = new US02_ConfigureAccessMethodController(accessMethodService);
    }

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
        AccessMethodResponseDTO dto = mock(AccessMethodResponseDTO.class);
        US02_ConfigureAccessMethodController ctrl1 = new US02_ConfigureAccessMethodController(accessMethodService);
        AccessMethod accessMethod = mock(AccessMethod.class);

        ArgumentCaptor<RegisterAccessMethodCommand> commandCaptor = ArgumentCaptor.forClass(RegisterAccessMethodCommand.class);
        when(accessMethodService.configureAccessMethod(commandCaptor.capture())).thenReturn(Optional.of(dto));

        // act
        boolean result = ctrl1.configureAccessMethod(accessMethodName);

        // assert
        assertTrue(result);
        assertNotNull(commandCaptor.getValue());
    }

    @Test
    void shouldNotConfigureAnAccessMethodIfNameAlreadyExists() {
        // arrange
        String accessMethodName = "M23";
        IAccessMethodService accessMethodService = mock(IAccessMethodService.class);
        US02_ConfigureAccessMethodController ctrl1 = new US02_ConfigureAccessMethodController(accessMethodService);

        ArgumentCaptor<RegisterAccessMethodCommand> commandCaptor = ArgumentCaptor.forClass(RegisterAccessMethodCommand.class);
        when(accessMethodService.configureAccessMethod(commandCaptor.capture())).thenReturn(Optional.empty());

        // act
        boolean result = ctrl1.configureAccessMethod(accessMethodName);

        // assert
        assertFalse(result);
        assertEquals(accessMethodName, commandCaptor.getValue().name());
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
        when(accessMethodService.configureAccessMethod(any(RegisterAccessMethodCommand.class)))
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
        IAccessMethodAssembler assembler = mock(IAccessMethodAssembler.class);

        AccessMethodResponseDTO responseDTO = mock(AccessMethodResponseDTO.class);
        when(assembler.toDto(any(AccessMethod.class))).thenReturn(responseDTO);

        IAccessMethodService service = new AccessMethodServiceImpl(factory, repository, assembler);
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
        IAccessMethodAssembler assembler = mock(IAccessMethodAssembler.class);
        IAccessMethodService service = new AccessMethodServiceImpl(factory, repository, assembler);
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

    @Test
    void shouldReturnTrueIfAccessMethodIsRegisteredSuccessfully_Integration_Test_JPA() {
        //arrange
        //act
        boolean result = controller.configureAccessMethod("M23");
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfAccessMethodAlreadyExists_Integration_Test_JPA() {
        //arrange
        //act
        boolean result = controller.configureAccessMethod("Maiores de 23 anos");
        //assert
        assertFalse(result);
    }
}
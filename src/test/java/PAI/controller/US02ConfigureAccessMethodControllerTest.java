package PAI.controller;

import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.domain.accessMethod.AccessMethodFactoryImpl;
import PAI.domain.accessMethod.IAccessMethodFactory;
import PAI.persistence.mem.accessMethod.AccessMethodListFactoryImpl;
import PAI.persistence.mem.accessMethod.AccessMethodRepositoryImpl;
import PAI.persistence.mem.accessMethod.IAccessMethodListFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class US02ConfigureAccessMethodControllerTest {

    @Test
    void shouldCreateThisController() {
        //arrange
        IAccessMethodFactory accessMethodFactory = new AccessMethodFactoryImpl();
        IAccessMethodListFactory accessMethodListFactory = new AccessMethodListFactoryImpl();
        AccessMethodRepositoryImpl doubleAccessMethodRepository = new AccessMethodRepositoryImpl(accessMethodFactory, accessMethodListFactory);
        //act
        US02_ConfigureAccessMethodController ctrl1 = new US02_ConfigureAccessMethodController(doubleAccessMethodRepository);
        //assert
        assertNotNull(ctrl1);

    }

    @Test
    void shouldNotConfigureAccessMethodIfAccessMethodRepositoryIsNull() {
        //arrange
        NameWithNumbersAndSpecialChars accessMethodName = new NameWithNumbersAndSpecialChars("Maiores 23");
        //act
        US02_ConfigureAccessMethodController ctrl1 = new US02_ConfigureAccessMethodController(null);

        boolean isConfigured = ctrl1.configureAccessMethod(accessMethodName);
        //assert
        assertFalse(isConfigured);
    }

    @Test
    void shouldConfigureAnAccessMethod() {
        //arrange
        IAccessMethodFactory accessMethodFactory = new AccessMethodFactoryImpl();
        IAccessMethodListFactory accessMethodListFactory = new AccessMethodListFactoryImpl();
        AccessMethodRepositoryImpl accessMethodRepository = new AccessMethodRepositoryImpl(accessMethodFactory, accessMethodListFactory);
        NameWithNumbersAndSpecialChars accessMethodName = new NameWithNumbersAndSpecialChars("Maiores 23");
        US02_ConfigureAccessMethodController ctrl1 = new US02_ConfigureAccessMethodController(accessMethodRepository);
        //act
        boolean isConfigured = ctrl1.configureAccessMethod(accessMethodName);
        //assert
        assertTrue(isConfigured);
    }

    @Test
    void shouldNotConfigureAnAccessMethodIfAccessMethodNameIsNull(){
        //arrange
        IAccessMethodFactory accessMethodFactory = new AccessMethodFactoryImpl();
        IAccessMethodListFactory accessMethodListFactory = new AccessMethodListFactoryImpl();
        AccessMethodRepositoryImpl accessMethodRepository = new AccessMethodRepositoryImpl(accessMethodFactory, accessMethodListFactory);
        US02_ConfigureAccessMethodController ctrl1 = new US02_ConfigureAccessMethodController(accessMethodRepository);
        //act
        boolean isConfigured = ctrl1.configureAccessMethod(null);
        //assert
        assertFalse(isConfigured);
    }

    @Test
    void shouldNotConfigureAnAccessMethodIfAccessMethodIsAlreadyRegistered(){
        //arrange
        IAccessMethodFactory accessMethodFactory = new AccessMethodFactoryImpl();
        IAccessMethodListFactory accessMethodListFactory = new AccessMethodListFactoryImpl();
        AccessMethodRepositoryImpl accessMethodRepository = new AccessMethodRepositoryImpl(accessMethodFactory, accessMethodListFactory);
        US02_ConfigureAccessMethodController ctrl1 = new US02_ConfigureAccessMethodController(accessMethodRepository);
        NameWithNumbersAndSpecialChars accessMethodName = new NameWithNumbersAndSpecialChars("Maiores 23");
        ctrl1.configureAccessMethod(accessMethodName);
        //act
        boolean isConfigured = ctrl1.configureAccessMethod(accessMethodName);
        //assert
        assertFalse(isConfigured);
    }
}
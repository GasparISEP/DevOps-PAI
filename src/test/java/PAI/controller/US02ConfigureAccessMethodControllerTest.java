package PAI.controller;

import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.domain.AccessMethod;
import PAI.domain.accessMethodDDD.AccessMethodDDD;
import PAI.domain.accessMethodDDD.AccessMethodDDDFactoryImpl;
import PAI.domain.accessMethodDDD.IAccessMethodDDDFactory;
import PAI.factory.AccessMethodFactoryImpl;
import PAI.factory.AccessMethodListFactoryImpl;
import PAI.repository.AccessMethodRepository;
import PAI.repository.accessMethodRepositoryDDD.AccessMethodDDDListFactoryImpl;
import PAI.repository.accessMethodRepositoryDDD.AccessMethodDDDRepository;
import PAI.repository.accessMethodRepositoryDDD.IAccessMethodDDDListFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US02ConfigureAccessMethodControllerTest {

    @Test
    void shouldCreateThisController() {
        //arrange
        IAccessMethodDDDFactory accessMethodFactory = new AccessMethodDDDFactoryImpl();
        IAccessMethodDDDListFactory accessMethodListFactory = new AccessMethodDDDListFactoryImpl();
        AccessMethodDDDRepository doubleAccessMethodRepository = new AccessMethodDDDRepository(accessMethodFactory, accessMethodListFactory);
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
        IAccessMethodDDDFactory accessMethodFactory = new AccessMethodDDDFactoryImpl();
        IAccessMethodDDDListFactory accessMethodListFactory = new AccessMethodDDDListFactoryImpl();
        AccessMethodDDDRepository accessMethodRepository = new AccessMethodDDDRepository(accessMethodFactory, accessMethodListFactory);
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
        IAccessMethodDDDFactory accessMethodFactory = new AccessMethodDDDFactoryImpl();
        IAccessMethodDDDListFactory accessMethodListFactory = new AccessMethodDDDListFactoryImpl();
        AccessMethodDDDRepository accessMethodRepository = new AccessMethodDDDRepository(accessMethodFactory, accessMethodListFactory);
        US02_ConfigureAccessMethodController ctrl1 = new US02_ConfigureAccessMethodController(accessMethodRepository);
        //act
        boolean isConfigured = ctrl1.configureAccessMethod(null);
        //assert
        assertFalse(isConfigured);
    }

    @Test
    void shouldNotConfigureAnAccessMethodIfAccessMethodIsAlreadyRegistered(){
        //arrange
        IAccessMethodDDDFactory accessMethodFactory = new AccessMethodDDDFactoryImpl();
        IAccessMethodDDDListFactory accessMethodListFactory = new AccessMethodDDDListFactoryImpl();
        AccessMethodDDDRepository accessMethodRepository = new AccessMethodDDDRepository(accessMethodFactory, accessMethodListFactory);
        US02_ConfigureAccessMethodController ctrl1 = new US02_ConfigureAccessMethodController(accessMethodRepository);
        NameWithNumbersAndSpecialChars accessMethodName = new NameWithNumbersAndSpecialChars("Maiores 23");
        ctrl1.configureAccessMethod(accessMethodName);
        //act
        boolean isConfigured = ctrl1.configureAccessMethod(accessMethodName);
        //assert
        assertFalse(isConfigured);
    }
}
package PAI.domain.accessMethod;

import PAI.VOs.AccessMethodID;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccessMethodFactoryImplTest {

    @Test
    void givenMockedConstructorAccessMethodFactoryShouldCreateAccessMethod() throws InstantiationException {
        //arrange
        AccessMethodFactoryImpl accessMethodFactoryImpl = new AccessMethodFactoryImpl();
        NameWithNumbersAndSpecialChars accessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        try (MockedConstruction<AccessMethod> mockAccessMethod = mockConstruction(AccessMethod.class, (mock, context) ->{
        })) {
            //act
            AccessMethod accessMethod = accessMethodFactoryImpl.createAccessMethod(accessMethodName);
            //assert
            assertNotNull(accessMethod);

            //O objeto isolado deve existir
            assertEquals(1, mockAccessMethod.constructed().size());
            AccessMethod doubleAccessMethod = mockAccessMethod.constructed().get(0);
            assertNotNull(doubleAccessMethod);
            assertEquals(accessMethod,doubleAccessMethod);
        }
    }

    @Test
    void mockingConstructorThrowingException(){
        //arrange
        AccessMethodFactoryImpl accessMethodFactoryImpl = new AccessMethodFactoryImpl();
        NameWithNumbersAndSpecialChars accessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        //Use try-with-resources to mock construction and throw an exception
        try (MockedConstruction<AccessMethod> mock = mockConstruction(AccessMethod.class,(mocked, context) ->
        {
            //Define behavior: throwing an exception when a new instance of Location is created
            throw new RuntimeException(new InstantiationException("AccessMethod constructor failed"));
        })) {
            //Act: trying to create accessMethod will throw the exception
            try {
                accessMethodFactoryImpl.createAccessMethod(accessMethodName);
                fail("Expect exception not thrown");
            } catch (Exception e) {
                //Assertion to check if the exception is thrown
                assertTrue(e.getCause().getMessage().contains("AccessMethod constructor failed"));
            }
        }
    }

    @Test
    void givenMockedConstructorAccessMethodFactoryShouldCreateAccessMethodOnlyWithName() throws InstantiationException {
        //arrange
        AccessMethodFactoryImpl accessMethodFactoryImpl = new AccessMethodFactoryImpl();
        NameWithNumbersAndSpecialChars accessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        try (MockedConstruction<AccessMethod> mockAccessMethod = mockConstruction(AccessMethod.class, (mock, context) ->{
        })) {
            //act
            AccessMethod accessMethod = accessMethodFactoryImpl.createAccessMethod(accessMethodName);
            //assert
            assertNotNull(accessMethod);

            //O objeto isolado deve existir
            assertEquals(1, mockAccessMethod.constructed().size());
            AccessMethod doubleAccessMethod = mockAccessMethod.constructed().get(0);
            assertNotNull(doubleAccessMethod);
            assertEquals(accessMethod,doubleAccessMethod);
        }
    }

    @Test
    void mockingConstructorOnlyWithNameThrowingException(){
        //arrange
        AccessMethodFactoryImpl accessMethodFactoryImpl = new AccessMethodFactoryImpl();
        NameWithNumbersAndSpecialChars accessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        //Use try-with-resources to mock construction and throw an exception
        try (MockedConstruction<AccessMethod> mock = mockConstruction(AccessMethod.class,(mocked, context) ->
        {
            //Define behavior: throwing an exception when a new instance of Location is created
            throw new RuntimeException(new InstantiationException("AccessMethod constructor failed"));
        })) {
            //Act: trying to create accessMethod will throw the exception
            try {
                accessMethodFactoryImpl.createAccessMethod(accessMethodName);
                fail("Expect exception not thrown");
            } catch (Exception e) {
                //Assertion to check if the exception is thrown
                assertTrue(e.getCause().getMessage().contains("AccessMethod constructor failed"));
            }
        }
    }

    @Test
    void shouldNotCreateAccessMethod(){
        //arrange
        AccessMethodFactoryImpl accessMethodFactory = new AccessMethodFactoryImpl();
        AccessMethodID accessMethodID = mock(AccessMethodID.class);
        NameWithNumbersAndSpecialChars accessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        //act + assert
        assertThrows(Exception.class, () -> accessMethodFactory.createAccessMethod( null));
    }

    @Test
    void shouldNotCreateAccessMethodWithOnlyName(){
        //arrange
        AccessMethodFactoryImpl accessMethodFactory = new AccessMethodFactoryImpl();
        //act + assert
        assertThrows(Exception.class, () -> accessMethodFactory.createAccessMethod(null));
    }

}
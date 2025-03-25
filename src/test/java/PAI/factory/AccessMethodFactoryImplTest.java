package PAI.factory;

import PAI.domain.AccessMethod;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccessMethodFactoryImplTest {

    @Test
    void givenMockedConstructorAccessMethodFactoryShouldCreateAccessMethod() throws InstantiationException {
        //arrange
        AccessMethodFactoryImpl accessMethodFactoryImpl = new AccessMethodFactoryImpl();
        String accessMethodName = "Maiores 23";
        try (MockedConstruction<AccessMethod> mockAccessMethod = mockConstruction(AccessMethod.class, (mock, context) ->{
            when(mock.hasThisAccessMethodName(accessMethodName)).thenReturn(true);
        })) {
        //act
        AccessMethod accessMethod = accessMethodFactoryImpl.createAccessMethod(accessMethodName);
        //assert
        assertNotNull(accessMethod);
        assertTrue(accessMethod.hasThisAccessMethodName(accessMethodName));

        //O objeto isolado deve existir
        assertEquals(1, mockAccessMethod.constructed().size());
        AccessMethod doubleAccessMethod = mockAccessMethod.constructed().get(0);
        assertNotNull(doubleAccessMethod);
        assertEquals(accessMethod,doubleAccessMethod);
        assertTrue(doubleAccessMethod.hasThisAccessMethodName(accessMethodName));
        }
    }



    @Test
    void mockingConstructorThrowingException(){
        //arrange
        AccessMethodFactoryImpl accessMethodFactoryImpl = new AccessMethodFactoryImpl();

        //Use tyr-with-resources to mock construction and throw an exception
        try (MockedConstruction<AccessMethod> mock = mockConstruction(AccessMethod.class,(mocked, context) ->
            {
            //Define behavior: throwing an exception when a new instance of Location is created
            throw new RuntimeException(new InstantiationException("AccessMethod constructor failed"));
            })) {
            //Act: trying to create accessMethod will throw the exception
            try {
                accessMethodFactoryImpl.createAccessMethod("Maiores 23");
                fail("Expect exception not thrown");
            } catch (Exception e) {
                //Assertion to check if the exception is thrown
                assertTrue(e.getCause().getMessage().contains("AccessMethod constructor failed"));
            }
        }
    }
    @Test
    void shouldNotCreateAccessMethod() throws InstantiationException {
        //arrange
        AccessMethodFactoryImpl accessMethodFactoryImpl = new AccessMethodFactoryImpl();
        //act
        //assert
        assertThrows(Exception.class, () -> accessMethodFactoryImpl.createAccessMethod(""));
    }
}
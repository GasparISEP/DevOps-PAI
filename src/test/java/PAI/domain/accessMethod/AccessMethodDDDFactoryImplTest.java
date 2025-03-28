package PAI.domain.accessMethod;

import PAI.VOs.AccessMethodID;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.domain.AccessMethod;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccessMethodDDDFactoryImplTest {

    @Test
    void givenMockedConstructorAccessMethodFactoryShouldCreateAccessMethod() throws InstantiationException {
        //arrange
        AccessMethodDDDFactoryImpl accessMethodFactoryImpl = new AccessMethodDDDFactoryImpl();
        AccessMethodID accessMethodID = mock(AccessMethodID.class);
        NameWithNumbersAndSpecialChars accessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        try (MockedConstruction<AccessMethodDDD> mockAccessMethod = mockConstruction(AccessMethodDDD.class, (mock, context) ->{
        })) {
            //act
            AccessMethodDDD accessMethod = accessMethodFactoryImpl.createAccessMethod(accessMethodID,accessMethodName);
            //assert
            assertNotNull(accessMethod);

            //O objeto isolado deve existir
            assertEquals(1, mockAccessMethod.constructed().size());
            AccessMethodDDD doubleAccessMethod = mockAccessMethod.constructed().get(0);
            assertNotNull(doubleAccessMethod);
            assertEquals(accessMethod,doubleAccessMethod);
        }
    }

    @Test
    void mockingConstructorThrowingException(){
        //arrange
        AccessMethodDDDFactoryImpl accessMethodFactoryImpl = new AccessMethodDDDFactoryImpl();
        AccessMethodID accessMethodID = mock(AccessMethodID.class);
        NameWithNumbersAndSpecialChars accessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        //Use try-with-resources to mock construction and throw an exception
        try (MockedConstruction<AccessMethodDDD> mock = mockConstruction(AccessMethodDDD.class,(mocked, context) ->
        {
            //Define behavior: throwing an exception when a new instance of Location is created
            throw new RuntimeException(new InstantiationException("AccessMethod constructor failed"));
        })) {
            //Act: trying to create accessMethod will throw the exception
            try {
                accessMethodFactoryImpl.createAccessMethod(accessMethodID, accessMethodName);
                fail("Expect exception not thrown");
            } catch (Exception e) {
                //Assertion to check if the exception is thrown
                assertTrue(e.getCause().getMessage().contains("AccessMethod constructor failed"));
            }
        }
    }

}
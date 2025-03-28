package PAI.domain.accessMethod;

import PAI.VOs.AccessMethodID;
import PAI.VOs.NameWithNumbersAndSpecialChars;
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

}
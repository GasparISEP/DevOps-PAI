package PAI.factory;

import PAI.domain.AccessMethod;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccessMethodFactoryTest {

    @Test
    void givenMockedConstructorAccessMethodFactoryShouldCreateAccessMethod() throws InstantiationException {
        //arrange
        String accessMethodName = "Maiores 23";
        try (MockedConstruction<AccessMethod> mockAccessMethod = mockConstruction(AccessMethod.class)) {
            AccessMethodFactory accessMethodFactory = new AccessMethodFactory();
        //act
        AccessMethod accessMethod = accessMethodFactory.createAccessMethod(accessMethodName);
        //assert
        assertNotNull(accessMethod);
        assertEquals(1, mockAccessMethod.constructed().size());

        AccessMethod doubleAccessMethod = mockAccessMethod.constructed().get(0);
        assertNotNull(doubleAccessMethod);
        }
    }

    @Test
    void shouldNotCreateAccessMethod() throws InstantiationException {
        //arrange
        AccessMethodFactory accessMethodFactory = new AccessMethodFactory();
        //act
        //assert
        assertThrows(Exception.class, () -> accessMethodFactory.createAccessMethod(""));
    }
    }
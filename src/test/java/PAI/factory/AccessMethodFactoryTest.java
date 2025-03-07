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
        AccessMethodFactory accessMethodFactory = new AccessMethodFactory();
        String accessMethodName = "Maiores 23";
        try (MockedConstruction<AccessMethod> mockAccessMethod = mockConstruction(AccessMethod.class, (mock, context) ->{
            String actualName = (String) context.arguments().get(0);
            when(mock.hasThisAccessMethodName(accessMethodName)).thenReturn(true);
            })) {
        //act
        AccessMethod accessMethod = accessMethodFactory.createAccessMethod(accessMethodName);
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
    void shouldNotCreateAccessMethod() throws InstantiationException {
        //arrange
        AccessMethodFactory accessMethodFactory = new AccessMethodFactory();
        //act
        //assert
        assertThrows(Exception.class, () -> accessMethodFactory.createAccessMethod(""));
    }
}
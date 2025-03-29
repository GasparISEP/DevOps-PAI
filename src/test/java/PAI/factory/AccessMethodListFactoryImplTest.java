package PAI.factory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccessMethodListFactoryImplTest {

    @Test
    void shouldCreateArrayList() {
        //arrange
        AccessMethodListFactoryImpl accessMethodListFactoryImpl = new AccessMethodListFactoryImpl();
        //act + assert
        assertNotNull(accessMethodListFactoryImpl);
    }
}
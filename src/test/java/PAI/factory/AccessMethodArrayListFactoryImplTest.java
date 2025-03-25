package PAI.factory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccessMethodArrayListFactoryImplTest {

    @Test
    void shouldCreateArrayList() {
        //arrange
        AccessMethodArrayListFactoryImpl accessMethodArrayListFactoryImpl = new AccessMethodArrayListFactoryImpl();
        //act + assert
        assertNotNull(accessMethodArrayListFactoryImpl);
    }
}
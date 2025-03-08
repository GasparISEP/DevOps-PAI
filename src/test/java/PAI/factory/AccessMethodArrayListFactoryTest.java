package PAI.factory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccessMethodArrayListFactoryTest {

    @Test
    void shouldCreateArrayList() {
        //arrange
        AccessMethodArrayListFactory accessMethodArrayListFactory = new AccessMethodArrayListFactory();
        //act + assert
        assertNotNull(accessMethodArrayListFactory);
    }
}
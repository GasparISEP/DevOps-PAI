package PAI.factory;

import PAI.domain.DegreeTypeDDD.DegreeType_2;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DegreeTypeListFactoryImpl_2Test {

    @Test
    void testCreateDegreeType_2List() {
        // Arrange
        DegreeTypeListFactoryImpl_2 factory = new DegreeTypeListFactoryImpl_2();

        // Act
        List<DegreeType_2> result = factory.createDegreeType_2List();

        // Assert
        assertNotNull(result, "List must not bu null");
        assertTrue(result.isEmpty(), "List should not be empty");
    }
}

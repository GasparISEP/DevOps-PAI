package PAI.factory;

import PAI.domain.degreeType.DegreeType;
import PAI.factory.DegreeTypeFactory.DegreeTypeListFactoryImpl;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DegreeTypeListFactoryImplTest {

    @Test
    void testCreateDegreeType_2List() {
        // Arrange
        DegreeTypeListFactoryImpl factory = new DegreeTypeListFactoryImpl();

        // Act
        List<DegreeType> result = factory.createDegreeType_2List();

        // Assert
        assertNotNull(result, "List must not bu null");
        assertTrue(result.isEmpty(), "List should not be empty");
    }
}

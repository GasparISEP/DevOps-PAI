package PAI.persistence.datamodel;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DegreeTypeDMTest {

    @Test
    void testConstructorAndGetters() {
        String id = "123e4567-e89b-12d3-a456-426614174000";
        String name = "Licenciatura";
        int maxEcts = 180;

        DegreeTypeDM degreeTypeDM = new DegreeTypeDM(id, name, maxEcts);

        assertEquals(id, degreeTypeDM.getId());
        assertEquals(name, degreeTypeDM.getName());
        assertEquals(maxEcts, degreeTypeDM.getMaxEcts());
    }

    @Test
    void testDefaultConstructor() {
        DegreeTypeDM degreeTypeDM = new DegreeTypeDM();

        assertNotNull(degreeTypeDM);
    }
}
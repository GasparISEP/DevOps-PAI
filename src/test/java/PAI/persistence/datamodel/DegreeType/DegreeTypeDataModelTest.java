package PAI.persistence.datamodel.DegreeType;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DegreeTypeDataModelTest {

    @Test
    void testConstructorAndGetters() {
        DegreeTypeIDDataModel id = new DegreeTypeIDDataModel("123e4567-e89b-12d3-a456-426614174000");
        String name = "Licenciatura";
        int maxEcts = 180;

        DegreeTypeDataModel degreeTypeDataModel = new DegreeTypeDataModel(id, name, maxEcts);

        assertEquals(id, degreeTypeDataModel.getId());
        assertEquals(name, degreeTypeDataModel.getName());
        assertEquals(maxEcts, degreeTypeDataModel.getMaxEcts());
    }

    @Test
    void testDefaultConstructor() {
        DegreeTypeDataModel degreeTypeDataModel = new DegreeTypeDataModel();

        assertNotNull(degreeTypeDataModel);
    }
}
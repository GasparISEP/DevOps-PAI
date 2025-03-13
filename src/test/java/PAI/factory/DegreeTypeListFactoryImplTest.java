package PAI.factory;

import PAI.domain.DegreeType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class DegreeTypeListFactoryImplTest {
    @Test
    void shouldCreateDegreeTypeList() {
        //Arrange
        DegreeTypeListFactoryImpl degreeTypeListFactory = new DegreeTypeListFactoryImpl();
        //
        List<DegreeType> degreeTypeList = degreeTypeListFactory.createDegreeTypeList();
        assertNotNull(degreeTypeList);
        assertInstanceOf(ArrayList.class, degreeTypeList);
    }

}
package PAI.factory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DegreeTypeListFactoryTest {

    @Test
    public void shouldCreateDegreeTypeList() {
        //Arrange
        DegreeTypeListFactory degreeTypeListFactory = new DegreeTypeListFactory();
        // Act + Assert
        assertNotNull(degreeTypeListFactory);
    }

}
package PAI.factory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DegreeTypeListFactoryImplTest {

    @Test
    public void shouldCreateDegreeTypeList() {
        //Arrange
        DegreeTypeListFactoryImpl degreeTypeListFactoryImpl = new DegreeTypeListFactoryImpl();
        // Act + Assert
        assertNotNull(degreeTypeListFactoryImpl);
    }

}
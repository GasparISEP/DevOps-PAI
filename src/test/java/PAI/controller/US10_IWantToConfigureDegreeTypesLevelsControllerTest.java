package PAI.controller;

import PAI.domain.DegreeTypeRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US10_IWantToConfigureDegreeTypesLevelsControllerTest {

    @Test
    void newDegreeTypeRepository() throws Exception {
        //arrange
        DegreeTypeRepository degreeTypeRepository = mock(DegreeTypeRepository.class);

        //act
        US10_IWantToConfigureDegreeTypesLevelsController degreeType1 = new US10_IWantToConfigureDegreeTypesLevelsController(degreeTypeRepository);

        //assert
        assertNotNull(degreeType1);
    }

    @Test
    void nullDegreeTypeRepository() {
        //arrange
        DegreeTypeRepository degreeTypeRepository = null;

        //act & assert
        Exception exception = assertThrows(Exception.class, () ->
                new US10_IWantToConfigureDegreeTypesLevelsController(degreeTypeRepository)
        );
        assertEquals("DegreeType Repository cannot be null", exception.getMessage());
    }

    @Test
    void registerDegreeTypeInSystem() throws Exception {
        //arrange
        DegreeTypeRepository degreeTypeRepository = mock(DegreeTypeRepository.class);
        US10_IWantToConfigureDegreeTypesLevelsController controller1 = new US10_IWantToConfigureDegreeTypesLevelsController(degreeTypeRepository);
        String name = "Master";
        int maxEcts = 30;

        when(degreeTypeRepository.registerDegreeType(name, maxEcts)).thenReturn(true);

        //act
        boolean result = controller1.registerDegreeType(name, maxEcts);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldntRegisterDegreeType() throws Exception {
        //arrange
        DegreeTypeRepository degreeTypeRepository = mock(DegreeTypeRepository.class);
        US10_IWantToConfigureDegreeTypesLevelsController controller = new US10_IWantToConfigureDegreeTypesLevelsController(degreeTypeRepository);
        String name = "Master";
        int maxEcts = 30;

        when(degreeTypeRepository.registerDegreeType(name, maxEcts)).thenReturn(false);

        boolean result = controller.registerDegreeType(name, maxEcts);

        assertFalse(result);
    }

}
package PAI.controller;

import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.service.DegreeType.DegreeTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US10_IWantToConfigureDegreeTypesLevelsControllerTest {

    private DegreeTypeService service;
    private US10_IWantToConfigureDegreeTypesLevelsController controller;

    @BeforeEach
    void setUp() {
        service = mock(DegreeTypeService.class);
        controller = new US10_IWantToConfigureDegreeTypesLevelsController(service);
    }

    @Test
    void testRegisterDegreeType_Success() throws Exception {
        Name name = new Name("Gestão");
        MaxEcts ects = new MaxEcts(180);

        when(service.registerDegreeType(name, ects)).thenReturn(true);

        boolean result = controller.registerDegreeType(name, ects);

        assertTrue(result);
        verify(service).registerDegreeType(name, ects);
    }

    @Test
    void testRegisterDegreeType_Failure() throws Exception {
        Name name = new Name("Filosofia");
        MaxEcts ects = new MaxEcts(180);

        when(service.registerDegreeType(name, ects)).thenReturn(false);

        boolean result = controller.registerDegreeType(name, ects);

        assertFalse(result);
        verify(service).registerDegreeType(name, ects);
    }

    @Test
    void testRegisterDegreeType_ThrowsException() throws Exception {
        Name name = new Name("Arquitetura");
        MaxEcts ects = new MaxEcts(180);

        when(service.registerDegreeType(name, ects)).thenThrow(new Exception("Erro"));

        Exception ex = assertThrows(Exception.class, () -> {
            controller.registerDegreeType(name, ects);
        });

        assertEquals("Erro", ex.getMessage());
        verify(service).registerDegreeType(name, ects);
    }
}
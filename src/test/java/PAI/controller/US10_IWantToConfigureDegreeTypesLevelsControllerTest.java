package PAI.controller;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.repository.DegreeTypeRepoDDD.DegreeTypeRepository_2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US10_IWantToConfigureDegreeTypesLevelsControllerTest {

    private DegreeTypeRepository_2 degreeTypeRepositoryMock;
    private US10_IWantToConfigureDegreeTypesLevelsController controller;

    @BeforeEach
    void setUp() {
        degreeTypeRepositoryMock = Mockito.mock(DegreeTypeRepository_2.class);
        controller = new US10_IWantToConfigureDegreeTypesLevelsController(degreeTypeRepositoryMock);
    }

    @Test
    void shouldCreateControllerSuccessfully() {
        assertNotNull(controller);
    }

    @Test
    void shouldRegisterDegreeTypeSuccessfully() throws Exception {
        DegreeTypeID id = new DegreeTypeID("DT1");
        Name name = new Name("Bachelor");
        MaxEcts maxEcts = new MaxEcts(180);

        when(degreeTypeRepositoryMock.registerDegreeType(id, name, maxEcts)).thenReturn(true);

        boolean result = controller.registerDegreeType(id, name, maxEcts);

        assertTrue(result);
        verify(degreeTypeRepositoryMock, times(1)).registerDegreeType(id, name, maxEcts);
    }

    @Test
    void shouldReturnFalseWhenRegisteringDuplicateDegreeType() throws Exception {
        DegreeTypeID id = new DegreeTypeID("DT1");
        Name name = new Name("Bachelor");
        MaxEcts maxEcts = new MaxEcts(180);

        when(degreeTypeRepositoryMock.registerDegreeType(id, name, maxEcts)).thenReturn(false);

        boolean result = controller.registerDegreeType(id, name, maxEcts);

        assertFalse(result);
        verify(degreeTypeRepositoryMock, times(1)).registerDegreeType(id, name, maxEcts);
    }

    @Test
    void shouldThrowExceptionWhenRepositoryFails() throws Exception {
        DegreeTypeID id = new DegreeTypeID("DT1");
        Name name = new Name("Bachelor");
        MaxEcts maxEcts = new MaxEcts(180);

        when(degreeTypeRepositoryMock.registerDegreeType(id, name, maxEcts)).thenThrow(new Exception("Repository error"));

        Exception exception = assertThrows(Exception.class, () -> controller.registerDegreeType(id, name, maxEcts));

        assertEquals("Repository error", exception.getMessage());
        verify(degreeTypeRepositoryMock, times(1)).registerDegreeType(id, name, maxEcts);
    }
}
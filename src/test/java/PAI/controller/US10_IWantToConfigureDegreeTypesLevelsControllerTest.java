package PAI.controller;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.repository.DegreeTypeRepository.DegreeTypeRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US10_IWantToConfigureDegreeTypesLevelsControllerTest {

    private DegreeTypeRepositoryImpl degreeTypeRepositoryImplMock;
    private US10_IWantToConfigureDegreeTypesLevelsController controller;

    @BeforeEach
    void setUp() {
        degreeTypeRepositoryImplMock = Mockito.mock(DegreeTypeRepositoryImpl.class);
        controller = new US10_IWantToConfigureDegreeTypesLevelsController(degreeTypeRepositoryImplMock);
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

        when(degreeTypeRepositoryImplMock.registerDegreeType(id, name, maxEcts)).thenReturn(true);

        boolean result = controller.registerDegreeType(id, name, maxEcts);

        assertTrue(result);
        verify(degreeTypeRepositoryImplMock, times(1)).registerDegreeType(id, name, maxEcts);
    }

    @Test
    void shouldReturnFalseWhenRegisteringDuplicateDegreeType() throws Exception {
        DegreeTypeID id = new DegreeTypeID("DT1");
        Name name = new Name("Bachelor");
        MaxEcts maxEcts = new MaxEcts(180);

        when(degreeTypeRepositoryImplMock.registerDegreeType(id, name, maxEcts)).thenReturn(false);

        boolean result = controller.registerDegreeType(id, name, maxEcts);

        assertFalse(result);
        verify(degreeTypeRepositoryImplMock, times(1)).registerDegreeType(id, name, maxEcts);
    }

    @Test
    void shouldThrowExceptionWhenRepositoryFails() throws Exception {
        DegreeTypeID id = new DegreeTypeID("DT1");
        Name name = new Name("Bachelor");
        MaxEcts maxEcts = new MaxEcts(180);

        when(degreeTypeRepositoryImplMock.registerDegreeType(id, name, maxEcts)).thenThrow(new Exception("Repository error"));

        Exception exception = assertThrows(Exception.class, () -> controller.registerDegreeType(id, name, maxEcts));

        assertEquals("Repository error", exception.getMessage());
        verify(degreeTypeRepositoryImplMock, times(1)).registerDegreeType(id, name, maxEcts);
    }
}
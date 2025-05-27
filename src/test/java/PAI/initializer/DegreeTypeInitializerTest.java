package PAI.initializer;

import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.controller.US10_IWantToConfigureDegreeTypesLevelsController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DegreeTypeInitializerTest {

    @Mock
    private US10_IWantToConfigureDegreeTypesLevelsController controller;

    @InjectMocks
    private DegreeTypeInitializer initializer;

    private final String csvContent =
            "MaxEcts;DegreeTypeName\n" +
                    "180;Bachelor\n" +
                    "120;Master\n" +
                    "240;Integrated Master\n" +
                    "240;PhD\n";

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInit_readsAllEntriesAndRegistersThem() throws Exception {
        InputStream csvStream = new ByteArrayInputStream(csvContent.getBytes());
        DegreeTypeInitializer spyInitializer = Mockito.spy(initializer);

        doReturn(csvStream).when(spyInitializer).getClassResourceAsStream(anyString());

        spyInitializer.init();

        verify(controller, times(4)).registerDegreeType(any(Name.class), any(MaxEcts.class));
        verify(controller).registerDegreeType(argThat(name -> name.getName().equals("Bachelor")), any(MaxEcts.class));
        verify(controller).registerDegreeType(argThat(name -> name.getName().equals("Master")), any(MaxEcts.class));
        verify(controller).registerDegreeType(argThat(name -> name.getName().equals("Integrated Master")), any(MaxEcts.class));
        verify(controller).registerDegreeType(argThat(name -> name.getName().equals("PhD")), any(MaxEcts.class));
    }
}
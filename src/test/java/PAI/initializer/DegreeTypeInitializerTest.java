package PAI.initializer;

import PAI.VOs.DegreeTypeID;
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
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DegreeTypeInitializerTest {

    @Mock
    private US10_IWantToConfigureDegreeTypesLevelsController controller;

    @InjectMocks
    private DegreeTypeInitializer initializer;

    private final String csvContent =
            "MaxEcts;DegreeTypeName;DegreeTypeID\n" +
                    "180;Bachelor;" + UUID.randomUUID() + "\n" +
                    "120;Master;" + UUID.randomUUID() + "\n" +
                    "240;Integrated Master;" + UUID.randomUUID() + "\n" +
                    "240;PhD;" + UUID.randomUUID() + "\n";

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

        // All 4 entries should be registered
        verify(controller, times(4)).registerDegreeTypeWithUUID(
                any(DegreeTypeID.class), any(Name.class), any(MaxEcts.class));

        // You can verify specific names if needed:
        verify(controller).registerDegreeTypeWithUUID(any(), argThat(name -> name.getName().equals("Bachelor")), any());
        verify(controller).registerDegreeTypeWithUUID(any(), argThat(name -> name.getName().equals("Master")), any());
        verify(controller).registerDegreeTypeWithUUID(any(), argThat(name -> name.getName().equals("Integrated Master")), any());
        verify(controller).registerDegreeTypeWithUUID(any(), argThat(name -> name.getName().equals("PhD")), any());

        verifyNoMoreInteractions(controller);
    }
}
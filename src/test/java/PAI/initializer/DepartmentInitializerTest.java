package PAI.initializer;

import PAI.VOs.DepartmentAcronym;
import PAI.VOs.Name;
import PAI.controller.US05_DepartmentRegistryController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DepartmentInitializerTest {


    @Mock
    private US05_DepartmentRegistryController controller;

    @InjectMocks
    private DepartmentInitializer initializer;

    private String _csvPath;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        _csvPath = "src/main/resources/Department.csv";
    }

    @Test
    void shouldInitializeAndRegisterDepartmentsFromCsv() throws Exception {
        // Arrange
        when(controller.registerDepartment(any(DepartmentAcronym.class), any(Name.class)))
                .thenReturn(true);

        // Act
        initializer.loadDepartment(controller, _csvPath);

        // Assert
        verify(controller).registerDepartment(new DepartmentAcronym("AAA"), new Name("Astronomy"));
        verify(controller).registerDepartment(new DepartmentAcronym("AAF"), new Name("Magic"));
        verify(controller).registerDepartment(new DepartmentAcronym("AAR"), new Name("History"));
        verify(controller).registerDepartment(new DepartmentAcronym("AAU"), new Name("Potions"));

        verify(controller, times(4)).registerDepartment(any(), any());
    }
}

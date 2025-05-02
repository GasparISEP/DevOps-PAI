package PAI.initializer;

import PAI.VOs.Date;
import PAI.VOs.DepartmentAcronym;
import PAI.VOs.Description;
import PAI.VOs.Name;
import PAI.controller.US07_IWantToCreateASchoolYearController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class SchoolYearInitializerTest {

    @Mock
    private US07_IWantToCreateASchoolYearController controller;

    @InjectMocks
    private SchoolYearInitializer initializer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldInitializeAndRegisterSchoolYearsFromCsv() throws Exception {
        // Arrange
        when(controller.addSchoolYear(anyString(), anyString(), anyString()))
                .thenReturn(true);


        // Act
        initializer.init();

        // Assert
        verify(controller).addSchoolYear("2015", "01-09-2015", "31-07-2016");
        verify(controller).addSchoolYear("2016", "01-09-2016", "31-07-2017");
        verify(controller).addSchoolYear("2017", "01-09-2017", "31-07-2018");

        verify(controller, times(3)).addSchoolYear(any(), any(), any());

    }
}
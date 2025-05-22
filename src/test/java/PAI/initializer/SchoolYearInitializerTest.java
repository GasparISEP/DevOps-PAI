package PAI.initializer;

import PAI.controller.US07_IWantToCreateASchoolYearController;
import PAI.domain.schoolYear.SchoolYear;
import PAI.dto.schoolYear.SchoolYearDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
        SchoolYear schoolYear = mock(SchoolYear.class);
        when(controller.addSchoolYear(anyString(), anyString(), anyString()))
                .thenReturn(schoolYear);


        // Act
        initializer.init();

        // Assert
        verify(controller).addSchoolYear("2015", "01-09-2015", "31-07-2016");
        verify(controller).addSchoolYear("2016", "01-09-2016", "31-07-2017");
        verify(controller).addSchoolYear("2017", "01-09-2017", "31-07-2018");
        verify(controller).addSchoolYear("2018", "01-09-2018", "31-07-2019");
        verify(controller).addSchoolYear("2019", "01-09-2019", "31-07-2020");
        verify(controller).addSchoolYear("2020", "01-09-2020", "31-07-2021");
        verify(controller).addSchoolYear("2021", "01-09-2021", "31-07-2022");
        verify(controller).addSchoolYear("2022", "01-09-2022", "31-07-2023");
        verify(controller).addSchoolYear("2023", "01-09-2023", "31-07-2024");
        verify(controller).addSchoolYear("2024", "01-09-2024", "31-07-2025");
        verify(controller).addSchoolYear("2025", "01-09-2025", "31-07-2026");
        verify(controller).addSchoolYear("2026", "01-09-2026", "31-07-2027");
        verify(controller).addSchoolYear("2027", "01-09-2027", "31-07-2028");
        verify(controller).addSchoolYear("2028", "01-09-2028", "31-07-2029");
        verify(controller).addSchoolYear("2029", "01-09-2029", "31-07-2030");
        verify(controller).addSchoolYear("2030", "01-09-2030", "31-07-2031");

        verify(controller, times(16)).addSchoolYear(any(), any(), any());
    }
}
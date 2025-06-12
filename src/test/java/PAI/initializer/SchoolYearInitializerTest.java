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
        when(controller.addSchoolYear(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(schoolYear);


        // Act
        initializer.init();

        // Assert
        verify(controller).addSchoolYear("550e8400-e29b-41d4-a716-446655440001","2015", "01-09-2015", "31-07-2016");
        verify(controller).addSchoolYear("550e8400-e29b-41d4-a716-446655440002","2016", "01-09-2016", "31-07-2017");
        verify(controller).addSchoolYear("550e8400-e29b-41d4-a716-446655440003","2017", "01-09-2017", "31-07-2018");
        verify(controller).addSchoolYear("550e8400-e29b-41d4-a716-446655440004","2018", "01-09-2018", "31-07-2019");
        verify(controller).addSchoolYear("550e8400-e29b-41d4-a716-446655440005","2019", "01-09-2019", "31-07-2020");
        verify(controller).addSchoolYear("550e8400-e29b-41d4-a716-446655440006","2020", "01-09-2020", "31-07-2021");
        verify(controller).addSchoolYear("550e8400-e29b-41d4-a716-446655440007","2021", "01-09-2021", "31-07-2022");
        verify(controller).addSchoolYear("550e8400-e29b-41d4-a716-446655440008","2022", "01-09-2022", "31-07-2023");
        verify(controller).addSchoolYear("550e8400-e29b-41d4-a716-446655440009","2023", "01-09-2023", "31-07-2024");
        verify(controller).addSchoolYear("550e8400-e29b-41d4-a716-446655440010","2024", "01-09-2024", "31-07-2025");
        verify(controller).addSchoolYear("550e8400-e29b-41d4-a716-446655440011","2025", "01-09-2025", "31-07-2026");
        verify(controller).addSchoolYear("550e8400-e29b-41d4-a716-446655440012","2026", "01-09-2026", "31-07-2027");
        verify(controller).addSchoolYear("550e8400-e29b-41d4-a716-446655440013","2027", "01-09-2027", "31-07-2028");
        verify(controller).addSchoolYear("550e8400-e29b-41d4-a716-446655440014","2028", "01-09-2028", "31-07-2029");
        verify(controller).addSchoolYear("550e8400-e29b-41d4-a716-446655440015","2029", "01-09-2029", "31-07-2030");
        verify(controller).addSchoolYear("550e8400-e29b-41d4-a716-446655440016","2030", "01-09-2030", "31-07-2031");

        verify(controller, times(16)).addSchoolYear(any(), any(), any(), any());
    }
}
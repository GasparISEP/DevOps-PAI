package PAI.initializer;

import PAI.VOs.*;
import PAI.domain.courseEdition.CourseEdition;
import PAI.service.courseEdition.ICreateCourseEditionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CourseEditionInitializerTest {

    @Mock
    private ICreateCourseEditionService courseEditionService;

    @InjectMocks
    private CourseEditionInitializer initializer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldProcessValidCSVLine() throws Exception {
        // Arrange
        String csvContent = "date,courseAcronym,courseName,field4,field5,schoolYearUUID,programmeAcronym,programmeName,field9\n" +
                "01-07-2023,ARIT,Arithmancy,,,22222222-2222-2222-2222-222222222222,DSD,Data Science,";
        
        InputStream inputStream = new ByteArrayInputStream(csvContent.getBytes());

        // Mock the resource stream
        CourseEditionInitializer spyInitializer = spy(initializer);
        doReturn(inputStream).when(spyInitializer).getResourceAsStream(anyString());

        CourseEdition courseEdition = mock(CourseEdition.class);
        when(courseEditionService.createAndSaveCourseEdition(any(), any())).thenReturn(courseEdition);

        // Act
        spyInitializer.init();

        // Assert
        verify(courseEditionService, times(1)).createAndSaveCourseEdition(any(), any());
    }

    @Test
    void shouldSkipInvalidCSVLine() throws Exception {
        // Arrange
        String csvContent = "date,courseAcronym,courseName,field4,field5,schoolYearUUID,programmeAcronym,programmeName,field9\n" +
                "invalid-date,ARIT,Arithmancy,,,22222222-2222-2222-2222-222222222222,DSD,Data Science,";
        
        InputStream inputStream = new ByteArrayInputStream(csvContent.getBytes());

        // Mock the resource stream
        CourseEditionInitializer spyInitializer = spy(initializer);
        doReturn(inputStream).when(spyInitializer).getResourceAsStream(anyString());

        // Act
        spyInitializer.init();

        // Assert
        verify(courseEditionService, never()).createAndSaveCourseEdition(any(), any());
    }

    @Test
    void shouldSkipEmptyLines() throws Exception {
        // Arrange
        String csvContent = "date,courseAcronym,courseName,field4,field5,schoolYearUUID,programmeAcronym,programmeName,field9\n" +
                "\n" +
                "01-07-2023,ARIT,Arithmancy,,,22222222-2222-2222-2222-222222222222,DSD,Data Science,";
        
        InputStream inputStream = new ByteArrayInputStream(csvContent.getBytes());

        // Mock the resource stream
        CourseEditionInitializer spyInitializer = spy(initializer);
        doReturn(inputStream).when(spyInitializer).getResourceAsStream(anyString());

        CourseEdition courseEdition = mock(CourseEdition.class);
        when(courseEditionService.createAndSaveCourseEdition(any(), any())).thenReturn(courseEdition);

        // Act
        spyInitializer.init();

        // Assert
        verify(courseEditionService, times(1)).createAndSaveCourseEdition(any(), any());
    }
}


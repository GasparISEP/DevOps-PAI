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
        String csvContent = "LOCAL_DATE,COURSEID_ACRONYM,COURSEID_NAME,EDITION_PROGRAMME_ACRONYM,EDITION_SCHOOL_YEAR,PROGRAMME_ACRONYM,PROGRAMME_NAME,TEACHER_ACRONYM\n" +
                "01-07-2023,ARIT,Arithmancy,DSD,550e8400-e29b-41d4-a716-446655440002,DSD,Data Science,PROF1";
        
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
        String csvContent = "LOCAL_DATE,COURSEID_ACRONYM,COURSEID_NAME,EDITION_PROGRAMME_ACRONYM,EDITION_SCHOOL_YEAR,PROGRAMME_ACRONYM,PROGRAMME_NAME,TEACHER_ACRONYM\n" +
                "01-07-2023,ARIT,Arithmancy,,,DSD,Data Science,PROF1";


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
        String csvContent = "LOCAL_DATE,COURSEID_ACRONYM,COURSEID_NAME,EDITION_PROGRAMME_ACRONYM,EDITION_SCHOOL_YEAR,PROGRAMME_ACRONYM,PROGRAMME_NAME,TEACHER_ACRONYM\n" +
                "" +
                "01-07-2023,ARIT,Arithmancy,DSD,550e8400-e29b-41d4-a716-446655440002,DSD,Data Science,PROF1\n";

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


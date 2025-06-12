package PAI.initializer;

import PAI.VOs.*;
import PAI.domain.courseEdition.CourseEdition;
import PAI.service.courseEdition.ICreateCourseEditionService;
import PAI.service.schoolYear.ISchoolYearService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CourseEditionInitializerTest {

    @Mock
    private ICreateCourseEditionService courseEditionService;

    @Mock
    private ISchoolYearService schoolYearService;

    private CourseEditionInitializer initializer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        initializer = new CourseEditionInitializer();
    }

    @Test
    void shouldProcessValidCSVLine() throws Exception {
        String csvContent = "LOCAL_DATE,COURSEID_ACRONYM,COURSEID_NAME,EDITION_PROGRAMME_ACRONYM,EDITION_SCHOOL_YEAR,PROGRAMME_ACRONYM,PROGRAMME_NAME,TEACHER_ACRONYM\n" +
                "01-07-2023,ARIT,Arithmancy,DSD,550e8400-e29b-41d4-a716-446655440002,DSD,Data Science,PROF1";

        Path tempFile = Files.createTempFile("test-course-edition", ".csv");
        Files.writeString(tempFile, csvContent);

        when(schoolYearService.getAllSchoolYearsIDs())
                .thenReturn(List.of(new SchoolYearID(UUID.fromString("550e8400-e29b-41d4-a716-446655440002"))));

        CourseEdition courseEdition = mock(CourseEdition.class);
        when(courseEditionService.createAndSaveCourseEdition(any(), any())).thenReturn(courseEdition);

        initializer.loadCourseEdition(courseEditionService, schoolYearService, tempFile);

        verify(courseEditionService, times(1)).createAndSaveCourseEdition(any(), any());

        Files.deleteIfExists(tempFile);
    }

    @Test
    void shouldSkipInvalidCSVLine() throws Exception {
        String csvContent = "LOCAL_DATE,COURSEID_ACRONYM,COURSEID_NAME,EDITION_PROGRAMME_ACRONYM,EDITION_SCHOOL_YEAR,PROGRAMME_ACRONYM,PROGRAMME_NAME,TEACHER_ACRONYM\n" +
                "01-07-2023,ARIT,Arithmancy,,,DSD,Data Science,PROF1";

        Path tempFile = Files.createTempFile("test-invalid-course-edition", ".csv");
        Files.writeString(tempFile, csvContent);

        when(schoolYearService.getAllSchoolYearsIDs())
                .thenReturn(List.of(new SchoolYearID(UUID.fromString("550e8400-e29b-41d4-a716-446655440002"))));

        initializer.loadCourseEdition(courseEditionService, schoolYearService, tempFile);

        verify(courseEditionService, never()).createAndSaveCourseEdition(any(), any());

        Files.deleteIfExists(tempFile);
    }

    @Test
    void shouldSkipEmptyLines() throws Exception {
        String csvContent = "LOCAL_DATE,COURSEID_ACRONYM,COURSEID_NAME,EDITION_PROGRAMME_ACRONYM,EDITION_SCHOOL_YEAR,PROGRAMME_ACRONYM,PROGRAMME_NAME,TEACHER_ACRONYM\n" +
                "\n" +
                "01-07-2023,ARIT,Arithmancy,DSD,550e8400-e29b-41d4-a716-446655440002,DSD,Data Science,PROF1";

        Path tempFile = Files.createTempFile("test-empty-lines-course-edition", ".csv");
        Files.writeString(tempFile, csvContent);

        when(schoolYearService.getAllSchoolYearsIDs())
                .thenReturn(List.of(new SchoolYearID(UUID.fromString("550e8400-e29b-41d4-a716-446655440002"))));

        CourseEdition courseEdition = mock(CourseEdition.class);
        when(courseEditionService.createAndSaveCourseEdition(any(), any())).thenReturn(courseEdition);

        initializer.loadCourseEdition(courseEditionService, schoolYearService, tempFile);

        verify(courseEditionService, times(1)).createAndSaveCourseEdition(any(), any());

        Files.deleteIfExists(tempFile);
    }
}

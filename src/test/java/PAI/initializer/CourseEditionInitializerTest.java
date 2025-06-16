package PAI.initializer;

import PAI.VOs.*;
import PAI.controller.US19_CreateCourseEditionController;
import PAI.domain.repositoryInterfaces.schoolYear.ISchoolYearRepository;
import PAI.domain.schoolYear.SchoolYear;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CourseEditionInitializerTest {

    @Mock
    private US19_CreateCourseEditionController controller;

    @Mock
    private ISchoolYearRepository schoolYearRepository;

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

        UUID schoolYearUUID = UUID.fromString("550e8400-e29b-41d4-a716-446655440002");
        SchoolYearID schoolYearID = new SchoolYearID(schoolYearUUID);
        when(schoolYearRepository.getAllSchoolYearsIDs())
                .thenReturn(List.of(schoolYearID));

        SchoolYear mockSchoolYear = mock(SchoolYear.class);
        Date mockStartDate = mock(Date.class);
        Date mockEndDate = mock(Date.class);
        when(mockStartDate.getLocalDate()).thenReturn(LocalDate.of(2023, 1, 1));
        when(mockEndDate.getLocalDate()).thenReturn(LocalDate.of(2023, 12, 31));

        when(mockSchoolYear.getStartDate()).thenReturn(mockStartDate);
        when(mockSchoolYear.getEndDate()).thenReturn(mockEndDate);
        when(schoolYearRepository.findBySchoolYearID(any()))
                .thenReturn(java.util.Optional.of(mockSchoolYear));

        when(controller.createCourseEdition(any(), any())).thenReturn(true);

        initializer.loadCourseEdition(controller, schoolYearRepository, tempFile.toString());

        verify(controller, times(1)).createCourseEdition(any(), any());

        Files.deleteIfExists(tempFile);
    }


    @Test
    void shouldSkipInvalidCSVLine() throws Exception {
        String csvContent = "LOCAL_DATE,COURSEID_ACRONYM,COURSEID_NAME,EDITION_PROGRAMME_ACRONYM,EDITION_SCHOOL_YEAR,PROGRAMME_ACRONYM,PROGRAMME_NAME,TEACHER_ACRONYM\n" +
                "01-07-2023,ARIT,Arithmancy,,,DSD,Data Science,PROF1";

        Path tempFile = Files.createTempFile("test-invalid-course-edition", ".csv");
        Files.writeString(tempFile, csvContent);

        when(schoolYearRepository.getAllSchoolYearsIDs())
                .thenReturn(List.of(new SchoolYearID(UUID.fromString("550e8400-e29b-41d4-a716-446655440002"))));

        initializer.loadCourseEdition(controller, schoolYearRepository, tempFile.toString());

        verify(controller, never()).createCourseEdition(any(), any());

        Files.deleteIfExists(tempFile);
    }
}
